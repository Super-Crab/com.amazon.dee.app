package com.amazon.deecomms.calling.contracts.active;

import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.accessibility.RealTimeTextPresenter;
import com.amazon.deecomms.calling.contracts.BasePresenterContract;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
/* loaded from: classes12.dex */
public interface ActiveAudioCallPresenterContract extends BasePresenterContract {
    void endCall();

    RealTimeTextEnablementAuthority getRealTimeTextEnablementAuthority();

    RealTimeTextPresenter getRealTimeTextPresenter();

    String getRemoteParticipantName();

    TelecomCallAudioManager getTelecomCallAudioManager();

    boolean isRTTEnabled();

    void onCallDowngradeMessageExpiry();

    void onViewBackgrounded();

    void onViewForegrounded();

    void toggleMic();

    void toggleSpeaker();
}
