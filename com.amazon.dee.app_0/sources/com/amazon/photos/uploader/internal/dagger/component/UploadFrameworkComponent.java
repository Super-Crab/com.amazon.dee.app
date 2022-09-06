package com.amazon.photos.uploader.internal.dagger.component;

import androidx.annotation.VisibleForTesting;
import com.amazon.photos.uploader.Feature;
import com.amazon.photos.uploader.UploadManager;
import com.amazon.photos.uploader.UploaderDaos;
import com.amazon.photos.uploader.UploaderOperations;
import com.amazon.photos.uploader.blockers.BlockerEvaluatorProvider;
import com.amazon.photos.uploader.internal.dagger.PerAccount;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule;
import com.amazon.photos.uploader.internal.dagger.module.DatabaseModule;
import com.amazon.photos.uploader.internal.workers.BlockerEvaluatorWorker;
import com.amazon.photos.uploader.internal.workers.ReevaluateWorker;
import com.amazon.photos.uploader.internal.workers.SchedulerWorker;
import com.amazon.photos.uploader.internal.workers.UploadWorker;
import dagger.Component;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadFrameworkComponent.kt */
@Component(modules = {ConfigurationModule.class, DatabaseModule.class})
@PerAccount
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H&J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H&J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H&J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH&J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH&J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH&R\"\u0010\u0002\u001a\u00020\u00038&@&X§\u000e¢\u0006\u0012\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcom/amazon/photos/uploader/internal/dagger/component/UploadFrameworkComponent;", "", "blockerEvaluatorProvider", "Lcom/amazon/photos/uploader/blockers/BlockerEvaluatorProvider;", "blockerEvaluatorProvider$annotations", "()V", "getBlockerEvaluatorProvider", "()Lcom/amazon/photos/uploader/blockers/BlockerEvaluatorProvider;", "setBlockerEvaluatorProvider", "(Lcom/amazon/photos/uploader/blockers/BlockerEvaluatorProvider;)V", "supportedFeatures", "", "Lcom/amazon/photos/uploader/Feature;", "getSupportedFeatures", "()Ljava/util/Set;", "inject", "", "uploadManager", "Lcom/amazon/photos/uploader/UploadManager;", "uploaderDaos", "Lcom/amazon/photos/uploader/UploaderDaos;", "uploaderOperations", "Lcom/amazon/photos/uploader/UploaderOperations;", "blockerEvaluatorWorker", "Lcom/amazon/photos/uploader/internal/workers/BlockerEvaluatorWorker;", "reevaluateWorker", "Lcom/amazon/photos/uploader/internal/workers/ReevaluateWorker;", "schedulerWorker", "Lcom/amazon/photos/uploader/internal/workers/SchedulerWorker;", "uploadWorker", "Lcom/amazon/photos/uploader/internal/workers/UploadWorker;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface UploadFrameworkComponent {

    /* compiled from: UploadFrameworkComponent.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        @VisibleForTesting
        public static /* synthetic */ void blockerEvaluatorProvider$annotations() {
        }
    }

    @NotNull
    BlockerEvaluatorProvider getBlockerEvaluatorProvider();

    @NotNull
    Set<Feature> getSupportedFeatures();

    void inject(@Nullable UploadManager uploadManager);

    void inject(@Nullable UploaderDaos uploaderDaos);

    void inject(@Nullable UploaderOperations uploaderOperations);

    void inject(@Nullable BlockerEvaluatorWorker blockerEvaluatorWorker);

    void inject(@Nullable ReevaluateWorker reevaluateWorker);

    void inject(@Nullable SchedulerWorker schedulerWorker);

    void inject(@Nullable UploadWorker uploadWorker);

    void setBlockerEvaluatorProvider(@NotNull BlockerEvaluatorProvider blockerEvaluatorProvider);
}
