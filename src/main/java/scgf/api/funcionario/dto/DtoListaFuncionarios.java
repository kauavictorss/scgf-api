package scgf.api.funcionario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import scgf.api.funcionario.model.Funcionario;
import scgf.api.funcionario.util.CpfUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DtoListaFuncionarios(String cpf, String nome, String email, DtoEspecialidade especialidade) {

    public DtoListaFuncionarios(Funcionario funcionario) {
        this(CpfUtils.formatarParaExibicao(funcionario.getCpf()), funcionario.getNome(), funcionario.getEmail(), new DtoEspecialidade(funcionario.getEspecialidade()));
    }
}
