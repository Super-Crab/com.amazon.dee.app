package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorProviderType;
import com.amazon.alexa.location.utils.MetricsUtil;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SensorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0016\u00101\u001a\u00020\u001e2\f\u00102\u001a\b\u0012\u0004\u0012\u00020,0+H&J-\u00103\u001a\u00020\u001e2#\u00104\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000105¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001e0\u0019H&J\b\u00107\u001a\u00020\u001eH&JC\u00108\u001a\u00020\u001e2\u0006\u00109\u001a\u00020:2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00070<2#\u00104\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000105¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001e0\u0019H&J\u0016\u0010=\u001a\u00020\u001e2\f\u00102\u001a\b\u0012\u0004\u0012\u00020,0+H&J-\u0010>\u001a\u00020\u001e2#\u00104\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000105¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001e0\u0019H&J5\u0010?\u001a\u00020\u001e2\u0006\u00109\u001a\u00020:2#\u00104\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000105¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001e0\u0019H&J-\u0010@\u001a\u00020\u001e2#\u00104\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000105¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001e0\u0019H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u0013X¦\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R5\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0019X¦\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0014\u0010#\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\tR\u0012\u0010%\u001a\u00020&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R&\u0010)\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0+\u0018\u00010*X¦\u000e¢\u0006\f\u001a\u0004\b-\u0010.\"\u0004\b/\u00100¨\u0006A"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SensorProvider;", "", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "getAvailability", "()Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "deviceTypeInUse", "", "getDeviceTypeInUse", "()Ljava/lang/String;", "deviceTypeInUseFirmwareVersion", "", "getDeviceTypeInUseFirmwareVersion", "()Ljava/lang/Integer;", "required", "", "getRequired", "()Z", "sampleObserver", "Lcom/amazon/alexa/fitness/sdk/SampleObserver;", "getSampleObserver", "()Lcom/amazon/alexa/fitness/sdk/SampleObserver;", "setSampleObserver", "(Lcom/amazon/alexa/fitness/sdk/SampleObserver;)V", "sensorCommandReceiver", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "Lkotlin/ParameterName;", "name", "sensorCommand", "", "getSensorCommandReceiver", "()Lkotlin/jvm/functions/Function1;", "setSensorCommandReceiver", "(Lkotlin/jvm/functions/Function1;)V", "sensorInUse", "getSensorInUse", "sensorProviderType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorProviderType;", "getSensorProviderType", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorProviderType;", "stateObservers", "", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/fitness/sdk/SensorStateObserver;", "getStateObservers", "()Ljava/util/List;", "setStateObservers", "(Ljava/util/List;)V", "addObserver", "observer", "pause", "callback", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "error", "prepare", "recover", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "sensorIds", "", "removeObserver", "resume", "start", "stop", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SensorProvider {
    void addObserver(@NotNull WeakReference<SensorStateObserver> weakReference);

    @Nullable
    SensorAvailability getAvailability();

    @Nullable
    String getDeviceTypeInUse();

    @Nullable
    Integer getDeviceTypeInUseFirmwareVersion();

    boolean getRequired();

    @Nullable
    SampleObserver getSampleObserver();

    @Nullable
    Function1<Command, Unit> getSensorCommandReceiver();

    @Nullable
    String getSensorInUse();

    @NotNull
    SensorProviderType getSensorProviderType();

    @Nullable
    List<WeakReference<SensorStateObserver>> getStateObservers();

    void pause(@NotNull Function1<? super SensorError, Unit> function1);

    void prepare();

    void recover(@NotNull UUID uuid, @NotNull List<String> list, @NotNull Function1<? super SensorError, Unit> function1);

    void removeObserver(@NotNull WeakReference<SensorStateObserver> weakReference);

    void resume(@NotNull Function1<? super SensorError, Unit> function1);

    void setSampleObserver(@Nullable SampleObserver sampleObserver);

    void setSensorCommandReceiver(@Nullable Function1<? super Command, Unit> function1);

    void setStateObservers(@Nullable List<WeakReference<SensorStateObserver>> list);

    void start(@NotNull UUID uuid, @NotNull Function1<? super SensorError, Unit> function1);

    void stop(@NotNull Function1<? super SensorError, Unit> function1);
}
