package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.entity.Ingrediente;
import main.entity.Sabor;
import main.entity.SaborTemIngrediente;

public interface SaborTemIngredienteRepository extends JpaRepository<SaborTemIngrediente, Long> {
	@Query("SELECT p.sabor FROM SaborTemIngrediente p WHERE p.ingrediente.id = :ingredienteId")
    List<Sabor> findSaborByIngredienteId(@Param("ingredienteId") Long ingredienteId);
	
	@Query("SELECT p.sabor FROM SaborTemIngrediente p WHERE p.sabor.id = :saborId")
    List<Ingrediente> findIngredienteBySaborId(@Param("saborId") Long saborId);
}


