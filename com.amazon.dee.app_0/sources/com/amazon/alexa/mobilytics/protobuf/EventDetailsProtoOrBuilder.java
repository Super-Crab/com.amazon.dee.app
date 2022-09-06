package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;
/* loaded from: classes9.dex */
public interface EventDetailsProtoOrBuilder extends MessageOrBuilder {
    boolean containsDebugInfo(String str);

    String getAbortReason();

    ByteString getAbortReasonBytes();

    double getActiveDuration();

    String getAppComponent();

    ByteString getAppComponentBytes();

    String getChannelName();

    ByteString getChannelNameBytes();

    String getContentId();

    ByteString getContentIdBytes();

    String getContentProvider();

    ByteString getContentProviderBytes();

    String getContentType();

    ByteString getContentTypeBytes();

    String getContentVersion();

    ByteString getContentVersionBytes();

    @Deprecated
    Map<String, String> getDebugInfo();

    int getDebugInfoCount();

    Map<String, String> getDebugInfoMap();

    String getDebugInfoOrDefault(String str, String str2);

    String getDebugInfoOrThrow(String str);

    long getEndTimestamp();

    String getErrorLevel();

    ByteString getErrorLevelBytes();

    String getErrorShortMsg();

    ByteString getErrorShortMsgBytes();

    String getErrorTitle();

    ByteString getErrorTitleBytes();

    long getEventCount();

    String getEventName();

    ByteString getEventNameBytes();

    long getEventNumericValue();

    String getInputType();

    ByteString getInputTypeBytes();

    EventDetailsProto.InteractionDetails getInteractionDetails();

    EventDetailsProto.InteractionDetailsOrBuilder getInteractionDetailsOrBuilder();

    String getInteractionType();

    ByteString getInteractionTypeBytes();

    EventDetailsProto.Metadata getMetadata();

    EventDetailsProto.MetadataOrBuilder getMetadataOrBuilder();

    String getOperationalEventType();

    ByteString getOperationalEventTypeBytes();

    String getOwnerIdentifier();

    ByteString getOwnerIdentifierBytes();

    String getRefMarker();

    ByteString getRefMarkerBytes();

    String getSourceContext();

    ByteString getSourceContextBytes();

    String getSpeakerId();

    ByteString getSpeakerIdBytes();

    long getStartTimestamp();

    String getSubComponent();

    ByteString getSubComponentBytes();

    double getTimelineElapsedDuration();

    String getTimelineId();

    ByteString getTimelineIdBytes();

    String getTimelineName();

    ByteString getTimelineNameBytes();

    String getTimelineNamespace();

    ByteString getTimelineNamespaceBytes();

    String getTimelineState();

    ByteString getTimelineStateBytes();

    double getTotalDuration();

    boolean hasInteractionDetails();

    boolean hasMetadata();
}
