package com.amazon.alexa.alertsca;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AudioFocusManager implements AudioManager.OnAudioFocusChangeListener {
    private static final String TAG = AudioFocusManager.class.getSimpleName();
    private AlertInteraction alertInteraction;
    private AudioFocusRequest audioFocusRequest;
    private AudioManager audioManager;
    private volatile boolean hasFocus;

    @Inject
    public AudioFocusManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    private int requestFocusFromSystem() {
        int i = Build.VERSION.SDK_INT;
        this.audioFocusRequest = new AudioFocusRequest.Builder(3).setAudioAttributes(new AudioAttributes.Builder().setUsage(4).setContentType(2).build()).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(this).build();
        return this.audioManager.requestAudioFocus(this.audioFocusRequest);
    }

    public synchronized void abandonRequest() {
        int i = Build.VERSION.SDK_INT;
        if (this.audioFocusRequest != null) {
            this.audioManager.abandonAudioFocusRequest(this.audioFocusRequest);
        }
        this.hasFocus = false;
    }

    public boolean hasFocus() {
        return this.hasFocus;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public synchronized void onAudioFocusChange(int i) {
        String str = "OnAudioFocusChange : " + i;
        if (i == -3) {
            this.hasFocus = false;
            this.alertInteraction.lostFocus();
        } else if (i == -2) {
            this.hasFocus = false;
            this.alertInteraction.lostFocus();
        } else if (i == -1) {
            this.hasFocus = false;
            this.alertInteraction.lostFocus();
        } else if (i == 1) {
            this.hasFocus = true;
            this.alertInteraction.gainedFocus();
        } else if (i == 2) {
            this.hasFocus = true;
            this.alertInteraction.gainedFocus();
        }
    }

    public synchronized boolean requestFocus(AlertInteraction alertInteraction) {
        this.alertInteraction = alertInteraction;
        if (!this.hasFocus) {
            boolean z = true;
            if (requestFocusFromSystem() != 1) {
                z = false;
            }
            this.hasFocus = z;
        }
        return this.hasFocus;
    }
}
