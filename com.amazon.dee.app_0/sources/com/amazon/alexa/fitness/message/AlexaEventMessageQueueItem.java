package com.amazon.alexa.fitness.message;

import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.fitness.util.Callback;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SpeechletEventEmitterImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/message/AlexaEventMessageQueueItem;", "", "alexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "callback", "Lcom/amazon/alexa/fitness/util/Callback;", "", "", "(Lcom/amazon/alexa/api/AlexaEvent;Lcom/amazon/alexa/fitness/util/Callback;)V", "getAlexaEvent", "()Lcom/amazon/alexa/api/AlexaEvent;", "getCallback", "()Lcom/amazon/alexa/fitness/util/Callback;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class AlexaEventMessageQueueItem {
    @NotNull
    private final AlexaEvent alexaEvent;
    @NotNull
    private final Callback<Unit, Throwable> callback;

    public AlexaEventMessageQueueItem(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.alexaEvent = alexaEvent;
        this.callback = callback;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AlexaEventMessageQueueItem copy$default(AlexaEventMessageQueueItem alexaEventMessageQueueItem, AlexaEvent alexaEvent, Callback callback, int i, Object obj) {
        if ((i & 1) != 0) {
            alexaEvent = alexaEventMessageQueueItem.alexaEvent;
        }
        if ((i & 2) != 0) {
            callback = alexaEventMessageQueueItem.callback;
        }
        return alexaEventMessageQueueItem.copy(alexaEvent, callback);
    }

    @NotNull
    public final AlexaEvent component1() {
        return this.alexaEvent;
    }

    @NotNull
    public final Callback<Unit, Throwable> component2() {
        return this.callback;
    }

    @NotNull
    public final AlexaEventMessageQueueItem copy(@NotNull AlexaEvent alexaEvent, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        return new AlexaEventMessageQueueItem(alexaEvent, callback);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AlexaEventMessageQueueItem)) {
                return false;
            }
            AlexaEventMessageQueueItem alexaEventMessageQueueItem = (AlexaEventMessageQueueItem) obj;
            return Intrinsics.areEqual(this.alexaEvent, alexaEventMessageQueueItem.alexaEvent) && Intrinsics.areEqual(this.callback, alexaEventMessageQueueItem.callback);
        }
        return true;
    }

    @NotNull
    public final AlexaEvent getAlexaEvent() {
        return this.alexaEvent;
    }

    @NotNull
    public final Callback<Unit, Throwable> getCallback() {
        return this.callback;
    }

    public int hashCode() {
        AlexaEvent alexaEvent = this.alexaEvent;
        int i = 0;
        int hashCode = (alexaEvent != null ? alexaEvent.hashCode() : 0) * 31;
        Callback<Unit, Throwable> callback = this.callback;
        if (callback != null) {
            i = callback.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaEventMessageQueueItem(alexaEvent=");
        outline107.append(this.alexaEvent);
        outline107.append(", callback=");
        outline107.append(this.callback);
        outline107.append(")");
        return outline107.toString();
    }
}
