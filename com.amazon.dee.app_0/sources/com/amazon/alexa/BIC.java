package com.amazon.alexa;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* compiled from: GooglePlayModule.java */
@Module
/* loaded from: classes.dex */
public class BIC {
    @Provides
    @Singleton
    public GoogleApiClient zZm(Context context) {
        return new GoogleApiClient.Builder(context).addApi(LocationServices.API).build();
    }
}
