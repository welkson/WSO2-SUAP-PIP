package br.edu.ifrn.pip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoAtributo {
    private String sujeito;
    private String recurso;
    private String acao;
    private String ambiente;
    private String nomeAtributo;
    private String emissor;
}
