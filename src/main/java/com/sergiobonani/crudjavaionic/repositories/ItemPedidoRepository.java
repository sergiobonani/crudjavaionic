package com.sergiobonani.crudjavaionic.repositories;

import com.sergiobonani.crudjavaionic.domain.Categoria;
import com.sergiobonani.crudjavaionic.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
