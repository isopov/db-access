package ru.mail.park;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
@Transactional
public abstract class AbstractControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JdbcTemplate template;

  protected abstract String getPrefix();

  @Test
  public void testCreate() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(getPrefix() + "/create")
        .param("value", "hello"))
        .andExpect(status().isOk());

    assertEquals(1, countRowsInTable(template, "foobar"));
  }

  @Test
  public void testListAll() throws Exception {
    testCreate();
    mockMvc.perform(MockMvcRequestBuilders.get(getPrefix() + "/listall"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].value").value("hello"));
  }

  @Test
  public void testList() throws Exception {
    testCreate();
    mockMvc.perform(MockMvcRequestBuilders.get(getPrefix() + "/list")
        .param("value", "hello"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].value").value("hello"));
  }

  @Test
  public void testGet() throws Exception {
    testCreate();

    Long id = template.queryForObject("select id from foobar", Long.class);

    mockMvc.perform(MockMvcRequestBuilders.get(getPrefix() + "/get")
        .param("id", String.valueOf(id)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("value").value("hello"));
  }

}
