package com.amazon.alexa.accessory.protocol;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Speech;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes6.dex */
public final class Voicestream {

    /* renamed from: com.amazon.alexa.accessory.protocol.Voicestream$1  reason: invalid class name */
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
    public static final class StartVoiceStream extends GeneratedMessageLite<StartVoiceStream, Builder> implements StartVoiceStreamOrBuilder {
        private static final StartVoiceStream DEFAULT_INSTANCE = new StartVoiceStream();
        private static volatile Parser<StartVoiceStream> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<StartVoiceStream, Builder> implements StartVoiceStreamOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(StartVoiceStream.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private StartVoiceStream() {
        }

        public static StartVoiceStream getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static StartVoiceStream parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StartVoiceStream) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StartVoiceStream parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<StartVoiceStream> parser() {
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
                    StartVoiceStream startVoiceStream = (StartVoiceStream) obj2;
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
                    return new StartVoiceStream();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (StartVoiceStream.class) {
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

        public static Builder newBuilder(StartVoiceStream startVoiceStream) {
            return DEFAULT_INSTANCE.createBuilder(startVoiceStream);
        }

        public static StartVoiceStream parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartVoiceStream) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StartVoiceStream parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StartVoiceStream parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StartVoiceStream parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StartVoiceStream parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StartVoiceStream parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StartVoiceStream parseFrom(InputStream inputStream) throws IOException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StartVoiceStream parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StartVoiceStream parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StartVoiceStream parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface StartVoiceStreamOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class StopVoiceStream extends GeneratedMessageLite<StopVoiceStream, Builder> implements StopVoiceStreamOrBuilder {
        private static final StopVoiceStream DEFAULT_INSTANCE = new StopVoiceStream();
        public static final int ERROR_CODE_FIELD_NUMBER = 1;
        private static volatile Parser<StopVoiceStream> PARSER;
        private int errorCode_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<StopVoiceStream, Builder> implements StopVoiceStreamOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearErrorCode() {
                copyOnWrite();
                ((StopVoiceStream) this.instance).clearErrorCode();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Voicestream.StopVoiceStreamOrBuilder
            public Common.ErrorCode getErrorCode() {
                return ((StopVoiceStream) this.instance).getErrorCode();
            }

            @Override // com.amazon.alexa.accessory.protocol.Voicestream.StopVoiceStreamOrBuilder
            public int getErrorCodeValue() {
                return ((StopVoiceStream) this.instance).getErrorCodeValue();
            }

            public Builder setErrorCode(Common.ErrorCode errorCode) {
                copyOnWrite();
                ((StopVoiceStream) this.instance).setErrorCode(errorCode);
                return this;
            }

            public Builder setErrorCodeValue(int i) {
                copyOnWrite();
                ((StopVoiceStream) this.instance).setErrorCodeValue(i);
                return this;
            }

            private Builder() {
                super(StopVoiceStream.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private StopVoiceStream() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorCode() {
            this.errorCode_ = 0;
        }

        public static StopVoiceStream getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static StopVoiceStream parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StopVoiceStream) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StopVoiceStream parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<StopVoiceStream> parser() {
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
                    StopVoiceStream stopVoiceStream = (StopVoiceStream) obj2;
                    boolean z2 = this.errorCode_ != 0;
                    int i = this.errorCode_;
                    if (stopVoiceStream.errorCode_ != 0) {
                        z = true;
                    }
                    this.errorCode_ = visitor.visitInt(z2, i, z, stopVoiceStream.errorCode_);
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
                    return new StopVoiceStream();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (StopVoiceStream.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Voicestream.StopVoiceStreamOrBuilder
        public Common.ErrorCode getErrorCode() {
            Common.ErrorCode forNumber = Common.ErrorCode.forNumber(this.errorCode_);
            return forNumber == null ? Common.ErrorCode.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Voicestream.StopVoiceStreamOrBuilder
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

        public static Builder newBuilder(StopVoiceStream stopVoiceStream) {
            return DEFAULT_INSTANCE.createBuilder(stopVoiceStream);
        }

        public static StopVoiceStream parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopVoiceStream) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StopVoiceStream parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StopVoiceStream parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StopVoiceStream parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StopVoiceStream parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StopVoiceStream parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StopVoiceStream parseFrom(InputStream inputStream) throws IOException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StopVoiceStream parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StopVoiceStream parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StopVoiceStream parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopVoiceStream) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface StopVoiceStreamOrBuilder extends MessageLiteOrBuilder {
        Common.ErrorCode getErrorCode();

        int getErrorCodeValue();
    }

    /* loaded from: classes6.dex */
    public static final class VoiceProvider extends GeneratedMessageLite<VoiceProvider, Builder> implements VoiceProviderOrBuilder {
        private static final VoiceProvider DEFAULT_INSTANCE = new VoiceProvider();
        private static volatile Parser<VoiceProvider> PARSER = null;
        public static final int SPEECH_SETTINGS_FIELD_NUMBER = 1;
        private Speech.SpeechSettings speechSettings_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<VoiceProvider, Builder> implements VoiceProviderOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearSpeechSettings() {
                copyOnWrite();
                ((VoiceProvider) this.instance).clearSpeechSettings();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Voicestream.VoiceProviderOrBuilder
            public Speech.SpeechSettings getSpeechSettings() {
                return ((VoiceProvider) this.instance).getSpeechSettings();
            }

            @Override // com.amazon.alexa.accessory.protocol.Voicestream.VoiceProviderOrBuilder
            public boolean hasSpeechSettings() {
                return ((VoiceProvider) this.instance).hasSpeechSettings();
            }

            public Builder mergeSpeechSettings(Speech.SpeechSettings speechSettings) {
                copyOnWrite();
                ((VoiceProvider) this.instance).mergeSpeechSettings(speechSettings);
                return this;
            }

            public Builder setSpeechSettings(Speech.SpeechSettings speechSettings) {
                copyOnWrite();
                ((VoiceProvider) this.instance).setSpeechSettings(speechSettings);
                return this;
            }

            private Builder() {
                super(VoiceProvider.DEFAULT_INSTANCE);
            }

            public Builder setSpeechSettings(Speech.SpeechSettings.Builder builder) {
                copyOnWrite();
                ((VoiceProvider) this.instance).setSpeechSettings(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private VoiceProvider() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSpeechSettings() {
            this.speechSettings_ = null;
        }

        public static VoiceProvider getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSpeechSettings(Speech.SpeechSettings speechSettings) {
            Speech.SpeechSettings speechSettings2 = this.speechSettings_;
            if (speechSettings2 != null && speechSettings2 != Speech.SpeechSettings.getDefaultInstance()) {
                this.speechSettings_ = Speech.SpeechSettings.newBuilder(this.speechSettings_).mergeFrom((Speech.SpeechSettings.Builder) speechSettings).mo10085buildPartial();
            } else {
                this.speechSettings_ = speechSettings;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static VoiceProvider parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (VoiceProvider) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VoiceProvider parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<VoiceProvider> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpeechSettings(Speech.SpeechSettings speechSettings) {
            if (speechSettings != null) {
                this.speechSettings_ = speechSettings;
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
                    this.speechSettings_ = (Speech.SpeechSettings) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.speechSettings_, ((VoiceProvider) obj2).speechSettings_);
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
                                    Speech.SpeechSettings.Builder mo10081toBuilder = this.speechSettings_ != null ? this.speechSettings_.mo10081toBuilder() : null;
                                    this.speechSettings_ = (Speech.SpeechSettings) codedInputStream.readMessage(Speech.SpeechSettings.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Speech.SpeechSettings.Builder) this.speechSettings_);
                                        this.speechSettings_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new VoiceProvider();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (VoiceProvider.class) {
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
            int i2 = 0;
            if (this.speechSettings_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getSpeechSettings());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Voicestream.VoiceProviderOrBuilder
        public Speech.SpeechSettings getSpeechSettings() {
            Speech.SpeechSettings speechSettings = this.speechSettings_;
            return speechSettings == null ? Speech.SpeechSettings.getDefaultInstance() : speechSettings;
        }

        @Override // com.amazon.alexa.accessory.protocol.Voicestream.VoiceProviderOrBuilder
        public boolean hasSpeechSettings() {
            return this.speechSettings_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.speechSettings_ != null) {
                codedOutputStream.writeMessage(1, getSpeechSettings());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(VoiceProvider voiceProvider) {
            return DEFAULT_INSTANCE.createBuilder(voiceProvider);
        }

        public static VoiceProvider parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VoiceProvider) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VoiceProvider parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static VoiceProvider parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpeechSettings(Speech.SpeechSettings.Builder builder) {
            this.speechSettings_ = builder.mo10084build();
        }

        public static VoiceProvider parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static VoiceProvider parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static VoiceProvider parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static VoiceProvider parseFrom(InputStream inputStream) throws IOException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VoiceProvider parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VoiceProvider parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static VoiceProvider parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VoiceProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface VoiceProviderOrBuilder extends MessageLiteOrBuilder {
        Speech.SpeechSettings getSpeechSettings();

        boolean hasSpeechSettings();
    }

    private Voicestream() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
