package com.amazon.alexa.accessory.protocol;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
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
public final class Metrics {

    /* renamed from: com.amazon.alexa.accessory.protocol.Metrics$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase;
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
            $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase = new int[MetricValue.ValueCase.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[MetricValue.ValueCase.ABOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[MetricValue.ValueCase.ASTRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[MetricValue.ValueCase.ANINTEGER.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[MetricValue.ValueCase.AFLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[MetricValue.ValueCase.ADOUBLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Metrics$MetricValue$ValueCase[MetricValue.ValueCase.VALUE_NOT_SET.ordinal()] = 6;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class MetricDescriptor extends GeneratedMessageLite<MetricDescriptor, Builder> implements MetricDescriptorOrBuilder {
        private static final MetricDescriptor DEFAULT_INSTANCE = new MetricDescriptor();
        public static final int DESTINATION_FIELD_NUMBER = 3;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<MetricDescriptor> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 2;
        private int destination_;
        private String name_ = "";
        private int type_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<MetricDescriptor, Builder> implements MetricDescriptorOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDestination() {
                copyOnWrite();
                ((MetricDescriptor) this.instance).clearDestination();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((MetricDescriptor) this.instance).clearName();
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((MetricDescriptor) this.instance).clearType();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
            public int getDestination() {
                return ((MetricDescriptor) this.instance).getDestination();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
            public String getName() {
                return ((MetricDescriptor) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
            public ByteString getNameBytes() {
                return ((MetricDescriptor) this.instance).getNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
            public MetricType getType() {
                return ((MetricDescriptor) this.instance).getType();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
            public int getTypeValue() {
                return ((MetricDescriptor) this.instance).getTypeValue();
            }

            public Builder setDestination(int i) {
                copyOnWrite();
                ((MetricDescriptor) this.instance).setDestination(i);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((MetricDescriptor) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((MetricDescriptor) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setType(MetricType metricType) {
                copyOnWrite();
                ((MetricDescriptor) this.instance).setType(metricType);
                return this;
            }

            public Builder setTypeValue(int i) {
                copyOnWrite();
                ((MetricDescriptor) this.instance).setTypeValue(i);
                return this;
            }

            private Builder() {
                super(MetricDescriptor.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private MetricDescriptor() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDestination() {
            this.destination_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.type_ = 0;
        }

        public static MetricDescriptor getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static MetricDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MetricDescriptor) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MetricDescriptor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<MetricDescriptor> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDestination(int i) {
            this.destination_ = i;
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
        public void setType(MetricType metricType) {
            if (metricType != null) {
                this.type_ = metricType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTypeValue(int i) {
            this.type_ = i;
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
                    MetricDescriptor metricDescriptor = (MetricDescriptor) obj2;
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !metricDescriptor.name_.isEmpty(), metricDescriptor.name_);
                    this.type_ = visitor.visitInt(this.type_ != 0, this.type_, metricDescriptor.type_ != 0, metricDescriptor.type_);
                    boolean z2 = this.destination_ != 0;
                    int i = this.destination_;
                    if (metricDescriptor.destination_ != 0) {
                        z = true;
                    }
                    this.destination_ = visitor.visitInt(z2, i, z, metricDescriptor.destination_);
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
                                } else if (readTag == 16) {
                                    this.type_ = codedInputStream.readEnum();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.destination_ = codedInputStream.readUInt32();
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
                    return new MetricDescriptor();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (MetricDescriptor.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
        public int getDestination() {
            return this.destination_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
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
            if (this.type_ != MetricType.COUNTER.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.type_);
            }
            int i3 = this.destination_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
        public MetricType getType() {
            MetricType forNumber = MetricType.forNumber(this.type_);
            return forNumber == null ? MetricType.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricDescriptorOrBuilder
        public int getTypeValue() {
            return this.type_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(1, getName());
            }
            if (this.type_ != MetricType.COUNTER.getNumber()) {
                codedOutputStream.writeEnum(2, this.type_);
            }
            int i = this.destination_;
            if (i != 0) {
                codedOutputStream.writeUInt32(3, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(MetricDescriptor metricDescriptor) {
            return DEFAULT_INSTANCE.createBuilder(metricDescriptor);
        }

        public static MetricDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricDescriptor) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MetricDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static MetricDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static MetricDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static MetricDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static MetricDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static MetricDescriptor parseFrom(InputStream inputStream) throws IOException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MetricDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MetricDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static MetricDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface MetricDescriptorOrBuilder extends MessageLiteOrBuilder {
        int getDestination();

        String getName();

        ByteString getNameBytes();

        MetricType getType();

        int getTypeValue();
    }

    /* loaded from: classes6.dex */
    public enum MetricType implements Internal.EnumLite {
        COUNTER(0),
        TIMER(1),
        UNRECOGNIZED(-1);
        
