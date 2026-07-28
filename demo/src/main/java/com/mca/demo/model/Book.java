package com.mca.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    
        @NotNull(message = "id cannot be null")
        private Long id;
        @NotNull(message = "name cannot be null")
        @NotBlank(message = "Name cannot be blank")
        private String name;

        @NotNull(message = "Price is requered")
        
        @DecimalMin(value = "5.0",inclusive=true,
               message = "price must be greater than 0")
                private BigDecimal price;
                
        @NotNull(message = "PublishedDate is Required")
        @PastOrPresent(message = "published Date cannot be in future")
        private LocalDate publishedDate;
    
}
