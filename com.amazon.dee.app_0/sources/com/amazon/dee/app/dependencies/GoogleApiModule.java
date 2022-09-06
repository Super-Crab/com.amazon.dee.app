package com.amazon.dee.app.dependencies;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class GoogleApiModule {
    @Provides
    @ApplicationScope
    public GoogleApiAvailability provideGoogleApiAvailability() {
        return GoogleApiAvailability.getInstance();
    }

    @Provides
    @ApplicationScope
    public FirebaseInstanceId provideInstanceID() {
        return FirebaseInstanceId.getInstance();
    }
}
