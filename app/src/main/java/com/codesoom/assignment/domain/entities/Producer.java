package com.codesoom.assignment.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * '생산자' Root Entity
 * <p>
 * All Known Extending Classes:
 * ToyProducer
 * </p>
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Producer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
