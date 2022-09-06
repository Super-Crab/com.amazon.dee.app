package com.amazon.alexa.enrollment.unified.speakerid.api;

import android.text.TextUtils;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.EnrollmentConfiguration;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.EnrollmentMetadata;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.EnrollmentStates;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.GetVoiceEnrollmentStatusResponse;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.StartVoiceEnrollmentRequest;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.StartVoiceEnrollmentResponse;
import com.amazon.alexa.enrollment.unified.speakerid.metrics.AmpdMetricsRecorder;
import com.amazon.alexa.enrollment.unified.speakerid.utils.EnrollmentUtil;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes7.dex */
public class EnrollmentAPI {
    private static final String GET_ENROLLMENT_STATUS = "/api/speakers/enrollment/getVoiceEnrollmentStatus";
    private static final String GET_ENROLLMENT_STATUS_QUERY_PARAMS = "?deviceSerialNumber=%s;deviceType=%s;personId=%s;personECID=%s;";
    private static final String HEADER_HTTP_AUTHORIZATION = "Authorization";
    private static final String HEADER_HTTP_AUTHORIZATION_PREFIX = "Bearer ";
    private static final String HEADER_HTTP_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_HTTP_CONTENT_TYPE_JSON = "application/json";
    private static final String PRIVACY_SETTINGS_PATH = "/v2/accounts/%s/settings/%s";
    private static final String START_ENROLLMENT_PATH = "/api/speakers/enrollment/startVoiceEnrollment";
    private static final String TAG = "EnrollmentAPI";
    private static final String VOX_ENROLLMENT_CONTEXT = "VOX_ENROLLMENT";
    private EnrollmentConfiguration apiConfiguration = EnrollmentConfiguration.getInstance();
    private final CoralService coralService;
    private final DeviceInformation deviceInformation;
    private final EnvironmentService environmentService;
    private final OkHttpClient httpRestClient;
    private final IdentityService identityService;
    private final Locale locale;
    private final AmpdMetricsRecorder metricsRecorder;
    private final PersonIdProvider personIdProvider;

    @Inject
    public EnrollmentAPI(CoralService coralService, DeviceInformation deviceInformation, PersonIdProvider personIdProvider, IdentityService identityService, Locale locale, AmpdMetricsRecorder ampdMetricsRecorder, EnvironmentService environmentService, OkHttpClient okHttpClient) {
        this.coralService = coralService;
        this.deviceInformation = deviceInformation;
        this.personIdProvider = personIdProvider;
        this.identityService = identityService;
        this.locale = locale;
        this.metricsRecorder = ampdMetricsRecorder;
        this.environmentService = environmentService;
        this.httpRestClient = okHttpClient;
    }

    private String callAmazonAlexaAPI(String str, String str2) throws Exception {
        String alexaAPIEndpoint = EnrollmentUtil.getAlexaAPIEndpoint(this.environmentService);
        String format = String.format(PRIVACY_SETTINGS_PATH, str, str2);
        String str3 = TAG;
        Log.i(str3, "Api path " + alexaAPIEndpoint + format);
        StringBuilder sb = new StringBuilder();
        sb.append(alexaAPIEndpoint);
        sb.append(format);
        Request.Builder addHeader = new Request.Builder().url(HttpUrl.parse(sb.toString())).addHeader("Content-Type", "application/json");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(HEADER_HTTP_AUTHORIZATION_PREFIX);
        outline107.append(getAccessToken());
        Request build = addHeader.addHeader("Authorization", outline107.toString()).build();
        AmpdMetricsRecorder ampdMetricsRecorder = this.metricsRecorder;
        MobilyticsMetricsTimer startTimer = ampdMetricsRecorder.startTimer("AMAZON_ALEXA_API_CALL_TIME_" + str2);
        Response execute = this.httpRestClient.newCall(build).execute();
        this.metricsRecorder.finishTimer(startTimer);
        AmpdMetricsRecorder ampdMetricsRecorder2 = this.metricsRecorder;
        ampdMetricsRecorder2.recordCounter("AMAZON_ALEXA_API_CALL_SUCCESS_" + str2);
        return execute.body().string();
    }

    private String fetchAdultCommsId(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.personIdProvider.getPersonId();
    }

    private String fetchPersonId(String str) {
        return TextUtils.isEmpty(str) ? this.personIdProvider.getPersonId() : str;
    }

