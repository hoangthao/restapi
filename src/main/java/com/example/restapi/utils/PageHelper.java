package com.example.restapi.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageHelper {
   public static Pageable parsePageable(int page, int size, List<String> sorts) {
        if (sorts == null || sorts.isEmpty()) {
            return PageRequest.of(page, size);
        }
        List<Sort.Order> orders = sorts.stream().map(i -> i.split(","))
            .filter(i -> i.length == 2)
            .filter(i -> isPresent(i[0]))
            .map(i -> order(i[0], i[1]))
            .collect(Collectors.toList());
        if (orders.isEmpty()) {
            return PageRequest.of(page, size);
        }
        return PageRequest.of(page, size, Sort.by(orders));
    }

    private static boolean isPresent(String v) {
        return Sort.Direction.fromOptionalString(v).isPresent();
    }

    private static Sort.Order order(String v1, String v2) {
        return Sort.Direction.fromString(v1) == Sort.Direction.ASC ? 
            Sort.Order.asc(v2.strip()) :
            Sort.Order.desc(v2.strip());
    }
}
