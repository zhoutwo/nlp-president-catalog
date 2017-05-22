package edu.rosehulman.zhouz2.data;

import java.time.LocalDate;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public class TimeDate implements ITime {
  private LocalDate date;

  public TimeDate() {
    this(null);
  }

  public TimeDate(LocalDate d) {
    date = d;
  }

  @Override
  public String getName() {
    return date.toString();
  }

  @Override
  public void setName(String name) {
    date = LocalDate.parse(name);
  }

  @Override
  public boolean match(IMatchable m) {
    // TODO Implement this
    return false;
  }

  @Override
  public boolean matchByName(String name) {
    return date.equals(LocalDate.parse(name));
  }

  @Override
  public boolean isUnknownValue() {
    return false;
  }

  @Override
  public SpecificityComparisonOutput isMoreSpecificThan(IMatchable m) {
    boolean test1 = match(m);
    boolean test2 = beMatchedBy(m);
    if (test1) {
      if (test2) {
        return SpecificityComparisonOutput.EQUALLY_SPECIFIC;
      } else {
        return SpecificityComparisonOutput.MORE_SPECIFIC;
      }
    } else {
      if (test2) {
        return SpecificityComparisonOutput.LESS_SPECIFIC;
      } else {
        return SpecificityComparisonOutput.NOT_RELATED;
      }
    }
  }
}
