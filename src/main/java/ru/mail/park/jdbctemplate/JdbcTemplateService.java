package ru.mail.park.jdbctemplate;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import ru.mail.park.Foobar;
import ru.mail.park.IService;

@Service
public class JdbcTemplateService implements IService {
  private final JdbcTemplate template;

  public JdbcTemplateService(JdbcTemplate template) {
    this.template = template;
  }

  @Override
  public void create(String value) {
    template.update("insert into foobar(val) values(?)", value);
  }

  @Override
  public Foobar get(long id) {
    String value = template.queryForObject("select val from foobar where id=?", String.class, id);
    return new Foobar(id, value);
  }

  private static final RowMapper<Foobar> FOOBAR_MAPPER =
      (res, rowNum) -> new Foobar(res.getLong(1), res.getString(2));

  @Override
  public List<Foobar> listAll() {
    return template.query("select id, val from foobar", FOOBAR_MAPPER);
  }

  @Override
  public List<Foobar> list(String value) {
    return template.query("select id, val from foobar where val=?", FOOBAR_MAPPER, value);
  }

}
