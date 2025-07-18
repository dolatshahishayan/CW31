package ir.maktabsharif.cw31.dto.author;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorSaveUpdateRequest {
    @NotBlank(groups = ValidationGroup.Update.class)
    private Integer id;
    @NotBlank(groups = ValidationGroup.Save.class)
    private String firstName;
    @NotBlank(groups = ValidationGroup.Save.class)
    private String lastName;
    @NotBlank(groups = ValidationGroup.Save.class)
    private String username;
    @NotBlank(groups = ValidationGroup.Save.class)
    private String password;
}
