package uk.co.produce.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import uk.co.produce.entities.Produce;

public interface ProduceRepository extends MongoRepository<Produce, String> {

}
