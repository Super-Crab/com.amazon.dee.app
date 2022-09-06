package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH&J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H&R \u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u00020\u000eX¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006#"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionManager;", "", "delegate", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/fitness/sdk/SessionManagerDelegate;", "getDelegate", "()Ljava/lang/ref/WeakReference;", "setDelegate", "(Ljava/lang/ref/WeakReference;)V", "fitnessAlgorithmsManager", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmsManager;", "getFitnessAlgorithmsManager", "()Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmsManager;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "getSampleStore", "()Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "setSampleStore", "(Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;)V", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "getSession", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "state", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "getState", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "initSensorProviders", "", "sensorProviders", "", "Lcom/amazon/alexa/fitness/sdk/SensorProvider;", "receive", "command", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SessionManager {
    @Nullable
    WeakReference<SessionManagerDelegate> getDelegate();

    @NotNull
    FitnessAlgorithmsManager getFitnessAlgorithmsManager();

    @NotNull
    SampleStore getSampleStore();

    @Nullable
    Session getSession();

    @NotNull
    FitnessSessionState getState();

    void initSensorProviders(@NotNull List<? extends SensorProvider> list);

    void receive(@NotNull Command command) throws SessionManagerException;

    void setDelegate(@Nullable WeakReference<SessionManagerDelegate> weakReference);

    void setSampleStore(@NotNull SampleStore sampleStore);
}
