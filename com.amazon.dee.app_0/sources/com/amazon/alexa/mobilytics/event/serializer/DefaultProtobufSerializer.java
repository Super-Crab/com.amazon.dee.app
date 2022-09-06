package com.amazon.alexa.mobilytics.event.serializer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class DefaultProtobufSerializer implements ProtobufSerializer {
    private static final String TAG = Log.tag(DefaultProtobufSerializer.class);
    private final List<ProtobufHandler> handlers;

    @Inject
    public DefaultProtobufSerializer(@NonNull List<ProtobufHandler> list) {
        this.handlers = (List) Preconditions.checkNotNull(list);
    }

    private MobilyticsMessageProto doSerialize(@NonNull MobilyticsEvent mobilyticsEvent, @Nullable ProtobufHandler[] protobufHandlerArr) {
        ArrayList<ProtobufHandler> arrayList = new ArrayList(this.handlers);
        if (protobufHandlerArr != null) {
            arrayList.addAll(Arrays.asList(protobufHandlerArr));
        }
        MobilyticsMessageProto.Builder newBuilder = MobilyticsMessageProto.newBuilder();
        for (ProtobufHandler protobufHandler : arrayList) {
            try {
                Log.v(TAG, "Running handler: %s", protobufHandler.getClass().getName());
                MobilyticsMessageProto serialize = protobufHandler.serialize(mobilyticsEvent);
                if (serialize != null) {
                    newBuilder.mergeFrom(serialize);
                }
            } catch (Exception e) {
                Log.e(TAG, e, "Handler %s failed to run", protobufHandler.getClass().getName());
            }
        }
        return newBuilder.mo10084build();
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.ProtobufSerializer
    public MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent) {
        return doSerialize(mobilyticsEvent, null);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.ProtobufSerializer
    public MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull ProtobufHandler... protobufHandlerArr) {
        return doSerialize(mobilyticsEvent, protobufHandlerArr);
    }
}
