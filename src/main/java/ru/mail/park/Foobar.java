package ru.mail.park;

public class Foobar {
  private final long id;
  private final String value;

  public Foobar(long id, String value) {
    this.id = id;
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public long getId() {
    return id;
  }

}
