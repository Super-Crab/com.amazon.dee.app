package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService;
import com.amazon.dee.app.services.alexadevicebackground.DeviceBackgroundImageService;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class AlexaDeviceBackgroundImageModule {
    Activity activity;

    @Provides
    @ApplicationScope
    public BackgroundImageService provideBackgroundImageService(CoralService coralService) {
        return new DeviceBackgroundImageService(coralService);
    }
}
