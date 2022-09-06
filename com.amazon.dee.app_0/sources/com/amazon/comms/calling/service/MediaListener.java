package com.amazon.comms.calling.service;

import com.amazon.comms.calling.service.Call;
/* loaded from: classes11.dex */
public interface MediaListener {
    void onCameraSwitchDone(boolean z);

    void onCameraSwitchError(String str);

    void onDtmfTonesSent(Call call, String str, int i, int i2);

    void onFirstFrameReceived(Call.Side side);

    void onFirstFrameRendered(Call.Side side);

    void onLocalFrameResolutionChanged(int i, int i2, int i3);

    void onMediaStatsUpdated(Call call);

    void onMediaStatusUpdated(Call call, Call.Side side, MediaStatus mediaStatus, MediaStateChangeTrigger mediaStateChangeTrigger);

    void onRemoteFrameResolutionChanged(int i, int i2, int i3);

    void onVideoEffectChanged(Call call);
}
