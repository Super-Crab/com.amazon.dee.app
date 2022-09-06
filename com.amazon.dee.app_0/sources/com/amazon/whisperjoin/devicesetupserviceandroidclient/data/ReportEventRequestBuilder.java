package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetup.common.v1.Event;
import com.amazon.devicesetupservice.reporting.KeyExchangeMethod;
import com.amazon.devicesetupservice.reporting.KeyManagement;
import com.amazon.devicesetupservice.reporting.NetworkState;
import com.amazon.devicesetupservice.reporting.ProvisioningMethod;
import com.amazon.devicesetupservice.reporting.Radio;
import com.amazon.devicesetupservice.reporting.RegistrationState;
import com.amazon.devicesetupservice.reporting.State;
import com.amazon.devicesetupservice.reporting.TrustMethod;
import com.amazon.devicesetupservice.v1.CredentialLockerUsageInfo;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.devicesetupservice.v1.WifiNetworkInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes13.dex */
public class ReportEventRequestBuilder {
    private static final int SEQUENCE_NUMBER_MIN = 1;
    private static final Set<String> STATE_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(State.values())));
    private static final Set<String> EVENT_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(Event.values())));
    private static final Set<String> PROVISIONING_METHOD_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(ProvisioningMethod.values())));
    private static final Set<String> TRUST_METHOD_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(TrustMethod.values())));
    private static final Set<String> KEY_EXCHANGE_METHOD_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(KeyExchangeMethod.values())));
    private static final Set<String> RADIO_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(Radio.values())));
    private static final Set<String> REGISTRATION_STATE_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(RegistrationState.values())));
    private static final Set<String> NETWORK_STATE_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(NetworkState.values())));
    private static final Set<String> KEY_MANAGEMENT_VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(KeyManagement.values())));
    private String mReportingUrl = null;
    private String mState = null;
    private String mEvent = null;
    private int mSequenceNumber = 0;
    private String mProvisioningMethod = null;
    private String mTrustMethod = null;
    private String mKeyExchangeMethod = null;
    private String mRadio = null;
    private String mRegistrationState = null;
    private WifiNetworkInfo mWifiNetworkInfo = null;
    private ProvisionableInfo mProvisionableInfo = null;
    private ProvisionerInfo mProvisionerInfo = null;
    private ErrorInfo mErrorInfo = null;
    private CredentialLockerUsageInfo mCredentialLockerUsageInfo = null;

    public ReportEventRequest createReportEventRequest() {
        if (this.mReportingUrl != null) {
            String str = this.mState;
            if (str != null) {
                if (STATE_VALUES.contains(str)) {
                    String str2 = this.mEvent;
                    if (str2 != null) {
                        if (EVENT_VALUES.contains(str2)) {
                            if (this.mSequenceNumber >= 1) {
                                String str3 = this.mProvisioningMethod;
                                if (str3 != null && !PROVISIONING_METHOD_VALUES.contains(str3)) {
                                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mProvisioningMethod not a recognized value: ");
                                    outline107.append(this.mProvisioningMethod);
                                    throw new IllegalArgumentException(outline107.toString());
                                }
                                String str4 = this.mTrustMethod;
                                if (str4 != null && !TRUST_METHOD_VALUES.contains(str4)) {
                                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("mTrustMethod not a recognized value: ");
                                    outline1072.append(this.mTrustMethod);
                                    throw new IllegalArgumentException(outline1072.toString());
                                }
                                String str5 = this.mKeyExchangeMethod;
                                if (str5 != null && !KEY_EXCHANGE_METHOD_VALUES.contains(str5)) {
                                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("mKeyExchangeMethod not a recognized value: ");
                                    outline1073.append(this.mKeyExchangeMethod);
                                    throw new IllegalArgumentException(outline1073.toString());
                                }
                                String str6 = this.mRadio;
                                if (str6 != null && !RADIO_VALUES.contains(str6)) {
                                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("mRadio not a recognized value: ");
                                    outline1074.append(this.mRadio);
                                    throw new IllegalArgumentException(outline1074.toString());
                                }
                                String str7 = this.mRegistrationState;
                                if (str7 != null && !REGISTRATION_STATE_VALUES.contains(str7)) {
                                    StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("mRegistrationState not a recognized value: ");
                                    outline1075.append(this.mRegistrationState);
                                    throw new IllegalArgumentException(outline1075.toString());
                                }
                                WifiNetworkInfo wifiNetworkInfo = this.mWifiNetworkInfo;
                                if (wifiNetworkInfo != null && wifiNetworkInfo.getNetworkState() != null && !NETWORK_STATE_VALUES.contains(this.mWifiNetworkInfo.getNetworkState())) {
                                    StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("mNetworkState in mWifiNetworkInfo is not a recognized value: ");
                                    outline1076.append(this.mWifiNetworkInfo.getNetworkState());
                                    throw new IllegalArgumentException(outline1076.toString());
                                }
                                WifiNetworkInfo wifiNetworkInfo2 = this.mWifiNetworkInfo;
                                if (wifiNetworkInfo2 != null && wifiNetworkInfo2.getKeyManagement() != null && !KEY_MANAGEMENT_VALUES.contains(this.mWifiNetworkInfo.getKeyManagement())) {
                                    StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("mKeyManagement in mWifiNetworkInfo is not a recognized value: ");
                                    outline1077.append(this.mWifiNetworkInfo.getKeyManagement());
                                    throw new IllegalArgumentException(outline1077.toString());
                                }
                                CredentialLockerUsageInfo credentialLockerUsageInfo = this.mCredentialLockerUsageInfo;
                                if (credentialLockerUsageInfo != null) {
                                    if (credentialLockerUsageInfo.getSsidFromCredlocker() != null) {
                                        if (this.mCredentialLockerUsageInfo.getUserIntendsToSaveCredentialToLocker() != null) {
                                            if (this.mCredentialLockerUsageInfo.getChosenSSIDCredChanged() == null) {
                                                throw new IllegalArgumentException("ChosenSSIDCredChanged in mCredentialLockerUsageInfo is null");
                                            }
                                        } else {
                                            throw new IllegalArgumentException("UserIntendsToSaveCredentialToLocker in mCredentialLockerUsageInfo is null");
                                        }
                                    } else {
                                        throw new IllegalArgumentException("SsidFromCredlocker in mCredentialLockerUsageInfo is null");
                                    }
                                }
                                return new ReportEventRequest(this.mReportingUrl, this.mState, this.mEvent, this.mSequenceNumber, this.mProvisioningMethod, this.mTrustMethod, this.mKeyExchangeMethod, this.mRadio, this.mRegistrationState, this.mWifiNetworkInfo, this.mProvisionableInfo, this.mProvisionerInfo, this.mErrorInfo, this.mCredentialLockerUsageInfo);
                            }
                            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("mSequenceNumber has to be greater than 1, found ");
                            outline1078.append(this.mSequenceNumber);
                            throw new IllegalArgumentException(outline1078.toString());
                        }
                        StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("mEvent not a recognized value: ");
                        outline1079.append(this.mEvent);
                        throw new IllegalArgumentException(outline1079.toString());
                    }
                    throw new IllegalArgumentException("mEvent can not be null");
                }
                StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("mState not a recognized value: ");
                outline10710.append(this.mState);
                throw new IllegalArgumentException(outline10710.toString());
            }
            throw new IllegalArgumentException("mState can not be null");
        }
        throw new IllegalArgumentException("mReportingUrl can not be null");
    }

    public ReportEventRequestBuilder setCredentialLockerUsageInfo(CredentialLockerUsageInfo credentialLockerUsageInfo) {
        this.mCredentialLockerUsageInfo = credentialLockerUsageInfo;
        return this;
    }

    public ReportEventRequestBuilder setErrorInfo(ErrorInfo errorInfo) {
        this.mErrorInfo = errorInfo;
        return this;
    }

    public ReportEventRequestBuilder setEvent(String str) {
        this.mEvent = str;
        return this;
    }

    public ReportEventRequestBuilder setKeyExchangeMethod(String str) {
        this.mKeyExchangeMethod = str;
        return this;
    }

    public ReportEventRequestBuilder setProvisionableInfo(ProvisionableInfo provisionableInfo) {
        this.mProvisionableInfo = provisionableInfo;
        return this;
    }

    public ReportEventRequestBuilder setProvisionerInfo(ProvisionerInfo provisionerInfo) {
        this.mProvisionerInfo = provisionerInfo;
        return this;
    }

    public ReportEventRequestBuilder setProvisioningMethod(String str) {
        this.mProvisioningMethod = str;
        return this;
    }

    public ReportEventRequestBuilder setRadio(String str) {
        this.mRadio = str;
        return this;
    }

    public ReportEventRequestBuilder setRegistrationState(String str) {
        this.mRegistrationState = str;
        return this;
    }

    public ReportEventRequestBuilder setReportingUrl(String str) {
        this.mReportingUrl = str;
        return this;
    }

    public ReportEventRequestBuilder setSequenceNumber(int i) {
        this.mSequenceNumber = i;
        return this;
    }

    public ReportEventRequestBuilder setState(String str) {
        this.mState = str;
        return this;
    }

    public ReportEventRequestBuilder setTrustMethod(String str) {
        this.mTrustMethod = str;
        return this;
    }

    public ReportEventRequestBuilder setWifiNetworkInfo(WifiNetworkInfo wifiNetworkInfo) {
        this.mWifiNetworkInfo = wifiNetworkInfo;
        return this;
    }
}
