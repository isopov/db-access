package ru.mail.park;

import java.util.List;

public interface IService {

  public void create(String value);

  public Foobar get(long id);

  public List<Foobar> listAll();

  public List<Foobar> list(String value);

}
