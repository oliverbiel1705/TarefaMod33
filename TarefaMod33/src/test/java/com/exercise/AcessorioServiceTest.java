package com.exercise;

import com.exercise.domain.model.Acessorio;
import com.exercise.domain.model.Carro;
import com.exercise.domain.model.Marca;
import com.exercise.domain.repository.AcessorioRepository;
import com.exercise.service.impl.AcessorioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AcessorioServiceTest {
    @Autowired
    private AcessorioRepository repository;
    @Autowired
    private AcessorioService service;

    @Test
    void saveAndFindAllTest() {
        Marca chevrolet = new Marca("Chevrolet");
        Carro chevette = new Carro("Chevette", chevrolet);
        Acessorio turbo = new Acessorio("Turbo", chevette);
        Acessorio injecaoModificada = new Acessorio("Injecao Modificada", chevette);

        service.save(turbo);
        service.save(injecaoModificada);

        List<Acessorio> acessorios = service.findAll();

        assertEquals(2, acessorios.size());
    }

    @Test
    void findByIdAndDeleteTest() {
        Acessorio fume = service.save(new Acessorio("Vidro fume"));

        Acessorio acessorioBuscado = service.findById(fume.getId());

        assertNotNull(acessorioBuscado);
        assertEquals(fume.getId(), acessorioBuscado.getId());
        assertEquals(fume.getNome(), acessorioBuscado.getNome());

        service.delete(fume.getId());

        assertFalse(repository.existsById(fume.getId()));
    }
}
