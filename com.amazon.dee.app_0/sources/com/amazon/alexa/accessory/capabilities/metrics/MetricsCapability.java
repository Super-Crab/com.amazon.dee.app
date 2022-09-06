package com.amazon.alexa.accessory.capabilities.metrics;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Metrics;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes.dex */
public final class MetricsCapability extends AccessoryCapability {
    private final PushMetricsListener pushMetricsListener;
    private ControlStream stream;

    /* loaded from: classes.dex */
    public interface PushMetricsListener {
        void onMetricsMapUpdated(Map<Integer, Metrics.MetricDescriptor> map);

        void onMetricsPushed(Collection<Metrics.MetricsEvent> collection);
    }

    public MetricsCapability(PushMetricsListener pushMetricsListener) {
        this.pushMetricsListener = pushMetricsListener;
    }

    private ControlMessageHandler<Metrics.PushMetrics> getPushMetricsHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.metrics.-$$Lambda$MetricsCapability$Wt_wkirt80_3mcQY31bdilAHvTw
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                MetricsCapability.this.lambda$getPushMetricsHandler$0$MetricsCapability(controlStream, command, (Metrics.PushMetrics) obj);
            }
        };
    }

    private ControlMessageHandler<Metrics.UpdateMetricsMap> getUpdateMetricsMapHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.metrics.-$$Lambda$MetricsCapability$ubNE3aXp-7NMsh4AhYz2B1UAkJw
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                MetricsCapability.this.lambda$getUpdateMetricsMapHandler$1$MetricsCapability(controlStream, command, (Metrics.UpdateMetricsMap) obj);
            }
        };
    }

    public /* synthetic */ void lambda$getPushMetricsHandler$0$MetricsCapability(ControlStream controlStream, Accessories.Command command, Metrics.PushMetrics pushMetrics) throws Exception {
        this.pushMetricsListener.onMetricsPushed(pushMetrics.getEventsList());
        controlStream.respond(Accessories.Command.PUSH_METRICS, Common.ErrorCode.SUCCESS);
    }

    public /* synthetic */ void lambda$getUpdateMetricsMapHandler$1$MetricsCapability(ControlStream controlStream, Accessories.Command command, Metrics.UpdateMetricsMap updateMetricsMap) throws Exception {
        this.pushMetricsListener.onMetricsMapUpdated(updateMetricsMap.getMapMap());
        controlStream.respond(Accessories.Command.UPDATE_METRICS_MAP, Common.ErrorCode.SUCCESS);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.PUSH_METRICS, getPushMetricsHandler());
        this.stream.addMessageHandler(Accessories.Command.UPDATE_METRICS_MAP, getUpdateMetricsMapHandler());
        accessoryDescriptor.add(this.stream);
    }
}
