package com.amazon.alexa.fitness.message;

import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.fitness.model.event.Error;
import com.amazon.alexa.fitness.model.event.Operation;
import com.amazon.alexa.fitness.util.Callback;
import com.amazon.alexa.fitness.util.StubCallback;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionEventReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H&J \u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J&\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H&J&\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H&J&\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H&¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/message/FitnessSessionEventReceiver;", "", "onFitnessSessionEnded", "", "alexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "callback", "Lcom/amazon/alexa/fitness/util/Callback;", "", "onFitnessSessionError", "error", "Lcom/amazon/alexa/fitness/model/event/Error;", "operation", "Lcom/amazon/alexa/fitness/model/event/Operation;", "onFitnessSessionPaused", "onFitnessSessionResumed", "onFitnessSessionStarted", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface FitnessSessionEventReceiver {

    /* compiled from: FitnessSessionEventReceiver.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void onFitnessSessionEnded$default(FitnessSessionEventReceiver fitnessSessionEventReceiver, AlexaEvent alexaEvent, Callback callback, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    callback = new StubCallback();
                }
                fitnessSessionEventReceiver.onFitnessSessionEnded(alexaEvent, callback);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onFitnessSessionEnded");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void onFitnessSessionPaused$default(FitnessSessionEventReceiver fitnessSessionEventReceiver, AlexaEvent alexaEvent, Callback callback, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    callback = new StubCallback();
                }
                fitnessSessionEventReceiver.onFitnessSessionPaused(alexaEvent, callback);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onFitnessSessionPaused");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void onFitnessSessionResumed$default(FitnessSessionEventReceiver fitnessSessionEventReceiver, AlexaEvent alexaEvent, Callback callback, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    callback = new StubCallback();
                }
                fitnessSessionEventReceiver.onFitnessSessionResumed(alexaEvent, callback);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onFitnessSessionResumed");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void onFitnessSessionStarted$default(FitnessSessionEventReceiver fitnessSessionEventReceiver, AlexaEvent alexaEvent, Callback callback, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    callback = new StubCallback();
                }
                fitnessSessionEventReceiver.onFitnessSessionStarted(alexaEvent, callback);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onFitnessSessionStarted");
        }
    }

    void onFitnessSessionEnded(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback);

    void onFitnessSessionError(@NotNull AlexaEvent alexaEvent, @NotNull Error error, @NotNull Operation operation);

    void onFitnessSessionPaused(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback);

    void onFitnessSessionResumed(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback);

    void onFitnessSessionStarted(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback);
}
