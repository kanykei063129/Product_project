package peaksoft.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.enums.Role;

@Builder
@Data
@AllArgsConstructor
public class SignUpRequest {
    @NotNull(message = "FirstName must not be empty")
    private String firstName;
    @NotNull(message = "LastName must not be empty")
    private String lastName;
    @NotNull(message = "Email must not be empty")
    @Column(unique = true)
    private String email;
    @NotNull(message = "Password must not be empty")
    private String password;
}
