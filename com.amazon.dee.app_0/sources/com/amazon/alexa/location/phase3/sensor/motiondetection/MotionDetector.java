package com.amazon.alexa.location.phase3.sensor.motiondetection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.location.phase3.LocationDataStore;
import com.amazon.alexa.location.phase3.LocationDataStoreService;
import com.amazon.alexa.location.phase3.sensor.activity.ActivitySensorInput;
import com.amazon.alexa.location.phase3.sensor.nativelocation.NativeLocationInput;
import com.amazon.alexa.location.utils.Clock;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
/* loaded from: classes9.dex */
public class MotionDetector {
    @VisibleForTesting
    static final double GRANULAR_ACTIVTY_DRIVING = 4.0d;
    @VisibleForTesting
    static final double GRANULAR_ACTIVTY_RUNNING = 3.0d;
    @VisibleForTesting
    static final double GRANULAR_ACTIVTY_STOPPED = 1.0d;
    @VisibleForTesting
    static final double GRANULAR_ACTIVTY_UNKNOWN = 0.0d;
    @VisibleForTesting
    static final double GRANULAR_ACTIVTY_WALKING = 2.0d;
    private static final String LAST_STATE_KEY = "lastState";
    private static final String TAG = "MotionDetector";
    private final Clock clock;
    private final LazyComponent<CrashReporter> crashReporter;
    @NonNull
    private final LocationDataStore<State> lastStateStore;
    @NonNull
    @VisibleForTesting
    State state = new State();

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes9.dex */
    public static class State {
        @Nullable
        ActivitySensorInput activityInput;
        long evaluationTime;
        @Nullable
        NativeLocationInput locationInput;
        double granularActivity = 0.0d;
        int motionState = 0;

        State() {
        }
    }

    public MotionDetector(@NonNull LocationDataStoreService locationDataStoreService, @NonNull LazyComponent<CrashReporter> lazyComponent, Clock clock) {
        this.lastStateStore = locationDataStoreService.getDataStore(LocationDataStoreService.LAST_MOTION_STATUS_TABLE, State.class);
        this.clock = clock;
        this.crashReporter = lazyComponent;
    }

    static /* synthetic */ void lambda$null$1() throws Throwable {
    }

    private static int mapDetectedActivityToConfident(@Nullable ActivitySensorInput activitySensorInput) {
        int i;
        if (activitySensorInput == null || (i = activitySensorInput.activityType) == 4 || i == 5) {
            return 0;
        }
        return activitySensorInput.confidence;
    }

    private static double mapDetectedActivityToGranularActivity(@Nullable ActivitySensorInput activitySensorInput) {
        if (activitySensorInput == null) {
            return 0.0d;
        }
        int i = activitySensorInput.activityType;
        if (i == 0 || i == 1) {
            return GRANULAR_ACTIVTY_DRIVING;
        }
        if (i == 2) {
            return 2.5d;
        }
        if (i == 3) {
            return 1.0d;
        }
        if (i == 7) {
            return 2.0d;
        }
        if (i == 8) {
            return GRANULAR_ACTIVTY_RUNNING;
        }
        return 0.0d;
    }

    private static double mapSpeedToGranulaActivity(float f) {
        return f > 5.0f ? GRANULAR_ACTIVTY_DRIVING : f > 2.0f ? GRANULAR_ACTIVTY_RUNNING : ((double) f) > 0.5d ? 2.0d : 1.0d;
    }

    public void clearData() {
        this.lastStateStore.removeAll();
    }

