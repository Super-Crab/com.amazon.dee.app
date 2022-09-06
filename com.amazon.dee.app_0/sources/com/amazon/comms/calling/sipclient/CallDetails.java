package com.amazon.comms.calling.sipclient;

import org.joda.time.DateTime;
/* loaded from: classes11.dex */
public class CallDetails {
    private DateTime callCompletedTime;
    private DateTime callConnectedTime;
    private DateTime callStartTime;

    public DateTime getCallCompletedTime() {
        return this.callCompletedTime;
    }

    public DateTime getCallConnectedTime() {
        return this.callConnectedTime;
    }

    public DateTime getCallStartTime() {
        return this.callStartTime;
    }

    public void setCallCompletedTime(DateTime dateTime) {
        this.callCompletedTime = dateTime;
    }

    public void setCallConnectedTime(DateTime dateTime) {
        this.callConnectedTime = dateTime;
    }

    public void setCallStartTime(DateTime dateTime) {
        this.callStartTime = dateTime;
    }
}
