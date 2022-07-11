package com.ewecarreira.pedidos.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.ewecarreira.pedidos.domain.entity.Categoria;
import com.ewecarreira.pedidos.domain.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT " +
            " obj " +
            "FROM Produto " +
            " obj " +
            "INNER JOIN " +
            " obj.categorias cat " +
            "WHERE " +
            " obj.nome LIKE %:nome% " +
            " AND cat IN :categorias")
    Page<Produto> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}