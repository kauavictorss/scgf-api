package scgf.api.funcionario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import scgf.api.funcionario.model.Especialidade;
import scgf.api.funcionario.model.Funcionario;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DtoListaFuncionarios(String cpf, String nome, String email, Especialidade especialidade) {

    public DtoListaFuncionarios(Funcionario funcionario) {
        this(funcionario.getCpf(), funcionario.getNome(), funcionario.getEmail(), funcionario.getEspecialidade());
    }
}
