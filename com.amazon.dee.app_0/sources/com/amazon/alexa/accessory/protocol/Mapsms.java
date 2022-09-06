package com.amazon.alexa.accessory.protocol;

import com.amazon.alexa.accessory.protocol.Common;
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
public final class Mapsms {

    /* renamed from: com.amazon.alexa.accessory.protocol.Mapsms$1  reason: invalid class name */
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
    public static final class ContactInformation extends GeneratedMessageLite<ContactInformation, Builder> implements ContactInformationOrBuilder {
        private static final ContactInformation DEFAULT_INSTANCE = new ContactInformation();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<ContactInformation> PARSER = null;
        public static final int PHONE_NUMBER_FIELD_NUMBER = 2;
        private String name_ = "";
        private String phoneNumber_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ContactInformation, Builder> implements ContactInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearName() {
                copyOnWrite();
                ((ContactInformation) this.instance).clearName();
                return this;
            }

            public Builder clearPhoneNumber() {
                copyOnWrite();
                ((ContactInformation) this.instance).clearPhoneNumber();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.ContactInformationOrBuilder
            public String getName() {
                return ((ContactInformation) this.instance).getName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.ContactInformationOrBuilder
            public ByteString getNameBytes() {
                return ((ContactInformation) this.instance).getNameBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.ContactInformationOrBuilder
            public String getPhoneNumber() {
                return ((ContactInformation) this.instance).getPhoneNumber();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.ContactInformationOrBuilder
            public ByteString getPhoneNumberBytes() {
                return ((ContactInformation) this.instance).getPhoneNumberBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((ContactInformation) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((ContactInformation) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setPhoneNumber(String str) {
                copyOnWrite();
                ((ContactInformation) this.instance).setPhoneNumber(str);
                return this;
            }

            public Builder setPhoneNumberBytes(ByteString byteString) {
                copyOnWrite();
                ((ContactInformation) this.instance).setPhoneNumberBytes(byteString);
                return this;
            }

            private Builder() {
                super(ContactInformation.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ContactInformation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPhoneNumber() {
            this.phoneNumber_ = getDefaultInstance().getPhoneNumber();
        }

        public static ContactInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ContactInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ContactInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ContactInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ContactInformation> parser() {
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
        public void setPhoneNumber(String str) {
            if (str != null) {
                this.phoneNumber_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPhoneNumberBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.phoneNumber_ = byteString.toStringUtf8();
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
                    ContactInformation contactInformation = (ContactInformation) obj2;
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !contactInformation.name_.isEmpty(), contactInformation.name_);
                    this.phoneNumber_ = visitor.visitString(!this.phoneNumber_.isEmpty(), this.phoneNumber_, true ^ contactInformation.phoneNumber_.isEmpty(), contactInformation.phoneNumber_);
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
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.phoneNumber_ = codedInputStream.readStringRequireUtf8();
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
                    return new ContactInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ContactInformation.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.ContactInformationOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.ContactInformationOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.ContactInformationOrBuilder
        public String getPhoneNumber() {
            return this.phoneNumber_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.ContactInformationOrBuilder
        public ByteString getPhoneNumberBytes() {
            return ByteString.copyFromUtf8(this.phoneNumber_);
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
            if (!this.phoneNumber_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getPhoneNumber());
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
            if (!this.phoneNumber_.isEmpty()) {
                codedOutputStream.writeString(2, getPhoneNumber());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ContactInformation contactInformation) {
            return DEFAULT_INSTANCE.createBuilder(contactInformation);
        }

        public static ContactInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ContactInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ContactInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ContactInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ContactInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ContactInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ContactInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ContactInformation parseFrom(InputStream inputStream) throws IOException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ContactInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ContactInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ContactInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ContactInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ContactInformationOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        String getPhoneNumber();

        ByteString getPhoneNumberBytes();
    }

    /* loaded from: classes6.dex */
    public static final class EndOfSmsMessageListResponse extends GeneratedMessageLite<EndOfSmsMessageListResponse, Builder> implements EndOfSmsMessageListResponseOrBuilder {
        private static final EndOfSmsMessageListResponse DEFAULT_INSTANCE = new EndOfSmsMessageListResponse();
        public static final int ERROR_CODE_FIELD_NUMBER = 1;
        private static volatile Parser<EndOfSmsMessageListResponse> PARSER;
        private int errorCode_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<EndOfSmsMessageListResponse, Builder> implements EndOfSmsMessageListResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearErrorCode() {
                copyOnWrite();
                ((EndOfSmsMessageListResponse) this.instance).clearErrorCode();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.EndOfSmsMessageListResponseOrBuilder
            public Common.ErrorCode getErrorCode() {
                return ((EndOfSmsMessageListResponse) this.instance).getErrorCode();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.EndOfSmsMessageListResponseOrBuilder
            public int getErrorCodeValue() {
                return ((EndOfSmsMessageListResponse) this.instance).getErrorCodeValue();
            }

            public Builder setErrorCode(Common.ErrorCode errorCode) {
                copyOnWrite();
                ((EndOfSmsMessageListResponse) this.instance).setErrorCode(errorCode);
                return this;
            }

            public Builder setErrorCodeValue(int i) {
                copyOnWrite();
                ((EndOfSmsMessageListResponse) this.instance).setErrorCodeValue(i);
                return this;
            }

            private Builder() {
                super(EndOfSmsMessageListResponse.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private EndOfSmsMessageListResponse() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorCode() {
            this.errorCode_ = 0;
        }

        public static EndOfSmsMessageListResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static EndOfSmsMessageListResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static EndOfSmsMessageListResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<EndOfSmsMessageListResponse> parser() {
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
                    EndOfSmsMessageListResponse endOfSmsMessageListResponse = (EndOfSmsMessageListResponse) obj2;
                    boolean z2 = this.errorCode_ != 0;
                    int i = this.errorCode_;
                    if (endOfSmsMessageListResponse.errorCode_ != 0) {
                        z = true;
                    }
                    this.errorCode_ = visitor.visitInt(z2, i, z, endOfSmsMessageListResponse.errorCode_);
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
                    return new EndOfSmsMessageListResponse();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (EndOfSmsMessageListResponse.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.EndOfSmsMessageListResponseOrBuilder
        public Common.ErrorCode getErrorCode() {
            Common.ErrorCode forNumber = Common.ErrorCode.forNumber(this.errorCode_);
            return forNumber == null ? Common.ErrorCode.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.EndOfSmsMessageListResponseOrBuilder
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

        public static Builder newBuilder(EndOfSmsMessageListResponse endOfSmsMessageListResponse) {
            return DEFAULT_INSTANCE.createBuilder(endOfSmsMessageListResponse);
        }

        public static EndOfSmsMessageListResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static EndOfSmsMessageListResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static EndOfSmsMessageListResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static EndOfSmsMessageListResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static EndOfSmsMessageListResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static EndOfSmsMessageListResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static EndOfSmsMessageListResponse parseFrom(InputStream inputStream) throws IOException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static EndOfSmsMessageListResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static EndOfSmsMessageListResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static EndOfSmsMessageListResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EndOfSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface EndOfSmsMessageListResponseOrBuilder extends MessageLiteOrBuilder {
        Common.ErrorCode getErrorCode();

        int getErrorCodeValue();
    }

    /* loaded from: classes6.dex */
    public static final class GetSmsMessageList extends GeneratedMessageLite<GetSmsMessageList, Builder> implements GetSmsMessageListOrBuilder {
        private static final GetSmsMessageList DEFAULT_INSTANCE = new GetSmsMessageList();
        public static final int MAX_COUNT_FIELD_NUMBER = 1;
        public static final int OFFSET_FIELD_NUMBER = 2;
        private static volatile Parser<GetSmsMessageList> PARSER = null;
        public static final int READ_FILTER_FIELD_NUMBER = 3;
        private int maxCount_;
        private int offset_;
        private int readFilter_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetSmsMessageList, Builder> implements GetSmsMessageListOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearMaxCount() {
                copyOnWrite();
                ((GetSmsMessageList) this.instance).clearMaxCount();
                return this;
            }

            public Builder clearOffset() {
                copyOnWrite();
                ((GetSmsMessageList) this.instance).clearOffset();
                return this;
            }

            public Builder clearReadFilter() {
                copyOnWrite();
                ((GetSmsMessageList) this.instance).clearReadFilter();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListOrBuilder
            public int getMaxCount() {
                return ((GetSmsMessageList) this.instance).getMaxCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListOrBuilder
            public int getOffset() {
                return ((GetSmsMessageList) this.instance).getOffset();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListOrBuilder
            public ReadStatusFilter getReadFilter() {
                return ((GetSmsMessageList) this.instance).getReadFilter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListOrBuilder
            public int getReadFilterValue() {
                return ((GetSmsMessageList) this.instance).getReadFilterValue();
            }

            public Builder setMaxCount(int i) {
                copyOnWrite();
                ((GetSmsMessageList) this.instance).setMaxCount(i);
                return this;
            }

            public Builder setOffset(int i) {
                copyOnWrite();
                ((GetSmsMessageList) this.instance).setOffset(i);
                return this;
            }

            public Builder setReadFilter(ReadStatusFilter readStatusFilter) {
                copyOnWrite();
                ((GetSmsMessageList) this.instance).setReadFilter(readStatusFilter);
                return this;
            }

            public Builder setReadFilterValue(int i) {
                copyOnWrite();
                ((GetSmsMessageList) this.instance).setReadFilterValue(i);
                return this;
            }

            private Builder() {
                super(GetSmsMessageList.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetSmsMessageList() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMaxCount() {
            this.maxCount_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOffset() {
            this.offset_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearReadFilter() {
            this.readFilter_ = 0;
        }

        public static GetSmsMessageList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetSmsMessageList parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetSmsMessageList) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetSmsMessageList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetSmsMessageList> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMaxCount(int i) {
            this.maxCount_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOffset(int i) {
            this.offset_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReadFilter(ReadStatusFilter readStatusFilter) {
            if (readStatusFilter != null) {
                this.readFilter_ = readStatusFilter.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReadFilterValue(int i) {
            this.readFilter_ = i;
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
                    GetSmsMessageList getSmsMessageList = (GetSmsMessageList) obj2;
                    this.maxCount_ = visitor.visitInt(this.maxCount_ != 0, this.maxCount_, getSmsMessageList.maxCount_ != 0, getSmsMessageList.maxCount_);
                    this.offset_ = visitor.visitInt(this.offset_ != 0, this.offset_, getSmsMessageList.offset_ != 0, getSmsMessageList.offset_);
                    boolean z2 = this.readFilter_ != 0;
                    int i = this.readFilter_;
                    if (getSmsMessageList.readFilter_ != 0) {
                        z = true;
                    }
                    this.readFilter_ = visitor.visitInt(z2, i, z, getSmsMessageList.readFilter_);
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
                                    this.maxCount_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.offset_ = codedInputStream.readUInt32();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.readFilter_ = codedInputStream.readEnum();
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
                    return new GetSmsMessageList();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetSmsMessageList.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListOrBuilder
        public int getMaxCount() {
            return this.maxCount_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListOrBuilder
        public int getOffset() {
            return this.offset_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListOrBuilder
        public ReadStatusFilter getReadFilter() {
            ReadStatusFilter forNumber = ReadStatusFilter.forNumber(this.readFilter_);
            return forNumber == null ? ReadStatusFilter.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListOrBuilder
        public int getReadFilterValue() {
            return this.readFilter_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.maxCount_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int i4 = this.offset_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
            }
            if (this.readFilter_ != ReadStatusFilter.UNREAD_STATUS_FILTER.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.readFilter_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.maxCount_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.offset_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            if (this.readFilter_ != ReadStatusFilter.UNREAD_STATUS_FILTER.getNumber()) {
                codedOutputStream.writeEnum(3, this.readFilter_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetSmsMessageList getSmsMessageList) {
            return DEFAULT_INSTANCE.createBuilder(getSmsMessageList);
        }

        public static GetSmsMessageList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetSmsMessageList) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetSmsMessageList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetSmsMessageList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetSmsMessageList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetSmsMessageList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetSmsMessageList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetSmsMessageList parseFrom(InputStream inputStream) throws IOException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetSmsMessageList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetSmsMessageList parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetSmsMessageList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetSmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetSmsMessageListOrBuilder extends MessageLiteOrBuilder {
        int getMaxCount();

        int getOffset();

        ReadStatusFilter getReadFilter();

        int getReadFilterValue();
    }

    /* loaded from: classes6.dex */
    public static final class GetSmsMessageListResponse extends GeneratedMessageLite<GetSmsMessageListResponse, Builder> implements GetSmsMessageListResponseOrBuilder {
        private static final GetSmsMessageListResponse DEFAULT_INSTANCE = new GetSmsMessageListResponse();
        private static volatile Parser<GetSmsMessageListResponse> PARSER = null;
        public static final int SMSMESSAGELIST_FIELD_NUMBER = 1;
        private SmsMessageList smsMessageList_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetSmsMessageListResponse, Builder> implements GetSmsMessageListResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearSmsMessageList() {
                copyOnWrite();
                ((GetSmsMessageListResponse) this.instance).clearSmsMessageList();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListResponseOrBuilder
            public SmsMessageList getSmsMessageList() {
                return ((GetSmsMessageListResponse) this.instance).getSmsMessageList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListResponseOrBuilder
            public boolean hasSmsMessageList() {
                return ((GetSmsMessageListResponse) this.instance).hasSmsMessageList();
            }

            public Builder mergeSmsMessageList(SmsMessageList smsMessageList) {
                copyOnWrite();
                ((GetSmsMessageListResponse) this.instance).mergeSmsMessageList(smsMessageList);
                return this;
            }

            public Builder setSmsMessageList(SmsMessageList smsMessageList) {
                copyOnWrite();
                ((GetSmsMessageListResponse) this.instance).setSmsMessageList(smsMessageList);
                return this;
            }

            private Builder() {
                super(GetSmsMessageListResponse.DEFAULT_INSTANCE);
            }

            public Builder setSmsMessageList(SmsMessageList.Builder builder) {
                copyOnWrite();
                ((GetSmsMessageListResponse) this.instance).setSmsMessageList(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetSmsMessageListResponse() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSmsMessageList() {
            this.smsMessageList_ = null;
        }

        public static GetSmsMessageListResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSmsMessageList(SmsMessageList smsMessageList) {
            SmsMessageList smsMessageList2 = this.smsMessageList_;
            if (smsMessageList2 != null && smsMessageList2 != SmsMessageList.getDefaultInstance()) {
                this.smsMessageList_ = SmsMessageList.newBuilder(this.smsMessageList_).mergeFrom((SmsMessageList.Builder) smsMessageList).mo10085buildPartial();
            } else {
                this.smsMessageList_ = smsMessageList;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetSmsMessageListResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetSmsMessageListResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetSmsMessageListResponse> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSmsMessageList(SmsMessageList smsMessageList) {
            if (smsMessageList != null) {
                this.smsMessageList_ = smsMessageList;
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
                    this.smsMessageList_ = (SmsMessageList) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.smsMessageList_, ((GetSmsMessageListResponse) obj2).smsMessageList_);
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
                                    SmsMessageList.Builder mo10081toBuilder = this.smsMessageList_ != null ? this.smsMessageList_.mo10081toBuilder() : null;
                                    this.smsMessageList_ = (SmsMessageList) codedInputStream.readMessage(SmsMessageList.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((SmsMessageList.Builder) this.smsMessageList_);
                                        this.smsMessageList_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new GetSmsMessageListResponse();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetSmsMessageListResponse.class) {
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
            if (this.smsMessageList_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getSmsMessageList());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListResponseOrBuilder
        public SmsMessageList getSmsMessageList() {
            SmsMessageList smsMessageList = this.smsMessageList_;
            return smsMessageList == null ? SmsMessageList.getDefaultInstance() : smsMessageList;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.GetSmsMessageListResponseOrBuilder
        public boolean hasSmsMessageList() {
            return this.smsMessageList_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.smsMessageList_ != null) {
                codedOutputStream.writeMessage(1, getSmsMessageList());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetSmsMessageListResponse getSmsMessageListResponse) {
            return DEFAULT_INSTANCE.createBuilder(getSmsMessageListResponse);
        }

        public static GetSmsMessageListResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetSmsMessageListResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetSmsMessageListResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSmsMessageList(SmsMessageList.Builder builder) {
            this.smsMessageList_ = builder.mo10084build();
        }

        public static GetSmsMessageListResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetSmsMessageListResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetSmsMessageListResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetSmsMessageListResponse parseFrom(InputStream inputStream) throws IOException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetSmsMessageListResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetSmsMessageListResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetSmsMessageListResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetSmsMessageListResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetSmsMessageListResponseOrBuilder extends MessageLiteOrBuilder {
        SmsMessageList getSmsMessageList();

        boolean hasSmsMessageList();
    }

    /* loaded from: classes6.dex */
    public static final class InitiateMapConnection extends GeneratedMessageLite<InitiateMapConnection, Builder> implements InitiateMapConnectionOrBuilder {
        private static final InitiateMapConnection DEFAULT_INSTANCE = new InitiateMapConnection();
        private static volatile Parser<InitiateMapConnection> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<InitiateMapConnection, Builder> implements InitiateMapConnectionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(InitiateMapConnection.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private InitiateMapConnection() {
        }

        public static InitiateMapConnection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static InitiateMapConnection parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (InitiateMapConnection) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InitiateMapConnection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<InitiateMapConnection> parser() {
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
                    InitiateMapConnection initiateMapConnection = (InitiateMapConnection) obj2;
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
                    return new InitiateMapConnection();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (InitiateMapConnection.class) {
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

        public static Builder newBuilder(InitiateMapConnection initiateMapConnection) {
            return DEFAULT_INSTANCE.createBuilder(initiateMapConnection);
        }

        public static InitiateMapConnection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InitiateMapConnection) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static InitiateMapConnection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static InitiateMapConnection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static InitiateMapConnection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static InitiateMapConnection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InitiateMapConnection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static InitiateMapConnection parseFrom(InputStream inputStream) throws IOException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InitiateMapConnection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static InitiateMapConnection parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static InitiateMapConnection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InitiateMapConnection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface InitiateMapConnectionOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class Message extends GeneratedMessageLite<Message, Builder> implements MessageOrBuilder {
        public static final int CHARSET_FIELD_NUMBER = 5;
        public static final int CONVERSATION_ID_FIELD_NUMBER = 7;
        private static final Message DEFAULT_INSTANCE = new Message();
        public static final int ISO8601_DATE_TIME_FIELD_NUMBER = 3;
        public static final int MESSAGE_CONTENT_FIELD_NUMBER = 4;
        public static final int MESSAGE_IDENTIFIER_FIELD_NUMBER = 2;
        public static final int MESSAGE_LANGUAGE_FIELD_NUMBER = 6;
        private static volatile Parser<Message> PARSER = null;
        public static final int READ_STATUS_FIELD_NUMBER = 1;
        public static final int SUBJECT_FIELD_NUMBER = 8;
        private boolean readStatus_;
        private String messageIdentifier_ = "";
        private String iso8601DateTime_ = "";
        private String messageContent_ = "";
        private String charset_ = "";
        private String messageLanguage_ = "";
        private String conversationId_ = "";
        private String subject_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Message, Builder> implements MessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCharset() {
                copyOnWrite();
                ((Message) this.instance).clearCharset();
                return this;
            }

            public Builder clearConversationId() {
                copyOnWrite();
                ((Message) this.instance).clearConversationId();
                return this;
            }

            public Builder clearIso8601DateTime() {
                copyOnWrite();
                ((Message) this.instance).clearIso8601DateTime();
                return this;
            }

            public Builder clearMessageContent() {
                copyOnWrite();
                ((Message) this.instance).clearMessageContent();
                return this;
            }

            public Builder clearMessageIdentifier() {
                copyOnWrite();
                ((Message) this.instance).clearMessageIdentifier();
                return this;
            }

            public Builder clearMessageLanguage() {
                copyOnWrite();
                ((Message) this.instance).clearMessageLanguage();
                return this;
            }

            public Builder clearReadStatus() {
                copyOnWrite();
                ((Message) this.instance).clearReadStatus();
                return this;
            }

            public Builder clearSubject() {
                copyOnWrite();
                ((Message) this.instance).clearSubject();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public String getCharset() {
                return ((Message) this.instance).getCharset();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public ByteString getCharsetBytes() {
                return ((Message) this.instance).getCharsetBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public String getConversationId() {
                return ((Message) this.instance).getConversationId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public ByteString getConversationIdBytes() {
                return ((Message) this.instance).getConversationIdBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public String getIso8601DateTime() {
                return ((Message) this.instance).getIso8601DateTime();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public ByteString getIso8601DateTimeBytes() {
                return ((Message) this.instance).getIso8601DateTimeBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public String getMessageContent() {
                return ((Message) this.instance).getMessageContent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public ByteString getMessageContentBytes() {
                return ((Message) this.instance).getMessageContentBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public String getMessageIdentifier() {
                return ((Message) this.instance).getMessageIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public ByteString getMessageIdentifierBytes() {
                return ((Message) this.instance).getMessageIdentifierBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public String getMessageLanguage() {
                return ((Message) this.instance).getMessageLanguage();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public ByteString getMessageLanguageBytes() {
                return ((Message) this.instance).getMessageLanguageBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public boolean getReadStatus() {
                return ((Message) this.instance).getReadStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public String getSubject() {
                return ((Message) this.instance).getSubject();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
            public ByteString getSubjectBytes() {
                return ((Message) this.instance).getSubjectBytes();
            }

            public Builder setCharset(String str) {
                copyOnWrite();
                ((Message) this.instance).setCharset(str);
                return this;
            }

            public Builder setCharsetBytes(ByteString byteString) {
                copyOnWrite();
                ((Message) this.instance).setCharsetBytes(byteString);
                return this;
            }

            public Builder setConversationId(String str) {
                copyOnWrite();
                ((Message) this.instance).setConversationId(str);
                return this;
            }

            public Builder setConversationIdBytes(ByteString byteString) {
                copyOnWrite();
                ((Message) this.instance).setConversationIdBytes(byteString);
                return this;
            }

            public Builder setIso8601DateTime(String str) {
                copyOnWrite();
                ((Message) this.instance).setIso8601DateTime(str);
                return this;
            }

            public Builder setIso8601DateTimeBytes(ByteString byteString) {
                copyOnWrite();
                ((Message) this.instance).setIso8601DateTimeBytes(byteString);
                return this;
            }

            public Builder setMessageContent(String str) {
                copyOnWrite();
                ((Message) this.instance).setMessageContent(str);
                return this;
            }

            public Builder setMessageContentBytes(ByteString byteString) {
                copyOnWrite();
                ((Message) this.instance).setMessageContentBytes(byteString);
                return this;
            }

            public Builder setMessageIdentifier(String str) {
                copyOnWrite();
                ((Message) this.instance).setMessageIdentifier(str);
                return this;
            }

            public Builder setMessageIdentifierBytes(ByteString byteString) {
                copyOnWrite();
                ((Message) this.instance).setMessageIdentifierBytes(byteString);
                return this;
            }

            public Builder setMessageLanguage(String str) {
                copyOnWrite();
                ((Message) this.instance).setMessageLanguage(str);
                return this;
            }

            public Builder setMessageLanguageBytes(ByteString byteString) {
                copyOnWrite();
                ((Message) this.instance).setMessageLanguageBytes(byteString);
                return this;
            }

            public Builder setReadStatus(boolean z) {
                copyOnWrite();
                ((Message) this.instance).setReadStatus(z);
                return this;
            }

            public Builder setSubject(String str) {
                copyOnWrite();
                ((Message) this.instance).setSubject(str);
                return this;
            }

            public Builder setSubjectBytes(ByteString byteString) {
                copyOnWrite();
                ((Message) this.instance).setSubjectBytes(byteString);
                return this;
            }

            private Builder() {
                super(Message.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Message() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCharset() {
            this.charset_ = getDefaultInstance().getCharset();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConversationId() {
            this.conversationId_ = getDefaultInstance().getConversationId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIso8601DateTime() {
            this.iso8601DateTime_ = getDefaultInstance().getIso8601DateTime();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessageContent() {
            this.messageContent_ = getDefaultInstance().getMessageContent();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessageIdentifier() {
            this.messageIdentifier_ = getDefaultInstance().getMessageIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessageLanguage() {
            this.messageLanguage_ = getDefaultInstance().getMessageLanguage();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearReadStatus() {
            this.readStatus_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSubject() {
            this.subject_ = getDefaultInstance().getSubject();
        }

        public static Message getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Message parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Message) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Message parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Message> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCharset(String str) {
            if (str != null) {
                this.charset_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCharsetBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.charset_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConversationId(String str) {
            if (str != null) {
                this.conversationId_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConversationIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.conversationId_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIso8601DateTime(String str) {
            if (str != null) {
                this.iso8601DateTime_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIso8601DateTimeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.iso8601DateTime_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageContent(String str) {
            if (str != null) {
                this.messageContent_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageContentBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.messageContent_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageIdentifier(String str) {
            if (str != null) {
                this.messageIdentifier_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageIdentifierBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.messageIdentifier_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageLanguage(String str) {
            if (str != null) {
                this.messageLanguage_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageLanguageBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.messageLanguage_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReadStatus(boolean z) {
            this.readStatus_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubject(String str) {
            if (str != null) {
                this.subject_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubjectBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.subject_ = byteString.toStringUtf8();
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
                    Message message = (Message) obj2;
                    boolean z = this.readStatus_;
                    boolean z2 = message.readStatus_;
                    this.readStatus_ = visitor.visitBoolean(z, z, z2, z2);
                    this.messageIdentifier_ = visitor.visitString(!this.messageIdentifier_.isEmpty(), this.messageIdentifier_, !message.messageIdentifier_.isEmpty(), message.messageIdentifier_);
                    this.iso8601DateTime_ = visitor.visitString(!this.iso8601DateTime_.isEmpty(), this.iso8601DateTime_, !message.iso8601DateTime_.isEmpty(), message.iso8601DateTime_);
                    this.messageContent_ = visitor.visitString(!this.messageContent_.isEmpty(), this.messageContent_, !message.messageContent_.isEmpty(), message.messageContent_);
                    this.charset_ = visitor.visitString(!this.charset_.isEmpty(), this.charset_, !message.charset_.isEmpty(), message.charset_);
                    this.messageLanguage_ = visitor.visitString(!this.messageLanguage_.isEmpty(), this.messageLanguage_, !message.messageLanguage_.isEmpty(), message.messageLanguage_);
                    this.conversationId_ = visitor.visitString(!this.conversationId_.isEmpty(), this.conversationId_, !message.conversationId_.isEmpty(), message.conversationId_);
                    this.subject_ = visitor.visitString(!this.subject_.isEmpty(), this.subject_, true ^ message.subject_.isEmpty(), message.subject_);
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
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.readStatus_ = codedInputStream.readBool();
                                    } else if (readTag == 18) {
                                        this.messageIdentifier_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 26) {
                                        this.iso8601DateTime_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 34) {
                                        this.messageContent_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 42) {
                                        this.charset_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 50) {
                                        this.messageLanguage_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 58) {
                                        this.conversationId_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag != 66) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.subject_ = codedInputStream.readStringRequireUtf8();
                                    }
                                }
                                z3 = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            }
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new Message();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (Message.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public String getCharset() {
            return this.charset_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public ByteString getCharsetBytes() {
            return ByteString.copyFromUtf8(this.charset_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public String getConversationId() {
            return this.conversationId_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public ByteString getConversationIdBytes() {
            return ByteString.copyFromUtf8(this.conversationId_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public String getIso8601DateTime() {
            return this.iso8601DateTime_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public ByteString getIso8601DateTimeBytes() {
            return ByteString.copyFromUtf8(this.iso8601DateTime_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public String getMessageContent() {
            return this.messageContent_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public ByteString getMessageContentBytes() {
            return ByteString.copyFromUtf8(this.messageContent_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public String getMessageIdentifier() {
            return this.messageIdentifier_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public ByteString getMessageIdentifierBytes() {
            return ByteString.copyFromUtf8(this.messageIdentifier_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public String getMessageLanguage() {
            return this.messageLanguage_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public ByteString getMessageLanguageBytes() {
            return ByteString.copyFromUtf8(this.messageLanguage_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public boolean getReadStatus() {
            return this.readStatus_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            boolean z = this.readStatus_;
            if (z) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
            }
            if (!this.messageIdentifier_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getMessageIdentifier());
            }
            if (!this.iso8601DateTime_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getIso8601DateTime());
            }
            if (!this.messageContent_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(4, getMessageContent());
            }
            if (!this.charset_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(5, getCharset());
            }
            if (!this.messageLanguage_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(6, getMessageLanguage());
            }
            if (!this.conversationId_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(7, getConversationId());
            }
            if (!this.subject_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(8, getSubject());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public String getSubject() {
            return this.subject_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.MessageOrBuilder
        public ByteString getSubjectBytes() {
            return ByteString.copyFromUtf8(this.subject_);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.readStatus_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (!this.messageIdentifier_.isEmpty()) {
                codedOutputStream.writeString(2, getMessageIdentifier());
            }
            if (!this.iso8601DateTime_.isEmpty()) {
                codedOutputStream.writeString(3, getIso8601DateTime());
            }
            if (!this.messageContent_.isEmpty()) {
                codedOutputStream.writeString(4, getMessageContent());
            }
            if (!this.charset_.isEmpty()) {
                codedOutputStream.writeString(5, getCharset());
            }
            if (!this.messageLanguage_.isEmpty()) {
                codedOutputStream.writeString(6, getMessageLanguage());
            }
            if (!this.conversationId_.isEmpty()) {
                codedOutputStream.writeString(7, getConversationId());
            }
            if (!this.subject_.isEmpty()) {
                codedOutputStream.writeString(8, getSubject());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(Message message) {
            return DEFAULT_INSTANCE.createBuilder(message);
        }

        public static Message parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Message) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Message parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Message parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Message parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Message parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Message parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Message parseFrom(InputStream inputStream) throws IOException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Message parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Message parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Message parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface MessageOrBuilder extends MessageLiteOrBuilder {
        String getCharset();

        ByteString getCharsetBytes();

        String getConversationId();

        ByteString getConversationIdBytes();

        String getIso8601DateTime();

        ByteString getIso8601DateTimeBytes();

        String getMessageContent();

        ByteString getMessageContentBytes();

        String getMessageIdentifier();

        ByteString getMessageIdentifierBytes();

        String getMessageLanguage();

        ByteString getMessageLanguageBytes();

        boolean getReadStatus();

        String getSubject();

        ByteString getSubjectBytes();
    }

    /* loaded from: classes6.dex */
    public static final class NotifySmsMessageList extends GeneratedMessageLite<NotifySmsMessageList, Builder> implements NotifySmsMessageListOrBuilder {
        private static final NotifySmsMessageList DEFAULT_INSTANCE = new NotifySmsMessageList();
        private static volatile Parser<NotifySmsMessageList> PARSER = null;
        public static final int SMSMESSAGELIST_FIELD_NUMBER = 1;
        private SmsMessageList smsMessageList_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotifySmsMessageList, Builder> implements NotifySmsMessageListOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearSmsMessageList() {
                copyOnWrite();
                ((NotifySmsMessageList) this.instance).clearSmsMessageList();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.NotifySmsMessageListOrBuilder
            public SmsMessageList getSmsMessageList() {
                return ((NotifySmsMessageList) this.instance).getSmsMessageList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.NotifySmsMessageListOrBuilder
            public boolean hasSmsMessageList() {
                return ((NotifySmsMessageList) this.instance).hasSmsMessageList();
            }

            public Builder mergeSmsMessageList(SmsMessageList smsMessageList) {
                copyOnWrite();
                ((NotifySmsMessageList) this.instance).mergeSmsMessageList(smsMessageList);
                return this;
            }

            public Builder setSmsMessageList(SmsMessageList smsMessageList) {
                copyOnWrite();
                ((NotifySmsMessageList) this.instance).setSmsMessageList(smsMessageList);
                return this;
            }

            private Builder() {
                super(NotifySmsMessageList.DEFAULT_INSTANCE);
            }

            public Builder setSmsMessageList(SmsMessageList.Builder builder) {
                copyOnWrite();
                ((NotifySmsMessageList) this.instance).setSmsMessageList(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotifySmsMessageList() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSmsMessageList() {
            this.smsMessageList_ = null;
        }

        public static NotifySmsMessageList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSmsMessageList(SmsMessageList smsMessageList) {
            SmsMessageList smsMessageList2 = this.smsMessageList_;
            if (smsMessageList2 != null && smsMessageList2 != SmsMessageList.getDefaultInstance()) {
                this.smsMessageList_ = SmsMessageList.newBuilder(this.smsMessageList_).mergeFrom((SmsMessageList.Builder) smsMessageList).mo10085buildPartial();
            } else {
                this.smsMessageList_ = smsMessageList;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotifySmsMessageList parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifySmsMessageList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotifySmsMessageList> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSmsMessageList(SmsMessageList smsMessageList) {
            if (smsMessageList != null) {
                this.smsMessageList_ = smsMessageList;
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
                    this.smsMessageList_ = (SmsMessageList) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.smsMessageList_, ((NotifySmsMessageList) obj2).smsMessageList_);
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
                                    SmsMessageList.Builder mo10081toBuilder = this.smsMessageList_ != null ? this.smsMessageList_.mo10081toBuilder() : null;
                                    this.smsMessageList_ = (SmsMessageList) codedInputStream.readMessage(SmsMessageList.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((SmsMessageList.Builder) this.smsMessageList_);
                                        this.smsMessageList_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new NotifySmsMessageList();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotifySmsMessageList.class) {
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
            if (this.smsMessageList_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getSmsMessageList());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.NotifySmsMessageListOrBuilder
        public SmsMessageList getSmsMessageList() {
            SmsMessageList smsMessageList = this.smsMessageList_;
            return smsMessageList == null ? SmsMessageList.getDefaultInstance() : smsMessageList;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.NotifySmsMessageListOrBuilder
        public boolean hasSmsMessageList() {
            return this.smsMessageList_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.smsMessageList_ != null) {
                codedOutputStream.writeMessage(1, getSmsMessageList());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotifySmsMessageList notifySmsMessageList) {
            return DEFAULT_INSTANCE.createBuilder(notifySmsMessageList);
        }

        public static NotifySmsMessageList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifySmsMessageList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotifySmsMessageList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSmsMessageList(SmsMessageList.Builder builder) {
            this.smsMessageList_ = builder.mo10084build();
        }

        public static NotifySmsMessageList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NotifySmsMessageList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifySmsMessageList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotifySmsMessageList parseFrom(InputStream inputStream) throws IOException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifySmsMessageList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifySmsMessageList parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotifySmsMessageList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifySmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotifySmsMessageListOrBuilder extends MessageLiteOrBuilder {
        SmsMessageList getSmsMessageList();

        boolean hasSmsMessageList();
    }

    /* loaded from: classes6.dex */
    public enum ReadStatusFilter implements Internal.EnumLite {
        UNREAD_STATUS_FILTER(0),
        READ_STATUS_FILTER(1),
        ALL_STATUS_FILTER(2),
        UNRECOGNIZED(-1);
        
        public static final int ALL_STATUS_FILTER_VALUE = 2;
        public static final int READ_STATUS_FILTER_VALUE = 1;
        public static final int UNREAD_STATUS_FILTER_VALUE = 0;
        private static final Internal.EnumLiteMap<ReadStatusFilter> internalValueMap = new Internal.EnumLiteMap<ReadStatusFilter>() { // from class: com.amazon.alexa.accessory.protocol.Mapsms.ReadStatusFilter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public ReadStatusFilter mo9850findValueByNumber(int i) {
                return ReadStatusFilter.forNumber(i);
            }
        };
        private final int value;

        ReadStatusFilter(int i) {
            this.value = i;
        }

        public static ReadStatusFilter forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return READ_STATUS_FILTER;
                }
                if (i == 2) {
                    return ALL_STATUS_FILTER;
                }
                return null;
            }
            return UNREAD_STATUS_FILTER;
        }

        public static Internal.EnumLiteMap<ReadStatusFilter> internalGetValueMap() {
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
        public static ReadStatusFilter valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class SendSms extends GeneratedMessageLite<SendSms, Builder> implements SendSmsOrBuilder {
        private static final SendSms DEFAULT_INSTANCE = new SendSms();
        private static volatile Parser<SendSms> PARSER = null;
        public static final int SMS_MESSAGE_FIELD_NUMBER = 1;
        private SmsMessage smsMessage_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SendSms, Builder> implements SendSmsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearSmsMessage() {
                copyOnWrite();
                ((SendSms) this.instance).clearSmsMessage();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SendSmsOrBuilder
            public SmsMessage getSmsMessage() {
                return ((SendSms) this.instance).getSmsMessage();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SendSmsOrBuilder
            public boolean hasSmsMessage() {
                return ((SendSms) this.instance).hasSmsMessage();
            }

            public Builder mergeSmsMessage(SmsMessage smsMessage) {
                copyOnWrite();
                ((SendSms) this.instance).mergeSmsMessage(smsMessage);
                return this;
            }

            public Builder setSmsMessage(SmsMessage smsMessage) {
                copyOnWrite();
                ((SendSms) this.instance).setSmsMessage(smsMessage);
                return this;
            }

            private Builder() {
                super(SendSms.DEFAULT_INSTANCE);
            }

            public Builder setSmsMessage(SmsMessage.Builder builder) {
                copyOnWrite();
                ((SendSms) this.instance).setSmsMessage(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SendSms() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSmsMessage() {
            this.smsMessage_ = null;
        }

        public static SendSms getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSmsMessage(SmsMessage smsMessage) {
            SmsMessage smsMessage2 = this.smsMessage_;
            if (smsMessage2 != null && smsMessage2 != SmsMessage.getDefaultInstance()) {
                this.smsMessage_ = SmsMessage.newBuilder(this.smsMessage_).mergeFrom((SmsMessage.Builder) smsMessage).mo10085buildPartial();
            } else {
                this.smsMessage_ = smsMessage;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SendSms parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SendSms) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SendSms parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SendSms> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSmsMessage(SmsMessage smsMessage) {
            if (smsMessage != null) {
                this.smsMessage_ = smsMessage;
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
                    this.smsMessage_ = (SmsMessage) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.smsMessage_, ((SendSms) obj2).smsMessage_);
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
                                    SmsMessage.Builder mo10081toBuilder = this.smsMessage_ != null ? this.smsMessage_.mo10081toBuilder() : null;
                                    this.smsMessage_ = (SmsMessage) codedInputStream.readMessage(SmsMessage.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((SmsMessage.Builder) this.smsMessage_);
                                        this.smsMessage_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new SendSms();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SendSms.class) {
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
            if (this.smsMessage_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getSmsMessage());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SendSmsOrBuilder
        public SmsMessage getSmsMessage() {
            SmsMessage smsMessage = this.smsMessage_;
            return smsMessage == null ? SmsMessage.getDefaultInstance() : smsMessage;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SendSmsOrBuilder
        public boolean hasSmsMessage() {
            return this.smsMessage_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.smsMessage_ != null) {
                codedOutputStream.writeMessage(1, getSmsMessage());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SendSms sendSms) {
            return DEFAULT_INSTANCE.createBuilder(sendSms);
        }

        public static SendSms parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SendSms) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SendSms parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SendSms parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSmsMessage(SmsMessage.Builder builder) {
            this.smsMessage_ = builder.mo10084build();
        }

        public static SendSms parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SendSms parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SendSms parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SendSms parseFrom(InputStream inputStream) throws IOException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SendSms parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SendSms parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SendSms parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SendSms) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SendSmsOrBuilder extends MessageLiteOrBuilder {
        SmsMessage getSmsMessage();

        boolean hasSmsMessage();
    }

    /* loaded from: classes6.dex */
    public static final class SetSmsReadStatus extends GeneratedMessageLite<SetSmsReadStatus, Builder> implements SetSmsReadStatusOrBuilder {
        private static final SetSmsReadStatus DEFAULT_INSTANCE = new SetSmsReadStatus();
        public static final int MESSAGE_IDENTIFIER_FIELD_NUMBER = 1;
        private static volatile Parser<SetSmsReadStatus> PARSER = null;
        public static final int READ_STATUS_FIELD_NUMBER = 2;
        private String messageIdentifier_ = "";
        private boolean readStatus_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SetSmsReadStatus, Builder> implements SetSmsReadStatusOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearMessageIdentifier() {
                copyOnWrite();
                ((SetSmsReadStatus) this.instance).clearMessageIdentifier();
                return this;
            }

            public Builder clearReadStatus() {
                copyOnWrite();
                ((SetSmsReadStatus) this.instance).clearReadStatus();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SetSmsReadStatusOrBuilder
            public String getMessageIdentifier() {
                return ((SetSmsReadStatus) this.instance).getMessageIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SetSmsReadStatusOrBuilder
            public ByteString getMessageIdentifierBytes() {
                return ((SetSmsReadStatus) this.instance).getMessageIdentifierBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SetSmsReadStatusOrBuilder
            public boolean getReadStatus() {
                return ((SetSmsReadStatus) this.instance).getReadStatus();
            }

            public Builder setMessageIdentifier(String str) {
                copyOnWrite();
                ((SetSmsReadStatus) this.instance).setMessageIdentifier(str);
                return this;
            }

            public Builder setMessageIdentifierBytes(ByteString byteString) {
                copyOnWrite();
                ((SetSmsReadStatus) this.instance).setMessageIdentifierBytes(byteString);
                return this;
            }

            public Builder setReadStatus(boolean z) {
                copyOnWrite();
                ((SetSmsReadStatus) this.instance).setReadStatus(z);
                return this;
            }

            private Builder() {
                super(SetSmsReadStatus.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SetSmsReadStatus() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessageIdentifier() {
            this.messageIdentifier_ = getDefaultInstance().getMessageIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearReadStatus() {
            this.readStatus_ = false;
        }

        public static SetSmsReadStatus getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SetSmsReadStatus parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetSmsReadStatus parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SetSmsReadStatus> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageIdentifier(String str) {
            if (str != null) {
                this.messageIdentifier_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageIdentifierBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.messageIdentifier_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReadStatus(boolean z) {
            this.readStatus_ = z;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    SetSmsReadStatus setSmsReadStatus = (SetSmsReadStatus) obj2;
                    this.messageIdentifier_ = visitor.visitString(!this.messageIdentifier_.isEmpty(), this.messageIdentifier_, true ^ setSmsReadStatus.messageIdentifier_.isEmpty(), setSmsReadStatus.messageIdentifier_);
                    boolean z = this.readStatus_;
                    boolean z2 = setSmsReadStatus.readStatus_;
                    this.readStatus_ = visitor.visitBoolean(z, z, z2, z2);
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
                                    this.messageIdentifier_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.readStatus_ = codedInputStream.readBool();
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
                    return new SetSmsReadStatus();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SetSmsReadStatus.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SetSmsReadStatusOrBuilder
        public String getMessageIdentifier() {
            return this.messageIdentifier_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SetSmsReadStatusOrBuilder
        public ByteString getMessageIdentifierBytes() {
            return ByteString.copyFromUtf8(this.messageIdentifier_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SetSmsReadStatusOrBuilder
        public boolean getReadStatus() {
            return this.readStatus_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.messageIdentifier_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getMessageIdentifier());
            }
            boolean z = this.readStatus_;
            if (z) {
                i2 += CodedOutputStream.computeBoolSize(2, z);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.messageIdentifier_.isEmpty()) {
                codedOutputStream.writeString(1, getMessageIdentifier());
            }
            boolean z = this.readStatus_;
            if (z) {
                codedOutputStream.writeBool(2, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SetSmsReadStatus setSmsReadStatus) {
            return DEFAULT_INSTANCE.createBuilder(setSmsReadStatus);
        }

        public static SetSmsReadStatus parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetSmsReadStatus parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SetSmsReadStatus parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SetSmsReadStatus parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SetSmsReadStatus parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SetSmsReadStatus parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SetSmsReadStatus parseFrom(InputStream inputStream) throws IOException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetSmsReadStatus parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetSmsReadStatus parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SetSmsReadStatus parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetSmsReadStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SetSmsReadStatusOrBuilder extends MessageLiteOrBuilder {
        String getMessageIdentifier();

        ByteString getMessageIdentifierBytes();

        boolean getReadStatus();
    }

    /* loaded from: classes6.dex */
    public static final class SmsMessage extends GeneratedMessageLite<SmsMessage, Builder> implements SmsMessageOrBuilder {
        public static final int CONTACT_FIELD_NUMBER = 2;
        private static final SmsMessage DEFAULT_INSTANCE = new SmsMessage();
        public static final int MESSAGE_PAYLOAD_FIELD_NUMBER = 1;
        private static volatile Parser<SmsMessage> PARSER;
        private ContactInformation contact_;
        private Message messagePayload_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SmsMessage, Builder> implements SmsMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearContact() {
                copyOnWrite();
                ((SmsMessage) this.instance).clearContact();
                return this;
            }

            public Builder clearMessagePayload() {
                copyOnWrite();
                ((SmsMessage) this.instance).clearMessagePayload();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageOrBuilder
            public ContactInformation getContact() {
                return ((SmsMessage) this.instance).getContact();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageOrBuilder
            public Message getMessagePayload() {
                return ((SmsMessage) this.instance).getMessagePayload();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageOrBuilder
            public boolean hasContact() {
                return ((SmsMessage) this.instance).hasContact();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageOrBuilder
            public boolean hasMessagePayload() {
                return ((SmsMessage) this.instance).hasMessagePayload();
            }

            public Builder mergeContact(ContactInformation contactInformation) {
                copyOnWrite();
                ((SmsMessage) this.instance).mergeContact(contactInformation);
                return this;
            }

            public Builder mergeMessagePayload(Message message) {
                copyOnWrite();
                ((SmsMessage) this.instance).mergeMessagePayload(message);
                return this;
            }

            public Builder setContact(ContactInformation contactInformation) {
                copyOnWrite();
                ((SmsMessage) this.instance).setContact(contactInformation);
                return this;
            }

            public Builder setMessagePayload(Message message) {
                copyOnWrite();
                ((SmsMessage) this.instance).setMessagePayload(message);
                return this;
            }

            private Builder() {
                super(SmsMessage.DEFAULT_INSTANCE);
            }

            public Builder setContact(ContactInformation.Builder builder) {
                copyOnWrite();
                ((SmsMessage) this.instance).setContact(builder);
                return this;
            }

            public Builder setMessagePayload(Message.Builder builder) {
                copyOnWrite();
                ((SmsMessage) this.instance).setMessagePayload(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SmsMessage() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContact() {
            this.contact_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessagePayload() {
            this.messagePayload_ = null;
        }

        public static SmsMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeContact(ContactInformation contactInformation) {
            ContactInformation contactInformation2 = this.contact_;
            if (contactInformation2 != null && contactInformation2 != ContactInformation.getDefaultInstance()) {
                this.contact_ = ContactInformation.newBuilder(this.contact_).mergeFrom((ContactInformation.Builder) contactInformation).mo10085buildPartial();
            } else {
                this.contact_ = contactInformation;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeMessagePayload(Message message) {
            Message message2 = this.messagePayload_;
            if (message2 != null && message2 != Message.getDefaultInstance()) {
                this.messagePayload_ = Message.newBuilder(this.messagePayload_).mergeFrom((Message.Builder) message).mo10085buildPartial();
            } else {
                this.messagePayload_ = message;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SmsMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SmsMessage) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SmsMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SmsMessage> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContact(ContactInformation contactInformation) {
            if (contactInformation != null) {
                this.contact_ = contactInformation;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessagePayload(Message message) {
            if (message != null) {
                this.messagePayload_ = message;
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
                    SmsMessage smsMessage = (SmsMessage) obj2;
                    this.messagePayload_ = (Message) visitor.visitMessage(this.messagePayload_, smsMessage.messagePayload_);
                    this.contact_ = (ContactInformation) visitor.visitMessage(this.contact_, smsMessage.contact_);
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
                                if (readTag == 10) {
                                    Message.Builder mo10081toBuilder = this.messagePayload_ != null ? this.messagePayload_.mo10081toBuilder() : null;
                                    this.messagePayload_ = (Message) codedInputStream.readMessage(Message.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Message.Builder) this.messagePayload_);
                                        this.messagePayload_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    ContactInformation.Builder mo10081toBuilder2 = this.contact_ != null ? this.contact_.mo10081toBuilder() : null;
                                    this.contact_ = (ContactInformation) codedInputStream.readMessage(ContactInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder2 != null) {
                                        mo10081toBuilder2.mergeFrom((ContactInformation.Builder) this.contact_);
                                        this.contact_ = mo10081toBuilder2.mo10085buildPartial();
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
                    return new SmsMessage();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SmsMessage.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageOrBuilder
        public ContactInformation getContact() {
            ContactInformation contactInformation = this.contact_;
            return contactInformation == null ? ContactInformation.getDefaultInstance() : contactInformation;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageOrBuilder
        public Message getMessagePayload() {
            Message message = this.messagePayload_;
            return message == null ? Message.getDefaultInstance() : message;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.messagePayload_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getMessagePayload());
            }
            if (this.contact_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getContact());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageOrBuilder
        public boolean hasContact() {
            return this.contact_ != null;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageOrBuilder
        public boolean hasMessagePayload() {
            return this.messagePayload_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.messagePayload_ != null) {
                codedOutputStream.writeMessage(1, getMessagePayload());
            }
            if (this.contact_ != null) {
                codedOutputStream.writeMessage(2, getContact());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SmsMessage smsMessage) {
            return DEFAULT_INSTANCE.createBuilder(smsMessage);
        }

        public static SmsMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmsMessage) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SmsMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SmsMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContact(ContactInformation.Builder builder) {
            this.contact_ = builder.mo10084build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessagePayload(Message.Builder builder) {
            this.messagePayload_ = builder.mo10084build();
        }

        public static SmsMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SmsMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SmsMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SmsMessage parseFrom(InputStream inputStream) throws IOException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SmsMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SmsMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SmsMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmsMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public static final class SmsMessageList extends GeneratedMessageLite<SmsMessageList, Builder> implements SmsMessageListOrBuilder {
        private static final SmsMessageList DEFAULT_INSTANCE = new SmsMessageList();
        private static volatile Parser<SmsMessageList> PARSER = null;
        public static final int SMS_MESSAGE_FIELD_NUMBER = 1;
        private Internal.ProtobufList<SmsMessage> smsMessage_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SmsMessageList, Builder> implements SmsMessageListOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllSmsMessage(Iterable<? extends SmsMessage> iterable) {
                copyOnWrite();
                ((SmsMessageList) this.instance).addAllSmsMessage(iterable);
                return this;
            }

            public Builder addSmsMessage(SmsMessage smsMessage) {
                copyOnWrite();
                ((SmsMessageList) this.instance).addSmsMessage(smsMessage);
                return this;
            }

            public Builder clearSmsMessage() {
                copyOnWrite();
                ((SmsMessageList) this.instance).clearSmsMessage();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageListOrBuilder
            public SmsMessage getSmsMessage(int i) {
                return ((SmsMessageList) this.instance).getSmsMessage(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageListOrBuilder
            public int getSmsMessageCount() {
                return ((SmsMessageList) this.instance).getSmsMessageCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageListOrBuilder
            public List<SmsMessage> getSmsMessageList() {
                return Collections.unmodifiableList(((SmsMessageList) this.instance).getSmsMessageList());
            }

            public Builder removeSmsMessage(int i) {
                copyOnWrite();
                ((SmsMessageList) this.instance).removeSmsMessage(i);
                return this;
            }

            public Builder setSmsMessage(int i, SmsMessage smsMessage) {
                copyOnWrite();
                ((SmsMessageList) this.instance).setSmsMessage(i, smsMessage);
                return this;
            }

            private Builder() {
                super(SmsMessageList.DEFAULT_INSTANCE);
            }

            public Builder addSmsMessage(int i, SmsMessage smsMessage) {
                copyOnWrite();
                ((SmsMessageList) this.instance).addSmsMessage(i, smsMessage);
                return this;
            }

            public Builder setSmsMessage(int i, SmsMessage.Builder builder) {
                copyOnWrite();
                ((SmsMessageList) this.instance).setSmsMessage(i, builder);
                return this;
            }

            public Builder addSmsMessage(SmsMessage.Builder builder) {
                copyOnWrite();
                ((SmsMessageList) this.instance).addSmsMessage(builder);
                return this;
            }

            public Builder addSmsMessage(int i, SmsMessage.Builder builder) {
                copyOnWrite();
                ((SmsMessageList) this.instance).addSmsMessage(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SmsMessageList() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSmsMessage(Iterable<? extends SmsMessage> iterable) {
            ensureSmsMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.smsMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSmsMessage(SmsMessage smsMessage) {
            if (smsMessage != null) {
                ensureSmsMessageIsMutable();
                this.smsMessage_.add(smsMessage);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSmsMessage() {
            this.smsMessage_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureSmsMessageIsMutable() {
            if (!this.smsMessage_.isModifiable()) {
                this.smsMessage_ = GeneratedMessageLite.mutableCopy(this.smsMessage_);
            }
        }

        public static SmsMessageList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SmsMessageList parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SmsMessageList) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SmsMessageList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SmsMessageList> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeSmsMessage(int i) {
            ensureSmsMessageIsMutable();
            this.smsMessage_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSmsMessage(int i, SmsMessage smsMessage) {
            if (smsMessage != null) {
                ensureSmsMessageIsMutable();
                this.smsMessage_.set(i, smsMessage);
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
                    this.smsMessage_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.smsMessage_, ((SmsMessageList) obj2).smsMessage_);
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
                                    if (!this.smsMessage_.isModifiable()) {
                                        this.smsMessage_ = GeneratedMessageLite.mutableCopy(this.smsMessage_);
                                    }
                                    this.smsMessage_.add(codedInputStream.readMessage(SmsMessage.parser(), extensionRegistryLite));
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
                    this.smsMessage_.makeImmutable();
                    return null;
                case 6:
                    return new SmsMessageList();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SmsMessageList.class) {
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
            for (int i3 = 0; i3 < this.smsMessage_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.smsMessage_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageListOrBuilder
        public SmsMessage getSmsMessage(int i) {
            return this.smsMessage_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageListOrBuilder
        public int getSmsMessageCount() {
            return this.smsMessage_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Mapsms.SmsMessageListOrBuilder
        public List<SmsMessage> getSmsMessageList() {
            return this.smsMessage_;
        }

        public SmsMessageOrBuilder getSmsMessageOrBuilder(int i) {
            return this.smsMessage_.get(i);
        }

        public List<? extends SmsMessageOrBuilder> getSmsMessageOrBuilderList() {
            return this.smsMessage_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.smsMessage_.size(); i++) {
                codedOutputStream.writeMessage(1, this.smsMessage_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SmsMessageList smsMessageList) {
            return DEFAULT_INSTANCE.createBuilder(smsMessageList);
        }

        public static SmsMessageList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmsMessageList) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SmsMessageList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SmsMessageList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSmsMessage(int i, SmsMessage smsMessage) {
            if (smsMessage != null) {
                ensureSmsMessageIsMutable();
                this.smsMessage_.add(i, smsMessage);
                return;
            }
            throw new NullPointerException();
        }

        public static SmsMessageList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSmsMessage(int i, SmsMessage.Builder builder) {
            ensureSmsMessageIsMutable();
            this.smsMessage_.set(i, builder.mo10084build());
        }

        public static SmsMessageList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SmsMessageList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSmsMessage(SmsMessage.Builder builder) {
            ensureSmsMessageIsMutable();
            this.smsMessage_.add(builder.mo10084build());
        }

        public static SmsMessageList parseFrom(InputStream inputStream) throws IOException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SmsMessageList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSmsMessage(int i, SmsMessage.Builder builder) {
            ensureSmsMessageIsMutable();
            this.smsMessage_.add(i, builder.mo10084build());
        }

        public static SmsMessageList parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SmsMessageList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmsMessageList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SmsMessageListOrBuilder extends MessageLiteOrBuilder {
        SmsMessage getSmsMessage(int i);

        int getSmsMessageCount();

        List<SmsMessage> getSmsMessageList();
    }

    /* loaded from: classes6.dex */
    public interface SmsMessageOrBuilder extends MessageLiteOrBuilder {
        ContactInformation getContact();

        Message getMessagePayload();

        boolean hasContact();

        boolean hasMessagePayload();
    }

    private Mapsms() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
