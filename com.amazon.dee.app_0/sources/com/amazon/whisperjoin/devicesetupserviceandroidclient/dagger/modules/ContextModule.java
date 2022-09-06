package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import android.app.Application;
import android.content.Context;
import com.jakewharton.threetenabp.AndroidThreeTen;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class ContextModule {
    private final Context mContext;

    public ContextModule(Context context) {
        this.mContext = context;
        AndroidThreeTen.init(context);
    }

    @Provides
    public Context providesContext() {
        return this.mContext;
    }

    public ContextModule(Application application) {
        this.mContext = application.getBaseContext();
    }
}
