package com.amazon.alexa.voice.handsfree.notifications.providers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import java.util.List;
/* loaded from: classes11.dex */
public class NotificationConfigurationProvider implements ConfigurationProvider {
    private final RemoteConfigManager mRemoteConfigManager;

    /* renamed from: com.amazon.alexa.voice.handsfree.notifications.providers.NotificationConfigurationProvider$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$notification$api$ConfigurationProvider$ConfigComponent = new int[ConfigurationProvider.ConfigComponent.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$api$ConfigurationProvider$ConfigComponent[ConfigurationProvider.ConfigComponent.HANDS_FREE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$api$ConfigurationProvider$ConfigComponent[ConfigurationProvider.ConfigComponent.NOTIFICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$api$ConfigurationProvider$ConfigComponent[ConfigurationProvider.ConfigComponent.LOCK_SCREEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public NotificationConfigurationProvider(@NonNull RemoteConfigManager remoteConfigManager) {
        this.mRemoteConfigManager = remoteConfigManager;
    }

    @Override // com.amazon.alexa.handsfree.notification.api.ConfigurationProvider
    @Nullable
    public Integer getMaxAllowedNotificationsBeforeTerms(@NonNull Context context) {
        return this.mRemoteConfigManager.getMaxAllowedNotificationsBeforeTerms();
    }

    @Override // com.amazon.alexa.handsfree.notification.api.ConfigurationProvider
    @Nullable
    public Integer getMaxDaysAfterFirstUtterance(@NonNull Context context, @NonNull String str) {
        return this.mRemoteConfigManager.getMaxDaysAfterFirstUtterance(str);
    }

    @Override // com.amazon.alexa.handsfree.notification.api.ConfigurationProvider
    @Nullable
    public Integer getMinHoursBeforeNextUtterance(@NonNull Context context, @NonNull String str) {
        return this.mRemoteConfigManager.getMinHoursBeforeNextUtterance(str);
    }

    @Override // com.amazon.alexa.handsfree.notification.api.ConfigurationProvider
    @Nullable
    public List<Long> getNotificationTimeIntervals(@NonNull Context context, @NonNull String str) {
        return this.mRemoteConfigManager.getNotificationDisplayIntervals(str);
    }

    @Override // com.amazon.alexa.handsfree.notification.api.ConfigurationProvider
    @Nullable
    public Integer getUtteranceNotificationMaxCount(@NonNull Context context, @NonNull String str) {
        return this.mRemoteConfigManager.getUtteranceNotificationMaxCount(str);
    }

    @Override // com.amazon.alexa.handsfree.notification.api.ConfigurationProvider
    public boolean isActive(@NonNull Context context, @NonNull ConfigurationProvider.ConfigComponent configComponent) {
        int ordinal = configComponent.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return this.mRemoteConfigManager.isLockScreenActive();
            }
            if (ordinal == 2) {
                return this.mRemoteConfigManager.isNotificationsActive();
            }
            return true;
        }
        return this.mRemoteConfigManager.isHandsFreeActive();
    }
}
