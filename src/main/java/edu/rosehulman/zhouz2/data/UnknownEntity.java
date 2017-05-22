package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public class UnknownEntity implements IEntity {
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
  public boolean matchByName(String name) {
    return false;
  }

  @Override
  public boolean isUnknownValue() {
    return false;
  }

  @Override
  public SpecificityComparisonOutput isMoreSpecificThan(IMatchable m) {
    return null;
  }
}
