package com.amazon.latencyinfra;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.latencyinfra.SingleEventInputArgument;
/* loaded from: classes12.dex */
public enum LatencyMarker {
    COMMS_CSL("comms.end.cold_start.time"),
    PROFILE_OOBE_START_CSL("profileOOBE.start.cold_start.time"),
    PROFILE_OOBE_END_CSL("profileOOBE.end.cold_start.time"),
    ACCESSORIES_CSL("accessories.end.cold_start.time"),
    MAIN_APPLICATION_CSL("mainApplication.end.cold_start.time"),
    MAIN_ACTIVITY_COMPONENT_INJECT_CSL("mainActivityComponentInject.end.cold_start.time"),
    MAIN_ACTIVITY_CREATE_CSL("mainActivityCreate.end.cold_start.time"),
    MAIN_ACTIVITY_RESUME_CSL("mainActivityResume.end.cold_start.time"),
    HOME_VIEW_CREATE_CSL("homeViewCreate.end.cold_start.time"),
    VOX_INGRESS_CSL("voxIngress.end.cold_start.time"),
    APP_INSTALL_LATENCY("app.install_start.time"),
    APP_UPDATE_LATENCY("app.update_start.time"),
    COOL_START_LATENCY("app.cool_start.time"),
    MOSAIC_THEME_INIT_CSL_START("mosaic.theme_init.start.cold_start.time"),
    MOSAIC_THEME_INIT_CSL_END("mosaic.theme_init.end.cold_start.time"),
    GENERIC_CSL_COMPLETION("generic.cold_start.time");
    
    private final String metric;

    LatencyMarker(@NonNull String str) {
        if (!TextUtils.isEmpty(str)) {
            this.metric = str;
            return;
        }
        throw new IllegalArgumentException("Marker must have a valid metric");
    }

    public SingleEventInputArgument getEvent(String str) {
        return new SingleEventInputArgument.Builder().withMetricsOption(true).withLogOption(true).withCustomerOption(true).withNamespace(str).withEventName(this.metric).build();
    }

    public String getMetric() {
        return this.metric;
    }
}
