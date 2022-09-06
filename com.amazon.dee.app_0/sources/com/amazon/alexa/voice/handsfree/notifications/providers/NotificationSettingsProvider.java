package com.amazon.alexa.voice.handsfree.notifications.providers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.api.SettingsProvider;
import com.amazon.alexa.handsfree.settings.quicksettings.AlexaQuickSettingTileStore;
/* loaded from: classes11.dex */
public class NotificationSettingsProvider implements SettingsProvider {
    private final AlexaQuickSettingTileStore mAlexaQuickSettingTileStore;

    public NotificationSettingsProvider(@NonNull Context context) {
        this(new AlexaQuickSettingTileStore(context));
    }

    @Override // com.amazon.alexa.handsfree.notification.api.SettingsProvider
    public boolean hasInteractedWithQsTile() {
        return this.mAlexaQuickSettingTileStore.hasInteractedWithQsTile();
    }

    @Override // com.amazon.alexa.handsfree.notification.api.SettingsProvider
    public boolean isQsTileInMainMenu() {
        return this.mAlexaQuickSettingTileStore.isQsTileInMainMenu();
    }

    @VisibleForTesting
    NotificationSettingsProvider(@NonNull AlexaQuickSettingTileStore alexaQuickSettingTileStore) {
        this.mAlexaQuickSettingTileStore = alexaQuickSettingTileStore;
    }
}
