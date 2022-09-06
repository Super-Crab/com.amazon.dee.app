package com.amazon.alexa;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: AttentionSystemSettingsStore.java */
@Singleton
/* loaded from: classes.dex */
public class yGK {
    public final Lazy<PersistentStorage> zZm;

    /* compiled from: AttentionSystemSettingsStore.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    enum zZm {
        WAKE_SOUND_ENABLED("wakeSoundEnabled"),
        ENDPOINT_SOUND_ENABLED("endpointSoundEnabled");
        
        public final String key;

        zZm(String str) {
            this.key = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.key;
        }
    }

    @Inject
    public yGK(@Named("AlexaDevicePreferences") Lazy<PersistentStorage> lazy) {
        this.zZm = lazy;
    }

    public synchronized void BIo(boolean z) {
        this.zZm.mo358get().edit().set(zZm.WAKE_SOUND_ENABLED.toString(), z).commitAsynchronously();
    }

    public synchronized void zQM() {
        this.zZm.mo358get().edit().remove(zZm.ENDPOINT_SOUND_ENABLED.toString()).commitAsynchronously();
    }

    public synchronized void zZm(boolean z) {
        this.zZm.mo358get().edit().set(zZm.ENDPOINT_SOUND_ENABLED.toString(), z).commitAsynchronously();
    }

    public synchronized void zyO() {
        this.zZm.mo358get().edit().remove(zZm.WAKE_SOUND_ENABLED.toString()).commitAsynchronously();
    }

    public synchronized boolean BIo() {
        return this.zZm.mo358get().getBoolean(zZm.WAKE_SOUND_ENABLED.toString(), false);
    }

    public synchronized boolean zZm() {
        return this.zZm.mo358get().getBoolean(zZm.ENDPOINT_SOUND_ENABLED.toString(), false);
    }
}
