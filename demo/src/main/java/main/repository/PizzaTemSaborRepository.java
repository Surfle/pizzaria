package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.entity.Pizza;
import main.entity.PizzaTemSabor;
import main.entity.Sabor;

public interface PizzaTemSaborRepository extends JpaRepository<PizzaTemSabor, Long> {
	@Query("SELECT p.pizza FROM PizzaTemSabor p WHERE p.sabor.id = :saborId")
    List<Pizza> findPizzaBySaborId(@Param("saborId") Long saborId);
	
	@Query("SELECT p.sabor FROM PizzaTemSabor p WHERE p.pizza.id = :pizzaId")
    List<Sabor> findSaborByPizzaId(@Param("pizzaId") Long pizzaId);
}
