package ru.mail.park.jdbctemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.mail.park.AbstractController;
import ru.mail.park.IService;

@RestController
@RequestMapping(path = "/template")
public class JdbcTemplateController extends AbstractController {

  private final JdbcTemplateService jdbcService;

  public JdbcTemplateController(JdbcTemplateService jdbcService) {
    this.jdbcService = jdbcService;
  }

  @Override
  protected IService getService() {
    return jdbcService;
  }

}
