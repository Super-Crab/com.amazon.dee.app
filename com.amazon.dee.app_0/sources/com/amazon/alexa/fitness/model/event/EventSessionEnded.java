package com.amazon.alexa.fitness.model.event;

import amazon.speech.simclient.settings.Settings;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/model/event/EventSessionEnded;", "Lcom/amazon/alexa/fitness/model/event/EventSession;", "id", "Ljava/util/UUID;", Settings.ListeningSettings.EXTRA_REASON, "", "(Ljava/util/UUID;Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class EventSessionEnded extends EventSession {
    @NotNull
    private final String reason;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventSessionEnded(@NotNull UUID id, @NotNull String reason) {
        super(id);
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        this.reason = reason;
    }

    @NotNull
    public final String getReason() {
        return this.reason;
    }
}
