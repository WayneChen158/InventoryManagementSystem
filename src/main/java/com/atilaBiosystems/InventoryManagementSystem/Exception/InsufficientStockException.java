package com.atilaBiosystems.InventoryManagementSystem.Exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
