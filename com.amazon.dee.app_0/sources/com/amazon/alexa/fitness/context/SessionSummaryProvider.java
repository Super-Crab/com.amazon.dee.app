package com.amazon.alexa.fitness.context;

import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionSummaryProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/context/SessionSummaryProvider;", "", "delegate", "Lcom/amazon/alexa/fitness/context/SessionSummaryProviderDelegate;", "getDelegate", "()Lcom/amazon/alexa/fitness/context/SessionSummaryProviderDelegate;", "setDelegate", "(Lcom/amazon/alexa/fitness/context/SessionSummaryProviderDelegate;)V", "onSessionStateChanged", "", "currentState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SessionSummaryProvider {
    @Nullable
    SessionSummaryProviderDelegate getDelegate();

    void onSessionStateChanged(@NotNull FitnessSessionState fitnessSessionState, @Nullable com.amazon.alexa.fitness.api.fitnessSdk.Session session);

    void setDelegate(@Nullable SessionSummaryProviderDelegate sessionSummaryProviderDelegate);
}
