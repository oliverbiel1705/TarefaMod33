package com.exercise.service.impl;

import com.exercise.domain.model.Acessorio;
import com.exercise.domain.model.Carro;
import com.exercise.domain.repository.CarroRepository;
import com.exercise.service.ICrud;
import com.exercise.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService implements ICrud<Carro, Long, Acessorio> {
    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Carro> findAll() {
        return repository.findAll();
    }

    @Override
    public Carro findById(Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Carro save(Carro carro) {
        return repository.save(carro);
    }

    @Override
    public void delete(Long id) {
        var carro = this.findById(id);
        repository.delete(carro);
    }

    @Override
    public Carro add(Long id, Acessorio acessorio) {
        var carro = this.findById(id);
        carro.addAcessorio(acessorio);
        return repository.save(carro);
    }

    @Override
    public Carro remove(Long id, Acessorio acessorio) {
        var carro = this.findById(id);
        carro.removeAcessorio(acessorio);
        return repository.save(carro);
    }
}
