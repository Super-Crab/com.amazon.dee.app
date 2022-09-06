package com.amazon.dee.app.services.alexadevicebackground;

import androidx.annotation.NonNull;
import com.amazon.dee.app.util.Observables;
import com.dee.app.http.CoralService;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class DeviceBackgroundImageService implements BackgroundImageService {
    BackgroundImage backgroundImage;
    private CoralService coralService;

    public DeviceBackgroundImageService(CoralService coralService) {
        this.coralService = coralService;
    }

    static /* synthetic */ Integer lambda$null$1(Throwable th, Integer num) {
        return num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Observable lambda$null$2(Observable observable, Integer num) {
        if (num.intValue() < 10) {
            return Observable.timer(500L, TimeUnit.MILLISECONDS);
        }
        return observable.flatMap($$Lambda$hmKclAA6n8ySLsW1I0cyNht1zyo.INSTANCE);
    }

    public /* synthetic */ Observable lambda$monitorDownload$6$DeviceBackgroundImageService(BackgroundImage backgroundImage) {
        return pollForUpdatedBackgroundImage().flatMap(new Func1() { // from class: com.amazon.dee.app.services.alexadevicebackground.-$$Lambda$DeviceBackgroundImageService$KhZIeyHEOC6G1_haJkG73JbZ0Hk
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DeviceBackgroundImageService.this.lambda$null$5$DeviceBackgroundImageService((BackgroundImage) obj);
            }
        }).retryWhen(Observables.exponentialBackoff(40));
    }

    public /* synthetic */ Observable lambda$null$0$DeviceBackgroundImageService(BackgroundImage backgroundImage) {
        if (this.backgroundImage.equals(backgroundImage)) {
            return Observable.just(backgroundImage);
        }
        if (backgroundImage.isDownloading()) {
            return Observable.just(backgroundImage);
        }
        return Observable.error(new DeviceDownloadFailedException("Value is still different, continue polling", -2));
    }

    public /* synthetic */ Observable lambda$null$5$DeviceBackgroundImageService(BackgroundImage backgroundImage) {
        if (this.backgroundImage.equals(backgroundImage)) {
            return Observable.just(backgroundImage);
        }
        if (backgroundImage.isDownloading()) {
            return Observable.error(new DeviceDownloadFailedException("Image is still downloading, continue polling", -1));
        }
        return Observable.error(new DeviceDownloadFailedException("Value is different, bail", -2));
    }

    public /* synthetic */ Observable lambda$update$4$DeviceBackgroundImageService(Void r2) {
        return pollForUpdatedBackgroundImage().flatMap(new Func1() { // from class: com.amazon.dee.app.services.alexadevicebackground.-$$Lambda$DeviceBackgroundImageService$2JSFy1MEubiUu22zDnyXUUgVvPw
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DeviceBackgroundImageService.this.lambda$null$0$DeviceBackgroundImageService((BackgroundImage) obj);
            }
        }).retryWhen($$Lambda$DeviceBackgroundImageService$L1xNBdCUl8pX3Ou3pUwBvl9nGso.INSTANCE);
    }

    @Override // com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService
    @NonNull
    public Observable<BackgroundImage> monitorDownload(BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this.coralService.request(String.format("/api/background-image?deviceSerialNumber=%s;deviceType=%s;softwareVersion=%s", this.backgroundImage.getDeviceSerialNumber(), this.backgroundImage.getDeviceType(), this.backgroundImage.getSoftwareVersion())).get().as(BackgroundImage.class).toObservable().subscribeOn(Schedulers.io()).switchMap(new Func1() { // from class: com.amazon.dee.app.services.alexadevicebackground.-$$Lambda$DeviceBackgroundImageService$r6vXquZcVWDPeDEqdyvy-thNWek
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DeviceBackgroundImageService.this.lambda$monitorDownload$6$DeviceBackgroundImageService((BackgroundImage) obj);
            }
        });
    }

    @Override // com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService
    @NonNull
    public Observable<BackgroundImage> pollForUpdatedBackgroundImage() {
        return this.coralService.request(String.format("/api/background-image?deviceSerialNumber=%s;deviceType=%s;softwareVersion=%s", this.backgroundImage.getDeviceSerialNumber(), this.backgroundImage.getDeviceType(), this.backgroundImage.getSoftwareVersion())).get().as(BackgroundImage.class).toObservable().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService
    @NonNull
    public Observable<BackgroundImage> update(BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        if (backgroundImage.isDelete()) {
            this.backgroundImage.setBackgroundImageType(BackgroundImage.DEFAULT_THEME);
        }
        return this.coralService.request("/api/background-image").post(this.backgroundImage).as(Void.class).toObservable().subscribeOn(Schedulers.io()).switchMap(new Func1() { // from class: com.amazon.dee.app.services.alexadevicebackground.-$$Lambda$DeviceBackgroundImageService$LVcc9f0wNkRjtVRn11Ck3rcjspY
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DeviceBackgroundImageService.this.lambda$update$4$DeviceBackgroundImageService((Void) obj);
            }
        });
    }
}
