package com.sergiobonani.crudjavaionic.repositories;

import com.sergiobonani.crudjavaionic.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
