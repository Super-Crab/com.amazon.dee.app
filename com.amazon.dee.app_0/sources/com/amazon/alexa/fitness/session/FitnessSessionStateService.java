package com.amazon.alexa.fitness.session;

import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionStateService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0005H&J\b\u0010\t\u001a\u00020\u0005H&J\b\u0010\n\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/session/FitnessSessionStateService;", "", "getFitnessSessionState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "isFitnessSessionActive", "", "isFitnessSessionInProgress", "isFitnessSessionInactive", "isFitnessSessionPaused", "isFitnessSessionSystemPaused", "isFitnessSessionUserPaused", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface FitnessSessionStateService {
    @NotNull
    FitnessSessionState getFitnessSessionState();

    boolean isFitnessSessionActive();

    boolean isFitnessSessionInProgress();

    boolean isFitnessSessionInactive();

    boolean isFitnessSessionPaused();

    boolean isFitnessSessionSystemPaused();

    boolean isFitnessSessionUserPaused();
}
