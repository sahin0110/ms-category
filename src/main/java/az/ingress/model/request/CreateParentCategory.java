package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateParentCategory {

    @NotBlank(message = "{validation.not.blank-parentCategory-name}")
    private String name;
}
