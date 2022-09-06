package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.location.LocationProvider;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class LocationModule {
    @Provides
    @ApplicationScope
    public LocationProvider provideLocationProvider(Context context) {
        return new LocationProvider(context);
    }
}
