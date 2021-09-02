package br.com.pocs.awsservices.pocawsservices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmeDto {

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("nome_diretor")
    private String nomeDiretor;

}
