package com.hospital.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
//    এখানে T হলো generic type। মানে data-এর ভিতরে কী থাকবে সেটা আগে থেকে fixed না।

    private boolean success;

    private String message;

    private T data;
//    data যেকোনো type-এর object হতে পারে।

    private LocalDateTime timestamp;



    public static <T> ApiResponse<T> success(String message, T data) {

        return new ApiResponse<>(
                true,
                message,
                data,
                LocalDateTime.now()
        );

    }
}