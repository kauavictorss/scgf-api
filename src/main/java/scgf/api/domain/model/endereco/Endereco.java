package scgf.api.domain.model.endereco;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scgf.api.application.dto.endereco.DtoEndereco;
import scgf.api.domain.enums.endereco.Estado;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Endereco {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;

    @Enumerated(EnumType.STRING)
    private Estado uf;

    private String numero;
    private String complemento;

    public Endereco(DtoEndereco dados) {
        this.cep = dados.cep();
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarEndereco(DtoEndereco endereco) {
        if (endereco.cep() != null) {
            this.cep = endereco.cep();
        }
        if (endereco.logradouro() != null) {
            this.logradouro = endereco.logradouro();
        }
        if (endereco.bairro() != null) {
            this.bairro = endereco.bairro();
        }
        if (endereco.cidade() != null) {
            this.cidade = endereco.cidade();
        }
        if (endereco.uf() != null) {
            this.uf = endereco.uf();
        }
        if (endereco.numero() != null) {
            this.numero = endereco.numero();
        }
        if (endereco.complemento() != null) {
            this.complemento = endereco.complemento();
        }
    }
}
