package com.amazon.alexa.voice.ftue;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.storage.PersistentStorage;
/* loaded from: classes11.dex */
public class UserFtuePreference implements FtuePreference {
    @VisibleForTesting
    static final String HANDS_FREE_PRIMER = "hands_free_primer";
    @VisibleForTesting
    static final String PRIMER_SHOWN = "primer";
    @VisibleForTesting
    static final String VOICE_STORAGE = "voice";
    private final PersistentStorage storage;

    public UserFtuePreference(PersistentStorage.Factory factory) {
        this.storage = factory.create("voice");
    }

    @Override // com.amazon.alexa.voice.ftue.FtuePreference
    public void clearLegacyFtuePreference() {
        this.storage.edit().remove(PRIMER_SHOWN).commit();
    }

    @Override // com.amazon.alexa.voice.ftue.FtuePreference
    public void clearWakewordFtuePreference() {
        this.storage.edit().remove(HANDS_FREE_PRIMER).commit();
    }

    @Override // com.amazon.alexa.voice.ftue.FtuePreference
    public boolean hasCompletedLegacyFtue() {
        return this.storage.getBoolean(PRIMER_SHOWN, false);
    }

    @Override // com.amazon.alexa.voice.ftue.FtuePreference
    public boolean hasCompletedWakewordFtue() {
        return this.storage.getBoolean(HANDS_FREE_PRIMER, false);
    }

    @Override // com.amazon.alexa.voice.ftue.FtuePreference
    public void setLegacyFtueCompleted() {
        this.storage.edit().set(PRIMER_SHOWN, true).commit();
    }

    @Override // com.amazon.alexa.voice.ftue.FtuePreference
    public void setWakewordFtueCompleted() {
        this.storage.edit().set(HANDS_FREE_PRIMER, true).commit();
    }
}
