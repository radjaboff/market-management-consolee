package market_management.dto;

import market_management.enums.UserRole;

public record UserDTO(
         String fullName,
         String email,
         String password) {
}
