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
public final class Nonhfpcalling {

    /* renamed from: com.amazon.alexa.accessory.protocol.Nonhfpcalling$1  reason: invalid class name */
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
    public static final class AcceptCall extends GeneratedMessageLite<AcceptCall, Builder> implements AcceptCallOrBuilder {
        public static final int CALL_UUID_FIELD_NUMBER = 1;
        private static final AcceptCall DEFAULT_INSTANCE = new AcceptCall();
        private static volatile Parser<AcceptCall> PARSER;
        private ByteString callUuid_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<AcceptCall, Builder> implements AcceptCallOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCallUuid() {
                copyOnWrite();
                ((AcceptCall) this.instance).clearCallUuid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.AcceptCallOrBuilder
            public ByteString getCallUuid() {
                return ((AcceptCall) this.instance).getCallUuid();
            }

            public Builder setCallUuid(ByteString byteString) {
                copyOnWrite();
                ((AcceptCall) this.instance).setCallUuid(byteString);
                return this;
            }

            private Builder() {
                super(AcceptCall.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private AcceptCall() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallUuid() {
            this.callUuid_ = getDefaultInstance().getCallUuid();
        }

        public static AcceptCall getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static AcceptCall parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AcceptCall) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AcceptCall parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<AcceptCall> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallUuid(ByteString byteString) {
            if (byteString != null) {
                this.callUuid_ = byteString;
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
                    AcceptCall acceptCall = (AcceptCall) obj2;
                    boolean z2 = this.callUuid_ != ByteString.EMPTY;
                    ByteString byteString = this.callUuid_;
                    if (acceptCall.callUuid_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.callUuid_ = visitor.visitByteString(z2, byteString, z, acceptCall.callUuid_);
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
                                    if (readTag != 10) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.callUuid_ = codedInputStream.readBytes();
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
                    return new AcceptCall();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (AcceptCall.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.AcceptCallOrBuilder
        public ByteString getCallUuid() {
            return this.callUuid_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.callUuid_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.callUuid_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.callUuid_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.callUuid_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(AcceptCall acceptCall) {
            return DEFAULT_INSTANCE.createBuilder(acceptCall);
        }

        public static AcceptCall parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AcceptCall) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AcceptCall parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static AcceptCall parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AcceptCall parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AcceptCall parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AcceptCall parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AcceptCall parseFrom(InputStream inputStream) throws IOException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AcceptCall parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AcceptCall parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AcceptCall parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AcceptCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface AcceptCallOrBuilder extends MessageLiteOrBuilder {
        ByteString getCallUuid();
    }

    /* loaded from: classes6.dex */
    public static final class CallDetails extends GeneratedMessageLite<CallDetails, Builder> implements CallDetailsOrBuilder {
        public static final int CALLER_DISPLAY_NAME_FIELD_NUMBER = 5;
        public static final int CALLER_NUMBER_FIELD_NUMBER = 6;
        public static final int CONNECTION_TIMESTAMP_MILLISECONDS_HI_FIELD_NUMBER = 1;
        public static final int CONNECTION_TIMESTAMP_MILLISECONDS_LO_FIELD_NUMBER = 2;
        private static final CallDetails DEFAULT_INSTANCE = new CallDetails();
        public static final int LAST_UPDATE_TIMESTAMP_MILLISECONDS_HI_FIELD_NUMBER = 3;
        public static final int LAST_UPDATE_TIMESTAMP_MILLISECONDS_LO_FIELD_NUMBER = 4;
        private static volatile Parser<CallDetails> PARSER;
        private String callerDisplayName_ = "";
        private String callerNumber_ = "";
        private int connectionTimestampMillisecondsHi_;
        private int connectionTimestampMillisecondsLo_;
        private int lastUpdateTimestampMillisecondsHi_;
        private int lastUpdateTimestampMillisecondsLo_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CallDetails, Builder> implements CallDetailsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCallerDisplayName() {
                copyOnWrite();
                ((CallDetails) this.instance).clearCallerDisplayName();
                return this;
            }

            public Builder clearCallerNumber() {
                copyOnWrite();
                ((CallDetails) this.instance).clearCallerNumber();
                return this;
            }

            public Builder clearConnectionTimestampMillisecondsHi() {
                copyOnWrite();
                ((CallDetails) this.instance).clearConnectionTimestampMillisecondsHi();
                return this;
            }

            public Builder clearConnectionTimestampMillisecondsLo() {
                copyOnWrite();
                ((CallDetails) this.instance).clearConnectionTimestampMillisecondsLo();
                return this;
            }

            public Builder clearLastUpdateTimestampMillisecondsHi() {
                copyOnWrite();
                ((CallDetails) this.instance).clearLastUpdateTimestampMillisecondsHi();
                return this;
            }

            public Builder clearLastUpdateTimestampMillisecondsLo() {
                copyOnWrite();
                ((CallDetails) this.instance).clearLastUpdateTimestampMillisecondsLo();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
            public String getCallerDisplayName() {
                return ((CallDetails) this.instance).getCallerDisplayName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
            public ByteString getCallerDisplayNameBytes() {
                return ((CallDetails) this.instance).getCallerDisplayNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
            public String getCallerNumber() {
                return ((CallDetails) this.instance).getCallerNumber();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
            public ByteString getCallerNumberBytes() {
                return ((CallDetails) this.instance).getCallerNumberBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
            public int getConnectionTimestampMillisecondsHi() {
                return ((CallDetails) this.instance).getConnectionTimestampMillisecondsHi();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
            public int getConnectionTimestampMillisecondsLo() {
                return ((CallDetails) this.instance).getConnectionTimestampMillisecondsLo();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
            public int getLastUpdateTimestampMillisecondsHi() {
                return ((CallDetails) this.instance).getLastUpdateTimestampMillisecondsHi();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
            public int getLastUpdateTimestampMillisecondsLo() {
                return ((CallDetails) this.instance).getLastUpdateTimestampMillisecondsLo();
            }

            public Builder setCallerDisplayName(String str) {
                copyOnWrite();
                ((CallDetails) this.instance).setCallerDisplayName(str);
                return this;
            }

            public Builder setCallerDisplayNameBytes(ByteString byteString) {
                copyOnWrite();
                ((CallDetails) this.instance).setCallerDisplayNameBytes(byteString);
                return this;
            }

            public Builder setCallerNumber(String str) {
                copyOnWrite();
                ((CallDetails) this.instance).setCallerNumber(str);
                return this;
            }

            public Builder setCallerNumberBytes(ByteString byteString) {
                copyOnWrite();
                ((CallDetails) this.instance).setCallerNumberBytes(byteString);
                return this;
            }

            public Builder setConnectionTimestampMillisecondsHi(int i) {
                copyOnWrite();
                ((CallDetails) this.instance).setConnectionTimestampMillisecondsHi(i);
                return this;
            }

            public Builder setConnectionTimestampMillisecondsLo(int i) {
                copyOnWrite();
                ((CallDetails) this.instance).setConnectionTimestampMillisecondsLo(i);
                return this;
            }

            public Builder setLastUpdateTimestampMillisecondsHi(int i) {
                copyOnWrite();
                ((CallDetails) this.instance).setLastUpdateTimestampMillisecondsHi(i);
                return this;
            }

            public Builder setLastUpdateTimestampMillisecondsLo(int i) {
                copyOnWrite();
                ((CallDetails) this.instance).setLastUpdateTimestampMillisecondsLo(i);
                return this;
            }

            private Builder() {
                super(CallDetails.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CallDetails() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallerDisplayName() {
            this.callerDisplayName_ = getDefaultInstance().getCallerDisplayName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallerNumber() {
            this.callerNumber_ = getDefaultInstance().getCallerNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConnectionTimestampMillisecondsHi() {
            this.connectionTimestampMillisecondsHi_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConnectionTimestampMillisecondsLo() {
            this.connectionTimestampMillisecondsLo_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLastUpdateTimestampMillisecondsHi() {
            this.lastUpdateTimestampMillisecondsHi_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLastUpdateTimestampMillisecondsLo() {
            this.lastUpdateTimestampMillisecondsLo_ = 0;
        }

        public static CallDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CallDetails parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CallDetails) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CallDetails parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CallDetails> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallerDisplayName(String str) {
            if (str != null) {
                this.callerDisplayName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallerDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.callerDisplayName_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallerNumber(String str) {
            if (str != null) {
                this.callerNumber_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallerNumberBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.callerNumber_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectionTimestampMillisecondsHi(int i) {
            this.connectionTimestampMillisecondsHi_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectionTimestampMillisecondsLo(int i) {
            this.connectionTimestampMillisecondsLo_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLastUpdateTimestampMillisecondsHi(int i) {
            this.lastUpdateTimestampMillisecondsHi_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLastUpdateTimestampMillisecondsLo(int i) {
            this.lastUpdateTimestampMillisecondsLo_ = i;
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
                    CallDetails callDetails = (CallDetails) obj2;
                    this.connectionTimestampMillisecondsHi_ = visitor.visitInt(this.connectionTimestampMillisecondsHi_ != 0, this.connectionTimestampMillisecondsHi_, callDetails.connectionTimestampMillisecondsHi_ != 0, callDetails.connectionTimestampMillisecondsHi_);
                    this.connectionTimestampMillisecondsLo_ = visitor.visitInt(this.connectionTimestampMillisecondsLo_ != 0, this.connectionTimestampMillisecondsLo_, callDetails.connectionTimestampMillisecondsLo_ != 0, callDetails.connectionTimestampMillisecondsLo_);
                    this.lastUpdateTimestampMillisecondsHi_ = visitor.visitInt(this.lastUpdateTimestampMillisecondsHi_ != 0, this.lastUpdateTimestampMillisecondsHi_, callDetails.lastUpdateTimestampMillisecondsHi_ != 0, callDetails.lastUpdateTimestampMillisecondsHi_);
                    boolean z2 = this.lastUpdateTimestampMillisecondsLo_ != 0;
                    int i = this.lastUpdateTimestampMillisecondsLo_;
                    if (callDetails.lastUpdateTimestampMillisecondsLo_ != 0) {
                        z = true;
                    }
                    this.lastUpdateTimestampMillisecondsLo_ = visitor.visitInt(z2, i, z, callDetails.lastUpdateTimestampMillisecondsLo_);
                    this.callerDisplayName_ = visitor.visitString(!this.callerDisplayName_.isEmpty(), this.callerDisplayName_, !callDetails.callerDisplayName_.isEmpty(), callDetails.callerDisplayName_);
                    this.callerNumber_ = visitor.visitString(!this.callerNumber_.isEmpty(), this.callerNumber_, !callDetails.callerNumber_.isEmpty(), callDetails.callerNumber_);
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
                                    this.connectionTimestampMillisecondsHi_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.connectionTimestampMillisecondsLo_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.lastUpdateTimestampMillisecondsHi_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.lastUpdateTimestampMillisecondsLo_ = codedInputStream.readUInt32();
                                } else if (readTag == 42) {
                                    this.callerDisplayName_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 50) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.callerNumber_ = codedInputStream.readStringRequireUtf8();
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
                    return new CallDetails();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CallDetails.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
        public String getCallerDisplayName() {
            return this.callerDisplayName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
        public ByteString getCallerDisplayNameBytes() {
            return ByteString.copyFromUtf8(this.callerDisplayName_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
        public String getCallerNumber() {
            return this.callerNumber_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
        public ByteString getCallerNumberBytes() {
            return ByteString.copyFromUtf8(this.callerNumber_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
        public int getConnectionTimestampMillisecondsHi() {
            return this.connectionTimestampMillisecondsHi_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
        public int getConnectionTimestampMillisecondsLo() {
            return this.connectionTimestampMillisecondsLo_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
        public int getLastUpdateTimestampMillisecondsHi() {
            return this.lastUpdateTimestampMillisecondsHi_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.CallDetailsOrBuilder
        public int getLastUpdateTimestampMillisecondsLo() {
            return this.lastUpdateTimestampMillisecondsLo_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.connectionTimestampMillisecondsHi_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int i4 = this.connectionTimestampMillisecondsLo_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
            }
            int i5 = this.lastUpdateTimestampMillisecondsHi_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i5);
            }
            int i6 = this.lastUpdateTimestampMillisecondsLo_;
            if (i6 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(4, i6);
            }
            if (!this.callerDisplayName_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(5, getCallerDisplayName());
            }
            if (!this.callerNumber_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(6, getCallerNumber());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.connectionTimestampMillisecondsHi_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.connectionTimestampMillisecondsLo_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            int i3 = this.lastUpdateTimestampMillisecondsHi_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(3, i3);
            }
            int i4 = this.lastUpdateTimestampMillisecondsLo_;
            if (i4 != 0) {
                codedOutputStream.writeUInt32(4, i4);
            }
            if (!this.callerDisplayName_.isEmpty()) {
                codedOutputStream.writeString(5, getCallerDisplayName());
            }
            if (!this.callerNumber_.isEmpty()) {
                codedOutputStream.writeString(6, getCallerNumber());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CallDetails callDetails) {
            return DEFAULT_INSTANCE.createBuilder(callDetails);
        }

        public static CallDetails parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallDetails) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CallDetails parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CallDetails parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CallDetails parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CallDetails parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CallDetails parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CallDetails parseFrom(InputStream inputStream) throws IOException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CallDetails parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CallDetails parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CallDetails parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CallDetailsOrBuilder extends MessageLiteOrBuilder {
        String getCallerDisplayName();

        ByteString getCallerDisplayNameBytes();

        String getCallerNumber();

        ByteString getCallerNumberBytes();

        int getConnectionTimestampMillisecondsHi();

        int getConnectionTimestampMillisecondsLo();

        int getLastUpdateTimestampMillisecondsHi();

        int getLastUpdateTimestampMillisecondsLo();
    }

    /* loaded from: classes6.dex */
    public static final class EndCall extends GeneratedMessageLite<EndCall, Builder> implements EndCallOrBuilder {
        public static final int CALL_UUID_FIELD_NUMBER = 1;
        private static final EndCall DEFAULT_INSTANCE = new EndCall();
        private static volatile Parser<EndCall> PARSER;
        private ByteString callUuid_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<EndCall, Builder> implements EndCallOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCallUuid() {
                copyOnWrite();
                ((EndCall) this.instance).clearCallUuid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.EndCallOrBuilder
            public ByteString getCallUuid() {
                return ((EndCall) this.instance).getCallUuid();
            }

            public Builder setCallUuid(ByteString byteString) {
                copyOnWrite();
                ((EndCall) this.instance).setCallUuid(byteString);
                return this;
            }

            private Builder() {
                super(EndCall.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private EndCall() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallUuid() {
            this.callUuid_ = getDefaultInstance().getCallUuid();
        }

        public static EndCall getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static EndCall parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (EndCall) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static EndCall parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<EndCall> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallUuid(ByteString byteString) {
            if (byteString != null) {
                this.callUuid_ = byteString;
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
                    EndCall endCall = (EndCall) obj2;
                    boolean z2 = this.callUuid_ != ByteString.EMPTY;
                    ByteString byteString = this.callUuid_;
                    if (endCall.callUuid_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.callUuid_ = visitor.visitByteString(z2, byteString, z, endCall.callUuid_);
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
                                    if (readTag != 10) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.callUuid_ = codedInputStream.readBytes();
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
                    return new EndCall();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (EndCall.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.EndCallOrBuilder
        public ByteString getCallUuid() {
            return this.callUuid_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.callUuid_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.callUuid_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.callUuid_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.callUuid_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(EndCall endCall) {
            return DEFAULT_INSTANCE.createBuilder(endCall);
        }

        public static EndCall parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EndCall) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static EndCall parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static EndCall parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static EndCall parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static EndCall parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static EndCall parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static EndCall parseFrom(InputStream inputStream) throws IOException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static EndCall parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static EndCall parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static EndCall parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EndCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface EndCallOrBuilder extends MessageLiteOrBuilder {
        ByteString getCallUuid();
    }

    /* loaded from: classes6.dex */
    public static final class RejectCall extends GeneratedMessageLite<RejectCall, Builder> implements RejectCallOrBuilder {
        public static final int CALL_UUID_FIELD_NUMBER = 1;
        private static final RejectCall DEFAULT_INSTANCE = new RejectCall();
        private static volatile Parser<RejectCall> PARSER;
        private ByteString callUuid_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<RejectCall, Builder> implements RejectCallOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCallUuid() {
                copyOnWrite();
                ((RejectCall) this.instance).clearCallUuid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.RejectCallOrBuilder
            public ByteString getCallUuid() {
                return ((RejectCall) this.instance).getCallUuid();
            }

            public Builder setCallUuid(ByteString byteString) {
                copyOnWrite();
                ((RejectCall) this.instance).setCallUuid(byteString);
                return this;
            }

            private Builder() {
                super(RejectCall.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private RejectCall() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallUuid() {
            this.callUuid_ = getDefaultInstance().getCallUuid();
        }

        public static RejectCall getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static RejectCall parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RejectCall) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RejectCall parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<RejectCall> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallUuid(ByteString byteString) {
            if (byteString != null) {
                this.callUuid_ = byteString;
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
                    RejectCall rejectCall = (RejectCall) obj2;
                    boolean z2 = this.callUuid_ != ByteString.EMPTY;
                    ByteString byteString = this.callUuid_;
                    if (rejectCall.callUuid_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.callUuid_ = visitor.visitByteString(z2, byteString, z, rejectCall.callUuid_);
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
                                    if (readTag != 10) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.callUuid_ = codedInputStream.readBytes();
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
                    return new RejectCall();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (RejectCall.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.RejectCallOrBuilder
        public ByteString getCallUuid() {
            return this.callUuid_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.callUuid_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.callUuid_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.callUuid_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.callUuid_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(RejectCall rejectCall) {
            return DEFAULT_INSTANCE.createBuilder(rejectCall);
        }

        public static RejectCall parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RejectCall) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RejectCall parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static RejectCall parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static RejectCall parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static RejectCall parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static RejectCall parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static RejectCall parseFrom(InputStream inputStream) throws IOException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RejectCall parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RejectCall parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static RejectCall parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RejectCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface RejectCallOrBuilder extends MessageLiteOrBuilder {
        ByteString getCallUuid();
    }

    /* loaded from: classes6.dex */
    public static final class UpdateCallState extends GeneratedMessageLite<UpdateCallState, Builder> implements UpdateCallStateOrBuilder {
        public static final int CALL_DETAILS_FIELD_NUMBER = 4;
        public static final int CALL_DIRECTION_FIELD_NUMBER = 2;
        public static final int CALL_STATUS_FIELD_NUMBER = 3;
        public static final int CALL_UUID_FIELD_NUMBER = 1;
        private static final UpdateCallState DEFAULT_INSTANCE = new UpdateCallState();
        private static volatile Parser<UpdateCallState> PARSER;
        private CallDetails callDetails_;
        private int callDirection_;
        private int callStatus_;
        private ByteString callUuid_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UpdateCallState, Builder> implements UpdateCallStateOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCallDetails() {
                copyOnWrite();
                ((UpdateCallState) this.instance).clearCallDetails();
                return this;
            }

            public Builder clearCallDirection() {
                copyOnWrite();
                ((UpdateCallState) this.instance).clearCallDirection();
                return this;
            }

            public Builder clearCallStatus() {
                copyOnWrite();
                ((UpdateCallState) this.instance).clearCallStatus();
                return this;
            }

            public Builder clearCallUuid() {
                copyOnWrite();
                ((UpdateCallState) this.instance).clearCallUuid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
            public CallDetails getCallDetails() {
                return ((UpdateCallState) this.instance).getCallDetails();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
            public CallDirection getCallDirection() {
                return ((UpdateCallState) this.instance).getCallDirection();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
            public int getCallDirectionValue() {
                return ((UpdateCallState) this.instance).getCallDirectionValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
            public CallStatus getCallStatus() {
                return ((UpdateCallState) this.instance).getCallStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
            public int getCallStatusValue() {
                return ((UpdateCallState) this.instance).getCallStatusValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
            public ByteString getCallUuid() {
                return ((UpdateCallState) this.instance).getCallUuid();
            }

            @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
            public boolean hasCallDetails() {
                return ((UpdateCallState) this.instance).hasCallDetails();
            }

            public Builder mergeCallDetails(CallDetails callDetails) {
                copyOnWrite();
                ((UpdateCallState) this.instance).mergeCallDetails(callDetails);
                return this;
            }

            public Builder setCallDetails(CallDetails callDetails) {
                copyOnWrite();
                ((UpdateCallState) this.instance).setCallDetails(callDetails);
                return this;
            }

            public Builder setCallDirection(CallDirection callDirection) {
                copyOnWrite();
                ((UpdateCallState) this.instance).setCallDirection(callDirection);
                return this;
            }

            public Builder setCallDirectionValue(int i) {
                copyOnWrite();
                ((UpdateCallState) this.instance).setCallDirectionValue(i);
                return this;
            }

            public Builder setCallStatus(CallStatus callStatus) {
                copyOnWrite();
                ((UpdateCallState) this.instance).setCallStatus(callStatus);
                return this;
            }

            public Builder setCallStatusValue(int i) {
                copyOnWrite();
                ((UpdateCallState) this.instance).setCallStatusValue(i);
                return this;
            }

            public Builder setCallUuid(ByteString byteString) {
                copyOnWrite();
                ((UpdateCallState) this.instance).setCallUuid(byteString);
                return this;
            }

            private Builder() {
                super(UpdateCallState.DEFAULT_INSTANCE);
            }

            public Builder setCallDetails(CallDetails.Builder builder) {
                copyOnWrite();
                ((UpdateCallState) this.instance).setCallDetails(builder);
                return this;
            }
        }

        /* loaded from: classes6.dex */
        public enum CallDirection implements Internal.EnumLite {
            UNKNOWN(0),
            INCOMING(1),
            OUTGOING(2),
            UNRECOGNIZED(-1);
            
            public static final int INCOMING_VALUE = 1;
            public static final int OUTGOING_VALUE = 2;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<CallDirection> internalValueMap = new Internal.EnumLiteMap<CallDirection>() { // from class: com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallState.CallDirection.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public CallDirection mo9850findValueByNumber(int i) {
                    return CallDirection.forNumber(i);
                }
            };
            private final int value;

            CallDirection(int i) {
                this.value = i;
            }

            public static CallDirection forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return INCOMING;
                    }
                    if (i == 2) {
                        return OUTGOING;
                    }
                    return null;
                }
                return UNKNOWN;
            }

            public static Internal.EnumLiteMap<CallDirection> internalGetValueMap() {
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
            public static CallDirection valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public enum CallStatus implements Internal.EnumLite {
            IDLE(0),
            DIALING(1),
            RINGING(2),
            ACTIVE(3),
            HOLDING(4),
            DISCONNECTING(5),
            DISCONNECTED(6),
            UNRECOGNIZED(-1);
            
            public static final int ACTIVE_VALUE = 3;
            public static final int DIALING_VALUE = 1;
            public static final int DISCONNECTED_VALUE = 6;
            public static final int DISCONNECTING_VALUE = 5;
            public static final int HOLDING_VALUE = 4;
            public static final int IDLE_VALUE = 0;
            public static final int RINGING_VALUE = 2;
            private static final Internal.EnumLiteMap<CallStatus> internalValueMap = new Internal.EnumLiteMap<CallStatus>() { // from class: com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallState.CallStatus.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public CallStatus mo9850findValueByNumber(int i) {
                    return CallStatus.forNumber(i);
                }
            };
            private final int value;

            CallStatus(int i) {
                this.value = i;
            }

            public static CallStatus forNumber(int i) {
                switch (i) {
                    case 0:
                        return IDLE;
                    case 1:
                        return DIALING;
                    case 2:
                        return RINGING;
                    case 3:
                        return ACTIVE;
                    case 4:
                        return HOLDING;
                    case 5:
                        return DISCONNECTING;
                    case 6:
                        return DISCONNECTED;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<CallStatus> internalGetValueMap() {
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
            public static CallStatus valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UpdateCallState() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallDetails() {
            this.callDetails_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallDirection() {
            this.callDirection_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallStatus() {
            this.callStatus_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallUuid() {
            this.callUuid_ = getDefaultInstance().getCallUuid();
        }

        public static UpdateCallState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCallDetails(CallDetails callDetails) {
            CallDetails callDetails2 = this.callDetails_;
            if (callDetails2 != null && callDetails2 != CallDetails.getDefaultInstance()) {
                this.callDetails_ = CallDetails.newBuilder(this.callDetails_).mergeFrom((CallDetails.Builder) callDetails).mo10085buildPartial();
            } else {
                this.callDetails_ = callDetails;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UpdateCallState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateCallState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateCallState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UpdateCallState> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallDetails(CallDetails callDetails) {
            if (callDetails != null) {
                this.callDetails_ = callDetails;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallDirection(CallDirection callDirection) {
            if (callDirection != null) {
                this.callDirection_ = callDirection.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallDirectionValue(int i) {
            this.callDirection_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallStatus(CallStatus callStatus) {
            if (callStatus != null) {
                this.callStatus_ = callStatus.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallStatusValue(int i) {
            this.callStatus_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallUuid(ByteString byteString) {
            if (byteString != null) {
                this.callUuid_ = byteString;
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
                    UpdateCallState updateCallState = (UpdateCallState) obj2;
                    this.callUuid_ = visitor.visitByteString(this.callUuid_ != ByteString.EMPTY, this.callUuid_, updateCallState.callUuid_ != ByteString.EMPTY, updateCallState.callUuid_);
                    this.callDirection_ = visitor.visitInt(this.callDirection_ != 0, this.callDirection_, updateCallState.callDirection_ != 0, updateCallState.callDirection_);
                    boolean z2 = this.callStatus_ != 0;
                    int i = this.callStatus_;
                    if (updateCallState.callStatus_ != 0) {
                        z = true;
                    }
                    this.callStatus_ = visitor.visitInt(z2, i, z, updateCallState.callStatus_);
                    this.callDetails_ = (CallDetails) visitor.visitMessage(this.callDetails_, updateCallState.callDetails_);
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
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.callUuid_ = codedInputStream.readBytes();
                                } else if (readTag == 16) {
                                    this.callDirection_ = codedInputStream.readEnum();
                                } else if (readTag == 24) {
                                    this.callStatus_ = codedInputStream.readEnum();
                                } else if (readTag != 34) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    CallDetails.Builder mo10081toBuilder = this.callDetails_ != null ? this.callDetails_.mo10081toBuilder() : null;
                                    this.callDetails_ = (CallDetails) codedInputStream.readMessage(CallDetails.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((CallDetails.Builder) this.callDetails_);
                                        this.callDetails_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new UpdateCallState();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UpdateCallState.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
        public CallDetails getCallDetails() {
            CallDetails callDetails = this.callDetails_;
            return callDetails == null ? CallDetails.getDefaultInstance() : callDetails;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
        public CallDirection getCallDirection() {
            CallDirection forNumber = CallDirection.forNumber(this.callDirection_);
            return forNumber == null ? CallDirection.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
        public int getCallDirectionValue() {
            return this.callDirection_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
        public CallStatus getCallStatus() {
            CallStatus forNumber = CallStatus.forNumber(this.callStatus_);
            return forNumber == null ? CallStatus.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
        public int getCallStatusValue() {
            return this.callStatus_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
        public ByteString getCallUuid() {
            return this.callUuid_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.callUuid_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.callUuid_);
            }
            if (this.callDirection_ != CallDirection.UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.callDirection_);
            }
            if (this.callStatus_ != CallStatus.IDLE.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.callStatus_);
            }
            if (this.callDetails_ != null) {
                i2 += CodedOutputStream.computeMessageSize(4, getCallDetails());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Nonhfpcalling.UpdateCallStateOrBuilder
        public boolean hasCallDetails() {
            return this.callDetails_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.callUuid_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.callUuid_);
            }
            if (this.callDirection_ != CallDirection.UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(2, this.callDirection_);
            }
            if (this.callStatus_ != CallStatus.IDLE.getNumber()) {
                codedOutputStream.writeEnum(3, this.callStatus_);
            }
            if (this.callDetails_ != null) {
                codedOutputStream.writeMessage(4, getCallDetails());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UpdateCallState updateCallState) {
            return DEFAULT_INSTANCE.createBuilder(updateCallState);
        }

        public static UpdateCallState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateCallState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateCallState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UpdateCallState parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallDetails(CallDetails.Builder builder) {
            this.callDetails_ = builder.mo10084build();
        }

        public static UpdateCallState parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UpdateCallState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UpdateCallState parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UpdateCallState parseFrom(InputStream inputStream) throws IOException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateCallState parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateCallState parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UpdateCallState parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateCallState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UpdateCallStateOrBuilder extends MessageLiteOrBuilder {
        CallDetails getCallDetails();

        UpdateCallState.CallDirection getCallDirection();

        int getCallDirectionValue();

        UpdateCallState.CallStatus getCallStatus();

        int getCallStatusValue();

        ByteString getCallUuid();

        boolean hasCallDetails();
    }

    private Nonhfpcalling() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
