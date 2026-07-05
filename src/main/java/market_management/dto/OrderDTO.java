package market_management.dto;

import java.time.LocalDateTime;

public record OrderDTO(
        String customerName,
        String productName,
        double price,
        int quantity,
        double totalPrice,
        LocalDateTime orderDate
) {}
