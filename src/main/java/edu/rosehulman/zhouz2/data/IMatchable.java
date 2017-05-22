package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public interface IMatchable {
  /**
   * Tests whether this object matches a given IMatchable, according to its own rules.
   * @param m IMatchable to be matched against
   * @return True if this object matches m, false otherwise
   */
  public boolean match(IMatchable m);

  /**
   * Essentially calls match method on the other IMatchable on this object
   * @param m IMatchable to be matched against
   * @return True if m matches this object, false otherwise
   */
  public default boolean beMatchedBy(IMatchable m) {
    return m.match(this);
  }

  /**
   * Tests whether the given name matches this object
   * @param name Name to test matching
   * @return True if the name matches this object, false otherwise
   */
  public boolean matchByName(String name);

  /**
   * Returns whether this IMatachable has unknown value. An unknown value always matches value of the same type.
   * @return True if this IMatchable has an unknown value, false otherwise
   */
  public boolean isUnknownValue();

  /**
   * Compares whether this IMatchable describes something more specific than the given IMatchable.
   * @param m IMatchabel to be compared against
   * @return Enum value that describes whether this IMatchable is more specific
   */
  public SpecificityComparisonOutput isMoreSpecificThan(IMatchable m);
}
