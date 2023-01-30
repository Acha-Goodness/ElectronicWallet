package com.fargo.Gwallet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tokenNumber;
    private LocalDateTime createdAt;
    private LocalDateTime ExpiredAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    private User user;
}
