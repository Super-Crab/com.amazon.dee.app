package com.amazon.alexa.mobilytics.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes9.dex */
public final class CustomerProto extends GeneratedMessageV3 implements CustomerProtoOrBuilder {
    public static final int COUNTRYOFRESIDENCE_FIELD_NUMBER = 6;
    public static final int DIRECTEDID_FIELD_NUMBER = 3;
    public static final int HASHEDCOMMSID_FIELD_NUMBER = 1;
    public static final int HOUSEHOLDID_FIELD_NUMBER = 2;
    public static final int MARKETPLACEID_FIELD_NUMBER = 4;
    public static final int PERSONIDV2_FIELD_NUMBER = 7;
    public static final int PERSONID_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private volatile Object countryOfResidence_;
    private volatile Object directedId_;
    private volatile Object hashedCommsId_;
    private volatile Object householdId_;
    private volatile Object marketplaceId_;
    private byte memoizedIsInitialized;
    private volatile Object personId_;
    private volatile Object personIdv2_;
    private static final CustomerProto DEFAULT_INSTANCE = new CustomerProto();
    private static final Parser<CustomerProto> PARSER = new AbstractParser<CustomerProto>() { // from class: com.amazon.alexa.mobilytics.protobuf.CustomerProto.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public CustomerProto mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CustomerProto(codedInputStream, extensionRegistryLite);
        }
    };

    public static CustomerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return CustomerProtoOuterClass.internal_static_protobuf_CustomerProto_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static CustomerProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CustomerProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CustomerProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<CustomerProto> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomerProto)) {
            return super.equals(obj);
        }
        CustomerProto customerProto = (CustomerProto) obj;
        return (((((((getHashedCommsId().equals(customerProto.getHashedCommsId())) && getHouseholdId().equals(customerProto.getHouseholdId())) && getDirectedId().equals(customerProto.getDirectedId())) && getMarketplaceId().equals(customerProto.getMarketplaceId())) && getPersonId().equals(customerProto.getPersonId())) && getCountryOfResidence().equals(customerProto.getCountryOfResidence())) && getPersonIdv2().equals(customerProto.getPersonIdv2())) && this.unknownFields.equals(customerProto.unknownFields);
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public String getCountryOfResidence() {
        Object obj = this.countryOfResidence_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.countryOfResidence_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public ByteString getCountryOfResidenceBytes() {
        Object obj = this.countryOfResidence_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.countryOfResidence_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public String getDirectedId() {
        Object obj = this.directedId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.directedId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public ByteString getDirectedIdBytes() {
        Object obj = this.directedId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.directedId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public String getHashedCommsId() {
        Object obj = this.hashedCommsId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.hashedCommsId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public ByteString getHashedCommsIdBytes() {
        Object obj = this.hashedCommsId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.hashedCommsId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public String getHouseholdId() {
        Object obj = this.householdId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.householdId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public ByteString getHouseholdIdBytes() {
        Object obj = this.householdId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.householdId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public String getMarketplaceId() {
        Object obj = this.marketplaceId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.marketplaceId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public ByteString getMarketplaceIdBytes() {
        Object obj = this.marketplaceId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.marketplaceId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<CustomerProto> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public String getPersonId() {
        Object obj = this.personId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.personId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public ByteString getPersonIdBytes() {
        Object obj = this.personId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.personId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public String getPersonIdv2() {
        Object obj = this.personIdv2_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.personIdv2_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
    public ByteString getPersonIdv2Bytes() {
        Object obj = this.personIdv2_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.personIdv2_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getHashedCommsIdBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.hashedCommsId_);
        }
        if (!getHouseholdIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.householdId_);
        }
        if (!getDirectedIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.directedId_);
        }
        if (!getMarketplaceIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(4, this.marketplaceId_);
        }
        if (!getPersonIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(5, this.personId_);
        }
        if (!getCountryOfResidenceBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(6, this.countryOfResidence_);
        }
        if (!getPersonIdv2Bytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(7, this.personIdv2_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getHashedCommsId().hashCode();
        int hashCode2 = getHouseholdId().hashCode();
        int hashCode3 = getDirectedId().hashCode();
        int hashCode4 = getMarketplaceId().hashCode();
        int hashCode5 = getPersonId().hashCode();
        int hashCode6 = getCountryOfResidence().hashCode();
        int hashCode7 = getPersonIdv2().hashCode();
        int hashCode8 = this.unknownFields.hashCode() + ((hashCode7 + ((((hashCode6 + ((((hashCode5 + ((((hashCode4 + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 37) + 6) * 53)) * 37) + 7) * 53)) * 29);
        this.memoizedHashCode = hashCode8;
        return hashCode8;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CustomerProtoOuterClass.internal_static_protobuf_CustomerProto_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomerProto.class, Builder.class);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getHashedCommsIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.hashedCommsId_);
        }
        if (!getHouseholdIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.householdId_);
        }
        if (!getDirectedIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.directedId_);
        }
        if (!getMarketplaceIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.marketplaceId_);
        }
        if (!getPersonIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.personId_);
        }
        if (!getCountryOfResidenceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.countryOfResidence_);
        }
        if (!getPersonIdv2Bytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.personIdv2_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes9.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CustomerProtoOrBuilder {
        private Object countryOfResidence_;
        private Object directedId_;
        private Object hashedCommsId_;
        private Object householdId_;
        private Object marketplaceId_;
        private Object personId_;
        private Object personIdv2_;

        public static final Descriptors.Descriptor getDescriptor() {
            return CustomerProtoOuterClass.internal_static_protobuf_CustomerProto_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearCountryOfResidence() {
            this.countryOfResidence_ = CustomerProto.getDefaultInstance().getCountryOfResidence();
            onChanged();
            return this;
        }

        public Builder clearDirectedId() {
            this.directedId_ = CustomerProto.getDefaultInstance().getDirectedId();
            onChanged();
            return this;
        }

        public Builder clearHashedCommsId() {
            this.hashedCommsId_ = CustomerProto.getDefaultInstance().getHashedCommsId();
            onChanged();
            return this;
        }

        public Builder clearHouseholdId() {
            this.householdId_ = CustomerProto.getDefaultInstance().getHouseholdId();
            onChanged();
            return this;
        }

        public Builder clearMarketplaceId() {
            this.marketplaceId_ = CustomerProto.getDefaultInstance().getMarketplaceId();
            onChanged();
            return this;
        }

        public Builder clearPersonId() {
            this.personId_ = CustomerProto.getDefaultInstance().getPersonId();
            onChanged();
            return this;
        }

        public Builder clearPersonIdv2() {
            this.personIdv2_ = CustomerProto.getDefaultInstance().getPersonIdv2();
            onChanged();
            return this;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public String getCountryOfResidence() {
            Object obj = this.countryOfResidence_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.countryOfResidence_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public ByteString getCountryOfResidenceBytes() {
            Object obj = this.countryOfResidence_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.countryOfResidence_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return CustomerProtoOuterClass.internal_static_protobuf_CustomerProto_descriptor;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public String getDirectedId() {
            Object obj = this.directedId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.directedId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public ByteString getDirectedIdBytes() {
            Object obj = this.directedId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.directedId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public String getHashedCommsId() {
            Object obj = this.hashedCommsId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.hashedCommsId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public ByteString getHashedCommsIdBytes() {
            Object obj = this.hashedCommsId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.hashedCommsId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public String getHouseholdId() {
            Object obj = this.householdId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.householdId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public ByteString getHouseholdIdBytes() {
            Object obj = this.householdId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.householdId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public String getMarketplaceId() {
            Object obj = this.marketplaceId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.marketplaceId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public ByteString getMarketplaceIdBytes() {
            Object obj = this.marketplaceId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.marketplaceId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public String getPersonId() {
            Object obj = this.personId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.personId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public ByteString getPersonIdBytes() {
            Object obj = this.personId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.personId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public String getPersonIdv2() {
            Object obj = this.personIdv2_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.personIdv2_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.CustomerProtoOrBuilder
        public ByteString getPersonIdv2Bytes() {
            Object obj = this.personIdv2_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.personIdv2_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CustomerProtoOuterClass.internal_static_protobuf_CustomerProto_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomerProto.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder setCountryOfResidence(String str) {
            if (str != null) {
                this.countryOfResidence_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setCountryOfResidenceBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.countryOfResidence_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setDirectedId(String str) {
            if (str != null) {
                this.directedId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setDirectedIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.directedId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setHashedCommsId(String str) {
            if (str != null) {
                this.hashedCommsId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setHashedCommsIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.hashedCommsId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setHouseholdId(String str) {
            if (str != null) {
                this.householdId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setHouseholdIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.householdId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setMarketplaceId(String str) {
            if (str != null) {
                this.marketplaceId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setMarketplaceIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.marketplaceId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setPersonId(String str) {
            if (str != null) {
                this.personId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setPersonIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.personId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setPersonIdv2(String str) {
            if (str != null) {
                this.personIdv2_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setPersonIdv2Bytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.personIdv2_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private Builder() {
            this.hashedCommsId_ = "";
            this.householdId_ = "";
            this.directedId_ = "";
            this.marketplaceId_ = "";
            this.personId_ = "";
            this.countryOfResidence_ = "";
            this.personIdv2_ = "";
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public CustomerProto mo10084build() {
            CustomerProto mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public CustomerProto mo10085buildPartial() {
            CustomerProto customerProto = new CustomerProto(this);
            customerProto.hashedCommsId_ = this.hashedCommsId_;
            customerProto.householdId_ = this.householdId_;
            customerProto.directedId_ = this.directedId_;
            customerProto.marketplaceId_ = this.marketplaceId_;
            customerProto.personId_ = this.personId_;
            customerProto.countryOfResidence_ = this.countryOfResidence_;
            customerProto.personIdv2_ = this.personIdv2_;
            onBuilt();
            return customerProto;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public CustomerProto mo10094getDefaultInstanceForType() {
            return CustomerProto.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setField */
        public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10100setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setRepeatedField */
        public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setUnknownFields */
        public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearOneof */
        public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo10090clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeUnknownFields */
        public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clear */
        public Builder mo10087clear() {
            super.mo10087clear();
            this.hashedCommsId_ = "";
            this.householdId_ = "";
            this.directedId_ = "";
            this.marketplaceId_ = "";
            this.personId_ = "";
            this.countryOfResidence_ = "";
            this.personIdv2_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone */
        public Builder mo10093clone() {
            return (Builder) super.mo10093clone();
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public Builder mo10096mergeFrom(Message message) {
            if (message instanceof CustomerProto) {
                return mergeFrom((CustomerProto) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CustomerProto customerProto) {
            if (customerProto == CustomerProto.getDefaultInstance()) {
                return this;
            }
            if (!customerProto.getHashedCommsId().isEmpty()) {
                this.hashedCommsId_ = customerProto.hashedCommsId_;
                onChanged();
            }
            if (!customerProto.getHouseholdId().isEmpty()) {
                this.householdId_ = customerProto.householdId_;
                onChanged();
            }
            if (!customerProto.getDirectedId().isEmpty()) {
                this.directedId_ = customerProto.directedId_;
                onChanged();
            }
            if (!customerProto.getMarketplaceId().isEmpty()) {
                this.marketplaceId_ = customerProto.marketplaceId_;
                onChanged();
            }
            if (!customerProto.getPersonId().isEmpty()) {
                this.personId_ = customerProto.personId_;
                onChanged();
            }
            if (!customerProto.getCountryOfResidence().isEmpty()) {
                this.countryOfResidence_ = customerProto.countryOfResidence_;
                onChanged();
            }
            if (!customerProto.getPersonIdv2().isEmpty()) {
                this.personIdv2_ = customerProto.personIdv2_;
                onChanged();
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) customerProto).unknownFields);
            onChanged();
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.hashedCommsId_ = "";
            this.householdId_ = "";
            this.directedId_ = "";
            this.marketplaceId_ = "";
            this.personId_ = "";
            this.countryOfResidence_ = "";
            this.personIdv2_ = "";
            maybeForceBuilderInitialization();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.amazon.alexa.mobilytics.protobuf.CustomerProto.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.CustomerProto.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.alexa.mobilytics.protobuf.CustomerProto r3 = (com.amazon.alexa.mobilytics.protobuf.CustomerProto) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                com.amazon.alexa.mobilytics.protobuf.CustomerProto r4 = (com.amazon.alexa.mobilytics.protobuf.CustomerProto) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.CustomerProto.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.CustomerProto$Builder");
        }
    }

    public static Builder newBuilder(CustomerProto customerProto) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(customerProto);
    }

    public static CustomerProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private CustomerProto(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static CustomerProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CustomerProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CustomerProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public CustomerProto mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static CustomerProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private CustomerProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.hashedCommsId_ = "";
        this.householdId_ = "";
        this.directedId_ = "";
        this.marketplaceId_ = "";
        this.personId_ = "";
        this.countryOfResidence_ = "";
        this.personIdv2_ = "";
    }

    public static CustomerProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static CustomerProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static CustomerProto parseFrom(InputStream inputStream) throws IOException {
        return (CustomerProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CustomerProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CustomerProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CustomerProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CustomerProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CustomerProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CustomerProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    private CustomerProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.hashedCommsId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.householdId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.directedId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                this.marketplaceId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 42) {
                                this.personId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 50) {
                                this.countryOfResidence_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag != 58) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.personIdv2_ = codedInputStream.readStringRequireUtf8();
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.mo10084build();
                    makeExtensionsImmutable();
                }
            }
            return;
        }
        throw new NullPointerException();
    }
}
