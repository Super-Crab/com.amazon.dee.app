package com.amazon.deecomms.common.audio;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.auth.EPMSAuthorizationProvider;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes12.dex */
public class MediaStorageServiceAudioMessage extends MediaStorageServiceAudio {
    private final String messagingMediaEndpoint;
    private final String resourceType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaStorageServiceAudioMessage(ReadableMap readableMap) {
        super(readableMap);
        this.resourceType = OkHttpClientWrapper.MESSAGING_CLIENT;
        this.messagingMediaEndpoint = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.MESSAGING_MEDIA_ENDPOINT_KEY);
    }

    @Override // com.amazon.deecomms.common.audio.MediaStorageServiceAudio
    public IHttpClient.Request generateAudioUploadRequest() {
        String fullUrlEndpoint = getFullUrlEndpoint(AppUrl.UPLOAD_MEDIA_RESOURCE_API);
        CommsLogger commsLogger = this.LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Audio Message Upload API Suffix: ");
        outline1.append(this.LOG.sensitive(fullUrlEndpoint));
        commsLogger.d(outline1.toString());
        OkHttpClientWrapper generateWrapper = generateWrapper();
        generateWrapper.setOperationMetricNameRoot(MetricKeys.OP_UPLOAD_MEDIA);
        return generateWrapper.request(fullUrlEndpoint).addQueryParameter("include-transcript", "true").authenticatedWithoutMAP();
    }

    @Override // com.amazon.deecomms.common.audio.MediaStorageServiceAudio
    OkHttpClientWrapper generateWrapper() {
        String str = this.senderCommsId;
        return new OkHttpClientWrapper(new JacksonJSONConverter(), new EPMSAuthorizationProvider(str, str), OkHttpClientWrapper.MESSAGING_CLIENT);
    }

    @Override // com.amazon.deecomms.common.audio.MediaStorageServiceAudio
    String getFullUrlEndpoint(String str) {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), this.messagingMediaEndpoint, str);
    }

    @Override // com.amazon.deecomms.common.audio.MediaStorageServiceAudio
    public String getResourceType() {
        return OkHttpClientWrapper.MESSAGING_CLIENT;
    }
}
