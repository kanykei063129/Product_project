package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.enums.Role;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String email;
}
