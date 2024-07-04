package com.course.productservice.productcatelogservice.exceptions;

public class ErrorMessageConstants {
        // Validation Errors
        public static final String VALIDATION_ERROR = "Validation error occurred";
        public static final String INVALID_EMAIL = "Invalid email format";
        public static final String EMPTY_FIELD = "Field cannot be empty";
        public static final String PASSWORD_TOO_SHORT = "Password must be at least 8 characters long";

        // Authentication and Authorization Errors
        public static final String UNAUTHORIZED = "Unauthorized access";
        public static final String FORBIDDEN = "You do not have permission to perform this action";
        public static final String INVALID_CREDENTIALS = "Invalid username or password";

        // Resource Errors
        public static final String RESOURCE_NOT_FOUND = "Resource not found";
        public static final String USER_NOT_FOUND = "User not found";
        public static final String PRODUCT_NOT_FOUND = "Product not found";
        public static final String ORDER_NOT_FOUND = "Order not found";

        // Server Errors
        public static final String INTERNAL_SERVER_ERROR = "Internal server error";
        public static final String SERVICE_UNAVAILABLE = "Service is currently unavailable";

        // Database Errors
        public static final String DATABASE_ERROR = "Database error occurred";
        public static final String DATA_INTEGRITY_VIOLATION = "Data integrity violation";

        // Custom Business Logic Errors
        public static final String INSUFFICIENT_FUNDS = "Insufficient funds for this transaction";
        public static final String DUPLICATE_USER = "User with this email already exists";
        public static final String OUT_OF_STOCK = "Product is out of stock";

}
