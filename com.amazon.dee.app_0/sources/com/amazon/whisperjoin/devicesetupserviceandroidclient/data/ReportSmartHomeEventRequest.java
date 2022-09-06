package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetup.common.v1.Event;
import com.amazon.devicesetupservice.reporting.State;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes13.dex */
public class ReportSmartHomeEventRequest {
    private static final int SEQUENCE_NUMBER_MIN = 1;
    private final String mBleMac;
    private final ErrorInfo mErrorInfo;
    private final String mEvent;
    private final String mProductIndex;
    private final ProvisionerInfo mProvisionerInfo;
    private final String mProvisioningMethod;
    private final String mRadio;
    private final String mReportEventIdentifier;
    private final int mSequenceNumber;
    private final String mState;
    private final String mZigbeeMac;
    private static final Set<String> STATE_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(State.values())));
    private static final Set<String> EVENT_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(Event.values())));

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mBleMac;
        private ErrorInfo mErrorInfo;
        private String mEvent;
        private String mProductIndex;
        private ProvisionerInfo mProvisionerInfo;
        private String mProvisioningMethod;
        private String mRadio;
        private String mReportEventIdentifier;
        private int mSequenceNumber;
        private String mState;
        private String mZigbeeMac;

        public ReportSmartHomeEventRequest createReportSmartHomeEventRequest() {
            if (this.mReportEventIdentifier != null) {
                if (this.mState != null) {
                    if (ReportSmartHomeEventRequest.STATE_VALUES.contains(this.mState)) {
                        if (this.mEvent != null) {
                            if (ReportSmartHomeEventRequest.EVENT_VALUES.contains(this.mEvent)) {
                                int i = this.mSequenceNumber;
                                if (i >= 1) {
                                    return new ReportSmartHomeEventRequest(this.mReportEventIdentifier, this.mState, this.mEvent, this.mBleMac, this.mZigbeeMac, this.mProductIndex, this.mProvisioningMethod, this.mRadio, i, this.mProvisionerInfo, this.mErrorInfo);
                                }
                                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mSequenceNumber has to be greater than 1, found ");
                                outline107.append(this.mSequenceNumber);
                                throw new IllegalArgumentException(outline107.toString());
                            }
                            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("mEvent not a recognized value: ");
                            outline1072.append(this.mEvent);
                            throw new IllegalArgumentException(outline1072.toString());
                        }
                        throw new IllegalArgumentException("mEvent can not be null");
                    }
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("mState not a recognized value: ");
                    outline1073.append(this.mState);
                    throw new IllegalArgumentException(outline1073.toString());
                }
                throw new IllegalArgumentException("mState can not be null");
            }
            throw new IllegalArgumentException("mReportEventIdentifier can not be null");
        }

        public Builder setBleMac(String str) {
            this.mBleMac = str;
            return this;
        }

        public Builder setErrorInfo(ErrorInfo errorInfo) {
            this.mErrorInfo = errorInfo;
            return this;
        }

        public Builder setEvent(String str) {
            this.mEvent = str;
            return this;
        }

        public Builder setProductIndex(String str) {
            this.mProductIndex = str;
            return this;
        }

        public Builder setProvisionerInfo(ProvisionerInfo provisionerInfo) {
            this.mProvisionerInfo = provisionerInfo;
            return this;
        }

        public Builder setProvisioningMethod(String str) {
            this.mProvisioningMethod = str;
            return this;
        }

        public Builder setRadio(String str) {
            this.mRadio = str;
            return this;
        }

        public Builder setReportEventIdentifier(String str) {
            this.mReportEventIdentifier = str;
            return this;
        }

        public Builder setSequenceNumber(int i) {
            this.mSequenceNumber = i;
            return this;
        }

        public Builder setState(String str) {
            this.mState = str;
            return this;
        }

        public Builder setZigbeeMac(String str) {
            this.mZigbeeMac = str;
            return this;
        }
    }

    public ReportSmartHomeEventRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, ProvisionerInfo provisionerInfo, ErrorInfo errorInfo) {
        this.mReportEventIdentifier = str;
        this.mState = str2;
        this.mEvent = str3;
        this.mBleMac = str4;
        this.mZigbeeMac = str5;
        this.mProductIndex = str6;
        this.mProvisioningMethod = str7;
        this.mRadio = str8;
        this.mSequenceNumber = i;
        this.mProvisionerInfo = provisionerInfo;
        this.mErrorInfo = errorInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReportSmartHomeEventRequest)) {
            return false;
        }
        ReportSmartHomeEventRequest reportSmartHomeEventRequest = (ReportSmartHomeEventRequest) obj;
        return this.mSequenceNumber == reportSmartHomeEventRequest.mSequenceNumber && Objects.equal(this.mReportEventIdentifier, reportSmartHomeEventRequest.mReportEventIdentifier) && Objects.equal(this.mState, reportSmartHomeEventRequest.mState) && Objects.equal(this.mEvent, reportSmartHomeEventRequest.mEvent) && Objects.equal(this.mBleMac, reportSmartHomeEventRequest.mBleMac) && Objects.equal(this.mZigbeeMac, reportSmartHomeEventRequest.mZigbeeMac) && Objects.equal(this.mProductIndex, reportSmartHomeEventRequest.mProductIndex) && Objects.equal(this.mProvisioningMethod, reportSmartHomeEventRequest.mProvisioningMethod) && Objects.equal(this.mRadio, reportSmartHomeEventRequest.mRadio) && Objects.equal(this.mProvisionerInfo, reportSmartHomeEventRequest.mProvisionerInfo) && Objects.equal(this.mErrorInfo, reportSmartHomeEventRequest.mErrorInfo);
    }

    public String getBleMac() {
        return this.mBleMac;
    }

    public ErrorInfo getErrorInfo() {
        return this.mErrorInfo;
    }

    public String getEvent() {
        return this.mEvent;
    }

    public String getProductIndex() {
        return this.mProductIndex;
    }

    public ProvisionerInfo getProvisionerInfo() {
        return this.mProvisionerInfo;
    }

    public String getProvisioningMethod() {
        return this.mProvisioningMethod;
    }

    public String getRadio() {
        return this.mRadio;
    }

    public String getReportEventIdentifier() {
        return this.mReportEventIdentifier;
    }

    public int getSequenceNumber() {
        return this.mSequenceNumber;
    }

    public String getState() {
        return this.mState;
    }

    public String getZigbeeMac() {
        return this.mZigbeeMac;
    }

    public int hashCode() {
        return Objects.hashCode(this.mReportEventIdentifier, this.mState, this.mEvent, this.mBleMac, this.mZigbeeMac, this.mProductIndex, this.mProvisioningMethod, this.mRadio, Integer.valueOf(this.mSequenceNumber), this.mProvisionerInfo, this.mErrorInfo);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mReportEventIdentifier", this.mReportEventIdentifier).add("mState", this.mState).add("mEvent", this.mEvent).add("mBleMac", this.mBleMac).add("mZigbeeMac", this.mZigbeeMac).add("mProductIndex", this.mProductIndex).add("mProvisioningMethod", this.mProvisioningMethod).add("mRadio", this.mRadio).add("mSequenceNumber", this.mSequenceNumber).add("mProvisionerInfo", this.mProvisionerInfo).add("mErrorInfo", this.mErrorInfo).toString();
    }
}
