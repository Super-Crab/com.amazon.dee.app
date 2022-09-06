package com.amazon.wellness.io.format.abs;

import com.amazon.wellness.io.format.abs.BiometricDataPoint;
import com.amazon.wellness.io.format.abs.SleepDomainTransitionEventV1;
import com.amazon.wellness.io.format.abs.SynchronizeRealTime;
import com.amazon.wellness.io.format.abs.SynchronizeUptime;
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
public final class Event extends GeneratedMessageV3 implements EventOrBuilder {
    public static final int ABSOLUTE_UPTIME_MILLISECONDS_HI_FIELD_NUMBER = 2;
    public static final int ABSOLUTE_UPTIME_MILLISECONDS_LO_FIELD_NUMBER = 1;
    public static final int BIOMETRIC_DATA_POINT_FIELD_NUMBER = 4;
    private static final Event DEFAULT_INSTANCE = new Event();
    private static final Parser<Event> PARSER = new AbstractParser<Event>() { // from class: com.amazon.wellness.io.format.abs.Event.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public Event mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Event(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RELATIVE_UPTIME_MILLISECONDS_FIELD_NUMBER = 3;
    public static final int SLEEP_DOMAIN_TRANSITION_EVENT_V1_FIELD_NUMBER = 7;
    public static final int SYNCHRONIZE_REAL_TIME_FIELD_NUMBER = 5;
    public static final int SYNCHRONIZE_UPTIME_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private int absoluteUptimeMillisecondsHi_;
    private int absoluteUptimeMillisecondsLo_;
    private byte memoizedIsInitialized;
    private int payloadCase_;
    private Object payload_;
    private int relativeUptimeMilliseconds_;

    /* renamed from: com.amazon.wellness.io.format.abs.Event$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$wellness$io$format$abs$Event$PayloadCase = new int[PayloadCase.values().length];

        static {
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$Event$PayloadCase[PayloadCase.BIOMETRIC_DATA_POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$Event$PayloadCase[PayloadCase.SYNCHRONIZE_REAL_TIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$Event$PayloadCase[PayloadCase.SYNCHRONIZE_UPTIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$Event$PayloadCase[PayloadCase.SLEEP_DOMAIN_TRANSITION_EVENT_V1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$wellness$io$format$abs$Event$PayloadCase[PayloadCase.PAYLOAD_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public enum PayloadCase implements Internal.EnumLite {
        BIOMETRIC_DATA_POINT(4),
        SYNCHRONIZE_REAL_TIME(5),
        SYNCHRONIZE_UPTIME(6),
        SLEEP_DOMAIN_TRANSITION_EVENT_V1(7),
        PAYLOAD_NOT_SET(0);
        
        private final int value;

        PayloadCase(int i) {
            this.value = i;
        }

        public static PayloadCase forNumber(int i) {
            if (i != 0) {
                if (i == 4) {
                    return BIOMETRIC_DATA_POINT;
                }
                if (i == 5) {
                    return SYNCHRONIZE_REAL_TIME;
                }
                if (i == 6) {
                    return SYNCHRONIZE_UPTIME;
                }
                if (i == 7) {
                    return SLEEP_DOMAIN_TRANSITION_EVENT_V1;
                }
                return null;
            }
            return PAYLOAD_NOT_SET;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PayloadCase valueOf(int i) {
            return forNumber(i);
        }
    }

    public static Event getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EventOuterClass.internal_static_Event_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static Event parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Event) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Event parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<Event> parser() {
        return PARSER;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x006e, code lost:
        if (getSleepDomainTransitionEventV1().equals(r6.getSleepDomainTransitionEventV1()) != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0070, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0082, code lost:
        if (getSynchronizeUptime().equals(r6.getSynchronizeUptime()) != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0093, code lost:
        if (getSynchronizeRealTime().equals(r6.getSynchronizeRealTime()) != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00a4, code lost:
        if (getBiometricDataPoint().equals(r6.getBiometricDataPoint()) != false) goto L37;
     */
    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L4
            return r0
        L4:
            boolean r1 = r6 instanceof com.amazon.wellness.io.format.abs.Event
            if (r1 != 0) goto Ld
            boolean r6 = super.equals(r6)
            return r6
        Ld:
            com.amazon.wellness.io.format.abs.Event r6 = (com.amazon.wellness.io.format.abs.Event) r6
            int r1 = r5.getAbsoluteUptimeMillisecondsLo()
            int r2 = r6.getAbsoluteUptimeMillisecondsLo()
            r3 = 0
            if (r1 != r2) goto L1c
            r1 = r0
            goto L1d
        L1c:
            r1 = r3
        L1d:
            if (r1 == 0) goto L2b
            int r1 = r5.getAbsoluteUptimeMillisecondsHi()
            int r2 = r6.getAbsoluteUptimeMillisecondsHi()
            if (r1 != r2) goto L2b
            r1 = r0
            goto L2c
        L2b:
            r1 = r3
        L2c:
            if (r1 == 0) goto L3a
            int r1 = r5.getRelativeUptimeMilliseconds()
            int r2 = r6.getRelativeUptimeMilliseconds()
            if (r1 != r2) goto L3a
            r1 = r0
            goto L3b
        L3a:
            r1 = r3
        L3b:
            if (r1 == 0) goto L4d
            com.amazon.wellness.io.format.abs.Event$PayloadCase r1 = r5.getPayloadCase()
            com.amazon.wellness.io.format.abs.Event$PayloadCase r2 = r6.getPayloadCase()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L4d
            r1 = r0
            goto L4e
        L4d:
            r1 = r3
        L4e:
            if (r1 != 0) goto L51
            return r3
        L51:
            int r2 = r5.payloadCase_
            r4 = 4
            if (r2 == r4) goto L96
            r4 = 5
            if (r2 == r4) goto L85
            r4 = 6
            if (r2 == r4) goto L74
            r4 = 7
            if (r2 == r4) goto L60
            goto La7
        L60:
            if (r1 == 0) goto L72
            com.amazon.wellness.io.format.abs.SleepDomainTransitionEventV1 r1 = r5.getSleepDomainTransitionEventV1()
            com.amazon.wellness.io.format.abs.SleepDomainTransitionEventV1 r2 = r6.getSleepDomainTransitionEventV1()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L72
        L70:
            r1 = r0
            goto La7
        L72:
            r1 = r3
            goto La7
        L74:
            if (r1 == 0) goto L72
            com.amazon.wellness.io.format.abs.SynchronizeUptime r1 = r5.getSynchronizeUptime()
            com.amazon.wellness.io.format.abs.SynchronizeUptime r2 = r6.getSynchronizeUptime()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L72
            goto L70
        L85:
            if (r1 == 0) goto L72
            com.amazon.wellness.io.format.abs.SynchronizeRealTime r1 = r5.getSynchronizeRealTime()
            com.amazon.wellness.io.format.abs.SynchronizeRealTime r2 = r6.getSynchronizeRealTime()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L72
            goto L70
        L96:
            if (r1 == 0) goto L72
            com.amazon.wellness.io.format.abs.BiometricDataPoint r1 = r5.getBiometricDataPoint()
            com.amazon.wellness.io.format.abs.BiometricDataPoint r2 = r6.getBiometricDataPoint()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L72
            goto L70
        La7:
            if (r1 == 0) goto Lb4
            com.google.protobuf.UnknownFieldSet r1 = r5.unknownFields
            com.google.protobuf.UnknownFieldSet r6 = r6.unknownFields
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto Lb4
            goto Lb5
        Lb4:
            r0 = r3
        Lb5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.wellness.io.format.abs.Event.equals(java.lang.Object):boolean");
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public int getAbsoluteUptimeMillisecondsHi() {
        return this.absoluteUptimeMillisecondsHi_;
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public int getAbsoluteUptimeMillisecondsLo() {
        return this.absoluteUptimeMillisecondsLo_;
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public BiometricDataPoint getBiometricDataPoint() {
        if (this.payloadCase_ == 4) {
            return (BiometricDataPoint) this.payload_;
        }
        return BiometricDataPoint.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public BiometricDataPointOrBuilder getBiometricDataPointOrBuilder() {
        if (this.payloadCase_ == 4) {
            return (BiometricDataPoint) this.payload_;
        }
        return BiometricDataPoint.getDefaultInstance();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<Event> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public PayloadCase getPayloadCase() {
        return PayloadCase.forNumber(this.payloadCase_);
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public int getRelativeUptimeMilliseconds() {
        return this.relativeUptimeMilliseconds_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int i3 = this.absoluteUptimeMillisecondsLo_;
        if (i3 != 0) {
            i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
        }
        int i4 = this.absoluteUptimeMillisecondsHi_;
        if (i4 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(2, i4);
        }
        int i5 = this.relativeUptimeMilliseconds_;
        if (i5 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(3, i5);
        }
        if (this.payloadCase_ == 4) {
            i2 += CodedOutputStream.computeMessageSize(4, (BiometricDataPoint) this.payload_);
        }
        if (this.payloadCase_ == 5) {
            i2 += CodedOutputStream.computeMessageSize(5, (SynchronizeRealTime) this.payload_);
        }
        if (this.payloadCase_ == 6) {
            i2 += CodedOutputStream.computeMessageSize(6, (SynchronizeUptime) this.payload_);
        }
        if (this.payloadCase_ == 7) {
            i2 += CodedOutputStream.computeMessageSize(7, (SleepDomainTransitionEventV1) this.payload_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public SleepDomainTransitionEventV1 getSleepDomainTransitionEventV1() {
        if (this.payloadCase_ == 7) {
            return (SleepDomainTransitionEventV1) this.payload_;
        }
        return SleepDomainTransitionEventV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public SleepDomainTransitionEventV1OrBuilder getSleepDomainTransitionEventV1OrBuilder() {
        if (this.payloadCase_ == 7) {
            return (SleepDomainTransitionEventV1) this.payload_;
        }
        return SleepDomainTransitionEventV1.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public SynchronizeRealTime getSynchronizeRealTime() {
        if (this.payloadCase_ == 5) {
            return (SynchronizeRealTime) this.payload_;
        }
        return SynchronizeRealTime.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public SynchronizeRealTimeOrBuilder getSynchronizeRealTimeOrBuilder() {
        if (this.payloadCase_ == 5) {
            return (SynchronizeRealTime) this.payload_;
        }
        return SynchronizeRealTime.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public SynchronizeUptime getSynchronizeUptime() {
        if (this.payloadCase_ == 6) {
            return (SynchronizeUptime) this.payload_;
        }
        return SynchronizeUptime.getDefaultInstance();
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public SynchronizeUptimeOrBuilder getSynchronizeUptimeOrBuilder() {
        if (this.payloadCase_ == 6) {
            return (SynchronizeUptime) this.payload_;
        }
        return SynchronizeUptime.getDefaultInstance();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public boolean hasBiometricDataPoint() {
        return this.payloadCase_ == 4;
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public boolean hasSleepDomainTransitionEventV1() {
        return this.payloadCase_ == 7;
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public boolean hasSynchronizeRealTime() {
        return this.payloadCase_ == 5;
    }

    @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
    public boolean hasSynchronizeUptime() {
        return this.payloadCase_ == 6;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int outline4;
        int hashCode;
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int absoluteUptimeMillisecondsLo = getAbsoluteUptimeMillisecondsLo();
        int absoluteUptimeMillisecondsHi = getAbsoluteUptimeMillisecondsHi();
        int relativeUptimeMilliseconds = getRelativeUptimeMilliseconds() + ((((absoluteUptimeMillisecondsHi + ((((absoluteUptimeMillisecondsLo + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53);
        int i2 = this.payloadCase_;
        if (i2 == 4) {
            outline4 = GeneratedOutlineSupport1.outline4(relativeUptimeMilliseconds, 37, 4, 53);
            hashCode = getBiometricDataPoint().hashCode();
        } else if (i2 == 5) {
            outline4 = GeneratedOutlineSupport1.outline4(relativeUptimeMilliseconds, 37, 5, 53);
            hashCode = getSynchronizeRealTime().hashCode();
        } else if (i2 == 6) {
            outline4 = GeneratedOutlineSupport1.outline4(relativeUptimeMilliseconds, 37, 6, 53);
            hashCode = getSynchronizeUptime().hashCode();
        } else {
            if (i2 == 7) {
                outline4 = GeneratedOutlineSupport1.outline4(relativeUptimeMilliseconds, 37, 7, 53);
                hashCode = getSleepDomainTransitionEventV1().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (relativeUptimeMilliseconds * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }
        relativeUptimeMilliseconds = hashCode + outline4;
        int hashCode22 = this.unknownFields.hashCode() + (relativeUptimeMilliseconds * 29);
        this.memoizedHashCode = hashCode22;
        return hashCode22;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EventOuterClass.internal_static_Event_fieldAccessorTable.ensureFieldAccessorsInitialized(Event.class, Builder.class);
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
        int i = this.absoluteUptimeMillisecondsLo_;
        if (i != 0) {
            codedOutputStream.writeUInt32(1, i);
        }
        int i2 = this.absoluteUptimeMillisecondsHi_;
        if (i2 != 0) {
            codedOutputStream.writeUInt32(2, i2);
        }
        int i3 = this.relativeUptimeMilliseconds_;
        if (i3 != 0) {
            codedOutputStream.writeUInt32(3, i3);
        }
        if (this.payloadCase_ == 4) {
            codedOutputStream.writeMessage(4, (BiometricDataPoint) this.payload_);
        }
        if (this.payloadCase_ == 5) {
            codedOutputStream.writeMessage(5, (SynchronizeRealTime) this.payload_);
        }
        if (this.payloadCase_ == 6) {
            codedOutputStream.writeMessage(6, (SynchronizeUptime) this.payload_);
        }
        if (this.payloadCase_ == 7) {
            codedOutputStream.writeMessage(7, (SleepDomainTransitionEventV1) this.payload_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes13.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EventOrBuilder {
        private int absoluteUptimeMillisecondsHi_;
        private int absoluteUptimeMillisecondsLo_;
        private SingleFieldBuilderV3<BiometricDataPoint, BiometricDataPoint.Builder, BiometricDataPointOrBuilder> biometricDataPointBuilder_;
        private int payloadCase_;
        private Object payload_;
        private int relativeUptimeMilliseconds_;
        private SingleFieldBuilderV3<SleepDomainTransitionEventV1, SleepDomainTransitionEventV1.Builder, SleepDomainTransitionEventV1OrBuilder> sleepDomainTransitionEventV1Builder_;
        private SingleFieldBuilderV3<SynchronizeRealTime, SynchronizeRealTime.Builder, SynchronizeRealTimeOrBuilder> synchronizeRealTimeBuilder_;
        private SingleFieldBuilderV3<SynchronizeUptime, SynchronizeUptime.Builder, SynchronizeUptimeOrBuilder> synchronizeUptimeBuilder_;

        private SingleFieldBuilderV3<BiometricDataPoint, BiometricDataPoint.Builder, BiometricDataPointOrBuilder> getBiometricDataPointFieldBuilder() {
            if (this.biometricDataPointBuilder_ == null) {
                if (this.payloadCase_ != 4) {
                    this.payload_ = BiometricDataPoint.getDefaultInstance();
                }
                this.biometricDataPointBuilder_ = new SingleFieldBuilderV3<>((BiometricDataPoint) this.payload_, getParentForChildren(), isClean());
                this.payload_ = null;
            }
            this.payloadCase_ = 4;
            onChanged();
            return this.biometricDataPointBuilder_;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EventOuterClass.internal_static_Event_descriptor;
        }

        private SingleFieldBuilderV3<SleepDomainTransitionEventV1, SleepDomainTransitionEventV1.Builder, SleepDomainTransitionEventV1OrBuilder> getSleepDomainTransitionEventV1FieldBuilder() {
            if (this.sleepDomainTransitionEventV1Builder_ == null) {
                if (this.payloadCase_ != 7) {
                    this.payload_ = SleepDomainTransitionEventV1.getDefaultInstance();
                }
                this.sleepDomainTransitionEventV1Builder_ = new SingleFieldBuilderV3<>((SleepDomainTransitionEventV1) this.payload_, getParentForChildren(), isClean());
                this.payload_ = null;
            }
            this.payloadCase_ = 7;
            onChanged();
            return this.sleepDomainTransitionEventV1Builder_;
        }

        private SingleFieldBuilderV3<SynchronizeRealTime, SynchronizeRealTime.Builder, SynchronizeRealTimeOrBuilder> getSynchronizeRealTimeFieldBuilder() {
            if (this.synchronizeRealTimeBuilder_ == null) {
                if (this.payloadCase_ != 5) {
                    this.payload_ = SynchronizeRealTime.getDefaultInstance();
                }
                this.synchronizeRealTimeBuilder_ = new SingleFieldBuilderV3<>((SynchronizeRealTime) this.payload_, getParentForChildren(), isClean());
                this.payload_ = null;
            }
            this.payloadCase_ = 5;
            onChanged();
            return this.synchronizeRealTimeBuilder_;
        }

        private SingleFieldBuilderV3<SynchronizeUptime, SynchronizeUptime.Builder, SynchronizeUptimeOrBuilder> getSynchronizeUptimeFieldBuilder() {
            if (this.synchronizeUptimeBuilder_ == null) {
                if (this.payloadCase_ != 6) {
                    this.payload_ = SynchronizeUptime.getDefaultInstance();
                }
                this.synchronizeUptimeBuilder_ = new SingleFieldBuilderV3<>((SynchronizeUptime) this.payload_, getParentForChildren(), isClean());
                this.payload_ = null;
            }
            this.payloadCase_ = 6;
            onChanged();
            return this.synchronizeUptimeBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearAbsoluteUptimeMillisecondsHi() {
            this.absoluteUptimeMillisecondsHi_ = 0;
            onChanged();
            return this;
        }

        public Builder clearAbsoluteUptimeMillisecondsLo() {
            this.absoluteUptimeMillisecondsLo_ = 0;
            onChanged();
            return this;
        }

        public Builder clearBiometricDataPoint() {
            if (this.biometricDataPointBuilder_ == null) {
                if (this.payloadCase_ == 4) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                    onChanged();
                }
            } else {
                if (this.payloadCase_ == 4) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                }
                this.biometricDataPointBuilder_.clear();
            }
            return this;
        }

        public Builder clearPayload() {
            this.payloadCase_ = 0;
            this.payload_ = null;
            onChanged();
            return this;
        }

        public Builder clearRelativeUptimeMilliseconds() {
            this.relativeUptimeMilliseconds_ = 0;
            onChanged();
            return this;
        }

        public Builder clearSleepDomainTransitionEventV1() {
            if (this.sleepDomainTransitionEventV1Builder_ == null) {
                if (this.payloadCase_ == 7) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                    onChanged();
                }
            } else {
                if (this.payloadCase_ == 7) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                }
                this.sleepDomainTransitionEventV1Builder_.clear();
            }
            return this;
        }

        public Builder clearSynchronizeRealTime() {
            if (this.synchronizeRealTimeBuilder_ == null) {
                if (this.payloadCase_ == 5) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                    onChanged();
                }
            } else {
                if (this.payloadCase_ == 5) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                }
                this.synchronizeRealTimeBuilder_.clear();
            }
            return this;
        }

        public Builder clearSynchronizeUptime() {
            if (this.synchronizeUptimeBuilder_ == null) {
                if (this.payloadCase_ == 6) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                    onChanged();
                }
            } else {
                if (this.payloadCase_ == 6) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                }
                this.synchronizeUptimeBuilder_.clear();
            }
            return this;
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public int getAbsoluteUptimeMillisecondsHi() {
            return this.absoluteUptimeMillisecondsHi_;
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public int getAbsoluteUptimeMillisecondsLo() {
            return this.absoluteUptimeMillisecondsLo_;
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public BiometricDataPoint getBiometricDataPoint() {
            SingleFieldBuilderV3<BiometricDataPoint, BiometricDataPoint.Builder, BiometricDataPointOrBuilder> singleFieldBuilderV3 = this.biometricDataPointBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.payloadCase_ == 4) {
                    return (BiometricDataPoint) this.payload_;
                }
                return BiometricDataPoint.getDefaultInstance();
            } else if (this.payloadCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return BiometricDataPoint.getDefaultInstance();
            }
        }

        public BiometricDataPoint.Builder getBiometricDataPointBuilder() {
            return getBiometricDataPointFieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public BiometricDataPointOrBuilder getBiometricDataPointOrBuilder() {
            SingleFieldBuilderV3<BiometricDataPoint, BiometricDataPoint.Builder, BiometricDataPointOrBuilder> singleFieldBuilderV3;
            if (this.payloadCase_ == 4 && (singleFieldBuilderV3 = this.biometricDataPointBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.payloadCase_ == 4) {
                return (BiometricDataPoint) this.payload_;
            }
            return BiometricDataPoint.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EventOuterClass.internal_static_Event_descriptor;
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public PayloadCase getPayloadCase() {
            return PayloadCase.forNumber(this.payloadCase_);
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public int getRelativeUptimeMilliseconds() {
            return this.relativeUptimeMilliseconds_;
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public SleepDomainTransitionEventV1 getSleepDomainTransitionEventV1() {
            SingleFieldBuilderV3<SleepDomainTransitionEventV1, SleepDomainTransitionEventV1.Builder, SleepDomainTransitionEventV1OrBuilder> singleFieldBuilderV3 = this.sleepDomainTransitionEventV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.payloadCase_ == 7) {
                    return (SleepDomainTransitionEventV1) this.payload_;
                }
                return SleepDomainTransitionEventV1.getDefaultInstance();
            } else if (this.payloadCase_ == 7) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return SleepDomainTransitionEventV1.getDefaultInstance();
            }
        }

        public SleepDomainTransitionEventV1.Builder getSleepDomainTransitionEventV1Builder() {
            return getSleepDomainTransitionEventV1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public SleepDomainTransitionEventV1OrBuilder getSleepDomainTransitionEventV1OrBuilder() {
            SingleFieldBuilderV3<SleepDomainTransitionEventV1, SleepDomainTransitionEventV1.Builder, SleepDomainTransitionEventV1OrBuilder> singleFieldBuilderV3;
            if (this.payloadCase_ == 7 && (singleFieldBuilderV3 = this.sleepDomainTransitionEventV1Builder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.payloadCase_ == 7) {
                return (SleepDomainTransitionEventV1) this.payload_;
            }
            return SleepDomainTransitionEventV1.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public SynchronizeRealTime getSynchronizeRealTime() {
            SingleFieldBuilderV3<SynchronizeRealTime, SynchronizeRealTime.Builder, SynchronizeRealTimeOrBuilder> singleFieldBuilderV3 = this.synchronizeRealTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.payloadCase_ == 5) {
                    return (SynchronizeRealTime) this.payload_;
                }
                return SynchronizeRealTime.getDefaultInstance();
            } else if (this.payloadCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return SynchronizeRealTime.getDefaultInstance();
            }
        }

        public SynchronizeRealTime.Builder getSynchronizeRealTimeBuilder() {
            return getSynchronizeRealTimeFieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public SynchronizeRealTimeOrBuilder getSynchronizeRealTimeOrBuilder() {
            SingleFieldBuilderV3<SynchronizeRealTime, SynchronizeRealTime.Builder, SynchronizeRealTimeOrBuilder> singleFieldBuilderV3;
            if (this.payloadCase_ == 5 && (singleFieldBuilderV3 = this.synchronizeRealTimeBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.payloadCase_ == 5) {
                return (SynchronizeRealTime) this.payload_;
            }
            return SynchronizeRealTime.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public SynchronizeUptime getSynchronizeUptime() {
            SingleFieldBuilderV3<SynchronizeUptime, SynchronizeUptime.Builder, SynchronizeUptimeOrBuilder> singleFieldBuilderV3 = this.synchronizeUptimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.payloadCase_ == 6) {
                    return (SynchronizeUptime) this.payload_;
                }
                return SynchronizeUptime.getDefaultInstance();
            } else if (this.payloadCase_ == 6) {
                return singleFieldBuilderV3.getMessage();
            } else {
                return SynchronizeUptime.getDefaultInstance();
            }
        }

        public SynchronizeUptime.Builder getSynchronizeUptimeBuilder() {
            return getSynchronizeUptimeFieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public SynchronizeUptimeOrBuilder getSynchronizeUptimeOrBuilder() {
            SingleFieldBuilderV3<SynchronizeUptime, SynchronizeUptime.Builder, SynchronizeUptimeOrBuilder> singleFieldBuilderV3;
            if (this.payloadCase_ == 6 && (singleFieldBuilderV3 = this.synchronizeUptimeBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.payloadCase_ == 6) {
                return (SynchronizeUptime) this.payload_;
            }
            return SynchronizeUptime.getDefaultInstance();
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public boolean hasBiometricDataPoint() {
            return this.payloadCase_ == 4;
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public boolean hasSleepDomainTransitionEventV1() {
            return this.payloadCase_ == 7;
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public boolean hasSynchronizeRealTime() {
            return this.payloadCase_ == 5;
        }

        @Override // com.amazon.wellness.io.format.abs.EventOrBuilder
        public boolean hasSynchronizeUptime() {
            return this.payloadCase_ == 6;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EventOuterClass.internal_static_Event_fieldAccessorTable.ensureFieldAccessorsInitialized(Event.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeBiometricDataPoint(BiometricDataPoint biometricDataPoint) {
            SingleFieldBuilderV3<BiometricDataPoint, BiometricDataPoint.Builder, BiometricDataPointOrBuilder> singleFieldBuilderV3 = this.biometricDataPointBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.payloadCase_ == 4 && this.payload_ != BiometricDataPoint.getDefaultInstance()) {
                    this.payload_ = BiometricDataPoint.newBuilder((BiometricDataPoint) this.payload_).mergeFrom(biometricDataPoint).mo10085buildPartial();
                } else {
                    this.payload_ = biometricDataPoint;
                }
                onChanged();
            } else {
                if (this.payloadCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(biometricDataPoint);
                }
                this.biometricDataPointBuilder_.setMessage(biometricDataPoint);
            }
            this.payloadCase_ = 4;
            return this;
        }

        public Builder mergeSleepDomainTransitionEventV1(SleepDomainTransitionEventV1 sleepDomainTransitionEventV1) {
            SingleFieldBuilderV3<SleepDomainTransitionEventV1, SleepDomainTransitionEventV1.Builder, SleepDomainTransitionEventV1OrBuilder> singleFieldBuilderV3 = this.sleepDomainTransitionEventV1Builder_;
            if (singleFieldBuilderV3 == null) {
                if (this.payloadCase_ == 7 && this.payload_ != SleepDomainTransitionEventV1.getDefaultInstance()) {
                    this.payload_ = SleepDomainTransitionEventV1.newBuilder((SleepDomainTransitionEventV1) this.payload_).mergeFrom(sleepDomainTransitionEventV1).mo10085buildPartial();
                } else {
                    this.payload_ = sleepDomainTransitionEventV1;
                }
                onChanged();
            } else {
                if (this.payloadCase_ == 7) {
                    singleFieldBuilderV3.mergeFrom(sleepDomainTransitionEventV1);
                }
                this.sleepDomainTransitionEventV1Builder_.setMessage(sleepDomainTransitionEventV1);
            }
            this.payloadCase_ = 7;
            return this;
        }

        public Builder mergeSynchronizeRealTime(SynchronizeRealTime synchronizeRealTime) {
            SingleFieldBuilderV3<SynchronizeRealTime, SynchronizeRealTime.Builder, SynchronizeRealTimeOrBuilder> singleFieldBuilderV3 = this.synchronizeRealTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.payloadCase_ == 5 && this.payload_ != SynchronizeRealTime.getDefaultInstance()) {
                    this.payload_ = SynchronizeRealTime.newBuilder((SynchronizeRealTime) this.payload_).mergeFrom(synchronizeRealTime).mo10085buildPartial();
                } else {
                    this.payload_ = synchronizeRealTime;
                }
                onChanged();
            } else {
                if (this.payloadCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(synchronizeRealTime);
                }
                this.synchronizeRealTimeBuilder_.setMessage(synchronizeRealTime);
            }
            this.payloadCase_ = 5;
            return this;
        }

        public Builder mergeSynchronizeUptime(SynchronizeUptime synchronizeUptime) {
            SingleFieldBuilderV3<SynchronizeUptime, SynchronizeUptime.Builder, SynchronizeUptimeOrBuilder> singleFieldBuilderV3 = this.synchronizeUptimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.payloadCase_ == 6 && this.payload_ != SynchronizeUptime.getDefaultInstance()) {
                    this.payload_ = SynchronizeUptime.newBuilder((SynchronizeUptime) this.payload_).mergeFrom(synchronizeUptime).mo10085buildPartial();
                } else {
                    this.payload_ = synchronizeUptime;
                }
                onChanged();
            } else {
                if (this.payloadCase_ == 6) {
                    singleFieldBuilderV3.mergeFrom(synchronizeUptime);
                }
                this.synchronizeUptimeBuilder_.setMessage(synchronizeUptime);
            }
            this.payloadCase_ = 6;
            return this;
        }

        public Builder setAbsoluteUptimeMillisecondsHi(int i) {
            this.absoluteUptimeMillisecondsHi_ = i;
            onChanged();
            return this;
        }

        public Builder setAbsoluteUptimeMillisecondsLo(int i) {
            this.absoluteUptimeMillisecondsLo_ = i;
            onChanged();
            return this;
        }

        public Builder setBiometricDataPoint(BiometricDataPoint biometricDataPoint) {
            SingleFieldBuilderV3<BiometricDataPoint, BiometricDataPoint.Builder, BiometricDataPointOrBuilder> singleFieldBuilderV3 = this.biometricDataPointBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(biometricDataPoint);
            } else if (biometricDataPoint != null) {
                this.payload_ = biometricDataPoint;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.payloadCase_ = 4;
            return this;
        }

        public Builder setRelativeUptimeMilliseconds(int i) {
            this.relativeUptimeMilliseconds_ = i;
            onChanged();
            return this;
        }

        public Builder setSleepDomainTransitionEventV1(SleepDomainTransitionEventV1 sleepDomainTransitionEventV1) {
            SingleFieldBuilderV3<SleepDomainTransitionEventV1, SleepDomainTransitionEventV1.Builder, SleepDomainTransitionEventV1OrBuilder> singleFieldBuilderV3 = this.sleepDomainTransitionEventV1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sleepDomainTransitionEventV1);
            } else if (sleepDomainTransitionEventV1 != null) {
                this.payload_ = sleepDomainTransitionEventV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.payloadCase_ = 7;
            return this;
        }

        public Builder setSynchronizeRealTime(SynchronizeRealTime synchronizeRealTime) {
            SingleFieldBuilderV3<SynchronizeRealTime, SynchronizeRealTime.Builder, SynchronizeRealTimeOrBuilder> singleFieldBuilderV3 = this.synchronizeRealTimeBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(synchronizeRealTime);
            } else if (synchronizeRealTime != null) {
                this.payload_ = synchronizeRealTime;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.payloadCase_ = 5;
            return this;
        }

        public Builder setSynchronizeUptime(SynchronizeUptime synchronizeUptime) {
            SingleFieldBuilderV3<SynchronizeUptime, SynchronizeUptime.Builder, SynchronizeUptimeOrBuilder> singleFieldBuilderV3 = this.synchronizeUptimeBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(synchronizeUptime);
            } else if (synchronizeUptime != null) {
                this.payload_ = synchronizeUptime;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.payloadCase_ = 6;
            return this;
        }

        private Builder() {
            this.payloadCase_ = 0;
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public Event mo10084build() {
            Event mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public Event mo10085buildPartial() {
            Event event = new Event(this);
            event.absoluteUptimeMillisecondsLo_ = this.absoluteUptimeMillisecondsLo_;
            event.absoluteUptimeMillisecondsHi_ = this.absoluteUptimeMillisecondsHi_;
            event.relativeUptimeMilliseconds_ = this.relativeUptimeMilliseconds_;
            if (this.payloadCase_ == 4) {
                SingleFieldBuilderV3<BiometricDataPoint, BiometricDataPoint.Builder, BiometricDataPointOrBuilder> singleFieldBuilderV3 = this.biometricDataPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    event.payload_ = this.payload_;
                } else {
                    event.payload_ = singleFieldBuilderV3.build();
                }
            }
            if (this.payloadCase_ == 5) {
                SingleFieldBuilderV3<SynchronizeRealTime, SynchronizeRealTime.Builder, SynchronizeRealTimeOrBuilder> singleFieldBuilderV32 = this.synchronizeRealTimeBuilder_;
                if (singleFieldBuilderV32 == null) {
                    event.payload_ = this.payload_;
                } else {
                    event.payload_ = singleFieldBuilderV32.build();
                }
            }
            if (this.payloadCase_ == 6) {
                SingleFieldBuilderV3<SynchronizeUptime, SynchronizeUptime.Builder, SynchronizeUptimeOrBuilder> singleFieldBuilderV33 = this.synchronizeUptimeBuilder_;
                if (singleFieldBuilderV33 == null) {
                    event.payload_ = this.payload_;
                } else {
                    event.payload_ = singleFieldBuilderV33.build();
                }
            }
            if (this.payloadCase_ == 7) {
                SingleFieldBuilderV3<SleepDomainTransitionEventV1, SleepDomainTransitionEventV1.Builder, SleepDomainTransitionEventV1OrBuilder> singleFieldBuilderV34 = this.sleepDomainTransitionEventV1Builder_;
                if (singleFieldBuilderV34 == null) {
                    event.payload_ = this.payload_;
                } else {
                    event.payload_ = singleFieldBuilderV34.build();
                }
            }
            event.payloadCase_ = this.payloadCase_;
            onBuilt();
            return event;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public Event mo10094getDefaultInstanceForType() {
            return Event.getDefaultInstance();
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
            this.absoluteUptimeMillisecondsLo_ = 0;
            this.absoluteUptimeMillisecondsHi_ = 0;
            this.relativeUptimeMilliseconds_ = 0;
            this.payloadCase_ = 0;
            this.payload_ = null;
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.payloadCase_ = 0;
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
            if (message instanceof Event) {
                return mergeFrom((Event) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder setBiometricDataPoint(BiometricDataPoint.Builder builder) {
            SingleFieldBuilderV3<BiometricDataPoint, BiometricDataPoint.Builder, BiometricDataPointOrBuilder> singleFieldBuilderV3 = this.biometricDataPointBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.payload_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.payloadCase_ = 4;
            return this;
        }

        public Builder setSleepDomainTransitionEventV1(SleepDomainTransitionEventV1.Builder builder) {
            SingleFieldBuilderV3<SleepDomainTransitionEventV1, SleepDomainTransitionEventV1.Builder, SleepDomainTransitionEventV1OrBuilder> singleFieldBuilderV3 = this.sleepDomainTransitionEventV1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.payload_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.payloadCase_ = 7;
            return this;
        }

        public Builder setSynchronizeRealTime(SynchronizeRealTime.Builder builder) {
            SingleFieldBuilderV3<SynchronizeRealTime, SynchronizeRealTime.Builder, SynchronizeRealTimeOrBuilder> singleFieldBuilderV3 = this.synchronizeRealTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.payload_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.payloadCase_ = 5;
            return this;
        }

        public Builder setSynchronizeUptime(SynchronizeUptime.Builder builder) {
            SingleFieldBuilderV3<SynchronizeUptime, SynchronizeUptime.Builder, SynchronizeUptimeOrBuilder> singleFieldBuilderV3 = this.synchronizeUptimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.payload_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            this.payloadCase_ = 6;
            return this;
        }

        public Builder mergeFrom(Event event) {
            if (event == Event.getDefaultInstance()) {
                return this;
            }
            if (event.getAbsoluteUptimeMillisecondsLo() != 0) {
                setAbsoluteUptimeMillisecondsLo(event.getAbsoluteUptimeMillisecondsLo());
            }
            if (event.getAbsoluteUptimeMillisecondsHi() != 0) {
                setAbsoluteUptimeMillisecondsHi(event.getAbsoluteUptimeMillisecondsHi());
            }
            if (event.getRelativeUptimeMilliseconds() != 0) {
                setRelativeUptimeMilliseconds(event.getRelativeUptimeMilliseconds());
            }
            int ordinal = event.getPayloadCase().ordinal();
            if (ordinal == 0) {
                mergeBiometricDataPoint(event.getBiometricDataPoint());
            } else if (ordinal == 1) {
                mergeSynchronizeRealTime(event.getSynchronizeRealTime());
            } else if (ordinal == 2) {
                mergeSynchronizeUptime(event.getSynchronizeUptime());
            } else if (ordinal == 3) {
                mergeSleepDomainTransitionEventV1(event.getSleepDomainTransitionEventV1());
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) event).unknownFields);
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
        public com.amazon.wellness.io.format.abs.Event.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.wellness.io.format.abs.Event.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.wellness.io.format.abs.Event r3 = (com.amazon.wellness.io.format.abs.Event) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.wellness.io.format.abs.Event r4 = (com.amazon.wellness.io.format.abs.Event) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.wellness.io.format.abs.Event.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.wellness.io.format.abs.Event$Builder");
        }
    }

    public static Builder newBuilder(Event event) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(event);
    }

    public static Event parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private Event(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.payloadCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    public static Event parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Event) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Event parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public Event mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static Event parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    public static Event parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private Event() {
        this.payloadCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.absoluteUptimeMillisecondsLo_ = 0;
        this.absoluteUptimeMillisecondsHi_ = 0;
        this.relativeUptimeMilliseconds_ = 0;
    }

    public static Event parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static Event parseFrom(InputStream inputStream) throws IOException {
        return (Event) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Event parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Event) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Event parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Event) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    private Event(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.absoluteUptimeMillisecondsLo_ = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                this.absoluteUptimeMillisecondsHi_ = codedInputStream.readUInt32();
                            } else if (readTag != 24) {
                                SleepDomainTransitionEventV1.Builder builder = null;
                                BiometricDataPoint.Builder mo10081toBuilder = null;
                                SynchronizeRealTime.Builder mo10081toBuilder2 = null;
                                SynchronizeUptime.Builder mo10081toBuilder3 = null;
                                if (readTag == 34) {
                                    mo10081toBuilder = this.payloadCase_ == 4 ? ((BiometricDataPoint) this.payload_).mo10081toBuilder() : mo10081toBuilder;
                                    this.payload_ = codedInputStream.readMessage(BiometricDataPoint.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((BiometricDataPoint) this.payload_);
                                        this.payload_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 4;
                                } else if (readTag == 42) {
                                    mo10081toBuilder2 = this.payloadCase_ == 5 ? ((SynchronizeRealTime) this.payload_).mo10081toBuilder() : mo10081toBuilder2;
                                    this.payload_ = codedInputStream.readMessage(SynchronizeRealTime.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder2 != null) {
                                        mo10081toBuilder2.mergeFrom((SynchronizeRealTime) this.payload_);
                                        this.payload_ = mo10081toBuilder2.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 5;
                                } else if (readTag == 50) {
                                    mo10081toBuilder3 = this.payloadCase_ == 6 ? ((SynchronizeUptime) this.payload_).mo10081toBuilder() : mo10081toBuilder3;
                                    this.payload_ = codedInputStream.readMessage(SynchronizeUptime.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder3 != null) {
                                        mo10081toBuilder3.mergeFrom((SynchronizeUptime) this.payload_);
                                        this.payload_ = mo10081toBuilder3.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 6;
                                } else if (readTag != 58) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    builder = this.payloadCase_ == 7 ? ((SleepDomainTransitionEventV1) this.payload_).mo10081toBuilder() : builder;
                                    this.payload_ = codedInputStream.readMessage(SleepDomainTransitionEventV1.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom((SleepDomainTransitionEventV1) this.payload_);
                                        this.payload_ = builder.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 7;
                                }
                            } else {
                                this.relativeUptimeMilliseconds_ = codedInputStream.readUInt32();
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

    public static Event parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Event) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
