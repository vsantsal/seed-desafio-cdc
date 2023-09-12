package com.deveficiente.desafiocdc.domain.repository;

import com.deveficiente.desafiocdc.domain.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
