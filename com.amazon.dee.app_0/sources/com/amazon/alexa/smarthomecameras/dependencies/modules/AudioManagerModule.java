package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.audio.DefaultAudioManager;
import com.amazon.alexa.smarthomecameras.rtcsc.CamerasAppClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public abstract class AudioManagerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static AudioManager providesAudioManager(Context context, CamerasAppClient camerasAppClient) {
        return new DefaultAudioManager(context, camerasAppClient);
    }
}
