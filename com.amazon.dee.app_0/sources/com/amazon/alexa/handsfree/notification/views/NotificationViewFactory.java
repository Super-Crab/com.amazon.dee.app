package com.amazon.alexa.handsfree.notification.views;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.channels.NotificationChannelProperties;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.notification.views.decider.DeciderNotification;
import com.amazon.alexa.handsfree.notification.views.enablehandsfree.EnableHandsFreeNotification;
import com.amazon.alexa.handsfree.notification.views.killswitch.KillSwitchNotification;
import com.amazon.alexa.handsfree.notification.views.languageswitching.LanguageSwitchingNotification;
import com.amazon.alexa.handsfree.notification.views.lockscreen.ShowOnLockscreenNotification;
import com.amazon.alexa.handsfree.notification.views.permission.PermissionNotification;
import com.amazon.alexa.handsfree.notification.views.quicksettings.QuickSettingsNotification;
import com.amazon.alexa.handsfree.notification.views.voiceprofile.VoiceProfileNotification;
/* loaded from: classes8.dex */
public class NotificationViewFactory {
    public NotificationView getDeciderNotification(@NonNull NotificationType notificationType) {
        return new DeciderNotification(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId(), notificationType);
    }

    public NotificationView getEnableHandsFreeNotification() {
        return new EnableHandsFreeNotification(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId());
    }

    public NotificationView getKillSwitchNotification() {
        return new KillSwitchNotification(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId());
    }

    public NotificationView getLanguageSwitchingNotification() {
        return new LanguageSwitchingNotification(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId());
    }

    public NotificationView getLockScreenNotification() {
        return new ShowOnLockscreenNotification(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId());
    }

    public NotificationView getPermissionNotification() {
        return new PermissionNotification(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId());
    }

    public NotificationView getQuickSettingsNotification() {
        return new QuickSettingsNotification(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId());
    }

    public NotificationView getVoiceProfileNotification() {
        return new VoiceProfileNotification(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId());
    }
}
