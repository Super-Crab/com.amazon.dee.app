package com.amazon.alexa.alertsca.exoplayer;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.devicesetup.common.v1.WifiConnectionState;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.Player;
/* loaded from: classes6.dex */
public class AmplitudeCalculatingEventListener extends Player.DefaultEventListener {
    private static final String TAG = "AmplitudeCalculatingEventListener";
    private final AmplitudeCalculatingAudioSink amplitudeCalculatingAudioSink;
    private final AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor;

    public AmplitudeCalculatingEventListener(AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor, AmplitudeCalculatingAudioSink amplitudeCalculatingAudioSink) {
        this.amplitudeMultiplyingAudioProcessor = amplitudeMultiplyingAudioProcessor;
        this.amplitudeCalculatingAudioSink = amplitudeCalculatingAudioSink;
    }

    private static String getStateString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? WebConstants.UriConstants.QUESTIONMARK_KEY : "ENDED" : "READY" : EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_STATE_BUFFERING : WifiConnectionState.IDLE;
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean z, int i) {
        GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("onPlayerStateChanged: "), getStateString(i), TAG);
        if (i == 4 && this.amplitudeMultiplyingAudioProcessor.isAmplificationEnabled()) {
            this.amplitudeMultiplyingAudioProcessor.setMultiplier(this.amplitudeCalculatingAudioSink.getMaxAmplitudeMultiplier());
        }
    }
}
