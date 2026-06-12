package scgf.api.application.dto.especialidade;

import scgf.api.domain.enums.especialidade.Especialidade;

public record DtoEspecialidade(String codigo, String descricao) {
    public DtoEspecialidade(Especialidade especialidade) {
        this(especialidade.getCodigo(), especialidade.getDescricaoExibicao());
    }
}
