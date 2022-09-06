package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
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
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;
/* loaded from: classes9.dex */
public final class EventDetailsProto extends GeneratedMessageV3 implements EventDetailsProtoOrBuilder {
    public static final int ABORTREASON_FIELD_NUMBER = 23;
    public static final int ACTIVEDURATION_FIELD_NUMBER = 26;
    public static final int APPCOMPONENT_FIELD_NUMBER = 5;
    public static final int CHANNELNAME_FIELD_NUMBER = 4;
    public static final int CONTENTID_FIELD_NUMBER = 8;
    public static final int CONTENTPROVIDER_FIELD_NUMBER = 9;
    public static final int CONTENTTYPE_FIELD_NUMBER = 10;
    public static final int CONTENTVERSION_FIELD_NUMBER = 11;
    public static final int DEBUGINFO_FIELD_NUMBER = 32;
    public static final int ENDTIMESTAMP_FIELD_NUMBER = 25;
    public static final int ERRORLEVEL_FIELD_NUMBER = 29;
    public static final int ERRORSHORTMSG_FIELD_NUMBER = 31;
    public static final int ERRORTITLE_FIELD_NUMBER = 30;
    public static final int EVENTCOUNT_FIELD_NUMBER = 28;
    public static final int EVENTNAME_FIELD_NUMBER = 1;
    public static final int EVENTNUMERICVALUE_FIELD_NUMBER = 7;
    public static final int INPUTTYPE_FIELD_NUMBER = 12;
    public static final int INTERACTIONDETAILS_FIELD_NUMBER = 14;
    public static final int INTERACTIONTYPE_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 15;
    public static final int OPERATIONALEVENTTYPE_FIELD_NUMBER = 3;
    public static final int OWNERIDENTIFIER_FIELD_NUMBER = 33;
    public static final int REFMARKER_FIELD_NUMBER = 16;
    public static final int SOURCECONTEXT_FIELD_NUMBER = 13;
    public static final int SPEAKERID_FIELD_NUMBER = 17;
    public static final int STARTTIMESTAMP_FIELD_NUMBER = 24;
    public static final int SUBCOMPONENT_FIELD_NUMBER = 6;
    public static final int TIMELINEELAPSEDDURATION_FIELD_NUMBER = 22;
    public static final int TIMELINEID_FIELD_NUMBER = 18;
    public static final int TIMELINENAMESPACE_FIELD_NUMBER = 20;
    public static final int TIMELINENAME_FIELD_NUMBER = 19;
    public static final int TIMELINESTATE_FIELD_NUMBER = 21;
    public static final int TOTALDURATION_FIELD_NUMBER = 27;
    private static final long serialVersionUID = 0;
    private volatile Object abortReason_;
    private double activeDuration_;
    private volatile Object appComponent_;
    private int bitField0_;
    private volatile Object channelName_;
    private volatile Object contentId_;
    private volatile Object contentProvider_;
    private volatile Object contentType_;
    private volatile Object contentVersion_;
    private MapField<String, String> debugInfo_;
    private long endTimestamp_;
    private volatile Object errorLevel_;
    private volatile Object errorShortMsg_;
    private volatile Object errorTitle_;
    private long eventCount_;
    private volatile Object eventName_;
    private long eventNumericValue_;
    private volatile Object inputType_;
    private InteractionDetails interactionDetails_;
    private volatile Object interactionType_;
    private byte memoizedIsInitialized;
    private Metadata metadata_;
    private volatile Object operationalEventType_;
    private volatile Object ownerIdentifier_;
    private volatile Object refMarker_;
    private volatile Object sourceContext_;
    private volatile Object speakerId_;
    private long startTimestamp_;
    private volatile Object subComponent_;
    private double timelineElapsedDuration_;
    private volatile Object timelineId_;
    private volatile Object timelineName_;
    private volatile Object timelineNamespace_;
    private volatile Object timelineState_;
    private double totalDuration_;
    private static final EventDetailsProto DEFAULT_INSTANCE = new EventDetailsProto();
    private static final Parser<EventDetailsProto> PARSER = new AbstractParser<EventDetailsProto>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public EventDetailsProto mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new EventDetailsProto(codedInputStream, extensionRegistryLite);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class DebugInfoDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry;

        static {
            Descriptors.Descriptor descriptor = EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_DebugInfoEntry_descriptor;
            WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
            defaultEntry = MapEntry.newDefaultInstance(descriptor, fieldType, "", fieldType, "");
        }

