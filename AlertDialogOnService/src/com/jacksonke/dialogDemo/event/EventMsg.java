package com.jacksonke.dialogDemo.event;

public class EventMsg {
	int mIdFrom = 0;
	int mIdTo = 0;
	public EventMsg(Object object){
		mIdFrom = System.identityHashCode(object);
	}
	
	public int getIdFrom(){
		return mIdFrom;
	}
	
	public int getIdTo(){
		return mIdTo;
	}
	
	public void setIdTo(Object object){
		mIdTo = System.identityHashCode(object);
	}
}
