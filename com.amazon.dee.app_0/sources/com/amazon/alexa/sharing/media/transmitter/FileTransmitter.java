package com.amazon.alexa.sharing.media.transmitter;

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
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingSDKException;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.media.model.CloudDriveFileDownloadListener;
import com.amazon.alexa.sharing.media.model.CloudDriveFileUploadListener;
import com.amazon.alexa.sharing.media.model.CloudDriveService;
import com.amazon.alexa.sharing.media.model.SetupCompletedListener;
import com.amazon.alexa.sharing.sharingsdk.photos.CommonContentProperties;
import com.amazon.alexa.sharing.util.SharedPreferenceUtils;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.ConflictError;
import com.amazon.clouddrive.exceptions.InvalidParameter;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtended;
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
import com.amazon.clouddrive.model.ImageProperties;
import com.amazon.clouddrive.model.ListChildrenRequest;
import com.amazon.clouddrive.model.ListChildrenResponse;
import com.amazon.clouddrive.model.ListNodesRequest;
import com.amazon.clouddrive.model.Node;
import com.amazon.clouddrive.model.Suppress;
import com.amazon.clouddrive.model.VideoProperties;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.metrics.TimerMetric;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.inject.Inject;
import rx.Completable;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
/* loaded from: classes10.dex */
public class FileTransmitter implements CloudDriveService {
    private static final String CREATE_ALEXA_FOLDER_METRICS_SUFFIX = "CreateAlexa";
    private static final String DEFAULT_IMAGE_EXTENSION = ".jpg";
    private static final String GET_ALEXA_FOLDER_METRICS_SUFFIX = "GetAlexa";
    private static final String GET_ROOT_NODE_ID_METRICS_SUFFIX = "RootID";
    private static final String INTERRUPT_CODE = "interrupt";
    private static final int INTERRUPT_STATUS_CODE = -9;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, FileTransmitter.class);
    private static final String METRIC_SOURCE = FileTransmitter.class.getSimpleName();
    private static final String SETUP_ACCOUNT_METRICS_SUFFIX = "Account";
    private static final String SOURCE = "com.amazon.alexa.sharing.media.transmitter.FileTransmitter";
    private String directedId;
    private String mAlexaFolderName;
    private String mAlexaFolderNodeId;
    private AmazonCloudDriveExtended mCDSClient;
    private final Context mContext;
    private UserIdentity mCurrentUser;
    private final EventBus mEventBus;
    private final IdentityService mIdentityService;
    private String mRootNodeId;
    private final CommsMetricsEmitter metricsEmitter;
    private final SharedPreferenceUtils sharedPreferenceUtils;

    @Inject
    public FileTransmitter(@NonNull Context context, @NonNull IdentityService identityService, @Nullable AmazonCloudDriveExtended amazonCloudDriveExtended, @NonNull EventBus eventBus, @NonNull SharedPreferenceUtils sharedPreferenceUtils, @NonNull CommsMetricsEmitter commsMetricsEmitter) {
        this.mContext = context;
        this.mIdentityService = identityService;
        this.mCurrentUser = identityService.getUser(SOURCE);
        this.mCDSClient = amazonCloudDriveExtended;
        UserIdentity userIdentity = this.mCurrentUser;
        this.directedId = userIdentity != null ? userIdentity.getDirectedId() : null;
        this.mEventBus = eventBus;
        this.metricsEmitter = commsMetricsEmitter;
        this.sharedPreferenceUtils = sharedPreferenceUtils;
        observeUserChanges();
    }

    private boolean createAlexaFolder() {
        LOG.d("[Sharing] creating alexa folder using name:Alexa Shared Photos");
        CreateNodeRequest createNodeRequest = new CreateNodeRequest("Alexa Shared Photos", "FOLDER");
        createNodeRequest.withParents(Collections.singletonList(this.mRootNodeId));
        try {
            setAlexaNodeId(this.mCDSClient.createNode(createNodeRequest).getId());
            setAlexaFolderName("Alexa Shared Photos");
            CommsLogger commsLogger = LOG;
            commsLogger.d("[Sharing] Alexa folder created, using name: " + this.mAlexaFolderName);
            return true;
        } catch (CloudDriveException e) {
            LOG.e("[Sharing] The CDS client threw error during createAlexaFolder!", e);
            int httpCode = e.getHttpCode();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateAlexa.");
            outline107.append(e.getCode());
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_SETUP_ERROR, e, httpCode, outline107.toString(), e.getLogref());
            return false;
        } catch (InterruptedException e2) {
            LOG.e("[Sharing] An interrupt event was encountered during createAlexaFolder!", e2);
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_SETUP_ERROR, e2, -9, "CreateAlexa.interrupt", INTERRUPT_CODE);
            return false;
        }
    }

    private ProgressListener createProgressListenerToListenOnDownloadCompletion(final String str, final GetNodeExtendedResponse getNodeExtendedResponse, final CloudDriveFileDownloadListener cloudDriveFileDownloadListener) {
        return new ProgressListener() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$FileTransmitter$_peN831xcADPel0l2AM5D08vbbI
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

    private boolean isNodeAvailable(GetNodeExtendedResponse getNodeExtendedResponse) {
        return "AVAILABLE".equals(getNodeExtendedResponse.getStatus());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$uploadFileExtended$6(String str, long j, long j2) {
        long j3 = j2 != 0 ? (j / j2) * 100 : -1L;
        CommsLogger commsLogger = LOG;
        commsLogger.i("[Sharing] UploadFileExtendedRequest - " + str + " - Upload Progress - " + j3);
    }

    private void resetCloudNodeId() {
        this.mRootNodeId = null;
        this.mAlexaFolderNodeId = null;
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
                commsLogger.d("[Sharing] Retrieved rootNodeId from CloudDrive: " + this.mRootNodeId);
                return true;
            }
            setRootNodeId(null);
            throw new CloudDriveException("No nodes were found in the list", "empty_nodes", this.directedId);
        } catch (CloudDriveException e) {
            LOG.e("[Sharing] The CDS client failed during retrieveRootNodeId!", e);
            int httpCode = e.getHttpCode();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RootID.");
            outline107.append(e.getCode());
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_SETUP_ERROR, e, httpCode, outline107.toString(), e.getLogref());
            return false;
        } catch (InterruptedException e2) {
            LOG.e("[Sharing] Interrupted during retrieveRootNodeId!", e2);
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_SETUP_ERROR, e2, -9, "RootID.interrupt", INTERRUPT_CODE);
            return false;
        }
    }

    private boolean setupAccount() {
        SetupAccountRequest setupAccountRequest = new SetupAccountRequest();
        setupAccountRequest.setTermsOfUse("1.0.0");
        try {
            this.mCDSClient.setupAccount(setupAccountRequest);
            this.sharedPreferenceUtils.writeBooleanPreferenceToSharedPrefs(this.mContext, "CDS_ACCOUNT_SETUP_SUCCESSFUL", true);
            return true;
        } catch (CloudDriveException e) {
            LOG.e("[Sharing] The CDS client failed during setupAccount!", e);
            int httpCode = e.getHttpCode();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Account.");
            outline107.append(e.getCode());
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_SETUP_ERROR, e, httpCode, outline107.toString(), e.getLogref());
            return false;
        } catch (InterruptedException e2) {
            LOG.e("[Sharing] Interrupted during setupAccount!", e2);
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_SETUP_ERROR, e2, -9, "Account.interrupt", INTERRUPT_CODE);
            return false;
        }
    }

    private void updateCommsUser() {
        this.directedId = this.mIdentityService.getUser(SOURCE).getDirectedId();
    }

    private boolean validateCurrentUser() throws AlexaSharingSDKException {
        UserIdentity userIdentity = this.mCurrentUser;
        if (userIdentity != null && userIdentity.getId() != null) {
            String str = this.directedId;
            if (str != null && !str.isEmpty()) {
                return true;
            }
            LOG.i("[Sharing] Skip the call to initialize -- the comms directedId is missing.");
            throw new AlexaSharingSDKException("Directed id was empty");
        }
        LOG.i("[Sharing] Skip the call to initialize -- the current user/cId is null.");
        throw new AlexaSharingSDKException("User id was empty");
    }

    synchronized boolean completeAccountSetupTasks() throws CloudDriveException {
        if (!this.sharedPreferenceUtils.getBooleanPreferenceFromSharedPrefs(this.mContext, "CDS_ACCOUNT_SETUP_SUCCESSFUL", false)) {
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
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_SETUP_SUCCESS, Collections.EMPTY_MAP);
        this.metricsEmitter.recordNonOccurrenceMetric(MetricKeys.CLOUD_DRIVE_SETUP_ERROR, Collections.EMPTY_MAP);
        LOG.d("[Sharing] Account initialized, AlexaFolderNodeId and RootNodeId set. Setup Completed.");
        return true;
    }

    @VisibleForTesting
    CloudDriveFileDownloadListener createDownloadFileListenerForSubscriber(@NonNull final Subscriber<? super CloudDriveService.DownloadResponse> subscriber) {
        return new CloudDriveFileDownloadListener() { // from class: com.amazon.alexa.sharing.media.transmitter.FileTransmitter.4
            TimerMetric timerMetric;

            {
                this.timerMetric = FileTransmitter.this.metricsEmitter.createTimerMetric(MetricKeys.CLOUD_DRIVE_DOWNLOAD_LATENCY, Collections.EMPTY_MAP);
            }

            @Override // com.amazon.alexa.sharing.media.model.CloudDriveFileDownloadListener
            public void onError(Exception exc) {
                FileTransmitter.LOG.e("[Sharing] Image download failed:", exc);
                FileTransmitter.this.recordCDSDownloadExceptionMetric(exc, MetricKeys.CLOUD_DRIVE_DOWNLOAD_ERROR);
                FileTransmitter.this.metricsEmitter.recordCriticalEvent(MetricKeys.CLOUD_DRIVE_DOWNLOAD_ERROR, exc.getClass().getSimpleName(), FileTransmitter.METRIC_SOURCE, exc);
                subscriber.onError(exc);
            }

            @Override // com.amazon.alexa.sharing.media.model.CloudDriveFileDownloadListener
            public void onSuccess(@NonNull String str, @NonNull String str2, @NonNull String str3, int i, int i2) {
                FileTransmitter.LOG.d("[Sharing] Image download success:", str);
                FileTransmitter.this.metricsEmitter.recordTimerMetric(this.timerMetric);
                FileTransmitter.this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_DOWNLOAD_SUCCESS, Collections.EMPTY_MAP);
                FileTransmitter.this.metricsEmitter.recordNonOccurrenceMetric(MetricKeys.CLOUD_DRIVE_DOWNLOAD_ERROR, Collections.EMPTY_MAP);
                subscriber.onNext(FileTransmitter.this.processDownloadFileResponseOnSuccess(str, str2, str3, i, i2));
                subscriber.onCompleted();
            }
        };
    }

    @VisibleForTesting
    CloudDriveFileDownloadListener createDownloadThumbnailListenerForSubscriber(@NonNull final Subscriber<? super CloudDriveService.DownloadResponse> subscriber) {
        return new CloudDriveFileDownloadListener() { // from class: com.amazon.alexa.sharing.media.transmitter.FileTransmitter.5
            TimerMetric timerMetric;

            {
                this.timerMetric = FileTransmitter.this.metricsEmitter.createTimerMetric(MetricKeys.CLOUD_DRIVE_THUMBNAIL_LATENCY, Collections.EMPTY_MAP);
            }

            @Override // com.amazon.alexa.sharing.media.model.CloudDriveFileDownloadListener
            public void onError(Exception exc) {
                FileTransmitter.LOG.e("[Sharing] Image thumbnail download failed:", exc);
                FileTransmitter.this.recordCDSDownloadExceptionMetric(exc, MetricKeys.CLOUD_DRIVE_THUMBNAIL_ERROR);
                FileTransmitter.this.metricsEmitter.recordCriticalEvent(MetricKeys.CLOUD_DRIVE_THUMBNAIL_ERROR, exc.getClass().getSimpleName(), FileTransmitter.METRIC_SOURCE, exc);
                subscriber.onError(exc);
            }

            @Override // com.amazon.alexa.sharing.media.model.CloudDriveFileDownloadListener
            public void onSuccess(@NonNull String str, @NonNull String str2, @NonNull String str3, int i, int i2) {
                FileTransmitter.LOG.d("[Sharing] Image thumbnail success:", str);
                FileTransmitter.this.metricsEmitter.recordTimerMetric(this.timerMetric);
                FileTransmitter.this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_THUMBNAIL_SUCCESS, Collections.EMPTY_MAP);
                FileTransmitter.this.metricsEmitter.recordNonOccurrenceMetric(MetricKeys.CLOUD_DRIVE_THUMBNAIL_ERROR, Collections.EMPTY_MAP);
                subscriber.onNext(FileTransmitter.this.processDownloadThumbnailResponseOnSuccess(str, i, i2));
                subscriber.onCompleted();
            }
        };
    }

    File createNewFileForName(String str) throws IOException {
        File file = new File(getLocalFilePath(str));
        file.createNewFile();
        return file;
    }

    @VisibleForTesting
    CloudDriveFileUploadListener createUploadListenerForSubscriber(@NonNull final Subscriber<? super CloudDriveService.UploadResponse> subscriber) {
        return new CloudDriveFileUploadListener() { // from class: com.amazon.alexa.sharing.media.transmitter.FileTransmitter.3
            TimerMetric timerMetric;

            {
                this.timerMetric = FileTransmitter.this.metricsEmitter.createTimerMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_LATENCY, Collections.EMPTY_MAP);
            }

            @Override // com.amazon.alexa.sharing.media.model.CloudDriveFileUploadListener
            public void onError(Exception exc) {
                FileTransmitter.LOG.e("[Sharing] Image upload failed:  ", exc);
                FileTransmitter.this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_ERROR, Collections.EMPTY_MAP);
                FileTransmitter.this.metricsEmitter.recordCriticalEvent(MetricKeys.CLOUD_DRIVE_UPLOAD_ERROR, exc.getClass().getSimpleName(), FileTransmitter.METRIC_SOURCE, exc);
                subscriber.onError(exc);
            }

            @Override // com.amazon.alexa.sharing.media.model.CloudDriveFileUploadListener
            public void onSuccess(@NonNull String str, @NonNull String str2) {
                FileTransmitter.LOG.d("[Sharing] Image upload success:", str);
                FileTransmitter.this.metricsEmitter.recordTimerMetric(this.timerMetric);
                FileTransmitter.this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_SUCCESS, Collections.EMPTY_MAP);
                FileTransmitter.this.metricsEmitter.recordNonOccurrenceMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_ERROR, Collections.EMPTY_MAP);
                subscriber.onNext(FileTransmitter.this.processUploadResponseOnSuccess(str, str2));
                subscriber.onCompleted();
            }
        };
    }

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public Observable<CloudDriveService.DownloadResponse> downloadImage(@NonNull final String str, @NonNull final String str2, @NonNull final String str3) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$FileTransmitter$vQKmPAd80NrmwjrtCW-5q5MMCZM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FileTransmitter.this.lambda$downloadImage$4$FileTransmitter(str, str2, str3, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public AsyncTask<Void, Void, Void> downloadSingleFile(@NonNull final String str, @NonNull final String str2, @NonNull final String str3, @NonNull final CloudDriveFileDownloadListener cloudDriveFileDownloadListener) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.alexa.sharing.media.transmitter.FileTransmitter.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                FileTransmitter.this.downloadSingleFileTask(str, str2, str3, cloudDriveFileDownloadListener);
                return null;
            }
        };
    }

    void downloadSingleFileTask(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull CloudDriveFileDownloadListener cloudDriveFileDownloadListener) {
        String outline76 = GeneratedOutlineSupport1.outline76(str, "_", str2, ".jpg");
        try {
            if (!isAccountSetupCompleted()) {
                completeAccountSetupTasks();
            }
            CommsLogger commsLogger = LOG;
            commsLogger.d("[Sharing] Downloading file, display name: " + outline76);
            GetNodeExtendedResponse nodeExtendedResponse = getNodeExtendedResponse(str, str3);
            if (isNodeAvailable(nodeExtendedResponse)) {
                File createNewFileForName = createNewFileForName(outline76);
                String path = createNewFileForName.getPath();
                CommsLogger commsLogger2 = LOG;
                commsLogger2.d("[Sharing] File available for download, Storing output to: " + path);
                DownloadFileExtendedRequest downloadFileExtendedRequest = getDownloadFileExtendedRequest(str, createNewFileForName);
                downloadFileExtendedRequest.setOwnerId(str3);
                this.mCDSClient.downloadFileExtended(downloadFileExtendedRequest, createProgressListenerToListenOnDownloadCompletion(path, nodeExtendedResponse, cloudDriveFileDownloadListener));
                return;
            }
            throw new CloudDriveException("Download failed, status returned:" + nodeExtendedResponse.getStatus(), Constants.CDS_NODE_NOT_FOUND, SOURCE);
        } catch (CloudDriveException e) {
            LOG.e("[Sharing] The CDS client threw error during downloadSingleFileTask!", e);
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_DOWNLOAD_ERROR, e, e.getHttpCode(), e.getCode(), str);
            cloudDriveFileDownloadListener.onError(e);
        } catch (InterruptedException e2) {
            LOG.e("[Sharing] An interrupt event was encountered during downloadSingleFileTask!", e2);
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_DOWNLOAD_ERROR, e2, -9, INTERRUPT_CODE, str);
            cloudDriveFileDownloadListener.onError(e2);
        } catch (Exception e3) {
            CommsLogger commsLogger3 = LOG;
            commsLogger3.e("[Sharing] Download failed for " + outline76 + " - Exception occurred", e3);
            recordCDSGenericFailureMetric(MetricKeys.CLOUD_DRIVE_DOWNLOAD_ERROR, e3.getClass().getSimpleName(), e3, str, -1);
            cloudDriveFileDownloadListener.onError(e3);
        }
    }

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public AsyncTask<Void, Void, Void> downloadSingleThumbnail(@NonNull final String str, @NonNull final int i, @NonNull final String str2, @NonNull final CloudDriveFileDownloadListener cloudDriveFileDownloadListener) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.alexa.sharing.media.transmitter.FileTransmitter.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                FileTransmitter.this.downloadSingleThumbnailTask(str, i, str2, cloudDriveFileDownloadListener);
                return null;
            }
        };
    }

    void downloadSingleThumbnailTask(@NonNull String str, @NonNull int i, @NonNull String str2, @NonNull CloudDriveFileDownloadListener cloudDriveFileDownloadListener) {
        String str3 = str + "_" + i + ".jpg";
        try {
            LOG.d("[Sharing] Downloading file, display name: " + str3);
            GetNodeExtendedResponse nodeExtendedResponse = getNodeExtendedResponse(str, str2);
            if (isNodeAvailable(nodeExtendedResponse)) {
                File createNewFileForName = createNewFileForName(str3);
                LOG.d("[Sharing] Thumbnail file available for download, Storing output to: " + createNewFileForName.getPath());
                GetThumbnailExtendedRequest thumbnailExtendedRequest = getThumbnailExtendedRequest(str, createNewFileForName);
                thumbnailExtendedRequest.setOwnerId(str2);
                thumbnailExtendedRequest.setViewBox(i);
                this.mCDSClient.getThumbnailExtended(thumbnailExtendedRequest, createProgressListenerToListenOnDownloadCompletion(createNewFileForName.getPath(), nodeExtendedResponse, cloudDriveFileDownloadListener));
                return;
            }
            throw new CloudDriveException("Thumbnail Download failed, status returned:" + nodeExtendedResponse.getStatus(), Constants.CDS_NODE_NOT_FOUND, SOURCE);
        } catch (CloudDriveException e) {
            LOG.e("[Sharing] The CDS client threw error during downloadSingleThumbnailTask!", e);
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_THUMBNAIL_ERROR, e, e.getHttpCode(), e.getCode(), str);
            cloudDriveFileDownloadListener.onError(e);
        } catch (InterruptedException e2) {
            LOG.e("[Sharing] An interrupt event was encountered during downloadSingleThumbnailTask!", e2);
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_THUMBNAIL_ERROR, e2, -9, INTERRUPT_CODE, str);
            cloudDriveFileDownloadListener.onError(e2);
        } catch (Exception e3) {
            LOG.e("[Sharing] Download failed for " + str3 + " - Exception occurred", e3);
            recordCDSGenericFailureMetric(MetricKeys.CLOUD_DRIVE_THUMBNAIL_ERROR, e3.getClass().getSimpleName(), e3, str, -1);
            cloudDriveFileDownloadListener.onError(e3);
        }
    }

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public Observable<CloudDriveService.DownloadResponse> downloadThumbnail(@NonNull final String str, @NonNull final int i, @NonNull final String str2) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$FileTransmitter$z9_H7-jfPApxK9GU7YQPYu0PsAg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FileTransmitter.this.lambda$downloadThumbnail$5$FileTransmitter(str, i, str2, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    public boolean fileExists(String str) {
        return new File(getLocalFilePath(str)).exists();
    }

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public String getAlexaFolderId() {
        return this.mAlexaFolderNodeId;
    }

    @Nullable
    UserIdentity getCurrentUser() {
        return this.mCurrentUser;
    }

    DownloadFileExtendedRequest getDownloadFileExtendedRequest(String str, File file) throws FileNotFoundException {
        return new DownloadFileExtendedRequest(str, new FileOutputStream(file));
    }

    public String getLocalFilePath(String str) {
        return this.mContext.getFilesDir().getPath() + "/" + str;
    }

    GetNodeExtendedResponse getNodeExtendedResponse(String str, String str2) throws InterruptedException, CloudDriveException {
        GetNodeExtendedRequest getNodeExtendedRequest = new GetNodeExtendedRequest(str);
        getNodeExtendedRequest.setOwnerId(str2);
        return this.mCDSClient.getNodeExtended(getNodeExtendedRequest);
    }

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public String getRootNodeId() {
        return this.mRootNodeId;
    }

    GetThumbnailExtendedRequest getThumbnailExtendedRequest(String str, File file) throws FileNotFoundException {
        return new GetThumbnailExtendedRequest(str, new FileOutputStream(file));
    }

    @VisibleForTesting
    UploadFileExtendedRequest getUploadFileExtendedRequest(@NonNull File file, @NonNull String str, @NonNull FileInputStream fileInputStream) {
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

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public void init(@NonNull SetupCompletedListener setupCompletedListener) {
        try {
            updateCommsUser();
            validateCurrentUser();
            startSetupAccountAsyncTask(setupCompletedListener).execute(new Void[0]);
        } catch (AlexaSharingSDKException e) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Sharing] Failed to complete setup account tasks -- ");
            outline107.append(e.getErrorMessage());
            commsLogger.e(outline107.toString());
            setupCompletedListener.onError(e);
        }
    }

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public Completable initialize() {
        LOG.d("[Sharing] Initializing a FileTransmitter");
        return Completable.create(new Completable.CompletableOnSubscribe() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$FileTransmitter$AG9Hx_pjGPDwvZsdM0vCwSA07rI
            @Override // rx.functions.Action1
            public final void call(Completable.CompletableSubscriber completableSubscriber) {
                FileTransmitter.this.lambda$initialize$2$FileTransmitter(completableSubscriber);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @VisibleForTesting
    boolean isAccountSetupCompleted() {
        return !TextUtils.isEmpty(this.mAlexaFolderNodeId) && !TextUtils.isEmpty(this.mRootNodeId) && !TextUtils.isEmpty(this.mAlexaFolderNodeId) && this.sharedPreferenceUtils.getBooleanPreferenceFromSharedPrefs(this.mContext, "CDS_ACCOUNT_SETUP_SUCCESSFUL", false);
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
        init(new SetupCompletedListener() { // from class: com.amazon.alexa.sharing.media.transmitter.FileTransmitter.1
            @Override // com.amazon.alexa.sharing.media.model.SetupCompletedListener
            public void onError(Exception exc) {
                completableSubscriber.onError(exc);
            }

            @Override // com.amazon.alexa.sharing.media.model.SetupCompletedListener
            public void onSuccess() {
                FileTransmitter.this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_SETUP_SUCCESS, Collections.EMPTY_MAP);
                completableSubscriber.onCompleted();
            }
        });
    }

    public /* synthetic */ void lambda$observeUserChanges$1$FileTransmitter(Message message) {
        userChangedCallback(this.mIdentityService.getUser(SOURCE));
    }

    @VisibleForTesting
    void observeUserChanges() {
        this.mEventBus.getSubscriber().subscribeFilter($$Lambda$FileTransmitter$B3PvRSun7znJUfZnR0382altvWI.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$FileTransmitter$9ayoGyGyZiU0M8wQL0WTxDTj0Dk
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(".");
        outline107.append(contentProperties.getExtension());
        cloudDriveFileDownloadListener.onSuccess(str, mediaTypeForContent, outline107.toString(), intValue, intValue2);
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
    CloudDriveService.UploadResponse processUploadResponseOnSuccess(@NonNull String str, @NonNull String str2) {
        CloudDriveService.UploadResponse uploadResponse = new CloudDriveService.UploadResponse();
        uploadResponse.setId(str);
        uploadResponse.setOwnerID(str2);
        return uploadResponse;
    }

    boolean recordCDSDownloadExceptionMetric(Exception exc, String str) {
        boolean equals = exc instanceof CloudDriveException ? Constants.CDS_NODE_NOT_FOUND.equals(((CloudDriveException) exc).getCode()) : false;
        if (equals) {
            this.metricsEmitter.recordNonOccurrenceMetric(str, Collections.EMPTY_MAP);
        } else {
            this.metricsEmitter.recordOccurrenceMetric(str, Collections.EMPTY_MAP);
        }
        return equals;
    }

    @VisibleForTesting
    String[] recordCDSFailureMetrics(@NonNull String str, @NonNull Exception exc, @NonNull int i, @NonNull String str2, @NonNull String str3) {
        return new String[]{recordCDSGenericFailureMetric(str, exc.getClass().getSimpleName(), exc, str3, i), recordCDSGenericFailureMetric(str, str2, exc, str3, i), recordCDSGenericFailureMetric(str, String.valueOf(i), exc, str3, i)};
    }

    @VisibleForTesting
    String recordCDSGenericFailureMetric(@NonNull String str, @NonNull String str2, @NonNull Throwable th, @NonNull String str3, @NonNull int i) {
        HashMap hashMap = new HashMap();
        String outline75 = GeneratedOutlineSupport1.outline75(str, ".", str2);
        hashMap.put("sourceContext", th.getClass().getSimpleName());
        hashMap.put("requestId", str3);
        hashMap.put("statusCode", Integer.valueOf(i));
        return this.metricsEmitter.recordOccurrenceMetric(outline75, hashMap);
    }

    protected boolean resolveParentNodeIdNotFoundError(@NonNull File file, @NonNull String str, @NonNull CloudDriveFileUploadListener cloudDriveFileUploadListener) {
        try {
            resetCloudNodeId();
            uploadFileExtended(file, str, cloudDriveFileUploadListener);
            return true;
        } catch (CloudDriveException e) {
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_UPLOAD_ERROR, e, e.getHttpCode(), e.getCode(), this.directedId);
            LOG.e("[Sharing] The CDS client threw error during resolveParentNodeIdNotFoundError!", e);
            return false;
        } catch (Exception e2) {
            recordCDSGenericFailureMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_ERROR, e2.getClass().getSimpleName(), e2, this.directedId, -1);
            CommsLogger commsLogger = LOG;
            commsLogger.e("[Sharing] Upload failed for file " + str + " - Exception occurred", e2);
            return false;
        }
    }

    boolean retrieveAlexaFolderNodeId() throws InterruptedException, CloudDriveException {
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
                LOG.e("[Sharing] The CDS client threw error during retrieveAlexaFolderNodeId!", e);
                int httpCode = e.getHttpCode();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetAlexa.");
                outline107.append(e.getCode());
                recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_SETUP_ERROR, e, httpCode, outline107.toString(), e.getLogref());
                throw e;
            } catch (InterruptedException e2) {
                LOG.e("[Sharing] An interrupt event was encountered during retrieveAlexaFolderNodeId!", e2);
                recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_SETUP_ERROR, e2, -9, "GetAlexa.interrupt", INTERRUPT_CODE);
                throw e2;
            }
        }
        return !TextUtils.isEmpty(this.mAlexaFolderNodeId);
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
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.alexa.sharing.media.transmitter.FileTransmitter.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                FileTransmitter.this.startSetupAccountTask(setupCompletedListener);
                return null;
            }
        };
    }

    void startSetupAccountTask(@NonNull SetupCompletedListener setupCompletedListener) {
        if (this.mCDSClient == null) {
            setupCompletedListener.onError(new CloudDriveException("[Sharing] CDS Account Setup failure, CDS Client cannot be null."));
            return;
        }
        try {
            completeAccountSetupTasks();
            setupCompletedListener.onSuccess();
        } catch (CloudDriveException e) {
            setupCompletedListener.onError(e);
        }
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

    protected boolean tryResolveUploadConflictError(@NonNull ConflictError conflictError, @NonNull CloudDriveFileUploadListener cloudDriveFileUploadListener) {
        boolean z = conflictError.getCode().equals("NAME_ALREADY_EXISTS") || conflictError.getCode().equals("ID_ALREADY_EXISTS") || conflictError.getCode().equals("MD5_DUPLICATE");
        if (z) {
            cloudDriveFileUploadListener.onSuccess(conflictError.getNodeId(), getCurrentUser().getId());
        } else {
            cloudDriveFileUploadListener.onError(conflictError);
        }
        return z;
    }

    void uploadFileExtended(@NonNull File file, @NonNull final String str, @NonNull CloudDriveFileUploadListener cloudDriveFileUploadListener) throws Exception {
        if (!isAccountSetupCompleted()) {
            completeAccountSetupTasks();
        }
        UploadFileExtendedResponse uploadFileExtended = this.mCDSClient.uploadFileExtended(getUploadFileExtendedRequest(file, str, wrapFileInputStream(file)), new ProgressListener() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$FileTransmitter$9TfOTeT55qfemujhxB5_WnZe0_Q
            @Override // com.amazon.clouddrive.handlers.ProgressListener
            public final void onProgress(long j, long j2) {
                FileTransmitter.lambda$uploadFileExtended$6(str, j, j2);
            }
        });
        cloudDriveFileUploadListener.onSuccess(uploadFileExtended.getId(), uploadFileExtended.getOwnerId());
    }

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public Observable<CloudDriveService.UploadResponse> uploadImage(@NonNull final Uri uri) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$FileTransmitter$xnKsl0Dvskn2iFSpeJ5U1uABYs8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FileTransmitter.this.lambda$uploadImage$3$FileTransmitter(uri, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.sharing.media.model.CloudDriveService
    public AsyncTask<Void, Void, Void> uploadSingleFile(@NonNull final Uri uri, @NonNull final CloudDriveFileUploadListener cloudDriveFileUploadListener) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.alexa.sharing.media.transmitter.FileTransmitter.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                FileTransmitter.this.uploadSingleFileTask(uri, cloudDriveFileUploadListener);
                return null;
            }
        };
    }

    void uploadSingleFileTask(@NonNull Uri uri, @NonNull CloudDriveFileUploadListener cloudDriveFileUploadListener) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Sharing] Staging a file from: ");
        outline107.append(uri.getPath());
        commsLogger.d(outline107.toString());
        File copyContentStreamToCache = this.sharedPreferenceUtils.copyContentStreamToCache(uri, this.mContext);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("");
        outline1072.append(new Random().nextInt(100));
        outline1072.append(copyContentStreamToCache.getName());
        String sb = outline1072.toString();
        try {
            try {
                uploadFileExtended(copyContentStreamToCache, sb, cloudDriveFileUploadListener);
            } catch (ConflictError e) {
                tryResolveUploadConflictError(e, cloudDriveFileUploadListener);
            } catch (InvalidParameter e2) {
                recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_UPLOAD_ERROR, e2, e2.getHttpCode(), e2.getCode(), this.directedId);
                if (!resolveParentNodeIdNotFoundError(copyContentStreamToCache, sb, cloudDriveFileUploadListener)) {
                    cloudDriveFileUploadListener.onError(e2);
                }
            }
        } catch (CloudDriveException e3) {
            LOG.e("[Sharing] The CDS client threw error during uploadSingleFileTask!", e3);
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_UPLOAD_ERROR, e3, e3.getHttpCode(), e3.getCode(), this.directedId);
            cloudDriveFileUploadListener.onError(e3);
        } catch (InterruptedException e4) {
            LOG.e("[Sharing] An interrupt event was encountered during uploadSingleFileTask!", e4);
            recordCDSFailureMetrics(MetricKeys.CLOUD_DRIVE_UPLOAD_ERROR, e4, -9, INTERRUPT_CODE, this.directedId);
            cloudDriveFileUploadListener.onError(e4);
        } catch (Exception e5) {
            recordCDSGenericFailureMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_ERROR, e5.getClass().getSimpleName(), e5, this.directedId, -1);
            CommsLogger commsLogger2 = LOG;
            commsLogger2.e("[Sharing] Upload failed for file " + sb + " - Exception occurred", e5);
            cloudDriveFileUploadListener.onError(e5);
        }
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
}
