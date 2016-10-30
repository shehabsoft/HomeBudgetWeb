package com.dataObjects;

public class StatusVO extends DataObjectVO{
private String statusMessage;
private boolean flage;
public String getStatusMessage() {
	return statusMessage;
}
public void setStatusMessage(String statusMessage) {
	this.statusMessage = statusMessage;
}
public boolean isFlage() {
	return flage;
}
public void setFlage(boolean flage) {
	this.flage = flage;
}

}
