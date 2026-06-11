package scgf.api.application.funcionario.validation;

import scgf.api.application.funcionario.dto.DtoCadastroFuncionario;

public interface ValidadorCadastroFuncionario {

    void validar(DtoCadastroFuncionario dados);
}
