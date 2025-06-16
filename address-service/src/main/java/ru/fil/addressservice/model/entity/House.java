package ru.fil.addressservice.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "houses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "street_id", referencedColumnName = "id")
    private Street street;

    @Column(name = "number")
    private String number;

    @OneToMany(mappedBy = "house", cascade = CascadeType.REMOVE)
    private List<Apartment> apartments;

    public void addApartment(Apartment apartment) {
        if (apartments == null) {
            apartments = new ArrayList<>();
        }
        apartments.add(apartment);
    }
}
