package com.amazon.photos.autosave.internal.preferences;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.AutosavePreferences;
import com.amazon.photos.autosave.DefaultAutosavePreferences;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.coroutines.DispatcherProvider;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.metrics.AutosaveMetricEvents;
import com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier;
import com.amazon.photos.autosave.model.AutosaveBucket;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.autosave.model.ModelExtensionsKt;
import com.amazon.photos.discovery.model.LocalFolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosavePreferencesImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b!\b\u0000\u0018\u0000 F2\u00020\u0001:\u0001FBM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0016H\u0016J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J \u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010'\u001a\u00020\u0018H\u0003J\b\u0010(\u001a\u00020\u0016H\u0016J\b\u0010)\u001a\u00020\u0016H\u0016J\b\u0010*\u001a\u00020\u0016H\u0016J\b\u0010+\u001a\u00020\u0016H\u0016J\b\u0010,\u001a\u00020\u0016H\u0016J\u0010\u0010-\u001a\u00020\u00162\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010.\u001a\u00020\u00162\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010/\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u0016H\u0016J\u0010\u00102\u001a\u00020\u00182\u0006\u00103\u001a\u00020\u0016H\u0016J\u0018\u00104\u001a\u00020\u00182\u0006\u00105\u001a\u00020&2\u0006\u00106\u001a\u00020\u0016H\u0016J\u0010\u00107\u001a\u00020\u00182\u0006\u00108\u001a\u00020\u0016H\u0016J\u0010\u00109\u001a\u00020\u00182\u0006\u0010:\u001a\u00020\u0016H\u0016J\u0018\u0010;\u001a\u00020\u00182\u0006\u00105\u001a\u00020&2\u0006\u0010<\u001a\u00020\u0016H\u0016J\u0010\u0010=\u001a\u00020\u00182\u0006\u0010>\u001a\u00020\u0016H\u0016J-\u0010?\u001a\u00020\u00182\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0002\u0010AJI\u0010?\u001a\u00020\u00182\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0002\u0010ER\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/amazon/photos/autosave/internal/preferences/AutosavePreferencesImpl;", "Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;", "autosaveBucketDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;", "defaultPreferences", "Lcom/amazon/photos/autosave/DefaultAutosavePreferences;", "preferenceChangeListener", "Lcom/amazon/photos/autosave/internal/preferences/AutosavePreferenceChangeListener;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "autosaveOperations", "Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "dispatcherProvider", "Lcom/amazon/photos/autosave/internal/coroutines/DispatcherProvider;", "queueHelper", "Lcom/amazon/photos/autosave/internal/preferences/PreferenceUploadQueueHelper;", "sharedPreferences", "Landroid/content/SharedPreferences;", "autosaveEventNotifier", "Lcom/amazon/photos/autosave/internal/observers/AutosaveEventNotifier;", "(Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;Lcom/amazon/photos/autosave/DefaultAutosavePreferences;Lcom/amazon/photos/autosave/internal/preferences/AutosavePreferenceChangeListener;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/autosave/internal/AutosaveOperations;Lcom/amazon/photos/autosave/internal/coroutines/DispatcherProvider;Lcom/amazon/photos/autosave/internal/preferences/PreferenceUploadQueueHelper;Landroid/content/SharedPreferences;Lcom/amazon/photos/autosave/internal/observers/AutosaveEventNotifier;)V", "isDestroyed", "", "destroy", "", "enableAutosaveForFolder", "folder", "Lcom/amazon/photos/discovery/model/LocalFolder;", "enabled", "getAutoSavedFolders", "", "Lcom/amazon/photos/autosave/model/AutosaveBucket;", "getMetricEnableSuffix", "", "getMetricName", "Lcom/amazon/clouddrive/android/core/interfaces/MetricName;", "prefix", "itemType", "Lcom/amazon/photos/autosave/model/MediaType;", "initPreferenceUploadQueueConstraints", "isAddToFamilyEnabled", "isAllFoldersEnabled", "isAllowedOnLowBattery", "isAllowedOnPowerSaverMode", "isAllowedOnlyWhileCharging", "isAutosaveAllowedOnMetered", "isAutosaveEnabled", "isAutosaveEnabledForFolder", "setAddToFamilyPreferences", "addToFamily", "setAllFolderPreferences", "enableAllFolders", "setAutosavePreference", "mediaType", "enable", "setChargingPreferences", "uploadOnlyOnCharging", "setLowBatteryPreferences", "uploadOnLowBattery", "setMeteredNetworkPreferences", "enableOnMetered", "setPowerSaverPreferences", "uploadOnPowerSaverMode", "setPreferences", "enableAutoUpload", "(Lcom/amazon/photos/autosave/model/MediaType;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "onlyOnCharging", "enableOnLowBattery", "enableOnPowerSaverMode", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosavePreferencesImpl implements InternalPreferences {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "AutosavePreferences";
    private final AutosaveBucketDao autosaveBucketDao;
    private final AutosaveEventNotifier autosaveEventNotifier;
    private final AutosaveOperations autosaveOperations;
    private final DefaultAutosavePreferences defaultPreferences;
    private final DispatcherProvider dispatcherProvider;
    private boolean isDestroyed;
    private final Metrics metrics;
    private final AutosavePreferenceChangeListener preferenceChangeListener;
    private final PreferenceUploadQueueHelper queueHelper;
    private final SharedPreferences sharedPreferences;

    /* compiled from: AutosavePreferencesImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/autosave/internal/preferences/AutosavePreferencesImpl$Companion;", "", "()V", "TAG", "", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AutosavePreferencesImpl(@NotNull AutosaveBucketDao autosaveBucketDao, @NotNull DefaultAutosavePreferences defaultPreferences, @NotNull AutosavePreferenceChangeListener preferenceChangeListener, @NotNull Metrics metrics, @NotNull AutosaveOperations autosaveOperations, @NotNull DispatcherProvider dispatcherProvider, @NotNull PreferenceUploadQueueHelper queueHelper, @NotNull SharedPreferences sharedPreferences, @NotNull AutosaveEventNotifier autosaveEventNotifier) {
        Intrinsics.checkParameterIsNotNull(autosaveBucketDao, "autosaveBucketDao");
        Intrinsics.checkParameterIsNotNull(defaultPreferences, "defaultPreferences");
        Intrinsics.checkParameterIsNotNull(preferenceChangeListener, "preferenceChangeListener");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(autosaveOperations, "autosaveOperations");
        Intrinsics.checkParameterIsNotNull(dispatcherProvider, "dispatcherProvider");
        Intrinsics.checkParameterIsNotNull(queueHelper, "queueHelper");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(autosaveEventNotifier, "autosaveEventNotifier");
        this.autosaveBucketDao = autosaveBucketDao;
        this.defaultPreferences = defaultPreferences;
        this.preferenceChangeListener = preferenceChangeListener;
        this.metrics = metrics;
        this.autosaveOperations = autosaveOperations;
        this.dispatcherProvider = dispatcherProvider;
        this.queueHelper = queueHelper;
        this.sharedPreferences = sharedPreferences;
        this.autosaveEventNotifier = autosaveEventNotifier;
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this.preferenceChangeListener);
        initPreferenceUploadQueueConstraints();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getMetricEnableSuffix(boolean z) {
        return z ? "ENABLED" : "DISABLED";
    }

    private final MetricName getMetricName(final String str, final MediaType mediaType, final boolean z) {
        return new MetricName() { // from class: com.amazon.photos.autosave.internal.preferences.AutosavePreferencesImpl$getMetricName$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                String metricEnableSuffix;
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append('_');
                sb.append(mediaType.name());
                sb.append('_');
                metricEnableSuffix = AutosavePreferencesImpl.this.getMetricEnableSuffix(z);
                sb.append(metricEnableSuffix);
                return sb.toString();
            }
        };
    }

    @WorkerThread
    private final void initPreferenceUploadQueueConstraints() {
        MediaType[] values;
        for (MediaType mediaType : MediaType.values()) {
            String autosavePreferenceKey = AutosavePreferences.Companion.getAutosavePreferenceKey(mediaType);
            String meteredNetworkPreferenceKey = AutosavePreferences.Companion.getMeteredNetworkPreferenceKey(mediaType);
            this.queueHelper.handlePreference(autosavePreferenceKey, this.sharedPreferences.getBoolean(autosavePreferenceKey, this.defaultPreferences.isAutosaveEnabled(mediaType)));
            this.queueHelper.handlePreference(meteredNetworkPreferenceKey, this.sharedPreferences.getBoolean(meteredNetworkPreferenceKey, this.defaultPreferences.isAutosaveAllowedOnMetered(mediaType)));
        }
        this.queueHelper.handlePreference("charging_settings", this.sharedPreferences.getBoolean("charging_settings", this.defaultPreferences.isAllowedOnlyWhileCharging()));
        this.queueHelper.handlePreference("low_battery_settings", this.sharedPreferences.getBoolean("low_battery_settings", this.defaultPreferences.isAllowedOnLowBattery()));
        this.queueHelper.handlePreference("power_saver_settings", this.sharedPreferences.getBoolean("power_saver_settings", this.defaultPreferences.isAllowedOnPowerSaverMode()));
        this.queueHelper.handlePreference("add_family_settings", this.sharedPreferences.getBoolean("add_family_settings", this.defaultPreferences.isAddToFamilyEnabled()));
        this.queueHelper.handlePreference("all_folders_settings", this.sharedPreferences.getBoolean("all_folders_settings", this.defaultPreferences.isAllFoldersEnabled()));
    }

    private final void setPreferences(MediaType mediaType, Boolean bool, Boolean bool2) {
        boolean z;
        boolean booleanValue;
        boolean booleanValue2;
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        if (bool == null || isAutosaveEnabled(mediaType) == (booleanValue2 = bool.booleanValue())) {
            z = false;
        } else {
            edit.putBoolean(AutosavePreferences.Companion.getAutosavePreferenceKey(mediaType), booleanValue2);
            this.metrics.recordSimpleEvent(TAG, getMetricName(AutosaveMetricEvents.AUTOSAVE, mediaType, booleanValue2), new MetricRecordingType[0]);
            z = true;
        }
        if (bool2 != null && isAutosaveAllowedOnMetered(mediaType) != (booleanValue = bool2.booleanValue())) {
            edit.putBoolean(AutosavePreferences.Companion.getMeteredNetworkPreferenceKey(mediaType), booleanValue);
            this.metrics.recordSimpleEvent(TAG, getMetricName(AutosaveMetricEvents.AUTOSAVE_METERED_CONNECTION, mediaType, booleanValue), new MetricRecordingType[0]);
            z = true;
        }
        if (z) {
            edit.apply();
            if (bool == null) {
                return;
            }
            this.autosaveEventNotifier.onAutosaveStateChanged$AndroidPhotosAutosave_release(mediaType, bool.booleanValue());
        }
    }

    static /* synthetic */ void setPreferences$default(AutosavePreferencesImpl autosavePreferencesImpl, MediaType mediaType, Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = null;
        }
        if ((i & 4) != 0) {
            bool2 = null;
        }
        autosavePreferencesImpl.setPreferences(mediaType, bool, bool2);
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        this.isDestroyed = true;
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this.preferenceChangeListener);
        this.sharedPreferences.edit().clear().apply();
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public void enableAutosaveForFolder(@NotNull LocalFolder folder, boolean z) {
        Intrinsics.checkParameterIsNotNull(folder, "folder");
        if (z) {
            this.autosaveBucketDao.insertOrIgnoreItem(ModelExtensionsKt.toAutosaveBucket(folder));
            this.sharedPreferences.edit().putBoolean(PreferenceUploadQueueHelper.FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_PHOTOS, false).putBoolean(PreferenceUploadQueueHelper.FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_VIDEOS, false).apply();
            this.autosaveOperations.triggerDiscoveryScan$AndroidPhotosAutosave_release();
        } else {
            AutosaveBucket bucketByPath = this.autosaveBucketDao.getBucketByPath(folder.getPath());
            if (bucketByPath != null) {
                this.autosaveOperations.cancelQueuedUploads$AndroidPhotosAutosave_release(bucketByPath, true);
            }
            setAllFolderPreferences(false);
        }
        this.metrics.recordSimpleEvent(TAG, getMetricName(AutosaveMetricEvents.AUTOSAVE_FOLDER, z), new MetricRecordingType[0]);
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    @NotNull
    public List<AutosaveBucket> getAutoSavedFolders() {
        return this.autosaveBucketDao.getAllBuckets();
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public boolean isAddToFamilyEnabled() {
        return this.sharedPreferences.getBoolean("add_family_settings", this.defaultPreferences.isAddToFamilyEnabled());
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public boolean isAllFoldersEnabled() {
        return this.sharedPreferences.getBoolean("all_folders_settings", this.defaultPreferences.isAllFoldersEnabled());
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public boolean isAllowedOnLowBattery() {
        return this.sharedPreferences.getBoolean("low_battery_settings", this.defaultPreferences.isAllowedOnLowBattery());
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public boolean isAllowedOnPowerSaverMode() {
        return this.sharedPreferences.getBoolean("power_saver_settings", this.defaultPreferences.isAllowedOnPowerSaverMode());
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public boolean isAllowedOnlyWhileCharging() {
        return this.sharedPreferences.getBoolean("charging_settings", this.defaultPreferences.isAllowedOnlyWhileCharging());
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public boolean isAutosaveAllowedOnMetered(@NotNull MediaType itemType) {
        Intrinsics.checkParameterIsNotNull(itemType, "itemType");
        return this.sharedPreferences.getBoolean(AutosavePreferences.Companion.getMeteredNetworkPreferenceKey(itemType), this.defaultPreferences.isAutosaveAllowedOnMetered(itemType));
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public boolean isAutosaveEnabled(@NotNull MediaType itemType) {
        Intrinsics.checkParameterIsNotNull(itemType, "itemType");
        return this.sharedPreferences.getBoolean(AutosavePreferences.Companion.getAutosavePreferenceKey(itemType), this.defaultPreferences.isAutosaveEnabled(itemType));
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public boolean isAutosaveEnabledForFolder(@NotNull LocalFolder folder) {
        Intrinsics.checkParameterIsNotNull(folder, "folder");
        return this.autosaveBucketDao.getBucketByPath(folder.getPath()) != null;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public void setAddToFamilyPreferences(boolean z) {
        setPreferences$default(this, null, null, Boolean.valueOf(z), null, null, 27, null);
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public void setAllFolderPreferences(boolean z) {
        setPreferences$default(this, null, null, null, null, Boolean.valueOf(z), 15, null);
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public void setAutosavePreference(@NotNull MediaType mediaType, boolean z) {
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        setPreferences$default(this, mediaType, Boolean.valueOf(z), null, 4, null);
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public void setChargingPreferences(boolean z) {
        setPreferences$default(this, Boolean.valueOf(z), null, null, null, null, 30, null);
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public void setLowBatteryPreferences(boolean z) {
        setPreferences$default(this, null, Boolean.valueOf(z), null, null, null, 29, null);
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public void setMeteredNetworkPreferences(@NotNull MediaType mediaType, boolean z) {
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        setPreferences$default(this, mediaType, null, Boolean.valueOf(z), 2, null);
    }

    @Override // com.amazon.photos.autosave.AutosavePreferences
    public void setPowerSaverPreferences(boolean z) {
        setPreferences$default(this, null, null, null, Boolean.valueOf(z), null, 23, null);
    }

    private final MetricName getMetricName(final String str, final boolean z) {
        return new MetricName() { // from class: com.amazon.photos.autosave.internal.preferences.AutosavePreferencesImpl$getMetricName$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                String metricEnableSuffix;
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append('_');
                metricEnableSuffix = AutosavePreferencesImpl.this.getMetricEnableSuffix(z);
                sb.append(metricEnableSuffix);
                return sb.toString();
            }
        };
    }

    static /* synthetic */ void setPreferences$default(AutosavePreferencesImpl autosavePreferencesImpl, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = null;
        }
        if ((i & 2) != 0) {
            bool2 = null;
        }
        if ((i & 4) != 0) {
            bool3 = null;
        }
        if ((i & 8) != 0) {
            bool4 = null;
        }
        if ((i & 16) != 0) {
            bool5 = null;
        }
        autosavePreferencesImpl.setPreferences(bool, bool2, bool3, bool4, bool5);
    }

    private final void setPreferences(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5) {
        boolean z;
        boolean booleanValue;
        boolean booleanValue2;
        boolean booleanValue3;
        boolean booleanValue4;
        boolean booleanValue5;
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        if (bool == null || isAllowedOnlyWhileCharging() == (booleanValue5 = bool.booleanValue())) {
            z = false;
        } else {
            edit.putBoolean("charging_settings", booleanValue5);
            this.metrics.recordSimpleEvent(TAG, getMetricName(AutosaveMetricEvents.AUTOSAVE_CHARGING_ONLY, booleanValue5), new MetricRecordingType[0]);
            z = true;
        }
        if (bool2 != null && isAllowedOnLowBattery() != (booleanValue4 = bool2.booleanValue())) {
            edit.putBoolean("low_battery_settings", booleanValue4);
            this.metrics.recordSimpleEvent(TAG, getMetricName(AutosaveMetricEvents.AUTOSAVE_LOW_BATTERY, booleanValue4), new MetricRecordingType[0]);
            z = true;
        }
        if (bool4 != null && isAllowedOnPowerSaverMode() != (booleanValue3 = bool4.booleanValue())) {
            edit.putBoolean("power_saver_settings", booleanValue3);
            this.metrics.recordSimpleEvent(TAG, getMetricName(AutosaveMetricEvents.AUTOSAVE_POWER_SAVER_UPLOAD, booleanValue3), new MetricRecordingType[0]);
            z = true;
        }
        if (bool3 != null && isAddToFamilyEnabled() != (booleanValue2 = bool3.booleanValue())) {
            edit.putBoolean("add_family_settings", booleanValue2);
            this.metrics.recordSimpleEvent(TAG, getMetricName(AutosaveMetricEvents.AUTOSAVE_ADD_TO_FAMILY, booleanValue2), new MetricRecordingType[0]);
            z = true;
        }
        if (bool5 != null && isAllFoldersEnabled() != (booleanValue = bool5.booleanValue())) {
            edit.putBoolean("all_folders_settings", booleanValue);
            this.metrics.recordSimpleEvent(TAG, getMetricName(AutosaveMetricEvents.AUTOSAVE_ALL_FOLDERS, booleanValue), new MetricRecordingType[0]);
            z = true;
        }
        if (z) {
            edit.apply();
        }
    }
}
