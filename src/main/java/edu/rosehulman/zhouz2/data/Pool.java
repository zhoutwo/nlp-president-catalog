package edu.rosehulman.zhouz2.data;

import java.util.*;

/**
 * Created by Zhou Zhou on 5/22/17.
 */
public class Pool<E extends IMatchable> implements Set<E> {
  private List<E> list;

  public Pool() {
    list = Collections.synchronizedList(new ArrayList<E>());
  }

  public Pool(int initialCapacity) {
    list = Collections.synchronizedList(new ArrayList<E>(initialCapacity));
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    if (!(o instanceof IMatchable)) {
      return false;
    }
    IMatchable m = (IMatchable) o;
    for (int i = 0; i < list.size(); i++) {
      IMatchable current = list.get(i);
      if (m.match(current)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }

  @Override
  public Object[] toArray() {
    return list.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return list.toArray(a);
  }

  @Override
  public boolean add(E e) {
    for (int i = 0; i < list.size(); i++) {
      IMatchable current = list.get(i);
      if (e.match(current)) {
        if (current.isUnknownValue()) {
          list.set(i, e);
          return true;
        } else {
          return false;
        }
      }
    }
    return list.add(e);
  }

  @Override
  public boolean remove(Object o) {
    if (!(o instanceof IMatchable)) {
      return false;
    }
    IMatchable m = (IMatchable) o;
    for (int i = 0; i < list.size(); i++) {
      IMatchable current = list.get(i);
      if (m.match(current)) {
        list.remove(i);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return list.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    boolean changed = false;
    for (E ele : c) {
      changed = changed || this.add(ele);
    }
    return changed;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    Pool<E> copy = new Pool<>(this.size());
    copy.addAll(this);
    if (!copy.removeAll(c)) {
      // None of them actually exists here
      return false;
    } else {
      return this.removeAll(copy);
    }
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    boolean changed = false;
    for (Object ele : c) {
      changed = changed || this.remove(ele);
    }
    return changed;
  }

  @Override
  public void clear() {
    list.clear();
  }
}
