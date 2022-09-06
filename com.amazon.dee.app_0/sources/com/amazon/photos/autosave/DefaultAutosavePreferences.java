package com.amazon.photos.autosave;

import com.amazon.photos.autosave.model.MediaType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultAutosavePreferences.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/photos/autosave/DefaultAutosavePreferences;", "", "isAddToFamilyEnabled", "", "isAllFoldersEnabled", "isAllowedOnLowBattery", "isAllowedOnPowerSaverMode", "isAllowedOnlyWhileCharging", "isAutosaveAllowedOnMetered", "itemType", "Lcom/amazon/photos/autosave/model/MediaType;", "isAutosaveEnabled", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface DefaultAutosavePreferences {

    /* compiled from: DefaultAutosavePreferences.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        public static boolean isAddToFamilyEnabled(DefaultAutosavePreferences defaultAutosavePreferences) {
            return false;
        }

        public static boolean isAllFoldersEnabled(DefaultAutosavePreferences defaultAutosavePreferences) {
            return false;
        }

        public static boolean isAllowedOnLowBattery(DefaultAutosavePreferences defaultAutosavePreferences) {
            return false;
        }

        public static boolean isAllowedOnPowerSaverMode(DefaultAutosavePreferences defaultAutosavePreferences) {
            return true;
        }

        public static boolean isAllowedOnlyWhileCharging(DefaultAutosavePreferences defaultAutosavePreferences) {
            return false;
        }

        public static boolean isAutosaveAllowedOnMetered(DefaultAutosavePreferences defaultAutosavePreferences, @NotNull MediaType itemType) {
            Intrinsics.checkParameterIsNotNull(itemType, "itemType");
            return false;
        }

        public static boolean isAutosaveEnabled(DefaultAutosavePreferences defaultAutosavePreferences, @NotNull MediaType itemType) {
            Intrinsics.checkParameterIsNotNull(itemType, "itemType");
            return false;
        }
    }

    boolean isAddToFamilyEnabled();

    boolean isAllFoldersEnabled();

    boolean isAllowedOnLowBattery();

    boolean isAllowedOnPowerSaverMode();

    boolean isAllowedOnlyWhileCharging();

    boolean isAutosaveAllowedOnMetered(@NotNull MediaType mediaType);

    boolean isAutosaveEnabled(@NotNull MediaType mediaType);
}
