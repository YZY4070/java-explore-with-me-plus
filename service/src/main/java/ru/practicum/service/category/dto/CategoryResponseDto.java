package ru.practicum.service.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    Long id;
    @NotBlank
    @Size(min = 1, max = 50, message = "Name length must be between 1 and 25 characters")
    String name;
}
