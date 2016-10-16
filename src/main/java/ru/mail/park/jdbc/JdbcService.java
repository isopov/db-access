package ru.mail.park.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import ru.mail.park.Foobar;
import ru.mail.park.IService;

@Service
public class JdbcService implements IService {
  private final DataSource ds;

  public JdbcService(DataSource ds) {
    this.ds = ds;
  }

  @Override
  public void create(String value) {
    try (Connection con = ds.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into foobar(val) values(?)")) {
      pst.setString(1, value);
      pst.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Foobar get(long id) {
    try (Connection con = ds.getConnection();
        PreparedStatement pst = con.prepareStatement("select val from foobar where id=?")) {
      pst.setLong(1, id);
      try (ResultSet res = pst.executeQuery()) {
        Assert.isTrue(res.next());
        return new Foobar(id, res.getString(1));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Foobar> listAll() {
    try (Connection con = ds.getConnection();
        PreparedStatement pst = con.prepareStatement("select id, val from foobar");
        ResultSet res = pst.executeQuery()) {
      List<Foobar> result = new ArrayList<>();
      while (res.next()) {
        result.add(new Foobar(res.getLong(1), res.getString(2)));
      }
      return result;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Foobar> list(String value) {
    try (Connection con = ds.getConnection();
        PreparedStatement pst = con.prepareStatement("select id from foobar where val=?")) {
      pst.setString(1, value);
      try (ResultSet res = pst.executeQuery()) {
        List<Foobar> result = new ArrayList<>();
        while (res.next()) {
          result.add(new Foobar(res.getLong(1), value));
        }
        return result;
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
