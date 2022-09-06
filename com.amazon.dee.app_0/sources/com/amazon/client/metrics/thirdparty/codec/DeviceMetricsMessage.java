package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.RemoteConfigurationAndroidClientContextDecorator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public final class DeviceMetricsMessage {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_metrics_DataPointMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_metrics_DataPointMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_metrics_KeyValue_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_metrics_KeyValue_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_metrics_MetricBatchMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_metrics_MetricBatchMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_metrics_MetricEntryMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_metrics_MetricEntryMessage_fieldAccessorTable;

    /* loaded from: classes11.dex */
    public static final class DataPointMessage extends GeneratedMessageV3 implements DataPointMessageOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int SAMPLESIZE_FIELD_NUMBER = 3;
        public static final int TYPE_FIELD_NUMBER = 4;
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private int sampleSize_;
        private int type_;
        private volatile Object value_;
        private static final DataPointMessage DEFAULT_INSTANCE = new DataPointMessage();
        @Deprecated
        public static final Parser<DataPointMessage> PARSER = new AbstractParser<DataPointMessage>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public DataPointMessage mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DataPointMessage(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes11.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DataPointMessageOrBuilder {
            private int bitField0_;
            private Object name_;
            private int sampleSize_;
            private int type_;
            private Object value_;

            private Builder() {
                this.name_ = "";
                this.value_ = "";
                this.type_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.value_ = "";
                this.type_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DeviceMetricsMessage.internal_static_metrics_DataPointMessage_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public DataPointMessage mo10084build() {
                DataPointMessage mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public DataPointMessage mo10085buildPartial() {
                DataPointMessage dataPointMessage = new DataPointMessage(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                dataPointMessage.name_ = this.name_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                dataPointMessage.value_ = this.value_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                dataPointMessage.sampleSize_ = this.sampleSize_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                dataPointMessage.type_ = this.type_;
                dataPointMessage.bitField0_ = i2;
                onBuilt();
                return dataPointMessage;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                this.value_ = "";
                this.bitField0_ &= -3;
                this.sampleSize_ = 0;
                this.bitField0_ &= -5;
                this.type_ = 0;
                this.bitField0_ &= -9;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = DataPointMessage.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            public Builder clearSampleSize() {
                this.bitField0_ &= -5;
                this.sampleSize_ = 0;
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.bitField0_ &= -9;
                this.type_ = 0;
                onChanged();
                return this;
            }

            public Builder clearValue() {
                this.bitField0_ &= -3;
                this.value_ = DataPointMessage.getDefaultInstance().getValue();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public DataPointMessage mo10094getDefaultInstanceForType() {
                return DataPointMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceMetricsMessage.internal_static_metrics_DataPointMessage_descriptor;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public String getName() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.name_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public int getSampleSize() {
                return this.sampleSize_;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public DataType getType() {
                DataType valueOf = DataType.valueOf(this.type_);
                return valueOf == null ? DataType.COUNTER : valueOf;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public String getValue() {
                Object obj = this.value_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.value_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public ByteString getValueBytes() {
                Object obj = this.value_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.value_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public boolean hasSampleSize() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public boolean hasType() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceMetricsMessage.internal_static_metrics_DataPointMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(DataPointMessage.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasName() && hasValue() && hasSampleSize() && hasType();
            }

            public Builder mergeFrom(DataPointMessage dataPointMessage) {
                if (dataPointMessage == DataPointMessage.getDefaultInstance()) {
                    return this;
                }
                if (dataPointMessage.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = dataPointMessage.name_;
                    onChanged();
                }
                if (dataPointMessage.hasValue()) {
                    this.bitField0_ |= 2;
                    this.value_ = dataPointMessage.value_;
                    onChanged();
                }
                if (dataPointMessage.hasSampleSize()) {
                    setSampleSize(dataPointMessage.getSampleSize());
                }
                if (dataPointMessage.hasType()) {
                    setType(dataPointMessage.getType());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) dataPointMessage).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$DataPointMessage> r1 = com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$DataPointMessage r3 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$DataPointMessage r4 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$DataPointMessage$Builder");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof DataPointMessage) {
                    return mergeFrom((DataPointMessage) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            public Builder setName(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.name_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.name_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            public Builder setSampleSize(int i) {
                this.bitField0_ |= 4;
                this.sampleSize_ = i;
                onChanged();
                return this;
            }

            public Builder setType(DataType dataType) {
                if (dataType != null) {
                    this.bitField0_ |= 8;
                    this.type_ = dataType.getNumber();
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }

            public Builder setValue(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.value_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setValueBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.value_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }
        }

        /* loaded from: classes11.dex */
        public enum DataType implements ProtocolMessageEnum {
            COUNTER(0),
            TIMER(1),
            DISCRETE(2),
            CLICKSTREAM(3);
            
            public static final int CLICKSTREAM_VALUE = 3;
            public static final int COUNTER_VALUE = 0;
            public static final int DISCRETE_VALUE = 2;
            public static final int TIMER_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<DataType> internalValueMap = new Internal.EnumLiteMap<DataType>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.DataType.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public DataType mo9850findValueByNumber(int i) {
                    return DataType.forNumber(i);
                }
            };
            private static final DataType[] VALUES = values();

            DataType(int i) {
                this.value = i;
            }

            public static DataType forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return TIMER;
                    }
                    if (i == 2) {
                        return DISCRETE;
                    }
                    if (i == 3) {
                        return CLICKSTREAM;
                    }
                    return null;
                }
                return COUNTER;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return DataPointMessage.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<DataType> internalGetValueMap() {
                return internalValueMap;
            }

            @Deprecated
            public static DataType valueOf(int i) {
                return forNumber(i);
            }

            public static DataType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }
        }

        private DataPointMessage() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.value_ = "";
            this.sampleSize_ = 0;
            this.type_ = 0;
        }

        private DataPointMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.name_ = readBytes;
                                } else if (readTag == 18) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 2;
                                    this.value_ = readBytes2;
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 4;
                                    this.sampleSize_ = codedInputStream.readInt32();
                                } else if (readTag == 32) {
                                    int readEnum = codedInputStream.readEnum();
                                    if (DataType.valueOf(readEnum) == null) {
                                        newBuilder.mergeVarintField(4, readEnum);
                                    } else {
                                        this.bitField0_ |= 8;
                                        this.type_ = readEnum;
                                    }
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
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

        private DataPointMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static DataPointMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceMetricsMessage.internal_static_metrics_DataPointMessage_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static Builder newBuilder(DataPointMessage dataPointMessage) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(dataPointMessage);
        }

        public static DataPointMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DataPointMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static DataPointMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DataPointMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DataPointMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        public static DataPointMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        public static DataPointMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DataPointMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static DataPointMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DataPointMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static DataPointMessage parseFrom(InputStream inputStream) throws IOException {
            return (DataPointMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static DataPointMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DataPointMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DataPointMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static DataPointMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static DataPointMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        public static DataPointMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static Parser<DataPointMessage> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DataPointMessage)) {
                return super.equals(obj);
            }
            DataPointMessage dataPointMessage = (DataPointMessage) obj;
            boolean z = hasName() == dataPointMessage.hasName();
            if (hasName()) {
                z = z && getName().equals(dataPointMessage.getName());
            }
            boolean z2 = z && hasValue() == dataPointMessage.hasValue();
            if (hasValue()) {
                z2 = z2 && getValue().equals(dataPointMessage.getValue());
            }
            boolean z3 = z2 && hasSampleSize() == dataPointMessage.hasSampleSize();
            if (hasSampleSize()) {
                z3 = z3 && getSampleSize() == dataPointMessage.getSampleSize();
            }
            boolean z4 = z3 && hasType() == dataPointMessage.hasType();
            if (hasType()) {
                z4 = z4 && this.type_ == dataPointMessage.type_;
            }
            return z4 && this.unknownFields.equals(dataPointMessage.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public DataPointMessage mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<DataPointMessage> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public int getSampleSize() {
            return this.sampleSize_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.value_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeInt32Size(3, this.sampleSize_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeEnumSize(4, this.type_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public DataType getType() {
            DataType valueOf = DataType.valueOf(this.type_);
            return valueOf == null ? DataType.COUNTER : valueOf;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public String getValue() {
            Object obj = this.value_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.value_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public ByteString getValueBytes() {
            Object obj = this.value_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.value_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public boolean hasSampleSize() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public boolean hasValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasName()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getName().hashCode();
            }
            if (hasValue()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getValue().hashCode();
            }
            if (hasSampleSize()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getSampleSize();
            }
            if (hasType()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + this.type_;
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceMetricsMessage.internal_static_metrics_DataPointMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(DataPointMessage.class, Builder.class);
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
            if (!hasName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasValue()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasSampleSize()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasType()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.value_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(3, this.sampleSize_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeEnum(4, this.type_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    /* loaded from: classes11.dex */
    public interface DataPointMessageOrBuilder extends MessageOrBuilder {
        String getName();

        ByteString getNameBytes();

        int getSampleSize();

        DataPointMessage.DataType getType();

        String getValue();

        ByteString getValueBytes();

        boolean hasName();

        boolean hasSampleSize();

        boolean hasType();

        boolean hasValue();
    }

    /* loaded from: classes11.dex */
    public static final class KeyValue extends GeneratedMessageV3 implements KeyValueOrBuilder {
        public static final int KEY_FIELD_NUMBER = 1;
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private volatile Object key_;
        private byte memoizedIsInitialized;
        private volatile Object value_;
        private static final KeyValue DEFAULT_INSTANCE = new KeyValue();
        @Deprecated
        public static final Parser<KeyValue> PARSER = new AbstractParser<KeyValue>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public KeyValue mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new KeyValue(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes11.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements KeyValueOrBuilder {
            private int bitField0_;
            private Object key_;
            private Object value_;

            private Builder() {
                this.key_ = "";
                this.value_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.key_ = "";
                this.value_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DeviceMetricsMessage.internal_static_metrics_KeyValue_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public KeyValue mo10084build() {
                KeyValue mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public KeyValue mo10085buildPartial() {
                KeyValue keyValue = new KeyValue(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                keyValue.key_ = this.key_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                keyValue.value_ = this.value_;
                keyValue.bitField0_ = i2;
                onBuilt();
                return keyValue;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.key_ = "";
                this.bitField0_ &= -2;
                this.value_ = "";
                this.bitField0_ &= -3;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            public Builder clearKey() {
                this.bitField0_ &= -2;
                this.key_ = KeyValue.getDefaultInstance().getKey();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            public Builder clearValue() {
                this.bitField0_ &= -3;
                this.value_ = KeyValue.getDefaultInstance().getValue();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public KeyValue mo10094getDefaultInstanceForType() {
                return KeyValue.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceMetricsMessage.internal_static_metrics_KeyValue_descriptor;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
            public String getKey() {
                Object obj = this.key_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.key_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
            public ByteString getKeyBytes() {
                Object obj = this.key_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.key_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
            public String getValue() {
                Object obj = this.value_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.value_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
            public ByteString getValueBytes() {
                Object obj = this.value_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.value_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
            public boolean hasKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceMetricsMessage.internal_static_metrics_KeyValue_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyValue.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasKey() && hasValue();
            }

            public Builder mergeFrom(KeyValue keyValue) {
                if (keyValue == KeyValue.getDefaultInstance()) {
                    return this;
                }
                if (keyValue.hasKey()) {
                    this.bitField0_ |= 1;
                    this.key_ = keyValue.key_;
                    onChanged();
                }
                if (keyValue.hasValue()) {
                    this.bitField0_ |= 2;
                    this.value_ = keyValue.value_;
                    onChanged();
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) keyValue).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$KeyValue> r1 = com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$KeyValue r3 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$KeyValue r4 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$KeyValue$Builder");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof KeyValue) {
                    return mergeFrom((KeyValue) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            public Builder setKey(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.key_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setKeyBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.key_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }

            public Builder setValue(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.value_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setValueBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.value_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }
        }

        private KeyValue() {
            this.memoizedIsInitialized = (byte) -1;
            this.key_ = "";
            this.value_ = "";
        }

        private KeyValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.key_ = readBytes;
                                } else if (readTag == 18) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 2;
                                    this.value_ = readBytes2;
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
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

        private KeyValue(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static KeyValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceMetricsMessage.internal_static_metrics_KeyValue_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static Builder newBuilder(KeyValue keyValue) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(keyValue);
        }

        public static KeyValue parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (KeyValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static KeyValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static KeyValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        public static KeyValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        public static KeyValue parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (KeyValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static KeyValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static KeyValue parseFrom(InputStream inputStream) throws IOException {
            return (KeyValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static KeyValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static KeyValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static KeyValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static KeyValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        public static KeyValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static Parser<KeyValue> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof KeyValue)) {
                return super.equals(obj);
            }
            KeyValue keyValue = (KeyValue) obj;
            boolean z = hasKey() == keyValue.hasKey();
            if (hasKey()) {
                z = z && getKey().equals(keyValue.getKey());
            }
            boolean z2 = z && hasValue() == keyValue.hasValue();
            if (hasValue()) {
                z2 = z2 && getValue().equals(keyValue.getValue());
            }
            return z2 && this.unknownFields.equals(keyValue.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public KeyValue mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
        public String getKey() {
            Object obj = this.key_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.key_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
        public ByteString getKeyBytes() {
            Object obj = this.key_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.key_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<KeyValue> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.key_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.value_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
        public String getValue() {
            Object obj = this.value_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.value_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
        public ByteString getValueBytes() {
            Object obj = this.value_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.value_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
        public boolean hasKey() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValueOrBuilder
        public boolean hasValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasKey()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getKey().hashCode();
            }
            if (hasValue()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getValue().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceMetricsMessage.internal_static_metrics_KeyValue_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyValue.class, Builder.class);
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
            if (!hasKey()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasValue()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.key_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.value_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    /* loaded from: classes11.dex */
    public interface KeyValueOrBuilder extends MessageOrBuilder {
        String getKey();

        ByteString getKeyBytes();

        String getValue();

        ByteString getValueBytes();

        boolean hasKey();

        boolean hasValue();
    }

    /* loaded from: classes11.dex */
    public static final class MetricBatchMessage extends GeneratedMessageV3 implements MetricBatchMessageOrBuilder {
        public static final int DEVICESERIALNUMBER_FIELD_NUMBER = 1;
        public static final int DEVICETYPE_FIELD_NUMBER = 2;
        public static final int METADATA_FIELD_NUMBER = 4;
        public static final int METRICENTRY_FIELD_NUMBER = 5;
        public static final int TAG_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private volatile Object deviceSerialNumber_;
        private volatile Object deviceType_;
        private byte memoizedIsInitialized;
        private List<KeyValue> metadata_;
        private List<MetricEntryMessage> metricEntry_;
        private volatile Object tag_;
        private static final MetricBatchMessage DEFAULT_INSTANCE = new MetricBatchMessage();
        @Deprecated
        public static final Parser<MetricBatchMessage> PARSER = new AbstractParser<MetricBatchMessage>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public MetricBatchMessage mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MetricBatchMessage(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes11.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetricBatchMessageOrBuilder {
            private int bitField0_;
            private Object deviceSerialNumber_;
            private Object deviceType_;
            private RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> metadataBuilder_;
            private List<KeyValue> metadata_;
            private RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> metricEntryBuilder_;
            private List<MetricEntryMessage> metricEntry_;
            private Object tag_;

            private Builder() {
                this.deviceSerialNumber_ = "";
                this.deviceType_ = "";
                this.tag_ = "";
                this.metadata_ = Collections.emptyList();
                this.metricEntry_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.deviceSerialNumber_ = "";
                this.deviceType_ = "";
                this.tag_ = "";
                this.metadata_ = Collections.emptyList();
                this.metricEntry_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void ensureMetadataIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.metadata_ = new ArrayList(this.metadata_);
                    this.bitField0_ |= 8;
                }
            }

            private void ensureMetricEntryIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.metricEntry_ = new ArrayList(this.metricEntry_);
                    this.bitField0_ |= 16;
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DeviceMetricsMessage.internal_static_metrics_MetricBatchMessage_descriptor;
            }

            private RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> getMetadataFieldBuilder() {
                if (this.metadataBuilder_ == null) {
                    this.metadataBuilder_ = new RepeatedFieldBuilderV3<>(this.metadata_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                    this.metadata_ = null;
                }
                return this.metadataBuilder_;
            }

            private RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> getMetricEntryFieldBuilder() {
                if (this.metricEntryBuilder_ == null) {
                    this.metricEntryBuilder_ = new RepeatedFieldBuilderV3<>(this.metricEntry_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                    this.metricEntry_ = null;
                }
                return this.metricEntryBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getMetadataFieldBuilder();
                    getMetricEntryFieldBuilder();
                }
            }

            public Builder addAllMetadata(Iterable<? extends KeyValue> iterable) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetadataIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.metadata_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllMetricEntry(Iterable<? extends MetricEntryMessage> iterable) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetricEntryIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.metricEntry_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addMetadata(int i, KeyValue.Builder builder) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetadataIsMutable();
                    this.metadata_.add(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder addMetadata(int i, KeyValue keyValue) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, keyValue);
                } else if (keyValue == null) {
                    throw new NullPointerException();
                } else {
                    ensureMetadataIsMutable();
                    this.metadata_.add(i, keyValue);
                    onChanged();
                }
                return this;
            }

            public Builder addMetadata(KeyValue.Builder builder) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetadataIsMutable();
                    this.metadata_.add(builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder addMetadata(KeyValue keyValue) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(keyValue);
                } else if (keyValue == null) {
                    throw new NullPointerException();
                } else {
                    ensureMetadataIsMutable();
                    this.metadata_.add(keyValue);
                    onChanged();
                }
                return this;
            }

            public KeyValue.Builder addMetadataBuilder() {
                return getMetadataFieldBuilder().addBuilder(KeyValue.getDefaultInstance());
            }

            public KeyValue.Builder addMetadataBuilder(int i) {
                return getMetadataFieldBuilder().addBuilder(i, KeyValue.getDefaultInstance());
            }

            public Builder addMetricEntry(int i, MetricEntryMessage.Builder builder) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetricEntryIsMutable();
                    this.metricEntry_.add(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder addMetricEntry(int i, MetricEntryMessage metricEntryMessage) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, metricEntryMessage);
                } else if (metricEntryMessage == null) {
                    throw new NullPointerException();
                } else {
                    ensureMetricEntryIsMutable();
                    this.metricEntry_.add(i, metricEntryMessage);
                    onChanged();
                }
                return this;
            }

            public Builder addMetricEntry(MetricEntryMessage.Builder builder) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetricEntryIsMutable();
                    this.metricEntry_.add(builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder addMetricEntry(MetricEntryMessage metricEntryMessage) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(metricEntryMessage);
                } else if (metricEntryMessage == null) {
                    throw new NullPointerException();
                } else {
                    ensureMetricEntryIsMutable();
                    this.metricEntry_.add(metricEntryMessage);
                    onChanged();
                }
                return this;
            }

            public MetricEntryMessage.Builder addMetricEntryBuilder() {
                return getMetricEntryFieldBuilder().addBuilder(MetricEntryMessage.getDefaultInstance());
            }

            public MetricEntryMessage.Builder addMetricEntryBuilder(int i) {
                return getMetricEntryFieldBuilder().addBuilder(i, MetricEntryMessage.getDefaultInstance());
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public MetricBatchMessage mo10084build() {
                MetricBatchMessage mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public MetricBatchMessage mo10085buildPartial() {
                List<KeyValue> build;
                List<MetricEntryMessage> build2;
                MetricBatchMessage metricBatchMessage = new MetricBatchMessage(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                metricBatchMessage.deviceSerialNumber_ = this.deviceSerialNumber_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                metricBatchMessage.deviceType_ = this.deviceType_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                metricBatchMessage.tag_ = this.tag_;
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.metadata_ = Collections.unmodifiableList(this.metadata_);
                        this.bitField0_ &= -9;
                    }
                    build = this.metadata_;
                } else {
                    build = repeatedFieldBuilderV3.build();
                }
                metricBatchMessage.metadata_ = build;
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV32 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    if ((this.bitField0_ & 16) == 16) {
                        this.metricEntry_ = Collections.unmodifiableList(this.metricEntry_);
                        this.bitField0_ &= -17;
                    }
                    build2 = this.metricEntry_;
                } else {
                    build2 = repeatedFieldBuilderV32.build();
                }
                metricBatchMessage.metricEntry_ = build2;
                metricBatchMessage.bitField0_ = i2;
                onBuilt();
                return metricBatchMessage;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.deviceSerialNumber_ = "";
                this.bitField0_ &= -2;
                this.deviceType_ = "";
                this.bitField0_ &= -3;
                this.tag_ = "";
                this.bitField0_ &= -5;
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.metadata_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV32 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    this.metricEntry_ = Collections.emptyList();
                    this.bitField0_ &= -17;
                } else {
                    repeatedFieldBuilderV32.clear();
                }
                return this;
            }

            public Builder clearDeviceSerialNumber() {
                this.bitField0_ &= -2;
                this.deviceSerialNumber_ = MetricBatchMessage.getDefaultInstance().getDeviceSerialNumber();
                onChanged();
                return this;
            }

            public Builder clearDeviceType() {
                this.bitField0_ &= -3;
                this.deviceType_ = MetricBatchMessage.getDefaultInstance().getDeviceType();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            public Builder clearMetadata() {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.metadata_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder clearMetricEntry() {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.metricEntry_ = Collections.emptyList();
                    this.bitField0_ &= -17;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            public Builder clearTag() {
                this.bitField0_ &= -5;
                this.tag_ = MetricBatchMessage.getDefaultInstance().getTag();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public MetricBatchMessage mo10094getDefaultInstanceForType() {
                return MetricBatchMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceMetricsMessage.internal_static_metrics_MetricBatchMessage_descriptor;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public String getDeviceSerialNumber() {
                Object obj = this.deviceSerialNumber_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.deviceSerialNumber_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public ByteString getDeviceSerialNumberBytes() {
                Object obj = this.deviceSerialNumber_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceSerialNumber_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public String getDeviceType() {
                Object obj = this.deviceType_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.deviceType_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public ByteString getDeviceTypeBytes() {
                Object obj = this.deviceType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public KeyValue getMetadata(int i) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                return repeatedFieldBuilderV3 == null ? this.metadata_.get(i) : repeatedFieldBuilderV3.getMessage(i);
            }

            public KeyValue.Builder getMetadataBuilder(int i) {
                return getMetadataFieldBuilder().getBuilder(i);
            }

            public List<KeyValue.Builder> getMetadataBuilderList() {
                return getMetadataFieldBuilder().getBuilderList();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public int getMetadataCount() {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                return repeatedFieldBuilderV3 == null ? this.metadata_.size() : repeatedFieldBuilderV3.getCount();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public List<KeyValue> getMetadataList() {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.metadata_) : repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public KeyValueOrBuilder getMetadataOrBuilder(int i) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                return (KeyValueOrBuilder) (repeatedFieldBuilderV3 == null ? this.metadata_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i));
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public List<? extends KeyValueOrBuilder> getMetadataOrBuilderList() {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.metadata_);
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public MetricEntryMessage getMetricEntry(int i) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                return repeatedFieldBuilderV3 == null ? this.metricEntry_.get(i) : repeatedFieldBuilderV3.getMessage(i);
            }

            public MetricEntryMessage.Builder getMetricEntryBuilder(int i) {
                return getMetricEntryFieldBuilder().getBuilder(i);
            }

            public List<MetricEntryMessage.Builder> getMetricEntryBuilderList() {
                return getMetricEntryFieldBuilder().getBuilderList();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public int getMetricEntryCount() {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                return repeatedFieldBuilderV3 == null ? this.metricEntry_.size() : repeatedFieldBuilderV3.getCount();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public List<MetricEntryMessage> getMetricEntryList() {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.metricEntry_) : repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public MetricEntryMessageOrBuilder getMetricEntryOrBuilder(int i) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                return (MetricEntryMessageOrBuilder) (repeatedFieldBuilderV3 == null ? this.metricEntry_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i));
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public List<? extends MetricEntryMessageOrBuilder> getMetricEntryOrBuilderList() {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.metricEntry_);
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public String getTag() {
                Object obj = this.tag_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.tag_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public ByteString getTagBytes() {
                Object obj = this.tag_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.tag_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public boolean hasDeviceSerialNumber() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public boolean hasDeviceType() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public boolean hasTag() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceMetricsMessage.internal_static_metrics_MetricBatchMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricBatchMessage.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasDeviceSerialNumber()) {
                    return false;
                }
                for (int i = 0; i < getMetadataCount(); i++) {
                    if (!getMetadata(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getMetricEntryCount(); i2++) {
                    if (!getMetricEntry(i2).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(MetricBatchMessage metricBatchMessage) {
                if (metricBatchMessage == MetricBatchMessage.getDefaultInstance()) {
                    return this;
                }
                if (metricBatchMessage.hasDeviceSerialNumber()) {
                    this.bitField0_ |= 1;
                    this.deviceSerialNumber_ = metricBatchMessage.deviceSerialNumber_;
                    onChanged();
                }
                if (metricBatchMessage.hasDeviceType()) {
                    this.bitField0_ |= 2;
                    this.deviceType_ = metricBatchMessage.deviceType_;
                    onChanged();
                }
                if (metricBatchMessage.hasTag()) {
                    this.bitField0_ |= 4;
                    this.tag_ = metricBatchMessage.tag_;
                    onChanged();
                }
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = null;
                if (this.metadataBuilder_ == null) {
                    if (!metricBatchMessage.metadata_.isEmpty()) {
                        if (this.metadata_.isEmpty()) {
                            this.metadata_ = metricBatchMessage.metadata_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureMetadataIsMutable();
                            this.metadata_.addAll(metricBatchMessage.metadata_);
                        }
                        onChanged();
                    }
                } else if (!metricBatchMessage.metadata_.isEmpty()) {
                    if (this.metadataBuilder_.isEmpty()) {
                        this.metadataBuilder_.dispose();
                        this.metadataBuilder_ = null;
                        this.metadata_ = metricBatchMessage.metadata_;
                        this.bitField0_ &= -9;
                        this.metadataBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getMetadataFieldBuilder() : null;
                    } else {
                        this.metadataBuilder_.addAllMessages(metricBatchMessage.metadata_);
                    }
                }
                if (this.metricEntryBuilder_ == null) {
                    if (!metricBatchMessage.metricEntry_.isEmpty()) {
                        if (this.metricEntry_.isEmpty()) {
                            this.metricEntry_ = metricBatchMessage.metricEntry_;
                            this.bitField0_ &= -17;
                        } else {
                            ensureMetricEntryIsMutable();
                            this.metricEntry_.addAll(metricBatchMessage.metricEntry_);
                        }
                        onChanged();
                    }
                } else if (!metricBatchMessage.metricEntry_.isEmpty()) {
                    if (this.metricEntryBuilder_.isEmpty()) {
                        this.metricEntryBuilder_.dispose();
                        this.metricEntryBuilder_ = null;
                        this.metricEntry_ = metricBatchMessage.metricEntry_;
                        this.bitField0_ &= -17;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getMetricEntryFieldBuilder();
                        }
                        this.metricEntryBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.metricEntryBuilder_.addAllMessages(metricBatchMessage.metricEntry_);
                    }
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) metricBatchMessage).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricBatchMessage> r1 = com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricBatchMessage r3 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricBatchMessage r4 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricBatchMessage$Builder");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof MetricBatchMessage) {
                    return mergeFrom((MetricBatchMessage) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            public Builder removeMetadata(int i) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetadataIsMutable();
                    this.metadata_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder removeMetricEntry(int i) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetricEntryIsMutable();
                    this.metricEntry_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder setDeviceSerialNumber(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.deviceSerialNumber_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceSerialNumberBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.deviceSerialNumber_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceType(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.deviceType_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.deviceType_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            public Builder setMetadata(int i, KeyValue.Builder builder) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetadataIsMutable();
                    this.metadata_.set(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder setMetadata(int i, KeyValue keyValue) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, keyValue);
                } else if (keyValue == null) {
                    throw new NullPointerException();
                } else {
                    ensureMetadataIsMutable();
                    this.metadata_.set(i, keyValue);
                    onChanged();
                }
                return this;
            }

            public Builder setMetricEntry(int i, MetricEntryMessage.Builder builder) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureMetricEntryIsMutable();
                    this.metricEntry_.set(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder setMetricEntry(int i, MetricEntryMessage metricEntryMessage) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, metricEntryMessage);
                } else if (metricEntryMessage == null) {
                    throw new NullPointerException();
                } else {
                    ensureMetricEntryIsMutable();
                    this.metricEntry_.set(i, metricEntryMessage);
                    onChanged();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            public Builder setTag(String str) {
                if (str != null) {
                    this.bitField0_ |= 4;
                    this.tag_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setTagBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.tag_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }
        }

        private MetricBatchMessage() {
            this.memoizedIsInitialized = (byte) -1;
            this.deviceSerialNumber_ = "";
            this.deviceType_ = "";
            this.tag_ = "";
            this.metadata_ = Collections.emptyList();
            this.metricEntry_ = Collections.emptyList();
        }

        private MetricBatchMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            List list;
            MessageLite readMessage;
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    if (z) {
                        break;
                    }
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.deviceSerialNumber_ = readBytes;
                                } else if (readTag == 18) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 2;
                                    this.deviceType_ = readBytes2;
                                } else if (readTag != 26) {
                                    if (readTag == 34) {
                                        if (!(z2 & true)) {
                                            this.metadata_ = new ArrayList();
                                            z2 |= true;
                                        }
                                        list = this.metadata_;
                                        readMessage = codedInputStream.readMessage(KeyValue.PARSER, extensionRegistryLite);
                                    } else if (readTag == 42) {
                                        if (!(z2 & true)) {
                                            this.metricEntry_ = new ArrayList();
                                            z2 |= true;
                                        }
                                        list = this.metricEntry_;
                                        readMessage = codedInputStream.readMessage(MetricEntryMessage.PARSER, extensionRegistryLite);
                                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                    list.add(readMessage);
                                } else {
                                    ByteString readBytes3 = codedInputStream.readBytes();
                                    this.bitField0_ |= 4;
                                    this.tag_ = readBytes3;
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        if (z2 & true) {
                            this.metadata_ = Collections.unmodifiableList(this.metadata_);
                        }
                        if (z2 & true) {
                            this.metricEntry_ = Collections.unmodifiableList(this.metricEntry_);
                        }
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        private MetricBatchMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static MetricBatchMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceMetricsMessage.internal_static_metrics_MetricBatchMessage_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static Builder newBuilder(MetricBatchMessage metricBatchMessage) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(metricBatchMessage);
        }

        public static MetricBatchMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MetricBatchMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MetricBatchMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricBatchMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MetricBatchMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        public static MetricBatchMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        public static MetricBatchMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MetricBatchMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MetricBatchMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricBatchMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static MetricBatchMessage parseFrom(InputStream inputStream) throws IOException {
            return (MetricBatchMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MetricBatchMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricBatchMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MetricBatchMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static MetricBatchMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MetricBatchMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        public static MetricBatchMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static Parser<MetricBatchMessage> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MetricBatchMessage)) {
                return super.equals(obj);
            }
            MetricBatchMessage metricBatchMessage = (MetricBatchMessage) obj;
            boolean z = hasDeviceSerialNumber() == metricBatchMessage.hasDeviceSerialNumber();
            if (hasDeviceSerialNumber()) {
                z = z && getDeviceSerialNumber().equals(metricBatchMessage.getDeviceSerialNumber());
            }
            boolean z2 = z && hasDeviceType() == metricBatchMessage.hasDeviceType();
            if (hasDeviceType()) {
                z2 = z2 && getDeviceType().equals(metricBatchMessage.getDeviceType());
            }
            boolean z3 = z2 && hasTag() == metricBatchMessage.hasTag();
            if (hasTag()) {
                z3 = z3 && getTag().equals(metricBatchMessage.getTag());
            }
            return ((z3 && getMetadataList().equals(metricBatchMessage.getMetadataList())) && getMetricEntryList().equals(metricBatchMessage.getMetricEntryList())) && this.unknownFields.equals(metricBatchMessage.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public MetricBatchMessage mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public String getDeviceSerialNumber() {
            Object obj = this.deviceSerialNumber_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.deviceSerialNumber_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public ByteString getDeviceSerialNumberBytes() {
            Object obj = this.deviceSerialNumber_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceSerialNumber_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public String getDeviceType() {
            Object obj = this.deviceType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.deviceType_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public ByteString getDeviceTypeBytes() {
            Object obj = this.deviceType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public KeyValue getMetadata(int i) {
            return this.metadata_.get(i);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public int getMetadataCount() {
            return this.metadata_.size();
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public List<KeyValue> getMetadataList() {
            return this.metadata_;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public KeyValueOrBuilder getMetadataOrBuilder(int i) {
            return this.metadata_.get(i);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public List<? extends KeyValueOrBuilder> getMetadataOrBuilderList() {
            return this.metadata_;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public MetricEntryMessage getMetricEntry(int i) {
            return this.metricEntry_.get(i);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public int getMetricEntryCount() {
            return this.metricEntry_.size();
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public List<MetricEntryMessage> getMetricEntryList() {
            return this.metricEntry_;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public MetricEntryMessageOrBuilder getMetricEntryOrBuilder(int i) {
            return this.metricEntry_.get(i);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public List<? extends MetricEntryMessageOrBuilder> getMetricEntryOrBuilderList() {
            return this.metricEntry_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<MetricBatchMessage> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (this.bitField0_ & 1) == 1 ? GeneratedMessageV3.computeStringSize(1, this.deviceSerialNumber_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeStringSize += GeneratedMessageV3.computeStringSize(2, this.deviceType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeStringSize += GeneratedMessageV3.computeStringSize(3, this.tag_);
            }
            int i2 = computeStringSize;
            for (int i3 = 0; i3 < this.metadata_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(4, this.metadata_.get(i3));
            }
            for (int i4 = 0; i4 < this.metricEntry_.size(); i4++) {
                i2 += CodedOutputStream.computeMessageSize(5, this.metricEntry_.get(i4));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public String getTag() {
            Object obj = this.tag_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.tag_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public ByteString getTagBytes() {
            Object obj = this.tag_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.tag_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public boolean hasDeviceSerialNumber() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public boolean hasDeviceType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public boolean hasTag() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasDeviceSerialNumber()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getDeviceSerialNumber().hashCode();
            }
            if (hasDeviceType()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getDeviceType().hashCode();
            }
            if (hasTag()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getTag().hashCode();
            }
            if (getMetadataCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + getMetadataList().hashCode();
            }
            if (getMetricEntryCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 5, 53) + getMetricEntryList().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceMetricsMessage.internal_static_metrics_MetricBatchMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricBatchMessage.class, Builder.class);
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
            if (!hasDeviceSerialNumber()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getMetadataCount(); i++) {
                if (!getMetadata(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getMetricEntryCount(); i2++) {
                if (!getMetricEntry(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.deviceSerialNumber_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.deviceType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.tag_);
            }
            for (int i = 0; i < this.metadata_.size(); i++) {
                codedOutputStream.writeMessage(4, this.metadata_.get(i));
            }
            for (int i2 = 0; i2 < this.metricEntry_.size(); i2++) {
                codedOutputStream.writeMessage(5, this.metricEntry_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    /* loaded from: classes11.dex */
    public interface MetricBatchMessageOrBuilder extends MessageOrBuilder {
        String getDeviceSerialNumber();

        ByteString getDeviceSerialNumberBytes();

        String getDeviceType();

        ByteString getDeviceTypeBytes();

        KeyValue getMetadata(int i);

        int getMetadataCount();

        List<KeyValue> getMetadataList();

        KeyValueOrBuilder getMetadataOrBuilder(int i);

        List<? extends KeyValueOrBuilder> getMetadataOrBuilderList();

        MetricEntryMessage getMetricEntry(int i);

        int getMetricEntryCount();

        List<MetricEntryMessage> getMetricEntryList();

        MetricEntryMessageOrBuilder getMetricEntryOrBuilder(int i);

        List<? extends MetricEntryMessageOrBuilder> getMetricEntryOrBuilderList();

        String getTag();

        ByteString getTagBytes();

        boolean hasDeviceSerialNumber();

        boolean hasDeviceType();

        boolean hasTag();
    }

    /* loaded from: classes11.dex */
    public static final class MetricEntryMessage extends GeneratedMessageV3 implements MetricEntryMessageOrBuilder {
        public static final int DATAPOINT_FIELD_NUMBER = 4;
        private static final MetricEntryMessage DEFAULT_INSTANCE = new MetricEntryMessage();
        @Deprecated
        public static final Parser<MetricEntryMessage> PARSER = new AbstractParser<MetricEntryMessage>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public MetricEntryMessage mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MetricEntryMessage(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PROGRAM_FIELD_NUMBER = 2;
        public static final int SOURCE_FIELD_NUMBER = 3;
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private List<DataPointMessage> dataPoint_;
        private byte memoizedIsInitialized;
        private volatile Object program_;
        private volatile Object source_;
        private long timestamp_;

        /* loaded from: classes11.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetricEntryMessageOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> dataPointBuilder_;
            private List<DataPointMessage> dataPoint_;
            private Object program_;
            private Object source_;
            private long timestamp_;

            private Builder() {
                this.program_ = "";
                this.source_ = "";
                this.dataPoint_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.program_ = "";
                this.source_ = "";
                this.dataPoint_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void ensureDataPointIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.dataPoint_ = new ArrayList(this.dataPoint_);
                    this.bitField0_ |= 8;
                }
            }

            private RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> getDataPointFieldBuilder() {
                if (this.dataPointBuilder_ == null) {
                    this.dataPointBuilder_ = new RepeatedFieldBuilderV3<>(this.dataPoint_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                    this.dataPoint_ = null;
                }
                return this.dataPointBuilder_;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DeviceMetricsMessage.internal_static_metrics_MetricEntryMessage_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getDataPointFieldBuilder();
                }
            }

            public Builder addAllDataPoint(Iterable<? extends DataPointMessage> iterable) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataPointIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.dataPoint_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addDataPoint(int i, DataPointMessage.Builder builder) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataPointIsMutable();
                    this.dataPoint_.add(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder addDataPoint(int i, DataPointMessage dataPointMessage) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, dataPointMessage);
                } else if (dataPointMessage == null) {
                    throw new NullPointerException();
                } else {
                    ensureDataPointIsMutable();
                    this.dataPoint_.add(i, dataPointMessage);
                    onChanged();
                }
                return this;
            }

            public Builder addDataPoint(DataPointMessage.Builder builder) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataPointIsMutable();
                    this.dataPoint_.add(builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder addDataPoint(DataPointMessage dataPointMessage) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(dataPointMessage);
                } else if (dataPointMessage == null) {
                    throw new NullPointerException();
                } else {
                    ensureDataPointIsMutable();
                    this.dataPoint_.add(dataPointMessage);
                    onChanged();
                }
                return this;
            }

            public DataPointMessage.Builder addDataPointBuilder() {
                return getDataPointFieldBuilder().addBuilder(DataPointMessage.getDefaultInstance());
            }

            public DataPointMessage.Builder addDataPointBuilder(int i) {
                return getDataPointFieldBuilder().addBuilder(i, DataPointMessage.getDefaultInstance());
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public MetricEntryMessage mo10084build() {
                MetricEntryMessage mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public MetricEntryMessage mo10085buildPartial() {
                List<DataPointMessage> build;
                MetricEntryMessage metricEntryMessage = new MetricEntryMessage(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                metricEntryMessage.timestamp_ = this.timestamp_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                metricEntryMessage.program_ = this.program_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                metricEntryMessage.source_ = this.source_;
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.dataPoint_ = Collections.unmodifiableList(this.dataPoint_);
                        this.bitField0_ &= -9;
                    }
                    build = this.dataPoint_;
                } else {
                    build = repeatedFieldBuilderV3.build();
                }
                metricEntryMessage.dataPoint_ = build;
                metricEntryMessage.bitField0_ = i2;
                onBuilt();
                return metricEntryMessage;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.timestamp_ = 0L;
                this.bitField0_ &= -2;
                this.program_ = "";
                this.bitField0_ &= -3;
                this.source_ = "";
                this.bitField0_ &= -5;
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.dataPoint_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder clearDataPoint() {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.dataPoint_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            public Builder clearProgram() {
                this.bitField0_ &= -3;
                this.program_ = MetricEntryMessage.getDefaultInstance().getProgram();
                onChanged();
                return this;
            }

            public Builder clearSource() {
                this.bitField0_ &= -5;
                this.source_ = MetricEntryMessage.getDefaultInstance().getSource();
                onChanged();
                return this;
            }

            public Builder clearTimestamp() {
                this.bitField0_ &= -2;
                this.timestamp_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public DataPointMessage getDataPoint(int i) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                return repeatedFieldBuilderV3 == null ? this.dataPoint_.get(i) : repeatedFieldBuilderV3.getMessage(i);
            }

            public DataPointMessage.Builder getDataPointBuilder(int i) {
                return getDataPointFieldBuilder().getBuilder(i);
            }

            public List<DataPointMessage.Builder> getDataPointBuilderList() {
                return getDataPointFieldBuilder().getBuilderList();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public int getDataPointCount() {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                return repeatedFieldBuilderV3 == null ? this.dataPoint_.size() : repeatedFieldBuilderV3.getCount();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public List<DataPointMessage> getDataPointList() {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.dataPoint_) : repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public DataPointMessageOrBuilder getDataPointOrBuilder(int i) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                return (DataPointMessageOrBuilder) (repeatedFieldBuilderV3 == null ? this.dataPoint_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i));
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public List<? extends DataPointMessageOrBuilder> getDataPointOrBuilderList() {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.dataPoint_);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public MetricEntryMessage mo10094getDefaultInstanceForType() {
                return MetricEntryMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceMetricsMessage.internal_static_metrics_MetricEntryMessage_descriptor;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public String getProgram() {
                Object obj = this.program_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.program_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public ByteString getProgramBytes() {
                Object obj = this.program_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.program_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public String getSource() {
                Object obj = this.source_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.source_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public ByteString getSourceBytes() {
                Object obj = this.source_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.source_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public long getTimestamp() {
                return this.timestamp_;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public boolean hasProgram() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public boolean hasSource() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public boolean hasTimestamp() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceMetricsMessage.internal_static_metrics_MetricEntryMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricEntryMessage.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (hasTimestamp() && hasProgram() && hasSource()) {
                    for (int i = 0; i < getDataPointCount(); i++) {
                        if (!getDataPoint(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(MetricEntryMessage metricEntryMessage) {
                if (metricEntryMessage == MetricEntryMessage.getDefaultInstance()) {
                    return this;
                }
                if (metricEntryMessage.hasTimestamp()) {
                    setTimestamp(metricEntryMessage.getTimestamp());
                }
                if (metricEntryMessage.hasProgram()) {
                    this.bitField0_ |= 2;
                    this.program_ = metricEntryMessage.program_;
                    onChanged();
                }
                if (metricEntryMessage.hasSource()) {
                    this.bitField0_ |= 4;
                    this.source_ = metricEntryMessage.source_;
                    onChanged();
                }
                if (this.dataPointBuilder_ == null) {
                    if (!metricEntryMessage.dataPoint_.isEmpty()) {
                        if (this.dataPoint_.isEmpty()) {
                            this.dataPoint_ = metricEntryMessage.dataPoint_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureDataPointIsMutable();
                            this.dataPoint_.addAll(metricEntryMessage.dataPoint_);
                        }
                        onChanged();
                    }
                } else if (!metricEntryMessage.dataPoint_.isEmpty()) {
                    if (this.dataPointBuilder_.isEmpty()) {
                        this.dataPointBuilder_.dispose();
                        RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = null;
                        this.dataPointBuilder_ = null;
                        this.dataPoint_ = metricEntryMessage.dataPoint_;
                        this.bitField0_ &= -9;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getDataPointFieldBuilder();
                        }
                        this.dataPointBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.dataPointBuilder_.addAllMessages(metricEntryMessage.dataPoint_);
                    }
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) metricEntryMessage).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricEntryMessage> r1 = com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricEntryMessage r3 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricEntryMessage r4 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricEntryMessage$Builder");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof MetricEntryMessage) {
                    return mergeFrom((MetricEntryMessage) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            public Builder removeDataPoint(int i) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataPointIsMutable();
                    this.dataPoint_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder setDataPoint(int i, DataPointMessage.Builder builder) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataPointIsMutable();
                    this.dataPoint_.set(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder setDataPoint(int i, DataPointMessage dataPointMessage) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, dataPointMessage);
                } else if (dataPointMessage == null) {
                    throw new NullPointerException();
                } else {
                    ensureDataPointIsMutable();
                    this.dataPoint_.set(i, dataPointMessage);
                    onChanged();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            public Builder setProgram(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.program_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setProgramBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.program_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            public Builder setSource(String str) {
                if (str != null) {
                    this.bitField0_ |= 4;
                    this.source_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setSourceBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.source_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setTimestamp(long j) {
                this.bitField0_ |= 1;
                this.timestamp_ = j;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }
        }

        private MetricEntryMessage() {
            this.memoizedIsInitialized = (byte) -1;
            this.timestamp_ = 0L;
            this.program_ = "";
            this.source_ = "";
            this.dataPoint_ = Collections.emptyList();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private MetricEntryMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    if (z) {
                        break;
                    }
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.bitField0_ |= 1;
                                        this.timestamp_ = codedInputStream.readInt64();
                                    } else if (readTag == 18) {
                                        ByteString readBytes = codedInputStream.readBytes();
                                        this.bitField0_ |= 2;
                                        this.program_ = readBytes;
                                    } else if (readTag == 26) {
                                        ByteString readBytes2 = codedInputStream.readBytes();
                                        this.bitField0_ |= 4;
                                        this.source_ = readBytes2;
                                    } else if (readTag == 34) {
                                        if (!(z2 & true)) {
                                            this.dataPoint_ = new ArrayList();
                                            z2 |= true;
                                        }
                                        this.dataPoint_.add(codedInputStream.readMessage(DataPointMessage.PARSER, extensionRegistryLite));
                                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.setUnfinishedMessage(this);
                            }
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        if (z2 & true) {
                            this.dataPoint_ = Collections.unmodifiableList(this.dataPoint_);
                        }
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        private MetricEntryMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static MetricEntryMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceMetricsMessage.internal_static_metrics_MetricEntryMessage_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static Builder newBuilder(MetricEntryMessage metricEntryMessage) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(metricEntryMessage);
        }

        public static MetricEntryMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MetricEntryMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MetricEntryMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricEntryMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MetricEntryMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        public static MetricEntryMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        public static MetricEntryMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MetricEntryMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MetricEntryMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricEntryMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static MetricEntryMessage parseFrom(InputStream inputStream) throws IOException {
            return (MetricEntryMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MetricEntryMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricEntryMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MetricEntryMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static MetricEntryMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MetricEntryMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        public static MetricEntryMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static Parser<MetricEntryMessage> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MetricEntryMessage)) {
                return super.equals(obj);
            }
            MetricEntryMessage metricEntryMessage = (MetricEntryMessage) obj;
            boolean z = hasTimestamp() == metricEntryMessage.hasTimestamp();
            if (hasTimestamp()) {
                z = z && getTimestamp() == metricEntryMessage.getTimestamp();
            }
            boolean z2 = z && hasProgram() == metricEntryMessage.hasProgram();
            if (hasProgram()) {
                z2 = z2 && getProgram().equals(metricEntryMessage.getProgram());
            }
            boolean z3 = z2 && hasSource() == metricEntryMessage.hasSource();
            if (hasSource()) {
                z3 = z3 && getSource().equals(metricEntryMessage.getSource());
            }
            return (z3 && getDataPointList().equals(metricEntryMessage.getDataPointList())) && this.unknownFields.equals(metricEntryMessage.unknownFields);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public DataPointMessage getDataPoint(int i) {
            return this.dataPoint_.get(i);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public int getDataPointCount() {
            return this.dataPoint_.size();
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public List<DataPointMessage> getDataPointList() {
            return this.dataPoint_;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public DataPointMessageOrBuilder getDataPointOrBuilder(int i) {
            return this.dataPoint_.get(i);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public List<? extends DataPointMessageOrBuilder> getDataPointOrBuilderList() {
            return this.dataPoint_;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public MetricEntryMessage mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<MetricEntryMessage> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public String getProgram() {
            Object obj = this.program_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.program_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public ByteString getProgramBytes() {
            Object obj = this.program_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.program_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeInt64Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt64Size(1, this.timestamp_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeInt64Size += GeneratedMessageV3.computeStringSize(2, this.program_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeInt64Size += GeneratedMessageV3.computeStringSize(3, this.source_);
            }
            for (int i2 = 0; i2 < this.dataPoint_.size(); i2++) {
                computeInt64Size += CodedOutputStream.computeMessageSize(4, this.dataPoint_.get(i2));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeInt64Size;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public String getSource() {
            Object obj = this.source_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.source_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public ByteString getSourceBytes() {
            Object obj = this.source_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.source_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public long getTimestamp() {
            return this.timestamp_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public boolean hasProgram() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public boolean hasSource() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public boolean hasTimestamp() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasTimestamp()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + Internal.hashLong(getTimestamp());
            }
            if (hasProgram()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getProgram().hashCode();
            }
            if (hasSource()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getSource().hashCode();
            }
            if (getDataPointCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + getDataPointList().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceMetricsMessage.internal_static_metrics_MetricEntryMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricEntryMessage.class, Builder.class);
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
            if (!hasTimestamp()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasProgram()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasSource()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else {
                for (int i = 0; i < getDataPointCount(); i++) {
                    if (!getDataPoint(i).isInitialized()) {
                        this.memoizedIsInitialized = (byte) 0;
                        return false;
                    }
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt64(1, this.timestamp_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.program_);
            }
            if ((this.bitField0_ & 4) == 4) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.source_);
            }
            for (int i = 0; i < this.dataPoint_.size(); i++) {
                codedOutputStream.writeMessage(4, this.dataPoint_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    /* loaded from: classes11.dex */
    public interface MetricEntryMessageOrBuilder extends MessageOrBuilder {
        DataPointMessage getDataPoint(int i);

        int getDataPointCount();

        List<DataPointMessage> getDataPointList();

        DataPointMessageOrBuilder getDataPointOrBuilder(int i);

        List<? extends DataPointMessageOrBuilder> getDataPointOrBuilderList();

        String getProgram();

        ByteString getProgramBytes();

        String getSource();

        ByteString getSourceBytes();

        long getTimestamp();

        boolean hasProgram();

        boolean hasSource();

        boolean hasTimestamp();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nRcom/amazon/client/metrics/thirdparty/codec/protocol/DeviceMetricsMessage.0.2.proto\u0012\u0007metrics\"¨\u0001\n\u0012MetricBatchMessage\u0012\u001a\n\u0012deviceSerialNumber\u0018\u0001 \u0002(\t\u0012\u0012\n\ndeviceType\u0018\u0002 \u0001(\t\u0012\u000b\n\u0003tag\u0018\u0003 \u0001(\t\u0012#\n\bmetadata\u0018\u0004 \u0003(\u000b2\u0011.metrics.KeyValue\u00120\n\u000bmetricEntry\u0018\u0005 \u0003(\u000b2\u001b.metrics.MetricEntryMessage\"v\n\u0012MetricEntryMessage\u0012\u0011\n\ttimestamp\u0018\u0001 \u0002(\u0003\u0012\u000f\n\u0007program\u0018\u0002 \u0002(\t\u0012\u000e\n\u0006source\u0018\u0003 \u0002(\t\u0012,\n\tdataPoint\u0018\u0004 \u0003(\u000b2\u0019.metrics.DataPointMessage\"¸\u0001\n\u0010DataPointMessage\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u0012\r\n\u0005value\u0018\u0002 \u0002(\t\u0012\u0012\n\nSampleSize\u0018\u0003 \u0002(\u0005\u00120\n\u0004type\u0018\u0004 \u0002(\u000e2\".metrics.DataPointMessage.DataType\"A\n\bDataType\u0012\u000b\n\u0007COUNTER\u0010\u0000\u0012\t\n\u0005TIMER\u0010\u0001\u0012\f\n\bDISCRETE\u0010\u0002\u0012\u000f\n\u000bCLICKSTREAM\u0010\u0003\"&\n\bKeyValue\u0012\u000b\n\u0003key\u0018\u0001 \u0002(\t\u0012\r\n\u0005value\u0018\u0002 \u0002(\tBB\n*com.amazon.client.metrics.thirdparty.codecB\u0014DeviceMetricsMessage"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = DeviceMetricsMessage.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_metrics_MetricBatchMessage_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_metrics_MetricBatchMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_metrics_MetricBatchMessage_descriptor, new String[]{RemoteConfigurationAndroidClientContextDecorator.DEVICE_SERIAL_NUMBER, "DeviceType", "Tag", "Metadata", "MetricEntry"});
        internal_static_metrics_MetricEntryMessage_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_metrics_MetricEntryMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_metrics_MetricEntryMessage_descriptor, new String[]{"Timestamp", "Program", AlexaMetricsConstants.EventConstants.SOURCE, "DataPoint"});
        internal_static_metrics_DataPointMessage_descriptor = getDescriptor().getMessageTypes().get(2);
        internal_static_metrics_DataPointMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_metrics_DataPointMessage_descriptor, new String[]{MAPCookie.KEY_NAME, MAPCookie.KEY_VALUE, "SampleSize", "Type"});
        internal_static_metrics_KeyValue_descriptor = getDescriptor().getMessageTypes().get(3);
        internal_static_metrics_KeyValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_metrics_KeyValue_descriptor, new String[]{"Key", MAPCookie.KEY_VALUE});
    }

    private DeviceMetricsMessage() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
