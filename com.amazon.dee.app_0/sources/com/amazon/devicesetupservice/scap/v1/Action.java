package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class Action implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.Action");
    private BlacklistParameters blacklistParameters;
    private String bleConnectionPriority;
    private BleParameters bleParameters;
    private SmartHomeCredentialData credentialData;
    private List<ScanFilter> scanFilters;
    private int sequenceNumber;
    private String timeout;
    private String type;
    private boolean useChunking;

    public boolean equals(Object obj) {
        if (!(obj instanceof Action)) {
            return false;
        }
        Action action = (Action) obj;
        return Helper.equals(this.bleParameters, action.bleParameters) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(action.sequenceNumber)) && Helper.equals(this.blacklistParameters, action.blacklistParameters) && Helper.equals(this.credentialData, action.credentialData) && Helper.equals(this.scanFilters, action.scanFilters) && Helper.equals(this.type, action.type) && Helper.equals(Boolean.valueOf(this.useChunking), Boolean.valueOf(action.useChunking)) && Helper.equals(this.bleConnectionPriority, action.bleConnectionPriority) && Helper.equals(this.timeout, action.timeout);
    }

    public BlacklistParameters getBlacklistParameters() {
        return this.blacklistParameters;
    }

    public String getBleConnectionPriority() {
        return this.bleConnectionPriority;
    }

    public BleParameters getBleParameters() {
        return this.bleParameters;
    }

    public SmartHomeCredentialData getCredentialData() {
        return this.credentialData;
    }

    public List<ScanFilter> getScanFilters() {
        return this.scanFilters;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getTimeout() {
        return this.timeout;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.bleParameters, Integer.valueOf(this.sequenceNumber), this.blacklistParameters, this.credentialData, this.scanFilters, this.type, Boolean.valueOf(this.useChunking), this.bleConnectionPriority, this.timeout);
    }

    public boolean isUseChunking() {
        return this.useChunking;
    }

    public void setBlacklistParameters(BlacklistParameters blacklistParameters) {
        this.blacklistParameters = blacklistParameters;
    }

    public void setBleConnectionPriority(String str) {
        this.bleConnectionPriority = str;
    }

    public void setBleParameters(BleParameters bleParameters) {
        this.bleParameters = bleParameters;
    }

    public void setCredentialData(SmartHomeCredentialData smartHomeCredentialData) {
        this.credentialData = smartHomeCredentialData;
    }

    public void setScanFilters(List<ScanFilter> list) {
        this.scanFilters = list;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setTimeout(String str) {
        this.timeout = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setUseChunking(boolean z) {
        this.useChunking = z;
    }
}
