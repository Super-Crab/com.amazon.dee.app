package com.amazon.deecomms.calling.api;
/* loaded from: classes12.dex */
public interface CallStateListener {
    void onCallAccepted(CallInfo callInfo);

    void onCallAdded(CallInfo callInfo);

    void onCallEnded(CallInfo callInfo);

    void onCallError(int i, String str);
}
