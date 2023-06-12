package peaksoft.dto.request;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


@Builder
public record UserRequest(
         String firstName,
         String lastName,
         String email,
        String password){

}