package scgf.api.application.funcionario.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import scgf.api.application.funcionario.dto.DtoCadastroFuncionario;
import scgf.api.infrastructure.funcionario.repository.RepoFuncionario;
import scgf.api.infrastructure.exception.RegraDeNegocioException;

@Component
@Order(3)
@RequiredArgsConstructor
public class ValidadorContaUnica implements ValidadorCadastroFuncionario {

    private final RepoFuncionario repositorio;

    @Override
    public void validar(DtoCadastroFuncionario dados) {
        if (dados.conta() != null && repositorio.existsByContaNumConta(dados.conta().numConta())) {
            throw new RegraDeNegocioException("Número da conta pertence a outro funcionário!");
        }
    }
}
