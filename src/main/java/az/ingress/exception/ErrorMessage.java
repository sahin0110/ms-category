package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    CATEGORY_NAME_ALREADY_EXISTS("Category with name: %s already exists"),
    UNEXPECTED_ERROR("Unexpected error"),
    CATEGORY_NOT_FOUND("Category with id: %s not found");

    private final String code;
}