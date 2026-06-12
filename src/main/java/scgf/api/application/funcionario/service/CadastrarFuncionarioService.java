package scgf.api.application.funcionario.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import scgf.api.application.funcionario.dto.DtoCadastroFuncionario;
import scgf.api.application.funcionario.dto.DtoDetalhamentoFuncionario;
import scgf.api.domain.funcionario.Funcionario;
import scgf.api.infrastructure.repository.FuncionarioRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CadastrarFuncionarioService {

    private final ValidadorCadastroService validadorCadastroService;
    private final FuncionarioRepository repositorio;

    @Transactional
    public DtoDetalhamentoFuncionario cadastrar(DtoCadastroFuncionario dados) {
        validadorCadastroService.validarCadastro(dados);

        var funcionario = new Funcionario(dados);
        validadorCadastroService.ajustarSalarioSeDesenvolvedor(funcionario);
        repositorio.save(funcionario);

        log.info("Funcionário cadastrado: {}", funcionario.getCpf());
        return new DtoDetalhamentoFuncionario(funcionario);
    }
}
