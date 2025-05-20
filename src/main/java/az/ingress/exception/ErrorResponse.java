package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.Collections.emptyList;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;
    private final List<String> validationErrors;

    public ErrorResponse(String message) {
        this.message = message;
        validationErrors = emptyList();
    }
}