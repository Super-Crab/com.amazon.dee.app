package com.amazon.dee.app.services.alexadevicebackground;

import androidx.annotation.NonNull;
import rx.Observable;
/* loaded from: classes12.dex */
public interface BackgroundImageService {
    public static final int MAX_GET_DEVICE_BACKGROUND_RETRIES = 10;
    public static final int MAX_GET_DOWNLOAD_RETRIES = 40;
    public static final int POLL_DELAY_MS = 500;

    @NonNull
    Observable<BackgroundImage> monitorDownload(BackgroundImage backgroundImage);

    @NonNull
    Observable<BackgroundImage> pollForUpdatedBackgroundImage();

    @NonNull
    Observable<BackgroundImage> update(BackgroundImage backgroundImage);
}
