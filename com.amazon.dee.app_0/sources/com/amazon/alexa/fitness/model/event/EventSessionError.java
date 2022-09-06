package com.amazon.alexa.fitness.model.event;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/model/event/EventSessionError;", "Lcom/amazon/alexa/fitness/model/event/EventSession;", "id", "Ljava/util/UUID;", "error", "", "operation", "(Ljava/util/UUID;Ljava/lang/String;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "getOperation", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class EventSessionError extends EventSession {
    @NotNull
    private final String error;
    @NotNull
    private final String operation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventSessionError(@NotNull UUID id, @NotNull String error, @NotNull String operation) {
        super(id);
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        this.error = error;
        this.operation = operation;
    }

    @NotNull
    public final String getError() {
        return this.error;
    }

    @NotNull
    public final String getOperation() {
        return this.operation;
    }
}
