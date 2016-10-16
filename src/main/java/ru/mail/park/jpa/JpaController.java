package ru.mail.park.jpa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.mail.park.AbstractController;
import ru.mail.park.IService;

@RestController
@RequestMapping(path = "/jpa")
public class JpaController extends AbstractController {

  private final JpaService jpaService;

  public JpaController(JpaService jpaService) {
    this.jpaService = jpaService;
  }

  @Override
  protected IService getService() {
    return jpaService;
  }

}
