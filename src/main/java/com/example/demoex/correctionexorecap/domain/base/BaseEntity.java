package com.example.demoex.correctionexorecap.domain.base;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
public class BaseEntity <T extends Serializable>{
    /**
     * The unique identifier for the entity.
     */
    @Id
    @GeneratedValue
    private T id;

    /**
     * The timestamp indicating when the entity was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Setter
    private LocalDateTime createdAt;

    /**
     * The timestamp indicating when the entity was last updated.
     */
    @Column(name = "updated_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Setter
    private LocalDateTime updatedAt;
}
