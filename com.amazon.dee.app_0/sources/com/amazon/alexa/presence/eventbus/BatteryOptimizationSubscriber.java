package com.amazon.alexa.presence.eventbus;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.battery.BatteryOptimization;
import com.amazon.alexa.presence.utils.EventBusUtil;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsServiceV2;
import java.util.UUID;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class BatteryOptimizationSubscriber implements Subscriber {
    @VisibleForTesting
    static final String EVENT_ACTION_IS_BATTERY_OPTIMIZED = "isbatteryoptimized";
    @VisibleForTesting
    static final String EVENT_ACTION_REQUEST_BATTERY_OPTIMIZATION = "requestbatteryoptimization";
    @VisibleForTesting
    public static final String EVENT_FILTER = "presenceBattery::request";
    @VisibleForTesting
    static final String PUBLISHER_EVENT_TYPE = "presenceBattery::response";
    private static final String TAG = Presence.tag();
    private final BatteryOptimization batteryOptimization;
    private final EventBus eventBus;
    private final EventMessageFilter eventMessageFilter;
    private final MetricsServiceV2 metricsServiceV2;
    private final UUID subscriberId = UUID.randomUUID();

    @Inject
    public BatteryOptimizationSubscriber(EventBus eventBus, BatteryOptimization batteryOptimization, EventMessageFilter eventMessageFilter, MetricsServiceV2 metricsServiceV2) {
        this.eventBus = eventBus;
        this.batteryOptimization = batteryOptimization;
        this.eventMessageFilter = eventMessageFilter;
        this.metricsServiceV2 = metricsServiceV2;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public UUID getUUID() {
        return this.subscriberId;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull Message message) {
        char c;
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline158("On battery optimization message received ", payloadAsString);
        MetricsUtil.recordZeroCount(this.metricsServiceV2, MetricsUtil.MetricsId.CHECK_BATTERY_OPTIMIZATION_MESSAGE, MetricsUtil.Method.BATTERY_OPTIMIZATION_SUBSCRIBER_MESSAGE_RECEIVED);
        MetricsUtil.recordZeroCount(this.metricsServiceV2, MetricsUtil.MetricsId.REQUEST_BATTERY_OPTIMIZATION_DISABLE_MESSAGE, MetricsUtil.Method.BATTERY_OPTIMIZATION_SUBSCRIBER_MESSAGE_RECEIVED);
        MetricsUtil.recordZeroCount(this.metricsServiceV2, MetricsUtil.MetricsId.INVALID_MESSAGE, MetricsUtil.Method.BATTERY_OPTIMIZATION_SUBSCRIBER_MESSAGE_RECEIVED);
        int hashCode = payloadAsString.hashCode();
        if (hashCode != -377335068) {
            if (hashCode == 1279992075 && payloadAsString.equals(EVENT_ACTION_REQUEST_BATTERY_OPTIMIZATION)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (payloadAsString.equals(EVENT_ACTION_IS_BATTERY_OPTIMIZED)) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            Log.i(TAG, "Check battery optimization action event observed.");
            MetricsUtil.recordCount(this.metricsServiceV2, MetricsUtil.MetricsId.CHECK_BATTERY_OPTIMIZATION_MESSAGE, MetricsUtil.Method.BATTERY_OPTIMIZATION_SUBSCRIBER_MESSAGE_RECEIVED);
            EventBusUtil.publishToEventBus(this.eventBus, new Message.Builder().setEventType(PUBLISHER_EVENT_TYPE).setPayload(String.valueOf(this.batteryOptimization.isBatteryOptimized())).build());
        } else if (c != 1) {
            GeneratedOutlineSupport1.outline158("No-op due to invalid action. Payload : ", payloadAsString);
            MetricsUtil.recordCount(this.metricsServiceV2, MetricsUtil.MetricsId.INVALID_MESSAGE, MetricsUtil.Method.BATTERY_OPTIMIZATION_SUBSCRIBER_MESSAGE_RECEIVED);
        } else {
            Log.i(TAG, "Request battery optimization action event observed.");
            MetricsUtil.recordCount(this.metricsServiceV2, MetricsUtil.MetricsId.REQUEST_BATTERY_OPTIMIZATION_DISABLE_MESSAGE, MetricsUtil.Method.BATTERY_OPTIMIZATION_SUBSCRIBER_MESSAGE_RECEIVED);
            this.batteryOptimization.requestDisable();
        }
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull Message message) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Supports battery optimization event message ");
        outline107.append(message.getPayloadAsString());
        outline107.toString();
        return this.eventMessageFilter.isMatch(message);
    }
}