        public static final int COUNTER_VALUE = 0;
        public static final int TIMER_VALUE = 1;
        private static final Internal.EnumLiteMap<MetricType> internalValueMap = new Internal.EnumLiteMap<MetricType>() { // from class: com.amazon.alexa.accessory.protocol.Metrics.MetricType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public MetricType mo9850findValueByNumber(int i) {
                return MetricType.forNumber(i);
            }
        };
        private final int value;

        MetricType(int i) {
            this.value = i;
        }

        public static MetricType forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return TIMER;
                }
                return null;
            }
            return COUNTER;
        }

        public static Internal.EnumLiteMap<MetricType> internalGetValueMap() {
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
        public static MetricType valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class MetricValue extends GeneratedMessageLite<MetricValue, Builder> implements MetricValueOrBuilder {
        public static final int ABOOLEAN_FIELD_NUMBER = 1;
        public static final int ADOUBLE_FIELD_NUMBER = 5;
        public static final int AFLOAT_FIELD_NUMBER = 4;
        public static final int ANINTEGER_FIELD_NUMBER = 3;
        public static final int ASTRING_FIELD_NUMBER = 2;
        private static final MetricValue DEFAULT_INSTANCE = new MetricValue();
        private static volatile Parser<MetricValue> PARSER;
        private int valueCase_ = 0;
        private Object value_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<MetricValue, Builder> implements MetricValueOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearABoolean() {
                copyOnWrite();
                ((MetricValue) this.instance).clearABoolean();
                return this;
            }

            public Builder clearADouble() {
                copyOnWrite();
                ((MetricValue) this.instance).clearADouble();
                return this;
            }

            public Builder clearAFloat() {
                copyOnWrite();
                ((MetricValue) this.instance).clearAFloat();
                return this;
            }

            public Builder clearAString() {
                copyOnWrite();
                ((MetricValue) this.instance).clearAString();
                return this;
            }

            public Builder clearAnInteger() {
                copyOnWrite();
                ((MetricValue) this.instance).clearAnInteger();
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((MetricValue) this.instance).clearValue();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
            public boolean getABoolean() {
                return ((MetricValue) this.instance).getABoolean();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
            public double getADouble() {
                return ((MetricValue) this.instance).getADouble();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
            public float getAFloat() {
                return ((MetricValue) this.instance).getAFloat();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
            public String getAString() {
                return ((MetricValue) this.instance).getAString();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
            public ByteString getAStringBytes() {
                return ((MetricValue) this.instance).getAStringBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
            public int getAnInteger() {
                return ((MetricValue) this.instance).getAnInteger();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
            public ValueCase getValueCase() {
                return ((MetricValue) this.instance).getValueCase();
            }

            public Builder setABoolean(boolean z) {
                copyOnWrite();
                ((MetricValue) this.instance).setABoolean(z);
                return this;
            }

            public Builder setADouble(double d) {
                copyOnWrite();
                ((MetricValue) this.instance).setADouble(d);
                return this;
            }

            public Builder setAFloat(float f) {
                copyOnWrite();
                ((MetricValue) this.instance).setAFloat(f);
                return this;
            }

            public Builder setAString(String str) {
                copyOnWrite();
                ((MetricValue) this.instance).setAString(str);
                return this;
            }

            public Builder setAStringBytes(ByteString byteString) {
                copyOnWrite();
                ((MetricValue) this.instance).setAStringBytes(byteString);
                return this;
            }

            public Builder setAnInteger(int i) {
                copyOnWrite();
                ((MetricValue) this.instance).setAnInteger(i);
                return this;
            }

            private Builder() {
                super(MetricValue.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        public enum ValueCase implements Internal.EnumLite {
            ABOOLEAN(1),
            ASTRING(2),
            ANINTEGER(3),
            AFLOAT(4),
            ADOUBLE(5),
            VALUE_NOT_SET(0);
            
            private final int value;

            ValueCase(int i) {
                this.value = i;
            }

            public static ValueCase forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return ABOOLEAN;
                    }
                    if (i == 2) {
                        return ASTRING;
                    }
                    if (i == 3) {
                        return ANINTEGER;
                    }
                    if (i == 4) {
                        return AFLOAT;
                    }
                    if (i == 5) {
                        return ADOUBLE;
                    }
                    return null;
                }
                return VALUE_NOT_SET;
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

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private MetricValue() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearABoolean() {
            if (this.valueCase_ == 1) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearADouble() {
            if (this.valueCase_ == 5) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAFloat() {
            if (this.valueCase_ == 4) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAString() {
            if (this.valueCase_ == 2) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAnInteger() {
            if (this.valueCase_ == 3) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValue() {
            this.valueCase_ = 0;
            this.value_ = null;
        }

        public static MetricValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static MetricValue parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MetricValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MetricValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<MetricValue> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setABoolean(boolean z) {
            this.valueCase_ = 1;
            this.value_ = Boolean.valueOf(z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setADouble(double d) {
            this.valueCase_ = 5;
            this.value_ = Double.valueOf(d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAFloat(float f) {
            this.valueCase_ = 4;
            this.value_ = Float.valueOf(f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAString(String str) {
            if (str != null) {
                this.valueCase_ = 2;
                this.value_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAStringBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.valueCase_ = 2;
                this.value_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAnInteger(int i) {
            this.valueCase_ = 3;
            this.value_ = Integer.valueOf(i);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            int i;
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MetricValue metricValue = (MetricValue) obj2;
                    int ordinal = metricValue.getValueCase().ordinal();
                    if (ordinal == 0) {
                        if (this.valueCase_ == 1) {
                            z = true;
                        }
                        this.value_ = visitor.visitOneofBoolean(z, this.value_, metricValue.value_);
                    } else if (ordinal == 1) {
                        if (this.valueCase_ == 2) {
                            z = true;
                        }
                        this.value_ = visitor.visitOneofString(z, this.value_, metricValue.value_);
                    } else if (ordinal == 2) {
                        if (this.valueCase_ == 3) {
                            z = true;
                        }
                        this.value_ = visitor.visitOneofInt(z, this.value_, metricValue.value_);
                    } else if (ordinal == 3) {
                        if (this.valueCase_ == 4) {
                            z = true;
                        }
                        this.value_ = visitor.visitOneofFloat(z, this.value_, metricValue.value_);
                    } else if (ordinal == 4) {
                        if (this.valueCase_ == 5) {
                            z = true;
                        }
                        this.value_ = visitor.visitOneofDouble(z, this.value_, metricValue.value_);
                    } else if (ordinal == 5) {
                        if (this.valueCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = metricValue.valueCase_) != 0) {
                        this.valueCase_ = i;
                    }
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
                                    this.valueCase_ = 1;
                                    this.value_ = Boolean.valueOf(codedInputStream.readBool());
                                } else if (readTag == 18) {
                                    String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    this.valueCase_ = 2;
                                    this.value_ = readStringRequireUtf8;
                                } else if (readTag == 24) {
                                    this.valueCase_ = 3;
                                    this.value_ = Integer.valueOf(codedInputStream.readUInt32());
                                } else if (readTag == 37) {
                                    this.valueCase_ = 4;
                                    this.value_ = Float.valueOf(codedInputStream.readFloat());
                                } else if (readTag != 41) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.valueCase_ = 5;
                                    this.value_ = Double.valueOf(codedInputStream.readDouble());
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
                    return new MetricValue();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (MetricValue.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
        public boolean getABoolean() {
            if (this.valueCase_ == 1) {
                return ((Boolean) this.value_).booleanValue();
            }
            return false;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
        public double getADouble() {
            return this.valueCase_ == 5 ? ((Double) this.value_).doubleValue() : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
        public float getAFloat() {
            if (this.valueCase_ == 4) {
                return ((Float) this.value_).floatValue();
            }
            return 0.0f;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
        public String getAString() {
            return this.valueCase_ == 2 ? (String) this.value_ : "";
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
        public ByteString getAStringBytes() {
            return ByteString.copyFromUtf8(this.valueCase_ == 2 ? (String) this.value_ : "");
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
        public int getAnInteger() {
            if (this.valueCase_ == 3) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.valueCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, ((Boolean) this.value_).booleanValue());
            }
            if (this.valueCase_ == 2) {
                i2 += CodedOutputStream.computeStringSize(2, getAString());
            }
            if (this.valueCase_ == 3) {
                i2 += CodedOutputStream.computeUInt32Size(3, ((Integer) this.value_).intValue());
            }
            if (this.valueCase_ == 4) {
                i2 += CodedOutputStream.computeFloatSize(4, ((Float) this.value_).floatValue());
            }
            if (this.valueCase_ == 5) {
                i2 += CodedOutputStream.computeDoubleSize(5, ((Double) this.value_).doubleValue());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricValueOrBuilder
        public ValueCase getValueCase() {
            return ValueCase.forNumber(this.valueCase_);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.valueCase_ == 1) {
                codedOutputStream.writeBool(1, ((Boolean) this.value_).booleanValue());
            }
            if (this.valueCase_ == 2) {
                codedOutputStream.writeString(2, getAString());
            }
            if (this.valueCase_ == 3) {
                codedOutputStream.writeUInt32(3, ((Integer) this.value_).intValue());
            }
            if (this.valueCase_ == 4) {
                codedOutputStream.writeFloat(4, ((Float) this.value_).floatValue());
            }
            if (this.valueCase_ == 5) {
                codedOutputStream.writeDouble(5, ((Double) this.value_).doubleValue());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(MetricValue metricValue) {
            return DEFAULT_INSTANCE.createBuilder(metricValue);
        }

        public static MetricValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MetricValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static MetricValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static MetricValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static MetricValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static MetricValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static MetricValue parseFrom(InputStream inputStream) throws IOException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MetricValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MetricValue parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static MetricValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface MetricValueOrBuilder extends MessageLiteOrBuilder {
        boolean getABoolean();

        double getADouble();

        float getAFloat();

        String getAString();

        ByteString getAStringBytes();

        int getAnInteger();

        MetricValue.ValueCase getValueCase();
    }

    /* loaded from: classes6.dex */
    public static final class MetricsEvent extends GeneratedMessageLite<MetricsEvent, Builder> implements MetricsEventOrBuilder {
        public static final int BOOT_NUM_FIELD_NUMBER = 5;
        private static final MetricsEvent DEFAULT_INSTANCE = new MetricsEvent();
        public static final int KEY_FIELD_NUMBER = 1;
        private static volatile Parser<MetricsEvent> PARSER = null;
        public static final int SEQ_NUM_FIELD_NUMBER = 6;
        public static final int TIMESTAMP_HI_FIELD_NUMBER = 2;
        public static final int TIMESTAMP_LO_FIELD_NUMBER = 3;
        public static final int VALUES_FIELD_NUMBER = 4;
        private int bitField0_;
        private int bootNum_;
        private int key_;
        private int seqNum_;
        private int timestampHi_;
        private int timestampLo_;
        private Internal.ProtobufList<MetricValue> values_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<MetricsEvent, Builder> implements MetricsEventOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllValues(Iterable<? extends MetricValue> iterable) {
                copyOnWrite();
                ((MetricsEvent) this.instance).addAllValues(iterable);
                return this;
            }

            public Builder addValues(MetricValue metricValue) {
                copyOnWrite();
                ((MetricsEvent) this.instance).addValues(metricValue);
                return this;
            }

            public Builder clearBootNum() {
                copyOnWrite();
                ((MetricsEvent) this.instance).clearBootNum();
                return this;
            }

            public Builder clearKey() {
                copyOnWrite();
                ((MetricsEvent) this.instance).clearKey();
                return this;
            }

            public Builder clearSeqNum() {
                copyOnWrite();
                ((MetricsEvent) this.instance).clearSeqNum();
                return this;
            }

            public Builder clearTimestampHi() {
                copyOnWrite();
                ((MetricsEvent) this.instance).clearTimestampHi();
                return this;
            }

            public Builder clearTimestampLo() {
                copyOnWrite();
                ((MetricsEvent) this.instance).clearTimestampLo();
                return this;
            }

            public Builder clearValues() {
                copyOnWrite();
                ((MetricsEvent) this.instance).clearValues();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
            public int getBootNum() {
                return ((MetricsEvent) this.instance).getBootNum();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
            public int getKey() {
                return ((MetricsEvent) this.instance).getKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
            public int getSeqNum() {
                return ((MetricsEvent) this.instance).getSeqNum();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
            public int getTimestampHi() {
                return ((MetricsEvent) this.instance).getTimestampHi();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
            public int getTimestampLo() {
                return ((MetricsEvent) this.instance).getTimestampLo();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
            public MetricValue getValues(int i) {
                return ((MetricsEvent) this.instance).getValues(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
            public int getValuesCount() {
                return ((MetricsEvent) this.instance).getValuesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
            public List<MetricValue> getValuesList() {
                return Collections.unmodifiableList(((MetricsEvent) this.instance).getValuesList());
            }

            public Builder removeValues(int i) {
                copyOnWrite();
                ((MetricsEvent) this.instance).removeValues(i);
                return this;
            }

            public Builder setBootNum(int i) {
                copyOnWrite();
                ((MetricsEvent) this.instance).setBootNum(i);
                return this;
            }

            public Builder setKey(int i) {
                copyOnWrite();
                ((MetricsEvent) this.instance).setKey(i);
                return this;
            }

            public Builder setSeqNum(int i) {
                copyOnWrite();
                ((MetricsEvent) this.instance).setSeqNum(i);
                return this;
            }

            public Builder setTimestampHi(int i) {
                copyOnWrite();
                ((MetricsEvent) this.instance).setTimestampHi(i);
                return this;
            }

            public Builder setTimestampLo(int i) {
                copyOnWrite();
                ((MetricsEvent) this.instance).setTimestampLo(i);
                return this;
            }

            public Builder setValues(int i, MetricValue metricValue) {
                copyOnWrite();
                ((MetricsEvent) this.instance).setValues(i, metricValue);
                return this;
            }

            private Builder() {
                super(MetricsEvent.DEFAULT_INSTANCE);
            }

            public Builder addValues(int i, MetricValue metricValue) {
                copyOnWrite();
                ((MetricsEvent) this.instance).addValues(i, metricValue);
                return this;
            }

            public Builder setValues(int i, MetricValue.Builder builder) {
                copyOnWrite();
                ((MetricsEvent) this.instance).setValues(i, builder);
                return this;
            }

            public Builder addValues(MetricValue.Builder builder) {
                copyOnWrite();
                ((MetricsEvent) this.instance).addValues(builder);
                return this;
            }

            public Builder addValues(int i, MetricValue.Builder builder) {
                copyOnWrite();
                ((MetricsEvent) this.instance).addValues(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private MetricsEvent() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllValues(Iterable<? extends MetricValue> iterable) {
            ensureValuesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.values_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addValues(MetricValue metricValue) {
            if (metricValue != null) {
                ensureValuesIsMutable();
                this.values_.add(metricValue);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBootNum() {
            this.bootNum_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKey() {
            this.key_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSeqNum() {
            this.seqNum_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimestampHi() {
            this.timestampHi_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimestampLo() {
            this.timestampLo_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValues() {
            this.values_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureValuesIsMutable() {
            if (!this.values_.isModifiable()) {
                this.values_ = GeneratedMessageLite.mutableCopy(this.values_);
            }
        }

        public static MetricsEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static MetricsEvent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MetricsEvent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MetricsEvent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<MetricsEvent> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeValues(int i) {
            ensureValuesIsMutable();
            this.values_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBootNum(int i) {
            this.bootNum_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKey(int i) {
            this.key_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSeqNum(int i) {
            this.seqNum_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestampHi(int i) {
            this.timestampHi_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestampLo(int i) {
            this.timestampLo_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValues(int i, MetricValue metricValue) {
            if (metricValue != null) {
                ensureValuesIsMutable();
                this.values_.set(i, metricValue);
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
                    MetricsEvent metricsEvent = (MetricsEvent) obj2;
                    this.key_ = visitor.visitInt(this.key_ != 0, this.key_, metricsEvent.key_ != 0, metricsEvent.key_);
                    this.timestampHi_ = visitor.visitInt(this.timestampHi_ != 0, this.timestampHi_, metricsEvent.timestampHi_ != 0, metricsEvent.timestampHi_);
                    this.timestampLo_ = visitor.visitInt(this.timestampLo_ != 0, this.timestampLo_, metricsEvent.timestampLo_ != 0, metricsEvent.timestampLo_);
                    this.values_ = visitor.visitList(this.values_, metricsEvent.values_);
                    this.bootNum_ = visitor.visitInt(this.bootNum_ != 0, this.bootNum_, metricsEvent.bootNum_ != 0, metricsEvent.bootNum_);
                    boolean z2 = this.seqNum_ != 0;
                    int i = this.seqNum_;
                    if (metricsEvent.seqNum_ != 0) {
                        z = true;
                    }
                    this.seqNum_ = visitor.visitInt(z2, i, z, metricsEvent.seqNum_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= metricsEvent.bitField0_;
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
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.key_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.timestampHi_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.timestampLo_ = codedInputStream.readUInt32();
                                } else if (readTag == 34) {
                                    if (!this.values_.isModifiable()) {
                                        this.values_ = GeneratedMessageLite.mutableCopy(this.values_);
                                    }
                                    this.values_.add(codedInputStream.readMessage(MetricValue.parser(), extensionRegistryLite));
                                } else if (readTag == 40) {
                                    this.bootNum_ = codedInputStream.readUInt32();
                                } else if (readTag != 48) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.seqNum_ = codedInputStream.readUInt32();
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
                    this.values_.makeImmutable();
                    return null;
                case 6:
                    return new MetricsEvent();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (MetricsEvent.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
        public int getBootNum() {
            return this.bootNum_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
        public int getKey() {
            return this.key_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
        public int getSeqNum() {
            return this.seqNum_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.key_;
            int computeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) + 0 : 0;
            int i3 = this.timestampHi_;
            if (i3 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = this.timestampLo_;
            if (i4 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(3, i4);
            }
            for (int i5 = 0; i5 < this.values_.size(); i5++) {
                computeUInt32Size += CodedOutputStream.computeMessageSize(4, this.values_.get(i5));
            }
            int i6 = this.bootNum_;
            if (i6 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(5, i6);
            }
            int i7 = this.seqNum_;
            if (i7 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(6, i7);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeUInt32Size;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
        public int getTimestampHi() {
            return this.timestampHi_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
        public int getTimestampLo() {
            return this.timestampLo_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
        public MetricValue getValues(int i) {
            return this.values_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
        public int getValuesCount() {
            return this.values_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.MetricsEventOrBuilder
        public List<MetricValue> getValuesList() {
            return this.values_;
        }

        public MetricValueOrBuilder getValuesOrBuilder(int i) {
            return this.values_.get(i);
        }

        public List<? extends MetricValueOrBuilder> getValuesOrBuilderList() {
            return this.values_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.key_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.timestampHi_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            int i3 = this.timestampLo_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(3, i3);
            }
            for (int i4 = 0; i4 < this.values_.size(); i4++) {
                codedOutputStream.writeMessage(4, this.values_.get(i4));
            }
            int i5 = this.bootNum_;
            if (i5 != 0) {
                codedOutputStream.writeUInt32(5, i5);
            }
            int i6 = this.seqNum_;
            if (i6 != 0) {
                codedOutputStream.writeUInt32(6, i6);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(MetricsEvent metricsEvent) {
            return DEFAULT_INSTANCE.createBuilder(metricsEvent);
        }

        public static MetricsEvent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricsEvent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MetricsEvent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static MetricsEvent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addValues(int i, MetricValue metricValue) {
            if (metricValue != null) {
                ensureValuesIsMutable();
                this.values_.add(i, metricValue);
                return;
            }
            throw new NullPointerException();
        }

        public static MetricsEvent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValues(int i, MetricValue.Builder builder) {
            ensureValuesIsMutable();
            this.values_.set(i, builder.mo10084build());
        }

        public static MetricsEvent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static MetricsEvent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addValues(MetricValue.Builder builder) {
            ensureValuesIsMutable();
            this.values_.add(builder.mo10084build());
        }

        public static MetricsEvent parseFrom(InputStream inputStream) throws IOException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MetricsEvent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addValues(int i, MetricValue.Builder builder) {
            ensureValuesIsMutable();
            this.values_.add(i, builder.mo10084build());
        }

        public static MetricsEvent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static MetricsEvent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MetricsEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface MetricsEventOrBuilder extends MessageLiteOrBuilder {
        int getBootNum();

        int getKey();

        int getSeqNum();

        int getTimestampHi();

        int getTimestampLo();

        MetricValue getValues(int i);

        int getValuesCount();

        List<MetricValue> getValuesList();
    }

    /* loaded from: classes6.dex */
    public static final class PushMetrics extends GeneratedMessageLite<PushMetrics, Builder> implements PushMetricsOrBuilder {
        private static final PushMetrics DEFAULT_INSTANCE = new PushMetrics();
        public static final int EVENTS_FIELD_NUMBER = 1;
        private static volatile Parser<PushMetrics> PARSER;
        private Internal.ProtobufList<MetricsEvent> events_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<PushMetrics, Builder> implements PushMetricsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllEvents(Iterable<? extends MetricsEvent> iterable) {
                copyOnWrite();
                ((PushMetrics) this.instance).addAllEvents(iterable);
                return this;
            }

            public Builder addEvents(MetricsEvent metricsEvent) {
                copyOnWrite();
                ((PushMetrics) this.instance).addEvents(metricsEvent);
                return this;
            }

            public Builder clearEvents() {
                copyOnWrite();
                ((PushMetrics) this.instance).clearEvents();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.PushMetricsOrBuilder
            public MetricsEvent getEvents(int i) {
                return ((PushMetrics) this.instance).getEvents(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.PushMetricsOrBuilder
            public int getEventsCount() {
                return ((PushMetrics) this.instance).getEventsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.PushMetricsOrBuilder
            public List<MetricsEvent> getEventsList() {
                return Collections.unmodifiableList(((PushMetrics) this.instance).getEventsList());
            }

            public Builder removeEvents(int i) {
                copyOnWrite();
                ((PushMetrics) this.instance).removeEvents(i);
                return this;
            }

            public Builder setEvents(int i, MetricsEvent metricsEvent) {
                copyOnWrite();
                ((PushMetrics) this.instance).setEvents(i, metricsEvent);
                return this;
            }

            private Builder() {
                super(PushMetrics.DEFAULT_INSTANCE);
            }

            public Builder addEvents(int i, MetricsEvent metricsEvent) {
                copyOnWrite();
                ((PushMetrics) this.instance).addEvents(i, metricsEvent);
                return this;
            }

            public Builder setEvents(int i, MetricsEvent.Builder builder) {
                copyOnWrite();
                ((PushMetrics) this.instance).setEvents(i, builder);
                return this;
            }

            public Builder addEvents(MetricsEvent.Builder builder) {
                copyOnWrite();
                ((PushMetrics) this.instance).addEvents(builder);
                return this;
            }

            public Builder addEvents(int i, MetricsEvent.Builder builder) {
                copyOnWrite();
                ((PushMetrics) this.instance).addEvents(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private PushMetrics() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllEvents(Iterable<? extends MetricsEvent> iterable) {
            ensureEventsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.events_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEvents(MetricsEvent metricsEvent) {
            if (metricsEvent != null) {
                ensureEventsIsMutable();
                this.events_.add(metricsEvent);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEvents() {
            this.events_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureEventsIsMutable() {
            if (!this.events_.isModifiable()) {
                this.events_ = GeneratedMessageLite.mutableCopy(this.events_);
            }
        }

        public static PushMetrics getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static PushMetrics parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PushMetrics) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PushMetrics parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<PushMetrics> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeEvents(int i) {
            ensureEventsIsMutable();
            this.events_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEvents(int i, MetricsEvent metricsEvent) {
            if (metricsEvent != null) {
                ensureEventsIsMutable();
                this.events_.set(i, metricsEvent);
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
                    this.events_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.events_, ((PushMetrics) obj2).events_);
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
                                    if (!this.events_.isModifiable()) {
                                        this.events_ = GeneratedMessageLite.mutableCopy(this.events_);
                                    }
                                    this.events_.add(codedInputStream.readMessage(MetricsEvent.parser(), extensionRegistryLite));
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
                    this.events_.makeImmutable();
                    return null;
                case 6:
                    return new PushMetrics();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (PushMetrics.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Metrics.PushMetricsOrBuilder
        public MetricsEvent getEvents(int i) {
            return this.events_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.PushMetricsOrBuilder
        public int getEventsCount() {
            return this.events_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.PushMetricsOrBuilder
        public List<MetricsEvent> getEventsList() {
            return this.events_;
        }

        public MetricsEventOrBuilder getEventsOrBuilder(int i) {
            return this.events_.get(i);
        }

        public List<? extends MetricsEventOrBuilder> getEventsOrBuilderList() {
            return this.events_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.events_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.events_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.events_.size(); i++) {
                codedOutputStream.writeMessage(1, this.events_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(PushMetrics pushMetrics) {
            return DEFAULT_INSTANCE.createBuilder(pushMetrics);
        }

        public static PushMetrics parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PushMetrics) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PushMetrics parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static PushMetrics parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEvents(int i, MetricsEvent metricsEvent) {
            if (metricsEvent != null) {
                ensureEventsIsMutable();
                this.events_.add(i, metricsEvent);
                return;
            }
            throw new NullPointerException();
        }

        public static PushMetrics parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEvents(int i, MetricsEvent.Builder builder) {
            ensureEventsIsMutable();
            this.events_.set(i, builder.mo10084build());
        }

        public static PushMetrics parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PushMetrics parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEvents(MetricsEvent.Builder builder) {
            ensureEventsIsMutable();
            this.events_.add(builder.mo10084build());
        }

        public static PushMetrics parseFrom(InputStream inputStream) throws IOException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PushMetrics parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEvents(int i, MetricsEvent.Builder builder) {
            ensureEventsIsMutable();
            this.events_.add(i, builder.mo10084build());
        }

        public static PushMetrics parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PushMetrics parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PushMetrics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface PushMetricsOrBuilder extends MessageLiteOrBuilder {
        MetricsEvent getEvents(int i);

        int getEventsCount();

        List<MetricsEvent> getEventsList();
    }

    /* loaded from: classes6.dex */
    public static final class UpdateMetricsMap extends GeneratedMessageLite<UpdateMetricsMap, Builder> implements UpdateMetricsMapOrBuilder {
        private static final UpdateMetricsMap DEFAULT_INSTANCE = new UpdateMetricsMap();
        public static final int MAP_FIELD_NUMBER = 1;
        private static volatile Parser<UpdateMetricsMap> PARSER;
        private MapFieldLite<Integer, MetricDescriptor> map_ = MapFieldLite.emptyMapField();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UpdateMetricsMap, Builder> implements UpdateMetricsMapOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearMap() {
                copyOnWrite();
                ((UpdateMetricsMap) this.instance).getMutableMapMap().clear();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
            public boolean containsMap(int i) {
                return ((UpdateMetricsMap) this.instance).getMapMap().containsKey(Integer.valueOf(i));
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
            @Deprecated
            public Map<Integer, MetricDescriptor> getMap() {
                return getMapMap();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
            public int getMapCount() {
                return ((UpdateMetricsMap) this.instance).getMapMap().size();
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
            public Map<Integer, MetricDescriptor> getMapMap() {
                return Collections.unmodifiableMap(((UpdateMetricsMap) this.instance).getMapMap());
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
            public MetricDescriptor getMapOrDefault(int i, MetricDescriptor metricDescriptor) {
                Map<Integer, MetricDescriptor> mapMap = ((UpdateMetricsMap) this.instance).getMapMap();
                return mapMap.containsKey(Integer.valueOf(i)) ? mapMap.get(Integer.valueOf(i)) : metricDescriptor;
            }

            @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
            public MetricDescriptor getMapOrThrow(int i) {
                Map<Integer, MetricDescriptor> mapMap = ((UpdateMetricsMap) this.instance).getMapMap();
                if (mapMap.containsKey(Integer.valueOf(i))) {
                    return mapMap.get(Integer.valueOf(i));
                }
                throw new IllegalArgumentException();
            }

            public Builder putAllMap(Map<Integer, MetricDescriptor> map) {
                copyOnWrite();
                ((UpdateMetricsMap) this.instance).getMutableMapMap().putAll(map);
                return this;
            }

            public Builder putMap(int i, MetricDescriptor metricDescriptor) {
                if (metricDescriptor != null) {
                    copyOnWrite();
                    ((UpdateMetricsMap) this.instance).getMutableMapMap().put(Integer.valueOf(i), metricDescriptor);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder removeMap(int i) {
                copyOnWrite();
                ((UpdateMetricsMap) this.instance).getMutableMapMap().remove(Integer.valueOf(i));
                return this;
            }

            private Builder() {
                super(UpdateMetricsMap.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        private static final class MapDefaultEntryHolder {
            static final MapEntryLite<Integer, MetricDescriptor> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.UINT32, 0, WireFormat.FieldType.MESSAGE, MetricDescriptor.getDefaultInstance());

            private MapDefaultEntryHolder() {
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UpdateMetricsMap() {
        }

        public static UpdateMetricsMap getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<Integer, MetricDescriptor> getMutableMapMap() {
            return internalGetMutableMap();
        }

        private MapFieldLite<Integer, MetricDescriptor> internalGetMap() {
            return this.map_;
        }

        private MapFieldLite<Integer, MetricDescriptor> internalGetMutableMap() {
            if (!this.map_.isMutable()) {
                this.map_ = this.map_.mutableCopy();
            }
            return this.map_;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UpdateMetricsMap parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateMetricsMap parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UpdateMetricsMap> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
        public boolean containsMap(int i) {
            return internalGetMap().containsKey(Integer.valueOf(i));
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    this.map_ = ((GeneratedMessageLite.Visitor) obj).visitMap(this.map_, ((UpdateMetricsMap) obj2).internalGetMap());
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
                                    if (!this.map_.isMutable()) {
                                        this.map_ = this.map_.mutableCopy();
                                    }
                                    MapDefaultEntryHolder.defaultEntry.parseInto(this.map_, codedInputStream, extensionRegistryLite);
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
                    this.map_.makeImmutable();
                    return null;
                case 6:
                    return new UpdateMetricsMap();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UpdateMetricsMap.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
        @Deprecated
        public Map<Integer, MetricDescriptor> getMap() {
            return getMapMap();
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
        public int getMapCount() {
            return internalGetMap().size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
        public Map<Integer, MetricDescriptor> getMapMap() {
            return Collections.unmodifiableMap(internalGetMap());
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
        public MetricDescriptor getMapOrDefault(int i, MetricDescriptor metricDescriptor) {
            MapFieldLite<Integer, MetricDescriptor> internalGetMap = internalGetMap();
            return internalGetMap.containsKey(Integer.valueOf(i)) ? internalGetMap.get(Integer.valueOf(i)) : metricDescriptor;
        }

        @Override // com.amazon.alexa.accessory.protocol.Metrics.UpdateMetricsMapOrBuilder
        public MetricDescriptor getMapOrThrow(int i) {
            MapFieldLite<Integer, MetricDescriptor> internalGetMap = internalGetMap();
            if (internalGetMap.containsKey(Integer.valueOf(i))) {
                return internalGetMap.get(Integer.valueOf(i));
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (Map.Entry<Integer, MetricDescriptor> entry : internalGetMap().entrySet()) {
                i2 += MapDefaultEntryHolder.defaultEntry.computeMessageSize(1, entry.getKey(), entry.getValue());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (Map.Entry<Integer, MetricDescriptor> entry : internalGetMap().entrySet()) {
                MapDefaultEntryHolder.defaultEntry.serializeTo(codedOutputStream, 1, entry.getKey(), entry.getValue());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UpdateMetricsMap updateMetricsMap) {
            return DEFAULT_INSTANCE.createBuilder(updateMetricsMap);
        }

        public static UpdateMetricsMap parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateMetricsMap parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UpdateMetricsMap parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UpdateMetricsMap parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UpdateMetricsMap parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UpdateMetricsMap parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UpdateMetricsMap parseFrom(InputStream inputStream) throws IOException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateMetricsMap parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateMetricsMap parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UpdateMetricsMap parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateMetricsMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UpdateMetricsMapOrBuilder extends MessageLiteOrBuilder {
        boolean containsMap(int i);

        @Deprecated
        Map<Integer, MetricDescriptor> getMap();

        int getMapCount();

        Map<Integer, MetricDescriptor> getMapMap();

        MetricDescriptor getMapOrDefault(int i, MetricDescriptor metricDescriptor);

        MetricDescriptor getMapOrThrow(int i);
    }

    private Metrics() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
