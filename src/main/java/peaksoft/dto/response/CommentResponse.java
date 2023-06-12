package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
public record CommentResponse(Long id,
                              String comment,
                              ZonedDateTime createdDate) {

    public CommentResponse(Long id, String comment, ZonedDateTime createdDate) {
        this.id = id;
        this.comment = comment;
        this.createdDate = createdDate;
    }
}
