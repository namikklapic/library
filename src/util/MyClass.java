package util;


import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

public class MyClass {
  protected EventListenerList listenerList = new EventListenerList();

  public void addMyEventListener(MyEventListener listener) {
    listenerList.add(MyEventListener.class, listener);
  }
  public void removeMyEventListener(MyEventListener listener) {
    listenerList.remove(MyEventListener.class, listener);
  }
  public void fireMyEvent(MyEvent evt) {
    Object[] listeners = listenerList.getListenerList();
    for (int i = 0; i < listeners.length; i = i+2) {
      if (listeners[i] == MyEventListener.class) {
        ((MyEventListener) listeners[i+1]).myEventOccurred(evt);
      }
    }
  }
}
