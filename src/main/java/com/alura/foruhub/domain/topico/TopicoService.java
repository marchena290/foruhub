package com.alura.foruhub.domain.topico;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Topico crear(Topico topico) {
        topico.setFechaCreacion(LocalDateTime.now());
        return repository.save(topico);
    }

    public List<Topico> listar() {
        return repository.findAll();
    }
}

