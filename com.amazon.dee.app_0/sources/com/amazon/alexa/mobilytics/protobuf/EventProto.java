package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes9.dex */
public final class EventProto extends GeneratedMessageV3 implements EventProtoOrBuilder {
    public static final int APPLICATIONFOREGROUNDED_FIELD_NUMBER = 5;
    public static final int DEBUGURL_FIELD_NUMBER = 7;
    public static final int EVENTDETAILS_FIELD_NUMBER = 6;
    public static final int EVENTID_FIELD_NUMBER = 2;
    public static final int EVENTTIMESTAMP_FIELD_NUMBER = 4;
    public static final int EVENTTYPE_FIELD_NUMBER = 1;
    public static final int SOURCENAME_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private boolean applicationForegrounded_;
    private volatile Object debugUrl_;
    private EventDetailsProto eventDetails_;
    private volatile Object eventId_;
    private long eventTimestamp_;
    private volatile Object eventType_;
    private byte memoizedIsInitialized;
    private volatile Object sourceName_;
    private static final EventProto DEFAULT_INSTANCE = new EventProto();
    private static final Parser<EventProto> PARSER = new AbstractParser<EventProto>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventProto.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public EventProto mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new EventProto(codedInputStream, extensionRegistryLite);
        }
    };

    public static EventProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EventProtoOuterClass.internal_static_protobuf_EventProto_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static EventProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EventProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static EventProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<EventProto> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventProto)) {
            return super.equals(obj);
        }
        EventProto eventProto = (EventProto) obj;
        boolean z = (((((getEventType().equals(eventProto.getEventType())) && getEventId().equals(eventProto.getEventId())) && getSourceName().equals(eventProto.getSourceName())) && (getEventTimestamp() > eventProto.getEventTimestamp() ? 1 : (getEventTimestamp() == eventProto.getEventTimestamp() ? 0 : -1)) == 0) && getApplicationForegrounded() == eventProto.getApplicationForegrounded()) && hasEventDetails() == eventProto.hasEventDetails();
        if (hasEventDetails()) {
            z = z && getEventDetails().equals(eventProto.getEventDetails());
        }
        return (z && getDebugUrl().equals(eventProto.getDebugUrl())) && this.unknownFields.equals(eventProto.unknownFields);
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public boolean getApplicationForegrounded() {
        return this.applicationForegrounded_;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public String getDebugUrl() {
        Object obj = this.debugUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.debugUrl_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public ByteString getDebugUrlBytes() {
        Object obj = this.debugUrl_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.debugUrl_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public EventDetailsProto getEventDetails() {
        EventDetailsProto eventDetailsProto = this.eventDetails_;
        return eventDetailsProto == null ? EventDetailsProto.getDefaultInstance() : eventDetailsProto;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public EventDetailsProtoOrBuilder getEventDetailsOrBuilder() {
        return getEventDetails();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public String getEventId() {
        Object obj = this.eventId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.eventId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public ByteString getEventIdBytes() {
        Object obj = this.eventId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.eventId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public long getEventTimestamp() {
        return this.eventTimestamp_;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public String getEventType() {
        Object obj = this.eventType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.eventType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public ByteString getEventTypeBytes() {
        Object obj = this.eventType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.eventType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<EventProto> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getEventTypeBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.eventType_);
        }
        if (!getEventIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.eventId_);
        }
        if (!getSourceNameBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.sourceName_);
        }
        long j = this.eventTimestamp_;
        if (j != 0) {
            i2 += CodedOutputStream.computeInt64Size(4, j);
        }
        boolean z = this.applicationForegrounded_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(5, z);
        }
        if (this.eventDetails_ != null) {
            i2 += CodedOutputStream.computeMessageSize(6, getEventDetails());
        }
        if (!getDebugUrlBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(7, this.debugUrl_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public String getSourceName() {
        Object obj = this.sourceName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.sourceName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public ByteString getSourceNameBytes() {
        Object obj = this.sourceName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.sourceName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
    public boolean hasEventDetails() {
        return this.eventDetails_ != null;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getEventType().hashCode();
        int hashCode2 = getEventId().hashCode();
        int hashCode3 = getSourceName().hashCode();
        int hashLong = Internal.hashLong(getEventTimestamp());
        int hashBoolean = Internal.hashBoolean(getApplicationForegrounded()) + ((((hashLong + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53);
        if (hasEventDetails()) {
            hashBoolean = getEventDetails().hashCode() + GeneratedOutlineSupport1.outline4(hashBoolean, 37, 6, 53);
        }
        int outline4 = GeneratedOutlineSupport1.outline4(hashBoolean, 37, 7, 53);
        int hashCode4 = this.unknownFields.hashCode() + ((getDebugUrl().hashCode() + outline4) * 29);
        this.memoizedHashCode = hashCode4;
        return hashCode4;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EventProtoOuterClass.internal_static_protobuf_EventProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EventProto.class, Builder.class);
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
        if (!getEventTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.eventType_);
        }
        if (!getEventIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.eventId_);
        }
        if (!getSourceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.sourceName_);
        }
        long j = this.eventTimestamp_;
        if (j != 0) {
            codedOutputStream.writeInt64(4, j);
        }
        boolean z = this.applicationForegrounded_;
        if (z) {
            codedOutputStream.writeBool(5, z);
        }
        if (this.eventDetails_ != null) {
            codedOutputStream.writeMessage(6, getEventDetails());
        }
        if (!getDebugUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.debugUrl_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes9.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EventProtoOrBuilder {
        private boolean applicationForegrounded_;
        private Object debugUrl_;
        private SingleFieldBuilderV3<EventDetailsProto, EventDetailsProto.Builder, EventDetailsProtoOrBuilder> eventDetailsBuilder_;
        private EventDetailsProto eventDetails_;
        private Object eventId_;
        private long eventTimestamp_;
        private Object eventType_;
        private Object sourceName_;

        public static final Descriptors.Descriptor getDescriptor() {
            return EventProtoOuterClass.internal_static_protobuf_EventProto_descriptor;
        }

        private SingleFieldBuilderV3<EventDetailsProto, EventDetailsProto.Builder, EventDetailsProtoOrBuilder> getEventDetailsFieldBuilder() {
            if (this.eventDetailsBuilder_ == null) {
                this.eventDetailsBuilder_ = new SingleFieldBuilderV3<>(getEventDetails(), getParentForChildren(), isClean());
                this.eventDetails_ = null;
            }
            return this.eventDetailsBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearApplicationForegrounded() {
            this.applicationForegrounded_ = false;
            onChanged();
            return this;
        }

        public Builder clearDebugUrl() {
            this.debugUrl_ = EventProto.getDefaultInstance().getDebugUrl();
            onChanged();
            return this;
        }

        public Builder clearEventDetails() {
            if (this.eventDetailsBuilder_ == null) {
                this.eventDetails_ = null;
                onChanged();
            } else {
                this.eventDetails_ = null;
                this.eventDetailsBuilder_ = null;
            }
            return this;
        }

        public Builder clearEventId() {
            this.eventId_ = EventProto.getDefaultInstance().getEventId();
            onChanged();
            return this;
        }

        public Builder clearEventTimestamp() {
            this.eventTimestamp_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearEventType() {
            this.eventType_ = EventProto.getDefaultInstance().getEventType();
            onChanged();
            return this;
        }

        public Builder clearSourceName() {
            this.sourceName_ = EventProto.getDefaultInstance().getSourceName();
            onChanged();
            return this;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public boolean getApplicationForegrounded() {
            return this.applicationForegrounded_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public String getDebugUrl() {
            Object obj = this.debugUrl_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.debugUrl_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public ByteString getDebugUrlBytes() {
            Object obj = this.debugUrl_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.debugUrl_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EventProtoOuterClass.internal_static_protobuf_EventProto_descriptor;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public EventDetailsProto getEventDetails() {
            SingleFieldBuilderV3<EventDetailsProto, EventDetailsProto.Builder, EventDetailsProtoOrBuilder> singleFieldBuilderV3 = this.eventDetailsBuilder_;
            if (singleFieldBuilderV3 == null) {
                EventDetailsProto eventDetailsProto = this.eventDetails_;
                return eventDetailsProto == null ? EventDetailsProto.getDefaultInstance() : eventDetailsProto;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public EventDetailsProto.Builder getEventDetailsBuilder() {
            onChanged();
            return getEventDetailsFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public EventDetailsProtoOrBuilder getEventDetailsOrBuilder() {
            SingleFieldBuilderV3<EventDetailsProto, EventDetailsProto.Builder, EventDetailsProtoOrBuilder> singleFieldBuilderV3 = this.eventDetailsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            EventDetailsProto eventDetailsProto = this.eventDetails_;
            return eventDetailsProto == null ? EventDetailsProto.getDefaultInstance() : eventDetailsProto;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public String getEventId() {
            Object obj = this.eventId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.eventId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public ByteString getEventIdBytes() {
            Object obj = this.eventId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.eventId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public long getEventTimestamp() {
            return this.eventTimestamp_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public String getEventType() {
            Object obj = this.eventType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.eventType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public ByteString getEventTypeBytes() {
            Object obj = this.eventType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.eventType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public String getSourceName() {
            Object obj = this.sourceName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.sourceName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public ByteString getSourceNameBytes() {
            Object obj = this.sourceName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.sourceName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventProtoOrBuilder
        public boolean hasEventDetails() {
            return (this.eventDetailsBuilder_ == null && this.eventDetails_ == null) ? false : true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EventProtoOuterClass.internal_static_protobuf_EventProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EventProto.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeEventDetails(EventDetailsProto eventDetailsProto) {
            SingleFieldBuilderV3<EventDetailsProto, EventDetailsProto.Builder, EventDetailsProtoOrBuilder> singleFieldBuilderV3 = this.eventDetailsBuilder_;
            if (singleFieldBuilderV3 == null) {
                EventDetailsProto eventDetailsProto2 = this.eventDetails_;
                if (eventDetailsProto2 != null) {
                    this.eventDetails_ = EventDetailsProto.newBuilder(eventDetailsProto2).mergeFrom(eventDetailsProto).mo10085buildPartial();
                } else {
                    this.eventDetails_ = eventDetailsProto;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(eventDetailsProto);
            }
            return this;
        }

        public Builder setApplicationForegrounded(boolean z) {
            this.applicationForegrounded_ = z;
            onChanged();
            return this;
        }

        public Builder setDebugUrl(String str) {
            if (str != null) {
                this.debugUrl_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setDebugUrlBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.debugUrl_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setEventDetails(EventDetailsProto eventDetailsProto) {
            SingleFieldBuilderV3<EventDetailsProto, EventDetailsProto.Builder, EventDetailsProtoOrBuilder> singleFieldBuilderV3 = this.eventDetailsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(eventDetailsProto);
            } else if (eventDetailsProto != null) {
                this.eventDetails_ = eventDetailsProto;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setEventId(String str) {
            if (str != null) {
                this.eventId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setEventIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.eventId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setEventTimestamp(long j) {
            this.eventTimestamp_ = j;
            onChanged();
            return this;
        }

        public Builder setEventType(String str) {
            if (str != null) {
                this.eventType_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setEventTypeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.eventType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSourceName(String str) {
            if (str != null) {
                this.sourceName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSourceNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.sourceName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private Builder() {
            this.eventType_ = "";
            this.eventId_ = "";
            this.sourceName_ = "";
            this.eventDetails_ = null;
            this.debugUrl_ = "";
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public EventProto mo10084build() {
            EventProto mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public EventProto mo10085buildPartial() {
            EventProto eventProto = new EventProto(this);
            eventProto.eventType_ = this.eventType_;
            eventProto.eventId_ = this.eventId_;
            eventProto.sourceName_ = this.sourceName_;
            eventProto.eventTimestamp_ = this.eventTimestamp_;
            eventProto.applicationForegrounded_ = this.applicationForegrounded_;
            SingleFieldBuilderV3<EventDetailsProto, EventDetailsProto.Builder, EventDetailsProtoOrBuilder> singleFieldBuilderV3 = this.eventDetailsBuilder_;
            if (singleFieldBuilderV3 == null) {
                eventProto.eventDetails_ = this.eventDetails_;
            } else {
                eventProto.eventDetails_ = singleFieldBuilderV3.build();
            }
            eventProto.debugUrl_ = this.debugUrl_;
            onBuilt();
            return eventProto;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public EventProto mo10094getDefaultInstanceForType() {
            return EventProto.getDefaultInstance();
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
            this.eventType_ = "";
            this.eventId_ = "";
            this.sourceName_ = "";
            this.eventTimestamp_ = 0L;
            this.applicationForegrounded_ = false;
            if (this.eventDetailsBuilder_ == null) {
                this.eventDetails_ = null;
            } else {
                this.eventDetails_ = null;
                this.eventDetailsBuilder_ = null;
            }
            this.debugUrl_ = "";
            return this;
        }

        public Builder setEventDetails(EventDetailsProto.Builder builder) {
            SingleFieldBuilderV3<EventDetailsProto, EventDetailsProto.Builder, EventDetailsProtoOrBuilder> singleFieldBuilderV3 = this.eventDetailsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.eventDetails_ = builder.mo10084build();
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
            if (message instanceof EventProto) {
                return mergeFrom((EventProto) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.eventType_ = "";
            this.eventId_ = "";
            this.sourceName_ = "";
            this.eventDetails_ = null;
            this.debugUrl_ = "";
            maybeForceBuilderInitialization();
        }

        public Builder mergeFrom(EventProto eventProto) {
            if (eventProto == EventProto.getDefaultInstance()) {
                return this;
            }
            if (!eventProto.getEventType().isEmpty()) {
                this.eventType_ = eventProto.eventType_;
                onChanged();
            }
            if (!eventProto.getEventId().isEmpty()) {
                this.eventId_ = eventProto.eventId_;
                onChanged();
            }
            if (!eventProto.getSourceName().isEmpty()) {
                this.sourceName_ = eventProto.sourceName_;
                onChanged();
            }
            if (eventProto.getEventTimestamp() != 0) {
                setEventTimestamp(eventProto.getEventTimestamp());
            }
            if (eventProto.getApplicationForegrounded()) {
                setApplicationForegrounded(eventProto.getApplicationForegrounded());
            }
            if (eventProto.hasEventDetails()) {
                mergeEventDetails(eventProto.getEventDetails());
            }
            if (!eventProto.getDebugUrl().isEmpty()) {
                this.debugUrl_ = eventProto.debugUrl_;
                onChanged();
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) eventProto).unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.amazon.alexa.mobilytics.protobuf.EventProto.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventProto.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.alexa.mobilytics.protobuf.EventProto r3 = (com.amazon.alexa.mobilytics.protobuf.EventProto) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.alexa.mobilytics.protobuf.EventProto r4 = (com.amazon.alexa.mobilytics.protobuf.EventProto) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventProto.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventProto$Builder");
        }
    }

    public static Builder newBuilder(EventProto eventProto) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(eventProto);
    }

    public static EventProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private EventProto(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static EventProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EventProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EventProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public EventProto mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static EventProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private EventProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.eventType_ = "";
        this.eventId_ = "";
        this.sourceName_ = "";
        this.eventTimestamp_ = 0L;
        this.applicationForegrounded_ = false;
        this.debugUrl_ = "";
    }

    public static EventProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static EventProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static EventProto parseFrom(InputStream inputStream) throws IOException {
        return (EventProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static EventProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EventProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EventProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EventProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    private EventProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.eventType_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.eventId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.sourceName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 32) {
                                this.eventTimestamp_ = codedInputStream.readInt64();
                            } else if (readTag == 40) {
                                this.applicationForegrounded_ = codedInputStream.readBool();
                            } else if (readTag == 50) {
                                EventDetailsProto.Builder mo10081toBuilder = this.eventDetails_ != null ? this.eventDetails_.mo10081toBuilder() : null;
                                this.eventDetails_ = (EventDetailsProto) codedInputStream.readMessage(EventDetailsProto.parser(), extensionRegistryLite);
                                if (mo10081toBuilder != null) {
                                    mo10081toBuilder.mergeFrom(this.eventDetails_);
                                    this.eventDetails_ = mo10081toBuilder.mo10085buildPartial();
                                }
                            } else if (readTag != 58) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.debugUrl_ = codedInputStream.readStringRequireUtf8();
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

    public static EventProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EventProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