    @VisibleForTesting
    Observable<MotionEvent> estimateMotionEvent() {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.location.phase3.sensor.motiondetection.-$$Lambda$MotionDetector$sDH7zSWBrXU5-R6e_3Z8Q8kl684
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                MotionDetector.this.lambda$estimateMotionEvent$3$MotionDetector(observableEmitter);
            }
        });
    }

    public Observable<MotionEvent> evaluateActivityInput(@NonNull ActivitySensorInput activitySensorInput) {
        this.state.activityInput = activitySensorInput;
        return estimateMotionEvent();
    }

    public Observable<MotionEvent> evaluateLocationInput(@NonNull NativeLocationInput nativeLocationInput) {
        this.state.locationInput = nativeLocationInput;
        return estimateMotionEvent();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0049, code lost:
        if (r6 >= com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector.GRANULAR_ACTIVTY_RUNNING) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0068, code lost:
        if (r4 != 4) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0073, code lost:
        if (r6 >= com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector.GRANULAR_ACTIVTY_RUNNING) goto L8;
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$estimateMotionEvent$3$MotionDetector(io.reactivex.rxjava3.core.ObservableEmitter r17) throws java.lang.Throwable {
        /*
            r16 = this;
            r0 = r16
            com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector$State r1 = r0.state
            com.amazon.alexa.location.phase3.sensor.nativelocation.NativeLocationInput r2 = r1.locationInput
            if (r2 == 0) goto L8c
            com.amazon.alexa.location.phase3.sensor.activity.ActivitySensorInput r1 = r1.activityInput
            int r1 = mapDetectedActivityToConfident(r1)
            com.amazon.alexa.location.utils.Clock r2 = r0.clock
            long r2 = r2.millis()
            com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector$State r4 = r0.state
            com.amazon.alexa.location.phase3.sensor.activity.ActivitySensorInput r4 = r4.activityInput
            double r4 = mapDetectedActivityToGranularActivity(r4)
            double r6 = (double) r1
            double r4 = r4 * r6
            com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector$State r6 = r0.state
            com.amazon.alexa.location.phase3.sensor.nativelocation.NativeLocationInput r6 = r6.locationInput
            float r6 = r6.speed
            double r6 = mapSpeedToGranulaActivity(r6)
            int r1 = 100 - r1
            double r8 = (double) r1
            double r6 = r6 * r8
            double r6 = r6 + r4
            r4 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r6 = r6 / r4
            com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector$State r1 = r0.state
            int r4 = r1.motionState
            double r8 = r1.granularActivity
            r10 = 4611686018427387904(0x4000000000000000, double:2.0)
            int r1 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            r12 = 3
            r13 = 2
            r14 = 4613937818241073152(0x4008000000000000, double:3.0)
            r5 = 4
            if (r1 >= 0) goto L4c
            int r1 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r1 > 0) goto L47
        L45:
            r5 = r13
            goto L77
        L47:
            int r1 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r1 < 0) goto L76
            goto L77
        L4c:
            int r1 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r1 >= 0) goto L62
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 > 0) goto L57
            goto L45
        L57:
            int r1 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r1 < 0) goto L76
            if (r4 == r12) goto L77
            if (r4 != r5) goto L60
            goto L77
        L60:
            r5 = r12
            goto L77
        L62:
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 > 0) goto L6b
            if (r4 == r12) goto L6f
            if (r4 != r5) goto L45
            goto L6f
        L6b:
            int r1 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r1 > 0) goto L71
        L6f:
            r5 = 1
            goto L77
        L71:
            int r1 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r1 < 0) goto L76
            goto L77
        L76:
            r5 = r4
        L77:
            com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector$State r1 = r0.state
            int r1 = r1.motionState
            if (r1 == r5) goto L8c
            com.amazon.alexa.location.phase3.sensor.motiondetection.MotionEvent r1 = new com.amazon.alexa.location.phase3.sensor.motiondetection.MotionEvent
            r1.<init>(r5, r2)
            r2 = r17
            r2.onNext(r1)
            com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector$State r1 = r0.state
            r1.motionState = r5
            goto L8e
        L8c:
            r2 = r17
        L8e:
            com.amazon.alexa.location.phase3.LocationDataStore<com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector$State> r1 = r0.lastStateStore
            com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector$State r3 = r0.state
            java.lang.String r4 = "lastState"
            io.reactivex.rxjava3.core.Completable r1 = r1.storeValueAsRx(r4, r3)
            com.amazon.alexa.location.phase3.sensor.motiondetection.-$$Lambda$MotionDetector$LAicGIbVlbneF6PQXUT5mAC6BvE r3 = com.amazon.alexa.location.phase3.sensor.motiondetection.$$Lambda$MotionDetector$LAicGIbVlbneF6PQXUT5mAC6BvE.INSTANCE
            io.reactivex.rxjava3.core.Completable r1 = r1.doOnError(r3)
            io.reactivex.rxjava3.core.Completable r1 = r1.onErrorComplete()
            com.amazon.alexa.location.phase3.sensor.motiondetection.-$$Lambda$MotionDetector$HCXDAtIdeD2L_RPfrUCS4XgV2c4 r3 = com.amazon.alexa.location.phase3.sensor.motiondetection.$$Lambda$MotionDetector$HCXDAtIdeD2L_RPfrUCS4XgV2c4.INSTANCE
            com.amazon.alexa.location.phase3.sensor.motiondetection.-$$Lambda$MotionDetector$ZWHYT5iQRgGem2NwzEEveJOToJ4 r4 = new com.amazon.alexa.location.phase3.sensor.motiondetection.-$$Lambda$MotionDetector$ZWHYT5iQRgGem2NwzEEveJOToJ4
            r4.<init>()
            r1.subscribe(r3, r4)
            r17.onComplete()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector.lambda$estimateMotionEvent$3$MotionDetector(io.reactivex.rxjava3.core.ObservableEmitter):void");
    }

    public /* synthetic */ void lambda$null$2$MotionDetector(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "lastStateStore.storeValueAsRx", th, this.crashReporter);
    }

    public void start() {
        State retrieveValue = this.lastStateStore.retrieveValue(LAST_STATE_KEY);
        if (retrieveValue != null) {
            this.state = retrieveValue;
        }
    }
}
