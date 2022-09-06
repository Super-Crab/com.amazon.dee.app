package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class WifiConfiguration extends WifiNetwork implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.WifiConfiguration");
    private CredentialConfiguration credentialConfiguration;
    private int frequency;
    private long lastConnectedDateUtcMillis;
    private int priority;

    @Override // com.amazon.credentiallocker.WifiNetwork
    public boolean equals(Object obj) {
        if (!(obj instanceof WifiConfiguration)) {
            return false;
        }
        WifiConfiguration wifiConfiguration = (WifiConfiguration) obj;
        return super.equals(obj) && Helper.equals(Integer.valueOf(this.priority), Integer.valueOf(wifiConfiguration.priority)) && Helper.equals(this.credentialConfiguration, wifiConfiguration.credentialConfiguration) && Helper.equals(Long.valueOf(this.lastConnectedDateUtcMillis), Long.valueOf(wifiConfiguration.lastConnectedDateUtcMillis)) && Helper.equals(Integer.valueOf(this.frequency), Integer.valueOf(wifiConfiguration.frequency));
    }

    public CredentialConfiguration getCredentialConfiguration() {
        return this.credentialConfiguration;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public long getLastConnectedDateUtcMillis() {
        return this.lastConnectedDateUtcMillis;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override // com.amazon.credentiallocker.WifiNetwork
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Integer.valueOf(this.priority), this.credentialConfiguration, Long.valueOf(this.lastConnectedDateUtcMillis), Integer.valueOf(this.frequency));
    }

    public void setCredentialConfiguration(CredentialConfiguration credentialConfiguration) {
        this.credentialConfiguration = credentialConfiguration;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public void setLastConnectedDateUtcMillis(long j) {
        this.lastConnectedDateUtcMillis = j;
    }

    public void setPriority(int i) {
        this.priority = i;
    }
}
