package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
public record BrandResponse(Long id,
                            String brandName,
                            String image) {

    public BrandResponse(Long id, String brandName, String image) {
        this.id = id;
        this.brandName = brandName;
        this.image = image;
    }
}
