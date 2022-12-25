package com.example.school;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    /*
    @NotNull
    @NotEmpty - коллекция или строка не могут быть пустыми
    @NotBlank - не пустая строка
    @Min @Max - ограничения для числовых типов
    @Pattern - регулярное выражение для строк
    @Email - шаблон для email адреса
     */
    @Between(from = 10, to = 20, message = "between 10 - 20")
    @NotNull(message = "Id should be used")
    @Min(value = 1, message = "should be greater than 0")
    private Long id;

    @NotBlank(message = "Name is mandatory")
//    @Pattern(regexp = ".*[Mm][Aa][Xx].*", message = "Max is already exist")
    private String first;
    @NotBlank(message = "Last Name is mandatory")

    private String last;


    @NotNull(message = "Id should be used")
    @Min(value = 18, message = "should be greater than 18")
    @Age(message = "Age should be even")
    private Integer age;


}
