package ir.maktabsharif.cw31.repository;

import ir.maktabsharif.cw31.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByUsername(String username);
    boolean existsByUsername(String username);
}
