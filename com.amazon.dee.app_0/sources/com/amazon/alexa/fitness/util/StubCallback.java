package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import java.lang.Throwable;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: Callback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J!\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00018\u0001H\u0016¢\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/util/StubCallback;", ExifInterface.GPS_DIRECTION_TRUE, "U", "", "Lcom/amazon/alexa/fitness/util/Callback;", "()V", "onError", "", "errorName", "", "error", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "onSuccess", "result", "(Ljava/lang/Object;)V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class StubCallback<T, U extends Throwable> implements Callback<T, U> {
    @Override // com.amazon.alexa.fitness.util.Callback
    public void onError(@Nullable String str, @Nullable U u) {
    }

    @Override // com.amazon.alexa.fitness.util.Callback
    public void onSuccess(@Nullable T t) {
    }
}
