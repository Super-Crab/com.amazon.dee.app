package com.amazon.rtcsc.capabilityagent.common.dependencies;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.rtcsc.capabilityagent.avs.RtcscContextProvider;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes13.dex */
public class ApplicationModule {
    private final Context mContext;

    public ApplicationModule(@NonNull Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Context providesApplicationContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Handler providesMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    @Singleton
    public AlexaServicesConnection providesRtcscAlexaServicesConnection(@NonNull Context context) {
        return new AlexaServicesConnection(this.mContext);
    }

    @Provides
    @Singleton
    public RtcscContextProvider providesRtcscContextProvider() {
        return new RtcscContextProvider();
    }
}
