package uk.com.capgemini.sweettreat.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uk.com.capgemini.sweettreat.models.DeliveryOptions;

@Repository
public interface DeliveryRepository extends MongoRepository<DeliveryOptions, UUID> {

  @Query()
  List<DeliveryOptions> findAllById();

  public long count();


  Optional<DeliveryOptions> findById(String s);

  boolean existsById(String s);
}
