package ir.maktabsharif.cw31.controller;

import ir.maktabsharif.cw31.dto.author.AuthorFindResponse;
import ir.maktabsharif.cw31.dto.author.AuthorSaveUpdateRequest;
import ir.maktabsharif.cw31.mapper.AuthorMapper;
import ir.maktabsharif.cw31.model.Author;
import ir.maktabsharif.cw31.service.author.AuthorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;


    @PostMapping("/save")
    public ResponseEntity<AuthorFindResponse> saveAuthor(@RequestBody AuthorSaveUpdateRequest request) {
        Author save = authorService.save(request);
        return ResponseEntity.ok(authorMapper.mapToResponse(save));
    }
}
