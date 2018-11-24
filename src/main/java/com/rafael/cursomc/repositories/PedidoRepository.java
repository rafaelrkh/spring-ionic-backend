package com.rafael.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.cursomc.domain.Clientes;
import com.rafael.cursomc.domain.Pedidos;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos, Integer>{

	@Transactional(readOnly=true)
	Page<Pedidos> findByCliente(Clientes cliente, Pageable pageRequest);
}
