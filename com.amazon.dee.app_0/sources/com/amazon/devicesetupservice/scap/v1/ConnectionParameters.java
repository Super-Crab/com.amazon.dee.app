package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ConnectionParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.ConnectionParameters");
    private String connectionInterval;
    private String connectionLatency;
    private int mtu;
    private String supervisionTimeout;

    public boolean equals(Object obj) {
        if (!(obj instanceof ConnectionParameters)) {
            return false;
        }
        ConnectionParameters connectionParameters = (ConnectionParameters) obj;
        return Helper.equals(this.supervisionTimeout, connectionParameters.supervisionTimeout) && Helper.equals(this.connectionLatency, connectionParameters.connectionLatency) && Helper.equals(this.connectionInterval, connectionParameters.connectionInterval) && Helper.equals(Integer.valueOf(this.mtu), Integer.valueOf(connectionParameters.mtu));
    }

    public String getConnectionInterval() {
        return this.connectionInterval;
    }

    public String getConnectionLatency() {
        return this.connectionLatency;
    }

    public int getMtu() {
        return this.mtu;
    }

    public String getSupervisionTimeout() {
        return this.supervisionTimeout;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.supervisionTimeout, this.connectionLatency, this.connectionInterval, Integer.valueOf(this.mtu));
    }

    public void setConnectionInterval(String str) {
        this.connectionInterval = str;
    }

    public void setConnectionLatency(String str) {
        this.connectionLatency = str;
    }

    public void setMtu(int i) {
        this.mtu = i;
    }

    public void setSupervisionTimeout(String str) {
        this.supervisionTimeout = str;
    }
}
