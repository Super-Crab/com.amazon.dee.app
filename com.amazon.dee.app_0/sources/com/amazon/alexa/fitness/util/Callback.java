package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import java.lang.Throwable;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: Callback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004J%\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00018\u0001H&¢\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/util/Callback;", ExifInterface.GPS_DIRECTION_TRUE, "U", "", "", "onError", "", "errorName", "", "error", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "onSuccess", "result", "(Ljava/lang/Object;)V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface Callback<T, U extends Throwable> {

    /* compiled from: Callback.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void onError$default(Callback callback, String str, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    str = null;
                }
                if ((i & 2) != 0) {
                    th = null;
                }
                callback.onError(str, th);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onError");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void onSuccess$default(Callback callback, Object obj, int i, Object obj2) {
            if (obj2 == null) {
                if ((i & 1) != 0) {
                    obj = null;
                }
                callback.onSuccess(obj);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onSuccess");
        }
    }

    void onError(@Nullable String str, @Nullable U u);

    void onSuccess(@Nullable T t);
}
