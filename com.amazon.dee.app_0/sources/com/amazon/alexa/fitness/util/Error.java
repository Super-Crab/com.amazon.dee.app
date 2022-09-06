package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ResultOrError.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u000f\u0010\n\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u000bJ\r\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/util/Error;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/fitness/util/ResultOrError;", "cause", "", "(Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "isError", "", "resultOrNull", "()Ljava/lang/Object;", "resultOrThrow", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Error<T> extends ResultOrError<T> {
    @NotNull
    private final Throwable cause;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Error(@NotNull Throwable cause) {
        super(null);
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        this.cause = cause;
    }

    @NotNull
    public final Throwable getCause() {
        return this.cause;
    }

    @Override // com.amazon.alexa.fitness.util.ResultOrError
    public boolean isError() {
        return true;
    }

    @Override // com.amazon.alexa.fitness.util.ResultOrError
    @Nullable
    public T resultOrNull() {
        return null;
    }

    @Override // com.amazon.alexa.fitness.util.ResultOrError
    public T resultOrThrow() throws Throwable {
        throw this.cause;
    }
}
