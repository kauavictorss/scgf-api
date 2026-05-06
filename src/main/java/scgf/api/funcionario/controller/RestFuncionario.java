package scgf.api.funcionario.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import scgf.api.conta.ContaTipo;
import scgf.api.funcionario.repository.RepoFuncionario;
import scgf.api.funcionario.dto.DtoAtualizarFuncionario;
import scgf.api.funcionario.dto.DtoCadastroFuncionario;
import scgf.api.funcionario.dto.DtoDetalhamentoFuncionario;
import scgf.api.especialidade.DtoEspecialidade;
import scgf.api.funcionario.dto.DtoListaFuncionarios;
import scgf.api.especialidade.Especialidade;
import scgf.api.funcionario.model.Funcionario;
import scgf.api.funcionario.service.ValidadorCadastroService;

import java.util.List;
import java.util.stream.Stream;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("funcionarios")
public class RestFuncionario {

    private final ValidadorCadastroService validador;
    private final RepoFuncionario repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoDetalhamentoFuncionario> cadastrar(@RequestBody @Valid DtoCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {
        log.info("Cadastrando funcionário: {}", dados);

        validador.validarCadastro(dados);
        var funcionario = new Funcionario(dados);
        validador.ajustarSalarioSeDesenvolvedor(funcionario);
        repositorio.save(funcionario);

        var uri = uriBuilder.path("/funcionarios/{cpf}").buildAndExpand(funcionario.getCpf()).toUri();

        log.info("Funcionário cadastrado com sucesso!");
        return ResponseEntity.created(uri).body(new DtoDetalhamentoFuncionario(funcionario));
    }

    @GetMapping("/listar/detalhes/{cpf}")
    public List<DtoDetalhamentoFuncionario> buscarDetalhesFuncionario(@PathVariable String cpf) {
        return repositorio.findById(cpf).stream().map(DtoDetalhamentoFuncionario::new).toList();
    }

    @GetMapping("/listar/ativos")
    public Page<DtoListaFuncionarios> listagemDeFuncionariosAtivos(@PageableDefault(sort = "nome") Pageable paginacao) {
        return repositorio.findAllByAtivo(paginacao).map(DtoListaFuncionarios::new);
    }

    @GetMapping("/listar/inativos")
    public Page<DtoListaFuncionarios>listaDeFuncionariosInativos(@PageableDefault(sort = "nome") Pageable paginacao) {
        return repositorio.findAllByInativo(paginacao).map(DtoListaFuncionarios::new);
    }

    @GetMapping("/cpf/{cpf}")
    public List<DtoListaFuncionarios> buscarPorCpf(@PathVariable String cpf) {
        return repositorio.findById(cpf).stream().map(DtoListaFuncionarios::new).toList();
    }

    @GetMapping("/email/{email}")
    public List<DtoListaFuncionarios> buscarEmailFuncionario(@PathVariable String email) {
        var funcionario = repositorio.findByEmail(email).stream().findFirst();
        String nomeFuncionario = funcionario.map(Funcionario::getNome).orElse("NÃO ENCONTRADO");
        log.info("Buscando funcionário {} com email: {}", nomeFuncionario, email);
        return repositorio.findByEmail(email).stream().map(DtoListaFuncionarios::new).toList();
    }

    @GetMapping("/dados-conta/{cpf}")
    public List<DtoDetalhamentoFuncionario> buscarDadosContaFuncionario(@PathVariable String cpf) {
        var funcionario = repositorio.findById(cpf);
        String nomeFuncionario = funcionario.map(Funcionario::getNome).orElse("NÃO ENCONTRADO");
        log.info("Buscando dados da conta do funcionário {} com CPF: {}", nomeFuncionario, cpf);
        return funcionario.stream()
            .map(f -> new DtoDetalhamentoFuncionario(f.getCpf(), f.getNome(), new DtoEspecialidade(f.getEspecialidade()), f.getConta()))
            .toList();
    }

    @GetMapping("/listar/especialidade/{especialidade}")
    public List<DtoListaFuncionarios> listarPorEspecialidade(@PathVariable String especialidade) {
        var especialidadeFiltro = Especialidade.from(especialidade);
        return repositorio.findByEspecialidade(especialidadeFiltro)
            .stream()
            .map(DtoListaFuncionarios::new)
            .toList();
    }

    @GetMapping("/listar/especialidades")
    public List<DtoEspecialidade> listarEspecialidades() {
        return Stream.of(Especialidade.values())
                .map(DtoEspecialidade::new)
                .toList();
    }

    @GetMapping("/listar/tipo-conta/{tpConta}")
    public List<DtoListaFuncionarios> listarFuncionariosPorTipoConta(@PathVariable String tpConta) {
        log.info("Listando funcionários por tipo de conta: {}", tpConta);
        return repositorio.findByContaTipo(List.of(ContaTipo.valueOf(tpConta)))
            .stream()
            .map(DtoListaFuncionarios::new)
            .toList();
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DtoDetalhamentoFuncionario> atualizarFuncionario(@RequestBody @Valid DtoAtualizarFuncionario dados) {
        var funcionario = repositorio.getReferenceById(dados.cpf());
        funcionario.atualizarDados(dados);
        log.info("Atualizando funcionário(a) {} com CPF: {}",funcionario.getNome(), dados.cpf());
        log.info("Dados atualizados: {}", dados);
        log.info("Funcionário atualizado com sucesso!");
        return ResponseEntity.ok(new DtoDetalhamentoFuncionario(funcionario));
    }

    @DeleteMapping("/remover/{cpf}")
    @Transactional
    public ResponseEntity<Void> removerFuncionario(@PathVariable String cpf) {
        var funcionario = repositorio.getReferenceById(cpf);
        funcionario.excuir();
        log.info("Removendo funcionário(a) {} com CPF: {}",funcionario.getNome(), cpf);
        log.info("Funcionário removido com sucesso!");
        return ResponseEntity.noContent().build();
    }
}
