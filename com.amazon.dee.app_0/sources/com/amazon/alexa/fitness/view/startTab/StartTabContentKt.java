package com.amazon.alexa.fitness.view.startTab;

import com.amazon.alexa.fitness.api.UserPreferenceKey;
import kotlin.Metadata;
/* compiled from: StartTabContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0006\u0010\u0002\u001a\u00020\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"TAG", "", "isRouteMappingOn", "", "AlexaMobileAndroidFitnessUI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class StartTabContentKt {
    private static final String TAG = "AFX-StartTabContent";

    public static final boolean isRouteMappingOn() {
        return StartTabContent.Companion.getUserPreferenceStore().get(UserPreferenceKey.IsRouteTrackingEnabled) && StartTabContent.Companion.getLocationService().getAuthorizationStatus() && StartTabContent.Companion.getLocationService().getLocationStatus();
    }
}
