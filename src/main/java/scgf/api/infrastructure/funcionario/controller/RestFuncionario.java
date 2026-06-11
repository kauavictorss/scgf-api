package scgf.api.infrastructure.funcionario.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import scgf.api.application.funcionario.dto.DtoAtualizarFuncionario;
import scgf.api.application.funcionario.dto.DtoCadastroFuncionario;
import scgf.api.application.funcionario.dto.DtoDetalhamentoFuncionario;
import scgf.api.application.especialidade.dto.DtoEspecialidade;
import scgf.api.application.funcionario.dto.DtoListaFuncionarios;
import scgf.api.application.funcionario.service.AtualizarFuncionarioService;
import scgf.api.application.funcionario.service.CadastrarFuncionarioService;
import scgf.api.application.funcionario.service.ConsultarFuncionarioService;
import scgf.api.application.funcionario.service.RemoverFuncionarioService;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("funcionarios")
public class RestFuncionario {

    private final CadastrarFuncionarioService cadastrarFuncionarioService;
    private final ConsultarFuncionarioService consultarFuncionarioService;
    private final AtualizarFuncionarioService atualizarFuncionarioService;
    private final RemoverFuncionarioService removerFuncionarioService;

    @PostMapping
    public ResponseEntity<DtoDetalhamentoFuncionario> cadastrar(@RequestBody @Valid DtoCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {
        log.info("Cadastrando funcionário: {}", dados);

        var funcionario = cadastrarFuncionarioService.cadastrar(dados);
        var uri = uriBuilder.path("/funcionarios/{cpf}").buildAndExpand(funcionario.cpf()).toUri();

        log.info("Funcionário cadastrado com sucesso!");
        return ResponseEntity.created(uri).body(funcionario);
    }

    @GetMapping("/listar/detalhes/{cpf}")
    public ResponseEntity<DtoDetalhamentoFuncionario> buscarDetalhesFuncionario(@PathVariable String cpf) {
        return ResponseEntity.ok(consultarFuncionarioService.buscarDetalhesPorCpf(cpf));
    }

    @GetMapping("/listar/ativos")
    public Page<DtoListaFuncionarios> listagemDeFuncionariosAtivos(@PageableDefault(sort = "nome") Pageable paginacao) {
        return consultarFuncionarioService.listarAtivos(paginacao);
    }

    @GetMapping("/listar/inativos")
    public Page<DtoListaFuncionarios>listaDeFuncionariosInativos(@PageableDefault(sort = "nome") Pageable paginacao) {
        return consultarFuncionarioService.listarInativos(paginacao);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<DtoListaFuncionarios> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(consultarFuncionarioService.buscarPorCpf(cpf));
    }

    @GetMapping("/email/{email}")
    public List<DtoListaFuncionarios> buscarEmailFuncionario(@PathVariable String email) {
        return consultarFuncionarioService.buscarPorEmail(email);
    }

    @GetMapping("/dados-conta/{cpf}")
    public ResponseEntity<DtoDetalhamentoFuncionario> buscarDadosContaFuncionario(@PathVariable String cpf) {
        return ResponseEntity.ok(consultarFuncionarioService.buscarDadosContaPorCpf(cpf));
    }

    @GetMapping("/listar/especialidade/{especialidade}")
    public List<DtoListaFuncionarios> listarPorEspecialidade(@PathVariable String especialidade) {
        return consultarFuncionarioService.listarPorEspecialidade(especialidade);
    }

    @GetMapping("/listar/especialidades")
    public List<DtoEspecialidade> listarEspecialidades() {
        return consultarFuncionarioService.listarEspecialidades();
    }

    @GetMapping("/listar/tipo-conta/{tpConta}")
    public List<DtoListaFuncionarios> listarFuncionariosPorTipoConta(@PathVariable String tpConta) {
        return consultarFuncionarioService.listarPorTipoConta(tpConta);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<DtoDetalhamentoFuncionario> atualizarFuncionario(@RequestBody @Valid DtoAtualizarFuncionario dados) {
        return ResponseEntity.ok(atualizarFuncionarioService.atualizar(dados));
    }

    @DeleteMapping("/remover/{cpf}")
    public ResponseEntity<Void> removerFuncionario(@PathVariable String cpf) {
        removerFuncionarioService.remover(cpf);
        return ResponseEntity.noContent().build();
    }
}
