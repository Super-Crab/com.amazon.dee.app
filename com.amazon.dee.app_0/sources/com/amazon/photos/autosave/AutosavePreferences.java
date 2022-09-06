package com.amazon.photos.autosave;

import androidx.annotation.WorkerThread;
import com.amazon.photos.autosave.model.AutosaveBucket;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.discovery.model.LocalFolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosavePreferences.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\bf\u0018\u0000 $2\u00020\u0001:\u0001$J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH'J\b\u0010\u000b\u001a\u00020\u0007H'J\b\u0010\f\u001a\u00020\u0007H'J\b\u0010\r\u001a\u00020\u0007H'J\b\u0010\u000e\u001a\u00020\u0007H'J\b\u0010\u000f\u001a\u00020\u0007H'J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H'J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H'J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0007H'J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0007H'J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0007H'J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0007H'J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0007H'J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u0007H'J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0007H'¨\u0006%"}, d2 = {"Lcom/amazon/photos/autosave/AutosavePreferences;", "", "enableAutosaveForFolder", "", "folder", "Lcom/amazon/photos/discovery/model/LocalFolder;", "enabled", "", "getAutoSavedFolders", "", "Lcom/amazon/photos/autosave/model/AutosaveBucket;", "isAddToFamilyEnabled", "isAllFoldersEnabled", "isAllowedOnLowBattery", "isAllowedOnPowerSaverMode", "isAllowedOnlyWhileCharging", "isAutosaveAllowedOnMetered", "itemType", "Lcom/amazon/photos/autosave/model/MediaType;", "isAutosaveEnabled", "isAutosaveEnabledForFolder", "setAddToFamilyPreferences", "addToFamily", "setAllFolderPreferences", "enableAllFolders", "setAutosavePreference", "mediaType", "enable", "setChargingPreferences", "uploadOnlyOnCharging", "setLowBatteryPreferences", "uploadOnLowBattery", "setMeteredNetworkPreferences", "enableOnMetered", "setPowerSaverPreferences", "uploadOnPowerSaverMode", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface AutosavePreferences {
    @NotNull
    public static final String ADD_FAMILY_SETTINGS = "add_family_settings";
    @NotNull
    public static final String ALL_FOLDERS_SETTINGS = "all_folders_settings";
    @NotNull
    public static final String CHARGING_SETTINGS = "charging_settings";
    public static final Companion Companion = Companion.$$INSTANCE;
    @NotNull
    public static final String LOW_BATTERY_SETTINGS = "low_battery_settings";
    @NotNull
    public static final String POWER_SAVER_SETTINGS = "power_saver_settings";

    /* compiled from: AutosavePreferences.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/autosave/AutosavePreferences$Companion;", "", "()V", "ADD_FAMILY_SETTINGS", "", "ALL_FOLDERS_SETTINGS", "AUTOSAVE_SETTINGS_PREFIX", "CHARGING_SETTINGS", "LOW_BATTERY_SETTINGS", "METERED_NETWORK_SETTINGS_PREFIX", "POWER_SAVER_SETTINGS", "getAutosavePreferenceKey", "itemType", "Lcom/amazon/photos/autosave/model/MediaType;", "getMeteredNetworkPreferenceKey", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        public static final String ADD_FAMILY_SETTINGS = "add_family_settings";
        @NotNull
        public static final String ALL_FOLDERS_SETTINGS = "all_folders_settings";
        private static final String AUTOSAVE_SETTINGS_PREFIX = "autosave_settings";
        @NotNull
        public static final String CHARGING_SETTINGS = "charging_settings";
        @NotNull
        public static final String LOW_BATTERY_SETTINGS = "low_battery_settings";
        private static final String METERED_NETWORK_SETTINGS_PREFIX = "metered_network_settings";
        @NotNull
        public static final String POWER_SAVER_SETTINGS = "power_saver_settings";

        private Companion() {
        }

        @NotNull
        public final String getAutosavePreferenceKey(@NotNull MediaType itemType) {
            Intrinsics.checkParameterIsNotNull(itemType, "itemType");
            return "autosave_settings_" + itemType.name();
        }

        @NotNull
        public final String getMeteredNetworkPreferenceKey(@NotNull MediaType itemType) {
            Intrinsics.checkParameterIsNotNull(itemType, "itemType");
            return "metered_network_settings_" + itemType.name();
        }
    }

    @WorkerThread
    void enableAutosaveForFolder(@NotNull LocalFolder localFolder, boolean z);

    @WorkerThread
    @NotNull
    List<AutosaveBucket> getAutoSavedFolders();

    @WorkerThread
    boolean isAddToFamilyEnabled();

    @WorkerThread
    boolean isAllFoldersEnabled();

    @WorkerThread
    boolean isAllowedOnLowBattery();

    @WorkerThread
    boolean isAllowedOnPowerSaverMode();

    @WorkerThread
    boolean isAllowedOnlyWhileCharging();

    @WorkerThread
    boolean isAutosaveAllowedOnMetered(@NotNull MediaType mediaType);

    @WorkerThread
    boolean isAutosaveEnabled(@NotNull MediaType mediaType);

    @WorkerThread
    boolean isAutosaveEnabledForFolder(@NotNull LocalFolder localFolder);

    @WorkerThread
    void setAddToFamilyPreferences(boolean z);

    @WorkerThread
    void setAllFolderPreferences(boolean z);

    @WorkerThread
    void setAutosavePreference(@NotNull MediaType mediaType, boolean z);

    @WorkerThread
    void setChargingPreferences(boolean z);

    @WorkerThread
    void setLowBatteryPreferences(boolean z);

    @WorkerThread
    void setMeteredNetworkPreferences(@NotNull MediaType mediaType, boolean z);

    @WorkerThread
    void setPowerSaverPreferences(boolean z);
}
