package com.amazon.alexa.accessory.protocol;

import com.amazon.alexa.accessory.protocol.Common;
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
public final class Transport {

    /* renamed from: com.amazon.alexa.accessory.protocol.Transport$1  reason: invalid class name */
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
    public static final class ConnectionDetails extends GeneratedMessageLite<ConnectionDetails, Builder> implements ConnectionDetailsOrBuilder {
        private static final ConnectionDetails DEFAULT_INSTANCE = new ConnectionDetails();
        public static final int IDENTIFIER_FIELD_NUMBER = 1;
        private static volatile Parser<ConnectionDetails> PARSER;
        private ByteString identifier_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ConnectionDetails, Builder> implements ConnectionDetailsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearIdentifier() {
                copyOnWrite();
                ((ConnectionDetails) this.instance).clearIdentifier();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Transport.ConnectionDetailsOrBuilder
            public ByteString getIdentifier() {
                return ((ConnectionDetails) this.instance).getIdentifier();
            }

            public Builder setIdentifier(ByteString byteString) {
                copyOnWrite();
                ((ConnectionDetails) this.instance).setIdentifier(byteString);
                return this;
            }

            private Builder() {
                super(ConnectionDetails.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ConnectionDetails() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIdentifier() {
            this.identifier_ = getDefaultInstance().getIdentifier();
        }

        public static ConnectionDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ConnectionDetails parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ConnectionDetails) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConnectionDetails parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ConnectionDetails> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdentifier(ByteString byteString) {
            if (byteString != null) {
                this.identifier_ = byteString;
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
                    ConnectionDetails connectionDetails = (ConnectionDetails) obj2;
                    boolean z2 = this.identifier_ != ByteString.EMPTY;
                    ByteString byteString = this.identifier_;
                    if (connectionDetails.identifier_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.identifier_ = visitor.visitByteString(z2, byteString, z, connectionDetails.identifier_);
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
                                        this.identifier_ = codedInputStream.readBytes();
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
                    return new ConnectionDetails();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ConnectionDetails.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Transport.ConnectionDetailsOrBuilder
        public ByteString getIdentifier() {
            return this.identifier_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.identifier_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.identifier_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.identifier_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.identifier_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ConnectionDetails connectionDetails) {
            return DEFAULT_INSTANCE.createBuilder(connectionDetails);
        }

        public static ConnectionDetails parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectionDetails) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConnectionDetails parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ConnectionDetails parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ConnectionDetails parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConnectionDetails parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConnectionDetails parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConnectionDetails parseFrom(InputStream inputStream) throws IOException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConnectionDetails parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConnectionDetails parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConnectionDetails parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectionDetails) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ConnectionDetailsOrBuilder extends MessageLiteOrBuilder {
        ByteString getIdentifier();
    }

    /* loaded from: classes6.dex */
    public static final class SwitchTransport extends GeneratedMessageLite<SwitchTransport, Builder> implements SwitchTransportOrBuilder {
        private static final SwitchTransport DEFAULT_INSTANCE = new SwitchTransport();
        public static final int NEW_TRANSPORT_FIELD_NUMBER = 1;
        private static volatile Parser<SwitchTransport> PARSER;
        private int newTransport_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SwitchTransport, Builder> implements SwitchTransportOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearNewTransport() {
                copyOnWrite();
                ((SwitchTransport) this.instance).clearNewTransport();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Transport.SwitchTransportOrBuilder
            public Common.Transport getNewTransport() {
                return ((SwitchTransport) this.instance).getNewTransport();
            }

            @Override // com.amazon.alexa.accessory.protocol.Transport.SwitchTransportOrBuilder
            public int getNewTransportValue() {
                return ((SwitchTransport) this.instance).getNewTransportValue();
            }

            public Builder setNewTransport(Common.Transport transport) {
                copyOnWrite();
                ((SwitchTransport) this.instance).setNewTransport(transport);
                return this;
            }

            public Builder setNewTransportValue(int i) {
                copyOnWrite();
                ((SwitchTransport) this.instance).setNewTransportValue(i);
                return this;
            }

            private Builder() {
                super(SwitchTransport.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SwitchTransport() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNewTransport() {
            this.newTransport_ = 0;
        }

        public static SwitchTransport getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SwitchTransport parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SwitchTransport) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SwitchTransport parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SwitchTransport> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNewTransport(Common.Transport transport) {
            if (transport != null) {
                this.newTransport_ = transport.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNewTransportValue(int i) {
            this.newTransport_ = i;
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
                    SwitchTransport switchTransport = (SwitchTransport) obj2;
                    boolean z2 = this.newTransport_ != 0;
                    int i = this.newTransport_;
                    if (switchTransport.newTransport_ != 0) {
                        z = true;
                    }
                    this.newTransport_ = visitor.visitInt(z2, i, z, switchTransport.newTransport_);
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
                                        this.newTransport_ = codedInputStream.readEnum();
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
                    return new SwitchTransport();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SwitchTransport.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Transport.SwitchTransportOrBuilder
        public Common.Transport getNewTransport() {
            Common.Transport forNumber = Common.Transport.forNumber(this.newTransport_);
            return forNumber == null ? Common.Transport.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Transport.SwitchTransportOrBuilder
        public int getNewTransportValue() {
            return this.newTransport_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.newTransport_ != Common.Transport.BLUETOOTH_LOW_ENERGY.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.newTransport_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.newTransport_ != Common.Transport.BLUETOOTH_LOW_ENERGY.getNumber()) {
                codedOutputStream.writeEnum(1, this.newTransport_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SwitchTransport switchTransport) {
            return DEFAULT_INSTANCE.createBuilder(switchTransport);
        }

        public static SwitchTransport parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SwitchTransport) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SwitchTransport parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SwitchTransport parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SwitchTransport parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SwitchTransport parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SwitchTransport parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SwitchTransport parseFrom(InputStream inputStream) throws IOException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SwitchTransport parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SwitchTransport parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SwitchTransport parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SwitchTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SwitchTransportOrBuilder extends MessageLiteOrBuilder {
        Common.Transport getNewTransport();

        int getNewTransportValue();
    }

    /* loaded from: classes6.dex */
    public static final class UpgradeTransport extends GeneratedMessageLite<UpgradeTransport, Builder> implements UpgradeTransportOrBuilder {
        private static final UpgradeTransport DEFAULT_INSTANCE = new UpgradeTransport();
        private static volatile Parser<UpgradeTransport> PARSER = null;
        public static final int TRANSPORT_FIELD_NUMBER = 1;
        private int transport_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UpgradeTransport, Builder> implements UpgradeTransportOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearTransport() {
                copyOnWrite();
                ((UpgradeTransport) this.instance).clearTransport();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Transport.UpgradeTransportOrBuilder
            public Common.Transport getTransport() {
                return ((UpgradeTransport) this.instance).getTransport();
            }

            @Override // com.amazon.alexa.accessory.protocol.Transport.UpgradeTransportOrBuilder
            public int getTransportValue() {
                return ((UpgradeTransport) this.instance).getTransportValue();
            }

            public Builder setTransport(Common.Transport transport) {
                copyOnWrite();
                ((UpgradeTransport) this.instance).setTransport(transport);
                return this;
            }

            public Builder setTransportValue(int i) {
                copyOnWrite();
                ((UpgradeTransport) this.instance).setTransportValue(i);
                return this;
            }

            private Builder() {
                super(UpgradeTransport.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UpgradeTransport() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTransport() {
            this.transport_ = 0;
        }

        public static UpgradeTransport getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UpgradeTransport parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpgradeTransport) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpgradeTransport parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UpgradeTransport> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTransport(Common.Transport transport) {
            if (transport != null) {
                this.transport_ = transport.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTransportValue(int i) {
            this.transport_ = i;
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
                    UpgradeTransport upgradeTransport = (UpgradeTransport) obj2;
                    boolean z2 = this.transport_ != 0;
                    int i = this.transport_;
                    if (upgradeTransport.transport_ != 0) {
                        z = true;
                    }
                    this.transport_ = visitor.visitInt(z2, i, z, upgradeTransport.transport_);
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
                                        this.transport_ = codedInputStream.readEnum();
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
                    return new UpgradeTransport();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UpgradeTransport.class) {
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
            if (this.transport_ != Common.Transport.BLUETOOTH_LOW_ENERGY.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.transport_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Transport.UpgradeTransportOrBuilder
        public Common.Transport getTransport() {
            Common.Transport forNumber = Common.Transport.forNumber(this.transport_);
            return forNumber == null ? Common.Transport.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Transport.UpgradeTransportOrBuilder
        public int getTransportValue() {
            return this.transport_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.transport_ != Common.Transport.BLUETOOTH_LOW_ENERGY.getNumber()) {
                codedOutputStream.writeEnum(1, this.transport_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UpgradeTransport upgradeTransport) {
            return DEFAULT_INSTANCE.createBuilder(upgradeTransport);
        }

        public static UpgradeTransport parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpgradeTransport) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpgradeTransport parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UpgradeTransport parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UpgradeTransport parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UpgradeTransport parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UpgradeTransport parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UpgradeTransport parseFrom(InputStream inputStream) throws IOException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpgradeTransport parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpgradeTransport parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UpgradeTransport parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpgradeTransport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UpgradeTransportOrBuilder extends MessageLiteOrBuilder {
        Common.Transport getTransport();

        int getTransportValue();
    }

    private Transport() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
