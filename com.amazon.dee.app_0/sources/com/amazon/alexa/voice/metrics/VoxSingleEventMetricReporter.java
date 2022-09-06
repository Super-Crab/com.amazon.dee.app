package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
/* loaded from: classes11.dex */
public class VoxSingleEventMetricReporter extends VoxMetricEventProcessor {
    private final MetricsService metricsService;

    public VoxSingleEventMetricReporter(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amazon.alexa.voice.metrics.VoxMetricEventProcessor
    public void process(VoxMetricEvent voxMetricEvent) {
        char c;
        String name = voxMetricEvent.getName();
        switch (name.hashCode()) {
            case -1903582027:
                if (name.equals("VERIFICATION_FAILED_UNKNOWN_ERROR")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1848585757:
                if (name.equals("VERIFICATION_FAILED_TIMEOUT")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1757972518:
                if (name.equals("VERIFICATION_FAILED_MISSING_PACKAGENAME")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1341407348:
                if (name.equals(VoxMetricEventName.VOX_EARCON_ENDPOINT_SOUND_DISABLED)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -184038583:
                if (name.equals(VoxMetricEventName.VOX_HANDSFREE_SETTINGS_DISABLED)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -176883276:
                if (name.equals("INTERACTION_SCHEDULER.SCHEDULE_FAILED.NOT_CONNECTED")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -137584623:
                if (name.equals(VoxMetricEventName.VOX_EARCON_ENDPOINT_SOUND_ENABLED)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 566426819:
                if (name.equals("LEADER_SELECTION_ATTEMPTED")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 875906586:
                if (name.equals("VERIFICATION_FAILED_DISABLED")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 924711071:
                if (name.equals(VoxMetricEventName.VOX_EARCON_WAKE_SOUND_ENABLED)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1306380252:
                if (name.equals("VERIFICATION_FAILED_UNKNOWN_LEADER")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1410358784:
                if (name.equals("VERIFICATION_FAILED_INCORRECT_SIGNATURE")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1524988094:
                if (name.equals(VoxMetricEventName.VOX_EARCON_WAKE_SOUND_DISABLED)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
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
            case '\t':
            case '\n':
            case 11:
            case '\f':
                Logger.verbose("VoxSingleEventMetricReporter record event " + name);
                this.metricsService.recordOccurrence(name, "vox_speech", true, null);
                return;
            default:
                return;
        }
    }
}
