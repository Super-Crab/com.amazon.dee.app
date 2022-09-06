package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public class NotifierFactory {
    private final Context mContext;

    public NotifierFactory(@NonNull Context context) {
        this.mContext = context;
    }

    public Notifier getEnableHandsFreeNotifier() {
        return new EnableHandsFreeNotifier(this.mContext);
    }

    public Notifier getHandsFreeTimeBasedNotifier() {
        return new HandsFreeTimeBasedNotifier(this.mContext);
    }

    public Notifier getHandsFreeUtteranceNotifier(@NonNull Intent intent) {
        return new HandsFreeUtteranceNotifier(this.mContext, intent);
    }

    public Notifier getKillSwitchNotifier() {
        return new KillSwitchNotifier(this.mContext);
    }

    public Notifier getLanguageSwitchingNotifier() {
        return new LanguageSwitchingNotifier(this.mContext);
    }

    public Notifier getPermissionNotifier() {
        return new PermissionNotifier(this.mContext);
    }

    public Notifier getQuickSettingsNotifier() {
        return new QuickSettingsNotifier(this.mContext);
    }

    public Notifier getShowOnLockScreenNotifier(@NonNull Intent intent) {
        return new ShowOnLockScreenNotifier(this.mContext, intent);
    }

    public Notifier getVoiceProfileSetupNotifier(@NonNull Intent intent) {
        return new VoiceProfileSetupNotifier(this.mContext, intent);
    }
}
