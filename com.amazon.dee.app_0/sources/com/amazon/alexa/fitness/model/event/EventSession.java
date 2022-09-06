package com.amazon.alexa.fitness.model.event;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FitnessSessionEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0012\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/model/event/EventSession;", "", "id", "Ljava/util/UUID;", "(Ljava/util/UUID;)V", "getId", "()Ljava/util/UUID;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class EventSession {
    @NotNull
    private final UUID id;

    public EventSession(@NotNull UUID id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.id = id;
    }

    @NotNull
    public final UUID getId() {
        return this.id;
    }
}
