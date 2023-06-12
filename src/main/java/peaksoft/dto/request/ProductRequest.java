package peaksoft.dto.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record ProductRequest(String name,
                             int price,
                             List<String>images,
                             String characteristic,
                             String madeIn,
                             Category category) {
}
