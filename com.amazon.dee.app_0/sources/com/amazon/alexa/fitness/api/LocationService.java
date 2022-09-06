package com.amazon.alexa.fitness.api;

import android.app.Activity;
import android.location.Location;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocationService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\tH&J \u0010\u0010\u001a\u00020\t2\u0016\u0010\u0011\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0004\u0012\u00020\t\u0018\u00010\u0012H&J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0015\u001a\u00020\tH&J\b\u0010\u0016\u001a\u00020\tH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/api/LocationService;", "", "authorizationStatus", "", "getAuthorizationStatus", "()Z", "locationStatus", "getLocationStatus", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/alexa/fitness/api/LocationServiceListener;", "removeListener", "requestLocationAccess", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "requestLocationOnce", "callback", "Lkotlin/Function1;", "Landroid/location/Location;", "requestToEnableLocation", "startUpdatingLocation", "stopUpdatingLocation", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface LocationService {
    void addListener(@NotNull LocationServiceListener locationServiceListener);

    boolean getAuthorizationStatus();

    boolean getLocationStatus();

    void removeListener(@NotNull LocationServiceListener locationServiceListener);

    void requestLocationAccess(@NotNull Activity activity);

    void requestLocationOnce();

    void requestLocationOnce(@Nullable Function1<? super Location, Unit> function1);

    void requestToEnableLocation(@NotNull Activity activity);

    void startUpdatingLocation();

    void stopUpdatingLocation();
}
