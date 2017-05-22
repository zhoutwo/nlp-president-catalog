package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public class ConcreteEntity implements IEntity {
  private String name;

  public ConcreteEntity() {
    this(null);
  }

  public ConcreteEntity(String n) {
    name = n;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String n) {
    name = n;
  }

  @Override
  public boolean match(IMatchable m) {
    if (!(m instanceof ConcreteEntity)) {
      return false;
    } else {
      return ((ConcreteEntity) m).name.equals(name);
    }
  }

  @Override
  public boolean matchByName(String n) {
    return n.equals(name);
  }

  @Override
  public boolean isUnknownValue() {
    return false;
  }

  @Override
  public SpecificityComparisonOutput isMoreSpecificThan(IMatchable m) {
    if (!(m instanceof ConcreteEntity)) {
      return SpecificityComparisonOutput.NOT_RELATED;
    } else {
      ConcreteEntity c = (ConcreteEntity) m;
      if (c.name.equals(name)) {
        return SpecificityComparisonOutput.EQUALLY_SPECIFIC;
      } else {
        return SpecificityComparisonOutput.UNKNOWN;
      }
    }
  }
}
