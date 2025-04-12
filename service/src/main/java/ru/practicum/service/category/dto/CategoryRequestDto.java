package ru.practicum.service.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank
    @Size(min = 1, max = 25, message = "Name length must be between 1 and 25 characters")
    String name;
}
