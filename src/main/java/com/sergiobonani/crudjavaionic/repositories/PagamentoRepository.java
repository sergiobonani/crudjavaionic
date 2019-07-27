package com.sergiobonani.crudjavaionic.repositories;

import com.sergiobonani.crudjavaionic.domain.Categoria;
import com.sergiobonani.crudjavaionic.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
