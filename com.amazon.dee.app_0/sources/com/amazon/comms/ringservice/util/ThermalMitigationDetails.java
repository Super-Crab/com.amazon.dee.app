package com.amazon.comms.ringservice.util;

import com.amazon.comms.calling.service.ConstraintsModificationType;
import com.amazon.comms.log.CommsLogger;
import java.util.Map;
/* loaded from: classes12.dex */
public class ThermalMitigationDetails {
    public static final int DEFAULT_MEDIA_CONFIG = 0;
    private static final CommsLogger log = CommsLogger.getLogger(ThermalMitigationDetails.class);
    private int fpsBase;
    private int videoWidthBase;
    private final Mitigation fps = new Mitigation(false, false);
    private final Mitigation resolution = new Mitigation(false, false);
    private final Mitigation audioOnly = new Mitigation(false, false);
    private long mitigationStartTime = 0;
    private long mitigationTimeTotal = 0;

    /* renamed from: com.amazon.comms.ringservice.util.ThermalMitigationDetails$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$ConstraintsModificationType = new int[ConstraintsModificationType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$comms$calling$service$ConstraintsModificationType[ConstraintsModificationType.suspendVideoThermal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$ConstraintsModificationType[ConstraintsModificationType.maxFrameRate.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$ConstraintsModificationType[ConstraintsModificationType.maxWidth.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class Mitigation {
        private boolean active;
        private boolean applied;

        public Mitigation(boolean z, boolean z2) {
            this.active = z;
            this.applied = z2;
        }

        public boolean isActive() {
            return this.active;
        }

        public boolean isApplied() {
            return this.applied;
        }
    }

    public ThermalMitigationDetails(int i, int i2) {
        this.fpsBase = i;
        this.videoWidthBase = i2;
    }

    private void updateMitigationStatus(Mitigation mitigation, boolean z) {
        if (!isMitigationActive()) {
            this.mitigationStartTime = System.currentTimeMillis();
        }
        mitigation.active = z;
        if (z) {
            mitigation.applied = true;
        }
    }

    public void endMitigation(long j) {
        long j2 = this.mitigationStartTime;
        if (j2 != 0) {
            this.mitigationTimeTotal = (j - j2) + this.mitigationTimeTotal;
            this.mitigationStartTime = 0L;
        }
    }

    public Mitigation getAudioOnly() {
        return this.audioOnly;
    }

    public Mitigation getFps() {
        return this.fps;
    }

    public int getFpsBase() {
        return this.fpsBase;
    }

    public long getMitigationStartTime() {
        return this.mitigationStartTime;
    }

    public long getMitigationTimeTotal() {
        return this.mitigationTimeTotal;
    }

    public Mitigation getResolution() {
        return this.resolution;
    }

    public int getVideoWidthBase() {
        return this.videoWidthBase;
    }

    public boolean isMitigationActive() {
        return this.fps.active || this.resolution.active || this.audioOnly.active;
    }

    public boolean isMitigationApplied() {
        return this.fps.applied || this.resolution.applied || this.audioOnly.applied;
    }

    public void recordThermalMitigation(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ConstraintsModificationType valueOf = ConstraintsModificationType.valueOf(entry.getKey());
            int intValue = entry.getValue().intValue();
            int ordinal = valueOf.ordinal();
            if (ordinal != 0) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        log.w("Thermal Mitigation Constraints UNKNOWN. Not Handled");
                    } else if (intValue != 0) {
                        updateMitigationStatus(this.audioOnly, true);
                    } else {
                        updateMitigationStatus(this.audioOnly, false);
                    }
                } else if (intValue < this.fpsBase && intValue != 0) {
                    updateMitigationStatus(this.fps, true);
                } else {
                    updateMitigationStatus(this.fps, false);
                }
            } else if (intValue < this.videoWidthBase && intValue != 0) {
                updateMitigationStatus(this.resolution, true);
            } else {
                updateMitigationStatus(this.resolution, false);
            }
        }
        if (!isMitigationActive()) {
            endMitigation(System.currentTimeMillis());
        }
    }

    public void setFpsBase(int i) {
        this.fpsBase = i;
    }

    public void setMitigationStartTime(long j) {
        this.mitigationStartTime = j;
    }

    public void setMitigationTimeTotal(long j) {
        this.mitigationTimeTotal = j;
    }

    public void setVideoWidthBase(int i) {
        this.videoWidthBase = i;
    }
}
