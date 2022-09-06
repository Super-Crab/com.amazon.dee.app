package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.device.messaging.ADM;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class AmazonMessagingModule {
    @Provides
    @ApplicationScope
    public ADM provideADM(Context context) {
        return new ADM(context);
    }
}
