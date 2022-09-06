package com.amazon.alexa.sharing.comms;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.api.SharingClient;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingException;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingNetworkException;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingResponseParseException;
import com.amazon.alexa.sharing.api.exceptions.BridgeError;
import com.amazon.alexa.sharing.api.models.CommsIdsRecipients;
import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.alexa.sharing.api.models.NewMessage;
import com.amazon.alexa.sharing.api.models.Payload;
import com.amazon.alexa.sharing.api.models.PayloadAttachment;
import com.amazon.alexa.sharing.api.models.PayloadAttachmentGsonAdapter;
import com.amazon.alexa.sharing.api.models.PayloadAttachmentText;
import com.amazon.alexa.sharing.api.models.PayloadAttachmentUrl;
import com.amazon.alexa.sharing.comms.SharingRemoteConfig;
import com.amazon.alexa.sharing.media.MediaFileManager;
import com.amazon.alexa.sharing.media.picker.ImagePickerModule;
import com.amazon.alexa.sharing.media.transmitter.FileTransmitter;
import com.amazon.alexa.sharing.media.transmitter.MediaDownloadManager;
import com.amazon.alexa.sharing.media.transmitter.MediaUploadManager;
import com.amazon.alexa.sharing.repo.models.acms.ACMSMessage;
import com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayload;
import com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayloadGsonAdapter;
import com.amazon.alexa.sharing.sharingsdk.ContentSharingService;
import com.amazon.alexa.sharing.sharingsdk.SocialFeedRequestHandler;
import com.amazon.alexa.sharing.util.FeatureServiceUtil;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.CommsBridgeError;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commsnetworking.CommsNetworkRequest;
import com.amazon.commsnetworking.CommsNetworkingClient;
import com.amazon.commsnetworking.NetworkException;
import com.amazon.commsnetworking.api.INetworkResponse;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.amazon.commsnetworking.auth.MAPAuthenticationProvider;
import com.amazon.commsnetworking.metrics.MetricMetadata;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import rx.Observable;
import rx.schedulers.Schedulers;
/* loaded from: classes10.dex */
public class AlexaSharingClient implements SharingClient {
    private static final String CREATE_NEW_MESSAGE = "createNewMsg";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AlexaSharingClient.class);
    private String clientTokenOverride;
    private final CommsBridgeService commsBridgeService;
    private final Lazy<AlexaCommsCoreRemoteConfigurationService> commsConfigService;
    private ContentSharingService contentSharingService;
    private final Gson gson;
    MediaDownloadManager mediaDownloadManager;
    private final MediaFileManager mediaFileManager;
    MediaUploadManager mediaUploadManager;
    private final CommsMetricsEmitter metricsEmitter;
    private final INetworkingClient networkingClient;
    private final SharingRemoteConfig sharingRemoteConfig;
    private final SocialFeedRequestHandler socialFeedRequestHandler;
    private final String source;

    /* loaded from: classes10.dex */
    public static class Builder {
        String source = "AlexaApp:AlexaSharingClient";
        CommsBridgeService bridge = null;
        INetworkingClient networkingClient = null;
        Gson gson = null;
        Lazy<AlexaCommsCoreMetricsService> commsMetricsService = null;
        Lazy<AlexaCommsCoreRemoteConfigurationService> commsConfigService = null;
        Lazy<AlexaCommsCoreIdentityService> commsIdentityService = null;
        NetworkClientConfig clientConfig = null;
        MediaFileManager mediaFileManager = null;
        ContentSharingService contentSharingService = null;
        MediaDownloadManager mediaDownloadManager = null;
        MediaUploadManager mediaUploadManager = null;
        SocialFeedRequestHandler socialFeedRequestHandler = null;

        private INetworkingClient useDefaultNetworking(NetworkClientConfig networkClientConfig) {
            return new CommsNetworkingClient.Builder().withSource(networkClientConfig.getSource()).withHost(networkClientConfig.getHost()).withAuthProvider(new MAPAuthenticationProvider(Constants.ALEXA_SHARING_CLIENT_SOURCE, this.commsIdentityService.mo358get(), this.commsConfigService.mo358get(), this.commsMetricsService.mo358get())).withMetricService(this.commsMetricsService.mo358get()).withReadTimeout(networkClientConfig.getTimeout()).withWriteTimeout(networkClientConfig.getTimeout()).build();
        }

        @NonNull
        public AlexaSharingClient build() {
            if (this.bridge != null) {
                if (this.gson != null) {
                    if (this.source != null) {
                        if (this.mediaFileManager != null) {
                            if (this.networkingClient == null) {
                                NetworkClientConfig networkClientConfig = this.clientConfig;
                                if (networkClientConfig != null) {
                                    if (this.commsIdentityService != null) {
                                        if (this.commsConfigService != null) {
                                            if (this.commsMetricsService != null) {
                                                this.networkingClient = useDefaultNetworking(networkClientConfig);
                                            } else {
                                                throw new IllegalArgumentException("`commsMetricsService` is required to build the default networking client.");
                                            }
                                        } else {
                                            throw new IllegalArgumentException("`commsConfigService` is required to build the default networking client.");
                                        }
                                    } else {
                                        throw new IllegalArgumentException("`commsIdentityService` is required to build the default networking client.");
                                    }
                                } else {
                                    throw new IllegalArgumentException("`clientConfig` is required to build the default networking client");
                                }
                            }
                            if (this.contentSharingService != null) {
                                if (this.mediaDownloadManager != null) {
                                    if (this.mediaUploadManager != null) {
                                        if (this.socialFeedRequestHandler != null) {
                                            return new AlexaSharingClient(this);
                                        }
                                        throw new IllegalArgumentException("`socialFeedRequestHandler` is required to build.");
                                    }
                                    throw new IllegalArgumentException("`mediaUploadManager` is required to build.");
                                }
                                throw new IllegalArgumentException("`mediaDownloadManager` is required to build.");
                            }
                            throw new IllegalArgumentException("`contentSharingService` is required to build.");
                        }
                        throw new IllegalArgumentException("`mediaFileManager` is required to build.");
                    }
                    throw new IllegalArgumentException("`source` is required to build.");
                }
                throw new IllegalArgumentException("`gson` is required to build. Use `withDefaultGson()`.");
            }
            throw new IllegalArgumentException("`CommsBridgeService` is required to build.");
        }

        @NonNull
        public Builder overrideNetworkingClient(@NonNull INetworkingClient iNetworkingClient) {
            this.networkingClient = iNetworkingClient;
            return this;
        }

        @NonNull
        public Builder setBridgeService(@NonNull CommsBridgeService commsBridgeService) {
            this.bridge = commsBridgeService;
            return this;
        }

        @NonNull
        public Builder setCommsConfigService(@NonNull Lazy<AlexaCommsCoreRemoteConfigurationService> lazy) {
            this.commsConfigService = lazy;
            return this;
        }

        @NonNull
        public Builder setCommsIdentityService(@NonNull Lazy<AlexaCommsCoreIdentityService> lazy) {
            this.commsIdentityService = lazy;
            return this;
        }

        @NonNull
        public Builder setCommsMetrics(@NonNull Lazy<AlexaCommsCoreMetricsService> lazy) {
            this.commsMetricsService = lazy;
            return this;
        }

        @NonNull
        Builder setContentSharingService(@NonNull ContentSharingService contentSharingService) {
            this.contentSharingService = contentSharingService;
            return this;
        }

        @NonNull
        public Builder setGson(@NonNull Gson gson) {
            this.gson = gson;
            return this;
        }

        @NonNull
        Builder setMediaDownloadManager(@NonNull MediaDownloadManager mediaDownloadManager) {
            this.mediaDownloadManager = mediaDownloadManager;
            return this;
        }

        @NonNull
        Builder setMediaFileManager(@NonNull MediaFileManager mediaFileManager) {
            this.mediaFileManager = mediaFileManager;
            return this;
        }

        @NonNull
        Builder setMediaUploadManager(@NonNull MediaUploadManager mediaUploadManager) {
            this.mediaUploadManager = mediaUploadManager;
            return this;
        }

        @NonNull
        Builder setSocialFeedRequestHandler(@NonNull SocialFeedRequestHandler socialFeedRequestHandler) {
            this.socialFeedRequestHandler = socialFeedRequestHandler;
            return this;
        }

        public Builder withContentSharingService(Lazy<AlexaCommsCoreMetricsService> lazy, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, FeatureServiceV2 featureServiceV2, Context context) {
            this.contentSharingService = new ContentSharingService(new CommsMetricsEmitter(lazy, ContentSharingService.class.getSimpleName()), alexaCommsCoreIdentityService, new FeatureServiceUtil(featureServiceV2), context);
            return this;
        }

        public Builder withDefaultGson() {
            this.gson = new GsonBuilder().registerTypeAdapter(MessagePayload.class, new MessagePayloadGsonAdapter()).registerTypeAdapter(PayloadAttachment.class, new PayloadAttachmentGsonAdapter()).create();
            return this;
        }

        public Builder withMediaDownloadManager(FileTransmitter fileTransmitter, CommsBridgeService commsBridgeService, Lazy<AlexaCommsCoreMetricsService> lazy) {
            this.mediaDownloadManager = new MediaDownloadManager(fileTransmitter, commsBridgeService, new CommsMetricsEmitter(lazy, MediaDownloadManager.class.getSimpleName()));
            return this;
        }

        public Builder withMediaFileManager() {
            this.mediaFileManager = new MediaFileManager();
            return this;
        }

        public Builder withMediaUploadManager(FileTransmitter fileTransmitter, Lazy<AlexaCommsCoreMetricsService> lazy) {
            this.mediaUploadManager = new MediaUploadManager(fileTransmitter, new CommsMetricsEmitter(lazy, MediaUploadManager.class.getSimpleName()));
            return this;
        }

        public Builder withNetworkClientConfig(String str, Lazy<AlexaCommsCoreRemoteConfigurationService> lazy) {
            String stringValue = SharingRemoteConfig.getInstance(lazy).getStringValue(SharingRemoteConfig.RemoteKey.ALEXA_SERVICE_HOST);
            CommsLogger commsLogger = AlexaSharingClient.LOG;
            commsLogger.d("[Sharing-config] Remote value retrieved for host:" + stringValue);
            String stringValue2 = SharingRemoteConfig.getInstance(lazy).getStringValue(SharingRemoteConfig.RemoteKey.ACMS_SEND_MSG_TIMEOUT);
            CommsLogger commsLogger2 = AlexaSharingClient.LOG;
            commsLogger2.d("[Sharing-config] Remote value retrieved for timeout: " + stringValue2);
            this.clientConfig = new NetworkClientConfig(str, stringValue, Integer.parseInt(stringValue2));
            return this;
        }

        public Builder withSocialFeedRequestHandler(FileTransmitter fileTransmitter, CommsBridgeService commsBridgeService, Lazy<AlexaCommsCoreMetricsService> lazy, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, Context context) {
            CommsMetricsEmitter commsMetricsEmitter = new CommsMetricsEmitter(lazy, SocialFeedRequestHandler.class.getSimpleName());
            this.socialFeedRequestHandler = new SocialFeedRequestHandler(commsMetricsEmitter, context, new MediaDownloadManager(fileTransmitter, commsBridgeService, commsMetricsEmitter), alexaCommsCoreIdentityService);
            return this;
        }

        public Builder withSource(String str) {
            this.source = str;
            return this;
        }
    }

    public AlexaSharingClient() {
        this(null, null, null, null, null, null, null, null);
        this.contentSharingService = null;
    }

    @Nullable
    private INetworkResponse executePostNewMessageRequest(Gson gson, NewMessage newMessage) throws AlexaSharingException {
        String stringValue = this.sharingRemoteConfig.getStringValue(SharingRemoteConfig.RemoteKey.ALEXA_SERVICE_HOST);
        CommsLogger commsLogger = LOG;
        commsLogger.d("[Sharing-config] Remote value retrieved for host:" + stringValue);
        CommsNetworkRequest build = new CommsNetworkRequest.Builder().withMethod(CommsNetworkRequest.Method.POST).withUrl(stringValue, Constants.CREATE_NEW_MESSAGE_PATH).withCommsNetworkingRequestTag(new MetricMetadata(this.source, String.format(MetricKeys.ALEXA_SHARING_NETWORK_METRIC_FORMAT, CREATE_NEW_MESSAGE), false)).build();
        String json = gson.toJson(newMessage);
        build.withPayload("application/json", json);
        try {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.d("[okhttp-sendMsg-1] Sending: " + json);
            INetworkResponse execute = this.networkingClient.execute(build);
            if (execute != null) {
                return execute;
            }
            throw new AlexaSharingException(BridgeError.CreateNewMessageAPINullResponse);
        } catch (NetworkException e) {
            e.printStackTrace();
            INetworkResponse response = e.getResponse();
            if (response != null) {
                String body = response.getBody();
                Integer valueOf = Integer.valueOf(response.hashCode());
                String obj = response.getHeaders() != null ? response.getHeaders().toString() : "nil-headers";
                String outline69 = GeneratedOutlineSupport1.outline69("[", valueOf, "]");
                LOG.e("[response-error]", e);
                CommsLogger commsLogger3 = LOG;
                commsLogger3.d("[response-error]" + outline69 + " Network Request has thrown error.");
                CommsLogger commsLogger4 = LOG;
                commsLogger4.d("[response-error]" + outline69 + " Response Body: " + body);
                CommsLogger commsLogger5 = LOG;
                commsLogger5.d("[response-error]" + outline69 + " Response headers: " + obj);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sharing SDK Error: Network request failed with status ");
                outline107.append(e.getStatusCode());
                outline107.append(RealTimeTextConstants.COLON_SPACE);
                outline107.append(body);
                throw new AlexaSharingNetworkException(outline107.toString(), BridgeError.CreateNewMessageAPIFailure.code(), CREATE_NEW_MESSAGE, response);
            }
            throw new AlexaSharingException(BridgeError.CreateNewMessageAPIFailure);
        }
    }

    @NonNull
    private String getClientToken() {
        String str = this.clientTokenOverride;
        return str != null ? str : UUID.randomUUID().toString();
    }

    private Message getNewMessageFromResponse(Gson gson, INetworkResponse iNetworkResponse) throws AlexaSharingException {
        String body = iNetworkResponse.getBody();
        String amazonRequestID = iNetworkResponse.getAmazonRequestID();
        try {
            JsonObject jsonObject = (JsonObject) gson.fromJson(body, (Class<Object>) JsonObject.class);
            if (jsonObject != null) {
                Message message = (Message) gson.fromJson(jsonObject.get("message"), (Class<Object>) Message.class);
                if (message != null) {
                    if (message.getType() == null) {
                        throw new AlexaSharingResponseParseException(BridgeError.InvalidMessageTypeInACMSResponse, CREATE_NEW_MESSAGE, amazonRequestID);
                    }
                    return message;
                }
                throw new AlexaSharingResponseParseException(BridgeError.InvalidMessagePayloadInACMSResponse, CREATE_NEW_MESSAGE, amazonRequestID);
            }
            throw new AlexaSharingResponseParseException(BridgeError.NullACMSResponsePayload, CREATE_NEW_MESSAGE, amazonRequestID);
        } catch (JsonSyntaxException e) {
            LOG.e("[response-error]", e);
            CommsLogger commsLogger = LOG;
            commsLogger.i("[response-error] A Network Request has returned non-JSON data.\n---\n" + body + "\n---\n");
            throw new AlexaSharingResponseParseException(BridgeError.InvalidACMSResponsePayload, CREATE_NEW_MESSAGE, amazonRequestID);
        }
    }

    @Nullable
    private String parseJsonForClientMessageId(JsonObject jsonObject) {
        JsonObject asJsonObject = jsonObject.getAsJsonObject("pendingMessage");
        if (!asJsonObject.has("clientMessageId")) {
            return null;
        }
        return asJsonObject.get("clientMessageId").getAsString();
    }

    private ArrayList<String> parseJsonForRecipients(JsonObject jsonObject) {
        ArrayList<String> arrayList = new ArrayList<>();
        JsonArray asJsonArray = jsonObject.getAsJsonArray("recipients");
        for (int i = 0; i < asJsonArray.size(); i++) {
            arrayList.add(asJsonArray.get(i).getAsString());
        }
        return arrayList;
    }

    private String parsePendingMessageForText(JsonObject jsonObject) {
        return jsonObject.getAsJsonObject("payload").get("text").getAsString();
    }

    private void recordInitializationFailure(CommsBridgeError commsBridgeError) {
        this.metricsEmitter.recordAPIClientFailMetric(MetricKeys.ALEXA_SHARING_SDK_INITIALIZED, commsBridgeError.getMessage());
    }

    private void recordInitializationSuccess() {
        this.metricsEmitter.recordOccurrenceMetric("alexa.sharing.setup.init.ok", Collections.EMPTY_MAP);
    }

    @VisibleForTesting
    Payload.Builder createMultiPartAttachments(Payload.Builder builder, String str) {
        Matcher matcher = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", 42).matcher(str);
        String outline72 = GeneratedOutlineSupport1.outline72("", str);
        int i = 0;
        while (matcher.find()) {
            String trim = matcher.group().trim();
            outline72 = outline72.replace(trim, "").trim();
            builder.addAttachment(new PayloadAttachmentUrl(trim, "", outline72));
            i++;
        }
        if (i == 1 && outline72.trim().length() == 0) {
            return builder;
        }
        builder.addAttachment(new PayloadAttachmentText(outline72));
        return builder;
    }

    ImagePickerModule getImagePickerModule(ReactApplicationContext reactApplicationContext) {
        return new ImagePickerModule(reactApplicationContext);
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public Intent handleShareIntent(Intent intent) {
        return this.contentSharingService.handleAndroidShareIntent(intent);
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public void initialize() {
    }

    public /* synthetic */ void lambda$prepareBridgeListeners$4$AlexaSharingClient(String str, ResponseResolver responseResolver) {
        this.contentSharingService.onReceiveSharingParseEvent(str, responseResolver);
    }

    public /* synthetic */ void lambda$prepareBridgeListeners$5$AlexaSharingClient(String str, ResponseResolver responseResolver) {
        this.contentSharingService.onSendSharingParseEvent(str, responseResolver);
    }

    public /* synthetic */ void lambda$prepareBridgeListeners$6$AlexaSharingClient(String str, ResponseResolver responseResolver) {
        this.socialFeedRequestHandler.onReceiveSocialFeedParseEvent(str, responseResolver);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mediaDownloadRequestHandler */
    public void lambda$prepareBridgeListeners$2$AlexaSharingClient(String str, ResponseResolver responseResolver) {
        try {
            this.mediaDownloadManager.downloadFilesFromCDS((JsonArray) this.gson.fromJson(str, (Class<Object>) JsonArray.class), responseResolver);
        } catch (Exception e) {
            String outline41 = GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Failed to process mediaDownloadRequest: "));
            LOG.e(outline41, e);
            responseResolver.reject(new AlexaSharingException(outline41, e.getClass().getSimpleName()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: mediaFileRemovalRequestHandler */
    public void lambda$prepareBridgeListeners$3$AlexaSharingClient(String str, ResponseResolver responseResolver) {
        String[] strArr;
        String[] strArr2 = new String[0];
        try {
            strArr = (String[]) this.gson.fromJson(str, (Class<Object>) String[].class);
        } catch (Exception e) {
            LOG.e(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Failed to process mediaFileRemovalRequest: ")), e);
            responseResolver.reject(new AlexaSharingException(BridgeError.InvalidMediaFileRemovalRequestPayload));
            strArr = strArr2;
        }
        this.mediaFileManager.removeFilesFromDisk(strArr, responseResolver);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mediaUploadRequestHandler */
    public void lambda$prepareBridgeListeners$1$AlexaSharingClient(String str, ResponseResolver responseResolver) {
        try {
            this.mediaUploadManager.uploadFilesToCDS((JsonArray) this.gson.fromJson(str, (Class<Object>) JsonArray.class), responseResolver);
        } catch (Exception e) {
            String outline41 = GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Failed to process mediaUploadRequest: "));
            LOG.e(outline41, e);
            responseResolver.reject(new AlexaSharingException(outline41, e.getClass().getSimpleName()));
        }
    }

    @VisibleForTesting
    ACMSMessage onSendNewMessageRequest(String str, String str2) throws AlexaSharingException {
        LOG.i("[comms-bridge-sendNewMsg] onSendNewMessageRequest received from Comms bridge");
        JsonObject jsonObject = (JsonObject) this.gson.fromJson(str, (Class<Object>) JsonObject.class);
        String parseJsonForClientMessageId = parseJsonForClientMessageId(jsonObject);
        CommsLogger commsLogger = LOG;
        commsLogger.i("[comms-bridge-sendNewMsg] onSendNewMessageRequest received from Comms bridge. ID: " + str2 + " for " + parseJsonForClientMessageId);
        JsonObject asJsonObject = jsonObject.getAsJsonObject("pendingMessage");
        String parsePendingMessageForText = parsePendingMessageForText(asJsonObject);
        String asString = asJsonObject.get("type").getAsString();
        ArrayList<String> parseJsonForRecipients = parseJsonForRecipients(jsonObject);
        setClientToken(parseJsonForClientMessageId);
        CommsLogger commsLogger2 = LOG;
        commsLogger2.i("[comms-bridge-sendNewMsg] onSendNewMessageRequest content parsed for " + parseJsonForClientMessageId);
        Payload.Builder builder = new Payload.Builder();
        builder.setTextFallback(parsePendingMessageForText);
        ACMSMessage sendNewMessage = sendNewMessage(parseJsonForRecipients, builder.build());
        sendNewMessage.setType(asString);
        sendNewMessage.setPayload(asJsonObject.getAsJsonObject("payload"));
        CommsLogger commsLogger3 = LOG;
        commsLogger3.i("[comms-bridge-sendNewMsg] onSendNewMessageRequest completed for " + parseJsonForClientMessageId);
        return sendNewMessage;
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public void openCamera(ReactApplicationContext reactApplicationContext, ReadableMap readableMap, Promise promise) {
        LOG.i("Handling OpenCamera request from DCAL");
        getImagePickerModule(reactApplicationContext).openCamera(readableMap, promise);
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public void openPicker(ReactApplicationContext reactApplicationContext, ReadableMap readableMap, Promise promise) {
        LOG.i("Handling OpenPicker request from DCAL");
        getImagePickerModule(reactApplicationContext).openPicker(readableMap, promise);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean prepareBridgeListeners() {
        try {
            this.commsBridgeService.registerRequestHandler(Constants.ALEXA_SHARING_KEY_SENDMSG, new RequestHandler() { // from class: com.amazon.alexa.sharing.comms.-$$Lambda$AlexaSharingClient$lSVdlSGXxi_1-YZXEo62jFbKNzk
                @Override // com.amazon.commscore.api.commsbridge.RequestHandler
                public final void handleRequest(Object obj, ResponseResolver responseResolver) {
                    AlexaSharingClient.this.lambda$prepareBridgeListeners$0$AlexaSharingClient((String) obj, responseResolver);
                }
            });
            this.commsBridgeService.registerRequestHandler(Constants.ALEXA_SHARING_KEY_UPLOAD_MEDIA, new RequestHandler() { // from class: com.amazon.alexa.sharing.comms.-$$Lambda$AlexaSharingClient$45deee02kjzaVJ9jvBjs98Sz-Gg
                @Override // com.amazon.commscore.api.commsbridge.RequestHandler
                public final void handleRequest(Object obj, ResponseResolver responseResolver) {
                    AlexaSharingClient.this.lambda$prepareBridgeListeners$1$AlexaSharingClient((String) obj, responseResolver);
                }
            });
            this.commsBridgeService.registerRequestHandler(Constants.ALEXA_SHARING_KEY_DOWNLOAD_MEDIA, new RequestHandler() { // from class: com.amazon.alexa.sharing.comms.-$$Lambda$AlexaSharingClient$8eb6S06Os6IlzMmxUgDcTtNPaOc
                @Override // com.amazon.commscore.api.commsbridge.RequestHandler
                public final void handleRequest(Object obj, ResponseResolver responseResolver) {
                    AlexaSharingClient.this.lambda$prepareBridgeListeners$2$AlexaSharingClient((String) obj, responseResolver);
                }
            });
            this.commsBridgeService.registerRequestHandler(Constants.ALEXA_SHARING_KEY_REMOVE_MEDIA_FILES, new RequestHandler() { // from class: com.amazon.alexa.sharing.comms.-$$Lambda$AlexaSharingClient$QoAlci-wtdFn2yqn4DGpEu6fRXs
                @Override // com.amazon.commscore.api.commsbridge.RequestHandler
                public final void handleRequest(Object obj, ResponseResolver responseResolver) {
                    AlexaSharingClient.this.lambda$prepareBridgeListeners$3$AlexaSharingClient((String) obj, responseResolver);
                }
            });
            this.commsBridgeService.registerRequestHandler(Constants.ALEXA_SHARING_KEY_PARSE_RCV_PAYLOAD, new RequestHandler() { // from class: com.amazon.alexa.sharing.comms.-$$Lambda$AlexaSharingClient$kcoMXFMqMhQ1unCuB4ia5JWBAhw
                @Override // com.amazon.commscore.api.commsbridge.RequestHandler
                public final void handleRequest(Object obj, ResponseResolver responseResolver) {
                    AlexaSharingClient.this.lambda$prepareBridgeListeners$4$AlexaSharingClient((String) obj, responseResolver);
                }
            });
            this.commsBridgeService.registerRequestHandler(Constants.ALEXA_SHARING_KEY_PARSE_SEND_PAYLOAD, new RequestHandler() { // from class: com.amazon.alexa.sharing.comms.-$$Lambda$AlexaSharingClient$obF-BI2cjVzVGgGUYRmZFF0NQuU
                @Override // com.amazon.commscore.api.commsbridge.RequestHandler
                public final void handleRequest(Object obj, ResponseResolver responseResolver) {
                    AlexaSharingClient.this.lambda$prepareBridgeListeners$5$AlexaSharingClient((String) obj, responseResolver);
                }
            });
            this.commsBridgeService.registerRequestHandler(Constants.ALEXA_SHARING_KEY_PARSE_SOCIAL_FEED_RECEIVE_PAYLOAD, new RequestHandler() { // from class: com.amazon.alexa.sharing.comms.-$$Lambda$AlexaSharingClient$2-ykHlWgRPy7qKjQCGtSFh2RNgY
                @Override // com.amazon.commscore.api.commsbridge.RequestHandler
                public final void handleRequest(Object obj, ResponseResolver responseResolver) {
                    AlexaSharingClient.this.lambda$prepareBridgeListeners$6$AlexaSharingClient((String) obj, responseResolver);
                }
            });
            LOG.i("Started CommsBridge request handlers.");
            recordInitializationSuccess();
            return true;
        } catch (CommsBridgeError e) {
            LOG.e("FATAL: Unable to register listeners due to a Bridge Error.Communication with the Sharing API will fail.", e);
            recordInitializationFailure(e);
            return false;
        }
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    @NonNull
    public ACMSMessage sendNewMessage(@NonNull List<String> list, @NonNull Payload payload) throws AlexaSharingException {
        String clientToken = getClientToken();
        CommsLogger commsLogger = LOG;
        commsLogger.i("[comms-bridge-sendNewMsg] sendNewMessage entered for " + clientToken);
        NewMessage newMessage = new NewMessage();
        newMessage.setClientToken(clientToken);
        newMessage.setRecipients(new CommsIdsRecipients(list));
        newMessage.setMessagePayload(payload);
        CommsLogger commsLogger2 = LOG;
        commsLogger2.i("[comms-bridge-sendNewMsg] Request ready to send to /new-message for " + clientToken);
        INetworkResponse executePostNewMessageRequest = executePostNewMessageRequest(this.gson, newMessage);
        CommsLogger commsLogger3 = LOG;
        commsLogger3.i("[comms-bridge-sendNewMsg] Response received from /new-message for " + clientToken);
        Message newMessageFromResponse = getNewMessageFromResponse(this.gson, executePostNewMessageRequest);
        CommsLogger commsLogger4 = LOG;
        commsLogger4.i("[comms-bridge-sendNewMsg] Message retrieved from response for " + clientToken);
        ACMSMessage fromNewMessageResponse = ACMSMessage.fromNewMessageResponse(newMessageFromResponse, clientToken);
        CommsLogger commsLogger5 = LOG;
        commsLogger5.i("[comms-bridge-sendNewMsg] ACMSMessage constructed from NewMessage Response for " + clientToken);
        return fromNewMessageResponse;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: sendNewMessageRequestHandler */
    public void lambda$prepareBridgeListeners$0$AlexaSharingClient(String str, ResponseResolver responseResolver) {
        String format = String.format(MetricKeys.ALEXA_SHARING_API_METRIC_FORMAT, CREATE_NEW_MESSAGE);
        try {
            ACMSMessage onSendNewMessageRequest = onSendNewMessageRequest(str, responseResolver.getRequestId());
            CommsMetricsEmitter commsMetricsEmitter = this.metricsEmitter;
            commsMetricsEmitter.recordOccurrenceMetric(format + ".success", Collections.EMPTY_MAP);
            CommsMetricsEmitter commsMetricsEmitter2 = this.metricsEmitter;
            commsMetricsEmitter2.recordNonOccurrenceMetric(format + ".error", Collections.EMPTY_MAP);
            responseResolver.resolve(onSendNewMessageRequest);
        } catch (AlexaSharingException e) {
            this.metricsEmitter.recordOccurrenceMetric(GeneratedOutlineSupport1.outline72(format, ".error"), Collections.EMPTY_MAP);
            responseResolver.reject(e);
        }
    }

    @VisibleForTesting
    void setClientToken(String str) {
        this.clientTokenOverride = str;
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public void startListening() {
        LOG.i("Attempt to start CommsBridge request handlers in a background thread");
        Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.sharing.comms.-$$Lambda$dqy3ZF2VCh4OXuqmdprvfcUFHBw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Boolean.valueOf(AlexaSharingClient.this.prepareBridgeListeners());
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    AlexaSharingClient(@NonNull Builder builder) {
        this(builder.bridge, builder.networkingClient, builder.gson, builder.commsMetricsService, builder.commsConfigService, builder.source, builder.mediaFileManager, builder.socialFeedRequestHandler);
        this.contentSharingService = builder.contentSharingService;
        this.mediaDownloadManager = builder.mediaDownloadManager;
        this.mediaUploadManager = builder.mediaUploadManager;
    }

    private AlexaSharingClient(CommsBridgeService commsBridgeService, INetworkingClient iNetworkingClient, Gson gson, Lazy<AlexaCommsCoreMetricsService> lazy, Lazy<AlexaCommsCoreRemoteConfigurationService> lazy2, String str, MediaFileManager mediaFileManager, SocialFeedRequestHandler socialFeedRequestHandler) {
        this.commsBridgeService = commsBridgeService;
        this.commsConfigService = lazy2;
        this.networkingClient = iNetworkingClient;
        this.metricsEmitter = new CommsMetricsEmitter(lazy, str);
        this.gson = gson;
        this.clientTokenOverride = null;
        this.source = str;
        this.mediaFileManager = mediaFileManager;
        this.socialFeedRequestHandler = socialFeedRequestHandler;
        this.sharingRemoteConfig = SharingRemoteConfig.getInstance(lazy2);
    }
}
