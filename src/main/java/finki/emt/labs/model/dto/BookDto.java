package finki.emt.labs.model.dto;

import finki.emt.labs.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private String name;
    private Category category;
    private Long authorId;
    private Integer availableCopies;
}
