package scgf.api.application.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import scgf.api.domain.enums.endereco.Estado;

public record DtoEndereco(
        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
        String cep,

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotNull(message = "UF é obrigatória")
        Estado uf,

        String numero,
        String complemento) {
}
