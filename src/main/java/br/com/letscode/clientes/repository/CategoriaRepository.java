package br.com.letscode.clientes.repository;

import br.com.letscode.clientes.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByCode(String code);

    @Query("SELECT ct FROM Categoria ct JOIN FETCH ct.clientes cl ORDER BY cl.name DESC")
    List<Categoria> findCategoriaComUsuarios();

    @Query("SELECT ct FROM Categoria ct JOIN FETCH ct.clientes cl WHERE ct.uuid = categoriaUuid ORDER BY cl.name DESC")
    List<Categoria> findCategoriaByUUIDComUsuarios(@Param("categoriaUuid") String categoriaUuid);


}
