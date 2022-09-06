package com.amazon.deecomms.media.photos;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.Endpoints;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.ConflictError;
import com.amazon.clouddrive.exceptions.InvalidParameter;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.clouddrive.extended.model.DownloadFileExtendedRequest;
import com.amazon.clouddrive.extended.model.GetNodeExtendedRequest;
import com.amazon.clouddrive.extended.model.GetNodeExtendedResponse;
import com.amazon.clouddrive.extended.model.GetThumbnailExtendedRequest;
import com.amazon.clouddrive.extended.model.SetupAccountRequest;
import com.amazon.clouddrive.extended.model.UploadFileExtendedRequest;
import com.amazon.clouddrive.extended.model.UploadFileExtendedResponse;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.model.ContentProperties;
import com.amazon.clouddrive.model.CreateNodeRequest;
import com.amazon.clouddrive.model.GetAccountEndpointRequest;
import com.amazon.clouddrive.model.GetAccountEndpointResponse;
import com.amazon.clouddrive.model.GetNodeRequest;
import com.amazon.clouddrive.model.ImageProperties;
import com.amazon.clouddrive.model.ListChildrenRequest;
import com.amazon.clouddrive.model.ListChildrenResponse;
import com.amazon.clouddrive.model.ListNodesRequest;
import com.amazon.clouddrive.model.Node;
import com.amazon.clouddrive.model.Suppress;
import com.amazon.clouddrive.model.VideoProperties;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.media.photos.CloudDriveService;
import com.amazon.deecomms.sharing.exceptions.InvalidCommsUserSDKException;
import com.amazon.deecomms.sharing.exceptions.SharingSDKException;
import com.amazon.deecomms.sharing.photos.CommonContentProperties;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import rx.Completable;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class FileTransmitter implements CloudDriveService {
    private static final String CREATE_ALEXA_FOLDER_METRICS_SUFFIX = "CreateAlexa";
    private static final String DEFAULT_IMAGE_EXTENSION = ".jpg";
    private static final String GET_ALEXA_FOLDER_METRICS_SUFFIX = "GetAlexa";
    private static final String GET_ROOT_NODE_ID_METRICS_SUFFIX = "RootID";
    private static final String INTERRUPT_CODE = "interrupt";
    private static final int INTERRUPT_STATUS_CODE = -9;
    private static final String SETUP_ACCOUNT_METRICS_SUFFIX = "Account";
    private static final String SOURCE = "FileTransmitter";
    private String directedId;
    private AccountConfiguration mAccountConfiguration;
    private String mAlexaFolderName;
    private String mAlexaFolderNodeId;
    private AmazonCloudDriveExtendedClient mCDSClient;
    private final ClientConfiguration mClientConfiguration;
    private final CommsIdentityManager mCommsIdentityManager;
    private final MAPAuthenticatedURLConnectionFactory mConnectionFactory;
    private final Context mContext;
    private UserIdentity mCurrentUser;
    private final EndpointsCache mEndpointsCache;
    private final EventBus mEventBus;
    private final ExecutorService mExecutor;
    private final IdentityService mIdentityService;
    private String mRootNodeId;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, FileTransmitter.class);
    private static final String className = FileTransmitter.class.getName();

    public FileTransmitter(@NonNull Context context, @NonNull ClientConfiguration clientConfiguration, @NonNull MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, @NonNull EndpointsCache endpointsCache, @NonNull IdentityService identityService, @NonNull CommsIdentityManager commsIdentityManager, @NonNull AccountConfiguration accountConfiguration, @NonNull ExecutorService executorService, @NonNull EventBus eventBus) {
        this(context, clientConfiguration, mAPAuthenticatedURLConnectionFactory, endpointsCache, identityService, commsIdentityManager, accountConfiguration, executorService, new AmazonCloudDriveExtendedClient(accountConfiguration, clientConfiguration, executorService), eventBus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean completeAccountSetupTasks() throws CloudDriveException {
        if (!Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, "CDS_ACCOUNT_SETUP_SUCCESSFUL", false)) {
            LOG.d("[Sharing] AlexaFolderNodeId is missing. Setup started.");
            if (!setupAccount()) {
                throw new CloudDriveException("[Sharing] CDS Account Setup failure, setupAccount() has failed.");
            }
        }
        if (TextUtils.isEmpty(this.mRootNodeId)) {
            LOG.d("[Sharing] mRootNodeId is missing. Setup started.");
            if (!retrieveRootNodeId()) {
                throw new CloudDriveException("[Sharing] CDS Account Setup failure, Could not retrieve root node id.");
            }
        }
        if (TextUtils.isEmpty(this.mAlexaFolderNodeId)) {
            LOG.d("[Sharing] mAlexaFolderNodeId is missing. Setup started.");
            try {
                if (!retrieveAlexaFolderNodeId() && !createAlexaFolder()) {
                    throw new CloudDriveException("[Sharing] CDS Account Setup failure, Alexa folder was not found and unable to create.");
                }
            } catch (CloudDriveException e) {
                e = e;
                throw new CloudDriveException("[Sharing] CDS Account Setup failure, Alexa folder was not found and unable to create.", e);
            } catch (InterruptedException e2) {
                e = e2;
                throw new CloudDriveException("[Sharing] CDS Account Setup failure, Alexa folder was not found and unable to create.", e);
            }
        }
        MetricsHelper.recordCounterMetricOperational("comms.cds.setup.error", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        LOG.d("[Sharing] Account initialized, AlexaFolderNodeId and RootNodeId set. Setup Completed.");
        return true;
    }

    private boolean createAlexaFolder() {
        LOG.d("[Sharing] creating alexa folder using name:Alexa Shared Photos");
        CreateNodeRequest createNodeRequest = new CreateNodeRequest("Alexa Shared Photos", "FOLDER");
        createNodeRequest.withParents(Collections.singletonList(this.mRootNodeId));
        try {
            setAlexaNodeId(this.mCDSClient.createNode(createNodeRequest).getId());
            setAlexaFolderName("Alexa Shared Photos");
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("[Sharing] Alexa folder created, using name: ");
            sb.append(this.mAlexaFolderName);
            commsLogger.d(sb.toString());
            return true;
        } catch (CloudDriveException e) {
            LOG.e("[Sharing] The CDS client threw error during createAlexaFolder!", e);
            int httpCode = e.getHttpCode();
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("CreateAlexa.");
            outline1.append(e.getCode());
            recordCDSFailureMetrics("comms.cds.setup.error", e, httpCode, outline1.toString(), e.getLogref());
            return false;
        } catch (InterruptedException e2) {
            LOG.e("[Sharing] An interrupt event was encountered during createAlexaFolder!", e2);
            recordCDSFailureMetrics("comms.cds.setup.error", e2, -9, "CreateAlexa.interrupt", INTERRUPT_CODE);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File createNewFileForName(String str) throws IOException {
        File file = new File(this.mContext.getFilesDir().getPath() + "/" + str);
        file.createNewFile();
        return file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProgressListener createProgressListenerToListenOnDownloadCompletion(final String str, final GetNodeExtendedResponse getNodeExtendedResponse, final CloudDriveFileDownloadListener cloudDriveFileDownloadListener) {
        return new ProgressListener() { // from class: com.amazon.deecomms.media.photos.-$$Lambda$FileTransmitter$DU63u5itS_7AaGbku8Dg_qGxEJI
            @Override // com.amazon.clouddrive.handlers.ProgressListener
            public final void onProgress(long j, long j2) {
                FileTransmitter.this.lambda$createProgressListenerToListenOnDownloadCompletion$7$FileTransmitter(str, getNodeExtendedResponse, cloudDriveFileDownloadListener, j, j2);
            }
        };
    }

    private Pair<Integer, Integer> getHeightAndWidthForContent(@Nullable ContentProperties contentProperties) throws IllegalArgumentException {
        String mediaTypeForContent = getMediaTypeForContent(contentProperties);
        if ("IMAGE".equalsIgnoreCase(mediaTypeForContent)) {
            ImageProperties image = contentProperties.getImage();
            if (image != null) {
                return new Pair<>(image.getHeight(), image.getWidth());
            }
            throw new IllegalArgumentException("ImageProperties are null");
        } else if ("VIDEO".equalsIgnoreCase(mediaTypeForContent)) {
            VideoProperties video = contentProperties.getVideo();
            if (video != null) {
                return new Pair<>(video.getHeight(), video.getWidth());
            }
            throw new IllegalArgumentException("VideoProperties are null");
        } else {
            throw new IllegalArgumentException("The contentProperties provided are neither image nor video.");
        }
    }

    private String getMediaTypeForContent(@Nullable ContentProperties contentProperties) throws IllegalArgumentException {
        if (contentProperties != null) {
            String contentType = contentProperties.getContentType();
            if (contentType != null) {
                return CommonContentProperties.getMediaTypeFromMimeType(contentType);
            }
            throw new IllegalArgumentException("contentType is null");
        }
        throw new IllegalArgumentException("contentProperties is null");
    }

    private void getTempLinkForNodeID(@NonNull String str, @NonNull String str2, @NonNull CloudDriveFileUploadListener cloudDriveFileUploadListener) {
        GetNodeRequest getNodeRequest = new GetNodeRequest(str);
        getNodeRequest.setTempLink(true);
        try {
            cloudDriveFileUploadListener.onSuccess(str, str2, this.mCDSClient.getNode(getNodeRequest).getTempLink());
        } catch (Exception e) {
            LOG.e("[Sharing] Get temp link for node has failed: ", e);
            cloudDriveFileUploadListener.onError(e);
        }
    }

    private UploadFileExtendedRequest getUploadFileExtendedRequest(@NonNull File file, @NonNull String str, @NonNull FileInputStream fileInputStream) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mAlexaFolderNodeId);
        CommsLogger commsLogger = LOG;
        commsLogger.d("[Sharing] Uploading a file named: " + str);
        UploadFileExtendedRequest uploadFileExtendedRequest = new UploadFileExtendedRequest(str, fileInputStream, file.length());
        uploadFileExtendedRequest.setParents(arrayList);
        uploadFileExtendedRequest.setSuppress(Suppress.Deduplication);
        uploadFileExtendedRequest.setConflictResolution("NONE");
        return uploadFileExtendedRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNodeAvailable(GetNodeExtendedResponse getNodeExtendedResponse) {
        return "AVAILABLE".equals(getNodeExtendedResponse.getStatus());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$uploadFileExtended$6(String str, long j, long j2) {
        long j3 = j2 != 0 ? (j / j2) * 100 : -1L;
        CommsLogger commsLogger = LOG;
        commsLogger.i("[Sharing] UploadFileExtendedRequest - " + str + " - Upload Progress - " + j3);
    }

    private void refreshEndpointsFromCDS() {
        try {
            GetAccountEndpointResponse accountEndpoint = this.mCDSClient.getAccountEndpoint(new GetAccountEndpointRequest());
            this.mEndpointsCache.setEndpoints(new Endpoints(accountEndpoint.getMetadataUrl(), accountEndpoint.getContentUrl(), accountEndpoint.getMetadataUrl().replace("v1/", "v2/photosGroups/")));
        } catch (CloudDriveException | InterruptedException e) {
            LOG.e("CDS Exception while attempting to retrieve fresh endpoints", e);
        }
    }

    private void resetCloudNodeId() {
        this.mRootNodeId = null;
        this.mAlexaFolderNodeId = null;
    }

    private boolean retrieveAlexaFolderNodeId() throws InterruptedException, CloudDriveException {
        String str = null;
        while (true) {
            try {
                ListChildrenRequest listChildrenRequest = new ListChildrenRequest(this.mRootNodeId);
                listChildrenRequest.setStartToken(str);
                ListChildrenResponse listChildren = this.mCDSClient.listChildren(listChildrenRequest);
                LOG.d("[Sharing] Retrieved listChildrenResponse from CloudDrive");
                String nextToken = listChildren.getNextToken();
                Iterator<Node> it2 = listChildren.getData().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Node next = it2.next();
                    if ("Alexa Shared Photos".equals(next.getName())) {
                        LOG.d("[Sharing] AlexaFolderName was found in the listChildrenResponse from CloudDrive");
                        setAlexaNodeId(next.getId());
                        break;
                    }
                }
                if (nextToken == null || !TextUtils.isEmpty(this.mAlexaFolderNodeId)) {
                    break;
                }
                str = nextToken;
            } catch (CloudDriveException e) {
                LOG.e("[Sharing] The CDS client threw error during retrieveRootNodeId!", e);
                int httpCode = e.getHttpCode();
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("GetAlexa.");
                outline1.append(e.getCode());
                recordCDSFailureMetrics("comms.cds.setup.error", e, httpCode, outline1.toString(), e.getLogref());
                throw e;
            } catch (InterruptedException e2) {
                LOG.e("[Sharing] An interrupt event was encountered during retrieveRootNodeId!", e2);
                recordCDSFailureMetrics("comms.cds.setup.error", e2, -9, "GetAlexa.interrupt", INTERRUPT_CODE);
                throw e2;
            }
        }
        return !TextUtils.isEmpty(this.mAlexaFolderNodeId);
    }

    private boolean retrieveRootNodeId() {
        LOG.d("[Sharing] Making request for RootNodeId.");
        ListNodesRequest listNodesRequest = new ListNodesRequest();
        listNodesRequest.setFilters("isRoot:true");
        try {
            List<Node> data = this.mCDSClient.listNodes(listNodesRequest).getData();
            if (!data.isEmpty()) {
                setRootNodeId(data.get(0).getId());
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("[Sharing] Retrieved rootNodeId from CloudDrive: ");
                sb.append(this.mRootNodeId);
                commsLogger.d(sb.toString());
                return true;
            }
            setRootNodeId(null);
            throw new CloudDriveException("No nodes were found in the list", "empty_nodes", this.directedId);
        } catch (CloudDriveException e) {
            LOG.e("[Sharing] The CDS client failed during retrieveRootNodeId!", e);
            int httpCode = e.getHttpCode();
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("RootID.");
            outline1.append(e.getCode());
            recordCDSFailureMetrics("comms.cds.setup.error", e, httpCode, outline1.toString(), e.getLogref());
            return false;
        } catch (InterruptedException e2) {
            LOG.e("[Sharing] Interrupted during retrieveRootNodeId!", e2);
            recordCDSFailureMetrics("comms.cds.setup.error", e2, -9, "RootID.interrupt", INTERRUPT_CODE);
            return false;
        }
    }

    private boolean setupAccount() {
        SetupAccountRequest setupAccountRequest = new SetupAccountRequest();
        setupAccountRequest.setTermsOfUse("1.0.0");
        try {
            this.mCDSClient.setupAccount(setupAccountRequest);
            Utils.writeBooleanPreferenceToSharedPrefs(this.mContext, "CDS_ACCOUNT_SETUP_SUCCESSFUL", true);
            return true;
        } catch (CloudDriveException e) {
            LOG.e("[Sharing] The CDS client failed during setupAccount!", e);
            int httpCode = e.getHttpCode();
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Account.");
            outline1.append(e.getCode());
            recordCDSFailureMetrics("comms.cds.setup.error", e, httpCode, outline1.toString(), e.getLogref());
            return false;
        } catch (InterruptedException e2) {
            LOG.e("[Sharing] Interrupted during setupAccount!", e2);
            recordCDSFailureMetrics("comms.cds.setup.error", e2, -9, "Account.interrupt", INTERRUPT_CODE);
            return false;
        }
    }

    private void updateCommsUser() {
        this.directedId = this.mCommsIdentityManager.getDirectedId(SOURCE, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadFileExtended(@NonNull File file, @NonNull final String str, @NonNull CloudDriveFileUploadListener cloudDriveFileUploadListener) throws Exception {
        if (!isAccountSetupCompleted()) {
            completeAccountSetupTasks();
        }
        UploadFileExtendedResponse uploadFileExtended = this.mCDSClient.uploadFileExtended(getUploadFileExtendedRequest(file, str, wrapFileInputStream(file)), new ProgressListener() { // from class: com.amazon.deecomms.media.photos.-$$Lambda$FileTransmitter$0qys7oBioqiDLC9yxHsKlkQCZs8
            @Override // com.amazon.clouddrive.handlers.ProgressListener
            public final void onProgress(long j, long j2) {
                FileTransmitter.lambda$uploadFileExtended$6(str, j, j2);
            }
        });
        getTempLinkForNodeID(uploadFileExtended.getId(), uploadFileExtended.getOwnerId(), cloudDriveFileUploadListener);
    }

    private boolean validateCurrentUser() throws SharingSDKException {
        UserIdentity userIdentity = this.mCurrentUser;
        if (userIdentity != null && userIdentity.getId() != null) {
            String str = this.directedId;
            if (str != null && !str.isEmpty()) {
                return true;
            }
            LOG.i("[Sharing] Skip the call to initialize -- the comms directedId is missing.");
            throw new InvalidCommsUserSDKException("Directed id was empty");
        }
        LOG.i("[Sharing] Skip the call to initialize -- the current user/cId is null.");
        throw new InvalidCommsUserSDKException("User id was empty");
    }

    @VisibleForTesting
    CloudDriveFileDownloadListener createDownloadFileListenerForSubscriber(@NonNull final Subscriber<? super CloudDriveService.DownloadResponse> subscriber) {
        return new CloudDriveFileDownloadListener() { // from class: com.amazon.deecomms.media.photos.FileTransmitter.4
            TimerMetric timerMetric = MetricsHelper.createTimerMetric(MetricKeys.CLOUD_DRIVE_DOWNLOAD_LATENCY);

            @Override // com.amazon.deecomms.media.photos.CloudDriveFileDownloadListener
            public void onError(Exception exc) {
                FileTransmitter.LOG.e("[Sharing] Image download failed:", exc);
                MetricsHelper.recordCounterMetricOperational("comms.cds.download", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                subscriber.onError(exc);
            }

            @Override // com.amazon.deecomms.media.photos.CloudDriveFileDownloadListener
            public void onSuccess(@NonNull String str, @NonNull String str2, @NonNull String str3, int i, int i2) {
                FileTransmitter.LOG.d("[Sharing] Image download success:", str);
                MetricsHelper.recordCounterMetricOperational("comms.cds.download", 1.0d);
                MetricsHelper.recordTimerMetric(this.timerMetric);
                subscriber.onNext(FileTransmitter.this.processDownloadFileResponseOnSuccess(str, str2, str3, i, i2));
                subscriber.onCompleted();
            }
        };
    }

    @VisibleForTesting
    CloudDriveFileDownloadListener createDownloadThumbnailListenerForSubscriber(@NonNull final Subscriber<? super CloudDriveService.DownloadResponse> subscriber) {
        return new CloudDriveFileDownloadListener() { // from class: com.amazon.deecomms.media.photos.FileTransmitter.5
            TimerMetric timerMetric = MetricsHelper.createTimerMetric(MetricKeys.CLOUD_DRIVE_THUMBNAIL_LATENCY);

            @Override // com.amazon.deecomms.media.photos.CloudDriveFileDownloadListener
            public void onError(Exception exc) {
                FileTransmitter.LOG.e("[Sharing] Image thumbnail failed:", exc);
                MetricsHelper.recordCounterMetricOperational("comms.cds.thumb", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                subscriber.onError(exc);
            }

            @Override // com.amazon.deecomms.media.photos.CloudDriveFileDownloadListener
            public void onSuccess(@NonNull String str, @NonNull String str2, @NonNull String str3, int i, int i2) {
                FileTransmitter.LOG.d("[Sharing] Image thumbnail success:", str);
                MetricsHelper.recordCounterMetricOperational("comms.cds.thumb", 1.0d);
                MetricsHelper.recordTimerMetric(this.timerMetric);
                subscriber.onNext(FileTransmitter.this.processDownloadThumbnailResponseOnSuccess(str, i, i2));
                subscriber.onCompleted();
            }
        };
    }

    @VisibleForTesting
    CloudDriveFileUploadListener createUploadListenerForSubscriber(@NonNull final Subscriber<? super CloudDriveService.UploadResponse> subscriber) {
        return new CloudDriveFileUploadListener() { // from class: com.amazon.deecomms.media.photos.FileTransmitter.3
            TimerMetric timerMetric = MetricsHelper.createTimerMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_LATENCY);

            @Override // com.amazon.deecomms.media.photos.CloudDriveFileUploadListener
            public void onError(Exception exc) {
                FileTransmitter.LOG.e("[Sharing] Image upload failed:  ", exc);
                MetricsHelper.recordCounterMetricOperational("comms.cds.upload", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                subscriber.onError(exc);
            }

            @Override // com.amazon.deecomms.media.photos.CloudDriveFileUploadListener
            public void onSuccess(@NonNull String str, @NonNull String str2, @Nullable String str3) {
                FileTransmitter.LOG.d("[Sharing] Image upload success:", str);
                MetricsHelper.recordCounterMetricOperational("comms.cds.upload", 1.0d);
                MetricsHelper.recordTimerMetric(this.timerMetric);
                subscriber.onNext(FileTransmitter.this.processUploadResponseOnSuccess(str, str2, str3));
                subscriber.onCompleted();
            }
        };
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public Observable<CloudDriveService.DownloadResponse> downloadImage(@NonNull final String str, @NonNull final String str2, @NonNull final String str3) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.deecomms.media.photos.-$$Lambda$FileTransmitter$3r3CImfUoyaOtlx8bbppPKZ9Ybw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FileTransmitter.this.lambda$downloadImage$4$FileTransmitter(str, str2, str3, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public AsyncTask<Void, Void, Void> downloadSingleFile(@NonNull final String str, @NonNull final String str2, @NonNull final String str3, @NonNull final CloudDriveFileDownloadListener cloudDriveFileDownloadListener) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.media.photos.FileTransmitter.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                GetNodeExtendedResponse nodeExtended;
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("_");
                String outline91 = GeneratedOutlineSupport1.outline91(sb, str2, ".jpg");
                try {
                    if (!FileTransmitter.this.isAccountSetupCompleted()) {
                        FileTransmitter.this.completeAccountSetupTasks();
                    }
                    CommsLogger commsLogger = FileTransmitter.LOG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("[Sharing] Downloading file, display name: ");
                    sb2.append(outline91);
                    commsLogger.d(sb2.toString());
                    ArrayList arrayList = new ArrayList();
                    if (!TextUtils.isEmpty(FileTransmitter.this.mAlexaFolderNodeId)) {
                        arrayList.add(FileTransmitter.this.mAlexaFolderNodeId);
                    }
                    GetNodeExtendedRequest getNodeExtendedRequest = new GetNodeExtendedRequest(str);
                    getNodeExtendedRequest.setOwnerId(str3);
                    nodeExtended = FileTransmitter.this.mCDSClient.getNodeExtended(getNodeExtendedRequest);
                } catch (CloudDriveException e) {
                    FileTransmitter.LOG.e("[Sharing] The CDS client threw error during retrieveRootNodeId!", e);
                    FileTransmitter.this.recordCDSFailureMetrics("comms.cds.download.error", e, e.getHttpCode(), e.getCode(), str);
                    cloudDriveFileDownloadListener.onError(e);
                } catch (InterruptedException e2) {
                    FileTransmitter.LOG.e("[Sharing] An interrupt event was encountered during retrieveRootNodeId!", e2);
                    FileTransmitter.this.recordCDSFailureMetrics("comms.cds.download.error", e2, -9, FileTransmitter.INTERRUPT_CODE, str);
                    cloudDriveFileDownloadListener.onError(e2);
                } catch (Exception e3) {
                    CommsLogger commsLogger2 = FileTransmitter.LOG;
                    commsLogger2.e("[Sharing] Download failed for " + outline91 + " - Exception occurred", e3);
                    FileTransmitter.this.recordCDSGenericFailureMetric("comms.cds.download.error", e3, str, -1);
                    cloudDriveFileDownloadListener.onError(e3);
                }
                if (FileTransmitter.this.isNodeAvailable(nodeExtended)) {
                    File createNewFileForName = FileTransmitter.this.createNewFileForName(outline91);
                    String path = createNewFileForName.getPath();
                    CommsLogger commsLogger3 = FileTransmitter.LOG;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("[Sharing] File available for download, Storing output to: ");
                    sb3.append(path);
                    commsLogger3.d(sb3.toString());
                    DownloadFileExtendedRequest downloadFileExtendedRequest = new DownloadFileExtendedRequest(str, new FileOutputStream(createNewFileForName));
                    downloadFileExtendedRequest.setOwnerId(str3);
                    FileTransmitter.this.mCDSClient.downloadFileExtended(downloadFileExtendedRequest, FileTransmitter.this.createProgressListenerToListenOnDownloadCompletion(path, nodeExtended, cloudDriveFileDownloadListener));
                    return null;
                }
                CloudDriveFileDownloadListener cloudDriveFileDownloadListener2 = cloudDriveFileDownloadListener;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Download failed, status returned:");
                sb4.append(nodeExtended.getStatus());
                cloudDriveFileDownloadListener2.onError(new CloudDriveException(sb4.toString()));
                return null;
            }
        };
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public AsyncTask<Void, Void, Void> downloadSingleThumbnail(@NonNull final String str, @NonNull final int i, @NonNull final String str2, @NonNull final CloudDriveFileDownloadListener cloudDriveFileDownloadListener) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.media.photos.FileTransmitter.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                GetNodeExtendedResponse nodeExtended;
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("_");
                String outline86 = GeneratedOutlineSupport1.outline86(sb, i, ".jpg");
                try {
                    CommsLogger commsLogger = FileTransmitter.LOG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("[Sharing] Downloading file, display name: ");
                    sb2.append(outline86);
                    commsLogger.d(sb2.toString());
                    ArrayList arrayList = new ArrayList();
                    if (!TextUtils.isEmpty(FileTransmitter.this.mAlexaFolderNodeId)) {
                        arrayList.add(FileTransmitter.this.mAlexaFolderNodeId);
                    }
                    GetNodeExtendedRequest getNodeExtendedRequest = new GetNodeExtendedRequest(str);
                    getNodeExtendedRequest.setOwnerId(str2);
                    nodeExtended = FileTransmitter.this.mCDSClient.getNodeExtended(getNodeExtendedRequest);
                } catch (CloudDriveException e) {
                    FileTransmitter.LOG.e("[Sharing] The CDS client threw error during retrieveRootNodeId!", e);
                    FileTransmitter.this.recordCDSFailureMetrics("comms.cds.thumb.error", e, e.getHttpCode(), e.getCode(), str);
                    cloudDriveFileDownloadListener.onError(e);
                } catch (InterruptedException e2) {
                    FileTransmitter.LOG.e("[Sharing] An interrupt event was encountered during retrieveRootNodeId!", e2);
                    FileTransmitter.this.recordCDSFailureMetrics("comms.cds.thumb.error", e2, -9, FileTransmitter.INTERRUPT_CODE, str);
                    cloudDriveFileDownloadListener.onError(e2);
                } catch (Exception e3) {
                    CommsLogger commsLogger2 = FileTransmitter.LOG;
                    commsLogger2.e("[Sharing] Download failed for " + outline86 + " - Exception occurred", e3);
                    FileTransmitter.this.recordCDSGenericFailureMetric("comms.cds.thumb.error", e3, str, -1);
                    cloudDriveFileDownloadListener.onError(e3);
                }
                if (FileTransmitter.this.isNodeAvailable(nodeExtended)) {
                    File createNewFileForName = FileTransmitter.this.createNewFileForName(outline86);
                    CommsLogger commsLogger3 = FileTransmitter.LOG;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("[Sharing] Thumbnail file available for download, Storing output to: ");
                    sb3.append(createNewFileForName.getPath());
                    commsLogger3.d(sb3.toString());
                    GetThumbnailExtendedRequest getThumbnailExtendedRequest = new GetThumbnailExtendedRequest(str, new FileOutputStream(createNewFileForName));
                    getThumbnailExtendedRequest.setOwnerId(str2);
                    getThumbnailExtendedRequest.setViewBox(i);
                    FileTransmitter.this.mCDSClient.getThumbnailExtended(getThumbnailExtendedRequest, FileTransmitter.this.createProgressListenerToListenOnDownloadCompletion(createNewFileForName.getPath(), nodeExtended, cloudDriveFileDownloadListener));
                    return null;
                }
                CloudDriveFileDownloadListener cloudDriveFileDownloadListener2 = cloudDriveFileDownloadListener;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Thumbnail Download failed, status returned:");
                sb4.append(nodeExtended.getStatus());
                cloudDriveFileDownloadListener2.onError(new CloudDriveException(sb4.toString()));
                return null;
            }
        };
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public Observable<CloudDriveService.DownloadResponse> downloadThumbnail(@NonNull final String str, @NonNull final int i, @NonNull final String str2) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.deecomms.media.photos.-$$Lambda$FileTransmitter$a8W6l9M6m7VVvqDTprxjldxV_gA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FileTransmitter.this.lambda$downloadThumbnail$5$FileTransmitter(str, i, str2, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public String getAlexaFolderId() {
        return this.mAlexaFolderNodeId;
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public String getCDSGroupEndpoint() {
        if (this.mEndpointsCache.cacheHasExpired()) {
            LOG.i("[Sharing] CDS Endpoint has expired. Retrieving new endpoints");
            refreshEndpointsFromCDS();
        }
        Endpoints endpoints = this.mEndpointsCache.getEndpoints();
        if (endpoints == null) {
            LOG.e("[Sharing] CDS Endpoint was not retrieved. Call to CDS will FAIL");
            return null;
        }
        return endpoints.getMetaDataEndpoint().replaceAll("/v1", "");
    }

    @Nullable
    UserIdentity getCurrentUser() {
        return this.mCurrentUser;
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public String getRootNodeId() {
        return this.mRootNodeId;
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public void init(@NonNull SetupCompletedListener setupCompletedListener) {
        try {
            updateCommsUser();
            validateCurrentUser();
            startSetupAccountAsyncTask(setupCompletedListener).execute(new Void[0]);
        } catch (SharingSDKException e) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Sharing] Failed to complete setup account tasks -- ");
            outline1.append(e.getErrorMessage());
            commsLogger.e(outline1.toString());
            setupCompletedListener.onError(e);
        }
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public Completable initialize() {
        LOG.d("[Sharing] Initializing a FileTransmitter");
        return Completable.create(new Completable.CompletableOnSubscribe() { // from class: com.amazon.deecomms.media.photos.-$$Lambda$FileTransmitter$kPoP6P2Szqkww2O45Kbk0UG3OpM
            @Override // rx.functions.Action1
            public final void call(Completable.CompletableSubscriber completableSubscriber) {
                FileTransmitter.this.lambda$initialize$2$FileTransmitter(completableSubscriber);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @VisibleForTesting
    boolean isAccountSetupCompleted() {
        return !TextUtils.isEmpty(this.mAlexaFolderNodeId) && !TextUtils.isEmpty(this.mRootNodeId) && !TextUtils.isEmpty(this.mAlexaFolderNodeId) && Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, "CDS_ACCOUNT_SETUP_SUCCESSFUL", false);
    }

    public /* synthetic */ void lambda$createProgressListenerToListenOnDownloadCompletion$7$FileTransmitter(String str, GetNodeExtendedResponse getNodeExtendedResponse, CloudDriveFileDownloadListener cloudDriveFileDownloadListener, long j, long j2) {
        long j3 = j2 != 0 ? (j / j2) * 100 : -1L;
        CommsLogger commsLogger = LOG;
        StringBuilder sb = new StringBuilder();
        sb.append("[Sharing] DownloadThumbnailExtendedRequest - ");
        sb.append(str);
        sb.append(" - Download Progress - ");
        sb.append(j3);
        GeneratedOutlineSupport1.outline177(sb, "%", commsLogger);
        if (j != j2) {
            return;
        }
        onDownloadCompleted(str, getNodeExtendedResponse.getContentProperties(), cloudDriveFileDownloadListener);
    }

    public /* synthetic */ void lambda$initialize$2$FileTransmitter(final Completable.CompletableSubscriber completableSubscriber) {
        init(new SetupCompletedListener() { // from class: com.amazon.deecomms.media.photos.FileTransmitter.1
            @Override // com.amazon.deecomms.media.photos.SetupCompletedListener
            public void onError(Exception exc) {
                completableSubscriber.onError(exc);
            }

            @Override // com.amazon.deecomms.media.photos.SetupCompletedListener
            public void onSuccess() {
                MetricsHelper.recordSingleOccurrenceOperational("comms.cds.setup");
                completableSubscriber.onCompleted();
            }
        });
    }

    public /* synthetic */ void lambda$observeUserChanges$1$FileTransmitter(Message message) {
        userChangedCallback(this.mIdentityService.getUser(FileTransmitter.class.getName()));
    }

    @VisibleForTesting
    void observeUserChanges() {
        this.mEventBus.getSubscriber().subscribeFilter($$Lambda$FileTransmitter$J8QZcyEeV4PUK11wBvdmE7HMXeA.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.media.photos.-$$Lambda$FileTransmitter$LvTf2AaL-nhYjEs3ITxywe64SSI
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                FileTransmitter.this.lambda$observeUserChanges$1$FileTransmitter(message);
            }
        });
    }

    @VisibleForTesting
    void onDownloadCompleted(@NonNull String str, @Nullable ContentProperties contentProperties, @NonNull CloudDriveFileDownloadListener cloudDriveFileDownloadListener) {
        String mediaTypeForContent = getMediaTypeForContent(contentProperties);
        Pair<Integer, Integer> heightAndWidthForContent = getHeightAndWidthForContent(contentProperties);
        int intValue = heightAndWidthForContent.first.intValue();
        int intValue2 = heightAndWidthForContent.second.intValue();
        StringBuilder outline1 = GeneratedOutlineSupport.outline1(".");
        outline1.append(contentProperties.getExtension());
        cloudDriveFileDownloadListener.onSuccess(str, mediaTypeForContent, outline1.toString(), intValue, intValue2);
    }

    @VisibleForTesting
    CloudDriveService.DownloadResponse processDownloadFileResponseOnSuccess(@NonNull String str, @NonNull String str2, @NonNull String str3, int i, int i2) {
        CloudDriveService.DownloadResponse downloadResponse = new CloudDriveService.DownloadResponse();
        downloadResponse.setLocalMediaPath(str);
        downloadResponse.setMediaType(str2);
        downloadResponse.setFileExtension(str3);
        downloadResponse.setHeight(i);
        downloadResponse.setWidth(i2);
        return downloadResponse;
    }

    @VisibleForTesting
    CloudDriveService.DownloadResponse processDownloadThumbnailResponseOnSuccess(@NonNull String str, int i, int i2) {
        CloudDriveService.DownloadResponse downloadResponse = new CloudDriveService.DownloadResponse();
        downloadResponse.setLocalMediaPath(str);
        downloadResponse.setMediaType("image");
        downloadResponse.setFileExtension(".jpg");
        downloadResponse.setHeight(i);
        downloadResponse.setWidth(i2);
        return downloadResponse;
    }

    @VisibleForTesting
    CloudDriveService.UploadResponse processUploadResponseOnSuccess(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        CloudDriveService.UploadResponse uploadResponse = new CloudDriveService.UploadResponse();
        uploadResponse.setId(str);
        uploadResponse.setUrl(str3 + "/alt/thumb?viewBox=1280");
        uploadResponse.setOwnerID(str2);
        return uploadResponse;
    }

    @VisibleForTesting
    CounterMetric[] recordCDSFailureMetrics(@NonNull String str, @NonNull Exception exc, @NonNull int i, @NonNull String str2, @NonNull String str3) {
        return MetricsHelper.recordCDSErrorMetrics(str, exc, i, str2, str3);
    }

    @VisibleForTesting
    CounterMetric recordCDSGenericFailureMetric(@NonNull String str, @NonNull Throwable th, @NonNull String str2, @NonNull int i) {
        CounterMetric generateOperationalException = CounterMetric.generateOperationalException(th, str, str2, i);
        MetricsHelper.recordSingleOccurrence(generateOperationalException);
        return generateOperationalException;
    }

    protected boolean resolveParentNodeIdNotFoundError(@NonNull File file, @NonNull String str, @NonNull CloudDriveFileUploadListener cloudDriveFileUploadListener) {
        try {
            resetCloudNodeId();
            uploadFileExtended(file, str, cloudDriveFileUploadListener);
            return true;
        } catch (CloudDriveException e) {
            recordCDSFailureMetrics("comms.cds.upload.error", e, e.getHttpCode(), e.getCode(), this.directedId);
            LOG.e("[Sharing] The CDS client threw error during retrieveRootNodeId!", e);
            return false;
        } catch (Exception e2) {
            recordCDSGenericFailureMetric("comms.cds.upload.error", e2, this.directedId, -1);
            CommsLogger commsLogger = LOG;
            commsLogger.e("[Sharing] Upload failed for file " + str + " - Exception occurred", e2);
            return false;
        }
    }

    protected boolean resolveUploadConflictError(@NonNull ConflictError conflictError, @NonNull CloudDriveFileUploadListener cloudDriveFileUploadListener) {
        if (conflictError.getCode().equals("NAME_ALREADY_EXISTS") || conflictError.getCode().equals("ID_ALREADY_EXISTS") || conflictError.getCode().equals("MD5_DUPLICATE")) {
            getTempLinkForNodeID(conflictError.getNodeId(), this.mCurrentUser.getId(), cloudDriveFileUploadListener);
            return true;
        }
        return false;
    }

    @VisibleForTesting
    void setAlexaFolderName(@Nullable String str) {
        this.mAlexaFolderName = str;
    }

    @VisibleForTesting
    void setAlexaNodeId(@Nullable String str) {
        this.mAlexaFolderNodeId = str;
    }

    @VisibleForTesting
    void setRootNodeId(@Nullable String str) {
        this.mRootNodeId = str;
    }

    @VisibleForTesting
    AsyncTask<Void, Void, Void> startSetupAccountAsyncTask(@NonNull final SetupCompletedListener setupCompletedListener) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.media.photos.FileTransmitter.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                if (FileTransmitter.this.mCDSClient != null) {
                    try {
                        FileTransmitter.this.completeAccountSetupTasks();
                        setupCompletedListener.onSuccess();
                    } catch (CloudDriveException e) {
                        setupCompletedListener.onError(e);
                    }
                    return null;
                }
                setupCompletedListener.onError(new CloudDriveException("[Sharing] CDS Account Setup failure, CDS Client cannot be null."));
                return null;
            }
        };
    }

    /* renamed from: subscribeToListenerAndBeginDownloadFile */
    public void lambda$downloadImage$4$FileTransmitter(@NonNull Subscriber<? super CloudDriveService.DownloadResponse> subscriber, @NonNull String str, @NonNull String str2, @NonNull String str3) {
        downloadSingleFile(str, str2, str3, createDownloadFileListenerForSubscriber(subscriber)).execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: subscribeToListenerAndBeginDownloadThumbnail */
    public void lambda$downloadThumbnail$5$FileTransmitter(@NonNull Subscriber<? super CloudDriveService.DownloadResponse> subscriber, @NonNull String str, int i, @NonNull String str2) {
        downloadSingleThumbnail(str, i, str2, createDownloadThumbnailListenerForSubscriber(subscriber)).execute(new Void[0]);
    }

    /* renamed from: subscribeToListenerAndBeginUploadImage */
    public void lambda$uploadImage$3$FileTransmitter(@NonNull Uri uri, @NonNull Subscriber<? super CloudDriveService.UploadResponse> subscriber) {
        uploadSingleFile(uri, createUploadListenerForSubscriber(subscriber)).execute(new Void[0]);
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public Observable<CloudDriveService.UploadResponse> uploadImage(@NonNull final Uri uri) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.deecomms.media.photos.-$$Lambda$FileTransmitter$rDDUEYHDFFoC0TLp9Md2gmK8wBU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FileTransmitter.this.lambda$uploadImage$3$FileTransmitter(uri, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @Override // com.amazon.deecomms.media.photos.CloudDriveService
    public AsyncTask<Void, Void, Void> uploadSingleFile(@NonNull final Uri uri, @NonNull final CloudDriveFileUploadListener cloudDriveFileUploadListener) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.media.photos.FileTransmitter.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                CommsLogger commsLogger = FileTransmitter.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Sharing] Staging a file from: ");
                outline1.append(uri.getPath());
                commsLogger.d(outline1.toString());
                File copyContentStreamToCache = Utils.copyContentStreamToCache(uri);
                StringBuilder outline12 = GeneratedOutlineSupport.outline1("");
                outline12.append(new Random().nextInt(100));
                outline12.append(copyContentStreamToCache.getName());
                String sb = outline12.toString();
                try {
                    try {
                        FileTransmitter.this.uploadFileExtended(copyContentStreamToCache, sb, cloudDriveFileUploadListener);
                        return null;
                    } catch (ConflictError e) {
                        if (!FileTransmitter.this.resolveUploadConflictError(e, cloudDriveFileUploadListener)) {
                            throw e;
                        }
                        return null;
                    } catch (InvalidParameter e2) {
                        FileTransmitter.this.recordCDSFailureMetrics("comms.cds.upload.error", e2, e2.getHttpCode(), e2.getCode(), FileTransmitter.this.directedId);
                        if (!FileTransmitter.this.resolveParentNodeIdNotFoundError(copyContentStreamToCache, sb, cloudDriveFileUploadListener)) {
                            throw e2;
                        }
                        return null;
                    }
                } catch (CloudDriveException e3) {
                    FileTransmitter.LOG.e("[Sharing] The CDS client threw error during retrieveRootNodeId!", e3);
                    FileTransmitter.this.recordCDSFailureMetrics("comms.cds.upload.error", e3, e3.getHttpCode(), e3.getCode(), FileTransmitter.this.directedId);
                    cloudDriveFileUploadListener.onError(e3);
                    return null;
                } catch (InterruptedException e4) {
                    FileTransmitter.LOG.e("[Sharing] An interrupt event was encountered during retrieveRootNodeId!", e4);
                    FileTransmitter fileTransmitter = FileTransmitter.this;
                    fileTransmitter.recordCDSFailureMetrics("comms.cds.upload.error", e4, -9, FileTransmitter.INTERRUPT_CODE, fileTransmitter.directedId);
                    cloudDriveFileUploadListener.onError(e4);
                    return null;
                } catch (Exception e5) {
                    FileTransmitter fileTransmitter2 = FileTransmitter.this;
                    fileTransmitter2.recordCDSGenericFailureMetric("comms.cds.upload.error", e5, fileTransmitter2.directedId, -1);
                    CommsLogger commsLogger2 = FileTransmitter.LOG;
                    commsLogger2.e("[Sharing] Upload failed for file " + sb + " - Exception occurred", e5);
                    cloudDriveFileUploadListener.onError(e5);
                    return null;
                }
            }
        };
    }

    @VisibleForTesting
    void userChangedCallback(UserIdentity userIdentity) {
        if (Objects.equals(userIdentity, this.mCurrentUser)) {
            return;
        }
        this.mCurrentUser = userIdentity;
    }

    @VisibleForTesting
    FileInputStream wrapFileInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    public FileTransmitter(@NonNull Context context, @NonNull ClientConfiguration clientConfiguration, @NonNull MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, @NonNull EndpointsCache endpointsCache, @NonNull IdentityService identityService, @NonNull CommsIdentityManager commsIdentityManager, @NonNull AccountConfiguration accountConfiguration, @NonNull ExecutorService executorService, @Nullable AmazonCloudDriveExtendedClient amazonCloudDriveExtendedClient, @NonNull EventBus eventBus) {
        this.mContext = context;
        this.mIdentityService = identityService;
        this.mCurrentUser = identityService.getUser(className);
        this.mCommsIdentityManager = commsIdentityManager;
        this.mConnectionFactory = mAPAuthenticatedURLConnectionFactory;
        this.mEndpointsCache = endpointsCache;
        this.mClientConfiguration = clientConfiguration;
        this.mAccountConfiguration = accountConfiguration;
        this.mExecutor = executorService;
        this.mCDSClient = amazonCloudDriveExtendedClient;
        this.directedId = this.mCommsIdentityManager.getDirectedId(SOURCE, false);
        this.mEventBus = eventBus;
        observeUserChanges();
    }
}
