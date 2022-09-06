package com.amazon.alexa.location;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.core.app.EnhancedJobIntentService;
import androidx.core.app.JobIntentService;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
/* loaded from: classes9.dex */
public class GeofenceRestoreJobIntentService extends EnhancedJobIntentService {
    private static final int JOB_ID = 53720235;
    private final LazyComponent<CrashReporter> crashReporter;
    private final LocationPermissionService locationPermissionService;
    private final LazyComponent<LocationService> locationService;
    private final LazyComponent<Mobilytics> mobilytics;
    private static final String TAG = GeofenceRestoreJobIntentService.class.getSimpleName();
    private static final String COMPONENT_RE_REGISTER_GEOFENCE = MobilyticsUtil.getComponentName("re_register_geofence");

    public GeofenceRestoreJobIntentService() {
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.locationService = componentRegistry.getLazy(LocationService.class);
        this.crashReporter = componentRegistry.getLazy(CrashReporter.class);
        this.mobilytics = componentRegistry.getLazy(Mobilytics.class);
        this.locationPermissionService = new AlexaLocationPermissionService(this);
    }

    public static void schedule(Context context) {
        JobIntentService.enqueueWork(context, GeofenceRestoreJobIntentService.class, (int) JOB_ID, new Intent());
    }

    public /* synthetic */ void lambda$onHandleWork$0$GeofenceRestoreJobIntentService(List list) throws Throwable {
        String.format("[SUCCESS] Successfully restored %d geofences on device reboot.", Integer.valueOf(list.size()));
        String str = COMPONENT_RE_REGISTER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.RE_REGISTER_SUCCESS, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public /* synthetic */ void lambda$onHandleWork$1$GeofenceRestoreJobIntentService(Throwable th) throws Throwable {
        if (th instanceof LocationException) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("re_register_failure_");
            outline107.append(MobilyticsUtil.METRICS_NAME_ERROR_PART.mo7740get(((LocationException) th).getErrorCode()));
            String sb = outline107.toString();
            String str = COMPONENT_RE_REGISTER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(sb, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        } else {
            String str2 = COMPONENT_RE_REGISTER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence("re_register_failure_unknown", true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
        th.printStackTrace();
        th.getMessage();
        ExceptionRecordUtil.recordExceptions(TAG, "locationService.restoreGeofences()", th, this.crashReporter);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        String str = COMPONENT_RE_REGISTER_GEOFENCE;
        this.mobilytics.mo10268get().recordTimer(this.mobilytics.mo10268get().createTimer(MobilyticsUtil.MetricsID.START_UPTIME, str, str, SystemClock.elapsedRealtime(), false, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID));
        if (this.locationPermissionService.hasFullLocationPermission()) {
            this.locationService.mo10268get().restoreGeofences().subscribe(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceRestoreJobIntentService$IdGa2_NdNiazn6BIIMnoFNBiCXM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    GeofenceRestoreJobIntentService.this.lambda$onHandleWork$0$GeofenceRestoreJobIntentService((List) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceRestoreJobIntentService$xmQzxlwplEKDLINaSTHFOZNOtKM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    GeofenceRestoreJobIntentService.this.lambda$onHandleWork$1$GeofenceRestoreJobIntentService((Throwable) obj);
                }
            });
        }
    }
}