        private DebugInfoDefaultEntryHolder() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class InteractionDetails extends GeneratedMessageV3 implements InteractionDetailsOrBuilder {
        public static final int ACTIONTYPE_FIELD_NUMBER = 1;
        public static final int DESTINATIONAPP_FIELD_NUMBER = 2;
        public static final int DESTINATIONSCREEN_FIELD_NUMBER = 3;
        public static final int DURATION_FIELD_NUMBER = 8;
        public static final int ELEMENTTYPE_FIELD_NUMBER = 4;
        public static final int ENDPOSITION_FIELD_NUMBER = 14;
        public static final int INDEX_FIELD_NUMBER = 5;
        public static final int INTERACTIONTYPE_FIELD_NUMBER = 6;
        public static final int REFERRALDETAILS_FIELD_NUMBER = 11;
        public static final int REFERRALSOURCE_FIELD_NUMBER = 9;
        public static final int REFERRALTYPE_FIELD_NUMBER = 10;
        public static final int STARTPOSITION_FIELD_NUMBER = 13;
        public static final int TOTALNUMBEROFITEMS_FIELD_NUMBER = 7;
        public static final int UTTERANCEID_FIELD_NUMBER = 12;
        private static final long serialVersionUID = 0;
        private volatile Object actionType_;
        private volatile Object destinationApp_;
        private volatile Object destinationScreen_;
        private double duration_;
        private volatile Object elementType_;
        private long endPosition_;
        private long index_;
        private volatile Object interactionType_;
        private byte memoizedIsInitialized;
        private volatile Object referralDetails_;
        private volatile Object referralSource_;
        private volatile Object referralType_;
        private long startPosition_;
        private long totalNumberOfItems_;
        private volatile Object utteranceID_;
        private static final InteractionDetails DEFAULT_INSTANCE = new InteractionDetails();
        private static final Parser<InteractionDetails> PARSER = new AbstractParser<InteractionDetails>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetails.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public InteractionDetails mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new InteractionDetails(codedInputStream, extensionRegistryLite);
            }
        };

        public static InteractionDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_InteractionDetails_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static InteractionDetails parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (InteractionDetails) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static InteractionDetails parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<InteractionDetails> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof InteractionDetails)) {
                return super.equals(obj);
            }
            InteractionDetails interactionDetails = (InteractionDetails) obj;
            return ((((((((((((((getActionType().equals(interactionDetails.getActionType())) && getDestinationApp().equals(interactionDetails.getDestinationApp())) && getDestinationScreen().equals(interactionDetails.getDestinationScreen())) && getElementType().equals(interactionDetails.getElementType())) && (getIndex() > interactionDetails.getIndex() ? 1 : (getIndex() == interactionDetails.getIndex() ? 0 : -1)) == 0) && getInteractionType().equals(interactionDetails.getInteractionType())) && (getTotalNumberOfItems() > interactionDetails.getTotalNumberOfItems() ? 1 : (getTotalNumberOfItems() == interactionDetails.getTotalNumberOfItems() ? 0 : -1)) == 0) && (Double.doubleToLongBits(getDuration()) > Double.doubleToLongBits(interactionDetails.getDuration()) ? 1 : (Double.doubleToLongBits(getDuration()) == Double.doubleToLongBits(interactionDetails.getDuration()) ? 0 : -1)) == 0) && getReferralSource().equals(interactionDetails.getReferralSource())) && getReferralType().equals(interactionDetails.getReferralType())) && getReferralDetails().equals(interactionDetails.getReferralDetails())) && getUtteranceID().equals(interactionDetails.getUtteranceID())) && (getStartPosition() > interactionDetails.getStartPosition() ? 1 : (getStartPosition() == interactionDetails.getStartPosition() ? 0 : -1)) == 0) && (getEndPosition() > interactionDetails.getEndPosition() ? 1 : (getEndPosition() == interactionDetails.getEndPosition() ? 0 : -1)) == 0) && this.unknownFields.equals(interactionDetails.unknownFields);
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public String getActionType() {
            Object obj = this.actionType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.actionType_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public ByteString getActionTypeBytes() {
            Object obj = this.actionType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.actionType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public String getDestinationApp() {
            Object obj = this.destinationApp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.destinationApp_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public ByteString getDestinationAppBytes() {
            Object obj = this.destinationApp_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.destinationApp_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public String getDestinationScreen() {
            Object obj = this.destinationScreen_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.destinationScreen_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public ByteString getDestinationScreenBytes() {
            Object obj = this.destinationScreen_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.destinationScreen_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public double getDuration() {
            return this.duration_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public String getElementType() {
            Object obj = this.elementType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.elementType_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public ByteString getElementTypeBytes() {
            Object obj = this.elementType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.elementType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public long getEndPosition() {
            return this.endPosition_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public long getIndex() {
            return this.index_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public String getInteractionType() {
            Object obj = this.interactionType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.interactionType_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public ByteString getInteractionTypeBytes() {
            Object obj = this.interactionType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.interactionType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<InteractionDetails> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public String getReferralDetails() {
            Object obj = this.referralDetails_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.referralDetails_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public ByteString getReferralDetailsBytes() {
            Object obj = this.referralDetails_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.referralDetails_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public String getReferralSource() {
            Object obj = this.referralSource_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.referralSource_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public ByteString getReferralSourceBytes() {
            Object obj = this.referralSource_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.referralSource_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public String getReferralType() {
            Object obj = this.referralType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.referralType_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public ByteString getReferralTypeBytes() {
            Object obj = this.referralType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.referralType_ = copyFromUtf8;
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
            if (!getActionTypeBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.actionType_);
            }
            if (!getDestinationAppBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.destinationApp_);
            }
            if (!getDestinationScreenBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(3, this.destinationScreen_);
            }
            if (!getElementTypeBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(4, this.elementType_);
            }
            long j = this.index_;
            if (j != 0) {
                i2 += CodedOutputStream.computeInt64Size(5, j);
            }
            if (!getInteractionTypeBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(6, this.interactionType_);
            }
            long j2 = this.totalNumberOfItems_;
            if (j2 != 0) {
                i2 += CodedOutputStream.computeInt64Size(7, j2);
            }
            double d = this.duration_;
            if (d != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                i2 += CodedOutputStream.computeDoubleSize(8, d);
            }
            if (!getReferralSourceBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(9, this.referralSource_);
            }
            if (!getReferralTypeBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(10, this.referralType_);
            }
            if (!getReferralDetailsBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(11, this.referralDetails_);
            }
            if (!getUtteranceIDBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(12, this.utteranceID_);
            }
            long j3 = this.startPosition_;
            if (j3 != 0) {
                i2 += CodedOutputStream.computeInt64Size(13, j3);
            }
            long j4 = this.endPosition_;
            if (j4 != 0) {
                i2 += CodedOutputStream.computeInt64Size(14, j4);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public long getStartPosition() {
            return this.startPosition_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public long getTotalNumberOfItems() {
            return this.totalNumberOfItems_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public String getUtteranceID() {
            Object obj = this.utteranceID_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.utteranceID_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
        public ByteString getUtteranceIDBytes() {
            Object obj = this.utteranceID_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.utteranceID_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getActionType().hashCode();
            int hashCode2 = getDestinationApp().hashCode();
            int hashCode3 = getDestinationScreen().hashCode();
            int hashCode4 = getElementType().hashCode();
            int hashLong = Internal.hashLong(getIndex());
            int hashCode5 = getInteractionType().hashCode();
            int hashLong2 = Internal.hashLong(getTotalNumberOfItems());
            int hashLong3 = Internal.hashLong(Double.doubleToLongBits(getDuration()));
            int hashCode6 = getReferralSource().hashCode();
            int hashCode7 = getReferralType().hashCode();
            int hashCode8 = getReferralDetails().hashCode();
            int hashCode9 = getUtteranceID().hashCode();
            int hashLong4 = Internal.hashLong(getStartPosition());
            int hashLong5 = Internal.hashLong(getEndPosition());
            int hashCode10 = this.unknownFields.hashCode() + ((hashLong5 + ((((hashLong4 + ((((hashCode9 + ((((hashCode8 + ((((hashCode7 + ((((hashCode6 + ((((hashLong3 + ((((hashLong2 + ((((hashCode5 + ((((hashLong + ((((hashCode4 + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 37) + 6) * 53)) * 37) + 7) * 53)) * 37) + 8) * 53)) * 37) + 9) * 53)) * 37) + 10) * 53)) * 37) + 11) * 53)) * 37) + 12) * 53)) * 37) + 13) * 53)) * 37) + 14) * 53)) * 29);
            this.memoizedHashCode = hashCode10;
            return hashCode10;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_InteractionDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(InteractionDetails.class, Builder.class);
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
            if (!getActionTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.actionType_);
            }
            if (!getDestinationAppBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.destinationApp_);
            }
            if (!getDestinationScreenBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.destinationScreen_);
            }
            if (!getElementTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.elementType_);
            }
            long j = this.index_;
            if (j != 0) {
                codedOutputStream.writeInt64(5, j);
            }
            if (!getInteractionTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.interactionType_);
            }
            long j2 = this.totalNumberOfItems_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(7, j2);
            }
            double d = this.duration_;
            if (d != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                codedOutputStream.writeDouble(8, d);
            }
            if (!getReferralSourceBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 9, this.referralSource_);
            }
            if (!getReferralTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 10, this.referralType_);
            }
            if (!getReferralDetailsBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 11, this.referralDetails_);
            }
            if (!getUtteranceIDBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 12, this.utteranceID_);
            }
            long j3 = this.startPosition_;
            if (j3 != 0) {
                codedOutputStream.writeInt64(13, j3);
            }
            long j4 = this.endPosition_;
            if (j4 != 0) {
                codedOutputStream.writeInt64(14, j4);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes9.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements InteractionDetailsOrBuilder {
            private Object actionType_;
            private Object destinationApp_;
            private Object destinationScreen_;
            private double duration_;
            private Object elementType_;
            private long endPosition_;
            private long index_;
            private Object interactionType_;
            private Object referralDetails_;
            private Object referralSource_;
            private Object referralType_;
            private long startPosition_;
            private long totalNumberOfItems_;
            private Object utteranceID_;

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_InteractionDetails_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearActionType() {
                this.actionType_ = InteractionDetails.getDefaultInstance().getActionType();
                onChanged();
                return this;
            }

            public Builder clearDestinationApp() {
                this.destinationApp_ = InteractionDetails.getDefaultInstance().getDestinationApp();
                onChanged();
                return this;
            }

            public Builder clearDestinationScreen() {
                this.destinationScreen_ = InteractionDetails.getDefaultInstance().getDestinationScreen();
                onChanged();
                return this;
            }

            public Builder clearDuration() {
                this.duration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                onChanged();
                return this;
            }

            public Builder clearElementType() {
                this.elementType_ = InteractionDetails.getDefaultInstance().getElementType();
                onChanged();
                return this;
            }

            public Builder clearEndPosition() {
                this.endPosition_ = 0L;
                onChanged();
                return this;
            }

            public Builder clearIndex() {
                this.index_ = 0L;
                onChanged();
                return this;
            }

            public Builder clearInteractionType() {
                this.interactionType_ = InteractionDetails.getDefaultInstance().getInteractionType();
                onChanged();
                return this;
            }

            public Builder clearReferralDetails() {
                this.referralDetails_ = InteractionDetails.getDefaultInstance().getReferralDetails();
                onChanged();
                return this;
            }

            public Builder clearReferralSource() {
                this.referralSource_ = InteractionDetails.getDefaultInstance().getReferralSource();
                onChanged();
                return this;
            }

            public Builder clearReferralType() {
                this.referralType_ = InteractionDetails.getDefaultInstance().getReferralType();
                onChanged();
                return this;
            }

            public Builder clearStartPosition() {
                this.startPosition_ = 0L;
                onChanged();
                return this;
            }

            public Builder clearTotalNumberOfItems() {
                this.totalNumberOfItems_ = 0L;
                onChanged();
                return this;
            }

            public Builder clearUtteranceID() {
                this.utteranceID_ = InteractionDetails.getDefaultInstance().getUtteranceID();
                onChanged();
                return this;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public String getActionType() {
                Object obj = this.actionType_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.actionType_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public ByteString getActionTypeBytes() {
                Object obj = this.actionType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.actionType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_InteractionDetails_descriptor;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public String getDestinationApp() {
                Object obj = this.destinationApp_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.destinationApp_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public ByteString getDestinationAppBytes() {
                Object obj = this.destinationApp_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.destinationApp_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public String getDestinationScreen() {
                Object obj = this.destinationScreen_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.destinationScreen_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public ByteString getDestinationScreenBytes() {
                Object obj = this.destinationScreen_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.destinationScreen_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public double getDuration() {
                return this.duration_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public String getElementType() {
                Object obj = this.elementType_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.elementType_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public ByteString getElementTypeBytes() {
                Object obj = this.elementType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.elementType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public long getEndPosition() {
                return this.endPosition_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public long getIndex() {
                return this.index_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public String getInteractionType() {
                Object obj = this.interactionType_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.interactionType_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public ByteString getInteractionTypeBytes() {
                Object obj = this.interactionType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.interactionType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public String getReferralDetails() {
                Object obj = this.referralDetails_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.referralDetails_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public ByteString getReferralDetailsBytes() {
                Object obj = this.referralDetails_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.referralDetails_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public String getReferralSource() {
                Object obj = this.referralSource_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.referralSource_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public ByteString getReferralSourceBytes() {
                Object obj = this.referralSource_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.referralSource_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public String getReferralType() {
                Object obj = this.referralType_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.referralType_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public ByteString getReferralTypeBytes() {
                Object obj = this.referralType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.referralType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public long getStartPosition() {
                return this.startPosition_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public long getTotalNumberOfItems() {
                return this.totalNumberOfItems_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public String getUtteranceID() {
                Object obj = this.utteranceID_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.utteranceID_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetailsOrBuilder
            public ByteString getUtteranceIDBytes() {
                Object obj = this.utteranceID_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.utteranceID_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_InteractionDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(InteractionDetails.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public Builder setActionType(String str) {
                if (str != null) {
                    this.actionType_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setActionTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.actionType_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDestinationApp(String str) {
                if (str != null) {
                    this.destinationApp_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDestinationAppBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.destinationApp_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDestinationScreen(String str) {
                if (str != null) {
                    this.destinationScreen_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDestinationScreenBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.destinationScreen_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDuration(double d) {
                this.duration_ = d;
                onChanged();
                return this;
            }

            public Builder setElementType(String str) {
                if (str != null) {
                    this.elementType_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setElementTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.elementType_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setEndPosition(long j) {
                this.endPosition_ = j;
                onChanged();
                return this;
            }

            public Builder setIndex(long j) {
                this.index_ = j;
                onChanged();
                return this;
            }

            public Builder setInteractionType(String str) {
                if (str != null) {
                    this.interactionType_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setInteractionTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.interactionType_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setReferralDetails(String str) {
                if (str != null) {
                    this.referralDetails_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setReferralDetailsBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.referralDetails_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setReferralSource(String str) {
                if (str != null) {
                    this.referralSource_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setReferralSourceBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.referralSource_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setReferralType(String str) {
                if (str != null) {
                    this.referralType_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setReferralTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.referralType_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setStartPosition(long j) {
                this.startPosition_ = j;
                onChanged();
                return this;
            }

            public Builder setTotalNumberOfItems(long j) {
                this.totalNumberOfItems_ = j;
                onChanged();
                return this;
            }

            public Builder setUtteranceID(String str) {
                if (str != null) {
                    this.utteranceID_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setUtteranceIDBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.utteranceID_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.actionType_ = "";
                this.destinationApp_ = "";
                this.destinationScreen_ = "";
                this.elementType_ = "";
                this.interactionType_ = "";
                this.referralSource_ = "";
                this.referralType_ = "";
                this.referralDetails_ = "";
                this.utteranceID_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public InteractionDetails mo10084build() {
                InteractionDetails mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public InteractionDetails mo10085buildPartial() {
                InteractionDetails interactionDetails = new InteractionDetails(this);
                interactionDetails.actionType_ = this.actionType_;
                interactionDetails.destinationApp_ = this.destinationApp_;
                interactionDetails.destinationScreen_ = this.destinationScreen_;
                interactionDetails.elementType_ = this.elementType_;
                interactionDetails.index_ = this.index_;
                interactionDetails.interactionType_ = this.interactionType_;
                interactionDetails.totalNumberOfItems_ = this.totalNumberOfItems_;
                interactionDetails.duration_ = this.duration_;
                interactionDetails.referralSource_ = this.referralSource_;
                interactionDetails.referralType_ = this.referralType_;
                interactionDetails.referralDetails_ = this.referralDetails_;
                interactionDetails.utteranceID_ = this.utteranceID_;
                interactionDetails.startPosition_ = this.startPosition_;
                interactionDetails.endPosition_ = this.endPosition_;
                onBuilt();
                return interactionDetails;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public InteractionDetails mo10094getDefaultInstanceForType() {
                return InteractionDetails.getDefaultInstance();
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
                this.actionType_ = "";
                this.destinationApp_ = "";
                this.destinationScreen_ = "";
                this.elementType_ = "";
                this.index_ = 0L;
                this.interactionType_ = "";
                this.totalNumberOfItems_ = 0L;
                this.duration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                this.referralSource_ = "";
                this.referralType_ = "";
                this.referralDetails_ = "";
                this.utteranceID_ = "";
                this.startPosition_ = 0L;
                this.endPosition_ = 0L;
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
                if (message instanceof InteractionDetails) {
                    return mergeFrom((InteractionDetails) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(InteractionDetails interactionDetails) {
                if (interactionDetails == InteractionDetails.getDefaultInstance()) {
                    return this;
                }
                if (!interactionDetails.getActionType().isEmpty()) {
                    this.actionType_ = interactionDetails.actionType_;
                    onChanged();
                }
                if (!interactionDetails.getDestinationApp().isEmpty()) {
                    this.destinationApp_ = interactionDetails.destinationApp_;
                    onChanged();
                }
                if (!interactionDetails.getDestinationScreen().isEmpty()) {
                    this.destinationScreen_ = interactionDetails.destinationScreen_;
                    onChanged();
                }
                if (!interactionDetails.getElementType().isEmpty()) {
                    this.elementType_ = interactionDetails.elementType_;
                    onChanged();
                }
                if (interactionDetails.getIndex() != 0) {
                    setIndex(interactionDetails.getIndex());
                }
                if (!interactionDetails.getInteractionType().isEmpty()) {
                    this.interactionType_ = interactionDetails.interactionType_;
                    onChanged();
                }
                if (interactionDetails.getTotalNumberOfItems() != 0) {
                    setTotalNumberOfItems(interactionDetails.getTotalNumberOfItems());
                }
                if (interactionDetails.getDuration() != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                    setDuration(interactionDetails.getDuration());
                }
                if (!interactionDetails.getReferralSource().isEmpty()) {
                    this.referralSource_ = interactionDetails.referralSource_;
                    onChanged();
                }
                if (!interactionDetails.getReferralType().isEmpty()) {
                    this.referralType_ = interactionDetails.referralType_;
                    onChanged();
                }
                if (!interactionDetails.getReferralDetails().isEmpty()) {
                    this.referralDetails_ = interactionDetails.referralDetails_;
                    onChanged();
                }
                if (!interactionDetails.getUtteranceID().isEmpty()) {
                    this.utteranceID_ = interactionDetails.utteranceID_;
                    onChanged();
                }
                if (interactionDetails.getStartPosition() != 0) {
                    setStartPosition(interactionDetails.getStartPosition());
                }
                if (interactionDetails.getEndPosition() != 0) {
                    setEndPosition(interactionDetails.getEndPosition());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) interactionDetails).unknownFields);
                onChanged();
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.actionType_ = "";
                this.destinationApp_ = "";
                this.destinationScreen_ = "";
                this.elementType_ = "";
                this.interactionType_ = "";
                this.referralSource_ = "";
                this.referralType_ = "";
                this.referralDetails_ = "";
                this.utteranceID_ = "";
                maybeForceBuilderInitialization();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetails.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetails.access$1900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$InteractionDetails r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetails) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$InteractionDetails r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetails) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.InteractionDetails.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$InteractionDetails$Builder");
            }
        }

        public static Builder newBuilder(InteractionDetails interactionDetails) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(interactionDetails);
        }

        public static InteractionDetails parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private InteractionDetails(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static InteractionDetails parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InteractionDetails) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static InteractionDetails parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public InteractionDetails mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static InteractionDetails parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private InteractionDetails() {
            this.memoizedIsInitialized = (byte) -1;
            this.actionType_ = "";
            this.destinationApp_ = "";
            this.destinationScreen_ = "";
            this.elementType_ = "";
            this.index_ = 0L;
            this.interactionType_ = "";
            this.totalNumberOfItems_ = 0L;
            this.duration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            this.referralSource_ = "";
            this.referralType_ = "";
            this.referralDetails_ = "";
            this.utteranceID_ = "";
            this.startPosition_ = 0L;
            this.endPosition_ = 0L;
        }

        public static InteractionDetails parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static InteractionDetails parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static InteractionDetails parseFrom(InputStream inputStream) throws IOException {
            return (InteractionDetails) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static InteractionDetails parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InteractionDetails) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static InteractionDetails parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (InteractionDetails) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static InteractionDetails parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InteractionDetails) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        private InteractionDetails(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                switch (readTag) {
                                    case 0:
                                        break;
                                    case 10:
                                        this.actionType_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 18:
                                        this.destinationApp_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 26:
                                        this.destinationScreen_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 34:
                                        this.elementType_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 40:
                                        this.index_ = codedInputStream.readInt64();
                                        continue;
                                    case 50:
                                        this.interactionType_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 56:
                                        this.totalNumberOfItems_ = codedInputStream.readInt64();
                                        continue;
                                    case 65:
                                        this.duration_ = codedInputStream.readDouble();
                                        continue;
                                    case 74:
                                        this.referralSource_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 82:
                                        this.referralType_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 90:
                                        this.referralDetails_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 98:
                                        this.utteranceID_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 104:
                                        this.startPosition_ = codedInputStream.readInt64();
                                        continue;
                                    case 112:
                                        this.endPosition_ = codedInputStream.readInt64();
                                        continue;
                                    default:
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                            break;
                                        } else {
                                            continue;
                                        }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
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

    /* loaded from: classes9.dex */
    public interface InteractionDetailsOrBuilder extends MessageOrBuilder {
        String getActionType();

        ByteString getActionTypeBytes();

        String getDestinationApp();

        ByteString getDestinationAppBytes();

        String getDestinationScreen();

        ByteString getDestinationScreenBytes();

        double getDuration();

        String getElementType();

        ByteString getElementTypeBytes();

        long getEndPosition();

        long getIndex();

        String getInteractionType();

        ByteString getInteractionTypeBytes();

        String getReferralDetails();

        ByteString getReferralDetailsBytes();

        String getReferralSource();

        ByteString getReferralSourceBytes();

        String getReferralType();

        ByteString getReferralTypeBytes();

        long getStartPosition();

        long getTotalNumberOfItems();

        String getUtteranceID();

        ByteString getUtteranceIDBytes();
    }

    /* loaded from: classes9.dex */
    public static final class Metadata extends GeneratedMessageV3 implements MetadataOrBuilder {
        public static final int A4ALAUNCH_FIELD_NUMBER = 8;
        public static final int A4ASDK_FIELD_NUMBER = 7;
        public static final int AMA_FIELD_NUMBER = 2;
        public static final int AMPD_FIELD_NUMBER = 3;
        public static final int COMMS_FIELD_NUMBER = 1;
        public static final int DREAM_FIELD_NUMBER = 4;
        public static final int ENTERTAINEMNT_FIELD_NUMBER = 5;
        public static final int PHOTOS_FIELD_NUMBER = 6;
        private static final long serialVersionUID = 0;
        private A4aLaunch a4ALaunch_;
        private A4aSdk a4ASdk_;
        private Ama ama_;
        private Ampd ampd_;
        private Comms comms_;
        private Dream dream_;
        private Entertainemnt entertainemnt_;
        private byte memoizedIsInitialized;
        private Photos photos_;
        private static final Metadata DEFAULT_INSTANCE = new Metadata();
        private static final Parser<Metadata> PARSER = new AbstractParser<Metadata>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public Metadata mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Metadata(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes9.dex */
        public static final class A4aLaunch extends GeneratedMessageV3 implements A4aLaunchOrBuilder {
            public static final int OUTCOME_FIELD_NUMBER = 1;
            public static final int REASONS_FIELD_NUMBER = 3;
            public static final int TARGET_FIELD_NUMBER = 2;
            public static final int TOKEN_FIELD_NUMBER = 4;
            private static final long serialVersionUID = 0;
            private byte memoizedIsInitialized;
            private volatile Object outcome_;
            private volatile Object reasons_;
            private volatile Object target_;
            private volatile Object token_;
            private static final A4aLaunch DEFAULT_INSTANCE = new A4aLaunch();
            private static final Parser<A4aLaunch> PARSER = new AbstractParser<A4aLaunch>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunch.1
                @Override // com.google.protobuf.Parser
                /* renamed from: parsePartialFrom */
                public A4aLaunch mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new A4aLaunch(codedInputStream, extensionRegistryLite);
                }
            };

            public static A4aLaunch getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aLaunch_descriptor;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.mo10081toBuilder();
            }

            public static A4aLaunch parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (A4aLaunch) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static A4aLaunch parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.mo8402parseFrom(byteBuffer);
            }

            public static Parser<A4aLaunch> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof A4aLaunch)) {
                    return super.equals(obj);
                }
                A4aLaunch a4aLaunch = (A4aLaunch) obj;
                return ((((getOutcome().equals(a4aLaunch.getOutcome())) && getTarget().equals(a4aLaunch.getTarget())) && getReasons().equals(a4aLaunch.getReasons())) && getToken().equals(a4aLaunch.getToken())) && this.unknownFields.equals(a4aLaunch.unknownFields);
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
            public String getOutcome() {
                Object obj = this.outcome_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.outcome_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
            public ByteString getOutcomeBytes() {
                Object obj = this.outcome_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.outcome_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: getParserForType */
            public Parser<A4aLaunch> mo9954getParserForType() {
                return PARSER;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
            public String getReasons() {
                Object obj = this.reasons_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.reasons_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
            public ByteString getReasonsBytes() {
                Object obj = this.reasons_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.reasons_ = copyFromUtf8;
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
                if (!getOutcomeBytes().isEmpty()) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.outcome_);
                }
                if (!getTargetBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(2, this.target_);
                }
                if (!getReasonsBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(3, this.reasons_);
                }
                if (!getTokenBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(4, this.token_);
                }
                int serializedSize = this.unknownFields.getSerializedSize() + i2;
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
            public String getTarget() {
                Object obj = this.target_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.target_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
            public ByteString getTargetBytes() {
                Object obj = this.target_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.target_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
            public String getToken() {
                Object obj = this.token_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.token_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
            public ByteString getTokenBytes() {
                Object obj = this.token_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.token_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
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
                int hashCode = getOutcome().hashCode();
                int hashCode2 = getTarget().hashCode();
                int hashCode3 = getReasons().hashCode();
                int hashCode4 = getToken().hashCode();
                int hashCode5 = this.unknownFields.hashCode() + ((hashCode4 + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 29);
                this.memoizedHashCode = hashCode5;
                return hashCode5;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aLaunch_fieldAccessorTable.ensureFieldAccessorsInitialized(A4aLaunch.class, Builder.class);
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
                if (!getOutcomeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.outcome_);
                }
                if (!getTargetBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.target_);
                }
                if (!getReasonsBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.reasons_);
                }
                if (!getTokenBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.token_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            /* loaded from: classes9.dex */
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements A4aLaunchOrBuilder {
                private Object outcome_;
                private Object reasons_;
                private Object target_;
                private Object token_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aLaunch_descriptor;
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                public Builder clearOutcome() {
                    this.outcome_ = A4aLaunch.getDefaultInstance().getOutcome();
                    onChanged();
                    return this;
                }

                public Builder clearReasons() {
                    this.reasons_ = A4aLaunch.getDefaultInstance().getReasons();
                    onChanged();
                    return this;
                }

                public Builder clearTarget() {
                    this.target_ = A4aLaunch.getDefaultInstance().getTarget();
                    onChanged();
                    return this;
                }

                public Builder clearToken() {
                    this.token_ = A4aLaunch.getDefaultInstance().getToken();
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aLaunch_descriptor;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
                public String getOutcome() {
                    Object obj = this.outcome_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.outcome_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
                public ByteString getOutcomeBytes() {
                    Object obj = this.outcome_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.outcome_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
                public String getReasons() {
                    Object obj = this.reasons_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.reasons_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
                public ByteString getReasonsBytes() {
                    Object obj = this.reasons_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.reasons_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
                public String getTarget() {
                    Object obj = this.target_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.target_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
                public ByteString getTargetBytes() {
                    Object obj = this.target_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.target_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
                public String getToken() {
                    Object obj = this.token_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.token_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunchOrBuilder
                public ByteString getTokenBytes() {
                    Object obj = this.token_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.token_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aLaunch_fieldAccessorTable.ensureFieldAccessorsInitialized(A4aLaunch.class, Builder.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public Builder setOutcome(String str) {
                    if (str != null) {
                        this.outcome_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setOutcomeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.outcome_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setReasons(String str) {
                    if (str != null) {
                        this.reasons_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setReasonsBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.reasons_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTarget(String str) {
                    if (str != null) {
                        this.target_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTargetBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.target_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setToken(String str) {
                    if (str != null) {
                        this.token_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTokenBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.token_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                private Builder() {
                    this.outcome_ = "";
                    this.target_ = "";
                    this.reasons_ = "";
                    this.token_ = "";
                    maybeForceBuilderInitialization();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: addRepeatedField */
                public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: build */
                public A4aLaunch mo10084build() {
                    A4aLaunch mo10085buildPartial = mo10085buildPartial();
                    if (mo10085buildPartial.isInitialized()) {
                        return mo10085buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: buildPartial */
                public A4aLaunch mo10085buildPartial() {
                    A4aLaunch a4aLaunch = new A4aLaunch(this);
                    a4aLaunch.outcome_ = this.outcome_;
                    a4aLaunch.target_ = this.target_;
                    a4aLaunch.reasons_ = this.reasons_;
                    a4aLaunch.token_ = this.token_;
                    onBuilt();
                    return a4aLaunch;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: clearField */
                public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.mo10088clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: getDefaultInstanceForType */
                public A4aLaunch mo10094getDefaultInstanceForType() {
                    return A4aLaunch.getDefaultInstance();
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
                    this.outcome_ = "";
                    this.target_ = "";
                    this.reasons_ = "";
                    this.token_ = "";
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
                    if (message instanceof A4aLaunch) {
                        return mergeFrom((A4aLaunch) message);
                    }
                    super.mo10096mergeFrom(message);
                    return this;
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.outcome_ = "";
                    this.target_ = "";
                    this.reasons_ = "";
                    this.token_ = "";
                    maybeForceBuilderInitialization();
                }

                public Builder mergeFrom(A4aLaunch a4aLaunch) {
                    if (a4aLaunch == A4aLaunch.getDefaultInstance()) {
                        return this;
                    }
                    if (!a4aLaunch.getOutcome().isEmpty()) {
                        this.outcome_ = a4aLaunch.outcome_;
                        onChanged();
                    }
                    if (!a4aLaunch.getTarget().isEmpty()) {
                        this.target_ = a4aLaunch.target_;
                        onChanged();
                    }
                    if (!a4aLaunch.getReasons().isEmpty()) {
                        this.reasons_ = a4aLaunch.reasons_;
                        onChanged();
                    }
                    if (!a4aLaunch.getToken().isEmpty()) {
                        this.token_ = a4aLaunch.token_;
                        onChanged();
                    }
                    mo10099mergeUnknownFields(((GeneratedMessageV3) a4aLaunch).unknownFields);
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
                public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunch.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunch.access$21100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$A4aLaunch r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunch) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$A4aLaunch r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunch) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aLaunch.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$A4aLaunch$Builder");
                }
            }

            public static Builder newBuilder(A4aLaunch a4aLaunch) {
                return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(a4aLaunch);
            }

            public static A4aLaunch parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
            }

            private A4aLaunch(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            public static A4aLaunch parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (A4aLaunch) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static A4aLaunch parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.mo8396parseFrom(byteString);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public A4aLaunch mo10094getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: toBuilder */
            public Builder mo10081toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            public static A4aLaunch parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: newBuilderForType */
            public Builder mo10079newBuilderForType() {
                return newBuilder();
            }

            private A4aLaunch() {
                this.memoizedIsInitialized = (byte) -1;
                this.outcome_ = "";
                this.target_ = "";
                this.reasons_ = "";
                this.token_ = "";
            }

            public static A4aLaunch parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.mo8404parseFrom(bArr);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: newBuilderForType */
            public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static A4aLaunch parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
            }

            public static A4aLaunch parseFrom(InputStream inputStream) throws IOException {
                return (A4aLaunch) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static A4aLaunch parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (A4aLaunch) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            private A4aLaunch(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.outcome_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 18) {
                                        this.target_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 26) {
                                        this.reasons_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag != 34) {
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.token_ = codedInputStream.readStringRequireUtf8();
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

            public static A4aLaunch parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (A4aLaunch) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static A4aLaunch parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (A4aLaunch) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes9.dex */
        public interface A4aLaunchOrBuilder extends MessageOrBuilder {
            String getOutcome();

            ByteString getOutcomeBytes();

            String getReasons();

            ByteString getReasonsBytes();

            String getTarget();

            ByteString getTargetBytes();

            String getToken();

            ByteString getTokenBytes();
        }

        /* loaded from: classes9.dex */
        public static final class A4aSdk extends GeneratedMessageV3 implements A4aSdkOrBuilder {
            public static final int DIALOGREQUESTID_FIELD_NUMBER = 1;
            public static final int INVOCATIONIDENTIFIER_FIELD_NUMBER = 3;
            public static final int INVOCATIONNAMESPACE_FIELD_NUMBER = 2;
            public static final int INVOCATIONTYPE_FIELD_NUMBER = 4;
            private static final long serialVersionUID = 0;
            private volatile Object dialogRequestId_;
            private volatile Object invocationIdentifier_;
            private volatile Object invocationNamespace_;
            private volatile Object invocationType_;
            private byte memoizedIsInitialized;
            private static final A4aSdk DEFAULT_INSTANCE = new A4aSdk();
            private static final Parser<A4aSdk> PARSER = new AbstractParser<A4aSdk>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdk.1
                @Override // com.google.protobuf.Parser
                /* renamed from: parsePartialFrom */
                public A4aSdk mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new A4aSdk(codedInputStream, extensionRegistryLite);
                }
            };

            public static A4aSdk getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aSdk_descriptor;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.mo10081toBuilder();
            }

            public static A4aSdk parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (A4aSdk) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static A4aSdk parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.mo8402parseFrom(byteBuffer);
            }

            public static Parser<A4aSdk> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof A4aSdk)) {
                    return super.equals(obj);
                }
                A4aSdk a4aSdk = (A4aSdk) obj;
                return ((((getDialogRequestId().equals(a4aSdk.getDialogRequestId())) && getInvocationNamespace().equals(a4aSdk.getInvocationNamespace())) && getInvocationIdentifier().equals(a4aSdk.getInvocationIdentifier())) && getInvocationType().equals(a4aSdk.getInvocationType())) && this.unknownFields.equals(a4aSdk.unknownFields);
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
            public String getDialogRequestId() {
                Object obj = this.dialogRequestId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.dialogRequestId_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
            public ByteString getDialogRequestIdBytes() {
                Object obj = this.dialogRequestId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dialogRequestId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
            public String getInvocationIdentifier() {
                Object obj = this.invocationIdentifier_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.invocationIdentifier_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
            public ByteString getInvocationIdentifierBytes() {
                Object obj = this.invocationIdentifier_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.invocationIdentifier_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
            public String getInvocationNamespace() {
                Object obj = this.invocationNamespace_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.invocationNamespace_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
            public ByteString getInvocationNamespaceBytes() {
                Object obj = this.invocationNamespace_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.invocationNamespace_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
            public String getInvocationType() {
                Object obj = this.invocationType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.invocationType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
            public ByteString getInvocationTypeBytes() {
                Object obj = this.invocationType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.invocationType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: getParserForType */
            public Parser<A4aSdk> mo9954getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if (!getDialogRequestIdBytes().isEmpty()) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.dialogRequestId_);
                }
                if (!getInvocationNamespaceBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(2, this.invocationNamespace_);
                }
                if (!getInvocationIdentifierBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(3, this.invocationIdentifier_);
                }
                if (!getInvocationTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(4, this.invocationType_);
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
                int hashCode = getDialogRequestId().hashCode();
                int hashCode2 = getInvocationNamespace().hashCode();
                int hashCode3 = getInvocationIdentifier().hashCode();
                int hashCode4 = getInvocationType().hashCode();
                int hashCode5 = this.unknownFields.hashCode() + ((hashCode4 + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 29);
                this.memoizedHashCode = hashCode5;
                return hashCode5;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aSdk_fieldAccessorTable.ensureFieldAccessorsInitialized(A4aSdk.class, Builder.class);
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
                if (!getDialogRequestIdBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.dialogRequestId_);
                }
                if (!getInvocationNamespaceBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.invocationNamespace_);
                }
                if (!getInvocationIdentifierBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.invocationIdentifier_);
                }
                if (!getInvocationTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.invocationType_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            /* loaded from: classes9.dex */
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements A4aSdkOrBuilder {
                private Object dialogRequestId_;
                private Object invocationIdentifier_;
                private Object invocationNamespace_;
                private Object invocationType_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aSdk_descriptor;
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                public Builder clearDialogRequestId() {
                    this.dialogRequestId_ = A4aSdk.getDefaultInstance().getDialogRequestId();
                    onChanged();
                    return this;
                }

                public Builder clearInvocationIdentifier() {
                    this.invocationIdentifier_ = A4aSdk.getDefaultInstance().getInvocationIdentifier();
                    onChanged();
                    return this;
                }

                public Builder clearInvocationNamespace() {
                    this.invocationNamespace_ = A4aSdk.getDefaultInstance().getInvocationNamespace();
                    onChanged();
                    return this;
                }

                public Builder clearInvocationType() {
                    this.invocationType_ = A4aSdk.getDefaultInstance().getInvocationType();
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aSdk_descriptor;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
                public String getDialogRequestId() {
                    Object obj = this.dialogRequestId_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.dialogRequestId_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
                public ByteString getDialogRequestIdBytes() {
                    Object obj = this.dialogRequestId_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.dialogRequestId_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
                public String getInvocationIdentifier() {
                    Object obj = this.invocationIdentifier_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.invocationIdentifier_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
                public ByteString getInvocationIdentifierBytes() {
                    Object obj = this.invocationIdentifier_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.invocationIdentifier_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
                public String getInvocationNamespace() {
                    Object obj = this.invocationNamespace_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.invocationNamespace_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
                public ByteString getInvocationNamespaceBytes() {
                    Object obj = this.invocationNamespace_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.invocationNamespace_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
                public String getInvocationType() {
                    Object obj = this.invocationType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.invocationType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdkOrBuilder
                public ByteString getInvocationTypeBytes() {
                    Object obj = this.invocationType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.invocationType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_A4aSdk_fieldAccessorTable.ensureFieldAccessorsInitialized(A4aSdk.class, Builder.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public Builder setDialogRequestId(String str) {
                    if (str != null) {
                        this.dialogRequestId_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDialogRequestIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.dialogRequestId_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setInvocationIdentifier(String str) {
                    if (str != null) {
                        this.invocationIdentifier_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setInvocationIdentifierBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.invocationIdentifier_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setInvocationNamespace(String str) {
                    if (str != null) {
                        this.invocationNamespace_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setInvocationNamespaceBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.invocationNamespace_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setInvocationType(String str) {
                    if (str != null) {
                        this.invocationType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setInvocationTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.invocationType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                private Builder() {
                    this.dialogRequestId_ = "";
                    this.invocationNamespace_ = "";
                    this.invocationIdentifier_ = "";
                    this.invocationType_ = "";
                    maybeForceBuilderInitialization();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: addRepeatedField */
                public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: build */
                public A4aSdk mo10084build() {
                    A4aSdk mo10085buildPartial = mo10085buildPartial();
                    if (mo10085buildPartial.isInitialized()) {
                        return mo10085buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: buildPartial */
                public A4aSdk mo10085buildPartial() {
                    A4aSdk a4aSdk = new A4aSdk(this);
                    a4aSdk.dialogRequestId_ = this.dialogRequestId_;
                    a4aSdk.invocationNamespace_ = this.invocationNamespace_;
                    a4aSdk.invocationIdentifier_ = this.invocationIdentifier_;
                    a4aSdk.invocationType_ = this.invocationType_;
                    onBuilt();
                    return a4aSdk;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: clearField */
                public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.mo10088clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: getDefaultInstanceForType */
                public A4aSdk mo10094getDefaultInstanceForType() {
                    return A4aSdk.getDefaultInstance();
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
                    this.dialogRequestId_ = "";
                    this.invocationNamespace_ = "";
                    this.invocationIdentifier_ = "";
                    this.invocationType_ = "";
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
                    if (message instanceof A4aSdk) {
                        return mergeFrom((A4aSdk) message);
                    }
                    super.mo10096mergeFrom(message);
                    return this;
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.dialogRequestId_ = "";
                    this.invocationNamespace_ = "";
                    this.invocationIdentifier_ = "";
                    this.invocationType_ = "";
                    maybeForceBuilderInitialization();
                }

                public Builder mergeFrom(A4aSdk a4aSdk) {
                    if (a4aSdk == A4aSdk.getDefaultInstance()) {
                        return this;
                    }
                    if (!a4aSdk.getDialogRequestId().isEmpty()) {
                        this.dialogRequestId_ = a4aSdk.dialogRequestId_;
                        onChanged();
                    }
                    if (!a4aSdk.getInvocationNamespace().isEmpty()) {
                        this.invocationNamespace_ = a4aSdk.invocationNamespace_;
                        onChanged();
                    }
                    if (!a4aSdk.getInvocationIdentifier().isEmpty()) {
                        this.invocationIdentifier_ = a4aSdk.invocationIdentifier_;
                        onChanged();
                    }
                    if (!a4aSdk.getInvocationType().isEmpty()) {
                        this.invocationType_ = a4aSdk.invocationType_;
                        onChanged();
                    }
                    mo10099mergeUnknownFields(((GeneratedMessageV3) a4aSdk).unknownFields);
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
                public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdk.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdk.access$19600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$A4aSdk r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdk) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$A4aSdk r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdk) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.A4aSdk.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$A4aSdk$Builder");
                }
            }

            public static Builder newBuilder(A4aSdk a4aSdk) {
                return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(a4aSdk);
            }

            public static A4aSdk parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
            }

            private A4aSdk(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            public static A4aSdk parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (A4aSdk) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static A4aSdk parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.mo8396parseFrom(byteString);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public A4aSdk mo10094getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: toBuilder */
            public Builder mo10081toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            public static A4aSdk parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: newBuilderForType */
            public Builder mo10079newBuilderForType() {
                return newBuilder();
            }

            private A4aSdk() {
                this.memoizedIsInitialized = (byte) -1;
                this.dialogRequestId_ = "";
                this.invocationNamespace_ = "";
                this.invocationIdentifier_ = "";
                this.invocationType_ = "";
            }

            public static A4aSdk parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.mo8404parseFrom(bArr);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: newBuilderForType */
            public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static A4aSdk parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
            }

            public static A4aSdk parseFrom(InputStream inputStream) throws IOException {
                return (A4aSdk) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static A4aSdk parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (A4aSdk) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            private A4aSdk(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.dialogRequestId_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 18) {
                                        this.invocationNamespace_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 26) {
                                        this.invocationIdentifier_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag != 34) {
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.invocationType_ = codedInputStream.readStringRequireUtf8();
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

            public static A4aSdk parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (A4aSdk) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static A4aSdk parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (A4aSdk) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes9.dex */
        public interface A4aSdkOrBuilder extends MessageOrBuilder {
            String getDialogRequestId();

            ByteString getDialogRequestIdBytes();

            String getInvocationIdentifier();

            ByteString getInvocationIdentifierBytes();

            String getInvocationNamespace();

            ByteString getInvocationNamespaceBytes();

            String getInvocationType();

            ByteString getInvocationTypeBytes();
        }

        /* loaded from: classes9.dex */
        public static final class Ama extends GeneratedMessageV3 implements AmaOrBuilder {
            public static final int ACCESSORYLIBRARYVERSION_FIELD_NUMBER = 1;
            public static final int ACCTIMESTAMP_FIELD_NUMBER = 12;
            public static final int BOOTNUMBER_FIELD_NUMBER = 18;
            public static final int DEVICESERIALNUMBER_FIELD_NUMBER = 3;
            public static final int DEVICETYPE_FIELD_NUMBER = 2;
            public static final int DIALOGID_FIELD_NUMBER = 16;
            public static final int DIALOGTURNID_FIELD_NUMBER = 17;
            public static final int FIRMACC1_FIELD_NUMBER = 8;
            public static final int FIRMACC2_FIELD_NUMBER = 9;
            public static final int FIRMACC3_FIELD_NUMBER = 10;
            public static final int FIRMWARELOCALE_FIELD_NUMBER = 6;
            public static final int FIRMWARENAME_FIELD_NUMBER = 5;
            public static final int FIRMWAREVERSIONNAME_FIELD_NUMBER = 4;
            public static final int INTENTNAME_FIELD_NUMBER = 13;
            public static final int LOCALEXECREASON_FIELD_NUMBER = 14;
            public static final int SEQUENCENUMBER_FIELD_NUMBER = 19;
            public static final int TRANSPORTTYPE_FIELD_NUMBER = 7;
            public static final int VALUES_FIELD_NUMBER = 11;
            public static final int VOICEREQID_FIELD_NUMBER = 15;
            private static final long serialVersionUID = 0;
            private long accTimestamp_;
            private volatile Object accessoryLibraryVersion_;
            private volatile Object bootNumber_;
            private volatile Object deviceSerialNumber_;
            private volatile Object deviceType_;
            private volatile Object dialogID_;
            private volatile Object dialogTurnID_;
            private volatile Object firmAcc1_;
            private volatile Object firmAcc2_;
            private volatile Object firmAcc3_;
            private volatile Object firmwareLocale_;
            private volatile Object firmwareName_;
            private volatile Object firmwareVersionName_;
            private volatile Object intentName_;
            private volatile Object localExecReason_;
            private byte memoizedIsInitialized;
            private volatile Object sequenceNumber_;
            private volatile Object transportType_;
            private volatile Object values_;
            private volatile Object voiceReqID_;
            private static final Ama DEFAULT_INSTANCE = new Ama();
            private static final Parser<Ama> PARSER = new AbstractParser<Ama>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ama.1
                @Override // com.google.protobuf.Parser
                /* renamed from: parsePartialFrom */
                public Ama mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Ama(codedInputStream, extensionRegistryLite);
                }
            };

            public static Ama getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ama_descriptor;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.mo10081toBuilder();
            }

            public static Ama parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Ama) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Ama parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.mo8402parseFrom(byteBuffer);
            }

            public static Parser<Ama> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Ama)) {
                    return super.equals(obj);
                }
                Ama ama = (Ama) obj;
                return (((((((((((((((((((getAccessoryLibraryVersion().equals(ama.getAccessoryLibraryVersion())) && getDeviceType().equals(ama.getDeviceType())) && getDeviceSerialNumber().equals(ama.getDeviceSerialNumber())) && getFirmwareVersionName().equals(ama.getFirmwareVersionName())) && getFirmwareName().equals(ama.getFirmwareName())) && getFirmwareLocale().equals(ama.getFirmwareLocale())) && getTransportType().equals(ama.getTransportType())) && getFirmAcc1().equals(ama.getFirmAcc1())) && getFirmAcc2().equals(ama.getFirmAcc2())) && getFirmAcc3().equals(ama.getFirmAcc3())) && getValues().equals(ama.getValues())) && (getAccTimestamp() > ama.getAccTimestamp() ? 1 : (getAccTimestamp() == ama.getAccTimestamp() ? 0 : -1)) == 0) && getIntentName().equals(ama.getIntentName())) && getLocalExecReason().equals(ama.getLocalExecReason())) && getVoiceReqID().equals(ama.getVoiceReqID())) && getDialogID().equals(ama.getDialogID())) && getDialogTurnID().equals(ama.getDialogTurnID())) && getBootNumber().equals(ama.getBootNumber())) && getSequenceNumber().equals(ama.getSequenceNumber())) && this.unknownFields.equals(ama.unknownFields);
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public long getAccTimestamp() {
                return this.accTimestamp_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getAccessoryLibraryVersion() {
                Object obj = this.accessoryLibraryVersion_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.accessoryLibraryVersion_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getAccessoryLibraryVersionBytes() {
                Object obj = this.accessoryLibraryVersion_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.accessoryLibraryVersion_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getBootNumber() {
                Object obj = this.bootNumber_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.bootNumber_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getBootNumberBytes() {
                Object obj = this.bootNumber_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bootNumber_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getDeviceSerialNumber() {
                Object obj = this.deviceSerialNumber_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.deviceSerialNumber_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getDeviceSerialNumberBytes() {
                Object obj = this.deviceSerialNumber_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceSerialNumber_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getDeviceType() {
                Object obj = this.deviceType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.deviceType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getDeviceTypeBytes() {
                Object obj = this.deviceType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getDialogID() {
                Object obj = this.dialogID_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.dialogID_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getDialogIDBytes() {
                Object obj = this.dialogID_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dialogID_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getDialogTurnID() {
                Object obj = this.dialogTurnID_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.dialogTurnID_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getDialogTurnIDBytes() {
                Object obj = this.dialogTurnID_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dialogTurnID_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getFirmAcc1() {
                Object obj = this.firmAcc1_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.firmAcc1_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getFirmAcc1Bytes() {
                Object obj = this.firmAcc1_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.firmAcc1_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getFirmAcc2() {
                Object obj = this.firmAcc2_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.firmAcc2_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getFirmAcc2Bytes() {
                Object obj = this.firmAcc2_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.firmAcc2_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getFirmAcc3() {
                Object obj = this.firmAcc3_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.firmAcc3_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getFirmAcc3Bytes() {
                Object obj = this.firmAcc3_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.firmAcc3_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getFirmwareLocale() {
                Object obj = this.firmwareLocale_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.firmwareLocale_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getFirmwareLocaleBytes() {
                Object obj = this.firmwareLocale_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.firmwareLocale_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getFirmwareName() {
                Object obj = this.firmwareName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.firmwareName_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getFirmwareNameBytes() {
                Object obj = this.firmwareName_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.firmwareName_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getFirmwareVersionName() {
                Object obj = this.firmwareVersionName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.firmwareVersionName_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getFirmwareVersionNameBytes() {
                Object obj = this.firmwareVersionName_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.firmwareVersionName_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getIntentName() {
                Object obj = this.intentName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.intentName_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getIntentNameBytes() {
                Object obj = this.intentName_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.intentName_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getLocalExecReason() {
                Object obj = this.localExecReason_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.localExecReason_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getLocalExecReasonBytes() {
                Object obj = this.localExecReason_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.localExecReason_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: getParserForType */
            public Parser<Ama> mo9954getParserForType() {
                return PARSER;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getSequenceNumber() {
                Object obj = this.sequenceNumber_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.sequenceNumber_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getSequenceNumberBytes() {
                Object obj = this.sequenceNumber_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.sequenceNumber_ = copyFromUtf8;
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
                if (!getAccessoryLibraryVersionBytes().isEmpty()) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.accessoryLibraryVersion_);
                }
                if (!getDeviceTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(2, this.deviceType_);
                }
                if (!getDeviceSerialNumberBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(3, this.deviceSerialNumber_);
                }
                if (!getFirmwareVersionNameBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(4, this.firmwareVersionName_);
                }
                if (!getFirmwareNameBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(5, this.firmwareName_);
                }
                if (!getFirmwareLocaleBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(6, this.firmwareLocale_);
                }
                if (!getTransportTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(7, this.transportType_);
                }
                if (!getFirmAcc1Bytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(8, this.firmAcc1_);
                }
                if (!getFirmAcc2Bytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(9, this.firmAcc2_);
                }
                if (!getFirmAcc3Bytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(10, this.firmAcc3_);
                }
                if (!getValuesBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(11, this.values_);
                }
                long j = this.accTimestamp_;
                if (j != 0) {
                    i2 += CodedOutputStream.computeInt64Size(12, j);
                }
                if (!getIntentNameBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(13, this.intentName_);
                }
                if (!getLocalExecReasonBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(14, this.localExecReason_);
                }
                if (!getVoiceReqIDBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(15, this.voiceReqID_);
                }
                if (!getDialogIDBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(16, this.dialogID_);
                }
                if (!getDialogTurnIDBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(17, this.dialogTurnID_);
                }
                if (!getBootNumberBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(18, this.bootNumber_);
                }
                if (!getSequenceNumberBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(19, this.sequenceNumber_);
                }
                int serializedSize = this.unknownFields.getSerializedSize() + i2;
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getTransportType() {
                Object obj = this.transportType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.transportType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getTransportTypeBytes() {
                Object obj = this.transportType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.transportType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getValues() {
                Object obj = this.values_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.values_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getValuesBytes() {
                Object obj = this.values_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.values_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public String getVoiceReqID() {
                Object obj = this.voiceReqID_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.voiceReqID_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
            public ByteString getVoiceReqIDBytes() {
                Object obj = this.voiceReqID_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.voiceReqID_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = getAccessoryLibraryVersion().hashCode();
                int hashCode2 = getDeviceType().hashCode();
                int hashCode3 = getDeviceSerialNumber().hashCode();
                int hashCode4 = getFirmwareVersionName().hashCode();
                int hashCode5 = getFirmwareName().hashCode();
                int hashCode6 = getFirmwareLocale().hashCode();
                int hashCode7 = getTransportType().hashCode();
                int hashCode8 = getFirmAcc1().hashCode();
                int hashCode9 = getFirmAcc2().hashCode();
                int hashCode10 = getFirmAcc3().hashCode();
                int hashCode11 = getValues().hashCode();
                int hashLong = Internal.hashLong(getAccTimestamp());
                int hashCode12 = getIntentName().hashCode();
                int hashCode13 = getLocalExecReason().hashCode();
                int hashCode14 = getVoiceReqID().hashCode();
                int hashCode15 = getDialogID().hashCode();
                int hashCode16 = getDialogTurnID().hashCode();
                int hashCode17 = getBootNumber().hashCode();
                int hashCode18 = getSequenceNumber().hashCode();
                int hashCode19 = this.unknownFields.hashCode() + ((hashCode18 + ((((hashCode17 + ((((hashCode16 + ((((hashCode15 + ((((hashCode14 + ((((hashCode13 + ((((hashCode12 + ((((hashLong + ((((hashCode11 + ((((hashCode10 + ((((hashCode9 + ((((hashCode8 + ((((hashCode7 + ((((hashCode6 + ((((hashCode5 + ((((hashCode4 + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 37) + 6) * 53)) * 37) + 7) * 53)) * 37) + 8) * 53)) * 37) + 9) * 53)) * 37) + 10) * 53)) * 37) + 11) * 53)) * 37) + 12) * 53)) * 37) + 13) * 53)) * 37) + 14) * 53)) * 37) + 15) * 53)) * 37) + 16) * 53)) * 37) + 17) * 53)) * 37) + 18) * 53)) * 37) + 19) * 53)) * 29);
                this.memoizedHashCode = hashCode19;
                return hashCode19;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ama_fieldAccessorTable.ensureFieldAccessorsInitialized(Ama.class, Builder.class);
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
                if (!getAccessoryLibraryVersionBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.accessoryLibraryVersion_);
                }
                if (!getDeviceTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.deviceType_);
                }
                if (!getDeviceSerialNumberBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.deviceSerialNumber_);
                }
                if (!getFirmwareVersionNameBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.firmwareVersionName_);
                }
                if (!getFirmwareNameBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 5, this.firmwareName_);
                }
                if (!getFirmwareLocaleBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 6, this.firmwareLocale_);
                }
                if (!getTransportTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 7, this.transportType_);
                }
                if (!getFirmAcc1Bytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 8, this.firmAcc1_);
                }
                if (!getFirmAcc2Bytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 9, this.firmAcc2_);
                }
                if (!getFirmAcc3Bytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 10, this.firmAcc3_);
                }
                if (!getValuesBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 11, this.values_);
                }
                long j = this.accTimestamp_;
                if (j != 0) {
                    codedOutputStream.writeInt64(12, j);
                }
                if (!getIntentNameBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 13, this.intentName_);
                }
                if (!getLocalExecReasonBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 14, this.localExecReason_);
                }
                if (!getVoiceReqIDBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 15, this.voiceReqID_);
                }
                if (!getDialogIDBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 16, this.dialogID_);
                }
                if (!getDialogTurnIDBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 17, this.dialogTurnID_);
                }
                if (!getBootNumberBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 18, this.bootNumber_);
                }
                if (!getSequenceNumberBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 19, this.sequenceNumber_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            /* loaded from: classes9.dex */
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AmaOrBuilder {
                private long accTimestamp_;
                private Object accessoryLibraryVersion_;
                private Object bootNumber_;
                private Object deviceSerialNumber_;
                private Object deviceType_;
                private Object dialogID_;
                private Object dialogTurnID_;
                private Object firmAcc1_;
                private Object firmAcc2_;
                private Object firmAcc3_;
                private Object firmwareLocale_;
                private Object firmwareName_;
                private Object firmwareVersionName_;
                private Object intentName_;
                private Object localExecReason_;
                private Object sequenceNumber_;
                private Object transportType_;
                private Object values_;
                private Object voiceReqID_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ama_descriptor;
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                public Builder clearAccTimestamp() {
                    this.accTimestamp_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearAccessoryLibraryVersion() {
                    this.accessoryLibraryVersion_ = Ama.getDefaultInstance().getAccessoryLibraryVersion();
                    onChanged();
                    return this;
                }

                public Builder clearBootNumber() {
                    this.bootNumber_ = Ama.getDefaultInstance().getBootNumber();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceSerialNumber() {
                    this.deviceSerialNumber_ = Ama.getDefaultInstance().getDeviceSerialNumber();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceType() {
                    this.deviceType_ = Ama.getDefaultInstance().getDeviceType();
                    onChanged();
                    return this;
                }

                public Builder clearDialogID() {
                    this.dialogID_ = Ama.getDefaultInstance().getDialogID();
                    onChanged();
                    return this;
                }

                public Builder clearDialogTurnID() {
                    this.dialogTurnID_ = Ama.getDefaultInstance().getDialogTurnID();
                    onChanged();
                    return this;
                }

                public Builder clearFirmAcc1() {
                    this.firmAcc1_ = Ama.getDefaultInstance().getFirmAcc1();
                    onChanged();
                    return this;
                }

                public Builder clearFirmAcc2() {
                    this.firmAcc2_ = Ama.getDefaultInstance().getFirmAcc2();
                    onChanged();
                    return this;
                }

                public Builder clearFirmAcc3() {
                    this.firmAcc3_ = Ama.getDefaultInstance().getFirmAcc3();
                    onChanged();
                    return this;
                }

                public Builder clearFirmwareLocale() {
                    this.firmwareLocale_ = Ama.getDefaultInstance().getFirmwareLocale();
                    onChanged();
                    return this;
                }

                public Builder clearFirmwareName() {
                    this.firmwareName_ = Ama.getDefaultInstance().getFirmwareName();
                    onChanged();
                    return this;
                }

                public Builder clearFirmwareVersionName() {
                    this.firmwareVersionName_ = Ama.getDefaultInstance().getFirmwareVersionName();
                    onChanged();
                    return this;
                }

                public Builder clearIntentName() {
                    this.intentName_ = Ama.getDefaultInstance().getIntentName();
                    onChanged();
                    return this;
                }

                public Builder clearLocalExecReason() {
                    this.localExecReason_ = Ama.getDefaultInstance().getLocalExecReason();
                    onChanged();
                    return this;
                }

                public Builder clearSequenceNumber() {
                    this.sequenceNumber_ = Ama.getDefaultInstance().getSequenceNumber();
                    onChanged();
                    return this;
                }

                public Builder clearTransportType() {
                    this.transportType_ = Ama.getDefaultInstance().getTransportType();
                    onChanged();
                    return this;
                }

                public Builder clearValues() {
                    this.values_ = Ama.getDefaultInstance().getValues();
                    onChanged();
                    return this;
                }

                public Builder clearVoiceReqID() {
                    this.voiceReqID_ = Ama.getDefaultInstance().getVoiceReqID();
                    onChanged();
                    return this;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public long getAccTimestamp() {
                    return this.accTimestamp_;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getAccessoryLibraryVersion() {
                    Object obj = this.accessoryLibraryVersion_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.accessoryLibraryVersion_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getAccessoryLibraryVersionBytes() {
                    Object obj = this.accessoryLibraryVersion_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.accessoryLibraryVersion_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getBootNumber() {
                    Object obj = this.bootNumber_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.bootNumber_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getBootNumberBytes() {
                    Object obj = this.bootNumber_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.bootNumber_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ama_descriptor;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getDeviceSerialNumber() {
                    Object obj = this.deviceSerialNumber_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.deviceSerialNumber_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getDeviceSerialNumberBytes() {
                    Object obj = this.deviceSerialNumber_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.deviceSerialNumber_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getDeviceType() {
                    Object obj = this.deviceType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.deviceType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getDeviceTypeBytes() {
                    Object obj = this.deviceType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.deviceType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getDialogID() {
                    Object obj = this.dialogID_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.dialogID_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getDialogIDBytes() {
                    Object obj = this.dialogID_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.dialogID_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getDialogTurnID() {
                    Object obj = this.dialogTurnID_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.dialogTurnID_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getDialogTurnIDBytes() {
                    Object obj = this.dialogTurnID_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.dialogTurnID_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getFirmAcc1() {
                    Object obj = this.firmAcc1_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.firmAcc1_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getFirmAcc1Bytes() {
                    Object obj = this.firmAcc1_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.firmAcc1_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getFirmAcc2() {
                    Object obj = this.firmAcc2_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.firmAcc2_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getFirmAcc2Bytes() {
                    Object obj = this.firmAcc2_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.firmAcc2_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getFirmAcc3() {
                    Object obj = this.firmAcc3_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.firmAcc3_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getFirmAcc3Bytes() {
                    Object obj = this.firmAcc3_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.firmAcc3_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getFirmwareLocale() {
                    Object obj = this.firmwareLocale_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.firmwareLocale_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getFirmwareLocaleBytes() {
                    Object obj = this.firmwareLocale_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.firmwareLocale_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getFirmwareName() {
                    Object obj = this.firmwareName_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.firmwareName_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getFirmwareNameBytes() {
                    Object obj = this.firmwareName_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.firmwareName_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getFirmwareVersionName() {
                    Object obj = this.firmwareVersionName_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.firmwareVersionName_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getFirmwareVersionNameBytes() {
                    Object obj = this.firmwareVersionName_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.firmwareVersionName_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getIntentName() {
                    Object obj = this.intentName_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.intentName_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getIntentNameBytes() {
                    Object obj = this.intentName_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.intentName_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getLocalExecReason() {
                    Object obj = this.localExecReason_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.localExecReason_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getLocalExecReasonBytes() {
                    Object obj = this.localExecReason_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.localExecReason_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getSequenceNumber() {
                    Object obj = this.sequenceNumber_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.sequenceNumber_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getSequenceNumberBytes() {
                    Object obj = this.sequenceNumber_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.sequenceNumber_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getTransportType() {
                    Object obj = this.transportType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.transportType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getTransportTypeBytes() {
                    Object obj = this.transportType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.transportType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getValues() {
                    Object obj = this.values_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.values_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getValuesBytes() {
                    Object obj = this.values_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.values_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public String getVoiceReqID() {
                    Object obj = this.voiceReqID_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.voiceReqID_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmaOrBuilder
                public ByteString getVoiceReqIDBytes() {
                    Object obj = this.voiceReqID_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.voiceReqID_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ama_fieldAccessorTable.ensureFieldAccessorsInitialized(Ama.class, Builder.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public Builder setAccTimestamp(long j) {
                    this.accTimestamp_ = j;
                    onChanged();
                    return this;
                }

                public Builder setAccessoryLibraryVersion(String str) {
                    if (str != null) {
                        this.accessoryLibraryVersion_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAccessoryLibraryVersionBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.accessoryLibraryVersion_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setBootNumber(String str) {
                    if (str != null) {
                        this.bootNumber_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setBootNumberBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.bootNumber_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceSerialNumber(String str) {
                    if (str != null) {
                        this.deviceSerialNumber_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceSerialNumberBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.deviceSerialNumber_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceType(String str) {
                    if (str != null) {
                        this.deviceType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.deviceType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDialogID(String str) {
                    if (str != null) {
                        this.dialogID_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDialogIDBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.dialogID_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDialogTurnID(String str) {
                    if (str != null) {
                        this.dialogTurnID_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDialogTurnIDBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.dialogTurnID_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmAcc1(String str) {
                    if (str != null) {
                        this.firmAcc1_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmAcc1Bytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.firmAcc1_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmAcc2(String str) {
                    if (str != null) {
                        this.firmAcc2_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmAcc2Bytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.firmAcc2_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmAcc3(String str) {
                    if (str != null) {
                        this.firmAcc3_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmAcc3Bytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.firmAcc3_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmwareLocale(String str) {
                    if (str != null) {
                        this.firmwareLocale_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmwareLocaleBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.firmwareLocale_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmwareName(String str) {
                    if (str != null) {
                        this.firmwareName_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmwareNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.firmwareName_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmwareVersionName(String str) {
                    if (str != null) {
                        this.firmwareVersionName_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setFirmwareVersionNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.firmwareVersionName_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setIntentName(String str) {
                    if (str != null) {
                        this.intentName_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setIntentNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.intentName_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLocalExecReason(String str) {
                    if (str != null) {
                        this.localExecReason_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLocalExecReasonBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.localExecReason_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSequenceNumber(String str) {
                    if (str != null) {
                        this.sequenceNumber_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSequenceNumberBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.sequenceNumber_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTransportType(String str) {
                    if (str != null) {
                        this.transportType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTransportTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.transportType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setValues(String str) {
                    if (str != null) {
                        this.values_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setValuesBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.values_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVoiceReqID(String str) {
                    if (str != null) {
                        this.voiceReqID_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVoiceReqIDBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.voiceReqID_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                private Builder() {
                    this.accessoryLibraryVersion_ = "";
                    this.deviceType_ = "";
                    this.deviceSerialNumber_ = "";
                    this.firmwareVersionName_ = "";
                    this.firmwareName_ = "";
                    this.firmwareLocale_ = "";
                    this.transportType_ = "";
                    this.firmAcc1_ = "";
                    this.firmAcc2_ = "";
                    this.firmAcc3_ = "";
                    this.values_ = "";
                    this.intentName_ = "";
                    this.localExecReason_ = "";
                    this.voiceReqID_ = "";
                    this.dialogID_ = "";
                    this.dialogTurnID_ = "";
                    this.bootNumber_ = "";
                    this.sequenceNumber_ = "";
                    maybeForceBuilderInitialization();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: addRepeatedField */
                public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: build */
                public Ama mo10084build() {
                    Ama mo10085buildPartial = mo10085buildPartial();
                    if (mo10085buildPartial.isInitialized()) {
                        return mo10085buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: buildPartial */
                public Ama mo10085buildPartial() {
                    Ama ama = new Ama(this);
                    ama.accessoryLibraryVersion_ = this.accessoryLibraryVersion_;
                    ama.deviceType_ = this.deviceType_;
                    ama.deviceSerialNumber_ = this.deviceSerialNumber_;
                    ama.firmwareVersionName_ = this.firmwareVersionName_;
                    ama.firmwareName_ = this.firmwareName_;
                    ama.firmwareLocale_ = this.firmwareLocale_;
                    ama.transportType_ = this.transportType_;
                    ama.firmAcc1_ = this.firmAcc1_;
                    ama.firmAcc2_ = this.firmAcc2_;
                    ama.firmAcc3_ = this.firmAcc3_;
                    ama.values_ = this.values_;
                    ama.accTimestamp_ = this.accTimestamp_;
                    ama.intentName_ = this.intentName_;
                    ama.localExecReason_ = this.localExecReason_;
                    ama.voiceReqID_ = this.voiceReqID_;
                    ama.dialogID_ = this.dialogID_;
                    ama.dialogTurnID_ = this.dialogTurnID_;
                    ama.bootNumber_ = this.bootNumber_;
                    ama.sequenceNumber_ = this.sequenceNumber_;
                    onBuilt();
                    return ama;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: clearField */
                public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.mo10088clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: getDefaultInstanceForType */
                public Ama mo10094getDefaultInstanceForType() {
                    return Ama.getDefaultInstance();
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
                    this.accessoryLibraryVersion_ = "";
                    this.deviceType_ = "";
                    this.deviceSerialNumber_ = "";
                    this.firmwareVersionName_ = "";
                    this.firmwareName_ = "";
                    this.firmwareLocale_ = "";
                    this.transportType_ = "";
                    this.firmAcc1_ = "";
                    this.firmAcc2_ = "";
                    this.firmAcc3_ = "";
                    this.values_ = "";
                    this.accTimestamp_ = 0L;
                    this.intentName_ = "";
                    this.localExecReason_ = "";
                    this.voiceReqID_ = "";
                    this.dialogID_ = "";
                    this.dialogTurnID_ = "";
                    this.bootNumber_ = "";
                    this.sequenceNumber_ = "";
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
                    if (message instanceof Ama) {
                        return mergeFrom((Ama) message);
                    }
                    super.mo10096mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Ama ama) {
                    if (ama == Ama.getDefaultInstance()) {
                        return this;
                    }
                    if (!ama.getAccessoryLibraryVersion().isEmpty()) {
                        this.accessoryLibraryVersion_ = ama.accessoryLibraryVersion_;
                        onChanged();
                    }
                    if (!ama.getDeviceType().isEmpty()) {
                        this.deviceType_ = ama.deviceType_;
                        onChanged();
                    }
                    if (!ama.getDeviceSerialNumber().isEmpty()) {
                        this.deviceSerialNumber_ = ama.deviceSerialNumber_;
                        onChanged();
                    }
                    if (!ama.getFirmwareVersionName().isEmpty()) {
                        this.firmwareVersionName_ = ama.firmwareVersionName_;
                        onChanged();
                    }
                    if (!ama.getFirmwareName().isEmpty()) {
                        this.firmwareName_ = ama.firmwareName_;
                        onChanged();
                    }
                    if (!ama.getFirmwareLocale().isEmpty()) {
                        this.firmwareLocale_ = ama.firmwareLocale_;
                        onChanged();
                    }
                    if (!ama.getTransportType().isEmpty()) {
                        this.transportType_ = ama.transportType_;
                        onChanged();
                    }
                    if (!ama.getFirmAcc1().isEmpty()) {
                        this.firmAcc1_ = ama.firmAcc1_;
                        onChanged();
                    }
                    if (!ama.getFirmAcc2().isEmpty()) {
                        this.firmAcc2_ = ama.firmAcc2_;
                        onChanged();
                    }
                    if (!ama.getFirmAcc3().isEmpty()) {
                        this.firmAcc3_ = ama.firmAcc3_;
                        onChanged();
                    }
                    if (!ama.getValues().isEmpty()) {
                        this.values_ = ama.values_;
                        onChanged();
                    }
                    if (ama.getAccTimestamp() != 0) {
                        setAccTimestamp(ama.getAccTimestamp());
                    }
                    if (!ama.getIntentName().isEmpty()) {
                        this.intentName_ = ama.intentName_;
                        onChanged();
                    }
                    if (!ama.getLocalExecReason().isEmpty()) {
                        this.localExecReason_ = ama.localExecReason_;
                        onChanged();
                    }
                    if (!ama.getVoiceReqID().isEmpty()) {
                        this.voiceReqID_ = ama.voiceReqID_;
                        onChanged();
                    }
                    if (!ama.getDialogID().isEmpty()) {
                        this.dialogID_ = ama.dialogID_;
                        onChanged();
                    }
                    if (!ama.getDialogTurnID().isEmpty()) {
                        this.dialogTurnID_ = ama.dialogTurnID_;
                        onChanged();
                    }
                    if (!ama.getBootNumber().isEmpty()) {
                        this.bootNumber_ = ama.bootNumber_;
                        onChanged();
                    }
                    if (!ama.getSequenceNumber().isEmpty()) {
                        this.sequenceNumber_ = ama.sequenceNumber_;
                        onChanged();
                    }
                    mo10099mergeUnknownFields(((GeneratedMessageV3) ama).unknownFields);
                    onChanged();
                    return this;
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.accessoryLibraryVersion_ = "";
                    this.deviceType_ = "";
                    this.deviceSerialNumber_ = "";
                    this.firmwareVersionName_ = "";
                    this.firmwareName_ = "";
                    this.firmwareLocale_ = "";
                    this.transportType_ = "";
                    this.firmAcc1_ = "";
                    this.firmAcc2_ = "";
                    this.firmAcc3_ = "";
                    this.values_ = "";
                    this.intentName_ = "";
                    this.localExecReason_ = "";
                    this.voiceReqID_ = "";
                    this.dialogID_ = "";
                    this.dialogTurnID_ = "";
                    this.bootNumber_ = "";
                    this.sequenceNumber_ = "";
                    maybeForceBuilderInitialization();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: mergeFrom */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ama.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ama.access$8200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Ama r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ama) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Ama r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ama) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ama.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Ama$Builder");
                }
            }

            public static Builder newBuilder(Ama ama) {
                return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(ama);
            }

            public static Ama parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
            }

            private Ama(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            public static Ama parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Ama) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Ama parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.mo8396parseFrom(byteString);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Ama mo10094getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: toBuilder */
            public Builder mo10081toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            public static Ama parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: newBuilderForType */
            public Builder mo10079newBuilderForType() {
                return newBuilder();
            }

            private Ama() {
                this.memoizedIsInitialized = (byte) -1;
                this.accessoryLibraryVersion_ = "";
                this.deviceType_ = "";
                this.deviceSerialNumber_ = "";
                this.firmwareVersionName_ = "";
                this.firmwareName_ = "";
                this.firmwareLocale_ = "";
                this.transportType_ = "";
                this.firmAcc1_ = "";
                this.firmAcc2_ = "";
                this.firmAcc3_ = "";
                this.values_ = "";
                this.accTimestamp_ = 0L;
                this.intentName_ = "";
                this.localExecReason_ = "";
                this.voiceReqID_ = "";
                this.dialogID_ = "";
                this.dialogTurnID_ = "";
                this.bootNumber_ = "";
                this.sequenceNumber_ = "";
            }

            public static Ama parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.mo8404parseFrom(bArr);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: newBuilderForType */
            public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static Ama parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
            }

            public static Ama parseFrom(InputStream inputStream) throws IOException {
                return (Ama) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Ama parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Ama) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Ama parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Ama) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Ama parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Ama) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            private Ama(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite != null) {
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                switch (readTag) {
                                    case 0:
                                        break;
                                    case 10:
                                        this.accessoryLibraryVersion_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 18:
                                        this.deviceType_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 26:
                                        this.deviceSerialNumber_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 34:
                                        this.firmwareVersionName_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 42:
                                        this.firmwareName_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 50:
                                        this.firmwareLocale_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 58:
                                        this.transportType_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 66:
                                        this.firmAcc1_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 74:
                                        this.firmAcc2_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 82:
                                        this.firmAcc3_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 90:
                                        this.values_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 96:
                                        this.accTimestamp_ = codedInputStream.readInt64();
                                        continue;
                                    case 106:
                                        this.intentName_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 114:
                                        this.localExecReason_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 122:
                                        this.voiceReqID_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 130:
                                        this.dialogID_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 138:
                                        this.dialogTurnID_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 146:
                                        this.bootNumber_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 154:
                                        this.sequenceNumber_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    default:
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                            break;
                                        } else {
                                            continue;
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

        /* loaded from: classes9.dex */
        public interface AmaOrBuilder extends MessageOrBuilder {
            long getAccTimestamp();

            String getAccessoryLibraryVersion();

            ByteString getAccessoryLibraryVersionBytes();

            String getBootNumber();

            ByteString getBootNumberBytes();

            String getDeviceSerialNumber();

            ByteString getDeviceSerialNumberBytes();

            String getDeviceType();

            ByteString getDeviceTypeBytes();

            String getDialogID();

            ByteString getDialogIDBytes();

            String getDialogTurnID();

            ByteString getDialogTurnIDBytes();

            String getFirmAcc1();

            ByteString getFirmAcc1Bytes();

            String getFirmAcc2();

            ByteString getFirmAcc2Bytes();

            String getFirmAcc3();

            ByteString getFirmAcc3Bytes();

            String getFirmwareLocale();

            ByteString getFirmwareLocaleBytes();

            String getFirmwareName();

            ByteString getFirmwareNameBytes();

            String getFirmwareVersionName();

            ByteString getFirmwareVersionNameBytes();

            String getIntentName();

            ByteString getIntentNameBytes();

            String getLocalExecReason();

            ByteString getLocalExecReasonBytes();

            String getSequenceNumber();

            ByteString getSequenceNumberBytes();

            String getTransportType();

            ByteString getTransportTypeBytes();

            String getValues();

            ByteString getValuesBytes();

            String getVoiceReqID();

            ByteString getVoiceReqIDBytes();
        }

        /* loaded from: classes9.dex */
        public static final class Ampd extends GeneratedMessageV3 implements AmpdOrBuilder {
            public static final int DESCRIPTION_FIELD_NUMBER = 2;
            public static final int DEVICETYPE_FIELD_NUMBER = 3;
            public static final int DSPAPKVERSION_FIELD_NUMBER = 1;
            public static final int ENROLLMENTTYPE_FIELD_NUMBER = 8;
            public static final int ERRORREASON_FIELD_NUMBER = 12;
            public static final int SVMODELID_FIELD_NUMBER = 4;
            public static final int SVRAWSCORE_FIELD_NUMBER = 9;
            public static final int SVTHRESHOLDLOWER_FIELD_NUMBER = 7;
            public static final int SVTHRESHOLDUPPER_FIELD_NUMBER = 6;
            public static final int WAKEWORDCONFIDENCE_FIELD_NUMBER = 10;
            public static final int WAKEWORDMODELLOCALE_FIELD_NUMBER = 11;
            public static final int WWMODELID_FIELD_NUMBER = 5;
            private static final long serialVersionUID = 0;
            private volatile Object description_;
            private volatile Object deviceType_;
            private volatile Object dspApkVersion_;
            private volatile Object enrollmentType_;
            private volatile Object errorReason_;
            private byte memoizedIsInitialized;
            private volatile Object svModelId_;
            private volatile Object svRawScore_;
            private volatile Object svThresholdLower_;
            private volatile Object svThresholdUpper_;
            private volatile Object wakeWordConfidence_;
            private volatile Object wakeWordModelLocale_;
            private volatile Object wwModelId_;
            private static final Ampd DEFAULT_INSTANCE = new Ampd();
            private static final Parser<Ampd> PARSER = new AbstractParser<Ampd>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ampd.1
                @Override // com.google.protobuf.Parser
                /* renamed from: parsePartialFrom */
                public Ampd mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Ampd(codedInputStream, extensionRegistryLite);
                }
            };

            public static Ampd getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ampd_descriptor;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.mo10081toBuilder();
            }

            public static Ampd parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Ampd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Ampd parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.mo8402parseFrom(byteBuffer);
            }

            public static Parser<Ampd> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Ampd)) {
                    return super.equals(obj);
                }
                Ampd ampd = (Ampd) obj;
                return ((((((((((((getDspApkVersion().equals(ampd.getDspApkVersion())) && getDescription().equals(ampd.getDescription())) && getDeviceType().equals(ampd.getDeviceType())) && getSvModelId().equals(ampd.getSvModelId())) && getWwModelId().equals(ampd.getWwModelId())) && getSvThresholdUpper().equals(ampd.getSvThresholdUpper())) && getSvThresholdLower().equals(ampd.getSvThresholdLower())) && getEnrollmentType().equals(ampd.getEnrollmentType())) && getSvRawScore().equals(ampd.getSvRawScore())) && getWakeWordConfidence().equals(ampd.getWakeWordConfidence())) && getWakeWordModelLocale().equals(ampd.getWakeWordModelLocale())) && getErrorReason().equals(ampd.getErrorReason())) && this.unknownFields.equals(ampd.unknownFields);
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getDescription() {
                Object obj = this.description_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getDescriptionBytes() {
                Object obj = this.description_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.description_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getDeviceType() {
                Object obj = this.deviceType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.deviceType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getDeviceTypeBytes() {
                Object obj = this.deviceType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getDspApkVersion() {
                Object obj = this.dspApkVersion_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.dspApkVersion_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getDspApkVersionBytes() {
                Object obj = this.dspApkVersion_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dspApkVersion_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getEnrollmentType() {
                Object obj = this.enrollmentType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.enrollmentType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getEnrollmentTypeBytes() {
                Object obj = this.enrollmentType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.enrollmentType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getErrorReason() {
                Object obj = this.errorReason_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.errorReason_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getErrorReasonBytes() {
                Object obj = this.errorReason_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.errorReason_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: getParserForType */
            public Parser<Ampd> mo9954getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if (!getDspApkVersionBytes().isEmpty()) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.dspApkVersion_);
                }
                if (!getDescriptionBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(2, this.description_);
                }
                if (!getDeviceTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(3, this.deviceType_);
                }
                if (!getSvModelIdBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(4, this.svModelId_);
                }
                if (!getWwModelIdBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(5, this.wwModelId_);
                }
                if (!getSvThresholdUpperBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(6, this.svThresholdUpper_);
                }
                if (!getSvThresholdLowerBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(7, this.svThresholdLower_);
                }
                if (!getEnrollmentTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(8, this.enrollmentType_);
                }
                if (!getSvRawScoreBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(9, this.svRawScore_);
                }
                if (!getWakeWordConfidenceBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(10, this.wakeWordConfidence_);
                }
                if (!getWakeWordModelLocaleBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(11, this.wakeWordModelLocale_);
                }
                if (!getErrorReasonBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(12, this.errorReason_);
                }
                int serializedSize = this.unknownFields.getSerializedSize() + i2;
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getSvModelId() {
                Object obj = this.svModelId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.svModelId_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getSvModelIdBytes() {
                Object obj = this.svModelId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.svModelId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getSvRawScore() {
                Object obj = this.svRawScore_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.svRawScore_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getSvRawScoreBytes() {
                Object obj = this.svRawScore_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.svRawScore_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getSvThresholdLower() {
                Object obj = this.svThresholdLower_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.svThresholdLower_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getSvThresholdLowerBytes() {
                Object obj = this.svThresholdLower_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.svThresholdLower_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getSvThresholdUpper() {
                Object obj = this.svThresholdUpper_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.svThresholdUpper_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getSvThresholdUpperBytes() {
                Object obj = this.svThresholdUpper_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.svThresholdUpper_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getWakeWordConfidence() {
                Object obj = this.wakeWordConfidence_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.wakeWordConfidence_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getWakeWordConfidenceBytes() {
                Object obj = this.wakeWordConfidence_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.wakeWordConfidence_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getWakeWordModelLocale() {
                Object obj = this.wakeWordModelLocale_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.wakeWordModelLocale_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getWakeWordModelLocaleBytes() {
                Object obj = this.wakeWordModelLocale_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.wakeWordModelLocale_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public String getWwModelId() {
                Object obj = this.wwModelId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.wwModelId_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
            public ByteString getWwModelIdBytes() {
                Object obj = this.wwModelId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.wwModelId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = getDspApkVersion().hashCode();
                int hashCode2 = getDescription().hashCode();
                int hashCode3 = getDeviceType().hashCode();
                int hashCode4 = getSvModelId().hashCode();
                int hashCode5 = getWwModelId().hashCode();
                int hashCode6 = getSvThresholdUpper().hashCode();
                int hashCode7 = getSvThresholdLower().hashCode();
                int hashCode8 = getEnrollmentType().hashCode();
                int hashCode9 = getSvRawScore().hashCode();
                int hashCode10 = getWakeWordConfidence().hashCode();
                int hashCode11 = getWakeWordModelLocale().hashCode();
                int hashCode12 = getErrorReason().hashCode();
                int hashCode13 = this.unknownFields.hashCode() + ((hashCode12 + ((((hashCode11 + ((((hashCode10 + ((((hashCode9 + ((((hashCode8 + ((((hashCode7 + ((((hashCode6 + ((((hashCode5 + ((((hashCode4 + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 37) + 6) * 53)) * 37) + 7) * 53)) * 37) + 8) * 53)) * 37) + 9) * 53)) * 37) + 10) * 53)) * 37) + 11) * 53)) * 37) + 12) * 53)) * 29);
                this.memoizedHashCode = hashCode13;
                return hashCode13;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ampd_fieldAccessorTable.ensureFieldAccessorsInitialized(Ampd.class, Builder.class);
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
                if (!getDspApkVersionBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.dspApkVersion_);
                }
                if (!getDescriptionBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.description_);
                }
                if (!getDeviceTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.deviceType_);
                }
                if (!getSvModelIdBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.svModelId_);
                }
                if (!getWwModelIdBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 5, this.wwModelId_);
                }
                if (!getSvThresholdUpperBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 6, this.svThresholdUpper_);
                }
                if (!getSvThresholdLowerBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 7, this.svThresholdLower_);
                }
                if (!getEnrollmentTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 8, this.enrollmentType_);
                }
                if (!getSvRawScoreBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 9, this.svRawScore_);
                }
                if (!getWakeWordConfidenceBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 10, this.wakeWordConfidence_);
                }
                if (!getWakeWordModelLocaleBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 11, this.wakeWordModelLocale_);
                }
                if (!getErrorReasonBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 12, this.errorReason_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            /* loaded from: classes9.dex */
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AmpdOrBuilder {
                private Object description_;
                private Object deviceType_;
                private Object dspApkVersion_;
                private Object enrollmentType_;
                private Object errorReason_;
                private Object svModelId_;
                private Object svRawScore_;
                private Object svThresholdLower_;
                private Object svThresholdUpper_;
                private Object wakeWordConfidence_;
                private Object wakeWordModelLocale_;
                private Object wwModelId_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ampd_descriptor;
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                public Builder clearDescription() {
                    this.description_ = Ampd.getDefaultInstance().getDescription();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceType() {
                    this.deviceType_ = Ampd.getDefaultInstance().getDeviceType();
                    onChanged();
                    return this;
                }

                public Builder clearDspApkVersion() {
                    this.dspApkVersion_ = Ampd.getDefaultInstance().getDspApkVersion();
                    onChanged();
                    return this;
                }

                public Builder clearEnrollmentType() {
                    this.enrollmentType_ = Ampd.getDefaultInstance().getEnrollmentType();
                    onChanged();
                    return this;
                }

                public Builder clearErrorReason() {
                    this.errorReason_ = Ampd.getDefaultInstance().getErrorReason();
                    onChanged();
                    return this;
                }

                public Builder clearSvModelId() {
                    this.svModelId_ = Ampd.getDefaultInstance().getSvModelId();
                    onChanged();
                    return this;
                }

                public Builder clearSvRawScore() {
                    this.svRawScore_ = Ampd.getDefaultInstance().getSvRawScore();
                    onChanged();
                    return this;
                }

                public Builder clearSvThresholdLower() {
                    this.svThresholdLower_ = Ampd.getDefaultInstance().getSvThresholdLower();
                    onChanged();
                    return this;
                }

                public Builder clearSvThresholdUpper() {
                    this.svThresholdUpper_ = Ampd.getDefaultInstance().getSvThresholdUpper();
                    onChanged();
                    return this;
                }

                public Builder clearWakeWordConfidence() {
                    this.wakeWordConfidence_ = Ampd.getDefaultInstance().getWakeWordConfidence();
                    onChanged();
                    return this;
                }

                public Builder clearWakeWordModelLocale() {
                    this.wakeWordModelLocale_ = Ampd.getDefaultInstance().getWakeWordModelLocale();
                    onChanged();
                    return this;
                }

                public Builder clearWwModelId() {
                    this.wwModelId_ = Ampd.getDefaultInstance().getWwModelId();
                    onChanged();
                    return this;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getDescription() {
                    Object obj = this.description_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.description_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getDescriptionBytes() {
                    Object obj = this.description_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.description_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ampd_descriptor;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getDeviceType() {
                    Object obj = this.deviceType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.deviceType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getDeviceTypeBytes() {
                    Object obj = this.deviceType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.deviceType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getDspApkVersion() {
                    Object obj = this.dspApkVersion_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.dspApkVersion_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getDspApkVersionBytes() {
                    Object obj = this.dspApkVersion_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.dspApkVersion_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getEnrollmentType() {
                    Object obj = this.enrollmentType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.enrollmentType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getEnrollmentTypeBytes() {
                    Object obj = this.enrollmentType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.enrollmentType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getErrorReason() {
                    Object obj = this.errorReason_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.errorReason_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getErrorReasonBytes() {
                    Object obj = this.errorReason_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.errorReason_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getSvModelId() {
                    Object obj = this.svModelId_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.svModelId_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getSvModelIdBytes() {
                    Object obj = this.svModelId_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.svModelId_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getSvRawScore() {
                    Object obj = this.svRawScore_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.svRawScore_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getSvRawScoreBytes() {
                    Object obj = this.svRawScore_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.svRawScore_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getSvThresholdLower() {
                    Object obj = this.svThresholdLower_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.svThresholdLower_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getSvThresholdLowerBytes() {
                    Object obj = this.svThresholdLower_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.svThresholdLower_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getSvThresholdUpper() {
                    Object obj = this.svThresholdUpper_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.svThresholdUpper_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getSvThresholdUpperBytes() {
                    Object obj = this.svThresholdUpper_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.svThresholdUpper_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getWakeWordConfidence() {
                    Object obj = this.wakeWordConfidence_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.wakeWordConfidence_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getWakeWordConfidenceBytes() {
                    Object obj = this.wakeWordConfidence_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.wakeWordConfidence_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getWakeWordModelLocale() {
                    Object obj = this.wakeWordModelLocale_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.wakeWordModelLocale_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getWakeWordModelLocaleBytes() {
                    Object obj = this.wakeWordModelLocale_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.wakeWordModelLocale_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public String getWwModelId() {
                    Object obj = this.wwModelId_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.wwModelId_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.AmpdOrBuilder
                public ByteString getWwModelIdBytes() {
                    Object obj = this.wwModelId_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.wwModelId_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Ampd_fieldAccessorTable.ensureFieldAccessorsInitialized(Ampd.class, Builder.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public Builder setDescription(String str) {
                    if (str != null) {
                        this.description_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDescriptionBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.description_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceType(String str) {
                    if (str != null) {
                        this.deviceType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.deviceType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDspApkVersion(String str) {
                    if (str != null) {
                        this.dspApkVersion_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDspApkVersionBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.dspApkVersion_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setEnrollmentType(String str) {
                    if (str != null) {
                        this.enrollmentType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setEnrollmentTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.enrollmentType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setErrorReason(String str) {
                    if (str != null) {
                        this.errorReason_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setErrorReasonBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.errorReason_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSvModelId(String str) {
                    if (str != null) {
                        this.svModelId_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSvModelIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.svModelId_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSvRawScore(String str) {
                    if (str != null) {
                        this.svRawScore_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSvRawScoreBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.svRawScore_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSvThresholdLower(String str) {
                    if (str != null) {
                        this.svThresholdLower_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSvThresholdLowerBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.svThresholdLower_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSvThresholdUpper(String str) {
                    if (str != null) {
                        this.svThresholdUpper_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSvThresholdUpperBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.svThresholdUpper_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setWakeWordConfidence(String str) {
                    if (str != null) {
                        this.wakeWordConfidence_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setWakeWordConfidenceBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.wakeWordConfidence_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setWakeWordModelLocale(String str) {
                    if (str != null) {
                        this.wakeWordModelLocale_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setWakeWordModelLocaleBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.wakeWordModelLocale_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setWwModelId(String str) {
                    if (str != null) {
                        this.wwModelId_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setWwModelIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.wwModelId_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                private Builder() {
                    this.dspApkVersion_ = "";
                    this.description_ = "";
                    this.deviceType_ = "";
                    this.svModelId_ = "";
                    this.wwModelId_ = "";
                    this.svThresholdUpper_ = "";
                    this.svThresholdLower_ = "";
                    this.enrollmentType_ = "";
                    this.svRawScore_ = "";
                    this.wakeWordConfidence_ = "";
                    this.wakeWordModelLocale_ = "";
                    this.errorReason_ = "";
                    maybeForceBuilderInitialization();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: addRepeatedField */
                public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: build */
                public Ampd mo10084build() {
                    Ampd mo10085buildPartial = mo10085buildPartial();
                    if (mo10085buildPartial.isInitialized()) {
                        return mo10085buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: buildPartial */
                public Ampd mo10085buildPartial() {
                    Ampd ampd = new Ampd(this);
                    ampd.dspApkVersion_ = this.dspApkVersion_;
                    ampd.description_ = this.description_;
                    ampd.deviceType_ = this.deviceType_;
                    ampd.svModelId_ = this.svModelId_;
                    ampd.wwModelId_ = this.wwModelId_;
                    ampd.svThresholdUpper_ = this.svThresholdUpper_;
                    ampd.svThresholdLower_ = this.svThresholdLower_;
                    ampd.enrollmentType_ = this.enrollmentType_;
                    ampd.svRawScore_ = this.svRawScore_;
                    ampd.wakeWordConfidence_ = this.wakeWordConfidence_;
                    ampd.wakeWordModelLocale_ = this.wakeWordModelLocale_;
                    ampd.errorReason_ = this.errorReason_;
                    onBuilt();
                    return ampd;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: clearField */
                public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.mo10088clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: getDefaultInstanceForType */
                public Ampd mo10094getDefaultInstanceForType() {
                    return Ampd.getDefaultInstance();
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
                    this.dspApkVersion_ = "";
                    this.description_ = "";
                    this.deviceType_ = "";
                    this.svModelId_ = "";
                    this.wwModelId_ = "";
                    this.svThresholdUpper_ = "";
                    this.svThresholdLower_ = "";
                    this.enrollmentType_ = "";
                    this.svRawScore_ = "";
                    this.wakeWordConfidence_ = "";
                    this.wakeWordModelLocale_ = "";
                    this.errorReason_ = "";
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
                    if (message instanceof Ampd) {
                        return mergeFrom((Ampd) message);
                    }
                    super.mo10096mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Ampd ampd) {
                    if (ampd == Ampd.getDefaultInstance()) {
                        return this;
                    }
                    if (!ampd.getDspApkVersion().isEmpty()) {
                        this.dspApkVersion_ = ampd.dspApkVersion_;
                        onChanged();
                    }
                    if (!ampd.getDescription().isEmpty()) {
                        this.description_ = ampd.description_;
                        onChanged();
                    }
                    if (!ampd.getDeviceType().isEmpty()) {
                        this.deviceType_ = ampd.deviceType_;
                        onChanged();
                    }
                    if (!ampd.getSvModelId().isEmpty()) {
                        this.svModelId_ = ampd.svModelId_;
                        onChanged();
                    }
                    if (!ampd.getWwModelId().isEmpty()) {
                        this.wwModelId_ = ampd.wwModelId_;
                        onChanged();
                    }
                    if (!ampd.getSvThresholdUpper().isEmpty()) {
                        this.svThresholdUpper_ = ampd.svThresholdUpper_;
                        onChanged();
                    }
                    if (!ampd.getSvThresholdLower().isEmpty()) {
                        this.svThresholdLower_ = ampd.svThresholdLower_;
                        onChanged();
                    }
                    if (!ampd.getEnrollmentType().isEmpty()) {
                        this.enrollmentType_ = ampd.enrollmentType_;
                        onChanged();
                    }
                    if (!ampd.getSvRawScore().isEmpty()) {
                        this.svRawScore_ = ampd.svRawScore_;
                        onChanged();
                    }
                    if (!ampd.getWakeWordConfidence().isEmpty()) {
                        this.wakeWordConfidence_ = ampd.wakeWordConfidence_;
                        onChanged();
                    }
                    if (!ampd.getWakeWordModelLocale().isEmpty()) {
                        this.wakeWordModelLocale_ = ampd.wakeWordModelLocale_;
                        onChanged();
                    }
                    if (!ampd.getErrorReason().isEmpty()) {
                        this.errorReason_ = ampd.errorReason_;
                        onChanged();
                    }
                    mo10099mergeUnknownFields(((GeneratedMessageV3) ampd).unknownFields);
                    onChanged();
                    return this;
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.dspApkVersion_ = "";
                    this.description_ = "";
                    this.deviceType_ = "";
                    this.svModelId_ = "";
                    this.wwModelId_ = "";
                    this.svThresholdUpper_ = "";
                    this.svThresholdLower_ = "";
                    this.enrollmentType_ = "";
                    this.svRawScore_ = "";
                    this.wakeWordConfidence_ = "";
                    this.wakeWordModelLocale_ = "";
                    this.errorReason_ = "";
                    maybeForceBuilderInitialization();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: mergeFrom */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ampd.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ampd.access$11900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Ampd r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ampd) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Ampd r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ampd) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Ampd.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Ampd$Builder");
                }
            }

            public static Builder newBuilder(Ampd ampd) {
                return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(ampd);
            }

            public static Ampd parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
            }

            private Ampd(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            public static Ampd parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Ampd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Ampd parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.mo8396parseFrom(byteString);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Ampd mo10094getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: toBuilder */
            public Builder mo10081toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            public static Ampd parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: newBuilderForType */
            public Builder mo10079newBuilderForType() {
                return newBuilder();
            }

            private Ampd() {
                this.memoizedIsInitialized = (byte) -1;
                this.dspApkVersion_ = "";
                this.description_ = "";
                this.deviceType_ = "";
                this.svModelId_ = "";
                this.wwModelId_ = "";
                this.svThresholdUpper_ = "";
                this.svThresholdLower_ = "";
                this.enrollmentType_ = "";
                this.svRawScore_ = "";
                this.wakeWordConfidence_ = "";
                this.wakeWordModelLocale_ = "";
                this.errorReason_ = "";
            }

            public static Ampd parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.mo8404parseFrom(bArr);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: newBuilderForType */
            public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static Ampd parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
            }

            public static Ampd parseFrom(InputStream inputStream) throws IOException {
                return (Ampd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Ampd parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Ampd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Ampd parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Ampd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Ampd parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Ampd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            private Ampd(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite != null) {
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                try {
                                    int readTag = codedInputStream.readTag();
                                    switch (readTag) {
                                        case 0:
                                            break;
                                        case 10:
                                            this.dspApkVersion_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 18:
                                            this.description_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 26:
                                            this.deviceType_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 34:
                                            this.svModelId_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 42:
                                            this.wwModelId_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 50:
                                            this.svThresholdUpper_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 58:
                                            this.svThresholdLower_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 66:
                                            this.enrollmentType_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 74:
                                            this.svRawScore_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 82:
                                            this.wakeWordConfidence_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 90:
                                            this.wakeWordModelLocale_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 98:
                                            this.errorReason_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        default:
                                            if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                                break;
                                            } else {
                                                continue;
                                            }
                                    }
                                    z = true;
                                } catch (IOException e) {
                                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                                }
                            } catch (InvalidProtocolBufferException e2) {
                                throw e2.setUnfinishedMessage(this);
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

        /* loaded from: classes9.dex */
        public interface AmpdOrBuilder extends MessageOrBuilder {
            String getDescription();

            ByteString getDescriptionBytes();

            String getDeviceType();

            ByteString getDeviceTypeBytes();

            String getDspApkVersion();

            ByteString getDspApkVersionBytes();

            String getEnrollmentType();

            ByteString getEnrollmentTypeBytes();

            String getErrorReason();

            ByteString getErrorReasonBytes();

            String getSvModelId();

            ByteString getSvModelIdBytes();

            String getSvRawScore();

            ByteString getSvRawScoreBytes();

            String getSvThresholdLower();

            ByteString getSvThresholdLowerBytes();

            String getSvThresholdUpper();

            ByteString getSvThresholdUpperBytes();

            String getWakeWordConfidence();

            ByteString getWakeWordConfidenceBytes();

            String getWakeWordModelLocale();

            ByteString getWakeWordModelLocaleBytes();

            String getWwModelId();

            ByteString getWwModelIdBytes();
        }

        /* loaded from: classes9.dex */
        public static final class Comms extends GeneratedMessageV3 implements CommsOrBuilder {
            public static final int CALLTYPE_FIELD_NUMBER = 1;
            public static final int DIRECTION_FIELD_NUMBER = 2;
            public static final int DURATION_FIELD_NUMBER = 3;
            public static final int MEDIATYPE_FIELD_NUMBER = 4;
            public static final int MESSAGETYPE_FIELD_NUMBER = 5;
            public static final int METADATATYPE_FIELD_NUMBER = 11;
            public static final int REQUESTID_FIELD_NUMBER = 6;
            public static final int SIZE_FIELD_NUMBER = 7;
            public static final int STATUSCODE_FIELD_NUMBER = 8;
            public static final int TARGETTYPE_FIELD_NUMBER = 9;
            public static final int TRANSPORT_FIELD_NUMBER = 10;
            public static final int TRICKLEICEENABLED_FIELD_NUMBER = 12;
            private static final long serialVersionUID = 0;
            private volatile Object callType_;
            private volatile Object direction_;
            private long duration_;
            private volatile Object mediaType_;
            private byte memoizedIsInitialized;
            private volatile Object messageType_;
            private volatile Object metadataType_;
            private volatile Object requestId_;
            private long size_;
            private long statusCode_;
            private volatile Object targetType_;
            private volatile Object transport_;
            private volatile Object trickleIceEnabled_;
            private static final Comms DEFAULT_INSTANCE = new Comms();
            private static final Parser<Comms> PARSER = new AbstractParser<Comms>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Comms.1
                @Override // com.google.protobuf.Parser
                /* renamed from: parsePartialFrom */
                public Comms mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Comms(codedInputStream, extensionRegistryLite);
                }
            };

            public static Comms getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Comms_descriptor;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.mo10081toBuilder();
            }

            public static Comms parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Comms) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Comms parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.mo8402parseFrom(byteBuffer);
            }

            public static Parser<Comms> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Comms)) {
                    return super.equals(obj);
                }
                Comms comms = (Comms) obj;
                return ((((((((((((getCallType().equals(comms.getCallType())) && getDirection().equals(comms.getDirection())) && (getDuration() > comms.getDuration() ? 1 : (getDuration() == comms.getDuration() ? 0 : -1)) == 0) && getMediaType().equals(comms.getMediaType())) && getMessageType().equals(comms.getMessageType())) && getRequestId().equals(comms.getRequestId())) && (getSize() > comms.getSize() ? 1 : (getSize() == comms.getSize() ? 0 : -1)) == 0) && (getStatusCode() > comms.getStatusCode() ? 1 : (getStatusCode() == comms.getStatusCode() ? 0 : -1)) == 0) && getTargetType().equals(comms.getTargetType())) && getTransport().equals(comms.getTransport())) && getMetadataType().equals(comms.getMetadataType())) && getTrickleIceEnabled().equals(comms.getTrickleIceEnabled())) && this.unknownFields.equals(comms.unknownFields);
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public String getCallType() {
                Object obj = this.callType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.callType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public ByteString getCallTypeBytes() {
                Object obj = this.callType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.callType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public String getDirection() {
                Object obj = this.direction_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.direction_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public ByteString getDirectionBytes() {
                Object obj = this.direction_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.direction_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public long getDuration() {
                return this.duration_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public String getMediaType() {
                Object obj = this.mediaType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.mediaType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public ByteString getMediaTypeBytes() {
                Object obj = this.mediaType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.mediaType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public String getMessageType() {
                Object obj = this.messageType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.messageType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public ByteString getMessageTypeBytes() {
                Object obj = this.messageType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.messageType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public String getMetadataType() {
                Object obj = this.metadataType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.metadataType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public ByteString getMetadataTypeBytes() {
                Object obj = this.metadataType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.metadataType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: getParserForType */
            public Parser<Comms> mo9954getParserForType() {
                return PARSER;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public String getRequestId() {
                Object obj = this.requestId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.requestId_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public ByteString getRequestIdBytes() {
                Object obj = this.requestId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.requestId_ = copyFromUtf8;
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
                if (!getCallTypeBytes().isEmpty()) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.callType_);
                }
                if (!getDirectionBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(2, this.direction_);
                }
                long j = this.duration_;
                if (j != 0) {
                    i2 += CodedOutputStream.computeInt64Size(3, j);
                }
                if (!getMediaTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(4, this.mediaType_);
                }
                if (!getMessageTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(5, this.messageType_);
                }
                if (!getRequestIdBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(6, this.requestId_);
                }
                long j2 = this.size_;
                if (j2 != 0) {
                    i2 += CodedOutputStream.computeInt64Size(7, j2);
                }
                long j3 = this.statusCode_;
                if (j3 != 0) {
                    i2 += CodedOutputStream.computeInt64Size(8, j3);
                }
                if (!getTargetTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(9, this.targetType_);
                }
                if (!getTransportBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(10, this.transport_);
                }
                if (!getMetadataTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(11, this.metadataType_);
                }
                if (!getTrickleIceEnabledBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(12, this.trickleIceEnabled_);
                }
                int serializedSize = this.unknownFields.getSerializedSize() + i2;
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public long getSize() {
                return this.size_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public long getStatusCode() {
                return this.statusCode_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public String getTargetType() {
                Object obj = this.targetType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.targetType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public ByteString getTargetTypeBytes() {
                Object obj = this.targetType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.targetType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public String getTransport() {
                Object obj = this.transport_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.transport_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public ByteString getTransportBytes() {
                Object obj = this.transport_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.transport_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public String getTrickleIceEnabled() {
                Object obj = this.trickleIceEnabled_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.trickleIceEnabled_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
            public ByteString getTrickleIceEnabledBytes() {
                Object obj = this.trickleIceEnabled_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.trickleIceEnabled_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
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
                int hashCode = getCallType().hashCode();
                int hashCode2 = getDirection().hashCode();
                int hashLong = Internal.hashLong(getDuration());
                int hashCode3 = getMediaType().hashCode();
                int hashCode4 = getMessageType().hashCode();
                int hashCode5 = getRequestId().hashCode();
                int hashLong2 = Internal.hashLong(getSize());
                int hashLong3 = Internal.hashLong(getStatusCode());
                int hashCode6 = getTargetType().hashCode();
                int hashCode7 = getTransport().hashCode();
                int hashCode8 = getMetadataType().hashCode();
                int hashCode9 = getTrickleIceEnabled().hashCode();
                int hashCode10 = this.unknownFields.hashCode() + ((hashCode9 + ((((hashCode8 + ((((hashCode7 + ((((hashCode6 + ((((hashLong3 + ((((hashLong2 + ((((hashCode5 + ((((hashCode4 + ((((hashCode3 + ((((hashLong + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 37) + 6) * 53)) * 37) + 7) * 53)) * 37) + 8) * 53)) * 37) + 9) * 53)) * 37) + 10) * 53)) * 37) + 11) * 53)) * 37) + 12) * 53)) * 29);
                this.memoizedHashCode = hashCode10;
                return hashCode10;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Comms_fieldAccessorTable.ensureFieldAccessorsInitialized(Comms.class, Builder.class);
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
                if (!getCallTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.callType_);
                }
                if (!getDirectionBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.direction_);
                }
                long j = this.duration_;
                if (j != 0) {
                    codedOutputStream.writeInt64(3, j);
                }
                if (!getMediaTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.mediaType_);
                }
                if (!getMessageTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 5, this.messageType_);
                }
                if (!getRequestIdBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 6, this.requestId_);
                }
                long j2 = this.size_;
                if (j2 != 0) {
                    codedOutputStream.writeInt64(7, j2);
                }
                long j3 = this.statusCode_;
                if (j3 != 0) {
                    codedOutputStream.writeInt64(8, j3);
                }
                if (!getTargetTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 9, this.targetType_);
                }
                if (!getTransportBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 10, this.transport_);
                }
                if (!getMetadataTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 11, this.metadataType_);
                }
                if (!getTrickleIceEnabledBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 12, this.trickleIceEnabled_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            /* loaded from: classes9.dex */
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CommsOrBuilder {
                private Object callType_;
                private Object direction_;
                private long duration_;
                private Object mediaType_;
                private Object messageType_;
                private Object metadataType_;
                private Object requestId_;
                private long size_;
                private long statusCode_;
                private Object targetType_;
                private Object transport_;
                private Object trickleIceEnabled_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Comms_descriptor;
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                public Builder clearCallType() {
                    this.callType_ = Comms.getDefaultInstance().getCallType();
                    onChanged();
                    return this;
                }

                public Builder clearDirection() {
                    this.direction_ = Comms.getDefaultInstance().getDirection();
                    onChanged();
                    return this;
                }

                public Builder clearDuration() {
                    this.duration_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearMediaType() {
                    this.mediaType_ = Comms.getDefaultInstance().getMediaType();
                    onChanged();
                    return this;
                }

                public Builder clearMessageType() {
                    this.messageType_ = Comms.getDefaultInstance().getMessageType();
                    onChanged();
                    return this;
                }

                public Builder clearMetadataType() {
                    this.metadataType_ = Comms.getDefaultInstance().getMetadataType();
                    onChanged();
                    return this;
                }

                public Builder clearRequestId() {
                    this.requestId_ = Comms.getDefaultInstance().getRequestId();
                    onChanged();
                    return this;
                }

                public Builder clearSize() {
                    this.size_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearStatusCode() {
                    this.statusCode_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearTargetType() {
                    this.targetType_ = Comms.getDefaultInstance().getTargetType();
                    onChanged();
                    return this;
                }

                public Builder clearTransport() {
                    this.transport_ = Comms.getDefaultInstance().getTransport();
                    onChanged();
                    return this;
                }

                public Builder clearTrickleIceEnabled() {
                    this.trickleIceEnabled_ = Comms.getDefaultInstance().getTrickleIceEnabled();
                    onChanged();
                    return this;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public String getCallType() {
                    Object obj = this.callType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.callType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public ByteString getCallTypeBytes() {
                    Object obj = this.callType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.callType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Comms_descriptor;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public String getDirection() {
                    Object obj = this.direction_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.direction_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public ByteString getDirectionBytes() {
                    Object obj = this.direction_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.direction_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public long getDuration() {
                    return this.duration_;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public String getMediaType() {
                    Object obj = this.mediaType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.mediaType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public ByteString getMediaTypeBytes() {
                    Object obj = this.mediaType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.mediaType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public String getMessageType() {
                    Object obj = this.messageType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.messageType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public ByteString getMessageTypeBytes() {
                    Object obj = this.messageType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.messageType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public String getMetadataType() {
                    Object obj = this.metadataType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.metadataType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public ByteString getMetadataTypeBytes() {
                    Object obj = this.metadataType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.metadataType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public String getRequestId() {
                    Object obj = this.requestId_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.requestId_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public ByteString getRequestIdBytes() {
                    Object obj = this.requestId_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.requestId_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public long getSize() {
                    return this.size_;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public long getStatusCode() {
                    return this.statusCode_;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public String getTargetType() {
                    Object obj = this.targetType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.targetType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public ByteString getTargetTypeBytes() {
                    Object obj = this.targetType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.targetType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public String getTransport() {
                    Object obj = this.transport_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.transport_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public ByteString getTransportBytes() {
                    Object obj = this.transport_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.transport_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public String getTrickleIceEnabled() {
                    Object obj = this.trickleIceEnabled_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.trickleIceEnabled_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.CommsOrBuilder
                public ByteString getTrickleIceEnabledBytes() {
                    Object obj = this.trickleIceEnabled_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.trickleIceEnabled_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Comms_fieldAccessorTable.ensureFieldAccessorsInitialized(Comms.class, Builder.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public Builder setCallType(String str) {
                    if (str != null) {
                        this.callType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setCallTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.callType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDirection(String str) {
                    if (str != null) {
                        this.direction_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDirectionBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.direction_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDuration(long j) {
                    this.duration_ = j;
                    onChanged();
                    return this;
                }

                public Builder setMediaType(String str) {
                    if (str != null) {
                        this.mediaType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMediaTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.mediaType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMessageType(String str) {
                    if (str != null) {
                        this.messageType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMessageTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.messageType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMetadataType(String str) {
                    if (str != null) {
                        this.metadataType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMetadataTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.metadataType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setRequestId(String str) {
                    if (str != null) {
                        this.requestId_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setRequestIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.requestId_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSize(long j) {
                    this.size_ = j;
                    onChanged();
                    return this;
                }

                public Builder setStatusCode(long j) {
                    this.statusCode_ = j;
                    onChanged();
                    return this;
                }

                public Builder setTargetType(String str) {
                    if (str != null) {
                        this.targetType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTargetTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.targetType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTransport(String str) {
                    if (str != null) {
                        this.transport_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTransportBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.transport_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTrickleIceEnabled(String str) {
                    if (str != null) {
                        this.trickleIceEnabled_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTrickleIceEnabledBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.trickleIceEnabled_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                private Builder() {
                    this.callType_ = "";
                    this.direction_ = "";
                    this.mediaType_ = "";
                    this.messageType_ = "";
                    this.requestId_ = "";
                    this.targetType_ = "";
                    this.transport_ = "";
                    this.metadataType_ = "";
                    this.trickleIceEnabled_ = "";
                    maybeForceBuilderInitialization();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: addRepeatedField */
                public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: build */
                public Comms mo10084build() {
                    Comms mo10085buildPartial = mo10085buildPartial();
                    if (mo10085buildPartial.isInitialized()) {
                        return mo10085buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: buildPartial */
                public Comms mo10085buildPartial() {
                    Comms comms = new Comms(this);
                    comms.callType_ = this.callType_;
                    comms.direction_ = this.direction_;
                    comms.duration_ = this.duration_;
                    comms.mediaType_ = this.mediaType_;
                    comms.messageType_ = this.messageType_;
                    comms.requestId_ = this.requestId_;
                    comms.size_ = this.size_;
                    comms.statusCode_ = this.statusCode_;
                    comms.targetType_ = this.targetType_;
                    comms.transport_ = this.transport_;
                    comms.metadataType_ = this.metadataType_;
                    comms.trickleIceEnabled_ = this.trickleIceEnabled_;
                    onBuilt();
                    return comms;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: clearField */
                public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.mo10088clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: getDefaultInstanceForType */
                public Comms mo10094getDefaultInstanceForType() {
                    return Comms.getDefaultInstance();
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
                    this.callType_ = "";
                    this.direction_ = "";
                    this.duration_ = 0L;
                    this.mediaType_ = "";
                    this.messageType_ = "";
                    this.requestId_ = "";
                    this.size_ = 0L;
                    this.statusCode_ = 0L;
                    this.targetType_ = "";
                    this.transport_ = "";
                    this.metadataType_ = "";
                    this.trickleIceEnabled_ = "";
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
                    if (message instanceof Comms) {
                        return mergeFrom((Comms) message);
                    }
                    super.mo10096mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Comms comms) {
                    if (comms == Comms.getDefaultInstance()) {
                        return this;
                    }
                    if (!comms.getCallType().isEmpty()) {
                        this.callType_ = comms.callType_;
                        onChanged();
                    }
                    if (!comms.getDirection().isEmpty()) {
                        this.direction_ = comms.direction_;
                        onChanged();
                    }
                    if (comms.getDuration() != 0) {
                        setDuration(comms.getDuration());
                    }
                    if (!comms.getMediaType().isEmpty()) {
                        this.mediaType_ = comms.mediaType_;
                        onChanged();
                    }
                    if (!comms.getMessageType().isEmpty()) {
                        this.messageType_ = comms.messageType_;
                        onChanged();
                    }
                    if (!comms.getRequestId().isEmpty()) {
                        this.requestId_ = comms.requestId_;
                        onChanged();
                    }
                    if (comms.getSize() != 0) {
                        setSize(comms.getSize());
                    }
                    if (comms.getStatusCode() != 0) {
                        setStatusCode(comms.getStatusCode());
                    }
                    if (!comms.getTargetType().isEmpty()) {
                        this.targetType_ = comms.targetType_;
                        onChanged();
                    }
                    if (!comms.getTransport().isEmpty()) {
                        this.transport_ = comms.transport_;
                        onChanged();
                    }
                    if (!comms.getMetadataType().isEmpty()) {
                        this.metadataType_ = comms.metadataType_;
                        onChanged();
                    }
                    if (!comms.getTrickleIceEnabled().isEmpty()) {
                        this.trickleIceEnabled_ = comms.trickleIceEnabled_;
                        onChanged();
                    }
                    mo10099mergeUnknownFields(((GeneratedMessageV3) comms).unknownFields);
                    onChanged();
                    return this;
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.callType_ = "";
                    this.direction_ = "";
                    this.mediaType_ = "";
                    this.messageType_ = "";
                    this.requestId_ = "";
                    this.targetType_ = "";
                    this.transport_ = "";
                    this.metadataType_ = "";
                    this.trickleIceEnabled_ = "";
                    maybeForceBuilderInitialization();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: mergeFrom */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Comms.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Comms.access$4700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Comms r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Comms) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Comms r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Comms) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Comms.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Comms$Builder");
                }
            }

            public static Builder newBuilder(Comms comms) {
                return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(comms);
            }

            public static Comms parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
            }

            private Comms(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            public static Comms parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Comms) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Comms parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.mo8396parseFrom(byteString);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Comms mo10094getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: toBuilder */
            public Builder mo10081toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            public static Comms parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: newBuilderForType */
            public Builder mo10079newBuilderForType() {
                return newBuilder();
            }

            private Comms() {
                this.memoizedIsInitialized = (byte) -1;
                this.callType_ = "";
                this.direction_ = "";
                this.duration_ = 0L;
                this.mediaType_ = "";
                this.messageType_ = "";
                this.requestId_ = "";
                this.size_ = 0L;
                this.statusCode_ = 0L;
                this.targetType_ = "";
                this.transport_ = "";
                this.metadataType_ = "";
                this.trickleIceEnabled_ = "";
            }

            public static Comms parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.mo8404parseFrom(bArr);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: newBuilderForType */
            public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static Comms parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
            }

            public static Comms parseFrom(InputStream inputStream) throws IOException {
                return (Comms) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Comms parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Comms) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Comms parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Comms) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Comms parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Comms) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            private Comms(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite != null) {
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                try {
                                    int readTag = codedInputStream.readTag();
                                    switch (readTag) {
                                        case 0:
                                            break;
                                        case 10:
                                            this.callType_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 18:
                                            this.direction_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 24:
                                            this.duration_ = codedInputStream.readInt64();
                                            continue;
                                        case 34:
                                            this.mediaType_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 42:
                                            this.messageType_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 50:
                                            this.requestId_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 56:
                                            this.size_ = codedInputStream.readInt64();
                                            continue;
                                        case 64:
                                            this.statusCode_ = codedInputStream.readInt64();
                                            continue;
                                        case 74:
                                            this.targetType_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 82:
                                            this.transport_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 90:
                                            this.metadataType_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        case 98:
                                            this.trickleIceEnabled_ = codedInputStream.readStringRequireUtf8();
                                            continue;
                                        default:
                                            if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                                break;
                                            } else {
                                                continue;
                                            }
                                    }
                                    z = true;
                                } catch (IOException e) {
                                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                                }
                            } catch (InvalidProtocolBufferException e2) {
                                throw e2.setUnfinishedMessage(this);
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

        /* loaded from: classes9.dex */
        public interface CommsOrBuilder extends MessageOrBuilder {
            String getCallType();

            ByteString getCallTypeBytes();

            String getDirection();

            ByteString getDirectionBytes();

            long getDuration();

            String getMediaType();

            ByteString getMediaTypeBytes();

            String getMessageType();

            ByteString getMessageTypeBytes();

            String getMetadataType();

            ByteString getMetadataTypeBytes();

            String getRequestId();

            ByteString getRequestIdBytes();

            long getSize();

            long getStatusCode();

            String getTargetType();

            ByteString getTargetTypeBytes();

            String getTransport();

            ByteString getTransportBytes();

            String getTrickleIceEnabled();

            ByteString getTrickleIceEnabledBytes();
        }

        /* loaded from: classes9.dex */
        public static final class Dream extends GeneratedMessageV3 implements DreamOrBuilder {
            public static final int DREAMERROR_FIELD_NUMBER = 3;
            public static final int DREAMNUMBER_FIELD_NUMBER = 2;
            public static final int DREAMUUID_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private volatile Object dreamError_;
            private volatile Object dreamNumber_;
            private volatile Object dreamUuid_;
            private byte memoizedIsInitialized;
            private static final Dream DEFAULT_INSTANCE = new Dream();
            private static final Parser<Dream> PARSER = new AbstractParser<Dream>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Dream.1
                @Override // com.google.protobuf.Parser
                /* renamed from: parsePartialFrom */
                public Dream mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Dream(codedInputStream, extensionRegistryLite);
                }
            };

            public static Dream getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Dream_descriptor;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.mo10081toBuilder();
            }

            public static Dream parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Dream) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Dream parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.mo8402parseFrom(byteBuffer);
            }

            public static Parser<Dream> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Dream)) {
                    return super.equals(obj);
                }
                Dream dream = (Dream) obj;
                return (((getDreamUuid().equals(dream.getDreamUuid())) && getDreamNumber().equals(dream.getDreamNumber())) && getDreamError().equals(dream.getDreamError())) && this.unknownFields.equals(dream.unknownFields);
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
            public String getDreamError() {
                Object obj = this.dreamError_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.dreamError_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
            public ByteString getDreamErrorBytes() {
                Object obj = this.dreamError_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dreamError_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
            public String getDreamNumber() {
                Object obj = this.dreamNumber_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.dreamNumber_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
            public ByteString getDreamNumberBytes() {
                Object obj = this.dreamNumber_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dreamNumber_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
            public String getDreamUuid() {
                Object obj = this.dreamUuid_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.dreamUuid_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
            public ByteString getDreamUuidBytes() {
                Object obj = this.dreamUuid_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dreamUuid_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: getParserForType */
            public Parser<Dream> mo9954getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if (!getDreamUuidBytes().isEmpty()) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.dreamUuid_);
                }
                if (!getDreamNumberBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(2, this.dreamNumber_);
                }
                if (!getDreamErrorBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(3, this.dreamError_);
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
                int hashCode = getDreamUuid().hashCode();
                int hashCode2 = getDreamNumber().hashCode();
                int hashCode3 = getDreamError().hashCode();
                int hashCode4 = this.unknownFields.hashCode() + ((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 29);
                this.memoizedHashCode = hashCode4;
                return hashCode4;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Dream_fieldAccessorTable.ensureFieldAccessorsInitialized(Dream.class, Builder.class);
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
                if (!getDreamUuidBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.dreamUuid_);
                }
                if (!getDreamNumberBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.dreamNumber_);
                }
                if (!getDreamErrorBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.dreamError_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            /* loaded from: classes9.dex */
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DreamOrBuilder {
                private Object dreamError_;
                private Object dreamNumber_;
                private Object dreamUuid_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Dream_descriptor;
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                public Builder clearDreamError() {
                    this.dreamError_ = Dream.getDefaultInstance().getDreamError();
                    onChanged();
                    return this;
                }

                public Builder clearDreamNumber() {
                    this.dreamNumber_ = Dream.getDefaultInstance().getDreamNumber();
                    onChanged();
                    return this;
                }

                public Builder clearDreamUuid() {
                    this.dreamUuid_ = Dream.getDefaultInstance().getDreamUuid();
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Dream_descriptor;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
                public String getDreamError() {
                    Object obj = this.dreamError_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.dreamError_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
                public ByteString getDreamErrorBytes() {
                    Object obj = this.dreamError_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.dreamError_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
                public String getDreamNumber() {
                    Object obj = this.dreamNumber_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.dreamNumber_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
                public ByteString getDreamNumberBytes() {
                    Object obj = this.dreamNumber_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.dreamNumber_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
                public String getDreamUuid() {
                    Object obj = this.dreamUuid_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.dreamUuid_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.DreamOrBuilder
                public ByteString getDreamUuidBytes() {
                    Object obj = this.dreamUuid_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.dreamUuid_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Dream_fieldAccessorTable.ensureFieldAccessorsInitialized(Dream.class, Builder.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public Builder setDreamError(String str) {
                    if (str != null) {
                        this.dreamError_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDreamErrorBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.dreamError_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDreamNumber(String str) {
                    if (str != null) {
                        this.dreamNumber_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDreamNumberBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.dreamNumber_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDreamUuid(String str) {
                    if (str != null) {
                        this.dreamUuid_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDreamUuidBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.dreamUuid_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                private Builder() {
                    this.dreamUuid_ = "";
                    this.dreamNumber_ = "";
                    this.dreamError_ = "";
                    maybeForceBuilderInitialization();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: addRepeatedField */
                public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: build */
                public Dream mo10084build() {
                    Dream mo10085buildPartial = mo10085buildPartial();
                    if (mo10085buildPartial.isInitialized()) {
                        return mo10085buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: buildPartial */
                public Dream mo10085buildPartial() {
                    Dream dream = new Dream(this);
                    dream.dreamUuid_ = this.dreamUuid_;
                    dream.dreamNumber_ = this.dreamNumber_;
                    dream.dreamError_ = this.dreamError_;
                    onBuilt();
                    return dream;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: clearField */
                public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.mo10088clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: getDefaultInstanceForType */
                public Dream mo10094getDefaultInstanceForType() {
                    return Dream.getDefaultInstance();
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
                    this.dreamUuid_ = "";
                    this.dreamNumber_ = "";
                    this.dreamError_ = "";
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
                    if (message instanceof Dream) {
                        return mergeFrom((Dream) message);
                    }
                    super.mo10096mergeFrom(message);
                    return this;
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.dreamUuid_ = "";
                    this.dreamNumber_ = "";
                    this.dreamError_ = "";
                    maybeForceBuilderInitialization();
                }

                public Builder mergeFrom(Dream dream) {
                    if (dream == Dream.getDefaultInstance()) {
                        return this;
                    }
                    if (!dream.getDreamUuid().isEmpty()) {
                        this.dreamUuid_ = dream.dreamUuid_;
                        onChanged();
                    }
                    if (!dream.getDreamNumber().isEmpty()) {
                        this.dreamNumber_ = dream.dreamNumber_;
                        onChanged();
                    }
                    if (!dream.getDreamError().isEmpty()) {
                        this.dreamError_ = dream.dreamError_;
                        onChanged();
                    }
                    mo10099mergeUnknownFields(((GeneratedMessageV3) dream).unknownFields);
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
                public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Dream.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Dream.access$14100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Dream r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Dream) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Dream r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Dream) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Dream.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Dream$Builder");
                }
            }

            public static Builder newBuilder(Dream dream) {
                return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(dream);
            }

            public static Dream parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
            }

            private Dream(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            public static Dream parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Dream) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Dream parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.mo8396parseFrom(byteString);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Dream mo10094getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: toBuilder */
            public Builder mo10081toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            public static Dream parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: newBuilderForType */
            public Builder mo10079newBuilderForType() {
                return newBuilder();
            }

            private Dream() {
                this.memoizedIsInitialized = (byte) -1;
                this.dreamUuid_ = "";
                this.dreamNumber_ = "";
                this.dreamError_ = "";
            }

            public static Dream parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.mo8404parseFrom(bArr);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: newBuilderForType */
            public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static Dream parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
            }

            public static Dream parseFrom(InputStream inputStream) throws IOException {
                return (Dream) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Dream parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Dream) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            private Dream(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.dreamUuid_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 18) {
                                        this.dreamNumber_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag != 26) {
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.dreamError_ = codedInputStream.readStringRequireUtf8();
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

            public static Dream parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Dream) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Dream parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Dream) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes9.dex */
        public interface DreamOrBuilder extends MessageOrBuilder {
            String getDreamError();

            ByteString getDreamErrorBytes();

            String getDreamNumber();

            ByteString getDreamNumberBytes();

            String getDreamUuid();

            ByteString getDreamUuidBytes();
        }

        /* loaded from: classes9.dex */
        public static final class Entertainemnt extends GeneratedMessageV3 implements EntertainemntOrBuilder {
            public static final int ITEMID_FIELD_NUMBER = 3;
            public static final int ITEMINDEX_FIELD_NUMBER = 5;
            public static final int PAGEID_FIELD_NUMBER = 1;
            public static final int SECTIONID_FIELD_NUMBER = 2;
            public static final int SECTIONINDEX_FIELD_NUMBER = 4;
            private static final long serialVersionUID = 0;
            private volatile Object itemID_;
            private long itemIndex_;
            private byte memoizedIsInitialized;
            private volatile Object pageID_;
            private volatile Object sectionID_;
            private long sectionIndex_;
            private static final Entertainemnt DEFAULT_INSTANCE = new Entertainemnt();
            private static final Parser<Entertainemnt> PARSER = new AbstractParser<Entertainemnt>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Entertainemnt.1
                @Override // com.google.protobuf.Parser
                /* renamed from: parsePartialFrom */
                public Entertainemnt mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Entertainemnt(codedInputStream, extensionRegistryLite);
                }
            };

            public static Entertainemnt getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Entertainemnt_descriptor;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.mo10081toBuilder();
            }

            public static Entertainemnt parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Entertainemnt) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Entertainemnt parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.mo8402parseFrom(byteBuffer);
            }

            public static Parser<Entertainemnt> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Entertainemnt)) {
                    return super.equals(obj);
                }
                Entertainemnt entertainemnt = (Entertainemnt) obj;
                return (((((getPageID().equals(entertainemnt.getPageID())) && getSectionID().equals(entertainemnt.getSectionID())) && getItemID().equals(entertainemnt.getItemID())) && (getSectionIndex() > entertainemnt.getSectionIndex() ? 1 : (getSectionIndex() == entertainemnt.getSectionIndex() ? 0 : -1)) == 0) && (getItemIndex() > entertainemnt.getItemIndex() ? 1 : (getItemIndex() == entertainemnt.getItemIndex() ? 0 : -1)) == 0) && this.unknownFields.equals(entertainemnt.unknownFields);
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
            public String getItemID() {
                Object obj = this.itemID_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.itemID_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
            public ByteString getItemIDBytes() {
                Object obj = this.itemID_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.itemID_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
            public long getItemIndex() {
                return this.itemIndex_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
            public String getPageID() {
                Object obj = this.pageID_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.pageID_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
            public ByteString getPageIDBytes() {
                Object obj = this.pageID_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.pageID_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: getParserForType */
            public Parser<Entertainemnt> mo9954getParserForType() {
                return PARSER;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
            public String getSectionID() {
                Object obj = this.sectionID_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.sectionID_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
            public ByteString getSectionIDBytes() {
                Object obj = this.sectionID_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.sectionID_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
            public long getSectionIndex() {
                return this.sectionIndex_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if (!getPageIDBytes().isEmpty()) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.pageID_);
                }
                if (!getSectionIDBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(2, this.sectionID_);
                }
                if (!getItemIDBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(3, this.itemID_);
                }
                long j = this.sectionIndex_;
                if (j != 0) {
                    i2 += CodedOutputStream.computeInt64Size(4, j);
                }
                long j2 = this.itemIndex_;
                if (j2 != 0) {
                    i2 += CodedOutputStream.computeInt64Size(5, j2);
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
                int hashCode = getPageID().hashCode();
                int hashCode2 = getSectionID().hashCode();
                int hashCode3 = getItemID().hashCode();
                int hashLong = Internal.hashLong(getSectionIndex());
                int hashLong2 = Internal.hashLong(getItemIndex());
                int hashCode4 = this.unknownFields.hashCode() + ((hashLong2 + ((((hashLong + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 29);
                this.memoizedHashCode = hashCode4;
                return hashCode4;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Entertainemnt_fieldAccessorTable.ensureFieldAccessorsInitialized(Entertainemnt.class, Builder.class);
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
                if (!getPageIDBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.pageID_);
                }
                if (!getSectionIDBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.sectionID_);
                }
                if (!getItemIDBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.itemID_);
                }
                long j = this.sectionIndex_;
                if (j != 0) {
                    codedOutputStream.writeInt64(4, j);
                }
                long j2 = this.itemIndex_;
                if (j2 != 0) {
                    codedOutputStream.writeInt64(5, j2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            /* loaded from: classes9.dex */
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EntertainemntOrBuilder {
                private Object itemID_;
                private long itemIndex_;
                private Object pageID_;
                private Object sectionID_;
                private long sectionIndex_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Entertainemnt_descriptor;
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                public Builder clearItemID() {
                    this.itemID_ = Entertainemnt.getDefaultInstance().getItemID();
                    onChanged();
                    return this;
                }

                public Builder clearItemIndex() {
                    this.itemIndex_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearPageID() {
                    this.pageID_ = Entertainemnt.getDefaultInstance().getPageID();
                    onChanged();
                    return this;
                }

                public Builder clearSectionID() {
                    this.sectionID_ = Entertainemnt.getDefaultInstance().getSectionID();
                    onChanged();
                    return this;
                }

                public Builder clearSectionIndex() {
                    this.sectionIndex_ = 0L;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Entertainemnt_descriptor;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
                public String getItemID() {
                    Object obj = this.itemID_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.itemID_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
                public ByteString getItemIDBytes() {
                    Object obj = this.itemID_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.itemID_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
                public long getItemIndex() {
                    return this.itemIndex_;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
                public String getPageID() {
                    Object obj = this.pageID_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.pageID_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
                public ByteString getPageIDBytes() {
                    Object obj = this.pageID_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.pageID_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
                public String getSectionID() {
                    Object obj = this.sectionID_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.sectionID_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
                public ByteString getSectionIDBytes() {
                    Object obj = this.sectionID_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.sectionID_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.EntertainemntOrBuilder
                public long getSectionIndex() {
                    return this.sectionIndex_;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Entertainemnt_fieldAccessorTable.ensureFieldAccessorsInitialized(Entertainemnt.class, Builder.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public Builder setItemID(String str) {
                    if (str != null) {
                        this.itemID_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setItemIDBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.itemID_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setItemIndex(long j) {
                    this.itemIndex_ = j;
                    onChanged();
                    return this;
                }

                public Builder setPageID(String str) {
                    if (str != null) {
                        this.pageID_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPageIDBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.pageID_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSectionID(String str) {
                    if (str != null) {
                        this.sectionID_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSectionIDBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.sectionID_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSectionIndex(long j) {
                    this.sectionIndex_ = j;
                    onChanged();
                    return this;
                }

                private Builder() {
                    this.pageID_ = "";
                    this.sectionID_ = "";
                    this.itemID_ = "";
                    maybeForceBuilderInitialization();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: addRepeatedField */
                public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: build */
                public Entertainemnt mo10084build() {
                    Entertainemnt mo10085buildPartial = mo10085buildPartial();
                    if (mo10085buildPartial.isInitialized()) {
                        return mo10085buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: buildPartial */
                public Entertainemnt mo10085buildPartial() {
                    Entertainemnt entertainemnt = new Entertainemnt(this);
                    entertainemnt.pageID_ = this.pageID_;
                    entertainemnt.sectionID_ = this.sectionID_;
                    entertainemnt.itemID_ = this.itemID_;
                    entertainemnt.sectionIndex_ = this.sectionIndex_;
                    entertainemnt.itemIndex_ = this.itemIndex_;
                    onBuilt();
                    return entertainemnt;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: clearField */
                public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.mo10088clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: getDefaultInstanceForType */
                public Entertainemnt mo10094getDefaultInstanceForType() {
                    return Entertainemnt.getDefaultInstance();
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
                    this.pageID_ = "";
                    this.sectionID_ = "";
                    this.itemID_ = "";
                    this.sectionIndex_ = 0L;
                    this.itemIndex_ = 0L;
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
                    if (message instanceof Entertainemnt) {
                        return mergeFrom((Entertainemnt) message);
                    }
                    super.mo10096mergeFrom(message);
                    return this;
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.pageID_ = "";
                    this.sectionID_ = "";
                    this.itemID_ = "";
                    maybeForceBuilderInitialization();
                }

                public Builder mergeFrom(Entertainemnt entertainemnt) {
                    if (entertainemnt == Entertainemnt.getDefaultInstance()) {
                        return this;
                    }
                    if (!entertainemnt.getPageID().isEmpty()) {
                        this.pageID_ = entertainemnt.pageID_;
                        onChanged();
                    }
                    if (!entertainemnt.getSectionID().isEmpty()) {
                        this.sectionID_ = entertainemnt.sectionID_;
                        onChanged();
                    }
                    if (!entertainemnt.getItemID().isEmpty()) {
                        this.itemID_ = entertainemnt.itemID_;
                        onChanged();
                    }
                    if (entertainemnt.getSectionIndex() != 0) {
                        setSectionIndex(entertainemnt.getSectionIndex());
                    }
                    if (entertainemnt.getItemIndex() != 0) {
                        setItemIndex(entertainemnt.getItemIndex());
                    }
                    mo10099mergeUnknownFields(((GeneratedMessageV3) entertainemnt).unknownFields);
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
                public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Entertainemnt.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Entertainemnt.access$15600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Entertainemnt r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Entertainemnt) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Entertainemnt r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Entertainemnt) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Entertainemnt.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Entertainemnt$Builder");
                }
            }

            public static Builder newBuilder(Entertainemnt entertainemnt) {
                return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(entertainemnt);
            }

            public static Entertainemnt parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
            }

            private Entertainemnt(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            public static Entertainemnt parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Entertainemnt) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Entertainemnt parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.mo8396parseFrom(byteString);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Entertainemnt mo10094getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: toBuilder */
            public Builder mo10081toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            public static Entertainemnt parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: newBuilderForType */
            public Builder mo10079newBuilderForType() {
                return newBuilder();
            }

            private Entertainemnt() {
                this.memoizedIsInitialized = (byte) -1;
                this.pageID_ = "";
                this.sectionID_ = "";
                this.itemID_ = "";
                this.sectionIndex_ = 0L;
                this.itemIndex_ = 0L;
            }

            public static Entertainemnt parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.mo8404parseFrom(bArr);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: newBuilderForType */
            public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static Entertainemnt parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
            }

            public static Entertainemnt parseFrom(InputStream inputStream) throws IOException {
                return (Entertainemnt) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Entertainemnt parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Entertainemnt) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Entertainemnt parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Entertainemnt) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            private Entertainemnt(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite != null) {
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                try {
                                    int readTag = codedInputStream.readTag();
                                    if (readTag != 0) {
                                        if (readTag == 10) {
                                            this.pageID_ = codedInputStream.readStringRequireUtf8();
                                        } else if (readTag == 18) {
                                            this.sectionID_ = codedInputStream.readStringRequireUtf8();
                                        } else if (readTag == 26) {
                                            this.itemID_ = codedInputStream.readStringRequireUtf8();
                                        } else if (readTag == 32) {
                                            this.sectionIndex_ = codedInputStream.readInt64();
                                        } else if (readTag != 40) {
                                            if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                            }
                                        } else {
                                            this.itemIndex_ = codedInputStream.readInt64();
                                        }
                                    }
                                    z = true;
                                } catch (IOException e) {
                                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                                }
                            } catch (InvalidProtocolBufferException e2) {
                                throw e2.setUnfinishedMessage(this);
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

            public static Entertainemnt parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Entertainemnt) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes9.dex */
        public interface EntertainemntOrBuilder extends MessageOrBuilder {
            String getItemID();

            ByteString getItemIDBytes();

            long getItemIndex();

            String getPageID();

            ByteString getPageIDBytes();

            String getSectionID();

            ByteString getSectionIDBytes();

            long getSectionIndex();
        }

        /* loaded from: classes9.dex */
        public static final class Photos extends GeneratedMessageV3 implements PhotosOrBuilder {
            public static final int BACKUPCHECKPOINTCOUNT_FIELD_NUMBER = 10;
            public static final int BACKUPCOMPLETETYPE_FIELD_NUMBER = 9;
            public static final int BACKUPMETHOD_FIELD_NUMBER = 2;
            public static final int DATAFETCHFAILEDERRORCODE_FIELD_NUMBER = 6;
            public static final int DEDUPLICATIONSOURCE_FIELD_NUMBER = 8;
            public static final int MAXCONCURRENTUPLOADOPERATIONS_FIELD_NUMBER = 3;
            public static final int MEDIATYPE_FIELD_NUMBER = 5;
            public static final int OPERATIONERROR_FIELD_NUMBER = 4;
            public static final int RESPONSEERRORCODE_FIELD_NUMBER = 7;
            public static final int UPLOADCONTEXT_FIELD_NUMBER = 11;
            public static final int UPLOADSESSIONTYPE_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private long backupCheckpointCount_;
            private volatile Object backupCompleteType_;
            private volatile Object backupMethod_;
            private volatile Object dataFetchFailedErrorCode_;
            private volatile Object deduplicationSource_;
            private long maxConcurrentUploadOperations_;
            private volatile Object mediaType_;
            private byte memoizedIsInitialized;
            private long operationError_;
            private volatile Object responseErrorCode_;
            private volatile Object uploadContext_;
            private volatile Object uploadSessionType_;
            private static final Photos DEFAULT_INSTANCE = new Photos();
            private static final Parser<Photos> PARSER = new AbstractParser<Photos>() { // from class: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Photos.1
                @Override // com.google.protobuf.Parser
                /* renamed from: parsePartialFrom */
                public Photos mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Photos(codedInputStream, extensionRegistryLite);
                }
            };

            public static Photos getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Photos_descriptor;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.mo10081toBuilder();
            }

            public static Photos parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Photos) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Photos parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.mo8402parseFrom(byteBuffer);
            }

            public static Parser<Photos> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Photos)) {
                    return super.equals(obj);
                }
                Photos photos = (Photos) obj;
                return (((((((((((getUploadSessionType().equals(photos.getUploadSessionType())) && getBackupMethod().equals(photos.getBackupMethod())) && (getMaxConcurrentUploadOperations() > photos.getMaxConcurrentUploadOperations() ? 1 : (getMaxConcurrentUploadOperations() == photos.getMaxConcurrentUploadOperations() ? 0 : -1)) == 0) && (getOperationError() > photos.getOperationError() ? 1 : (getOperationError() == photos.getOperationError() ? 0 : -1)) == 0) && getMediaType().equals(photos.getMediaType())) && getDataFetchFailedErrorCode().equals(photos.getDataFetchFailedErrorCode())) && getResponseErrorCode().equals(photos.getResponseErrorCode())) && getDeduplicationSource().equals(photos.getDeduplicationSource())) && getBackupCompleteType().equals(photos.getBackupCompleteType())) && (getBackupCheckpointCount() > photos.getBackupCheckpointCount() ? 1 : (getBackupCheckpointCount() == photos.getBackupCheckpointCount() ? 0 : -1)) == 0) && getUploadContext().equals(photos.getUploadContext())) && this.unknownFields.equals(photos.unknownFields);
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public long getBackupCheckpointCount() {
                return this.backupCheckpointCount_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public String getBackupCompleteType() {
                Object obj = this.backupCompleteType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.backupCompleteType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public ByteString getBackupCompleteTypeBytes() {
                Object obj = this.backupCompleteType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.backupCompleteType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public String getBackupMethod() {
                Object obj = this.backupMethod_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.backupMethod_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public ByteString getBackupMethodBytes() {
                Object obj = this.backupMethod_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.backupMethod_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public String getDataFetchFailedErrorCode() {
                Object obj = this.dataFetchFailedErrorCode_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.dataFetchFailedErrorCode_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public ByteString getDataFetchFailedErrorCodeBytes() {
                Object obj = this.dataFetchFailedErrorCode_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dataFetchFailedErrorCode_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public String getDeduplicationSource() {
                Object obj = this.deduplicationSource_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.deduplicationSource_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public ByteString getDeduplicationSourceBytes() {
                Object obj = this.deduplicationSource_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deduplicationSource_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public long getMaxConcurrentUploadOperations() {
                return this.maxConcurrentUploadOperations_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public String getMediaType() {
                Object obj = this.mediaType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.mediaType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public ByteString getMediaTypeBytes() {
                Object obj = this.mediaType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.mediaType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public long getOperationError() {
                return this.operationError_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: getParserForType */
            public Parser<Photos> mo9954getParserForType() {
                return PARSER;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public String getResponseErrorCode() {
                Object obj = this.responseErrorCode_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.responseErrorCode_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public ByteString getResponseErrorCodeBytes() {
                Object obj = this.responseErrorCode_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.responseErrorCode_ = copyFromUtf8;
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
                if (!getUploadSessionTypeBytes().isEmpty()) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.uploadSessionType_);
                }
                if (!getBackupMethodBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(2, this.backupMethod_);
                }
                long j = this.maxConcurrentUploadOperations_;
                if (j != 0) {
                    i2 += CodedOutputStream.computeInt64Size(3, j);
                }
                long j2 = this.operationError_;
                if (j2 != 0) {
                    i2 += CodedOutputStream.computeInt64Size(4, j2);
                }
                if (!getMediaTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(5, this.mediaType_);
                }
                if (!getDataFetchFailedErrorCodeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(6, this.dataFetchFailedErrorCode_);
                }
                if (!getResponseErrorCodeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(7, this.responseErrorCode_);
                }
                if (!getDeduplicationSourceBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(8, this.deduplicationSource_);
                }
                if (!getBackupCompleteTypeBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(9, this.backupCompleteType_);
                }
                long j3 = this.backupCheckpointCount_;
                if (j3 != 0) {
                    i2 += CodedOutputStream.computeInt64Size(10, j3);
                }
                if (!getUploadContextBytes().isEmpty()) {
                    i2 += GeneratedMessageV3.computeStringSize(11, this.uploadContext_);
                }
                int serializedSize = this.unknownFields.getSerializedSize() + i2;
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public String getUploadContext() {
                Object obj = this.uploadContext_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.uploadContext_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public ByteString getUploadContextBytes() {
                Object obj = this.uploadContext_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.uploadContext_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public String getUploadSessionType() {
                Object obj = this.uploadSessionType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.uploadSessionType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
            public ByteString getUploadSessionTypeBytes() {
                Object obj = this.uploadSessionType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.uploadSessionType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = getUploadSessionType().hashCode();
                int hashCode2 = getBackupMethod().hashCode();
                int hashLong = Internal.hashLong(getMaxConcurrentUploadOperations());
                int hashLong2 = Internal.hashLong(getOperationError());
                int hashCode3 = getMediaType().hashCode();
                int hashCode4 = getDataFetchFailedErrorCode().hashCode();
                int hashCode5 = getResponseErrorCode().hashCode();
                int hashCode6 = getDeduplicationSource().hashCode();
                int hashCode7 = getBackupCompleteType().hashCode();
                int hashLong3 = Internal.hashLong(getBackupCheckpointCount());
                int hashCode8 = getUploadContext().hashCode();
                int hashCode9 = this.unknownFields.hashCode() + ((hashCode8 + ((((hashLong3 + ((((hashCode7 + ((((hashCode6 + ((((hashCode5 + ((((hashCode4 + ((((hashCode3 + ((((hashLong2 + ((((hashLong + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 37) + 6) * 53)) * 37) + 7) * 53)) * 37) + 8) * 53)) * 37) + 9) * 53)) * 37) + 10) * 53)) * 37) + 11) * 53)) * 29);
                this.memoizedHashCode = hashCode9;
                return hashCode9;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Photos_fieldAccessorTable.ensureFieldAccessorsInitialized(Photos.class, Builder.class);
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
                if (!getUploadSessionTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.uploadSessionType_);
                }
                if (!getBackupMethodBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.backupMethod_);
                }
                long j = this.maxConcurrentUploadOperations_;
                if (j != 0) {
                    codedOutputStream.writeInt64(3, j);
                }
                long j2 = this.operationError_;
                if (j2 != 0) {
                    codedOutputStream.writeInt64(4, j2);
                }
                if (!getMediaTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 5, this.mediaType_);
                }
                if (!getDataFetchFailedErrorCodeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 6, this.dataFetchFailedErrorCode_);
                }
                if (!getResponseErrorCodeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 7, this.responseErrorCode_);
                }
                if (!getDeduplicationSourceBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 8, this.deduplicationSource_);
                }
                if (!getBackupCompleteTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 9, this.backupCompleteType_);
                }
                long j3 = this.backupCheckpointCount_;
                if (j3 != 0) {
                    codedOutputStream.writeInt64(10, j3);
                }
                if (!getUploadContextBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 11, this.uploadContext_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            /* loaded from: classes9.dex */
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PhotosOrBuilder {
                private long backupCheckpointCount_;
                private Object backupCompleteType_;
                private Object backupMethod_;
                private Object dataFetchFailedErrorCode_;
                private Object deduplicationSource_;
                private long maxConcurrentUploadOperations_;
                private Object mediaType_;
                private long operationError_;
                private Object responseErrorCode_;
                private Object uploadContext_;
                private Object uploadSessionType_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Photos_descriptor;
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                public Builder clearBackupCheckpointCount() {
                    this.backupCheckpointCount_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearBackupCompleteType() {
                    this.backupCompleteType_ = Photos.getDefaultInstance().getBackupCompleteType();
                    onChanged();
                    return this;
                }

                public Builder clearBackupMethod() {
                    this.backupMethod_ = Photos.getDefaultInstance().getBackupMethod();
                    onChanged();
                    return this;
                }

                public Builder clearDataFetchFailedErrorCode() {
                    this.dataFetchFailedErrorCode_ = Photos.getDefaultInstance().getDataFetchFailedErrorCode();
                    onChanged();
                    return this;
                }

                public Builder clearDeduplicationSource() {
                    this.deduplicationSource_ = Photos.getDefaultInstance().getDeduplicationSource();
                    onChanged();
                    return this;
                }

                public Builder clearMaxConcurrentUploadOperations() {
                    this.maxConcurrentUploadOperations_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearMediaType() {
                    this.mediaType_ = Photos.getDefaultInstance().getMediaType();
                    onChanged();
                    return this;
                }

                public Builder clearOperationError() {
                    this.operationError_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearResponseErrorCode() {
                    this.responseErrorCode_ = Photos.getDefaultInstance().getResponseErrorCode();
                    onChanged();
                    return this;
                }

                public Builder clearUploadContext() {
                    this.uploadContext_ = Photos.getDefaultInstance().getUploadContext();
                    onChanged();
                    return this;
                }

                public Builder clearUploadSessionType() {
                    this.uploadSessionType_ = Photos.getDefaultInstance().getUploadSessionType();
                    onChanged();
                    return this;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public long getBackupCheckpointCount() {
                    return this.backupCheckpointCount_;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public String getBackupCompleteType() {
                    Object obj = this.backupCompleteType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.backupCompleteType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public ByteString getBackupCompleteTypeBytes() {
                    Object obj = this.backupCompleteType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.backupCompleteType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public String getBackupMethod() {
                    Object obj = this.backupMethod_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.backupMethod_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public ByteString getBackupMethodBytes() {
                    Object obj = this.backupMethod_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.backupMethod_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public String getDataFetchFailedErrorCode() {
                    Object obj = this.dataFetchFailedErrorCode_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.dataFetchFailedErrorCode_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public ByteString getDataFetchFailedErrorCodeBytes() {
                    Object obj = this.dataFetchFailedErrorCode_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.dataFetchFailedErrorCode_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public String getDeduplicationSource() {
                    Object obj = this.deduplicationSource_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.deduplicationSource_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public ByteString getDeduplicationSourceBytes() {
                    Object obj = this.deduplicationSource_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.deduplicationSource_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Photos_descriptor;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public long getMaxConcurrentUploadOperations() {
                    return this.maxConcurrentUploadOperations_;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public String getMediaType() {
                    Object obj = this.mediaType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.mediaType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public ByteString getMediaTypeBytes() {
                    Object obj = this.mediaType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.mediaType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public long getOperationError() {
                    return this.operationError_;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public String getResponseErrorCode() {
                    Object obj = this.responseErrorCode_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.responseErrorCode_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public ByteString getResponseErrorCodeBytes() {
                    Object obj = this.responseErrorCode_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.responseErrorCode_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public String getUploadContext() {
                    Object obj = this.uploadContext_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.uploadContext_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public ByteString getUploadContextBytes() {
                    Object obj = this.uploadContext_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.uploadContext_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public String getUploadSessionType() {
                    Object obj = this.uploadSessionType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.uploadSessionType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.PhotosOrBuilder
                public ByteString getUploadSessionTypeBytes() {
                    Object obj = this.uploadSessionType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.uploadSessionType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_Photos_fieldAccessorTable.ensureFieldAccessorsInitialized(Photos.class, Builder.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public Builder setBackupCheckpointCount(long j) {
                    this.backupCheckpointCount_ = j;
                    onChanged();
                    return this;
                }

                public Builder setBackupCompleteType(String str) {
                    if (str != null) {
                        this.backupCompleteType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setBackupCompleteTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.backupCompleteType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setBackupMethod(String str) {
                    if (str != null) {
                        this.backupMethod_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setBackupMethodBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.backupMethod_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDataFetchFailedErrorCode(String str) {
                    if (str != null) {
                        this.dataFetchFailedErrorCode_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDataFetchFailedErrorCodeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.dataFetchFailedErrorCode_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeduplicationSource(String str) {
                    if (str != null) {
                        this.deduplicationSource_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeduplicationSourceBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.deduplicationSource_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMaxConcurrentUploadOperations(long j) {
                    this.maxConcurrentUploadOperations_ = j;
                    onChanged();
                    return this;
                }

                public Builder setMediaType(String str) {
                    if (str != null) {
                        this.mediaType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMediaTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.mediaType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setOperationError(long j) {
                    this.operationError_ = j;
                    onChanged();
                    return this;
                }

                public Builder setResponseErrorCode(String str) {
                    if (str != null) {
                        this.responseErrorCode_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setResponseErrorCodeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.responseErrorCode_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUploadContext(String str) {
                    if (str != null) {
                        this.uploadContext_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUploadContextBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.uploadContext_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUploadSessionType(String str) {
                    if (str != null) {
                        this.uploadSessionType_ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUploadSessionTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        AbstractMessageLite.checkByteStringIsUtf8(byteString);
                        this.uploadSessionType_ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                private Builder() {
                    this.uploadSessionType_ = "";
                    this.backupMethod_ = "";
                    this.mediaType_ = "";
                    this.dataFetchFailedErrorCode_ = "";
                    this.responseErrorCode_ = "";
                    this.deduplicationSource_ = "";
                    this.backupCompleteType_ = "";
                    this.uploadContext_ = "";
                    maybeForceBuilderInitialization();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: addRepeatedField */
                public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: build */
                public Photos mo10084build() {
                    Photos mo10085buildPartial = mo10085buildPartial();
                    if (mo10085buildPartial.isInitialized()) {
                        return mo10085buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: buildPartial */
                public Photos mo10085buildPartial() {
                    Photos photos = new Photos(this);
                    photos.uploadSessionType_ = this.uploadSessionType_;
                    photos.backupMethod_ = this.backupMethod_;
                    photos.maxConcurrentUploadOperations_ = this.maxConcurrentUploadOperations_;
                    photos.operationError_ = this.operationError_;
                    photos.mediaType_ = this.mediaType_;
                    photos.dataFetchFailedErrorCode_ = this.dataFetchFailedErrorCode_;
                    photos.responseErrorCode_ = this.responseErrorCode_;
                    photos.deduplicationSource_ = this.deduplicationSource_;
                    photos.backupCompleteType_ = this.backupCompleteType_;
                    photos.backupCheckpointCount_ = this.backupCheckpointCount_;
                    photos.uploadContext_ = this.uploadContext_;
                    onBuilt();
                    return photos;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: clearField */
                public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.mo10088clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: getDefaultInstanceForType */
                public Photos mo10094getDefaultInstanceForType() {
                    return Photos.getDefaultInstance();
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
                    this.uploadSessionType_ = "";
                    this.backupMethod_ = "";
                    this.maxConcurrentUploadOperations_ = 0L;
                    this.operationError_ = 0L;
                    this.mediaType_ = "";
                    this.dataFetchFailedErrorCode_ = "";
                    this.responseErrorCode_ = "";
                    this.deduplicationSource_ = "";
                    this.backupCompleteType_ = "";
                    this.backupCheckpointCount_ = 0L;
                    this.uploadContext_ = "";
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
                    if (message instanceof Photos) {
                        return mergeFrom((Photos) message);
                    }
                    super.mo10096mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Photos photos) {
                    if (photos == Photos.getDefaultInstance()) {
                        return this;
                    }
                    if (!photos.getUploadSessionType().isEmpty()) {
                        this.uploadSessionType_ = photos.uploadSessionType_;
                        onChanged();
                    }
                    if (!photos.getBackupMethod().isEmpty()) {
                        this.backupMethod_ = photos.backupMethod_;
                        onChanged();
                    }
                    if (photos.getMaxConcurrentUploadOperations() != 0) {
                        setMaxConcurrentUploadOperations(photos.getMaxConcurrentUploadOperations());
                    }
                    if (photos.getOperationError() != 0) {
                        setOperationError(photos.getOperationError());
                    }
                    if (!photos.getMediaType().isEmpty()) {
                        this.mediaType_ = photos.mediaType_;
                        onChanged();
                    }
                    if (!photos.getDataFetchFailedErrorCode().isEmpty()) {
                        this.dataFetchFailedErrorCode_ = photos.dataFetchFailedErrorCode_;
                        onChanged();
                    }
                    if (!photos.getResponseErrorCode().isEmpty()) {
                        this.responseErrorCode_ = photos.responseErrorCode_;
                        onChanged();
                    }
                    if (!photos.getDeduplicationSource().isEmpty()) {
                        this.deduplicationSource_ = photos.deduplicationSource_;
                        onChanged();
                    }
                    if (!photos.getBackupCompleteType().isEmpty()) {
                        this.backupCompleteType_ = photos.backupCompleteType_;
                        onChanged();
                    }
                    if (photos.getBackupCheckpointCount() != 0) {
                        setBackupCheckpointCount(photos.getBackupCheckpointCount());
                    }
                    if (!photos.getUploadContext().isEmpty()) {
                        this.uploadContext_ = photos.uploadContext_;
                        onChanged();
                    }
                    mo10099mergeUnknownFields(((GeneratedMessageV3) photos).unknownFields);
                    onChanged();
                    return this;
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.uploadSessionType_ = "";
                    this.backupMethod_ = "";
                    this.mediaType_ = "";
                    this.dataFetchFailedErrorCode_ = "";
                    this.responseErrorCode_ = "";
                    this.deduplicationSource_ = "";
                    this.backupCompleteType_ = "";
                    this.uploadContext_ = "";
                    maybeForceBuilderInitialization();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: mergeFrom */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Photos.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Photos.access$17700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Photos r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Photos) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Photos r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Photos) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Photos.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Photos$Builder");
                }
            }

            public static Builder newBuilder(Photos photos) {
                return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(photos);
            }

            public static Photos parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
            }

            private Photos(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            public static Photos parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Photos) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Photos parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.mo8396parseFrom(byteString);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Photos mo10094getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: toBuilder */
            public Builder mo10081toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            public static Photos parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: newBuilderForType */
            public Builder mo10079newBuilderForType() {
                return newBuilder();
            }

            private Photos() {
                this.memoizedIsInitialized = (byte) -1;
                this.uploadSessionType_ = "";
                this.backupMethod_ = "";
                this.maxConcurrentUploadOperations_ = 0L;
                this.operationError_ = 0L;
                this.mediaType_ = "";
                this.dataFetchFailedErrorCode_ = "";
                this.responseErrorCode_ = "";
                this.deduplicationSource_ = "";
                this.backupCompleteType_ = "";
                this.backupCheckpointCount_ = 0L;
                this.uploadContext_ = "";
            }

            public static Photos parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.mo8404parseFrom(bArr);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: newBuilderForType */
            public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static Photos parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
            }

            public static Photos parseFrom(InputStream inputStream) throws IOException {
                return (Photos) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Photos parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Photos) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Photos parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Photos) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Photos parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Photos) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            private Photos(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite != null) {
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                switch (readTag) {
                                    case 0:
                                        break;
                                    case 10:
                                        this.uploadSessionType_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 18:
                                        this.backupMethod_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 24:
                                        this.maxConcurrentUploadOperations_ = codedInputStream.readInt64();
                                        continue;
                                    case 32:
                                        this.operationError_ = codedInputStream.readInt64();
                                        continue;
                                    case 42:
                                        this.mediaType_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 50:
                                        this.dataFetchFailedErrorCode_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 58:
                                        this.responseErrorCode_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 66:
                                        this.deduplicationSource_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 74:
                                        this.backupCompleteType_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 80:
                                        this.backupCheckpointCount_ = codedInputStream.readInt64();
                                        continue;
                                    case 90:
                                        this.uploadContext_ = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    default:
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                            break;
                                        } else {
                                            continue;
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

        /* loaded from: classes9.dex */
        public interface PhotosOrBuilder extends MessageOrBuilder {
            long getBackupCheckpointCount();

            String getBackupCompleteType();

            ByteString getBackupCompleteTypeBytes();

            String getBackupMethod();

            ByteString getBackupMethodBytes();

            String getDataFetchFailedErrorCode();

            ByteString getDataFetchFailedErrorCodeBytes();

            String getDeduplicationSource();

            ByteString getDeduplicationSourceBytes();

            long getMaxConcurrentUploadOperations();

            String getMediaType();

            ByteString getMediaTypeBytes();

            long getOperationError();

            String getResponseErrorCode();

            ByteString getResponseErrorCodeBytes();

            String getUploadContext();

            ByteString getUploadContextBytes();

            String getUploadSessionType();

            ByteString getUploadSessionTypeBytes();
        }

        public static Metadata getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Metadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Metadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<Metadata> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Metadata)) {
                return super.equals(obj);
            }
            Metadata metadata = (Metadata) obj;
            boolean z = hasComms() == metadata.hasComms();
            if (hasComms()) {
                z = z && getComms().equals(metadata.getComms());
            }
            boolean z2 = z && hasAma() == metadata.hasAma();
            if (hasAma()) {
                z2 = z2 && getAma().equals(metadata.getAma());
            }
            boolean z3 = z2 && hasAmpd() == metadata.hasAmpd();
            if (hasAmpd()) {
                z3 = z3 && getAmpd().equals(metadata.getAmpd());
            }
            boolean z4 = z3 && hasDream() == metadata.hasDream();
            if (hasDream()) {
                z4 = z4 && getDream().equals(metadata.getDream());
            }
            boolean z5 = z4 && hasEntertainemnt() == metadata.hasEntertainemnt();
            if (hasEntertainemnt()) {
                z5 = z5 && getEntertainemnt().equals(metadata.getEntertainemnt());
            }
            boolean z6 = z5 && hasPhotos() == metadata.hasPhotos();
            if (hasPhotos()) {
                z6 = z6 && getPhotos().equals(metadata.getPhotos());
            }
            boolean z7 = z6 && hasA4ASdk() == metadata.hasA4ASdk();
            if (hasA4ASdk()) {
                z7 = z7 && getA4ASdk().equals(metadata.getA4ASdk());
            }
            boolean z8 = z7 && hasA4ALaunch() == metadata.hasA4ALaunch();
            if (hasA4ALaunch()) {
                z8 = z8 && getA4ALaunch().equals(metadata.getA4ALaunch());
            }
            return z8 && this.unknownFields.equals(metadata.unknownFields);
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public A4aLaunch getA4ALaunch() {
            A4aLaunch a4aLaunch = this.a4ALaunch_;
            return a4aLaunch == null ? A4aLaunch.getDefaultInstance() : a4aLaunch;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public A4aLaunchOrBuilder getA4ALaunchOrBuilder() {
            return getA4ALaunch();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public A4aSdk getA4ASdk() {
            A4aSdk a4aSdk = this.a4ASdk_;
            return a4aSdk == null ? A4aSdk.getDefaultInstance() : a4aSdk;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public A4aSdkOrBuilder getA4ASdkOrBuilder() {
            return getA4ASdk();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public Ama getAma() {
            Ama ama = this.ama_;
            return ama == null ? Ama.getDefaultInstance() : ama;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public AmaOrBuilder getAmaOrBuilder() {
            return getAma();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public Ampd getAmpd() {
            Ampd ampd = this.ampd_;
            return ampd == null ? Ampd.getDefaultInstance() : ampd;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public AmpdOrBuilder getAmpdOrBuilder() {
            return getAmpd();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public Comms getComms() {
            Comms comms = this.comms_;
            return comms == null ? Comms.getDefaultInstance() : comms;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public CommsOrBuilder getCommsOrBuilder() {
            return getComms();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public Dream getDream() {
            Dream dream = this.dream_;
            return dream == null ? Dream.getDefaultInstance() : dream;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public DreamOrBuilder getDreamOrBuilder() {
            return getDream();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public Entertainemnt getEntertainemnt() {
            Entertainemnt entertainemnt = this.entertainemnt_;
            return entertainemnt == null ? Entertainemnt.getDefaultInstance() : entertainemnt;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public EntertainemntOrBuilder getEntertainemntOrBuilder() {
            return getEntertainemnt();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<Metadata> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public Photos getPhotos() {
            Photos photos = this.photos_;
            return photos == null ? Photos.getDefaultInstance() : photos;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public PhotosOrBuilder getPhotosOrBuilder() {
            return getPhotos();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.comms_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getComms());
            }
            if (this.ama_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getAma());
            }
            if (this.ampd_ != null) {
                i2 += CodedOutputStream.computeMessageSize(3, getAmpd());
            }
            if (this.dream_ != null) {
                i2 += CodedOutputStream.computeMessageSize(4, getDream());
            }
            if (this.entertainemnt_ != null) {
                i2 += CodedOutputStream.computeMessageSize(5, getEntertainemnt());
            }
            if (this.photos_ != null) {
                i2 += CodedOutputStream.computeMessageSize(6, getPhotos());
            }
            if (this.a4ASdk_ != null) {
                i2 += CodedOutputStream.computeMessageSize(7, getA4ASdk());
            }
            if (this.a4ALaunch_ != null) {
                i2 += CodedOutputStream.computeMessageSize(8, getA4ALaunch());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public boolean hasA4ALaunch() {
            return this.a4ALaunch_ != null;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public boolean hasA4ASdk() {
            return this.a4ASdk_ != null;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public boolean hasAma() {
            return this.ama_ != null;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public boolean hasAmpd() {
            return this.ampd_ != null;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public boolean hasComms() {
            return this.comms_ != null;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public boolean hasDream() {
            return this.dream_ != null;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public boolean hasEntertainemnt() {
            return this.entertainemnt_ != null;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
        public boolean hasPhotos() {
            return this.photos_ != null;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasComms()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getComms().hashCode();
            }
            if (hasAma()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getAma().hashCode();
            }
            if (hasAmpd()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getAmpd().hashCode();
            }
            if (hasDream()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + getDream().hashCode();
            }
            if (hasEntertainemnt()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 5, 53) + getEntertainemnt().hashCode();
            }
            if (hasPhotos()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 6, 53) + getPhotos().hashCode();
            }
            if (hasA4ASdk()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 7, 53) + getA4ASdk().hashCode();
            }
            if (hasA4ALaunch()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 8, 53) + getA4ALaunch().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
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
            if (this.comms_ != null) {
                codedOutputStream.writeMessage(1, getComms());
            }
            if (this.ama_ != null) {
                codedOutputStream.writeMessage(2, getAma());
            }
            if (this.ampd_ != null) {
                codedOutputStream.writeMessage(3, getAmpd());
            }
            if (this.dream_ != null) {
                codedOutputStream.writeMessage(4, getDream());
            }
            if (this.entertainemnt_ != null) {
                codedOutputStream.writeMessage(5, getEntertainemnt());
            }
            if (this.photos_ != null) {
                codedOutputStream.writeMessage(6, getPhotos());
            }
            if (this.a4ASdk_ != null) {
                codedOutputStream.writeMessage(7, getA4ASdk());
            }
            if (this.a4ALaunch_ != null) {
                codedOutputStream.writeMessage(8, getA4ALaunch());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes9.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetadataOrBuilder {
            private SingleFieldBuilderV3<A4aLaunch, A4aLaunch.Builder, A4aLaunchOrBuilder> a4ALaunchBuilder_;
            private A4aLaunch a4ALaunch_;
            private SingleFieldBuilderV3<A4aSdk, A4aSdk.Builder, A4aSdkOrBuilder> a4ASdkBuilder_;
            private A4aSdk a4ASdk_;
            private SingleFieldBuilderV3<Ama, Ama.Builder, AmaOrBuilder> amaBuilder_;
            private Ama ama_;
            private SingleFieldBuilderV3<Ampd, Ampd.Builder, AmpdOrBuilder> ampdBuilder_;
            private Ampd ampd_;
            private SingleFieldBuilderV3<Comms, Comms.Builder, CommsOrBuilder> commsBuilder_;
            private Comms comms_;
            private SingleFieldBuilderV3<Dream, Dream.Builder, DreamOrBuilder> dreamBuilder_;
            private Dream dream_;
            private SingleFieldBuilderV3<Entertainemnt, Entertainemnt.Builder, EntertainemntOrBuilder> entertainemntBuilder_;
            private Entertainemnt entertainemnt_;
            private SingleFieldBuilderV3<Photos, Photos.Builder, PhotosOrBuilder> photosBuilder_;
            private Photos photos_;

            private SingleFieldBuilderV3<A4aLaunch, A4aLaunch.Builder, A4aLaunchOrBuilder> getA4ALaunchFieldBuilder() {
                if (this.a4ALaunchBuilder_ == null) {
                    this.a4ALaunchBuilder_ = new SingleFieldBuilderV3<>(getA4ALaunch(), getParentForChildren(), isClean());
                    this.a4ALaunch_ = null;
                }
                return this.a4ALaunchBuilder_;
            }

            private SingleFieldBuilderV3<A4aSdk, A4aSdk.Builder, A4aSdkOrBuilder> getA4ASdkFieldBuilder() {
                if (this.a4ASdkBuilder_ == null) {
                    this.a4ASdkBuilder_ = new SingleFieldBuilderV3<>(getA4ASdk(), getParentForChildren(), isClean());
                    this.a4ASdk_ = null;
                }
                return this.a4ASdkBuilder_;
            }

            private SingleFieldBuilderV3<Ama, Ama.Builder, AmaOrBuilder> getAmaFieldBuilder() {
                if (this.amaBuilder_ == null) {
                    this.amaBuilder_ = new SingleFieldBuilderV3<>(getAma(), getParentForChildren(), isClean());
                    this.ama_ = null;
                }
                return this.amaBuilder_;
            }

            private SingleFieldBuilderV3<Ampd, Ampd.Builder, AmpdOrBuilder> getAmpdFieldBuilder() {
                if (this.ampdBuilder_ == null) {
                    this.ampdBuilder_ = new SingleFieldBuilderV3<>(getAmpd(), getParentForChildren(), isClean());
                    this.ampd_ = null;
                }
                return this.ampdBuilder_;
            }

            private SingleFieldBuilderV3<Comms, Comms.Builder, CommsOrBuilder> getCommsFieldBuilder() {
                if (this.commsBuilder_ == null) {
                    this.commsBuilder_ = new SingleFieldBuilderV3<>(getComms(), getParentForChildren(), isClean());
                    this.comms_ = null;
                }
                return this.commsBuilder_;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_descriptor;
            }

            private SingleFieldBuilderV3<Dream, Dream.Builder, DreamOrBuilder> getDreamFieldBuilder() {
                if (this.dreamBuilder_ == null) {
                    this.dreamBuilder_ = new SingleFieldBuilderV3<>(getDream(), getParentForChildren(), isClean());
                    this.dream_ = null;
                }
                return this.dreamBuilder_;
            }

            private SingleFieldBuilderV3<Entertainemnt, Entertainemnt.Builder, EntertainemntOrBuilder> getEntertainemntFieldBuilder() {
                if (this.entertainemntBuilder_ == null) {
                    this.entertainemntBuilder_ = new SingleFieldBuilderV3<>(getEntertainemnt(), getParentForChildren(), isClean());
                    this.entertainemnt_ = null;
                }
                return this.entertainemntBuilder_;
            }

            private SingleFieldBuilderV3<Photos, Photos.Builder, PhotosOrBuilder> getPhotosFieldBuilder() {
                if (this.photosBuilder_ == null) {
                    this.photosBuilder_ = new SingleFieldBuilderV3<>(getPhotos(), getParentForChildren(), isClean());
                    this.photos_ = null;
                }
                return this.photosBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearA4ALaunch() {
                if (this.a4ALaunchBuilder_ == null) {
                    this.a4ALaunch_ = null;
                    onChanged();
                } else {
                    this.a4ALaunch_ = null;
                    this.a4ALaunchBuilder_ = null;
                }
                return this;
            }

            public Builder clearA4ASdk() {
                if (this.a4ASdkBuilder_ == null) {
                    this.a4ASdk_ = null;
                    onChanged();
                } else {
                    this.a4ASdk_ = null;
                    this.a4ASdkBuilder_ = null;
                }
                return this;
            }

            public Builder clearAma() {
                if (this.amaBuilder_ == null) {
                    this.ama_ = null;
                    onChanged();
                } else {
                    this.ama_ = null;
                    this.amaBuilder_ = null;
                }
                return this;
            }

            public Builder clearAmpd() {
                if (this.ampdBuilder_ == null) {
                    this.ampd_ = null;
                    onChanged();
                } else {
                    this.ampd_ = null;
                    this.ampdBuilder_ = null;
                }
                return this;
            }

            public Builder clearComms() {
                if (this.commsBuilder_ == null) {
                    this.comms_ = null;
                    onChanged();
                } else {
                    this.comms_ = null;
                    this.commsBuilder_ = null;
                }
                return this;
            }

            public Builder clearDream() {
                if (this.dreamBuilder_ == null) {
                    this.dream_ = null;
                    onChanged();
                } else {
                    this.dream_ = null;
                    this.dreamBuilder_ = null;
                }
                return this;
            }

            public Builder clearEntertainemnt() {
                if (this.entertainemntBuilder_ == null) {
                    this.entertainemnt_ = null;
                    onChanged();
                } else {
                    this.entertainemnt_ = null;
                    this.entertainemntBuilder_ = null;
                }
                return this;
            }

            public Builder clearPhotos() {
                if (this.photosBuilder_ == null) {
                    this.photos_ = null;
                    onChanged();
                } else {
                    this.photos_ = null;
                    this.photosBuilder_ = null;
                }
                return this;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public A4aLaunch getA4ALaunch() {
                SingleFieldBuilderV3<A4aLaunch, A4aLaunch.Builder, A4aLaunchOrBuilder> singleFieldBuilderV3 = this.a4ALaunchBuilder_;
                if (singleFieldBuilderV3 == null) {
                    A4aLaunch a4aLaunch = this.a4ALaunch_;
                    return a4aLaunch == null ? A4aLaunch.getDefaultInstance() : a4aLaunch;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public A4aLaunch.Builder getA4ALaunchBuilder() {
                onChanged();
                return getA4ALaunchFieldBuilder().getBuilder();
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public A4aLaunchOrBuilder getA4ALaunchOrBuilder() {
                SingleFieldBuilderV3<A4aLaunch, A4aLaunch.Builder, A4aLaunchOrBuilder> singleFieldBuilderV3 = this.a4ALaunchBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                A4aLaunch a4aLaunch = this.a4ALaunch_;
                return a4aLaunch == null ? A4aLaunch.getDefaultInstance() : a4aLaunch;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public A4aSdk getA4ASdk() {
                SingleFieldBuilderV3<A4aSdk, A4aSdk.Builder, A4aSdkOrBuilder> singleFieldBuilderV3 = this.a4ASdkBuilder_;
                if (singleFieldBuilderV3 == null) {
                    A4aSdk a4aSdk = this.a4ASdk_;
                    return a4aSdk == null ? A4aSdk.getDefaultInstance() : a4aSdk;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public A4aSdk.Builder getA4ASdkBuilder() {
                onChanged();
                return getA4ASdkFieldBuilder().getBuilder();
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public A4aSdkOrBuilder getA4ASdkOrBuilder() {
                SingleFieldBuilderV3<A4aSdk, A4aSdk.Builder, A4aSdkOrBuilder> singleFieldBuilderV3 = this.a4ASdkBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                A4aSdk a4aSdk = this.a4ASdk_;
                return a4aSdk == null ? A4aSdk.getDefaultInstance() : a4aSdk;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public Ama getAma() {
                SingleFieldBuilderV3<Ama, Ama.Builder, AmaOrBuilder> singleFieldBuilderV3 = this.amaBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Ama ama = this.ama_;
                    return ama == null ? Ama.getDefaultInstance() : ama;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Ama.Builder getAmaBuilder() {
                onChanged();
                return getAmaFieldBuilder().getBuilder();
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public AmaOrBuilder getAmaOrBuilder() {
                SingleFieldBuilderV3<Ama, Ama.Builder, AmaOrBuilder> singleFieldBuilderV3 = this.amaBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Ama ama = this.ama_;
                return ama == null ? Ama.getDefaultInstance() : ama;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public Ampd getAmpd() {
                SingleFieldBuilderV3<Ampd, Ampd.Builder, AmpdOrBuilder> singleFieldBuilderV3 = this.ampdBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Ampd ampd = this.ampd_;
                    return ampd == null ? Ampd.getDefaultInstance() : ampd;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Ampd.Builder getAmpdBuilder() {
                onChanged();
                return getAmpdFieldBuilder().getBuilder();
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public AmpdOrBuilder getAmpdOrBuilder() {
                SingleFieldBuilderV3<Ampd, Ampd.Builder, AmpdOrBuilder> singleFieldBuilderV3 = this.ampdBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Ampd ampd = this.ampd_;
                return ampd == null ? Ampd.getDefaultInstance() : ampd;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public Comms getComms() {
                SingleFieldBuilderV3<Comms, Comms.Builder, CommsOrBuilder> singleFieldBuilderV3 = this.commsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Comms comms = this.comms_;
                    return comms == null ? Comms.getDefaultInstance() : comms;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Comms.Builder getCommsBuilder() {
                onChanged();
                return getCommsFieldBuilder().getBuilder();
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public CommsOrBuilder getCommsOrBuilder() {
                SingleFieldBuilderV3<Comms, Comms.Builder, CommsOrBuilder> singleFieldBuilderV3 = this.commsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Comms comms = this.comms_;
                return comms == null ? Comms.getDefaultInstance() : comms;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_descriptor;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public Dream getDream() {
                SingleFieldBuilderV3<Dream, Dream.Builder, DreamOrBuilder> singleFieldBuilderV3 = this.dreamBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Dream dream = this.dream_;
                    return dream == null ? Dream.getDefaultInstance() : dream;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Dream.Builder getDreamBuilder() {
                onChanged();
                return getDreamFieldBuilder().getBuilder();
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public DreamOrBuilder getDreamOrBuilder() {
                SingleFieldBuilderV3<Dream, Dream.Builder, DreamOrBuilder> singleFieldBuilderV3 = this.dreamBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Dream dream = this.dream_;
                return dream == null ? Dream.getDefaultInstance() : dream;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public Entertainemnt getEntertainemnt() {
                SingleFieldBuilderV3<Entertainemnt, Entertainemnt.Builder, EntertainemntOrBuilder> singleFieldBuilderV3 = this.entertainemntBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Entertainemnt entertainemnt = this.entertainemnt_;
                    return entertainemnt == null ? Entertainemnt.getDefaultInstance() : entertainemnt;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Entertainemnt.Builder getEntertainemntBuilder() {
                onChanged();
                return getEntertainemntFieldBuilder().getBuilder();
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public EntertainemntOrBuilder getEntertainemntOrBuilder() {
                SingleFieldBuilderV3<Entertainemnt, Entertainemnt.Builder, EntertainemntOrBuilder> singleFieldBuilderV3 = this.entertainemntBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Entertainemnt entertainemnt = this.entertainemnt_;
                return entertainemnt == null ? Entertainemnt.getDefaultInstance() : entertainemnt;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public Photos getPhotos() {
                SingleFieldBuilderV3<Photos, Photos.Builder, PhotosOrBuilder> singleFieldBuilderV3 = this.photosBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Photos photos = this.photos_;
                    return photos == null ? Photos.getDefaultInstance() : photos;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Photos.Builder getPhotosBuilder() {
                onChanged();
                return getPhotosFieldBuilder().getBuilder();
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public PhotosOrBuilder getPhotosOrBuilder() {
                SingleFieldBuilderV3<Photos, Photos.Builder, PhotosOrBuilder> singleFieldBuilderV3 = this.photosBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Photos photos = this.photos_;
                return photos == null ? Photos.getDefaultInstance() : photos;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public boolean hasA4ALaunch() {
                return (this.a4ALaunchBuilder_ == null && this.a4ALaunch_ == null) ? false : true;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public boolean hasA4ASdk() {
                return (this.a4ASdkBuilder_ == null && this.a4ASdk_ == null) ? false : true;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public boolean hasAma() {
                return (this.amaBuilder_ == null && this.ama_ == null) ? false : true;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public boolean hasAmpd() {
                return (this.ampdBuilder_ == null && this.ampd_ == null) ? false : true;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public boolean hasComms() {
                return (this.commsBuilder_ == null && this.comms_ == null) ? false : true;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public boolean hasDream() {
                return (this.dreamBuilder_ == null && this.dream_ == null) ? false : true;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public boolean hasEntertainemnt() {
                return (this.entertainemntBuilder_ == null && this.entertainemnt_ == null) ? false : true;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.MetadataOrBuilder
            public boolean hasPhotos() {
                return (this.photosBuilder_ == null && this.photos_ == null) ? false : true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeA4ALaunch(A4aLaunch a4aLaunch) {
                SingleFieldBuilderV3<A4aLaunch, A4aLaunch.Builder, A4aLaunchOrBuilder> singleFieldBuilderV3 = this.a4ALaunchBuilder_;
                if (singleFieldBuilderV3 == null) {
                    A4aLaunch a4aLaunch2 = this.a4ALaunch_;
                    if (a4aLaunch2 != null) {
                        this.a4ALaunch_ = A4aLaunch.newBuilder(a4aLaunch2).mergeFrom(a4aLaunch).mo10085buildPartial();
                    } else {
                        this.a4ALaunch_ = a4aLaunch;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(a4aLaunch);
                }
                return this;
            }

            public Builder mergeA4ASdk(A4aSdk a4aSdk) {
                SingleFieldBuilderV3<A4aSdk, A4aSdk.Builder, A4aSdkOrBuilder> singleFieldBuilderV3 = this.a4ASdkBuilder_;
                if (singleFieldBuilderV3 == null) {
                    A4aSdk a4aSdk2 = this.a4ASdk_;
                    if (a4aSdk2 != null) {
                        this.a4ASdk_ = A4aSdk.newBuilder(a4aSdk2).mergeFrom(a4aSdk).mo10085buildPartial();
                    } else {
                        this.a4ASdk_ = a4aSdk;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(a4aSdk);
                }
                return this;
            }

            public Builder mergeAma(Ama ama) {
                SingleFieldBuilderV3<Ama, Ama.Builder, AmaOrBuilder> singleFieldBuilderV3 = this.amaBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Ama ama2 = this.ama_;
                    if (ama2 != null) {
                        this.ama_ = Ama.newBuilder(ama2).mergeFrom(ama).mo10085buildPartial();
                    } else {
                        this.ama_ = ama;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ama);
                }
                return this;
            }

            public Builder mergeAmpd(Ampd ampd) {
                SingleFieldBuilderV3<Ampd, Ampd.Builder, AmpdOrBuilder> singleFieldBuilderV3 = this.ampdBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Ampd ampd2 = this.ampd_;
                    if (ampd2 != null) {
                        this.ampd_ = Ampd.newBuilder(ampd2).mergeFrom(ampd).mo10085buildPartial();
                    } else {
                        this.ampd_ = ampd;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ampd);
                }
                return this;
            }

            public Builder mergeComms(Comms comms) {
                SingleFieldBuilderV3<Comms, Comms.Builder, CommsOrBuilder> singleFieldBuilderV3 = this.commsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Comms comms2 = this.comms_;
                    if (comms2 != null) {
                        this.comms_ = Comms.newBuilder(comms2).mergeFrom(comms).mo10085buildPartial();
                    } else {
                        this.comms_ = comms;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(comms);
                }
                return this;
            }

            public Builder mergeDream(Dream dream) {
                SingleFieldBuilderV3<Dream, Dream.Builder, DreamOrBuilder> singleFieldBuilderV3 = this.dreamBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Dream dream2 = this.dream_;
                    if (dream2 != null) {
                        this.dream_ = Dream.newBuilder(dream2).mergeFrom(dream).mo10085buildPartial();
                    } else {
                        this.dream_ = dream;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(dream);
                }
                return this;
            }

            public Builder mergeEntertainemnt(Entertainemnt entertainemnt) {
                SingleFieldBuilderV3<Entertainemnt, Entertainemnt.Builder, EntertainemntOrBuilder> singleFieldBuilderV3 = this.entertainemntBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Entertainemnt entertainemnt2 = this.entertainemnt_;
                    if (entertainemnt2 != null) {
                        this.entertainemnt_ = Entertainemnt.newBuilder(entertainemnt2).mergeFrom(entertainemnt).mo10085buildPartial();
                    } else {
                        this.entertainemnt_ = entertainemnt;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(entertainemnt);
                }
                return this;
            }

            public Builder mergePhotos(Photos photos) {
                SingleFieldBuilderV3<Photos, Photos.Builder, PhotosOrBuilder> singleFieldBuilderV3 = this.photosBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Photos photos2 = this.photos_;
                    if (photos2 != null) {
                        this.photos_ = Photos.newBuilder(photos2).mergeFrom(photos).mo10085buildPartial();
                    } else {
                        this.photos_ = photos;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(photos);
                }
                return this;
            }

            public Builder setA4ALaunch(A4aLaunch a4aLaunch) {
                SingleFieldBuilderV3<A4aLaunch, A4aLaunch.Builder, A4aLaunchOrBuilder> singleFieldBuilderV3 = this.a4ALaunchBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(a4aLaunch);
                } else if (a4aLaunch != null) {
                    this.a4ALaunch_ = a4aLaunch;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setA4ASdk(A4aSdk a4aSdk) {
                SingleFieldBuilderV3<A4aSdk, A4aSdk.Builder, A4aSdkOrBuilder> singleFieldBuilderV3 = this.a4ASdkBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(a4aSdk);
                } else if (a4aSdk != null) {
                    this.a4ASdk_ = a4aSdk;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setAma(Ama ama) {
                SingleFieldBuilderV3<Ama, Ama.Builder, AmaOrBuilder> singleFieldBuilderV3 = this.amaBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ama);
                } else if (ama != null) {
                    this.ama_ = ama;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setAmpd(Ampd ampd) {
                SingleFieldBuilderV3<Ampd, Ampd.Builder, AmpdOrBuilder> singleFieldBuilderV3 = this.ampdBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ampd);
                } else if (ampd != null) {
                    this.ampd_ = ampd;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setComms(Comms comms) {
                SingleFieldBuilderV3<Comms, Comms.Builder, CommsOrBuilder> singleFieldBuilderV3 = this.commsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(comms);
                } else if (comms != null) {
                    this.comms_ = comms;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setDream(Dream dream) {
                SingleFieldBuilderV3<Dream, Dream.Builder, DreamOrBuilder> singleFieldBuilderV3 = this.dreamBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(dream);
                } else if (dream != null) {
                    this.dream_ = dream;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setEntertainemnt(Entertainemnt entertainemnt) {
                SingleFieldBuilderV3<Entertainemnt, Entertainemnt.Builder, EntertainemntOrBuilder> singleFieldBuilderV3 = this.entertainemntBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(entertainemnt);
                } else if (entertainemnt != null) {
                    this.entertainemnt_ = entertainemnt;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setPhotos(Photos photos) {
                SingleFieldBuilderV3<Photos, Photos.Builder, PhotosOrBuilder> singleFieldBuilderV3 = this.photosBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(photos);
                } else if (photos != null) {
                    this.photos_ = photos;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            private Builder() {
                this.comms_ = null;
                this.ama_ = null;
                this.ampd_ = null;
                this.dream_ = null;
                this.entertainemnt_ = null;
                this.photos_ = null;
                this.a4ASdk_ = null;
                this.a4ALaunch_ = null;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public Metadata mo10084build() {
                Metadata mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public Metadata mo10085buildPartial() {
                Metadata metadata = new Metadata(this);
                SingleFieldBuilderV3<Comms, Comms.Builder, CommsOrBuilder> singleFieldBuilderV3 = this.commsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    metadata.comms_ = this.comms_;
                } else {
                    metadata.comms_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<Ama, Ama.Builder, AmaOrBuilder> singleFieldBuilderV32 = this.amaBuilder_;
                if (singleFieldBuilderV32 == null) {
                    metadata.ama_ = this.ama_;
                } else {
                    metadata.ama_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<Ampd, Ampd.Builder, AmpdOrBuilder> singleFieldBuilderV33 = this.ampdBuilder_;
                if (singleFieldBuilderV33 == null) {
                    metadata.ampd_ = this.ampd_;
                } else {
                    metadata.ampd_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<Dream, Dream.Builder, DreamOrBuilder> singleFieldBuilderV34 = this.dreamBuilder_;
                if (singleFieldBuilderV34 == null) {
                    metadata.dream_ = this.dream_;
                } else {
                    metadata.dream_ = singleFieldBuilderV34.build();
                }
                SingleFieldBuilderV3<Entertainemnt, Entertainemnt.Builder, EntertainemntOrBuilder> singleFieldBuilderV35 = this.entertainemntBuilder_;
                if (singleFieldBuilderV35 == null) {
                    metadata.entertainemnt_ = this.entertainemnt_;
                } else {
                    metadata.entertainemnt_ = singleFieldBuilderV35.build();
                }
                SingleFieldBuilderV3<Photos, Photos.Builder, PhotosOrBuilder> singleFieldBuilderV36 = this.photosBuilder_;
                if (singleFieldBuilderV36 == null) {
                    metadata.photos_ = this.photos_;
                } else {
                    metadata.photos_ = singleFieldBuilderV36.build();
                }
                SingleFieldBuilderV3<A4aSdk, A4aSdk.Builder, A4aSdkOrBuilder> singleFieldBuilderV37 = this.a4ASdkBuilder_;
                if (singleFieldBuilderV37 == null) {
                    metadata.a4ASdk_ = this.a4ASdk_;
                } else {
                    metadata.a4ASdk_ = singleFieldBuilderV37.build();
                }
                SingleFieldBuilderV3<A4aLaunch, A4aLaunch.Builder, A4aLaunchOrBuilder> singleFieldBuilderV38 = this.a4ALaunchBuilder_;
                if (singleFieldBuilderV38 == null) {
                    metadata.a4ALaunch_ = this.a4ALaunch_;
                } else {
                    metadata.a4ALaunch_ = singleFieldBuilderV38.build();
                }
                onBuilt();
                return metadata;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Metadata mo10094getDefaultInstanceForType() {
                return Metadata.getDefaultInstance();
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
                if (this.commsBuilder_ == null) {
                    this.comms_ = null;
                } else {
                    this.comms_ = null;
                    this.commsBuilder_ = null;
                }
                if (this.amaBuilder_ == null) {
                    this.ama_ = null;
                } else {
                    this.ama_ = null;
                    this.amaBuilder_ = null;
                }
                if (this.ampdBuilder_ == null) {
                    this.ampd_ = null;
                } else {
                    this.ampd_ = null;
                    this.ampdBuilder_ = null;
                }
                if (this.dreamBuilder_ == null) {
                    this.dream_ = null;
                } else {
                    this.dream_ = null;
                    this.dreamBuilder_ = null;
                }
                if (this.entertainemntBuilder_ == null) {
                    this.entertainemnt_ = null;
                } else {
                    this.entertainemnt_ = null;
                    this.entertainemntBuilder_ = null;
                }
                if (this.photosBuilder_ == null) {
                    this.photos_ = null;
                } else {
                    this.photos_ = null;
                    this.photosBuilder_ = null;
                }
                if (this.a4ASdkBuilder_ == null) {
                    this.a4ASdk_ = null;
                } else {
                    this.a4ASdk_ = null;
                    this.a4ASdkBuilder_ = null;
                }
                if (this.a4ALaunchBuilder_ == null) {
                    this.a4ALaunch_ = null;
                } else {
                    this.a4ALaunch_ = null;
                    this.a4ALaunchBuilder_ = null;
                }
                return this;
            }

            public Builder setA4ALaunch(A4aLaunch.Builder builder) {
                SingleFieldBuilderV3<A4aLaunch, A4aLaunch.Builder, A4aLaunchOrBuilder> singleFieldBuilderV3 = this.a4ALaunchBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.a4ALaunch_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder setA4ASdk(A4aSdk.Builder builder) {
                SingleFieldBuilderV3<A4aSdk, A4aSdk.Builder, A4aSdkOrBuilder> singleFieldBuilderV3 = this.a4ASdkBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.a4ASdk_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder setAma(Ama.Builder builder) {
                SingleFieldBuilderV3<Ama, Ama.Builder, AmaOrBuilder> singleFieldBuilderV3 = this.amaBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.ama_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder setAmpd(Ampd.Builder builder) {
                SingleFieldBuilderV3<Ampd, Ampd.Builder, AmpdOrBuilder> singleFieldBuilderV3 = this.ampdBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.ampd_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder setComms(Comms.Builder builder) {
                SingleFieldBuilderV3<Comms, Comms.Builder, CommsOrBuilder> singleFieldBuilderV3 = this.commsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.comms_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder setDream(Dream.Builder builder) {
                SingleFieldBuilderV3<Dream, Dream.Builder, DreamOrBuilder> singleFieldBuilderV3 = this.dreamBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.dream_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder setEntertainemnt(Entertainemnt.Builder builder) {
                SingleFieldBuilderV3<Entertainemnt, Entertainemnt.Builder, EntertainemntOrBuilder> singleFieldBuilderV3 = this.entertainemntBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.entertainemnt_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder setPhotos(Photos.Builder builder) {
                SingleFieldBuilderV3<Photos, Photos.Builder, PhotosOrBuilder> singleFieldBuilderV3 = this.photosBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.photos_ = builder.mo10084build();
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
                if (message instanceof Metadata) {
                    return mergeFrom((Metadata) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Metadata metadata) {
                if (metadata == Metadata.getDefaultInstance()) {
                    return this;
                }
                if (metadata.hasComms()) {
                    mergeComms(metadata.getComms());
                }
                if (metadata.hasAma()) {
                    mergeAma(metadata.getAma());
                }
                if (metadata.hasAmpd()) {
                    mergeAmpd(metadata.getAmpd());
                }
                if (metadata.hasDream()) {
                    mergeDream(metadata.getDream());
                }
                if (metadata.hasEntertainemnt()) {
                    mergeEntertainemnt(metadata.getEntertainemnt());
                }
                if (metadata.hasPhotos()) {
                    mergePhotos(metadata.getPhotos());
                }
                if (metadata.hasA4ASdk()) {
                    mergeA4ASdk(metadata.getA4ASdk());
                }
                if (metadata.hasA4ALaunch()) {
                    mergeA4ALaunch(metadata.getA4ALaunch());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) metadata).unknownFields);
                onChanged();
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.comms_ = null;
                this.ama_ = null;
                this.ampd_ = null;
                this.dream_ = null;
                this.entertainemnt_ = null;
                this.photos_ = null;
                this.a4ASdk_ = null;
                this.a4ALaunch_ = null;
                maybeForceBuilderInitialization();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.access$23000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Metadata.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Metadata$Builder");
            }
        }

        public static Builder newBuilder(Metadata metadata) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(metadata);
        }

        public static Metadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private Metadata(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Metadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Metadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public Metadata mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static Metadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private Metadata() {
            this.memoizedIsInitialized = (byte) -1;
        }

        public static Metadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static Metadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        private Metadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                A4aLaunch.Builder builder = null;
                                Comms.Builder mo10081toBuilder = null;
                                Ama.Builder mo10081toBuilder2 = null;
                                Ampd.Builder mo10081toBuilder3 = null;
                                Dream.Builder mo10081toBuilder4 = null;
                                Entertainemnt.Builder mo10081toBuilder5 = null;
                                Photos.Builder mo10081toBuilder6 = null;
                                A4aSdk.Builder mo10081toBuilder7 = null;
                                if (readTag == 10) {
                                    mo10081toBuilder = this.comms_ != null ? this.comms_.mo10081toBuilder() : mo10081toBuilder;
                                    this.comms_ = (Comms) codedInputStream.readMessage(Comms.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom(this.comms_);
                                        this.comms_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    mo10081toBuilder2 = this.ama_ != null ? this.ama_.mo10081toBuilder() : mo10081toBuilder2;
                                    this.ama_ = (Ama) codedInputStream.readMessage(Ama.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder2 != null) {
                                        mo10081toBuilder2.mergeFrom(this.ama_);
                                        this.ama_ = mo10081toBuilder2.mo10085buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    mo10081toBuilder3 = this.ampd_ != null ? this.ampd_.mo10081toBuilder() : mo10081toBuilder3;
                                    this.ampd_ = (Ampd) codedInputStream.readMessage(Ampd.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder3 != null) {
                                        mo10081toBuilder3.mergeFrom(this.ampd_);
                                        this.ampd_ = mo10081toBuilder3.mo10085buildPartial();
                                    }
                                } else if (readTag == 34) {
                                    mo10081toBuilder4 = this.dream_ != null ? this.dream_.mo10081toBuilder() : mo10081toBuilder4;
                                    this.dream_ = (Dream) codedInputStream.readMessage(Dream.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder4 != null) {
                                        mo10081toBuilder4.mergeFrom(this.dream_);
                                        this.dream_ = mo10081toBuilder4.mo10085buildPartial();
                                    }
                                } else if (readTag == 42) {
                                    mo10081toBuilder5 = this.entertainemnt_ != null ? this.entertainemnt_.mo10081toBuilder() : mo10081toBuilder5;
                                    this.entertainemnt_ = (Entertainemnt) codedInputStream.readMessage(Entertainemnt.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder5 != null) {
                                        mo10081toBuilder5.mergeFrom(this.entertainemnt_);
                                        this.entertainemnt_ = mo10081toBuilder5.mo10085buildPartial();
                                    }
                                } else if (readTag == 50) {
                                    mo10081toBuilder6 = this.photos_ != null ? this.photos_.mo10081toBuilder() : mo10081toBuilder6;
                                    this.photos_ = (Photos) codedInputStream.readMessage(Photos.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder6 != null) {
                                        mo10081toBuilder6.mergeFrom(this.photos_);
                                        this.photos_ = mo10081toBuilder6.mo10085buildPartial();
                                    }
                                } else if (readTag == 58) {
                                    mo10081toBuilder7 = this.a4ASdk_ != null ? this.a4ASdk_.mo10081toBuilder() : mo10081toBuilder7;
                                    this.a4ASdk_ = (A4aSdk) codedInputStream.readMessage(A4aSdk.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder7 != null) {
                                        mo10081toBuilder7.mergeFrom(this.a4ASdk_);
                                        this.a4ASdk_ = mo10081toBuilder7.mo10085buildPartial();
                                    }
                                } else if (readTag != 66) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    builder = this.a4ALaunch_ != null ? this.a4ALaunch_.mo10081toBuilder() : builder;
                                    this.a4ALaunch_ = (A4aLaunch) codedInputStream.readMessage(A4aLaunch.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.a4ALaunch_);
                                        this.a4ALaunch_ = builder.mo10085buildPartial();
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

        public static Metadata parseFrom(InputStream inputStream) throws IOException {
            return (Metadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Metadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Metadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Metadata parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Metadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Metadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Metadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes9.dex */
    public interface MetadataOrBuilder extends MessageOrBuilder {
        Metadata.A4aLaunch getA4ALaunch();

        Metadata.A4aLaunchOrBuilder getA4ALaunchOrBuilder();

        Metadata.A4aSdk getA4ASdk();

        Metadata.A4aSdkOrBuilder getA4ASdkOrBuilder();

        Metadata.Ama getAma();

        Metadata.AmaOrBuilder getAmaOrBuilder();

        Metadata.Ampd getAmpd();

        Metadata.AmpdOrBuilder getAmpdOrBuilder();

        Metadata.Comms getComms();

        Metadata.CommsOrBuilder getCommsOrBuilder();

        Metadata.Dream getDream();

        Metadata.DreamOrBuilder getDreamOrBuilder();

        Metadata.Entertainemnt getEntertainemnt();

        Metadata.EntertainemntOrBuilder getEntertainemntOrBuilder();

        Metadata.Photos getPhotos();

        Metadata.PhotosOrBuilder getPhotosOrBuilder();

        boolean hasA4ALaunch();

        boolean hasA4ASdk();

        boolean hasAma();

        boolean hasAmpd();

        boolean hasComms();

        boolean hasDream();

        boolean hasEntertainemnt();

        boolean hasPhotos();
    }

    public static EventDetailsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_descriptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetDebugInfo() {
        MapField<String, String> mapField = this.debugInfo_;
        return mapField == null ? MapField.emptyMapField(DebugInfoDefaultEntryHolder.defaultEntry) : mapField;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static EventDetailsProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EventDetailsProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static EventDetailsProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<EventDetailsProto> parser() {
        return PARSER;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public boolean containsDebugInfo(String str) {
        if (str != null) {
            return internalGetDebugInfo().getMap().containsKey(str);
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventDetailsProto)) {
            return super.equals(obj);
        }
        EventDetailsProto eventDetailsProto = (EventDetailsProto) obj;
        boolean z = (((((((((((((getEventName().equals(eventDetailsProto.getEventName())) && getInteractionType().equals(eventDetailsProto.getInteractionType())) && getOperationalEventType().equals(eventDetailsProto.getOperationalEventType())) && getChannelName().equals(eventDetailsProto.getChannelName())) && getAppComponent().equals(eventDetailsProto.getAppComponent())) && getSubComponent().equals(eventDetailsProto.getSubComponent())) && (getEventNumericValue() > eventDetailsProto.getEventNumericValue() ? 1 : (getEventNumericValue() == eventDetailsProto.getEventNumericValue() ? 0 : -1)) == 0) && getContentId().equals(eventDetailsProto.getContentId())) && getContentProvider().equals(eventDetailsProto.getContentProvider())) && getContentType().equals(eventDetailsProto.getContentType())) && getContentVersion().equals(eventDetailsProto.getContentVersion())) && getInputType().equals(eventDetailsProto.getInputType())) && getSourceContext().equals(eventDetailsProto.getSourceContext())) && hasInteractionDetails() == eventDetailsProto.hasInteractionDetails();
        if (hasInteractionDetails()) {
            z = z && getInteractionDetails().equals(eventDetailsProto.getInteractionDetails());
        }
        boolean z2 = z && hasMetadata() == eventDetailsProto.hasMetadata();
        if (hasMetadata()) {
            z2 = z2 && getMetadata().equals(eventDetailsProto.getMetadata());
        }
        return ((((((((((((((((((z2 && getRefMarker().equals(eventDetailsProto.getRefMarker())) && getSpeakerId().equals(eventDetailsProto.getSpeakerId())) && getTimelineId().equals(eventDetailsProto.getTimelineId())) && getTimelineName().equals(eventDetailsProto.getTimelineName())) && getTimelineNamespace().equals(eventDetailsProto.getTimelineNamespace())) && getTimelineState().equals(eventDetailsProto.getTimelineState())) && (Double.doubleToLongBits(getTimelineElapsedDuration()) > Double.doubleToLongBits(eventDetailsProto.getTimelineElapsedDuration()) ? 1 : (Double.doubleToLongBits(getTimelineElapsedDuration()) == Double.doubleToLongBits(eventDetailsProto.getTimelineElapsedDuration()) ? 0 : -1)) == 0) && getAbortReason().equals(eventDetailsProto.getAbortReason())) && (getStartTimestamp() > eventDetailsProto.getStartTimestamp() ? 1 : (getStartTimestamp() == eventDetailsProto.getStartTimestamp() ? 0 : -1)) == 0) && (getEndTimestamp() > eventDetailsProto.getEndTimestamp() ? 1 : (getEndTimestamp() == eventDetailsProto.getEndTimestamp() ? 0 : -1)) == 0) && (Double.doubleToLongBits(getActiveDuration()) > Double.doubleToLongBits(eventDetailsProto.getActiveDuration()) ? 1 : (Double.doubleToLongBits(getActiveDuration()) == Double.doubleToLongBits(eventDetailsProto.getActiveDuration()) ? 0 : -1)) == 0) && (Double.doubleToLongBits(getTotalDuration()) > Double.doubleToLongBits(eventDetailsProto.getTotalDuration()) ? 1 : (Double.doubleToLongBits(getTotalDuration()) == Double.doubleToLongBits(eventDetailsProto.getTotalDuration()) ? 0 : -1)) == 0) && (getEventCount() > eventDetailsProto.getEventCount() ? 1 : (getEventCount() == eventDetailsProto.getEventCount() ? 0 : -1)) == 0) && getErrorLevel().equals(eventDetailsProto.getErrorLevel())) && getErrorTitle().equals(eventDetailsProto.getErrorTitle())) && getErrorShortMsg().equals(eventDetailsProto.getErrorShortMsg())) && internalGetDebugInfo().equals(eventDetailsProto.internalGetDebugInfo())) && getOwnerIdentifier().equals(eventDetailsProto.getOwnerIdentifier())) && this.unknownFields.equals(eventDetailsProto.unknownFields);
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getAbortReason() {
        Object obj = this.abortReason_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.abortReason_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getAbortReasonBytes() {
        Object obj = this.abortReason_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.abortReason_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public double getActiveDuration() {
        return this.activeDuration_;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getAppComponent() {
        Object obj = this.appComponent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.appComponent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getAppComponentBytes() {
        Object obj = this.appComponent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.appComponent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getChannelName() {
        Object obj = this.channelName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.channelName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getChannelNameBytes() {
        Object obj = this.channelName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.channelName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getContentId() {
        Object obj = this.contentId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.contentId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getContentIdBytes() {
        Object obj = this.contentId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.contentId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getContentProvider() {
        Object obj = this.contentProvider_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.contentProvider_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getContentProviderBytes() {
        Object obj = this.contentProvider_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.contentProvider_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getContentType() {
        Object obj = this.contentType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.contentType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getContentTypeBytes() {
        Object obj = this.contentType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.contentType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getContentVersion() {
        Object obj = this.contentVersion_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.contentVersion_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getContentVersionBytes() {
        Object obj = this.contentVersion_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.contentVersion_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    @Deprecated
    public Map<String, String> getDebugInfo() {
        return getDebugInfoMap();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public int getDebugInfoCount() {
        return internalGetDebugInfo().getMap().size();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public Map<String, String> getDebugInfoMap() {
        return internalGetDebugInfo().getMap();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getDebugInfoOrDefault(String str, String str2) {
        if (str != null) {
            Map<String, String> map = internalGetDebugInfo().getMap();
            return map.containsKey(str) ? map.get(str) : str2;
        }
        throw new NullPointerException();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getDebugInfoOrThrow(String str) {
        if (str != null) {
            Map<String, String> map = internalGetDebugInfo().getMap();
            if (map.containsKey(str)) {
                return map.get(str);
            }
            throw new IllegalArgumentException();
        }
        throw new NullPointerException();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public long getEndTimestamp() {
        return this.endTimestamp_;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getErrorLevel() {
        Object obj = this.errorLevel_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.errorLevel_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getErrorLevelBytes() {
        Object obj = this.errorLevel_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.errorLevel_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getErrorShortMsg() {
        Object obj = this.errorShortMsg_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.errorShortMsg_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getErrorShortMsgBytes() {
        Object obj = this.errorShortMsg_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.errorShortMsg_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getErrorTitle() {
        Object obj = this.errorTitle_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.errorTitle_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getErrorTitleBytes() {
        Object obj = this.errorTitle_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.errorTitle_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public long getEventCount() {
        return this.eventCount_;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getEventName() {
        Object obj = this.eventName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.eventName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getEventNameBytes() {
        Object obj = this.eventName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.eventName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public long getEventNumericValue() {
        return this.eventNumericValue_;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getInputType() {
        Object obj = this.inputType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.inputType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getInputTypeBytes() {
        Object obj = this.inputType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.inputType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public InteractionDetails getInteractionDetails() {
        InteractionDetails interactionDetails = this.interactionDetails_;
        return interactionDetails == null ? InteractionDetails.getDefaultInstance() : interactionDetails;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public InteractionDetailsOrBuilder getInteractionDetailsOrBuilder() {
        return getInteractionDetails();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getInteractionType() {
        Object obj = this.interactionType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.interactionType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getInteractionTypeBytes() {
        Object obj = this.interactionType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.interactionType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public Metadata getMetadata() {
        Metadata metadata = this.metadata_;
        return metadata == null ? Metadata.getDefaultInstance() : metadata;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public MetadataOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getOperationalEventType() {
        Object obj = this.operationalEventType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.operationalEventType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getOperationalEventTypeBytes() {
        Object obj = this.operationalEventType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.operationalEventType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getOwnerIdentifier() {
        Object obj = this.ownerIdentifier_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.ownerIdentifier_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getOwnerIdentifierBytes() {
        Object obj = this.ownerIdentifier_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.ownerIdentifier_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<EventDetailsProto> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getRefMarker() {
        Object obj = this.refMarker_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.refMarker_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getRefMarkerBytes() {
        Object obj = this.refMarker_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.refMarker_ = copyFromUtf8;
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
        if (!getEventNameBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.eventName_);
        }
        if (!getInteractionTypeBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.interactionType_);
        }
        if (!getOperationalEventTypeBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.operationalEventType_);
        }
        if (!getChannelNameBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(4, this.channelName_);
        }
        if (!getAppComponentBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(5, this.appComponent_);
        }
        if (!getSubComponentBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(6, this.subComponent_);
        }
        long j = this.eventNumericValue_;
        if (j != 0) {
            i2 += CodedOutputStream.computeInt64Size(7, j);
        }
        if (!getContentIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(8, this.contentId_);
        }
        if (!getContentProviderBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(9, this.contentProvider_);
        }
        if (!getContentTypeBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(10, this.contentType_);
        }
        if (!getContentVersionBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(11, this.contentVersion_);
        }
        if (!getInputTypeBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(12, this.inputType_);
        }
        if (!getSourceContextBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(13, this.sourceContext_);
        }
        if (this.interactionDetails_ != null) {
            i2 += CodedOutputStream.computeMessageSize(14, getInteractionDetails());
        }
        if (this.metadata_ != null) {
            i2 += CodedOutputStream.computeMessageSize(15, getMetadata());
        }
        if (!getRefMarkerBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(16, this.refMarker_);
        }
        if (!getSpeakerIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(17, this.speakerId_);
        }
        if (!getTimelineIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(18, this.timelineId_);
        }
        if (!getTimelineNameBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(19, this.timelineName_);
        }
        if (!getTimelineNamespaceBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(20, this.timelineNamespace_);
        }
        if (!getTimelineStateBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(21, this.timelineState_);
        }
        double d = this.timelineElapsedDuration_;
        if (d != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            i2 += CodedOutputStream.computeDoubleSize(22, d);
        }
        if (!getAbortReasonBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(23, this.abortReason_);
        }
        long j2 = this.startTimestamp_;
        if (j2 != 0) {
            i2 += CodedOutputStream.computeInt64Size(24, j2);
        }
        long j3 = this.endTimestamp_;
        if (j3 != 0) {
            i2 += CodedOutputStream.computeInt64Size(25, j3);
        }
        double d2 = this.activeDuration_;
        if (d2 != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            i2 += CodedOutputStream.computeDoubleSize(26, d2);
        }
        double d3 = this.totalDuration_;
        if (d3 != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            i2 += CodedOutputStream.computeDoubleSize(27, d3);
        }
        long j4 = this.eventCount_;
        if (j4 != 0) {
            i2 += CodedOutputStream.computeInt64Size(28, j4);
        }
        if (!getErrorLevelBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(29, this.errorLevel_);
        }
        if (!getErrorTitleBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(30, this.errorTitle_);
        }
        if (!getErrorShortMsgBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(31, this.errorShortMsg_);
        }
        for (Map.Entry<String, String> entry : internalGetDebugInfo().getMap().entrySet()) {
            i2 += CodedOutputStream.computeMessageSize(32, DebugInfoDefaultEntryHolder.defaultEntry.mo10079newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).mo10084build());
        }
        if (!getOwnerIdentifierBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(33, this.ownerIdentifier_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getSourceContext() {
        Object obj = this.sourceContext_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.sourceContext_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getSourceContextBytes() {
        Object obj = this.sourceContext_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.sourceContext_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getSpeakerId() {
        Object obj = this.speakerId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.speakerId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getSpeakerIdBytes() {
        Object obj = this.speakerId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.speakerId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public long getStartTimestamp() {
        return this.startTimestamp_;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getSubComponent() {
        Object obj = this.subComponent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.subComponent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getSubComponentBytes() {
        Object obj = this.subComponent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.subComponent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public double getTimelineElapsedDuration() {
        return this.timelineElapsedDuration_;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getTimelineId() {
        Object obj = this.timelineId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.timelineId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getTimelineIdBytes() {
        Object obj = this.timelineId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.timelineId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getTimelineName() {
        Object obj = this.timelineName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.timelineName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getTimelineNameBytes() {
        Object obj = this.timelineName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.timelineName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getTimelineNamespace() {
        Object obj = this.timelineNamespace_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.timelineNamespace_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getTimelineNamespaceBytes() {
        Object obj = this.timelineNamespace_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.timelineNamespace_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public String getTimelineState() {
        Object obj = this.timelineState_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.timelineState_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public ByteString getTimelineStateBytes() {
        Object obj = this.timelineState_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.timelineState_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public double getTotalDuration() {
        return this.totalDuration_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public boolean hasInteractionDetails() {
        return this.interactionDetails_ != null;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getEventName().hashCode();
        int hashCode2 = getInteractionType().hashCode();
        int hashCode3 = getOperationalEventType().hashCode();
        int hashCode4 = getChannelName().hashCode();
        int hashCode5 = getAppComponent().hashCode();
        int hashCode6 = getSubComponent().hashCode();
        int hashLong = Internal.hashLong(getEventNumericValue());
        int hashCode7 = getContentId().hashCode();
        int hashCode8 = getContentProvider().hashCode();
        int hashCode9 = getContentType().hashCode();
        int hashCode10 = getContentVersion().hashCode();
        int hashCode11 = getInputType().hashCode();
        int hashCode12 = getSourceContext().hashCode() + ((((hashCode11 + ((((hashCode10 + ((((hashCode9 + ((((hashCode8 + ((((hashCode7 + ((((hashLong + ((((hashCode6 + ((((hashCode5 + ((((hashCode4 + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 37) + 6) * 53)) * 37) + 7) * 53)) * 37) + 8) * 53)) * 37) + 9) * 53)) * 37) + 10) * 53)) * 37) + 11) * 53)) * 37) + 12) * 53)) * 37) + 13) * 53);
        if (hasInteractionDetails()) {
            hashCode12 = getInteractionDetails().hashCode() + GeneratedOutlineSupport1.outline4(hashCode12, 37, 14, 53);
        }
        if (hasMetadata()) {
            hashCode12 = getMetadata().hashCode() + GeneratedOutlineSupport1.outline4(hashCode12, 37, 15, 53);
        }
        int outline4 = GeneratedOutlineSupport1.outline4(hashCode12, 37, 16, 53);
        int hashCode13 = getSpeakerId().hashCode();
        int hashCode14 = getTimelineId().hashCode();
        int hashCode15 = getTimelineName().hashCode();
        int hashCode16 = getTimelineNamespace().hashCode();
        int hashCode17 = getTimelineState().hashCode();
        int hashLong2 = Internal.hashLong(Double.doubleToLongBits(getTimelineElapsedDuration()));
        int hashCode18 = getAbortReason().hashCode();
        int hashLong3 = Internal.hashLong(getStartTimestamp());
        int hashLong4 = Internal.hashLong(getEndTimestamp());
        int hashLong5 = Internal.hashLong(Double.doubleToLongBits(getActiveDuration()));
        int hashLong6 = Internal.hashLong(Double.doubleToLongBits(getTotalDuration()));
        int hashLong7 = Internal.hashLong(getEventCount());
        int hashCode19 = getErrorLevel().hashCode();
        int hashCode20 = getErrorTitle().hashCode();
        int hashCode21 = getErrorShortMsg().hashCode() + ((((hashCode20 + ((((hashCode19 + ((((hashLong7 + ((((hashLong6 + ((((hashLong5 + ((((hashLong4 + ((((hashLong3 + ((((hashCode18 + ((((hashLong2 + ((((hashCode17 + ((((hashCode16 + ((((hashCode15 + ((((hashCode14 + ((((hashCode13 + ((((getRefMarker().hashCode() + outline4) * 37) + 17) * 53)) * 37) + 18) * 53)) * 37) + 19) * 53)) * 37) + 20) * 53)) * 37) + 21) * 53)) * 37) + 22) * 53)) * 37) + 23) * 53)) * 37) + 24) * 53)) * 37) + 25) * 53)) * 37) + 26) * 53)) * 37) + 27) * 53)) * 37) + 28) * 53)) * 37) + 29) * 53)) * 37) + 30) * 53)) * 37) + 31) * 53);
        if (!internalGetDebugInfo().getMap().isEmpty()) {
            hashCode21 = GeneratedOutlineSupport1.outline4(hashCode21, 37, 32, 53) + internalGetDebugInfo().hashCode();
        }
        int outline42 = GeneratedOutlineSupport1.outline4(hashCode21, 37, 33, 53);
        int hashCode22 = this.unknownFields.hashCode() + ((getOwnerIdentifier().hashCode() + outline42) * 29);
        this.memoizedHashCode = hashCode22;
        return hashCode22;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EventDetailsProto.class, Builder.class);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 32) {
            return internalGetDebugInfo();
        }
        throw new RuntimeException(GeneratedOutlineSupport1.outline49("Invalid map field number: ", i));
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
        if (!getEventNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.eventName_);
        }
        if (!getInteractionTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.interactionType_);
        }
        if (!getOperationalEventTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.operationalEventType_);
        }
        if (!getChannelNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.channelName_);
        }
        if (!getAppComponentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.appComponent_);
        }
        if (!getSubComponentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.subComponent_);
        }
        long j = this.eventNumericValue_;
        if (j != 0) {
            codedOutputStream.writeInt64(7, j);
        }
        if (!getContentIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.contentId_);
        }
        if (!getContentProviderBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 9, this.contentProvider_);
        }
        if (!getContentTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 10, this.contentType_);
        }
        if (!getContentVersionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.contentVersion_);
        }
        if (!getInputTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 12, this.inputType_);
        }
        if (!getSourceContextBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 13, this.sourceContext_);
        }
        if (this.interactionDetails_ != null) {
            codedOutputStream.writeMessage(14, getInteractionDetails());
        }
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(15, getMetadata());
        }
        if (!getRefMarkerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 16, this.refMarker_);
        }
        if (!getSpeakerIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 17, this.speakerId_);
        }
        if (!getTimelineIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 18, this.timelineId_);
        }
        if (!getTimelineNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 19, this.timelineName_);
        }
        if (!getTimelineNamespaceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 20, this.timelineNamespace_);
        }
        if (!getTimelineStateBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 21, this.timelineState_);
        }
        double d = this.timelineElapsedDuration_;
        if (d != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            codedOutputStream.writeDouble(22, d);
        }
        if (!getAbortReasonBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 23, this.abortReason_);
        }
        long j2 = this.startTimestamp_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(24, j2);
        }
        long j3 = this.endTimestamp_;
        if (j3 != 0) {
            codedOutputStream.writeInt64(25, j3);
        }
        double d2 = this.activeDuration_;
        if (d2 != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            codedOutputStream.writeDouble(26, d2);
        }
        double d3 = this.totalDuration_;
        if (d3 != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            codedOutputStream.writeDouble(27, d3);
        }
        long j4 = this.eventCount_;
        if (j4 != 0) {
            codedOutputStream.writeInt64(28, j4);
        }
        if (!getErrorLevelBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 29, this.errorLevel_);
        }
        if (!getErrorTitleBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 30, this.errorTitle_);
        }
        if (!getErrorShortMsgBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 31, this.errorShortMsg_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetDebugInfo(), DebugInfoDefaultEntryHolder.defaultEntry, 32);
        if (!getOwnerIdentifierBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 33, this.ownerIdentifier_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes9.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EventDetailsProtoOrBuilder {
        private Object abortReason_;
        private double activeDuration_;
        private Object appComponent_;
        private int bitField0_;
        private int bitField1_;
        private Object channelName_;
        private Object contentId_;
        private Object contentProvider_;
        private Object contentType_;
        private Object contentVersion_;
        private MapField<String, String> debugInfo_;
        private long endTimestamp_;
        private Object errorLevel_;
        private Object errorShortMsg_;
        private Object errorTitle_;
        private long eventCount_;
        private Object eventName_;
        private long eventNumericValue_;
        private Object inputType_;
        private SingleFieldBuilderV3<InteractionDetails, InteractionDetails.Builder, InteractionDetailsOrBuilder> interactionDetailsBuilder_;
        private InteractionDetails interactionDetails_;
        private Object interactionType_;
        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> metadataBuilder_;
        private Metadata metadata_;
        private Object operationalEventType_;
        private Object ownerIdentifier_;
        private Object refMarker_;
        private Object sourceContext_;
        private Object speakerId_;
        private long startTimestamp_;
        private Object subComponent_;
        private double timelineElapsedDuration_;
        private Object timelineId_;
        private Object timelineName_;
        private Object timelineNamespace_;
        private Object timelineState_;
        private double totalDuration_;

        public static final Descriptors.Descriptor getDescriptor() {
            return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_descriptor;
        }

        private SingleFieldBuilderV3<InteractionDetails, InteractionDetails.Builder, InteractionDetailsOrBuilder> getInteractionDetailsFieldBuilder() {
            if (this.interactionDetailsBuilder_ == null) {
                this.interactionDetailsBuilder_ = new SingleFieldBuilderV3<>(getInteractionDetails(), getParentForChildren(), isClean());
                this.interactionDetails_ = null;
            }
            return this.interactionDetailsBuilder_;
        }

        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        private MapField<String, String> internalGetDebugInfo() {
            MapField<String, String> mapField = this.debugInfo_;
            return mapField == null ? MapField.emptyMapField(DebugInfoDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableDebugInfo() {
            onChanged();
            if (this.debugInfo_ == null) {
                this.debugInfo_ = MapField.newMapField(DebugInfoDefaultEntryHolder.defaultEntry);
            }
            if (!this.debugInfo_.isMutable()) {
                this.debugInfo_ = this.debugInfo_.copy();
            }
            return this.debugInfo_;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearAbortReason() {
            this.abortReason_ = EventDetailsProto.getDefaultInstance().getAbortReason();
            onChanged();
            return this;
        }

        public Builder clearActiveDuration() {
            this.activeDuration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            onChanged();
            return this;
        }

        public Builder clearAppComponent() {
            this.appComponent_ = EventDetailsProto.getDefaultInstance().getAppComponent();
            onChanged();
            return this;
        }

        public Builder clearChannelName() {
            this.channelName_ = EventDetailsProto.getDefaultInstance().getChannelName();
            onChanged();
            return this;
        }

        public Builder clearContentId() {
            this.contentId_ = EventDetailsProto.getDefaultInstance().getContentId();
            onChanged();
            return this;
        }

        public Builder clearContentProvider() {
            this.contentProvider_ = EventDetailsProto.getDefaultInstance().getContentProvider();
            onChanged();
            return this;
        }

        public Builder clearContentType() {
            this.contentType_ = EventDetailsProto.getDefaultInstance().getContentType();
            onChanged();
            return this;
        }

        public Builder clearContentVersion() {
            this.contentVersion_ = EventDetailsProto.getDefaultInstance().getContentVersion();
            onChanged();
            return this;
        }

        public Builder clearDebugInfo() {
            internalGetMutableDebugInfo().getMutableMap().clear();
            return this;
        }

        public Builder clearEndTimestamp() {
            this.endTimestamp_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearErrorLevel() {
            this.errorLevel_ = EventDetailsProto.getDefaultInstance().getErrorLevel();
            onChanged();
            return this;
        }

        public Builder clearErrorShortMsg() {
            this.errorShortMsg_ = EventDetailsProto.getDefaultInstance().getErrorShortMsg();
            onChanged();
            return this;
        }

        public Builder clearErrorTitle() {
            this.errorTitle_ = EventDetailsProto.getDefaultInstance().getErrorTitle();
            onChanged();
            return this;
        }

        public Builder clearEventCount() {
            this.eventCount_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearEventName() {
            this.eventName_ = EventDetailsProto.getDefaultInstance().getEventName();
            onChanged();
            return this;
        }

        public Builder clearEventNumericValue() {
            this.eventNumericValue_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearInputType() {
            this.inputType_ = EventDetailsProto.getDefaultInstance().getInputType();
            onChanged();
            return this;
        }

        public Builder clearInteractionDetails() {
            if (this.interactionDetailsBuilder_ == null) {
                this.interactionDetails_ = null;
                onChanged();
            } else {
                this.interactionDetails_ = null;
                this.interactionDetailsBuilder_ = null;
            }
            return this;
        }

        public Builder clearInteractionType() {
            this.interactionType_ = EventDetailsProto.getDefaultInstance().getInteractionType();
            onChanged();
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Builder clearOperationalEventType() {
            this.operationalEventType_ = EventDetailsProto.getDefaultInstance().getOperationalEventType();
            onChanged();
            return this;
        }

        public Builder clearOwnerIdentifier() {
            this.ownerIdentifier_ = EventDetailsProto.getDefaultInstance().getOwnerIdentifier();
            onChanged();
            return this;
        }

        public Builder clearRefMarker() {
            this.refMarker_ = EventDetailsProto.getDefaultInstance().getRefMarker();
            onChanged();
            return this;
        }

        public Builder clearSourceContext() {
            this.sourceContext_ = EventDetailsProto.getDefaultInstance().getSourceContext();
            onChanged();
            return this;
        }

        public Builder clearSpeakerId() {
            this.speakerId_ = EventDetailsProto.getDefaultInstance().getSpeakerId();
            onChanged();
            return this;
        }

        public Builder clearStartTimestamp() {
            this.startTimestamp_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearSubComponent() {
            this.subComponent_ = EventDetailsProto.getDefaultInstance().getSubComponent();
            onChanged();
            return this;
        }

        public Builder clearTimelineElapsedDuration() {
            this.timelineElapsedDuration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            onChanged();
            return this;
        }

        public Builder clearTimelineId() {
            this.timelineId_ = EventDetailsProto.getDefaultInstance().getTimelineId();
            onChanged();
            return this;
        }

        public Builder clearTimelineName() {
            this.timelineName_ = EventDetailsProto.getDefaultInstance().getTimelineName();
            onChanged();
            return this;
        }

        public Builder clearTimelineNamespace() {
            this.timelineNamespace_ = EventDetailsProto.getDefaultInstance().getTimelineNamespace();
            onChanged();
            return this;
        }

        public Builder clearTimelineState() {
            this.timelineState_ = EventDetailsProto.getDefaultInstance().getTimelineState();
            onChanged();
            return this;
        }

        public Builder clearTotalDuration() {
            this.totalDuration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            onChanged();
            return this;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public boolean containsDebugInfo(String str) {
            if (str != null) {
                return internalGetDebugInfo().getMap().containsKey(str);
            }
            throw new NullPointerException();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getAbortReason() {
            Object obj = this.abortReason_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.abortReason_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getAbortReasonBytes() {
            Object obj = this.abortReason_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.abortReason_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public double getActiveDuration() {
            return this.activeDuration_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getAppComponent() {
            Object obj = this.appComponent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.appComponent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getAppComponentBytes() {
            Object obj = this.appComponent_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.appComponent_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getChannelName() {
            Object obj = this.channelName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.channelName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getChannelNameBytes() {
            Object obj = this.channelName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.channelName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getContentId() {
            Object obj = this.contentId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.contentId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getContentIdBytes() {
            Object obj = this.contentId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.contentId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getContentProvider() {
            Object obj = this.contentProvider_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.contentProvider_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getContentProviderBytes() {
            Object obj = this.contentProvider_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.contentProvider_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getContentType() {
            Object obj = this.contentType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.contentType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getContentTypeBytes() {
            Object obj = this.contentType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.contentType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getContentVersion() {
            Object obj = this.contentVersion_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.contentVersion_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getContentVersionBytes() {
            Object obj = this.contentVersion_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.contentVersion_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        @Deprecated
        public Map<String, String> getDebugInfo() {
            return getDebugInfoMap();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public int getDebugInfoCount() {
            return internalGetDebugInfo().getMap().size();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public Map<String, String> getDebugInfoMap() {
            return internalGetDebugInfo().getMap();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getDebugInfoOrDefault(String str, String str2) {
            if (str != null) {
                Map<String, String> map = internalGetDebugInfo().getMap();
                return map.containsKey(str) ? map.get(str) : str2;
            }
            throw new NullPointerException();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getDebugInfoOrThrow(String str) {
            if (str != null) {
                Map<String, String> map = internalGetDebugInfo().getMap();
                if (map.containsKey(str)) {
                    return map.get(str);
                }
                throw new IllegalArgumentException();
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_descriptor;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public long getEndTimestamp() {
            return this.endTimestamp_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getErrorLevel() {
            Object obj = this.errorLevel_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.errorLevel_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getErrorLevelBytes() {
            Object obj = this.errorLevel_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.errorLevel_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getErrorShortMsg() {
            Object obj = this.errorShortMsg_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.errorShortMsg_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getErrorShortMsgBytes() {
            Object obj = this.errorShortMsg_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.errorShortMsg_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getErrorTitle() {
            Object obj = this.errorTitle_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.errorTitle_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getErrorTitleBytes() {
            Object obj = this.errorTitle_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.errorTitle_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public long getEventCount() {
            return this.eventCount_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getEventName() {
            Object obj = this.eventName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.eventName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getEventNameBytes() {
            Object obj = this.eventName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.eventName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public long getEventNumericValue() {
            return this.eventNumericValue_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getInputType() {
            Object obj = this.inputType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.inputType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getInputTypeBytes() {
            Object obj = this.inputType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.inputType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public InteractionDetails getInteractionDetails() {
            SingleFieldBuilderV3<InteractionDetails, InteractionDetails.Builder, InteractionDetailsOrBuilder> singleFieldBuilderV3 = this.interactionDetailsBuilder_;
            if (singleFieldBuilderV3 == null) {
                InteractionDetails interactionDetails = this.interactionDetails_;
                return interactionDetails == null ? InteractionDetails.getDefaultInstance() : interactionDetails;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public InteractionDetails.Builder getInteractionDetailsBuilder() {
            onChanged();
            return getInteractionDetailsFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public InteractionDetailsOrBuilder getInteractionDetailsOrBuilder() {
            SingleFieldBuilderV3<InteractionDetails, InteractionDetails.Builder, InteractionDetailsOrBuilder> singleFieldBuilderV3 = this.interactionDetailsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            InteractionDetails interactionDetails = this.interactionDetails_;
            return interactionDetails == null ? InteractionDetails.getDefaultInstance() : interactionDetails;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getInteractionType() {
            Object obj = this.interactionType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.interactionType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getInteractionTypeBytes() {
            Object obj = this.interactionType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.interactionType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public Metadata getMetadata() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Metadata metadata = this.metadata_;
                return metadata == null ? Metadata.getDefaultInstance() : metadata;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Metadata.Builder getMetadataBuilder() {
            onChanged();
            return getMetadataFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public MetadataOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.getDefaultInstance() : metadata;
        }

        @Deprecated
        public Map<String, String> getMutableDebugInfo() {
            return internalGetMutableDebugInfo().getMutableMap();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getOperationalEventType() {
            Object obj = this.operationalEventType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.operationalEventType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getOperationalEventTypeBytes() {
            Object obj = this.operationalEventType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.operationalEventType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getOwnerIdentifier() {
            Object obj = this.ownerIdentifier_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.ownerIdentifier_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getOwnerIdentifierBytes() {
            Object obj = this.ownerIdentifier_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ownerIdentifier_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getRefMarker() {
            Object obj = this.refMarker_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.refMarker_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getRefMarkerBytes() {
            Object obj = this.refMarker_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.refMarker_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getSourceContext() {
            Object obj = this.sourceContext_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.sourceContext_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getSourceContextBytes() {
            Object obj = this.sourceContext_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.sourceContext_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getSpeakerId() {
            Object obj = this.speakerId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.speakerId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getSpeakerIdBytes() {
            Object obj = this.speakerId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.speakerId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public long getStartTimestamp() {
            return this.startTimestamp_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getSubComponent() {
            Object obj = this.subComponent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.subComponent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getSubComponentBytes() {
            Object obj = this.subComponent_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.subComponent_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public double getTimelineElapsedDuration() {
            return this.timelineElapsedDuration_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getTimelineId() {
            Object obj = this.timelineId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.timelineId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getTimelineIdBytes() {
            Object obj = this.timelineId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timelineId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getTimelineName() {
            Object obj = this.timelineName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.timelineName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getTimelineNameBytes() {
            Object obj = this.timelineName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timelineName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getTimelineNamespace() {
            Object obj = this.timelineNamespace_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.timelineNamespace_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getTimelineNamespaceBytes() {
            Object obj = this.timelineNamespace_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timelineNamespace_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public String getTimelineState() {
            Object obj = this.timelineState_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.timelineState_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public ByteString getTimelineStateBytes() {
            Object obj = this.timelineState_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timelineState_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public double getTotalDuration() {
            return this.totalDuration_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public boolean hasInteractionDetails() {
            return (this.interactionDetailsBuilder_ == null && this.interactionDetails_ == null) ? false : true;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.EventDetailsProtoOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EventDetailsProtoOuterClass.internal_static_protobuf_EventDetailsProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EventDetailsProto.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 32) {
                return internalGetDebugInfo();
            }
            throw new RuntimeException(GeneratedOutlineSupport1.outline49("Invalid map field number: ", i));
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 32) {
                return internalGetMutableDebugInfo();
            }
            throw new RuntimeException(GeneratedOutlineSupport1.outline49("Invalid map field number: ", i));
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeInteractionDetails(InteractionDetails interactionDetails) {
            SingleFieldBuilderV3<InteractionDetails, InteractionDetails.Builder, InteractionDetailsOrBuilder> singleFieldBuilderV3 = this.interactionDetailsBuilder_;
            if (singleFieldBuilderV3 == null) {
                InteractionDetails interactionDetails2 = this.interactionDetails_;
                if (interactionDetails2 != null) {
                    this.interactionDetails_ = InteractionDetails.newBuilder(interactionDetails2).mergeFrom(interactionDetails).mo10085buildPartial();
                } else {
                    this.interactionDetails_ = interactionDetails;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(interactionDetails);
            }
            return this;
        }

        public Builder mergeMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Metadata metadata2 = this.metadata_;
                if (metadata2 != null) {
                    this.metadata_ = Metadata.newBuilder(metadata2).mergeFrom(metadata).mo10085buildPartial();
                } else {
                    this.metadata_ = metadata;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(metadata);
            }
            return this;
        }

        public Builder putAllDebugInfo(Map<String, String> map) {
            internalGetMutableDebugInfo().getMutableMap().putAll(map);
            return this;
        }

        public Builder putDebugInfo(String str, String str2) {
            if (str != null) {
                if (str2 != null) {
                    internalGetMutableDebugInfo().getMutableMap().put(str, str2);
                    return this;
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }

        public Builder removeDebugInfo(String str) {
            if (str != null) {
                internalGetMutableDebugInfo().getMutableMap().remove(str);
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setAbortReason(String str) {
            if (str != null) {
                this.abortReason_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setAbortReasonBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.abortReason_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setActiveDuration(double d) {
            this.activeDuration_ = d;
            onChanged();
            return this;
        }

        public Builder setAppComponent(String str) {
            if (str != null) {
                this.appComponent_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setAppComponentBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.appComponent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setChannelName(String str) {
            if (str != null) {
                this.channelName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setChannelNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.channelName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setContentId(String str) {
            if (str != null) {
                this.contentId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setContentIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.contentId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setContentProvider(String str) {
            if (str != null) {
                this.contentProvider_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setContentProviderBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.contentProvider_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setContentType(String str) {
            if (str != null) {
                this.contentType_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setContentTypeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.contentType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setContentVersion(String str) {
            if (str != null) {
                this.contentVersion_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setContentVersionBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.contentVersion_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setEndTimestamp(long j) {
            this.endTimestamp_ = j;
            onChanged();
            return this;
        }

        public Builder setErrorLevel(String str) {
            if (str != null) {
                this.errorLevel_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setErrorLevelBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.errorLevel_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setErrorShortMsg(String str) {
            if (str != null) {
                this.errorShortMsg_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setErrorShortMsgBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.errorShortMsg_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setErrorTitle(String str) {
            if (str != null) {
                this.errorTitle_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setErrorTitleBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.errorTitle_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setEventCount(long j) {
            this.eventCount_ = j;
            onChanged();
            return this;
        }

        public Builder setEventName(String str) {
            if (str != null) {
                this.eventName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setEventNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.eventName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setEventNumericValue(long j) {
            this.eventNumericValue_ = j;
            onChanged();
            return this;
        }

        public Builder setInputType(String str) {
            if (str != null) {
                this.inputType_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setInputTypeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.inputType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setInteractionDetails(InteractionDetails interactionDetails) {
            SingleFieldBuilderV3<InteractionDetails, InteractionDetails.Builder, InteractionDetailsOrBuilder> singleFieldBuilderV3 = this.interactionDetailsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(interactionDetails);
            } else if (interactionDetails != null) {
                this.interactionDetails_ = interactionDetails;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setInteractionType(String str) {
            if (str != null) {
                this.interactionType_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setInteractionTypeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.interactionType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(metadata);
            } else if (metadata != null) {
                this.metadata_ = metadata;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setOperationalEventType(String str) {
            if (str != null) {
                this.operationalEventType_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setOperationalEventTypeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.operationalEventType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setOwnerIdentifier(String str) {
            if (str != null) {
                this.ownerIdentifier_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setOwnerIdentifierBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.ownerIdentifier_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setRefMarker(String str) {
            if (str != null) {
                this.refMarker_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setRefMarkerBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.refMarker_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSourceContext(String str) {
            if (str != null) {
                this.sourceContext_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSourceContextBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.sourceContext_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSpeakerId(String str) {
            if (str != null) {
                this.speakerId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSpeakerIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.speakerId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setStartTimestamp(long j) {
            this.startTimestamp_ = j;
            onChanged();
            return this;
        }

        public Builder setSubComponent(String str) {
            if (str != null) {
                this.subComponent_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSubComponentBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.subComponent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTimelineElapsedDuration(double d) {
            this.timelineElapsedDuration_ = d;
            onChanged();
            return this;
        }

        public Builder setTimelineId(String str) {
            if (str != null) {
                this.timelineId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTimelineIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.timelineId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTimelineName(String str) {
            if (str != null) {
                this.timelineName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTimelineNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.timelineName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTimelineNamespace(String str) {
            if (str != null) {
                this.timelineNamespace_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTimelineNamespaceBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.timelineNamespace_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTimelineState(String str) {
            if (str != null) {
                this.timelineState_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTimelineStateBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.timelineState_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTotalDuration(double d) {
            this.totalDuration_ = d;
            onChanged();
            return this;
        }

        private Builder() {
            this.eventName_ = "";
            this.interactionType_ = "";
            this.operationalEventType_ = "";
            this.channelName_ = "";
            this.appComponent_ = "";
            this.subComponent_ = "";
            this.contentId_ = "";
            this.contentProvider_ = "";
            this.contentType_ = "";
            this.contentVersion_ = "";
            this.inputType_ = "";
            this.sourceContext_ = "";
            this.interactionDetails_ = null;
            this.metadata_ = null;
            this.refMarker_ = "";
            this.speakerId_ = "";
            this.timelineId_ = "";
            this.timelineName_ = "";
            this.timelineNamespace_ = "";
            this.timelineState_ = "";
            this.abortReason_ = "";
            this.errorLevel_ = "";
            this.errorTitle_ = "";
            this.errorShortMsg_ = "";
            this.ownerIdentifier_ = "";
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public EventDetailsProto mo10084build() {
            EventDetailsProto mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public EventDetailsProto mo10085buildPartial() {
            EventDetailsProto eventDetailsProto = new EventDetailsProto(this);
            eventDetailsProto.eventName_ = this.eventName_;
            eventDetailsProto.interactionType_ = this.interactionType_;
            eventDetailsProto.operationalEventType_ = this.operationalEventType_;
            eventDetailsProto.channelName_ = this.channelName_;
            eventDetailsProto.appComponent_ = this.appComponent_;
            eventDetailsProto.subComponent_ = this.subComponent_;
            eventDetailsProto.eventNumericValue_ = this.eventNumericValue_;
            eventDetailsProto.contentId_ = this.contentId_;
            eventDetailsProto.contentProvider_ = this.contentProvider_;
            eventDetailsProto.contentType_ = this.contentType_;
            eventDetailsProto.contentVersion_ = this.contentVersion_;
            eventDetailsProto.inputType_ = this.inputType_;
            eventDetailsProto.sourceContext_ = this.sourceContext_;
            SingleFieldBuilderV3<InteractionDetails, InteractionDetails.Builder, InteractionDetailsOrBuilder> singleFieldBuilderV3 = this.interactionDetailsBuilder_;
            if (singleFieldBuilderV3 == null) {
                eventDetailsProto.interactionDetails_ = this.interactionDetails_;
            } else {
                eventDetailsProto.interactionDetails_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV32 = this.metadataBuilder_;
            if (singleFieldBuilderV32 == null) {
                eventDetailsProto.metadata_ = this.metadata_;
            } else {
                eventDetailsProto.metadata_ = singleFieldBuilderV32.build();
            }
            eventDetailsProto.refMarker_ = this.refMarker_;
            eventDetailsProto.speakerId_ = this.speakerId_;
            eventDetailsProto.timelineId_ = this.timelineId_;
            eventDetailsProto.timelineName_ = this.timelineName_;
            eventDetailsProto.timelineNamespace_ = this.timelineNamespace_;
            eventDetailsProto.timelineState_ = this.timelineState_;
            eventDetailsProto.timelineElapsedDuration_ = this.timelineElapsedDuration_;
            eventDetailsProto.abortReason_ = this.abortReason_;
            eventDetailsProto.startTimestamp_ = this.startTimestamp_;
            eventDetailsProto.endTimestamp_ = this.endTimestamp_;
            eventDetailsProto.activeDuration_ = this.activeDuration_;
            eventDetailsProto.totalDuration_ = this.totalDuration_;
            eventDetailsProto.eventCount_ = this.eventCount_;
            eventDetailsProto.errorLevel_ = this.errorLevel_;
            eventDetailsProto.errorTitle_ = this.errorTitle_;
            eventDetailsProto.errorShortMsg_ = this.errorShortMsg_;
            eventDetailsProto.debugInfo_ = internalGetDebugInfo();
            eventDetailsProto.debugInfo_.makeImmutable();
            eventDetailsProto.ownerIdentifier_ = this.ownerIdentifier_;
            eventDetailsProto.bitField0_ = 0;
            onBuilt();
            return eventDetailsProto;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public EventDetailsProto mo10094getDefaultInstanceForType() {
            return EventDetailsProto.getDefaultInstance();
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
            this.eventName_ = "";
            this.interactionType_ = "";
            this.operationalEventType_ = "";
            this.channelName_ = "";
            this.appComponent_ = "";
            this.subComponent_ = "";
            this.eventNumericValue_ = 0L;
            this.contentId_ = "";
            this.contentProvider_ = "";
            this.contentType_ = "";
            this.contentVersion_ = "";
            this.inputType_ = "";
            this.sourceContext_ = "";
            if (this.interactionDetailsBuilder_ == null) {
                this.interactionDetails_ = null;
            } else {
                this.interactionDetails_ = null;
                this.interactionDetailsBuilder_ = null;
            }
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            this.refMarker_ = "";
            this.speakerId_ = "";
            this.timelineId_ = "";
            this.timelineName_ = "";
            this.timelineNamespace_ = "";
            this.timelineState_ = "";
            this.timelineElapsedDuration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            this.abortReason_ = "";
            this.startTimestamp_ = 0L;
            this.endTimestamp_ = 0L;
            this.activeDuration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            this.totalDuration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            this.eventCount_ = 0L;
            this.errorLevel_ = "";
            this.errorTitle_ = "";
            this.errorShortMsg_ = "";
            internalGetMutableDebugInfo().clear();
            this.ownerIdentifier_ = "";
            return this;
        }

        public Builder setInteractionDetails(InteractionDetails.Builder builder) {
            SingleFieldBuilderV3<InteractionDetails, InteractionDetails.Builder, InteractionDetailsOrBuilder> singleFieldBuilderV3 = this.interactionDetailsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.interactionDetails_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder setMetadata(Metadata.Builder builder) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.mo10084build();
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
            if (message instanceof EventDetailsProto) {
                return mergeFrom((EventDetailsProto) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(EventDetailsProto eventDetailsProto) {
            if (eventDetailsProto == EventDetailsProto.getDefaultInstance()) {
                return this;
            }
            if (!eventDetailsProto.getEventName().isEmpty()) {
                this.eventName_ = eventDetailsProto.eventName_;
                onChanged();
            }
            if (!eventDetailsProto.getInteractionType().isEmpty()) {
                this.interactionType_ = eventDetailsProto.interactionType_;
                onChanged();
            }
            if (!eventDetailsProto.getOperationalEventType().isEmpty()) {
                this.operationalEventType_ = eventDetailsProto.operationalEventType_;
                onChanged();
            }
            if (!eventDetailsProto.getChannelName().isEmpty()) {
                this.channelName_ = eventDetailsProto.channelName_;
                onChanged();
            }
            if (!eventDetailsProto.getAppComponent().isEmpty()) {
                this.appComponent_ = eventDetailsProto.appComponent_;
                onChanged();
            }
            if (!eventDetailsProto.getSubComponent().isEmpty()) {
                this.subComponent_ = eventDetailsProto.subComponent_;
                onChanged();
            }
            if (eventDetailsProto.getEventNumericValue() != 0) {
                setEventNumericValue(eventDetailsProto.getEventNumericValue());
            }
            if (!eventDetailsProto.getContentId().isEmpty()) {
                this.contentId_ = eventDetailsProto.contentId_;
                onChanged();
            }
            if (!eventDetailsProto.getContentProvider().isEmpty()) {
                this.contentProvider_ = eventDetailsProto.contentProvider_;
                onChanged();
            }
            if (!eventDetailsProto.getContentType().isEmpty()) {
                this.contentType_ = eventDetailsProto.contentType_;
                onChanged();
            }
            if (!eventDetailsProto.getContentVersion().isEmpty()) {
                this.contentVersion_ = eventDetailsProto.contentVersion_;
                onChanged();
            }
            if (!eventDetailsProto.getInputType().isEmpty()) {
                this.inputType_ = eventDetailsProto.inputType_;
                onChanged();
            }
            if (!eventDetailsProto.getSourceContext().isEmpty()) {
                this.sourceContext_ = eventDetailsProto.sourceContext_;
                onChanged();
            }
            if (eventDetailsProto.hasInteractionDetails()) {
                mergeInteractionDetails(eventDetailsProto.getInteractionDetails());
            }
            if (eventDetailsProto.hasMetadata()) {
                mergeMetadata(eventDetailsProto.getMetadata());
            }
            if (!eventDetailsProto.getRefMarker().isEmpty()) {
                this.refMarker_ = eventDetailsProto.refMarker_;
                onChanged();
            }
            if (!eventDetailsProto.getSpeakerId().isEmpty()) {
                this.speakerId_ = eventDetailsProto.speakerId_;
                onChanged();
            }
            if (!eventDetailsProto.getTimelineId().isEmpty()) {
                this.timelineId_ = eventDetailsProto.timelineId_;
                onChanged();
            }
            if (!eventDetailsProto.getTimelineName().isEmpty()) {
                this.timelineName_ = eventDetailsProto.timelineName_;
                onChanged();
            }
            if (!eventDetailsProto.getTimelineNamespace().isEmpty()) {
                this.timelineNamespace_ = eventDetailsProto.timelineNamespace_;
                onChanged();
            }
            if (!eventDetailsProto.getTimelineState().isEmpty()) {
                this.timelineState_ = eventDetailsProto.timelineState_;
                onChanged();
            }
            if (eventDetailsProto.getTimelineElapsedDuration() != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                setTimelineElapsedDuration(eventDetailsProto.getTimelineElapsedDuration());
            }
            if (!eventDetailsProto.getAbortReason().isEmpty()) {
                this.abortReason_ = eventDetailsProto.abortReason_;
                onChanged();
            }
            if (eventDetailsProto.getStartTimestamp() != 0) {
                setStartTimestamp(eventDetailsProto.getStartTimestamp());
            }
            if (eventDetailsProto.getEndTimestamp() != 0) {
                setEndTimestamp(eventDetailsProto.getEndTimestamp());
            }
            if (eventDetailsProto.getActiveDuration() != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                setActiveDuration(eventDetailsProto.getActiveDuration());
            }
            if (eventDetailsProto.getTotalDuration() != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                setTotalDuration(eventDetailsProto.getTotalDuration());
            }
            if (eventDetailsProto.getEventCount() != 0) {
                setEventCount(eventDetailsProto.getEventCount());
            }
            if (!eventDetailsProto.getErrorLevel().isEmpty()) {
                this.errorLevel_ = eventDetailsProto.errorLevel_;
                onChanged();
            }
            if (!eventDetailsProto.getErrorTitle().isEmpty()) {
                this.errorTitle_ = eventDetailsProto.errorTitle_;
                onChanged();
            }
            if (!eventDetailsProto.getErrorShortMsg().isEmpty()) {
                this.errorShortMsg_ = eventDetailsProto.errorShortMsg_;
                onChanged();
            }
            internalGetMutableDebugInfo().mergeFrom(eventDetailsProto.internalGetDebugInfo());
            if (!eventDetailsProto.getOwnerIdentifier().isEmpty()) {
                this.ownerIdentifier_ = eventDetailsProto.ownerIdentifier_;
                onChanged();
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) eventDetailsProto).unknownFields);
            onChanged();
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.eventName_ = "";
            this.interactionType_ = "";
            this.operationalEventType_ = "";
            this.channelName_ = "";
            this.appComponent_ = "";
            this.subComponent_ = "";
            this.contentId_ = "";
            this.contentProvider_ = "";
            this.contentType_ = "";
            this.contentVersion_ = "";
            this.inputType_ = "";
            this.sourceContext_ = "";
            this.interactionDetails_ = null;
            this.metadata_ = null;
            this.refMarker_ = "";
            this.speakerId_ = "";
            this.timelineId_ = "";
            this.timelineName_ = "";
            this.timelineNamespace_ = "";
            this.timelineState_ = "";
            this.abortReason_ = "";
            this.errorLevel_ = "";
            this.errorTitle_ = "";
            this.errorShortMsg_ = "";
            this.ownerIdentifier_ = "";
            maybeForceBuilderInitialization();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.access$27200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.alexa.mobilytics.protobuf.EventDetailsProto r3 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.alexa.mobilytics.protobuf.EventDetailsProto r4 = (com.amazon.alexa.mobilytics.protobuf.EventDetailsProto) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.EventDetailsProto.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.EventDetailsProto$Builder");
        }
    }

    public static Builder newBuilder(EventDetailsProto eventDetailsProto) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(eventDetailsProto);
    }

    public static EventDetailsProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private EventDetailsProto(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static EventDetailsProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EventDetailsProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EventDetailsProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public EventDetailsProto mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static EventDetailsProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private EventDetailsProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.eventName_ = "";
        this.interactionType_ = "";
        this.operationalEventType_ = "";
        this.channelName_ = "";
        this.appComponent_ = "";
        this.subComponent_ = "";
        this.eventNumericValue_ = 0L;
        this.contentId_ = "";
        this.contentProvider_ = "";
        this.contentType_ = "";
        this.contentVersion_ = "";
        this.inputType_ = "";
        this.sourceContext_ = "";
        this.refMarker_ = "";
        this.speakerId_ = "";
        this.timelineId_ = "";
        this.timelineName_ = "";
        this.timelineNamespace_ = "";
        this.timelineState_ = "";
        this.timelineElapsedDuration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.abortReason_ = "";
        this.startTimestamp_ = 0L;
        this.endTimestamp_ = 0L;
        this.activeDuration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.totalDuration_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.eventCount_ = 0L;
        this.errorLevel_ = "";
        this.errorTitle_ = "";
        this.errorShortMsg_ = "";
        this.ownerIdentifier_ = "";
    }

    public static EventDetailsProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static EventDetailsProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static EventDetailsProto parseFrom(InputStream inputStream) throws IOException {
        return (EventDetailsProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static EventDetailsProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EventDetailsProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EventDetailsProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EventDetailsProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static EventDetailsProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EventDetailsProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    /* JADX WARN: Type inference failed for: r3v50, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object] */
    private EventDetailsProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            InteractionDetails.Builder builder = null;
                            Metadata.Builder mo10081toBuilder = null;
                            switch (readTag) {
                                case 0:
                                    break;
                                case 10:
                                    this.eventName_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 18:
                                    this.interactionType_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 26:
                                    this.operationalEventType_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 34:
                                    this.channelName_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 42:
                                    this.appComponent_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 50:
                                    this.subComponent_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 56:
                                    this.eventNumericValue_ = codedInputStream.readInt64();
                                    continue;
                                case 66:
                                    this.contentId_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 74:
                                    this.contentProvider_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 82:
                                    this.contentType_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 90:
                                    this.contentVersion_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 98:
                                    this.inputType_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 106:
                                    this.sourceContext_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 114:
                                    builder = this.interactionDetails_ != null ? this.interactionDetails_.mo10081toBuilder() : builder;
                                    this.interactionDetails_ = (InteractionDetails) codedInputStream.readMessage(InteractionDetails.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.interactionDetails_);
                                        this.interactionDetails_ = builder.mo10085buildPartial();
                                    } else {
                                        continue;
                                    }
                                case 122:
                                    mo10081toBuilder = this.metadata_ != null ? this.metadata_.mo10081toBuilder() : mo10081toBuilder;
                                    this.metadata_ = (Metadata) codedInputStream.readMessage(Metadata.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom(this.metadata_);
                                        this.metadata_ = mo10081toBuilder.mo10085buildPartial();
                                    } else {
                                        continue;
                                    }
                                case 130:
                                    this.refMarker_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 138:
                                    this.speakerId_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 146:
                                    this.timelineId_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 154:
                                    this.timelineName_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 162:
                                    this.timelineNamespace_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 170:
                                    this.timelineState_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 177:
                                    this.timelineElapsedDuration_ = codedInputStream.readDouble();
                                    continue;
                                case 186:
                                    this.abortReason_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 192:
                                    this.startTimestamp_ = codedInputStream.readInt64();
                                    continue;
                                case 200:
                                    this.endTimestamp_ = codedInputStream.readInt64();
                                    continue;
                                case 209:
                                    this.activeDuration_ = codedInputStream.readDouble();
                                    continue;
                                case JfifUtil.MARKER_EOI /* 217 */:
                                    this.totalDuration_ = codedInputStream.readDouble();
                                    continue;
                                case 224:
                                    this.eventCount_ = codedInputStream.readInt64();
                                    continue;
                                case 234:
                                    this.errorLevel_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 242:
                                    this.errorTitle_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 250:
                                    this.errorShortMsg_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 258:
                                    boolean z3 = z2 ? 1 : 0;
                                    boolean z4 = z2 ? 1 : 0;
                                    boolean z5 = z2 ? 1 : 0;
                                    boolean z6 = z2 ? 1 : 0;
                                    boolean z7 = z2 ? 1 : 0;
                                    boolean z8 = z3 & true;
                                    z2 = z2;
                                    if (!z8) {
                                        this.debugInfo_ = MapField.newMapField(DebugInfoDefaultEntryHolder.defaultEntry);
                                        z2 |= true;
                                    }
                                    MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(DebugInfoDefaultEntryHolder.defaultEntry.mo9954getParserForType(), extensionRegistryLite);
                                    this.debugInfo_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                                    continue;
                                case 266:
                                    this.ownerIdentifier_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                default:
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        break;
                                    } else {
                                        continue;
                                    }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
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
