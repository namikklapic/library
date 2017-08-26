package util;

import java.util.EventObject;

public class MyEvent extends EventObject {
	  public MyEvent(Object source, String message) {
	    super(source);
	    setEventMessage(message);
	  }
	  public String getEventMessage() {
		return eventMessage;
	}
	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}
	private String eventMessage;
}