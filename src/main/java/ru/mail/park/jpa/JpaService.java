package ru.mail.park.jpa;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ru.mail.park.Foobar;
import ru.mail.park.IService;

@Service
@Transactional
public class JpaService implements IService {

  @PersistenceContext
  private EntityManager em;

  @Override
  public void create(String value) {
    FoobarEntity entity = new FoobarEntity();
    entity.setValue(value);
    em.persist(entity);

  }

  @Override
  public Foobar get(long id) {
    return em.find(FoobarEntity.class, id).toDto();
  }

  @Override
  public List<Foobar> listAll() {
    return em.createQuery("select f from FoobarEntity f", FoobarEntity.class)
        .getResultList()
        .stream()
        .map(FoobarEntity::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<Foobar> list(String value) {
    return em.createQuery("select f from FoobarEntity f where f.value=:value", FoobarEntity.class)
        .setParameter("value", value)
        .getResultList()
        .stream()
        .map(FoobarEntity::toDto)
        .collect(Collectors.toList());
  }

}
