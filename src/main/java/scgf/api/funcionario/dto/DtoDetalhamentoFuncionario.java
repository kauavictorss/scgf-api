package scgf.api.funcionario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import scgf.api.conta.Conta;
import scgf.api.endereco.Endereco;
import scgf.api.funcionario.model.Funcionario;
import scgf.api.funcionario.util.CpfUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DtoDetalhamentoFuncionario(String cpf, String nome, Integer idade, String email, DtoEspecialidade especialidade, Conta conta, Endereco endereco) {
    public DtoDetalhamentoFuncionario (Funcionario funcionario) {
        this(CpfUtils.formatarParaExibicao(funcionario.getCpf()), funcionario.getNome(), funcionario.getIdade(), funcionario.getEmail(), new DtoEspecialidade(funcionario.getEspecialidade()), funcionario.getConta(), funcionario.getEndereco());
    }

    public DtoDetalhamentoFuncionario(String cpf, String nome, DtoEspecialidade especialidade, Conta conta) {
        this(CpfUtils.formatarParaExibicao(cpf), nome, null, null, especialidade, conta, null);
    }
}
