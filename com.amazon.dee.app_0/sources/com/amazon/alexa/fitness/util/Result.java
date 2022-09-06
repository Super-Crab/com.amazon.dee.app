package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: ResultOrError.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\u000f\u0010\n\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0006J\r\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/fitness/util/Result;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/fitness/util/ResultOrError;", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "isError", "", "resultOrNull", "resultOrThrow", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Result<T> extends ResultOrError<T> {
    private final T value;

    public Result(T t) {
        super(null);
        this.value = t;
    }

    public final T getValue() {
        return this.value;
    }

    @Override // com.amazon.alexa.fitness.util.ResultOrError
    public boolean isError() {
        return false;
    }

    @Override // com.amazon.alexa.fitness.util.ResultOrError
    @Nullable
    public T resultOrNull() {
        return this.value;
    }

    @Override // com.amazon.alexa.fitness.util.ResultOrError
    public T resultOrThrow() {
        return this.value;
    }
}
