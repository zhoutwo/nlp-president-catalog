package edu.rosehulman.zhouz2.data;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public class SingleEvent implements IEvent {
  private String name;
  private IEntity initiator;
  private IAction action;
  private IEntity receiver;
  private ITime time;
  private ILocation location;
  private IEvent causedBy;
  private IEvent resultIn;

  public SingleEvent() {
    this(null);
  }

  public SingleEvent(String n) {
    name = n;
    // Difference between unknown and null: unknown matches everything - "missing information",
    // where null has a specific meaning - "doesn't exist"
    initiator = new UnknownEntity();
    action = new UnknownAction();
    receiver = new UnknownEntity();
    time = new UnknownTime();
    location = new UnknownLocation();
    causedBy = null;
    resultIn = null;
  }

  @Override
  public void setInitiator(IEntity i) {
    initiator = i;
  }

  @Override
  public IEntity getInitiator() {
    return initiator;
  }

  @Override
  public void setAction(IAction a) {
    action = a;
  }

  @Override
  public IAction getAction() {
    return action;
  }

  @Override
  public void setReceiver(IEntity r) {
    receiver = r;
  }

  @Override
  public IEntity getReceiver() {
    return receiver;
  }

  @Override
  public void setTime(ITime t) {
    time = t;
  }

  @Override
  public ITime getTime() {
    return time;
  }

  @Override
  public void setLocation(ILocation l) {
    location = l;
  }

  @Override
  public ILocation getLocation() {
    return location;
  }

  @Override
  public void setCausedBy(IEvent e) {
    causedBy = e;
  }

  @Override
  public IEvent getCausedBy() {
    return causedBy;
  }

  @Override
  public void setResultIn(IEvent e) {
    resultIn = e;
  }

  @Override
  public IEvent getResultIn() {
    return resultIn;
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
    if (m == this) {
      return true;
    } else if (!(m instanceof IEvent)) {
      return false;
    } else if (m.isUnknownValue()) {
      return true;
    } else if (m instanceof SingleEvent) {
      SingleEvent e = (SingleEvent) m;
      if (this.matchByName(e.getName())) {
        return true;
      } else {
        return initiator.match(e.initiator) &&
               action.match(e.action) &&
               receiver.match(e.receiver) &&
               time.match(e.time) &&
               location.match(e.location) &&
               causedBy.match(e.causedBy) &&
               resultIn.match(e.resultIn);
      }
    } else if (m instanceof MultipleEvent) {
      return beMatchedBy(m);
    } else {
      System.err.println("Unknown IEvent type: " + m.getClass());
      return false;
    }
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
    if (m == this) {
      return SpecificityComparisonOutput.EQUALLY_SPECIFIC;
    } else if (!match(m)) {
      return SpecificityComparisonOutput.NOT_RELATED;
    } else {
      // TODO: implement this
    }
    return null;
  }
}
