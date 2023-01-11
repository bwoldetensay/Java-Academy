package uk.com.capgemini.sweettreat.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;
import uk.com.capgemini.sweettreat.models.DeliveryOptions;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository{

  @Override
  public List<DeliveryOptions> findAllById() {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(UUID uuid) {

  }


  @Override
  public void delete(DeliveryOptions entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends UUID> uuids) {

  }


  @Override
  public void deleteAll(Iterable<? extends DeliveryOptions> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public <S extends DeliveryOptions> S save(S entity) {
    return null;
  }

  @Override
  public <S extends DeliveryOptions> List<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<DeliveryOptions> findById(UUID uuid) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(UUID uuid) {
    return false;
  }

  @Override
  public Optional<DeliveryOptions> findById(String s) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(String s) {
    return false;
  }

  @Override
  public List<DeliveryOptions> findAll() {
    return null;
  }


  @Override
  public Iterable<DeliveryOptions> findAllById(Iterable<UUID> strings) {
    return null;
  }

  @Override
  public List<DeliveryOptions> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<DeliveryOptions> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public <S extends DeliveryOptions> S insert(S entity) {
    return null;
  }

  @Override
  public <S extends DeliveryOptions> List<S> insert(Iterable<S> entities) {
    return null;
  }

  @Override
  public <S extends DeliveryOptions> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends DeliveryOptions> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends DeliveryOptions> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends DeliveryOptions> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends DeliveryOptions> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends DeliveryOptions> boolean exists(Example<S> example) {
    return false;
  }

  @Override
  public <S extends DeliveryOptions, R> R findBy(Example<S> example,
      Function<FetchableFluentQuery<S>, R> queryFunction) {
    return null;
  }
}
