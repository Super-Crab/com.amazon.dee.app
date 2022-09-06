package com.amazon.alexa.tarazed.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault;
import com.amazon.dee.app.framework.EventBusMessagingReceiver;
import com.amazon.tarazed.core.TarazedIntents;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: EventBusHandlerDefault.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/tarazed/eventbus/EventBusHandlerDefault;", "Lcom/amazon/alexa/tarazed/eventbus/EventBusHandler;", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "handle", "", "message", "Lcom/amazon/alexa/eventbus/api/Message;", "handleIdentitySignOut", "handleLaunchEvent", "handlePauseEvent", "parseDMPSPayload", "", "Companion", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public final class EventBusHandlerDefault implements EventBusHandler {
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_EVENT_BUS_EVENT_RECEIVED = "EventReceived";
    private static final String METRIC_EVENT_BUS_LAUNCH_EVENT = "LaunchEvent";
    private static final String METRIC_EVENT_BUS_NULL_LAUNCH_REQUEST = "NullLaunchRequest";
    private static final String METRIC_EVENT_BUS_PAUSE_EVENT = "PauseEvent";
    private static final String METRIC_EVENT_BUS_SIGNOUT_EVENT = "SignoutEvent";
    private static final String METRIC_EVENT_BUS_UNEXPECTED_EVENT = "UnexpectedEvent";
    private static final String TAG = "EventBusHandlerDefault";
    private final Context context;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;

    /* compiled from: EventBusHandlerDefault.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/tarazed/eventbus/EventBusHandlerDefault$Companion;", "", "()V", "METRIC_EVENT_BUS_EVENT_RECEIVED", "", "METRIC_EVENT_BUS_LAUNCH_EVENT", "METRIC_EVENT_BUS_NULL_LAUNCH_REQUEST", "METRIC_EVENT_BUS_PAUSE_EVENT", "METRIC_EVENT_BUS_SIGNOUT_EVENT", "METRIC_EVENT_BUS_UNEXPECTED_EVENT", "TAG", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EventBusHandlerDefault(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.context = context;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
    }

    private final void handleIdentitySignOut() {
        if (!TarazedSessionAndroidService.Companion.isSessionActive()) {
            return;
        }
        this.logger.i(TAG, "User has signed out of the app, ending session");
        this.metricsHelper.addCount(TAG, METRIC_EVENT_BUS_SIGNOUT_EVENT, 1.0d);
        Intent intent = new Intent(this.context, TarazedSessionAndroidService.class);
        intent.setAction(TarazedIntents.SessionAndroidService.END_SESSION_ON_3P_APP_EVENT);
        this.context.startService(intent);
    }

    private final void handleLaunchEvent(Message message) {
        String parseDMPSPayload = parseDMPSPayload(message);
        if (parseDMPSPayload == null) {
            this.logger.e(TAG, "Could not get launch request from DMPS payload");
            this.metricsHelper.addCountHighPriority(TAG, METRIC_EVENT_BUS_NULL_LAUNCH_REQUEST, 1.0d);
            return;
        }
        this.metricsHelper.addCount(TAG, METRIC_EVENT_BUS_LAUNCH_EVENT, 1.0d);
        Intent intent = new Intent(this.context, TarazedSessionAndroidService.class);
        intent.setAction(TarazedIntents.SessionAndroidService.START_SESSION);
        intent.putExtra(TarazedIntents.SessionAndroidService.EXTRA_LAUNCH_REQUEST, parseDMPSPayload);
        int i = Build.VERSION.SDK_INT;
        this.context.startForegroundService(intent);
    }

    private final void handlePauseEvent() {
        if (!TarazedSessionAndroidService.Companion.isSessionActive()) {
            return;
        }
        this.metricsHelper.addCount(TAG, METRIC_EVENT_BUS_PAUSE_EVENT, 1.0d);
        Intent intent = new Intent(this.context, TarazedSessionAndroidService.class);
        intent.setAction(TarazedIntents.SessionAndroidService.PAUSE_SESSION);
        this.context.startService(intent);
    }

    private final String parseDMPSPayload(Message message) {
        JSONArray jSONArray = new JSONArray(message.getPayloadAsString());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.getJSONObject(i).optJSONObject("requestContent");
            if (optJSONObject != null) {
                return optJSONObject.getString(EventBusMessagingReceiver.NotificationEvent.ORIGINAL_URI);
            }
        }
        return null;
    }

    @Override // com.amazon.alexa.tarazed.eventbus.EventBusHandler
    public void handle(@NotNull Message message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Tarazed event (");
        outline107.append(message.getEventType());
        outline107.append(") received from event bus");
        tarazedSessionLogger.i(TAG, outline107.toString());
        this.metricsHelper.addCount(TAG, METRIC_EVENT_BUS_EVENT_RECEIVED, 1.0d);
        String eventType = message.getEventType();
        if (eventType != null) {
            int hashCode = eventType.hashCode();
            if (hashCode != -1848576398) {
                if (hashCode != -1717819618) {
                    if (hashCode == -1585814857 && eventType.equals(DMPSHandlerDefault.EVENT_BUS_TARAZED_LAUNCH_EVENT_TYPE)) {
                        handleLaunchEvent(message);
                        return;
                    }
                } else if (eventType.equals(IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS)) {
                    handleIdentitySignOut();
                    return;
                }
            } else if (eventType.equals(DMPSHandlerDefault.EVENT_BUS_TARAZED_PAUSE_EVENT_TYPE)) {
                handlePauseEvent();
                return;
            }
        }
        TarazedSessionLogger tarazedSessionLogger2 = this.logger;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("unexpected type: ");
        outline1072.append(message.getEventType());
        outline1072.append(" from event bus, ignored");
        tarazedSessionLogger2.w(TAG, outline1072.toString());
        this.metricsHelper.addCount(TAG, METRIC_EVENT_BUS_UNEXPECTED_EVENT, 1.0d);
    }
}
