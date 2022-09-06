package com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree;
/* loaded from: classes8.dex */
public interface HandsFreeSettingContract {

    /* loaded from: classes8.dex */
    public interface Listener {
        void setActive();

        void setInactive();

        void setLocallyDisabled();

        void setRemotelyDisabled();
    }

    /* loaded from: classes8.dex */
    public interface View {
        void setActive();

        void setInactive();

        void setLocallyDisabled();

        void setRemotelyDisabled();

        void setupListener(ViewListener viewListener);
    }

    /* loaded from: classes8.dex */
    public interface ViewListener {
        boolean onPreferenceChanged();
    }

    /* loaded from: classes8.dex */
    public interface WakeWordManager {
        boolean isWakeWordActive();

        void toggleWakeWordDetection();
    }
}
