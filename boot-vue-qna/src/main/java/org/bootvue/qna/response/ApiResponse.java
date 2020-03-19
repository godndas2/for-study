package org.bootvue.qna.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class ApiResponse<T> {
    private T data;
    private List<String> errors;
}
