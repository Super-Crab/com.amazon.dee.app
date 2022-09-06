package com.amazon.alexa.sharing.media.picker;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.accessory.notificationpublisher.storage.LocalStorageHelper;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.api.exceptions.BridgeError;
import com.amazon.alexa.sharing.media.picker.util.ImageUtil;
import com.amazon.alexa.sharing.sharingsdk.photos.CommonContentProperties;
import com.amazon.alexa.sharing.util.DeviceInfoUtil;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.apache.commons.codec.language.bm.Languages;
/* loaded from: classes10.dex */
public class ImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final String AUTHORITY_SUFFIX = ".sharing.media.picker";
    private static final int CAMERA_PICKER_REQUEST = 51111;
    private static final String FILE_PREFIX = "file://";
    private static final int IMAGE_PICKER_REQUEST = 51110;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ImagePickerModule.class);
    private Uri mCameraCaptureURI;
    private String mCurrentMediaPath;
    private DeviceInfoUtil mDeviceInfoUtil;
    private ImageUtil mImageUtil;
    private boolean mIncludeExif;
    private boolean mIsMultiple;
    private String mMediaType;
    private ReactApplicationContext mReactContext;
    private ResultCollector mResultCollector;
    private boolean mUseFrontCamera;

    public ImagePickerModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, new ImageUtil(reactApplicationContext), new ResultCollector(), new DeviceInfoUtil());
    }

    private File createVideoFile() throws IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("video-");
        outline107.append(UUID.randomUUID().toString());
        File createMediaFile = createMediaFile(this.mReactContext.getFilesDir(), outline107.toString(), ".mp4");
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("file:");
        outline1072.append(createMediaFile.getAbsolutePath());
        this.mCurrentMediaPath = outline1072.toString();
        return createMediaFile;
    }

    private WritableMap getVideoData(String str, String str2) throws Exception {
        Bitmap validateVideo = validateVideo(str);
        long lastModified = new File(str).lastModified();
        WritableMap writableMap = getWritableMap();
        writableMap.putInt("width", validateVideo.getWidth());
        writableMap.putInt("height", validateVideo.getHeight());
        writableMap.putString("mime", str2);
        writableMap.putString("sourceURL", FILE_PREFIX + str);
        writableMap.putInt("size", (int) new File(str).length());
        writableMap.putString(RouteParameter.PATH, FILE_PREFIX + str);
        writableMap.putString(LocalStorageHelper.MODIFICATION_DATE_KEY, String.valueOf(lastModified));
        return writableMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$permissionsCheck$0(Promise promise, BridgeError bridgeError, Callable callable, BridgeError bridgeError2, int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            for (int i2 : iArr) {
                if (i2 == -1) {
                    promise.reject(bridgeError.code(), bridgeError.message());
                    return true;
                }
            }
            try {
                callable.call();
            } catch (Exception e) {
                promise.reject(bridgeError2.code(), e.getClass().getSimpleName(), e);
            }
        }
        return true;
    }

    private Bitmap validateVideo(String str) throws Exception {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime();
        if (frameAtTime != null) {
            return frameAtTime;
        }
        throw new Exception("Cannot retrieve video data");
    }

    File createImageFile() throws IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("image-");
        outline107.append(UUID.randomUUID().toString());
        File createMediaFile = createMediaFile(this.mReactContext.getFilesDir(), outline107.toString(), Constants.DEFAULT_IMAGE_EXTENSION);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("file:");
        outline1072.append(createMediaFile.getAbsolutePath());
        this.mCurrentMediaPath = outline1072.toString();
        return createMediaFile;
    }

    Intent createIntent(String str) {
        return new Intent(str);
    }

    File createMediaFile(File file, String str, String str2) throws IOException {
        if (!file.exists() && !file.isDirectory() && !file.mkdirs()) {
            LOG.e("Failed to Create Directory for MediaFile");
        }
        return File.createTempFile(str, str2, file);
    }

    public WritableMap createWritableMapFromContentProperties(CommonContentProperties commonContentProperties) {
        WritableMap writableMap = getWritableMap();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FILE_PREFIX);
        outline107.append(commonContentProperties.getPath());
        writableMap.putString(RouteParameter.PATH, outline107.toString());
        writableMap.putInt("width", commonContentProperties.getWidth());
        writableMap.putInt("height", commonContentProperties.getHeight());
        writableMap.putString("mime", commonContentProperties.getMimeType());
        writableMap.putString("sourceURL", FILE_PREFIX + commonContentProperties.getPath());
        writableMap.putInt("size", (int) new File(commonContentProperties.getPath()).length());
        writableMap.putString(LocalStorageHelper.MODIFICATION_DATE_KEY, String.valueOf(commonContentProperties.getModificationDate()));
        writableMap.putInt("orientation", commonContentProperties.getOrientation());
        if (commonContentProperties.getExif() != null) {
            writableMap.putMap("exif", exifHashMapToWritableMap(commonContentProperties.getExif()));
        }
        return writableMap;
    }

    WritableMap exifHashMapToWritableMap(HashMap<String, String> hashMap) {
        WritableMap writableMap = getWritableMap();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            writableMap.putString(entry.getKey(), entry.getValue());
        }
        return writableMap;
    }

    Activity getActivity() {
        return super.getCurrentActivity();
    }

    String getAuthorityId(Activity activity) {
        return activity.getPackageName() + AUTHORITY_SUFFIX;
    }

    public Uri getCameraCaptureURI() {
        return this.mCameraCaptureURI;
    }

    WritableMap getImageData(String str) throws Exception {
        return createWritableMapFromContentProperties(CommonContentProperties.fromPath(Uri.parse(str), this.mImageUtil, this.mIncludeExif));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ImagePickerBridge";
    }

    WritableMap getSelection(Activity activity, Uri uri, boolean z) throws Exception {
        String resolveRealPath = this.mImageUtil.resolveRealPath(this.mCurrentMediaPath, activity, uri, z);
        if (resolveRealPath != null && !resolveRealPath.isEmpty()) {
            return getSelectionByMimeType(resolveRealPath);
        }
        throw new Exception("Cannot resolve asset path.");
    }

    WritableMap getSelectionByMimeType(String str) throws Exception {
        String mimeType = this.mImageUtil.getMimeType(this.mReactContext, str);
        if (mimeType != null && mimeType.startsWith("video/")) {
            return getVideoData(str, mimeType);
        }
        return getImageData(str);
    }

    Uri getUriFromFile(File file) {
        return Uri.fromFile(file);
    }

    WritableMap getWritableMap() {
        return new WritableNativeMap();
    }

    void initiateCamera(Activity activity) {
        String str;
        File createImageFile;
        try {
            if ("video".equals(this.mMediaType)) {
                str = "android.media.action.VIDEO_CAPTURE";
                createImageFile = createVideoFile();
            } else {
                str = "android.media.action.IMAGE_CAPTURE";
                createImageFile = createImageFile();
            }
            Intent createIntent = createIntent(str);
            if (this.mDeviceInfoUtil.isBelowLolliPop()) {
                this.mCameraCaptureURI = getUriFromFile(createImageFile);
            } else {
                this.mCameraCaptureURI = FileProvider.getUriForFile(activity, getAuthorityId(activity), createImageFile);
            }
            createIntent.putExtra(ContactsModuleConstants.OUTPUT, this.mCameraCaptureURI);
            if (this.mUseFrontCamera) {
                createIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                createIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
                createIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
            }
            if (createIntent.resolveActivity(activity.getPackageManager()) == null) {
                this.mResultCollector.notifyProblem(BridgeError.FailedToLaunchCamera);
            } else {
                activity.startActivityForResult(createIntent, CAMERA_PICKER_REQUEST);
            }
        } catch (Exception e) {
            this.mResultCollector.notifyProblem(BridgeError.FailedToStartCameraActivity.code(), e);
        }
    }

    void initiatePicker(Activity activity) {
        try {
            Intent createIntent = createIntent("android.intent.action.GET_CONTENT");
            if (this.mMediaType.equals("photo")) {
                createIntent.setType("image/*");
            } else if (this.mMediaType.equals("video")) {
                createIntent.setType("video/*");
            } else {
                createIntent.setType("*/*");
                createIntent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
            }
            createIntent.setFlags(67108864);
            createIntent.putExtra("android.intent.extra.ALLOW_MULTIPLE", this.mIsMultiple);
            createIntent.addCategory("android.intent.category.OPENABLE");
            activity.startActivityForResult(Intent.createChooser(createIntent, "Pick an image"), IMAGE_PICKER_REQUEST);
        } catch (Exception e) {
            this.mResultCollector.notifyProblem(BridgeError.FailedToStartPickerActivity.code(), e);
        }
    }

    boolean isCameraAvailable(Activity activity) {
        return activity.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    public /* synthetic */ Void lambda$openCamera$1$ImagePickerModule(Activity activity) throws Exception {
        initiateCamera(activity);
        return null;
    }

    public /* synthetic */ Void lambda$openPicker$2$ImagePickerModule(Activity activity) throws Exception {
        initiatePicker(activity);
        return null;
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (i == IMAGE_PICKER_REQUEST) {
            onImagePickerResult(activity, i2, intent);
        } else if (i != CAMERA_PICKER_REQUEST) {
        } else {
            onCameraPickerResult(activity, i2, intent);
        }
    }

    void onCameraPickerResult(Activity activity, int i, Intent intent) {
        if (i == 0) {
            this.mResultCollector.notifyProblem(BridgeError.ImagePickerCancelled);
        } else if (i != -1) {
        } else {
            Uri cameraCaptureURI = getCameraCaptureURI();
            if (cameraCaptureURI == null) {
                this.mResultCollector.notifyProblem(BridgeError.ImageDataNotFound);
                return;
            }
            this.mResultCollector.setWaitCount(1);
            processSelection(activity, cameraCaptureURI, true);
        }
    }

    void onImagePickerResult(Activity activity, int i, Intent intent) {
        if (i == 0) {
            this.mResultCollector.notifyProblem(BridgeError.ImagePickerCancelled);
        } else if (i == -1) {
            if (this.mIsMultiple) {
                ClipData clipData = intent.getClipData();
                if (clipData == null) {
                    this.mResultCollector.setWaitCount(1);
                    processSelection(activity, intent.getData(), false);
                    return;
                }
                this.mResultCollector.setWaitCount(clipData.getItemCount());
                for (int i2 = 0; i2 < clipData.getItemCount(); i2++) {
                    processSelection(activity, clipData.getItemAt(i2).getUri(), false);
                }
                return;
            }
            Uri data = intent.getData();
            if (data == null) {
                this.mResultCollector.notifyProblem(BridgeError.ImageDataNotFound);
            } else {
                processSelection(activity, data, false);
            }
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void openCamera(ReadableMap readableMap, Promise promise) {
        final Activity activity = getActivity();
        if (activity == null) {
            promise.reject(BridgeError.ActivityNotExists.code(), BridgeError.ActivityNotExists.message());
        } else if (!isCameraAvailable(activity)) {
            promise.reject(BridgeError.CameraNotAvailable.code(), BridgeError.CameraNotAvailable.message());
        } else {
            setConfiguration(readableMap);
            this.mResultCollector.setup(promise, false);
            permissionsCheck(activity, promise, Arrays.asList("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"), new Callable() { // from class: com.amazon.alexa.sharing.media.picker.-$$Lambda$ImagePickerModule$iTsXor4j24pgovNmfn9-QD8x_GA
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return ImagePickerModule.this.lambda$openCamera$1$ImagePickerModule(activity);
                }
            });
        }
    }

    @ReactMethod
    public void openPicker(ReadableMap readableMap, Promise promise) {
        final Activity activity = getActivity();
        if (activity == null) {
            promise.reject(BridgeError.ActivityNotExists.code(), BridgeError.ActivityNotExists.message());
            return;
        }
        setConfiguration(readableMap);
        this.mResultCollector.setup(promise, this.mIsMultiple);
        permissionsCheck(activity, promise, Collections.singletonList("android.permission.WRITE_EXTERNAL_STORAGE"), new Callable() { // from class: com.amazon.alexa.sharing.media.picker.-$$Lambda$ImagePickerModule$0C3LIFxAh-ebqyOA1xf7DjKAtgc
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ImagePickerModule.this.lambda$openPicker$2$ImagePickerModule(activity);
            }
        });
    }

    void permissionsCheck(Activity activity, final Promise promise, List<String> list, final Callable<Void> callable) {
        ArrayList arrayList = new ArrayList();
        final BridgeError bridgeError = BridgeError.PermissionsDenied;
        final BridgeError bridgeError2 = BridgeError.FailedToInvokeCallable;
        for (String str : list) {
            if (ContextCompat.checkSelfPermission(activity, str) != 0) {
                arrayList.add(str);
            }
        }
        if (!arrayList.isEmpty()) {
            ((PermissionAwareActivity) activity).requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), 1, new PermissionListener() { // from class: com.amazon.alexa.sharing.media.picker.-$$Lambda$ImagePickerModule$hE6cIQUy6EaVS7Apt1Sh8PxhTWE
                @Override // com.facebook.react.modules.core.PermissionListener
                public final boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
                    ImagePickerModule.lambda$permissionsCheck$0(Promise.this, bridgeError, callable, bridgeError2, i, strArr, iArr);
                    return true;
                }
            });
            return;
        }
        try {
            callable.call();
        } catch (Exception e) {
            promise.reject(bridgeError2.code(), e.getClass().getSimpleName(), e);
        }
    }

    void processSelection(Activity activity, Uri uri, boolean z) {
        try {
            this.mResultCollector.notifySuccess(getSelection(activity, uri, z));
        } catch (Exception e) {
            this.mResultCollector.notifyProblem(BridgeError.ImageDataNotFound.code(), e.getMessage());
        }
    }

    void setConfiguration(ReadableMap readableMap) {
        this.mMediaType = readableMap.hasKey("mediaType") ? readableMap.getString("mediaType") : Languages.ANY;
        boolean z = true;
        this.mIsMultiple = readableMap.hasKey("multiple") && readableMap.getBoolean("multiple");
        this.mIncludeExif = readableMap.hasKey("mIncludeExif") && readableMap.getBoolean("mIncludeExif");
        if (!readableMap.hasKey("mUseFrontCamera") || !readableMap.getBoolean("mUseFrontCamera")) {
            z = false;
        }
        this.mUseFrontCamera = z;
    }

    public ImagePickerModule(ReactApplicationContext reactApplicationContext, ImageUtil imageUtil, ResultCollector resultCollector, DeviceInfoUtil deviceInfoUtil) {
        super(reactApplicationContext);
        this.mMediaType = Languages.ANY;
        this.mIsMultiple = false;
        this.mIncludeExif = false;
        this.mUseFrontCamera = false;
        reactApplicationContext.addActivityEventListener(this);
        this.mReactContext = reactApplicationContext;
        this.mImageUtil = imageUtil;
        this.mResultCollector = resultCollector;
        this.mDeviceInfoUtil = deviceInfoUtil;
    }
}
