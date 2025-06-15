package ru.fil.houseservice.elasticsearch.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDocument implements Serializable {

    @Id
    private String id;

    private String street;

    private String house;

    private String entrance;

    private String apartment;

    private String fullAddress;

    private Integer apartmentId;

    public void updateFullAddress() {
        this.fullAddress = "ул. " + street + ", д. " + house + ", п. " + entrance + ", кв. " + apartment;
    }
}
