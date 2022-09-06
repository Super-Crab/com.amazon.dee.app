package com.amazon.deecomms.calling.initiation;

import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
/* loaded from: classes12.dex */
public interface InitiationLogicContract {
    void initiateAudioCall(String str, String str2);

    void initiateCoboCall(String str, String str2, ContactPhoneNumber contactPhoneNumber);

    @Deprecated
    void initiateCoboCallFromActivity(String str, String str2, ContactPhoneNumber contactPhoneNumber);

    void initiateCoboCallWithoutWarning(String str, String str2, ContactPhoneNumber contactPhoneNumber);

    void initiateContactDropIn(String str, String str2);

    void initiateDeviceDropIn(String str, String str2, String str3, boolean z, String str4);

    void initiateDeviceTargetedCall(String str, String str2, String str3, boolean z);

    void initiateGroupCall(String str);

    void initiateGroupDropIn(String str);

    void initiateSkypeCall(String str);

    void initiateTargetedDropIn(String str, String str2, String str3, String str4, boolean z, boolean z2);

    void initiateVideoCall(String str, String str2);
}
