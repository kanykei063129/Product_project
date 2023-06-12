package peaksoft.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
public record CommentRequest(String comment){
}
