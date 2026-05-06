package scgf.api.especialidade;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Locale;

@Getter
public enum Especialidade {
    // TI:
    DESENVOLVEDOR("Desenvolvedor(a)"),
    CIBERSEGURANCA("Cibersegurança"),
    ENGENHARIA_DE_SOFTWARE("Engenharia de Software"),
    TEC_INFORMATICA("Técnico em Informática"),

    // Engenharias:
    ENGENHARIA_CIVIL("Engenharia Civil"),
    ENGENHARIA_ELETRICA("Engenharia Elétrica"),
    ENGENHARIA_MECANICA("Engenharia Mecânica"),

    // Saúde:
    PSICOLOGIA("Psicologia"),
    ODONTOLOGIA("Odontologia"),
    NUTRICAO("Nutrição"),
    MEDICINA("Medicina"),
    TEC_ENFERMAGEM("Técnico em Enfermagem"),

    // Outros:
    PEDAGOGIA("Pedagogia"),
    ADMINISTRACAO("Administração"),
    CONTABILIDADE("Contabilidade"),
    TEC_REFRIGERACAO("Técnico em Refrigeração"),

    // Direitos,
    DIREITO("Direito"),
    ADVOCACIA("Advogado(a)"),
    JUIZ_DIREITO("Juiz de Direito"),
    JUIZ_ELEITORAL("Juiz Eleitoral");

    private final String descricao;

    Especialidade(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public boolean isAreaTi() {
        return this == DESENVOLVEDOR
            || this == CIBERSEGURANCA
            || this == ENGENHARIA_DE_SOFTWARE
            || this == TEC_INFORMATICA;
    }

    public String getDescricaoExibicao() {
        return isAreaTi() ? descricao + " (TI)" : descricao;
    }

    @JsonCreator
    public static Especialidade from(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Especialidade inválida: null");
        }

        var texto = valor.trim();
        for (var especialidade : values()) {
            if (especialidade.name().equalsIgnoreCase(texto)
                || especialidade.descricao.equalsIgnoreCase(texto)
                || especialidade.getDescricaoExibicao().equalsIgnoreCase(texto)) {
                return especialidade;
            }
        }

        var nomeConvertidoConst = texto.toUpperCase(Locale.ROOT).replace(' ', '_');
        for (var especialidade : values()) {
            if (especialidade.name().equals(nomeConvertidoConst)) {
                return especialidade;
            }
        }

        throw new IllegalArgumentException("Especialidade inválida: " + valor);
    }
}
