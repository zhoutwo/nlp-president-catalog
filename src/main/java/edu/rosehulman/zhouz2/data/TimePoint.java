package edu.rosehulman.zhouz2.data;

import java.time.LocalDateTime;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public class TimePoint implements ITime {
  private LocalDateTime time;

  public TimePoint() {
    this(null);
  }

  public TimePoint(LocalDateTime t) {
    time = t;
  }

  @Override
  public String getName() {
    return time.toString();
  }

  @Override
  public void setName(String name) {
    time = LocalDateTime.parse(name);
  }

  @Override
  public boolean match(IMatchable m) {
    // TODO Implement this
    return false;
  }

  @Override
  public boolean matchByName(String name) {
    return time.equals(LocalDateTime.parse(name));
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
        throw new RuntimeException("This is not possible");
      }
    } else {
      if (test2) {
        throw new RuntimeException("This is not possible");
      } else {
        return SpecificityComparisonOutput.NOT_RELATED;
      }
    }
  }
}
