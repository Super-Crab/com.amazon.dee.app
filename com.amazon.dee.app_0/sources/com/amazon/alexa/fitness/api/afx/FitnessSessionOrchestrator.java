package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.LocationCoordinate;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionOrchestrator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001)J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J1\u0010\f\u001a\u00020\u00032'\u0010\r\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00030\u000eH&J\b\u0010\u0014\u001a\u00020\u0015H&JJ\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u001828\u0010\u0019\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u001b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00030\u001aH&J\u0016\u0010\u001f\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H&J\u0016\u0010#\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H&J%\u0010$\u001a\u00020\u00032\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050&2\b\u0010'\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\u0010(¨\u0006*"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "", "addNewWorkout", "", "workout", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "allRequiredSensorsAvailable", "", "deleteAllWorkouts", "deleteWorkout", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "getLocationList", "callback", "Lkotlin/Function1;", "", "Lcom/amazon/alexa/fitness/api/LocationCoordinate;", "Lkotlin/ParameterName;", "name", "locations", "getUIState", "Lcom/amazon/alexa/fitness/api/afx/UIState;", "receive", "command", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "receivedHandler", "Lkotlin/Function2;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "error", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "currentState", "registerDelegate", "delegate", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "removeDelegate", "updateWorkouts", "workouts", "", "nextToken", "([Lcom/amazon/alexa/fitness/api/afits/FitnessSession;Ljava/lang/String;)V", "CommandProcessingError", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface FitnessSessionOrchestrator {

    /* compiled from: FitnessSessionOrchestrator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "", "(Ljava/lang/String;I)V", "NoFitnessProfile", "InvalidCommand", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public enum CommandProcessingError {
        NoFitnessProfile,
        InvalidCommand
    }

    void addNewWorkout(@NotNull FitnessSession fitnessSession);

    boolean allRequiredSensorsAvailable();

    void deleteAllWorkouts();

    void deleteWorkout(@NotNull String str);

    void getLocationList(@NotNull Function1<? super List<LocationCoordinate>, Unit> function1);

    @NotNull
    UIState getUIState();

    void receive(@NotNull Command command, @NotNull Function2<? super CommandProcessingError, ? super FitnessSessionState, Unit> function2);

    void registerDelegate(@NotNull WeakReference<FitnessSessionOrchestratorDelegate> weakReference);

    void removeDelegate(@NotNull WeakReference<FitnessSessionOrchestratorDelegate> weakReference);

    void updateWorkouts(@NotNull FitnessSession[] fitnessSessionArr, @Nullable String str);
}
