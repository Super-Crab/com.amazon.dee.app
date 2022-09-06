package com.amazon.alexa.fitness.algorithm;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.WorkoutType;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.SessionRecoveryManager;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAlgorithmsManagerImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0015H\u0016J\"\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0017H\u0002J\u0018\u0010 \u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"H\u0002J\u001e\u0010#\u001a\u00020\u001a2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u001b\u001a\u00020\u0015H\u0016J\b\u0010%\u001a\u00020\u001aH\u0002J\b\u0010&\u001a\u00020\u001aH\u0016J\u0010\u0010'\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010(\u001a\u00020\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R4\u0010\t\u001a\u0012\u0012\b\u0012\u00060\u000bj\u0002`\f\u0012\u0004\u0012\u00020\r0\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmsManagerImpl;", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmsManager;", "recoveryManager", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryManager;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/sdk/SessionRecoveryManager;Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;Lcom/amazon/alexa/fitness/logs/ILog;)V", "activeAlgorithms", "", "", "Lcom/amazon/alexa/fitness/algorithm/AlgorithmId;", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithm;", "activeAlgorithms$annotations", "()V", "getActiveAlgorithms", "()Ljava/util/Map;", "setActiveAlgorithms", "(Ljava/util/Map;)V", "activeWorkoutType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/WorkoutType;", "algorithmProvidersPerWorkoutType", "", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmProvider;", "deregisterAllAlgorithms", "", "workoutType", "prepareAlgosFor", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "allowedAlgorithms", "recover", "recoveredState", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmRecoverableState;", ADMRegistrationConstants.METHOD_REGISTER, "algorithmProviders", "registerWithRecoveryManager", "sessionEnded", "start", "stateDidChange", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAlgorithmsManagerImpl implements FitnessAlgorithmsManager {
    @NotNull
    private Map<String, FitnessAlgorithm> activeAlgorithms;
    private WorkoutType activeWorkoutType;
    private final Map<WorkoutType, List<FitnessAlgorithmProvider>> algorithmProvidersPerWorkoutType;
    private final ILog log;
    private final SessionRecoveryManager recoveryManager;
    private final SampleStore sampleStore;

    @Inject
    public FitnessAlgorithmsManagerImpl(@NotNull SessionRecoveryManager recoveryManager, @NotNull SampleStore sampleStore, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(recoveryManager, "recoveryManager");
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.recoveryManager = recoveryManager;
        this.sampleStore = sampleStore;
        this.log = log;
        this.algorithmProvidersPerWorkoutType = new LinkedHashMap();
        this.activeAlgorithms = new LinkedHashMap();
        registerWithRecoveryManager();
    }

    @VisibleForTesting
    public static /* synthetic */ void activeAlgorithms$annotations() {
    }

    private final void prepareAlgosFor(Session session, List<String> list) {
        List<FitnessAlgorithmProvider> list2 = this.algorithmProvidersPerWorkoutType.get(session.getConfiguration().getWorkoutType());
        if (list2 != null) {
            for (FitnessAlgorithmProvider fitnessAlgorithmProvider : list2) {
                if (list == null || list.contains(fitnessAlgorithmProvider.getAlgorithmId())) {
                    String algorithmId = fitnessAlgorithmProvider.getAlgorithmId();
                    if (this.activeAlgorithms.containsKey(algorithmId)) {
                        ILog.DefaultImpls.error$default(this.log, "FitnessAlgorithmsManager", GeneratedOutlineSupport1.outline72(algorithmId, " is already active, not setting up again"), null, 4, null);
                    } else {
                        this.activeAlgorithms.put(algorithmId, fitnessAlgorithmProvider.getBuild().mo12165invoke(session.getConfiguration()));
                        ILog.DefaultImpls.info$default(this.log, "FitnessAlgorithmsManager", GeneratedOutlineSupport1.outline75("adding ", algorithmId, " to active algorithms"), null, 4, null);
                    }
                }
            }
        } else {
            ILog.DefaultImpls.warn$default(this.log, "FitnessAlgorithmsManager", "No algorithms registered for workout type.", null, 4, null);
        }
        this.activeWorkoutType = session.getConfiguration().getWorkoutType();
        for (Map.Entry<String, FitnessAlgorithm> entry : this.activeAlgorithms.entrySet()) {
            entry.getValue().setup(this.sampleStore, session);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void prepareAlgosFor$default(FitnessAlgorithmsManagerImpl fitnessAlgorithmsManagerImpl, Session session, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = null;
        }
        fitnessAlgorithmsManagerImpl.prepareAlgosFor(session, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recover(Session session, FitnessAlgorithmRecoverableState fitnessAlgorithmRecoverableState) {
        if (session.getConfiguration().getWorkoutType() == fitnessAlgorithmRecoverableState.getWorkoutType()) {
            prepareAlgosFor(session, fitnessAlgorithmRecoverableState.getListOfAlgorithmIds());
        } else {
            prepareAlgosFor$default(this, session, null, 2, null);
        }
    }

    private final void registerWithRecoveryManager() {
        this.recoveryManager.registerComponentRecovery("FitnessAlgorithmsManager", new FitnessAlgorithmsManagerImpl$registerWithRecoveryManager$1(this), new FitnessAlgorithmsManagerImpl$registerWithRecoveryManager$2(this));
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager
    public void deregisterAllAlgorithms(@NotNull WorkoutType workoutType) {
        Intrinsics.checkParameterIsNotNull(workoutType, "workoutType");
        this.algorithmProvidersPerWorkoutType.remove(workoutType);
    }

    @NotNull
    public final Map<String, FitnessAlgorithm> getActiveAlgorithms() {
        return this.activeAlgorithms;
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager
    public void register(@NotNull List<FitnessAlgorithmProvider> algorithmProviders, @NotNull WorkoutType workoutType) {
        Intrinsics.checkParameterIsNotNull(algorithmProviders, "algorithmProviders");
        Intrinsics.checkParameterIsNotNull(workoutType, "workoutType");
        this.algorithmProvidersPerWorkoutType.put(workoutType, algorithmProviders);
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager
    public void sessionEnded() {
        ILog.DefaultImpls.info$default(this.log, "FitnessAlgorithmsManager", "Session ended, cleaning up algorithms.", null, 4, null);
        for (Map.Entry<String, FitnessAlgorithm> entry : this.activeAlgorithms.entrySet()) {
            entry.getValue().sessionEnded();
        }
        this.activeAlgorithms.clear();
    }

    public final void setActiveAlgorithms(@NotNull Map<String, FitnessAlgorithm> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.activeAlgorithms = map;
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager
    public void start(@NotNull Session session) {
        Intrinsics.checkParameterIsNotNull(session, "session");
        ILog.DefaultImpls.info$default(this.log, "FitnessAlgorithmsManager", "Starting algorithms.", null, 4, null);
        prepareAlgosFor$default(this, session, null, 2, null);
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager
    public void stateDidChange(@Nullable Session session) {
        if (session != null) {
            for (Map.Entry<String, FitnessAlgorithm> entry : this.activeAlgorithms.entrySet()) {
                entry.getValue().sessionChangedState(session);
            }
        }
    }
}
