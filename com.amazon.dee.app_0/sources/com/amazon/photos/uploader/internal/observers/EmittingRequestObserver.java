package com.amazon.photos.uploader.internal.observers;

import com.amazon.photos.uploader.ResultMetadata;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.observables.AbandonedRequestInfo;
import com.amazon.photos.uploader.observables.UploadRequestObserver;
import io.reactivex.rxjava3.core.Emitter;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: EmittingRequestObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u0016\u0010\u0013\u001a\u00020\u00112\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0016J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\"\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J \u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0016J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u0018\u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%H\u0016J\u001a\u0010&\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005Rb\u0010\u0003\u001aV\u0012$\u0012\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006 \b*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u00050\u0005 \b**\u0012$\u0012\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006 \b*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u00050\u0005\u0018\u00010\t0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/photos/uploader/internal/observers/EmittingRequestObserver;", "Lcom/amazon/photos/uploader/observables/UploadRequestObserver;", "()V", "emitters", "", "Lio/reactivex/rxjava3/core/Emitter;", "", "Lcom/amazon/photos/uploader/UploadRequest;", "kotlin.jvm.PlatformType", "", "requestMap", "Ljava/util/concurrent/ConcurrentHashMap;", "", "addEmitter", "", "emitter", "onRequestAdded", "", "uploadRequest", "onRequestsAbandoned", "abandonedRequestInfoList", "", "Lcom/amazon/photos/uploader/observables/AbandonedRequestInfo;", "onUploadBlocked", "blocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "onUploadFailed", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "onUploadProgressUpdate", "currentProgress", "maxProgress", "onUploadStarted", "onUploadSucceeded", "resultMetadata", "Lcom/amazon/photos/uploader/ResultMetadata;", "removeEmitter", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class EmittingRequestObserver implements UploadRequestObserver {
    private final ConcurrentHashMap<Long, UploadRequest> requestMap = new ConcurrentHashMap<>();
    private final Set<Emitter<Collection<UploadRequest>>> emitters = Collections.newSetFromMap(new ConcurrentHashMap());

    public final boolean addEmitter(@NotNull Emitter<Collection<UploadRequest>> emitter) {
        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
        return this.emitters.add(emitter);
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestAdded(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestsAbandoned(@NotNull List<AbandonedRequestInfo> abandonedRequestInfoList) {
        Intrinsics.checkParameterIsNotNull(abandonedRequestInfoList, "abandonedRequestInfoList");
        for (AbandonedRequestInfo abandonedRequestInfo : abandonedRequestInfoList) {
            this.requestMap.remove(Long.valueOf(abandonedRequestInfo.getUploadRequest().getId()));
        }
        Set<Emitter<Collection<UploadRequest>>> emitters = this.emitters;
        Intrinsics.checkExpressionValueIsNotNull(emitters, "emitters");
        Iterator<T> it2 = emitters.iterator();
        while (it2.hasNext()) {
            ((Emitter) it2.next()).onNext(this.requestMap.values());
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Set<? extends Blocker> blockers) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blockers, "blockers");
        UploadRequestObserver.DefaultImpls.onUploadBlocked(this, uploadRequest, blockers);
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadFailed(@NotNull UploadRequest uploadRequest, @Nullable Throwable th, @NotNull UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        this.requestMap.remove(Long.valueOf(uploadRequest.getId()));
        Set<Emitter<Collection<UploadRequest>>> emitters = this.emitters;
        Intrinsics.checkExpressionValueIsNotNull(emitters, "emitters");
        Iterator<T> it2 = emitters.iterator();
        while (it2.hasNext()) {
            ((Emitter) it2.next()).onNext(this.requestMap.values());
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadProgressUpdate(@NotNull UploadRequest uploadRequest, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        this.requestMap.put(Long.valueOf(uploadRequest.getId()), uploadRequest);
        Set<Emitter<Collection<UploadRequest>>> emitters = this.emitters;
        Intrinsics.checkExpressionValueIsNotNull(emitters, "emitters");
        Iterator<T> it2 = emitters.iterator();
        while (it2.hasNext()) {
            ((Emitter) it2.next()).onNext(this.requestMap.values());
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadStarted(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        this.requestMap.put(Long.valueOf(uploadRequest.getId()), uploadRequest);
        Set<Emitter<Collection<UploadRequest>>> emitters = this.emitters;
        Intrinsics.checkExpressionValueIsNotNull(emitters, "emitters");
        Iterator<T> it2 = emitters.iterator();
        while (it2.hasNext()) {
            ((Emitter) it2.next()).onNext(this.requestMap.values());
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadSucceeded(@NotNull UploadRequest uploadRequest, @NotNull ResultMetadata resultMetadata) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(resultMetadata, "resultMetadata");
        this.requestMap.remove(Long.valueOf(uploadRequest.getId()));
        Set<Emitter<Collection<UploadRequest>>> emitters = this.emitters;
        Intrinsics.checkExpressionValueIsNotNull(emitters, "emitters");
        Iterator<T> it2 = emitters.iterator();
        while (it2.hasNext()) {
            ((Emitter) it2.next()).onNext(this.requestMap.values());
        }
    }

    public final boolean removeEmitter(@NotNull Emitter<Collection<UploadRequest>> emitter) {
        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
        return this.emitters.remove(emitter);
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Blocker blocker) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blocker, "blocker");
        this.requestMap.remove(Long.valueOf(uploadRequest.getId()));
        Set<Emitter<Collection<UploadRequest>>> emitters = this.emitters;
        Intrinsics.checkExpressionValueIsNotNull(emitters, "emitters");
        Iterator<T> it2 = emitters.iterator();
        while (it2.hasNext()) {
            ((Emitter) it2.next()).onNext(this.requestMap.values());
        }
    }
}
