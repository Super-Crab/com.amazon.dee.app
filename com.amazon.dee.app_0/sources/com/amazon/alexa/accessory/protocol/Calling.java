package com.amazon.alexa.accessory.protocol;

import com.google.protobuf.AbstractMessageLite;
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
public final class Calling {

    /* renamed from: com.amazon.alexa.accessory.protocol.Calling$1  reason: invalid class name */
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
    public static final class ForwardATCommand extends GeneratedMessageLite<ForwardATCommand, Builder> implements ForwardATCommandOrBuilder {
        public static final int COMMAND_FIELD_NUMBER = 1;
        private static final ForwardATCommand DEFAULT_INSTANCE = new ForwardATCommand();
        private static volatile Parser<ForwardATCommand> PARSER;
        private String command_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ForwardATCommand, Builder> implements ForwardATCommandOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCommand() {
                copyOnWrite();
                ((ForwardATCommand) this.instance).clearCommand();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Calling.ForwardATCommandOrBuilder
            public String getCommand() {
                return ((ForwardATCommand) this.instance).getCommand();
            }

            @Override // com.amazon.alexa.accessory.protocol.Calling.ForwardATCommandOrBuilder
            public ByteString getCommandBytes() {
                return ((ForwardATCommand) this.instance).getCommandBytes();
            }

            public Builder setCommand(String str) {
                copyOnWrite();
                ((ForwardATCommand) this.instance).setCommand(str);
                return this;
            }

            public Builder setCommandBytes(ByteString byteString) {
                copyOnWrite();
                ((ForwardATCommand) this.instance).setCommandBytes(byteString);
                return this;
            }

            private Builder() {
                super(ForwardATCommand.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ForwardATCommand() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCommand() {
            this.command_ = getDefaultInstance().getCommand();
        }

        public static ForwardATCommand getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ForwardATCommand parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ForwardATCommand) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ForwardATCommand parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ForwardATCommand> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCommand(String str) {
            if (str != null) {
                this.command_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCommandBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.command_ = byteString.toStringUtf8();
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
                    ForwardATCommand forwardATCommand = (ForwardATCommand) obj2;
                    this.command_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.command_.isEmpty(), this.command_, true ^ forwardATCommand.command_.isEmpty(), forwardATCommand.command_);
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
                                        this.command_ = codedInputStream.readStringRequireUtf8();
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
                    return new ForwardATCommand();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ForwardATCommand.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Calling.ForwardATCommandOrBuilder
        public String getCommand() {
            return this.command_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Calling.ForwardATCommandOrBuilder
        public ByteString getCommandBytes() {
            return ByteString.copyFromUtf8(this.command_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.command_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getCommand());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.command_.isEmpty()) {
                codedOutputStream.writeString(1, getCommand());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ForwardATCommand forwardATCommand) {
            return DEFAULT_INSTANCE.createBuilder(forwardATCommand);
        }

        public static ForwardATCommand parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ForwardATCommand) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ForwardATCommand parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ForwardATCommand parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ForwardATCommand parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ForwardATCommand parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ForwardATCommand parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ForwardATCommand parseFrom(InputStream inputStream) throws IOException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ForwardATCommand parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ForwardATCommand parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ForwardATCommand parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ForwardATCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ForwardATCommandOrBuilder extends MessageLiteOrBuilder {
        String getCommand();

        ByteString getCommandBytes();
    }

    /* loaded from: classes6.dex */
    public static final class IncomingCall extends GeneratedMessageLite<IncomingCall, Builder> implements IncomingCallOrBuilder {
        public static final int CALLER_NAME_FIELD_NUMBER = 2;
        public static final int CALLER_NUMBER_FIELD_NUMBER = 1;
        private static final IncomingCall DEFAULT_INSTANCE = new IncomingCall();
        private static volatile Parser<IncomingCall> PARSER;
        private String callerNumber_ = "";
        private String callerName_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<IncomingCall, Builder> implements IncomingCallOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCallerName() {
                copyOnWrite();
                ((IncomingCall) this.instance).clearCallerName();
                return this;
            }

            public Builder clearCallerNumber() {
                copyOnWrite();
                ((IncomingCall) this.instance).clearCallerNumber();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Calling.IncomingCallOrBuilder
            public String getCallerName() {
                return ((IncomingCall) this.instance).getCallerName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Calling.IncomingCallOrBuilder
            public ByteString getCallerNameBytes() {
                return ((IncomingCall) this.instance).getCallerNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Calling.IncomingCallOrBuilder
            public String getCallerNumber() {
                return ((IncomingCall) this.instance).getCallerNumber();
            }

            @Override // com.amazon.alexa.accessory.protocol.Calling.IncomingCallOrBuilder
            public ByteString getCallerNumberBytes() {
                return ((IncomingCall) this.instance).getCallerNumberBytes();
            }

            public Builder setCallerName(String str) {
                copyOnWrite();
                ((IncomingCall) this.instance).setCallerName(str);
                return this;
            }

            public Builder setCallerNameBytes(ByteString byteString) {
                copyOnWrite();
                ((IncomingCall) this.instance).setCallerNameBytes(byteString);
                return this;
            }

            public Builder setCallerNumber(String str) {
                copyOnWrite();
                ((IncomingCall) this.instance).setCallerNumber(str);
                return this;
            }

            public Builder setCallerNumberBytes(ByteString byteString) {
                copyOnWrite();
                ((IncomingCall) this.instance).setCallerNumberBytes(byteString);
                return this;
            }

            private Builder() {
                super(IncomingCall.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private IncomingCall() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallerName() {
            this.callerName_ = getDefaultInstance().getCallerName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallerNumber() {
            this.callerNumber_ = getDefaultInstance().getCallerNumber();
        }

        public static IncomingCall getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static IncomingCall parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (IncomingCall) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IncomingCall parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<IncomingCall> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallerName(String str) {
            if (str != null) {
                this.callerName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallerNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.callerName_ = byteString.toStringUtf8();
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

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    IncomingCall incomingCall = (IncomingCall) obj2;
                    this.callerNumber_ = visitor.visitString(!this.callerNumber_.isEmpty(), this.callerNumber_, !incomingCall.callerNumber_.isEmpty(), incomingCall.callerNumber_);
                    this.callerName_ = visitor.visitString(!this.callerName_.isEmpty(), this.callerName_, true ^ incomingCall.callerName_.isEmpty(), incomingCall.callerName_);
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
                                    this.callerNumber_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.callerName_ = codedInputStream.readStringRequireUtf8();
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
                    return new IncomingCall();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (IncomingCall.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Calling.IncomingCallOrBuilder
        public String getCallerName() {
            return this.callerName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Calling.IncomingCallOrBuilder
        public ByteString getCallerNameBytes() {
            return ByteString.copyFromUtf8(this.callerName_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Calling.IncomingCallOrBuilder
        public String getCallerNumber() {
            return this.callerNumber_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Calling.IncomingCallOrBuilder
        public ByteString getCallerNumberBytes() {
            return ByteString.copyFromUtf8(this.callerNumber_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.callerNumber_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getCallerNumber());
            }
            if (!this.callerName_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getCallerName());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.callerNumber_.isEmpty()) {
                codedOutputStream.writeString(1, getCallerNumber());
            }
            if (!this.callerName_.isEmpty()) {
                codedOutputStream.writeString(2, getCallerName());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(IncomingCall incomingCall) {
            return DEFAULT_INSTANCE.createBuilder(incomingCall);
        }

        public static IncomingCall parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncomingCall) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IncomingCall parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static IncomingCall parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static IncomingCall parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static IncomingCall parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static IncomingCall parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static IncomingCall parseFrom(InputStream inputStream) throws IOException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IncomingCall parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IncomingCall parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static IncomingCall parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncomingCall) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface IncomingCallOrBuilder extends MessageLiteOrBuilder {
        String getCallerName();

        ByteString getCallerNameBytes();

        String getCallerNumber();

        ByteString getCallerNumberBytes();
    }

    private Calling() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
