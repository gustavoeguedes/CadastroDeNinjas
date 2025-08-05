package dev.java10X.CadastroDeNinjas.Ninjas;

import dev.java10X.CadastroDeNinjas.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private Long id;

    private String nome;


    private String email;

    private int idade;


    private String imageUrl;

    private MissoesModel missoes;
    private String rank;

}
