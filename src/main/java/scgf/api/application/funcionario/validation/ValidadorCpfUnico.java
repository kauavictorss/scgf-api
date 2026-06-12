package scgf.api.application.funcionario.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import scgf.api.application.funcionario.dto.DtoCadastroFuncionario;
import scgf.api.infrastructure.repository.FuncionarioRepository;
import scgf.api.infrastructure.exception.RegraDeNegocioException;

@Component
@Order(2)
@RequiredArgsConstructor
public class ValidadorCpfUnico implements ValidadorCadastroFuncionario {

    private final FuncionarioRepository repositorio;

    @Override
    public void validar(DtoCadastroFuncionario dados) {
        if (repositorio.existsById(dados.cpf())) {
            throw new RegraDeNegocioException("CPF já cadastrado!");
        }
    }
}
