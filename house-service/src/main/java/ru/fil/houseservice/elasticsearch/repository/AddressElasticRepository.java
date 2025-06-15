package ru.fil.houseservice.elasticsearch.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ru.fil.houseservice.elasticsearch.document.AddressDocument;

import java.util.List;

@Repository
public interface AddressElasticRepository extends ElasticsearchRepository<AddressDocument, String> {

    @Query("{ \"match\": { \"fullAddress\": { \"query\": \"?0\", \"operator\": \"and\", \"fuzziness\": \"AUTO\" } }}")
    List<AddressDocument> searchByFullAddressWithFuzzy(String value);
}
