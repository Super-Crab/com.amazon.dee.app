package com.reactnativecommunity.webview;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.util.Pair;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.sharing.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
@ReactModule(name = RNCWebViewModule.MODULE_NAME)
/* loaded from: classes3.dex */
public class RNCWebViewModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final int FILE_DOWNLOAD_PERMISSION_REQUEST = 1;
    public static final String MODULE_NAME = "RNCWebView";
    private static final int PICKER = 1;
    private static final int PICKER_LEGACY = 3;
    protected static final ShouldOverrideUrlLoadingLock shouldOverrideUrlLoadingLock = new ShouldOverrideUrlLoadingLock();
    private DownloadManager.Request downloadRequest;
    private ValueCallback<Uri[]> filePathCallback;
    private ValueCallback<Uri> filePathCallbackLegacy;
    private File outputImage;
    private File outputVideo;
    private PermissionListener webviewFileDownloaderPermissionListener;

    /* renamed from: com.reactnativecommunity.webview.RNCWebViewModule$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$reactnativecommunity$webview$RNCWebViewModule$MimeType = new int[MimeType.values().length];

        static {
            try {
                $SwitchMap$com$reactnativecommunity$webview$RNCWebViewModule$MimeType[MimeType.IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$reactnativecommunity$webview$RNCWebViewModule$MimeType[MimeType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum MimeType {
        DEFAULT("*/*"),
        IMAGE("image"),
        VIDEO("video");
        
        private final String value;

        MimeType(String str) {
            this.value = str;
        }
    }

    /* loaded from: classes3.dex */
    protected static class ShouldOverrideUrlLoadingLock {
        private int nextLockIdentifier = 0;
        private final HashMap<Integer, AtomicReference<ShouldOverrideCallbackState>> shouldOverrideLocks = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: classes3.dex */
        public enum ShouldOverrideCallbackState {
            UNDECIDED,
            SHOULD_OVERRIDE,
            DO_NOT_OVERRIDE
        }

        protected ShouldOverrideUrlLoadingLock() {
        }

        @Nullable
        public synchronized AtomicReference<ShouldOverrideCallbackState> getLock(Integer num) {
            return this.shouldOverrideLocks.get(num);
        }

        public synchronized Pair<Integer, AtomicReference<ShouldOverrideCallbackState>> getNewLock() {
            int i;
            AtomicReference<ShouldOverrideCallbackState> atomicReference;
            i = this.nextLockIdentifier;
            this.nextLockIdentifier = i + 1;
            atomicReference = new AtomicReference<>(ShouldOverrideCallbackState.UNDECIDED);
            this.shouldOverrideLocks.put(Integer.valueOf(i), atomicReference);
            return new Pair<>(Integer.valueOf(i), atomicReference);
        }

        public synchronized void removeLock(Integer num) {
            this.shouldOverrideLocks.remove(num);
        }
    }

    public RNCWebViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.webviewFileDownloaderPermissionListener = new PermissionListener() { // from class: com.reactnativecommunity.webview.RNCWebViewModule.1
            @Override // com.facebook.react.modules.core.PermissionListener
            public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
                if (i != 1) {
                    return false;
                }
                if (iArr.length <= 0 || iArr[0] != 0) {
                    Toast.makeText(RNCWebViewModule.this.getCurrentActivity().getApplicationContext(), "Cannot download files as permission was denied. Please provide permission to write to storage, in order to download files.", 1).show();
                } else if (RNCWebViewModule.this.downloadRequest != null) {
                    RNCWebViewModule.this.downloadFile();
                }
                return true;
            }
        };
        reactApplicationContext.addActivityEventListener(this);
    }

    private Boolean acceptsImages(String str) {
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains(MimeType.IMAGE.value));
    }

    private Boolean acceptsVideo(String str) {
        int i = Build.VERSION.SDK_INT;
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains(MimeType.VIDEO.value));
    }

    private Boolean arrayContainsString(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private String[] getAcceptedMimeType(String[] strArr) {
        if (noAcceptTypesSet(strArr).booleanValue()) {
            return new String[]{MimeType.DEFAULT.value};
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str.matches("\\.\\w+")) {
                String mimeTypeFromExtension = getMimeTypeFromExtension(str.replace(".", ""));
                if (mimeTypeFromExtension != null) {
                    strArr2[i] = mimeTypeFromExtension;
                } else {
                    strArr2[i] = str;
                }
            } else {
                strArr2[i] = str;
            }
        }
        return strArr2;
    }

    private File getCapturedFile(MimeType mimeType) throws IOException {
        String str;
        int ordinal = mimeType.ordinal();
        String str2 = "";
        if (ordinal == 1) {
            String str3 = Environment.DIRECTORY_PICTURES;
            str2 = "image-";
            str = Constants.DEFAULT_IMAGE_EXTENSION;
        } else if (ordinal != 2) {
            str = str2;
        } else {
            String str4 = Environment.DIRECTORY_MOVIES;
            str2 = "video-";
            str = ".mp4";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str2);
        outline107.append(String.valueOf(System.currentTimeMillis()));
        outline107.append(str);
        outline107.toString();
        int i = Build.VERSION.SDK_INT;
        return File.createTempFile(str2, str, getReactApplicationContext().getExternalFilesDir(null));
    }

    private Intent getFileChooserIntent(String str) {
        String str2 = str.isEmpty() ? MimeType.DEFAULT.value : str;
        if (str.matches("\\.\\w+")) {
            str2 = getMimeTypeFromExtension(str.replace(".", ""));
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str2);
        return intent;
    }

    private String getMimeTypeFromExtension(String str) {
        if (str != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        }
        return null;
    }

    private Uri getOutputUri(File file) {
        int i = Build.VERSION.SDK_INT;
        String packageName = getReactApplicationContext().getPackageName();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        return FileProvider.getUriForFile(reactApplicationContext, packageName + ".fileprovider", file);
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            if (currentActivity instanceof PermissionAwareActivity) {
                return (PermissionAwareActivity) currentActivity;
            }
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
        throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
    }

    private Intent getPhotoIntent() {
        Intent intent;
        Uri outputUri;
        try {
            this.outputImage = getCapturedFile(MimeType.IMAGE);
            outputUri = getOutputUri(this.outputImage);
            intent = new Intent("android.media.action.IMAGE_CAPTURE");
        } catch (IOException | IllegalArgumentException e) {
            e = e;
            intent = null;
        }
        try {
            intent.putExtra(ContactsModuleConstants.OUTPUT, outputUri);
        } catch (IOException | IllegalArgumentException e2) {
            e = e2;
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            return intent;
        }
        return intent;
    }

    private Uri[] getSelectedFiles(Intent intent, int i) {
        if (intent == null) {
            return null;
        }
        if (intent.getClipData() != null) {
            int itemCount = intent.getClipData().getItemCount();
            Uri[] uriArr = new Uri[itemCount];
            for (int i2 = 0; i2 < itemCount; i2++) {
                uriArr[i2] = intent.getClipData().getItemAt(i2).getUri();
            }
            return uriArr;
        } else if (intent.getData() == null || i != -1) {
            return null;
        } else {
            int i3 = Build.VERSION.SDK_INT;
            return WebChromeClient.FileChooserParams.parseResult(i, intent);
        }
    }

    private Intent getVideoIntent() {
        Intent intent;
        Uri outputUri;
        try {
            this.outputVideo = getCapturedFile(MimeType.VIDEO);
            outputUri = getOutputUri(this.outputVideo);
            intent = new Intent("android.media.action.VIDEO_CAPTURE");
        } catch (IOException | IllegalArgumentException e) {
            e = e;
            intent = null;
        }
        try {
            intent.putExtra(ContactsModuleConstants.OUTPUT, outputUri);
        } catch (IOException | IllegalArgumentException e2) {
            e = e2;
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            return intent;
        }
        return intent;
    }

    private Boolean noAcceptTypesSet(String[] strArr) {
        boolean z = true;
        if (strArr.length != 0 && (strArr.length != 1 || strArr[0] == null || strArr[0].length() != 0)) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public void downloadFile() {
        ((DownloadManager) getCurrentActivity().getBaseContext().getSystemService("download")).enqueue(this.downloadRequest);
        Toast.makeText(getCurrentActivity().getApplicationContext(), "Downloading", 1).show();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    public boolean grantFileDownloaderPermissions() {
        if (Build.VERSION.SDK_INT > 28) {
            return true;
        }
        boolean z = ContextCompat.checkSelfPermission(getCurrentActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        if (!z) {
            int i = Build.VERSION.SDK_INT;
            getPermissionAwareActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1, this.webviewFileDownloaderPermissionListener);
        }
        return z;
    }

    @ReactMethod
    public void isFileUploadSupported(Promise promise) {
        Boolean.valueOf(false);
        int i = Build.VERSION.SDK_INT;
        promise.resolve(true);
    }

    protected boolean needsCameraPermission() {
        try {
            if (Arrays.asList(getCurrentActivity().getPackageManager().getPackageInfo(getReactApplicationContext().getPackageName(), 4096).requestedPermissions).contains("android.permission.CAMERA")) {
                if (ContextCompat.checkSelfPermission(getCurrentActivity(), "android.permission.CAMERA") != 0) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return true;
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (this.filePathCallback == null && this.filePathCallbackLegacy == null) {
            return;
        }
        File file = this.outputImage;
        boolean z = file != null && file.length() > 0;
        File file2 = this.outputVideo;
        boolean z2 = file2 != null && file2.length() > 0;
        if (i != 1) {
            if (i == 3) {
                if (i2 != -1) {
                    this.filePathCallbackLegacy.onReceiveValue(null);
                } else if (z) {
                    this.filePathCallbackLegacy.onReceiveValue(getOutputUri(this.outputImage));
                } else if (z2) {
                    this.filePathCallbackLegacy.onReceiveValue(getOutputUri(this.outputVideo));
                } else {
                    this.filePathCallbackLegacy.onReceiveValue(intent.getData());
                }
            }
        } else if (i2 != -1) {
            ValueCallback<Uri[]> valueCallback = this.filePathCallback;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
        } else if (z) {
            this.filePathCallback.onReceiveValue(new Uri[]{getOutputUri(this.outputImage)});
        } else if (z2) {
            this.filePathCallback.onReceiveValue(new Uri[]{getOutputUri(this.outputVideo)});
        } else {
            this.filePathCallback.onReceiveValue(getSelectedFiles(intent, i2));
        }
        File file3 = this.outputImage;
        if (file3 != null && !z) {
            file3.delete();
        }
        File file4 = this.outputVideo;
        if (file4 != null && !z2) {
            file4.delete();
        }
        this.filePathCallback = null;
        this.filePathCallbackLegacy = null;
        this.outputImage = null;
        this.outputVideo = null;
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public void onShouldStartLoadWithRequestCallback(boolean z, int i) {
        AtomicReference<ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState> lock = shouldOverrideUrlLoadingLock.getLock(Integer.valueOf(i));
        if (lock != null) {
            synchronized (lock) {
                lock.set(z ? ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.DO_NOT_OVERRIDE : ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.SHOULD_OVERRIDE);
                lock.notify();
            }
        }
    }

    public void setDownloadRequest(DownloadManager.Request request) {
        this.downloadRequest = request;
    }

    public void startPhotoPickerIntent(ValueCallback<Uri> valueCallback, String str) {
        Intent videoIntent;
        Intent photoIntent;
        this.filePathCallbackLegacy = valueCallback;
        Intent createChooser = Intent.createChooser(getFileChooserIntent(str), "");
        ArrayList arrayList = new ArrayList();
        if (acceptsImages(str).booleanValue() && (photoIntent = getPhotoIntent()) != null) {
            arrayList.add(photoIntent);
        }
        if (acceptsVideo(str).booleanValue() && (videoIntent = getVideoIntent()) != null) {
            arrayList.add(videoIntent);
        }
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (createChooser.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(createChooser, 3);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
    }

    private Boolean acceptsImages(String[] strArr) {
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf(arrayContainsString(acceptedMimeType, MimeType.DEFAULT.value).booleanValue() || arrayContainsString(acceptedMimeType, MimeType.IMAGE.value).booleanValue());
    }

    private Boolean acceptsVideo(String[] strArr) {
        int i = Build.VERSION.SDK_INT;
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf(arrayContainsString(acceptedMimeType, MimeType.DEFAULT.value).booleanValue() || arrayContainsString(acceptedMimeType, MimeType.VIDEO.value).booleanValue());
    }

    private Intent getFileChooserIntent(String[] strArr, boolean z) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(MimeType.DEFAULT.value);
        intent.putExtra("android.intent.extra.MIME_TYPES", getAcceptedMimeType(strArr));
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", z);
        return intent;
    }

    @RequiresApi(api = 21)
    public boolean startPhotoPickerIntent(ValueCallback<Uri[]> valueCallback, String[] strArr, boolean z) {
        Intent videoIntent;
        Intent photoIntent;
        this.filePathCallback = valueCallback;
        ArrayList arrayList = new ArrayList();
        if (!needsCameraPermission()) {
            if (acceptsImages(strArr).booleanValue() && (photoIntent = getPhotoIntent()) != null) {
                arrayList.add(photoIntent);
            }
            if (acceptsVideo(strArr).booleanValue() && (videoIntent = getVideoIntent()) != null) {
                arrayList.add(videoIntent);
            }
        }
        Intent fileChooserIntent = getFileChooserIntent(strArr, z);
        Intent intent = new Intent("android.intent.action.CHOOSER");
        intent.putExtra("android.intent.extra.INTENT", fileChooserIntent);
        intent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (intent.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(intent, 1);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
        return true;
    }
}
