package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessAccessoryCommandHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J=\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tH&J=\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tH&J=\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tH&J=\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tH&¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/accessory/FitnessAccessoryCommandHandler;", "", "pause", "", "accessorySession", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "callback", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "Lkotlin/ParameterName;", "name", "error", "resume", "start", "stop", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface FitnessAccessoryCommandHandler {
    void pause(@NotNull AccessorySession accessorySession, @NotNull UUID uuid, @NotNull Function1<? super SensorError, Unit> function1);

    void resume(@NotNull AccessorySession accessorySession, @NotNull UUID uuid, @NotNull Function1<? super SensorError, Unit> function1);

    void start(@NotNull AccessorySession accessorySession, @NotNull UUID uuid, @NotNull Function1<? super SensorError, Unit> function1);

    void stop(@NotNull AccessorySession accessorySession, @NotNull UUID uuid, @NotNull Function1<? super SensorError, Unit> function1);
}
