package scgf.api.funcionario.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import scgf.api.conta.DtoConta;
import scgf.api.endereco.DtoEndereco;
import scgf.api.funcionario.model.Especialidade;

public record DtoCadastroFuncionario(

        @NotBlank @Pattern(regexp = "(\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})", message = "CPF deve conter 11 dígitos ou estar no formato 000.000.000-00")
        String cpf,

        @NotBlank
        String nome,

        @NotNull(message = "Idade é obrigatória")
        Integer idade,

        @NotBlank
        @Email
        String email,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        DtoConta conta,

        @NotNull
        @Valid
        DtoEndereco endereco) {
}
