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
public final class System {

    /* renamed from: com.amazon.alexa.accessory.protocol.System$1  reason: invalid class name */
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
    public static final class ConnectUser extends GeneratedMessageLite<ConnectUser, Builder> implements ConnectUserOrBuilder {
        public static final int ADDRESS_FIELD_NUMBER = 1;
        private static final ConnectUser DEFAULT_INSTANCE = new ConnectUser();
        private static volatile Parser<ConnectUser> PARSER;
        private String address_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ConnectUser, Builder> implements ConnectUserOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAddress() {
                copyOnWrite();
                ((ConnectUser) this.instance).clearAddress();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.ConnectUserOrBuilder
            public String getAddress() {
                return ((ConnectUser) this.instance).getAddress();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.ConnectUserOrBuilder
            public ByteString getAddressBytes() {
                return ((ConnectUser) this.instance).getAddressBytes();
            }

            public Builder setAddress(String str) {
                copyOnWrite();
                ((ConnectUser) this.instance).setAddress(str);
                return this;
            }

            public Builder setAddressBytes(ByteString byteString) {
                copyOnWrite();
                ((ConnectUser) this.instance).setAddressBytes(byteString);
                return this;
            }

            private Builder() {
                super(ConnectUser.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ConnectUser() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAddress() {
            this.address_ = getDefaultInstance().getAddress();
        }

        public static ConnectUser getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ConnectUser parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ConnectUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConnectUser parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ConnectUser> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddress(String str) {
            if (str != null) {
                this.address_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddressBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.address_ = byteString.toStringUtf8();
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
                    ConnectUser connectUser = (ConnectUser) obj2;
                    this.address_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.address_.isEmpty(), this.address_, true ^ connectUser.address_.isEmpty(), connectUser.address_);
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
                                        this.address_ = codedInputStream.readStringRequireUtf8();
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
                    return new ConnectUser();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ConnectUser.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.ConnectUserOrBuilder
        public String getAddress() {
            return this.address_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.ConnectUserOrBuilder
        public ByteString getAddressBytes() {
            return ByteString.copyFromUtf8(this.address_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.address_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getAddress());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.address_.isEmpty()) {
                codedOutputStream.writeString(1, getAddress());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ConnectUser connectUser) {
            return DEFAULT_INSTANCE.createBuilder(connectUser);
        }

        public static ConnectUser parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConnectUser parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ConnectUser parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ConnectUser parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConnectUser parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConnectUser parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConnectUser parseFrom(InputStream inputStream) throws IOException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConnectUser parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConnectUser parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConnectUser parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ConnectUserOrBuilder extends MessageLiteOrBuilder {
        String getAddress();

        ByteString getAddressBytes();
    }

    /* loaded from: classes6.dex */
    public static final class DisconnectUser extends GeneratedMessageLite<DisconnectUser, Builder> implements DisconnectUserOrBuilder {
        public static final int ADDRESS_FIELD_NUMBER = 1;
        private static final DisconnectUser DEFAULT_INSTANCE = new DisconnectUser();
        private static volatile Parser<DisconnectUser> PARSER;
        private String address_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<DisconnectUser, Builder> implements DisconnectUserOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAddress() {
                copyOnWrite();
                ((DisconnectUser) this.instance).clearAddress();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.DisconnectUserOrBuilder
            public String getAddress() {
                return ((DisconnectUser) this.instance).getAddress();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.DisconnectUserOrBuilder
            public ByteString getAddressBytes() {
                return ((DisconnectUser) this.instance).getAddressBytes();
            }

            public Builder setAddress(String str) {
                copyOnWrite();
                ((DisconnectUser) this.instance).setAddress(str);
                return this;
            }

            public Builder setAddressBytes(ByteString byteString) {
                copyOnWrite();
                ((DisconnectUser) this.instance).setAddressBytes(byteString);
                return this;
            }

            private Builder() {
                super(DisconnectUser.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private DisconnectUser() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAddress() {
            this.address_ = getDefaultInstance().getAddress();
        }

        public static DisconnectUser getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DisconnectUser parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DisconnectUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DisconnectUser parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DisconnectUser> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddress(String str) {
            if (str != null) {
                this.address_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddressBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.address_ = byteString.toStringUtf8();
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
                    DisconnectUser disconnectUser = (DisconnectUser) obj2;
                    this.address_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.address_.isEmpty(), this.address_, true ^ disconnectUser.address_.isEmpty(), disconnectUser.address_);
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
                                        this.address_ = codedInputStream.readStringRequireUtf8();
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
                    return new DisconnectUser();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (DisconnectUser.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.DisconnectUserOrBuilder
        public String getAddress() {
            return this.address_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.DisconnectUserOrBuilder
        public ByteString getAddressBytes() {
            return ByteString.copyFromUtf8(this.address_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.address_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getAddress());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.address_.isEmpty()) {
                codedOutputStream.writeString(1, getAddress());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(DisconnectUser disconnectUser) {
            return DEFAULT_INSTANCE.createBuilder(disconnectUser);
        }

        public static DisconnectUser parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisconnectUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DisconnectUser parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DisconnectUser parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DisconnectUser parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DisconnectUser parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DisconnectUser parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DisconnectUser parseFrom(InputStream inputStream) throws IOException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DisconnectUser parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DisconnectUser parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DisconnectUser parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisconnectUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface DisconnectUserOrBuilder extends MessageLiteOrBuilder {
        String getAddress();

        ByteString getAddressBytes();
    }

    /* loaded from: classes6.dex */
    public static final class GetCurrentUser extends GeneratedMessageLite<GetCurrentUser, Builder> implements GetCurrentUserOrBuilder {
        private static final GetCurrentUser DEFAULT_INSTANCE = new GetCurrentUser();
        private static volatile Parser<GetCurrentUser> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetCurrentUser, Builder> implements GetCurrentUserOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(GetCurrentUser.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetCurrentUser() {
        }

        public static GetCurrentUser getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetCurrentUser parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetCurrentUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCurrentUser parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetCurrentUser> parser() {
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
                    GetCurrentUser getCurrentUser = (GetCurrentUser) obj2;
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
                    return new GetCurrentUser();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetCurrentUser.class) {
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

        public static Builder newBuilder(GetCurrentUser getCurrentUser) {
            return DEFAULT_INSTANCE.createBuilder(getCurrentUser);
        }

        public static GetCurrentUser parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCurrentUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCurrentUser parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetCurrentUser parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetCurrentUser parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetCurrentUser parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetCurrentUser parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetCurrentUser parseFrom(InputStream inputStream) throws IOException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCurrentUser parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCurrentUser parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetCurrentUser parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetCurrentUserOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class GetLocales extends GeneratedMessageLite<GetLocales, Builder> implements GetLocalesOrBuilder {
        private static final GetLocales DEFAULT_INSTANCE = new GetLocales();
        private static volatile Parser<GetLocales> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetLocales, Builder> implements GetLocalesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(GetLocales.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetLocales() {
        }

        public static GetLocales getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetLocales parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetLocales) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetLocales parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetLocales> parser() {
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
                    GetLocales getLocales = (GetLocales) obj2;
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
                    return new GetLocales();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetLocales.class) {
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

        public static Builder newBuilder(GetLocales getLocales) {
            return DEFAULT_INSTANCE.createBuilder(getLocales);
        }

        public static GetLocales parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetLocales) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetLocales parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetLocales parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetLocales parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetLocales parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetLocales parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetLocales parseFrom(InputStream inputStream) throws IOException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetLocales parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetLocales parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetLocales parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetLocales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetLocalesOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class GetUsers extends GeneratedMessageLite<GetUsers, Builder> implements GetUsersOrBuilder {
        private static final GetUsers DEFAULT_INSTANCE = new GetUsers();
        private static volatile Parser<GetUsers> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetUsers, Builder> implements GetUsersOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(GetUsers.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetUsers() {
        }

        public static GetUsers getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetUsers parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetUsers) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetUsers parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetUsers> parser() {
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
                    GetUsers getUsers = (GetUsers) obj2;
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
                    return new GetUsers();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetUsers.class) {
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

        public static Builder newBuilder(GetUsers getUsers) {
            return DEFAULT_INSTANCE.createBuilder(getUsers);
        }

        public static GetUsers parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetUsers) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetUsers parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetUsers parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetUsers parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetUsers parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetUsers parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetUsers parseFrom(InputStream inputStream) throws IOException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetUsers parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetUsers parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetUsers parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetUsersOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class GetWakewords extends GeneratedMessageLite<GetWakewords, Builder> implements GetWakewordsOrBuilder {
        private static final GetWakewords DEFAULT_INSTANCE = new GetWakewords();
        private static volatile Parser<GetWakewords> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetWakewords, Builder> implements GetWakewordsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(GetWakewords.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetWakewords() {
        }

        public static GetWakewords getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetWakewords parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetWakewords) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetWakewords parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetWakewords> parser() {
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
                    GetWakewords getWakewords = (GetWakewords) obj2;
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
                    return new GetWakewords();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetWakewords.class) {
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

        public static Builder newBuilder(GetWakewords getWakewords) {
            return DEFAULT_INSTANCE.createBuilder(getWakewords);
        }

        public static GetWakewords parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetWakewords) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetWakewords parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetWakewords parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetWakewords parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetWakewords parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetWakewords parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetWakewords parseFrom(InputStream inputStream) throws IOException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetWakewords parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetWakewords parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetWakewords parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetWakewordsOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class KeepAlive extends GeneratedMessageLite<KeepAlive, Builder> implements KeepAliveOrBuilder {
        private static final KeepAlive DEFAULT_INSTANCE = new KeepAlive();
        private static volatile Parser<KeepAlive> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<KeepAlive, Builder> implements KeepAliveOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(KeepAlive.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private KeepAlive() {
        }

        public static KeepAlive getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static KeepAlive parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (KeepAlive) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeepAlive parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<KeepAlive> parser() {
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
                    KeepAlive keepAlive = (KeepAlive) obj2;
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
                    return new KeepAlive();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (KeepAlive.class) {
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

        public static Builder newBuilder(KeepAlive keepAlive) {
            return DEFAULT_INSTANCE.createBuilder(keepAlive);
        }

        public static KeepAlive parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeepAlive) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeepAlive parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static KeepAlive parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static KeepAlive parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static KeepAlive parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static KeepAlive parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static KeepAlive parseFrom(InputStream inputStream) throws IOException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeepAlive parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeepAlive parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static KeepAlive parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeepAlive) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface KeepAliveOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class LaunchApp extends GeneratedMessageLite<LaunchApp, Builder> implements LaunchAppOrBuilder {
        public static final int APP_ID_FIELD_NUMBER = 1;
        private static final LaunchApp DEFAULT_INSTANCE = new LaunchApp();
        private static volatile Parser<LaunchApp> PARSER;
        private String appId_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<LaunchApp, Builder> implements LaunchAppOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAppId() {
                copyOnWrite();
                ((LaunchApp) this.instance).clearAppId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.LaunchAppOrBuilder
            public String getAppId() {
                return ((LaunchApp) this.instance).getAppId();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.LaunchAppOrBuilder
            public ByteString getAppIdBytes() {
                return ((LaunchApp) this.instance).getAppIdBytes();
            }

            public Builder setAppId(String str) {
                copyOnWrite();
                ((LaunchApp) this.instance).setAppId(str);
                return this;
            }

            public Builder setAppIdBytes(ByteString byteString) {
                copyOnWrite();
                ((LaunchApp) this.instance).setAppIdBytes(byteString);
                return this;
            }

            private Builder() {
                super(LaunchApp.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private LaunchApp() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppId() {
            this.appId_ = getDefaultInstance().getAppId();
        }

        public static LaunchApp getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static LaunchApp parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LaunchApp) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LaunchApp parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<LaunchApp> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppId(String str) {
            if (str != null) {
                this.appId_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.appId_ = byteString.toStringUtf8();
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
                    LaunchApp launchApp = (LaunchApp) obj2;
                    this.appId_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.appId_.isEmpty(), this.appId_, true ^ launchApp.appId_.isEmpty(), launchApp.appId_);
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
                                        this.appId_ = codedInputStream.readStringRequireUtf8();
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
                    return new LaunchApp();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (LaunchApp.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.LaunchAppOrBuilder
        public String getAppId() {
            return this.appId_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.LaunchAppOrBuilder
        public ByteString getAppIdBytes() {
            return ByteString.copyFromUtf8(this.appId_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.appId_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getAppId());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.appId_.isEmpty()) {
                codedOutputStream.writeString(1, getAppId());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(LaunchApp launchApp) {
            return DEFAULT_INSTANCE.createBuilder(launchApp);
        }

        public static LaunchApp parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LaunchApp) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LaunchApp parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static LaunchApp parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static LaunchApp parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static LaunchApp parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static LaunchApp parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static LaunchApp parseFrom(InputStream inputStream) throws IOException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LaunchApp parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LaunchApp parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static LaunchApp parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LaunchApp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface LaunchAppOrBuilder extends MessageLiteOrBuilder {
        String getAppId();

        ByteString getAppIdBytes();
    }

    /* loaded from: classes6.dex */
    public static final class Locale extends GeneratedMessageLite<Locale, Builder> implements LocaleOrBuilder {
        private static final Locale DEFAULT_INSTANCE = new Locale();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<Locale> PARSER;
        private String name_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Locale, Builder> implements LocaleOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearName() {
                copyOnWrite();
                ((Locale) this.instance).clearName();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.LocaleOrBuilder
            public String getName() {
                return ((Locale) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.LocaleOrBuilder
            public ByteString getNameBytes() {
                return ((Locale) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((Locale) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((Locale) this.instance).setNameBytes(byteString);
                return this;
            }

            private Builder() {
                super(Locale.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Locale() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        public static Locale getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Locale parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Locale) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Locale parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Locale> parser() {
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
                    Locale locale = (Locale) obj2;
                    this.name_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.name_.isEmpty(), this.name_, true ^ locale.name_.isEmpty(), locale.name_);
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
                    return new Locale();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (Locale.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.LocaleOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.LocaleOrBuilder
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

        public static Builder newBuilder(Locale locale) {
            return DEFAULT_INSTANCE.createBuilder(locale);
        }

        public static Locale parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Locale) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Locale parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Locale parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Locale parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Locale parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Locale parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Locale parseFrom(InputStream inputStream) throws IOException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Locale parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Locale parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Locale parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Locale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface LocaleOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();
    }

    /* loaded from: classes6.dex */
    public static final class Locales extends GeneratedMessageLite<Locales, Builder> implements LocalesOrBuilder {
        public static final int CURRENT_LOCALE_FIELD_NUMBER = 2;
        private static final Locales DEFAULT_INSTANCE = new Locales();
        private static volatile Parser<Locales> PARSER = null;
        public static final int SUPPORTED_LOCALES_FIELD_NUMBER = 1;
        private int bitField0_;
        private Locale currentLocale_;
        private Internal.ProtobufList<Locale> supportedLocales_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Locales, Builder> implements LocalesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllSupportedLocales(Iterable<? extends Locale> iterable) {
                copyOnWrite();
                ((Locales) this.instance).addAllSupportedLocales(iterable);
                return this;
            }

            public Builder addSupportedLocales(Locale locale) {
                copyOnWrite();
                ((Locales) this.instance).addSupportedLocales(locale);
                return this;
            }

            public Builder clearCurrentLocale() {
                copyOnWrite();
                ((Locales) this.instance).clearCurrentLocale();
                return this;
            }

            public Builder clearSupportedLocales() {
                copyOnWrite();
                ((Locales) this.instance).clearSupportedLocales();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
            public Locale getCurrentLocale() {
                return ((Locales) this.instance).getCurrentLocale();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
            public Locale getSupportedLocales(int i) {
                return ((Locales) this.instance).getSupportedLocales(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
            public int getSupportedLocalesCount() {
                return ((Locales) this.instance).getSupportedLocalesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
            public List<Locale> getSupportedLocalesList() {
                return Collections.unmodifiableList(((Locales) this.instance).getSupportedLocalesList());
            }

            @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
            public boolean hasCurrentLocale() {
                return ((Locales) this.instance).hasCurrentLocale();
            }

            public Builder mergeCurrentLocale(Locale locale) {
                copyOnWrite();
                ((Locales) this.instance).mergeCurrentLocale(locale);
                return this;
            }

            public Builder removeSupportedLocales(int i) {
                copyOnWrite();
                ((Locales) this.instance).removeSupportedLocales(i);
                return this;
            }

            public Builder setCurrentLocale(Locale locale) {
                copyOnWrite();
                ((Locales) this.instance).setCurrentLocale(locale);
                return this;
            }

            public Builder setSupportedLocales(int i, Locale locale) {
                copyOnWrite();
                ((Locales) this.instance).setSupportedLocales(i, locale);
                return this;
            }

            private Builder() {
                super(Locales.DEFAULT_INSTANCE);
            }

            public Builder addSupportedLocales(int i, Locale locale) {
                copyOnWrite();
                ((Locales) this.instance).addSupportedLocales(i, locale);
                return this;
            }

            public Builder setCurrentLocale(Locale.Builder builder) {
                copyOnWrite();
                ((Locales) this.instance).setCurrentLocale(builder);
                return this;
            }

            public Builder setSupportedLocales(int i, Locale.Builder builder) {
                copyOnWrite();
                ((Locales) this.instance).setSupportedLocales(i, builder);
                return this;
            }

            public Builder addSupportedLocales(Locale.Builder builder) {
                copyOnWrite();
                ((Locales) this.instance).addSupportedLocales(builder);
                return this;
            }

            public Builder addSupportedLocales(int i, Locale.Builder builder) {
                copyOnWrite();
                ((Locales) this.instance).addSupportedLocales(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Locales() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedLocales(Iterable<? extends Locale> iterable) {
            ensureSupportedLocalesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.supportedLocales_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedLocales(Locale locale) {
            if (locale != null) {
                ensureSupportedLocalesIsMutable();
                this.supportedLocales_.add(locale);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCurrentLocale() {
            this.currentLocale_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedLocales() {
            this.supportedLocales_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureSupportedLocalesIsMutable() {
            if (!this.supportedLocales_.isModifiable()) {
                this.supportedLocales_ = GeneratedMessageLite.mutableCopy(this.supportedLocales_);
            }
        }

        public static Locales getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCurrentLocale(Locale locale) {
            Locale locale2 = this.currentLocale_;
            if (locale2 != null && locale2 != Locale.getDefaultInstance()) {
                this.currentLocale_ = Locale.newBuilder(this.currentLocale_).mergeFrom((Locale.Builder) locale).mo10085buildPartial();
            } else {
                this.currentLocale_ = locale;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Locales parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Locales) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Locales parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Locales> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeSupportedLocales(int i) {
            ensureSupportedLocalesIsMutable();
            this.supportedLocales_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurrentLocale(Locale locale) {
            if (locale != null) {
                this.currentLocale_ = locale;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedLocales(int i, Locale locale) {
            if (locale != null) {
                ensureSupportedLocalesIsMutable();
                this.supportedLocales_.set(i, locale);
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
                    Locales locales = (Locales) obj2;
                    this.supportedLocales_ = visitor.visitList(this.supportedLocales_, locales.supportedLocales_);
                    this.currentLocale_ = (Locale) visitor.visitMessage(this.currentLocale_, locales.currentLocale_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= locales.bitField0_;
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
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 10) {
                                        if (!this.supportedLocales_.isModifiable()) {
                                            this.supportedLocales_ = GeneratedMessageLite.mutableCopy(this.supportedLocales_);
                                        }
                                        this.supportedLocales_.add(codedInputStream.readMessage(Locale.parser(), extensionRegistryLite));
                                    } else if (readTag != 18) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        Locale.Builder mo10081toBuilder = this.currentLocale_ != null ? this.currentLocale_.mo10081toBuilder() : null;
                                        this.currentLocale_ = (Locale) codedInputStream.readMessage(Locale.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder != null) {
                                            mo10081toBuilder.mergeFrom((Locale.Builder) this.currentLocale_);
                                            this.currentLocale_ = mo10081toBuilder.mo10085buildPartial();
                                        }
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            }
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    this.supportedLocales_.makeImmutable();
                    return null;
                case 6:
                    return new Locales();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (Locales.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
        public Locale getCurrentLocale() {
            Locale locale = this.currentLocale_;
            return locale == null ? Locale.getDefaultInstance() : locale;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.supportedLocales_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.supportedLocales_.get(i3));
            }
            if (this.currentLocale_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getCurrentLocale());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
        public Locale getSupportedLocales(int i) {
            return this.supportedLocales_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
        public int getSupportedLocalesCount() {
            return this.supportedLocales_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
        public List<Locale> getSupportedLocalesList() {
            return this.supportedLocales_;
        }

        public LocaleOrBuilder getSupportedLocalesOrBuilder(int i) {
            return this.supportedLocales_.get(i);
        }

        public List<? extends LocaleOrBuilder> getSupportedLocalesOrBuilderList() {
            return this.supportedLocales_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.LocalesOrBuilder
        public boolean hasCurrentLocale() {
            return this.currentLocale_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.supportedLocales_.size(); i++) {
                codedOutputStream.writeMessage(1, this.supportedLocales_.get(i));
            }
            if (this.currentLocale_ != null) {
                codedOutputStream.writeMessage(2, getCurrentLocale());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(Locales locales) {
            return DEFAULT_INSTANCE.createBuilder(locales);
        }

        public static Locales parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Locales) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Locales parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Locales parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurrentLocale(Locale.Builder builder) {
            this.currentLocale_ = builder.mo10084build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedLocales(int i, Locale locale) {
            if (locale != null) {
                ensureSupportedLocalesIsMutable();
                this.supportedLocales_.add(i, locale);
                return;
            }
            throw new NullPointerException();
        }

        public static Locales parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedLocales(int i, Locale.Builder builder) {
            ensureSupportedLocalesIsMutable();
            this.supportedLocales_.set(i, builder.mo10084build());
        }

        public static Locales parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Locales parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedLocales(Locale.Builder builder) {
            ensureSupportedLocalesIsMutable();
            this.supportedLocales_.add(builder.mo10084build());
        }

        public static Locales parseFrom(InputStream inputStream) throws IOException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Locales parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedLocales(int i, Locale.Builder builder) {
            ensureSupportedLocalesIsMutable();
            this.supportedLocales_.add(i, builder.mo10084build());
        }

        public static Locales parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Locales parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Locales) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface LocalesOrBuilder extends MessageLiteOrBuilder {
        Locale getCurrentLocale();

        Locale getSupportedLocales(int i);

        int getSupportedLocalesCount();

        List<Locale> getSupportedLocalesList();

        boolean hasCurrentLocale();
    }

    /* loaded from: classes6.dex */
    public static final class RemoveDevice extends GeneratedMessageLite<RemoveDevice, Builder> implements RemoveDeviceOrBuilder {
        private static final RemoveDevice DEFAULT_INSTANCE = new RemoveDevice();
        private static volatile Parser<RemoveDevice> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<RemoveDevice, Builder> implements RemoveDeviceOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(RemoveDevice.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private RemoveDevice() {
        }

        public static RemoveDevice getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static RemoveDevice parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoveDevice) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RemoveDevice parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<RemoveDevice> parser() {
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
                    RemoveDevice removeDevice = (RemoveDevice) obj2;
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
                    return new RemoveDevice();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (RemoveDevice.class) {
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

        public static Builder newBuilder(RemoveDevice removeDevice) {
            return DEFAULT_INSTANCE.createBuilder(removeDevice);
        }

        public static RemoveDevice parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoveDevice) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RemoveDevice parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static RemoveDevice parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static RemoveDevice parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static RemoveDevice parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static RemoveDevice parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static RemoveDevice parseFrom(InputStream inputStream) throws IOException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RemoveDevice parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RemoveDevice parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static RemoveDevice parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoveDevice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface RemoveDeviceOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class ResetConnection extends GeneratedMessageLite<ResetConnection, Builder> implements ResetConnectionOrBuilder {
        private static final ResetConnection DEFAULT_INSTANCE = new ResetConnection();
        public static final int FORCE_DISCONNECT_FIELD_NUMBER = 2;
        private static volatile Parser<ResetConnection> PARSER = null;
        public static final int RESET_REASON_FIELD_NUMBER = 3;
        public static final int TIMEOUT_FIELD_NUMBER = 1;
        private boolean forceDisconnect_;
        private int resetReason_;
        private int timeout_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ResetConnection, Builder> implements ResetConnectionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearForceDisconnect() {
                copyOnWrite();
                ((ResetConnection) this.instance).clearForceDisconnect();
                return this;
            }

            public Builder clearResetReason() {
                copyOnWrite();
                ((ResetConnection) this.instance).clearResetReason();
                return this;
            }

            public Builder clearTimeout() {
                copyOnWrite();
                ((ResetConnection) this.instance).clearTimeout();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.ResetConnectionOrBuilder
            public boolean getForceDisconnect() {
                return ((ResetConnection) this.instance).getForceDisconnect();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.ResetConnectionOrBuilder
            public ResetReason getResetReason() {
                return ((ResetConnection) this.instance).getResetReason();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.ResetConnectionOrBuilder
            public int getResetReasonValue() {
                return ((ResetConnection) this.instance).getResetReasonValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.ResetConnectionOrBuilder
            public int getTimeout() {
                return ((ResetConnection) this.instance).getTimeout();
            }

            public Builder setForceDisconnect(boolean z) {
                copyOnWrite();
                ((ResetConnection) this.instance).setForceDisconnect(z);
                return this;
            }

            public Builder setResetReason(ResetReason resetReason) {
                copyOnWrite();
                ((ResetConnection) this.instance).setResetReason(resetReason);
                return this;
            }

            public Builder setResetReasonValue(int i) {
                copyOnWrite();
                ((ResetConnection) this.instance).setResetReasonValue(i);
                return this;
            }

            public Builder setTimeout(int i) {
                copyOnWrite();
                ((ResetConnection) this.instance).setTimeout(i);
                return this;
            }

            private Builder() {
                super(ResetConnection.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        public enum ResetReason implements Internal.EnumLite {
            UNKNOWN(0),
            AAP_REFUSED_MAX_CONNECTIONS_REACHED(1),
            UNRECOGNIZED(-1);
            
            public static final int AAP_REFUSED_MAX_CONNECTIONS_REACHED_VALUE = 1;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<ResetReason> internalValueMap = new Internal.EnumLiteMap<ResetReason>() { // from class: com.amazon.alexa.accessory.protocol.System.ResetConnection.ResetReason.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public ResetReason mo9850findValueByNumber(int i) {
                    return ResetReason.forNumber(i);
                }
            };
            private final int value;

            ResetReason(int i) {
                this.value = i;
            }

            public static ResetReason forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return AAP_REFUSED_MAX_CONNECTIONS_REACHED;
                    }
                    return null;
                }
                return UNKNOWN;
            }

            public static Internal.EnumLiteMap<ResetReason> internalGetValueMap() {
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
            public static ResetReason valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ResetConnection() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearForceDisconnect() {
            this.forceDisconnect_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResetReason() {
            this.resetReason_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimeout() {
            this.timeout_ = 0;
        }

        public static ResetConnection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ResetConnection parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ResetConnection) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetConnection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ResetConnection> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setForceDisconnect(boolean z) {
            this.forceDisconnect_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetReason(ResetReason resetReason) {
            if (resetReason != null) {
                this.resetReason_ = resetReason.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetReasonValue(int i) {
            this.resetReason_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimeout(int i) {
            this.timeout_ = i;
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
                    ResetConnection resetConnection = (ResetConnection) obj2;
                    this.timeout_ = visitor.visitInt(this.timeout_ != 0, this.timeout_, resetConnection.timeout_ != 0, resetConnection.timeout_);
                    boolean z2 = this.forceDisconnect_;
                    boolean z3 = resetConnection.forceDisconnect_;
                    this.forceDisconnect_ = visitor.visitBoolean(z2, z2, z3, z3);
                    boolean z4 = this.resetReason_ != 0;
                    int i = this.resetReason_;
                    if (resetConnection.resetReason_ != 0) {
                        z = true;
                    }
                    this.resetReason_ = visitor.visitInt(z4, i, z, resetConnection.resetReason_);
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
                                    this.timeout_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.forceDisconnect_ = codedInputStream.readBool();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.resetReason_ = codedInputStream.readEnum();
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
                    return new ResetConnection();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ResetConnection.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.ResetConnectionOrBuilder
        public boolean getForceDisconnect() {
            return this.forceDisconnect_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.ResetConnectionOrBuilder
        public ResetReason getResetReason() {
            ResetReason forNumber = ResetReason.forNumber(this.resetReason_);
            return forNumber == null ? ResetReason.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.ResetConnectionOrBuilder
        public int getResetReasonValue() {
            return this.resetReason_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.timeout_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            boolean z = this.forceDisconnect_;
            if (z) {
                i2 += CodedOutputStream.computeBoolSize(2, z);
            }
            if (this.resetReason_ != ResetReason.UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.resetReason_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.ResetConnectionOrBuilder
        public int getTimeout() {
            return this.timeout_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.timeout_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            boolean z = this.forceDisconnect_;
            if (z) {
                codedOutputStream.writeBool(2, z);
            }
            if (this.resetReason_ != ResetReason.UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(3, this.resetReason_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ResetConnection resetConnection) {
            return DEFAULT_INSTANCE.createBuilder(resetConnection);
        }

        public static ResetConnection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetConnection) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetConnection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ResetConnection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ResetConnection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ResetConnection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ResetConnection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ResetConnection parseFrom(InputStream inputStream) throws IOException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetConnection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetConnection parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ResetConnection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ResetConnectionOrBuilder extends MessageLiteOrBuilder {
        boolean getForceDisconnect();

        ResetConnection.ResetReason getResetReason();

        int getResetReasonValue();

        int getTimeout();
    }

    /* loaded from: classes6.dex */
    public static final class SetLocale extends GeneratedMessageLite<SetLocale, Builder> implements SetLocaleOrBuilder {
        private static final SetLocale DEFAULT_INSTANCE = new SetLocale();
        public static final int LOCALE_FIELD_NUMBER = 1;
        private static volatile Parser<SetLocale> PARSER;
        private Locale locale_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SetLocale, Builder> implements SetLocaleOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearLocale() {
                copyOnWrite();
                ((SetLocale) this.instance).clearLocale();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.SetLocaleOrBuilder
            public Locale getLocale() {
                return ((SetLocale) this.instance).getLocale();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.SetLocaleOrBuilder
            public boolean hasLocale() {
                return ((SetLocale) this.instance).hasLocale();
            }

            public Builder mergeLocale(Locale locale) {
                copyOnWrite();
                ((SetLocale) this.instance).mergeLocale(locale);
                return this;
            }

            public Builder setLocale(Locale locale) {
                copyOnWrite();
                ((SetLocale) this.instance).setLocale(locale);
                return this;
            }

            private Builder() {
                super(SetLocale.DEFAULT_INSTANCE);
            }

            public Builder setLocale(Locale.Builder builder) {
                copyOnWrite();
                ((SetLocale) this.instance).setLocale(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SetLocale() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLocale() {
            this.locale_ = null;
        }

        public static SetLocale getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLocale(Locale locale) {
            Locale locale2 = this.locale_;
            if (locale2 != null && locale2 != Locale.getDefaultInstance()) {
                this.locale_ = Locale.newBuilder(this.locale_).mergeFrom((Locale.Builder) locale).mo10085buildPartial();
            } else {
                this.locale_ = locale;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SetLocale parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SetLocale) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetLocale parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SetLocale> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocale(Locale locale) {
            if (locale != null) {
                this.locale_ = locale;
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
                    this.locale_ = (Locale) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.locale_, ((SetLocale) obj2).locale_);
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
                                    Locale.Builder mo10081toBuilder = this.locale_ != null ? this.locale_.mo10081toBuilder() : null;
                                    this.locale_ = (Locale) codedInputStream.readMessage(Locale.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Locale.Builder) this.locale_);
                                        this.locale_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new SetLocale();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SetLocale.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.SetLocaleOrBuilder
        public Locale getLocale() {
            Locale locale = this.locale_;
            return locale == null ? Locale.getDefaultInstance() : locale;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.locale_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getLocale());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.SetLocaleOrBuilder
        public boolean hasLocale() {
            return this.locale_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.locale_ != null) {
                codedOutputStream.writeMessage(1, getLocale());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SetLocale setLocale) {
            return DEFAULT_INSTANCE.createBuilder(setLocale);
        }

        public static SetLocale parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetLocale) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetLocale parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SetLocale parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocale(Locale.Builder builder) {
            this.locale_ = builder.mo10084build();
        }

        public static SetLocale parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SetLocale parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SetLocale parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SetLocale parseFrom(InputStream inputStream) throws IOException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetLocale parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetLocale parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SetLocale parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetLocale) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SetLocaleOrBuilder extends MessageLiteOrBuilder {
        Locale getLocale();

        boolean hasLocale();
    }

    /* loaded from: classes6.dex */
    public static final class SetWakewords extends GeneratedMessageLite<SetWakewords, Builder> implements SetWakewordsOrBuilder {
        public static final int ACTIVE_WAKEWORDS_FIELD_NUMBER = 1;
        private static final SetWakewords DEFAULT_INSTANCE = new SetWakewords();
        private static volatile Parser<SetWakewords> PARSER;
        private Internal.ProtobufList<String> activeWakewords_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SetWakewords, Builder> implements SetWakewordsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addActiveWakewords(String str) {
                copyOnWrite();
                ((SetWakewords) this.instance).addActiveWakewords(str);
                return this;
            }

            public Builder addActiveWakewordsBytes(ByteString byteString) {
                copyOnWrite();
                ((SetWakewords) this.instance).addActiveWakewordsBytes(byteString);
                return this;
            }

            public Builder addAllActiveWakewords(Iterable<String> iterable) {
                copyOnWrite();
                ((SetWakewords) this.instance).addAllActiveWakewords(iterable);
                return this;
            }

            public Builder clearActiveWakewords() {
                copyOnWrite();
                ((SetWakewords) this.instance).clearActiveWakewords();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.SetWakewordsOrBuilder
            public String getActiveWakewords(int i) {
                return ((SetWakewords) this.instance).getActiveWakewords(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.System.SetWakewordsOrBuilder
            public ByteString getActiveWakewordsBytes(int i) {
                return ((SetWakewords) this.instance).getActiveWakewordsBytes(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.System.SetWakewordsOrBuilder
            public int getActiveWakewordsCount() {
                return ((SetWakewords) this.instance).getActiveWakewordsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.SetWakewordsOrBuilder
            public List<String> getActiveWakewordsList() {
                return Collections.unmodifiableList(((SetWakewords) this.instance).getActiveWakewordsList());
            }

            public Builder setActiveWakewords(int i, String str) {
                copyOnWrite();
                ((SetWakewords) this.instance).setActiveWakewords(i, str);
                return this;
            }

            private Builder() {
                super(SetWakewords.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SetWakewords() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addActiveWakewords(String str) {
            if (str != null) {
                ensureActiveWakewordsIsMutable();
                this.activeWakewords_.add(str);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addActiveWakewordsBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                ensureActiveWakewordsIsMutable();
                this.activeWakewords_.add(byteString.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllActiveWakewords(Iterable<String> iterable) {
            ensureActiveWakewordsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.activeWakewords_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearActiveWakewords() {
            this.activeWakewords_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureActiveWakewordsIsMutable() {
            if (!this.activeWakewords_.isModifiable()) {
                this.activeWakewords_ = GeneratedMessageLite.mutableCopy(this.activeWakewords_);
            }
        }

        public static SetWakewords getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SetWakewords parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SetWakewords) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetWakewords parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SetWakewords> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActiveWakewords(int i, String str) {
            if (str != null) {
                ensureActiveWakewordsIsMutable();
                this.activeWakewords_.set(i, str);
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
                    this.activeWakewords_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.activeWakewords_, ((SetWakewords) obj2).activeWakewords_);
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
                                    if (!this.activeWakewords_.isModifiable()) {
                                        this.activeWakewords_ = GeneratedMessageLite.mutableCopy(this.activeWakewords_);
                                    }
                                    this.activeWakewords_.add(readStringRequireUtf8);
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
                    this.activeWakewords_.makeImmutable();
                    return null;
                case 6:
                    return new SetWakewords();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SetWakewords.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.SetWakewordsOrBuilder
        public String getActiveWakewords(int i) {
            return this.activeWakewords_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.System.SetWakewordsOrBuilder
        public ByteString getActiveWakewordsBytes(int i) {
            return ByteString.copyFromUtf8(this.activeWakewords_.get(i));
        }

        @Override // com.amazon.alexa.accessory.protocol.System.SetWakewordsOrBuilder
        public int getActiveWakewordsCount() {
            return this.activeWakewords_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.System.SetWakewordsOrBuilder
        public List<String> getActiveWakewordsList() {
            return this.activeWakewords_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.activeWakewords_.size(); i3++) {
                i2 += CodedOutputStream.computeStringSizeNoTag(this.activeWakewords_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + (getActiveWakewordsList().size() * 1) + 0 + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.activeWakewords_.size(); i++) {
                codedOutputStream.writeString(1, this.activeWakewords_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SetWakewords setWakewords) {
            return DEFAULT_INSTANCE.createBuilder(setWakewords);
        }

        public static SetWakewords parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetWakewords) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetWakewords parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SetWakewords parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SetWakewords parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SetWakewords parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SetWakewords parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SetWakewords parseFrom(InputStream inputStream) throws IOException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetWakewords parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetWakewords parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SetWakewords parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetWakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SetWakewordsOrBuilder extends MessageLiteOrBuilder {
        String getActiveWakewords(int i);

        ByteString getActiveWakewordsBytes(int i);

        int getActiveWakewordsCount();

        List<String> getActiveWakewordsList();
    }

    /* loaded from: classes6.dex */
    public static final class SwitchCurrentUser extends GeneratedMessageLite<SwitchCurrentUser, Builder> implements SwitchCurrentUserOrBuilder {
        private static final SwitchCurrentUser DEFAULT_INSTANCE = new SwitchCurrentUser();
        private static volatile Parser<SwitchCurrentUser> PARSER = null;
        public static final int USER_ID_FIELD_NUMBER = 1;
        private int userId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SwitchCurrentUser, Builder> implements SwitchCurrentUserOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearUserId() {
                copyOnWrite();
                ((SwitchCurrentUser) this.instance).clearUserId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.SwitchCurrentUserOrBuilder
            public int getUserId() {
                return ((SwitchCurrentUser) this.instance).getUserId();
            }

            public Builder setUserId(int i) {
                copyOnWrite();
                ((SwitchCurrentUser) this.instance).setUserId(i);
                return this;
            }

            private Builder() {
                super(SwitchCurrentUser.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SwitchCurrentUser() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUserId() {
            this.userId_ = 0;
        }

        public static SwitchCurrentUser getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SwitchCurrentUser parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SwitchCurrentUser parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SwitchCurrentUser> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserId(int i) {
            this.userId_ = i;
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
                    SwitchCurrentUser switchCurrentUser = (SwitchCurrentUser) obj2;
                    boolean z2 = this.userId_ != 0;
                    int i = this.userId_;
                    if (switchCurrentUser.userId_ != 0) {
                        z = true;
                    }
                    this.userId_ = visitor.visitInt(z2, i, z, switchCurrentUser.userId_);
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
                                        this.userId_ = codedInputStream.readUInt32();
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
                    return new SwitchCurrentUser();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SwitchCurrentUser.class) {
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
            int i3 = this.userId_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.SwitchCurrentUserOrBuilder
        public int getUserId() {
            return this.userId_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.userId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SwitchCurrentUser switchCurrentUser) {
            return DEFAULT_INSTANCE.createBuilder(switchCurrentUser);
        }

        public static SwitchCurrentUser parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SwitchCurrentUser parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SwitchCurrentUser parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SwitchCurrentUser parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SwitchCurrentUser parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SwitchCurrentUser parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SwitchCurrentUser parseFrom(InputStream inputStream) throws IOException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SwitchCurrentUser parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SwitchCurrentUser parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SwitchCurrentUser parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SwitchCurrentUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SwitchCurrentUserOrBuilder extends MessageLiteOrBuilder {
        int getUserId();
    }

    /* loaded from: classes6.dex */
    public static final class SynchronizeSettings extends GeneratedMessageLite<SynchronizeSettings, Builder> implements SynchronizeSettingsOrBuilder {
        private static final SynchronizeSettings DEFAULT_INSTANCE = new SynchronizeSettings();
        private static volatile Parser<SynchronizeSettings> PARSER = null;
        public static final int TIMESTAMP_HI_FIELD_NUMBER = 1;
        public static final int TIMESTAMP_LO_FIELD_NUMBER = 2;
        private int timestampHi_;
        private int timestampLo_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SynchronizeSettings, Builder> implements SynchronizeSettingsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearTimestampHi() {
                copyOnWrite();
                ((SynchronizeSettings) this.instance).clearTimestampHi();
                return this;
            }

            public Builder clearTimestampLo() {
                copyOnWrite();
                ((SynchronizeSettings) this.instance).clearTimestampLo();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.SynchronizeSettingsOrBuilder
            public int getTimestampHi() {
                return ((SynchronizeSettings) this.instance).getTimestampHi();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.SynchronizeSettingsOrBuilder
            public int getTimestampLo() {
                return ((SynchronizeSettings) this.instance).getTimestampLo();
            }

            public Builder setTimestampHi(int i) {
                copyOnWrite();
                ((SynchronizeSettings) this.instance).setTimestampHi(i);
                return this;
            }

            public Builder setTimestampLo(int i) {
                copyOnWrite();
                ((SynchronizeSettings) this.instance).setTimestampLo(i);
                return this;
            }

            private Builder() {
                super(SynchronizeSettings.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SynchronizeSettings() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimestampHi() {
            this.timestampHi_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimestampLo() {
            this.timestampLo_ = 0;
        }

        public static SynchronizeSettings getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SynchronizeSettings parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SynchronizeSettings) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SynchronizeSettings parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SynchronizeSettings> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestampHi(int i) {
            this.timestampHi_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestampLo(int i) {
            this.timestampLo_ = i;
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
                    SynchronizeSettings synchronizeSettings = (SynchronizeSettings) obj2;
                    this.timestampHi_ = visitor.visitInt(this.timestampHi_ != 0, this.timestampHi_, synchronizeSettings.timestampHi_ != 0, synchronizeSettings.timestampHi_);
                    boolean z2 = this.timestampLo_ != 0;
                    int i = this.timestampLo_;
                    if (synchronizeSettings.timestampLo_ != 0) {
                        z = true;
                    }
                    this.timestampLo_ = visitor.visitInt(z2, i, z, synchronizeSettings.timestampLo_);
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
                                    this.timestampHi_ = codedInputStream.readUInt32();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.timestampLo_ = codedInputStream.readUInt32();
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
                    return new SynchronizeSettings();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SynchronizeSettings.class) {
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
            int i3 = this.timestampHi_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int i4 = this.timestampLo_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.SynchronizeSettingsOrBuilder
        public int getTimestampHi() {
            return this.timestampHi_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.SynchronizeSettingsOrBuilder
        public int getTimestampLo() {
            return this.timestampLo_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.timestampHi_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.timestampLo_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SynchronizeSettings synchronizeSettings) {
            return DEFAULT_INSTANCE.createBuilder(synchronizeSettings);
        }

        public static SynchronizeSettings parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SynchronizeSettings) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SynchronizeSettings parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SynchronizeSettings parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SynchronizeSettings parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SynchronizeSettings parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SynchronizeSettings parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SynchronizeSettings parseFrom(InputStream inputStream) throws IOException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SynchronizeSettings parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SynchronizeSettings parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SynchronizeSettings parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SynchronizeSettings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SynchronizeSettingsOrBuilder extends MessageLiteOrBuilder {
        int getTimestampHi();

        int getTimestampLo();
    }

    /* loaded from: classes6.dex */
    public static final class UnpairUser extends GeneratedMessageLite<UnpairUser, Builder> implements UnpairUserOrBuilder {
        public static final int ADDRESS_FIELD_NUMBER = 1;
        private static final UnpairUser DEFAULT_INSTANCE = new UnpairUser();
        private static volatile Parser<UnpairUser> PARSER;
        private String address_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UnpairUser, Builder> implements UnpairUserOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAddress() {
                copyOnWrite();
                ((UnpairUser) this.instance).clearAddress();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UnpairUserOrBuilder
            public String getAddress() {
                return ((UnpairUser) this.instance).getAddress();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UnpairUserOrBuilder
            public ByteString getAddressBytes() {
                return ((UnpairUser) this.instance).getAddressBytes();
            }

            public Builder setAddress(String str) {
                copyOnWrite();
                ((UnpairUser) this.instance).setAddress(str);
                return this;
            }

            public Builder setAddressBytes(ByteString byteString) {
                copyOnWrite();
                ((UnpairUser) this.instance).setAddressBytes(byteString);
                return this;
            }

            private Builder() {
                super(UnpairUser.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UnpairUser() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAddress() {
            this.address_ = getDefaultInstance().getAddress();
        }

        public static UnpairUser getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UnpairUser parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnpairUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnpairUser parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UnpairUser> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddress(String str) {
            if (str != null) {
                this.address_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddressBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.address_ = byteString.toStringUtf8();
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
                    UnpairUser unpairUser = (UnpairUser) obj2;
                    this.address_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.address_.isEmpty(), this.address_, true ^ unpairUser.address_.isEmpty(), unpairUser.address_);
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
                                        this.address_ = codedInputStream.readStringRequireUtf8();
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
                    return new UnpairUser();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UnpairUser.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.UnpairUserOrBuilder
        public String getAddress() {
            return this.address_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UnpairUserOrBuilder
        public ByteString getAddressBytes() {
            return ByteString.copyFromUtf8(this.address_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.address_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getAddress());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.address_.isEmpty()) {
                codedOutputStream.writeString(1, getAddress());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UnpairUser unpairUser) {
            return DEFAULT_INSTANCE.createBuilder(unpairUser);
        }

        public static UnpairUser parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnpairUser) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnpairUser parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UnpairUser parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UnpairUser parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UnpairUser parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UnpairUser parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UnpairUser parseFrom(InputStream inputStream) throws IOException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnpairUser parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnpairUser parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UnpairUser parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnpairUser) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UnpairUserOrBuilder extends MessageLiteOrBuilder {
        String getAddress();

        ByteString getAddressBytes();
    }

    /* loaded from: classes6.dex */
    public static final class UpdateUsers extends GeneratedMessageLite<UpdateUsers, Builder> implements UpdateUsersOrBuilder {
        private static final UpdateUsers DEFAULT_INSTANCE = new UpdateUsers();
        private static volatile Parser<UpdateUsers> PARSER = null;
        public static final int USERS_FIELD_NUMBER = 1;
        private Users users_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UpdateUsers, Builder> implements UpdateUsersOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearUsers() {
                copyOnWrite();
                ((UpdateUsers) this.instance).clearUsers();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UpdateUsersOrBuilder
            public Users getUsers() {
                return ((UpdateUsers) this.instance).getUsers();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UpdateUsersOrBuilder
            public boolean hasUsers() {
                return ((UpdateUsers) this.instance).hasUsers();
            }

            public Builder mergeUsers(Users users) {
                copyOnWrite();
                ((UpdateUsers) this.instance).mergeUsers(users);
                return this;
            }

            public Builder setUsers(Users users) {
                copyOnWrite();
                ((UpdateUsers) this.instance).setUsers(users);
                return this;
            }

            private Builder() {
                super(UpdateUsers.DEFAULT_INSTANCE);
            }

            public Builder setUsers(Users.Builder builder) {
                copyOnWrite();
                ((UpdateUsers) this.instance).setUsers(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UpdateUsers() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUsers() {
            this.users_ = null;
        }

        public static UpdateUsers getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUsers(Users users) {
            Users users2 = this.users_;
            if (users2 != null && users2 != Users.getDefaultInstance()) {
                this.users_ = Users.newBuilder(this.users_).mergeFrom((Users.Builder) users).mo10085buildPartial();
            } else {
                this.users_ = users;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UpdateUsers parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateUsers) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateUsers parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UpdateUsers> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsers(Users users) {
            if (users != null) {
                this.users_ = users;
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
                    this.users_ = (Users) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.users_, ((UpdateUsers) obj2).users_);
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
                                    Users.Builder mo10081toBuilder = this.users_ != null ? this.users_.mo10081toBuilder() : null;
                                    this.users_ = (Users) codedInputStream.readMessage(Users.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Users.Builder) this.users_);
                                        this.users_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new UpdateUsers();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UpdateUsers.class) {
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
            if (this.users_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getUsers());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UpdateUsersOrBuilder
        public Users getUsers() {
            Users users = this.users_;
            return users == null ? Users.getDefaultInstance() : users;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UpdateUsersOrBuilder
        public boolean hasUsers() {
            return this.users_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.users_ != null) {
                codedOutputStream.writeMessage(1, getUsers());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UpdateUsers updateUsers) {
            return DEFAULT_INSTANCE.createBuilder(updateUsers);
        }

        public static UpdateUsers parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateUsers) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateUsers parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UpdateUsers parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsers(Users.Builder builder) {
            this.users_ = builder.mo10084build();
        }

        public static UpdateUsers parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UpdateUsers parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UpdateUsers parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UpdateUsers parseFrom(InputStream inputStream) throws IOException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateUsers parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateUsers parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UpdateUsers parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateUsers) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UpdateUsersOrBuilder extends MessageLiteOrBuilder {
        Users getUsers();

        boolean hasUsers();
    }

    /* loaded from: classes6.dex */
    public static final class User extends GeneratedMessageLite<User, Builder> implements UserOrBuilder {
        public static final int AAP_CONNECTED_FIELD_NUMBER = 10;
        public static final int ADDRESS_FIELD_NUMBER = 3;
        public static final int CONNECTED_AUDIO_TYPES_FIELD_NUMBER = 5;
        public static final int CONNECTED_FIELD_NUMBER = 7;
        public static final int FOCUS_FIELD_NUMBER = 8;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int LAST_KNOWN_AAP_CAPABILITY_FIELD_NUMBER = 11;
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser<User> PARSER = null;
        public static final int PRIMARY_AUDIO_CONNECTION_TYPE_FIELD_NUMBER = 6;
        public static final int SELF_FIELD_NUMBER = 4;
        public static final int VOLUME_FIELD_NUMBER = 9;
        private boolean aapConnected_;
        private int bitField0_;
        private int connectedAudioTypesMemoizedSerializedSize;
        private boolean connected_;
        private int focus_;
        private int id_;
        private int lastKnownAapCapability_;
        private int primaryAudioConnectionType_;
        private boolean self_;
        private int volume_;
        private static final Internal.ListAdapter.Converter<Integer, AudioConnectionType> connectedAudioTypes_converter_ = new Internal.ListAdapter.Converter<Integer, AudioConnectionType>() { // from class: com.amazon.alexa.accessory.protocol.System.User.1
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public AudioConnectionType convert(Integer num) {
                AudioConnectionType forNumber = AudioConnectionType.forNumber(num.intValue());
                return forNumber == null ? AudioConnectionType.UNRECOGNIZED : forNumber;
            }
        };
        private static final User DEFAULT_INSTANCE = new User();
        private String name_ = "";
        private String address_ = "";
        private Internal.IntList connectedAudioTypes_ = GeneratedMessageLite.emptyIntList();

        /* loaded from: classes6.dex */
        public enum AapCapability implements Internal.EnumLite {
            AAP_SUPPORT_UNKNOWN(0),
            AAP_NOT_SUPPORTED(1),
            AAP_SUPPORTED(2),
            UNRECOGNIZED(-1);
            
            public static final int AAP_NOT_SUPPORTED_VALUE = 1;
            public static final int AAP_SUPPORTED_VALUE = 2;
            public static final int AAP_SUPPORT_UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<AapCapability> internalValueMap = new Internal.EnumLiteMap<AapCapability>() { // from class: com.amazon.alexa.accessory.protocol.System.User.AapCapability.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public AapCapability mo9850findValueByNumber(int i) {
                    return AapCapability.forNumber(i);
                }
            };
            private final int value;

            AapCapability(int i) {
                this.value = i;
            }

            public static AapCapability forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return AAP_NOT_SUPPORTED;
                    }
                    if (i == 2) {
                        return AAP_SUPPORTED;
                    }
                    return null;
                }
                return AAP_SUPPORT_UNKNOWN;
            }

            public static Internal.EnumLiteMap<AapCapability> internalGetValueMap() {
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
            public static AapCapability valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public enum AudioConnectionType implements Internal.EnumLite {
            UNKNOWN(0),
            NONE(1),
            A2DP(2),
            HFP(3),
            UNRECOGNIZED(-1);
            
            public static final int A2DP_VALUE = 2;
            public static final int HFP_VALUE = 3;
            public static final int NONE_VALUE = 1;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<AudioConnectionType> internalValueMap = new Internal.EnumLiteMap<AudioConnectionType>() { // from class: com.amazon.alexa.accessory.protocol.System.User.AudioConnectionType.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public AudioConnectionType mo9850findValueByNumber(int i) {
                    return AudioConnectionType.forNumber(i);
                }
            };
            private final int value;

            AudioConnectionType(int i) {
                this.value = i;
            }

            public static AudioConnectionType forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return NONE;
                    }
                    if (i == 2) {
                        return A2DP;
                    }
                    if (i == 3) {
                        return HFP;
                    }
                    return null;
                }
                return UNKNOWN;
            }

            public static Internal.EnumLiteMap<AudioConnectionType> internalGetValueMap() {
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
            public static AudioConnectionType valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<User, Builder> implements UserOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllConnectedAudioTypes(Iterable<? extends AudioConnectionType> iterable) {
                copyOnWrite();
                ((User) this.instance).addAllConnectedAudioTypes(iterable);
                return this;
            }

            public Builder addAllConnectedAudioTypesValue(Iterable<Integer> iterable) {
                copyOnWrite();
                ((User) this.instance).addAllConnectedAudioTypesValue(iterable);
                return this;
            }

            public Builder addConnectedAudioTypes(AudioConnectionType audioConnectionType) {
                copyOnWrite();
                ((User) this.instance).addConnectedAudioTypes(audioConnectionType);
                return this;
            }

            public Builder addConnectedAudioTypesValue(int i) {
                ((User) this.instance).addConnectedAudioTypesValue(i);
                return this;
            }

            public Builder clearAapConnected() {
                copyOnWrite();
                ((User) this.instance).clearAapConnected();
                return this;
            }

            public Builder clearAddress() {
                copyOnWrite();
                ((User) this.instance).clearAddress();
                return this;
            }

            public Builder clearConnected() {
                copyOnWrite();
                ((User) this.instance).clearConnected();
                return this;
            }

            public Builder clearConnectedAudioTypes() {
                copyOnWrite();
                ((User) this.instance).clearConnectedAudioTypes();
                return this;
            }

            public Builder clearFocus() {
                copyOnWrite();
                ((User) this.instance).clearFocus();
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((User) this.instance).clearId();
                return this;
            }

            public Builder clearLastKnownAapCapability() {
                copyOnWrite();
                ((User) this.instance).clearLastKnownAapCapability();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((User) this.instance).clearName();
                return this;
            }

            public Builder clearPrimaryAudioConnectionType() {
                copyOnWrite();
                ((User) this.instance).clearPrimaryAudioConnectionType();
                return this;
            }

            public Builder clearSelf() {
                copyOnWrite();
                ((User) this.instance).clearSelf();
                return this;
            }

            public Builder clearVolume() {
                copyOnWrite();
                ((User) this.instance).clearVolume();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public boolean getAapConnected() {
                return ((User) this.instance).getAapConnected();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public String getAddress() {
                return ((User) this.instance).getAddress();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public ByteString getAddressBytes() {
                return ((User) this.instance).getAddressBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public boolean getConnected() {
                return ((User) this.instance).getConnected();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public AudioConnectionType getConnectedAudioTypes(int i) {
                return ((User) this.instance).getConnectedAudioTypes(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public int getConnectedAudioTypesCount() {
                return ((User) this.instance).getConnectedAudioTypesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public List<AudioConnectionType> getConnectedAudioTypesList() {
                return ((User) this.instance).getConnectedAudioTypesList();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public int getConnectedAudioTypesValue(int i) {
                return ((User) this.instance).getConnectedAudioTypesValue(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public List<Integer> getConnectedAudioTypesValueList() {
                return Collections.unmodifiableList(((User) this.instance).getConnectedAudioTypesValueList());
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public Focus getFocus() {
                return ((User) this.instance).getFocus();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public int getFocusValue() {
                return ((User) this.instance).getFocusValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public int getId() {
                return ((User) this.instance).getId();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public AapCapability getLastKnownAapCapability() {
                return ((User) this.instance).getLastKnownAapCapability();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public int getLastKnownAapCapabilityValue() {
                return ((User) this.instance).getLastKnownAapCapabilityValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public String getName() {
                return ((User) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public ByteString getNameBytes() {
                return ((User) this.instance).getNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public AudioConnectionType getPrimaryAudioConnectionType() {
                return ((User) this.instance).getPrimaryAudioConnectionType();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public int getPrimaryAudioConnectionTypeValue() {
                return ((User) this.instance).getPrimaryAudioConnectionTypeValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public boolean getSelf() {
                return ((User) this.instance).getSelf();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
            public int getVolume() {
                return ((User) this.instance).getVolume();
            }

            public Builder setAapConnected(boolean z) {
                copyOnWrite();
                ((User) this.instance).setAapConnected(z);
                return this;
            }

            public Builder setAddress(String str) {
                copyOnWrite();
                ((User) this.instance).setAddress(str);
                return this;
            }

            public Builder setAddressBytes(ByteString byteString) {
                copyOnWrite();
                ((User) this.instance).setAddressBytes(byteString);
                return this;
            }

            public Builder setConnected(boolean z) {
                copyOnWrite();
                ((User) this.instance).setConnected(z);
                return this;
            }

            public Builder setConnectedAudioTypes(int i, AudioConnectionType audioConnectionType) {
                copyOnWrite();
                ((User) this.instance).setConnectedAudioTypes(i, audioConnectionType);
                return this;
            }

            public Builder setConnectedAudioTypesValue(int i, int i2) {
                copyOnWrite();
                ((User) this.instance).setConnectedAudioTypesValue(i, i2);
                return this;
            }

            public Builder setFocus(Focus focus) {
                copyOnWrite();
                ((User) this.instance).setFocus(focus);
                return this;
            }

            public Builder setFocusValue(int i) {
                copyOnWrite();
                ((User) this.instance).setFocusValue(i);
                return this;
            }

            public Builder setId(int i) {
                copyOnWrite();
                ((User) this.instance).setId(i);
                return this;
            }

            public Builder setLastKnownAapCapability(AapCapability aapCapability) {
                copyOnWrite();
                ((User) this.instance).setLastKnownAapCapability(aapCapability);
                return this;
            }

            public Builder setLastKnownAapCapabilityValue(int i) {
                copyOnWrite();
                ((User) this.instance).setLastKnownAapCapabilityValue(i);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((User) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((User) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setPrimaryAudioConnectionType(AudioConnectionType audioConnectionType) {
                copyOnWrite();
                ((User) this.instance).setPrimaryAudioConnectionType(audioConnectionType);
                return this;
            }

            public Builder setPrimaryAudioConnectionTypeValue(int i) {
                copyOnWrite();
                ((User) this.instance).setPrimaryAudioConnectionTypeValue(i);
                return this;
            }

            public Builder setSelf(boolean z) {
                copyOnWrite();
                ((User) this.instance).setSelf(z);
                return this;
            }

            public Builder setVolume(int i) {
                copyOnWrite();
                ((User) this.instance).setVolume(i);
                return this;
            }

            private Builder() {
                super(User.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        public enum Focus implements Internal.EnumLite {
            UNKNOWN_FOCUS(0),
            NO_FOCUS(1),
            HAS_FOCUS(2),
            SUSPENDED(3),
            BORROWED(4),
            UNRECOGNIZED(-1);
            
            public static final int BORROWED_VALUE = 4;
            public static final int HAS_FOCUS_VALUE = 2;
            public static final int NO_FOCUS_VALUE = 1;
            public static final int SUSPENDED_VALUE = 3;
            public static final int UNKNOWN_FOCUS_VALUE = 0;
            private static final Internal.EnumLiteMap<Focus> internalValueMap = new Internal.EnumLiteMap<Focus>() { // from class: com.amazon.alexa.accessory.protocol.System.User.Focus.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public Focus mo9850findValueByNumber(int i) {
                    return Focus.forNumber(i);
                }
            };
            private final int value;

            Focus(int i) {
                this.value = i;
            }

            public static Focus forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return NO_FOCUS;
                    }
                    if (i == 2) {
                        return HAS_FOCUS;
                    }
                    if (i == 3) {
                        return SUSPENDED;
                    }
                    if (i == 4) {
                        return BORROWED;
                    }
                    return null;
                }
                return UNKNOWN_FOCUS;
            }

            public static Internal.EnumLiteMap<Focus> internalGetValueMap() {
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
            public static Focus valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private User() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllConnectedAudioTypes(Iterable<? extends AudioConnectionType> iterable) {
            ensureConnectedAudioTypesIsMutable();
            for (AudioConnectionType audioConnectionType : iterable) {
                this.connectedAudioTypes_.addInt(audioConnectionType.getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllConnectedAudioTypesValue(Iterable<Integer> iterable) {
            ensureConnectedAudioTypesIsMutable();
            for (Integer num : iterable) {
                this.connectedAudioTypes_.addInt(num.intValue());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addConnectedAudioTypes(AudioConnectionType audioConnectionType) {
            if (audioConnectionType != null) {
                ensureConnectedAudioTypesIsMutable();
                this.connectedAudioTypes_.addInt(audioConnectionType.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addConnectedAudioTypesValue(int i) {
            ensureConnectedAudioTypesIsMutable();
            this.connectedAudioTypes_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAapConnected() {
            this.aapConnected_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAddress() {
            this.address_ = getDefaultInstance().getAddress();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConnected() {
            this.connected_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConnectedAudioTypes() {
            this.connectedAudioTypes_ = GeneratedMessageLite.emptyIntList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFocus() {
            this.focus_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearId() {
            this.id_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLastKnownAapCapability() {
            this.lastKnownAapCapability_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPrimaryAudioConnectionType() {
            this.primaryAudioConnectionType_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSelf() {
            this.self_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVolume() {
            this.volume_ = 0;
        }

        private void ensureConnectedAudioTypesIsMutable() {
            if (!this.connectedAudioTypes_.isModifiable()) {
                this.connectedAudioTypes_ = GeneratedMessageLite.mutableCopy(this.connectedAudioTypes_);
            }
        }

        public static User getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static User parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (User) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static User parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<User> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAapConnected(boolean z) {
            this.aapConnected_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddress(String str) {
            if (str != null) {
                this.address_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddressBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.address_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnected(boolean z) {
            this.connected_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectedAudioTypes(int i, AudioConnectionType audioConnectionType) {
            if (audioConnectionType != null) {
                ensureConnectedAudioTypesIsMutable();
                this.connectedAudioTypes_.setInt(i, audioConnectionType.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectedAudioTypesValue(int i, int i2) {
            ensureConnectedAudioTypesIsMutable();
            this.connectedAudioTypes_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFocus(Focus focus) {
            if (focus != null) {
                this.focus_ = focus.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFocusValue(int i) {
            this.focus_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setId(int i) {
            this.id_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLastKnownAapCapability(AapCapability aapCapability) {
            if (aapCapability != null) {
                this.lastKnownAapCapability_ = aapCapability.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLastKnownAapCapabilityValue(int i) {
            this.lastKnownAapCapability_ = i;
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
        public void setPrimaryAudioConnectionType(AudioConnectionType audioConnectionType) {
            if (audioConnectionType != null) {
                this.primaryAudioConnectionType_ = audioConnectionType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPrimaryAudioConnectionTypeValue(int i) {
            this.primaryAudioConnectionType_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelf(boolean z) {
            this.self_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVolume(int i) {
            this.volume_ = i;
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
                    User user = (User) obj2;
                    this.id_ = visitor.visitInt(this.id_ != 0, this.id_, user.id_ != 0, user.id_);
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !user.name_.isEmpty(), user.name_);
                    this.address_ = visitor.visitString(!this.address_.isEmpty(), this.address_, !user.address_.isEmpty(), user.address_);
                    boolean z2 = this.self_;
                    boolean z3 = user.self_;
                    this.self_ = visitor.visitBoolean(z2, z2, z3, z3);
                    this.connectedAudioTypes_ = visitor.visitIntList(this.connectedAudioTypes_, user.connectedAudioTypes_);
                    this.primaryAudioConnectionType_ = visitor.visitInt(this.primaryAudioConnectionType_ != 0, this.primaryAudioConnectionType_, user.primaryAudioConnectionType_ != 0, user.primaryAudioConnectionType_);
                    boolean z4 = this.connected_;
                    boolean z5 = user.connected_;
                    this.connected_ = visitor.visitBoolean(z4, z4, z5, z5);
                    this.focus_ = visitor.visitInt(this.focus_ != 0, this.focus_, user.focus_ != 0, user.focus_);
                    this.volume_ = visitor.visitInt(this.volume_ != 0, this.volume_, user.volume_ != 0, user.volume_);
                    boolean z6 = this.aapConnected_;
                    boolean z7 = user.aapConnected_;
                    this.aapConnected_ = visitor.visitBoolean(z6, z6, z7, z7);
                    boolean z8 = this.lastKnownAapCapability_ != 0;
                    int i = this.lastKnownAapCapability_;
                    if (user.lastKnownAapCapability_ != 0) {
                        z = true;
                    }
                    this.lastKnownAapCapability_ = visitor.visitInt(z8, i, z, user.lastKnownAapCapability_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= user.bitField0_;
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
                            switch (readTag) {
                                case 0:
                                    break;
                                case 8:
                                    this.id_ = codedInputStream.readUInt32();
                                    continue;
                                case 18:
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 26:
                                    this.address_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 32:
                                    this.self_ = codedInputStream.readBool();
                                    continue;
                                case 40:
                                    if (!this.connectedAudioTypes_.isModifiable()) {
                                        this.connectedAudioTypes_ = GeneratedMessageLite.mutableCopy(this.connectedAudioTypes_);
                                    }
                                    this.connectedAudioTypes_.addInt(codedInputStream.readEnum());
                                    continue;
                                case 42:
                                    if (!this.connectedAudioTypes_.isModifiable()) {
                                        this.connectedAudioTypes_ = GeneratedMessageLite.mutableCopy(this.connectedAudioTypes_);
                                    }
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.connectedAudioTypes_.addInt(codedInputStream.readEnum());
                                    }
                                    codedInputStream.popLimit(pushLimit);
                                    continue;
                                case 48:
                                    this.primaryAudioConnectionType_ = codedInputStream.readEnum();
                                    continue;
                                case 56:
                                    this.connected_ = codedInputStream.readBool();
                                    continue;
                                case 64:
                                    this.focus_ = codedInputStream.readEnum();
                                    continue;
                                case 72:
                                    this.volume_ = codedInputStream.readUInt32();
                                    continue;
                                case 80:
                                    this.aapConnected_ = codedInputStream.readBool();
                                    continue;
                                case 88:
                                    this.lastKnownAapCapability_ = codedInputStream.readEnum();
                                    continue;
                                default:
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                        break;
                                    } else {
                                        continue;
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
                    this.connectedAudioTypes_.makeImmutable();
                    return null;
                case 6:
                    return new User();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (User.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public boolean getAapConnected() {
            return this.aapConnected_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public String getAddress() {
            return this.address_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public ByteString getAddressBytes() {
            return ByteString.copyFromUtf8(this.address_);
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public boolean getConnected() {
            return this.connected_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public AudioConnectionType getConnectedAudioTypes(int i) {
            return connectedAudioTypes_converter_.convert(Integer.valueOf(this.connectedAudioTypes_.getInt(i)));
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public int getConnectedAudioTypesCount() {
            return this.connectedAudioTypes_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public List<AudioConnectionType> getConnectedAudioTypesList() {
            return new Internal.ListAdapter(this.connectedAudioTypes_, connectedAudioTypes_converter_);
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public int getConnectedAudioTypesValue(int i) {
            return this.connectedAudioTypes_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public List<Integer> getConnectedAudioTypesValueList() {
            return this.connectedAudioTypes_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public Focus getFocus() {
            Focus forNumber = Focus.forNumber(this.focus_);
            return forNumber == null ? Focus.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public int getFocusValue() {
            return this.focus_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public int getId() {
            return this.id_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public AapCapability getLastKnownAapCapability() {
            AapCapability forNumber = AapCapability.forNumber(this.lastKnownAapCapability_);
            return forNumber == null ? AapCapability.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public int getLastKnownAapCapabilityValue() {
            return this.lastKnownAapCapability_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public AudioConnectionType getPrimaryAudioConnectionType() {
            AudioConnectionType forNumber = AudioConnectionType.forNumber(this.primaryAudioConnectionType_);
            return forNumber == null ? AudioConnectionType.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public int getPrimaryAudioConnectionTypeValue() {
            return this.primaryAudioConnectionType_;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public boolean getSelf() {
            return this.self_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.id_;
            int computeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) + 0 : 0;
            if (!this.name_.isEmpty()) {
                computeUInt32Size += CodedOutputStream.computeStringSize(2, getName());
            }
            if (!this.address_.isEmpty()) {
                computeUInt32Size += CodedOutputStream.computeStringSize(3, getAddress());
            }
            boolean z = this.self_;
            if (z) {
                computeUInt32Size += CodedOutputStream.computeBoolSize(4, z);
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.connectedAudioTypes_.size(); i4++) {
                i3 += CodedOutputStream.computeEnumSizeNoTag(this.connectedAudioTypes_.getInt(i4));
            }
            int i5 = computeUInt32Size + i3;
            if (!getConnectedAudioTypesList().isEmpty()) {
                i5 = i5 + 1 + CodedOutputStream.computeUInt32SizeNoTag(i3);
            }
            this.connectedAudioTypesMemoizedSerializedSize = i3;
            if (this.primaryAudioConnectionType_ != AudioConnectionType.UNKNOWN.getNumber()) {
                i5 += CodedOutputStream.computeEnumSize(6, this.primaryAudioConnectionType_);
            }
            boolean z2 = this.connected_;
            if (z2) {
                i5 += CodedOutputStream.computeBoolSize(7, z2);
            }
            if (this.focus_ != Focus.UNKNOWN_FOCUS.getNumber()) {
                i5 += CodedOutputStream.computeEnumSize(8, this.focus_);
            }
            int i6 = this.volume_;
            if (i6 != 0) {
                i5 += CodedOutputStream.computeUInt32Size(9, i6);
            }
            boolean z3 = this.aapConnected_;
            if (z3) {
                i5 += CodedOutputStream.computeBoolSize(10, z3);
            }
            if (this.lastKnownAapCapability_ != AapCapability.AAP_SUPPORT_UNKNOWN.getNumber()) {
                i5 += CodedOutputStream.computeEnumSize(11, this.lastKnownAapCapability_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i5;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UserOrBuilder
        public int getVolume() {
            return this.volume_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            int i = this.id_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(2, getName());
            }
            if (!this.address_.isEmpty()) {
                codedOutputStream.writeString(3, getAddress());
            }
            boolean z = this.self_;
            if (z) {
                codedOutputStream.writeBool(4, z);
            }
            if (getConnectedAudioTypesList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(42);
                codedOutputStream.writeUInt32NoTag(this.connectedAudioTypesMemoizedSerializedSize);
            }
            for (int i2 = 0; i2 < this.connectedAudioTypes_.size(); i2++) {
                codedOutputStream.writeEnumNoTag(this.connectedAudioTypes_.getInt(i2));
            }
            if (this.primaryAudioConnectionType_ != AudioConnectionType.UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(6, this.primaryAudioConnectionType_);
            }
            boolean z2 = this.connected_;
            if (z2) {
                codedOutputStream.writeBool(7, z2);
            }
            if (this.focus_ != Focus.UNKNOWN_FOCUS.getNumber()) {
                codedOutputStream.writeEnum(8, this.focus_);
            }
            int i3 = this.volume_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(9, i3);
            }
            boolean z3 = this.aapConnected_;
            if (z3) {
                codedOutputStream.writeBool(10, z3);
            }
            if (this.lastKnownAapCapability_ != AapCapability.AAP_SUPPORT_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(11, this.lastKnownAapCapability_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(User user) {
            return DEFAULT_INSTANCE.createBuilder(user);
        }

        public static User parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (User) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static User parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static User parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static User parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static User parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static User parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static User parseFrom(InputStream inputStream) throws IOException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static User parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static User parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static User parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (User) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UserOrBuilder extends MessageLiteOrBuilder {
        boolean getAapConnected();

        String getAddress();

        ByteString getAddressBytes();

        boolean getConnected();

        User.AudioConnectionType getConnectedAudioTypes(int i);

        int getConnectedAudioTypesCount();

        List<User.AudioConnectionType> getConnectedAudioTypesList();

        int getConnectedAudioTypesValue(int i);

        List<Integer> getConnectedAudioTypesValueList();

        User.Focus getFocus();

        int getFocusValue();

        int getId();

        User.AapCapability getLastKnownAapCapability();

        int getLastKnownAapCapabilityValue();

        String getName();

        ByteString getNameBytes();

        User.AudioConnectionType getPrimaryAudioConnectionType();

        int getPrimaryAudioConnectionTypeValue();

        boolean getSelf();

        int getVolume();
    }

    /* loaded from: classes6.dex */
    public static final class Users extends GeneratedMessageLite<Users, Builder> implements UsersOrBuilder {
        private static final Users DEFAULT_INSTANCE = new Users();
        private static volatile Parser<Users> PARSER = null;
        public static final int USERS_FIELD_NUMBER = 1;
        private Internal.ProtobufList<User> users_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Users, Builder> implements UsersOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllUsers(Iterable<? extends User> iterable) {
                copyOnWrite();
                ((Users) this.instance).addAllUsers(iterable);
                return this;
            }

            public Builder addUsers(User user) {
                copyOnWrite();
                ((Users) this.instance).addUsers(user);
                return this;
            }

            public Builder clearUsers() {
                copyOnWrite();
                ((Users) this.instance).clearUsers();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UsersOrBuilder
            public User getUsers(int i) {
                return ((Users) this.instance).getUsers(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UsersOrBuilder
            public int getUsersCount() {
                return ((Users) this.instance).getUsersCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.UsersOrBuilder
            public List<User> getUsersList() {
                return Collections.unmodifiableList(((Users) this.instance).getUsersList());
            }

            public Builder removeUsers(int i) {
                copyOnWrite();
                ((Users) this.instance).removeUsers(i);
                return this;
            }

            public Builder setUsers(int i, User user) {
                copyOnWrite();
                ((Users) this.instance).setUsers(i, user);
                return this;
            }

            private Builder() {
                super(Users.DEFAULT_INSTANCE);
            }

            public Builder addUsers(int i, User user) {
                copyOnWrite();
                ((Users) this.instance).addUsers(i, user);
                return this;
            }

            public Builder setUsers(int i, User.Builder builder) {
                copyOnWrite();
                ((Users) this.instance).setUsers(i, builder);
                return this;
            }

            public Builder addUsers(User.Builder builder) {
                copyOnWrite();
                ((Users) this.instance).addUsers(builder);
                return this;
            }

            public Builder addUsers(int i, User.Builder builder) {
                copyOnWrite();
                ((Users) this.instance).addUsers(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Users() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllUsers(Iterable<? extends User> iterable) {
            ensureUsersIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.users_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUsers(User user) {
            if (user != null) {
                ensureUsersIsMutable();
                this.users_.add(user);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUsers() {
            this.users_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureUsersIsMutable() {
            if (!this.users_.isModifiable()) {
                this.users_ = GeneratedMessageLite.mutableCopy(this.users_);
            }
        }

        public static Users getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Users parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Users) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Users parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Users> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeUsers(int i) {
            ensureUsersIsMutable();
            this.users_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsers(int i, User user) {
            if (user != null) {
                ensureUsersIsMutable();
                this.users_.set(i, user);
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
                    this.users_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.users_, ((Users) obj2).users_);
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
                                    if (!this.users_.isModifiable()) {
                                        this.users_ = GeneratedMessageLite.mutableCopy(this.users_);
                                    }
                                    this.users_.add(codedInputStream.readMessage(User.parser(), extensionRegistryLite));
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
                    this.users_.makeImmutable();
                    return null;
                case 6:
                    return new Users();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (Users.class) {
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
            for (int i3 = 0; i3 < this.users_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.users_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UsersOrBuilder
        public User getUsers(int i) {
            return this.users_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UsersOrBuilder
        public int getUsersCount() {
            return this.users_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.System.UsersOrBuilder
        public List<User> getUsersList() {
            return this.users_;
        }

        public UserOrBuilder getUsersOrBuilder(int i) {
            return this.users_.get(i);
        }

        public List<? extends UserOrBuilder> getUsersOrBuilderList() {
            return this.users_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.users_.size(); i++) {
                codedOutputStream.writeMessage(1, this.users_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(Users users) {
            return DEFAULT_INSTANCE.createBuilder(users);
        }

        public static Users parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Users) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Users parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Users parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUsers(int i, User user) {
            if (user != null) {
                ensureUsersIsMutable();
                this.users_.add(i, user);
                return;
            }
            throw new NullPointerException();
        }

        public static Users parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsers(int i, User.Builder builder) {
            ensureUsersIsMutable();
            this.users_.set(i, builder.mo10084build());
        }

        public static Users parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Users parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUsers(User.Builder builder) {
            ensureUsersIsMutable();
            this.users_.add(builder.mo10084build());
        }

        public static Users parseFrom(InputStream inputStream) throws IOException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Users parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUsers(int i, User.Builder builder) {
            ensureUsersIsMutable();
            this.users_.add(i, builder.mo10084build());
        }

        public static Users parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Users parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Users) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UsersOrBuilder extends MessageLiteOrBuilder {
        User getUsers(int i);

        int getUsersCount();

        List<User> getUsersList();
    }

    /* loaded from: classes6.dex */
    public static final class Wakewords extends GeneratedMessageLite<Wakewords, Builder> implements WakewordsOrBuilder {
        public static final int CURRENT_WAKEWORDS_FIELD_NUMBER = 1;
        private static final Wakewords DEFAULT_INSTANCE = new Wakewords();
        private static volatile Parser<Wakewords> PARSER;
        private Internal.ProtobufList<String> currentWakewords_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Wakewords, Builder> implements WakewordsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllCurrentWakewords(Iterable<String> iterable) {
                copyOnWrite();
                ((Wakewords) this.instance).addAllCurrentWakewords(iterable);
                return this;
            }

            public Builder addCurrentWakewords(String str) {
                copyOnWrite();
                ((Wakewords) this.instance).addCurrentWakewords(str);
                return this;
            }

            public Builder addCurrentWakewordsBytes(ByteString byteString) {
                copyOnWrite();
                ((Wakewords) this.instance).addCurrentWakewordsBytes(byteString);
                return this;
            }

            public Builder clearCurrentWakewords() {
                copyOnWrite();
                ((Wakewords) this.instance).clearCurrentWakewords();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.System.WakewordsOrBuilder
            public String getCurrentWakewords(int i) {
                return ((Wakewords) this.instance).getCurrentWakewords(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.System.WakewordsOrBuilder
            public ByteString getCurrentWakewordsBytes(int i) {
                return ((Wakewords) this.instance).getCurrentWakewordsBytes(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.System.WakewordsOrBuilder
            public int getCurrentWakewordsCount() {
                return ((Wakewords) this.instance).getCurrentWakewordsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.System.WakewordsOrBuilder
            public List<String> getCurrentWakewordsList() {
                return Collections.unmodifiableList(((Wakewords) this.instance).getCurrentWakewordsList());
            }

            public Builder setCurrentWakewords(int i, String str) {
                copyOnWrite();
                ((Wakewords) this.instance).setCurrentWakewords(i, str);
                return this;
            }

            private Builder() {
                super(Wakewords.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Wakewords() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllCurrentWakewords(Iterable<String> iterable) {
            ensureCurrentWakewordsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.currentWakewords_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCurrentWakewords(String str) {
            if (str != null) {
                ensureCurrentWakewordsIsMutable();
                this.currentWakewords_.add(str);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCurrentWakewordsBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                ensureCurrentWakewordsIsMutable();
                this.currentWakewords_.add(byteString.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCurrentWakewords() {
            this.currentWakewords_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureCurrentWakewordsIsMutable() {
            if (!this.currentWakewords_.isModifiable()) {
                this.currentWakewords_ = GeneratedMessageLite.mutableCopy(this.currentWakewords_);
            }
        }

        public static Wakewords getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Wakewords parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Wakewords) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Wakewords parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Wakewords> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurrentWakewords(int i, String str) {
            if (str != null) {
                ensureCurrentWakewordsIsMutable();
                this.currentWakewords_.set(i, str);
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
                    this.currentWakewords_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.currentWakewords_, ((Wakewords) obj2).currentWakewords_);
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
                                    if (!this.currentWakewords_.isModifiable()) {
                                        this.currentWakewords_ = GeneratedMessageLite.mutableCopy(this.currentWakewords_);
                                    }
                                    this.currentWakewords_.add(readStringRequireUtf8);
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
                    this.currentWakewords_.makeImmutable();
                    return null;
                case 6:
                    return new Wakewords();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (Wakewords.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.System.WakewordsOrBuilder
        public String getCurrentWakewords(int i) {
            return this.currentWakewords_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.System.WakewordsOrBuilder
        public ByteString getCurrentWakewordsBytes(int i) {
            return ByteString.copyFromUtf8(this.currentWakewords_.get(i));
        }

        @Override // com.amazon.alexa.accessory.protocol.System.WakewordsOrBuilder
        public int getCurrentWakewordsCount() {
            return this.currentWakewords_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.System.WakewordsOrBuilder
        public List<String> getCurrentWakewordsList() {
            return this.currentWakewords_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.currentWakewords_.size(); i3++) {
                i2 += CodedOutputStream.computeStringSizeNoTag(this.currentWakewords_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + (getCurrentWakewordsList().size() * 1) + 0 + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.currentWakewords_.size(); i++) {
                codedOutputStream.writeString(1, this.currentWakewords_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(Wakewords wakewords) {
            return DEFAULT_INSTANCE.createBuilder(wakewords);
        }

        public static Wakewords parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Wakewords) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Wakewords parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Wakewords parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Wakewords parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Wakewords parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Wakewords parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Wakewords parseFrom(InputStream inputStream) throws IOException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Wakewords parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Wakewords parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Wakewords parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Wakewords) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface WakewordsOrBuilder extends MessageLiteOrBuilder {
        String getCurrentWakewords(int i);

        ByteString getCurrentWakewordsBytes(int i);

        int getCurrentWakewordsCount();

        List<String> getCurrentWakewordsList();
    }

    private System() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
