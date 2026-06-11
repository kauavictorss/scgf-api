package scgf.api.application.funcionario.dto;

import jakarta.validation.constraints.NotBlank;
import scgf.api.application.conta.dto.DtoConta;
import scgf.api.application.endereco.dto.DtoEndereco;
import scgf.api.domain.especialidade.Especialidade;

public record DtoAtualizarFuncionario(@NotBlank String cpf, String nome, Integer idade, String email, Especialidade especialidade, DtoConta conta, DtoEndereco endereco) {
}
