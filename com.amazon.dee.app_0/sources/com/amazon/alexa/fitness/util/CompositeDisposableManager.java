package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.dee.app.metrics.MetricsConstants;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CompositeDisposableManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u0006H\u0016¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/util/CompositeDisposableManager;", "Lcom/amazon/alexa/fitness/util/DisposableManager;", "()V", "disposables", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", BulkOperationType.add, ExifInterface.GPS_DIRECTION_TRUE, "Lio/reactivex/rxjava3/disposables/Disposable;", "disposable", "(Lio/reactivex/rxjava3/disposables/Disposable;)Lio/reactivex/rxjava3/disposables/Disposable;", MetricsConstants.Method.CACHE_CLEAR, "", "size", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CompositeDisposableManager implements DisposableManager {
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override // com.amazon.alexa.fitness.util.DisposableManager
    @NotNull
    public <T extends Disposable> T add(@NotNull T disposable) {
        Intrinsics.checkParameterIsNotNull(disposable, "disposable");
        this.disposables.add(disposable);
        return disposable;
    }

    @Override // com.amazon.alexa.fitness.util.DisposableManager
    public void clear() {
        this.disposables.clear();
    }

    @Override // com.amazon.alexa.fitness.util.DisposableManager
    public int size() {
        return this.disposables.size();
    }
}
