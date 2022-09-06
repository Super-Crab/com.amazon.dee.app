package com.imagepicker;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.util.Collections;
import java.util.List;
@ReactModule(name = ImagePickerModule.NAME)
/* loaded from: classes3.dex */
public class ImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    static final String NAME = "ImagePickerManager";
    public static final int REQUEST_LAUNCH_IMAGE_CAPTURE = 13001;
    public static final int REQUEST_LAUNCH_LIBRARY = 13003;
    public static final int REQUEST_LAUNCH_VIDEO_CAPTURE = 13002;
    Callback callback;
    Uri cameraCaptureURI;
    private Uri fileUri;
    Options options;
    final ReactApplicationContext reactContext;

    public ImagePickerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        this.reactContext.addActivityEventListener(this);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void launchCamera(ReadableMap readableMap, Callback callback) {
        int i;
        Intent intent;
        File createFile;
        if (!Utils.isCameraAvailable(this.reactContext)) {
            callback.invoke(Utils.getErrorMap(Utils.errCameraUnavailable, null));
            return;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            callback.invoke(Utils.getErrorMap(Utils.errOthers, "Activity error"));
        } else if (!Utils.isCameraPermissionFulfilled(this.reactContext, currentActivity)) {
            callback.invoke(Utils.getErrorMap(Utils.errOthers, Utils.cameraPermissionDescription));
        } else {
            this.callback = callback;
            this.options = new Options(readableMap);
            if (this.options.saveToPhotos.booleanValue() && Build.VERSION.SDK_INT <= 28 && !Utils.hasPermission(currentActivity)) {
                callback.invoke(Utils.getErrorMap(Utils.errPermission, null));
                return;
            }
            if (this.options.mediaType.equals(Utils.mediaTypeVideo)) {
                i = REQUEST_LAUNCH_VIDEO_CAPTURE;
                intent = new Intent("android.media.action.VIDEO_CAPTURE");
                intent.putExtra("android.intent.extra.videoQuality", this.options.videoQuality);
                int i2 = this.options.durationLimit;
                if (i2 > 0) {
                    intent.putExtra("android.intent.extra.durationLimit", i2);
                }
                createFile = Utils.createFile(this.reactContext, "mp4");
                this.cameraCaptureURI = Utils.createUri(createFile, this.reactContext);
            } else {
                i = REQUEST_LAUNCH_IMAGE_CAPTURE;
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                createFile = Utils.createFile(this.reactContext, ImageType.JPG);
                this.cameraCaptureURI = Utils.createUri(createFile, this.reactContext);
            }
            if (this.options.useFrontCamera.booleanValue()) {
                Utils.setFrontCamera(intent);
            }
            this.fileUri = Uri.fromFile(createFile);
            intent.putExtra(ContactsModuleConstants.OUTPUT, this.cameraCaptureURI);
            intent.addFlags(3);
            try {
                currentActivity.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                callback.invoke(Utils.getErrorMap(Utils.errOthers, e.getMessage()));
                this.callback = null;
            }
        }
    }

    @ReactMethod
    public void launchImageLibrary(ReadableMap readableMap, Callback callback) {
        Intent intent;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            callback.invoke(Utils.getErrorMap(Utils.errOthers, "Activity error"));
            return;
        }
        this.callback = callback;
        this.options = new Options(readableMap);
        boolean z = this.options.selectionLimit == 1;
        boolean equals = this.options.mediaType.equals(Utils.mediaTypePhoto);
        boolean equals2 = this.options.mediaType.equals(Utils.mediaTypeVideo);
        if (z && (equals || equals2)) {
            intent = new Intent("android.intent.action.PICK");
        } else {
            intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
        }
        if (!z) {
            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        }
        if (equals) {
            intent.setType("image/*");
        } else if (equals2) {
            intent.setType("video/*");
        } else {
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
        }
        try {
            currentActivity.startActivityForResult(Intent.createChooser(intent, null), REQUEST_LAUNCH_LIBRARY);
        } catch (ActivityNotFoundException e) {
            callback.invoke(Utils.getErrorMap(Utils.errOthers, e.getMessage()));
            this.callback = null;
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (!Utils.isValidRequestCode(i) || this.callback == null) {
            return;
        }
        if (i2 != -1) {
            if (i == 13001) {
                Utils.deleteFile(this.fileUri);
            }
            this.callback.invoke(Utils.getCancelMap());
            return;
        }
        switch (i) {
            case REQUEST_LAUNCH_IMAGE_CAPTURE /* 13001 */:
                if (this.options.saveToPhotos.booleanValue()) {
                    Utils.saveToPublicDirectory(this.cameraCaptureURI, this.reactContext, "photo");
                }
                onAssetsObtained(Collections.singletonList(this.fileUri));
                return;
            case REQUEST_LAUNCH_VIDEO_CAPTURE /* 13002 */:
                if (this.options.saveToPhotos.booleanValue()) {
                    Utils.saveToPublicDirectory(this.cameraCaptureURI, this.reactContext, "video");
                }
                onAssetsObtained(Collections.singletonList(this.fileUri));
                return;
            case REQUEST_LAUNCH_LIBRARY /* 13003 */:
                onAssetsObtained(Utils.collectUrisFromData(intent));
                return;
            default:
                return;
        }
    }

    void onAssetsObtained(List<Uri> list) {
        try {
            try {
                this.callback.invoke(Utils.getResponseMap(list, this.options, this.reactContext));
            } catch (RuntimeException e) {
                this.callback.invoke(Utils.getErrorMap(Utils.errOthers, e.getMessage()));
            }
        } finally {
            this.callback = null;
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }
}
