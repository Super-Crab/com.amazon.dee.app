package com.amazon.alexa;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
/* compiled from: InteractionAudioFocusRequest.java */
@TargetApi(26)
/* loaded from: classes.dex */
public class pWO {
    public static final String zZm = "pWO";
    public final AudioFocusRequest BIo;
    public final zZm zQM;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: InteractionAudioFocusRequest.java */
    /* loaded from: classes.dex */
    public class zZm implements AudioManager.OnAudioFocusChangeListener {
        public boolean BIo = true;
        public final AudioManager.OnAudioFocusChangeListener zZm;

        public zZm(pWO pwo, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
            this.zZm = onAudioFocusChangeListener;
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public synchronized void onAudioFocusChange(int i) {
            String str = "onAudioFocusChange: " + i;
            if (this.BIo) {
                this.zZm.onAudioFocusChange(i);
            } else {
                Log.i(pWO.zZm, "Ignoring focus change to old AudioFocusRequest: " + i);
            }
        }

        public synchronized void zZm(boolean z) {
            this.BIo = z;
        }
    }

    public pWO(PJz pJz, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        int i = Build.VERSION.SDK_INT;
        this.zQM = new zZm(this, onAudioFocusChangeListener);
        PNy pNy = (PNy) pJz;
        AudioFocusRequest.Builder builder = new AudioFocusRequest.Builder(pNy.BIo.zZm());
        AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
        builder2.setUsage(pNy.zQM.zZm());
        builder2.setContentType(pNy.zyO.zZm());
        this.BIo = builder.setAudioAttributes(builder2.build()).setOnAudioFocusChangeListener(this.zQM).setAcceptsDelayedFocusGain(true).setWillPauseWhenDucked(true).build();
    }
}
