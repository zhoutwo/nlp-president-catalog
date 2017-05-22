package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public interface IEntity extends IMatchable {
  /**
   * Returns the name of this IEntity
   * @return Name of this IEntity
   */
  public String getName();

  /**
   * Sets the name of this IEntity
   * @param name Name of this IEntity to be changed to
   */
  public void setName(String name);
}
