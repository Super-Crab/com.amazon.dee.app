package com.amazon.comms.calling.service;
/* loaded from: classes11.dex */
public interface CallListener {
    void onAccepted(Call call);

    void onEarlyMedia(Call call);

    void onError(Call call, ErrorCode errorCode, int i, String str);

    void onHangup(Call call, HangupReason hangupReason);

    void onMediaDTMFResponse(Call call, boolean z, String str);

    void onReconnect(Call call, boolean z, boolean z2, String str);

    void onRemoteParticipantUpdated(Call call);

    void onRinging(Call call);

    void onSignalingDTMFResponse(Call call, int i, String str);
}
