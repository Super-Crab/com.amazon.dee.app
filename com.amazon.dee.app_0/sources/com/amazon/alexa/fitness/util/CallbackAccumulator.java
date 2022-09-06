package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import java.lang.Throwable;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Callback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B!\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ!\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00018\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0013R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/util/CallbackAccumulator;", ExifInterface.GPS_DIRECTION_TRUE, "U", "", "Lcom/amazon/alexa/fitness/util/Callback;", "callback", OperationalEventType.COUNTER, "", "(Lcom/amazon/alexa/fitness/util/Callback;I)V", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", "onError", "", "errorName", "", "error", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "onSuccess", "result", "(Ljava/lang/Object;)V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CallbackAccumulator<T, U extends Throwable> implements Callback<T, U> {
    private final Callback<T, U> callback;
    private int counter;
    private final ReentrantLock lock;

    public CallbackAccumulator(@NotNull Callback<T, U> callback, int i) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
        this.counter = i;
        this.lock = new ReentrantLock();
    }

    @Override // com.amazon.alexa.fitness.util.Callback
    public void onError(@Nullable String str, @Nullable U u) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.counter > 0) {
                this.counter = 0;
                this.callback.onError(str, u);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.util.Callback
    public void onSuccess(@Nullable T t) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.counter > 0) {
                this.counter--;
                if (this.counter == 0) {
                    this.callback.onSuccess(t);
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }
}
