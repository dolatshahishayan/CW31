package ir.maktabsharif.cw31.dto.author;

import ir.maktabsharif.cw31.model.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorFindResponse {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role;
    private Boolean isEnabled;
}
