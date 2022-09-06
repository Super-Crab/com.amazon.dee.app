package com.amazon.alexa.handsfree.settings.quicksettings;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class AlexaQuickSettingTileStore {
    @VisibleForTesting
    static final String QS_TILE_INTERACTION = "com.amazon.alexa.handsfree.settings.quicksettings.qs_tile_interaction";
    @VisibleForTesting
    static final String QS_TILE_LOCATION = "com.amazon.alexa.handsfree.settings.quicksettings.qs_tile_location";
    @VisibleForTesting
    static final String SHARED_PREFERENCES_FILE = "com.amazon.alexa.handsfree.settings.quicksettings.AlexaQuickSettingTileStore";
    private final SharedPreferences mSharedPreferences;

    /* loaded from: classes8.dex */
    enum QuickSettingTileLocation {
        MAIN_MENU(1),
        EDIT_MENU(0);
        
        private int mValue;

        QuickSettingTileLocation(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public AlexaQuickSettingTileStore(@NonNull Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
    }

    public boolean hasInteractedWithQsTile() {
        return this.mSharedPreferences.getBoolean(QS_TILE_INTERACTION, false);
    }

    public boolean isQsTileInMainMenu() {
        return this.mSharedPreferences.getInt(QS_TILE_LOCATION, 0) == QuickSettingTileLocation.MAIN_MENU.mValue;
    }

    public void markInteractedWithQsTile() {
        GeneratedOutlineSupport1.outline143(this.mSharedPreferences, QS_TILE_INTERACTION, true);
    }

    public void setQsTileLocation(QuickSettingTileLocation quickSettingTileLocation) {
        this.mSharedPreferences.edit().putInt(QS_TILE_LOCATION, quickSettingTileLocation.getValue()).apply();
    }
}
