package com.amazon.alexa.accessory.protocol;

import com.amazon.alexa.accessory.protocol.Common;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public final class Device {

    /* renamed from: com.amazon.alexa.accessory.protocol.Device$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class CompleteSetup extends GeneratedMessageLite<CompleteSetup, Builder> implements CompleteSetupOrBuilder {
        private static final CompleteSetup DEFAULT_INSTANCE = new CompleteSetup();
        public static final int ERROR_CODE_FIELD_NUMBER = 1;
        private static volatile Parser<CompleteSetup> PARSER;
        private int errorCode_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CompleteSetup, Builder> implements CompleteSetupOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearErrorCode() {
                copyOnWrite();
                ((CompleteSetup) this.instance).clearErrorCode();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.CompleteSetupOrBuilder
            public Common.ErrorCode getErrorCode() {
                return ((CompleteSetup) this.instance).getErrorCode();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.CompleteSetupOrBuilder
            public int getErrorCodeValue() {
                return ((CompleteSetup) this.instance).getErrorCodeValue();
            }

            public Builder setErrorCode(Common.ErrorCode errorCode) {
                copyOnWrite();
                ((CompleteSetup) this.instance).setErrorCode(errorCode);
                return this;
            }

            public Builder setErrorCodeValue(int i) {
                copyOnWrite();
                ((CompleteSetup) this.instance).setErrorCodeValue(i);
                return this;
            }

            private Builder() {
                super(CompleteSetup.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CompleteSetup() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorCode() {
            this.errorCode_ = 0;
        }

        public static CompleteSetup getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CompleteSetup parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CompleteSetup) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompleteSetup parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CompleteSetup> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCode(Common.ErrorCode errorCode) {
            if (errorCode != null) {
                this.errorCode_ = errorCode.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCodeValue(int i) {
            this.errorCode_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CompleteSetup completeSetup = (CompleteSetup) obj2;
                    boolean z2 = this.errorCode_ != 0;
                    int i = this.errorCode_;
                    if (completeSetup.errorCode_ != 0) {
                        z = true;
                    }
                    this.errorCode_ = visitor.visitInt(z2, i, z, completeSetup.errorCode_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 8) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.errorCode_ = codedInputStream.readEnum();
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this));
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new CompleteSetup();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CompleteSetup.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.CompleteSetupOrBuilder
        public Common.ErrorCode getErrorCode() {
            Common.ErrorCode forNumber = Common.ErrorCode.forNumber(this.errorCode_);
            return forNumber == null ? Common.ErrorCode.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.CompleteSetupOrBuilder
        public int getErrorCodeValue() {
            return this.errorCode_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.errorCode_ != Common.ErrorCode.SUCCESS.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.errorCode_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.errorCode_ != Common.ErrorCode.SUCCESS.getNumber()) {
                codedOutputStream.writeEnum(1, this.errorCode_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CompleteSetup completeSetup) {
            return DEFAULT_INSTANCE.createBuilder(completeSetup);
        }

        public static CompleteSetup parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompleteSetup) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompleteSetup parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CompleteSetup parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CompleteSetup parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CompleteSetup parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CompleteSetup parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CompleteSetup parseFrom(InputStream inputStream) throws IOException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompleteSetup parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompleteSetup parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CompleteSetup parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompleteSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CompleteSetupOrBuilder extends MessageLiteOrBuilder {
        Common.ErrorCode getErrorCode();

        int getErrorCodeValue();
    }

    /* loaded from: classes6.dex */
    public enum ConnectionStatus implements Internal.EnumLite {
        CONNECTION_STATUS_UNKNOWN(0),
        CONNECTION_STATUS_CONNECTED(1),
        CONNECTION_STATUS_DISCONNECTED(2),
        UNRECOGNIZED(-1);
        
        public static final int CONNECTION_STATUS_CONNECTED_VALUE = 1;
        public static final int CONNECTION_STATUS_DISCONNECTED_VALUE = 2;
        public static final int CONNECTION_STATUS_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<ConnectionStatus> internalValueMap = new Internal.EnumLiteMap<ConnectionStatus>() { // from class: com.amazon.alexa.accessory.protocol.Device.ConnectionStatus.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public ConnectionStatus mo9850findValueByNumber(int i) {
                return ConnectionStatus.forNumber(i);
            }
        };
        private final int value;

        ConnectionStatus(int i) {
            this.value = i;
        }

        public static ConnectionStatus forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return CONNECTION_STATUS_CONNECTED;
                }
                if (i == 2) {
                    return CONNECTION_STATUS_DISCONNECTED;
                }
                return null;
            }
            return CONNECTION_STATUS_UNKNOWN;
        }

        public static Internal.EnumLiteMap<ConnectionStatus> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static ConnectionStatus valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class DeviceBattery extends GeneratedMessageLite<DeviceBattery, Builder> implements DeviceBatteryOrBuilder {
        private static final DeviceBattery DEFAULT_INSTANCE = new DeviceBattery();
        public static final int LEVEL_FIELD_NUMBER = 1;
        private static volatile Parser<DeviceBattery> PARSER = null;
        public static final int SCALE_FIELD_NUMBER = 2;
        public static final int STATUS_FIELD_NUMBER = 3;
        private int level_;
        private int scale_;
        private int status_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<DeviceBattery, Builder> implements DeviceBatteryOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearLevel() {
                copyOnWrite();
                ((DeviceBattery) this.instance).clearLevel();
                return this;
            }

            public Builder clearScale() {
                copyOnWrite();
                ((DeviceBattery) this.instance).clearScale();
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((DeviceBattery) this.instance).clearStatus();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceBatteryOrBuilder
            public int getLevel() {
                return ((DeviceBattery) this.instance).getLevel();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceBatteryOrBuilder
            public int getScale() {
                return ((DeviceBattery) this.instance).getScale();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceBatteryOrBuilder
            public Status getStatus() {
                return ((DeviceBattery) this.instance).getStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceBatteryOrBuilder
            public int getStatusValue() {
                return ((DeviceBattery) this.instance).getStatusValue();
            }

            public Builder setLevel(int i) {
                copyOnWrite();
                ((DeviceBattery) this.instance).setLevel(i);
                return this;
            }

            public Builder setScale(int i) {
                copyOnWrite();
                ((DeviceBattery) this.instance).setScale(i);
                return this;
            }

            public Builder setStatus(Status status) {
                copyOnWrite();
                ((DeviceBattery) this.instance).setStatus(status);
                return this;
            }

            public Builder setStatusValue(int i) {
                copyOnWrite();
                ((DeviceBattery) this.instance).setStatusValue(i);
                return this;
            }

            private Builder() {
                super(DeviceBattery.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        public enum Status implements Internal.EnumLite {
            UNKNOWN(0),
            CHARGING(1),
            DISCHARGING(2),
            FULL(3),
            UNRECOGNIZED(-1);
            
            public static final int CHARGING_VALUE = 1;
            public static final int DISCHARGING_VALUE = 2;
            public static final int FULL_VALUE = 3;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<Status> internalValueMap = new Internal.EnumLiteMap<Status>() { // from class: com.amazon.alexa.accessory.protocol.Device.DeviceBattery.Status.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public Status mo9850findValueByNumber(int i) {
                    return Status.forNumber(i);
                }
            };
            private final int value;

            Status(int i) {
                this.value = i;
            }

            public static Status forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return CHARGING;
                    }
                    if (i == 2) {
                        return DISCHARGING;
                    }
                    if (i == 3) {
                        return FULL;
                    }
                    return null;
                }
                return UNKNOWN;
            }

            public static Internal.EnumLiteMap<Status> internalGetValueMap() {
                return internalValueMap;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Status valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private DeviceBattery() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLevel() {
            this.level_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearScale() {
            this.scale_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.status_ = 0;
        }

        public static DeviceBattery getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DeviceBattery parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DeviceBattery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceBattery parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DeviceBattery> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLevel(int i) {
            this.level_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScale(int i) {
            this.scale_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(Status status) {
            if (status != null) {
                this.status_ = status.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatusValue(int i) {
            this.status_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    DeviceBattery deviceBattery = (DeviceBattery) obj2;
                    this.level_ = visitor.visitInt(this.level_ != 0, this.level_, deviceBattery.level_ != 0, deviceBattery.level_);
                    this.scale_ = visitor.visitInt(this.scale_ != 0, this.scale_, deviceBattery.scale_ != 0, deviceBattery.scale_);
                    boolean z2 = this.status_ != 0;
                    int i = this.status_;
                    if (deviceBattery.status_ != 0) {
                        z = true;
                    }
                    this.status_ = visitor.visitInt(z2, i, z, deviceBattery.status_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.level_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.scale_ = codedInputStream.readUInt32();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.status_ = codedInputStream.readEnum();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new DeviceBattery();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (DeviceBattery.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceBatteryOrBuilder
        public int getLevel() {
            return this.level_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceBatteryOrBuilder
        public int getScale() {
            return this.scale_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.level_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int i4 = this.scale_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
            }
            if (this.status_ != Status.UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.status_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceBatteryOrBuilder
        public Status getStatus() {
            Status forNumber = Status.forNumber(this.status_);
            return forNumber == null ? Status.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceBatteryOrBuilder
        public int getStatusValue() {
            return this.status_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.level_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.scale_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            if (this.status_ != Status.UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(3, this.status_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(DeviceBattery deviceBattery) {
            return DEFAULT_INSTANCE.createBuilder(deviceBattery);
        }

        public static DeviceBattery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceBattery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceBattery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DeviceBattery parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DeviceBattery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DeviceBattery parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DeviceBattery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DeviceBattery parseFrom(InputStream inputStream) throws IOException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceBattery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceBattery parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DeviceBattery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceBattery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface DeviceBatteryOrBuilder extends MessageLiteOrBuilder {
        int getLevel();

        int getScale();

        DeviceBattery.Status getStatus();

        int getStatusValue();
    }

    /* loaded from: classes6.dex */
    public static final class DeviceConfiguration extends GeneratedMessageLite<DeviceConfiguration, Builder> implements DeviceConfigurationOrBuilder {
        private static final DeviceConfiguration DEFAULT_INSTANCE = new DeviceConfiguration();
        public static final int NEEDS_ASSISTANT_OVERRIDE_FIELD_NUMBER = 1;
        public static final int NEEDS_SETUP_FIELD_NUMBER = 2;
        private static volatile Parser<DeviceConfiguration> PARSER;
        private boolean needsAssistantOverride_;
        private boolean needsSetup_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<DeviceConfiguration, Builder> implements DeviceConfigurationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearNeedsAssistantOverride() {
                copyOnWrite();
                ((DeviceConfiguration) this.instance).clearNeedsAssistantOverride();
                return this;
            }

            public Builder clearNeedsSetup() {
                copyOnWrite();
                ((DeviceConfiguration) this.instance).clearNeedsSetup();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceConfigurationOrBuilder
            public boolean getNeedsAssistantOverride() {
                return ((DeviceConfiguration) this.instance).getNeedsAssistantOverride();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceConfigurationOrBuilder
            public boolean getNeedsSetup() {
                return ((DeviceConfiguration) this.instance).getNeedsSetup();
            }

            public Builder setNeedsAssistantOverride(boolean z) {
                copyOnWrite();
                ((DeviceConfiguration) this.instance).setNeedsAssistantOverride(z);
                return this;
            }

            public Builder setNeedsSetup(boolean z) {
                copyOnWrite();
                ((DeviceConfiguration) this.instance).setNeedsSetup(z);
                return this;
            }

            private Builder() {
                super(DeviceConfiguration.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private DeviceConfiguration() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNeedsAssistantOverride() {
            this.needsAssistantOverride_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNeedsSetup() {
            this.needsSetup_ = false;
        }

        public static DeviceConfiguration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DeviceConfiguration parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DeviceConfiguration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceConfiguration parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DeviceConfiguration> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNeedsAssistantOverride(boolean z) {
            this.needsAssistantOverride_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNeedsSetup(boolean z) {
            this.needsSetup_ = z;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    DeviceConfiguration deviceConfiguration = (DeviceConfiguration) obj2;
                    boolean z = this.needsAssistantOverride_;
                    boolean z2 = deviceConfiguration.needsAssistantOverride_;
                    this.needsAssistantOverride_ = visitor.visitBoolean(z, z, z2, z2);
                    boolean z3 = this.needsSetup_;
                    boolean z4 = deviceConfiguration.needsSetup_;
                    this.needsSetup_ = visitor.visitBoolean(z3, z3, z4, z4);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    boolean z5 = false;
                    while (!z5) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.needsAssistantOverride_ = codedInputStream.readBool();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.needsSetup_ = codedInputStream.readBool();
                                }
                            }
                            z5 = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new DeviceConfiguration();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (DeviceConfiguration.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceConfigurationOrBuilder
        public boolean getNeedsAssistantOverride() {
            return this.needsAssistantOverride_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceConfigurationOrBuilder
        public boolean getNeedsSetup() {
            return this.needsSetup_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            boolean z = this.needsAssistantOverride_;
            if (z) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
            }
            boolean z2 = this.needsSetup_;
            if (z2) {
                i2 += CodedOutputStream.computeBoolSize(2, z2);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.needsAssistantOverride_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            boolean z2 = this.needsSetup_;
            if (z2) {
                codedOutputStream.writeBool(2, z2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(DeviceConfiguration deviceConfiguration) {
            return DEFAULT_INSTANCE.createBuilder(deviceConfiguration);
        }

        public static DeviceConfiguration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceConfiguration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceConfiguration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DeviceConfiguration parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DeviceConfiguration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DeviceConfiguration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DeviceConfiguration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DeviceConfiguration parseFrom(InputStream inputStream) throws IOException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceConfiguration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceConfiguration parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DeviceConfiguration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface DeviceConfigurationOrBuilder extends MessageLiteOrBuilder {
        boolean getNeedsAssistantOverride();

        boolean getNeedsSetup();
    }

    /* loaded from: classes6.dex */
    public static final class DeviceFeatures extends GeneratedMessageLite<DeviceFeatures, Builder> implements DeviceFeaturesOrBuilder {
        private static final DeviceFeatures DEFAULT_INSTANCE = new DeviceFeatures();
        public static final int DEVICE_ATTRIBUTES_FIELD_NUMBER = 2;
        public static final int FEATURES_FIELD_NUMBER = 1;
        public static final int FEATURE_PROPERTIES_FIELD_NUMBER = 3;
        private static volatile Parser<DeviceFeatures> PARSER;
        private int bitField0_;
        private int deviceAttributes_;
        private Internal.ProtobufList<FeatureProperties> featureProperties_ = GeneratedMessageLite.emptyProtobufList();
        private int features_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<DeviceFeatures, Builder> implements DeviceFeaturesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllFeatureProperties(Iterable<? extends FeatureProperties> iterable) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).addAllFeatureProperties(iterable);
                return this;
            }

            public Builder addFeatureProperties(FeatureProperties featureProperties) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).addFeatureProperties(featureProperties);
                return this;
            }

            public Builder clearDeviceAttributes() {
                copyOnWrite();
                ((DeviceFeatures) this.instance).clearDeviceAttributes();
                return this;
            }

            public Builder clearFeatureProperties() {
                copyOnWrite();
                ((DeviceFeatures) this.instance).clearFeatureProperties();
                return this;
            }

            public Builder clearFeatures() {
                copyOnWrite();
                ((DeviceFeatures) this.instance).clearFeatures();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
            public int getDeviceAttributes() {
                return ((DeviceFeatures) this.instance).getDeviceAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
            public FeatureProperties getFeatureProperties(int i) {
                return ((DeviceFeatures) this.instance).getFeatureProperties(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
            public int getFeaturePropertiesCount() {
                return ((DeviceFeatures) this.instance).getFeaturePropertiesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
            public List<FeatureProperties> getFeaturePropertiesList() {
                return Collections.unmodifiableList(((DeviceFeatures) this.instance).getFeaturePropertiesList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
            public int getFeatures() {
                return ((DeviceFeatures) this.instance).getFeatures();
            }

            public Builder removeFeatureProperties(int i) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).removeFeatureProperties(i);
                return this;
            }

            public Builder setDeviceAttributes(int i) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).setDeviceAttributes(i);
                return this;
            }

            public Builder setFeatureProperties(int i, FeatureProperties featureProperties) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).setFeatureProperties(i, featureProperties);
                return this;
            }

            public Builder setFeatures(int i) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).setFeatures(i);
                return this;
            }

            private Builder() {
                super(DeviceFeatures.DEFAULT_INSTANCE);
            }

            public Builder addFeatureProperties(int i, FeatureProperties featureProperties) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).addFeatureProperties(i, featureProperties);
                return this;
            }

            public Builder setFeatureProperties(int i, FeatureProperties.Builder builder) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).setFeatureProperties(i, builder);
                return this;
            }

            public Builder addFeatureProperties(FeatureProperties.Builder builder) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).addFeatureProperties(builder);
                return this;
            }

            public Builder addFeatureProperties(int i, FeatureProperties.Builder builder) {
                copyOnWrite();
                ((DeviceFeatures) this.instance).addFeatureProperties(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private DeviceFeatures() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFeatureProperties(Iterable<? extends FeatureProperties> iterable) {
            ensureFeaturePropertiesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.featureProperties_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFeatureProperties(FeatureProperties featureProperties) {
            if (featureProperties != null) {
                ensureFeaturePropertiesIsMutable();
                this.featureProperties_.add(featureProperties);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceAttributes() {
            this.deviceAttributes_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFeatureProperties() {
            this.featureProperties_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFeatures() {
            this.features_ = 0;
        }

        private void ensureFeaturePropertiesIsMutable() {
            if (!this.featureProperties_.isModifiable()) {
                this.featureProperties_ = GeneratedMessageLite.mutableCopy(this.featureProperties_);
            }
        }

        public static DeviceFeatures getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DeviceFeatures parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DeviceFeatures) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceFeatures parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DeviceFeatures> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeFeatureProperties(int i) {
            ensureFeaturePropertiesIsMutable();
            this.featureProperties_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceAttributes(int i) {
            this.deviceAttributes_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFeatureProperties(int i, FeatureProperties featureProperties) {
            if (featureProperties != null) {
                ensureFeaturePropertiesIsMutable();
                this.featureProperties_.set(i, featureProperties);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFeatures(int i) {
            this.features_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    DeviceFeatures deviceFeatures = (DeviceFeatures) obj2;
                    this.features_ = visitor.visitInt(this.features_ != 0, this.features_, deviceFeatures.features_ != 0, deviceFeatures.features_);
                    boolean z2 = this.deviceAttributes_ != 0;
                    int i = this.deviceAttributes_;
                    if (deviceFeatures.deviceAttributes_ != 0) {
                        z = true;
                    }
                    this.deviceAttributes_ = visitor.visitInt(z2, i, z, deviceFeatures.deviceAttributes_);
                    this.featureProperties_ = visitor.visitList(this.featureProperties_, deviceFeatures.featureProperties_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= deviceFeatures.bitField0_;
                    }
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.features_ = codedInputStream.readUInt32();
                                    } else if (readTag == 16) {
                                        this.deviceAttributes_ = codedInputStream.readUInt32();
                                    } else if (readTag != 26) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        if (!this.featureProperties_.isModifiable()) {
                                            this.featureProperties_ = GeneratedMessageLite.mutableCopy(this.featureProperties_);
                                        }
                                        this.featureProperties_.add(codedInputStream.readMessage(FeatureProperties.parser(), extensionRegistryLite));
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            }
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    this.featureProperties_.makeImmutable();
                    return null;
                case 6:
                    return new DeviceFeatures();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (DeviceFeatures.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
        public int getDeviceAttributes() {
            return this.deviceAttributes_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
        public FeatureProperties getFeatureProperties(int i) {
            return this.featureProperties_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
        public int getFeaturePropertiesCount() {
            return this.featureProperties_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
        public List<FeatureProperties> getFeaturePropertiesList() {
            return this.featureProperties_;
        }

        public FeaturePropertiesOrBuilder getFeaturePropertiesOrBuilder(int i) {
            return this.featureProperties_.get(i);
        }

        public List<? extends FeaturePropertiesOrBuilder> getFeaturePropertiesOrBuilderList() {
            return this.featureProperties_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceFeaturesOrBuilder
        public int getFeatures() {
            return this.features_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.features_;
            int computeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) + 0 : 0;
            int i3 = this.deviceAttributes_;
            if (i3 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
            }
            for (int i4 = 0; i4 < this.featureProperties_.size(); i4++) {
                computeUInt32Size += CodedOutputStream.computeMessageSize(3, this.featureProperties_.get(i4));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeUInt32Size;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.features_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.deviceAttributes_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            for (int i3 = 0; i3 < this.featureProperties_.size(); i3++) {
                codedOutputStream.writeMessage(3, this.featureProperties_.get(i3));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(DeviceFeatures deviceFeatures) {
            return DEFAULT_INSTANCE.createBuilder(deviceFeatures);
        }

        public static DeviceFeatures parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceFeatures) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceFeatures parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DeviceFeatures parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFeatureProperties(int i, FeatureProperties featureProperties) {
            if (featureProperties != null) {
                ensureFeaturePropertiesIsMutable();
                this.featureProperties_.add(i, featureProperties);
                return;
            }
            throw new NullPointerException();
        }

        public static DeviceFeatures parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFeatureProperties(int i, FeatureProperties.Builder builder) {
            ensureFeaturePropertiesIsMutable();
            this.featureProperties_.set(i, builder.mo10084build());
        }

        public static DeviceFeatures parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DeviceFeatures parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFeatureProperties(FeatureProperties.Builder builder) {
            ensureFeaturePropertiesIsMutable();
            this.featureProperties_.add(builder.mo10084build());
        }

        public static DeviceFeatures parseFrom(InputStream inputStream) throws IOException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceFeatures parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFeatureProperties(int i, FeatureProperties.Builder builder) {
            ensureFeaturePropertiesIsMutable();
            this.featureProperties_.add(i, builder.mo10084build());
        }

        public static DeviceFeatures parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DeviceFeatures parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface DeviceFeaturesOrBuilder extends MessageLiteOrBuilder {
        int getDeviceAttributes();

        FeatureProperties getFeatureProperties(int i);

        int getFeaturePropertiesCount();

        List<FeatureProperties> getFeaturePropertiesList();

        int getFeatures();
    }

    /* loaded from: classes6.dex */
    public static final class DeviceInformation extends GeneratedMessageLite<DeviceInformation, Builder> implements DeviceInformationOrBuilder {
        public static final int ASSOCIATED_DEVICES_FIELD_NUMBER = 9;
        public static final int BATTERY_FIELD_NUMBER = 6;
        public static final int DEVICE_ID_FIELD_NUMBER = 5;
        public static final int DEVICE_TYPE_FIELD_NUMBER = 4;
        public static final int METADATA_FIELD_NUMBER = 12;
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser<DeviceInformation> PARSER = null;
        public static final int PRODUCT_COLOR_FIELD_NUMBER = 8;
        public static final int SERIAL_NUMBER_FIELD_NUMBER = 1;
        public static final int STATUS_FIELD_NUMBER = 7;
        public static final int SUPPORTED_SPEECH_INITIATIONS_FIELD_NUMBER = 10;
        public static final int SUPPORTED_TRANSPORTS_FIELD_NUMBER = 3;
        public static final int SUPPORTED_WAKEWORDS_FIELD_NUMBER = 11;
        private DeviceBattery battery_;
        private int bitField0_;
        private int deviceId_;
        private int productColor_;
        private DeviceStatus status_;
        private int supportedSpeechInitiationsMemoizedSerializedSize;
        private int supportedTransportsMemoizedSerializedSize;
        private static final Internal.ListAdapter.Converter<Integer, Common.Transport> supportedTransports_converter_ = new Internal.ListAdapter.Converter<Integer, Common.Transport>() { // from class: com.amazon.alexa.accessory.protocol.Device.DeviceInformation.1
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public Common.Transport convert(Integer num) {
                Common.Transport forNumber = Common.Transport.forNumber(num.intValue());
                return forNumber == null ? Common.Transport.UNRECOGNIZED : forNumber;
            }
        };
        private static final Internal.ListAdapter.Converter<Integer, Common.SpeechInitiationType> supportedSpeechInitiations_converter_ = new Internal.ListAdapter.Converter<Integer, Common.SpeechInitiationType>() { // from class: com.amazon.alexa.accessory.protocol.Device.DeviceInformation.2
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public Common.SpeechInitiationType convert(Integer num) {
                Common.SpeechInitiationType forNumber = Common.SpeechInitiationType.forNumber(num.intValue());
                return forNumber == null ? Common.SpeechInitiationType.UNRECOGNIZED : forNumber;
            }
        };
        private static final DeviceInformation DEFAULT_INSTANCE = new DeviceInformation();
        private int associatedDevicesMemoizedSerializedSize = -1;
        private MapFieldLite<String, String> metadata_ = MapFieldLite.emptyMapField();
        private String serialNumber_ = "";
        private String name_ = "";
        private Internal.IntList supportedTransports_ = GeneratedMessageLite.emptyIntList();
        private String deviceType_ = "";
        private Internal.IntList associatedDevices_ = GeneratedMessageLite.emptyIntList();
        private Internal.IntList supportedSpeechInitiations_ = GeneratedMessageLite.emptyIntList();
        private Internal.ProtobufList<String> supportedWakewords_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<DeviceInformation, Builder> implements DeviceInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllAssociatedDevices(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addAllAssociatedDevices(iterable);
                return this;
            }

            public Builder addAllSupportedSpeechInitiations(Iterable<? extends Common.SpeechInitiationType> iterable) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addAllSupportedSpeechInitiations(iterable);
                return this;
            }

            public Builder addAllSupportedSpeechInitiationsValue(Iterable<Integer> iterable) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addAllSupportedSpeechInitiationsValue(iterable);
                return this;
            }

            public Builder addAllSupportedTransports(Iterable<? extends Common.Transport> iterable) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addAllSupportedTransports(iterable);
                return this;
            }

            public Builder addAllSupportedTransportsValue(Iterable<Integer> iterable) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addAllSupportedTransportsValue(iterable);
                return this;
            }

            public Builder addAllSupportedWakewords(Iterable<String> iterable) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addAllSupportedWakewords(iterable);
                return this;
            }

            public Builder addAssociatedDevices(int i) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addAssociatedDevices(i);
                return this;
            }

            public Builder addSupportedSpeechInitiations(Common.SpeechInitiationType speechInitiationType) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addSupportedSpeechInitiations(speechInitiationType);
                return this;
            }

            public Builder addSupportedSpeechInitiationsValue(int i) {
                ((DeviceInformation) this.instance).addSupportedSpeechInitiationsValue(i);
                return this;
            }

            public Builder addSupportedTransports(Common.Transport transport) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addSupportedTransports(transport);
                return this;
            }

            public Builder addSupportedTransportsValue(int i) {
                ((DeviceInformation) this.instance).addSupportedTransportsValue(i);
                return this;
            }

            public Builder addSupportedWakewords(String str) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addSupportedWakewords(str);
                return this;
            }

            public Builder addSupportedWakewordsBytes(ByteString byteString) {
                copyOnWrite();
                ((DeviceInformation) this.instance).addSupportedWakewordsBytes(byteString);
                return this;
            }

            public Builder clearAssociatedDevices() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearAssociatedDevices();
                return this;
            }

            public Builder clearBattery() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearBattery();
                return this;
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearDeviceId();
                return this;
            }

            public Builder clearDeviceType() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearDeviceType();
                return this;
            }

            public Builder clearMetadata() {
                copyOnWrite();
                ((DeviceInformation) this.instance).getMutableMetadataMap().clear();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearName();
                return this;
            }

            public Builder clearProductColor() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearProductColor();
                return this;
            }

            public Builder clearSerialNumber() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearSerialNumber();
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearStatus();
                return this;
            }

            public Builder clearSupportedSpeechInitiations() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearSupportedSpeechInitiations();
                return this;
            }

            public Builder clearSupportedTransports() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearSupportedTransports();
                return this;
            }

            public Builder clearSupportedWakewords() {
                copyOnWrite();
                ((DeviceInformation) this.instance).clearSupportedWakewords();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public boolean containsMetadata(String str) {
                if (str != null) {
                    return ((DeviceInformation) this.instance).getMetadataMap().containsKey(str);
                }
                throw new NullPointerException();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getAssociatedDevices(int i) {
                return ((DeviceInformation) this.instance).getAssociatedDevices(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getAssociatedDevicesCount() {
                return ((DeviceInformation) this.instance).getAssociatedDevicesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public List<Integer> getAssociatedDevicesList() {
                return Collections.unmodifiableList(((DeviceInformation) this.instance).getAssociatedDevicesList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public DeviceBattery getBattery() {
                return ((DeviceInformation) this.instance).getBattery();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getDeviceId() {
                return ((DeviceInformation) this.instance).getDeviceId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public String getDeviceType() {
                return ((DeviceInformation) this.instance).getDeviceType();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public ByteString getDeviceTypeBytes() {
                return ((DeviceInformation) this.instance).getDeviceTypeBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            @Deprecated
            public Map<String, String> getMetadata() {
                return getMetadataMap();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getMetadataCount() {
                return ((DeviceInformation) this.instance).getMetadataMap().size();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public Map<String, String> getMetadataMap() {
                return Collections.unmodifiableMap(((DeviceInformation) this.instance).getMetadataMap());
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public String getMetadataOrDefault(String str, String str2) {
                if (str != null) {
                    Map<String, String> metadataMap = ((DeviceInformation) this.instance).getMetadataMap();
                    return metadataMap.containsKey(str) ? metadataMap.get(str) : str2;
                }
                throw new NullPointerException();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public String getMetadataOrThrow(String str) {
                if (str != null) {
                    Map<String, String> metadataMap = ((DeviceInformation) this.instance).getMetadataMap();
                    if (metadataMap.containsKey(str)) {
                        return metadataMap.get(str);
                    }
                    throw new IllegalArgumentException();
                }
                throw new NullPointerException();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public String getName() {
                return ((DeviceInformation) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public ByteString getNameBytes() {
                return ((DeviceInformation) this.instance).getNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getProductColor() {
                return ((DeviceInformation) this.instance).getProductColor();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public String getSerialNumber() {
                return ((DeviceInformation) this.instance).getSerialNumber();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public ByteString getSerialNumberBytes() {
                return ((DeviceInformation) this.instance).getSerialNumberBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public DeviceStatus getStatus() {
                return ((DeviceInformation) this.instance).getStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public Common.SpeechInitiationType getSupportedSpeechInitiations(int i) {
                return ((DeviceInformation) this.instance).getSupportedSpeechInitiations(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getSupportedSpeechInitiationsCount() {
                return ((DeviceInformation) this.instance).getSupportedSpeechInitiationsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public List<Common.SpeechInitiationType> getSupportedSpeechInitiationsList() {
                return ((DeviceInformation) this.instance).getSupportedSpeechInitiationsList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getSupportedSpeechInitiationsValue(int i) {
                return ((DeviceInformation) this.instance).getSupportedSpeechInitiationsValue(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public List<Integer> getSupportedSpeechInitiationsValueList() {
                return Collections.unmodifiableList(((DeviceInformation) this.instance).getSupportedSpeechInitiationsValueList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public Common.Transport getSupportedTransports(int i) {
                return ((DeviceInformation) this.instance).getSupportedTransports(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getSupportedTransportsCount() {
                return ((DeviceInformation) this.instance).getSupportedTransportsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public List<Common.Transport> getSupportedTransportsList() {
                return ((DeviceInformation) this.instance).getSupportedTransportsList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getSupportedTransportsValue(int i) {
                return ((DeviceInformation) this.instance).getSupportedTransportsValue(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public List<Integer> getSupportedTransportsValueList() {
                return Collections.unmodifiableList(((DeviceInformation) this.instance).getSupportedTransportsValueList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public String getSupportedWakewords(int i) {
                return ((DeviceInformation) this.instance).getSupportedWakewords(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public ByteString getSupportedWakewordsBytes(int i) {
                return ((DeviceInformation) this.instance).getSupportedWakewordsBytes(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public int getSupportedWakewordsCount() {
                return ((DeviceInformation) this.instance).getSupportedWakewordsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public List<String> getSupportedWakewordsList() {
                return Collections.unmodifiableList(((DeviceInformation) this.instance).getSupportedWakewordsList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public boolean hasBattery() {
                return ((DeviceInformation) this.instance).hasBattery();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
            public boolean hasStatus() {
                return ((DeviceInformation) this.instance).hasStatus();
            }

            public Builder mergeBattery(DeviceBattery deviceBattery) {
                copyOnWrite();
                ((DeviceInformation) this.instance).mergeBattery(deviceBattery);
                return this;
            }

            public Builder mergeStatus(DeviceStatus deviceStatus) {
                copyOnWrite();
                ((DeviceInformation) this.instance).mergeStatus(deviceStatus);
                return this;
            }

            public Builder putAllMetadata(Map<String, String> map) {
                copyOnWrite();
                ((DeviceInformation) this.instance).getMutableMetadataMap().putAll(map);
                return this;
            }

            public Builder putMetadata(String str, String str2) {
                if (str != null) {
                    if (str2 != null) {
                        copyOnWrite();
                        ((DeviceInformation) this.instance).getMutableMetadataMap().put(str, str2);
                        return this;
                    }
                    throw new NullPointerException();
                }
                throw new NullPointerException();
            }

            public Builder removeMetadata(String str) {
                if (str != null) {
                    copyOnWrite();
                    ((DeviceInformation) this.instance).getMutableMetadataMap().remove(str);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setAssociatedDevices(int i, int i2) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setAssociatedDevices(i, i2);
                return this;
            }

            public Builder setBattery(DeviceBattery deviceBattery) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setBattery(deviceBattery);
                return this;
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setDeviceId(i);
                return this;
            }

            public Builder setDeviceType(String str) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setDeviceType(str);
                return this;
            }

            public Builder setDeviceTypeBytes(ByteString byteString) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setDeviceTypeBytes(byteString);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setProductColor(int i) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setProductColor(i);
                return this;
            }

            public Builder setSerialNumber(String str) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setSerialNumber(str);
                return this;
            }

            public Builder setSerialNumberBytes(ByteString byteString) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setSerialNumberBytes(byteString);
                return this;
            }

            public Builder setStatus(DeviceStatus deviceStatus) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setStatus(deviceStatus);
                return this;
            }

            public Builder setSupportedSpeechInitiations(int i, Common.SpeechInitiationType speechInitiationType) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setSupportedSpeechInitiations(i, speechInitiationType);
                return this;
            }

            public Builder setSupportedSpeechInitiationsValue(int i, int i2) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setSupportedSpeechInitiationsValue(i, i2);
                return this;
            }

            public Builder setSupportedTransports(int i, Common.Transport transport) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setSupportedTransports(i, transport);
                return this;
            }

            public Builder setSupportedTransportsValue(int i, int i2) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setSupportedTransportsValue(i, i2);
                return this;
            }

            public Builder setSupportedWakewords(int i, String str) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setSupportedWakewords(i, str);
                return this;
            }

            private Builder() {
                super(DeviceInformation.DEFAULT_INSTANCE);
            }

            public Builder setBattery(DeviceBattery.Builder builder) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setBattery(builder);
                return this;
            }

            public Builder setStatus(DeviceStatus.Builder builder) {
                copyOnWrite();
                ((DeviceInformation) this.instance).setStatus(builder);
                return this;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static final class MetadataDefaultEntryHolder {
            static final MapEntryLite<String, String> defaultEntry;

            static {
                WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
                defaultEntry = MapEntryLite.newDefaultInstance(fieldType, "", fieldType, "");
            }

            private MetadataDefaultEntryHolder() {
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private DeviceInformation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAssociatedDevices(Iterable<? extends Integer> iterable) {
            ensureAssociatedDevicesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.associatedDevices_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedSpeechInitiations(Iterable<? extends Common.SpeechInitiationType> iterable) {
            ensureSupportedSpeechInitiationsIsMutable();
            for (Common.SpeechInitiationType speechInitiationType : iterable) {
                this.supportedSpeechInitiations_.addInt(speechInitiationType.getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedSpeechInitiationsValue(Iterable<Integer> iterable) {
            ensureSupportedSpeechInitiationsIsMutable();
            for (Integer num : iterable) {
                this.supportedSpeechInitiations_.addInt(num.intValue());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedTransports(Iterable<? extends Common.Transport> iterable) {
            ensureSupportedTransportsIsMutable();
            for (Common.Transport transport : iterable) {
                this.supportedTransports_.addInt(transport.getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedTransportsValue(Iterable<Integer> iterable) {
            ensureSupportedTransportsIsMutable();
            for (Integer num : iterable) {
                this.supportedTransports_.addInt(num.intValue());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedWakewords(Iterable<String> iterable) {
            ensureSupportedWakewordsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.supportedWakewords_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAssociatedDevices(int i) {
            ensureAssociatedDevicesIsMutable();
            this.associatedDevices_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedSpeechInitiations(Common.SpeechInitiationType speechInitiationType) {
            if (speechInitiationType != null) {
                ensureSupportedSpeechInitiationsIsMutable();
                this.supportedSpeechInitiations_.addInt(speechInitiationType.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedSpeechInitiationsValue(int i) {
            ensureSupportedSpeechInitiationsIsMutable();
            this.supportedSpeechInitiations_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedTransports(Common.Transport transport) {
            if (transport != null) {
                ensureSupportedTransportsIsMutable();
                this.supportedTransports_.addInt(transport.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedTransportsValue(int i) {
            ensureSupportedTransportsIsMutable();
            this.supportedTransports_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedWakewords(String str) {
            if (str != null) {
                ensureSupportedWakewordsIsMutable();
                this.supportedWakewords_.add(str);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedWakewordsBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                ensureSupportedWakewordsIsMutable();
                this.supportedWakewords_.add(byteString.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAssociatedDevices() {
            this.associatedDevices_ = GeneratedMessageLite.emptyIntList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBattery() {
            this.battery_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceType() {
            this.deviceType_ = getDefaultInstance().getDeviceType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProductColor() {
            this.productColor_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSerialNumber() {
            this.serialNumber_ = getDefaultInstance().getSerialNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.status_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedSpeechInitiations() {
            this.supportedSpeechInitiations_ = GeneratedMessageLite.emptyIntList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedTransports() {
            this.supportedTransports_ = GeneratedMessageLite.emptyIntList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedWakewords() {
            this.supportedWakewords_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureAssociatedDevicesIsMutable() {
            if (!this.associatedDevices_.isModifiable()) {
                this.associatedDevices_ = GeneratedMessageLite.mutableCopy(this.associatedDevices_);
            }
        }

        private void ensureSupportedSpeechInitiationsIsMutable() {
            if (!this.supportedSpeechInitiations_.isModifiable()) {
                this.supportedSpeechInitiations_ = GeneratedMessageLite.mutableCopy(this.supportedSpeechInitiations_);
            }
        }

        private void ensureSupportedTransportsIsMutable() {
            if (!this.supportedTransports_.isModifiable()) {
                this.supportedTransports_ = GeneratedMessageLite.mutableCopy(this.supportedTransports_);
            }
        }

        private void ensureSupportedWakewordsIsMutable() {
            if (!this.supportedWakewords_.isModifiable()) {
                this.supportedWakewords_ = GeneratedMessageLite.mutableCopy(this.supportedWakewords_);
            }
        }

        public static DeviceInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<String, String> getMutableMetadataMap() {
            return internalGetMutableMetadata();
        }

        private MapFieldLite<String, String> internalGetMetadata() {
            return this.metadata_;
        }

        private MapFieldLite<String, String> internalGetMutableMetadata() {
            if (!this.metadata_.isMutable()) {
                this.metadata_ = this.metadata_.mutableCopy();
            }
            return this.metadata_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBattery(DeviceBattery deviceBattery) {
            DeviceBattery deviceBattery2 = this.battery_;
            if (deviceBattery2 != null && deviceBattery2 != DeviceBattery.getDefaultInstance()) {
                this.battery_ = DeviceBattery.newBuilder(this.battery_).mergeFrom((DeviceBattery.Builder) deviceBattery).mo10085buildPartial();
            } else {
                this.battery_ = deviceBattery;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStatus(DeviceStatus deviceStatus) {
            DeviceStatus deviceStatus2 = this.status_;
            if (deviceStatus2 != null && deviceStatus2 != DeviceStatus.getDefaultInstance()) {
                this.status_ = DeviceStatus.newBuilder(this.status_).mergeFrom((DeviceStatus.Builder) deviceStatus).mo10085buildPartial();
            } else {
                this.status_ = deviceStatus;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DeviceInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DeviceInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DeviceInformation> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAssociatedDevices(int i, int i2) {
            ensureAssociatedDevicesIsMutable();
            this.associatedDevices_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBattery(DeviceBattery deviceBattery) {
            if (deviceBattery != null) {
                this.battery_ = deviceBattery;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceId(int i) {
            this.deviceId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceType(String str) {
            if (str != null) {
                this.deviceType_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceTypeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.deviceType_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            if (str != null) {
                this.name_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.name_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProductColor(int i) {
            this.productColor_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSerialNumber(String str) {
            if (str != null) {
                this.serialNumber_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSerialNumberBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.serialNumber_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(DeviceStatus deviceStatus) {
            if (deviceStatus != null) {
                this.status_ = deviceStatus;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedSpeechInitiations(int i, Common.SpeechInitiationType speechInitiationType) {
            if (speechInitiationType != null) {
                ensureSupportedSpeechInitiationsIsMutable();
                this.supportedSpeechInitiations_.setInt(i, speechInitiationType.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedSpeechInitiationsValue(int i, int i2) {
            ensureSupportedSpeechInitiationsIsMutable();
            this.supportedSpeechInitiations_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedTransports(int i, Common.Transport transport) {
            if (transport != null) {
                ensureSupportedTransportsIsMutable();
                this.supportedTransports_.setInt(i, transport.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedTransportsValue(int i, int i2) {
            ensureSupportedTransportsIsMutable();
            this.supportedTransports_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedWakewords(int i, String str) {
            if (str != null) {
                ensureSupportedWakewordsIsMutable();
                this.supportedWakewords_.set(i, str);
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public boolean containsMetadata(String str) {
            if (str != null) {
                return internalGetMetadata().containsKey(str);
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    DeviceInformation deviceInformation = (DeviceInformation) obj2;
                    this.serialNumber_ = visitor.visitString(!this.serialNumber_.isEmpty(), this.serialNumber_, !deviceInformation.serialNumber_.isEmpty(), deviceInformation.serialNumber_);
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !deviceInformation.name_.isEmpty(), deviceInformation.name_);
                    this.supportedTransports_ = visitor.visitIntList(this.supportedTransports_, deviceInformation.supportedTransports_);
                    this.deviceType_ = visitor.visitString(!this.deviceType_.isEmpty(), this.deviceType_, !deviceInformation.deviceType_.isEmpty(), deviceInformation.deviceType_);
                    this.deviceId_ = visitor.visitInt(this.deviceId_ != 0, this.deviceId_, deviceInformation.deviceId_ != 0, deviceInformation.deviceId_);
                    this.battery_ = (DeviceBattery) visitor.visitMessage(this.battery_, deviceInformation.battery_);
                    this.status_ = (DeviceStatus) visitor.visitMessage(this.status_, deviceInformation.status_);
                    boolean z2 = this.productColor_ != 0;
                    int i = this.productColor_;
                    if (deviceInformation.productColor_ != 0) {
                        z = true;
                    }
                    this.productColor_ = visitor.visitInt(z2, i, z, deviceInformation.productColor_);
                    this.associatedDevices_ = visitor.visitIntList(this.associatedDevices_, deviceInformation.associatedDevices_);
                    this.supportedSpeechInitiations_ = visitor.visitIntList(this.supportedSpeechInitiations_, deviceInformation.supportedSpeechInitiations_);
                    this.supportedWakewords_ = visitor.visitList(this.supportedWakewords_, deviceInformation.supportedWakewords_);
                    this.metadata_ = visitor.visitMap(this.metadata_, deviceInformation.internalGetMetadata());
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= deviceInformation.bitField0_;
                    }
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            switch (readTag) {
                                case 0:
                                    break;
                                case 10:
                                    this.serialNumber_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 18:
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 24:
                                    if (!this.supportedTransports_.isModifiable()) {
                                        this.supportedTransports_ = GeneratedMessageLite.mutableCopy(this.supportedTransports_);
                                    }
                                    this.supportedTransports_.addInt(codedInputStream.readEnum());
                                    continue;
                                case 26:
                                    if (!this.supportedTransports_.isModifiable()) {
                                        this.supportedTransports_ = GeneratedMessageLite.mutableCopy(this.supportedTransports_);
                                    }
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.supportedTransports_.addInt(codedInputStream.readEnum());
                                    }
                                    codedInputStream.popLimit(pushLimit);
                                    continue;
                                case 34:
                                    this.deviceType_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 40:
                                    this.deviceId_ = codedInputStream.readUInt32();
                                    continue;
                                case 50:
                                    DeviceBattery.Builder mo10081toBuilder = this.battery_ != null ? this.battery_.mo10081toBuilder() : null;
                                    this.battery_ = (DeviceBattery) codedInputStream.readMessage(DeviceBattery.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((DeviceBattery.Builder) this.battery_);
                                        this.battery_ = mo10081toBuilder.mo10085buildPartial();
                                    } else {
                                        continue;
                                    }
                                case 58:
                                    DeviceStatus.Builder mo10081toBuilder2 = this.status_ != null ? this.status_.mo10081toBuilder() : null;
                                    this.status_ = (DeviceStatus) codedInputStream.readMessage(DeviceStatus.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder2 != null) {
                                        mo10081toBuilder2.mergeFrom((DeviceStatus.Builder) this.status_);
                                        this.status_ = mo10081toBuilder2.mo10085buildPartial();
                                    } else {
                                        continue;
                                    }
                                case 64:
                                    this.productColor_ = codedInputStream.readUInt32();
                                    continue;
                                case 72:
                                    if (!this.associatedDevices_.isModifiable()) {
                                        this.associatedDevices_ = GeneratedMessageLite.mutableCopy(this.associatedDevices_);
                                    }
                                    this.associatedDevices_.addInt(codedInputStream.readUInt32());
                                    continue;
                                case 74:
                                    int pushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if (!this.associatedDevices_.isModifiable() && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.associatedDevices_ = GeneratedMessageLite.mutableCopy(this.associatedDevices_);
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.associatedDevices_.addInt(codedInputStream.readUInt32());
                                    }
                                    codedInputStream.popLimit(pushLimit2);
                                    continue;
                                    break;
                                case 80:
                                    if (!this.supportedSpeechInitiations_.isModifiable()) {
                                        this.supportedSpeechInitiations_ = GeneratedMessageLite.mutableCopy(this.supportedSpeechInitiations_);
                                    }
                                    this.supportedSpeechInitiations_.addInt(codedInputStream.readEnum());
                                    continue;
                                case 82:
                                    if (!this.supportedSpeechInitiations_.isModifiable()) {
                                        this.supportedSpeechInitiations_ = GeneratedMessageLite.mutableCopy(this.supportedSpeechInitiations_);
                                    }
                                    int pushLimit3 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.supportedSpeechInitiations_.addInt(codedInputStream.readEnum());
                                    }
                                    codedInputStream.popLimit(pushLimit3);
                                    continue;
                                case 90:
                                    String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    if (!this.supportedWakewords_.isModifiable()) {
                                        this.supportedWakewords_ = GeneratedMessageLite.mutableCopy(this.supportedWakewords_);
                                    }
                                    this.supportedWakewords_.add(readStringRequireUtf8);
                                    continue;
                                case 98:
                                    if (!this.metadata_.isMutable()) {
                                        this.metadata_ = this.metadata_.mutableCopy();
                                    }
                                    MetadataDefaultEntryHolder.defaultEntry.parseInto(this.metadata_, codedInputStream, extensionRegistryLite);
                                    continue;
                                default:
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                        break;
                                    } else {
                                        continue;
                                    }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                    break;
                case 5:
                    this.supportedTransports_.makeImmutable();
                    this.associatedDevices_.makeImmutable();
                    this.supportedSpeechInitiations_.makeImmutable();
                    this.supportedWakewords_.makeImmutable();
                    this.metadata_.makeImmutable();
                    return null;
                case 6:
                    return new DeviceInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (DeviceInformation.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getAssociatedDevices(int i) {
            return this.associatedDevices_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getAssociatedDevicesCount() {
            return this.associatedDevices_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public List<Integer> getAssociatedDevicesList() {
            return this.associatedDevices_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public DeviceBattery getBattery() {
            DeviceBattery deviceBattery = this.battery_;
            return deviceBattery == null ? DeviceBattery.getDefaultInstance() : deviceBattery;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public String getDeviceType() {
            return this.deviceType_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public ByteString getDeviceTypeBytes() {
            return ByteString.copyFromUtf8(this.deviceType_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        @Deprecated
        public Map<String, String> getMetadata() {
            return getMetadataMap();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getMetadataCount() {
            return internalGetMetadata().size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public Map<String, String> getMetadataMap() {
            return Collections.unmodifiableMap(internalGetMetadata());
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public String getMetadataOrDefault(String str, String str2) {
            if (str != null) {
                MapFieldLite<String, String> internalGetMetadata = internalGetMetadata();
                return internalGetMetadata.containsKey(str) ? internalGetMetadata.get(str) : str2;
            }
            throw new NullPointerException();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public String getMetadataOrThrow(String str) {
            if (str != null) {
                MapFieldLite<String, String> internalGetMetadata = internalGetMetadata();
                if (internalGetMetadata.containsKey(str)) {
                    return internalGetMetadata.get(str);
                }
                throw new IllegalArgumentException();
            }
            throw new NullPointerException();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getProductColor() {
            return this.productColor_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public String getSerialNumber() {
            return this.serialNumber_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public ByteString getSerialNumberBytes() {
            return ByteString.copyFromUtf8(this.serialNumber_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = !this.serialNumber_.isEmpty() ? CodedOutputStream.computeStringSize(1, getSerialNumber()) + 0 : 0;
            if (!this.name_.isEmpty()) {
                computeStringSize += CodedOutputStream.computeStringSize(2, getName());
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.supportedTransports_.size(); i3++) {
                i2 += CodedOutputStream.computeEnumSizeNoTag(this.supportedTransports_.getInt(i3));
            }
            int i4 = computeStringSize + i2;
            if (!getSupportedTransportsList().isEmpty()) {
                i4 = i4 + 1 + CodedOutputStream.computeUInt32SizeNoTag(i2);
            }
            this.supportedTransportsMemoizedSerializedSize = i2;
            if (!this.deviceType_.isEmpty()) {
                i4 += CodedOutputStream.computeStringSize(4, getDeviceType());
            }
            int i5 = this.deviceId_;
            if (i5 != 0) {
                i4 += CodedOutputStream.computeUInt32Size(5, i5);
            }
            if (this.battery_ != null) {
                i4 += CodedOutputStream.computeMessageSize(6, getBattery());
            }
            if (this.status_ != null) {
                i4 += CodedOutputStream.computeMessageSize(7, getStatus());
            }
            int i6 = this.productColor_;
            if (i6 != 0) {
                i4 += CodedOutputStream.computeUInt32Size(8, i6);
            }
            int i7 = 0;
            for (int i8 = 0; i8 < this.associatedDevices_.size(); i8++) {
                i7 += CodedOutputStream.computeUInt32SizeNoTag(this.associatedDevices_.getInt(i8));
            }
            int i9 = i4 + i7;
            if (!getAssociatedDevicesList().isEmpty()) {
                i9 = i9 + 1 + CodedOutputStream.computeInt32SizeNoTag(i7);
            }
            this.associatedDevicesMemoizedSerializedSize = i7;
            int i10 = 0;
            for (int i11 = 0; i11 < this.supportedSpeechInitiations_.size(); i11++) {
                i10 += CodedOutputStream.computeEnumSizeNoTag(this.supportedSpeechInitiations_.getInt(i11));
            }
            int i12 = i9 + i10;
            if (!getSupportedSpeechInitiationsList().isEmpty()) {
                i12 = i12 + 1 + CodedOutputStream.computeUInt32SizeNoTag(i10);
            }
            this.supportedSpeechInitiationsMemoizedSerializedSize = i10;
            int i13 = 0;
            for (int i14 = 0; i14 < this.supportedWakewords_.size(); i14++) {
                i13 += CodedOutputStream.computeStringSizeNoTag(this.supportedWakewords_.get(i14));
            }
            int size = (getSupportedWakewordsList().size() * 1) + i12 + i13;
            for (Map.Entry<String, String> entry : internalGetMetadata().entrySet()) {
                size += MetadataDefaultEntryHolder.defaultEntry.computeMessageSize(12, entry.getKey(), entry.getValue());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + size;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public DeviceStatus getStatus() {
            DeviceStatus deviceStatus = this.status_;
            return deviceStatus == null ? DeviceStatus.getDefaultInstance() : deviceStatus;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public Common.SpeechInitiationType getSupportedSpeechInitiations(int i) {
            return supportedSpeechInitiations_converter_.convert(Integer.valueOf(this.supportedSpeechInitiations_.getInt(i)));
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getSupportedSpeechInitiationsCount() {
            return this.supportedSpeechInitiations_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public List<Common.SpeechInitiationType> getSupportedSpeechInitiationsList() {
            return new Internal.ListAdapter(this.supportedSpeechInitiations_, supportedSpeechInitiations_converter_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getSupportedSpeechInitiationsValue(int i) {
            return this.supportedSpeechInitiations_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public List<Integer> getSupportedSpeechInitiationsValueList() {
            return this.supportedSpeechInitiations_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public Common.Transport getSupportedTransports(int i) {
            return supportedTransports_converter_.convert(Integer.valueOf(this.supportedTransports_.getInt(i)));
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getSupportedTransportsCount() {
            return this.supportedTransports_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public List<Common.Transport> getSupportedTransportsList() {
            return new Internal.ListAdapter(this.supportedTransports_, supportedTransports_converter_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getSupportedTransportsValue(int i) {
            return this.supportedTransports_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public List<Integer> getSupportedTransportsValueList() {
            return this.supportedTransports_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public String getSupportedWakewords(int i) {
            return this.supportedWakewords_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public ByteString getSupportedWakewordsBytes(int i) {
            return ByteString.copyFromUtf8(this.supportedWakewords_.get(i));
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public int getSupportedWakewordsCount() {
            return this.supportedWakewords_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public List<String> getSupportedWakewordsList() {
            return this.supportedWakewords_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public boolean hasBattery() {
            return this.battery_ != null;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceInformationOrBuilder
        public boolean hasStatus() {
            return this.status_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if (!this.serialNumber_.isEmpty()) {
                codedOutputStream.writeString(1, getSerialNumber());
            }
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(2, getName());
            }
            if (getSupportedTransportsList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(26);
                codedOutputStream.writeUInt32NoTag(this.supportedTransportsMemoizedSerializedSize);
            }
            for (int i = 0; i < this.supportedTransports_.size(); i++) {
                codedOutputStream.writeEnumNoTag(this.supportedTransports_.getInt(i));
            }
            if (!this.deviceType_.isEmpty()) {
                codedOutputStream.writeString(4, getDeviceType());
            }
            int i2 = this.deviceId_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(5, i2);
            }
            if (this.battery_ != null) {
                codedOutputStream.writeMessage(6, getBattery());
            }
            if (this.status_ != null) {
                codedOutputStream.writeMessage(7, getStatus());
            }
            int i3 = this.productColor_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(8, i3);
            }
            if (getAssociatedDevicesList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(74);
                codedOutputStream.writeUInt32NoTag(this.associatedDevicesMemoizedSerializedSize);
            }
            for (int i4 = 0; i4 < this.associatedDevices_.size(); i4++) {
                codedOutputStream.writeUInt32NoTag(this.associatedDevices_.getInt(i4));
            }
            if (getSupportedSpeechInitiationsList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(82);
                codedOutputStream.writeUInt32NoTag(this.supportedSpeechInitiationsMemoizedSerializedSize);
            }
            for (int i5 = 0; i5 < this.supportedSpeechInitiations_.size(); i5++) {
                codedOutputStream.writeEnumNoTag(this.supportedSpeechInitiations_.getInt(i5));
            }
            for (int i6 = 0; i6 < this.supportedWakewords_.size(); i6++) {
                codedOutputStream.writeString(11, this.supportedWakewords_.get(i6));
            }
            for (Map.Entry<String, String> entry : internalGetMetadata().entrySet()) {
                MetadataDefaultEntryHolder.defaultEntry.serializeTo(codedOutputStream, 12, entry.getKey(), entry.getValue());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(DeviceInformation deviceInformation) {
            return DEFAULT_INSTANCE.createBuilder(deviceInformation);
        }

        public static DeviceInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DeviceInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBattery(DeviceBattery.Builder builder) {
            this.battery_ = builder.mo10084build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(DeviceStatus.Builder builder) {
            this.status_ = builder.mo10084build();
        }

        public static DeviceInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DeviceInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DeviceInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DeviceInformation parseFrom(InputStream inputStream) throws IOException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DeviceInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface DeviceInformationOrBuilder extends MessageLiteOrBuilder {
        boolean containsMetadata(String str);

        int getAssociatedDevices(int i);

        int getAssociatedDevicesCount();

        List<Integer> getAssociatedDevicesList();

        DeviceBattery getBattery();

        int getDeviceId();

        String getDeviceType();

        ByteString getDeviceTypeBytes();

        @Deprecated
        Map<String, String> getMetadata();

        int getMetadataCount();

        Map<String, String> getMetadataMap();

        String getMetadataOrDefault(String str, String str2);

        String getMetadataOrThrow(String str);

        String getName();

        ByteString getNameBytes();

        int getProductColor();

        String getSerialNumber();

        ByteString getSerialNumberBytes();

        DeviceStatus getStatus();

        Common.SpeechInitiationType getSupportedSpeechInitiations(int i);

        int getSupportedSpeechInitiationsCount();

        List<Common.SpeechInitiationType> getSupportedSpeechInitiationsList();

        int getSupportedSpeechInitiationsValue(int i);

        List<Integer> getSupportedSpeechInitiationsValueList();

        Common.Transport getSupportedTransports(int i);

        int getSupportedTransportsCount();

        List<Common.Transport> getSupportedTransportsList();

        int getSupportedTransportsValue(int i);

        List<Integer> getSupportedTransportsValueList();

        String getSupportedWakewords(int i);

        ByteString getSupportedWakewordsBytes(int i);

        int getSupportedWakewordsCount();

        List<String> getSupportedWakewordsList();

        boolean hasBattery();

        boolean hasStatus();
    }

    /* loaded from: classes6.dex */
    public enum DevicePresence implements Internal.EnumLite {
        DEVICE_PRESENCE_UNKNOWN(0),
        DEVICE_PRESENCE_ACTIVE(1),
        DEVICE_PRESENCE_INACTIVE(2),
        DEVICE_PRESENCE_ACCESSIBLE(3),
        UNRECOGNIZED(-1);
        
        public static final int DEVICE_PRESENCE_ACCESSIBLE_VALUE = 3;
        public static final int DEVICE_PRESENCE_ACTIVE_VALUE = 1;
        public static final int DEVICE_PRESENCE_INACTIVE_VALUE = 2;
        public static final int DEVICE_PRESENCE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<DevicePresence> internalValueMap = new Internal.EnumLiteMap<DevicePresence>() { // from class: com.amazon.alexa.accessory.protocol.Device.DevicePresence.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public DevicePresence mo9850findValueByNumber(int i) {
                return DevicePresence.forNumber(i);
            }
        };
        private final int value;

        DevicePresence(int i) {
            this.value = i;
        }

        public static DevicePresence forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return DEVICE_PRESENCE_ACTIVE;
                }
                if (i == 2) {
                    return DEVICE_PRESENCE_INACTIVE;
                }
                if (i == 3) {
                    return DEVICE_PRESENCE_ACCESSIBLE;
                }
                return null;
            }
            return DEVICE_PRESENCE_UNKNOWN;
        }

        public static Internal.EnumLiteMap<DevicePresence> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static DevicePresence valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class DeviceStatus extends GeneratedMessageLite<DeviceStatus, Builder> implements DeviceStatusOrBuilder {
        private static final DeviceStatus DEFAULT_INSTANCE = new DeviceStatus();
        public static final int LINK_FIELD_NUMBER = 1;
        public static final int NFMI_FIELD_NUMBER = 2;
        private static volatile Parser<DeviceStatus> PARSER = null;
        public static final int PRESENCE_FIELD_NUMBER = 3;
        private int link_;
        private int nfmi_;
        private int presence_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<DeviceStatus, Builder> implements DeviceStatusOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearLink() {
                copyOnWrite();
                ((DeviceStatus) this.instance).clearLink();
                return this;
            }

            public Builder clearNfmi() {
                copyOnWrite();
                ((DeviceStatus) this.instance).clearNfmi();
                return this;
            }

            public Builder clearPresence() {
                copyOnWrite();
                ((DeviceStatus) this.instance).clearPresence();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
            public ConnectionStatus getLink() {
                return ((DeviceStatus) this.instance).getLink();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
            public int getLinkValue() {
                return ((DeviceStatus) this.instance).getLinkValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
            public ConnectionStatus getNfmi() {
                return ((DeviceStatus) this.instance).getNfmi();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
            public int getNfmiValue() {
                return ((DeviceStatus) this.instance).getNfmiValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
            public DevicePresence getPresence() {
                return ((DeviceStatus) this.instance).getPresence();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
            public int getPresenceValue() {
                return ((DeviceStatus) this.instance).getPresenceValue();
            }

            public Builder setLink(ConnectionStatus connectionStatus) {
                copyOnWrite();
                ((DeviceStatus) this.instance).setLink(connectionStatus);
                return this;
            }

            public Builder setLinkValue(int i) {
                copyOnWrite();
                ((DeviceStatus) this.instance).setLinkValue(i);
                return this;
            }

            public Builder setNfmi(ConnectionStatus connectionStatus) {
                copyOnWrite();
                ((DeviceStatus) this.instance).setNfmi(connectionStatus);
                return this;
            }

            public Builder setNfmiValue(int i) {
                copyOnWrite();
                ((DeviceStatus) this.instance).setNfmiValue(i);
                return this;
            }

            public Builder setPresence(DevicePresence devicePresence) {
                copyOnWrite();
                ((DeviceStatus) this.instance).setPresence(devicePresence);
                return this;
            }

            public Builder setPresenceValue(int i) {
                copyOnWrite();
                ((DeviceStatus) this.instance).setPresenceValue(i);
                return this;
            }

            private Builder() {
                super(DeviceStatus.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private DeviceStatus() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLink() {
            this.link_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNfmi() {
            this.nfmi_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPresence() {
            this.presence_ = 0;
        }

        public static DeviceStatus getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DeviceStatus parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DeviceStatus) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceStatus parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DeviceStatus> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLink(ConnectionStatus connectionStatus) {
            if (connectionStatus != null) {
                this.link_ = connectionStatus.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLinkValue(int i) {
            this.link_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNfmi(ConnectionStatus connectionStatus) {
            if (connectionStatus != null) {
                this.nfmi_ = connectionStatus.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNfmiValue(int i) {
            this.nfmi_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPresence(DevicePresence devicePresence) {
            if (devicePresence != null) {
                this.presence_ = devicePresence.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPresenceValue(int i) {
            this.presence_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    DeviceStatus deviceStatus = (DeviceStatus) obj2;
                    this.link_ = visitor.visitInt(this.link_ != 0, this.link_, deviceStatus.link_ != 0, deviceStatus.link_);
                    this.nfmi_ = visitor.visitInt(this.nfmi_ != 0, this.nfmi_, deviceStatus.nfmi_ != 0, deviceStatus.nfmi_);
                    boolean z2 = this.presence_ != 0;
                    int i = this.presence_;
                    if (deviceStatus.presence_ != 0) {
                        z = true;
                    }
                    this.presence_ = visitor.visitInt(z2, i, z, deviceStatus.presence_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.link_ = codedInputStream.readEnum();
                                } else if (readTag == 16) {
                                    this.nfmi_ = codedInputStream.readEnum();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.presence_ = codedInputStream.readEnum();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new DeviceStatus();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (DeviceStatus.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
        public ConnectionStatus getLink() {
            ConnectionStatus forNumber = ConnectionStatus.forNumber(this.link_);
            return forNumber == null ? ConnectionStatus.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
        public int getLinkValue() {
            return this.link_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
        public ConnectionStatus getNfmi() {
            ConnectionStatus forNumber = ConnectionStatus.forNumber(this.nfmi_);
            return forNumber == null ? ConnectionStatus.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
        public int getNfmiValue() {
            return this.nfmi_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
        public DevicePresence getPresence() {
            DevicePresence forNumber = DevicePresence.forNumber(this.presence_);
            return forNumber == null ? DevicePresence.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.DeviceStatusOrBuilder
        public int getPresenceValue() {
            return this.presence_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.link_ != ConnectionStatus.CONNECTION_STATUS_UNKNOWN.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.link_);
            }
            if (this.nfmi_ != ConnectionStatus.CONNECTION_STATUS_UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.nfmi_);
            }
            if (this.presence_ != DevicePresence.DEVICE_PRESENCE_UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.presence_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.link_ != ConnectionStatus.CONNECTION_STATUS_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(1, this.link_);
            }
            if (this.nfmi_ != ConnectionStatus.CONNECTION_STATUS_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(2, this.nfmi_);
            }
            if (this.presence_ != DevicePresence.DEVICE_PRESENCE_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(3, this.presence_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(DeviceStatus deviceStatus) {
            return DEFAULT_INSTANCE.createBuilder(deviceStatus);
        }

        public static DeviceStatus parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceStatus) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceStatus parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DeviceStatus parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DeviceStatus parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DeviceStatus parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DeviceStatus parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DeviceStatus parseFrom(InputStream inputStream) throws IOException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceStatus parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceStatus parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DeviceStatus parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface DeviceStatusOrBuilder extends MessageLiteOrBuilder {
        ConnectionStatus getLink();

        int getLinkValue();

        ConnectionStatus getNfmi();

        int getNfmiValue();

        DevicePresence getPresence();

        int getPresenceValue();
    }

    /* loaded from: classes6.dex */
    public static final class FeatureProperties extends GeneratedMessageLite<FeatureProperties, Builder> implements FeaturePropertiesOrBuilder {
        private static final FeatureProperties DEFAULT_INSTANCE = new FeatureProperties();
        public static final int ENVELOPE_VERSION_FIELD_NUMBER = 2;
        public static final int FEATURE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<FeatureProperties> PARSER = null;
        public static final int SUB_FEATURES_FIELD_NUMBER = 3;
        private int envelopeVersion_;
        private int featureId_;
        private int subFeatures_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FeatureProperties, Builder> implements FeaturePropertiesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearEnvelopeVersion() {
                copyOnWrite();
                ((FeatureProperties) this.instance).clearEnvelopeVersion();
                return this;
            }

            public Builder clearFeatureId() {
                copyOnWrite();
                ((FeatureProperties) this.instance).clearFeatureId();
                return this;
            }

            public Builder clearSubFeatures() {
                copyOnWrite();
                ((FeatureProperties) this.instance).clearSubFeatures();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.FeaturePropertiesOrBuilder
            public int getEnvelopeVersion() {
                return ((FeatureProperties) this.instance).getEnvelopeVersion();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.FeaturePropertiesOrBuilder
            public int getFeatureId() {
                return ((FeatureProperties) this.instance).getFeatureId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.FeaturePropertiesOrBuilder
            public int getSubFeatures() {
                return ((FeatureProperties) this.instance).getSubFeatures();
            }

            public Builder setEnvelopeVersion(int i) {
                copyOnWrite();
                ((FeatureProperties) this.instance).setEnvelopeVersion(i);
                return this;
            }

            public Builder setFeatureId(int i) {
                copyOnWrite();
                ((FeatureProperties) this.instance).setFeatureId(i);
                return this;
            }

            public Builder setSubFeatures(int i) {
                copyOnWrite();
                ((FeatureProperties) this.instance).setSubFeatures(i);
                return this;
            }

            private Builder() {
                super(FeatureProperties.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private FeatureProperties() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEnvelopeVersion() {
            this.envelopeVersion_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFeatureId() {
            this.featureId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSubFeatures() {
            this.subFeatures_ = 0;
        }

        public static FeatureProperties getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FeatureProperties parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FeatureProperties) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FeatureProperties parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FeatureProperties> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEnvelopeVersion(int i) {
            this.envelopeVersion_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFeatureId(int i) {
            this.featureId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubFeatures(int i) {
            this.subFeatures_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    FeatureProperties featureProperties = (FeatureProperties) obj2;
                    this.featureId_ = visitor.visitInt(this.featureId_ != 0, this.featureId_, featureProperties.featureId_ != 0, featureProperties.featureId_);
                    this.envelopeVersion_ = visitor.visitInt(this.envelopeVersion_ != 0, this.envelopeVersion_, featureProperties.envelopeVersion_ != 0, featureProperties.envelopeVersion_);
                    boolean z2 = this.subFeatures_ != 0;
                    int i = this.subFeatures_;
                    if (featureProperties.subFeatures_ != 0) {
                        z = true;
                    }
                    this.subFeatures_ = visitor.visitInt(z2, i, z, featureProperties.subFeatures_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.featureId_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.envelopeVersion_ = codedInputStream.readUInt32();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.subFeatures_ = codedInputStream.readUInt32();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new FeatureProperties();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (FeatureProperties.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.FeaturePropertiesOrBuilder
        public int getEnvelopeVersion() {
            return this.envelopeVersion_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.FeaturePropertiesOrBuilder
        public int getFeatureId() {
            return this.featureId_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.featureId_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int i4 = this.envelopeVersion_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
            }
            int i5 = this.subFeatures_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i5);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.FeaturePropertiesOrBuilder
        public int getSubFeatures() {
            return this.subFeatures_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.featureId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.envelopeVersion_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            int i3 = this.subFeatures_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(3, i3);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(FeatureProperties featureProperties) {
            return DEFAULT_INSTANCE.createBuilder(featureProperties);
        }

        public static FeatureProperties parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeatureProperties) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FeatureProperties parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FeatureProperties parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FeatureProperties parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FeatureProperties parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FeatureProperties parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FeatureProperties parseFrom(InputStream inputStream) throws IOException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FeatureProperties parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FeatureProperties parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FeatureProperties parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeatureProperties) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface FeaturePropertiesOrBuilder extends MessageLiteOrBuilder {
        int getEnvelopeVersion();

        int getFeatureId();

        int getSubFeatures();
    }

    /* loaded from: classes6.dex */
    public static final class GetDeviceConfiguration extends GeneratedMessageLite<GetDeviceConfiguration, Builder> implements GetDeviceConfigurationOrBuilder {
        private static final GetDeviceConfiguration DEFAULT_INSTANCE = new GetDeviceConfiguration();
        private static volatile Parser<GetDeviceConfiguration> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetDeviceConfiguration, Builder> implements GetDeviceConfigurationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(GetDeviceConfiguration.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetDeviceConfiguration() {
        }

        public static GetDeviceConfiguration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetDeviceConfiguration parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetDeviceConfiguration parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetDeviceConfiguration> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    GetDeviceConfiguration getDeviceConfiguration = (GetDeviceConfiguration) obj2;
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag == 0 || !parseUnknownField(readTag, codedInputStream)) {
                                z = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new GetDeviceConfiguration();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetDeviceConfiguration.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetDeviceConfiguration getDeviceConfiguration) {
            return DEFAULT_INSTANCE.createBuilder(getDeviceConfiguration);
        }

        public static GetDeviceConfiguration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetDeviceConfiguration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetDeviceConfiguration parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetDeviceConfiguration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetDeviceConfiguration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetDeviceConfiguration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetDeviceConfiguration parseFrom(InputStream inputStream) throws IOException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetDeviceConfiguration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetDeviceConfiguration parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetDeviceConfiguration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetDeviceConfigurationOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class GetDeviceFeatures extends GeneratedMessageLite<GetDeviceFeatures, Builder> implements GetDeviceFeaturesOrBuilder {
        private static final GetDeviceFeatures DEFAULT_INSTANCE = new GetDeviceFeatures();
        private static volatile Parser<GetDeviceFeatures> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetDeviceFeatures, Builder> implements GetDeviceFeaturesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(GetDeviceFeatures.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetDeviceFeatures() {
        }

        public static GetDeviceFeatures getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetDeviceFeatures parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetDeviceFeatures parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetDeviceFeatures> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    GetDeviceFeatures getDeviceFeatures = (GetDeviceFeatures) obj2;
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag == 0 || !parseUnknownField(readTag, codedInputStream)) {
                                z = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new GetDeviceFeatures();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetDeviceFeatures.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetDeviceFeatures getDeviceFeatures) {
            return DEFAULT_INSTANCE.createBuilder(getDeviceFeatures);
        }

        public static GetDeviceFeatures parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetDeviceFeatures parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetDeviceFeatures parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetDeviceFeatures parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetDeviceFeatures parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetDeviceFeatures parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetDeviceFeatures parseFrom(InputStream inputStream) throws IOException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetDeviceFeatures parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetDeviceFeatures parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetDeviceFeatures parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetDeviceFeaturesOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class GetDeviceInformation extends GeneratedMessageLite<GetDeviceInformation, Builder> implements GetDeviceInformationOrBuilder {
        private static final GetDeviceInformation DEFAULT_INSTANCE = new GetDeviceInformation();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<GetDeviceInformation> PARSER;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetDeviceInformation, Builder> implements GetDeviceInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((GetDeviceInformation) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.GetDeviceInformationOrBuilder
            public int getDeviceId() {
                return ((GetDeviceInformation) this.instance).getDeviceId();
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((GetDeviceInformation) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(GetDeviceInformation.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetDeviceInformation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        public static GetDeviceInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetDeviceInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetDeviceInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetDeviceInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetDeviceInformation> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceId(int i) {
            this.deviceId_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    GetDeviceInformation getDeviceInformation = (GetDeviceInformation) obj2;
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (getDeviceInformation.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, getDeviceInformation.deviceId_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 8) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.deviceId_ = codedInputStream.readUInt32();
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this));
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new GetDeviceInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetDeviceInformation.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.GetDeviceInformationOrBuilder
        public int getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.deviceId_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.deviceId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetDeviceInformation getDeviceInformation) {
            return DEFAULT_INSTANCE.createBuilder(getDeviceInformation);
        }

        public static GetDeviceInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetDeviceInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetDeviceInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetDeviceInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetDeviceInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetDeviceInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetDeviceInformation parseFrom(InputStream inputStream) throws IOException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetDeviceInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetDeviceInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetDeviceInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetDeviceInformationOrBuilder extends MessageLiteOrBuilder {
        int getDeviceId();
    }

    /* loaded from: classes6.dex */
    public static final class NotifyDeviceConfiguration extends GeneratedMessageLite<NotifyDeviceConfiguration, Builder> implements NotifyDeviceConfigurationOrBuilder {
        private static final NotifyDeviceConfiguration DEFAULT_INSTANCE = new NotifyDeviceConfiguration();
        public static final int DEVICE_CONFIGURATION_FIELD_NUMBER = 1;
        private static volatile Parser<NotifyDeviceConfiguration> PARSER;
        private DeviceConfiguration deviceConfiguration_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotifyDeviceConfiguration, Builder> implements NotifyDeviceConfigurationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceConfiguration() {
                copyOnWrite();
                ((NotifyDeviceConfiguration) this.instance).clearDeviceConfiguration();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.NotifyDeviceConfigurationOrBuilder
            public DeviceConfiguration getDeviceConfiguration() {
                return ((NotifyDeviceConfiguration) this.instance).getDeviceConfiguration();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.NotifyDeviceConfigurationOrBuilder
            public boolean hasDeviceConfiguration() {
                return ((NotifyDeviceConfiguration) this.instance).hasDeviceConfiguration();
            }

            public Builder mergeDeviceConfiguration(DeviceConfiguration deviceConfiguration) {
                copyOnWrite();
                ((NotifyDeviceConfiguration) this.instance).mergeDeviceConfiguration(deviceConfiguration);
                return this;
            }

            public Builder setDeviceConfiguration(DeviceConfiguration deviceConfiguration) {
                copyOnWrite();
                ((NotifyDeviceConfiguration) this.instance).setDeviceConfiguration(deviceConfiguration);
                return this;
            }

            private Builder() {
                super(NotifyDeviceConfiguration.DEFAULT_INSTANCE);
            }

            public Builder setDeviceConfiguration(DeviceConfiguration.Builder builder) {
                copyOnWrite();
                ((NotifyDeviceConfiguration) this.instance).setDeviceConfiguration(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotifyDeviceConfiguration() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceConfiguration() {
            this.deviceConfiguration_ = null;
        }

        public static NotifyDeviceConfiguration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDeviceConfiguration(DeviceConfiguration deviceConfiguration) {
            DeviceConfiguration deviceConfiguration2 = this.deviceConfiguration_;
            if (deviceConfiguration2 != null && deviceConfiguration2 != DeviceConfiguration.getDefaultInstance()) {
                this.deviceConfiguration_ = DeviceConfiguration.newBuilder(this.deviceConfiguration_).mergeFrom((DeviceConfiguration.Builder) deviceConfiguration).mo10085buildPartial();
            } else {
                this.deviceConfiguration_ = deviceConfiguration;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotifyDeviceConfiguration parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyDeviceConfiguration parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotifyDeviceConfiguration> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceConfiguration(DeviceConfiguration deviceConfiguration) {
            if (deviceConfiguration != null) {
                this.deviceConfiguration_ = deviceConfiguration;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    this.deviceConfiguration_ = (DeviceConfiguration) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.deviceConfiguration_, ((NotifyDeviceConfiguration) obj2).deviceConfiguration_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag != 10) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    DeviceConfiguration.Builder mo10081toBuilder = this.deviceConfiguration_ != null ? this.deviceConfiguration_.mo10081toBuilder() : null;
                                    this.deviceConfiguration_ = (DeviceConfiguration) codedInputStream.readMessage(DeviceConfiguration.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((DeviceConfiguration.Builder) this.deviceConfiguration_);
                                        this.deviceConfiguration_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new NotifyDeviceConfiguration();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotifyDeviceConfiguration.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.NotifyDeviceConfigurationOrBuilder
        public DeviceConfiguration getDeviceConfiguration() {
            DeviceConfiguration deviceConfiguration = this.deviceConfiguration_;
            return deviceConfiguration == null ? DeviceConfiguration.getDefaultInstance() : deviceConfiguration;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.deviceConfiguration_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getDeviceConfiguration());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.NotifyDeviceConfigurationOrBuilder
        public boolean hasDeviceConfiguration() {
            return this.deviceConfiguration_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.deviceConfiguration_ != null) {
                codedOutputStream.writeMessage(1, getDeviceConfiguration());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotifyDeviceConfiguration notifyDeviceConfiguration) {
            return DEFAULT_INSTANCE.createBuilder(notifyDeviceConfiguration);
        }

        public static NotifyDeviceConfiguration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyDeviceConfiguration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotifyDeviceConfiguration parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceConfiguration(DeviceConfiguration.Builder builder) {
            this.deviceConfiguration_ = builder.mo10084build();
        }

        public static NotifyDeviceConfiguration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NotifyDeviceConfiguration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyDeviceConfiguration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotifyDeviceConfiguration parseFrom(InputStream inputStream) throws IOException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyDeviceConfiguration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyDeviceConfiguration parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotifyDeviceConfiguration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyDeviceConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotifyDeviceConfigurationOrBuilder extends MessageLiteOrBuilder {
        DeviceConfiguration getDeviceConfiguration();

        boolean hasDeviceConfiguration();
    }

    /* loaded from: classes6.dex */
    public static final class NotifyDeviceInformation extends GeneratedMessageLite<NotifyDeviceInformation, Builder> implements NotifyDeviceInformationOrBuilder {
        private static final NotifyDeviceInformation DEFAULT_INSTANCE = new NotifyDeviceInformation();
        public static final int DEVICE_INFORMATION_FIELD_NUMBER = 1;
        private static volatile Parser<NotifyDeviceInformation> PARSER;
        private DeviceInformation deviceInformation_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotifyDeviceInformation, Builder> implements NotifyDeviceInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceInformation() {
                copyOnWrite();
                ((NotifyDeviceInformation) this.instance).clearDeviceInformation();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.NotifyDeviceInformationOrBuilder
            public DeviceInformation getDeviceInformation() {
                return ((NotifyDeviceInformation) this.instance).getDeviceInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.NotifyDeviceInformationOrBuilder
            public boolean hasDeviceInformation() {
                return ((NotifyDeviceInformation) this.instance).hasDeviceInformation();
            }

            public Builder mergeDeviceInformation(DeviceInformation deviceInformation) {
                copyOnWrite();
                ((NotifyDeviceInformation) this.instance).mergeDeviceInformation(deviceInformation);
                return this;
            }

            public Builder setDeviceInformation(DeviceInformation deviceInformation) {
                copyOnWrite();
                ((NotifyDeviceInformation) this.instance).setDeviceInformation(deviceInformation);
                return this;
            }

            private Builder() {
                super(NotifyDeviceInformation.DEFAULT_INSTANCE);
            }

            public Builder setDeviceInformation(DeviceInformation.Builder builder) {
                copyOnWrite();
                ((NotifyDeviceInformation) this.instance).setDeviceInformation(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotifyDeviceInformation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceInformation() {
            this.deviceInformation_ = null;
        }

        public static NotifyDeviceInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDeviceInformation(DeviceInformation deviceInformation) {
            DeviceInformation deviceInformation2 = this.deviceInformation_;
            if (deviceInformation2 != null && deviceInformation2 != DeviceInformation.getDefaultInstance()) {
                this.deviceInformation_ = DeviceInformation.newBuilder(this.deviceInformation_).mergeFrom((DeviceInformation.Builder) deviceInformation).mo10085buildPartial();
            } else {
                this.deviceInformation_ = deviceInformation;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotifyDeviceInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyDeviceInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotifyDeviceInformation> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceInformation(DeviceInformation deviceInformation) {
            if (deviceInformation != null) {
                this.deviceInformation_ = deviceInformation;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    this.deviceInformation_ = (DeviceInformation) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.deviceInformation_, ((NotifyDeviceInformation) obj2).deviceInformation_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag != 10) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    DeviceInformation.Builder mo10081toBuilder = this.deviceInformation_ != null ? this.deviceInformation_.mo10081toBuilder() : null;
                                    this.deviceInformation_ = (DeviceInformation) codedInputStream.readMessage(DeviceInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((DeviceInformation.Builder) this.deviceInformation_);
                                        this.deviceInformation_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new NotifyDeviceInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotifyDeviceInformation.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.NotifyDeviceInformationOrBuilder
        public DeviceInformation getDeviceInformation() {
            DeviceInformation deviceInformation = this.deviceInformation_;
            return deviceInformation == null ? DeviceInformation.getDefaultInstance() : deviceInformation;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.deviceInformation_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getDeviceInformation());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.NotifyDeviceInformationOrBuilder
        public boolean hasDeviceInformation() {
            return this.deviceInformation_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.deviceInformation_ != null) {
                codedOutputStream.writeMessage(1, getDeviceInformation());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotifyDeviceInformation notifyDeviceInformation) {
            return DEFAULT_INSTANCE.createBuilder(notifyDeviceInformation);
        }

        public static NotifyDeviceInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyDeviceInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotifyDeviceInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceInformation(DeviceInformation.Builder builder) {
            this.deviceInformation_ = builder.mo10084build();
        }

        public static NotifyDeviceInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NotifyDeviceInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyDeviceInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotifyDeviceInformation parseFrom(InputStream inputStream) throws IOException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyDeviceInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyDeviceInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotifyDeviceInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotifyDeviceInformationOrBuilder extends MessageLiteOrBuilder {
        DeviceInformation getDeviceInformation();

        boolean hasDeviceInformation();
    }

    /* loaded from: classes6.dex */
    public static final class OverrideAssistant extends GeneratedMessageLite<OverrideAssistant, Builder> implements OverrideAssistantOrBuilder {
        private static final OverrideAssistant DEFAULT_INSTANCE = new OverrideAssistant();
        public static final int ERROR_CODE_FIELD_NUMBER = 1;
        private static volatile Parser<OverrideAssistant> PARSER;
        private int errorCode_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<OverrideAssistant, Builder> implements OverrideAssistantOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearErrorCode() {
                copyOnWrite();
                ((OverrideAssistant) this.instance).clearErrorCode();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.OverrideAssistantOrBuilder
            public Common.ErrorCode getErrorCode() {
                return ((OverrideAssistant) this.instance).getErrorCode();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.OverrideAssistantOrBuilder
            public int getErrorCodeValue() {
                return ((OverrideAssistant) this.instance).getErrorCodeValue();
            }

            public Builder setErrorCode(Common.ErrorCode errorCode) {
                copyOnWrite();
                ((OverrideAssistant) this.instance).setErrorCode(errorCode);
                return this;
            }

            public Builder setErrorCodeValue(int i) {
                copyOnWrite();
                ((OverrideAssistant) this.instance).setErrorCodeValue(i);
                return this;
            }

            private Builder() {
                super(OverrideAssistant.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private OverrideAssistant() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorCode() {
            this.errorCode_ = 0;
        }

        public static OverrideAssistant getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static OverrideAssistant parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (OverrideAssistant) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static OverrideAssistant parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<OverrideAssistant> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCode(Common.ErrorCode errorCode) {
            if (errorCode != null) {
                this.errorCode_ = errorCode.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCodeValue(int i) {
            this.errorCode_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    OverrideAssistant overrideAssistant = (OverrideAssistant) obj2;
                    boolean z2 = this.errorCode_ != 0;
                    int i = this.errorCode_;
                    if (overrideAssistant.errorCode_ != 0) {
                        z = true;
                    }
                    this.errorCode_ = visitor.visitInt(z2, i, z, overrideAssistant.errorCode_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 8) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.errorCode_ = codedInputStream.readEnum();
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this));
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new OverrideAssistant();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (OverrideAssistant.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.OverrideAssistantOrBuilder
        public Common.ErrorCode getErrorCode() {
            Common.ErrorCode forNumber = Common.ErrorCode.forNumber(this.errorCode_);
            return forNumber == null ? Common.ErrorCode.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.OverrideAssistantOrBuilder
        public int getErrorCodeValue() {
            return this.errorCode_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.errorCode_ != Common.ErrorCode.SUCCESS.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.errorCode_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.errorCode_ != Common.ErrorCode.SUCCESS.getNumber()) {
                codedOutputStream.writeEnum(1, this.errorCode_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(OverrideAssistant overrideAssistant) {
            return DEFAULT_INSTANCE.createBuilder(overrideAssistant);
        }

        public static OverrideAssistant parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (OverrideAssistant) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static OverrideAssistant parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static OverrideAssistant parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static OverrideAssistant parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static OverrideAssistant parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static OverrideAssistant parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static OverrideAssistant parseFrom(InputStream inputStream) throws IOException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static OverrideAssistant parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static OverrideAssistant parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static OverrideAssistant parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (OverrideAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface OverrideAssistantOrBuilder extends MessageLiteOrBuilder {
        Common.ErrorCode getErrorCode();

        int getErrorCodeValue();
    }

    /* loaded from: classes6.dex */
    public static final class StartSetup extends GeneratedMessageLite<StartSetup, Builder> implements StartSetupOrBuilder {
        private static final StartSetup DEFAULT_INSTANCE = new StartSetup();
        private static volatile Parser<StartSetup> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<StartSetup, Builder> implements StartSetupOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(StartSetup.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private StartSetup() {
        }

        public static StartSetup getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static StartSetup parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StartSetup) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StartSetup parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<StartSetup> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    StartSetup startSetup = (StartSetup) obj2;
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag == 0 || !parseUnknownField(readTag, codedInputStream)) {
                                z = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new StartSetup();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (StartSetup.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(StartSetup startSetup) {
            return DEFAULT_INSTANCE.createBuilder(startSetup);
        }

        public static StartSetup parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartSetup) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StartSetup parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StartSetup parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StartSetup parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StartSetup parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StartSetup parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StartSetup parseFrom(InputStream inputStream) throws IOException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StartSetup parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StartSetup parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StartSetup parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartSetup) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface StartSetupOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class UpdateDeviceInformation extends GeneratedMessageLite<UpdateDeviceInformation, Builder> implements UpdateDeviceInformationOrBuilder {
        private static final UpdateDeviceInformation DEFAULT_INSTANCE = new UpdateDeviceInformation();
        public static final int DEVICE_ID_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<UpdateDeviceInformation> PARSER;
        private int deviceId_;
        private String name_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UpdateDeviceInformation, Builder> implements UpdateDeviceInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((UpdateDeviceInformation) this.instance).clearDeviceId();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((UpdateDeviceInformation) this.instance).clearName();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.UpdateDeviceInformationOrBuilder
            public int getDeviceId() {
                return ((UpdateDeviceInformation) this.instance).getDeviceId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.UpdateDeviceInformationOrBuilder
            public String getName() {
                return ((UpdateDeviceInformation) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Device.UpdateDeviceInformationOrBuilder
            public ByteString getNameBytes() {
                return ((UpdateDeviceInformation) this.instance).getNameBytes();
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((UpdateDeviceInformation) this.instance).setDeviceId(i);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((UpdateDeviceInformation) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((UpdateDeviceInformation) this.instance).setNameBytes(byteString);
                return this;
            }

            private Builder() {
                super(UpdateDeviceInformation.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UpdateDeviceInformation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        public static UpdateDeviceInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UpdateDeviceInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateDeviceInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UpdateDeviceInformation> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceId(int i) {
            this.deviceId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            if (str != null) {
                this.name_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.name_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UpdateDeviceInformation updateDeviceInformation = (UpdateDeviceInformation) obj2;
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !updateDeviceInformation.name_.isEmpty(), updateDeviceInformation.name_);
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (updateDeviceInformation.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, updateDeviceInformation.deviceId_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.deviceId_ = codedInputStream.readUInt32();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new UpdateDeviceInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UpdateDeviceInformation.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.UpdateDeviceInformationOrBuilder
        public int getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.UpdateDeviceInformationOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Device.UpdateDeviceInformationOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.name_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getName());
            }
            int i3 = this.deviceId_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(1, getName());
            }
            int i = this.deviceId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UpdateDeviceInformation updateDeviceInformation) {
            return DEFAULT_INSTANCE.createBuilder(updateDeviceInformation);
        }

        public static UpdateDeviceInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateDeviceInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UpdateDeviceInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UpdateDeviceInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UpdateDeviceInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UpdateDeviceInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UpdateDeviceInformation parseFrom(InputStream inputStream) throws IOException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateDeviceInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateDeviceInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UpdateDeviceInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateDeviceInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UpdateDeviceInformationOrBuilder extends MessageLiteOrBuilder {
        int getDeviceId();

        String getName();

        ByteString getNameBytes();
    }

    private Device() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
