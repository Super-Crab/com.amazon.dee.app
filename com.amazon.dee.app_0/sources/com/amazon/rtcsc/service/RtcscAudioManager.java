package com.amazon.rtcsc.service;

import android.content.Context;
import android.media.AudioManager;
import com.amazon.rtcsc.utils.RtcscLogger;
/* loaded from: classes13.dex */
public class RtcscAudioManager {
    private AudioManager mAudioManager;
    private final RtcscLogger mLog = RtcscLogger.getLogger(RtcscAudioManager.class);
    private int mSavedAudioMode;
    private boolean mShouldRestore;

    public RtcscAudioManager(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public void restoreAudioMode() {
        if (this.mShouldRestore) {
            this.mAudioManager.setMode(this.mSavedAudioMode);
            this.mLog.i("Restoring the audio mode");
            this.mShouldRestore = false;
            return;
        }
        this.mLog.i("Skipped restoring audio mode.");
    }

    public void setCommunicationsMode() {
        int mode = this.mAudioManager.getMode();
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.i("Current Audio Mode is " + mode);
        if (mode != 3) {
            this.mSavedAudioMode = mode;
            this.mShouldRestore = true;
            this.mAudioManager.setMode(3);
            this.mLog.i("Audio Mode set to MODE_IN_COMMUNICATION");
            return;
        }
        this.mLog.i("Skipped setting audio mode. Already set to MODE_IN_COMMUNICATION");
    }
}