    private String getAccessToken() throws IllegalAccessException {
        String accessToken = this.identityService.getAccessToken(EnrollmentAPI.class.getSimpleName());
        if (accessToken == null || TextUtils.isEmpty(accessToken)) {
            Log.e(TAG, "Empty accessToken");
            throw new IllegalAccessException("Empty accessToken");
        }
        return accessToken;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: isValidEnrollmentState */
    public boolean lambda$startEnrollment$2$EnrollmentAPI(StartVoiceEnrollmentResponse startVoiceEnrollmentResponse) {
        String name = startVoiceEnrollmentResponse.getVoiceEnrollmentStatus().getEnrollmentState().name();
        return !EnrollmentStates.DEVICE_NOT_AVAILABLE.name().equalsIgnoreCase(name) && !EnrollmentStates.INELIGIBLE_UNSUPPORTED_LOCALE.name().equalsIgnoreCase(name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getSettingsValueForKey$6(String str, String str2) throws Throwable {
        String str3 = TAG;
        Log.i(str3, "GetSettingsValue For " + str + " Key response is: " + str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getVoiceEnrollmentStatus$4(GetVoiceEnrollmentStatusResponse getVoiceEnrollmentStatusResponse) throws Throwable {
        String str = TAG;
        Log.i(str, "Get enrollment status response path: " + getVoiceEnrollmentStatusResponse);
    }

    public GetVoiceEnrollmentStatusResponse callGetVoiceEnrollmentStatusApi(String str, String str2) throws Exception {
        String outline72 = GeneratedOutlineSupport1.outline72(GET_ENROLLMENT_STATUS, String.format(GET_ENROLLMENT_STATUS_QUERY_PARAMS, this.deviceInformation.getDeviceSerialNumber(), this.deviceInformation.getDeviceType(), str, str2));
        String str3 = TAG;
        Log.i(str3, "Get enrollment status request path: " + outline72);
        MobilyticsMetricsTimer startTimer = this.metricsRecorder.startTimer("GET_VOICE_ENROLL_STATUS_TIME");
        GetVoiceEnrollmentStatusResponse getVoiceEnrollmentStatusResponse = (GetVoiceEnrollmentStatusResponse) this.coralService.request(outline72).get().as(GetVoiceEnrollmentStatusResponse.class).execute();
        this.metricsRecorder.finishTimer(startTimer);
        this.metricsRecorder.recordCounter("GET_VOICE_ENROLL_STATUS_SUCCESS");
        return getVoiceEnrollmentStatusResponse;
    }

    public StartVoiceEnrollmentResponse callStartVoiceEnrollmentApi(EnrollmentMetadata enrollmentMetadata) throws Exception {
        String enrollmentContext = enrollmentMetadata.getEnrollmentContext();
        String personECID = enrollmentMetadata.getPersonECID();
        String childCustomerId = enrollmentMetadata.getChildCustomerId();
        String personId = enrollmentMetadata.getPersonId();
        UserIdentity user = this.identityService.getUser(EnrollmentAPI.class.getSimpleName());
        String deviceSerialNumber = this.deviceInformation.getDeviceSerialNumber();
        String deviceType = this.deviceInformation.getDeviceType();
        String marketplaceId = user.getMarketplace().getObfuscatedId().toString();
        if (!TextUtils.isEmpty(enrollmentContext)) {
            AmpdMetricsRecorder ampdMetricsRecorder = this.metricsRecorder;
            ampdMetricsRecorder.recordCounter("ENROLLMENT_CONTEXT_" + enrollmentContext);
        } else {
            enrollmentContext = VOX_ENROLLMENT_CONTEXT;
        }
        StartVoiceEnrollmentRequest startVoiceEnrollmentRequest = new StartVoiceEnrollmentRequest(fetchPersonId(personId), personECID, deviceType, deviceSerialNumber, this.locale.toString(), marketplaceId, enrollmentContext, true, childCustomerId, fetchAdultCommsId(personId));
        String str = TAG;
        Log.i(str, "Request to start voice enrollment: " + startVoiceEnrollmentRequest);
        MobilyticsMetricsTimer startTimer = this.metricsRecorder.startTimer("START_VOICE_ENROLLMENT_TIME");
        StartVoiceEnrollmentResponse startVoiceEnrollmentResponse = (StartVoiceEnrollmentResponse) this.coralService.request(START_ENROLLMENT_PATH).post(startVoiceEnrollmentRequest).as(StartVoiceEnrollmentResponse.class).execute();
        this.metricsRecorder.finishTimer(startTimer);
        this.metricsRecorder.recordCounter("START_VOICE_ENROLLMENT_SUCCESS");
        return startVoiceEnrollmentResponse;
    }

    public Single<String> getSettingsValueForKey(final String str) {
        final String id = this.identityService.getUser(EnrollmentAPI.class.getSimpleName()).getId();
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.enrollment.unified.speakerid.api.-$$Lambda$EnrollmentAPI$sc91tg6Y9xv_PwLYun7OHGpj8T4
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                EnrollmentAPI.this.lambda$getSettingsValueForKey$5$EnrollmentAPI(id, str, singleEmitter);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.api.-$$Lambda$EnrollmentAPI$uuqumRTv3OW_wtgjANlT1v-ErFk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                EnrollmentAPI.lambda$getSettingsValueForKey$6(str, (String) obj);
            }
        });
    }

    public Single<GetVoiceEnrollmentStatusResponse> getVoiceEnrollmentStatus(final EnrollmentMetadata enrollmentMetadata) {
        final String fetchPersonId = fetchPersonId(enrollmentMetadata.getPersonId());
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.enrollment.unified.speakerid.api.-$$Lambda$EnrollmentAPI$4c456G71_gpJ0e39nlx5KlS1ceo
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                EnrollmentAPI.this.lambda$getVoiceEnrollmentStatus$3$EnrollmentAPI(fetchPersonId, enrollmentMetadata, singleEmitter);
            }
        }).timeout(this.apiConfiguration.getGetEnrollmentStatusApiTimeout(), TimeUnit.MILLISECONDS).doOnSuccess($$Lambda$EnrollmentAPI$ZHp7oKutOFXmdAfjgnrAti8ymOI.INSTANCE);
    }

    public /* synthetic */ void lambda$getSettingsValueForKey$5$EnrollmentAPI(String str, String str2, SingleEmitter singleEmitter) throws Throwable {
        singleEmitter.onSuccess(callAmazonAlexaAPI(str, str2));
    }

    public /* synthetic */ void lambda$getVoiceEnrollmentStatus$3$EnrollmentAPI(String str, EnrollmentMetadata enrollmentMetadata, SingleEmitter singleEmitter) throws Throwable {
        singleEmitter.onSuccess(callGetVoiceEnrollmentStatusApi(str, enrollmentMetadata.getPersonECID()));
    }

    public /* synthetic */ void lambda$startEnrollment$0$EnrollmentAPI(EnrollmentMetadata enrollmentMetadata, SingleEmitter singleEmitter) throws Throwable {
        singleEmitter.onSuccess(callStartVoiceEnrollmentApi(enrollmentMetadata));
    }

    public /* synthetic */ void lambda$startEnrollment$1$EnrollmentAPI(StartVoiceEnrollmentResponse startVoiceEnrollmentResponse) throws Throwable {
        if (!lambda$startEnrollment$2$EnrollmentAPI(startVoiceEnrollmentResponse)) {
            EnrollmentStates enrollmentState = startVoiceEnrollmentResponse.getVoiceEnrollmentStatus().getEnrollmentState();
            String str = TAG;
            Log.i(str, "startVoiceEnrollment invalid enrollment state " + enrollmentState);
            if (EnrollmentStates.INELIGIBLE_UNSUPPORTED_LOCALE.equals(enrollmentState)) {
                this.metricsRecorder.recordCounter("START_VOICE_ENROLLMENT_INELIGIBLE_UNSUPPORTED_LOCALE");
            }
            if (!EnrollmentStates.DEVICE_NOT_AVAILABLE.equals(enrollmentState)) {
                return;
            }
            this.metricsRecorder.recordCounter("START_VOICE_ENROLLMENT_DEVICE_NOT_AVAILABLE");
        }
    }

    public Single<StartVoiceEnrollmentResponse> startEnrollment(final EnrollmentMetadata enrollmentMetadata) {
        this.metricsRecorder.initializeMetricsContext(enrollmentMetadata.getEnrollmentContext());
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.enrollment.unified.speakerid.api.-$$Lambda$EnrollmentAPI$mUnq4J4_cxp5zcrunKjR--6zU-w
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                EnrollmentAPI.this.lambda$startEnrollment$0$EnrollmentAPI(enrollmentMetadata, singleEmitter);
            }
        }).timeout(this.apiConfiguration.getStartEnrollmentApiTimeout(), TimeUnit.MILLISECONDS).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.api.-$$Lambda$EnrollmentAPI$Lk5xbsW0rL_Ybs3dZeJp0h1S4DY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                EnrollmentAPI.this.lambda$startEnrollment$1$EnrollmentAPI((StartVoiceEnrollmentResponse) obj);
            }
        }).filter(new Predicate() { // from class: com.amazon.alexa.enrollment.unified.speakerid.api.-$$Lambda$EnrollmentAPI$KKiXuirV0cdQstXiAbXmCGr0f_A
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return EnrollmentAPI.this.lambda$startEnrollment$2$EnrollmentAPI((StartVoiceEnrollmentResponse) obj);
            }
        }).repeat(this.apiConfiguration.getStartEnrollmentApiMaxRetryCount()).take(1L).singleOrError();
    }
}
