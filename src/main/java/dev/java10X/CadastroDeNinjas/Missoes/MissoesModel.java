package dev.java10X.CadastroDeNinjas.Missoes;

import dev.java10X.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_missoes")
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDaMissao;
    private String dificuldade;


    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninjas;
}
