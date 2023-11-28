package mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mvc.model.Livros;

public interface LivrosRepository extends JpaRepository<Livros, Long> {

}
