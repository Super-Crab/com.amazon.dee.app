package com.amazon.alexa.voice.ftue;
/* loaded from: classes11.dex */
public interface FtuePreference {
    void clearLegacyFtuePreference();

    void clearWakewordFtuePreference();

    boolean hasCompletedLegacyFtue();

    boolean hasCompletedWakewordFtue();

    void setLegacyFtueCompleted();

    void setWakewordFtueCompleted();
}
