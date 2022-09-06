package com.amazon.dee.app.elements.bridges;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.photos.PhotosFeatureGuardian;
import com.amazon.alexa.photos.UploadBundleManager;
import com.amazon.alexa.photos.api.PhotosChooser;
import com.amazon.alexa.photos.api.PhotosUploader;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.dee.app.elements.AlexaMobileAndroidPackage;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImage;
import com.amazon.dee.app.services.alexadevicebackground.InvalidValueException;
import com.amazon.dee.app.services.alexadevicebackground.MissingKeyException;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageActivity;
import com.amazonaws.com.google.gson.Gson;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import dagger.Lazy;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.lang.ref.WeakReference;
import java.util.HashMap;
@ReactModule(name = "AlexaDeviceBackgroundImage")
/* loaded from: classes12.dex */
public class AlexaDeviceBackgroundImageModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    static final String ERROR_INTERRUPTED = "New image selector opened";
    static final String ERROR_NO_NODE_ID_CANT_REMOVE = "Could not remove photo with no node ID";
    static final String ERROR_NO_NODE_ID_REMOVED = "Did not get node id for removed file";
    static final String ERROR_NO_NODE_ID_UPLOADED = "Did not get node id for uploaded file";
    static final String ERROR_UNKNOWN = "Unknown";
    static final String MODULE_NAME = "BackgroundImageSelectorModule";
    static final String UPLOADS_IN_PROGRESS = "Uploads already in progress";
    static final String USER_CANCELED = "User Canceled";
    private final WeakReference<Activity> activityWeakReference;
    @VisibleForTesting
    String alexaAlbumNodeId;
    private final Lazy<AMPDInformationProvider> ampdInformationProvider;
    private final FeatureServiceV2.FeatureUpdateListener autoSaveFeatureUpdateListener;
    private final Lazy<CloudDriveMetrics> cloudDriveMetrics;
    private final CollectionsFactory collectionsFactory;
    private final CompositeDisposable disposable;
    private final Lazy<EventBus> eventBus;
    private final LazyComponent<FeatureServiceV2> featureServiceV2Lazy;
    private final Lazy<IdentityService> identityService;
    private boolean isAutosaveFeatureEnabled;
    private boolean isManualUploadEnabled;
    private final FeatureServiceV2.FeatureUpdateListener manualFeatureUpdateListener;
    private final PhotosChooser photosChooser;
    private final Lazy<PhotosFeatureGuardian> photosFeatureGuardian;
    private final Lazy<PhotosUploader> photosUploader;
    private Promise removeImagePromise;
    @VisibleForTesting
    Promise selectImagePromise;
    private final Lazy<TaskManager> taskManager;
    private final Lazy<UploadBundleManager> uploadBundleManager;
    private static final String TAG = Log.tag(AlexaDeviceBackgroundImageModule.class);
    static final String METRICS_TAG = AlexaDeviceBackgroundImageModule.class.getSimpleName();

    public AlexaDeviceBackgroundImageModule(@NonNull ReactApplicationContext reactApplicationContext, @NonNull CollectionsFactory collectionsFactory, @NonNull Lazy<IdentityService> lazy, @NonNull LazyComponent<FeatureServiceV2> lazyComponent, @NonNull PhotosChooser photosChooser, @NonNull Lazy<PhotosUploader> lazy2, @NonNull WeakReference<Activity> weakReference, @NonNull Lazy<UploadBundleManager> lazy3, @NonNull Lazy<EventBus> lazy4, @NonNull Lazy<TaskManager> lazy5, @NonNull Lazy<PhotosFeatureGuardian> lazy6, @NonNull Lazy<AMPDInformationProvider> lazy7, @NonNull Lazy<CloudDriveMetrics> lazy8) {
        super(reactApplicationContext);
        this.disposable = new CompositeDisposable();
        this.isAutosaveFeatureEnabled = false;
        this.isManualUploadEnabled = false;
        this.alexaAlbumNodeId = null;
        this.activityWeakReference = weakReference;
        this.collectionsFactory = collectionsFactory;
        this.photosUploader = lazy2;
        this.photosChooser = photosChooser;
        this.eventBus = lazy4;
        this.uploadBundleManager = lazy3;
        this.taskManager = lazy5;
        this.identityService = lazy;
        this.featureServiceV2Lazy = lazyComponent;
        this.photosFeatureGuardian = lazy6;
        this.ampdInformationProvider = lazy7;
        this.cloudDriveMetrics = lazy8;
        reactApplicationContext.addActivityEventListener(this);
        this.autoSaveFeatureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaDeviceBackgroundImageModule$PwnCLGje5_fB-HPPojS5bqSXNQY
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public final void onFeatureUpdate(String str) {
                AlexaDeviceBackgroundImageModule.this.lambda$new$0$AlexaDeviceBackgroundImageModule(str);
            }
        };
        this.manualFeatureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaDeviceBackgroundImageModule$sXrHVjk5ZG0Nyr24e4-x_Kv1w5Q
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public final void onFeatureUpdate(String str) {
                AlexaDeviceBackgroundImageModule.this.lambda$new$1$AlexaDeviceBackgroundImageModule(str);
            }
        };
        setupUserStatusUploadBundleObservers();
    }

    private void createBundle() {
        this.uploadBundleManager.mo358get().createAndSaveBundle();
    }

    private void destroyBundle() {
        this.taskManager.mo358get().getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaDeviceBackgroundImageModule$JFqm6JwUJ3FXROA7NG-XAWGvSaA
            @Override // java.lang.Runnable
            public final void run() {
                AlexaDeviceBackgroundImageModule.this.useBundleAndDestroy();
            }
        });
    }

    @Nullable
    private Activity getActivity() {
        Activity currentActivity = getCurrentActivity();
        return currentActivity == null ? this.activityWeakReference.get() : currentActivity;
    }

    @Nullable
    private String getCurrentAccountId() {
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (user != null) {
            return user.getDirectedId();
        }
        return null;
    }

    private void handleLogout() {
        this.alexaAlbumNodeId = null;
        this.disposable.clear();
        this.photosUploader.mo358get().cancelUploads();
        destroyBundle();
        Promise promise = this.selectImagePromise;
        if (promise != null) {
            promise.reject("Error", ERROR_INTERRUPTED);
            this.selectImagePromise = null;
        }
        resetFeatureFlags();
    }

    private boolean hasPhotosFeaturesChanged() {
        if (this.isAutosaveFeatureEnabled != isFeatureEnabled("AMAZON_PHOTOS_ALEXA_APP_AUTO_SAVE_ANDROID")) {
            recordWeblabChangeMetric(PhotosMetricsConstants.AUTOSAVE_SUFFIX);
            return true;
        } else if (this.isManualUploadEnabled == isFeatureEnabled("AMAZON_PHOTOS_ALEXA_APP_MANUAL_UPLOAD_ANDROID")) {
            return false;
        } else {
            recordWeblabChangeMetric(PhotosMetricsConstants.MANUAL_SUFFIX);
            return true;
        }
    }

    private boolean isFeatureEnabled(@NonNull String str) {
        return this.featureServiceV2Lazy.mo10268get().hasAccess(str, false);
    }

    private void recordSetupBundleMetric(@NonNull String str) {
        if (isFeatureEnabled("AMAZON_PHOTOS_ALEXA_APP_AUTO_SAVE_ANDROID")) {
            String str2 = METRICS_TAG;
            this.cloudDriveMetrics.mo358get().recordEvent(str2, "InitializingUploadBundle_AUTOSAVE" + str);
        } else if (!isFeatureEnabled("AMAZON_PHOTOS_ALEXA_APP_MANUAL_UPLOAD_ANDROID")) {
        } else {
            String str3 = METRICS_TAG;
            this.cloudDriveMetrics.mo358get().recordEvent(str3, "InitializingUploadBundle_MANUAL" + str);
        }
    }

    private void recordWeblabChangeMetric(@NonNull String str) {
        String str2 = METRICS_TAG;
        this.cloudDriveMetrics.mo358get().recordEvent(str2, PhotosMetricsConstants.USER_WEBLAB_TREATMENT_CHANGED + str);
    }

    private void resetFeatureFlags() {
        this.isAutosaveFeatureEnabled = false;
        this.isManualUploadEnabled = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupBundleIfEnabled() {
        if (this.photosFeatureGuardian.mo358get().isUploaderV2Available()) {
            createBundle();
            disableAutoSaveForAMPD();
        }
    }

    private void setupUserStatusUploadBundleObservers() {
        this.eventBus.mo358get().getSubscriber().subscribeFilter(new EventTypeMessageFilter(AlexaMobileAndroidPackage.NATIVE_MODULES_CREATED_EVENT), new MessageHandler() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaDeviceBackgroundImageModule$rsOu88HMC5BM3YnFuxTQkfoYEmA
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaDeviceBackgroundImageModule.this.lambda$setupUserStatusUploadBundleObservers$2$AlexaDeviceBackgroundImageModule(message);
            }
        });
        this.eventBus.mo358get().getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaDeviceBackgroundImageModule$HBmMhfWQr1gVLDAt9I_ERe_JsZU
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaDeviceBackgroundImageModule.this.lambda$setupUserStatusUploadBundleObservers$3$AlexaDeviceBackgroundImageModule(message);
            }
        });
        this.featureServiceV2Lazy.mo10268get().observeFeature("AMAZON_PHOTOS_ALEXA_APP_AUTO_SAVE_ANDROID", this.autoSaveFeatureUpdateListener);
        this.featureServiceV2Lazy.mo10268get().observeFeature("AMAZON_PHOTOS_ALEXA_APP_MANUAL_UPLOAD_ANDROID", this.manualFeatureUpdateListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void useBundleAndDestroy() {
        this.uploadBundleManager.mo358get().destroyUploaderBundle();
    }

    @VisibleForTesting
    void cacheFeatureFlags() {
        this.isAutosaveFeatureEnabled = isFeatureEnabled("AMAZON_PHOTOS_ALEXA_APP_AUTO_SAVE_ANDROID");
        this.isManualUploadEnabled = isFeatureEnabled("AMAZON_PHOTOS_ALEXA_APP_MANUAL_UPLOAD_ANDROID");
    }

    @ReactMethod
    public void cancelUploads() {
        this.photosUploader.mo358get().cancelUploads();
    }

    @VisibleForTesting
    void destroyAndSetupBundle() {
        this.taskManager.mo358get().getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaDeviceBackgroundImageModule$rXIUe899dJpcbA0Z0g1Yn4UqUA0
            @Override // java.lang.Runnable
            public final void run() {
                AlexaDeviceBackgroundImageModule.this.lambda$destroyAndSetupBundle$4$AlexaDeviceBackgroundImageModule();
            }
        });
    }

    @VisibleForTesting
    void disableAutoSaveForAMPD() {
        if (this.ampdInformationProvider.mo358get().isAMPDDevice()) {
            UploadBundleManager.UploaderStatus currentUploaderStatus = this.uploadBundleManager.mo358get().getCurrentUploaderStatus();
            if (!(currentUploaderStatus != null && currentUploaderStatus.isAutoSaveEnabled())) {
                return;
            }
            this.cloudDriveMetrics.mo358get().recordEvent(METRICS_TAG, AlexaMetricsConstants.MetricEvents.AUTO_SAVE_WAS_ENABLED_FOR_AMPD);
            this.photosUploader.mo358get().setAutoSaveEnabled(false);
        }
    }

    @ReactMethod
    public void getAlbumId(@NonNull final Promise promise) {
        Preconditions.checkNotNull(promise, "Promise must not be null");
        CompositeDisposable compositeDisposable = this.disposable;
        Single<String> observeOn = this.photosUploader.mo358get().createAlexaAlbum().subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
        promise.getClass();
        Consumer<? super String> consumer = new Consumer() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$JXMiO5RS-1OjCh6hdfNXQ8VBx2E
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((String) obj);
            }
        };
        promise.getClass();
        compositeDisposable.add(observeOn.subscribe(consumer, new Consumer() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$uy64BVvzETLGSEBSP5-7B5ZCU4o
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.reject((Throwable) obj);
            }
        }));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getUploaderStatus(@NonNull Promise promise) {
        Preconditions.checkNotNull(promise, "Promise must not be null");
        UploadBundleManager.UploaderStatus currentUploaderStatus = this.uploadBundleManager.mo358get().getCurrentUploaderStatus();
        if (currentUploaderStatus != null) {
            promise.resolve(new Gson().toJson(currentUploaderStatus));
            return;
        }
        this.cloudDriveMetrics.mo358get().recordEvent(METRICS_TAG, PhotosMetricsConstants.UPLOADER_STATUS_UNINITIALIZED);
        promise.reject(new NullPointerException("Uploader status is not initialized"));
    }

    @VisibleForTesting
    void handleUserChange() {
        if (this.identityService.mo358get().getUser(TAG) != null) {
            this.cloudDriveMetrics.mo358get().recordEvent(METRICS_TAG, PhotosMetricsConstants.USER_IDENTITY_CHANGED_SIGN_IN);
            return;
        }
        this.cloudDriveMetrics.mo358get().recordEvent(METRICS_TAG, PhotosMetricsConstants.USER_IDENTITY_CHANGED_SIGN_OUT);
        handleLogout();
    }

    @ReactMethod
    public void initiateSelection(@NonNull String str, @Nullable Promise promise) {
        Promise promise2 = this.selectImagePromise;
        if (promise2 != null) {
            promise2.reject("Error", ERROR_INTERRUPTED);
            return;
        }
        this.selectImagePromise = promise;
        this.alexaAlbumNodeId = str;
        if (!this.photosFeatureGuardian.mo358get().isAutosaveFeatureAvailable() && this.photosUploader.mo358get().isUploadInProgress()) {
            this.selectImagePromise.reject("Error", UPLOADS_IN_PROGRESS);
            this.selectImagePromise = null;
            return;
        }
        Activity activity = getActivity();
        if (activity != null) {
            this.photosChooser.choosePhotos(activity, 12);
            return;
        }
        Promise promise3 = this.selectImagePromise;
        if (promise3 == null) {
            return;
        }
        promise3.reject("Error", ERROR_INTERRUPTED);
    }

    @ReactMethod
    public void isUploadInProgress(@NonNull Promise promise) {
        Preconditions.checkNotNull(promise, "Promise must not be null");
        promise.resolve(Boolean.valueOf(this.photosUploader.mo358get().isUploadInProgress()));
    }

    public /* synthetic */ void lambda$destroyAndSetupBundle$4$AlexaDeviceBackgroundImageModule() {
        if (!this.photosFeatureGuardian.mo358get().isAutosaveFeatureAvailable()) {
            useBundleAndDestroy();
        }
        setupBundleIfEnabled();
    }

    public /* synthetic */ void lambda$new$0$AlexaDeviceBackgroundImageModule(String str) {
        onFeatureUpdateEvent(str, "AMAZON_PHOTOS_ALEXA_APP_AUTO_SAVE_ANDROID");
    }

    public /* synthetic */ void lambda$new$1$AlexaDeviceBackgroundImageModule(String str) {
        onFeatureUpdateEvent(str, "AMAZON_PHOTOS_ALEXA_APP_MANUAL_UPLOAD_ANDROID");
    }

    public /* synthetic */ void lambda$setupUserStatusUploadBundleObservers$2$AlexaDeviceBackgroundImageModule(Message message) {
        setupBundleOnAppLaunch();
    }

    public /* synthetic */ void lambda$setupUserStatusUploadBundleObservers$3$AlexaDeviceBackgroundImageModule(Message message) {
        handleUserChange();
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (i == 7) {
            if (i2 != -1) {
                this.selectImagePromise = null;
            } else if (intent != null) {
                BackgroundImage backgroundImage = (BackgroundImage) intent.getParcelableExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL);
                if (backgroundImage == null) {
                    Log.e(TAG, ERROR_NO_NODE_ID_UPLOADED);
                    Promise promise = this.selectImagePromise;
                    if (promise == null) {
                        return;
                    }
                    promise.reject("Error", ERROR_NO_NODE_ID_UPLOADED);
                    this.selectImagePromise = null;
                    return;
                }
                Promise promise2 = this.selectImagePromise;
                if (promise2 == null) {
                    return;
                }
                promise2.resolve(toWritableMap(backgroundImage));
                this.selectImagePromise = null;
            } else {
                Promise promise3 = this.selectImagePromise;
                if (promise3 == null) {
                    return;
                }
                promise3.reject("Error", "Unknown");
                this.selectImagePromise = null;
            }
        } else if (i == 8) {
            if (i2 != -1) {
                this.removeImagePromise = null;
            } else if (intent != null) {
                BackgroundImage backgroundImage2 = (BackgroundImage) intent.getParcelableExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL);
                if (backgroundImage2 == null) {
                    Log.e(TAG, ERROR_NO_NODE_ID_REMOVED);
                    Promise promise4 = this.removeImagePromise;
                    if (promise4 == null) {
                        return;
                    }
                    promise4.reject("Error", ERROR_NO_NODE_ID_REMOVED);
                    this.removeImagePromise = null;
                    return;
                }
                Promise promise5 = this.removeImagePromise;
                if (promise5 == null) {
                    return;
                }
                promise5.resolve(toWritableMap(backgroundImage2));
                this.removeImagePromise = null;
            } else {
                Promise promise6 = this.removeImagePromise;
                if (promise6 == null) {
                    return;
                }
                promise6.reject("Error", "Unknown");
                this.removeImagePromise = null;
            }
        } else if (i != 12) {
        } else {
            if (i2 == -1) {
                if (intent != null && this.alexaAlbumNodeId != null) {
                    this.photosUploader.mo358get().upload(this.photosChooser.handleResponse(intent), this.alexaAlbumNodeId);
                    Promise promise7 = this.selectImagePromise;
                    if (promise7 == null) {
                        return;
                    }
                    promise7.resolve(true);
                    this.selectImagePromise = null;
                    return;
                }
                Promise promise8 = this.selectImagePromise;
                if (promise8 == null) {
                    return;
                }
                promise8.reject("Error", "Unknown");
                this.selectImagePromise = null;
                return;
            }
            Promise promise9 = this.selectImagePromise;
            if (promise9 == null) {
                return;
            }
            promise9.reject("Cancel", USER_CANCELED);
            this.selectImagePromise = null;
        }
    }

    @VisibleForTesting
    void onFeatureUpdateEvent(String str, @NonNull String str2) {
        if (!str2.equalsIgnoreCase(str) || Strings.isNullOrEmpty(getCurrentAccountId())) {
            return;
        }
        if (hasPhotosFeaturesChanged()) {
            this.cloudDriveMetrics.mo358get().recordEvent(METRICS_TAG, PhotosMetricsConstants.USER_WEBLAB_TREATMENT_CHANGED);
            cacheFeatureFlags();
            destroyAndSetupBundle();
            return;
        }
        recordSetupBundleMetric(PhotosMetricsConstants.FEATURE_UPDATE_SUFFIX);
        setupBundle();
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void removeBackgroundImage(ReadableMap readableMap, Promise promise) {
        Promise promise2 = this.removeImagePromise;
        if (promise2 != null) {
            promise2.reject("Error", ERROR_INTERRUPTED);
        }
        this.removeImagePromise = promise;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            try {
                BackgroundImage backgroundImage = new BackgroundImage(toHashMap(readableMap));
                if (!TextUtils.isEmpty(backgroundImage.getBackgroundImageID())) {
                    Intent intent = new Intent(currentActivity, AlexaDeviceBackgroundImageActivity.class);
                    intent.putExtra(AlexaDeviceBackgroundImageActivity.ACTION_MODE, 1);
                    intent.putExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL, backgroundImage);
                    intent.addFlags(536870912);
                    currentActivity.startActivityForResult(intent, 8);
                    return;
                }
                Log.e(TAG, ERROR_NO_NODE_ID_CANT_REMOVE);
                this.removeImagePromise.reject("Error", ERROR_NO_NODE_ID_CANT_REMOVE);
                this.removeImagePromise = null;
                return;
            } catch (InvalidValueException | MissingKeyException e) {
                this.removeImagePromise.reject("Error", e.toString());
                this.removeImagePromise = null;
                return;
            }
        }
        Log.e(TAG, "Cannot find current activity");
    }

    @ReactMethod
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public void selectBackgroundImage(ReadableMap readableMap, Promise promise) {
        Promise promise2 = this.selectImagePromise;
        if (promise2 != null) {
            promise2.reject("Error", ERROR_INTERRUPTED);
        }
        this.selectImagePromise = promise;
        try {
            BackgroundImage backgroundImage = new BackgroundImage(toHashMap(readableMap));
            if (getCurrentActivity() != null) {
                Activity currentActivity = getCurrentActivity();
                Intent intent = new Intent(currentActivity, AlexaDeviceBackgroundImageActivity.class);
                intent.putExtra(AlexaDeviceBackgroundImageActivity.ACTION_MODE, 0);
                intent.addFlags(536870912);
                intent.putExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL, backgroundImage);
                currentActivity.startActivityForResult(intent, 7);
                return;
            }
            Log.e(TAG, "Cannot find current activity");
            this.removeImagePromise.reject("Error", "Unknown");
            this.selectImagePromise = null;
        } catch (InvalidValueException | MissingKeyException e) {
            this.selectImagePromise.reject("Error", e.toString());
            this.selectImagePromise = null;
        }
    }

    @ReactMethod
    public void setAutoSaveEnabled(Boolean bool, @NonNull Promise promise) {
        Preconditions.checkNotNull(promise, "Promise must not be null");
        this.photosUploader.mo358get().setAutoSaveEnabled(bool.booleanValue());
        promise.resolve(bool);
    }

    @ReactMethod
    public void setCellularDataEnabled(Boolean bool, @NonNull Promise promise) {
        Preconditions.checkNotNull(promise, "Promise must not be null");
        this.photosUploader.mo358get().setCellularDataEnabled(bool.booleanValue());
        promise.resolve(bool);
    }

    @VisibleForTesting
    void setupBundle() {
        this.taskManager.mo358get().getExecutor(0).submit(new Runnable() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$AlexaDeviceBackgroundImageModule$r2sRw7MuJb4IEabmq1i9aujbSLU
            @Override // java.lang.Runnable
            public final void run() {
                AlexaDeviceBackgroundImageModule.this.setupBundleIfEnabled();
            }
        });
    }

    @VisibleForTesting
    void setupBundleOnAppLaunch() {
        if (this.identityService.mo358get().getUser(TAG) != null) {
            recordSetupBundleMetric(PhotosMetricsConstants.APP_LAUNCH_SUFFIX);
            cacheFeatureFlags();
            setupBundle();
        }
    }

    @ReactMethod
    public void shouldRequestAutoRevokeDisable(@NonNull Promise promise) {
        Activity activity;
        Preconditions.checkNotNull(promise, "Promise must not be null");
        promise.resolve(Boolean.valueOf((Build.VERSION.SDK_INT < 30 || (activity = getActivity()) == null) ? false : !activity.getPackageManager().isAutoRevokeWhitelisted()));
    }

    public HashMap<String, Object> toHashMap(ReadableMap readableMap) {
        HashMap<String, Object> hashMap = new HashMap<>();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            ReadableType type = readableMap.getType(nextKey);
            if (ReadableType.String == type) {
                hashMap.put(nextKey, readableMap.getString(nextKey));
            } else if (ReadableType.Array == type) {
                hashMap.put(nextKey, readableMap.getArray(nextKey));
            } else if (ReadableType.Boolean == type) {
                hashMap.put(nextKey, Boolean.valueOf(readableMap.getBoolean(nextKey)));
            } else if (ReadableType.Number == type) {
                hashMap.put(nextKey, Integer.valueOf(readableMap.getInt(nextKey)));
            } else if (ReadableType.Map == type) {
                hashMap.put(nextKey, toHashMap(readableMap.mo6945getMap(nextKey)));
            } else if (ReadableType.Null == type) {
                hashMap.put(nextKey, null);
            }
        }
        return hashMap;
    }

    public WritableMap toWritableMap(BackgroundImage backgroundImage) {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putString("deviceType", backgroundImage.getDeviceType());
        createMap.putString("deviceSerialNumber", backgroundImage.getDeviceSerialNumber());
        createMap.putString("softwareVersion", backgroundImage.getSoftwareVersion());
        createMap.putString(BackgroundImage.BACKGROUND_IMAGE_ID, backgroundImage.getBackgroundImageID());
        createMap.putString(BackgroundImage.BACKGROUND_IMAGE_TYPE, backgroundImage.getBackgroundImageType());
        createMap.putString(BackgroundImage.BACKGROUND_IMAGE_URL, backgroundImage.getBackgroundImageURL());
        return createMap;
    }
}
