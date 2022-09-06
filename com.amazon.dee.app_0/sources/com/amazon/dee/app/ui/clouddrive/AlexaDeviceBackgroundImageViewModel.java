package com.amazon.dee.app.ui.clouddrive;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.dee.app.framework.ViewModel;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImage;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.photos.PhotoService;
import com.dee.app.http.CoralServiceException;
import com.dee.app.metrics.MetricsService;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class AlexaDeviceBackgroundImageViewModel implements ViewModel {
    private static final String TAG = Log.tag(AlexaDeviceBackgroundImageViewModel.class);
    private final Activity activity;
    private final BackgroundImage backgroundImage;
    private final BackgroundImageService deviceBackgroundImageService;
    private final IdentityService identityService;
    private final InitializeServiceListener initializeServiceListener;
    private final MetricsService metricsService;
    private final PhotoService photoService;
    private final RemoveFileListener removeFileListener;

    /* loaded from: classes12.dex */
    public interface InitializeServiceListener {
        void onError(Exception exc);

        void onSucess();
    }

    /* loaded from: classes12.dex */
    public interface RemoveFileListener {
        void onError(Exception exc);

        void onSucess(BackgroundImage backgroundImage);
    }

    public AlexaDeviceBackgroundImageViewModel(@NonNull Activity activity, @NonNull IdentityService identityService, @NonNull PhotoService photoService, @NonNull BackgroundImageService backgroundImageService, @NonNull MetricsService metricsService, @NonNull InitializeServiceListener initializeServiceListener, @NonNull RemoveFileListener removeFileListener, @NonNull BackgroundImage backgroundImage) {
        this.activity = activity;
        this.identityService = identityService;
        this.photoService = photoService;
        this.deviceBackgroundImageService = backgroundImageService;
        this.metricsService = metricsService;
        this.initializeServiceListener = initializeServiceListener;
        this.removeFileListener = removeFileListener;
        this.backgroundImage = backgroundImage;
    }

    private void removeImagefromAmazonDrive(String str) {
        this.photoService.deleteImage(str).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$AlexaDeviceBackgroundImageViewModel$OnWYgF0Iu6yIdm2WbczyvzBp3oY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlexaDeviceBackgroundImageViewModel.this.lambda$removeImagefromAmazonDrive$4$AlexaDeviceBackgroundImageViewModel((PhotoService.DeleteResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$AlexaDeviceBackgroundImageViewModel$zzHH5RkId2h6uX8Sc39iXmj6cQw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlexaDeviceBackgroundImageViewModel.this.lambda$removeImagefromAmazonDrive$5$AlexaDeviceBackgroundImageViewModel((Throwable) obj);
            }
        });
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void destroy() {
    }

    public void initializePhotoService() {
        if (this.identityService.isAuthenticated(TAG)) {
            this.photoService.initialize().subscribe(new Action0() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$AlexaDeviceBackgroundImageViewModel$f3D-hVdSkUuQBy2B70MTnrLGGGk
                @Override // rx.functions.Action0
                public final void call() {
                    AlexaDeviceBackgroundImageViewModel.this.lambda$initializePhotoService$0$AlexaDeviceBackgroundImageViewModel();
                }
            }, new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$AlexaDeviceBackgroundImageViewModel$vNpsaZHFFfFvC6zR2OSY6dHa-Js
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AlexaDeviceBackgroundImageViewModel.this.lambda$initializePhotoService$1$AlexaDeviceBackgroundImageViewModel((Throwable) obj);
                }
            });
            return;
        }
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.CONNECT_TO_CLOUD_DRIVE_ERROR, "There was an error connecting to Photo Service", AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        Log.e(TAG, "There was an error connecting to Photo Service");
        this.initializeServiceListener.onError(new CloudDriveException("There was an error connecting to Photo Service"));
    }

    public /* synthetic */ void lambda$initializePhotoService$0$AlexaDeviceBackgroundImageViewModel() {
        this.initializeServiceListener.onSucess();
    }

    public /* synthetic */ void lambda$initializePhotoService$1$AlexaDeviceBackgroundImageViewModel(Throwable th) {
        this.initializeServiceListener.onError(new Exception(th));
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.CONNECT_TO_CLOUD_DRIVE_ERROR, th.getMessage(), AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        Log.e(TAG, "BackgroundImage: initializePhotoService -> error");
    }

    public /* synthetic */ void lambda$removeBackgroundImage$2$AlexaDeviceBackgroundImageViewModel(String str, BackgroundImage backgroundImage) {
        this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.KNIGHT_REMOVE_DWCS_SUCCESS, AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        removeImagefromAmazonDrive(str);
    }

    public /* synthetic */ void lambda$removeBackgroundImage$3$AlexaDeviceBackgroundImageViewModel(Throwable th) {
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.KNIGHT_REMOVE_DWCS_ERROR, th.getMessage(), AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        Log.e(TAG, th, "BackgroundImage: removeBackgroundImage-Failed to update background image on Device", new Object[0]);
        this.removeFileListener.onError(new CoralServiceException(th));
    }

    public /* synthetic */ void lambda$removeImagefromAmazonDrive$4$AlexaDeviceBackgroundImageViewModel(PhotoService.DeleteResponse deleteResponse) {
        this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.KNIGHT_REMOVE_CDS_SUCCESS, AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        this.backgroundImage.setBackgroundImageID("");
        this.backgroundImage.setBackgroundImageURL("");
        this.backgroundImage.setBackgroundImageType(BackgroundImage.DEFAULT_THEME);
        this.removeFileListener.onSucess(this.backgroundImage);
    }

    public /* synthetic */ void lambda$removeImagefromAmazonDrive$5$AlexaDeviceBackgroundImageViewModel(Throwable th) {
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.KNIGHT_REMOVE_CDS_ERROR, th.getMessage(), AlexaMetricsConstants.MetricsComponents.CLOUD_DRIVE_SERVICE, null);
        this.removeFileListener.onError(new Exception(th));
    }

    public void removeBackgroundImage() {
        final String backgroundImageID = this.backgroundImage.getBackgroundImageID();
        this.backgroundImage.setBackgroundImageID("");
        this.backgroundImage.setBackgroundImageURL("");
        this.backgroundImage.setBackgroundImageType(BackgroundImage.DELETE_PERSONAL_PHOTO);
        this.deviceBackgroundImageService.update(this.backgroundImage).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$AlexaDeviceBackgroundImageViewModel$CTRWnb_CCwdHtdOcOQdW8sOKAwU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlexaDeviceBackgroundImageViewModel.this.lambda$removeBackgroundImage$2$AlexaDeviceBackgroundImageViewModel(backgroundImageID, (BackgroundImage) obj);
            }
        }, new Action1() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$AlexaDeviceBackgroundImageViewModel$T9mhQeRdSF5yQko4coKJPv4wMSg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlexaDeviceBackgroundImageViewModel.this.lambda$removeBackgroundImage$3$AlexaDeviceBackgroundImageViewModel((Throwable) obj);
            }
        });
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    @Nullable
    public Bundle saveState() {
        return null;
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void updateContentMode(int i) {
    }
}
