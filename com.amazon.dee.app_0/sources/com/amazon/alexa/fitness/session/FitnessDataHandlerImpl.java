package com.amazon.alexa.fitness.session;

import com.amazon.alexa.accessory.io.SourceInputStream;
import com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource;
import com.amazon.alexa.fitness.api.fitnessSdk.ActivityType;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.EchoBudSampleData;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.wellness.io.format.abs.ActivityV1;
import com.amazon.wellness.io.format.abs.AlexaBiometricData;
import com.amazon.wellness.io.format.abs.BiometricDataPoint;
import com.amazon.wellness.io.format.abs.CadenceV1;
import com.amazon.wellness.io.format.abs.Event;
import com.amazon.wellness.io.format.abs.FitnessDataParser;
import com.amazon.wellness.io.format.abs.StepCountV1;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessDataHandlerImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001dB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\n\u0010\u0018\u001a\u00020\u0019*\u00020\u001aJ\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u0016*\u00060\u001cR\u00020\u00002\u0006\u0010\u0017\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl;", "Lcom/amazon/alexa/fitness/session/FitnessDataHandler;", "fitnessDataParser", "Lcom/amazon/wellness/io/format/abs/FitnessDataParser;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/wellness/io/format/abs/FitnessDataParser;Lcom/amazon/alexa/fitness/logs/ILog;)V", "lastTimestamp", "", "Ljava/lang/Long;", "lastUptime", "", "Ljava/lang/Integer;", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "parseFitnessDataSource", "Lcom/amazon/wellness/io/format/abs/AlexaBiometricData;", "fitnessDataSource", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessDataSource;", "process", "Lcom/amazon/alexa/fitness/sdk/EchoBudSampleData;", "timestamp", "toActivityType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "Lcom/amazon/wellness/io/format/abs/ActivityV1;", "toEchoBudSample", "Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl$RawBiometricSample;", "RawBiometricSample", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessDataHandlerImpl implements FitnessDataHandler {
    private final FitnessDataParser fitnessDataParser;
    private Long lastTimestamp;
    private Integer lastUptime;
    @NotNull
    private final ReentrantLock lock;
    private final ILog log;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FitnessDataHandlerImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001:\u0001#B=\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0012\b\u0002\u0010\u0007\u001a\f\u0018\u00010\bR\u00060\u0000R\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u001e\u001a\u00060\u0000R\u00020\t2\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020\"H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0018\u0010\u0010\"\u0004\b\u0019\u0010\u0012R$\u0010\u0007\u001a\f\u0018\u00010\bR\u00060\u0000R\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006$"}, d2 = {"Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl$RawBiometricSample;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "cadence", "", "stepCount", "uptime", "Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl$RawBiometricSample$Uptime;", "Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl;", "(Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl;Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl$RawBiometricSample$Uptime;)V", "getActivity", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "setActivity", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;)V", "getCadence", "()Ljava/lang/Integer;", "setCadence", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "<set-?>", "sampleCount", "getSampleCount", "()I", "getStepCount", "setStepCount", "getUptime", "()Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl$RawBiometricSample$Uptime;", "setUptime", "(Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl$RawBiometricSample$Uptime;)V", "populateRawBiometricSample", "event", "Lcom/amazon/wellness/io/format/abs/Event;", "toString", "", "Uptime", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final class RawBiometricSample {
        @Nullable
        private ActivityType activity;
        @Nullable
        private Integer cadence;
        private int sampleCount;
        @Nullable
        private Integer stepCount;
        @Nullable
        private Uptime uptime;

        /* compiled from: FitnessDataHandlerImpl.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0003R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl$RawBiometricSample$Uptime;", "", "absoluteUptimeMillisecondsLo", "", "absoluteUptimeMillisecondsHi", "relativeUptimeMilliseconds", "(Lcom/amazon/alexa/fitness/session/FitnessDataHandlerImpl$RawBiometricSample;III)V", "getAbsoluteUptimeMillisecondsHi", "()I", "setAbsoluteUptimeMillisecondsHi", "(I)V", "getAbsoluteUptimeMillisecondsLo", "setAbsoluteUptimeMillisecondsLo", "getRelativeUptimeMilliseconds", "setRelativeUptimeMilliseconds", "getValue", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes8.dex */
        public final class Uptime {
            private int absoluteUptimeMillisecondsHi;
            private int absoluteUptimeMillisecondsLo;
            private int relativeUptimeMilliseconds;

            public Uptime(int i, int i2, int i3) {
                this.absoluteUptimeMillisecondsLo = i;
                this.absoluteUptimeMillisecondsHi = i2;
                this.relativeUptimeMilliseconds = i3;
            }

            public final int getAbsoluteUptimeMillisecondsHi() {
                return this.absoluteUptimeMillisecondsHi;
            }

            public final int getAbsoluteUptimeMillisecondsLo() {
                return this.absoluteUptimeMillisecondsLo;
            }

            public final int getRelativeUptimeMilliseconds() {
                return this.relativeUptimeMilliseconds;
            }

            public final int getValue() {
                if (this.absoluteUptimeMillisecondsLo == 0 && this.absoluteUptimeMillisecondsHi == 0) {
                    return this.relativeUptimeMilliseconds;
                }
                return (this.absoluteUptimeMillisecondsHi << 32) + this.absoluteUptimeMillisecondsLo;
            }

            public final void setAbsoluteUptimeMillisecondsHi(int i) {
                this.absoluteUptimeMillisecondsHi = i;
            }

            public final void setAbsoluteUptimeMillisecondsLo(int i) {
                this.absoluteUptimeMillisecondsLo = i;
            }

            public final void setRelativeUptimeMilliseconds(int i) {
                this.relativeUptimeMilliseconds = i;
            }
        }

        public RawBiometricSample(@Nullable ActivityType activityType, @Nullable Integer num, @Nullable Integer num2, @Nullable Uptime uptime) {
            this.activity = activityType;
            this.cadence = num;
            this.stepCount = num2;
            this.uptime = uptime;
        }

        @Nullable
        public final ActivityType getActivity() {
            return this.activity;
        }

        @Nullable
        public final Integer getCadence() {
            return this.cadence;
        }

        public final int getSampleCount() {
            return this.sampleCount;
        }

        @Nullable
        public final Integer getStepCount() {
            return this.stepCount;
        }

        @Nullable
        public final Uptime getUptime() {
            return this.uptime;
        }

        @NotNull
        public final RawBiometricSample populateRawBiometricSample(@NotNull Event event) {
            boolean hasActivityV1;
            Intrinsics.checkParameterIsNotNull(event, "event");
            this.sampleCount++;
            this.uptime = new Uptime(event.getAbsoluteUptimeMillisecondsLo(), event.getAbsoluteUptimeMillisecondsHi(), event.getRelativeUptimeMilliseconds());
            BiometricDataPoint biometricDataPoint = event.getBiometricDataPoint();
            if (biometricDataPoint.hasCadenceV1()) {
                CadenceV1 cadenceV1 = biometricDataPoint.getCadenceV1();
                Intrinsics.checkExpressionValueIsNotNull(cadenceV1, "cadenceV1");
                this.cadence = Integer.valueOf(cadenceV1.getStepsPerMinute());
            } else if (biometricDataPoint.hasStepCountV1()) {
                StepCountV1 stepCountV1 = biometricDataPoint.getStepCountV1();
                Intrinsics.checkExpressionValueIsNotNull(stepCountV1, "stepCountV1");
                this.stepCount = Integer.valueOf(stepCountV1.getAbsoluteStepCount());
            } else {
                hasActivityV1 = FitnessDataHandlerImplKt.hasActivityV1(biometricDataPoint);
                if (hasActivityV1) {
                    FitnessDataHandlerImpl fitnessDataHandlerImpl = FitnessDataHandlerImpl.this;
                    ActivityV1 activityV1 = biometricDataPoint.getActivityV1();
                    Intrinsics.checkExpressionValueIsNotNull(activityV1, "activityV1");
                    this.activity = fitnessDataHandlerImpl.toActivityType(activityV1);
                } else {
                    ILog iLog = FitnessDataHandlerImpl.this.log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected biometric data received: ");
                    outline107.append(biometricDataPoint.getValueCase());
                    ILog.DefaultImpls.error$default(iLog, "FitnessDataHandler", outline107.toString(), null, 4, null);
                }
            }
            return this;
        }

        public final void setActivity(@Nullable ActivityType activityType) {
            this.activity = activityType;
        }

        public final void setCadence(@Nullable Integer num) {
            this.cadence = num;
        }

        public final void setStepCount(@Nullable Integer num) {
            this.stepCount = num;
        }

        public final void setUptime(@Nullable Uptime uptime) {
            this.uptime = uptime;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("activity: ");
            outline107.append(this.activity);
            outline107.append(", cadence: ");
            outline107.append(this.cadence);
            outline107.append(", stepCount: ");
            outline107.append(this.stepCount);
            outline107.append(", uptime: ");
            Uptime uptime = this.uptime;
            outline107.append(uptime != null ? Integer.valueOf(uptime.getValue()) : null);
            return outline107.toString();
        }

        public /* synthetic */ RawBiometricSample(FitnessDataHandlerImpl fitnessDataHandlerImpl, ActivityType activityType, Integer num, Integer num2, Uptime uptime, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : activityType, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : uptime);
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActivityV1.values().length];

        static {
            $EnumSwitchMapping$0[ActivityV1.ACTIVITY_WALKING.ordinal()] = 1;
            $EnumSwitchMapping$0[ActivityV1.ACTIVITY_RUNNING.ordinal()] = 2;
            $EnumSwitchMapping$0[ActivityV1.ACTIVITY_IDLE.ordinal()] = 3;
            $EnumSwitchMapping$0[ActivityV1.UNRECOGNIZED.ordinal()] = 4;
            $EnumSwitchMapping$0[ActivityV1.ACTIVITY_BICYCLING.ordinal()] = 5;
            $EnumSwitchMapping$0[ActivityV1.ACTIVITY_DRIVING.ordinal()] = 6;
            $EnumSwitchMapping$0[ActivityV1.ACTIVITY_UNKNOWN.ordinal()] = 7;
        }
    }

    @Inject
    public FitnessDataHandlerImpl(@NotNull FitnessDataParser fitnessDataParser, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(fitnessDataParser, "fitnessDataParser");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.fitnessDataParser = fitnessDataParser;
        this.log = log;
        this.lock = new ReentrantLock();
    }

    private final AlexaBiometricData parseFitnessDataSource(FitnessDataSource fitnessDataSource) {
        AlexaBiometricData alexaBiometricData;
        SourceInputStream sourceInputStream = new SourceInputStream(fitnessDataSource);
        try {
            try {
                alexaBiometricData = this.fitnessDataParser.parse(sourceInputStream);
            } catch (IOException e) {
                this.log.error("FitnessDataHandler", "Error parsing fitness data stream from accessory", e);
                alexaBiometricData = null;
            }
            CloseableKt.closeFinally(sourceInputStream, null);
            return alexaBiometricData;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(sourceInputStream, th);
                throw th2;
            }
        }
    }

    private final EchoBudSampleData toEchoBudSample(@NotNull RawBiometricSample rawBiometricSample, long j) {
        int sampleCount = rawBiometricSample.getSampleCount();
        RawBiometricSample.Uptime uptime = rawBiometricSample.getUptime();
        ActivityType activity = rawBiometricSample.getActivity();
        Integer stepCount = rawBiometricSample.getStepCount();
        Integer cadence = rawBiometricSample.getCadence();
        if (sampleCount == 3 && uptime != null && activity != null && stepCount != null && cadence != null) {
            int value = uptime.getValue();
            ILog iLog = this.log;
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Sample MCU uptime = ", value, ", delta = ");
            Integer num = this.lastUptime;
            outline109.append(value - (num != null ? num.intValue() : value));
            outline109.append("; Sample timestamp delta: ");
            Long l = this.lastTimestamp;
            outline109.append(j - (l != null ? l.longValue() : j));
            outline109.append("ms");
            ILog.DefaultImpls.info$default(iLog, "FitnessDataHandler", outline109.toString(), null, 4, null);
            this.lastTimestamp = Long.valueOf(j);
            this.lastUptime = Integer.valueOf(value);
            return new EchoBudSampleData(j, activity, stepCount.intValue(), cadence.intValue());
        }
        ILog iLog2 = this.log;
        ILog.DefaultImpls.error$default(iLog2, "FitnessDataHandler", "Invalid biometric data received: " + rawBiometricSample, null, 4, null);
        return null;
    }

    @NotNull
    public final ReentrantLock getLock() {
        return this.lock;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0022, code lost:
        r13 = kotlin.collections.CollectionsKt___CollectionsKt.asSequence(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0028, code lost:
        r13 = kotlin.sequences.SequencesKt___SequencesKt.filter(r13, com.amazon.alexa.fitness.session.FitnessDataHandlerImpl$process$1$1.INSTANCE);
     */
    @Override // com.amazon.alexa.fitness.session.FitnessDataHandler
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.alexa.fitness.sdk.EchoBudSampleData process(long r11, @org.jetbrains.annotations.NotNull com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource r13) {
        /*
            r10 = this;
            java.lang.String r0 = "fitnessDataSource"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r0)
            java.util.concurrent.locks.ReentrantLock r0 = r10.lock
            r0.lock()
            com.amazon.alexa.fitness.logs.ILog r1 = r10.log     // Catch: java.lang.Throwable -> L5f
            java.lang.String r2 = "FitnessDataHandler"
            java.lang.String r3 = "Begin processing fitness data..."
            r4 = 0
            r5 = 4
            r6 = 0
            com.amazon.alexa.fitness.logs.ILog.DefaultImpls.debug$default(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L5f
            com.amazon.wellness.io.format.abs.AlexaBiometricData r13 = r10.parseFitnessDataSource(r13)     // Catch: java.lang.Throwable -> L5f
            if (r13 == 0) goto L5a
            java.util.Set r13 = r13.getEvents()     // Catch: java.lang.Throwable -> L5f
            if (r13 == 0) goto L5a
            kotlin.sequences.Sequence r13 = kotlin.collections.CollectionsKt.asSequence(r13)     // Catch: java.lang.Throwable -> L5f
            if (r13 == 0) goto L5a
            com.amazon.alexa.fitness.session.FitnessDataHandlerImpl$process$1$1 r1 = com.amazon.alexa.fitness.session.FitnessDataHandlerImpl$process$1$1.INSTANCE     // Catch: java.lang.Throwable -> L5f
            kotlin.sequences.Sequence r13 = kotlin.sequences.SequencesKt.filter(r13, r1)     // Catch: java.lang.Throwable -> L5f
            if (r13 == 0) goto L5a
            com.amazon.alexa.fitness.session.FitnessDataHandlerImpl$RawBiometricSample r9 = new com.amazon.alexa.fitness.session.FitnessDataHandlerImpl$RawBiometricSample     // Catch: java.lang.Throwable -> L5f
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 15
            r8 = 0
            r1 = r9
            r2 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L5f
            java.util.Iterator r13 = r13.iterator()     // Catch: java.lang.Throwable -> L5f
        L42:
            boolean r1 = r13.hasNext()     // Catch: java.lang.Throwable -> L5f
            if (r1 == 0) goto L53
            java.lang.Object r1 = r13.next()     // Catch: java.lang.Throwable -> L5f
            com.amazon.wellness.io.format.abs.Event r1 = (com.amazon.wellness.io.format.abs.Event) r1     // Catch: java.lang.Throwable -> L5f
            com.amazon.alexa.fitness.session.FitnessDataHandlerImpl$RawBiometricSample r9 = r9.populateRawBiometricSample(r1)     // Catch: java.lang.Throwable -> L5f
            goto L42
        L53:
            if (r9 == 0) goto L5a
            com.amazon.alexa.fitness.sdk.EchoBudSampleData r11 = r10.toEchoBudSample(r9, r11)     // Catch: java.lang.Throwable -> L5f
            goto L5b
        L5a:
            r11 = 0
        L5b:
            r0.unlock()
            return r11
        L5f:
            r11 = move-exception
            r0.unlock()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.session.FitnessDataHandlerImpl.process(long, com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource):com.amazon.alexa.fitness.sdk.EchoBudSampleData");
    }

    @NotNull
    public final ActivityType toActivityType(@NotNull ActivityV1 toActivityType) {
        Intrinsics.checkParameterIsNotNull(toActivityType, "$this$toActivityType");
        switch (WhenMappings.$EnumSwitchMapping$0[toActivityType.ordinal()]) {
            case 1:
                return ActivityType.WALKING;
            case 2:
                return ActivityType.RUNNING;
            case 3:
                return ActivityType.IDLE;
            case 4:
                return ActivityType.UNRECOGNIZED;
            case 5:
                return ActivityType.BICYCLING;
            case 6:
                return ActivityType.DRIVING;
            case 7:
                return ActivityType.UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
