package com.amazon.deecomms.media;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.audio.MediaStorageServiceAudio;
import com.amazon.deecomms.common.auth.EPMSAuthorizationProvider;
import com.amazon.deecomms.common.auth.MAPAuthorizationProvider;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.model.MediaCreateResponse;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class MediaStorageServiceClient {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MediaStorageServiceClient.class.getSimpleName());
    private static final String QUERY_PARAM_INCLUDE_TRANSCRIPT = "include-transcript";
    private static final String QUERY_PARAM_PREPEND_CHIME = "prepend-chime";
    private final String announcementMediaEndpoint;
    private String authAsCommsId;
    private final IdentityService identityService;
    private String mDeviceId;
    private String mLocale;
    private String mPreferredMarketplace;
    private String mRequestId;
    private String mUserAgent;
    private final String messagingMediaEndpoint;
    private final String soundEffectsMediaEndpoint;

    /* loaded from: classes12.dex */
    public enum MediaType {
        MESSAGE,
        ANNOUNCEMENT
    }

    public MediaStorageServiceClient(String str) {
        this(str, CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.MESSAGING_MEDIA_ENDPOINT_KEY), CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.ANNOUNCEMENT_MEDIA_ENDPOINT_KEY), CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.SOUND_EFFECTS_MEDIA_ENDPOINT_KEY), Utils.getUserAgent(), new DeviceInfo().getUniqueDeviceId(CommsDaggerWrapper.getComponent().getContext()), CommsDaggerWrapper.getComponent().getCommsInternal().getLocale(), CommsDaggerWrapper.getComponent().getIdentityService(), CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false));
    }

    private String convertMediaTypeToMessagePayloadType(String str) {
        return MediaType.ANNOUNCEMENT.name().equalsIgnoreCase(str) ? "announcement/audio" : "message/audio";
    }

    @NonNull
    private IHttpClient.Request generateAudioUploadRequest(@NonNull OkHttpClientWrapper okHttpClientWrapper, @NonNull String str) {
        IHttpClient.Request authenticatedWithoutMAP;
        if (OkHttpClientWrapper.ANNOUNCEMENT_CLIENT.equalsIgnoreCase(str)) {
            String fullUrlForAnnouncements = getFullUrlForAnnouncements(AppUrl.UPLOAD_ANNOUNCEMENT_MEDIA_RESOURCE_API);
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Announcement Upload API Suffix: ");
            outline1.append(LOG.sensitive(fullUrlForAnnouncements));
            commsLogger.d(outline1.toString());
            okHttpClientWrapper.setOperationMetricNameRoot(MetricKeys.OP_UPLOAD_ANNOUNCEMENT_MEDIA);
            authenticatedWithoutMAP = okHttpClientWrapper.request(fullUrlForAnnouncements).addQueryParameter(QUERY_PARAM_INCLUDE_TRANSCRIPT, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE).addQueryParameter(QUERY_PARAM_PREPEND_CHIME, "true").authenticatedWithoutMAP();
        } else {
            String fullUrlForMessaging = getFullUrlForMessaging(AppUrl.UPLOAD_MEDIA_RESOURCE_API);
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Audio Message Upload API Suffix: ");
            outline12.append(LOG.sensitive(fullUrlForMessaging));
            commsLogger2.d(outline12.toString());
            okHttpClientWrapper.setOperationMetricNameRoot(MetricKeys.OP_UPLOAD_MEDIA);
            authenticatedWithoutMAP = okHttpClientWrapper.request(fullUrlForMessaging).addQueryParameter(QUERY_PARAM_INCLUDE_TRANSCRIPT, "true").authenticatedWithoutMAP();
        }
        this.mRequestId = authenticatedWithoutMAP.getRequestId();
        return includeHeadersForMSS(authenticatedWithoutMAP);
    }

    private IHttpClient.AuthHeaderProvider getAuthHeaderProvider(String str, String str2) {
        if ("announcement/audio".equalsIgnoreCase(str2)) {
            MAPAuthorizationProvider mAPAuthorizationProvider = new MAPAuthorizationProvider(this.authAsCommsId, str);
            mAPAuthorizationProvider.setIdentityService(this.identityService);
            return mAPAuthorizationProvider;
        }
        return new EPMSAuthorizationProvider(this.authAsCommsId, str);
    }

    private String getClientWrapperType(String str) {
        return "announcement/audio".equalsIgnoreCase(str) ? OkHttpClientWrapper.ANNOUNCEMENT_CLIENT : OkHttpClientWrapper.MESSAGING_CLIENT;
    }

    private String getFullUrlForAnnouncements(String str) {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), this.announcementMediaEndpoint, str);
    }

    private String getFullUrlForMessaging(String str) {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), this.messagingMediaEndpoint, str);
    }

    private IHttpClient.Request includeHeadersForMSS(IHttpClient.Request request) {
        return request.addHeader("X-Amzn-RequestId", this.mRequestId).addHeader(Constants.MEDIA_SERVICE_DEVICE_ID, this.mDeviceId).addHeader("User-Agent", this.mUserAgent).addHeader("Accept-Language", this.mLocale).addHeader(Constants.ACCEPT_PFM, this.mPreferredMarketplace);
    }

    @VisibleForTesting
    OkHttpClientWrapper createOkHttpClientWrapper(IHttpClient.AuthHeaderProvider authHeaderProvider, String str) {
        if (authHeaderProvider == null) {
            return null;
        }
        return new OkHttpClientWrapper(new JacksonJSONConverter(), authHeaderProvider, str);
    }

    public void downloadAudioFile(String str, String str2, String str3, IHttpClient.Callback callback) {
        GeneratedOutlineSupport1.outline159("Downloading audio file for type: ", str3, LOG);
        IHttpClient.AuthHeaderProvider authHeaderProvider = getAuthHeaderProvider(str2, str3);
        String clientWrapperType = getClientWrapperType(str3);
        IHttpClient.Request generateAudioDownloadRequest = generateAudioDownloadRequest(createOkHttpClientWrapper(authHeaderProvider, clientWrapperType), clientWrapperType, str);
        generateAudioDownloadRequest.addMetricMetadata(MetricKeys.META_COMMS_ITEM_ID, str);
        this.mRequestId = generateAudioDownloadRequest.getRequestId();
        String obj = generateAudioDownloadRequest.toString();
        CommsLogger commsLogger = LOG;
        StringBuilder outline2 = GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline1("Media Storage Download - Request ID "), this.mRequestId, ", URI: ", obj, ", Message type: ");
        outline2.append(str3);
        outline2.append(", User agent: ");
        outline2.append(this.mUserAgent);
        commsLogger.i(outline2.toString());
        generateAudioDownloadRequest.get().enqueue(callback);
    }

    @VisibleForTesting
    IHttpClient.Request generateAudioDownloadRequest(@NonNull OkHttpClientWrapper okHttpClientWrapper, @NonNull String str, @NonNull String str2) {
        IHttpClient.Request authenticatedWithoutMAP;
        if (OkHttpClientWrapper.ANNOUNCEMENT_CLIENT.equalsIgnoreCase(str)) {
            String fullUrlForAnnouncements = getFullUrlForAnnouncements(MessageFormat.format(AppUrl.ANNOUNCEMENT_MEDIA_DOWNLOAD_RESOURCE_API, str2));
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Announcement Upload API Suffix: ");
            outline1.append(LOG.sensitive(fullUrlForAnnouncements));
            commsLogger.d(outline1.toString());
            okHttpClientWrapper.setOperationMetricNameRoot(MetricKeys.OP_DOWNLOAD_ANNOUNCEMENT_MEDIA);
            authenticatedWithoutMAP = okHttpClientWrapper.request(fullUrlForAnnouncements).authenticatedWithoutMAP();
        } else {
            String fullUrlForMessaging = getFullUrlForMessaging(MessageFormat.format(AppUrl.MEDIA_RESOURCE_API, str2));
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Audio Message Upload API Suffix: ");
            outline12.append(LOG.sensitive(fullUrlForMessaging));
            commsLogger2.d(outline12.toString());
            okHttpClientWrapper.setOperationMetricNameRoot(MetricKeys.OP_DOWNLOAD_MEDIA);
            authenticatedWithoutMAP = okHttpClientWrapper.request(fullUrlForMessaging).authenticatedWithoutMAP();
        }
        this.mRequestId = authenticatedWithoutMAP.getRequestId();
        return includeHeadersForMSS(authenticatedWithoutMAP);
    }

    public MediaCreateResponse uploadAudioFile(String str, File file, String str2, MediaType mediaType) throws IOException {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Uploading media type: ");
        outline1.append(LOG.sensitive(mediaType.name()));
        commsLogger.d(outline1.toString());
        String convertMediaTypeToMessagePayloadType = convertMediaTypeToMessagePayloadType(mediaType.name());
        EPMSAuthorizationProvider ePMSAuthorizationProvider = new EPMSAuthorizationProvider(this.authAsCommsId, str2);
        String clientWrapperType = getClientWrapperType(convertMediaTypeToMessagePayloadType);
        IHttpClient.Request generateAudioUploadRequest = generateAudioUploadRequest(createOkHttpClientWrapper(ePMSAuthorizationProvider, clientWrapperType), clientWrapperType);
        this.mRequestId = generateAudioUploadRequest.getRequestId();
        CommsLogger commsLogger2 = LOG;
        StringBuilder outline12 = GeneratedOutlineSupport.outline1("Media Storage Upload - Request ID ");
        outline12.append(this.mRequestId);
        outline12.append(", Media type");
        outline12.append(mediaType);
        outline12.append(", User agent ");
        GeneratedOutlineSupport1.outline177(outline12, this.mUserAgent, commsLogger2);
        if (file != null) {
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline13 = GeneratedOutlineSupport.outline1("File path: ");
            outline13.append(LOG.sensitive(file.getPath()));
            commsLogger3.d(outline13.toString());
        }
        generateAudioUploadRequest.addHeader("X-Amzn-RequestId", this.mRequestId).addHeader(Constants.MEDIA_SERVICE_DEVICE_ID, this.mDeviceId).addHeader("User-Agent", this.mUserAgent).addHeader("Accept-Language", this.mLocale).addHeader(Constants.ACCEPT_PFM, this.mPreferredMarketplace);
        try {
            IHttpClient.Response mo3640execute = generateAudioUploadRequest.post(str, file).mo3640execute();
            if (!mo3640execute.isSuccessful()) {
                mo3640execute.close();
                return null;
            }
            MediaCreateResponse mediaCreateResponse = (MediaCreateResponse) mo3640execute.convert(MediaCreateResponse.class);
            mo3640execute.close();
            return mediaCreateResponse;
        } catch (ServiceException e) {
            LOG.e("Exception occurred while MediaStorageService", e);
            return null;
        }
    }

    public void downloadAudioFile(String str, IHttpClient.Callback callback) {
        OkHttpClientWrapper createOkHttpClientWrapper = createOkHttpClientWrapper(getAuthHeaderProvider(this.authAsCommsId, ""), "");
        IHttpClient.Request request = createOkHttpClientWrapper.request(this.soundEffectsMediaEndpoint + str);
        this.mRequestId = request.getRequestId();
        request.get().enqueue(callback);
    }

    public MediaStorageServiceClient(String str, String str2, String str3, String str4, String str5, String str6, String str7, IdentityService identityService, String str8) {
        this.authAsCommsId = str;
        this.messagingMediaEndpoint = str2;
        this.announcementMediaEndpoint = str3;
        this.soundEffectsMediaEndpoint = str4;
        this.mUserAgent = str5;
        this.mDeviceId = str6;
        this.mLocale = str7;
        this.identityService = identityService;
        this.mPreferredMarketplace = str8;
    }

    public MediaCreateResponse uploadAudioFile(String str, MediaStorageServiceAudio mediaStorageServiceAudio) throws IOException {
        IHttpClient.Request generateAudioUploadRequest = mediaStorageServiceAudio.generateAudioUploadRequest();
        includeHeadersForMSS(generateAudioUploadRequest);
        this.mRequestId = generateAudioUploadRequest.getRequestId();
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Media Storage Upload - Request ID ");
        outline1.append(this.mRequestId);
        outline1.append(", Media type");
        outline1.append(mediaStorageServiceAudio.getResourceType());
        outline1.append(", User agent ");
        outline1.append(this.mUserAgent);
        commsLogger.i(outline1.toString());
        File mediaFile = mediaStorageServiceAudio.getMediaFile();
        if (mediaFile != null) {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("File path: ");
            outline12.append(LOG.sensitive(mediaFile.getPath()));
            commsLogger2.d(outline12.toString());
        }
        try {
            IHttpClient.Response mo3640execute = generateAudioUploadRequest.post(str, mediaFile).mo3640execute();
            if (!mo3640execute.isSuccessful()) {
                mo3640execute.close();
                return null;
            }
            MediaCreateResponse mediaCreateResponse = (MediaCreateResponse) mo3640execute.convert(MediaCreateResponse.class);
            mo3640execute.close();
            return mediaCreateResponse;
        } catch (ServiceException e) {
            LOG.e("Exception occurred while MediaStorageService", e);
            return null;
        }
    }
}
