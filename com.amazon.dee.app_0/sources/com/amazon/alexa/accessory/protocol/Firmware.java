package com.amazon.alexa.accessory.protocol;

import com.amazon.alexa.accessory.protocol.System;
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
public final class Firmware {

    /* renamed from: com.amazon.alexa.accessory.protocol.Firmware$1  reason: invalid class name */
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
    public static final class ApplyFirmware extends GeneratedMessageLite<ApplyFirmware, Builder> implements ApplyFirmwareOrBuilder {
        private static final ApplyFirmware DEFAULT_INSTANCE = new ApplyFirmware();
        public static final int FIRMWARE_INFORMATION_FIELD_NUMBER = 1;
        private static volatile Parser<ApplyFirmware> PARSER = null;
        public static final int RESTART_REQUIRED_FIELD_NUMBER = 2;
        private FirmwareInformation firmwareInformation_;
        private boolean restartRequired_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ApplyFirmware, Builder> implements ApplyFirmwareOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearFirmwareInformation() {
                copyOnWrite();
                ((ApplyFirmware) this.instance).clearFirmwareInformation();
                return this;
            }

            public Builder clearRestartRequired() {
                copyOnWrite();
                ((ApplyFirmware) this.instance).clearRestartRequired();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ApplyFirmwareOrBuilder
            public FirmwareInformation getFirmwareInformation() {
                return ((ApplyFirmware) this.instance).getFirmwareInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ApplyFirmwareOrBuilder
            public boolean getRestartRequired() {
                return ((ApplyFirmware) this.instance).getRestartRequired();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ApplyFirmwareOrBuilder
            public boolean hasFirmwareInformation() {
                return ((ApplyFirmware) this.instance).hasFirmwareInformation();
            }

            public Builder mergeFirmwareInformation(FirmwareInformation firmwareInformation) {
                copyOnWrite();
                ((ApplyFirmware) this.instance).mergeFirmwareInformation(firmwareInformation);
                return this;
            }

            public Builder setFirmwareInformation(FirmwareInformation firmwareInformation) {
                copyOnWrite();
                ((ApplyFirmware) this.instance).setFirmwareInformation(firmwareInformation);
                return this;
            }

            public Builder setRestartRequired(boolean z) {
                copyOnWrite();
                ((ApplyFirmware) this.instance).setRestartRequired(z);
                return this;
            }

            private Builder() {
                super(ApplyFirmware.DEFAULT_INSTANCE);
            }

            public Builder setFirmwareInformation(FirmwareInformation.Builder builder) {
                copyOnWrite();
                ((ApplyFirmware) this.instance).setFirmwareInformation(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ApplyFirmware() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFirmwareInformation() {
            this.firmwareInformation_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRestartRequired() {
            this.restartRequired_ = false;
        }

        public static ApplyFirmware getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFirmwareInformation(FirmwareInformation firmwareInformation) {
            FirmwareInformation firmwareInformation2 = this.firmwareInformation_;
            if (firmwareInformation2 != null && firmwareInformation2 != FirmwareInformation.getDefaultInstance()) {
                this.firmwareInformation_ = FirmwareInformation.newBuilder(this.firmwareInformation_).mergeFrom((FirmwareInformation.Builder) firmwareInformation).mo10085buildPartial();
            } else {
                this.firmwareInformation_ = firmwareInformation;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ApplyFirmware parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ApplyFirmware) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ApplyFirmware parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ApplyFirmware> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareInformation(FirmwareInformation firmwareInformation) {
            if (firmwareInformation != null) {
                this.firmwareInformation_ = firmwareInformation;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRestartRequired(boolean z) {
            this.restartRequired_ = z;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ApplyFirmware applyFirmware = (ApplyFirmware) obj2;
                    this.firmwareInformation_ = (FirmwareInformation) visitor.visitMessage(this.firmwareInformation_, applyFirmware.firmwareInformation_);
                    boolean z = this.restartRequired_;
                    boolean z2 = applyFirmware.restartRequired_;
                    this.restartRequired_ = visitor.visitBoolean(z, z, z2, z2);
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
                    boolean z3 = false;
                    while (!z3) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    FirmwareInformation.Builder mo10081toBuilder = this.firmwareInformation_ != null ? this.firmwareInformation_.mo10081toBuilder() : null;
                                    this.firmwareInformation_ = (FirmwareInformation) codedInputStream.readMessage(FirmwareInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((FirmwareInformation.Builder) this.firmwareInformation_);
                                        this.firmwareInformation_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.restartRequired_ = codedInputStream.readBool();
                                }
                            }
                            z3 = true;
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
                    return new ApplyFirmware();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ApplyFirmware.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ApplyFirmwareOrBuilder
        public FirmwareInformation getFirmwareInformation() {
            FirmwareInformation firmwareInformation = this.firmwareInformation_;
            return firmwareInformation == null ? FirmwareInformation.getDefaultInstance() : firmwareInformation;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ApplyFirmwareOrBuilder
        public boolean getRestartRequired() {
            return this.restartRequired_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.firmwareInformation_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getFirmwareInformation());
            }
            boolean z = this.restartRequired_;
            if (z) {
                i2 += CodedOutputStream.computeBoolSize(2, z);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ApplyFirmwareOrBuilder
        public boolean hasFirmwareInformation() {
            return this.firmwareInformation_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.firmwareInformation_ != null) {
                codedOutputStream.writeMessage(1, getFirmwareInformation());
            }
            boolean z = this.restartRequired_;
            if (z) {
                codedOutputStream.writeBool(2, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ApplyFirmware applyFirmware) {
            return DEFAULT_INSTANCE.createBuilder(applyFirmware);
        }

        public static ApplyFirmware parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ApplyFirmware) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ApplyFirmware parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ApplyFirmware parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareInformation(FirmwareInformation.Builder builder) {
            this.firmwareInformation_ = builder.mo10084build();
        }

        public static ApplyFirmware parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ApplyFirmware parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ApplyFirmware parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ApplyFirmware parseFrom(InputStream inputStream) throws IOException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ApplyFirmware parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ApplyFirmware parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ApplyFirmware parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ApplyFirmware) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ApplyFirmwareOrBuilder extends MessageLiteOrBuilder {
        FirmwareInformation getFirmwareInformation();

        boolean getRestartRequired();

        boolean hasFirmwareInformation();
    }

    /* loaded from: classes6.dex */
    public static final class ArtifactFilter extends GeneratedMessageLite<ArtifactFilter, Builder> implements ArtifactFilterOrBuilder {
        public static final int ARTIFACT_FILTER_FIELD_NUMBER = 2;
        public static final int ARTIFACT_NAME_FIELD_NUMBER = 1;
        private static final ArtifactFilter DEFAULT_INSTANCE = new ArtifactFilter();
        private static volatile Parser<ArtifactFilter> PARSER;
        private String artifactName_ = "";
        private String artifactFilter_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ArtifactFilter, Builder> implements ArtifactFilterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearArtifactFilter() {
                copyOnWrite();
                ((ArtifactFilter) this.instance).clearArtifactFilter();
                return this;
            }

            public Builder clearArtifactName() {
                copyOnWrite();
                ((ArtifactFilter) this.instance).clearArtifactName();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactFilterOrBuilder
            public String getArtifactFilter() {
                return ((ArtifactFilter) this.instance).getArtifactFilter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactFilterOrBuilder
            public ByteString getArtifactFilterBytes() {
                return ((ArtifactFilter) this.instance).getArtifactFilterBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactFilterOrBuilder
            public String getArtifactName() {
                return ((ArtifactFilter) this.instance).getArtifactName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactFilterOrBuilder
            public ByteString getArtifactNameBytes() {
                return ((ArtifactFilter) this.instance).getArtifactNameBytes();
            }

            public Builder setArtifactFilter(String str) {
                copyOnWrite();
                ((ArtifactFilter) this.instance).setArtifactFilter(str);
                return this;
            }

            public Builder setArtifactFilterBytes(ByteString byteString) {
                copyOnWrite();
                ((ArtifactFilter) this.instance).setArtifactFilterBytes(byteString);
                return this;
            }

            public Builder setArtifactName(String str) {
                copyOnWrite();
                ((ArtifactFilter) this.instance).setArtifactName(str);
                return this;
            }

            public Builder setArtifactNameBytes(ByteString byteString) {
                copyOnWrite();
                ((ArtifactFilter) this.instance).setArtifactNameBytes(byteString);
                return this;
            }

            private Builder() {
                super(ArtifactFilter.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ArtifactFilter() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactFilter() {
            this.artifactFilter_ = getDefaultInstance().getArtifactFilter();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactName() {
            this.artifactName_ = getDefaultInstance().getArtifactName();
        }

        public static ArtifactFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ArtifactFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ArtifactFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ArtifactFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ArtifactFilter> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactFilter(String str) {
            if (str != null) {
                this.artifactFilter_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactFilterBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.artifactFilter_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactName(String str) {
            if (str != null) {
                this.artifactName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.artifactName_ = byteString.toStringUtf8();
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
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ArtifactFilter artifactFilter = (ArtifactFilter) obj2;
                    this.artifactName_ = visitor.visitString(!this.artifactName_.isEmpty(), this.artifactName_, !artifactFilter.artifactName_.isEmpty(), artifactFilter.artifactName_);
                    this.artifactFilter_ = visitor.visitString(!this.artifactFilter_.isEmpty(), this.artifactFilter_, true ^ artifactFilter.artifactFilter_.isEmpty(), artifactFilter.artifactFilter_);
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
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.artifactName_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.artifactFilter_ = codedInputStream.readStringRequireUtf8();
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
                    return new ArtifactFilter();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ArtifactFilter.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactFilterOrBuilder
        public String getArtifactFilter() {
            return this.artifactFilter_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactFilterOrBuilder
        public ByteString getArtifactFilterBytes() {
            return ByteString.copyFromUtf8(this.artifactFilter_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactFilterOrBuilder
        public String getArtifactName() {
            return this.artifactName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactFilterOrBuilder
        public ByteString getArtifactNameBytes() {
            return ByteString.copyFromUtf8(this.artifactName_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.artifactName_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getArtifactName());
            }
            if (!this.artifactFilter_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getArtifactFilter());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.artifactName_.isEmpty()) {
                codedOutputStream.writeString(1, getArtifactName());
            }
            if (!this.artifactFilter_.isEmpty()) {
                codedOutputStream.writeString(2, getArtifactFilter());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ArtifactFilter artifactFilter) {
            return DEFAULT_INSTANCE.createBuilder(artifactFilter);
        }

        public static ArtifactFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ArtifactFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ArtifactFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ArtifactFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ArtifactFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ArtifactFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ArtifactFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ArtifactFilter parseFrom(InputStream inputStream) throws IOException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ArtifactFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ArtifactFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ArtifactFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ArtifactFilterOrBuilder extends MessageLiteOrBuilder {
        String getArtifactFilter();

        ByteString getArtifactFilterBytes();

        String getArtifactName();

        ByteString getArtifactNameBytes();
    }

    /* loaded from: classes6.dex */
    public static final class ArtifactList extends GeneratedMessageLite<ArtifactList, Builder> implements ArtifactListOrBuilder {
        public static final int ARTIFACT_NAME_FIELD_NUMBER = 1;
        private static final ArtifactList DEFAULT_INSTANCE = new ArtifactList();
        private static volatile Parser<ArtifactList> PARSER;
        private Internal.ProtobufList<String> artifactName_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ArtifactList, Builder> implements ArtifactListOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllArtifactName(Iterable<String> iterable) {
                copyOnWrite();
                ((ArtifactList) this.instance).addAllArtifactName(iterable);
                return this;
            }

            public Builder addArtifactName(String str) {
                copyOnWrite();
                ((ArtifactList) this.instance).addArtifactName(str);
                return this;
            }

            public Builder addArtifactNameBytes(ByteString byteString) {
                copyOnWrite();
                ((ArtifactList) this.instance).addArtifactNameBytes(byteString);
                return this;
            }

            public Builder clearArtifactName() {
                copyOnWrite();
                ((ArtifactList) this.instance).clearArtifactName();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactListOrBuilder
            public String getArtifactName(int i) {
                return ((ArtifactList) this.instance).getArtifactName(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactListOrBuilder
            public ByteString getArtifactNameBytes(int i) {
                return ((ArtifactList) this.instance).getArtifactNameBytes(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactListOrBuilder
            public int getArtifactNameCount() {
                return ((ArtifactList) this.instance).getArtifactNameCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactListOrBuilder
            public List<String> getArtifactNameList() {
                return Collections.unmodifiableList(((ArtifactList) this.instance).getArtifactNameList());
            }

            public Builder setArtifactName(int i, String str) {
                copyOnWrite();
                ((ArtifactList) this.instance).setArtifactName(i, str);
                return this;
            }

            private Builder() {
                super(ArtifactList.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ArtifactList() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllArtifactName(Iterable<String> iterable) {
            ensureArtifactNameIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.artifactName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addArtifactName(String str) {
            if (str != null) {
                ensureArtifactNameIsMutable();
                this.artifactName_.add(str);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addArtifactNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                ensureArtifactNameIsMutable();
                this.artifactName_.add(byteString.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactName() {
            this.artifactName_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureArtifactNameIsMutable() {
            if (!this.artifactName_.isModifiable()) {
                this.artifactName_ = GeneratedMessageLite.mutableCopy(this.artifactName_);
            }
        }

        public static ArtifactList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ArtifactList parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ArtifactList) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ArtifactList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ArtifactList> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactName(int i, String str) {
            if (str != null) {
                ensureArtifactNameIsMutable();
                this.artifactName_.set(i, str);
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
                    this.artifactName_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.artifactName_, ((ArtifactList) obj2).artifactName_);
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
                            if (readTag != 0) {
                                if (readTag != 10) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    if (!this.artifactName_.isModifiable()) {
                                        this.artifactName_ = GeneratedMessageLite.mutableCopy(this.artifactName_);
                                    }
                                    this.artifactName_.add(readStringRequireUtf8);
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
                    this.artifactName_.makeImmutable();
                    return null;
                case 6:
                    return new ArtifactList();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ArtifactList.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactListOrBuilder
        public String getArtifactName(int i) {
            return this.artifactName_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactListOrBuilder
        public ByteString getArtifactNameBytes(int i) {
            return ByteString.copyFromUtf8(this.artifactName_.get(i));
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactListOrBuilder
        public int getArtifactNameCount() {
            return this.artifactName_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactListOrBuilder
        public List<String> getArtifactNameList() {
            return this.artifactName_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.artifactName_.size(); i3++) {
                i2 += CodedOutputStream.computeStringSizeNoTag(this.artifactName_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + (getArtifactNameList().size() * 1) + 0 + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.artifactName_.size(); i++) {
                codedOutputStream.writeString(1, this.artifactName_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ArtifactList artifactList) {
            return DEFAULT_INSTANCE.createBuilder(artifactList);
        }

        public static ArtifactList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ArtifactList) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ArtifactList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ArtifactList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ArtifactList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ArtifactList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ArtifactList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ArtifactList parseFrom(InputStream inputStream) throws IOException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ArtifactList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ArtifactList parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ArtifactList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ArtifactList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ArtifactListOrBuilder extends MessageLiteOrBuilder {
        String getArtifactName(int i);

        ByteString getArtifactNameBytes(int i);

        int getArtifactNameCount();

        List<String> getArtifactNameList();
    }

    /* loaded from: classes6.dex */
    public enum ArtifactRequestReason implements Internal.EnumLite {
        FIRMWARE_UPDATE(0),
        WAKEWORD_CHANGE(1),
        LOCALE_CHANGE(2),
        I18N_REQUEST(3),
        UNRECOGNIZED(-1);
        
        public static final int FIRMWARE_UPDATE_VALUE = 0;
        public static final int I18N_REQUEST_VALUE = 3;
        public static final int LOCALE_CHANGE_VALUE = 2;
        public static final int WAKEWORD_CHANGE_VALUE = 1;
        private static final Internal.EnumLiteMap<ArtifactRequestReason> internalValueMap = new Internal.EnumLiteMap<ArtifactRequestReason>() { // from class: com.amazon.alexa.accessory.protocol.Firmware.ArtifactRequestReason.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public ArtifactRequestReason mo9850findValueByNumber(int i) {
                return ArtifactRequestReason.forNumber(i);
            }
        };
        private final int value;

        ArtifactRequestReason(int i) {
            this.value = i;
        }

        public static ArtifactRequestReason forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return WAKEWORD_CHANGE;
                }
                if (i == 2) {
                    return LOCALE_CHANGE;
                }
                if (i == 3) {
                    return I18N_REQUEST;
                }
                return null;
            }
            return FIRMWARE_UPDATE;
        }

        public static Internal.EnumLiteMap<ArtifactRequestReason> internalGetValueMap() {
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
        public static ArtifactRequestReason valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class ArtifactUpdatePreference extends GeneratedMessageLite<ArtifactUpdatePreference, Builder> implements ArtifactUpdatePreferenceOrBuilder {
        public static final int ARTIFACT_NAME_FIELD_NUMBER = 1;
        private static final ArtifactUpdatePreference DEFAULT_INSTANCE = new ArtifactUpdatePreference();
        private static volatile Parser<ArtifactUpdatePreference> PARSER = null;
        public static final int UPDATE_REQUIRED_FIELD_NUMBER = 2;
        private String artifactName_ = "";
        private boolean updateRequired_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ArtifactUpdatePreference, Builder> implements ArtifactUpdatePreferenceOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearArtifactName() {
                copyOnWrite();
                ((ArtifactUpdatePreference) this.instance).clearArtifactName();
                return this;
            }

            public Builder clearUpdateRequired() {
                copyOnWrite();
                ((ArtifactUpdatePreference) this.instance).clearUpdateRequired();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactUpdatePreferenceOrBuilder
            public String getArtifactName() {
                return ((ArtifactUpdatePreference) this.instance).getArtifactName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactUpdatePreferenceOrBuilder
            public ByteString getArtifactNameBytes() {
                return ((ArtifactUpdatePreference) this.instance).getArtifactNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactUpdatePreferenceOrBuilder
            public boolean getUpdateRequired() {
                return ((ArtifactUpdatePreference) this.instance).getUpdateRequired();
            }

            public Builder setArtifactName(String str) {
                copyOnWrite();
                ((ArtifactUpdatePreference) this.instance).setArtifactName(str);
                return this;
            }

            public Builder setArtifactNameBytes(ByteString byteString) {
                copyOnWrite();
                ((ArtifactUpdatePreference) this.instance).setArtifactNameBytes(byteString);
                return this;
            }

            public Builder setUpdateRequired(boolean z) {
                copyOnWrite();
                ((ArtifactUpdatePreference) this.instance).setUpdateRequired(z);
                return this;
            }

            private Builder() {
                super(ArtifactUpdatePreference.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ArtifactUpdatePreference() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactName() {
            this.artifactName_ = getDefaultInstance().getArtifactName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateRequired() {
            this.updateRequired_ = false;
        }

        public static ArtifactUpdatePreference getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ArtifactUpdatePreference parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ArtifactUpdatePreference parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ArtifactUpdatePreference> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactName(String str) {
            if (str != null) {
                this.artifactName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.artifactName_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateRequired(boolean z) {
            this.updateRequired_ = z;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ArtifactUpdatePreference artifactUpdatePreference = (ArtifactUpdatePreference) obj2;
                    this.artifactName_ = visitor.visitString(!this.artifactName_.isEmpty(), this.artifactName_, true ^ artifactUpdatePreference.artifactName_.isEmpty(), artifactUpdatePreference.artifactName_);
                    boolean z = this.updateRequired_;
                    boolean z2 = artifactUpdatePreference.updateRequired_;
                    this.updateRequired_ = visitor.visitBoolean(z, z, z2, z2);
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
                    boolean z3 = false;
                    while (!z3) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.artifactName_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.updateRequired_ = codedInputStream.readBool();
                                }
                            }
                            z3 = true;
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
                    return new ArtifactUpdatePreference();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ArtifactUpdatePreference.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactUpdatePreferenceOrBuilder
        public String getArtifactName() {
            return this.artifactName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactUpdatePreferenceOrBuilder
        public ByteString getArtifactNameBytes() {
            return ByteString.copyFromUtf8(this.artifactName_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.artifactName_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getArtifactName());
            }
            boolean z = this.updateRequired_;
            if (z) {
                i2 += CodedOutputStream.computeBoolSize(2, z);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ArtifactUpdatePreferenceOrBuilder
        public boolean getUpdateRequired() {
            return this.updateRequired_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.artifactName_.isEmpty()) {
                codedOutputStream.writeString(1, getArtifactName());
            }
            boolean z = this.updateRequired_;
            if (z) {
                codedOutputStream.writeBool(2, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ArtifactUpdatePreference artifactUpdatePreference) {
            return DEFAULT_INSTANCE.createBuilder(artifactUpdatePreference);
        }

        public static ArtifactUpdatePreference parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ArtifactUpdatePreference parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ArtifactUpdatePreference parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ArtifactUpdatePreference parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ArtifactUpdatePreference parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ArtifactUpdatePreference parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ArtifactUpdatePreference parseFrom(InputStream inputStream) throws IOException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ArtifactUpdatePreference parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ArtifactUpdatePreference parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ArtifactUpdatePreference parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ArtifactUpdatePreferenceOrBuilder extends MessageLiteOrBuilder {
        String getArtifactName();

        ByteString getArtifactNameBytes();

        boolean getUpdateRequired();
    }

    /* loaded from: classes6.dex */
    public enum ChecksumAlgorithm implements Internal.EnumLite {
        MD5(0),
        UNRECOGNIZED(-1);
        
        public static final int MD5_VALUE = 0;
        private static final Internal.EnumLiteMap<ChecksumAlgorithm> internalValueMap = new Internal.EnumLiteMap<ChecksumAlgorithm>() { // from class: com.amazon.alexa.accessory.protocol.Firmware.ChecksumAlgorithm.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public ChecksumAlgorithm mo9850findValueByNumber(int i) {
                return ChecksumAlgorithm.forNumber(i);
            }
        };
        private final int value;

        ChecksumAlgorithm(int i) {
            this.value = i;
        }

        public static ChecksumAlgorithm forNumber(int i) {
            if (i != 0) {
                return null;
            }
            return MD5;
        }

        public static Internal.EnumLiteMap<ChecksumAlgorithm> internalGetValueMap() {
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
        public static ChecksumAlgorithm valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class FirmwareComponent extends GeneratedMessageLite<FirmwareComponent, Builder> implements FirmwareComponentOrBuilder {
        private static final FirmwareComponent DEFAULT_INSTANCE = new FirmwareComponent();
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser<FirmwareComponent> PARSER = null;
        public static final int SIGNATURE_FIELD_NUMBER = 4;
        public static final int SIZE_FIELD_NUMBER = 3;
        public static final int VERSION_FIELD_NUMBER = 1;
        private String name_ = "";
        private String signature_ = "";
        private int size_;
        private int version_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FirmwareComponent, Builder> implements FirmwareComponentOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearName() {
                copyOnWrite();
                ((FirmwareComponent) this.instance).clearName();
                return this;
            }

            public Builder clearSignature() {
                copyOnWrite();
                ((FirmwareComponent) this.instance).clearSignature();
                return this;
            }

            public Builder clearSize() {
                copyOnWrite();
                ((FirmwareComponent) this.instance).clearSize();
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((FirmwareComponent) this.instance).clearVersion();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
            public String getName() {
                return ((FirmwareComponent) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
            public ByteString getNameBytes() {
                return ((FirmwareComponent) this.instance).getNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
            public String getSignature() {
                return ((FirmwareComponent) this.instance).getSignature();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
            public ByteString getSignatureBytes() {
                return ((FirmwareComponent) this.instance).getSignatureBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
            public int getSize() {
                return ((FirmwareComponent) this.instance).getSize();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
            public int getVersion() {
                return ((FirmwareComponent) this.instance).getVersion();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((FirmwareComponent) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((FirmwareComponent) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setSignature(String str) {
                copyOnWrite();
                ((FirmwareComponent) this.instance).setSignature(str);
                return this;
            }

            public Builder setSignatureBytes(ByteString byteString) {
                copyOnWrite();
                ((FirmwareComponent) this.instance).setSignatureBytes(byteString);
                return this;
            }

            public Builder setSize(int i) {
                copyOnWrite();
                ((FirmwareComponent) this.instance).setSize(i);
                return this;
            }

            public Builder setVersion(int i) {
                copyOnWrite();
                ((FirmwareComponent) this.instance).setVersion(i);
                return this;
            }

            private Builder() {
                super(FirmwareComponent.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private FirmwareComponent() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSignature() {
            this.signature_ = getDefaultInstance().getSignature();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSize() {
            this.size_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersion() {
            this.version_ = 0;
        }

        public static FirmwareComponent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FirmwareComponent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FirmwareComponent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirmwareComponent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FirmwareComponent> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
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
        public void setSignature(String str) {
            if (str != null) {
                this.signature_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSignatureBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.signature_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSize(int i) {
            this.size_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersion(int i) {
            this.version_ = i;
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
                    FirmwareComponent firmwareComponent = (FirmwareComponent) obj2;
                    this.version_ = visitor.visitInt(this.version_ != 0, this.version_, firmwareComponent.version_ != 0, firmwareComponent.version_);
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !firmwareComponent.name_.isEmpty(), firmwareComponent.name_);
                    boolean z2 = this.size_ != 0;
                    int i = this.size_;
                    if (firmwareComponent.size_ != 0) {
                        z = true;
                    }
                    this.size_ = visitor.visitInt(z2, i, z, firmwareComponent.size_);
                    this.signature_ = visitor.visitString(!this.signature_.isEmpty(), this.signature_, !firmwareComponent.signature_.isEmpty(), firmwareComponent.signature_);
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
                                    this.version_ = codedInputStream.readUInt32();
                                } else if (readTag == 18) {
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 24) {
                                    this.size_ = codedInputStream.readUInt32();
                                } else if (readTag != 34) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.signature_ = codedInputStream.readStringRequireUtf8();
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
                    return new FirmwareComponent();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (FirmwareComponent.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
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
            int i3 = this.version_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (!this.name_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getName());
            }
            int i4 = this.size_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i4);
            }
            if (!this.signature_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(4, getSignature());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
        public String getSignature() {
            return this.signature_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
        public ByteString getSignatureBytes() {
            return ByteString.copyFromUtf8(this.signature_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
        public int getSize() {
            return this.size_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareComponentOrBuilder
        public int getVersion() {
            return this.version_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.version_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(2, getName());
            }
            int i2 = this.size_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(3, i2);
            }
            if (!this.signature_.isEmpty()) {
                codedOutputStream.writeString(4, getSignature());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(FirmwareComponent firmwareComponent) {
            return DEFAULT_INSTANCE.createBuilder(firmwareComponent);
        }

        public static FirmwareComponent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareComponent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirmwareComponent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FirmwareComponent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FirmwareComponent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FirmwareComponent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FirmwareComponent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FirmwareComponent parseFrom(InputStream inputStream) throws IOException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirmwareComponent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirmwareComponent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FirmwareComponent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface FirmwareComponentOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        String getSignature();

        ByteString getSignatureBytes();

        int getSize();

        int getVersion();
    }

    /* loaded from: classes6.dex */
    public static final class FirmwareInformation extends GeneratedMessageLite<FirmwareInformation, Builder> implements FirmwareInformationOrBuilder {
        public static final int COMPONENTS_FIELD_NUMBER = 3;
        private static final FirmwareInformation DEFAULT_INSTANCE = new FirmwareInformation();
        public static final int DEVICE_ID_FIELD_NUMBER = 6;
        public static final int LOCALE_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser<FirmwareInformation> PARSER = null;
        public static final int SEGMENT_SIZE_FIELD_NUMBER = 7;
        public static final int VERSION_FIELD_NUMBER = 1;
        public static final int VERSION_NAME_FIELD_NUMBER = 5;
        private int bitField0_;
        private int deviceId_;
        private int segmentSize_;
        private int version_;
        private String name_ = "";
        private Internal.ProtobufList<FirmwareComponent> components_ = GeneratedMessageLite.emptyProtobufList();
        private String locale_ = "";
        private String versionName_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FirmwareInformation, Builder> implements FirmwareInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllComponents(Iterable<? extends FirmwareComponent> iterable) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).addAllComponents(iterable);
                return this;
            }

            public Builder addComponents(FirmwareComponent firmwareComponent) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).addComponents(firmwareComponent);
                return this;
            }

            public Builder clearComponents() {
                copyOnWrite();
                ((FirmwareInformation) this.instance).clearComponents();
                return this;
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((FirmwareInformation) this.instance).clearDeviceId();
                return this;
            }

            public Builder clearLocale() {
                copyOnWrite();
                ((FirmwareInformation) this.instance).clearLocale();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((FirmwareInformation) this.instance).clearName();
                return this;
            }

            public Builder clearSegmentSize() {
                copyOnWrite();
                ((FirmwareInformation) this.instance).clearSegmentSize();
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((FirmwareInformation) this.instance).clearVersion();
                return this;
            }

            public Builder clearVersionName() {
                copyOnWrite();
                ((FirmwareInformation) this.instance).clearVersionName();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public FirmwareComponent getComponents(int i) {
                return ((FirmwareInformation) this.instance).getComponents(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public int getComponentsCount() {
                return ((FirmwareInformation) this.instance).getComponentsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public List<FirmwareComponent> getComponentsList() {
                return Collections.unmodifiableList(((FirmwareInformation) this.instance).getComponentsList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public int getDeviceId() {
                return ((FirmwareInformation) this.instance).getDeviceId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public String getLocale() {
                return ((FirmwareInformation) this.instance).getLocale();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public ByteString getLocaleBytes() {
                return ((FirmwareInformation) this.instance).getLocaleBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public String getName() {
                return ((FirmwareInformation) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public ByteString getNameBytes() {
                return ((FirmwareInformation) this.instance).getNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public int getSegmentSize() {
                return ((FirmwareInformation) this.instance).getSegmentSize();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public int getVersion() {
                return ((FirmwareInformation) this.instance).getVersion();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public String getVersionName() {
                return ((FirmwareInformation) this.instance).getVersionName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
            public ByteString getVersionNameBytes() {
                return ((FirmwareInformation) this.instance).getVersionNameBytes();
            }

            public Builder removeComponents(int i) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).removeComponents(i);
                return this;
            }

            public Builder setComponents(int i, FirmwareComponent firmwareComponent) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setComponents(i, firmwareComponent);
                return this;
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setDeviceId(i);
                return this;
            }

            public Builder setLocale(String str) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setLocale(str);
                return this;
            }

            public Builder setLocaleBytes(ByteString byteString) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setLocaleBytes(byteString);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setSegmentSize(int i) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setSegmentSize(i);
                return this;
            }

            public Builder setVersion(int i) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setVersion(i);
                return this;
            }

            public Builder setVersionName(String str) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setVersionName(str);
                return this;
            }

            public Builder setVersionNameBytes(ByteString byteString) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setVersionNameBytes(byteString);
                return this;
            }

            private Builder() {
                super(FirmwareInformation.DEFAULT_INSTANCE);
            }

            public Builder addComponents(int i, FirmwareComponent firmwareComponent) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).addComponents(i, firmwareComponent);
                return this;
            }

            public Builder setComponents(int i, FirmwareComponent.Builder builder) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).setComponents(i, builder);
                return this;
            }

            public Builder addComponents(FirmwareComponent.Builder builder) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).addComponents(builder);
                return this;
            }

            public Builder addComponents(int i, FirmwareComponent.Builder builder) {
                copyOnWrite();
                ((FirmwareInformation) this.instance).addComponents(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private FirmwareInformation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllComponents(Iterable<? extends FirmwareComponent> iterable) {
            ensureComponentsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.components_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addComponents(FirmwareComponent firmwareComponent) {
            if (firmwareComponent != null) {
                ensureComponentsIsMutable();
                this.components_.add(firmwareComponent);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearComponents() {
            this.components_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLocale() {
            this.locale_ = getDefaultInstance().getLocale();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegmentSize() {
            this.segmentSize_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersion() {
            this.version_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersionName() {
            this.versionName_ = getDefaultInstance().getVersionName();
        }

        private void ensureComponentsIsMutable() {
            if (!this.components_.isModifiable()) {
                this.components_ = GeneratedMessageLite.mutableCopy(this.components_);
            }
        }

        public static FirmwareInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FirmwareInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FirmwareInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirmwareInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FirmwareInformation> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeComponents(int i) {
            ensureComponentsIsMutable();
            this.components_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setComponents(int i, FirmwareComponent firmwareComponent) {
            if (firmwareComponent != null) {
                ensureComponentsIsMutable();
                this.components_.set(i, firmwareComponent);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceId(int i) {
            this.deviceId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocale(String str) {
            if (str != null) {
                this.locale_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocaleBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.locale_ = byteString.toStringUtf8();
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
        public void setSegmentSize(int i) {
            this.segmentSize_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersion(int i) {
            this.version_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersionName(String str) {
            if (str != null) {
                this.versionName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersionNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.versionName_ = byteString.toStringUtf8();
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
                    FirmwareInformation firmwareInformation = (FirmwareInformation) obj2;
                    this.version_ = visitor.visitInt(this.version_ != 0, this.version_, firmwareInformation.version_ != 0, firmwareInformation.version_);
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !firmwareInformation.name_.isEmpty(), firmwareInformation.name_);
                    this.components_ = visitor.visitList(this.components_, firmwareInformation.components_);
                    this.locale_ = visitor.visitString(!this.locale_.isEmpty(), this.locale_, !firmwareInformation.locale_.isEmpty(), firmwareInformation.locale_);
                    this.versionName_ = visitor.visitString(!this.versionName_.isEmpty(), this.versionName_, !firmwareInformation.versionName_.isEmpty(), firmwareInformation.versionName_);
                    this.deviceId_ = visitor.visitInt(this.deviceId_ != 0, this.deviceId_, firmwareInformation.deviceId_ != 0, firmwareInformation.deviceId_);
                    boolean z2 = this.segmentSize_ != 0;
                    int i = this.segmentSize_;
                    if (firmwareInformation.segmentSize_ != 0) {
                        z = true;
                    }
                    this.segmentSize_ = visitor.visitInt(z2, i, z, firmwareInformation.segmentSize_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= firmwareInformation.bitField0_;
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
                                    this.version_ = codedInputStream.readUInt32();
                                } else if (readTag == 18) {
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 26) {
                                    if (!this.components_.isModifiable()) {
                                        this.components_ = GeneratedMessageLite.mutableCopy(this.components_);
                                    }
                                    this.components_.add(codedInputStream.readMessage(FirmwareComponent.parser(), extensionRegistryLite));
                                } else if (readTag == 34) {
                                    this.locale_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 42) {
                                    this.versionName_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 48) {
                                    this.deviceId_ = codedInputStream.readUInt32();
                                } else if (readTag != 56) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.segmentSize_ = codedInputStream.readUInt32();
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
                    this.components_.makeImmutable();
                    return null;
                case 6:
                    return new FirmwareInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (FirmwareInformation.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public FirmwareComponent getComponents(int i) {
            return this.components_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public int getComponentsCount() {
            return this.components_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public List<FirmwareComponent> getComponentsList() {
            return this.components_;
        }

        public FirmwareComponentOrBuilder getComponentsOrBuilder(int i) {
            return this.components_.get(i);
        }

        public List<? extends FirmwareComponentOrBuilder> getComponentsOrBuilderList() {
            return this.components_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public int getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public String getLocale() {
            return this.locale_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public ByteString getLocaleBytes() {
            return ByteString.copyFromUtf8(this.locale_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public int getSegmentSize() {
            return this.segmentSize_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.version_;
            int computeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) + 0 : 0;
            if (!this.name_.isEmpty()) {
                computeUInt32Size += CodedOutputStream.computeStringSize(2, getName());
            }
            for (int i3 = 0; i3 < this.components_.size(); i3++) {
                computeUInt32Size += CodedOutputStream.computeMessageSize(3, this.components_.get(i3));
            }
            if (!this.locale_.isEmpty()) {
                computeUInt32Size += CodedOutputStream.computeStringSize(4, getLocale());
            }
            if (!this.versionName_.isEmpty()) {
                computeUInt32Size += CodedOutputStream.computeStringSize(5, getVersionName());
            }
            int i4 = this.deviceId_;
            if (i4 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(6, i4);
            }
            int i5 = this.segmentSize_;
            if (i5 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(7, i5);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeUInt32Size;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public int getVersion() {
            return this.version_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public String getVersionName() {
            return this.versionName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformationOrBuilder
        public ByteString getVersionNameBytes() {
            return ByteString.copyFromUtf8(this.versionName_);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.version_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(2, getName());
            }
            for (int i2 = 0; i2 < this.components_.size(); i2++) {
                codedOutputStream.writeMessage(3, this.components_.get(i2));
            }
            if (!this.locale_.isEmpty()) {
                codedOutputStream.writeString(4, getLocale());
            }
            if (!this.versionName_.isEmpty()) {
                codedOutputStream.writeString(5, getVersionName());
            }
            int i3 = this.deviceId_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(6, i3);
            }
            int i4 = this.segmentSize_;
            if (i4 != 0) {
                codedOutputStream.writeUInt32(7, i4);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(FirmwareInformation firmwareInformation) {
            return DEFAULT_INSTANCE.createBuilder(firmwareInformation);
        }

        public static FirmwareInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirmwareInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FirmwareInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addComponents(int i, FirmwareComponent firmwareComponent) {
            if (firmwareComponent != null) {
                ensureComponentsIsMutable();
                this.components_.add(i, firmwareComponent);
                return;
            }
            throw new NullPointerException();
        }

        public static FirmwareInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setComponents(int i, FirmwareComponent.Builder builder) {
            ensureComponentsIsMutable();
            this.components_.set(i, builder.mo10084build());
        }

        public static FirmwareInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FirmwareInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addComponents(FirmwareComponent.Builder builder) {
            ensureComponentsIsMutable();
            this.components_.add(builder.mo10084build());
        }

        public static FirmwareInformation parseFrom(InputStream inputStream) throws IOException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirmwareInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addComponents(int i, FirmwareComponent.Builder builder) {
            ensureComponentsIsMutable();
            this.components_.add(i, builder.mo10084build());
        }

        public static FirmwareInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FirmwareInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface FirmwareInformationOrBuilder extends MessageLiteOrBuilder {
        FirmwareComponent getComponents(int i);

        int getComponentsCount();

        List<FirmwareComponent> getComponentsList();

        int getDeviceId();

        String getLocale();

        ByteString getLocaleBytes();

        String getName();

        ByteString getNameBytes();

        int getSegmentSize();

        int getVersion();

        String getVersionName();

        ByteString getVersionNameBytes();
    }

    /* loaded from: classes6.dex */
    public static final class FirmwareUpdatePreferences extends GeneratedMessageLite<FirmwareUpdatePreferences, Builder> implements FirmwareUpdatePreferencesOrBuilder {
        private static final FirmwareUpdatePreferences DEFAULT_INSTANCE = new FirmwareUpdatePreferences();
        public static final int DESIRED_COMPONENTS_FIELD_NUMBER = 1;
        private static volatile Parser<FirmwareUpdatePreferences> PARSER;
        private Internal.ProtobufList<String> desiredComponents_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FirmwareUpdatePreferences, Builder> implements FirmwareUpdatePreferencesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllDesiredComponents(Iterable<String> iterable) {
                copyOnWrite();
                ((FirmwareUpdatePreferences) this.instance).addAllDesiredComponents(iterable);
                return this;
            }

            public Builder addDesiredComponents(String str) {
                copyOnWrite();
                ((FirmwareUpdatePreferences) this.instance).addDesiredComponents(str);
                return this;
            }

            public Builder addDesiredComponentsBytes(ByteString byteString) {
                copyOnWrite();
                ((FirmwareUpdatePreferences) this.instance).addDesiredComponentsBytes(byteString);
                return this;
            }

            public Builder clearDesiredComponents() {
                copyOnWrite();
                ((FirmwareUpdatePreferences) this.instance).clearDesiredComponents();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareUpdatePreferencesOrBuilder
            public String getDesiredComponents(int i) {
                return ((FirmwareUpdatePreferences) this.instance).getDesiredComponents(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareUpdatePreferencesOrBuilder
            public ByteString getDesiredComponentsBytes(int i) {
                return ((FirmwareUpdatePreferences) this.instance).getDesiredComponentsBytes(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareUpdatePreferencesOrBuilder
            public int getDesiredComponentsCount() {
                return ((FirmwareUpdatePreferences) this.instance).getDesiredComponentsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareUpdatePreferencesOrBuilder
            public List<String> getDesiredComponentsList() {
                return Collections.unmodifiableList(((FirmwareUpdatePreferences) this.instance).getDesiredComponentsList());
            }

            public Builder setDesiredComponents(int i, String str) {
                copyOnWrite();
                ((FirmwareUpdatePreferences) this.instance).setDesiredComponents(i, str);
                return this;
            }

            private Builder() {
                super(FirmwareUpdatePreferences.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private FirmwareUpdatePreferences() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllDesiredComponents(Iterable<String> iterable) {
            ensureDesiredComponentsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.desiredComponents_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addDesiredComponents(String str) {
            if (str != null) {
                ensureDesiredComponentsIsMutable();
                this.desiredComponents_.add(str);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addDesiredComponentsBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                ensureDesiredComponentsIsMutable();
                this.desiredComponents_.add(byteString.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDesiredComponents() {
            this.desiredComponents_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureDesiredComponentsIsMutable() {
            if (!this.desiredComponents_.isModifiable()) {
                this.desiredComponents_ = GeneratedMessageLite.mutableCopy(this.desiredComponents_);
            }
        }

        public static FirmwareUpdatePreferences getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FirmwareUpdatePreferences parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirmwareUpdatePreferences parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FirmwareUpdatePreferences> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDesiredComponents(int i, String str) {
            if (str != null) {
                ensureDesiredComponentsIsMutable();
                this.desiredComponents_.set(i, str);
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
                    this.desiredComponents_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.desiredComponents_, ((FirmwareUpdatePreferences) obj2).desiredComponents_);
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
                            if (readTag != 0) {
                                if (readTag != 10) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    if (!this.desiredComponents_.isModifiable()) {
                                        this.desiredComponents_ = GeneratedMessageLite.mutableCopy(this.desiredComponents_);
                                    }
                                    this.desiredComponents_.add(readStringRequireUtf8);
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
                    this.desiredComponents_.makeImmutable();
                    return null;
                case 6:
                    return new FirmwareUpdatePreferences();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (FirmwareUpdatePreferences.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareUpdatePreferencesOrBuilder
        public String getDesiredComponents(int i) {
            return this.desiredComponents_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareUpdatePreferencesOrBuilder
        public ByteString getDesiredComponentsBytes(int i) {
            return ByteString.copyFromUtf8(this.desiredComponents_.get(i));
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareUpdatePreferencesOrBuilder
        public int getDesiredComponentsCount() {
            return this.desiredComponents_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.FirmwareUpdatePreferencesOrBuilder
        public List<String> getDesiredComponentsList() {
            return this.desiredComponents_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.desiredComponents_.size(); i3++) {
                i2 += CodedOutputStream.computeStringSizeNoTag(this.desiredComponents_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + (getDesiredComponentsList().size() * 1) + 0 + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.desiredComponents_.size(); i++) {
                codedOutputStream.writeString(1, this.desiredComponents_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(FirmwareUpdatePreferences firmwareUpdatePreferences) {
            return DEFAULT_INSTANCE.createBuilder(firmwareUpdatePreferences);
        }

        public static FirmwareUpdatePreferences parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirmwareUpdatePreferences parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FirmwareUpdatePreferences parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FirmwareUpdatePreferences parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FirmwareUpdatePreferences parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FirmwareUpdatePreferences parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FirmwareUpdatePreferences parseFrom(InputStream inputStream) throws IOException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirmwareUpdatePreferences parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirmwareUpdatePreferences parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FirmwareUpdatePreferences parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface FirmwareUpdatePreferencesOrBuilder extends MessageLiteOrBuilder {
        String getDesiredComponents(int i);

        ByteString getDesiredComponentsBytes(int i);

        int getDesiredComponentsCount();

        List<String> getDesiredComponentsList();
    }

    /* loaded from: classes6.dex */
    public static final class FirmwareUpdateUnavailable extends GeneratedMessageLite<FirmwareUpdateUnavailable, Builder> implements FirmwareUpdateUnavailableOrBuilder {
        private static final FirmwareUpdateUnavailable DEFAULT_INSTANCE = new FirmwareUpdateUnavailable();
        private static volatile Parser<FirmwareUpdateUnavailable> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FirmwareUpdateUnavailable, Builder> implements FirmwareUpdateUnavailableOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(FirmwareUpdateUnavailable.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private FirmwareUpdateUnavailable() {
        }

        public static FirmwareUpdateUnavailable getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FirmwareUpdateUnavailable parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirmwareUpdateUnavailable parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FirmwareUpdateUnavailable> parser() {
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
                    FirmwareUpdateUnavailable firmwareUpdateUnavailable = (FirmwareUpdateUnavailable) obj2;
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
                    return new FirmwareUpdateUnavailable();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (FirmwareUpdateUnavailable.class) {
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

        public static Builder newBuilder(FirmwareUpdateUnavailable firmwareUpdateUnavailable) {
            return DEFAULT_INSTANCE.createBuilder(firmwareUpdateUnavailable);
        }

        public static FirmwareUpdateUnavailable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirmwareUpdateUnavailable parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FirmwareUpdateUnavailable parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FirmwareUpdateUnavailable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FirmwareUpdateUnavailable parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FirmwareUpdateUnavailable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FirmwareUpdateUnavailable parseFrom(InputStream inputStream) throws IOException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirmwareUpdateUnavailable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirmwareUpdateUnavailable parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FirmwareUpdateUnavailable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirmwareUpdateUnavailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface FirmwareUpdateUnavailableOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class GetArtifactFilter extends GeneratedMessageLite<GetArtifactFilter, Builder> implements GetArtifactFilterOrBuilder {
        public static final int ARTIFACT_NAME_FIELD_NUMBER = 1;
        private static final GetArtifactFilter DEFAULT_INSTANCE = new GetArtifactFilter();
        private static volatile Parser<GetArtifactFilter> PARSER;
        private String artifactName_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetArtifactFilter, Builder> implements GetArtifactFilterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearArtifactName() {
                copyOnWrite();
                ((GetArtifactFilter) this.instance).clearArtifactName();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactFilterOrBuilder
            public String getArtifactName() {
                return ((GetArtifactFilter) this.instance).getArtifactName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactFilterOrBuilder
            public ByteString getArtifactNameBytes() {
                return ((GetArtifactFilter) this.instance).getArtifactNameBytes();
            }

            public Builder setArtifactName(String str) {
                copyOnWrite();
                ((GetArtifactFilter) this.instance).setArtifactName(str);
                return this;
            }

            public Builder setArtifactNameBytes(ByteString byteString) {
                copyOnWrite();
                ((GetArtifactFilter) this.instance).setArtifactNameBytes(byteString);
                return this;
            }

            private Builder() {
                super(GetArtifactFilter.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetArtifactFilter() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactName() {
            this.artifactName_ = getDefaultInstance().getArtifactName();
        }

        public static GetArtifactFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetArtifactFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetArtifactFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetArtifactFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetArtifactFilter> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactName(String str) {
            if (str != null) {
                this.artifactName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.artifactName_ = byteString.toStringUtf8();
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
                    GetArtifactFilter getArtifactFilter = (GetArtifactFilter) obj2;
                    this.artifactName_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.artifactName_.isEmpty(), this.artifactName_, true ^ getArtifactFilter.artifactName_.isEmpty(), getArtifactFilter.artifactName_);
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
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 10) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.artifactName_ = codedInputStream.readStringRequireUtf8();
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
                    return new GetArtifactFilter();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetArtifactFilter.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactFilterOrBuilder
        public String getArtifactName() {
            return this.artifactName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactFilterOrBuilder
        public ByteString getArtifactNameBytes() {
            return ByteString.copyFromUtf8(this.artifactName_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.artifactName_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getArtifactName());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.artifactName_.isEmpty()) {
                codedOutputStream.writeString(1, getArtifactName());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetArtifactFilter getArtifactFilter) {
            return DEFAULT_INSTANCE.createBuilder(getArtifactFilter);
        }

        public static GetArtifactFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetArtifactFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetArtifactFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetArtifactFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetArtifactFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetArtifactFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetArtifactFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetArtifactFilter parseFrom(InputStream inputStream) throws IOException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetArtifactFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetArtifactFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetArtifactFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetArtifactFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetArtifactFilterOrBuilder extends MessageLiteOrBuilder {
        String getArtifactName();

        ByteString getArtifactNameBytes();
    }

    /* loaded from: classes6.dex */
    public static final class GetArtifactUpdatePreference extends GeneratedMessageLite<GetArtifactUpdatePreference, Builder> implements GetArtifactUpdatePreferenceOrBuilder {
        public static final int ARTIFACT_IDENTIFIER_FIELD_NUMBER = 4;
        public static final int ARTIFACT_KEY_FIELD_NUMBER = 3;
        public static final int ARTIFACT_NAME_FIELD_NUMBER = 1;
        public static final int ARTIFACT_SIZE_FIELD_NUMBER = 5;
        public static final int ARTIFACT_TYPE_FIELD_NUMBER = 2;
        public static final int CHECKSUM_ALGORITHM_FIELD_NUMBER = 6;
        public static final int CHECKSUM_FIELD_NUMBER = 7;
        private static final GetArtifactUpdatePreference DEFAULT_INSTANCE = new GetArtifactUpdatePreference();
        private static volatile Parser<GetArtifactUpdatePreference> PARSER;
        private int artifactSize_;
        private int checksumAlgorithm_;
        private String artifactName_ = "";
        private String artifactType_ = "";
        private String artifactKey_ = "";
        private String artifactIdentifier_ = "";
        private ByteString checksum_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetArtifactUpdatePreference, Builder> implements GetArtifactUpdatePreferenceOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearArtifactIdentifier() {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).clearArtifactIdentifier();
                return this;
            }

            public Builder clearArtifactKey() {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).clearArtifactKey();
                return this;
            }

            public Builder clearArtifactName() {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).clearArtifactName();
                return this;
            }

            public Builder clearArtifactSize() {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).clearArtifactSize();
                return this;
            }

            public Builder clearArtifactType() {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).clearArtifactType();
                return this;
            }

            public Builder clearChecksum() {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).clearChecksum();
                return this;
            }

            public Builder clearChecksumAlgorithm() {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).clearChecksumAlgorithm();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public String getArtifactIdentifier() {
                return ((GetArtifactUpdatePreference) this.instance).getArtifactIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public ByteString getArtifactIdentifierBytes() {
                return ((GetArtifactUpdatePreference) this.instance).getArtifactIdentifierBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public String getArtifactKey() {
                return ((GetArtifactUpdatePreference) this.instance).getArtifactKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public ByteString getArtifactKeyBytes() {
                return ((GetArtifactUpdatePreference) this.instance).getArtifactKeyBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public String getArtifactName() {
                return ((GetArtifactUpdatePreference) this.instance).getArtifactName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public ByteString getArtifactNameBytes() {
                return ((GetArtifactUpdatePreference) this.instance).getArtifactNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public int getArtifactSize() {
                return ((GetArtifactUpdatePreference) this.instance).getArtifactSize();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public String getArtifactType() {
                return ((GetArtifactUpdatePreference) this.instance).getArtifactType();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public ByteString getArtifactTypeBytes() {
                return ((GetArtifactUpdatePreference) this.instance).getArtifactTypeBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public ByteString getChecksum() {
                return ((GetArtifactUpdatePreference) this.instance).getChecksum();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public ChecksumAlgorithm getChecksumAlgorithm() {
                return ((GetArtifactUpdatePreference) this.instance).getChecksumAlgorithm();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
            public int getChecksumAlgorithmValue() {
                return ((GetArtifactUpdatePreference) this.instance).getChecksumAlgorithmValue();
            }

            public Builder setArtifactIdentifier(String str) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setArtifactIdentifier(str);
                return this;
            }

            public Builder setArtifactIdentifierBytes(ByteString byteString) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setArtifactIdentifierBytes(byteString);
                return this;
            }

            public Builder setArtifactKey(String str) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setArtifactKey(str);
                return this;
            }

            public Builder setArtifactKeyBytes(ByteString byteString) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setArtifactKeyBytes(byteString);
                return this;
            }

            public Builder setArtifactName(String str) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setArtifactName(str);
                return this;
            }

            public Builder setArtifactNameBytes(ByteString byteString) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setArtifactNameBytes(byteString);
                return this;
            }

            public Builder setArtifactSize(int i) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setArtifactSize(i);
                return this;
            }

            public Builder setArtifactType(String str) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setArtifactType(str);
                return this;
            }

            public Builder setArtifactTypeBytes(ByteString byteString) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setArtifactTypeBytes(byteString);
                return this;
            }

            public Builder setChecksum(ByteString byteString) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setChecksum(byteString);
                return this;
            }

            public Builder setChecksumAlgorithm(ChecksumAlgorithm checksumAlgorithm) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setChecksumAlgorithm(checksumAlgorithm);
                return this;
            }

            public Builder setChecksumAlgorithmValue(int i) {
                copyOnWrite();
                ((GetArtifactUpdatePreference) this.instance).setChecksumAlgorithmValue(i);
                return this;
            }

            private Builder() {
                super(GetArtifactUpdatePreference.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetArtifactUpdatePreference() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactIdentifier() {
            this.artifactIdentifier_ = getDefaultInstance().getArtifactIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactKey() {
            this.artifactKey_ = getDefaultInstance().getArtifactKey();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactName() {
            this.artifactName_ = getDefaultInstance().getArtifactName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactSize() {
            this.artifactSize_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactType() {
            this.artifactType_ = getDefaultInstance().getArtifactType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearChecksum() {
            this.checksum_ = getDefaultInstance().getChecksum();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearChecksumAlgorithm() {
            this.checksumAlgorithm_ = 0;
        }

        public static GetArtifactUpdatePreference getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetArtifactUpdatePreference parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetArtifactUpdatePreference parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetArtifactUpdatePreference> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactIdentifier(String str) {
            if (str != null) {
                this.artifactIdentifier_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactIdentifierBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.artifactIdentifier_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactKey(String str) {
            if (str != null) {
                this.artifactKey_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactKeyBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.artifactKey_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactName(String str) {
            if (str != null) {
                this.artifactName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.artifactName_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactSize(int i) {
            this.artifactSize_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactType(String str) {
            if (str != null) {
                this.artifactType_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactTypeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.artifactType_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setChecksum(ByteString byteString) {
            if (byteString != null) {
                this.checksum_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setChecksumAlgorithm(ChecksumAlgorithm checksumAlgorithm) {
            if (checksumAlgorithm != null) {
                this.checksumAlgorithm_ = checksumAlgorithm.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setChecksumAlgorithmValue(int i) {
            this.checksumAlgorithm_ = i;
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
                    GetArtifactUpdatePreference getArtifactUpdatePreference = (GetArtifactUpdatePreference) obj2;
                    this.artifactName_ = visitor.visitString(!this.artifactName_.isEmpty(), this.artifactName_, !getArtifactUpdatePreference.artifactName_.isEmpty(), getArtifactUpdatePreference.artifactName_);
                    this.artifactType_ = visitor.visitString(!this.artifactType_.isEmpty(), this.artifactType_, !getArtifactUpdatePreference.artifactType_.isEmpty(), getArtifactUpdatePreference.artifactType_);
                    this.artifactKey_ = visitor.visitString(!this.artifactKey_.isEmpty(), this.artifactKey_, !getArtifactUpdatePreference.artifactKey_.isEmpty(), getArtifactUpdatePreference.artifactKey_);
                    this.artifactIdentifier_ = visitor.visitString(!this.artifactIdentifier_.isEmpty(), this.artifactIdentifier_, !getArtifactUpdatePreference.artifactIdentifier_.isEmpty(), getArtifactUpdatePreference.artifactIdentifier_);
                    this.artifactSize_ = visitor.visitInt(this.artifactSize_ != 0, this.artifactSize_, getArtifactUpdatePreference.artifactSize_ != 0, getArtifactUpdatePreference.artifactSize_);
                    this.checksumAlgorithm_ = visitor.visitInt(this.checksumAlgorithm_ != 0, this.checksumAlgorithm_, getArtifactUpdatePreference.checksumAlgorithm_ != 0, getArtifactUpdatePreference.checksumAlgorithm_);
                    boolean z2 = this.checksum_ != ByteString.EMPTY;
                    ByteString byteString = this.checksum_;
                    if (getArtifactUpdatePreference.checksum_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.checksum_ = visitor.visitByteString(z2, byteString, z, getArtifactUpdatePreference.checksum_);
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
                                    this.artifactName_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.artifactType_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 26) {
                                    this.artifactKey_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 34) {
                                    this.artifactIdentifier_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 40) {
                                    this.artifactSize_ = codedInputStream.readUInt32();
                                } else if (readTag == 48) {
                                    this.checksumAlgorithm_ = codedInputStream.readEnum();
                                } else if (readTag != 58) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.checksum_ = codedInputStream.readBytes();
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
                    return new GetArtifactUpdatePreference();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetArtifactUpdatePreference.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public String getArtifactIdentifier() {
            return this.artifactIdentifier_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public ByteString getArtifactIdentifierBytes() {
            return ByteString.copyFromUtf8(this.artifactIdentifier_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public String getArtifactKey() {
            return this.artifactKey_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public ByteString getArtifactKeyBytes() {
            return ByteString.copyFromUtf8(this.artifactKey_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public String getArtifactName() {
            return this.artifactName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public ByteString getArtifactNameBytes() {
            return ByteString.copyFromUtf8(this.artifactName_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public int getArtifactSize() {
            return this.artifactSize_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public String getArtifactType() {
            return this.artifactType_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public ByteString getArtifactTypeBytes() {
            return ByteString.copyFromUtf8(this.artifactType_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public ByteString getChecksum() {
            return this.checksum_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public ChecksumAlgorithm getChecksumAlgorithm() {
            ChecksumAlgorithm forNumber = ChecksumAlgorithm.forNumber(this.checksumAlgorithm_);
            return forNumber == null ? ChecksumAlgorithm.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetArtifactUpdatePreferenceOrBuilder
        public int getChecksumAlgorithmValue() {
            return this.checksumAlgorithm_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.artifactName_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getArtifactName());
            }
            if (!this.artifactType_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getArtifactType());
            }
            if (!this.artifactKey_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getArtifactKey());
            }
            if (!this.artifactIdentifier_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(4, getArtifactIdentifier());
            }
            int i3 = this.artifactSize_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(5, i3);
            }
            if (this.checksumAlgorithm_ != ChecksumAlgorithm.MD5.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(6, this.checksumAlgorithm_);
            }
            if (!this.checksum_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(7, this.checksum_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.artifactName_.isEmpty()) {
                codedOutputStream.writeString(1, getArtifactName());
            }
            if (!this.artifactType_.isEmpty()) {
                codedOutputStream.writeString(2, getArtifactType());
            }
            if (!this.artifactKey_.isEmpty()) {
                codedOutputStream.writeString(3, getArtifactKey());
            }
            if (!this.artifactIdentifier_.isEmpty()) {
                codedOutputStream.writeString(4, getArtifactIdentifier());
            }
            int i = this.artifactSize_;
            if (i != 0) {
                codedOutputStream.writeUInt32(5, i);
            }
            if (this.checksumAlgorithm_ != ChecksumAlgorithm.MD5.getNumber()) {
                codedOutputStream.writeEnum(6, this.checksumAlgorithm_);
            }
            if (!this.checksum_.isEmpty()) {
                codedOutputStream.writeBytes(7, this.checksum_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetArtifactUpdatePreference getArtifactUpdatePreference) {
            return DEFAULT_INSTANCE.createBuilder(getArtifactUpdatePreference);
        }

        public static GetArtifactUpdatePreference parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetArtifactUpdatePreference parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetArtifactUpdatePreference parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetArtifactUpdatePreference parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetArtifactUpdatePreference parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetArtifactUpdatePreference parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetArtifactUpdatePreference parseFrom(InputStream inputStream) throws IOException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetArtifactUpdatePreference parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetArtifactUpdatePreference parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetArtifactUpdatePreference parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetArtifactUpdatePreference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetArtifactUpdatePreferenceOrBuilder extends MessageLiteOrBuilder {
        String getArtifactIdentifier();

        ByteString getArtifactIdentifierBytes();

        String getArtifactKey();

        ByteString getArtifactKeyBytes();

        String getArtifactName();

        ByteString getArtifactNameBytes();

        int getArtifactSize();

        String getArtifactType();

        ByteString getArtifactTypeBytes();

        ByteString getChecksum();

        ChecksumAlgorithm getChecksumAlgorithm();

        int getChecksumAlgorithmValue();
    }

    /* loaded from: classes6.dex */
    public static final class GetCachedComponent extends GeneratedMessageLite<GetCachedComponent, Builder> implements GetCachedComponentOrBuilder {
        private static final GetCachedComponent DEFAULT_INSTANCE = new GetCachedComponent();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<GetCachedComponent> PARSER;
        private String name_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetCachedComponent, Builder> implements GetCachedComponentOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearName() {
                copyOnWrite();
                ((GetCachedComponent) this.instance).clearName();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetCachedComponentOrBuilder
            public String getName() {
                return ((GetCachedComponent) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetCachedComponentOrBuilder
            public ByteString getNameBytes() {
                return ((GetCachedComponent) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((GetCachedComponent) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((GetCachedComponent) this.instance).setNameBytes(byteString);
                return this;
            }

            private Builder() {
                super(GetCachedComponent.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetCachedComponent() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        public static GetCachedComponent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetCachedComponent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetCachedComponent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCachedComponent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetCachedComponent> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
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
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GetCachedComponent getCachedComponent = (GetCachedComponent) obj2;
                    this.name_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.name_.isEmpty(), this.name_, true ^ getCachedComponent.name_.isEmpty(), getCachedComponent.name_);
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
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 10) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.name_ = codedInputStream.readStringRequireUtf8();
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
                    return new GetCachedComponent();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetCachedComponent.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetCachedComponentOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetCachedComponentOrBuilder
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
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(1, getName());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetCachedComponent getCachedComponent) {
            return DEFAULT_INSTANCE.createBuilder(getCachedComponent);
        }

        public static GetCachedComponent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCachedComponent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCachedComponent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetCachedComponent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetCachedComponent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetCachedComponent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetCachedComponent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetCachedComponent parseFrom(InputStream inputStream) throws IOException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCachedComponent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCachedComponent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetCachedComponent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetCachedComponentOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();
    }

    /* loaded from: classes6.dex */
    public static final class GetDeviceArtifacts extends GeneratedMessageLite<GetDeviceArtifacts, Builder> implements GetDeviceArtifactsOrBuilder {
        public static final int ARTIFACT_REQUEST_REASON_FIELD_NUMBER = 1;
        private static final GetDeviceArtifacts DEFAULT_INSTANCE = new GetDeviceArtifacts();
        public static final int LOCALE_FIELD_NUMBER = 2;
        private static volatile Parser<GetDeviceArtifacts> PARSER = null;
        public static final int WAKEWORDS_FIELD_NUMBER = 3;
        private int artifactRequestReason_;
        private int bitField0_;
        private System.Locale locale_;
        private Internal.ProtobufList<String> wakewords_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetDeviceArtifacts, Builder> implements GetDeviceArtifactsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllWakewords(Iterable<String> iterable) {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).addAllWakewords(iterable);
                return this;
            }

            public Builder addWakewords(String str) {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).addWakewords(str);
                return this;
            }

            public Builder addWakewordsBytes(ByteString byteString) {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).addWakewordsBytes(byteString);
                return this;
            }

            public Builder clearArtifactRequestReason() {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).clearArtifactRequestReason();
                return this;
            }

            public Builder clearLocale() {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).clearLocale();
                return this;
            }

            public Builder clearWakewords() {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).clearWakewords();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
            public ArtifactRequestReason getArtifactRequestReason() {
                return ((GetDeviceArtifacts) this.instance).getArtifactRequestReason();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
            public int getArtifactRequestReasonValue() {
                return ((GetDeviceArtifacts) this.instance).getArtifactRequestReasonValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
            public System.Locale getLocale() {
                return ((GetDeviceArtifacts) this.instance).getLocale();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
            public String getWakewords(int i) {
                return ((GetDeviceArtifacts) this.instance).getWakewords(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
            public ByteString getWakewordsBytes(int i) {
                return ((GetDeviceArtifacts) this.instance).getWakewordsBytes(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
            public int getWakewordsCount() {
                return ((GetDeviceArtifacts) this.instance).getWakewordsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
            public List<String> getWakewordsList() {
                return Collections.unmodifiableList(((GetDeviceArtifacts) this.instance).getWakewordsList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
            public boolean hasLocale() {
                return ((GetDeviceArtifacts) this.instance).hasLocale();
            }

            public Builder mergeLocale(System.Locale locale) {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).mergeLocale(locale);
                return this;
            }

            public Builder setArtifactRequestReason(ArtifactRequestReason artifactRequestReason) {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).setArtifactRequestReason(artifactRequestReason);
                return this;
            }

            public Builder setArtifactRequestReasonValue(int i) {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).setArtifactRequestReasonValue(i);
                return this;
            }

            public Builder setLocale(System.Locale locale) {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).setLocale(locale);
                return this;
            }

            public Builder setWakewords(int i, String str) {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).setWakewords(i, str);
                return this;
            }

            private Builder() {
                super(GetDeviceArtifacts.DEFAULT_INSTANCE);
            }

            public Builder setLocale(System.Locale.Builder builder) {
                copyOnWrite();
                ((GetDeviceArtifacts) this.instance).setLocale(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetDeviceArtifacts() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllWakewords(Iterable<String> iterable) {
            ensureWakewordsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.wakewords_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addWakewords(String str) {
            if (str != null) {
                ensureWakewordsIsMutable();
                this.wakewords_.add(str);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addWakewordsBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                ensureWakewordsIsMutable();
                this.wakewords_.add(byteString.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactRequestReason() {
            this.artifactRequestReason_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLocale() {
            this.locale_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWakewords() {
            this.wakewords_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureWakewordsIsMutable() {
            if (!this.wakewords_.isModifiable()) {
                this.wakewords_ = GeneratedMessageLite.mutableCopy(this.wakewords_);
            }
        }

        public static GetDeviceArtifacts getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLocale(System.Locale locale) {
            System.Locale locale2 = this.locale_;
            if (locale2 != null && locale2 != System.Locale.getDefaultInstance()) {
                this.locale_ = System.Locale.newBuilder(this.locale_).mergeFrom((System.Locale.Builder) locale).mo10085buildPartial();
            } else {
                this.locale_ = locale;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetDeviceArtifacts parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetDeviceArtifacts parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetDeviceArtifacts> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactRequestReason(ArtifactRequestReason artifactRequestReason) {
            if (artifactRequestReason != null) {
                this.artifactRequestReason_ = artifactRequestReason.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactRequestReasonValue(int i) {
            this.artifactRequestReason_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocale(System.Locale locale) {
            if (locale != null) {
                this.locale_ = locale;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWakewords(int i, String str) {
            if (str != null) {
                ensureWakewordsIsMutable();
                this.wakewords_.set(i, str);
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
                    GetDeviceArtifacts getDeviceArtifacts = (GetDeviceArtifacts) obj2;
                    boolean z2 = this.artifactRequestReason_ != 0;
                    int i = this.artifactRequestReason_;
                    if (getDeviceArtifacts.artifactRequestReason_ != 0) {
                        z = true;
                    }
                    this.artifactRequestReason_ = visitor.visitInt(z2, i, z, getDeviceArtifacts.artifactRequestReason_);
                    this.locale_ = (System.Locale) visitor.visitMessage(this.locale_, getDeviceArtifacts.locale_);
                    this.wakewords_ = visitor.visitList(this.wakewords_, getDeviceArtifacts.wakewords_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= getDeviceArtifacts.bitField0_;
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
                                    this.artifactRequestReason_ = codedInputStream.readEnum();
                                } else if (readTag == 18) {
                                    System.Locale.Builder mo10081toBuilder = this.locale_ != null ? this.locale_.mo10081toBuilder() : null;
                                    this.locale_ = (System.Locale) codedInputStream.readMessage(System.Locale.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((System.Locale.Builder) this.locale_);
                                        this.locale_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                } else if (readTag != 26) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    if (!this.wakewords_.isModifiable()) {
                                        this.wakewords_ = GeneratedMessageLite.mutableCopy(this.wakewords_);
                                    }
                                    this.wakewords_.add(readStringRequireUtf8);
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
                    this.wakewords_.makeImmutable();
                    return null;
                case 6:
                    return new GetDeviceArtifacts();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetDeviceArtifacts.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
        public ArtifactRequestReason getArtifactRequestReason() {
            ArtifactRequestReason forNumber = ArtifactRequestReason.forNumber(this.artifactRequestReason_);
            return forNumber == null ? ArtifactRequestReason.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
        public int getArtifactRequestReasonValue() {
            return this.artifactRequestReason_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
        public System.Locale getLocale() {
            System.Locale locale = this.locale_;
            return locale == null ? System.Locale.getDefaultInstance() : locale;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.artifactRequestReason_ != ArtifactRequestReason.FIRMWARE_UPDATE.getNumber() ? CodedOutputStream.computeEnumSize(1, this.artifactRequestReason_) + 0 : 0;
            if (this.locale_ != null) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, getLocale());
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.wakewords_.size(); i3++) {
                i2 += CodedOutputStream.computeStringSizeNoTag(this.wakewords_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + (getWakewordsList().size() * 1) + computeEnumSize + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
        public String getWakewords(int i) {
            return this.wakewords_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
        public ByteString getWakewordsBytes(int i) {
            return ByteString.copyFromUtf8(this.wakewords_.get(i));
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
        public int getWakewordsCount() {
            return this.wakewords_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
        public List<String> getWakewordsList() {
            return this.wakewords_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetDeviceArtifactsOrBuilder
        public boolean hasLocale() {
            return this.locale_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.artifactRequestReason_ != ArtifactRequestReason.FIRMWARE_UPDATE.getNumber()) {
                codedOutputStream.writeEnum(1, this.artifactRequestReason_);
            }
            if (this.locale_ != null) {
                codedOutputStream.writeMessage(2, getLocale());
            }
            for (int i = 0; i < this.wakewords_.size(); i++) {
                codedOutputStream.writeString(3, this.wakewords_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetDeviceArtifacts getDeviceArtifacts) {
            return DEFAULT_INSTANCE.createBuilder(getDeviceArtifacts);
        }

        public static GetDeviceArtifacts parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetDeviceArtifacts parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetDeviceArtifacts parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocale(System.Locale.Builder builder) {
            this.locale_ = builder.mo10084build();
        }

        public static GetDeviceArtifacts parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetDeviceArtifacts parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetDeviceArtifacts parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetDeviceArtifacts parseFrom(InputStream inputStream) throws IOException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetDeviceArtifacts parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetDeviceArtifacts parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetDeviceArtifacts parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetDeviceArtifacts) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetDeviceArtifactsOrBuilder extends MessageLiteOrBuilder {
        ArtifactRequestReason getArtifactRequestReason();

        int getArtifactRequestReasonValue();

        System.Locale getLocale();

        String getWakewords(int i);

        ByteString getWakewordsBytes(int i);

        int getWakewordsCount();

        List<String> getWakewordsList();

        boolean hasLocale();
    }

    /* loaded from: classes6.dex */
    public static final class GetFirmwareInformation extends GeneratedMessageLite<GetFirmwareInformation, Builder> implements GetFirmwareInformationOrBuilder {
        private static final GetFirmwareInformation DEFAULT_INSTANCE = new GetFirmwareInformation();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<GetFirmwareInformation> PARSER;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetFirmwareInformation, Builder> implements GetFirmwareInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((GetFirmwareInformation) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareInformationOrBuilder
            public int getDeviceId() {
                return ((GetFirmwareInformation) this.instance).getDeviceId();
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((GetFirmwareInformation) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(GetFirmwareInformation.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetFirmwareInformation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        public static GetFirmwareInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetFirmwareInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetFirmwareInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetFirmwareInformation> parser() {
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
                    GetFirmwareInformation getFirmwareInformation = (GetFirmwareInformation) obj2;
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (getFirmwareInformation.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, getFirmwareInformation.deviceId_);
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
                    return new GetFirmwareInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetFirmwareInformation.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareInformationOrBuilder
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

        public static Builder newBuilder(GetFirmwareInformation getFirmwareInformation) {
            return DEFAULT_INSTANCE.createBuilder(getFirmwareInformation);
        }

        public static GetFirmwareInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetFirmwareInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetFirmwareInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetFirmwareInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetFirmwareInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetFirmwareInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetFirmwareInformation parseFrom(InputStream inputStream) throws IOException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetFirmwareInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetFirmwareInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetFirmwareInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetFirmwareInformationOrBuilder extends MessageLiteOrBuilder {
        int getDeviceId();
    }

    /* loaded from: classes6.dex */
    public static final class GetFirmwareUpdatePreferences extends GeneratedMessageLite<GetFirmwareUpdatePreferences, Builder> implements GetFirmwareUpdatePreferencesOrBuilder {
        private static final GetFirmwareUpdatePreferences DEFAULT_INSTANCE = new GetFirmwareUpdatePreferences();
        public static final int FIRMWARE_INFORMATION_FIELD_NUMBER = 1;
        public static final int METADATA_FIELD_NUMBER = 2;
        public static final int NEXT_AVAILABLE_UPDATES_FIELD_NUMBER = 3;
        private static volatile Parser<GetFirmwareUpdatePreferences> PARSER;
        private int bitField0_;
        private FirmwareInformation firmwareInformation_;
        private ByteString metadata_ = ByteString.EMPTY;
        private Internal.ProtobufList<NextAvailableUpdate> nextAvailableUpdates_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetFirmwareUpdatePreferences, Builder> implements GetFirmwareUpdatePreferencesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllNextAvailableUpdates(Iterable<? extends NextAvailableUpdate> iterable) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).addAllNextAvailableUpdates(iterable);
                return this;
            }

            public Builder addNextAvailableUpdates(NextAvailableUpdate nextAvailableUpdate) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).addNextAvailableUpdates(nextAvailableUpdate);
                return this;
            }

            public Builder clearFirmwareInformation() {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).clearFirmwareInformation();
                return this;
            }

            public Builder clearMetadata() {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).clearMetadata();
                return this;
            }

            public Builder clearNextAvailableUpdates() {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).clearNextAvailableUpdates();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
            public FirmwareInformation getFirmwareInformation() {
                return ((GetFirmwareUpdatePreferences) this.instance).getFirmwareInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
            public ByteString getMetadata() {
                return ((GetFirmwareUpdatePreferences) this.instance).getMetadata();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
            public NextAvailableUpdate getNextAvailableUpdates(int i) {
                return ((GetFirmwareUpdatePreferences) this.instance).getNextAvailableUpdates(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
            public int getNextAvailableUpdatesCount() {
                return ((GetFirmwareUpdatePreferences) this.instance).getNextAvailableUpdatesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
            public List<NextAvailableUpdate> getNextAvailableUpdatesList() {
                return Collections.unmodifiableList(((GetFirmwareUpdatePreferences) this.instance).getNextAvailableUpdatesList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
            public boolean hasFirmwareInformation() {
                return ((GetFirmwareUpdatePreferences) this.instance).hasFirmwareInformation();
            }

            public Builder mergeFirmwareInformation(FirmwareInformation firmwareInformation) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).mergeFirmwareInformation(firmwareInformation);
                return this;
            }

            public Builder removeNextAvailableUpdates(int i) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).removeNextAvailableUpdates(i);
                return this;
            }

            public Builder setFirmwareInformation(FirmwareInformation firmwareInformation) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).setFirmwareInformation(firmwareInformation);
                return this;
            }

            public Builder setMetadata(ByteString byteString) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).setMetadata(byteString);
                return this;
            }

            public Builder setNextAvailableUpdates(int i, NextAvailableUpdate nextAvailableUpdate) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).setNextAvailableUpdates(i, nextAvailableUpdate);
                return this;
            }

            private Builder() {
                super(GetFirmwareUpdatePreferences.DEFAULT_INSTANCE);
            }

            public Builder addNextAvailableUpdates(int i, NextAvailableUpdate nextAvailableUpdate) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).addNextAvailableUpdates(i, nextAvailableUpdate);
                return this;
            }

            public Builder setFirmwareInformation(FirmwareInformation.Builder builder) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).setFirmwareInformation(builder);
                return this;
            }

            public Builder setNextAvailableUpdates(int i, NextAvailableUpdate.Builder builder) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).setNextAvailableUpdates(i, builder);
                return this;
            }

            public Builder addNextAvailableUpdates(NextAvailableUpdate.Builder builder) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).addNextAvailableUpdates(builder);
                return this;
            }

            public Builder addNextAvailableUpdates(int i, NextAvailableUpdate.Builder builder) {
                copyOnWrite();
                ((GetFirmwareUpdatePreferences) this.instance).addNextAvailableUpdates(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetFirmwareUpdatePreferences() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllNextAvailableUpdates(Iterable<? extends NextAvailableUpdate> iterable) {
            ensureNextAvailableUpdatesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.nextAvailableUpdates_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNextAvailableUpdates(NextAvailableUpdate nextAvailableUpdate) {
            if (nextAvailableUpdate != null) {
                ensureNextAvailableUpdatesIsMutable();
                this.nextAvailableUpdates_.add(nextAvailableUpdate);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFirmwareInformation() {
            this.firmwareInformation_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMetadata() {
            this.metadata_ = getDefaultInstance().getMetadata();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNextAvailableUpdates() {
            this.nextAvailableUpdates_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureNextAvailableUpdatesIsMutable() {
            if (!this.nextAvailableUpdates_.isModifiable()) {
                this.nextAvailableUpdates_ = GeneratedMessageLite.mutableCopy(this.nextAvailableUpdates_);
            }
        }

        public static GetFirmwareUpdatePreferences getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFirmwareInformation(FirmwareInformation firmwareInformation) {
            FirmwareInformation firmwareInformation2 = this.firmwareInformation_;
            if (firmwareInformation2 != null && firmwareInformation2 != FirmwareInformation.getDefaultInstance()) {
                this.firmwareInformation_ = FirmwareInformation.newBuilder(this.firmwareInformation_).mergeFrom((FirmwareInformation.Builder) firmwareInformation).mo10085buildPartial();
            } else {
                this.firmwareInformation_ = firmwareInformation;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetFirmwareUpdatePreferences parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetFirmwareUpdatePreferences parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetFirmwareUpdatePreferences> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeNextAvailableUpdates(int i) {
            ensureNextAvailableUpdatesIsMutable();
            this.nextAvailableUpdates_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareInformation(FirmwareInformation firmwareInformation) {
            if (firmwareInformation != null) {
                this.firmwareInformation_ = firmwareInformation;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMetadata(ByteString byteString) {
            if (byteString != null) {
                this.metadata_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextAvailableUpdates(int i, NextAvailableUpdate nextAvailableUpdate) {
            if (nextAvailableUpdate != null) {
                ensureNextAvailableUpdatesIsMutable();
                this.nextAvailableUpdates_.set(i, nextAvailableUpdate);
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
                    GetFirmwareUpdatePreferences getFirmwareUpdatePreferences = (GetFirmwareUpdatePreferences) obj2;
                    this.firmwareInformation_ = (FirmwareInformation) visitor.visitMessage(this.firmwareInformation_, getFirmwareUpdatePreferences.firmwareInformation_);
                    boolean z2 = this.metadata_ != ByteString.EMPTY;
                    ByteString byteString = this.metadata_;
                    if (getFirmwareUpdatePreferences.metadata_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.metadata_ = visitor.visitByteString(z2, byteString, z, getFirmwareUpdatePreferences.metadata_);
                    this.nextAvailableUpdates_ = visitor.visitList(this.nextAvailableUpdates_, getFirmwareUpdatePreferences.nextAvailableUpdates_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= getFirmwareUpdatePreferences.bitField0_;
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
                                    if (readTag == 10) {
                                        FirmwareInformation.Builder mo10081toBuilder = this.firmwareInformation_ != null ? this.firmwareInformation_.mo10081toBuilder() : null;
                                        this.firmwareInformation_ = (FirmwareInformation) codedInputStream.readMessage(FirmwareInformation.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder != null) {
                                            mo10081toBuilder.mergeFrom((FirmwareInformation.Builder) this.firmwareInformation_);
                                            this.firmwareInformation_ = mo10081toBuilder.mo10085buildPartial();
                                        }
                                    } else if (readTag == 18) {
                                        this.metadata_ = codedInputStream.readBytes();
                                    } else if (readTag != 26) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        if (!this.nextAvailableUpdates_.isModifiable()) {
                                            this.nextAvailableUpdates_ = GeneratedMessageLite.mutableCopy(this.nextAvailableUpdates_);
                                        }
                                        this.nextAvailableUpdates_.add(codedInputStream.readMessage(NextAvailableUpdate.parser(), extensionRegistryLite));
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
                    this.nextAvailableUpdates_.makeImmutable();
                    return null;
                case 6:
                    return new GetFirmwareUpdatePreferences();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetFirmwareUpdatePreferences.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
        public FirmwareInformation getFirmwareInformation() {
            FirmwareInformation firmwareInformation = this.firmwareInformation_;
            return firmwareInformation == null ? FirmwareInformation.getDefaultInstance() : firmwareInformation;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
        public ByteString getMetadata() {
            return this.metadata_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
        public NextAvailableUpdate getNextAvailableUpdates(int i) {
            return this.nextAvailableUpdates_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
        public int getNextAvailableUpdatesCount() {
            return this.nextAvailableUpdates_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
        public List<NextAvailableUpdate> getNextAvailableUpdatesList() {
            return this.nextAvailableUpdates_;
        }

        public NextAvailableUpdateOrBuilder getNextAvailableUpdatesOrBuilder(int i) {
            return this.nextAvailableUpdates_.get(i);
        }

        public List<? extends NextAvailableUpdateOrBuilder> getNextAvailableUpdatesOrBuilderList() {
            return this.nextAvailableUpdates_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.firmwareInformation_ != null ? CodedOutputStream.computeMessageSize(1, getFirmwareInformation()) + 0 : 0;
            if (!this.metadata_.isEmpty()) {
                computeMessageSize += CodedOutputStream.computeBytesSize(2, this.metadata_);
            }
            for (int i2 = 0; i2 < this.nextAvailableUpdates_.size(); i2++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, this.nextAvailableUpdates_.get(i2));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeMessageSize;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.GetFirmwareUpdatePreferencesOrBuilder
        public boolean hasFirmwareInformation() {
            return this.firmwareInformation_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.firmwareInformation_ != null) {
                codedOutputStream.writeMessage(1, getFirmwareInformation());
            }
            if (!this.metadata_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.metadata_);
            }
            for (int i = 0; i < this.nextAvailableUpdates_.size(); i++) {
                codedOutputStream.writeMessage(3, this.nextAvailableUpdates_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetFirmwareUpdatePreferences getFirmwareUpdatePreferences) {
            return DEFAULT_INSTANCE.createBuilder(getFirmwareUpdatePreferences);
        }

        public static GetFirmwareUpdatePreferences parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetFirmwareUpdatePreferences parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetFirmwareUpdatePreferences parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareInformation(FirmwareInformation.Builder builder) {
            this.firmwareInformation_ = builder.mo10084build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNextAvailableUpdates(int i, NextAvailableUpdate nextAvailableUpdate) {
            if (nextAvailableUpdate != null) {
                ensureNextAvailableUpdatesIsMutable();
                this.nextAvailableUpdates_.add(i, nextAvailableUpdate);
                return;
            }
            throw new NullPointerException();
        }

        public static GetFirmwareUpdatePreferences parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextAvailableUpdates(int i, NextAvailableUpdate.Builder builder) {
            ensureNextAvailableUpdatesIsMutable();
            this.nextAvailableUpdates_.set(i, builder.mo10084build());
        }

        public static GetFirmwareUpdatePreferences parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetFirmwareUpdatePreferences parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNextAvailableUpdates(NextAvailableUpdate.Builder builder) {
            ensureNextAvailableUpdatesIsMutable();
            this.nextAvailableUpdates_.add(builder.mo10084build());
        }

        public static GetFirmwareUpdatePreferences parseFrom(InputStream inputStream) throws IOException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetFirmwareUpdatePreferences parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNextAvailableUpdates(int i, NextAvailableUpdate.Builder builder) {
            ensureNextAvailableUpdatesIsMutable();
            this.nextAvailableUpdates_.add(i, builder.mo10084build());
        }

        public static GetFirmwareUpdatePreferences parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetFirmwareUpdatePreferences parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetFirmwareUpdatePreferences) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetFirmwareUpdatePreferencesOrBuilder extends MessageLiteOrBuilder {
        FirmwareInformation getFirmwareInformation();

        ByteString getMetadata();

        NextAvailableUpdate getNextAvailableUpdates(int i);

        int getNextAvailableUpdatesCount();

        List<NextAvailableUpdate> getNextAvailableUpdatesList();

        boolean hasFirmwareInformation();
    }

    /* loaded from: classes6.dex */
    public static final class InitiateFirmwareUpdate extends GeneratedMessageLite<InitiateFirmwareUpdate, Builder> implements InitiateFirmwareUpdateOrBuilder {
        private static final InitiateFirmwareUpdate DEFAULT_INSTANCE = new InitiateFirmwareUpdate();
        private static volatile Parser<InitiateFirmwareUpdate> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<InitiateFirmwareUpdate, Builder> implements InitiateFirmwareUpdateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(InitiateFirmwareUpdate.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private InitiateFirmwareUpdate() {
        }

        public static InitiateFirmwareUpdate getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static InitiateFirmwareUpdate parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InitiateFirmwareUpdate parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<InitiateFirmwareUpdate> parser() {
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
                    InitiateFirmwareUpdate initiateFirmwareUpdate = (InitiateFirmwareUpdate) obj2;
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
                    return new InitiateFirmwareUpdate();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (InitiateFirmwareUpdate.class) {
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

        public static Builder newBuilder(InitiateFirmwareUpdate initiateFirmwareUpdate) {
            return DEFAULT_INSTANCE.createBuilder(initiateFirmwareUpdate);
        }

        public static InitiateFirmwareUpdate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static InitiateFirmwareUpdate parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static InitiateFirmwareUpdate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static InitiateFirmwareUpdate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static InitiateFirmwareUpdate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InitiateFirmwareUpdate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static InitiateFirmwareUpdate parseFrom(InputStream inputStream) throws IOException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InitiateFirmwareUpdate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static InitiateFirmwareUpdate parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static InitiateFirmwareUpdate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InitiateFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface InitiateFirmwareUpdateOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class NextAvailableUpdate extends GeneratedMessageLite<NextAvailableUpdate, Builder> implements NextAvailableUpdateOrBuilder {
        private static final NextAvailableUpdate DEFAULT_INSTANCE = new NextAvailableUpdate();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<NextAvailableUpdate> PARSER;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NextAvailableUpdate, Builder> implements NextAvailableUpdateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((NextAvailableUpdate) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.NextAvailableUpdateOrBuilder
            public int getDeviceId() {
                return ((NextAvailableUpdate) this.instance).getDeviceId();
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((NextAvailableUpdate) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(NextAvailableUpdate.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NextAvailableUpdate() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        public static NextAvailableUpdate getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NextAvailableUpdate parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NextAvailableUpdate parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NextAvailableUpdate> parser() {
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
                    NextAvailableUpdate nextAvailableUpdate = (NextAvailableUpdate) obj2;
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (nextAvailableUpdate.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, nextAvailableUpdate.deviceId_);
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
                    return new NextAvailableUpdate();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NextAvailableUpdate.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.NextAvailableUpdateOrBuilder
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

        public static Builder newBuilder(NextAvailableUpdate nextAvailableUpdate) {
            return DEFAULT_INSTANCE.createBuilder(nextAvailableUpdate);
        }

        public static NextAvailableUpdate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NextAvailableUpdate parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NextAvailableUpdate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static NextAvailableUpdate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NextAvailableUpdate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NextAvailableUpdate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NextAvailableUpdate parseFrom(InputStream inputStream) throws IOException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NextAvailableUpdate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NextAvailableUpdate parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NextAvailableUpdate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NextAvailableUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NextAvailableUpdateOrBuilder extends MessageLiteOrBuilder {
        int getDeviceId();
    }

    /* loaded from: classes6.dex */
    public static final class NotifyFirmwareInformation extends GeneratedMessageLite<NotifyFirmwareInformation, Builder> implements NotifyFirmwareInformationOrBuilder {
        private static final NotifyFirmwareInformation DEFAULT_INSTANCE = new NotifyFirmwareInformation();
        public static final int FIRMWARE_INFORMATION_FIELD_NUMBER = 1;
        private static volatile Parser<NotifyFirmwareInformation> PARSER;
        private FirmwareInformation firmwareInformation_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotifyFirmwareInformation, Builder> implements NotifyFirmwareInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearFirmwareInformation() {
                copyOnWrite();
                ((NotifyFirmwareInformation) this.instance).clearFirmwareInformation();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.NotifyFirmwareInformationOrBuilder
            public FirmwareInformation getFirmwareInformation() {
                return ((NotifyFirmwareInformation) this.instance).getFirmwareInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.NotifyFirmwareInformationOrBuilder
            public boolean hasFirmwareInformation() {
                return ((NotifyFirmwareInformation) this.instance).hasFirmwareInformation();
            }

            public Builder mergeFirmwareInformation(FirmwareInformation firmwareInformation) {
                copyOnWrite();
                ((NotifyFirmwareInformation) this.instance).mergeFirmwareInformation(firmwareInformation);
                return this;
            }

            public Builder setFirmwareInformation(FirmwareInformation firmwareInformation) {
                copyOnWrite();
                ((NotifyFirmwareInformation) this.instance).setFirmwareInformation(firmwareInformation);
                return this;
            }

            private Builder() {
                super(NotifyFirmwareInformation.DEFAULT_INSTANCE);
            }

            public Builder setFirmwareInformation(FirmwareInformation.Builder builder) {
                copyOnWrite();
                ((NotifyFirmwareInformation) this.instance).setFirmwareInformation(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotifyFirmwareInformation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFirmwareInformation() {
            this.firmwareInformation_ = null;
        }

        public static NotifyFirmwareInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFirmwareInformation(FirmwareInformation firmwareInformation) {
            FirmwareInformation firmwareInformation2 = this.firmwareInformation_;
            if (firmwareInformation2 != null && firmwareInformation2 != FirmwareInformation.getDefaultInstance()) {
                this.firmwareInformation_ = FirmwareInformation.newBuilder(this.firmwareInformation_).mergeFrom((FirmwareInformation.Builder) firmwareInformation).mo10085buildPartial();
            } else {
                this.firmwareInformation_ = firmwareInformation;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotifyFirmwareInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyFirmwareInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotifyFirmwareInformation> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareInformation(FirmwareInformation firmwareInformation) {
            if (firmwareInformation != null) {
                this.firmwareInformation_ = firmwareInformation;
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
                    this.firmwareInformation_ = (FirmwareInformation) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.firmwareInformation_, ((NotifyFirmwareInformation) obj2).firmwareInformation_);
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
                                    FirmwareInformation.Builder mo10081toBuilder = this.firmwareInformation_ != null ? this.firmwareInformation_.mo10081toBuilder() : null;
                                    this.firmwareInformation_ = (FirmwareInformation) codedInputStream.readMessage(FirmwareInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((FirmwareInformation.Builder) this.firmwareInformation_);
                                        this.firmwareInformation_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new NotifyFirmwareInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotifyFirmwareInformation.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.NotifyFirmwareInformationOrBuilder
        public FirmwareInformation getFirmwareInformation() {
            FirmwareInformation firmwareInformation = this.firmwareInformation_;
            return firmwareInformation == null ? FirmwareInformation.getDefaultInstance() : firmwareInformation;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.firmwareInformation_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getFirmwareInformation());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.NotifyFirmwareInformationOrBuilder
        public boolean hasFirmwareInformation() {
            return this.firmwareInformation_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.firmwareInformation_ != null) {
                codedOutputStream.writeMessage(1, getFirmwareInformation());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotifyFirmwareInformation notifyFirmwareInformation) {
            return DEFAULT_INSTANCE.createBuilder(notifyFirmwareInformation);
        }

        public static NotifyFirmwareInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyFirmwareInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotifyFirmwareInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareInformation(FirmwareInformation.Builder builder) {
            this.firmwareInformation_ = builder.mo10084build();
        }

        public static NotifyFirmwareInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NotifyFirmwareInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyFirmwareInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotifyFirmwareInformation parseFrom(InputStream inputStream) throws IOException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyFirmwareInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyFirmwareInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotifyFirmwareInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyFirmwareInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotifyFirmwareInformationOrBuilder extends MessageLiteOrBuilder {
        FirmwareInformation getFirmwareInformation();

        boolean hasFirmwareInformation();
    }

    /* loaded from: classes6.dex */
    public static final class ResetCachedComponent extends GeneratedMessageLite<ResetCachedComponent, Builder> implements ResetCachedComponentOrBuilder {
        private static final ResetCachedComponent DEFAULT_INSTANCE = new ResetCachedComponent();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<ResetCachedComponent> PARSER;
        private String name_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ResetCachedComponent, Builder> implements ResetCachedComponentOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearName() {
                copyOnWrite();
                ((ResetCachedComponent) this.instance).clearName();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ResetCachedComponentOrBuilder
            public String getName() {
                return ((ResetCachedComponent) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.ResetCachedComponentOrBuilder
            public ByteString getNameBytes() {
                return ((ResetCachedComponent) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((ResetCachedComponent) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((ResetCachedComponent) this.instance).setNameBytes(byteString);
                return this;
            }

            private Builder() {
                super(ResetCachedComponent.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ResetCachedComponent() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        public static ResetCachedComponent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ResetCachedComponent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ResetCachedComponent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetCachedComponent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ResetCachedComponent> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
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
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    ResetCachedComponent resetCachedComponent = (ResetCachedComponent) obj2;
                    this.name_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.name_.isEmpty(), this.name_, true ^ resetCachedComponent.name_.isEmpty(), resetCachedComponent.name_);
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
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 10) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.name_ = codedInputStream.readStringRequireUtf8();
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
                    return new ResetCachedComponent();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ResetCachedComponent.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ResetCachedComponentOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.ResetCachedComponentOrBuilder
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
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(1, getName());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ResetCachedComponent resetCachedComponent) {
            return DEFAULT_INSTANCE.createBuilder(resetCachedComponent);
        }

        public static ResetCachedComponent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetCachedComponent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetCachedComponent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ResetCachedComponent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ResetCachedComponent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ResetCachedComponent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ResetCachedComponent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ResetCachedComponent parseFrom(InputStream inputStream) throws IOException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetCachedComponent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetCachedComponent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ResetCachedComponent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetCachedComponent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ResetCachedComponentOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();
    }

    /* loaded from: classes6.dex */
    public static final class StartFirmwareUpdate extends GeneratedMessageLite<StartFirmwareUpdate, Builder> implements StartFirmwareUpdateOrBuilder {
        private static final StartFirmwareUpdate DEFAULT_INSTANCE = new StartFirmwareUpdate();
        private static volatile Parser<StartFirmwareUpdate> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<StartFirmwareUpdate, Builder> implements StartFirmwareUpdateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(StartFirmwareUpdate.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private StartFirmwareUpdate() {
        }

        public static StartFirmwareUpdate getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static StartFirmwareUpdate parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StartFirmwareUpdate parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<StartFirmwareUpdate> parser() {
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
                    StartFirmwareUpdate startFirmwareUpdate = (StartFirmwareUpdate) obj2;
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
                    return new StartFirmwareUpdate();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (StartFirmwareUpdate.class) {
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

        public static Builder newBuilder(StartFirmwareUpdate startFirmwareUpdate) {
            return DEFAULT_INSTANCE.createBuilder(startFirmwareUpdate);
        }

        public static StartFirmwareUpdate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StartFirmwareUpdate parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StartFirmwareUpdate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StartFirmwareUpdate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StartFirmwareUpdate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StartFirmwareUpdate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StartFirmwareUpdate parseFrom(InputStream inputStream) throws IOException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StartFirmwareUpdate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StartFirmwareUpdate parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StartFirmwareUpdate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartFirmwareUpdate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface StartFirmwareUpdateOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class UpdateComponentSegment extends GeneratedMessageLite<UpdateComponentSegment, Builder> implements UpdateComponentSegmentOrBuilder {
        public static final int COMPONENT_NAME_FIELD_NUMBER = 1;
        public static final int COMPONENT_OFFSET_FIELD_NUMBER = 2;
        private static final UpdateComponentSegment DEFAULT_INSTANCE = new UpdateComponentSegment();
        private static volatile Parser<UpdateComponentSegment> PARSER = null;
        public static final int SEGMENT_SIGNATURE_FIELD_NUMBER = 4;
        public static final int SEGMENT_SIZE_FIELD_NUMBER = 3;
        private int componentOffset_;
        private int segmentSize_;
        private String componentName_ = "";
        private String segmentSignature_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UpdateComponentSegment, Builder> implements UpdateComponentSegmentOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearComponentName() {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).clearComponentName();
                return this;
            }

            public Builder clearComponentOffset() {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).clearComponentOffset();
                return this;
            }

            public Builder clearSegmentSignature() {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).clearSegmentSignature();
                return this;
            }

            public Builder clearSegmentSize() {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).clearSegmentSize();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
            public String getComponentName() {
                return ((UpdateComponentSegment) this.instance).getComponentName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
            public ByteString getComponentNameBytes() {
                return ((UpdateComponentSegment) this.instance).getComponentNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
            public int getComponentOffset() {
                return ((UpdateComponentSegment) this.instance).getComponentOffset();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
            public String getSegmentSignature() {
                return ((UpdateComponentSegment) this.instance).getSegmentSignature();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
            public ByteString getSegmentSignatureBytes() {
                return ((UpdateComponentSegment) this.instance).getSegmentSignatureBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
            public int getSegmentSize() {
                return ((UpdateComponentSegment) this.instance).getSegmentSize();
            }

            public Builder setComponentName(String str) {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).setComponentName(str);
                return this;
            }

            public Builder setComponentNameBytes(ByteString byteString) {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).setComponentNameBytes(byteString);
                return this;
            }

            public Builder setComponentOffset(int i) {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).setComponentOffset(i);
                return this;
            }

            public Builder setSegmentSignature(String str) {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).setSegmentSignature(str);
                return this;
            }

            public Builder setSegmentSignatureBytes(ByteString byteString) {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).setSegmentSignatureBytes(byteString);
                return this;
            }

            public Builder setSegmentSize(int i) {
                copyOnWrite();
                ((UpdateComponentSegment) this.instance).setSegmentSize(i);
                return this;
            }

            private Builder() {
                super(UpdateComponentSegment.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UpdateComponentSegment() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearComponentName() {
            this.componentName_ = getDefaultInstance().getComponentName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearComponentOffset() {
            this.componentOffset_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegmentSignature() {
            this.segmentSignature_ = getDefaultInstance().getSegmentSignature();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegmentSize() {
            this.segmentSize_ = 0;
        }

        public static UpdateComponentSegment getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UpdateComponentSegment parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateComponentSegment parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UpdateComponentSegment> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setComponentName(String str) {
            if (str != null) {
                this.componentName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setComponentNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.componentName_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setComponentOffset(int i) {
            this.componentOffset_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentSignature(String str) {
            if (str != null) {
                this.segmentSignature_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentSignatureBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.segmentSignature_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentSize(int i) {
            this.segmentSize_ = i;
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
                    UpdateComponentSegment updateComponentSegment = (UpdateComponentSegment) obj2;
                    this.componentName_ = visitor.visitString(!this.componentName_.isEmpty(), this.componentName_, !updateComponentSegment.componentName_.isEmpty(), updateComponentSegment.componentName_);
                    this.componentOffset_ = visitor.visitInt(this.componentOffset_ != 0, this.componentOffset_, updateComponentSegment.componentOffset_ != 0, updateComponentSegment.componentOffset_);
                    boolean z2 = this.segmentSize_ != 0;
                    int i = this.segmentSize_;
                    if (updateComponentSegment.segmentSize_ != 0) {
                        z = true;
                    }
                    this.segmentSize_ = visitor.visitInt(z2, i, z, updateComponentSegment.segmentSize_);
                    this.segmentSignature_ = visitor.visitString(!this.segmentSignature_.isEmpty(), this.segmentSignature_, !updateComponentSegment.segmentSignature_.isEmpty(), updateComponentSegment.segmentSignature_);
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
                                    this.componentName_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 16) {
                                    this.componentOffset_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.segmentSize_ = codedInputStream.readUInt32();
                                } else if (readTag != 34) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.segmentSignature_ = codedInputStream.readStringRequireUtf8();
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
                    return new UpdateComponentSegment();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UpdateComponentSegment.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
        public String getComponentName() {
            return this.componentName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
        public ByteString getComponentNameBytes() {
            return ByteString.copyFromUtf8(this.componentName_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
        public int getComponentOffset() {
            return this.componentOffset_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
        public String getSegmentSignature() {
            return this.segmentSignature_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
        public ByteString getSegmentSignatureBytes() {
            return ByteString.copyFromUtf8(this.segmentSignature_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.UpdateComponentSegmentOrBuilder
        public int getSegmentSize() {
            return this.segmentSize_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.componentName_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getComponentName());
            }
            int i3 = this.componentOffset_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = this.segmentSize_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i4);
            }
            if (!this.segmentSignature_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(4, getSegmentSignature());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.componentName_.isEmpty()) {
                codedOutputStream.writeString(1, getComponentName());
            }
            int i = this.componentOffset_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            int i2 = this.segmentSize_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(3, i2);
            }
            if (!this.segmentSignature_.isEmpty()) {
                codedOutputStream.writeString(4, getSegmentSignature());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UpdateComponentSegment updateComponentSegment) {
            return DEFAULT_INSTANCE.createBuilder(updateComponentSegment);
        }

        public static UpdateComponentSegment parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateComponentSegment parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UpdateComponentSegment parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UpdateComponentSegment parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UpdateComponentSegment parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UpdateComponentSegment parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UpdateComponentSegment parseFrom(InputStream inputStream) throws IOException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateComponentSegment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateComponentSegment parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UpdateComponentSegment parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateComponentSegment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UpdateComponentSegmentOrBuilder extends MessageLiteOrBuilder {
        String getComponentName();

        ByteString getComponentNameBytes();

        int getComponentOffset();

        String getSegmentSignature();

        ByteString getSegmentSignatureBytes();

        int getSegmentSize();
    }

    /* loaded from: classes6.dex */
    public static final class VerifyArtifactSignature extends GeneratedMessageLite<VerifyArtifactSignature, Builder> implements VerifyArtifactSignatureOrBuilder {
        public static final int ARTIFACT_NAME_FIELD_NUMBER = 1;
        private static final VerifyArtifactSignature DEFAULT_INSTANCE = new VerifyArtifactSignature();
        private static volatile Parser<VerifyArtifactSignature> PARSER = null;
        public static final int SIGNATURE_FIELD_NUMBER = 2;
        private String artifactName_ = "";
        private ByteString signature_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<VerifyArtifactSignature, Builder> implements VerifyArtifactSignatureOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearArtifactName() {
                copyOnWrite();
                ((VerifyArtifactSignature) this.instance).clearArtifactName();
                return this;
            }

            public Builder clearSignature() {
                copyOnWrite();
                ((VerifyArtifactSignature) this.instance).clearSignature();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.VerifyArtifactSignatureOrBuilder
            public String getArtifactName() {
                return ((VerifyArtifactSignature) this.instance).getArtifactName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.VerifyArtifactSignatureOrBuilder
            public ByteString getArtifactNameBytes() {
                return ((VerifyArtifactSignature) this.instance).getArtifactNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Firmware.VerifyArtifactSignatureOrBuilder
            public ByteString getSignature() {
                return ((VerifyArtifactSignature) this.instance).getSignature();
            }

            public Builder setArtifactName(String str) {
                copyOnWrite();
                ((VerifyArtifactSignature) this.instance).setArtifactName(str);
                return this;
            }

            public Builder setArtifactNameBytes(ByteString byteString) {
                copyOnWrite();
                ((VerifyArtifactSignature) this.instance).setArtifactNameBytes(byteString);
                return this;
            }

            public Builder setSignature(ByteString byteString) {
                copyOnWrite();
                ((VerifyArtifactSignature) this.instance).setSignature(byteString);
                return this;
            }

            private Builder() {
                super(VerifyArtifactSignature.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private VerifyArtifactSignature() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactName() {
            this.artifactName_ = getDefaultInstance().getArtifactName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSignature() {
            this.signature_ = getDefaultInstance().getSignature();
        }

        public static VerifyArtifactSignature getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static VerifyArtifactSignature parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VerifyArtifactSignature parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<VerifyArtifactSignature> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactName(String str) {
            if (str != null) {
                this.artifactName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.artifactName_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSignature(ByteString byteString) {
            if (byteString != null) {
                this.signature_ = byteString;
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
                    VerifyArtifactSignature verifyArtifactSignature = (VerifyArtifactSignature) obj2;
                    this.artifactName_ = visitor.visitString(!this.artifactName_.isEmpty(), this.artifactName_, !verifyArtifactSignature.artifactName_.isEmpty(), verifyArtifactSignature.artifactName_);
                    boolean z2 = this.signature_ != ByteString.EMPTY;
                    ByteString byteString = this.signature_;
                    if (verifyArtifactSignature.signature_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.signature_ = visitor.visitByteString(z2, byteString, z, verifyArtifactSignature.signature_);
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
                                    this.artifactName_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.signature_ = codedInputStream.readBytes();
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
                    return new VerifyArtifactSignature();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (VerifyArtifactSignature.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Firmware.VerifyArtifactSignatureOrBuilder
        public String getArtifactName() {
            return this.artifactName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.VerifyArtifactSignatureOrBuilder
        public ByteString getArtifactNameBytes() {
            return ByteString.copyFromUtf8(this.artifactName_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.artifactName_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getArtifactName());
            }
            if (!this.signature_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(2, this.signature_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Firmware.VerifyArtifactSignatureOrBuilder
        public ByteString getSignature() {
            return this.signature_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.artifactName_.isEmpty()) {
                codedOutputStream.writeString(1, getArtifactName());
            }
            if (!this.signature_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.signature_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(VerifyArtifactSignature verifyArtifactSignature) {
            return DEFAULT_INSTANCE.createBuilder(verifyArtifactSignature);
        }

        public static VerifyArtifactSignature parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VerifyArtifactSignature parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static VerifyArtifactSignature parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static VerifyArtifactSignature parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static VerifyArtifactSignature parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static VerifyArtifactSignature parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static VerifyArtifactSignature parseFrom(InputStream inputStream) throws IOException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VerifyArtifactSignature parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VerifyArtifactSignature parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static VerifyArtifactSignature parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifyArtifactSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface VerifyArtifactSignatureOrBuilder extends MessageLiteOrBuilder {
        String getArtifactName();

        ByteString getArtifactNameBytes();

        ByteString getSignature();
    }

    private Firmware() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
