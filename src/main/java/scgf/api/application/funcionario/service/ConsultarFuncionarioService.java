package scgf.api.application.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import scgf.api.domain.conta.ContaTipo;
import scgf.api.application.especialidade.dto.DtoEspecialidade;
import scgf.api.domain.especialidade.Especialidade;
import scgf.api.application.funcionario.dto.DtoDetalhamentoFuncionario;
import scgf.api.application.funcionario.dto.DtoListaFuncionarios;
import scgf.api.domain.funcionario.Funcionario;
import scgf.api.infrastructure.repository.FuncionarioRepository;
import scgf.api.infrastructure.exception.RecursoNaoEncontradoException;

import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsultarFuncionarioService {

    private final FuncionarioRepository repositorio;

    public DtoDetalhamentoFuncionario buscarDetalhesPorCpf(String cpf) {
        var funcionario = buscarFuncionarioPorCpf(cpf);
        return new DtoDetalhamentoFuncionario(funcionario);
    }

    public Page<DtoListaFuncionarios> listarAtivos(Pageable paginacao) {
        return repositorio.findAllByAtivo(paginacao).map(DtoListaFuncionarios::new);
    }

    public Page<DtoListaFuncionarios> listarInativos(Pageable paginacao) {
        return repositorio.findAllByInativo(paginacao).map(DtoListaFuncionarios::new);
    }

    public DtoListaFuncionarios buscarPorCpf(String cpf) {
        return new DtoListaFuncionarios(buscarFuncionarioPorCpf(cpf));
    }

    public List<DtoListaFuncionarios> buscarPorEmail(String email) {
        var funcionarios = repositorio.findByEmail(email);
        var nomeFuncionario = funcionarios.stream()
            .findFirst()
            .map(Funcionario::getNome)
            .orElse("NÃO ENCONTRADO");

        log.info("Buscando funcionário {} com email: {}", nomeFuncionario, email);

        return funcionarios.stream().map(DtoListaFuncionarios::new).toList();
    }

    public DtoDetalhamentoFuncionario buscarDadosContaPorCpf(String cpf) {
        var funcionario = buscarFuncionarioPorCpf(cpf);
        log.info("Buscando dados da conta do funcionário {} com CPF: {}", funcionario.getNome(), cpf);

        return new DtoDetalhamentoFuncionario(
            funcionario.getCpf(),
            funcionario.getNome(),
            new DtoEspecialidade(funcionario.getEspecialidade()),
            funcionario.getConta()
        );
    }

    public List<DtoListaFuncionarios> listarPorEspecialidade(String especialidade) {
        var especialidadeFiltro = Especialidade.from(especialidade);

        return repositorio.findByEspecialidade(especialidadeFiltro)
            .stream()
            .map(DtoListaFuncionarios::new)
            .toList();
    }

    public List<DtoEspecialidade> listarEspecialidades() {
        return Stream.of(Especialidade.values())
            .map(DtoEspecialidade::new)
            .toList();
    }

    public List<DtoListaFuncionarios> listarPorTipoConta(String tpConta) {
        log.info("Listando funcionários por tipo de conta: {}", tpConta);

        return repositorio.findByContaTipo(List.of(ContaTipo.valueOf(tpConta)))
            .stream()
            .map(DtoListaFuncionarios::new)
            .toList();
    }

    private Funcionario buscarFuncionarioPorCpf(String cpf) {
        return repositorio.findById(cpf)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado!"));
    }
}
