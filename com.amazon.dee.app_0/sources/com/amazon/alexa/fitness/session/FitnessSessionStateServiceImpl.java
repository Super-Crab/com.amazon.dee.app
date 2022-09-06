package com.amazon.alexa.fitness.session;

import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionCommandSource;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionEvent;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType;
import com.amazon.alexa.fitness.sdk.SessionManager;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionStateServiceImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J%\u0010\f\u001a\u00020\b2\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u000e\"\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/fitness/session/FitnessSessionStateServiceImpl;", "Lcom/amazon/alexa/fitness/session/FitnessSessionStateService;", "sessionManager", "Lcom/amazon/alexa/fitness/sdk/SessionManager;", "(Lcom/amazon/alexa/fitness/sdk/SessionManager;)V", "getFitnessSessionState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "isFitnessSessionActive", "", "isFitnessSessionInProgress", "isFitnessSessionInactive", "isFitnessSessionPaused", "isFitnessSessionStateOneOf", "fitnessSessionStates", "", "([Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;)Z", "isFitnessSessionSystemPaused", "isFitnessSessionUserPaused", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionStateServiceImpl implements FitnessSessionStateService {
    private final SessionManager sessionManager;

    @Inject
    public FitnessSessionStateServiceImpl(@NotNull SessionManager sessionManager) {
        Intrinsics.checkParameterIsNotNull(sessionManager, "sessionManager");
        this.sessionManager = sessionManager;
    }

    private final boolean isFitnessSessionStateOneOf(FitnessSessionState... fitnessSessionStateArr) {
        for (FitnessSessionState fitnessSessionState : fitnessSessionStateArr) {
            if (fitnessSessionState == getFitnessSessionState()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.fitness.session.FitnessSessionStateService
    @NotNull
    public FitnessSessionState getFitnessSessionState() {
        FitnessSessionState currentState;
        Session session = this.sessionManager.getSession();
        return (session == null || (currentState = SessionDataModelsKt.currentState(session)) == null) ? FitnessSessionState.IDLE : currentState;
    }

    @Override // com.amazon.alexa.fitness.session.FitnessSessionStateService
    public boolean isFitnessSessionActive() {
        return getFitnessSessionState().isSessionActive();
    }

    @Override // com.amazon.alexa.fitness.session.FitnessSessionStateService
    public boolean isFitnessSessionInProgress() {
        return getFitnessSessionState().isSessionInProgress();
    }

    @Override // com.amazon.alexa.fitness.session.FitnessSessionStateService
    public boolean isFitnessSessionInactive() {
        return getFitnessSessionState().isSessionInactive();
    }

    @Override // com.amazon.alexa.fitness.session.FitnessSessionStateService
    public boolean isFitnessSessionPaused() {
        return getFitnessSessionState().isSessionPaused();
    }

    @Override // com.amazon.alexa.fitness.session.FitnessSessionStateService
    public boolean isFitnessSessionSystemPaused() {
        if (isFitnessSessionStateOneOf(FitnessSessionState.PAUSED, FitnessSessionState.RESUMING)) {
            Session session = this.sessionManager.getSession();
            List<SessionEvent> events = session != null ? session.getEvents() : null;
            if (events != null) {
                for (SessionEvent sessionEvent : events) {
                    if (sessionEvent.getEventType() instanceof SessionEventType.StateChangeEvent) {
                        SessionEventType eventType = sessionEvent.getEventType();
                        if (eventType != null) {
                            SessionEventType.StateChangeEvent stateChangeEvent = (SessionEventType.StateChangeEvent) eventType;
                            if (stateChangeEvent.getState() == FitnessSessionState.PAUSED) {
                                Command command = stateChangeEvent.getCommand();
                                if (command == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.fitnessSdk.Command.Pause");
                                }
                                return ((Command.Pause) command).getSource() == SessionCommandSource.SENSOR;
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType.StateChangeEvent");
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.fitness.session.FitnessSessionStateService
    public boolean isFitnessSessionUserPaused() {
        if (isFitnessSessionStateOneOf(FitnessSessionState.PAUSED, FitnessSessionState.RESUMING)) {
            Session session = this.sessionManager.getSession();
            List<SessionEvent> events = session != null ? session.getEvents() : null;
            if (events != null) {
                for (SessionEvent sessionEvent : events) {
                    if (sessionEvent.getEventType() instanceof SessionEventType.StateChangeEvent) {
                        SessionEventType eventType = sessionEvent.getEventType();
                        if (eventType != null) {
                            SessionEventType.StateChangeEvent stateChangeEvent = (SessionEventType.StateChangeEvent) eventType;
                            if (stateChangeEvent.getState() == FitnessSessionState.PAUSED) {
                                Command command = stateChangeEvent.getCommand();
                                if (command == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.fitnessSdk.Command.Pause");
                                }
                                return ((Command.Pause) command).getSource() != SessionCommandSource.SENSOR;
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType.StateChangeEvent");
                        }
                    }
                }
            }
        }
        return false;
    }
}
