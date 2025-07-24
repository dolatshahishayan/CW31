package ir.maktabsharif.cw31.service.author;

import ir.maktabsharif.cw31.dto.author.AuthorSaveUpdateRequest;
import ir.maktabsharif.cw31.model.Author;

public interface AuthorService {
    Author findByUsername(String username);

    Author save(AuthorSaveUpdateRequest request);

    boolean existsByUsername(String username);

    void delete(Integer authorId);
}
