package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
/* compiled from: ResultOrError.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\u000f\u0010\u0006\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/util/ResultOrError;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "isError", "", "resultOrNull", "()Ljava/lang/Object;", "resultOrThrow", "Lcom/amazon/alexa/fitness/util/Result;", "Lcom/amazon/alexa/fitness/util/Error;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class ResultOrError<T> {
    private ResultOrError() {
    }

    public abstract boolean isError();

    @Nullable
    public abstract T resultOrNull();

    public abstract T resultOrThrow();

    public /* synthetic */ ResultOrError(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
