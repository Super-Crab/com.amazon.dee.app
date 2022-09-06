package com.amazon.alexa.location;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
/* loaded from: classes9.dex */
public class GoogleApiService {
    private final Context context;

    public GoogleApiService(Context context) {
        this.context = context;
    }

    public boolean isGoogleApiAvailable() {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.context) == 0;
    }
}
