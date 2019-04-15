package com.remondis.propertypath.common;

public class C {

  private String string;

  public C() {
    super();
  }

  public C(String c) {
    super();
    this.string = c;
  }

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  @Override
  public String toString() {
    return "C [string=" + string + "]";
  }

}
