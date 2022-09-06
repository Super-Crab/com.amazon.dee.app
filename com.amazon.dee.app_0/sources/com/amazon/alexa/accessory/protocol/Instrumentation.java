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
public final class Instrumentation {

    /* renamed from: com.amazon.alexa.accessory.protocol.Instrumentation$1  reason: invalid class name */
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
    public static final class IssueRemoteClearPairing extends GeneratedMessageLite<IssueRemoteClearPairing, Builder> implements IssueRemoteClearPairingOrBuilder {
        private static final IssueRemoteClearPairing DEFAULT_INSTANCE = new IssueRemoteClearPairing();
        private static volatile Parser<IssueRemoteClearPairing> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<IssueRemoteClearPairing, Builder> implements IssueRemoteClearPairingOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(IssueRemoteClearPairing.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private IssueRemoteClearPairing() {
        }

        public static IssueRemoteClearPairing getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static IssueRemoteClearPairing parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueRemoteClearPairing parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<IssueRemoteClearPairing> parser() {
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
                    IssueRemoteClearPairing issueRemoteClearPairing = (IssueRemoteClearPairing) obj2;
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
                    return new IssueRemoteClearPairing();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (IssueRemoteClearPairing.class) {
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

        public static Builder newBuilder(IssueRemoteClearPairing issueRemoteClearPairing) {
            return DEFAULT_INSTANCE.createBuilder(issueRemoteClearPairing);
        }

        public static IssueRemoteClearPairing parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueRemoteClearPairing parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static IssueRemoteClearPairing parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static IssueRemoteClearPairing parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static IssueRemoteClearPairing parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static IssueRemoteClearPairing parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static IssueRemoteClearPairing parseFrom(InputStream inputStream) throws IOException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueRemoteClearPairing parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueRemoteClearPairing parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static IssueRemoteClearPairing parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteClearPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface IssueRemoteClearPairingOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class IssueRemoteCommand extends GeneratedMessageLite<IssueRemoteCommand, Builder> implements IssueRemoteCommandOrBuilder {
        public static final int COMMAND_CATEGORY_FIELD_NUMBER = 2;
        public static final int COMMAND_DATA_FIELD_NUMBER = 3;
        public static final int COMMAND_LINE_FIELD_NUMBER = 1;
        private static final IssueRemoteCommand DEFAULT_INSTANCE = new IssueRemoteCommand();
        private static volatile Parser<IssueRemoteCommand> PARSER;
        private int commandCategory_;
        private String commandLine_ = "";
        private ByteString commandData_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<IssueRemoteCommand, Builder> implements IssueRemoteCommandOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCommandCategory() {
                copyOnWrite();
                ((IssueRemoteCommand) this.instance).clearCommandCategory();
                return this;
            }

            public Builder clearCommandData() {
                copyOnWrite();
                ((IssueRemoteCommand) this.instance).clearCommandData();
                return this;
            }

            public Builder clearCommandLine() {
                copyOnWrite();
                ((IssueRemoteCommand) this.instance).clearCommandLine();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Instrumentation.IssueRemoteCommandOrBuilder
            public int getCommandCategory() {
                return ((IssueRemoteCommand) this.instance).getCommandCategory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Instrumentation.IssueRemoteCommandOrBuilder
            public ByteString getCommandData() {
                return ((IssueRemoteCommand) this.instance).getCommandData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Instrumentation.IssueRemoteCommandOrBuilder
            public String getCommandLine() {
                return ((IssueRemoteCommand) this.instance).getCommandLine();
            }

            @Override // com.amazon.alexa.accessory.protocol.Instrumentation.IssueRemoteCommandOrBuilder
            public ByteString getCommandLineBytes() {
                return ((IssueRemoteCommand) this.instance).getCommandLineBytes();
            }

            public Builder setCommandCategory(int i) {
                copyOnWrite();
                ((IssueRemoteCommand) this.instance).setCommandCategory(i);
                return this;
            }

            public Builder setCommandData(ByteString byteString) {
                copyOnWrite();
                ((IssueRemoteCommand) this.instance).setCommandData(byteString);
                return this;
            }

            public Builder setCommandLine(String str) {
                copyOnWrite();
                ((IssueRemoteCommand) this.instance).setCommandLine(str);
                return this;
            }

            public Builder setCommandLineBytes(ByteString byteString) {
                copyOnWrite();
                ((IssueRemoteCommand) this.instance).setCommandLineBytes(byteString);
                return this;
            }

            private Builder() {
                super(IssueRemoteCommand.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private IssueRemoteCommand() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCommandCategory() {
            this.commandCategory_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCommandData() {
            this.commandData_ = getDefaultInstance().getCommandData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCommandLine() {
            this.commandLine_ = getDefaultInstance().getCommandLine();
        }

        public static IssueRemoteCommand getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static IssueRemoteCommand parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueRemoteCommand parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<IssueRemoteCommand> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCommandCategory(int i) {
            this.commandCategory_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCommandData(ByteString byteString) {
            if (byteString != null) {
                this.commandData_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCommandLine(String str) {
            if (str != null) {
                this.commandLine_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCommandLineBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.commandLine_ = byteString.toStringUtf8();
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
                    IssueRemoteCommand issueRemoteCommand = (IssueRemoteCommand) obj2;
                    this.commandLine_ = visitor.visitString(!this.commandLine_.isEmpty(), this.commandLine_, !issueRemoteCommand.commandLine_.isEmpty(), issueRemoteCommand.commandLine_);
                    this.commandCategory_ = visitor.visitInt(this.commandCategory_ != 0, this.commandCategory_, issueRemoteCommand.commandCategory_ != 0, issueRemoteCommand.commandCategory_);
                    boolean z2 = this.commandData_ != ByteString.EMPTY;
                    ByteString byteString = this.commandData_;
                    if (issueRemoteCommand.commandData_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.commandData_ = visitor.visitByteString(z2, byteString, z, issueRemoteCommand.commandData_);
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
                                    this.commandLine_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 16) {
                                    this.commandCategory_ = codedInputStream.readUInt32();
                                } else if (readTag != 26) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.commandData_ = codedInputStream.readBytes();
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
                    return new IssueRemoteCommand();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (IssueRemoteCommand.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Instrumentation.IssueRemoteCommandOrBuilder
        public int getCommandCategory() {
            return this.commandCategory_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Instrumentation.IssueRemoteCommandOrBuilder
        public ByteString getCommandData() {
            return this.commandData_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Instrumentation.IssueRemoteCommandOrBuilder
        public String getCommandLine() {
            return this.commandLine_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Instrumentation.IssueRemoteCommandOrBuilder
        public ByteString getCommandLineBytes() {
            return ByteString.copyFromUtf8(this.commandLine_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.commandLine_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getCommandLine());
            }
            int i3 = this.commandCategory_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            if (!this.commandData_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(3, this.commandData_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.commandLine_.isEmpty()) {
                codedOutputStream.writeString(1, getCommandLine());
            }
            int i = this.commandCategory_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            if (!this.commandData_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.commandData_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(IssueRemoteCommand issueRemoteCommand) {
            return DEFAULT_INSTANCE.createBuilder(issueRemoteCommand);
        }

        public static IssueRemoteCommand parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueRemoteCommand parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static IssueRemoteCommand parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static IssueRemoteCommand parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static IssueRemoteCommand parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static IssueRemoteCommand parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static IssueRemoteCommand parseFrom(InputStream inputStream) throws IOException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueRemoteCommand parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueRemoteCommand parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static IssueRemoteCommand parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteCommand) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface IssueRemoteCommandOrBuilder extends MessageLiteOrBuilder {
        int getCommandCategory();

        ByteString getCommandData();

        String getCommandLine();

        ByteString getCommandLineBytes();
    }

    /* loaded from: classes6.dex */
    public static final class IssueRemoteReset extends GeneratedMessageLite<IssueRemoteReset, Builder> implements IssueRemoteResetOrBuilder {
        private static final IssueRemoteReset DEFAULT_INSTANCE = new IssueRemoteReset();
        private static volatile Parser<IssueRemoteReset> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<IssueRemoteReset, Builder> implements IssueRemoteResetOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(IssueRemoteReset.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private IssueRemoteReset() {
        }

        public static IssueRemoteReset getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static IssueRemoteReset parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (IssueRemoteReset) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueRemoteReset parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<IssueRemoteReset> parser() {
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
                    IssueRemoteReset issueRemoteReset = (IssueRemoteReset) obj2;
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
                    return new IssueRemoteReset();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (IssueRemoteReset.class) {
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

        public static Builder newBuilder(IssueRemoteReset issueRemoteReset) {
            return DEFAULT_INSTANCE.createBuilder(issueRemoteReset);
        }

        public static IssueRemoteReset parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteReset) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueRemoteReset parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static IssueRemoteReset parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static IssueRemoteReset parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static IssueRemoteReset parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static IssueRemoteReset parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static IssueRemoteReset parseFrom(InputStream inputStream) throws IOException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueRemoteReset parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueRemoteReset parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static IssueRemoteReset parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteReset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface IssueRemoteResetOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class IssueRemoteRestart extends GeneratedMessageLite<IssueRemoteRestart, Builder> implements IssueRemoteRestartOrBuilder {
        private static final IssueRemoteRestart DEFAULT_INSTANCE = new IssueRemoteRestart();
        private static volatile Parser<IssueRemoteRestart> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<IssueRemoteRestart, Builder> implements IssueRemoteRestartOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(IssueRemoteRestart.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private IssueRemoteRestart() {
        }

        public static IssueRemoteRestart getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static IssueRemoteRestart parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueRemoteRestart parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<IssueRemoteRestart> parser() {
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
                    IssueRemoteRestart issueRemoteRestart = (IssueRemoteRestart) obj2;
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
                    return new IssueRemoteRestart();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (IssueRemoteRestart.class) {
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

        public static Builder newBuilder(IssueRemoteRestart issueRemoteRestart) {
            return DEFAULT_INSTANCE.createBuilder(issueRemoteRestart);
        }

        public static IssueRemoteRestart parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueRemoteRestart parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static IssueRemoteRestart parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static IssueRemoteRestart parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static IssueRemoteRestart parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static IssueRemoteRestart parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static IssueRemoteRestart parseFrom(InputStream inputStream) throws IOException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueRemoteRestart parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueRemoteRestart parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static IssueRemoteRestart parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueRemoteRestart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface IssueRemoteRestartOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class PrintDebug extends GeneratedMessageLite<PrintDebug, Builder> implements PrintDebugOrBuilder {
        public static final int DATA_FIELD_NUMBER = 1;
        private static final PrintDebug DEFAULT_INSTANCE = new PrintDebug();
        public static final int FLAGS_FIELD_NUMBER = 2;
        private static volatile Parser<PrintDebug> PARSER;
        private ByteString data_ = ByteString.EMPTY;
        private int flags_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<PrintDebug, Builder> implements PrintDebugOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearData() {
                copyOnWrite();
                ((PrintDebug) this.instance).clearData();
                return this;
            }

            public Builder clearFlags() {
                copyOnWrite();
                ((PrintDebug) this.instance).clearFlags();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Instrumentation.PrintDebugOrBuilder
            public ByteString getData() {
                return ((PrintDebug) this.instance).getData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Instrumentation.PrintDebugOrBuilder
            public int getFlags() {
                return ((PrintDebug) this.instance).getFlags();
            }

            public Builder setData(ByteString byteString) {
                copyOnWrite();
                ((PrintDebug) this.instance).setData(byteString);
                return this;
            }

            public Builder setFlags(int i) {
                copyOnWrite();
                ((PrintDebug) this.instance).setFlags(i);
                return this;
            }

            private Builder() {
                super(PrintDebug.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private PrintDebug() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearData() {
            this.data_ = getDefaultInstance().getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFlags() {
            this.flags_ = 0;
        }

        public static PrintDebug getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static PrintDebug parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PrintDebug) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PrintDebug parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<PrintDebug> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setData(ByteString byteString) {
            if (byteString != null) {
                this.data_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFlags(int i) {
            this.flags_ = i;
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
                    PrintDebug printDebug = (PrintDebug) obj2;
                    this.data_ = visitor.visitByteString(this.data_ != ByteString.EMPTY, this.data_, printDebug.data_ != ByteString.EMPTY, printDebug.data_);
                    boolean z2 = this.flags_ != 0;
                    int i = this.flags_;
                    if (printDebug.flags_ != 0) {
                        z = true;
                    }
                    this.flags_ = visitor.visitInt(z2, i, z, printDebug.flags_);
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
                                    this.data_ = codedInputStream.readBytes();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.flags_ = codedInputStream.readUInt32();
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
                    return new PrintDebug();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (PrintDebug.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Instrumentation.PrintDebugOrBuilder
        public ByteString getData() {
            return this.data_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Instrumentation.PrintDebugOrBuilder
        public int getFlags() {
            return this.flags_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.data_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.data_);
            }
            int i3 = this.flags_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.data_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.data_);
            }
            int i = this.flags_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(PrintDebug printDebug) {
            return DEFAULT_INSTANCE.createBuilder(printDebug);
        }

        public static PrintDebug parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PrintDebug) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PrintDebug parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static PrintDebug parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static PrintDebug parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PrintDebug parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PrintDebug parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PrintDebug parseFrom(InputStream inputStream) throws IOException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PrintDebug parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PrintDebug parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PrintDebug parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PrintDebug) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface PrintDebugOrBuilder extends MessageLiteOrBuilder {
        ByteString getData();

        int getFlags();
    }

    private Instrumentation() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
