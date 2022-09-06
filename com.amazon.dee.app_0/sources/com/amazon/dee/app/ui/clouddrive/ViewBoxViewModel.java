package com.amazon.dee.app.ui.clouddrive;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.dee.app.framework.ViewModel;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImage;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService;
import com.amazon.dee.app.services.alexadevicebackground.DeviceDownloadFailedException;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.photos.PhotoService;
import com.amazon.dee.app.ui.external.ExternalUIActivity;
import com.amazon.dee.app.ui.web.ExternalUILauncherBridge;
import com.amazon.dee.app.ui.web.WebConstants;
import com.amazon.dee.app.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class ViewBoxViewModel implements ViewModel {
    private static final String AMAZON_DRIVE_TAC_URI = "https://www.amazon.com/gp/help/customer/display.html?&nodeId=201504890&pop-up=1";
    private static final String APP_STORE_INTENT_URI = "amzn://apps/android?asin=";
    private static final String GOOGLE_PLAY_INTENT_URI = "market://details?id=com.amazon.clouddrive.photos";
    private static final String PRIME_PHOTOS_ASIN = "B00A11AN6O";
    private static final String TAC_AUTHORIZED_HOST = "www.amazon.com";
    private static final String TAG = Utils.safeTag(ViewBoxViewModel.class.getSimpleName());
    private final Activity activity;
    private final BackgroundImage backgroundImage;
    private final Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
    private Uri croppedImageUri = null;
    private final BackgroundImageService deviceBackgroundImageService;
    private final EnvironmentService environmentService;
    private final IdentityService identityService;
    private final Uri imageSourceUri;
    private final MetricsService metricsService;
    private final PhotoService photoService;
    private final UploadFileListener uploadFileListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public interface UploadFileListener {
        void onDownloading(BackgroundImage backgroundImage);

        void onError(Exception exc);

        void onSuccess(BackgroundImage backgroundImage);
    }

    public ViewBoxViewModel(Activity activity, IdentityService identityService, PhotoService photoService, EnvironmentService environmentService, BackgroundImageService backgroundImageService, MetricsService metricsService, Uri uri, UploadFileListener uploadFileListener, BackgroundImage backgroundImage) {
        this.activity = activity;
        this.identityService = identityService;
        this.photoService = photoService;
        this.environmentService = environmentService;
        this.deviceBackgroundImageService = backgroundImageService;
        this.metricsService = metricsService;
        this.imageSourceUri = uri;
        this.uploadFileListener = uploadFileListener;
        this.backgroundImage = backgroundImage;
    }

    private void closeSilently(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Throwable th) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to close: ");
            outline107.append(th.getMessage());
            Log.e(str, outline107.toString());
        }
    }

    private void launchThirdPartyUIActivity(String str, ArrayList<String> arrayList) {
        Intent intent = new Intent(this.activity.getApplicationContext(), ExternalUIActivity.class);
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putExtra(WebConstants.ExternalUIConstants.HOST_ALLOWLIST_KEY, arrayList);
        intent.putExtra("BRIDGE_ACTION_KEY", ExternalUILauncherBridge.BridgeAction.THIRD_PARTY.toString());
        try {
            this.activity.startActivityForResult(intent, 2);
        } catch (ActivityNotFoundException unused) {
            Log.e(TAG, String.format("Could not launch an activity with specified intent: %s", intent));
        }
    }

    private void monitorDownloadOnDevice() {
        this.deviceBackgroundImageService.monitorDownload(this.backgroundImage).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$ViewBoxViewModel$pLCB806OmLc2NmGhUlxTr0WcvnI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ViewBoxViewModel.this.lambda$monitorDownloadOnDevice$4$ViewBoxViewModel((BackgroundImage) obj);
            }
        }, new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$ViewBoxViewModel$B-X1VrUDHw5Tk2Fsuu8c917Eyts
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ViewBoxViewModel.this.lambda$monitorDownloadOnDevice$5$ViewBoxViewModel((Throwable) obj);
            }
        });
    }

    private boolean saveOutput(@NonNull Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        String str = this.activity.getCacheDir() + "/cropped/";
        File file = new File(str);
        if (!file.mkdirs()) {
            Log.e(TAG, String.format("Failed to create staging directory %s", str));
        }
        String lastPathSegment = this.imageSourceUri.getLastPathSegment();
        if (lastPathSegment.contains("/")) {
            lastPathSegment = lastPathSegment.substring(lastPathSegment.lastIndexOf("/") + 1);
        }
        File file2 = new File(file, lastPathSegment);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bitmap.compress(this.compressFormat, 100, fileOutputStream);
            closeSilently(fileOutputStream);
            this.croppedImageUri = Uri.fromFile(file2);
            bitmap.recycle();
            return true;
        } catch (IOException e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            Log.e(TAG, e, "Error compressing bitmap", new Object[0]);
            closeSilently(fileOutputStream2);
            this.croppedImageUri = Uri.fromFile(file2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            closeSilently(fileOutputStream2);
            this.croppedImageUri = Uri.fromFile(file2);
            throw th;
        }
    }

    private void sendImageIdToDevice(String str, String str2) {
        this.backgroundImage.setBackgroundImageID(str);
        this.backgroundImage.setBackgroundImageType(BackgroundImage.PERSONAL_BG_IMAGE);
        this.backgroundImage.setBackgroundImageURL(str2);
        this.deviceBackgroundImageService.update(this.backgroundImage).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$ViewBoxViewModel$61IJWs0lAY-PYeUxIxoRQjgn0i4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ViewBoxViewModel.this.lambda$sendImageIdToDevice$2$ViewBoxViewModel((BackgroundImage) obj);
            }
        }, new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$ViewBoxViewModel$185avP-QrUsJW0WmPjjmWaLzOjY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ViewBoxViewModel.this.lambda$sendImageIdToDevice$3$ViewBoxViewModel((Throwable) obj);
            }
        });
    }

    private void uploadFile() {
        Uri uri;
        if (this.identityService.isAuthenticated(TAG) && (uri = this.croppedImageUri) != null) {
            this.photoService.uploadImage(uri).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$ViewBoxViewModel$KP9Q0wNw-kT85DO0th05HFVfrOM
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    ViewBoxViewModel.this.lambda$uploadFile$0$ViewBoxViewModel((PhotoService.UploadResponse) obj);
                }
            }, new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$ViewBoxViewModel$Ppi9ivBRzqZ-CGtYcXHdEWCOBOQ
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    ViewBoxViewModel.this.lambda$uploadFile$1$ViewBoxViewModel((Throwable) obj);
                }
            });
            return;
        }
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_CDS_ERROR, "There was an error uploading the file to Cloud Drive.", AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        this.uploadFileListener.onError(new CloudDriveException("There was an error uploading the file to Cloud Drive."));
    }

    public void back() {
        this.activity.onBackPressed();
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void destroy() {
    }

    public void gotoPrimePhotosApp() {
        if (this.environmentService.getDeviceInformation().isFireOS()) {
            try {
                this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("amzn://apps/android?asin=B00A11AN6O")));
                return;
            } catch (ActivityNotFoundException unused) {
                Log.e(TAG, "Could not open Amazon App store");
                return;
            }
        }
        try {
            this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(GOOGLE_PLAY_INTENT_URI)));
        } catch (ActivityNotFoundException unused2) {
            Log.e(TAG, "Could not open Google Play store");
        }
    }

    public /* synthetic */ void lambda$monitorDownloadOnDevice$4$ViewBoxViewModel(BackgroundImage backgroundImage) {
        this.metricsService.recordTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_TIME);
        this.metricsService.recordTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_DWCS_TIME);
        this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_DWCS_SUCCESS, AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        this.uploadFileListener.onSuccess(backgroundImage);
    }

    public /* synthetic */ void lambda$monitorDownloadOnDevice$5$ViewBoxViewModel(Throwable th) {
        this.metricsService.cancelTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_TIME);
        this.metricsService.cancelTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_DWCS_TIME);
        Log.e(TAG, th, "BackgroundImage: sendImageIdToDevice: Failed to update image on Device", new Object[0]);
        if (th.getClass().isAssignableFrom(DeviceDownloadFailedException.class)) {
            DeviceDownloadFailedException deviceDownloadFailedException = (DeviceDownloadFailedException) th;
            this.uploadFileListener.onError(deviceDownloadFailedException);
            if (deviceDownloadFailedException.getErrorCode() == -1) {
                this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_DWCS_TIMEOUT, AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
                return;
            }
        } else {
            this.uploadFileListener.onError(new DeviceDownloadFailedException(th, -2));
        }
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_DWCS_ERROR, th.getMessage(), AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
    }

    public /* synthetic */ void lambda$sendImageIdToDevice$2$ViewBoxViewModel(BackgroundImage backgroundImage) {
        if (backgroundImage.isDownloading()) {
            this.uploadFileListener.onDownloading(backgroundImage);
            monitorDownloadOnDevice();
            return;
        }
        this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_DWCS_SUCCESS, AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        this.uploadFileListener.onSuccess(backgroundImage);
    }

    public /* synthetic */ void lambda$sendImageIdToDevice$3$ViewBoxViewModel(Throwable th) {
        this.metricsService.cancelTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_TIME);
        this.metricsService.cancelTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_DWCS_TIME);
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_DWCS_ERROR, th.getMessage(), AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        Log.e(TAG, th, "BackgroundImage: sendImageIdToDevice: Failed to update image on Device", new Object[0]);
        this.uploadFileListener.onError(new DeviceDownloadFailedException(th, -2));
    }

    public /* synthetic */ void lambda$uploadFile$0$ViewBoxViewModel(PhotoService.UploadResponse uploadResponse) {
        this.metricsService.recordTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_CDS_TIME);
        this.metricsService.startTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_DWCS_TIME, AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_CDS_SUCCESS, AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        sendImageIdToDevice(uploadResponse.getId(), uploadResponse.getUrl());
        String path = this.croppedImageUri.getPath();
        if (!new File(path).delete()) {
            Log.e(TAG, String.format("failed to delete staged file %s", path));
        }
    }

    public /* synthetic */ void lambda$uploadFile$1$ViewBoxViewModel(Throwable th) {
        this.metricsService.cancelTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_TIME);
        this.metricsService.cancelTimer(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_CDS_TIME);
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.KNIGHT_UPLOAD_CDS_ERROR, th.getMessage(), AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        Log.e(TAG, "BackgroundImage: uploadFile -> onError");
        this.uploadFileListener.onError(new Exception(th));
    }

    public void openTermsAndConditions() {
        launchThirdPartyUIActivity(AMAZON_DRIVE_TAC_URI, GeneratedOutlineSupport1.outline126("www.amazon.com"));
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    @Nullable
    public Bundle saveState() {
        return new Bundle();
    }

    public void saveUploadCroppedImage(@NonNull Bitmap bitmap) {
        if (saveOutput(bitmap)) {
            uploadFile();
        } else {
            this.uploadFileListener.onError(new FileNotFoundException("Unable to save Image into your device."));
        }
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void updateContentMode(int i) {
    }
}
