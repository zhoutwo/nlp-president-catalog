package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public class UnknownTime implements ITime {
  @Override
  public String getName() {
    return null;
  }

  @Override
  public void setName(String name) {

  }

  @Override
  public boolean match(IMatchable m) {
    return false;
  }

  @Override
  public boolean isUnknownValue() {
    return false;
  }
}
