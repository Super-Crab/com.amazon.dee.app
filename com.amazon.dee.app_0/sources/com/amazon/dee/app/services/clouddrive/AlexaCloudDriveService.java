package com.amazon.dee.app.services.clouddrive;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.presence.eventbus.EventMessageFilter;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.InvalidParameter;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.clouddrive.extended.model.MoveNodeToTrashExtendedRequest;
import com.amazon.clouddrive.extended.model.MoveNodeToTrashExtendedResponse;
import com.amazon.clouddrive.extended.model.SetupAccountRequest;
import com.amazon.clouddrive.extended.model.UploadFileExtendedRequest;
import com.amazon.clouddrive.extended.model.UploadFileExtendedResponse;
import com.amazon.clouddrive.handlers.AsyncHandler;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.model.CreateNodeRequest;
import com.amazon.clouddrive.model.CreateNodeResponse;
import com.amazon.clouddrive.model.GetNodeRequest;
import com.amazon.clouddrive.model.GetNodeResponse;
import com.amazon.clouddrive.model.ListChildrenRequest;
import com.amazon.clouddrive.model.ListChildrenResponse;
import com.amazon.clouddrive.model.ListNodesRequest;
import com.amazon.clouddrive.model.ListNodesResponse;
import com.amazon.clouddrive.model.Node;
import com.amazon.clouddrive.model.Suppress;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.clouddrive.CloudDriveService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.photos.PhotoService;
import com.amazon.dee.app.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import rx.Completable;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class AlexaCloudDriveService implements CloudDriveService {
    private static final String DEFAULT_IMAGE_EXTENSION = ".jpg";
    protected static final String SPACE_PROVIDED_KEY = "space_provided";
    private static final String TAG = Log.tag(AlexaCloudDriveService.class);
    @VisibleForTesting
    String alexaFolderName;
    private String alexaFolderNodeId;
    public AmazonCloudDriveExtendedClient amazonCloudDriveExtendedClient;
    private final Context appContext;
    private UserIdentity currentUser;
    private final EventBus eventBus;
    private final IdentityService identityService;
    private final MetricsService metricsService;
    private final Mobilytics mobilytics;
    private final PersistentStorage persistentStorage;
    private String rootNodeId;
    private final TaskManager taskManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class UploadFileAsync implements Runnable {
        private final Context mContext;
        private Uri mSourceUri;
        private final CloudDriveService.CloudDriveFileActionListener mUploadListener;

        public UploadFileAsync(Context context, Uri uri, CloudDriveService.CloudDriveFileActionListener cloudDriveFileActionListener) {
            this.mContext = context.getApplicationContext();
            this.mSourceUri = uri;
            this.mUploadListener = cloudDriveFileActionListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            HashMap outline133 = GeneratedOutlineSupport1.outline133("ownerIdentifier", "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
            AlexaCloudDriveService.this.metricsService.startTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_TIME, AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, outline133);
            AlexaCloudDriveService.this.metricsService.startTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_CDS_TIME, AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, outline133);
            try {
                File copyContentStreamToStagingFile = Utils.copyContentStreamToStagingFile(this.mContext, this.mSourceUri, String.valueOf(new Random().nextInt(100)) + this.mSourceUri.getLastPathSegment() + ".jpg");
                ArrayList arrayList = new ArrayList();
                if (!TextUtils.isEmpty(AlexaCloudDriveService.this.alexaFolderNodeId)) {
                    arrayList.add(AlexaCloudDriveService.this.alexaFolderNodeId);
                }
                UploadFileExtendedRequest uploadFileExtendedRequest = new UploadFileExtendedRequest(String.valueOf(new Random().nextInt(100)) + copyContentStreamToStagingFile.getName(), new FileInputStream(copyContentStreamToStagingFile), copyContentStreamToStagingFile.length());
                uploadFileExtendedRequest.setParents(arrayList);
                uploadFileExtendedRequest.setSuppress(Suppress.Deduplication);
                UploadFileExtendedResponse uploadFileExtended = AlexaCloudDriveService.this.amazonCloudDriveExtendedClient.uploadFileExtended(uploadFileExtendedRequest, new ProgressListener() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.UploadFileAsync.1
                    @Override // com.amazon.clouddrive.handlers.ProgressListener
                    public void onProgress(long j, long j2) {
                    }
                });
                if (uploadFileExtended != null) {
                    if (this.mUploadListener != null) {
                        AlexaCloudDriveService.this.getTempLinkForNodeId(uploadFileExtended.getId(), this.mUploadListener);
                    }
                } else if (this.mUploadListener != null) {
                    this.mUploadListener.onError(new CloudDriveException("Unable to upload file."));
                }
                if (copyContentStreamToStagingFile.delete()) {
                    return;
                }
                AlexaCloudDriveService.this.recordErrorEvent(AlexaMetricsConstants.MetricEvents.FAIL_TO_DELETE_UPLOAD_STAGING_FILE, String.format("Failed to delete staging file %s", copyContentStreamToStagingFile.getName()), null);
                Log.e(AlexaCloudDriveService.TAG, String.format("Failed to delete staging file %s", copyContentStreamToStagingFile.getName()));
            } catch (Exception e) {
                Log.e(AlexaCloudDriveService.TAG, e.getMessage());
                AlexaCloudDriveService.this.recordErrorEvent(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_ERROR, e.getMessage(), null);
                CloudDriveService.CloudDriveFileActionListener cloudDriveFileActionListener = this.mUploadListener;
                if (cloudDriveFileActionListener == null) {
                    Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: uploadFiled error- mUploadListener is null");
                } else {
                    cloudDriveFileActionListener.onError(e);
                }
            }
        }
    }

    public AlexaCloudDriveService(Context context, PersistentStorage.Factory factory, IdentityService identityService, AmazonCloudDriveExtendedClient amazonCloudDriveExtendedClient, Mobilytics mobilytics, MetricsService metricsService, TaskManager taskManager, EventBus eventBus) {
        this.appContext = context;
        this.amazonCloudDriveExtendedClient = amazonCloudDriveExtendedClient;
        this.identityService = identityService;
        this.mobilytics = mobilytics;
        this.metricsService = metricsService;
        this.taskManager = taskManager;
        this.eventBus = eventBus;
        this.persistentStorage = factory.create("service.clouddrive");
        if (identityService.isAuthenticated(TAG)) {
            this.currentUser = identityService.getUser(TAG);
        }
        observeUserChanges();
    }

    private void createAlexaFolder(@Nullable final CloudDriveService.InitCompletedListener initCompletedListener) {
        if (TextUtils.isEmpty(this.rootNodeId)) {
            return;
        }
        if (this.amazonCloudDriveExtendedClient == null) {
            recordErrorEvent(AlexaMetricsConstants.MetricEvents.INIT_AMAZON_DRIVE_ERROR, "There was an error initializing Amazon Drive. Could not create Alexa folder.", null);
            Log.e(TAG, "BackgroundImage: There was an error initializing Amazon Drive. Could not create Alexa folder.");
            return;
        }
        if (TextUtils.isEmpty(this.alexaFolderName)) {
            this.alexaFolderName = this.appContext.getResources().getString(R.string.amazon_drive_folder_name);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.rootNodeId);
        CreateNodeRequest createNodeRequest = new CreateNodeRequest(this.alexaFolderName, "FOLDER");
        createNodeRequest.withParents(arrayList);
        this.amazonCloudDriveExtendedClient.createNodeAsync(createNodeRequest, new AsyncHandler<CreateNodeRequest, CreateNodeResponse>() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.8
            @Override // com.amazon.clouddrive.handlers.AsyncHandler
            public void onCanceled(CreateNodeRequest createNodeRequest2) {
                CloudDriveService.InitCompletedListener initCompletedListener2 = initCompletedListener;
                if (initCompletedListener2 == null) {
                    Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: createAlexaFolder canceled- initListener is null");
                } else {
                    initCompletedListener2.onError(new IllegalStateException("Request was cancelled"));
                }
            }

            @Override // com.amazon.clouddrive.handlers.AsyncHandler
            public void onError(CreateNodeRequest createNodeRequest2, Exception exc) {
                String outline41 = GeneratedOutlineSupport1.outline41(exc, GeneratedOutlineSupport1.outline107("BackgroundImage: There was an error initializing Amazon Cloud Drive. Could not create Alexa folder.  "));
                AlexaCloudDriveService.this.recordErrorEvent(AlexaMetricsConstants.MetricEvents.INIT_AMAZON_DRIVE_ERROR, outline41, null);
                Log.e(AlexaCloudDriveService.TAG, outline41);
                CloudDriveService.InitCompletedListener initCompletedListener2 = initCompletedListener;
                if (initCompletedListener2 == null) {
                    Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: createAlexaFolder error- initListener is null");
                } else {
                    initCompletedListener2.onError(exc);
                }
            }

            @Override // com.amazon.clouddrive.handlers.AsyncHandler
            public void onSuccess(CreateNodeRequest createNodeRequest2, CreateNodeResponse createNodeResponse) {
                AlexaCloudDriveService.this.alexaFolderNodeId = createNodeResponse.getId();
                String str = AlexaCloudDriveService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BackgroundImage: Created Alexa Folder: ");
                outline107.append(AlexaCloudDriveService.this.alexaFolderNodeId);
                Log.i(str, outline107.toString());
                CloudDriveService.InitCompletedListener initCompletedListener2 = initCompletedListener;
                if (initCompletedListener2 == null) {
                    Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: createAlexaFolder success- initListener is null");
                } else {
                    initCompletedListener2.onSuccess();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findAlexaFolder(String str, @Nullable CloudDriveService.InitCompletedListener initCompletedListener) {
        if (!TextUtils.isEmpty(this.alexaFolderNodeId)) {
            return;
        }
        if (TextUtils.isEmpty(this.alexaFolderName)) {
            this.alexaFolderName = this.appContext.getResources().getString(R.string.amazon_drive_folder_name);
        }
        String str2 = null;
        Log.e(TAG, "BackgroundImage: findAlexaFolder");
        while (true) {
            try {
                ListChildrenRequest listChildrenRequest = new ListChildrenRequest(str);
                listChildrenRequest.setStartToken(str2);
                ListChildrenResponse listChildren = this.amazonCloudDriveExtendedClient.listChildren(listChildrenRequest);
                String nextToken = listChildren.getNextToken();
                Iterator<Node> it2 = listChildren.getData().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Node next = it2.next();
                    String str3 = "BackgroundImage: findAlexaFolder " + next.getName();
                    if (this.alexaFolderName.equals(next.getName())) {
                        this.alexaFolderNodeId = next.getId();
                        break;
                    }
                }
                if (nextToken == null || !TextUtils.isEmpty(this.alexaFolderNodeId)) {
                    break;
                }
                str2 = nextToken;
            } catch (CloudDriveException e) {
                Log.e(TAG, e, "Caught exception listing node contents.", new Object[0]);
                if (initCompletedListener != null) {
                    initCompletedListener.onError(e);
                    return;
                } else {
                    Log.e(TAG, "BackgroundImage: findAlexaFolder error- initListener is null");
                    return;
                }
            } catch (InterruptedException e2) {
                Log.e(TAG, "Interrupted while listing node contents.");
                if (initCompletedListener != null) {
                    initCompletedListener.onError(e2);
                    return;
                } else {
                    Log.e(TAG, "BackgroundImage: findAlexaFolder error- initListener is null");
                    return;
                }
            }
        }
        if (initCompletedListener != null) {
            initCompletedListener.onSuccess();
        } else {
            Log.e(TAG, "BackgroundImage: findAlexaFolder success- initListener is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getTempLinkForNodeId(final String str, final CloudDriveService.CloudDriveFileActionListener cloudDriveFileActionListener) {
        GetNodeRequest getNodeRequest = new GetNodeRequest(str);
        getNodeRequest.setTempLink(true);
        try {
            this.amazonCloudDriveExtendedClient.getNodeAsync(getNodeRequest, new AsyncHandler<GetNodeRequest, GetNodeResponse>() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.9
                @Override // com.amazon.clouddrive.handlers.AsyncHandler
                public void onCanceled(GetNodeRequest getNodeRequest2) {
                    CloudDriveService.CloudDriveFileActionListener cloudDriveFileActionListener2 = cloudDriveFileActionListener;
                    if (cloudDriveFileActionListener2 == null) {
                        Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: getTempLinkForNodeId canceled- listener is null");
                    } else {
                        cloudDriveFileActionListener2.onError(new CloudDriveException("Getting temp link request was cancelled"));
                    }
                }

                @Override // com.amazon.clouddrive.handlers.AsyncHandler
                public void onError(GetNodeRequest getNodeRequest2, Exception exc) {
                    CloudDriveService.CloudDriveFileActionListener cloudDriveFileActionListener2 = cloudDriveFileActionListener;
                    if (cloudDriveFileActionListener2 == null) {
                        Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: getTempLinkForNodeId error- listener is null");
                    } else {
                        cloudDriveFileActionListener2.onError(exc);
                    }
                }

                @Override // com.amazon.clouddrive.handlers.AsyncHandler
                public void onSuccess(GetNodeRequest getNodeRequest2, GetNodeResponse getNodeResponse) {
                    CloudDriveService.CloudDriveFileActionListener cloudDriveFileActionListener2 = cloudDriveFileActionListener;
                    if (cloudDriveFileActionListener2 == null) {
                        Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: getTempLinkForNodeId success- initListener is null");
                    } else {
                        cloudDriveFileActionListener2.onSuccess(str, getNodeResponse.getTempLink());
                    }
                }
            });
        } catch (Exception e) {
            android.util.Log.e(TAG, e.getMessage());
            if (cloudDriveFileActionListener != null) {
                cloudDriveFileActionListener.onError(new CloudDriveException(e.getMessage()));
            } else {
                Log.e(TAG, "BackgroundImage: getTempLinkForNodeId error- listener is null");
            }
        }
    }

    private void setupAccount(@NonNull final CloudDriveService.InitCompletedListener initCompletedListener) {
        SetupAccountRequest setupAccountRequest = new SetupAccountRequest();
        setupAccountRequest.setTermsOfUse("1.0.0");
        this.amazonCloudDriveExtendedClient.setupAccountAsync(setupAccountRequest, new AsyncHandler<SetupAccountRequest, Void>() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.5
            @Override // com.amazon.clouddrive.handlers.AsyncHandler
            public void onCanceled(SetupAccountRequest setupAccountRequest2) {
                AlexaCloudDriveService.this.recordErrorEvent(AlexaMetricsConstants.MetricEvents.SETUP_ACCOUNT_REQUEST_CANCEL_ERROR, "BackgroundImage: Setup account was cancelled", null);
                Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: Setup account was cancelled");
                initCompletedListener.onError(new CloudDriveException("Setup account was cancelled"));
            }

            @Override // com.amazon.clouddrive.handlers.AsyncHandler
            public void onError(SetupAccountRequest setupAccountRequest2, Exception exc) {
                AlexaCloudDriveService.this.recordErrorEvent(AlexaMetricsConstants.MetricEvents.SETUP_ACCOUNT_REQUEST_ERROR, GeneratedOutlineSupport1.outline41(exc, GeneratedOutlineSupport1.outline107("BackgroundImage: setupAccountRequest error ")), null);
                String str = AlexaCloudDriveService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BackgroundImage: setupAccountRequest error ");
                outline107.append(exc.getMessage());
                Log.e(str, outline107.toString());
                initCompletedListener.onError(exc);
            }

            @Override // com.amazon.clouddrive.handlers.AsyncHandler
            public void onSuccess(SetupAccountRequest setupAccountRequest2, Void r3) {
                Log.i(AlexaCloudDriveService.TAG, "BackgroundImage: setupAccountRequest completed successfully.");
                if (AlexaCloudDriveService.this.persistentStorage != null) {
                    AlexaCloudDriveService.this.persistentStorage.edit().set(AlexaCloudDriveService.SPACE_PROVIDED_KEY, true).commit();
                }
                AlexaCloudDriveService.this.getRootNodeId(initCompletedListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadFileAsync(Uri uri, CloudDriveService.CloudDriveFileActionListener cloudDriveFileActionListener) {
        this.taskManager.getExecutor(1).execute(new UploadFileAsync(this.appContext, uri, cloudDriveFileActionListener));
    }

    @Override // com.amazon.dee.app.services.photos.PhotoService
    public Observable<PhotoService.DeleteResponse> deleteImage(@NonNull final String str) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.dee.app.services.clouddrive.-$$Lambda$AlexaCloudDriveService$Quk6XagQ84kC-cuwrwOYl3SkZm4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlexaCloudDriveService.this.lambda$deleteImage$2$AlexaCloudDriveService(str, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService
    public String getAlexaFolderId() {
        return this.alexaFolderNodeId;
    }

    @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService
    public String getRootNodeId() {
        return this.rootNodeId;
    }

    @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService
    public void init(@NonNull CloudDriveService.InitCompletedListener initCompletedListener) {
        if (this.amazonCloudDriveExtendedClient == null) {
            recordErrorEvent(AlexaMetricsConstants.MetricEvents.INIT_AMAZON_DRIVE_ERROR, "There was an error initializing Amazon Drive.", null);
            Log.e(TAG, "BackgroundImage: There was an error initializing Amazon Drive.");
            initCompletedListener.onError(new CloudDriveException("There was an error initializing Amazon Drive."));
            return;
        }
        PersistentStorage persistentStorage = this.persistentStorage;
        if (persistentStorage != null && persistentStorage.getBoolean(SPACE_PROVIDED_KEY, false)) {
            if (!TextUtils.isEmpty(getRootNodeId()) && !TextUtils.isEmpty(getAlexaFolderId())) {
                initCompletedListener.onSuccess();
                return;
            } else {
                getRootNodeId(initCompletedListener);
                return;
            }
        }
        setupAccount(initCompletedListener);
    }

    @Override // com.amazon.dee.app.services.photos.PhotoService
    public Completable initialize() {
        return Completable.create(new Completable.CompletableOnSubscribe() { // from class: com.amazon.dee.app.services.clouddrive.-$$Lambda$AlexaCloudDriveService$tYsTXfK7KIWzVFjF7YuHOXoddSY
            @Override // rx.functions.Action1
            public final void call(Completable.CompletableSubscriber completableSubscriber) {
                AlexaCloudDriveService.this.lambda$initialize$0$AlexaCloudDriveService(completableSubscriber);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    public /* synthetic */ void lambda$deleteImage$2$AlexaCloudDriveService(String str, final Subscriber subscriber) {
        removeSingleFile(str, new CloudDriveService.CloudDriveFileActionListener() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.3
            @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService.CloudDriveFileActionListener
            public void onError(Exception exc) {
                subscriber.onError(exc);
            }

            @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService.CloudDriveFileActionListener
            public void onSuccess(@NonNull String str2, @Nullable String str3) {
                PhotoService.DeleteResponse deleteResponse = new PhotoService.DeleteResponse();
                deleteResponse.setId(str2);
                subscriber.onNext(deleteResponse);
                subscriber.onCompleted();
            }
        });
    }

    public /* synthetic */ void lambda$initialize$0$AlexaCloudDriveService(final Completable.CompletableSubscriber completableSubscriber) {
        init(new CloudDriveService.InitCompletedListener() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.1
            @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService.InitCompletedListener
            public void onError(Exception exc) {
                completableSubscriber.onError(exc);
            }

            @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService.InitCompletedListener
            public void onSuccess() {
                completableSubscriber.onCompleted();
            }
        });
    }

    public /* synthetic */ void lambda$observeUserChanges$3$AlexaCloudDriveService(Message message) {
        UserIdentity user = this.identityService.getUser(TAG);
        if (Objects.equals(user, this.currentUser)) {
            return;
        }
        PersistentStorage persistentStorage = this.persistentStorage;
        if (persistentStorage != null) {
            persistentStorage.edit().set(SPACE_PROVIDED_KEY, false).commit();
        }
        this.rootNodeId = null;
        this.alexaFolderNodeId = null;
        this.currentUser = user;
    }

    public /* synthetic */ void lambda$uploadImage$1$AlexaCloudDriveService(Uri uri, final Subscriber subscriber) {
        uploadSingleFile(uri, new CloudDriveService.CloudDriveFileActionListener() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.2
            @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService.CloudDriveFileActionListener
            public void onError(Exception exc) {
                subscriber.onError(exc);
            }

            @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService.CloudDriveFileActionListener
            public void onSuccess(@NonNull String str, @Nullable String str2) {
                PhotoService.UploadResponse uploadResponse = new PhotoService.UploadResponse();
                uploadResponse.setId(str);
                uploadResponse.setUrl(str2 + "/alt/thumb?viewBox=1280");
                subscriber.onNext(uploadResponse);
                subscriber.onCompleted();
            }
        });
    }

    void observeUserChanges() {
        this.eventBus.getSubscriber().subscribeFilter(new EventMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.services.clouddrive.-$$Lambda$AlexaCloudDriveService$S6w4Iuiq6QXrG9W6neY0Qv_RTz0
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaCloudDriveService.this.lambda$observeUserChanges$3$AlexaCloudDriveService(message);
            }
        });
    }

    protected void recordErrorEvent(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Event name: ", str, "\nSource context: ", str2, "\nExtras: ");
        outline116.append(str3);
        outline116.toString();
        MobilyticsOperationalEvent createOperationalEvent = this.mobilytics.createOperationalEvent(str, "error", AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        if (str2 != null) {
            createOperationalEvent.setSourceContext(str2);
        }
        if (str3 != null) {
            createOperationalEvent.setContentProvider(str3);
        }
        this.mobilytics.recordOperationalEvent(createOperationalEvent);
    }

    @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService
    public void removeSingleFile(@NonNull final String str, @NonNull final CloudDriveService.CloudDriveFileActionListener cloudDriveFileActionListener) {
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "BackgroundImage: Cannot remove photo without NodeId");
            cloudDriveFileActionListener.onError(new InvalidParameter("BackgroundImage: Cannot remove photo without NodeId"));
        } else if (this.amazonCloudDriveExtendedClient == null) {
            Log.e(TAG, "BackgroundImage: There was an error removing photo from Amazon Drive. Could not connect to Amazon Drive.");
            cloudDriveFileActionListener.onError(new CloudDriveException("BackgroundImage: There was an error removing photo from Amazon Drive. Could not connect to Amazon Drive."));
        } else {
            this.amazonCloudDriveExtendedClient.moveNodeToTrashExtendedAsync(new MoveNodeToTrashExtendedRequest(str), new AsyncHandler<MoveNodeToTrashExtendedRequest, MoveNodeToTrashExtendedResponse>() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.7
                @Override // com.amazon.clouddrive.handlers.AsyncHandler
                public void onCanceled(MoveNodeToTrashExtendedRequest moveNodeToTrashExtendedRequest) {
                    String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("BackgroundImage: The request to remove photo "), str, " was cancelled");
                    Log.e(AlexaCloudDriveService.TAG, outline91);
                    cloudDriveFileActionListener.onError(new CloudDriveException(outline91));
                }

                @Override // com.amazon.clouddrive.handlers.AsyncHandler
                public void onError(MoveNodeToTrashExtendedRequest moveNodeToTrashExtendedRequest, Exception exc) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BackgroundImage: There was an error removing photo ");
                    outline107.append(str);
                    outline107.append(exc.getMessage());
                    Log.e(AlexaCloudDriveService.TAG, outline107.toString());
                    cloudDriveFileActionListener.onError(exc);
                }

                @Override // com.amazon.clouddrive.handlers.AsyncHandler
                public void onSuccess(MoveNodeToTrashExtendedRequest moveNodeToTrashExtendedRequest, MoveNodeToTrashExtendedResponse moveNodeToTrashExtendedResponse) {
                    String str2 = AlexaCloudDriveService.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BackgroundImage: Successfully removed photo ");
                    outline107.append(moveNodeToTrashExtendedResponse.getId());
                    Log.i(str2, outline107.toString());
                    cloudDriveFileActionListener.onSuccess(moveNodeToTrashExtendedResponse.getId(), null);
                }
            });
        }
    }

    @Override // com.amazon.dee.app.services.photos.PhotoService
    public Observable<PhotoService.UploadResponse> uploadImage(@NonNull final Uri uri) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.dee.app.services.clouddrive.-$$Lambda$AlexaCloudDriveService$qS7DOzCzbTat0M1yQr2O-uxXt-g
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlexaCloudDriveService.this.lambda$uploadImage$1$AlexaCloudDriveService(uri, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService
    public void uploadSingleFile(@NonNull final Uri uri, @NonNull final CloudDriveService.CloudDriveFileActionListener cloudDriveFileActionListener) {
        if (this.amazonCloudDriveExtendedClient == null) {
            recordErrorEvent(AlexaMetricsConstants.MetricEvents.INIT_AMAZON_DRIVE_ERROR, "There was an error initializing Amazon Drive.", null);
            Log.e(TAG, "BackgroundImage: There was an error initializing Amazon Drive.");
            return;
        }
        CloudDriveService.InitCompletedListener initCompletedListener = new CloudDriveService.InitCompletedListener() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.4
            @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService.InitCompletedListener
            public void onError(Exception exc) {
                cloudDriveFileActionListener.onError(exc);
            }

            @Override // com.amazon.dee.app.services.clouddrive.CloudDriveService.InitCompletedListener
            public void onSuccess() {
                AlexaCloudDriveService.this.uploadFileAsync(uri, cloudDriveFileActionListener);
            }
        };
        if (TextUtils.isEmpty(this.alexaFolderNodeId)) {
            createAlexaFolder(initCompletedListener);
        } else {
            uploadFileAsync(uri, cloudDriveFileActionListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRootNodeId(final CloudDriveService.InitCompletedListener initCompletedListener) {
        ListNodesRequest listNodesRequest = new ListNodesRequest();
        listNodesRequest.setFilters("isRoot:true");
        Log.e(TAG, "BackgroundImage: getRootNodeId");
        this.amazonCloudDriveExtendedClient.listNodesAsync(listNodesRequest, new AsyncHandler<ListNodesRequest, ListNodesResponse>() { // from class: com.amazon.dee.app.services.clouddrive.AlexaCloudDriveService.6
            @Override // com.amazon.clouddrive.handlers.AsyncHandler
            public void onCanceled(ListNodesRequest listNodesRequest2) {
                Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: There was an error calling Amazon Drive. Could not get Root Node Id.");
                CloudDriveService.InitCompletedListener initCompletedListener2 = initCompletedListener;
                if (initCompletedListener2 == null) {
                    Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: findRootNodeID initCompletedListener is null");
                } else {
                    initCompletedListener2.onError(new IllegalStateException("Request was cancelled"));
                }
            }

            @Override // com.amazon.clouddrive.handlers.AsyncHandler
            public void onError(ListNodesRequest listNodesRequest2, Exception exc) {
                String str = AlexaCloudDriveService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BackgroundImage: There was an error calling Amazon Drive. Could not get Root Node Id. ");
                outline107.append(exc.getMessage());
                Log.e(str, outline107.toString());
                CloudDriveService.InitCompletedListener initCompletedListener2 = initCompletedListener;
                if (initCompletedListener2 == null) {
                    Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: findRootNodeID initCompletedListener is null");
                } else {
                    initCompletedListener2.onError(exc);
                }
            }

            @Override // com.amazon.clouddrive.handlers.AsyncHandler
            public void onSuccess(ListNodesRequest listNodesRequest2, ListNodesResponse listNodesResponse) {
                List<Node> data = listNodesResponse.getData();
                if (data.isEmpty()) {
                    AlexaCloudDriveService.this.rootNodeId = null;
                    Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: There was an error calling Amazon Drive. Could not get Root Node Id.");
                    CloudDriveService.InitCompletedListener initCompletedListener2 = initCompletedListener;
                    if (initCompletedListener2 == null) {
                        return;
                    }
                    initCompletedListener2.onError(new CloudDriveException("There was an error calling Amazon Drive. Could not get Root Node Id."));
                    return;
                }
                AlexaCloudDriveService.this.rootNodeId = data.get(0).getId();
                Log.e(AlexaCloudDriveService.TAG, "BackgroundImage: findRootNode success");
                AlexaCloudDriveService alexaCloudDriveService = AlexaCloudDriveService.this;
                alexaCloudDriveService.findAlexaFolder(alexaCloudDriveService.rootNodeId, initCompletedListener);
            }
        });
    }
}
