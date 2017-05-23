package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public class ConcreteAction implements IAction {
  private String name;

  public ConcreteAction() {
    this(null);
  }

  public ConcreteAction(String n) {
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
    // TODO Implement this
    return false;
  }

  @Override
  public boolean matchByName(String n) {
    return name.equals(n);
  }

  @Override
  public boolean isUnknownValue() {
    return false;
  }

  @Override
  public SpecificityComparisonOutput isMoreSpecificThan(IMatchable m) {
    if (!(m instanceof IAction)) {
      return SpecificityComparisonOutput.NOT_RELATED;
    } else if (m.isUnknownValue()) {
      return SpecificityComparisonOutput.MORE_SPECIFIC;
    } else if (match(m)) {
      return SpecificityComparisonOutput.EQUALLY_SPECIFIC;
    } else {
      return SpecificityComparisonOutput.NOT_RELATED;
    }
  }
}
