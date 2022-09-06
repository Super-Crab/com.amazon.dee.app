package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.routing.RouteToVoiceAction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class VoxScrimShownLatencyMetricsReporter extends VoxMetricEventProcessor {
    private static final String INGRESS_TO_SCRIM = "VOX_INGRESS_TO_SCRIM_VISIBLE_LATENCY";
    static final Map<String, VoiceService.InvocationType> LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP = new HashMap();
    private static final String VOICE_ROUTE_NO_TIMESTAMP = "VOICE_ROUTE:NO_TIMESTAMP:";
    private VoiceService.InvocationType ingressMethod;
    private MetricsService metricsService;
    private long ingressOccurTime = 0;
    private String routeReferer = "UNKNOWN";

    static {
        LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP.put(VoxMetricEventName.INGRESS_IN_APP_WAKEWORD_OCCUR, VoiceService.InvocationType.WAKE_WORD);
        LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP.put(VoxLaunchConstants.VOX_LAUNCH_SHORTCUT, VoiceService.InvocationType.SHORTCUT);
        LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP.put(VoxLaunchConstants.VOX_LAUNCH_QUICK_ACTIONS_WIDGET, VoiceService.InvocationType.QUICK_ACTIONS_WIDGET);
        LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP.put(VoxLaunchConstants.VOX_LAUNCH_DEVICE_ASSIST, VoiceService.InvocationType.DEVICE_ASSISTANT);
        LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP.put(VoxLaunchConstants.VOX_LAUNCH_INGRESS_BUTTON, VoiceService.InvocationType.APP_INGRESS);
        LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP.put(VoxLaunchConstants.VOX_LAUNCH_PUSHBUTTON, VoiceService.InvocationType.PUSH_BUTTON);
        LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP.put(VoxLaunchConstants.VOX_LAUNCH_LISTEN_INTENT, VoiceService.InvocationType.INTENT);
        LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP.put("UNKNOWN", VoiceService.InvocationType.UNKNOWN);
    }

    public VoxScrimShownLatencyMetricsReporter(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    private void abortIngress(VoxMetricEvent voxMetricEvent) {
        this.ingressOccurTime = 0L;
        this.ingressMethod = null;
    }

    private void recordDone(VoxMetricEvent voxMetricEvent) {
        if (voxMetricEvent.getTimestamp() == 0) {
            abortIngress(voxMetricEvent);
            return;
        }
        if (this.ingressMethod != null) {
            long timestamp = voxMetricEvent.getTimestamp();
            long j = this.ingressOccurTime;
            long j2 = timestamp - j;
            VoiceService.InvocationType invocationType = VoiceService.InvocationType.ROUTE;
            VoiceService.InvocationType invocationType2 = this.ingressMethod;
            if (invocationType == invocationType2) {
                if (j > 0) {
                    VoxMetricEventProcessor.recordLatency(this.metricsService, VoiceService.InvocationType.ROUTE.getName() + "." + this.routeReferer, VoiceMetricsConstants.VOX_TAP_TO_SCRIM, j2);
                } else {
                    MetricsService metricsService = this.metricsService;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(VOICE_ROUTE_NO_TIMESTAMP);
                    outline107.append(this.routeReferer);
                    metricsService.recordOccurrence(outline107.toString(), "vox_speech", true, null);
                }
            } else if (VoiceService.InvocationType.WAKE_WORD == invocationType2) {
                VoxMetricEventProcessor.recordLatency(this.metricsService, "VOX_INGRESS_TO_SCRIM_VISIBLE_LATENCY:WAKEWORD", j2);
            } else {
                VoxMetricEventProcessor.recordLatency(this.metricsService, invocationType2.getName(), VoiceMetricsConstants.VOX_TAP_TO_SCRIM, j2);
            }
        }
        this.ingressMethod = null;
    }

    private void recordIngress(VoxMetricEvent voxMetricEvent) {
        this.ingressOccurTime = voxMetricEvent.getTimestamp();
        this.ingressMethod = LAUNCH_TYPE_TO_INVOCATION_TYPE_MAP.get(voxMetricEvent.getName());
        if (this.ingressMethod == null) {
            this.ingressMethod = VoiceService.InvocationType.UNKNOWN;
        }
    }

    private void recordIngressForRoute(VoxMetricEvent voxMetricEvent) {
        this.ingressOccurTime = voxMetricEvent.getTimestamp();
        this.routeReferer = RouteToVoiceAction.getRefererFromMetricEventName(voxMetricEvent.getName());
        this.ingressMethod = VoiceService.InvocationType.ROUTE;
    }

    @Override // com.amazon.alexa.voice.metrics.VoxMetricEventProcessor
    public void process(VoxMetricEvent voxMetricEvent) {
        String name = voxMetricEvent.getName();
        if (RouteToVoiceAction.isLaunchedFromRoute(name)) {
            recordIngressForRoute(voxMetricEvent);
            return;
        }
        char c = 65535;
        switch (name.hashCode()) {
            case -1946147886:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_SHORTCUT)) {
                    c = 4;
                    break;
                }
                break;
            case -1685100536:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_LISTEN_INTENT)) {
                    c = 1;
                    break;
                }
                break;
            case -1477432488:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_PUSHBUTTON)) {
                    c = 3;
                    break;
                }
                break;
            case -648440791:
                if (name.equals(VoxMetricEventName.INGRESS_IN_APP_WAKEWORD_OCCUR)) {
                    c = 0;
                    break;
                }
                break;
            case -493900776:
                if (name.equals(VoxMetricEventName.SCRIM_SHOWN)) {
                    c = '\t';
                    break;
                }
                break;
            case -169016401:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_SETTINGS)) {
                    c = 7;
                    break;
                }
                break;
            case 433141802:
                if (name.equals("UNKNOWN")) {
                    c = '\b';
                    break;
                }
                break;
            case 445445599:
                if (name.equals(VoxMetricEventName.REQUEST_ABORTED)) {
                    c = '\n';
                    break;
                }
                break;
            case 647775625:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_INGRESS_BUTTON)) {
                    c = 6;
                    break;
                }
                break;
            case 1196123684:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_QUICK_ACTIONS_WIDGET)) {
                    c = 5;
                    break;
                }
                break;
            case 1801630822:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_DEVICE_ASSIST)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
                recordIngress(voxMetricEvent);
                return;
            case '\t':
                recordDone(voxMetricEvent);
                return;
            case '\n':
                abortIngress(voxMetricEvent);
                return;
            default:
                return;
        }
    }
}
