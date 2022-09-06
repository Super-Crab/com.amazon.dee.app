package com.amazon.alexa.fitness.sdk;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MessageProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/MessageProcessor;", "", "post", "", "runnable", "Ljava/lang/Runnable;", "postWithDelay", "delayInMillis", "", BulkOperationType.remove, "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface MessageProcessor {
    void post(@NotNull Runnable runnable);

    void postWithDelay(@NotNull Runnable runnable, long j);

    void remove(@NotNull Runnable runnable);
}
