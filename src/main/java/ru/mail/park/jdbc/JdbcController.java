package ru.mail.park.jdbc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.mail.park.AbstractController;
import ru.mail.park.IService;

@RestController
@RequestMapping(path = "/jdbc")
public class JdbcController extends AbstractController {

  private final JdbcService jdbcService;

  public JdbcController(JdbcService jdbcService) {
    this.jdbcService = jdbcService;
  }

  @Override
  protected IService getService() {
    return jdbcService;
  }

}
