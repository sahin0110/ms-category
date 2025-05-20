package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubCategoryRequest {

    @NotNull(message = "{validation.not.null-category-id}")
    private Long parentId;
    @NotBlank(message = "{validation.not.blank-subCategory-name}")
    private String name;
}
