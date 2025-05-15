package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    ALREADY_EXISTS("Already exists"),
    UNEXPECTED_ERROR("Unexpected error occurred"),
    CATEGORY_NOT_FOUND("Category not found");

    private final String message;
}