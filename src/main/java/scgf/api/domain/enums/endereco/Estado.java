package scgf.api.domain.enums.endereco;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.text.Normalizer;

@Getter
public enum Estado {
    AC("Acre"),
    AL("Alagoas"),
    AP("Amapá"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceará"),
    DF("Distrito Federal"),
    ES("Espírito Santo"),
    GO("Goiás"),
    MA("Maranhão"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Pará"),
    PB("Paraíba"),
    PR("Paraná"),
    PE("Pernambuco"),
    PI("Piauí"),
    RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"),
    RS("Rio Grande do Sul"),
    RO("Rondônia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SP("São Paulo"),
    SE("Sergipe"),
    TO("Tocantins");

    private final String nome;

    Estado(String nome) {
        this.nome = nome;
    }

    @JsonCreator
    public static Estado fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        String normalizedValue = normalize(value);

        for (Estado estado : Estado.values()) {
            if (estado.name().equalsIgnoreCase(normalizedValue) || normalize(estado.nome).equalsIgnoreCase(normalizedValue)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + value);
    }

    private static String normalize(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .trim();
    }
}
