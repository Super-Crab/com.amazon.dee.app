package com.amazon.deecomms.nativemodules.imagepicker;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.accessory.notificationpublisher.storage.LocalStorageHelper;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.api.SharingClient;
import com.amazon.deecomms.R;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.nativemodules.imagepicker.util.Compression;
import com.amazon.deecomms.nativemodules.imagepicker.util.ResultCollector;
import com.amazon.deecomms.sharing.photos.CommonContentProperties;
import com.android.tools.r8.GeneratedOutlineSupport;
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
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.apache.commons.codec.language.bm.Languages;
/* loaded from: classes12.dex */
public class PickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final int CAMERA_PICKER_REQUEST = 61111;
    private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";
    private static final String E_CALLBACK_ERROR = "E_CALLBACK_ERROR";
    private static final String E_CAMERA_IS_NOT_AVAILABLE = "E_CAMERA_IS_NOT_AVAILABLE";
    private static final String E_CANNOT_LAUNCH_CAMERA = "E_CANNOT_LAUNCH_CAMERA";
    private static final String E_DECOUPLING_HANDLER_FAILED = "E_DECOUPLING_HANDLER_FAILED";
    private static final String E_FAILED_TO_OPEN_CAMERA = "E_FAILED_TO_OPEN_CAMERA";
    private static final String E_FAILED_TO_SHOW_PICKER = "E_FAILED_TO_SHOW_PICKER";
    private static final String E_NO_IMAGE_DATA_FOUND = "E_NO_IMAGE_DATA_FOUND";
    private static final String E_PERMISSIONS_MISSING = "E_PERMISSION_MISSING";
    private static final String E_PICKER_CANCELLED_KEY = "E_PICKER_CANCELLED";
    private static final String E_PICKER_CANCELLED_MSG = "User cancelled image selection";
    private static final int IMAGE_PICKER_REQUEST = 61110;
    private Uri mCameraCaptureURI;
    private CapabilitiesManager mCapabilitiesManager;
    private Compression mCompression;
    private String mCurrentMediaPath;
    private boolean mIncludeBase64;
    private boolean mIncludeExif;
    private boolean mIsMultiple;
    private ReadableMap mMediaOptions;
    private String mMediaType;
    private ReactApplicationContext mReactContext;
    private ResultCollector mResultCollector;
    private SharingClient mSharingClient;
    private boolean mUseFrontCamera;

    public PickerModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, CommsDaggerWrapper.getComponent().getCapabilitiesManager(), (SharingClient) GeneratedOutlineSupport1.outline20(SharingClient.class), new ResultCollector());
    }

    private void cameraPickerResult(Activity activity, int i, int i2, Intent intent) {
        if (i2 == 0) {
            this.mResultCollector.notifyProblem(E_PICKER_CANCELLED_KEY, E_PICKER_CANCELLED_MSG);
        } else if (i2 != -1) {
        } else {
            Uri uri = this.mCameraCaptureURI;
            if (uri == null) {
                this.mResultCollector.notifyProblem(E_NO_IMAGE_DATA_FOUND, "Cannot resolve image url");
                return;
            }
            try {
                this.mResultCollector.setWaitCount(1);
                WritableMap selection = getSelection(activity, uri, true);
                if (selection == null) {
                    return;
                }
                this.mResultCollector.notifySuccess(selection);
            } catch (Exception e) {
                this.mResultCollector.notifyProblem(E_NO_IMAGE_DATA_FOUND, e.getMessage());
            }
        }
    }

    private File createImageFile() throws IOException {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("image-");
        outline1.append(UUID.randomUUID().toString());
        File createMediaFile = createMediaFile(this.mReactContext.getFilesDir(), outline1.toString(), Constants.DEFAULT_IMAGE_EXTENSION);
        StringBuilder outline12 = GeneratedOutlineSupport.outline1("file:");
        outline12.append(createMediaFile.getAbsolutePath());
        this.mCurrentMediaPath = outline12.toString();
        return createMediaFile;
    }

    private File createMediaFile(File file, String str, String str2) throws IOException {
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        return File.createTempFile(str, str2, file);
    }

    private File createVideoFile() throws IOException {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("video-");
        outline1.append(UUID.randomUUID().toString());
        File createMediaFile = createMediaFile(this.mReactContext.getFilesDir(), outline1.toString(), ".mp4");
        StringBuilder outline12 = GeneratedOutlineSupport.outline1("file:");
        outline12.append(createMediaFile.getAbsolutePath());
        this.mCurrentMediaPath = outline12.toString();
        return createMediaFile;
    }

    private void getAsyncSelection(Activity activity, Uri uri, boolean z) throws Exception {
        String resolveRealPath = this.mCompression.resolveRealPath(this.mCurrentMediaPath, activity, uri, z);
        if (resolveRealPath != null && !resolveRealPath.isEmpty()) {
            String mimeType = this.mCompression.getMimeType(this.mReactContext, resolveRealPath);
            if (mimeType != null && mimeType.startsWith("video/")) {
                getVideo(activity, resolveRealPath, mimeType);
                return;
            } else {
                this.mResultCollector.notifySuccess(getImage(resolveRealPath));
                return;
            }
        }
        this.mResultCollector.notifyProblem(E_NO_IMAGE_DATA_FOUND, "Cannot resolve asset path.");
    }

    private static String getAuthorityId(Activity activity) {
        return activity.getString(R.string.genericFileProviderAuthoritiesId);
    }

    private WritableMap getImage(String str) throws Exception {
        return CommonContentProperties.fromPath(Uri.parse(str), this.mCompression, this.mMediaOptions, this.mIncludeExif, this.mIncludeBase64).toWritableMap();
    }

    private WritableMap getSelection(Activity activity, Uri uri, boolean z) throws Exception {
        String resolveRealPath = this.mCompression.resolveRealPath(this.mCurrentMediaPath, activity, uri, z);
        if (resolveRealPath != null && !resolveRealPath.isEmpty()) {
            String mimeType = this.mCompression.getMimeType(this.mReactContext, resolveRealPath);
            if (mimeType != null && mimeType.startsWith("video/")) {
                getVideo(activity, resolveRealPath, mimeType);
                return null;
            }
            return getImage(resolveRealPath);
        }
        throw new Exception("Cannot resolve asset path.");
    }

    private String getTmpDir(Activity activity) {
        String str = activity.getCacheDir() + "/react-native-image-crop-picker";
        new File(str).mkdir();
        return str;
    }

    private void getVideo(Activity activity, String str, String str2) throws Exception {
        validateVideo(str);
        String str3 = getTmpDir(activity) + "/" + UUID.randomUUID().toString() + ".mp4";
        try {
            Bitmap validateVideo = validateVideo(str);
            long lastModified = new File(str).lastModified();
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putInt("width", validateVideo.getWidth());
            writableNativeMap.putInt("height", validateVideo.getHeight());
            writableNativeMap.putString("mime", str2);
            writableNativeMap.putString("sourceURL", "file://" + str);
            writableNativeMap.putInt("size", (int) new File(str).length());
            writableNativeMap.putString(RouteParameter.PATH, "file://" + str);
            writableNativeMap.putString(LocalStorageHelper.MODIFICATION_DATE_KEY, String.valueOf(lastModified));
            this.mResultCollector.notifySuccess(writableNativeMap);
        } catch (Exception e) {
            this.mResultCollector.notifyProblem(E_NO_IMAGE_DATA_FOUND, e);
        }
    }

    private void imagePickerResult(Activity activity, int i, Intent intent) {
        if (i == 0) {
            this.mResultCollector.notifyProblem(E_PICKER_CANCELLED_KEY, E_PICKER_CANCELLED_MSG);
        } else if (i == -1) {
            if (this.mIsMultiple) {
                ClipData clipData = intent.getClipData();
                try {
                    if (clipData == null) {
                        this.mResultCollector.setWaitCount(1);
                        getAsyncSelection(activity, intent.getData(), false);
                        return;
                    }
                    this.mResultCollector.setWaitCount(clipData.getItemCount());
                    for (int i2 = 0; i2 < clipData.getItemCount(); i2++) {
                        getAsyncSelection(activity, clipData.getItemAt(i2).getUri(), false);
                    }
                    return;
                } catch (Exception e) {
                    this.mResultCollector.notifyProblem(E_NO_IMAGE_DATA_FOUND, e.getMessage());
                    return;
                }
            }
            Uri data = intent.getData();
            if (data == null) {
                this.mResultCollector.notifyProblem(E_NO_IMAGE_DATA_FOUND, "Cannot resolve image url");
                return;
            }
            try {
                getAsyncSelection(activity, data, false);
            } catch (Exception e2) {
                this.mResultCollector.notifyProblem(E_NO_IMAGE_DATA_FOUND, e2.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initiateCamera(Activity activity) {
        File createImageFile;
        String str;
        try {
            if ("video".equals(this.mMediaType)) {
                createImageFile = createVideoFile();
                str = "android.media.action.VIDEO_CAPTURE";
            } else {
                createImageFile = createImageFile();
                str = "android.media.action.IMAGE_CAPTURE";
            }
            Intent intent = new Intent(str);
            int i = Build.VERSION.SDK_INT;
            this.mCameraCaptureURI = FileProvider.getUriForFile(activity, getAuthorityId(activity), createImageFile);
            intent.putExtra(ContactsModuleConstants.OUTPUT, this.mCameraCaptureURI);
            if (this.mUseFrontCamera) {
                intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
                intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
            }
            if (intent.resolveActivity(activity.getPackageManager()) == null) {
                this.mResultCollector.notifyProblem(E_CANNOT_LAUNCH_CAMERA, "Cannot launch camera");
            } else {
                activity.startActivityForResult(intent, 61111);
            }
        } catch (Exception e) {
            this.mResultCollector.notifyProblem(E_FAILED_TO_OPEN_CAMERA, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initiatePicker(Activity activity) {
        try {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            if (this.mMediaType.equals("photo")) {
                intent.setType("image/*");
            } else if (this.mMediaType.equals("video")) {
                intent.setType("video/*");
            } else {
                intent.setType("*/*");
                intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
            }
            intent.setFlags(67108864);
            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", this.mIsMultiple);
            intent.addCategory("android.intent.category.OPENABLE");
            activity.startActivityForResult(Intent.createChooser(intent, "Pick an image"), 61110);
        } catch (Exception e) {
            this.mResultCollector.notifyProblem(E_FAILED_TO_SHOW_PICKER, e);
        }
    }

    private boolean isCameraAvailable(Activity activity) {
        return activity.getPackageManager().hasSystemFeature("android.hardware.camera") || activity.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    private void permissionsCheck(Activity activity, final Promise promise, List<String> list, final Callable<Void> callable) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (ContextCompat.checkSelfPermission(activity, str) != 0) {
                arrayList.add(str);
            }
        }
        if (!arrayList.isEmpty()) {
            ((PermissionAwareActivity) activity).requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), 1, new PermissionListener() { // from class: com.amazon.deecomms.nativemodules.imagepicker.PickerModule.1
                @Override // com.facebook.react.modules.core.PermissionListener
                public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
                    if (i == 1) {
                        for (int i2 : iArr) {
                            if (i2 == -1) {
                                promise.reject(PickerModule.E_PERMISSIONS_MISSING, "Required permission missing");
                                return true;
                            }
                        }
                        try {
                            callable.call();
                        } catch (Exception e) {
                            promise.reject(PickerModule.E_CALLBACK_ERROR, "Unknown error", e);
                        }
                    }
                    return true;
                }
            });
            return;
        }
        try {
            callable.call();
        } catch (Exception e) {
            promise.reject(E_CALLBACK_ERROR, "Unknown error", e);
        }
    }

    private void setConfiguration(ReadableMap readableMap) {
        this.mMediaType = readableMap.hasKey("mediaType") ? readableMap.getString("mediaType") : Languages.ANY;
        boolean z = true;
        this.mIsMultiple = readableMap.hasKey("multiple") && readableMap.getBoolean("multiple");
        this.mIncludeBase64 = readableMap.hasKey("mIncludeBase64") && readableMap.getBoolean("mIncludeBase64");
        this.mIncludeExif = readableMap.hasKey("mIncludeExif") && readableMap.getBoolean("mIncludeExif");
        if (!readableMap.hasKey("mUseFrontCamera") || !readableMap.getBoolean("mUseFrontCamera")) {
            z = false;
        }
        this.mUseFrontCamera = z;
        this.mMediaOptions = readableMap;
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

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ImagePickerBridge";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (i == 61110) {
            imagePickerResult(activity, i2, intent);
        } else if (i != 61111) {
        } else {
            cameraPickerResult(activity, i, i2, intent);
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void openCamera(ReadableMap readableMap, Promise promise) {
        this.mResultCollector.setup(promise, false);
        try {
            if (this.mCapabilitiesManager.hasSharingDecouplingAccess()) {
                this.mSharingClient.openCamera(this.mReactContext, readableMap, promise);
                return;
            }
            final Activity currentActivity = getCurrentActivity();
            if (currentActivity == null) {
                promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
            } else if (!isCameraAvailable(currentActivity)) {
                promise.reject(E_CAMERA_IS_NOT_AVAILABLE, "Camera not available");
            } else {
                setConfiguration(readableMap);
                permissionsCheck(currentActivity, promise, Arrays.asList("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"), new Callable<Void>() { // from class: com.amazon.deecomms.nativemodules.imagepicker.PickerModule.2
                    @Override // java.util.concurrent.Callable
                    public Void call() {
                        PickerModule.this.initiateCamera(currentActivity);
                        return null;
                    }
                });
            }
        } catch (Exception e) {
            this.mResultCollector.notifyProblem(E_DECOUPLING_HANDLER_FAILED, e);
        }
    }

    @ReactMethod
    public void openPicker(ReadableMap readableMap, Promise promise) {
        this.mResultCollector.setup(promise, this.mIsMultiple);
        try {
            if (this.mCapabilitiesManager.hasSharingDecouplingAccess()) {
                this.mSharingClient.openPicker(this.mReactContext, readableMap, promise);
                return;
            }
            final Activity currentActivity = getCurrentActivity();
            if (currentActivity == null) {
                promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
                return;
            }
            setConfiguration(readableMap);
            permissionsCheck(currentActivity, promise, Collections.singletonList("android.permission.WRITE_EXTERNAL_STORAGE"), new Callable<Void>() { // from class: com.amazon.deecomms.nativemodules.imagepicker.PickerModule.3
                @Override // java.util.concurrent.Callable
                public Void call() {
                    PickerModule.this.initiatePicker(currentActivity);
                    return null;
                }
            });
        } catch (Exception e) {
            this.mResultCollector.notifyProblem(E_DECOUPLING_HANDLER_FAILED, e);
        }
    }

    public PickerModule(ReactApplicationContext reactApplicationContext, CapabilitiesManager capabilitiesManager, SharingClient sharingClient, ResultCollector resultCollector) {
        super(reactApplicationContext);
        this.mMediaType = Languages.ANY;
        this.mIsMultiple = false;
        this.mIncludeBase64 = false;
        this.mIncludeExif = false;
        this.mUseFrontCamera = false;
        reactApplicationContext.addActivityEventListener(this);
        this.mReactContext = reactApplicationContext;
        this.mCompression = new Compression(this.mReactContext);
        this.mCapabilitiesManager = capabilitiesManager;
        this.mSharingClient = sharingClient;
        this.mResultCollector = resultCollector;
    }
}
