package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public class MultipleEvent implements IEvent {
  @Override
  public void setInitiator(IEntity i) {

  }

  @Override
  public IEntity getInitiator() {
    return null;
  }

  @Override
  public void setAction(IAction a) {

  }

  @Override
  public IAction getAction() {
    return null;
  }

  @Override
  public void setReceiver(IEntity r) {

  }

  @Override
  public IEntity getReceiver() {
    return null;
  }

  @Override
  public void setTime(ITime t) {

  }

  @Override
  public ITime getTime() {
    return null;
  }

  @Override
  public void setLocation(ILocation l) {

  }

  @Override
  public ILocation getLocation() {
    return null;
  }

  @Override
  public void setCausedBy(IEvent e) {

  }

  @Override
  public IEvent getCausedBy() {
    return null;
  }

  @Override
  public void setResultIn(IEvent e) {

  }

  @Override
  public IEvent getResultIn() {
    return null;
  }

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
