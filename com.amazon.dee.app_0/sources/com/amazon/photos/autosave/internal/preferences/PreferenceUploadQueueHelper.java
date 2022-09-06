package com.amazon.photos.autosave.internal.preferences;

import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.photos.autosave.AutosavePreferences;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.upload.AutosaveUploadConfigurationProviderKt;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.autosave.model.ModelExtensionsKt;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.model.LocalFolder;
import com.amazon.photos.uploader.QueueConstraint;
import com.amazon.photos.uploader.UploadManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import org.jetbrains.annotations.NotNull;
/* compiled from: PreferenceUploadQueueHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u001d\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\b\u001aJ`\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\u001e\u0010\u001f\u001a\u001a\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100 2\u001e\u0010\"\u001a\u001a\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100 H\u0002JX\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\u001e\u0010\u001f\u001a\u001a\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100 2\u001e\u0010\"\u001a\u001a\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100 H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/photos/autosave/internal/preferences/PreferenceUploadQueueHelper;", "", "uploadManager", "Lcom/amazon/photos/uploader/UploadManager;", "autosaveOperations", "Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "autosaveBucketDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;", "transactionRunner", "Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Lcom/amazon/photos/uploader/UploadManager;Lcom/amazon/photos/autosave/internal/AutosaveOperations;Lcom/amazon/photos/discovery/Discovery;Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;Landroid/content/SharedPreferences;)V", "cancelAndRescheduleQueue", "", "preferenceVal", "", "handlePreference", "preferenceKey", "", "updateAutosaveEnabled", "isAutoSaveEnabled", "itemType", "Lcom/amazon/photos/autosave/model/MediaType;", "updateAutosaveEnabled$AndroidPhotosAutosave_release", "updateQueueConstraints", "mediaType", "constraint", "Lcom/amazon/photos/uploader/QueueConstraint;", "onEnabled", "Lkotlin/reflect/KFunction3;", "Lcom/amazon/photos/uploader/QueueManager;", "onDisabled", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PreferenceUploadQueueHelper {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_PHOTOS = "FullScanRunAfterFirstEnableAutosavePhotos";
    @NotNull
    public static final String FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_VIDEOS = "FullScanRunAfterFirstEnableAutosaveVideos";
    private final AutosaveBucketDao autosaveBucketDao;
    private final AutosaveOperations autosaveOperations;
    private final Discovery discovery;
    private final SharedPreferences sharedPreferences;
    private final AutosaveTransactionRunner transactionRunner;
    private final UploadManager uploadManager;

    /* compiled from: PreferenceUploadQueueHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/autosave/internal/preferences/PreferenceUploadQueueHelper$Companion;", "", "()V", "FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_PHOTOS", "", "FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_VIDEOS", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MediaType.values().length];

        static {
            $EnumSwitchMapping$0[MediaType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$0[MediaType.VIDEO.ordinal()] = 2;
        }
    }

    public PreferenceUploadQueueHelper(@NotNull UploadManager uploadManager, @NotNull AutosaveOperations autosaveOperations, @NotNull Discovery discovery, @NotNull AutosaveBucketDao autosaveBucketDao, @NotNull AutosaveTransactionRunner transactionRunner, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(uploadManager, "uploadManager");
        Intrinsics.checkParameterIsNotNull(autosaveOperations, "autosaveOperations");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(autosaveBucketDao, "autosaveBucketDao");
        Intrinsics.checkParameterIsNotNull(transactionRunner, "transactionRunner");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        this.uploadManager = uploadManager;
        this.autosaveOperations = autosaveOperations;
        this.discovery = discovery;
        this.autosaveBucketDao = autosaveBucketDao;
        this.transactionRunner = transactionRunner;
        this.sharedPreferences = sharedPreferences;
    }

    private final void cancelAndRescheduleQueue(boolean z) {
        this.autosaveOperations.cancelAndReschedule$AndroidPhotosAutosave_release(z);
    }

    private final void updateQueueConstraints(boolean z, QueueConstraint queueConstraint, KFunction<Unit> kFunction, KFunction<Unit> kFunction2) {
        for (MediaType mediaType : MediaType.values()) {
            updateQueueConstraints(mediaType, z, queueConstraint, kFunction, kFunction2);
        }
    }

    @WorkerThread
    public final void handlePreference(@NotNull String preferenceKey, boolean z) {
        Intrinsics.checkParameterIsNotNull(preferenceKey, "preferenceKey");
        if (Intrinsics.areEqual(preferenceKey, "charging_settings")) {
            updateQueueConstraints(z, QueueConstraint.RequiresChargingConstraint.INSTANCE, PreferenceUploadQueueHelper$handlePreference$1.INSTANCE, PreferenceUploadQueueHelper$handlePreference$2.INSTANCE);
        } else if (Intrinsics.areEqual(preferenceKey, "low_battery_settings")) {
            updateQueueConstraints(z, QueueConstraint.BlockInLowBattery.INSTANCE, PreferenceUploadQueueHelper$handlePreference$3.INSTANCE, PreferenceUploadQueueHelper$handlePreference$4.INSTANCE);
        } else if (Intrinsics.areEqual(preferenceKey, AutosavePreferences.Companion.getMeteredNetworkPreferenceKey(MediaType.PHOTO))) {
            updateQueueConstraints(MediaType.PHOTO, z, QueueConstraint.RequiresUnmeteredConnection.INSTANCE, PreferenceUploadQueueHelper$handlePreference$5.INSTANCE, PreferenceUploadQueueHelper$handlePreference$6.INSTANCE);
        } else if (Intrinsics.areEqual(preferenceKey, AutosavePreferences.Companion.getMeteredNetworkPreferenceKey(MediaType.VIDEO))) {
            updateQueueConstraints(MediaType.VIDEO, z, QueueConstraint.RequiresUnmeteredConnection.INSTANCE, PreferenceUploadQueueHelper$handlePreference$7.INSTANCE, PreferenceUploadQueueHelper$handlePreference$8.INSTANCE);
        } else if (Intrinsics.areEqual(preferenceKey, "power_saver_settings")) {
            updateQueueConstraints(z, QueueConstraint.BlockInPowerSavingMode.INSTANCE, PreferenceUploadQueueHelper$handlePreference$9.INSTANCE, PreferenceUploadQueueHelper$handlePreference$10.INSTANCE);
        } else if (Intrinsics.areEqual(preferenceKey, "add_family_settings")) {
            cancelAndRescheduleQueue(z);
        } else if (Intrinsics.areEqual(preferenceKey, AutosavePreferences.Companion.getAutosavePreferenceKey(MediaType.PHOTO))) {
            updateAutosaveEnabled$AndroidPhotosAutosave_release(z, MediaType.PHOTO);
        } else if (Intrinsics.areEqual(preferenceKey, AutosavePreferences.Companion.getAutosavePreferenceKey(MediaType.VIDEO))) {
            updateAutosaveEnabled$AndroidPhotosAutosave_release(z, MediaType.VIDEO);
        } else if (!Intrinsics.areEqual(preferenceKey, "all_folders_settings") || !z) {
        } else {
            this.transactionRunner.runInTransaction$AndroidPhotosAutosave_release(new Runnable() { // from class: com.amazon.photos.autosave.internal.preferences.PreferenceUploadQueueHelper$handlePreference$11
                @Override // java.lang.Runnable
                public final void run() {
                    Discovery discovery;
                    int collectionSizeOrDefault;
                    AutosaveBucketDao autosaveBucketDao;
                    discovery = PreferenceUploadQueueHelper.this.discovery;
                    List<LocalFolder> allFolders = discovery.getDaos().getLocalFolderDao().getAllFolders();
                    collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(allFolders, 10);
                    ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                    for (LocalFolder localFolder : allFolders) {
                        autosaveBucketDao = PreferenceUploadQueueHelper.this.autosaveBucketDao;
                        arrayList.add(Long.valueOf(autosaveBucketDao.insertOrIgnoreItem(ModelExtensionsKt.toAutosaveBucket(localFolder))));
                    }
                }
            });
            this.autosaveOperations.triggerDiscoveryScan$AndroidPhotosAutosave_release();
        }
    }

    @VisibleForTesting
    public final void updateAutosaveEnabled$AndroidPhotosAutosave_release(boolean z, @NotNull MediaType itemType) {
        String str;
        Intrinsics.checkParameterIsNotNull(itemType, "itemType");
        if (z) {
            SharedPreferences.Editor edit = this.sharedPreferences.edit();
            int i = WhenMappings.$EnumSwitchMapping$0[itemType.ordinal()];
            if (i == 1) {
                str = FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_PHOTOS;
            } else if (i != 2) {
                throw new NoWhenBranchMatchedException();
            } else {
                str = FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_VIDEOS;
            }
            edit.putBoolean(str, false).apply();
            this.autosaveOperations.triggerDiscoveryScan$AndroidPhotosAutosave_release();
            return;
        }
        this.autosaveOperations.cancelQueuedUploads$AndroidPhotosAutosave_release(itemType);
    }

    private final void updateQueueConstraints(MediaType mediaType, boolean z, QueueConstraint queueConstraint, KFunction<Unit> kFunction, KFunction<Unit> kFunction2) {
        if (z) {
            ((Function3) kFunction).invoke(this.uploadManager.getQueueManager(), AutosaveUploadConfigurationProviderKt.getQueue(mediaType), queueConstraint);
        } else {
            ((Function3) kFunction2).invoke(this.uploadManager.getQueueManager(), AutosaveUploadConfigurationProviderKt.getQueue(mediaType), queueConstraint);
        }
    }
}
