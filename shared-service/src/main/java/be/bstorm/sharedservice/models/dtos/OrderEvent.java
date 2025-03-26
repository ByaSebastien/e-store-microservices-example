package be.bstorm.sharedservice.models.dtos;

public record OrderEvent(
        Long orderId,
        double amount
) {
}
