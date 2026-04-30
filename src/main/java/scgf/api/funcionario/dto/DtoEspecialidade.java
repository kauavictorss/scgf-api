package scgf.api.funcionario.dto;

import scgf.api.funcionario.model.Especialidade;

public record DtoEspecialidade(String codigo, String descricao) {
    public DtoEspecialidade(Especialidade especialidade) {
        this(especialidade.getCodigo(), especialidade.getDescricaoExibicao());
    }
}
