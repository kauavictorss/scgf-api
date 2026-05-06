package scgf.api.funcionario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import scgf.api.especialidade.DtoEspecialidade;
import scgf.api.funcionario.model.Funcionario;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DtoListaFuncionarios(String cpf, String nome, String email, DtoEspecialidade especialidade) {

    public DtoListaFuncionarios(Funcionario funcionario) {
        this(funcionario.getCpf(), funcionario.getNome(), funcionario.getEmail(), new DtoEspecialidade(funcionario.getEspecialidade()));
    }
}
