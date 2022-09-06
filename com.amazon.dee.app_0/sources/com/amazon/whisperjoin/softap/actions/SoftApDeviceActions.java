package com.amazon.whisperjoin.softap.actions;

import com.amazon.whisperjoin.softap.exceptions.SoftAPChannelUnsecuredException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidBssidException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidSsidException;
import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import com.amazon.whisperjoin.softap.pojos.RegistrationToken;
import com.amazon.whisperjoin.softap.pojos.WifiConfiguration;
import rx.Observable;
import rx.Single;
/* loaded from: classes13.dex */
public interface SoftApDeviceActions {
    Observable<Void> connect(String str) throws SoftAPInvalidSsidException;

    Observable<Void> connect(String str, String str2) throws SoftAPInvalidSsidException, SoftAPInvalidBssidException;

    void disconnect();

    Single<DeviceDetails> getDeviceDetails();

    Single<Void> provisionDevice(WifiConfiguration wifiConfiguration) throws SoftAPChannelUnsecuredException;

    Single<Void> saveProvisioningData(ProvisioningData provisioningData);

    Single<Void> saveRegistrationToken(RegistrationToken registrationToken) throws SoftAPChannelUnsecuredException;
}
