package com.amazon.alexa.fitness.identity;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: IdentityManagerImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u0014\u001a\u00020\u00122\u0010\u0010\u0015\u001a\f\u0012\u0004\u0012\u00020\u00120\u0011j\u0002`\u0013H\u0016J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0017H\u0016J\n\u0010\u001c\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u00122\u0010\u0010\u0015\u001a\f\u0012\u0004\u0012\u00020\u00120\u0011j\u0002`\u0013H\u0016J\u000e\u0010 \u001a\u0004\u0018\u00010\u0017*\u00020\u0017H\u0002J\u000e\u0010!\u001a\u0004\u0018\u00010\u0017*\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00120\u0011j\u0002`\u00130\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/alexa/fitness/identity/IdentityManagerImpl;", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "eventBus", "Lcom/amazon/alexa/eventbus/api/EventBus;", "identityService", "Lcom/amazon/alexa/identity/api/IdentityService;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "tokenManagement", "Lcom/amazon/identity/auth/device/api/TokenManagement;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/eventbus/api/EventBus;Lcom/amazon/alexa/identity/api/IdentityService;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/identity/auth/device/api/TokenManagement;Lcom/amazon/alexa/fitness/logs/ILog;)V", "listeners", "", "Lkotlin/Function0;", "", "Lcom/amazon/alexa/fitness/identity/IdentityManagerIdentityChangedListener;", "addIdentityChangedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "getMapTokenByDirectedId", "", "directedId", "getUser", "Lcom/amazon/alexa/identity/api/UserIdentity;", "getUserIdentityDirectedId", "getUserProfileDirectedId", "isUserLoggedIn", "", "removeIdentityChangedListener", "getMAPTokenFromDirectedId", "getMapTokenFromDirectedIdWithoutMetrics", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class IdentityManagerImpl implements IdentityManager {
    private final IdentityService identityService;
    private List<Function0<Unit>> listeners;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    private final TokenManagement tokenManagement;

    @Inject
    public IdentityManagerImpl(@NotNull EventBus eventBus, @NotNull IdentityService identityService, @NotNull MetricEventFactory metricEventFactory, @NotNull MetricEventRecorder metricEventRecorder, @NotNull TokenManagement tokenManagement, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(eventBus, "eventBus");
        Intrinsics.checkParameterIsNotNull(identityService, "identityService");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(tokenManagement, "tokenManagement");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.identityService = identityService;
        this.metricEventFactory = metricEventFactory;
        this.metricEventRecorder = metricEventRecorder;
        this.tokenManagement = tokenManagement;
        this.log = log;
        this.listeners = new ArrayList();
        eventBus.getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.fitness.identity.IdentityManagerImpl.1
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(@NotNull Message it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                ILog.DefaultImpls.debug$default(IdentityManagerImpl.this.log, MetricsClass.IDENTITY_MANAGER, "user identity changed, notifying listeners", null, 4, null);
                for (Function0 function0 : IdentityManagerImpl.this.listeners) {
                    ILog.DefaultImpls.debug$default(IdentityManagerImpl.this.log, MetricsClass.IDENTITY_MANAGER, "invoking listener", null, 4, null);
                    function0.mo12560invoke();
                }
            }
        });
    }

    private final String getMAPTokenFromDirectedId(@NotNull String str) {
        MetricEvent createMetricEvent = this.metricEventFactory.createMetricEvent(MetricsClass.IDENTITY_MANAGER);
        String buildMetricName = MetricsConstantsKt.buildMetricName(MetricsOperation.GET, MetricsCategory.MAP_TOKEN, MetricsName.LATENCY);
        String buildMetricName2 = MetricsConstantsKt.buildMetricName(MetricsOperation.GET, MetricsCategory.MAP_TOKEN, "Success");
        createMetricEvent.startTimer(buildMetricName);
        String mapTokenFromDirectedIdWithoutMetrics = getMapTokenFromDirectedIdWithoutMetrics(str);
        createMetricEvent.stopTimer(buildMetricName);
        createMetricEvent.setCounter(buildMetricName2, mapTokenFromDirectedIdWithoutMetrics != null ? 1L : 0L);
        this.metricEventRecorder.record(createMetricEvent);
        return mapTokenFromDirectedIdWithoutMetrics;
    }

    private final String getMapTokenFromDirectedIdWithoutMetrics(@NotNull String str) {
        try {
            return this.tokenManagement.getToken(str, "com.amazon.dcp.sso.token.oauth.amazon.access_token", null, null).get().getString("value_key");
        } catch (Throwable th) {
            ILog iLog = this.log;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Caught exception while getting the token for the directed Id ", str, RealTimeTextConstants.COLON_SPACE);
            outline115.append(th.getMessage());
            iLog.error(MetricsClass.IDENTITY_MANAGER, outline115.toString(), th);
            return null;
        }
    }

    private final UserIdentity getUser() {
        return this.identityService.getUser(MetricsClass.IDENTITY_MANAGER);
    }

    @Override // com.amazon.alexa.fitness.identity.IdentityManager
    public void addIdentityChangedListener(@NotNull Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        List<Function0<Unit>> list = this.listeners;
        boolean z = false;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it2 = list.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (Intrinsics.areEqual((Function0) it2.next(), listener)) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (!z) {
            ILog.DefaultImpls.debug$default(this.log, MetricsClass.IDENTITY_MANAGER, "adding identity change listener", null, 4, null);
            this.listeners.add(listener);
            return;
        }
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.IDENTITY_MANAGER, "listener already present, skipping", null, 4, null);
    }

    @Override // com.amazon.alexa.fitness.identity.IdentityManager
    @Nullable
    public String getMapTokenByDirectedId(@NotNull String directedId) {
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        return getMAPTokenFromDirectedId(directedId);
    }

    @Override // com.amazon.alexa.fitness.identity.IdentityManager
    @Nullable
    public String getUserIdentityDirectedId() {
        UserIdentity user = getUser();
        if (user != null) {
            return user.getDirectedId();
        }
        return null;
    }

    @Override // com.amazon.alexa.fitness.identity.IdentityManager
    @Nullable
    public String getUserProfileDirectedId() {
        String directedId;
        UserIdentity user = getUser();
        if (user != null) {
            UserProfile userProfile = user.getUserProfile();
            return (userProfile == null || (directedId = userProfile.getDirectedId()) == null) ? user.getDirectedId() : directedId;
        }
        return null;
    }

    @Override // com.amazon.alexa.fitness.identity.IdentityManager
    public boolean isUserLoggedIn() {
        return this.identityService.isAuthenticated(MetricsClass.IDENTITY_MANAGER);
    }

    @Override // com.amazon.alexa.fitness.identity.IdentityManager
    public void removeIdentityChangedListener(@NotNull Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        CollectionsKt__MutableCollectionsKt.removeAll((List) this.listeners, (Function1) new IdentityManagerImpl$removeIdentityChangedListener$1(listener));
    }
}
