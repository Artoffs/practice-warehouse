package com.example.practice.entity;

import com.example.practice.entity.enums.ShipmentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shipment")
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_point_id")
    private PickupPoint pickUpPoint;

    @OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Order> orders;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private ShipmentStatus status;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipment that)) return false;
        return this.id != null && Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
