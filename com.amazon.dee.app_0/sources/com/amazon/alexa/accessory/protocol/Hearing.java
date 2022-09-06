package com.amazon.alexa.accessory.protocol;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public final class Hearing {

    /* renamed from: com.amazon.alexa.accessory.protocol.Hearing$1  reason: invalid class name */
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
    public static final class AudioBand extends GeneratedMessageLite<AudioBand, Builder> implements AudioBandOrBuilder {
        private static final AudioBand DEFAULT_INSTANCE = new AudioBand();
        public static final int FC_FIELD_NUMBER = 1;
        private static volatile Parser<AudioBand> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 2;
        private float fc_;
        private float value_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<AudioBand, Builder> implements AudioBandOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearFc() {
                copyOnWrite();
                ((AudioBand) this.instance).clearFc();
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((AudioBand) this.instance).clearValue();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioBandOrBuilder
            public float getFc() {
                return ((AudioBand) this.instance).getFc();
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioBandOrBuilder
            public float getValue() {
                return ((AudioBand) this.instance).getValue();
            }

            public Builder setFc(float f) {
                copyOnWrite();
                ((AudioBand) this.instance).setFc(f);
                return this;
            }

            public Builder setValue(float f) {
                copyOnWrite();
                ((AudioBand) this.instance).setValue(f);
                return this;
            }

            private Builder() {
                super(AudioBand.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private AudioBand() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFc() {
            this.fc_ = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValue() {
            this.value_ = 0.0f;
        }

        public static AudioBand getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static AudioBand parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AudioBand) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AudioBand parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<AudioBand> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFc(float f) {
            this.fc_ = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValue(float f) {
            this.value_ = f;
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
                    AudioBand audioBand = (AudioBand) obj2;
                    this.fc_ = visitor.visitFloat(this.fc_ != 0.0f, this.fc_, audioBand.fc_ != 0.0f, audioBand.fc_);
                    boolean z2 = this.value_ != 0.0f;
                    float f = this.value_;
                    if (audioBand.value_ != 0.0f) {
                        z = true;
                    }
                    this.value_ = visitor.visitFloat(z2, f, z, audioBand.value_);
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
                                if (readTag == 13) {
                                    this.fc_ = codedInputStream.readFloat();
                                } else if (readTag != 21) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.value_ = codedInputStream.readFloat();
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
                    return new AudioBand();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (AudioBand.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioBandOrBuilder
        public float getFc() {
            return this.fc_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            float f = this.fc_;
            if (f != 0.0f) {
                i2 = 0 + CodedOutputStream.computeFloatSize(1, f);
            }
            float f2 = this.value_;
            if (f2 != 0.0f) {
                i2 += CodedOutputStream.computeFloatSize(2, f2);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioBandOrBuilder
        public float getValue() {
            return this.value_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            float f = this.fc_;
            if (f != 0.0f) {
                codedOutputStream.writeFloat(1, f);
            }
            float f2 = this.value_;
            if (f2 != 0.0f) {
                codedOutputStream.writeFloat(2, f2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(AudioBand audioBand) {
            return DEFAULT_INSTANCE.createBuilder(audioBand);
        }

        public static AudioBand parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AudioBand) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AudioBand parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static AudioBand parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AudioBand parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AudioBand parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AudioBand parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AudioBand parseFrom(InputStream inputStream) throws IOException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AudioBand parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AudioBand parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AudioBand parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AudioBand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface AudioBandOrBuilder extends MessageLiteOrBuilder {
        float getFc();

        float getValue();
    }

    /* loaded from: classes6.dex */
    public static final class AudioChannel extends GeneratedMessageLite<AudioChannel, Builder> implements AudioChannelOrBuilder {
        public static final int BANDS_FIELD_NUMBER = 2;
        private static final AudioChannel DEFAULT_INSTANCE = new AudioChannel();
        private static volatile Parser<AudioChannel> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 1;
        private Internal.ProtobufList<AudioBand> bands_ = GeneratedMessageLite.emptyProtobufList();
        private int bitField0_;
        private int type_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<AudioChannel, Builder> implements AudioChannelOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllBands(Iterable<? extends AudioBand> iterable) {
                copyOnWrite();
                ((AudioChannel) this.instance).addAllBands(iterable);
                return this;
            }

            public Builder addBands(AudioBand audioBand) {
                copyOnWrite();
                ((AudioChannel) this.instance).addBands(audioBand);
                return this;
            }

            public Builder clearBands() {
                copyOnWrite();
                ((AudioChannel) this.instance).clearBands();
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((AudioChannel) this.instance).clearType();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
            public AudioBand getBands(int i) {
                return ((AudioChannel) this.instance).getBands(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
            public int getBandsCount() {
                return ((AudioChannel) this.instance).getBandsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
            public List<AudioBand> getBandsList() {
                return Collections.unmodifiableList(((AudioChannel) this.instance).getBandsList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
            public AudioChannelType getType() {
                return ((AudioChannel) this.instance).getType();
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
            public int getTypeValue() {
                return ((AudioChannel) this.instance).getTypeValue();
            }

            public Builder removeBands(int i) {
                copyOnWrite();
                ((AudioChannel) this.instance).removeBands(i);
                return this;
            }

            public Builder setBands(int i, AudioBand audioBand) {
                copyOnWrite();
                ((AudioChannel) this.instance).setBands(i, audioBand);
                return this;
            }

            public Builder setType(AudioChannelType audioChannelType) {
                copyOnWrite();
                ((AudioChannel) this.instance).setType(audioChannelType);
                return this;
            }

            public Builder setTypeValue(int i) {
                copyOnWrite();
                ((AudioChannel) this.instance).setTypeValue(i);
                return this;
            }

            private Builder() {
                super(AudioChannel.DEFAULT_INSTANCE);
            }

            public Builder addBands(int i, AudioBand audioBand) {
                copyOnWrite();
                ((AudioChannel) this.instance).addBands(i, audioBand);
                return this;
            }

            public Builder setBands(int i, AudioBand.Builder builder) {
                copyOnWrite();
                ((AudioChannel) this.instance).setBands(i, builder);
                return this;
            }

            public Builder addBands(AudioBand.Builder builder) {
                copyOnWrite();
                ((AudioChannel) this.instance).addBands(builder);
                return this;
            }

            public Builder addBands(int i, AudioBand.Builder builder) {
                copyOnWrite();
                ((AudioChannel) this.instance).addBands(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private AudioChannel() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllBands(Iterable<? extends AudioBand> iterable) {
            ensureBandsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.bands_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBands(AudioBand audioBand) {
            if (audioBand != null) {
                ensureBandsIsMutable();
                this.bands_.add(audioBand);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBands() {
            this.bands_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.type_ = 0;
        }

        private void ensureBandsIsMutable() {
            if (!this.bands_.isModifiable()) {
                this.bands_ = GeneratedMessageLite.mutableCopy(this.bands_);
            }
        }

        public static AudioChannel getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static AudioChannel parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AudioChannel) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AudioChannel parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<AudioChannel> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeBands(int i) {
            ensureBandsIsMutable();
            this.bands_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBands(int i, AudioBand audioBand) {
            if (audioBand != null) {
                ensureBandsIsMutable();
                this.bands_.set(i, audioBand);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(AudioChannelType audioChannelType) {
            if (audioChannelType != null) {
                this.type_ = audioChannelType.getNumber();
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
                    AudioChannel audioChannel = (AudioChannel) obj2;
                    boolean z2 = this.type_ != 0;
                    int i = this.type_;
                    if (audioChannel.type_ != 0) {
                        z = true;
                    }
                    this.type_ = visitor.visitInt(z2, i, z, audioChannel.type_);
                    this.bands_ = visitor.visitList(this.bands_, audioChannel.bands_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= audioChannel.bitField0_;
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
                                    this.type_ = codedInputStream.readEnum();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    if (!this.bands_.isModifiable()) {
                                        this.bands_ = GeneratedMessageLite.mutableCopy(this.bands_);
                                    }
                                    this.bands_.add(codedInputStream.readMessage(AudioBand.parser(), extensionRegistryLite));
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
                    this.bands_.makeImmutable();
                    return null;
                case 6:
                    return new AudioChannel();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (AudioChannel.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
        public AudioBand getBands(int i) {
            return this.bands_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
        public int getBandsCount() {
            return this.bands_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
        public List<AudioBand> getBandsList() {
            return this.bands_;
        }

        public AudioBandOrBuilder getBandsOrBuilder(int i) {
            return this.bands_.get(i);
        }

        public List<? extends AudioBandOrBuilder> getBandsOrBuilderList() {
            return this.bands_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.type_ != AudioChannelType.ALL.getNumber() ? CodedOutputStream.computeEnumSize(1, this.type_) + 0 : 0;
            for (int i2 = 0; i2 < this.bands_.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.bands_.get(i2));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeEnumSize;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
        public AudioChannelType getType() {
            AudioChannelType forNumber = AudioChannelType.forNumber(this.type_);
            return forNumber == null ? AudioChannelType.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudioChannelOrBuilder
        public int getTypeValue() {
            return this.type_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.type_ != AudioChannelType.ALL.getNumber()) {
                codedOutputStream.writeEnum(1, this.type_);
            }
            for (int i = 0; i < this.bands_.size(); i++) {
                codedOutputStream.writeMessage(2, this.bands_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(AudioChannel audioChannel) {
            return DEFAULT_INSTANCE.createBuilder(audioChannel);
        }

        public static AudioChannel parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AudioChannel) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AudioChannel parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static AudioChannel parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBands(int i, AudioBand audioBand) {
            if (audioBand != null) {
                ensureBandsIsMutable();
                this.bands_.add(i, audioBand);
                return;
            }
            throw new NullPointerException();
        }

        public static AudioChannel parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBands(int i, AudioBand.Builder builder) {
            ensureBandsIsMutable();
            this.bands_.set(i, builder.mo10084build());
        }

        public static AudioChannel parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AudioChannel parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBands(AudioBand.Builder builder) {
            ensureBandsIsMutable();
            this.bands_.add(builder.mo10084build());
        }

        public static AudioChannel parseFrom(InputStream inputStream) throws IOException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AudioChannel parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBands(int i, AudioBand.Builder builder) {
            ensureBandsIsMutable();
            this.bands_.add(i, builder.mo10084build());
        }

        public static AudioChannel parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AudioChannel parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AudioChannel) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface AudioChannelOrBuilder extends MessageLiteOrBuilder {
        AudioBand getBands(int i);

        int getBandsCount();

        List<AudioBand> getBandsList();

        AudioChannelType getType();

        int getTypeValue();
    }

    /* loaded from: classes6.dex */
    public enum AudioChannelType implements Internal.EnumLite {
        ALL(0),
        LEFT(1),
        RIGHT(2),
        UNRECOGNIZED(-1);
        
        public static final int ALL_VALUE = 0;
        public static final int LEFT_VALUE = 1;
        public static final int RIGHT_VALUE = 2;
        private static final Internal.EnumLiteMap<AudioChannelType> internalValueMap = new Internal.EnumLiteMap<AudioChannelType>() { // from class: com.amazon.alexa.accessory.protocol.Hearing.AudioChannelType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public AudioChannelType mo9850findValueByNumber(int i) {
                return AudioChannelType.forNumber(i);
            }
        };
        private final int value;

        AudioChannelType(int i) {
            this.value = i;
        }

        public static AudioChannelType forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return LEFT;
                }
                if (i == 2) {
                    return RIGHT;
                }
                return null;
            }
            return ALL;
        }

        public static Internal.EnumLiteMap<AudioChannelType> internalGetValueMap() {
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
        public static AudioChannelType valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class Audiogram extends GeneratedMessageLite<Audiogram, Builder> implements AudiogramOrBuilder {
        public static final int AUDIOGRAM_FIELD_NUMBER = 2;
        private static final Audiogram DEFAULT_INSTANCE = new Audiogram();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<Audiogram> PARSER;
        private Internal.ProtobufList<AudioChannel> audiogram_ = GeneratedMessageLite.emptyProtobufList();
        private int bitField0_;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Audiogram, Builder> implements AudiogramOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllAudiogram(Iterable<? extends AudioChannel> iterable) {
                copyOnWrite();
                ((Audiogram) this.instance).addAllAudiogram(iterable);
                return this;
            }

            public Builder addAudiogram(AudioChannel audioChannel) {
                copyOnWrite();
                ((Audiogram) this.instance).addAudiogram(audioChannel);
                return this;
            }

            public Builder clearAudiogram() {
                copyOnWrite();
                ((Audiogram) this.instance).clearAudiogram();
                return this;
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((Audiogram) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudiogramOrBuilder
            public AudioChannel getAudiogram(int i) {
                return ((Audiogram) this.instance).getAudiogram(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudiogramOrBuilder
            public int getAudiogramCount() {
                return ((Audiogram) this.instance).getAudiogramCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudiogramOrBuilder
            public List<AudioChannel> getAudiogramList() {
                return Collections.unmodifiableList(((Audiogram) this.instance).getAudiogramList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.AudiogramOrBuilder
            public int getDeviceId() {
                return ((Audiogram) this.instance).getDeviceId();
            }

            public Builder removeAudiogram(int i) {
                copyOnWrite();
                ((Audiogram) this.instance).removeAudiogram(i);
                return this;
            }

            public Builder setAudiogram(int i, AudioChannel audioChannel) {
                copyOnWrite();
                ((Audiogram) this.instance).setAudiogram(i, audioChannel);
                return this;
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((Audiogram) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(Audiogram.DEFAULT_INSTANCE);
            }

            public Builder addAudiogram(int i, AudioChannel audioChannel) {
                copyOnWrite();
                ((Audiogram) this.instance).addAudiogram(i, audioChannel);
                return this;
            }

            public Builder setAudiogram(int i, AudioChannel.Builder builder) {
                copyOnWrite();
                ((Audiogram) this.instance).setAudiogram(i, builder);
                return this;
            }

            public Builder addAudiogram(AudioChannel.Builder builder) {
                copyOnWrite();
                ((Audiogram) this.instance).addAudiogram(builder);
                return this;
            }

            public Builder addAudiogram(int i, AudioChannel.Builder builder) {
                copyOnWrite();
                ((Audiogram) this.instance).addAudiogram(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Audiogram() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAudiogram(Iterable<? extends AudioChannel> iterable) {
            ensureAudiogramIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.audiogram_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAudiogram(AudioChannel audioChannel) {
            if (audioChannel != null) {
                ensureAudiogramIsMutable();
                this.audiogram_.add(audioChannel);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAudiogram() {
            this.audiogram_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        private void ensureAudiogramIsMutable() {
            if (!this.audiogram_.isModifiable()) {
                this.audiogram_ = GeneratedMessageLite.mutableCopy(this.audiogram_);
            }
        }

        public static Audiogram getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Audiogram parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Audiogram) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Audiogram parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Audiogram> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAudiogram(int i) {
            ensureAudiogramIsMutable();
            this.audiogram_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAudiogram(int i, AudioChannel audioChannel) {
            if (audioChannel != null) {
                ensureAudiogramIsMutable();
                this.audiogram_.set(i, audioChannel);
                return;
            }
            throw new NullPointerException();
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
                    Audiogram audiogram = (Audiogram) obj2;
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (audiogram.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, audiogram.deviceId_);
                    this.audiogram_ = visitor.visitList(this.audiogram_, audiogram.audiogram_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= audiogram.bitField0_;
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
                                    this.deviceId_ = codedInputStream.readUInt32();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    if (!this.audiogram_.isModifiable()) {
                                        this.audiogram_ = GeneratedMessageLite.mutableCopy(this.audiogram_);
                                    }
                                    this.audiogram_.add(codedInputStream.readMessage(AudioChannel.parser(), extensionRegistryLite));
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
                    this.audiogram_.makeImmutable();
                    return null;
                case 6:
                    return new Audiogram();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (Audiogram.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudiogramOrBuilder
        public AudioChannel getAudiogram(int i) {
            return this.audiogram_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudiogramOrBuilder
        public int getAudiogramCount() {
            return this.audiogram_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudiogramOrBuilder
        public List<AudioChannel> getAudiogramList() {
            return this.audiogram_;
        }

        public AudioChannelOrBuilder getAudiogramOrBuilder(int i) {
            return this.audiogram_.get(i);
        }

        public List<? extends AudioChannelOrBuilder> getAudiogramOrBuilderList() {
            return this.audiogram_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.AudiogramOrBuilder
        public int getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.deviceId_;
            int computeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) + 0 : 0;
            for (int i3 = 0; i3 < this.audiogram_.size(); i3++) {
                computeUInt32Size += CodedOutputStream.computeMessageSize(2, this.audiogram_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeUInt32Size;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.deviceId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            for (int i2 = 0; i2 < this.audiogram_.size(); i2++) {
                codedOutputStream.writeMessage(2, this.audiogram_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(Audiogram audiogram) {
            return DEFAULT_INSTANCE.createBuilder(audiogram);
        }

        public static Audiogram parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Audiogram) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Audiogram parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Audiogram parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAudiogram(int i, AudioChannel audioChannel) {
            if (audioChannel != null) {
                ensureAudiogramIsMutable();
                this.audiogram_.add(i, audioChannel);
                return;
            }
            throw new NullPointerException();
        }

        public static Audiogram parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAudiogram(int i, AudioChannel.Builder builder) {
            ensureAudiogramIsMutable();
            this.audiogram_.set(i, builder.mo10084build());
        }

        public static Audiogram parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Audiogram parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAudiogram(AudioChannel.Builder builder) {
            ensureAudiogramIsMutable();
            this.audiogram_.add(builder.mo10084build());
        }

        public static Audiogram parseFrom(InputStream inputStream) throws IOException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Audiogram parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAudiogram(int i, AudioChannel.Builder builder) {
            ensureAudiogramIsMutable();
            this.audiogram_.add(i, builder.mo10084build());
        }

        public static Audiogram parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Audiogram parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Audiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface AudiogramOrBuilder extends MessageLiteOrBuilder {
        AudioChannel getAudiogram(int i);

        int getAudiogramCount();

        List<AudioChannel> getAudiogramList();

        int getDeviceId();
    }

    /* loaded from: classes6.dex */
    public static final class GetAudiogram extends GeneratedMessageLite<GetAudiogram, Builder> implements GetAudiogramOrBuilder {
        private static final GetAudiogram DEFAULT_INSTANCE = new GetAudiogram();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<GetAudiogram> PARSER;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetAudiogram, Builder> implements GetAudiogramOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((GetAudiogram) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.GetAudiogramOrBuilder
            public int getDeviceId() {
                return ((GetAudiogram) this.instance).getDeviceId();
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((GetAudiogram) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(GetAudiogram.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetAudiogram() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        public static GetAudiogram getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetAudiogram parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetAudiogram) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetAudiogram parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetAudiogram> parser() {
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
                    GetAudiogram getAudiogram = (GetAudiogram) obj2;
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (getAudiogram.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, getAudiogram.deviceId_);
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
                    return new GetAudiogram();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetAudiogram.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Hearing.GetAudiogramOrBuilder
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

        public static Builder newBuilder(GetAudiogram getAudiogram) {
            return DEFAULT_INSTANCE.createBuilder(getAudiogram);
        }

        public static GetAudiogram parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetAudiogram) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetAudiogram parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetAudiogram parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetAudiogram parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetAudiogram parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetAudiogram parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetAudiogram parseFrom(InputStream inputStream) throws IOException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetAudiogram parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetAudiogram parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetAudiogram parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetAudiogramOrBuilder extends MessageLiteOrBuilder {
        int getDeviceId();
    }

    /* loaded from: classes6.dex */
    public static final class GetMediaEnhancementCorrectionAmount extends GeneratedMessageLite<GetMediaEnhancementCorrectionAmount, Builder> implements GetMediaEnhancementCorrectionAmountOrBuilder {
        private static final GetMediaEnhancementCorrectionAmount DEFAULT_INSTANCE = new GetMediaEnhancementCorrectionAmount();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<GetMediaEnhancementCorrectionAmount> PARSER;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetMediaEnhancementCorrectionAmount, Builder> implements GetMediaEnhancementCorrectionAmountOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((GetMediaEnhancementCorrectionAmount) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.GetMediaEnhancementCorrectionAmountOrBuilder
            public int getDeviceId() {
                return ((GetMediaEnhancementCorrectionAmount) this.instance).getDeviceId();
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((GetMediaEnhancementCorrectionAmount) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(GetMediaEnhancementCorrectionAmount.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetMediaEnhancementCorrectionAmount() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        public static GetMediaEnhancementCorrectionAmount getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetMediaEnhancementCorrectionAmount parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetMediaEnhancementCorrectionAmount> parser() {
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
                    GetMediaEnhancementCorrectionAmount getMediaEnhancementCorrectionAmount = (GetMediaEnhancementCorrectionAmount) obj2;
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (getMediaEnhancementCorrectionAmount.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, getMediaEnhancementCorrectionAmount.deviceId_);
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
                    return new GetMediaEnhancementCorrectionAmount();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetMediaEnhancementCorrectionAmount.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Hearing.GetMediaEnhancementCorrectionAmountOrBuilder
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

        public static Builder newBuilder(GetMediaEnhancementCorrectionAmount getMediaEnhancementCorrectionAmount) {
            return DEFAULT_INSTANCE.createBuilder(getMediaEnhancementCorrectionAmount);
        }

        public static GetMediaEnhancementCorrectionAmount parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(InputStream inputStream) throws IOException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetMediaEnhancementCorrectionAmount parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetMediaEnhancementCorrectionAmountOrBuilder extends MessageLiteOrBuilder {
        int getDeviceId();
    }

    /* loaded from: classes6.dex */
    public static final class MediaEnhancementCorrectionAmount extends GeneratedMessageLite<MediaEnhancementCorrectionAmount, Builder> implements MediaEnhancementCorrectionAmountOrBuilder {
        public static final int CORRECTION_AMOUNT_FIELD_NUMBER = 2;
        private static final MediaEnhancementCorrectionAmount DEFAULT_INSTANCE = new MediaEnhancementCorrectionAmount();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<MediaEnhancementCorrectionAmount> PARSER;
        private int correctionAmount_;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<MediaEnhancementCorrectionAmount, Builder> implements MediaEnhancementCorrectionAmountOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCorrectionAmount() {
                copyOnWrite();
                ((MediaEnhancementCorrectionAmount) this.instance).clearCorrectionAmount();
                return this;
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((MediaEnhancementCorrectionAmount) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.MediaEnhancementCorrectionAmountOrBuilder
            public int getCorrectionAmount() {
                return ((MediaEnhancementCorrectionAmount) this.instance).getCorrectionAmount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.MediaEnhancementCorrectionAmountOrBuilder
            public int getDeviceId() {
                return ((MediaEnhancementCorrectionAmount) this.instance).getDeviceId();
            }

            public Builder setCorrectionAmount(int i) {
                copyOnWrite();
                ((MediaEnhancementCorrectionAmount) this.instance).setCorrectionAmount(i);
                return this;
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((MediaEnhancementCorrectionAmount) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(MediaEnhancementCorrectionAmount.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private MediaEnhancementCorrectionAmount() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCorrectionAmount() {
            this.correctionAmount_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        public static MediaEnhancementCorrectionAmount getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static MediaEnhancementCorrectionAmount parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<MediaEnhancementCorrectionAmount> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCorrectionAmount(int i) {
            this.correctionAmount_ = i;
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
                    MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount = (MediaEnhancementCorrectionAmount) obj2;
                    this.deviceId_ = visitor.visitInt(this.deviceId_ != 0, this.deviceId_, mediaEnhancementCorrectionAmount.deviceId_ != 0, mediaEnhancementCorrectionAmount.deviceId_);
                    boolean z2 = this.correctionAmount_ != 0;
                    int i = this.correctionAmount_;
                    if (mediaEnhancementCorrectionAmount.correctionAmount_ != 0) {
                        z = true;
                    }
                    this.correctionAmount_ = visitor.visitInt(z2, i, z, mediaEnhancementCorrectionAmount.correctionAmount_);
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
                                    this.deviceId_ = codedInputStream.readUInt32();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.correctionAmount_ = codedInputStream.readUInt32();
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
                    return new MediaEnhancementCorrectionAmount();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (MediaEnhancementCorrectionAmount.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Hearing.MediaEnhancementCorrectionAmountOrBuilder
        public int getCorrectionAmount() {
            return this.correctionAmount_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.MediaEnhancementCorrectionAmountOrBuilder
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
            int i4 = this.correctionAmount_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
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
            int i2 = this.correctionAmount_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
            return DEFAULT_INSTANCE.createBuilder(mediaEnhancementCorrectionAmount);
        }

        public static MediaEnhancementCorrectionAmount parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(InputStream inputStream) throws IOException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static MediaEnhancementCorrectionAmount parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface MediaEnhancementCorrectionAmountOrBuilder extends MessageLiteOrBuilder {
        int getCorrectionAmount();

        int getDeviceId();
    }

    /* loaded from: classes6.dex */
    public static final class SetAudiogram extends GeneratedMessageLite<SetAudiogram, Builder> implements SetAudiogramOrBuilder {
        public static final int AUDIOGRAM_FIELD_NUMBER = 1;
        private static final SetAudiogram DEFAULT_INSTANCE = new SetAudiogram();
        private static volatile Parser<SetAudiogram> PARSER;
        private Audiogram audiogram_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SetAudiogram, Builder> implements SetAudiogramOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAudiogram() {
                copyOnWrite();
                ((SetAudiogram) this.instance).clearAudiogram();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.SetAudiogramOrBuilder
            public Audiogram getAudiogram() {
                return ((SetAudiogram) this.instance).getAudiogram();
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.SetAudiogramOrBuilder
            public boolean hasAudiogram() {
                return ((SetAudiogram) this.instance).hasAudiogram();
            }

            public Builder mergeAudiogram(Audiogram audiogram) {
                copyOnWrite();
                ((SetAudiogram) this.instance).mergeAudiogram(audiogram);
                return this;
            }

            public Builder setAudiogram(Audiogram audiogram) {
                copyOnWrite();
                ((SetAudiogram) this.instance).setAudiogram(audiogram);
                return this;
            }

            private Builder() {
                super(SetAudiogram.DEFAULT_INSTANCE);
            }

            public Builder setAudiogram(Audiogram.Builder builder) {
                copyOnWrite();
                ((SetAudiogram) this.instance).setAudiogram(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SetAudiogram() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAudiogram() {
            this.audiogram_ = null;
        }

        public static SetAudiogram getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAudiogram(Audiogram audiogram) {
            Audiogram audiogram2 = this.audiogram_;
            if (audiogram2 != null && audiogram2 != Audiogram.getDefaultInstance()) {
                this.audiogram_ = Audiogram.newBuilder(this.audiogram_).mergeFrom((Audiogram.Builder) audiogram).mo10085buildPartial();
            } else {
                this.audiogram_ = audiogram;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SetAudiogram parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SetAudiogram) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetAudiogram parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SetAudiogram> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAudiogram(Audiogram audiogram) {
            if (audiogram != null) {
                this.audiogram_ = audiogram;
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
                    this.audiogram_ = (Audiogram) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.audiogram_, ((SetAudiogram) obj2).audiogram_);
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
                                    Audiogram.Builder mo10081toBuilder = this.audiogram_ != null ? this.audiogram_.mo10081toBuilder() : null;
                                    this.audiogram_ = (Audiogram) codedInputStream.readMessage(Audiogram.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Audiogram.Builder) this.audiogram_);
                                        this.audiogram_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new SetAudiogram();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SetAudiogram.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Hearing.SetAudiogramOrBuilder
        public Audiogram getAudiogram() {
            Audiogram audiogram = this.audiogram_;
            return audiogram == null ? Audiogram.getDefaultInstance() : audiogram;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.audiogram_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getAudiogram());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.SetAudiogramOrBuilder
        public boolean hasAudiogram() {
            return this.audiogram_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.audiogram_ != null) {
                codedOutputStream.writeMessage(1, getAudiogram());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SetAudiogram setAudiogram) {
            return DEFAULT_INSTANCE.createBuilder(setAudiogram);
        }

        public static SetAudiogram parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetAudiogram) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetAudiogram parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SetAudiogram parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAudiogram(Audiogram.Builder builder) {
            this.audiogram_ = builder.mo10084build();
        }

        public static SetAudiogram parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SetAudiogram parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SetAudiogram parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SetAudiogram parseFrom(InputStream inputStream) throws IOException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetAudiogram parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetAudiogram parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SetAudiogram parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetAudiogram) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SetAudiogramOrBuilder extends MessageLiteOrBuilder {
        Audiogram getAudiogram();

        boolean hasAudiogram();
    }

    /* loaded from: classes6.dex */
    public static final class SetMediaEnhancementCorrectionAmount extends GeneratedMessageLite<SetMediaEnhancementCorrectionAmount, Builder> implements SetMediaEnhancementCorrectionAmountOrBuilder {
        public static final int CORRECTION_AMOUNT_FIELD_NUMBER = 1;
        private static final SetMediaEnhancementCorrectionAmount DEFAULT_INSTANCE = new SetMediaEnhancementCorrectionAmount();
        private static volatile Parser<SetMediaEnhancementCorrectionAmount> PARSER;
        private MediaEnhancementCorrectionAmount correctionAmount_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SetMediaEnhancementCorrectionAmount, Builder> implements SetMediaEnhancementCorrectionAmountOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCorrectionAmount() {
                copyOnWrite();
                ((SetMediaEnhancementCorrectionAmount) this.instance).clearCorrectionAmount();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.SetMediaEnhancementCorrectionAmountOrBuilder
            public MediaEnhancementCorrectionAmount getCorrectionAmount() {
                return ((SetMediaEnhancementCorrectionAmount) this.instance).getCorrectionAmount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Hearing.SetMediaEnhancementCorrectionAmountOrBuilder
            public boolean hasCorrectionAmount() {
                return ((SetMediaEnhancementCorrectionAmount) this.instance).hasCorrectionAmount();
            }

            public Builder mergeCorrectionAmount(MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
                copyOnWrite();
                ((SetMediaEnhancementCorrectionAmount) this.instance).mergeCorrectionAmount(mediaEnhancementCorrectionAmount);
                return this;
            }

            public Builder setCorrectionAmount(MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
                copyOnWrite();
                ((SetMediaEnhancementCorrectionAmount) this.instance).setCorrectionAmount(mediaEnhancementCorrectionAmount);
                return this;
            }

            private Builder() {
                super(SetMediaEnhancementCorrectionAmount.DEFAULT_INSTANCE);
            }

            public Builder setCorrectionAmount(MediaEnhancementCorrectionAmount.Builder builder) {
                copyOnWrite();
                ((SetMediaEnhancementCorrectionAmount) this.instance).setCorrectionAmount(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SetMediaEnhancementCorrectionAmount() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCorrectionAmount() {
            this.correctionAmount_ = null;
        }

        public static SetMediaEnhancementCorrectionAmount getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCorrectionAmount(MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
            MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount2 = this.correctionAmount_;
            if (mediaEnhancementCorrectionAmount2 != null && mediaEnhancementCorrectionAmount2 != MediaEnhancementCorrectionAmount.getDefaultInstance()) {
                this.correctionAmount_ = MediaEnhancementCorrectionAmount.newBuilder(this.correctionAmount_).mergeFrom((MediaEnhancementCorrectionAmount.Builder) mediaEnhancementCorrectionAmount).mo10085buildPartial();
            } else {
                this.correctionAmount_ = mediaEnhancementCorrectionAmount;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SetMediaEnhancementCorrectionAmount parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SetMediaEnhancementCorrectionAmount> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCorrectionAmount(MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
            if (mediaEnhancementCorrectionAmount != null) {
                this.correctionAmount_ = mediaEnhancementCorrectionAmount;
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
                    this.correctionAmount_ = (MediaEnhancementCorrectionAmount) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.correctionAmount_, ((SetMediaEnhancementCorrectionAmount) obj2).correctionAmount_);
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
                                    MediaEnhancementCorrectionAmount.Builder mo10081toBuilder = this.correctionAmount_ != null ? this.correctionAmount_.mo10081toBuilder() : null;
                                    this.correctionAmount_ = (MediaEnhancementCorrectionAmount) codedInputStream.readMessage(MediaEnhancementCorrectionAmount.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((MediaEnhancementCorrectionAmount.Builder) this.correctionAmount_);
                                        this.correctionAmount_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new SetMediaEnhancementCorrectionAmount();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SetMediaEnhancementCorrectionAmount.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Hearing.SetMediaEnhancementCorrectionAmountOrBuilder
        public MediaEnhancementCorrectionAmount getCorrectionAmount() {
            MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount = this.correctionAmount_;
            return mediaEnhancementCorrectionAmount == null ? MediaEnhancementCorrectionAmount.getDefaultInstance() : mediaEnhancementCorrectionAmount;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.correctionAmount_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getCorrectionAmount());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Hearing.SetMediaEnhancementCorrectionAmountOrBuilder
        public boolean hasCorrectionAmount() {
            return this.correctionAmount_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.correctionAmount_ != null) {
                codedOutputStream.writeMessage(1, getCorrectionAmount());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SetMediaEnhancementCorrectionAmount setMediaEnhancementCorrectionAmount) {
            return DEFAULT_INSTANCE.createBuilder(setMediaEnhancementCorrectionAmount);
        }

        public static SetMediaEnhancementCorrectionAmount parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCorrectionAmount(MediaEnhancementCorrectionAmount.Builder builder) {
            this.correctionAmount_ = builder.mo10084build();
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(InputStream inputStream) throws IOException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SetMediaEnhancementCorrectionAmount parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetMediaEnhancementCorrectionAmount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SetMediaEnhancementCorrectionAmountOrBuilder extends MessageLiteOrBuilder {
        MediaEnhancementCorrectionAmount getCorrectionAmount();

        boolean hasCorrectionAmount();
    }

    private Hearing() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
