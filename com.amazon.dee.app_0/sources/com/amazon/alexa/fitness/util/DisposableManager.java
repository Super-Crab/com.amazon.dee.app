package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.dee.app.metrics.MetricsConstants;
import io.reactivex.rxjava3.disposables.Disposable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: DisposableManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0003H&¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/util/DisposableManager;", "", BulkOperationType.add, ExifInterface.GPS_DIRECTION_TRUE, "Lio/reactivex/rxjava3/disposables/Disposable;", "disposable", "(Lio/reactivex/rxjava3/disposables/Disposable;)Lio/reactivex/rxjava3/disposables/Disposable;", MetricsConstants.Method.CACHE_CLEAR, "", "size", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface DisposableManager {
    @NotNull
    <T extends Disposable> T add(@NotNull T t);

    void clear();

    int size();
}
