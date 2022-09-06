package com.amazon.photos.autosave.internal.observers;

import android.content.SharedPreferences;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.metrics.AutosaveLatencyRecorder;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.autosave.internal.preferences.PreferenceUploadQueueHelper;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.autosave.model.ModelExtensionsKt;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.model.FolderType;
import com.amazon.photos.discovery.model.LocalFolder;
import com.amazon.photos.discovery.observers.LocalContentChangeObserver;
import com.amazon.photos.discovery.observers.LocalContentType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveContentChangeObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\u0010\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u0014H\u0016J\u0016\u0010\"\u001a\u00020\u001a2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\u001e\u0010#\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/photos/autosave/internal/observers/AutosaveContentChangeObserver;", "Lcom/amazon/photos/discovery/observers/LocalContentChangeObserver;", "autosaveOperations", "Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "autosavePreferences", "Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "autosaveBucketDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;", "transactionRunner", "Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;", "sharedPreferences", "Landroid/content/SharedPreferences;", "autosaveLatencyRecorder", "Lcom/amazon/photos/autosave/internal/metrics/AutosaveLatencyRecorder;", "(Lcom/amazon/photos/autosave/internal/AutosaveOperations;Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;Lcom/amazon/photos/discovery/Discovery;Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;Landroid/content/SharedPreferences;Lcom/amazon/photos/autosave/internal/metrics/AutosaveLatencyRecorder;)V", JoinPoint.SYNCHRONIZATION_LOCK, "", "isFirstScan", "", "mediaType", "Lcom/amazon/photos/autosave/model/MediaType;", "flag", "", "onContentAdded", "", "contentType", "Lcom/amazon/photos/discovery/observers/LocalContentType;", ContactsModuleConstants.CONTACT_IDS, "", "", "onContentDedupeComplete", "wasContentDeduped", "onContentDeduped", "onContentDeleted", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveContentChangeObserver implements LocalContentChangeObserver {
    private final AutosaveBucketDao autosaveBucketDao;
    private final AutosaveLatencyRecorder autosaveLatencyRecorder;
    private final AutosaveOperations autosaveOperations;
    private final InternalPreferences autosavePreferences;
    private final Discovery discovery;
    private final Object lock;
    private final SharedPreferences sharedPreferences;
    private final AutosaveTransactionRunner transactionRunner;

    public AutosaveContentChangeObserver(@NotNull AutosaveOperations autosaveOperations, @NotNull InternalPreferences autosavePreferences, @NotNull Discovery discovery, @NotNull AutosaveBucketDao autosaveBucketDao, @NotNull AutosaveTransactionRunner transactionRunner, @NotNull SharedPreferences sharedPreferences, @NotNull AutosaveLatencyRecorder autosaveLatencyRecorder) {
        Intrinsics.checkParameterIsNotNull(autosaveOperations, "autosaveOperations");
        Intrinsics.checkParameterIsNotNull(autosavePreferences, "autosavePreferences");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(autosaveBucketDao, "autosaveBucketDao");
        Intrinsics.checkParameterIsNotNull(transactionRunner, "transactionRunner");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(autosaveLatencyRecorder, "autosaveLatencyRecorder");
        this.autosaveOperations = autosaveOperations;
        this.autosavePreferences = autosavePreferences;
        this.discovery = discovery;
        this.autosaveBucketDao = autosaveBucketDao;
        this.transactionRunner = transactionRunner;
        this.sharedPreferences = sharedPreferences;
        this.autosaveLatencyRecorder = autosaveLatencyRecorder;
        this.lock = new Object();
    }

    private final boolean isFirstScan(MediaType mediaType, String str) {
        return this.autosavePreferences.isAutosaveEnabled(mediaType) && !this.sharedPreferences.getBoolean(str, false);
    }

    @Override // com.amazon.photos.discovery.observers.LocalContentChangeObserver
    public void onContentAdded(@NotNull LocalContentType contentType, @NotNull final List<Long> ids) {
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        if (contentType == LocalContentType.FOLDER) {
            this.transactionRunner.runInTransaction$AndroidPhotosAutosave_release(new Runnable() { // from class: com.amazon.photos.autosave.internal.observers.AutosaveContentChangeObserver$onContentAdded$1
                @Override // java.lang.Runnable
                public final void run() {
                    Discovery discovery;
                    InternalPreferences internalPreferences;
                    AutosaveBucketDao autosaveBucketDao;
                    discovery = AutosaveContentChangeObserver.this.discovery;
                    for (LocalFolder localFolder : discovery.getDaos().getLocalFolderDao().getFoldersById(ids)) {
                        internalPreferences = AutosaveContentChangeObserver.this.autosavePreferences;
                        if (internalPreferences.isAllFoldersEnabled() || localFolder.getFolderType() == FolderType.CAMERA) {
                            autosaveBucketDao = AutosaveContentChangeObserver.this.autosaveBucketDao;
                            autosaveBucketDao.insertOrIgnoreItem(ModelExtensionsKt.toAutosaveBucket(localFolder));
                        }
                    }
                }
            });
        }
    }

    @Override // com.amazon.photos.discovery.observers.LocalContentChangeObserver
    public void onContentDedupeComplete(boolean z) {
        List<Long> emptyList;
        synchronized (this.lock) {
            if (isFirstScan(MediaType.PHOTO, PreferenceUploadQueueHelper.FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_PHOTOS) || isFirstScan(MediaType.VIDEO, PreferenceUploadQueueHelper.FULL_SCAN_RUN_AFTER_FIRST_ENABLE_AUTOSAVE_VIDEOS)) {
                AutosaveOperations autosaveOperations = this.autosaveOperations;
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                autosaveOperations.triggerAutosave$AndroidPhotosAutosave_release(emptyList, true);
            }
            this.autosaveLatencyRecorder.onContentDeduped$AndroidPhotosAutosave_release();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.photos.discovery.observers.LocalContentChangeObserver
    public void onContentDeduped(@NotNull List<Long> ids) {
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        if (!ids.isEmpty()) {
            AutosaveOperations.triggerAutosave$AndroidPhotosAutosave_release$default(this.autosaveOperations, ids, false, 2, null);
            this.autosaveLatencyRecorder.onContentDeduped$AndroidPhotosAutosave_release();
        }
    }

    @Override // com.amazon.photos.discovery.observers.LocalContentChangeObserver
    public void onContentDeleted(@NotNull LocalContentType contentType, @NotNull List<Long> ids) {
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        if (contentType != LocalContentType.LOCAL_ITEM || !(!ids.isEmpty())) {
            return;
        }
        this.autosaveOperations.cancelUploadsById$AndroidPhotosAutosave_release(ids);
    }
}
