package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateParentCategory {

    @NotBlank(message = "validation.not.blank-parentCategory-name")
    private String name;
}
