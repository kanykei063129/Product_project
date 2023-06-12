package peaksoft.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class SimpleResponse {
    private HttpStatus status;
    private String message;
}
