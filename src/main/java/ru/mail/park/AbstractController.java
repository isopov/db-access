package ru.mail.park;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class AbstractController {
  protected abstract IService getService();

  @RequestMapping(path = "/create")
  public void create(@RequestParam String value) {
    getService().create(value);
  }

  @RequestMapping(path = "/get")
  public Foobar get(@RequestParam long id) {
    return getService().get(id);
  }

  @RequestMapping(path = "/listall")
  public List<Foobar> get() {
    return getService().listAll();
  }

  @RequestMapping(path = "/list")
  public List<Foobar> get(@RequestParam String value) {
    return getService().list(value);
  }

}
