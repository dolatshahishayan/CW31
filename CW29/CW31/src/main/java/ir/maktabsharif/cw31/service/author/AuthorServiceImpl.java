package ir.maktabsharif.cw31.service.author;

import ir.maktabsharif.cw31.dto.author.AuthorSaveUpdateRequest;
import ir.maktabsharif.cw31.exception.UserWithSameUsernameExistsException;
import ir.maktabsharif.cw31.mapper.AuthorMapper;
import ir.maktabsharif.cw31.model.Author;
import ir.maktabsharif.cw31.model.enums.Role;
import ir.maktabsharif.cw31.repository.AuthorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Author save(AuthorSaveUpdateRequest request) {
        if (existsByUsername(request.getUsername())){
            throw new UserWithSameUsernameExistsException();
        }
        Author author = authorMapper.mapToEntity(request);
        author.setPassword(passwordEncoder.encode(request.getPassword()));
        author.setRole(Role.ROLE_AUTHOR);
        return authorRepository.save(author);
    }

    @Override
    public boolean existsByUsername(String username) {
        return authorRepository.existsByUsername(username);
    }

    @Override
    public void delete(Integer authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public Author findByUsername(String username) {
        return authorRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
    }
}
