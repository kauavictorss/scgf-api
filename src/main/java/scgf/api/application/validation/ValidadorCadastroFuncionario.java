package scgf.api.application.validation;

import scgf.api.application.dto.funcionario.DtoCadastroFuncionario;

public interface ValidadorCadastroFuncionario {

    void validar(DtoCadastroFuncionario dados);
}
