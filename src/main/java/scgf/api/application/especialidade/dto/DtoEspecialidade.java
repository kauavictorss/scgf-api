package scgf.api.application.especialidade.dto;

import scgf.api.domain.especialidade.Especialidade;

public record DtoEspecialidade(String codigo, String descricao) {
    public DtoEspecialidade(Especialidade especialidade) {
        this(especialidade.getCodigo(), especialidade.getDescricaoExibicao());
    }
}
