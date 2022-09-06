package com.amazon.alexa;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.davs.ArtifactManager;
import com.amazon.alexa.wakeword.davs.DavsClient;
import com.amazon.alexa.wakeword.davs.NetworkManager;
import com.amazon.alexa.wakeword.pryon.LocaleProvider;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: OfflinePromptsModule.java */
@Module
/* loaded from: classes.dex */
public class QYP {
    @Provides
    @Singleton
    @Named("offline_store")
    public SharedPreferences BIo(Context context) {
        return context.getSharedPreferences("offline_store", 0);
    }

    @Provides
    @Singleton
    @Named("offline_store")
    public File zZm(Context context) {
        return new File(context.getFilesDir(), "alexa-voice-sdk");
    }

    @Provides
    @Singleton
    public kHl zZm() {
        return new kHl();
    }

    @Provides
    @Singleton
    public JEn zZm(ArtifactManager artifactManager, DavsClient davsClient, NetworkManager networkManager, TimeProvider timeProvider, LocaleProvider localeProvider, kHl khl, Gju gju, @Named("offline_store") Lazy<SharedPreferences> lazy, @Named("offline_store") File file) {
        return new C0217lcZ(artifactManager, davsClient, networkManager, timeProvider, new mZG(this, lazy), localeProvider, khl, file, gju);
    }
}
