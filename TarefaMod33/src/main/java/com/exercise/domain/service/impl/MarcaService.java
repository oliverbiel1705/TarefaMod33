package com.exercise.service.impl;

import com.exercise.domain.model.Carro;
import com.exercise.domain.model.Marca;
import com.exercise.domain.repository.MarcaRepository;
import com.exercise.service.ICrud;
import com.exercise.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService implements ICrud<Marca, Long, Carro> {
    private final MarcaRepository repository;

    public MarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Marca> findAll() {
        return repository.findAll();
    }

    @Override
    public Marca findById(Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Marca save(Marca marca) {
        return repository.save(marca);
    }

    @Override
    public void delete(Long id) {
        var marca = this.findById(id);
        repository.delete(marca);
    }

    @Override
    public Marca add(Long id, Carro carro) {
        var marca = this.findById(id);
        marca.addCarro(carro);
        return repository.save(marca);
    }

    @Override
    public Marca remove(Long id, Carro carro) {
        var marca = this.findById(id);
        marca.removeCarro(carro);
        return repository.save(marca);
    }
}
