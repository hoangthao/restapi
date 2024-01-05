package com.example.restapi.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDto {
    
    private Optional<String> from;
    private Optional<String> to;
    private Optional<String> description;

    private int page = 1;
    private int size = 5;
    private List<String> sorts = new ArrayList<>();
}
