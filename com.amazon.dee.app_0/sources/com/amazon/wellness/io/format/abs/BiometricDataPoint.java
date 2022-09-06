package com.amazon.wellness.io.format.abs;

import com.amazon.wellness.io.format.abs.BodyTemperatureV1;
import com.amazon.wellness.io.format.abs.CadenceV1;
import com.amazon.wellness.io.format.abs.CaloriesV1;
import com.amazon.wellness.io.format.abs.HeartRateElevationV1;
import com.amazon.wellness.io.format.abs.HeartRateV1;
import com.amazon.wellness.io.format.abs.MovementSummaryV1;
import com.amazon.wellness.io.format.abs.RRIntervalV1;
import com.amazon.wellness.io.format.abs.SleepStateV1;
import com.amazon.wellness.io.format.abs.StepCountV1;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public final class BiometricDataPoint extends GeneratedMessageV3 implements BiometricDataPointOrBuilder {
    public static final int ACTIVITY_INTENSITY_V1_FIELD_NUMBER = 7;
    public static final int ACTIVITY_V1_FIELD_NUMBER = 5;
    public static final int BODY_TEMPERATURE_V1_FIELD_NUMBER = 6;
    public static final int CADENCE_V1_FIELD_NUMBER = 3;
    public static final int CALORIES_V1_FIELD_NUMBER = 8;
    public static final int HEART_RATE_ELEVATION_V1_FIELD_NUMBER = 11;
    public static final int HEART_RATE_V1_FIELD_NUMBER = 1;
    public static final int MOVEMENT_SUMMARY_V1_FIELD_NUMBER = 10;
    public static final int RR_INTERVAL_V1_FIELD_NUMBER = 2;
    public static final int SLEEP_STATE_V1_FIELD_NUMBER = 9;
    public static final int STEP_COUNT_V1_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int valueCase_;
    private Object value_;
    private static final BiometricDataPoint DEFAULT_INSTANCE = new BiometricDataPoint();
    private static final Parser<BiometricDataPoint> PARSER = new AbstractParser<BiometricDataPoint>() { // from class: com.amazon.wellness.io.format.abs.BiometricDataPoint.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public BiometricDataPoint mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BiometricDataPoint(codedInputStream, extensionRegistryLite);
        }
    };

    /* renamed from: com.amazon.wellness.io.format.abs.BiometricDataPoint$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase = new int[ValueCase.values().length];

        static {
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.HEART_RATE_V1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.RR_INTERVAL_V1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.CADENCE_V1.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.STEP_COUNT_V1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.ACTIVITY_V1.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.BODY_TEMPERATURE_V1.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.ACTIVITY_INTENSITY_V1.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.CALORIES_V1.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.SLEEP_STATE_V1.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.MOVEMENT_SUMMARY_V1.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.HEART_RATE_ELEVATION_V1.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$BiometricDataPoint$ValueCase[ValueCase.VALUE_NOT_SET.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public enum ValueCase implements Internal.EnumLite {
        HEART_RATE_V1(1),
        RR_INTERVAL_V1(2),
        CADENCE_V1(3),
        STEP_COUNT_V1(4),
        ACTIVITY_V1(5),
        BODY_TEMPERATURE_V1(6),
        ACTIVITY_INTENSITY_V1(7),
        CALORIES_V1(8),
        SLEEP_STATE_V1(9),
        MOVEMENT_SUMMARY_V1(10),
        HEART_RATE_ELEVATION_V1(11),
        VALUE_NOT_SET(0);
        
        private final int value;

        ValueCase(int i) {
            this.value = i;
        }

        public static ValueCase forNumber(int i) {
            switch (i) {
                case 0:
                    return VALUE_NOT_SET;
                case 1:
                    return HEART_RATE_V1;
                case 2:
                    return RR_INTERVAL_V1;
                case 3:
                    return CADENCE_V1;
                case 4:
                    return STEP_COUNT_V1;
                case 5:
                    return ACTIVITY_V1;
                case 6:
                    return BODY_TEMPERATURE_V1;
                case 7:
                    return ACTIVITY_INTENSITY_V1;
                case 8:
                    return CALORIES_V1;
                case 9:
                    return SLEEP_STATE_V1;
                case 10:
                    return MOVEMENT_SUMMARY_V1;
                case 11:
                    return HEART_RATE_ELEVATION_V1;
                default:
                    return null;
            }
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ValueCase valueOf(int i) {
            return forNumber(i);
        }
    }

    public static BiometricDataPoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return Biometric.internal_static_BiometricDataPoint_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static BiometricDataPoint parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BiometricDataPoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BiometricDataPoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<BiometricDataPoint> parser() {
        return PARSER;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0039, code lost:
        if (getHeartRateElevationV1().equals(r5.getHeartRateElevationV1()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004f, code lost:
        if (getMovementSummaryV1().equals(r5.getMovementSummaryV1()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0060, code lost:
        if (getSleepStateV1().equals(r5.getSleepStateV1()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0071, code lost:
        if (getCaloriesV1().equals(r5.getCaloriesV1()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007e, code lost:
        if (getActivityIntensityV1Value() == r5.getActivityIntensityV1Value()) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008f, code lost:
        if (getBodyTemperatureV1().equals(r5.getBodyTemperatureV1()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009c, code lost:
        if (getActivityV1Value() == r5.getActivityV1Value()) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ad, code lost:
        if (getStepCountV1().equals(r5.getStepCountV1()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00be, code lost:
        if (getCadenceV1().equals(r5.getCadenceV1()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d0, code lost:
        if (getRrIntervalV1().equals(r5.getRrIntervalV1()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00e2, code lost:
        if (getHeartRateV1().equals(r5.getHeartRateV1()) != false) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e8  */
    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L4
            return r0
        L4:
            boolean r1 = r5 instanceof com.amazon.wellness.io.format.abs.BiometricDataPoint
            if (r1 != 0) goto Ld
            boolean r5 = super.equals(r5)
            return r5
        Ld:
            com.amazon.wellness.io.format.abs.BiometricDataPoint r5 = (com.amazon.wellness.io.format.abs.BiometricDataPoint) r5
            com.amazon.wellness.io.format.abs.BiometricDataPoint$ValueCase r1 = r4.getValueCase()
            com.amazon.wellness.io.format.abs.BiometricDataPoint$ValueCase r2 = r5.getValueCase()
            boolean r1 = r1.equals(r2)
            r2 = 0
            if (r1 == 0) goto L20
            r1 = r0
            goto L21
        L20:
            r1 = r2
        L21:
            if (r1 != 0) goto L24
            return r2
        L24:
            int r3 = r4.valueCase_
            switch(r3) {
                case 1: goto Ld4;
                case 2: goto Lc2;
                case 3: goto Lb0;
                case 4: goto L9f;
                case 5: goto L92;
                case 6: goto L81;
                case 7: goto L74;
                case 8: goto L63;
                case 9: goto L52;
                case 10: goto L41;
                case 11: goto L2b;
                default: goto L29;
            }
        L29:
            goto Le6
        L2b:
            if (r1 == 0) goto L3e
            com.amazon.wellness.io.format.abs.HeartRateElevationV1 r1 = r4.getHeartRateElevationV1()
            com.amazon.wellness.io.format.abs.HeartRateElevationV1 r3 = r5.getHeartRateElevationV1()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3e
        L3b:
            r1 = r0
            goto Le6
        L3e:
            r1 = r2
            goto Le6
        L41:
            if (r1 == 0) goto L3e
            com.amazon.wellness.io.format.abs.MovementSummaryV1 r1 = r4.getMovementSummaryV1()
            com.amazon.wellness.io.format.abs.MovementSummaryV1 r3 = r5.getMovementSummaryV1()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3e
            goto L3b
        L52:
            if (r1 == 0) goto L3e
            com.amazon.wellness.io.format.abs.SleepStateV1 r1 = r4.getSleepStateV1()
            com.amazon.wellness.io.format.abs.SleepStateV1 r3 = r5.getSleepStateV1()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3e
            goto L3b
        L63:
            if (r1 == 0) goto L3e
            com.amazon.wellness.io.format.abs.CaloriesV1 r1 = r4.getCaloriesV1()
            com.amazon.wellness.io.format.abs.CaloriesV1 r3 = r5.getCaloriesV1()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3e
            goto L3b
        L74:
            if (r1 == 0) goto L3e
            int r1 = r4.getActivityIntensityV1Value()
            int r3 = r5.getActivityIntensityV1Value()
            if (r1 != r3) goto L3e
            goto L3b
        L81:
            if (r1 == 0) goto L3e
            com.amazon.wellness.io.format.abs.BodyTemperatureV1 r1 = r4.getBodyTemperatureV1()
            com.amazon.wellness.io.format.abs.BodyTemperatureV1 r3 = r5.getBodyTemperatureV1()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3e
            goto L3b
        L92:
            if (r1 == 0) goto L3e
            int r1 = r4.getActivityV1Value()
            int r3 = r5.getActivityV1Value()
            if (r1 != r3) goto L3e
            goto L3b
        L9f:
            if (r1 == 0) goto L3e
            com.amazon.wellness.io.format.abs.StepCountV1 r1 = r4.getStepCountV1()
            com.amazon.wellness.io.format.abs.StepCountV1 r3 = r5.getStepCountV1()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3e
            goto L3b
        Lb0:
            if (r1 == 0) goto L3e
            com.amazon.wellness.io.format.abs.CadenceV1 r1 = r4.getCadenceV1()
            com.amazon.wellness.io.format.abs.CadenceV1 r3 = r5.getCadenceV1()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3e
            goto L3b
        Lc2:
            if (r1 == 0) goto L3e
            com.amazon.wellness.io.format.abs.RRIntervalV1 r1 = r4.getRrIntervalV1()
            com.amazon.wellness.io.format.abs.RRIntervalV1 r3 = r5.getRrIntervalV1()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3e
            goto L3b
        Ld4:
            if (r1 == 0) goto L3e
            com.amazon.wellness.io.format.abs.HeartRateV1 r1 = r4.getHeartRateV1()
            com.amazon.wellness.io.format.abs.HeartRateV1 r3 = r5.getHeartRateV1()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3e
            goto L3b
        Le6:
            if (r1 == 0) goto Lf3
            com.google.protobuf.UnknownFieldSet r1 = r4.unknownFields
            com.google.protobuf.UnknownFieldSet r5 = r5.unknownFields
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto Lf3
            goto Lf4
        Lf3:
            r0 = r2
        Lf4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.wellness.io.format.abs.BiometricDataPoint.equals(java.lang.Object):boolean");
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public ActivityIntensityV1 getActivityIntensityV1() {
        if (this.valueCase_ == 7) {
            ActivityIntensityV1 valueOf = ActivityIntensityV1.valueOf(((Integer) this.value_).intValue());
            return valueOf == null ? ActivityIntensityV1.UNRECOGNIZED : valueOf;
        }
        return ActivityIntensityV1.ACTIVITY_INTENSITY_UNKNOWN;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public int getActivityIntensityV1Value() {
        if (this.valueCase_ == 7) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public ActivityV1 getActivityV1() {
        if (this.valueCase_ == 5) {
            ActivityV1 valueOf = ActivityV1.valueOf(((Integer) this.value_).intValue());
            return valueOf == null ? ActivityV1.UNRECOGNIZED : valueOf;
        }
        return ActivityV1.ACTIVITY_UNKNOWN;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public int getActivityV1Value() {
        if (this.valueCase_ == 5) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public BodyTemperatureV1 getBodyTemperatureV1() {
        if (this.valueCase_ == 6) {
            return (BodyTemperatureV1) this.value_;
        }
        return BodyTemperatureV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public BodyTemperatureV1OrBuilder getBodyTemperatureV1OrBuilder() {
        if (this.valueCase_ == 6) {
            return (BodyTemperatureV1) this.value_;
        }
        return BodyTemperatureV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public CadenceV1 getCadenceV1() {
        if (this.valueCase_ == 3) {
            return (CadenceV1) this.value_;
        }
        return CadenceV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public CadenceV1OrBuilder getCadenceV1OrBuilder() {
        if (this.valueCase_ == 3) {
            return (CadenceV1) this.value_;
        }
        return CadenceV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public CaloriesV1 getCaloriesV1() {
        if (this.valueCase_ == 8) {
            return (CaloriesV1) this.value_;
        }
        return CaloriesV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public CaloriesV1OrBuilder getCaloriesV1OrBuilder() {
        if (this.valueCase_ == 8) {
            return (CaloriesV1) this.value_;
        }
        return CaloriesV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public HeartRateElevationV1 getHeartRateElevationV1() {
        if (this.valueCase_ == 11) {
            return (HeartRateElevationV1) this.value_;
        }
        return HeartRateElevationV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public HeartRateElevationV1OrBuilder getHeartRateElevationV1OrBuilder() {
        if (this.valueCase_ == 11) {
            return (HeartRateElevationV1) this.value_;
        }
        return HeartRateElevationV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public HeartRateV1 getHeartRateV1() {
        if (this.valueCase_ == 1) {
            return (HeartRateV1) this.value_;
        }
        return HeartRateV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public HeartRateV1OrBuilder getHeartRateV1OrBuilder() {
        if (this.valueCase_ == 1) {
            return (HeartRateV1) this.value_;
        }
        return HeartRateV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public MovementSummaryV1 getMovementSummaryV1() {
        if (this.valueCase_ == 10) {
            return (MovementSummaryV1) this.value_;
        }
        return MovementSummaryV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public MovementSummaryV1OrBuilder getMovementSummaryV1OrBuilder() {
        if (this.valueCase_ == 10) {
            return (MovementSummaryV1) this.value_;
        }
        return MovementSummaryV1.getDefaultInstance();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<BiometricDataPoint> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public RRIntervalV1 getRrIntervalV1() {
        if (this.valueCase_ == 2) {
            return (RRIntervalV1) this.value_;
        }
        return RRIntervalV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public RRIntervalV1OrBuilder getRrIntervalV1OrBuilder() {
        if (this.valueCase_ == 2) {
            return (RRIntervalV1) this.value_;
        }
        return RRIntervalV1.getDefaultInstance();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.valueCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeMessageSize(1, (HeartRateV1) this.value_);
        }
        if (this.valueCase_ == 2) {
            i2 += CodedOutputStream.computeMessageSize(2, (RRIntervalV1) this.value_);
        }
        if (this.valueCase_ == 3) {
            i2 += CodedOutputStream.computeMessageSize(3, (CadenceV1) this.value_);
        }
        if (this.valueCase_ == 4) {
            i2 += CodedOutputStream.computeMessageSize(4, (StepCountV1) this.value_);
        }
        if (this.valueCase_ == 5) {
            i2 += CodedOutputStream.computeEnumSize(5, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 6) {
            i2 += CodedOutputStream.computeMessageSize(6, (BodyTemperatureV1) this.value_);
        }
        if (this.valueCase_ == 7) {
            i2 += CodedOutputStream.computeEnumSize(7, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 8) {
            i2 += CodedOutputStream.computeMessageSize(8, (CaloriesV1) this.value_);
        }
        if (this.valueCase_ == 9) {
            i2 += CodedOutputStream.computeMessageSize(9, (SleepStateV1) this.value_);
        }
        if (this.valueCase_ == 10) {
            i2 += CodedOutputStream.computeMessageSize(10, (MovementSummaryV1) this.value_);
        }
        if (this.valueCase_ == 11) {
            i2 += CodedOutputStream.computeMessageSize(11, (HeartRateElevationV1) this.value_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public SleepStateV1 getSleepStateV1() {
        if (this.valueCase_ == 9) {
            return (SleepStateV1) this.value_;
        }
        return SleepStateV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public SleepStateV1OrBuilder getSleepStateV1OrBuilder() {
        if (this.valueCase_ == 9) {
            return (SleepStateV1) this.value_;
        }
        return SleepStateV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public StepCountV1 getStepCountV1() {
        if (this.valueCase_ == 4) {
            return (StepCountV1) this.value_;
        }
        return StepCountV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public StepCountV1OrBuilder getStepCountV1OrBuilder() {
        if (this.valueCase_ == 4) {
            return (StepCountV1) this.value_;
        }
        return StepCountV1.getDefaultInstance();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public ValueCase getValueCase() {
        return ValueCase.forNumber(this.valueCase_);
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public boolean hasBodyTemperatureV1() {
        return this.valueCase_ == 6;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public boolean hasCadenceV1() {
        return this.valueCase_ == 3;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public boolean hasCaloriesV1() {
        return this.valueCase_ == 8;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public boolean hasHeartRateElevationV1() {
        return this.valueCase_ == 11;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public boolean hasHeartRateV1() {
        return this.valueCase_ == 1;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public boolean hasMovementSummaryV1() {
        return this.valueCase_ == 10;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public boolean hasRrIntervalV1() {
        return this.valueCase_ == 2;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public boolean hasSleepStateV1() {
        return this.valueCase_ == 9;
    }

    @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
    public boolean hasStepCountV1() {
        return this.valueCase_ == 4;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int outline4;
        int hashCode;
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode2 = getDescriptor().hashCode() + 779;
        switch (this.valueCase_) {
            case 1:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 1, 53);
                hashCode = getHeartRateV1().hashCode();
                hashCode2 = outline4 + hashCode;
                int hashCode3 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode3;
                return hashCode3;
            case 2:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 2, 53);
                hashCode = getRrIntervalV1().hashCode();
                hashCode2 = outline4 + hashCode;
                int hashCode32 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode32;
                return hashCode32;
            case 3:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 3, 53);
                hashCode = getCadenceV1().hashCode();
                hashCode2 = outline4 + hashCode;
                int hashCode322 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode322;
                return hashCode322;
            case 4:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 4, 53);
                hashCode = getStepCountV1().hashCode();
                hashCode2 = outline4 + hashCode;
                int hashCode3222 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode3222;
                return hashCode3222;
            case 5:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 5, 53);
                hashCode = getActivityV1Value();
                hashCode2 = outline4 + hashCode;
                int hashCode32222 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode32222;
                return hashCode32222;
            case 6:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 6, 53);
                hashCode = getBodyTemperatureV1().hashCode();
                hashCode2 = outline4 + hashCode;
                int hashCode322222 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode322222;
                return hashCode322222;
            case 7:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 7, 53);
                hashCode = getActivityIntensityV1Value();
                hashCode2 = outline4 + hashCode;
                int hashCode3222222 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode3222222;
                return hashCode3222222;
            case 8:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 8, 53);
                hashCode = getCaloriesV1().hashCode();
                hashCode2 = outline4 + hashCode;
                int hashCode32222222 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode32222222;
                return hashCode32222222;
            case 9:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 9, 53);
                hashCode = getSleepStateV1().hashCode();
                hashCode2 = outline4 + hashCode;
                int hashCode322222222 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode322222222;
                return hashCode322222222;
            case 10:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 10, 53);
                hashCode = getMovementSummaryV1().hashCode();
                hashCode2 = outline4 + hashCode;
                int hashCode3222222222 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode3222222222;
                return hashCode3222222222;
            case 11:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode2, 37, 11, 53);
                hashCode = getHeartRateElevationV1().hashCode();
                hashCode2 = outline4 + hashCode;
                int hashCode32222222222 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode32222222222;
                return hashCode32222222222;
            default:
                int hashCode322222222222 = this.unknownFields.hashCode() + (hashCode2 * 29);
                this.memoizedHashCode = hashCode322222222222;
                return hashCode322222222222;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return Biometric.internal_static_BiometricDataPoint_fieldAccessorTable.ensureFieldAccessorsInitialized(BiometricDataPoint.class, Builder.class);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.valueCase_ == 1) {
            codedOutputStream.writeMessage(1, (HeartRateV1) this.value_);
        }
        if (this.valueCase_ == 2) {
            codedOutputStream.writeMessage(2, (RRIntervalV1) this.value_);
        }
        if (this.valueCase_ == 3) {
            codedOutputStream.writeMessage(3, (CadenceV1) this.value_);
        }
        if (this.valueCase_ == 4) {
            codedOutputStream.writeMessage(4, (StepCountV1) this.value_);
        }
        if (this.valueCase_ == 5) {
            codedOutputStream.writeEnum(5, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 6) {
            codedOutputStream.writeMessage(6, (BodyTemperatureV1) this.value_);
        }
        if (this.valueCase_ == 7) {
            codedOutputStream.writeEnum(7, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 8) {
            codedOutputStream.writeMessage(8, (CaloriesV1) this.value_);
        }
        if (this.valueCase_ == 9) {
            codedOutputStream.writeMessage(9, (SleepStateV1) this.value_);
        }
        if (this.valueCase_ == 10) {
            codedOutputStream.writeMessage(10, (MovementSummaryV1) this.value_);
        }
        if (this.valueCase_ == 11) {
            codedOutputStream.writeMessage(11, (HeartRateElevationV1) this.value_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes13.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BiometricDataPointOrBuilder {
        private SingleFieldBuilderV3<BodyTemperatureV1, BodyTemperatureV1.Builder, BodyTemperatureV1OrBuilder> bodyTemperatureV1Builder_;
        private SingleFieldBuilderV3<CadenceV1, CadenceV1.Builder, CadenceV1OrBuilder> cadenceV1Builder_;
        private SingleFieldBuilderV3<CaloriesV1, CaloriesV1.Builder, CaloriesV1OrBuilder> caloriesV1Builder_;
        private SingleFieldBuilderV3<HeartRateElevationV1, HeartRateElevationV1.Builder, HeartRateElevationV1OrBuilder> heartRateElevationV1Builder_;
        private SingleFieldBuilderV3<HeartRateV1, HeartRateV1.Builder, HeartRateV1OrBuilder> heartRateV1Builder_;
        private SingleFieldBuilderV3<MovementSummaryV1, MovementSummaryV1.Builder, MovementSummaryV1OrBuilder> movementSummaryV1Builder_;
        private SingleFieldBuilderV3<RRIntervalV1, RRIntervalV1.Builder, RRIntervalV1OrBuilder> rrIntervalV1Builder_;
        private SingleFieldBuilderV3<SleepStateV1, SleepStateV1.Builder, SleepStateV1OrBuilder> sleepStateV1Builder_;
        private SingleFieldBuilderV3<StepCountV1, StepCountV1.Builder, StepCountV1OrBuilder> stepCountV1Builder_;
        private int valueCase_;
        private Object value_;

        private SingleFieldBuilderV3<BodyTemperatureV1, BodyTemperatureV1.Builder, BodyTemperatureV1OrBuilder> getBodyTemperatureV1FieldBuilder() {
            if (this.bodyTemperatureV1Builder_ == null) {
                if (this.valueCase_ != 6) {
                    this.value_ = BodyTemperatureV1.getDefaultInstance();
                }
                this.bodyTemperatureV1Builder_ = new SingleFieldBuilderV3<>((BodyTemperatureV1) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 6;
            onChanged();
            return this.bodyTemperatureV1Builder_;
        }

        private SingleFieldBuilderV3<CadenceV1, CadenceV1.Builder, CadenceV1OrBuilder> getCadenceV1FieldBuilder() {
            if (this.cadenceV1Builder_ == null) {
                if (this.valueCase_ != 3) {
                    this.value_ = CadenceV1.getDefaultInstance();
                }
                this.cadenceV1Builder_ = new SingleFieldBuilderV3<>((CadenceV1) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 3;
            onChanged();
            return this.cadenceV1Builder_;
        }

        private SingleFieldBuilderV3<CaloriesV1, CaloriesV1.Builder, CaloriesV1OrBuilder> getCaloriesV1FieldBuilder() {
            if (this.caloriesV1Builder_ == null) {
                if (this.valueCase_ != 8) {
                    this.value_ = CaloriesV1.getDefaultInstance();
                }
                this.caloriesV1Builder_ = new SingleFieldBuilderV3<>((CaloriesV1) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 8;
            onChanged();
            return this.caloriesV1Builder_;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Biometric.internal_static_BiometricDataPoint_descriptor;
        }

        private SingleFieldBuilderV3<HeartRateElevationV1, HeartRateElevationV1.Builder, HeartRateElevationV1OrBuilder> getHeartRateElevationV1FieldBuilder() {
            if (this.heartRateElevationV1Builder_ == null) {
                if (this.valueCase_ != 11) {
                    this.value_ = HeartRateElevationV1.getDefaultInstance();
                }
                this.heartRateElevationV1Builder_ = new SingleFieldBuilderV3<>((HeartRateElevationV1) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 11;
            onChanged();
            return this.heartRateElevationV1Builder_;
        }

        private SingleFieldBuilderV3<HeartRateV1, HeartRateV1.Builder, HeartRateV1OrBuilder> getHeartRateV1FieldBuilder() {
            if (this.heartRateV1Builder_ == null) {
                if (this.valueCase_ != 1) {
                    this.value_ = HeartRateV1.getDefaultInstance();
                }
                this.heartRateV1Builder_ = new SingleFieldBuilderV3<>((HeartRateV1) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 1;
            onChanged();
            return this.heartRateV1Builder_;
        }

        private SingleFieldBuilderV3<MovementSummaryV1, MovementSummaryV1.Builder, MovementSummaryV1OrBuilder> getMovementSummaryV1FieldBuilder() {
            if (this.movementSummaryV1Builder_ == null) {
                if (this.valueCase_ != 10) {
                    this.value_ = MovementSummaryV1.getDefaultInstance();
                }
                this.movementSummaryV1Builder_ = new SingleFieldBuilderV3<>((MovementSummaryV1) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 10;
            onChanged();
            return this.movementSummaryV1Builder_;
        }

        private SingleFieldBuilderV3<RRIntervalV1, RRIntervalV1.Builder, RRIntervalV1OrBuilder> getRrIntervalV1FieldBuilder() {
            if (this.rrIntervalV1Builder_ == null) {
                if (this.valueCase_ != 2) {
                    this.value_ = RRIntervalV1.getDefaultInstance();
                }
                this.rrIntervalV1Builder_ = new SingleFieldBuilderV3<>((RRIntervalV1) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 2;
            onChanged();
            return this.rrIntervalV1Builder_;
        }

        private SingleFieldBuilderV3<SleepStateV1, SleepStateV1.Builder, SleepStateV1OrBuilder> getSleepStateV1FieldBuilder() {
            if (this.sleepStateV1Builder_ == null) {
                if (this.valueCase_ != 9) {
                    this.value_ = SleepStateV1.getDefaultInstance();
                }
                this.sleepStateV1Builder_ = new SingleFieldBuilderV3<>((SleepStateV1) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 9;
            onChanged();
            return this.sleepStateV1Builder_;
        }

        private SingleFieldBuilderV3<StepCountV1, StepCountV1.Builder, StepCountV1OrBuilder> getStepCountV1FieldBuilder() {
            if (this.stepCountV1Builder_ == null) {
                if (this.valueCase_ != 4) {
                    this.value_ = StepCountV1.getDefaultInstance();
                }
                this.stepCountV1Builder_ = new SingleFieldBuilderV3<>((StepCountV1) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 4;
            onChanged();
            return this.stepCountV1Builder_;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearActivityIntensityV1() {
            if (this.valueCase_ == 7) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        public Builder clearActivityV1() {
            if (this.valueCase_ == 5) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        public Builder clearBodyTemperatureV1() {
            if (this.bodyTemperatureV1Builder_ == null) {
                if (this.valueCase_ == 6) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                    onChanged();
                }
            } else {
                if (this.valueCase_ == 6) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                this.bodyTemperatureV1Builder_.clear();
            }
            return this;
        }

        public Builder clearCadenceV1() {
            if (this.cadenceV1Builder_ == null) {
                if (this.valueCase_ == 3) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                    onChanged();
                }
            } else {
                if (this.valueCase_ == 3) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                this.cadenceV1Builder_.clear();
            }
            return this;
        }

        public Builder clearCaloriesV1() {
            if (this.caloriesV1Builder_ == null) {
                if (this.valueCase_ == 8) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                    onChanged();
                }
            } else {
                if (this.valueCase_ == 8) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                this.caloriesV1Builder_.clear();
            }
            return this;
        }

        public Builder clearHeartRateElevationV1() {
            if (this.heartRateElevationV1Builder_ == null) {
                if (this.valueCase_ == 11) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                    onChanged();
                }
            } else {
                if (this.valueCase_ == 11) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                this.heartRateElevationV1Builder_.clear();
            }
            return this;
        }

        public Builder clearHeartRateV1() {
            if (this.heartRateV1Builder_ == null) {
                if (this.valueCase_ == 1) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                    onChanged();
                }
            } else {
                if (this.valueCase_ == 1) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                this.heartRateV1Builder_.clear();
            }
            return this;
        }

        public Builder clearMovementSummaryV1() {
            if (this.movementSummaryV1Builder_ == null) {
                if (this.valueCase_ == 10) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                    onChanged();
                }
            } else {
                if (this.valueCase_ == 10) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                this.movementSummaryV1Builder_.clear();
            }
            return this;
        }

        public Builder clearRrIntervalV1() {
            if (this.rrIntervalV1Builder_ == null) {
                if (this.valueCase_ == 2) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                    onChanged();
                }
            } else {
                if (this.valueCase_ == 2) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                this.rrIntervalV1Builder_.clear();
            }
            return this;
        }

        public Builder clearSleepStateV1() {
            if (this.sleepStateV1Builder_ == null) {
                if (this.valueCase_ == 9) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                    onChanged();
                }
            } else {
                if (this.valueCase_ == 9) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                this.sleepStateV1Builder_.clear();
            }
            return this;
        }

        public Builder clearStepCountV1() {
            if (this.stepCountV1Builder_ == null) {
                if (this.valueCase_ == 4) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                    onChanged();
                }
            } else {
                if (this.valueCase_ == 4) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                this.stepCountV1Builder_.clear();
            }
            return this;
        }

        public Builder clearValue() {
            this.valueCase_ = 0;
            this.value_ = null;
            onChanged();
            return this;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public ActivityIntensityV1 getActivityIntensityV1() {
            if (this.valueCase_ == 7) {
                ActivityIntensityV1 valueOf = ActivityIntensityV1.valueOf(((Integer) this.value_).intValue());
                return valueOf == null ? ActivityIntensityV1.UNRECOGNIZED : valueOf;
            }
            return ActivityIntensityV1.ACTIVITY_INTENSITY_UNKNOWN;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public int getActivityIntensityV1Value() {
            if (this.valueCase_ == 7) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public ActivityV1 getActivityV1() {
            if (this.valueCase_ == 5) {
                ActivityV1 valueOf = ActivityV1.valueOf(((Integer) this.value_).intValue());
                return valueOf == null ? ActivityV1.UNRECOGNIZED : valueOf;
            }
            return ActivityV1.ACTIVITY_UNKNOWN;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public int getActivityV1Value() {
            if (this.valueCase_ == 5) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public BodyTemperatureV1 getBodyTemperatureV1() {
            SingleFieldBuilderV3<BodyTemperatureV1, BodyTemperatureV1.Builder, BodyTemperatureV1OrBuilder> singleFieldBuilderV3 = this.bodyTemperatureV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 6) {
                    return (BodyTemperatureV1) this.value_;
                }
                return BodyTemperatureV1.getDefaultInstance();
            } else if (this.valueCase_ == 6) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return BodyTemperatureV1.getDefaultInstance();
            }
        }

        public BodyTemperatureV1.Builder getBodyTemperatureV1Builder() {
            return getBodyTemperatureV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public BodyTemperatureV1OrBuilder getBodyTemperatureV1OrBuilder() {
            SingleFieldBuilderV3<BodyTemperatureV1, BodyTemperatureV1.Builder, BodyTemperatureV1OrBuilder> singleFieldBuilderV3;
            if (this.valueCase_ == 6 && (singleFieldBuilderV3 = this.bodyTemperatureV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.valueCase_ == 6) {
                return (BodyTemperatureV1) this.value_;
            }
            return BodyTemperatureV1.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public CadenceV1 getCadenceV1() {
            SingleFieldBuilderV3<CadenceV1, CadenceV1.Builder, CadenceV1OrBuilder> singleFieldBuilderV3 = this.cadenceV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 3) {
                    return (CadenceV1) this.value_;
                }
                return CadenceV1.getDefaultInstance();
            } else if (this.valueCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return CadenceV1.getDefaultInstance();
            }
        }

        public CadenceV1.Builder getCadenceV1Builder() {
            return getCadenceV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public CadenceV1OrBuilder getCadenceV1OrBuilder() {
            SingleFieldBuilderV3<CadenceV1, CadenceV1.Builder, CadenceV1OrBuilder> singleFieldBuilderV3;
            if (this.valueCase_ == 3 && (singleFieldBuilderV3 = this.cadenceV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.valueCase_ == 3) {
                return (CadenceV1) this.value_;
            }
            return CadenceV1.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public CaloriesV1 getCaloriesV1() {
            SingleFieldBuilderV3<CaloriesV1, CaloriesV1.Builder, CaloriesV1OrBuilder> singleFieldBuilderV3 = this.caloriesV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 8) {
                    return (CaloriesV1) this.value_;
                }
                return CaloriesV1.getDefaultInstance();
            } else if (this.valueCase_ == 8) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return CaloriesV1.getDefaultInstance();
            }
        }

        public CaloriesV1.Builder getCaloriesV1Builder() {
            return getCaloriesV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public CaloriesV1OrBuilder getCaloriesV1OrBuilder() {
            SingleFieldBuilderV3<CaloriesV1, CaloriesV1.Builder, CaloriesV1OrBuilder> singleFieldBuilderV3;
            if (this.valueCase_ == 8 && (singleFieldBuilderV3 = this.caloriesV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.valueCase_ == 8) {
                return (CaloriesV1) this.value_;
            }
            return CaloriesV1.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return Biometric.internal_static_BiometricDataPoint_descriptor;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public HeartRateElevationV1 getHeartRateElevationV1() {
            SingleFieldBuilderV3<HeartRateElevationV1, HeartRateElevationV1.Builder, HeartRateElevationV1OrBuilder> singleFieldBuilderV3 = this.heartRateElevationV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 11) {
                    return (HeartRateElevationV1) this.value_;
                }
                return HeartRateElevationV1.getDefaultInstance();
            } else if (this.valueCase_ == 11) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return HeartRateElevationV1.getDefaultInstance();
            }
        }

        public HeartRateElevationV1.Builder getHeartRateElevationV1Builder() {
            return getHeartRateElevationV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public HeartRateElevationV1OrBuilder getHeartRateElevationV1OrBuilder() {
            SingleFieldBuilderV3<HeartRateElevationV1, HeartRateElevationV1.Builder, HeartRateElevationV1OrBuilder> singleFieldBuilderV3;
            if (this.valueCase_ == 11 && (singleFieldBuilderV3 = this.heartRateElevationV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.valueCase_ == 11) {
                return (HeartRateElevationV1) this.value_;
            }
            return HeartRateElevationV1.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public HeartRateV1 getHeartRateV1() {
            SingleFieldBuilderV3<HeartRateV1, HeartRateV1.Builder, HeartRateV1OrBuilder> singleFieldBuilderV3 = this.heartRateV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 1) {
                    return (HeartRateV1) this.value_;
                }
                return HeartRateV1.getDefaultInstance();
            } else if (this.valueCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return HeartRateV1.getDefaultInstance();
            }
        }

        public HeartRateV1.Builder getHeartRateV1Builder() {
            return getHeartRateV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public HeartRateV1OrBuilder getHeartRateV1OrBuilder() {
            SingleFieldBuilderV3<HeartRateV1, HeartRateV1.Builder, HeartRateV1OrBuilder> singleFieldBuilderV3;
            if (this.valueCase_ == 1 && (singleFieldBuilderV3 = this.heartRateV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.valueCase_ == 1) {
                return (HeartRateV1) this.value_;
            }
            return HeartRateV1.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public MovementSummaryV1 getMovementSummaryV1() {
            SingleFieldBuilderV3<MovementSummaryV1, MovementSummaryV1.Builder, MovementSummaryV1OrBuilder> singleFieldBuilderV3 = this.movementSummaryV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 10) {
                    return (MovementSummaryV1) this.value_;
                }
                return MovementSummaryV1.getDefaultInstance();
            } else if (this.valueCase_ == 10) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return MovementSummaryV1.getDefaultInstance();
            }
        }

        public MovementSummaryV1.Builder getMovementSummaryV1Builder() {
            return getMovementSummaryV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public MovementSummaryV1OrBuilder getMovementSummaryV1OrBuilder() {
            SingleFieldBuilderV3<MovementSummaryV1, MovementSummaryV1.Builder, MovementSummaryV1OrBuilder> singleFieldBuilderV3;
            if (this.valueCase_ == 10 && (singleFieldBuilderV3 = this.movementSummaryV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.valueCase_ == 10) {
                return (MovementSummaryV1) this.value_;
            }
            return MovementSummaryV1.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public RRIntervalV1 getRrIntervalV1() {
            SingleFieldBuilderV3<RRIntervalV1, RRIntervalV1.Builder, RRIntervalV1OrBuilder> singleFieldBuilderV3 = this.rrIntervalV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 2) {
                    return (RRIntervalV1) this.value_;
                }
                return RRIntervalV1.getDefaultInstance();
            } else if (this.valueCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return RRIntervalV1.getDefaultInstance();
            }
        }

        public RRIntervalV1.Builder getRrIntervalV1Builder() {
            return getRrIntervalV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public RRIntervalV1OrBuilder getRrIntervalV1OrBuilder() {
            SingleFieldBuilderV3<RRIntervalV1, RRIntervalV1.Builder, RRIntervalV1OrBuilder> singleFieldBuilderV3;
            if (this.valueCase_ == 2 && (singleFieldBuilderV3 = this.rrIntervalV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.valueCase_ == 2) {
                return (RRIntervalV1) this.value_;
            }
            return RRIntervalV1.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public SleepStateV1 getSleepStateV1() {
            SingleFieldBuilderV3<SleepStateV1, SleepStateV1.Builder, SleepStateV1OrBuilder> singleFieldBuilderV3 = this.sleepStateV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 9) {
                    return (SleepStateV1) this.value_;
                }
                return SleepStateV1.getDefaultInstance();
            } else if (this.valueCase_ == 9) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return SleepStateV1.getDefaultInstance();
            }
        }

        public SleepStateV1.Builder getSleepStateV1Builder() {
            return getSleepStateV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public SleepStateV1OrBuilder getSleepStateV1OrBuilder() {
            SingleFieldBuilderV3<SleepStateV1, SleepStateV1.Builder, SleepStateV1OrBuilder> singleFieldBuilderV3;
            if (this.valueCase_ == 9 && (singleFieldBuilderV3 = this.sleepStateV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.valueCase_ == 9) {
                return (SleepStateV1) this.value_;
            }
            return SleepStateV1.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public StepCountV1 getStepCountV1() {
            SingleFieldBuilderV3<StepCountV1, StepCountV1.Builder, StepCountV1OrBuilder> singleFieldBuilderV3 = this.stepCountV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 4) {
                    return (StepCountV1) this.value_;
                }
                return StepCountV1.getDefaultInstance();
            } else if (this.valueCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return StepCountV1.getDefaultInstance();
            }
        }

        public StepCountV1.Builder getStepCountV1Builder() {
            return getStepCountV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public StepCountV1OrBuilder getStepCountV1OrBuilder() {
            SingleFieldBuilderV3<StepCountV1, StepCountV1.Builder, StepCountV1OrBuilder> singleFieldBuilderV3;
            if (this.valueCase_ == 4 && (singleFieldBuilderV3 = this.stepCountV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.valueCase_ == 4) {
                return (StepCountV1) this.value_;
            }
            return StepCountV1.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public ValueCase getValueCase() {
            return ValueCase.forNumber(this.valueCase_);
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public boolean hasBodyTemperatureV1() {
            return this.valueCase_ == 6;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public boolean hasCadenceV1() {
            return this.valueCase_ == 3;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public boolean hasCaloriesV1() {
            return this.valueCase_ == 8;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public boolean hasHeartRateElevationV1() {
            return this.valueCase_ == 11;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public boolean hasHeartRateV1() {
            return this.valueCase_ == 1;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public boolean hasMovementSummaryV1() {
            return this.valueCase_ == 10;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public boolean hasRrIntervalV1() {
            return this.valueCase_ == 2;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public boolean hasSleepStateV1() {
            return this.valueCase_ == 9;
        }

        @Override // com.amazon.wellness.io.format.abs.BiometricDataPointOrBuilder
        public boolean hasStepCountV1() {
            return this.valueCase_ == 4;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Biometric.internal_static_BiometricDataPoint_fieldAccessorTable.ensureFieldAccessorsInitialized(BiometricDataPoint.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeBodyTemperatureV1(BodyTemperatureV1 bodyTemperatureV1) {
            SingleFieldBuilderV3<BodyTemperatureV1, BodyTemperatureV1.Builder, BodyTemperatureV1OrBuilder> singleFieldBuilderV3 = this.bodyTemperatureV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 6 && this.value_ != BodyTemperatureV1.getDefaultInstance()) {
                    this.value_ = BodyTemperatureV1.newBuilder((BodyTemperatureV1) this.value_).mergeFrom(bodyTemperatureV1).mo10085buildPartial();
                } else {
                    this.value_ = bodyTemperatureV1;
                }
                onChanged();
            } else {
                if (this.valueCase_ == 6) {
                    singleFieldBuilderV3.mergeFrom(bodyTemperatureV1);
                }
                this.bodyTemperatureV1Builder_.setMessage(bodyTemperatureV1);
            }
            this.valueCase_ = 6;
            return this;
        }

        public Builder mergeCadenceV1(CadenceV1 cadenceV1) {
            SingleFieldBuilderV3<CadenceV1, CadenceV1.Builder, CadenceV1OrBuilder> singleFieldBuilderV3 = this.cadenceV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 3 && this.value_ != CadenceV1.getDefaultInstance()) {
                    this.value_ = CadenceV1.newBuilder((CadenceV1) this.value_).mergeFrom(cadenceV1).mo10085buildPartial();
                } else {
                    this.value_ = cadenceV1;
                }
                onChanged();
            } else {
                if (this.valueCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(cadenceV1);
                }
                this.cadenceV1Builder_.setMessage(cadenceV1);
            }
            this.valueCase_ = 3;
            return this;
        }

        public Builder mergeCaloriesV1(CaloriesV1 caloriesV1) {
            SingleFieldBuilderV3<CaloriesV1, CaloriesV1.Builder, CaloriesV1OrBuilder> singleFieldBuilderV3 = this.caloriesV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 8 && this.value_ != CaloriesV1.getDefaultInstance()) {
                    this.value_ = CaloriesV1.newBuilder((CaloriesV1) this.value_).mergeFrom(caloriesV1).mo10085buildPartial();
                } else {
                    this.value_ = caloriesV1;
                }
                onChanged();
            } else {
                if (this.valueCase_ == 8) {
                    singleFieldBuilderV3.mergeFrom(caloriesV1);
                }
                this.caloriesV1Builder_.setMessage(caloriesV1);
            }
            this.valueCase_ = 8;
            return this;
        }

        public Builder mergeHeartRateElevationV1(HeartRateElevationV1 heartRateElevationV1) {
            SingleFieldBuilderV3<HeartRateElevationV1, HeartRateElevationV1.Builder, HeartRateElevationV1OrBuilder> singleFieldBuilderV3 = this.heartRateElevationV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 11 && this.value_ != HeartRateElevationV1.getDefaultInstance()) {
                    this.value_ = HeartRateElevationV1.newBuilder((HeartRateElevationV1) this.value_).mergeFrom(heartRateElevationV1).mo10085buildPartial();
                } else {
                    this.value_ = heartRateElevationV1;
                }
                onChanged();
            } else {
                if (this.valueCase_ == 11) {
                    singleFieldBuilderV3.mergeFrom(heartRateElevationV1);
                }
                this.heartRateElevationV1Builder_.setMessage(heartRateElevationV1);
            }
            this.valueCase_ = 11;
            return this;
        }

        public Builder mergeHeartRateV1(HeartRateV1 heartRateV1) {
            SingleFieldBuilderV3<HeartRateV1, HeartRateV1.Builder, HeartRateV1OrBuilder> singleFieldBuilderV3 = this.heartRateV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 1 && this.value_ != HeartRateV1.getDefaultInstance()) {
                    this.value_ = HeartRateV1.newBuilder((HeartRateV1) this.value_).mergeFrom(heartRateV1).mo10085buildPartial();
                } else {
                    this.value_ = heartRateV1;
                }
                onChanged();
            } else {
                if (this.valueCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(heartRateV1);
                }
                this.heartRateV1Builder_.setMessage(heartRateV1);
            }
            this.valueCase_ = 1;
            return this;
        }

        public Builder mergeMovementSummaryV1(MovementSummaryV1 movementSummaryV1) {
            SingleFieldBuilderV3<MovementSummaryV1, MovementSummaryV1.Builder, MovementSummaryV1OrBuilder> singleFieldBuilderV3 = this.movementSummaryV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 10 && this.value_ != MovementSummaryV1.getDefaultInstance()) {
                    this.value_ = MovementSummaryV1.newBuilder((MovementSummaryV1) this.value_).mergeFrom(movementSummaryV1).mo10085buildPartial();
                } else {
                    this.value_ = movementSummaryV1;
                }
                onChanged();
            } else {
                if (this.valueCase_ == 10) {
                    singleFieldBuilderV3.mergeFrom(movementSummaryV1);
                }
                this.movementSummaryV1Builder_.setMessage(movementSummaryV1);
            }
            this.valueCase_ = 10;
            return this;
        }

        public Builder mergeRrIntervalV1(RRIntervalV1 rRIntervalV1) {
            SingleFieldBuilderV3<RRIntervalV1, RRIntervalV1.Builder, RRIntervalV1OrBuilder> singleFieldBuilderV3 = this.rrIntervalV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 2 && this.value_ != RRIntervalV1.getDefaultInstance()) {
                    this.value_ = RRIntervalV1.newBuilder((RRIntervalV1) this.value_).mergeFrom(rRIntervalV1).mo10085buildPartial();
                } else {
                    this.value_ = rRIntervalV1;
                }
                onChanged();
            } else {
                if (this.valueCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(rRIntervalV1);
                }
                this.rrIntervalV1Builder_.setMessage(rRIntervalV1);
            }
            this.valueCase_ = 2;
            return this;
        }

        public Builder mergeSleepStateV1(SleepStateV1 sleepStateV1) {
            SingleFieldBuilderV3<SleepStateV1, SleepStateV1.Builder, SleepStateV1OrBuilder> singleFieldBuilderV3 = this.sleepStateV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 9 && this.value_ != SleepStateV1.getDefaultInstance()) {
                    this.value_ = SleepStateV1.newBuilder((SleepStateV1) this.value_).mergeFrom(sleepStateV1).mo10085buildPartial();
                } else {
                    this.value_ = sleepStateV1;
                }
                onChanged();
            } else {
                if (this.valueCase_ == 9) {
                    singleFieldBuilderV3.mergeFrom(sleepStateV1);
                }
                this.sleepStateV1Builder_.setMessage(sleepStateV1);
            }
            this.valueCase_ = 9;
            return this;
        }

        public Builder mergeStepCountV1(StepCountV1 stepCountV1) {
            SingleFieldBuilderV3<StepCountV1, StepCountV1.Builder, StepCountV1OrBuilder> singleFieldBuilderV3 = this.stepCountV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 4 && this.value_ != StepCountV1.getDefaultInstance()) {
                    this.value_ = StepCountV1.newBuilder((StepCountV1) this.value_).mergeFrom(stepCountV1).mo10085buildPartial();
                } else {
                    this.value_ = stepCountV1;
                }
                onChanged();
            } else {
                if (this.valueCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(stepCountV1);
                }
                this.stepCountV1Builder_.setMessage(stepCountV1);
            }
            this.valueCase_ = 4;
            return this;
        }

        public Builder setActivityIntensityV1(ActivityIntensityV1 activityIntensityV1) {
            if (activityIntensityV1 != null) {
                this.valueCase_ = 7;
                this.value_ = Integer.valueOf(activityIntensityV1.getNumber());
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setActivityIntensityV1Value(int i) {
            this.valueCase_ = 7;
            this.value_ = Integer.valueOf(i);
            onChanged();
            return this;
        }

        public Builder setActivityV1(ActivityV1 activityV1) {
            if (activityV1 != null) {
                this.valueCase_ = 5;
                this.value_ = Integer.valueOf(activityV1.getNumber());
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setActivityV1Value(int i) {
            this.valueCase_ = 5;
            this.value_ = Integer.valueOf(i);
            onChanged();
            return this;
        }

        public Builder setBodyTemperatureV1(BodyTemperatureV1 bodyTemperatureV1) {
            SingleFieldBuilderV3<BodyTemperatureV1, BodyTemperatureV1.Builder, BodyTemperatureV1OrBuilder> singleFieldBuilderV3 = this.bodyTemperatureV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(bodyTemperatureV1);
            } else if (bodyTemperatureV1 != null) {
                this.value_ = bodyTemperatureV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.valueCase_ = 6;
            return this;
        }

        public Builder setCadenceV1(CadenceV1 cadenceV1) {
            SingleFieldBuilderV3<CadenceV1, CadenceV1.Builder, CadenceV1OrBuilder> singleFieldBuilderV3 = this.cadenceV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(cadenceV1);
            } else if (cadenceV1 != null) {
                this.value_ = cadenceV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.valueCase_ = 3;
            return this;
        }

        public Builder setCaloriesV1(CaloriesV1 caloriesV1) {
            SingleFieldBuilderV3<CaloriesV1, CaloriesV1.Builder, CaloriesV1OrBuilder> singleFieldBuilderV3 = this.caloriesV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(caloriesV1);
            } else if (caloriesV1 != null) {
                this.value_ = caloriesV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.valueCase_ = 8;
            return this;
        }

        public Builder setHeartRateElevationV1(HeartRateElevationV1 heartRateElevationV1) {
            SingleFieldBuilderV3<HeartRateElevationV1, HeartRateElevationV1.Builder, HeartRateElevationV1OrBuilder> singleFieldBuilderV3 = this.heartRateElevationV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(heartRateElevationV1);
            } else if (heartRateElevationV1 != null) {
                this.value_ = heartRateElevationV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.valueCase_ = 11;
            return this;
        }

        public Builder setHeartRateV1(HeartRateV1 heartRateV1) {
            SingleFieldBuilderV3<HeartRateV1, HeartRateV1.Builder, HeartRateV1OrBuilder> singleFieldBuilderV3 = this.heartRateV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(heartRateV1);
            } else if (heartRateV1 != null) {
                this.value_ = heartRateV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.valueCase_ = 1;
            return this;
        }

        public Builder setMovementSummaryV1(MovementSummaryV1 movementSummaryV1) {
            SingleFieldBuilderV3<MovementSummaryV1, MovementSummaryV1.Builder, MovementSummaryV1OrBuilder> singleFieldBuilderV3 = this.movementSummaryV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(movementSummaryV1);
            } else if (movementSummaryV1 != null) {
                this.value_ = movementSummaryV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.valueCase_ = 10;
            return this;
        }

        public Builder setRrIntervalV1(RRIntervalV1 rRIntervalV1) {
            SingleFieldBuilderV3<RRIntervalV1, RRIntervalV1.Builder, RRIntervalV1OrBuilder> singleFieldBuilderV3 = this.rrIntervalV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(rRIntervalV1);
            } else if (rRIntervalV1 != null) {
                this.value_ = rRIntervalV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.valueCase_ = 2;
            return this;
        }

        public Builder setSleepStateV1(SleepStateV1 sleepStateV1) {
            SingleFieldBuilderV3<SleepStateV1, SleepStateV1.Builder, SleepStateV1OrBuilder> singleFieldBuilderV3 = this.sleepStateV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sleepStateV1);
            } else if (sleepStateV1 != null) {
                this.value_ = sleepStateV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.valueCase_ = 9;
            return this;
        }

        public Builder setStepCountV1(StepCountV1 stepCountV1) {
            SingleFieldBuilderV3<StepCountV1, StepCountV1.Builder, StepCountV1OrBuilder> singleFieldBuilderV3 = this.stepCountV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(stepCountV1);
            } else if (stepCountV1 != null) {
                this.value_ = stepCountV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.valueCase_ = 4;
            return this;
        }

        private Builder() {
            this.valueCase_ = 0;
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public BiometricDataPoint mo10084build() {
            BiometricDataPoint mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public BiometricDataPoint mo10085buildPartial() {
            BiometricDataPoint biometricDataPoint = new BiometricDataPoint(this);
            if (this.valueCase_ == 1) {
                SingleFieldBuilderV3<HeartRateV1, HeartRateV1.Builder, HeartRateV1OrBuilder> singleFieldBuilderV3 = this.heartRateV1Builder_;
                if (singleFieldBuilderV3 == null) {
                    biometricDataPoint.value_ = this.value_;
                } else {
                    biometricDataPoint.value_ = singleFieldBuilderV3.build();
                }
            }
            if (this.valueCase_ == 2) {
                SingleFieldBuilderV3<RRIntervalV1, RRIntervalV1.Builder, RRIntervalV1OrBuilder> singleFieldBuilderV32 = this.rrIntervalV1Builder_;
                if (singleFieldBuilderV32 == null) {
                    biometricDataPoint.value_ = this.value_;
                } else {
                    biometricDataPoint.value_ = singleFieldBuilderV32.build();
                }
            }
            if (this.valueCase_ == 3) {
                SingleFieldBuilderV3<CadenceV1, CadenceV1.Builder, CadenceV1OrBuilder> singleFieldBuilderV33 = this.cadenceV1Builder_;
                if (singleFieldBuilderV33 == null) {
                    biometricDataPoint.value_ = this.value_;
                } else {
                    biometricDataPoint.value_ = singleFieldBuilderV33.build();
                }
            }
            if (this.valueCase_ == 4) {
                SingleFieldBuilderV3<StepCountV1, StepCountV1.Builder, StepCountV1OrBuilder> singleFieldBuilderV34 = this.stepCountV1Builder_;
                if (singleFieldBuilderV34 == null) {
                    biometricDataPoint.value_ = this.value_;
                } else {
                    biometricDataPoint.value_ = singleFieldBuilderV34.build();
                }
            }
            if (this.valueCase_ == 5) {
                biometricDataPoint.value_ = this.value_;
            }
            if (this.valueCase_ == 6) {
                SingleFieldBuilderV3<BodyTemperatureV1, BodyTemperatureV1.Builder, BodyTemperatureV1OrBuilder> singleFieldBuilderV35 = this.bodyTemperatureV1Builder_;
                if (singleFieldBuilderV35 == null) {
                    biometricDataPoint.value_ = this.value_;
                } else {
                    biometricDataPoint.value_ = singleFieldBuilderV35.build();
                }
            }
            if (this.valueCase_ == 7) {
                biometricDataPoint.value_ = this.value_;
            }
            if (this.valueCase_ == 8) {
                SingleFieldBuilderV3<CaloriesV1, CaloriesV1.Builder, CaloriesV1OrBuilder> singleFieldBuilderV36 = this.caloriesV1Builder_;
                if (singleFieldBuilderV36 == null) {
                    biometricDataPoint.value_ = this.value_;
                } else {
                    biometricDataPoint.value_ = singleFieldBuilderV36.build();
                }
            }
            if (this.valueCase_ == 9) {
                SingleFieldBuilderV3<SleepStateV1, SleepStateV1.Builder, SleepStateV1OrBuilder> singleFieldBuilderV37 = this.sleepStateV1Builder_;
                if (singleFieldBuilderV37 == null) {
                    biometricDataPoint.value_ = this.value_;
                } else {
                    biometricDataPoint.value_ = singleFieldBuilderV37.build();
                }
            }
            if (this.valueCase_ == 10) {
                SingleFieldBuilderV3<MovementSummaryV1, MovementSummaryV1.Builder, MovementSummaryV1OrBuilder> singleFieldBuilderV38 = this.movementSummaryV1Builder_;
                if (singleFieldBuilderV38 == null) {
                    biometricDataPoint.value_ = this.value_;
                } else {
                    biometricDataPoint.value_ = singleFieldBuilderV38.build();
                }
            }
            if (this.valueCase_ == 11) {
                SingleFieldBuilderV3<HeartRateElevationV1, HeartRateElevationV1.Builder, HeartRateElevationV1OrBuilder> singleFieldBuilderV39 = this.heartRateElevationV1Builder_;
                if (singleFieldBuilderV39 == null) {
                    biometricDataPoint.value_ = this.value_;
                } else {
                    biometricDataPoint.value_ = singleFieldBuilderV39.build();
                }
            }
            biometricDataPoint.valueCase_ = this.valueCase_;
            onBuilt();
            return biometricDataPoint;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public BiometricDataPoint mo10094getDefaultInstanceForType() {
            return BiometricDataPoint.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setField */
        public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10100setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setRepeatedField */
        public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setUnknownFields */
        public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearOneof */
        public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo10090clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeUnknownFields */
        public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clear */
        public Builder mo10087clear() {
            super.mo10087clear();
            this.valueCase_ = 0;
            this.value_ = null;
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.valueCase_ = 0;
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone */
        public Builder mo10093clone() {
            return (Builder) super.mo10093clone();
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public Builder mo10096mergeFrom(Message message) {
            if (message instanceof BiometricDataPoint) {
                return mergeFrom((BiometricDataPoint) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder setBodyTemperatureV1(BodyTemperatureV1.Builder builder) {
            SingleFieldBuilderV3<BodyTemperatureV1, BodyTemperatureV1.Builder, BodyTemperatureV1OrBuilder> singleFieldBuilderV3 = this.bodyTemperatureV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.valueCase_ = 6;
            return this;
        }

        public Builder setCadenceV1(CadenceV1.Builder builder) {
            SingleFieldBuilderV3<CadenceV1, CadenceV1.Builder, CadenceV1OrBuilder> singleFieldBuilderV3 = this.cadenceV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.valueCase_ = 3;
            return this;
        }

        public Builder setCaloriesV1(CaloriesV1.Builder builder) {
            SingleFieldBuilderV3<CaloriesV1, CaloriesV1.Builder, CaloriesV1OrBuilder> singleFieldBuilderV3 = this.caloriesV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.valueCase_ = 8;
            return this;
        }

        public Builder setHeartRateElevationV1(HeartRateElevationV1.Builder builder) {
            SingleFieldBuilderV3<HeartRateElevationV1, HeartRateElevationV1.Builder, HeartRateElevationV1OrBuilder> singleFieldBuilderV3 = this.heartRateElevationV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.valueCase_ = 11;
            return this;
        }

        public Builder setHeartRateV1(HeartRateV1.Builder builder) {
            SingleFieldBuilderV3<HeartRateV1, HeartRateV1.Builder, HeartRateV1OrBuilder> singleFieldBuilderV3 = this.heartRateV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.valueCase_ = 1;
            return this;
        }

        public Builder setMovementSummaryV1(MovementSummaryV1.Builder builder) {
            SingleFieldBuilderV3<MovementSummaryV1, MovementSummaryV1.Builder, MovementSummaryV1OrBuilder> singleFieldBuilderV3 = this.movementSummaryV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.valueCase_ = 10;
            return this;
        }

        public Builder setRrIntervalV1(RRIntervalV1.Builder builder) {
            SingleFieldBuilderV3<RRIntervalV1, RRIntervalV1.Builder, RRIntervalV1OrBuilder> singleFieldBuilderV3 = this.rrIntervalV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.valueCase_ = 2;
            return this;
        }

        public Builder setSleepStateV1(SleepStateV1.Builder builder) {
            SingleFieldBuilderV3<SleepStateV1, SleepStateV1.Builder, SleepStateV1OrBuilder> singleFieldBuilderV3 = this.sleepStateV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.valueCase_ = 9;
            return this;
        }

        public Builder setStepCountV1(StepCountV1.Builder builder) {
            SingleFieldBuilderV3<StepCountV1, StepCountV1.Builder, StepCountV1OrBuilder> singleFieldBuilderV3 = this.stepCountV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.valueCase_ = 4;
            return this;
        }

        public Builder mergeFrom(BiometricDataPoint biometricDataPoint) {
            if (biometricDataPoint == BiometricDataPoint.getDefaultInstance()) {
                return this;
            }
            switch (biometricDataPoint.getValueCase().ordinal()) {
                case 0:
                    mergeHeartRateV1(biometricDataPoint.getHeartRateV1());
                    break;
                case 1:
                    mergeRrIntervalV1(biometricDataPoint.getRrIntervalV1());
                    break;
                case 2:
                    mergeCadenceV1(biometricDataPoint.getCadenceV1());
                    break;
                case 3:
                    mergeStepCountV1(biometricDataPoint.getStepCountV1());
                    break;
                case 4:
                    setActivityV1Value(biometricDataPoint.getActivityV1Value());
                    break;
                case 5:
                    mergeBodyTemperatureV1(biometricDataPoint.getBodyTemperatureV1());
                    break;
                case 6:
                    setActivityIntensityV1Value(biometricDataPoint.getActivityIntensityV1Value());
                    break;
                case 7:
                    mergeCaloriesV1(biometricDataPoint.getCaloriesV1());
                    break;
                case 8:
                    mergeSleepStateV1(biometricDataPoint.getSleepStateV1());
                    break;
                case 9:
                    mergeMovementSummaryV1(biometricDataPoint.getMovementSummaryV1());
                    break;
                case 10:
                    mergeHeartRateElevationV1(biometricDataPoint.getHeartRateElevationV1());
                    break;
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) biometricDataPoint).unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.amazon.wellness.io.format.abs.BiometricDataPoint.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.wellness.io.format.abs.BiometricDataPoint.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.wellness.io.format.abs.BiometricDataPoint r3 = (com.amazon.wellness.io.format.abs.BiometricDataPoint) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                com.amazon.wellness.io.format.abs.BiometricDataPoint r4 = (com.amazon.wellness.io.format.abs.BiometricDataPoint) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.wellness.io.format.abs.BiometricDataPoint.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.wellness.io.format.abs.BiometricDataPoint$Builder");
        }
    }

    public static Builder newBuilder(BiometricDataPoint biometricDataPoint) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(biometricDataPoint);
    }

    public static BiometricDataPoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private BiometricDataPoint(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    public static BiometricDataPoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BiometricDataPoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BiometricDataPoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public BiometricDataPoint mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static BiometricDataPoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    public static BiometricDataPoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private BiometricDataPoint() {
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    public static BiometricDataPoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static BiometricDataPoint parseFrom(InputStream inputStream) throws IOException {
        return (BiometricDataPoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    private BiometricDataPoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        HeartRateV1.Builder builder = null;
                        HeartRateElevationV1.Builder mo10081toBuilder = null;
                        MovementSummaryV1.Builder mo10081toBuilder2 = null;
                        SleepStateV1.Builder mo10081toBuilder3 = null;
                        CaloriesV1.Builder mo10081toBuilder4 = null;
                        BodyTemperatureV1.Builder mo10081toBuilder5 = null;
                        StepCountV1.Builder mo10081toBuilder6 = null;
                        CadenceV1.Builder mo10081toBuilder7 = null;
                        RRIntervalV1.Builder mo10081toBuilder8 = null;
                        switch (readTag) {
                            case 0:
                                break;
                            case 10:
                                builder = this.valueCase_ == 1 ? ((HeartRateV1) this.value_).mo10081toBuilder() : builder;
                                this.value_ = codedInputStream.readMessage(HeartRateV1.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((HeartRateV1) this.value_);
                                    this.value_ = builder.mo10085buildPartial();
                                }
                                this.valueCase_ = 1;
                                continue;
                            case 18:
                                mo10081toBuilder8 = this.valueCase_ == 2 ? ((RRIntervalV1) this.value_).mo10081toBuilder() : mo10081toBuilder8;
                                this.value_ = codedInputStream.readMessage(RRIntervalV1.parser(), extensionRegistryLite);
                                if (mo10081toBuilder8 != null) {
                                    mo10081toBuilder8.mergeFrom((RRIntervalV1) this.value_);
                                    this.value_ = mo10081toBuilder8.mo10085buildPartial();
                                }
                                this.valueCase_ = 2;
                                continue;
                            case 26:
                                mo10081toBuilder7 = this.valueCase_ == 3 ? ((CadenceV1) this.value_).mo10081toBuilder() : mo10081toBuilder7;
                                this.value_ = codedInputStream.readMessage(CadenceV1.parser(), extensionRegistryLite);
                                if (mo10081toBuilder7 != null) {
                                    mo10081toBuilder7.mergeFrom((CadenceV1) this.value_);
                                    this.value_ = mo10081toBuilder7.mo10085buildPartial();
                                }
                                this.valueCase_ = 3;
                                continue;
                            case 34:
                                mo10081toBuilder6 = this.valueCase_ == 4 ? ((StepCountV1) this.value_).mo10081toBuilder() : mo10081toBuilder6;
                                this.value_ = codedInputStream.readMessage(StepCountV1.parser(), extensionRegistryLite);
                                if (mo10081toBuilder6 != null) {
                                    mo10081toBuilder6.mergeFrom((StepCountV1) this.value_);
                                    this.value_ = mo10081toBuilder6.mo10085buildPartial();
                                }
                                this.valueCase_ = 4;
                                continue;
                            case 40:
                                int readEnum = codedInputStream.readEnum();
                                this.valueCase_ = 5;
                                this.value_ = Integer.valueOf(readEnum);
                                continue;
                            case 50:
                                mo10081toBuilder5 = this.valueCase_ == 6 ? ((BodyTemperatureV1) this.value_).mo10081toBuilder() : mo10081toBuilder5;
                                this.value_ = codedInputStream.readMessage(BodyTemperatureV1.parser(), extensionRegistryLite);
                                if (mo10081toBuilder5 != null) {
                                    mo10081toBuilder5.mergeFrom((BodyTemperatureV1) this.value_);
                                    this.value_ = mo10081toBuilder5.mo10085buildPartial();
                                }
                                this.valueCase_ = 6;
                                continue;
                            case 56:
                                int readEnum2 = codedInputStream.readEnum();
                                this.valueCase_ = 7;
                                this.value_ = Integer.valueOf(readEnum2);
                                continue;
                            case 66:
                                mo10081toBuilder4 = this.valueCase_ == 8 ? ((CaloriesV1) this.value_).mo10081toBuilder() : mo10081toBuilder4;
                                this.value_ = codedInputStream.readMessage(CaloriesV1.parser(), extensionRegistryLite);
                                if (mo10081toBuilder4 != null) {
                                    mo10081toBuilder4.mergeFrom((CaloriesV1) this.value_);
                                    this.value_ = mo10081toBuilder4.mo10085buildPartial();
                                }
                                this.valueCase_ = 8;
                                continue;
                            case 74:
                                mo10081toBuilder3 = this.valueCase_ == 9 ? ((SleepStateV1) this.value_).mo10081toBuilder() : mo10081toBuilder3;
                                this.value_ = codedInputStream.readMessage(SleepStateV1.parser(), extensionRegistryLite);
                                if (mo10081toBuilder3 != null) {
                                    mo10081toBuilder3.mergeFrom((SleepStateV1) this.value_);
                                    this.value_ = mo10081toBuilder3.mo10085buildPartial();
                                }
                                this.valueCase_ = 9;
                                continue;
                            case 82:
                                mo10081toBuilder2 = this.valueCase_ == 10 ? ((MovementSummaryV1) this.value_).mo10081toBuilder() : mo10081toBuilder2;
                                this.value_ = codedInputStream.readMessage(MovementSummaryV1.parser(), extensionRegistryLite);
                                if (mo10081toBuilder2 != null) {
                                    mo10081toBuilder2.mergeFrom((MovementSummaryV1) this.value_);
                                    this.value_ = mo10081toBuilder2.mo10085buildPartial();
                                }
                                this.valueCase_ = 10;
                                continue;
                            case 90:
                                mo10081toBuilder = this.valueCase_ == 11 ? ((HeartRateElevationV1) this.value_).mo10081toBuilder() : mo10081toBuilder;
                                this.value_ = codedInputStream.readMessage(HeartRateElevationV1.parser(), extensionRegistryLite);
                                if (mo10081toBuilder != null) {
                                    mo10081toBuilder.mergeFrom((HeartRateElevationV1) this.value_);
                                    this.value_ = mo10081toBuilder.mo10085buildPartial();
                                }
                                this.valueCase_ = 11;
                                continue;
                            default:
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    break;
                                } else {
                                    continue;
                                }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.mo10084build();
                    makeExtensionsImmutable();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    public static BiometricDataPoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BiometricDataPoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BiometricDataPoint parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BiometricDataPoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BiometricDataPoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BiometricDataPoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
