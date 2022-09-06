package com.amazon.deecomms.common.audio;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
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
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes12.dex */
public class MediaStorageServiceAudioAnnouncement extends MediaStorageServiceAudio {
    final String QUERY_PARAM_PREPEND_SOUND_EFFECT;
    final String SOUND_EFFECTS_KEY;
    private final String announcementMediaEndpoint;
    private final String resourceType;
    private ArrayList<Object> soundEffects;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaStorageServiceAudioAnnouncement(ReadableMap readableMap) {
        super(readableMap);
        this.soundEffects = new ArrayList<>(0);
        this.resourceType = OkHttpClientWrapper.ANNOUNCEMENT_CLIENT;
        this.SOUND_EFFECTS_KEY = "soundEffects";
        this.QUERY_PARAM_PREPEND_SOUND_EFFECT = "prepend-sound-effects";
        this.announcementMediaEndpoint = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.ANNOUNCEMENT_MEDIA_ENDPOINT_KEY);
        if (!readableMap.hasKey("soundEffects") || readableMap.getArray("soundEffects") == null) {
            return;
        }
        this.soundEffects = readableMap.getArray("soundEffects").toArrayList();
    }

    void addSoundEffectsQuery(IHttpClient.Request request) {
        Iterator<Object> it2 = this.soundEffects.iterator();
        while (it2.hasNext()) {
            request.addQueryParameter("prepend-sound-effects", it2.next().toString());
        }
    }

    @Override // com.amazon.deecomms.common.audio.MediaStorageServiceAudio
    public IHttpClient.Request generateAudioUploadRequest() {
        String fullUrlEndpoint = getFullUrlEndpoint(AppUrl.UPLOAD_ANNOUNCEMENT_MEDIA_RESOURCE_API);
        CommsLogger commsLogger = this.LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Announcement Upload API Suffix: ");
        outline1.append(this.LOG.sensitive(fullUrlEndpoint));
        commsLogger.d(outline1.toString());
        OkHttpClientWrapper generateWrapper = generateWrapper();
        generateWrapper.setOperationMetricNameRoot(MetricKeys.OP_UPLOAD_ANNOUNCEMENT_MEDIA);
        IHttpClient.Request authenticatedWithoutMAP = generateWrapper.request(fullUrlEndpoint).addQueryParameter("include-transcript", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE).addQueryParameter("prepend-sound-effects", "chime").authenticatedWithoutMAP();
        if (!this.soundEffects.isEmpty()) {
            addSoundEffectsQuery(authenticatedWithoutMAP);
        }
        return authenticatedWithoutMAP;
    }

    @Override // com.amazon.deecomms.common.audio.MediaStorageServiceAudio
    OkHttpClientWrapper generateWrapper() {
        String str = this.senderCommsId;
        return new OkHttpClientWrapper(new JacksonJSONConverter(), new EPMSAuthorizationProvider(str, str), OkHttpClientWrapper.ANNOUNCEMENT_CLIENT);
    }

    @Override // com.amazon.deecomms.common.audio.MediaStorageServiceAudio
    String getFullUrlEndpoint(String str) {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), this.announcementMediaEndpoint, str);
    }

    @Override // com.amazon.deecomms.common.audio.MediaStorageServiceAudio
    public String getResourceType() {
        return OkHttpClientWrapper.ANNOUNCEMENT_CLIENT;
    }
}
