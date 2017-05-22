package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public interface IEvent extends IEntity {
  public void setInitiator(IEntity i);
  public IEntity getInitiator();
  public void setAction(IAction a);
  public IAction getAction();
  public void setReceiver(IEntity r);
  public IEntity getReceiver();
  public void setTime(ITime t);
  public ITime getTime();
  public void setLocation(ILocation l);
  public ILocation getLocation();
  public void setCausedBy(IEvent e);
  public IEvent getCausedBy();
  public void setResultIn(IEvent e);
  public IEvent getResultIn();
}
