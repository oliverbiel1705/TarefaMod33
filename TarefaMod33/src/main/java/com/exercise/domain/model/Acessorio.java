package com.exercise.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
@Entity
@Table(name = "tb_acessorio")
public class Acessorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToMany(mappedBy = "acessorios", fetch = FetchType.LAZY)
    private List<Carro> carros = new ArrayList<>();

    public Acessorio(String nome) {
        this.nome = nome;
    }

    public Acessorio(String nome, Carro carro) {
        this.nome = nome;
        this.carros.add(carro);
    }

    public void addCarro(Carro carro) {
        this.carros.add(carro);
    }

    public void removeCarro(Carro carro) {
        this.carros.remove(carro);
    }
}
