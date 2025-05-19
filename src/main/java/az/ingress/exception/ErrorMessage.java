package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    CATEGORY_ALREADY_EXISTS("Category already exists with id: %s"),
    UNEXPECTED_ERROR("Unexpected error occurred"),
    CATEGORY_NOT_FOUND("Category not found with id: %s");

    private final String code;
}