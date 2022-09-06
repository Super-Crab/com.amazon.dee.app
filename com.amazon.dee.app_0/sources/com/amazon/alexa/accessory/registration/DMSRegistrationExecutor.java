package com.amazon.alexa.accessory.registration;

import android.annotation.SuppressLint;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.BuildStageProvider;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.internal.http.HttpCall;
import com.amazon.alexa.accessory.internal.http.HttpMethod;
import com.amazon.alexa.accessory.internal.http.HttpRequest;
import com.amazon.alexa.accessory.internal.http.HttpStatusUnsuccessfulException;
import com.amazon.alexa.accessory.internal.http.JsonHttpBody;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.MetricsUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.HttpRequestConstants;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.util.Locale;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class DMSRegistrationExecutor implements RegistrationExecutor {
    private static final String BETA_DMS_ENDPOINT = "https://firs-ta-g7g.vipinteg.amazon.com/FirsProxy/";
    private static final String DEREGISTRATION_ENDPOINT = "deregisterAssociatedDeviceV2";
    private static final String GAMMA_DMS_ENDPOINT = "https://firs-ta-g7g-preprod.amazon.com/FirsProxy/";
    private static final String PROD_DMS_ENDPOINT = "https://firs-ta-g7g.amazon.com/FirsProxy/";
    private static final String REGISTRATION_ENDPOINT = "registerAssociatedDeviceV2";
    private static final String TAG = "DMSRegistrationExecutor:";
    private final BuildStageProvider buildStageProvider;
    private final DeviceSupplierV2 deviceSupplier;

    /* renamed from: com.amazon.alexa.accessory.registration.DMSRegistrationExecutor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$BuildStageProvider$BuildStage = new int[BuildStageProvider.BuildStage.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$BuildStageProvider$BuildStage[BuildStageProvider.BuildStage.PROD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$BuildStageProvider$BuildStage[BuildStageProvider.BuildStage.GAMMA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$BuildStageProvider$BuildStage[BuildStageProvider.BuildStage.BETA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public DMSRegistrationExecutor(BuildStageProvider buildStageProvider, DeviceSupplierV2 deviceSupplierV2) {
        Preconditions.notNull(buildStageProvider, "buildStageProvider");
        Preconditions.notNull(deviceSupplierV2, "deviceSupplier");
        this.buildStageProvider = buildStageProvider;
        this.deviceSupplier = deviceSupplierV2;
    }

    private String getDeregistrationEndpoint() throws IOException {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), getEndpointPrefix(), DEREGISTRATION_ENDPOINT);
    }

    private String getEndpointPrefix() throws IOException {
        BuildStageProvider.BuildStage buildStage = this.buildStageProvider.getBuildStage();
        int ordinal = this.buildStageProvider.getBuildStage().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return GAMMA_DMS_ENDPOINT;
            }
            if (ordinal != 2) {
                throw new IOException(String.format(Locale.US, "DMSRegistrationExecutor: Unable to determine build stage, determined %s", buildStage));
            }
            return BETA_DMS_ENDPOINT;
        }
        return PROD_DMS_ENDPOINT;
    }

    private String getRegistrationEndpoint() throws IOException {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), getEndpointPrefix(), REGISTRATION_ENDPOINT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordDeregistrationHasData$12(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.DEREGISTRATION_HAS_DATA, "unknown", z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordDeregistrationRequestPrepared$20(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.DEREGISTRATION_PREPARED_REQUEST, "unknown", z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordDeregistrationStatusCode$16(int i, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.DEREGISTRATION_STATUS_CODE, GeneratedOutlineSupport1.outline49("unknown:", i), true, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordDeregistrationSuccessMetric$8(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.DEREGISTRATION_SUCCESS, "unknown", z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordMetricForThrowable$4(String str, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(str, "unknown", true, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordRegistrationHasData$10(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.REGISTRATION_HAS_DATA, "unknown", z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordRegistrationRequestPrepared$18(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.REGISTRATION_PREPARED_REQUEST, "unknown", z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordRegistrationStatusCode$14(int i, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.REGISTRATION_STATUS_CODE, GeneratedOutlineSupport1.outline49("unknown:", i), true, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordRegistrationSuccessMetric$6(boolean z, Throwable th) throws Throwable {
        Logger.e("%s failed to get deviceType, publish metric with unknown", th, TAG);
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.REGISTRATION_SUCCESS, "unknown", z, null);
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationExecutor
    public Completable deregister(final DeviceDeregistrationRequest deviceDeregistrationRequest, final User user) {
        Preconditions.notNull(deviceDeregistrationRequest, "deviceDeregistrationRequest");
        Preconditions.notNull(user, "user");
        return Completable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$D5-mrgzPdLUH8bZKsmHHE391FW4
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return DMSRegistrationExecutor.this.lambda$deregister$23$DMSRegistrationExecutor(user, deviceDeregistrationRequest);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationExecutor
    public Completable disassociate(DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier, User user) {
        return Completable.error(new UnsupportedOperationException("Available 11/28/2018"));
    }

    public /* synthetic */ CompletableSource lambda$deregister$23$DMSRegistrationExecutor(User user, final DeviceDeregistrationRequest deviceDeregistrationRequest) throws Throwable {
        if (user.getAccessToken() == null) {
            recordDeregistrationRequestPrepared(false, deviceDeregistrationRequest);
            return Completable.error(new IllegalArgumentException("No access token available for user " + user));
        }
        recordDeregistrationRequestPrepared(true, deviceDeregistrationRequest);
        DeregisterAssociatedDeviceV2Request deregisterAssociatedDeviceV2Request = new DeregisterAssociatedDeviceV2Request(deviceDeregistrationRequest);
        String languageTag = Locale.getDefault().toLanguageTag();
        String deregistrationEndpoint = getDeregistrationEndpoint();
        HttpRequest build = HttpRequest.createBuilder().method(HttpMethod.POST).url(deregistrationEndpoint).header("Content-Type", HttpRequestConstants.JSON_CONTENT_TYPE_VALUE).header("Content-Encoding", "amz-1.0").header("x-amz-access-token", user.getAccessToken()).header("Accept-Language", languageTag).body(new JsonHttpBody(deregisterAssociatedDeviceV2Request)).build();
        Logger.d("DMSRegistrationExecutor: Sending request for deregister: %s, %s, %s", deregistrationEndpoint, deregisterAssociatedDeviceV2Request.toJsonObject().toString(4), languageTag);
        return build.newCall().executeSingle().doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$HvbUtsFKhLK0CJ1qzFHYZTyzZKA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.this.lambda$null$21$DMSRegistrationExecutor(deviceDeregistrationRequest, (Throwable) obj);
            }
        }).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$BUVLzm8icEc3cBNqxWib6AVFwHI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DMSRegistrationExecutor.this.lambda$null$22$DMSRegistrationExecutor(deviceDeregistrationRequest, (HttpCall.HttpResult) obj);
            }
        });
    }

    public /* synthetic */ DeviceRegistration lambda$null$0$DMSRegistrationExecutor(String str, DeviceRegistrationRequest deviceRegistrationRequest, HttpCall.HttpResult httpResult) throws Throwable {
        int i = httpResult.statuseCode;
        boolean z = false;
        if (i >= 200 && i < 300) {
            Logger.d("DMSRegistrationExecutor: Received response for register " + httpResult);
        } else {
            Logger.e("DMSRegistrationExecutor: Received error response for register request: %s response %s", str, httpResult);
        }
        recordRegistrationStatusCode(httpResult.statuseCode, deviceRegistrationRequest);
        int i2 = httpResult.statuseCode;
        if (i2 >= 200 && i2 < 300) {
            if (httpResult.response.length != 0) {
                z = true;
            }
            recordRegistrationHasData(z, deviceRegistrationRequest);
            DeviceRegistration deviceRegistration = new DeviceRegistration(deviceRegistrationRequest, DeviceRegistrationResponse.FACTORY.mo1239create(new JSONObject(new String(httpResult.response)).getJSONObject("response")), System.currentTimeMillis());
            recordRegistrationSuccessMetric(true, deviceRegistrationRequest);
            return deviceRegistration;
        }
        throw new HttpStatusUnsuccessfulException(httpResult.statuseCode);
    }

    public /* synthetic */ void lambda$null$21$DMSRegistrationExecutor(DeviceDeregistrationRequest deviceDeregistrationRequest, Throwable th) throws Throwable {
        recordDeregistrationSuccessMetric(false, deviceDeregistrationRequest);
    }

    public /* synthetic */ CompletableSource lambda$null$22$DMSRegistrationExecutor(DeviceDeregistrationRequest deviceDeregistrationRequest, HttpCall.HttpResult httpResult) throws Throwable {
        Logger.d("DMSRegistrationExecutor: Received response for deregister " + httpResult);
        recordDeregistrationStatusCode(httpResult.statuseCode, deviceDeregistrationRequest);
        recordDeregistrationHasData(httpResult.response.length != 0, deviceDeregistrationRequest);
        if (httpResult.statuseCode == 200) {
            recordDeregistrationSuccessMetric(true, deviceDeregistrationRequest);
            return Completable.complete();
        }
        recordDeregistrationSuccessMetric(false, deviceDeregistrationRequest);
        throw new IOException("Invalid response for deregister request " + httpResult);
    }

    public /* synthetic */ SingleSource lambda$register$1$DMSRegistrationExecutor(final DeviceRegistrationRequest deviceRegistrationRequest, User user) throws Throwable {
        RegisterAssociatedDeviceV2Request registerAssociatedDeviceV2Request = new RegisterAssociatedDeviceV2Request(deviceRegistrationRequest);
        if (user.getAccessToken() == null) {
            recordRegistrationRequestPrepared(false, deviceRegistrationRequest);
            return Single.error(new IllegalArgumentException("No access token available for user " + user));
        }
        recordRegistrationRequestPrepared(true, deviceRegistrationRequest);
        String languageTag = Locale.getDefault().toLanguageTag();
        String registrationEndpoint = getRegistrationEndpoint();
        HttpRequest build = HttpRequest.createBuilder().method(HttpMethod.POST).url(registrationEndpoint).header("Content-Type", HttpRequestConstants.JSON_CONTENT_TYPE_VALUE).header("Content-Encoding", "amz-1.0").header("x-amz-access-token", user.getAccessToken()).header("Accept-Language", languageTag).body(new JsonHttpBody(registerAssociatedDeviceV2Request)).build();
        final String jSONObject = registerAssociatedDeviceV2Request.toJsonObject().toString(4);
        Logger.d("DMSRegistrationExecutor: Sending request for register: %s %s, %s", registrationEndpoint, jSONObject, languageTag);
        return build.newCall().executeSingle().map(new Function() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$cyT87qK136GjX8ObWfBJ3TmplYo
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DMSRegistrationExecutor.this.lambda$null$0$DMSRegistrationExecutor(jSONObject, deviceRegistrationRequest, (HttpCall.HttpResult) obj);
            }
        });
    }

    public /* synthetic */ void lambda$register$2$DMSRegistrationExecutor(DeviceRegistrationRequest deviceRegistrationRequest, Throwable th) throws Throwable {
        recordMetricForThrowable(th, deviceRegistrationRequest);
        recordRegistrationSuccessMetric(false, deviceRegistrationRequest);
    }

    @VisibleForTesting
    void recordDeregistrationHasData(final boolean z, DeviceDeregistrationRequest deviceDeregistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceDeregistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$Rw8o4Ma6wZKUfLZ6VZ3_Uz6I9Jg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.DEREGISTRATION_HAS_DATA, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$ambMhIU1FzHs_HN_GjitLq_Gwso
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.lambda$recordDeregistrationHasData$12(z, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    void recordDeregistrationRequestPrepared(final boolean z, DeviceDeregistrationRequest deviceDeregistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceDeregistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$yOPkmdpXQaDw6ITC9hd0XLO1KyQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.DEREGISTRATION_PREPARED_REQUEST, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$popHXNV96YMIgUzF4rrghgDJdP0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.lambda$recordDeregistrationRequestPrepared$20(z, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    void recordDeregistrationStatusCode(final int i, DeviceDeregistrationRequest deviceDeregistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceDeregistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$GeDtwi_u6fVmJzwGColFzlqeel8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.DEREGISTRATION_STATUS_CODE, GeneratedOutlineSupport1.outline74((String) obj, ":", i), true, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$Ncci3HyhM0tUJTVByQSaVyL3rds
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.lambda$recordDeregistrationStatusCode$16(i, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    void recordDeregistrationSuccessMetric(final boolean z, DeviceDeregistrationRequest deviceDeregistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceDeregistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$Std6ld_FvDHB5eyv85ZZ9AdvtLI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.DEREGISTRATION_SUCCESS, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$NdA88yIStWXWDLY_MgSA4RgSYyY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.lambda$recordDeregistrationSuccessMetric$8(z, (Throwable) obj);
            }
        });
    }

    @SuppressLint({"CheckResult"})
    @VisibleForTesting
    void recordMetricForThrowable(Throwable th, DeviceRegistrationRequest deviceRegistrationRequest) {
        final String createMetricNameFromThrowable = MetricsUtils.createMetricNameFromThrowable(MetricsConstants.Dms.REGISTRATION_FAILURE_PREFIX, th);
        MetricsConstants.Dms.getDeviceType(deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$HdChl15d09VSTWRIj8HM64eNZvg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(createMetricNameFromThrowable, (String) obj, true, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$LGn6Zo28mlSfTzFUr6rKh2ZXaqg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.lambda$recordMetricForThrowable$4(createMetricNameFromThrowable, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    void recordRegistrationHasData(final boolean z, DeviceRegistrationRequest deviceRegistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$m0gU6-C1jTSDZnHe230g7zFR3uc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.REGISTRATION_HAS_DATA, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$OEhvxPezxfxIBJXMW3Hg-gO6D_Q
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.lambda$recordRegistrationHasData$10(z, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    void recordRegistrationRequestPrepared(final boolean z, DeviceRegistrationRequest deviceRegistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$Do8DOoibz5zIkjZ0j55DHx9BjWI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.REGISTRATION_PREPARED_REQUEST, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$6AC9jhrbB7qfiW3k_c4OitFGVbs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.lambda$recordRegistrationRequestPrepared$18(z, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    void recordRegistrationStatusCode(final int i, DeviceRegistrationRequest deviceRegistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$fIxbTOAgn01aMYJmL-aF93XJNYs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dms.REGISTRATION_STATUS_CODE, GeneratedOutlineSupport1.outline74((String) obj, ":", i), true, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$hZWz-lWCL3sfHc2g9pqDx-g_oNE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.lambda$recordRegistrationStatusCode$14(i, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    void recordRegistrationSuccessMetric(final boolean z, DeviceRegistrationRequest deviceRegistrationRequest) {
        MetricsConstants.Dms.getDeviceType(deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier(), this.deviceSupplier).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$kZtHqhmtGP5560Cr89C61TDkZoE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GeneratedOutlineSupport1.outline171(MetricsConstants.Dms.REGISTRATION_SUCCESS, (String) obj, z, null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$gIaQ3_5x668Wl9Tx83e4vIGT_yc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.lambda$recordRegistrationSuccessMetric$6(z, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationExecutor
    public Single<DeviceRegistration> register(final DeviceRegistrationRequest deviceRegistrationRequest, final User user) {
        Preconditions.notNull(deviceRegistrationRequest, "deviceRegistrationRequest");
        Preconditions.notNull(user, "user");
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$aZmGeguSvsUFwOA1fa8JJeohNAs
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return DMSRegistrationExecutor.this.lambda$register$1$DMSRegistrationExecutor(deviceRegistrationRequest, user);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.registration.-$$Lambda$DMSRegistrationExecutor$5jAL5xTM2NKKhKwdZhXT0P7HfwI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DMSRegistrationExecutor.this.lambda$register$2$DMSRegistrationExecutor(deviceRegistrationRequest, (Throwable) obj);
            }
        }).subscribeOn(Schedulers.io());
    }
}
