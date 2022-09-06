package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.alexa.mobilytics.protobuf.ApplicationProto;
import com.amazon.alexa.mobilytics.protobuf.ClientProto;
import com.amazon.alexa.mobilytics.protobuf.CustomerProto;
import com.amazon.alexa.mobilytics.protobuf.DeviceProto;
import com.amazon.alexa.mobilytics.protobuf.EventProto;
import com.amazon.alexa.mobilytics.protobuf.SessionProto;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes9.dex */
public final class MobilyticsMessageProto extends GeneratedMessageV3 implements MobilyticsMessageProtoOrBuilder {
    public static final int APPLICATION_FIELD_NUMBER = 4;
    public static final int CLIENT_FIELD_NUMBER = 3;
    public static final int CUSTOMER_FIELD_NUMBER = 1;
    public static final int DEVICE_FIELD_NUMBER = 6;
    public static final int EVENT_FIELD_NUMBER = 7;
    public static final int PIVOTS_FIELD_NUMBER = 2;
    public static final int SESSION_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private ApplicationProto application_;
    private ClientProto client_;
    private CustomerProto customer_;
    private DeviceProto device_;
    private EventProto event_;
    private byte memoizedIsInitialized;
    private volatile Object pivots_;
    private SessionProto session_;
    private static final MobilyticsMessageProto DEFAULT_INSTANCE = new MobilyticsMessageProto();
    private static final Parser<MobilyticsMessageProto> PARSER = new AbstractParser<MobilyticsMessageProto>() { // from class: com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public MobilyticsMessageProto mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MobilyticsMessageProto(codedInputStream, extensionRegistryLite);
        }
    };

    public static MobilyticsMessageProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MobilyticsMessageProtoOuterClass.internal_static_protobuf_MobilyticsMessageProto_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static MobilyticsMessageProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MobilyticsMessageProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MobilyticsMessageProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<MobilyticsMessageProto> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MobilyticsMessageProto)) {
            return super.equals(obj);
        }
        MobilyticsMessageProto mobilyticsMessageProto = (MobilyticsMessageProto) obj;
        boolean z = hasCustomer() == mobilyticsMessageProto.hasCustomer();
        if (hasCustomer()) {
            z = z && getCustomer().equals(mobilyticsMessageProto.getCustomer());
        }
        boolean z2 = (z && getPivots().equals(mobilyticsMessageProto.getPivots())) && hasClient() == mobilyticsMessageProto.hasClient();
        if (hasClient()) {
            z2 = z2 && getClient().equals(mobilyticsMessageProto.getClient());
        }
        boolean z3 = z2 && hasApplication() == mobilyticsMessageProto.hasApplication();
        if (hasApplication()) {
            z3 = z3 && getApplication().equals(mobilyticsMessageProto.getApplication());
        }
        boolean z4 = z3 && hasSession() == mobilyticsMessageProto.hasSession();
        if (hasSession()) {
            z4 = z4 && getSession().equals(mobilyticsMessageProto.getSession());
        }
        boolean z5 = z4 && hasDevice() == mobilyticsMessageProto.hasDevice();
        if (hasDevice()) {
            z5 = z5 && getDevice().equals(mobilyticsMessageProto.getDevice());
        }
        boolean z6 = z5 && hasEvent() == mobilyticsMessageProto.hasEvent();
        if (hasEvent()) {
            z6 = z6 && getEvent().equals(mobilyticsMessageProto.getEvent());
        }
        return z6 && this.unknownFields.equals(mobilyticsMessageProto.unknownFields);
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public ApplicationProto getApplication() {
        ApplicationProto applicationProto = this.application_;
        return applicationProto == null ? ApplicationProto.getDefaultInstance() : applicationProto;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public ApplicationProtoOrBuilder getApplicationOrBuilder() {
        return getApplication();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public ClientProto getClient() {
        ClientProto clientProto = this.client_;
        return clientProto == null ? ClientProto.getDefaultInstance() : clientProto;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public ClientProtoOrBuilder getClientOrBuilder() {
        return getClient();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public CustomerProto getCustomer() {
        CustomerProto customerProto = this.customer_;
        return customerProto == null ? CustomerProto.getDefaultInstance() : customerProto;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public CustomerProtoOrBuilder getCustomerOrBuilder() {
        return getCustomer();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public DeviceProto getDevice() {
        DeviceProto deviceProto = this.device_;
        return deviceProto == null ? DeviceProto.getDefaultInstance() : deviceProto;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public DeviceProtoOrBuilder getDeviceOrBuilder() {
        return getDevice();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public EventProto getEvent() {
        EventProto eventProto = this.event_;
        return eventProto == null ? EventProto.getDefaultInstance() : eventProto;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public EventProtoOrBuilder getEventOrBuilder() {
        return getEvent();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<MobilyticsMessageProto> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public String getPivots() {
        Object obj = this.pivots_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.pivots_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public ByteString getPivotsBytes() {
        Object obj = this.pivots_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.pivots_ = copyFromUtf8;
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
        if (this.customer_ != null) {
            i2 = 0 + CodedOutputStream.computeMessageSize(1, getCustomer());
        }
        if (!getPivotsBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.pivots_);
        }
        if (this.client_ != null) {
            i2 += CodedOutputStream.computeMessageSize(3, getClient());
        }
        if (this.application_ != null) {
            i2 += CodedOutputStream.computeMessageSize(4, getApplication());
        }
        if (this.session_ != null) {
            i2 += CodedOutputStream.computeMessageSize(5, getSession());
        }
        if (this.device_ != null) {
            i2 += CodedOutputStream.computeMessageSize(6, getDevice());
        }
        if (this.event_ != null) {
            i2 += CodedOutputStream.computeMessageSize(7, getEvent());
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public SessionProto getSession() {
        SessionProto sessionProto = this.session_;
        return sessionProto == null ? SessionProto.getDefaultInstance() : sessionProto;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public SessionProtoOrBuilder getSessionOrBuilder() {
        return getSession();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public boolean hasApplication() {
        return this.application_ != null;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public boolean hasClient() {
        return this.client_ != null;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public boolean hasCustomer() {
        return this.customer_ != null;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public boolean hasDevice() {
        return this.device_ != null;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public boolean hasEvent() {
        return this.event_ != null;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
    public boolean hasSession() {
        return this.session_ != null;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (hasCustomer()) {
            hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getCustomer().hashCode();
        }
        int hashCode2 = getPivots().hashCode() + GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53);
        if (hasClient()) {
            hashCode2 = getClient().hashCode() + GeneratedOutlineSupport1.outline4(hashCode2, 37, 3, 53);
        }
        if (hasApplication()) {
            hashCode2 = getApplication().hashCode() + GeneratedOutlineSupport1.outline4(hashCode2, 37, 4, 53);
        }
        if (hasSession()) {
            hashCode2 = getSession().hashCode() + GeneratedOutlineSupport1.outline4(hashCode2, 37, 5, 53);
        }
        if (hasDevice()) {
            hashCode2 = getDevice().hashCode() + GeneratedOutlineSupport1.outline4(hashCode2, 37, 6, 53);
        }
        if (hasEvent()) {
            hashCode2 = getEvent().hashCode() + GeneratedOutlineSupport1.outline4(hashCode2, 37, 7, 53);
        }
        int hashCode3 = this.unknownFields.hashCode() + (hashCode2 * 29);
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilyticsMessageProtoOuterClass.internal_static_protobuf_MobilyticsMessageProto_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilyticsMessageProto.class, Builder.class);
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
        if (this.customer_ != null) {
            codedOutputStream.writeMessage(1, getCustomer());
        }
        if (!getPivotsBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.pivots_);
        }
        if (this.client_ != null) {
            codedOutputStream.writeMessage(3, getClient());
        }
        if (this.application_ != null) {
            codedOutputStream.writeMessage(4, getApplication());
        }
        if (this.session_ != null) {
            codedOutputStream.writeMessage(5, getSession());
        }
        if (this.device_ != null) {
            codedOutputStream.writeMessage(6, getDevice());
        }
        if (this.event_ != null) {
            codedOutputStream.writeMessage(7, getEvent());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes9.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilyticsMessageProtoOrBuilder {
        private SingleFieldBuilderV3<ApplicationProto, ApplicationProto.Builder, ApplicationProtoOrBuilder> applicationBuilder_;
        private ApplicationProto application_;
        private SingleFieldBuilderV3<ClientProto, ClientProto.Builder, ClientProtoOrBuilder> clientBuilder_;
        private ClientProto client_;
        private SingleFieldBuilderV3<CustomerProto, CustomerProto.Builder, CustomerProtoOrBuilder> customerBuilder_;
        private CustomerProto customer_;
        private SingleFieldBuilderV3<DeviceProto, DeviceProto.Builder, DeviceProtoOrBuilder> deviceBuilder_;
        private DeviceProto device_;
        private SingleFieldBuilderV3<EventProto, EventProto.Builder, EventProtoOrBuilder> eventBuilder_;
        private EventProto event_;
        private Object pivots_;
        private SingleFieldBuilderV3<SessionProto, SessionProto.Builder, SessionProtoOrBuilder> sessionBuilder_;
        private SessionProto session_;

        private SingleFieldBuilderV3<ApplicationProto, ApplicationProto.Builder, ApplicationProtoOrBuilder> getApplicationFieldBuilder() {
            if (this.applicationBuilder_ == null) {
                this.applicationBuilder_ = new SingleFieldBuilderV3<>(getApplication(), getParentForChildren(), isClean());
                this.application_ = null;
            }
            return this.applicationBuilder_;
        }

        private SingleFieldBuilderV3<ClientProto, ClientProto.Builder, ClientProtoOrBuilder> getClientFieldBuilder() {
            if (this.clientBuilder_ == null) {
                this.clientBuilder_ = new SingleFieldBuilderV3<>(getClient(), getParentForChildren(), isClean());
                this.client_ = null;
            }
            return this.clientBuilder_;
        }

        private SingleFieldBuilderV3<CustomerProto, CustomerProto.Builder, CustomerProtoOrBuilder> getCustomerFieldBuilder() {
            if (this.customerBuilder_ == null) {
                this.customerBuilder_ = new SingleFieldBuilderV3<>(getCustomer(), getParentForChildren(), isClean());
                this.customer_ = null;
            }
            return this.customerBuilder_;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MobilyticsMessageProtoOuterClass.internal_static_protobuf_MobilyticsMessageProto_descriptor;
        }

        private SingleFieldBuilderV3<DeviceProto, DeviceProto.Builder, DeviceProtoOrBuilder> getDeviceFieldBuilder() {
            if (this.deviceBuilder_ == null) {
                this.deviceBuilder_ = new SingleFieldBuilderV3<>(getDevice(), getParentForChildren(), isClean());
                this.device_ = null;
            }
            return this.deviceBuilder_;
        }

        private SingleFieldBuilderV3<EventProto, EventProto.Builder, EventProtoOrBuilder> getEventFieldBuilder() {
            if (this.eventBuilder_ == null) {
                this.eventBuilder_ = new SingleFieldBuilderV3<>(getEvent(), getParentForChildren(), isClean());
                this.event_ = null;
            }
            return this.eventBuilder_;
        }

        private SingleFieldBuilderV3<SessionProto, SessionProto.Builder, SessionProtoOrBuilder> getSessionFieldBuilder() {
            if (this.sessionBuilder_ == null) {
                this.sessionBuilder_ = new SingleFieldBuilderV3<>(getSession(), getParentForChildren(), isClean());
                this.session_ = null;
            }
            return this.sessionBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearApplication() {
            if (this.applicationBuilder_ == null) {
                this.application_ = null;
                onChanged();
            } else {
                this.application_ = null;
                this.applicationBuilder_ = null;
            }
            return this;
        }

        public Builder clearClient() {
            if (this.clientBuilder_ == null) {
                this.client_ = null;
                onChanged();
            } else {
                this.client_ = null;
                this.clientBuilder_ = null;
            }
            return this;
        }

        public Builder clearCustomer() {
            if (this.customerBuilder_ == null) {
                this.customer_ = null;
                onChanged();
            } else {
                this.customer_ = null;
                this.customerBuilder_ = null;
            }
            return this;
        }

        public Builder clearDevice() {
            if (this.deviceBuilder_ == null) {
                this.device_ = null;
                onChanged();
            } else {
                this.device_ = null;
                this.deviceBuilder_ = null;
            }
            return this;
        }

        public Builder clearEvent() {
            if (this.eventBuilder_ == null) {
                this.event_ = null;
                onChanged();
            } else {
                this.event_ = null;
                this.eventBuilder_ = null;
            }
            return this;
        }

        public Builder clearPivots() {
            this.pivots_ = MobilyticsMessageProto.getDefaultInstance().getPivots();
            onChanged();
            return this;
        }

        public Builder clearSession() {
            if (this.sessionBuilder_ == null) {
                this.session_ = null;
                onChanged();
            } else {
                this.session_ = null;
                this.sessionBuilder_ = null;
            }
            return this;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public ApplicationProto getApplication() {
            SingleFieldBuilderV3<ApplicationProto, ApplicationProto.Builder, ApplicationProtoOrBuilder> singleFieldBuilderV3 = this.applicationBuilder_;
            if (singleFieldBuilderV3 == null) {
                ApplicationProto applicationProto = this.application_;
                return applicationProto == null ? ApplicationProto.getDefaultInstance() : applicationProto;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public ApplicationProto.Builder getApplicationBuilder() {
            onChanged();
            return getApplicationFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public ApplicationProtoOrBuilder getApplicationOrBuilder() {
            SingleFieldBuilderV3<ApplicationProto, ApplicationProto.Builder, ApplicationProtoOrBuilder> singleFieldBuilderV3 = this.applicationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            ApplicationProto applicationProto = this.application_;
            return applicationProto == null ? ApplicationProto.getDefaultInstance() : applicationProto;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public ClientProto getClient() {
            SingleFieldBuilderV3<ClientProto, ClientProto.Builder, ClientProtoOrBuilder> singleFieldBuilderV3 = this.clientBuilder_;
            if (singleFieldBuilderV3 == null) {
                ClientProto clientProto = this.client_;
                return clientProto == null ? ClientProto.getDefaultInstance() : clientProto;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public ClientProto.Builder getClientBuilder() {
            onChanged();
            return getClientFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public ClientProtoOrBuilder getClientOrBuilder() {
            SingleFieldBuilderV3<ClientProto, ClientProto.Builder, ClientProtoOrBuilder> singleFieldBuilderV3 = this.clientBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            ClientProto clientProto = this.client_;
            return clientProto == null ? ClientProto.getDefaultInstance() : clientProto;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public CustomerProto getCustomer() {
            SingleFieldBuilderV3<CustomerProto, CustomerProto.Builder, CustomerProtoOrBuilder> singleFieldBuilderV3 = this.customerBuilder_;
            if (singleFieldBuilderV3 == null) {
                CustomerProto customerProto = this.customer_;
                return customerProto == null ? CustomerProto.getDefaultInstance() : customerProto;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public CustomerProto.Builder getCustomerBuilder() {
            onChanged();
            return getCustomerFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public CustomerProtoOrBuilder getCustomerOrBuilder() {
            SingleFieldBuilderV3<CustomerProto, CustomerProto.Builder, CustomerProtoOrBuilder> singleFieldBuilderV3 = this.customerBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            CustomerProto customerProto = this.customer_;
            return customerProto == null ? CustomerProto.getDefaultInstance() : customerProto;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return MobilyticsMessageProtoOuterClass.internal_static_protobuf_MobilyticsMessageProto_descriptor;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public DeviceProto getDevice() {
            SingleFieldBuilderV3<DeviceProto, DeviceProto.Builder, DeviceProtoOrBuilder> singleFieldBuilderV3 = this.deviceBuilder_;
            if (singleFieldBuilderV3 == null) {
                DeviceProto deviceProto = this.device_;
                return deviceProto == null ? DeviceProto.getDefaultInstance() : deviceProto;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public DeviceProto.Builder getDeviceBuilder() {
            onChanged();
            return getDeviceFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public DeviceProtoOrBuilder getDeviceOrBuilder() {
            SingleFieldBuilderV3<DeviceProto, DeviceProto.Builder, DeviceProtoOrBuilder> singleFieldBuilderV3 = this.deviceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            DeviceProto deviceProto = this.device_;
            return deviceProto == null ? DeviceProto.getDefaultInstance() : deviceProto;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public EventProto getEvent() {
            SingleFieldBuilderV3<EventProto, EventProto.Builder, EventProtoOrBuilder> singleFieldBuilderV3 = this.eventBuilder_;
            if (singleFieldBuilderV3 == null) {
                EventProto eventProto = this.event_;
                return eventProto == null ? EventProto.getDefaultInstance() : eventProto;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public EventProto.Builder getEventBuilder() {
            onChanged();
            return getEventFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public EventProtoOrBuilder getEventOrBuilder() {
            SingleFieldBuilderV3<EventProto, EventProto.Builder, EventProtoOrBuilder> singleFieldBuilderV3 = this.eventBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            EventProto eventProto = this.event_;
            return eventProto == null ? EventProto.getDefaultInstance() : eventProto;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public String getPivots() {
            Object obj = this.pivots_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.pivots_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public ByteString getPivotsBytes() {
            Object obj = this.pivots_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.pivots_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public SessionProto getSession() {
            SingleFieldBuilderV3<SessionProto, SessionProto.Builder, SessionProtoOrBuilder> singleFieldBuilderV3 = this.sessionBuilder_;
            if (singleFieldBuilderV3 == null) {
                SessionProto sessionProto = this.session_;
                return sessionProto == null ? SessionProto.getDefaultInstance() : sessionProto;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public SessionProto.Builder getSessionBuilder() {
            onChanged();
            return getSessionFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public SessionProtoOrBuilder getSessionOrBuilder() {
            SingleFieldBuilderV3<SessionProto, SessionProto.Builder, SessionProtoOrBuilder> singleFieldBuilderV3 = this.sessionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SessionProto sessionProto = this.session_;
            return sessionProto == null ? SessionProto.getDefaultInstance() : sessionProto;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public boolean hasApplication() {
            return (this.applicationBuilder_ == null && this.application_ == null) ? false : true;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public boolean hasClient() {
            return (this.clientBuilder_ == null && this.client_ == null) ? false : true;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public boolean hasCustomer() {
            return (this.customerBuilder_ == null && this.customer_ == null) ? false : true;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public boolean hasDevice() {
            return (this.deviceBuilder_ == null && this.device_ == null) ? false : true;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public boolean hasEvent() {
            return (this.eventBuilder_ == null && this.event_ == null) ? false : true;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOrBuilder
        public boolean hasSession() {
            return (this.sessionBuilder_ == null && this.session_ == null) ? false : true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MobilyticsMessageProtoOuterClass.internal_static_protobuf_MobilyticsMessageProto_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilyticsMessageProto.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeApplication(ApplicationProto applicationProto) {
            SingleFieldBuilderV3<ApplicationProto, ApplicationProto.Builder, ApplicationProtoOrBuilder> singleFieldBuilderV3 = this.applicationBuilder_;
            if (singleFieldBuilderV3 == null) {
                ApplicationProto applicationProto2 = this.application_;
                if (applicationProto2 != null) {
                    this.application_ = ApplicationProto.newBuilder(applicationProto2).mergeFrom(applicationProto).mo10085buildPartial();
                } else {
                    this.application_ = applicationProto;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(applicationProto);
            }
            return this;
        }

        public Builder mergeClient(ClientProto clientProto) {
            SingleFieldBuilderV3<ClientProto, ClientProto.Builder, ClientProtoOrBuilder> singleFieldBuilderV3 = this.clientBuilder_;
            if (singleFieldBuilderV3 == null) {
                ClientProto clientProto2 = this.client_;
                if (clientProto2 != null) {
                    this.client_ = ClientProto.newBuilder(clientProto2).mergeFrom(clientProto).mo10085buildPartial();
                } else {
                    this.client_ = clientProto;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(clientProto);
            }
            return this;
        }

        public Builder mergeCustomer(CustomerProto customerProto) {
            SingleFieldBuilderV3<CustomerProto, CustomerProto.Builder, CustomerProtoOrBuilder> singleFieldBuilderV3 = this.customerBuilder_;
            if (singleFieldBuilderV3 == null) {
                CustomerProto customerProto2 = this.customer_;
                if (customerProto2 != null) {
                    this.customer_ = CustomerProto.newBuilder(customerProto2).mergeFrom(customerProto).mo10085buildPartial();
                } else {
                    this.customer_ = customerProto;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(customerProto);
            }
            return this;
        }

        public Builder mergeDevice(DeviceProto deviceProto) {
            SingleFieldBuilderV3<DeviceProto, DeviceProto.Builder, DeviceProtoOrBuilder> singleFieldBuilderV3 = this.deviceBuilder_;
            if (singleFieldBuilderV3 == null) {
                DeviceProto deviceProto2 = this.device_;
                if (deviceProto2 != null) {
                    this.device_ = DeviceProto.newBuilder(deviceProto2).mergeFrom(deviceProto).mo10085buildPartial();
                } else {
                    this.device_ = deviceProto;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(deviceProto);
            }
            return this;
        }

        public Builder mergeEvent(EventProto eventProto) {
            SingleFieldBuilderV3<EventProto, EventProto.Builder, EventProtoOrBuilder> singleFieldBuilderV3 = this.eventBuilder_;
            if (singleFieldBuilderV3 == null) {
                EventProto eventProto2 = this.event_;
                if (eventProto2 != null) {
                    this.event_ = EventProto.newBuilder(eventProto2).mergeFrom(eventProto).mo10085buildPartial();
                } else {
                    this.event_ = eventProto;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(eventProto);
            }
            return this;
        }

        public Builder mergeSession(SessionProto sessionProto) {
            SingleFieldBuilderV3<SessionProto, SessionProto.Builder, SessionProtoOrBuilder> singleFieldBuilderV3 = this.sessionBuilder_;
            if (singleFieldBuilderV3 == null) {
                SessionProto sessionProto2 = this.session_;
                if (sessionProto2 != null) {
                    this.session_ = SessionProto.newBuilder(sessionProto2).mergeFrom(sessionProto).mo10085buildPartial();
                } else {
                    this.session_ = sessionProto;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sessionProto);
            }
            return this;
        }

        public Builder setApplication(ApplicationProto applicationProto) {
            SingleFieldBuilderV3<ApplicationProto, ApplicationProto.Builder, ApplicationProtoOrBuilder> singleFieldBuilderV3 = this.applicationBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(applicationProto);
            } else if (applicationProto != null) {
                this.application_ = applicationProto;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setClient(ClientProto clientProto) {
            SingleFieldBuilderV3<ClientProto, ClientProto.Builder, ClientProtoOrBuilder> singleFieldBuilderV3 = this.clientBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(clientProto);
            } else if (clientProto != null) {
                this.client_ = clientProto;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setCustomer(CustomerProto customerProto) {
            SingleFieldBuilderV3<CustomerProto, CustomerProto.Builder, CustomerProtoOrBuilder> singleFieldBuilderV3 = this.customerBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(customerProto);
            } else if (customerProto != null) {
                this.customer_ = customerProto;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setDevice(DeviceProto deviceProto) {
            SingleFieldBuilderV3<DeviceProto, DeviceProto.Builder, DeviceProtoOrBuilder> singleFieldBuilderV3 = this.deviceBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(deviceProto);
            } else if (deviceProto != null) {
                this.device_ = deviceProto;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setEvent(EventProto eventProto) {
            SingleFieldBuilderV3<EventProto, EventProto.Builder, EventProtoOrBuilder> singleFieldBuilderV3 = this.eventBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(eventProto);
            } else if (eventProto != null) {
                this.event_ = eventProto;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setPivots(String str) {
            if (str != null) {
                this.pivots_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setPivotsBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.pivots_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSession(SessionProto sessionProto) {
            SingleFieldBuilderV3<SessionProto, SessionProto.Builder, SessionProtoOrBuilder> singleFieldBuilderV3 = this.sessionBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sessionProto);
            } else if (sessionProto != null) {
                this.session_ = sessionProto;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        private Builder() {
            this.customer_ = null;
            this.pivots_ = "";
            this.client_ = null;
            this.application_ = null;
            this.session_ = null;
            this.device_ = null;
            this.event_ = null;
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public MobilyticsMessageProto mo10084build() {
            MobilyticsMessageProto mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public MobilyticsMessageProto mo10085buildPartial() {
            MobilyticsMessageProto mobilyticsMessageProto = new MobilyticsMessageProto(this);
            SingleFieldBuilderV3<CustomerProto, CustomerProto.Builder, CustomerProtoOrBuilder> singleFieldBuilderV3 = this.customerBuilder_;
            if (singleFieldBuilderV3 == null) {
                mobilyticsMessageProto.customer_ = this.customer_;
            } else {
                mobilyticsMessageProto.customer_ = singleFieldBuilderV3.build();
            }
            mobilyticsMessageProto.pivots_ = this.pivots_;
            SingleFieldBuilderV3<ClientProto, ClientProto.Builder, ClientProtoOrBuilder> singleFieldBuilderV32 = this.clientBuilder_;
            if (singleFieldBuilderV32 == null) {
                mobilyticsMessageProto.client_ = this.client_;
            } else {
                mobilyticsMessageProto.client_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<ApplicationProto, ApplicationProto.Builder, ApplicationProtoOrBuilder> singleFieldBuilderV33 = this.applicationBuilder_;
            if (singleFieldBuilderV33 == null) {
                mobilyticsMessageProto.application_ = this.application_;
            } else {
                mobilyticsMessageProto.application_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<SessionProto, SessionProto.Builder, SessionProtoOrBuilder> singleFieldBuilderV34 = this.sessionBuilder_;
            if (singleFieldBuilderV34 == null) {
                mobilyticsMessageProto.session_ = this.session_;
            } else {
                mobilyticsMessageProto.session_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<DeviceProto, DeviceProto.Builder, DeviceProtoOrBuilder> singleFieldBuilderV35 = this.deviceBuilder_;
            if (singleFieldBuilderV35 == null) {
                mobilyticsMessageProto.device_ = this.device_;
            } else {
                mobilyticsMessageProto.device_ = singleFieldBuilderV35.build();
            }
            SingleFieldBuilderV3<EventProto, EventProto.Builder, EventProtoOrBuilder> singleFieldBuilderV36 = this.eventBuilder_;
            if (singleFieldBuilderV36 == null) {
                mobilyticsMessageProto.event_ = this.event_;
            } else {
                mobilyticsMessageProto.event_ = singleFieldBuilderV36.build();
            }
            onBuilt();
            return mobilyticsMessageProto;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public MobilyticsMessageProto mo10094getDefaultInstanceForType() {
            return MobilyticsMessageProto.getDefaultInstance();
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
            if (this.customerBuilder_ == null) {
                this.customer_ = null;
            } else {
                this.customer_ = null;
                this.customerBuilder_ = null;
            }
            this.pivots_ = "";
            if (this.clientBuilder_ == null) {
                this.client_ = null;
            } else {
                this.client_ = null;
                this.clientBuilder_ = null;
            }
            if (this.applicationBuilder_ == null) {
                this.application_ = null;
            } else {
                this.application_ = null;
                this.applicationBuilder_ = null;
            }
            if (this.sessionBuilder_ == null) {
                this.session_ = null;
            } else {
                this.session_ = null;
                this.sessionBuilder_ = null;
            }
            if (this.deviceBuilder_ == null) {
                this.device_ = null;
            } else {
                this.device_ = null;
                this.deviceBuilder_ = null;
            }
            if (this.eventBuilder_ == null) {
                this.event_ = null;
            } else {
                this.event_ = null;
                this.eventBuilder_ = null;
            }
            return this;
        }

        public Builder setApplication(ApplicationProto.Builder builder) {
            SingleFieldBuilderV3<ApplicationProto, ApplicationProto.Builder, ApplicationProtoOrBuilder> singleFieldBuilderV3 = this.applicationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.application_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder setClient(ClientProto.Builder builder) {
            SingleFieldBuilderV3<ClientProto, ClientProto.Builder, ClientProtoOrBuilder> singleFieldBuilderV3 = this.clientBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.client_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder setCustomer(CustomerProto.Builder builder) {
            SingleFieldBuilderV3<CustomerProto, CustomerProto.Builder, CustomerProtoOrBuilder> singleFieldBuilderV3 = this.customerBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.customer_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder setDevice(DeviceProto.Builder builder) {
            SingleFieldBuilderV3<DeviceProto, DeviceProto.Builder, DeviceProtoOrBuilder> singleFieldBuilderV3 = this.deviceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.device_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder setEvent(EventProto.Builder builder) {
            SingleFieldBuilderV3<EventProto, EventProto.Builder, EventProtoOrBuilder> singleFieldBuilderV3 = this.eventBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.event_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder setSession(SessionProto.Builder builder) {
            SingleFieldBuilderV3<SessionProto, SessionProto.Builder, SessionProtoOrBuilder> singleFieldBuilderV3 = this.sessionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.session_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
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
            if (message instanceof MobilyticsMessageProto) {
                return mergeFrom((MobilyticsMessageProto) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MobilyticsMessageProto mobilyticsMessageProto) {
            if (mobilyticsMessageProto == MobilyticsMessageProto.getDefaultInstance()) {
                return this;
            }
            if (mobilyticsMessageProto.hasCustomer()) {
                mergeCustomer(mobilyticsMessageProto.getCustomer());
            }
            if (!mobilyticsMessageProto.getPivots().isEmpty()) {
                this.pivots_ = mobilyticsMessageProto.pivots_;
                onChanged();
            }
            if (mobilyticsMessageProto.hasClient()) {
                mergeClient(mobilyticsMessageProto.getClient());
            }
            if (mobilyticsMessageProto.hasApplication()) {
                mergeApplication(mobilyticsMessageProto.getApplication());
            }
            if (mobilyticsMessageProto.hasSession()) {
                mergeSession(mobilyticsMessageProto.getSession());
            }
            if (mobilyticsMessageProto.hasDevice()) {
                mergeDevice(mobilyticsMessageProto.getDevice());
            }
            if (mobilyticsMessageProto.hasEvent()) {
                mergeEvent(mobilyticsMessageProto.getEvent());
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) mobilyticsMessageProto).unknownFields);
            onChanged();
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.customer_ = null;
            this.pivots_ = "";
            this.client_ = null;
            this.application_ = null;
            this.session_ = null;
            this.device_ = null;
            this.event_ = null;
            maybeForceBuilderInitialization();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto r3 = (com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto r4 = (com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto$Builder");
        }
    }

    public static Builder newBuilder(MobilyticsMessageProto mobilyticsMessageProto) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(mobilyticsMessageProto);
    }

    public static MobilyticsMessageProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private MobilyticsMessageProto(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static MobilyticsMessageProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MobilyticsMessageProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MobilyticsMessageProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public MobilyticsMessageProto mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static MobilyticsMessageProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private MobilyticsMessageProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.pivots_ = "";
    }

    public static MobilyticsMessageProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static MobilyticsMessageProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static MobilyticsMessageProto parseFrom(InputStream inputStream) throws IOException {
        return (MobilyticsMessageProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    private MobilyticsMessageProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            EventProto.Builder builder = null;
                            CustomerProto.Builder mo10081toBuilder = null;
                            ClientProto.Builder mo10081toBuilder2 = null;
                            ApplicationProto.Builder mo10081toBuilder3 = null;
                            SessionProto.Builder mo10081toBuilder4 = null;
                            DeviceProto.Builder mo10081toBuilder5 = null;
                            if (readTag == 10) {
                                mo10081toBuilder = this.customer_ != null ? this.customer_.mo10081toBuilder() : mo10081toBuilder;
                                this.customer_ = (CustomerProto) codedInputStream.readMessage(CustomerProto.parser(), extensionRegistryLite);
                                if (mo10081toBuilder != null) {
                                    mo10081toBuilder.mergeFrom(this.customer_);
                                    this.customer_ = mo10081toBuilder.mo10085buildPartial();
                                }
                            } else if (readTag == 18) {
                                this.pivots_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                mo10081toBuilder2 = this.client_ != null ? this.client_.mo10081toBuilder() : mo10081toBuilder2;
                                this.client_ = (ClientProto) codedInputStream.readMessage(ClientProto.parser(), extensionRegistryLite);
                                if (mo10081toBuilder2 != null) {
                                    mo10081toBuilder2.mergeFrom(this.client_);
                                    this.client_ = mo10081toBuilder2.mo10085buildPartial();
                                }
                            } else if (readTag == 34) {
                                mo10081toBuilder3 = this.application_ != null ? this.application_.mo10081toBuilder() : mo10081toBuilder3;
                                this.application_ = (ApplicationProto) codedInputStream.readMessage(ApplicationProto.parser(), extensionRegistryLite);
                                if (mo10081toBuilder3 != null) {
                                    mo10081toBuilder3.mergeFrom(this.application_);
                                    this.application_ = mo10081toBuilder3.mo10085buildPartial();
                                }
                            } else if (readTag == 42) {
                                mo10081toBuilder4 = this.session_ != null ? this.session_.mo10081toBuilder() : mo10081toBuilder4;
                                this.session_ = (SessionProto) codedInputStream.readMessage(SessionProto.parser(), extensionRegistryLite);
                                if (mo10081toBuilder4 != null) {
                                    mo10081toBuilder4.mergeFrom(this.session_);
                                    this.session_ = mo10081toBuilder4.mo10085buildPartial();
                                }
                            } else if (readTag == 50) {
                                mo10081toBuilder5 = this.device_ != null ? this.device_.mo10081toBuilder() : mo10081toBuilder5;
                                this.device_ = (DeviceProto) codedInputStream.readMessage(DeviceProto.parser(), extensionRegistryLite);
                                if (mo10081toBuilder5 != null) {
                                    mo10081toBuilder5.mergeFrom(this.device_);
                                    this.device_ = mo10081toBuilder5.mo10085buildPartial();
                                }
                            } else if (readTag != 58) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                builder = this.event_ != null ? this.event_.mo10081toBuilder() : builder;
                                this.event_ = (EventProto) codedInputStream.readMessage(EventProto.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.event_);
                                    this.event_ = builder.mo10085buildPartial();
                                }
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

    public static MobilyticsMessageProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MobilyticsMessageProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MobilyticsMessageProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MobilyticsMessageProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MobilyticsMessageProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MobilyticsMessageProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
