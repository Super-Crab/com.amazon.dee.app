package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.BuildConfig;
import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.protobuf.ApplicationProto;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class ApplicationProtobufHandler implements ProtobufHandler {
    private static final String TAG = Log.tag(ApplicationProtobufHandler.class);
    private final ApplicationConfiguration applicationConfiguration;

    @Inject
    public ApplicationProtobufHandler(@NonNull ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = (ApplicationConfiguration) Preconditions.checkNotNull(applicationConfiguration);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler
    @Nullable
    public MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent) {
        ApplicationProto.Builder newBuilder = ApplicationProto.newBuilder();
        newBuilder.setVersionCode(this.applicationConfiguration.versionCode());
        newBuilder.setVersionName(this.applicationConfiguration.versionName());
        if (mobilyticsEvent.getEventType().equals(EventType.OPERATIONAL)) {
            newBuilder.setPackageName(this.applicationConfiguration.packageName()).setTitle(this.applicationConfiguration.title()).setAppId(this.applicationConfiguration.id()).setSdk(ApplicationProto.Sdk.newBuilder().setName("AlexaMobilyticsAndroid").setVersion(BuildConfig.VERSION_NAME));
        }
        return MobilyticsMessageProto.newBuilder().setApplication(newBuilder).mo10084build();
    }
}
