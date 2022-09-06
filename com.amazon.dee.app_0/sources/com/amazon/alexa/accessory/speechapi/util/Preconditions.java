package com.amazon.alexa.accessory.speechapi.util;

import android.os.Looper;
import android.util.Log;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Preconditions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0018\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/util/Preconditions;", "", "()V", "checkMainThread", "", "checkNotNull", "object", "variableName", "", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Preconditions {
    public static final Preconditions INSTANCE = new Preconditions();

    private Preconditions() {
    }

    public final void checkMainThread() {
        Thread currentThread = Thread.currentThread();
        Looper mainLooper = Looper.getMainLooper();
        Intrinsics.checkExpressionValueIsNotNull(mainLooper, "Looper.getMainLooper()");
        if (currentThread == mainLooper.getThread()) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Current thread must be a main thread");
        Log.e("Throwing ", illegalStateException.toString());
        throw illegalStateException;
    }

    public final void checkNotNull(@Nullable Object obj, @NotNull String variableName) {
        Intrinsics.checkParameterIsNotNull(variableName, "variableName");
        if (obj != null) {
            return;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        Object[] objArr = {variableName};
        String format = String.format(locale, "%s must not be null", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        NullPointerException nullPointerException = new NullPointerException(format);
        Log.e("Throwing ", nullPointerException.toString());
        throw nullPointerException;
    }
}
