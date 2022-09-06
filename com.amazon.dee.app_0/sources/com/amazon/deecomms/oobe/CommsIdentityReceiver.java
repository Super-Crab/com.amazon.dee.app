package com.amazon.deecomms.oobe;
/* loaded from: classes12.dex */
public interface CommsIdentityReceiver {
    void onVerificationFailure(VerificationFailure verificationFailure);

    void onVerifiedIdentity(String str, String str2, String str3);
}
