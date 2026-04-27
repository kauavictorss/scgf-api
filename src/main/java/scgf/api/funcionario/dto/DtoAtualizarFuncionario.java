package scgf.api.funcionario.dto;

import jakarta.validation.constraints.NotBlank;
import scgf.api.conta.DtoConta;
import scgf.api.endereco.DtoEndereco;
import scgf.api.funcionario.model.Especialidade;

public record DtoAtualizarFuncionario(@NotBlank String cpf, String nome, Integer idade, String email, Especialidade especialidade, DtoConta conta, DtoEndereco endereco) {
}
