package com.amazon.alexa.cantilever;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.cantilever.HelpMetricsConstants;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public class HelpSessionService {
    private static final int MINIMUM_HTTP_ERROR = 400;
    private static final int MINIMUM_HTTP_SERVER_ERROR = 500;
    private static final String TAG = "HelpSessionService";
    private static final String THANK_YOU_PATH = "thankyou";
    private final LazyComponent<EnvironmentService> environmentService;
    private final HelpMetricsService helpMetricsService;
    private boolean isHelpFlowCompleted;
    private boolean isLoadUrlCalled;
    private boolean isUnableToLoad;
    private MAPSignInState mapSignInState;
    private final Set<String> recordableJsEvents;
    private MetricSessionState state;
    @Nullable
    private LazyComponent<TTCFCheckpoint> ttcfService;

    /* renamed from: com.amazon.alexa.cantilever.HelpSessionService$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$cantilever$HelpSessionService$MetricSessionState = new int[MetricSessionState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$cantilever$HelpSessionService$MetricSessionState[MetricSessionState.ATTEMPTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$cantilever$HelpSessionService$MetricSessionState[MetricSessionState.AUTHENTICATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes6.dex */
    enum MAPSignInState {
        MAP_NOT_SIGNED_IN,
        MAP_SIGNED_IN,
        MAP_SIGNED_IN_LAST_TIME
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public enum MetricSessionState {
        UNINITIALIZED,
        ATTEMPTED,
        FAILED,
        LANDED,
        AUTHENTICATED,
        CANCELED
    }

    public HelpSessionService(HelpMetricsService helpMetricsService) {
        this(helpMetricsService, ComponentRegistry.getInstance());
    }

    private boolean isInProgress() {
        int ordinal = this.state.ordinal();
        return ordinal == 1 || ordinal == 4;
    }

    private void markComplete() {
        LazyComponent<TTCFCheckpoint> lazyComponent = this.ttcfService;
        if (lazyComponent != null) {
            lazyComponent.mo10268get().markComplete(RouteName.HELP);
            this.ttcfService = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isHelpFlowCompleted() {
        return this.isHelpFlowCompleted;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isLoadUrlCalled() {
        return this.isLoadUrlCalled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isMAPNotSignedIn() {
        return this.mapSignInState == MAPSignInState.MAP_NOT_SIGNED_IN;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isMAPSignedInLastTime() {
        return this.mapSignInState == MAPSignInState.MAP_SIGNED_IN_LAST_TIME;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isUnableToLoad() {
        return this.isUnableToLoad;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onHelpFlowCompleted(String str) {
        this.isHelpFlowCompleted = !TextUtils.isEmpty(str) && str.contains(THANK_YOU_PATH);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onHelpLoadAttempt() {
        if (this.state == MetricSessionState.UNINITIALIZED) {
            this.state = MetricSessionState.ATTEMPTED;
            this.helpMetricsService.recordHelpLoadAttempt();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onHelpLoadDetached() {
        if (isInProgress()) {
            this.state = MetricSessionState.CANCELED;
            this.helpMetricsService.recordHelpLoadCancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onHelpLoadFinishLanding(String str) {
        boolean isKnownUrl = this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AUTH_PORTAL_SIGN_IN, str);
        if (isKnownUrl && this.mapSignInState == MAPSignInState.MAP_SIGNED_IN) {
            this.mapSignInState = MAPSignInState.MAP_SIGNED_IN_LAST_TIME;
            return;
        }
        this.mapSignInState = MAPSignInState.MAP_NOT_SIGNED_IN;
        if (TextUtils.isEmpty(str) || !isInProgress()) {
            return;
        }
        if (isKnownUrl) {
            MetricSessionState metricSessionState = this.state;
            MetricSessionState metricSessionState2 = MetricSessionState.AUTHENTICATED;
            if (metricSessionState == metricSessionState2) {
                return;
            }
            this.state = metricSessionState2;
            this.helpMetricsService.recordHelpLoadSignIn();
            return;
        }
        this.state = MetricSessionState.LANDED;
        markComplete();
        this.helpMetricsService.recordHelpLoadSuccess();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onHelpLoadHttpError(int i, @NonNull String str) {
        if (i >= 400) {
            if (isInProgress()) {
                this.state = MetricSessionState.FAILED;
                this.helpMetricsService.recordHelpLoadHttpError(i, str);
            }
            this.isUnableToLoad = i >= 500;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onHelpLoadOtherError(int i, @NonNull String str) {
        if (isInProgress()) {
            this.state = MetricSessionState.FAILED;
            this.helpMetricsService.recordHelpLoadOtherError(i, str);
        }
        this.isUnableToLoad = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onJsEventSent(String str) {
        if (!(isInProgress() || this.state == MetricSessionState.LANDED) || !this.recordableJsEvents.contains(str)) {
            return;
        }
        this.recordableJsEvents.remove(str);
        markComplete();
        this.helpMetricsService.recordJsEvent(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onLoadUrlCalled() {
        this.isLoadUrlCalled = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onMAPOverrideUrlLoading(String str) {
        if (this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AUTH_PORTAL_SIGN_IN, str)) {
            this.mapSignInState = MAPSignInState.MAP_SIGNED_IN;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetUnableToLoadFlag() {
        this.isUnableToLoad = false;
    }

    public HelpSessionService(HelpMetricsService helpMetricsService, ComponentRegistry componentRegistry) {
        this.recordableJsEvents = new HashSet(HelpMetricsConstants.EventName.JS_EVENTS);
        this.state = MetricSessionState.UNINITIALIZED;
        this.mapSignInState = MAPSignInState.MAP_NOT_SIGNED_IN;
        this.helpMetricsService = helpMetricsService;
        this.environmentService = componentRegistry.getLazy(EnvironmentService.class);
        this.ttcfService = componentRegistry.getLazy(TTCFCheckpoint.class);
    }
}
