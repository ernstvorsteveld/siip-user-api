package group.siip.userapi.user.web;

import group.siip.userapi.user.gateway.mongo.UserDocument;
import group.siip.userapi.user.usecase.RequestPayloadMissingException;
import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private String error;
    private List<String> validationErrors;

    public ErrorResponse(ConstraintViolationException e) {
        validationErrors = e.getConstraintViolations()
                .stream()
                .map(c -> c.getMessage())
                .collect(Collectors.toList());
    }

    public ErrorResponse(MethodArgumentNotValidException e) {
        validationErrors = e.getFieldErrors()
                .stream()
                .map(c -> c.getDefaultMessage())
                .collect(Collectors.toList());
    }

    public ErrorResponse(DuplicateKeyException e) {
        List<UserDocument.UniqueIndexes> collect = Arrays.stream(UserDocument.UniqueIndexes.values())
                .filter(u -> e.getMessage().indexOf(u.name()) > 0).collect(Collectors.toList());
        error = map(collect.get(0));
    }

    public ErrorResponse(RequestPayloadMissingException ex) {
        error = "No request payload found.";
    }

    private String map(UserDocument.UniqueIndexes value) {
        switch (value){
            case uk_mobile: return "Mobile number not unique";
            default: return "Unknown";
        }
    }
}
