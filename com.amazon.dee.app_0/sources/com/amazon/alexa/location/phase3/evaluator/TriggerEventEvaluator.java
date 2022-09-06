package com.amazon.alexa.location.phase3.evaluator;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.location.TriggeringGeofenceInfo;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.phase3.ActiveGeofences;
import com.amazon.alexa.location.phase3.DwellTracker;
import com.amazon.alexa.location.phase3.sensor.motiondetection.MotionEvent;
import com.amazon.alexa.location.phase3.sensor.osgeofence.OsGeofenceTriggerEvent;
import com.amazon.alexa.location.phase3.sensor.wifidetection.WifiDetectionEvent;
import com.amazon.alexa.location.utils.Clock;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes9.dex */
public class TriggerEventEvaluator {
    private static final String LOG_TAG = "TriggerEventEvaluator";
    @NonNull
    private final ActiveGeofences activeFences;
    @NonNull
    private final Clock clock;
    @NonNull
    private final DwellTracker dwellTracker;

    /* renamed from: com.amazon.alexa.location.phase3.evaluator.TriggerEventEvaluator$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$location$phase3$sensor$wifidetection$WifiDetectionEvent$TransitionType = new int[WifiDetectionEvent.TransitionType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$location$phase3$sensor$wifidetection$WifiDetectionEvent$TransitionType[WifiDetectionEvent.TransitionType.ARRIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$phase3$sensor$wifidetection$WifiDetectionEvent$TransitionType[WifiDetectionEvent.TransitionType.LEAVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static final class EvaluatedTrigger {
        public final long createTime;
        @NonNull
        public final String fenceId;
        public final int transitionType;
        public boolean hadOsTrigger = false;
        public boolean hadRelevantWifiStateChange = false;
        public boolean hadRelevantActivityStateChange = false;
        public boolean hadCommonDwellingLocation = false;

        EvaluatedTrigger(@NonNull String str, int i, long j) {
            this.fenceId = str;
            this.createTime = j;
            this.transitionType = i;
        }

        @Nullable
        static EvaluatedTrigger fromTriggerEvent(@NonNull GeofenceTriggerEvent geofenceTriggerEvent, long j) {
            int i = geofenceTriggerEvent.transitionType;
            if (i == 0 || i == 1) {
                return new EvaluatedTrigger(geofenceTriggerEvent.geofenceId, geofenceTriggerEvent.transitionType, j);
            }
            return null;
        }

        public static String generateKey(@NonNull String str, int i) {
            return String.format(Locale.US, "%s_%d", str, Integer.valueOf(i));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public TriggeringGeofenceInfo toTriggeringInfo() {
            int i = this.transitionType;
            int i2 = 2;
            if (i == 0) {
                i2 = 1;
            } else if (i != 1) {
                if (i != 2) {
                    Log.w(TriggerEventEvaluator.LOG_TAG, "Transition type of an evaluated trigger should be either ENTER, EXIT or DWELL");
                    return null;
                }
                i2 = 4;
            }
            return new TriggeringGeofenceInfo(this.fenceId, i2, this.createTime);
        }
    }

    public TriggerEventEvaluator(@NonNull DwellTracker dwellTracker, @NonNull ActiveGeofences activeGeofences, @NonNull Clock clock) {
        this.dwellTracker = dwellTracker;
        this.activeFences = activeGeofences;
        this.clock = clock;
    }

    public Map<String, EvaluatedTrigger> evaluate(LocationEventQueue locationEventQueue) {
        char c;
        boolean z;
        char c2;
        HashMap hashMap = new HashMap();
        char c3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        for (LocationEvent locationEvent : locationEventQueue.getAllEvents()) {
            if (locationEvent instanceof GeofenceTriggerEvent) {
                GeofenceTriggerEvent geofenceTriggerEvent = (GeofenceTriggerEvent) locationEvent;
                String generateKey = EvaluatedTrigger.generateKey(geofenceTriggerEvent.geofenceId, geofenceTriggerEvent.transitionType);
                EvaluatedTrigger evaluatedTrigger = (EvaluatedTrigger) hashMap.get(generateKey);
                if (evaluatedTrigger == null) {
                    ALSGeofence aLSGeofence = this.activeFences.get(geofenceTriggerEvent.geofenceId);
                    if (aLSGeofence == null) {
                        Locale locale = Locale.US;
                        Object[] objArr = new Object[1];
                        objArr[c3 == 1 ? 1 : 0] = geofenceTriggerEvent.geofenceId;
                        Log.w(LOG_TAG, String.format(locale, "Trigger event received for inactive fence %s; ignoring", objArr));
                        c2 = c3;
                        z = z2;
                    } else {
                        z = z2;
                        EvaluatedTrigger fromTriggerEvent = EvaluatedTrigger.fromTriggerEvent(geofenceTriggerEvent, this.clock.millis());
                        if (fromTriggerEvent == null) {
                            c2 = 0;
                            Log.w(LOG_TAG, String.format(Locale.US, "Trigger event for fence %s had invalid transition type %d; ignoring", geofenceTriggerEvent.geofenceId, Integer.valueOf(geofenceTriggerEvent.transitionType)));
                        } else {
                            c = 0;
                            fromTriggerEvent.hadCommonDwellingLocation = this.dwellTracker.hasDwelledEnoughIn(aLSGeofence);
                            evaluatedTrigger = fromTriggerEvent;
                        }
                    }
                    c3 = c2;
                    z2 = z;
                } else {
                    c = c3;
                    z = z2;
                }
                if (geofenceTriggerEvent instanceof OsGeofenceTriggerEvent) {
                    evaluatedTrigger.hadOsTrigger = true;
                }
                hashMap.put(generateKey, evaluatedTrigger);
            } else {
                c = c3;
                z = z2;
                if (locationEvent instanceof WifiDetectionEvent) {
                    int ordinal = ((WifiDetectionEvent) locationEvent).transitionType.ordinal();
                    if (ordinal != 0) {
                        boolean z8 = true;
                        if (ordinal != 1) {
                            z8 = z7;
                        }
                        z7 = z8;
                    } else {
                        z6 = true;
                    }
                } else if (locationEvent instanceof MotionEvent) {
                    int i = ((MotionEvent) locationEvent).state;
                    if (i == 1) {
                        z2 = true;
                    } else if (i == 2) {
                        z2 = z;
                        z4 = true;
                    } else if (i == 3) {
                        z2 = z;
                        z3 = true;
                    } else if (i == 4) {
                        z2 = z;
                        z5 = true;
                    }
                    c3 = c;
                }
            }
            z2 = z;
            c3 = c;
        }
        boolean z9 = c3 == 1 ? 1 : 0;
        boolean z10 = z2;
        for (EvaluatedTrigger evaluatedTrigger2 : hashMap.values()) {
            if (!evaluatedTrigger2.hadOsTrigger) {
                int i2 = evaluatedTrigger2.transitionType;
                if (i2 == 0) {
                    evaluatedTrigger2.hadRelevantActivityStateChange = (z10 || z4) ? true : z9;
                    evaluatedTrigger2.hadRelevantWifiStateChange = z6;
                } else if (i2 == 1) {
                    evaluatedTrigger2.hadRelevantActivityStateChange = (z3 || z5) ? true : z9;
                    evaluatedTrigger2.hadRelevantWifiStateChange = z7;
                }
            }
        }
        return hashMap;
    }

    public boolean needMoreData(EvaluatedTrigger evaluatedTrigger) {
        return !shouldFire(evaluatedTrigger) && !evaluatedTrigger.hadRelevantActivityStateChange;
    }

    public boolean shouldFire(EvaluatedTrigger evaluatedTrigger) {
        if (evaluatedTrigger.hadOsTrigger) {
            return true;
        }
        if (!evaluatedTrigger.hadRelevantActivityStateChange) {
            return false;
        }
        return evaluatedTrigger.hadRelevantWifiStateChange || evaluatedTrigger.hadCommonDwellingLocation;
    }
}
