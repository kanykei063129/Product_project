package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

import java.time.ZonedDateTime;
import java.util.List;
@Builder
public record UserResponse(Long id,
                           String firstName,
                           String lastName,
                           String email) {

    public UserResponse(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
