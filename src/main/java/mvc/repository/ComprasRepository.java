package mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mvc.model.Compras;

public interface ComprasRepository extends JpaRepository<Compras, Long> {

}
