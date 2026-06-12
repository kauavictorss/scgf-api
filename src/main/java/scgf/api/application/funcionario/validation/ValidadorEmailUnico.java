package scgf.api.application.funcionario.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import scgf.api.application.funcionario.dto.DtoCadastroFuncionario;
import scgf.api.infrastructure.repository.FuncionarioRepository;
import scgf.api.infrastructure.exception.RegraDeNegocioException;

@Component
@Order(4)
@RequiredArgsConstructor
public class ValidadorEmailUnico implements ValidadorCadastroFuncionario {

    private final FuncionarioRepository repositorio;

    @Override
    public void validar(DtoCadastroFuncionario dados) {
        if (repositorio.existsByEmail(dados.email())) {
            throw new RegraDeNegocioException("Email pertence a outro funcionário!");
        }
    }
}
