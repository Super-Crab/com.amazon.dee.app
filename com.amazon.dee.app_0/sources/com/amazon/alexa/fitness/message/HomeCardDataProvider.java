package com.amazon.alexa.fitness.message;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.fitness.R;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate;
import com.amazon.alexa.fitness.api.afx.HomeCardViewModel;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.SessionManager;
import com.amazon.alexa.fitness.util.GuavaUtilsKt;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import java.lang.ref.WeakReference;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HomeCardDataProvider.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0017\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0014H\u0016J\u001c\u0010\u001f\u001a\u00020\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0018\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u0014H\u0016J\b\u0010%\u001a\u00020\u001cH\u0002J\u0010\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020(H\u0016J\u0017\u0010)\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002¢\u0006\u0002\u0010,J\b\u0010-\u001a\u00020.H\u0002J\u000e\u0010/\u001a\u00020.*\u0004\u0018\u000100H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/amazon/alexa/fitness/message/HomeCardDataProvider;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "accessorySensorProvider", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySensorProvider;", "context", "Landroid/content/Context;", "fitnessHomeCardPublisher", "Lcom/amazon/alexa/fitness/message/FitnessHomeCardPublisher;", "homeCardViewModel", "Lcom/amazon/alexa/fitness/api/afx/HomeCardViewModel;", "sessionManager", "Lcom/amazon/alexa/fitness/sdk/SessionManager;", "fitnessSessionOrchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "componentRegistry", "Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySensorProvider;Landroid/content/Context;Lcom/amazon/alexa/fitness/message/FitnessHomeCardPublisher;Lcom/amazon/alexa/fitness/api/afx/HomeCardViewModel;Lcom/amazon/alexa/fitness/sdk/SessionManager;Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;Lcom/amazon/alexa/fitness/logs/ILog;)V", "fitnessMetrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "lastKnownState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "mainHandler", "Landroid/os/Handler;", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "checkAndUpdateHomeCard", "", "onMetricsUpdated", "metrics", "onSessionChanged", "error", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "onSessionEnded", "endedSession", "finalMetrics", "registerForHomeEnteredEvents", "sensorAvailabilityChanged", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "setDuration", "duration", "", "(Ljava/lang/Long;)V", "shouldShowDuration", "", "isHomeEnteredEvent", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class HomeCardDataProvider implements FitnessSessionOrchestratorDelegate {
    private final FitnessAccessorySensorProvider accessorySensorProvider;
    private final ComponentRegistry componentRegistry;
    private final Context context;
    private final FitnessHomeCardPublisher fitnessHomeCardPublisher;
    private FitnessMetrics fitnessMetrics;
    private final HomeCardViewModel homeCardViewModel;
    private FitnessSessionState lastKnownState;
    private final ILog log;
    private final Handler mainHandler;
    private Session session;

    @Inject
    public HomeCardDataProvider(@NotNull FitnessAccessorySensorProvider accessorySensorProvider, @NotNull Context context, @NotNull FitnessHomeCardPublisher fitnessHomeCardPublisher, @NotNull HomeCardViewModel homeCardViewModel, @NotNull SessionManager sessionManager, @NotNull FitnessSessionOrchestrator fitnessSessionOrchestrator, @NotNull ComponentRegistry componentRegistry, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(accessorySensorProvider, "accessorySensorProvider");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(fitnessHomeCardPublisher, "fitnessHomeCardPublisher");
        Intrinsics.checkParameterIsNotNull(homeCardViewModel, "homeCardViewModel");
        Intrinsics.checkParameterIsNotNull(sessionManager, "sessionManager");
        Intrinsics.checkParameterIsNotNull(fitnessSessionOrchestrator, "fitnessSessionOrchestrator");
        Intrinsics.checkParameterIsNotNull(componentRegistry, "componentRegistry");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.accessorySensorProvider = accessorySensorProvider;
        this.context = context;
        this.fitnessHomeCardPublisher = fitnessHomeCardPublisher;
        this.homeCardViewModel = homeCardViewModel;
        this.componentRegistry = componentRegistry;
        this.log = log;
        this.lastKnownState = sessionManager.getState();
        this.mainHandler = new Handler(Looper.getMainLooper());
        fitnessSessionOrchestrator.registerDelegate(new WeakReference<>(this));
        checkAndUpdateHomeCard();
        registerForHomeEnteredEvents();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAndUpdateHomeCard() {
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("last known state = ");
        outline107.append(this.lastKnownState);
        ILog.DefaultImpls.info$default(iLog, "HomeCardDataProvider", outline107.toString(), null, 4, null);
        if (!this.lastKnownState.isSessionInactive() && !this.lastKnownState.isSessionInitializing()) {
            if (!this.lastKnownState.isSessionInProgress()) {
                return;
            }
            if (this.accessorySensorProvider.getSensorInUse() != null) {
                ILog.DefaultImpls.info$default(this.log, "HomeCardDataProvider", "session in progress and accessory exists, show in progress view", null, 4, null);
                this.fitnessHomeCardPublisher.showFitnessHomeCard();
                this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.message.HomeCardDataProvider$checkAndUpdateHomeCard$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        FitnessSessionState fitnessSessionState;
                        HomeCardViewModel homeCardViewModel;
                        Context context;
                        HomeCardViewModel homeCardViewModel2;
                        Context context2;
                        fitnessSessionState = HomeCardDataProvider.this.lastKnownState;
                        if (fitnessSessionState.isSessionPaused()) {
                            homeCardViewModel2 = HomeCardDataProvider.this.homeCardViewModel;
                            context2 = HomeCardDataProvider.this.context;
                            String string = context2.getString(R.string.workout_paused);
                            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.workout_paused)");
                            homeCardViewModel2.setSubTitle(string);
                            return;
                        }
                        homeCardViewModel = HomeCardDataProvider.this.homeCardViewModel;
                        context = HomeCardDataProvider.this.context;
                        String string2 = context.getString(R.string.workout_in_progress);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.string.workout_in_progress)");
                        homeCardViewModel.setSubTitle(string2);
                    }
                });
                return;
            }
            ILog.DefaultImpls.info$default(this.log, "HomeCardDataProvider", "session in progress and accessory does not exist, show re-connect message", null, 4, null);
            this.fitnessHomeCardPublisher.showFitnessHomeCard();
            HomeCardViewModel homeCardViewModel = this.homeCardViewModel;
            String string = this.context.getString(R.string.home_card_accessory_disconnect_title);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…cessory_disconnect_title)");
            homeCardViewModel.setTitle(string);
            HomeCardViewModel homeCardViewModel2 = this.homeCardViewModel;
            String string2 = this.context.getString(R.string.home_card_accessory_disconnected_subtitle);
            Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.stri…ry_disconnected_subtitle)");
            homeCardViewModel2.setSubTitle(string2);
            return;
        }
        this.fitnessHomeCardPublisher.hideFitnessHomeCard();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isHomeEnteredEvent(@Nullable String str) {
        return Intrinsics.areEqual(str, Constants.DEVICE_HOME_EVENT);
    }

    private final void registerForHomeEnteredEvents() {
        Optional optional = this.componentRegistry.get(EventBus.class);
        Intrinsics.checkExpressionValueIsNotNull(optional, "componentRegistry.get(EventBus::class.java)");
        GuavaUtilsKt.flatMap(optional, HomeCardDataProvider$registerForHomeEnteredEvents$1.INSTANCE).transform(new Function<T, V>() { // from class: com.amazon.alexa.fitness.message.HomeCardDataProvider$registerForHomeEnteredEvents$2
            @Override // com.google.common.base.Function
            @Nullable
            /* renamed from: apply */
            public final UUID mo8172apply(@Nullable MultiFilterSubscriber multiFilterSubscriber) {
                if (multiFilterSubscriber != null) {
                    return multiFilterSubscriber.subscribe(new MessageFilter() { // from class: com.amazon.alexa.fitness.message.HomeCardDataProvider$registerForHomeEnteredEvents$2.1
                        @Override // com.amazon.alexa.eventbus.api.MessageFilter
                        public final boolean isMatch(@NotNull Message message) {
                            boolean isHomeEnteredEvent;
                            Intrinsics.checkParameterIsNotNull(message, "message");
                            isHomeEnteredEvent = HomeCardDataProvider.this.isHomeEnteredEvent(message.getEventType());
                            return isHomeEnteredEvent;
                        }
                    }, new MessageHandler() { // from class: com.amazon.alexa.fitness.message.HomeCardDataProvider$registerForHomeEnteredEvents$2.2
                        @Override // com.amazon.alexa.eventbus.api.MessageHandler
                        public final void handle(@NotNull Message message) {
                            Intrinsics.checkParameterIsNotNull(message, "<anonymous parameter 0>");
                            HomeCardDataProvider.this.checkAndUpdateHomeCard();
                        }
                    });
                }
                return null;
            }
        });
    }

    private final void setDuration(Long l) {
        if (l != null) {
            long longValue = l.longValue();
            HomeCardViewModel homeCardViewModel = this.homeCardViewModel;
            String string = this.context.getString(R.string.workout_duration, DateUtils.formatElapsedTime(longValue));
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…ls.formatElapsedTime(it))");
            homeCardViewModel.setTitle(string);
        }
    }

    private final boolean shouldShowDuration() {
        return this.lastKnownState.isSessionInProgress() && this.accessorySensorProvider.getSensorInUse() != null;
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onMetricsUpdated(@Nullable FitnessMetrics fitnessMetrics) {
        Session session;
        this.fitnessMetrics = fitnessMetrics;
        if (!shouldShowDuration() || (session = this.session) == null) {
            return;
        }
        setDuration(Long.valueOf(SessionDataModelsKt.processingDuration$default(session, null, 1, null)));
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionChanged(@Nullable Session session, @Nullable FitnessSessionOrchestrator.CommandProcessingError commandProcessingError) {
        FitnessSessionState fitnessSessionState;
        ILog.DefaultImpls.info$default(this.log, "HomeCardDataProvider", "session changed", null, 4, null);
        if (session == null || (fitnessSessionState = SessionDataModelsKt.currentState(session)) == null) {
            fitnessSessionState = FitnessSessionState.IDLE;
        }
        this.lastKnownState = fitnessSessionState;
        this.session = session;
        checkAndUpdateHomeCard();
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionEnded(@NotNull Session endedSession, @NotNull FitnessMetrics finalMetrics) {
        Intrinsics.checkParameterIsNotNull(endedSession, "endedSession");
        Intrinsics.checkParameterIsNotNull(finalMetrics, "finalMetrics");
        ILog.DefaultImpls.info$default(this.log, "HomeCardDataProvider", "session ended", null, 4, null);
        this.lastKnownState = SessionDataModelsKt.currentState(endedSession);
        this.fitnessMetrics = finalMetrics;
        checkAndUpdateHomeCard();
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void sensorAvailabilityChanged(@NotNull SensorAvailability availability) {
        Intrinsics.checkParameterIsNotNull(availability, "availability");
        checkAndUpdateHomeCard();
    }
}
