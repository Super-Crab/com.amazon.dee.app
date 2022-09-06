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
/* loaded from: classes6.dex */
public final class Cbl {

    /* renamed from: com.amazon.alexa.accessory.protocol.Cbl$1  reason: invalid class name */
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
    public static final class CblInformation extends GeneratedMessageLite<CblInformation, Builder> implements CblInformationOrBuilder {
        private static final CblInformation DEFAULT_INSTANCE = new CblInformation();
        public static final int EXPIRES_IN_SEC_FIELD_NUMBER = 3;
        private static volatile Parser<CblInformation> PARSER = null;
        public static final int USER_CODE_FIELD_NUMBER = 1;
        public static final int VERIFICATION_URI_FIELD_NUMBER = 2;
        private int expiresInSec_;
        private String userCode_ = "";
        private String verificationUri_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CblInformation, Builder> implements CblInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearExpiresInSec() {
                copyOnWrite();
                ((CblInformation) this.instance).clearExpiresInSec();
                return this;
            }

            public Builder clearUserCode() {
                copyOnWrite();
                ((CblInformation) this.instance).clearUserCode();
                return this;
            }

            public Builder clearVerificationUri() {
                copyOnWrite();
                ((CblInformation) this.instance).clearVerificationUri();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
            public int getExpiresInSec() {
                return ((CblInformation) this.instance).getExpiresInSec();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
            public String getUserCode() {
                return ((CblInformation) this.instance).getUserCode();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
            public ByteString getUserCodeBytes() {
                return ((CblInformation) this.instance).getUserCodeBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
            public String getVerificationUri() {
                return ((CblInformation) this.instance).getVerificationUri();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
            public ByteString getVerificationUriBytes() {
                return ((CblInformation) this.instance).getVerificationUriBytes();
            }

            public Builder setExpiresInSec(int i) {
                copyOnWrite();
                ((CblInformation) this.instance).setExpiresInSec(i);
                return this;
            }

            public Builder setUserCode(String str) {
                copyOnWrite();
                ((CblInformation) this.instance).setUserCode(str);
                return this;
            }

            public Builder setUserCodeBytes(ByteString byteString) {
                copyOnWrite();
                ((CblInformation) this.instance).setUserCodeBytes(byteString);
                return this;
            }

            public Builder setVerificationUri(String str) {
                copyOnWrite();
                ((CblInformation) this.instance).setVerificationUri(str);
                return this;
            }

            public Builder setVerificationUriBytes(ByteString byteString) {
                copyOnWrite();
                ((CblInformation) this.instance).setVerificationUriBytes(byteString);
                return this;
            }

            private Builder() {
                super(CblInformation.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CblInformation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExpiresInSec() {
            this.expiresInSec_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUserCode() {
            this.userCode_ = getDefaultInstance().getUserCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVerificationUri() {
            this.verificationUri_ = getDefaultInstance().getVerificationUri();
        }

        public static CblInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CblInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CblInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CblInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CblInformation> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExpiresInSec(int i) {
            this.expiresInSec_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserCode(String str) {
            if (str != null) {
                this.userCode_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserCodeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.userCode_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVerificationUri(String str) {
            if (str != null) {
                this.verificationUri_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVerificationUriBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.verificationUri_ = byteString.toStringUtf8();
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
                    CblInformation cblInformation = (CblInformation) obj2;
                    this.userCode_ = visitor.visitString(!this.userCode_.isEmpty(), this.userCode_, !cblInformation.userCode_.isEmpty(), cblInformation.userCode_);
                    this.verificationUri_ = visitor.visitString(!this.verificationUri_.isEmpty(), this.verificationUri_, !cblInformation.verificationUri_.isEmpty(), cblInformation.verificationUri_);
                    boolean z2 = this.expiresInSec_ != 0;
                    int i = this.expiresInSec_;
                    if (cblInformation.expiresInSec_ != 0) {
                        z = true;
                    }
                    this.expiresInSec_ = visitor.visitInt(z2, i, z, cblInformation.expiresInSec_);
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
                                    this.userCode_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.verificationUri_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.expiresInSec_ = codedInputStream.readUInt32();
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
                    return new CblInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CblInformation.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
        public int getExpiresInSec() {
            return this.expiresInSec_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.userCode_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getUserCode());
            }
            if (!this.verificationUri_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getVerificationUri());
            }
            int i3 = this.expiresInSec_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
        public String getUserCode() {
            return this.userCode_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
        public ByteString getUserCodeBytes() {
            return ByteString.copyFromUtf8(this.userCode_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
        public String getVerificationUri() {
            return this.verificationUri_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cbl.CblInformationOrBuilder
        public ByteString getVerificationUriBytes() {
            return ByteString.copyFromUtf8(this.verificationUri_);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.userCode_.isEmpty()) {
                codedOutputStream.writeString(1, getUserCode());
            }
            if (!this.verificationUri_.isEmpty()) {
                codedOutputStream.writeString(2, getVerificationUri());
            }
            int i = this.expiresInSec_;
            if (i != 0) {
                codedOutputStream.writeUInt32(3, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CblInformation cblInformation) {
            return DEFAULT_INSTANCE.createBuilder(cblInformation);
        }

        public static CblInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CblInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CblInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CblInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CblInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CblInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CblInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CblInformation parseFrom(InputStream inputStream) throws IOException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CblInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CblInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CblInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CblInformationOrBuilder extends MessageLiteOrBuilder {
        int getExpiresInSec();

        String getUserCode();

        ByteString getUserCodeBytes();

        String getVerificationUri();

        ByteString getVerificationUriBytes();
    }

    /* loaded from: classes6.dex */
    public static final class CblLoginState extends GeneratedMessageLite<CblLoginState, Builder> implements CblLoginStateOrBuilder {
        private static final CblLoginState DEFAULT_INSTANCE = new CblLoginState();
        private static volatile Parser<CblLoginState> PARSER = null;
        public static final int STATE_FIELD_NUMBER = 1;
        private int state_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CblLoginState, Builder> implements CblLoginStateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearState() {
                copyOnWrite();
                ((CblLoginState) this.instance).clearState();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cbl.CblLoginStateOrBuilder
            public State getState() {
                return ((CblLoginState) this.instance).getState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cbl.CblLoginStateOrBuilder
            public int getStateValue() {
                return ((CblLoginState) this.instance).getStateValue();
            }

            public Builder setState(State state) {
                copyOnWrite();
                ((CblLoginState) this.instance).setState(state);
                return this;
            }

            public Builder setStateValue(int i) {
                copyOnWrite();
                ((CblLoginState) this.instance).setStateValue(i);
                return this;
            }

            private Builder() {
                super(CblLoginState.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        public enum State implements Internal.EnumLite {
            UNKNOWN(0),
            LOGGED_OUT(1),
            LOGGED_IN(2),
            UNRECOGNIZED(-1);
            
            public static final int LOGGED_IN_VALUE = 2;
            public static final int LOGGED_OUT_VALUE = 1;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() { // from class: com.amazon.alexa.accessory.protocol.Cbl.CblLoginState.State.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public State mo9850findValueByNumber(int i) {
                    return State.forNumber(i);
                }
            };
            private final int value;

            State(int i) {
                this.value = i;
            }

            public static State forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return LOGGED_OUT;
                    }
                    if (i == 2) {
                        return LOGGED_IN;
                    }
                    return null;
                }
                return UNKNOWN;
            }

            public static Internal.EnumLiteMap<State> internalGetValueMap() {
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
            public static State valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CblLoginState() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearState() {
            this.state_ = 0;
        }

        public static CblLoginState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CblLoginState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CblLoginState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CblLoginState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CblLoginState> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setState(State state) {
            if (state != null) {
                this.state_ = state.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStateValue(int i) {
            this.state_ = i;
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
                    CblLoginState cblLoginState = (CblLoginState) obj2;
                    boolean z2 = this.state_ != 0;
                    int i = this.state_;
                    if (cblLoginState.state_ != 0) {
                        z = true;
                    }
                    this.state_ = visitor.visitInt(z2, i, z, cblLoginState.state_);
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
                                        this.state_ = codedInputStream.readEnum();
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
                    return new CblLoginState();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CblLoginState.class) {
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
            if (this.state_ != State.UNKNOWN.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.state_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cbl.CblLoginStateOrBuilder
        public State getState() {
            State forNumber = State.forNumber(this.state_);
            return forNumber == null ? State.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cbl.CblLoginStateOrBuilder
        public int getStateValue() {
            return this.state_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.state_ != State.UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(1, this.state_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CblLoginState cblLoginState) {
            return DEFAULT_INSTANCE.createBuilder(cblLoginState);
        }

        public static CblLoginState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CblLoginState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CblLoginState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CblLoginState parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CblLoginState parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CblLoginState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CblLoginState parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CblLoginState parseFrom(InputStream inputStream) throws IOException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CblLoginState parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CblLoginState parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CblLoginState parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CblLoginStateOrBuilder extends MessageLiteOrBuilder {
        CblLoginState.State getState();

        int getStateValue();
    }

    /* loaded from: classes6.dex */
    public static final class GetCblInformation extends GeneratedMessageLite<GetCblInformation, Builder> implements GetCblInformationOrBuilder {
        private static final GetCblInformation DEFAULT_INSTANCE = new GetCblInformation();
        private static volatile Parser<GetCblInformation> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetCblInformation, Builder> implements GetCblInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(GetCblInformation.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetCblInformation() {
        }

        public static GetCblInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetCblInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetCblInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCblInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetCblInformation> parser() {
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
                    GetCblInformation getCblInformation = (GetCblInformation) obj2;
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
                    return new GetCblInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetCblInformation.class) {
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

        public static Builder newBuilder(GetCblInformation getCblInformation) {
            return DEFAULT_INSTANCE.createBuilder(getCblInformation);
        }

        public static GetCblInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCblInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCblInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetCblInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetCblInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetCblInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetCblInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetCblInformation parseFrom(InputStream inputStream) throws IOException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCblInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCblInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetCblInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCblInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetCblInformationOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class GetCblLoginState extends GeneratedMessageLite<GetCblLoginState, Builder> implements GetCblLoginStateOrBuilder {
        private static final GetCblLoginState DEFAULT_INSTANCE = new GetCblLoginState();
        private static volatile Parser<GetCblLoginState> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetCblLoginState, Builder> implements GetCblLoginStateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(GetCblLoginState.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetCblLoginState() {
        }

        public static GetCblLoginState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetCblLoginState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetCblLoginState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCblLoginState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetCblLoginState> parser() {
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
                    GetCblLoginState getCblLoginState = (GetCblLoginState) obj2;
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
                    return new GetCblLoginState();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetCblLoginState.class) {
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

        public static Builder newBuilder(GetCblLoginState getCblLoginState) {
            return DEFAULT_INSTANCE.createBuilder(getCblLoginState);
        }

        public static GetCblLoginState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCblLoginState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCblLoginState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetCblLoginState parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetCblLoginState parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetCblLoginState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetCblLoginState parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetCblLoginState parseFrom(InputStream inputStream) throws IOException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCblLoginState parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCblLoginState parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetCblLoginState parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetCblLoginStateOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class NotifyCblLoginState extends GeneratedMessageLite<NotifyCblLoginState, Builder> implements NotifyCblLoginStateOrBuilder {
        private static final NotifyCblLoginState DEFAULT_INSTANCE = new NotifyCblLoginState();
        public static final int LOGIN_STATE_FIELD_NUMBER = 1;
        private static volatile Parser<NotifyCblLoginState> PARSER;
        private CblLoginState loginState_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotifyCblLoginState, Builder> implements NotifyCblLoginStateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearLoginState() {
                copyOnWrite();
                ((NotifyCblLoginState) this.instance).clearLoginState();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cbl.NotifyCblLoginStateOrBuilder
            public CblLoginState getLoginState() {
                return ((NotifyCblLoginState) this.instance).getLoginState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cbl.NotifyCblLoginStateOrBuilder
            public boolean hasLoginState() {
                return ((NotifyCblLoginState) this.instance).hasLoginState();
            }

            public Builder mergeLoginState(CblLoginState cblLoginState) {
                copyOnWrite();
                ((NotifyCblLoginState) this.instance).mergeLoginState(cblLoginState);
                return this;
            }

            public Builder setLoginState(CblLoginState cblLoginState) {
                copyOnWrite();
                ((NotifyCblLoginState) this.instance).setLoginState(cblLoginState);
                return this;
            }

            private Builder() {
                super(NotifyCblLoginState.DEFAULT_INSTANCE);
            }

            public Builder setLoginState(CblLoginState.Builder builder) {
                copyOnWrite();
                ((NotifyCblLoginState) this.instance).setLoginState(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotifyCblLoginState() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLoginState() {
            this.loginState_ = null;
        }

        public static NotifyCblLoginState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLoginState(CblLoginState cblLoginState) {
            CblLoginState cblLoginState2 = this.loginState_;
            if (cblLoginState2 != null && cblLoginState2 != CblLoginState.getDefaultInstance()) {
                this.loginState_ = CblLoginState.newBuilder(this.loginState_).mergeFrom((CblLoginState.Builder) cblLoginState).mo10085buildPartial();
            } else {
                this.loginState_ = cblLoginState;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotifyCblLoginState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCblLoginState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotifyCblLoginState> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLoginState(CblLoginState cblLoginState) {
            if (cblLoginState != null) {
                this.loginState_ = cblLoginState;
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
                    this.loginState_ = (CblLoginState) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.loginState_, ((NotifyCblLoginState) obj2).loginState_);
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
                                    CblLoginState.Builder mo10081toBuilder = this.loginState_ != null ? this.loginState_.mo10081toBuilder() : null;
                                    this.loginState_ = (CblLoginState) codedInputStream.readMessage(CblLoginState.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((CblLoginState.Builder) this.loginState_);
                                        this.loginState_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new NotifyCblLoginState();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotifyCblLoginState.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cbl.NotifyCblLoginStateOrBuilder
        public CblLoginState getLoginState() {
            CblLoginState cblLoginState = this.loginState_;
            return cblLoginState == null ? CblLoginState.getDefaultInstance() : cblLoginState;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.loginState_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getLoginState());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cbl.NotifyCblLoginStateOrBuilder
        public boolean hasLoginState() {
            return this.loginState_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.loginState_ != null) {
                codedOutputStream.writeMessage(1, getLoginState());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotifyCblLoginState notifyCblLoginState) {
            return DEFAULT_INSTANCE.createBuilder(notifyCblLoginState);
        }

        public static NotifyCblLoginState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyCblLoginState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotifyCblLoginState parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLoginState(CblLoginState.Builder builder) {
            this.loginState_ = builder.mo10084build();
        }

        public static NotifyCblLoginState parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NotifyCblLoginState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyCblLoginState parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotifyCblLoginState parseFrom(InputStream inputStream) throws IOException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCblLoginState parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyCblLoginState parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotifyCblLoginState parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyCblLoginState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotifyCblLoginStateOrBuilder extends MessageLiteOrBuilder {
        CblLoginState getLoginState();

        boolean hasLoginState();
    }

    private Cbl() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
