package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public interface IMatchable {
  /**
   * Tests whether this object matches a given IMatachable, according to its own rules.
   * @param m IMatchable to be matched against
   * @return True if this object matches m, false otherwise
   */
  public boolean match(IMatchable m);

  /**
   * Returns whether this IMatachable has unknown value. An unknown value always matches value of the same type.
   * @return True if this IMatchable has an unknown value, false otherwise
   */
  public boolean isUnknownValue();
}
