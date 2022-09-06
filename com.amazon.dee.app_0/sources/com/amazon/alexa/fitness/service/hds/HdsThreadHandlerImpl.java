package com.amazon.alexa.fitness.service.hds;

import android.os.Handler;
import android.os.HandlerThread;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HdsThreadHandlerImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0016\u0010\u000e\u001a\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HdsThreadHandlerImpl;", "Lcom/amazon/alexa/fitness/service/hds/HdsThreadHandler;", "()V", "retryHandler", "Landroid/os/Handler;", "retryThread", "Landroid/os/HandlerThread;", "uploadHandler", "uploadThread", "postRetry", "", "retryFunc", "Lkotlin/Function0;", "", "postUpload", "uploadFunc", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HdsThreadHandlerImpl implements HdsThreadHandler {
    private final Handler retryHandler;
    private final Handler uploadHandler;
    private final HandlerThread retryThread = new HandlerThread("HdsRetryThread");
    private final HandlerThread uploadThread = new HandlerThread("HdsUploadThread");

    @Inject
    public HdsThreadHandlerImpl() {
        this.retryThread.start();
        this.retryHandler = new Handler(this.retryThread.getLooper());
        this.uploadThread.start();
        this.uploadHandler = new Handler(this.uploadThread.getLooper());
    }

    @Override // com.amazon.alexa.fitness.service.hds.HdsThreadHandler
    public boolean postRetry(@NotNull final Function0<Unit> retryFunc) {
        Intrinsics.checkParameterIsNotNull(retryFunc, "retryFunc");
        return this.retryHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.service.hds.HdsThreadHandlerImpl$postRetry$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.mo12560invoke();
            }
        });
    }

    @Override // com.amazon.alexa.fitness.service.hds.HdsThreadHandler
    public boolean postUpload(@NotNull final Function0<Unit> uploadFunc) {
        Intrinsics.checkParameterIsNotNull(uploadFunc, "uploadFunc");
        return this.uploadHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.service.hds.HdsThreadHandlerImpl$postUpload$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.mo12560invoke();
            }
        });
    }
}
