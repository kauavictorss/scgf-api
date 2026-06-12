package scgf.api.application.dto.funcionario;

import jakarta.validation.constraints.NotBlank;
import scgf.api.application.dto.conta.DtoConta;
import scgf.api.application.dto.endereco.DtoEndereco;
import scgf.api.domain.enums.especialidade.Especialidade;

public record DtoAtualizarFuncionario(@NotBlank String cpf, String nome, Integer idade, String email, Especialidade especialidade, DtoConta conta, DtoEndereco endereco) {
}
