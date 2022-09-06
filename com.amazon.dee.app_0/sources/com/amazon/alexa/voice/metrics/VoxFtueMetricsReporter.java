package com.amazon.alexa.voice.metrics;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.routing.RouteToVoiceAction;
import com.amazon.alexa.voice.ui.onedesign.ftue.metrics.VoxUiMetricEventName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class VoxFtueMetricsReporter extends VoxMetricEventProcessor {
    @VisibleForTesting
    static final String ACCEPTED = "ACCEPTED";
    @VisibleForTesting
    static final String FTUE_PREFIX = "FTUE_";
    @VisibleForTesting
    static final String HANDSFREE = "HANDSFREE";
    @VisibleForTesting
    static final String LANGUAGE_PICKER = "LANGUAGE_PICKER";
    @VisibleForTesting
    static final String LEGACY = "LEGACY";
    @VisibleForTesting
    static final Map<String, String> PRIMER_TYPE_METHOD_MAP = new HashMap();
    @VisibleForTesting
    static final String REJECTED = "REJECTED";
    @VisibleForTesting
    static final String RETURNING_USER = "RETURNING_USER";
    @VisibleForTesting
    static final String VOX_PREFIX = "VOX_";
    @VisibleForTesting
    static final String VOX_PRIMER = "VOX_PRIMER";
    @VisibleForTesting
    static final String WAKEWORD_PREFIX = "WAKEWORD_";
    private final MetricsService metricsService;
    private String launchType = "UNKNOWN";
    private String primerType = "UNKNOWN";
    private boolean isLearnMoreVisited = false;

    static {
        PRIMER_TYPE_METHOD_MAP.put(VoxUiMetricEventName.HANDSFREE_FTUE_SHOWN, HANDSFREE);
        PRIMER_TYPE_METHOD_MAP.put(VoxUiMetricEventName.LEGACY_FTUE_SHOWN, LEGACY);
        PRIMER_TYPE_METHOD_MAP.put(VoxUiMetricEventName.RETURNING_USER_PRIMER_SHOWN, RETURNING_USER);
        PRIMER_TYPE_METHOD_MAP.put(VoxUiMetricEventName.FTUE_LANGUAGE_PICKER_SHOWN, LANGUAGE_PICKER);
    }

    public VoxFtueMetricsReporter(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    private void primerPageShown(VoxMetricEvent voxMetricEvent) {
        String name = voxMetricEvent.getName();
        if (PRIMER_TYPE_METHOD_MAP.containsKey(name)) {
            this.primerType = PRIMER_TYPE_METHOD_MAP.get(name);
        }
        MetricsService metricsService = this.metricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VOX_PRIMER:");
        outline107.append(this.primerType);
        VoxMetricEventProcessor.recordEventMetric(metricsService, outline107.toString());
    }

    private void recordLaunchType(VoxMetricEvent voxMetricEvent) {
        String name = voxMetricEvent.getName();
        if (VoxLaunchConstants.LAUNCH_TYPE_METHOD_MAP.containsKey(name)) {
            this.launchType = VoxLaunchConstants.LAUNCH_TYPE_METHOD_MAP.get(name);
        }
    }

    private void recordPrimerActionAfterLearnMoreClicked(VoxMetricEvent voxMetricEvent) {
        if (HANDSFREE.equals(this.primerType) && this.isLearnMoreVisited) {
            String name = voxMetricEvent.getName();
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116(WAKEWORD_PREFIX, FTUE_PREFIX, VoxUiMetricEventName.LEARNMORE_VISITED, ":", FTUE_PREFIX);
            outline116.append(VoxUiMetricEventName.PRIMER_ALLOWED.equals(name) ? ACCEPTED : REJECTED);
            VoxMetricEventProcessor.recordEventMetric(this.metricsService, outline116.toString());
        }
        this.isLearnMoreVisited = false;
    }

    private void recordRouteLaunchType() {
        this.launchType = VoxLaunchConstants.ROUTE;
    }

    @Override // com.amazon.alexa.voice.metrics.VoxMetricEventProcessor
    public void process(VoxMetricEvent voxMetricEvent) {
        String name = voxMetricEvent.getName();
        if (RouteToVoiceAction.isLaunchedFromRoute(name)) {
            recordRouteLaunchType();
            return;
        }
        char c = 65535;
        switch (name.hashCode()) {
            case -1946147886:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_SHORTCUT)) {
                    c = 1;
                    break;
                }
                break;
            case -1477432488:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_PUSHBUTTON)) {
                    c = 5;
                    break;
                }
                break;
            case -1359127798:
                if (name.equals(VoxMetricEventName.FTUE_COMPLETED)) {
                    c = '\r';
                    break;
                }
                break;
            case -1058626292:
                if (name.equals(VoxUiMetricEventName.RETURNING_USER_PRIMER_SHOWN)) {
                    c = '\t';
                    break;
                }
                break;
            case -864468196:
                if (name.equals(VoxUiMetricEventName.PRIMER_ALLOWED)) {
                    c = 11;
                    break;
                }
                break;
            case -527256508:
                if (name.equals(VoxUiMetricEventName.LEARNMORE_VISITED)) {
                    c = 14;
                    break;
                }
                break;
            case -169016401:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_SETTINGS)) {
                    c = 0;
                    break;
                }
                break;
            case 647775625:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_INGRESS_BUTTON)) {
                    c = 4;
                    break;
                }
                break;
            case 854154943:
                if (name.equals(VoxUiMetricEventName.HANDSFREE_FTUE_SHOWN)) {
                    c = 7;
                    break;
                }
                break;
            case 922750854:
                if (name.equals(VoxUiMetricEventName.LEGACY_FTUE_SHOWN)) {
                    c = '\b';
                    break;
                }
                break;
            case 1159968647:
                if (name.equals(VoxUiMetricEventName.PRIMER_DENIED)) {
                    c = '\f';
                    break;
                }
                break;
            case 1196123684:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_QUICK_ACTIONS_WIDGET)) {
                    c = 2;
                    break;
                }
                break;
            case 1320733952:
                if (name.equals(VoxMetricEventName.FTUE_STARTED)) {
                    c = 6;
                    break;
                }
                break;
            case 1721359846:
                if (name.equals(VoxUiMetricEventName.FTUE_LANGUAGE_PICKER_SHOWN)) {
                    c = '\n';
                    break;
                }
                break;
            case 1801630822:
                if (name.equals(VoxLaunchConstants.VOX_LAUNCH_DEVICE_ASSIST)) {
                    c = 3;
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
                recordLaunchType(voxMetricEvent);
                return;
            case 6:
                MetricsService metricsService = this.metricsService;
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115(VOX_PREFIX, name, ":");
                outline115.append(this.launchType);
                VoxMetricEventProcessor.recordEventMetric(metricsService, outline115.toString());
                return;
            case 7:
            case '\b':
            case '\t':
            case '\n':
                primerPageShown(voxMetricEvent);
                return;
            case 11:
            case '\f':
                MetricsService metricsService2 = this.metricsService;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VOX_PRIMER:");
                outline107.append(this.primerType);
                outline107.append(":");
                outline107.append(voxMetricEvent.getName());
                VoxMetricEventProcessor.recordEventMetric(metricsService2, outline107.toString());
                recordPrimerActionAfterLearnMoreClicked(voxMetricEvent);
                return;
            case '\r':
                MetricsService metricsService3 = this.metricsService;
                StringBuilder outline1152 = GeneratedOutlineSupport1.outline115(VOX_PREFIX, name, ":");
                outline1152.append(this.launchType);
                VoxMetricEventProcessor.recordEventMetric(metricsService3, outline1152.toString());
                return;
            case 14:
                this.isLearnMoreVisited = true;
                return;
            default:
                return;
        }
    }
}
