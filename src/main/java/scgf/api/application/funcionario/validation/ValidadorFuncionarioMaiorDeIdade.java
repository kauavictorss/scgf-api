package scgf.api.application.funcionario.validation;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import scgf.api.application.funcionario.dto.DtoCadastroFuncionario;
import scgf.api.infrastructure.exception.RegraDeNegocioException;

@Component
@Order(1)
public class ValidadorFuncionarioMaiorDeIdade implements ValidadorCadastroFuncionario {

    @Override
    public void validar(DtoCadastroFuncionario dados) {
        if (dados.idade() != null && dados.idade() < 18) {
            throw new RegraDeNegocioException("Funcionário menor de idade!");
        }
    }
}
