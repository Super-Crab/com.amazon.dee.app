package com.amazon.alexa.enrollment.unified.speakerid;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.unified.api.SettingsProvider;
import com.amazon.alexa.enrollment.unified.model.GetSpeakerByPersonIdResponse;
import com.amazon.alexa.enrollment.unified.model.Speaker;
import com.amazon.alexa.enrollment.unified.speakerid.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.unified.speakerid.metrics.SpeakerIDEnrollmentMetricsReporter;
import com.amazon.alexa.enrollment.unified.speakerid.provider.SchedulerProvider;
import com.amazon.alexa.enrollment.unified.speakerid.provider.SpeakerIDAPIProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
/* loaded from: classes7.dex */
public class SpeakerIDSettingsProvider implements SettingsProvider {
    private static final String SPEAKER_API = "/api/speakers/v2/%s";
    private static final long SPEAKER_API_TIMEOUT = 3000;
    private static final String TAG = "SpeakerIDSettingsProvider";
    private final CompositeDisposable mCompositeDisposable;
    private final CoralService mCoralService;
    private final SpeakerIDEnrollmentMetricsReporter mMetricsReporter;
    private final PersonIdProvider mPersonIdProvider;
    private final SchedulerProvider mSchedulerProvider;
    private final SpeakerIDAPIProvider mSpeakerIDAPIProvider;

    public SpeakerIDSettingsProvider(@NonNull Context context) {
        this(new CompositeDisposable(), new SchedulerProvider(), new SpeakerIDEnrollmentMetricsReporter(context), (PersonIdProvider) GeneratedOutlineSupport1.outline20(PersonIdProvider.class), (CoralService) GeneratedOutlineSupport1.outline20(CoralService.class), new SpeakerIDAPIProvider(context));
    }

    private GetSpeakerByPersonIdResponse callGetSpeakerByPersonId() throws CoralServiceException {
        return (GetSpeakerByPersonIdResponse) this.mCoralService.request(String.format(SPEAKER_API, this.mPersonIdProvider.getPersonId())).get().as(GetSpeakerByPersonIdResponse.class).execute();
    }

    private Single<Boolean> isSpeakerEnrolled() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDSettingsProvider$_8Mz0PTnzqDPU7OiO5FQr1201Is
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                SpeakerIDSettingsProvider.this.lambda$isSpeakerEnrolled$2$SpeakerIDSettingsProvider(singleEmitter);
            }
        }).timeout(3000L, TimeUnit.MILLISECONDS).map($$Lambda$SpeakerIDSettingsProvider$40sThUd4Od5ANa9trDxqKa1_VE.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVoiceHistoryRetentionPeriodZero(@NonNull String str) {
        String str2 = TAG;
        Log.i(str2, "VoiceHistoryRetentionPeriod setting value " + str);
        return "\"P30D\"".equalsIgnoreCase(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$isSpeakerEnrolled$3(GetSpeakerByPersonIdResponse getSpeakerByPersonIdResponse) throws Throwable {
        Speaker speaker = getSpeakerByPersonIdResponse.getSpeaker();
        boolean z = false;
        if (speaker != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Found speaker with voice training status: ");
            outline107.append(speaker.getVoiceTrainingStatus());
            Log.d(str, outline107.toString());
            if (speaker.getSpeakerUUID() != null) {
                z = true;
            }
            String str2 = TAG;
            Log.d(str2, "Speaker has UUID: " + z);
            return Boolean.valueOf(z);
        }
        Log.d(TAG, "Speaker is null");
        return false;
    }

    private void reportGetSpeakerFailure(@NonNull Throwable th) {
        this.mMetricsReporter.reportGetSpeakerFailure();
        if ((th instanceof CoralServiceException) || (th instanceof UnknownHostException)) {
            return;
        }
        this.mMetricsReporter.reportGetSpeakerFailureException(th);
        this.mMetricsReporter.recordNonFatalError(th, SpeakerIDEnrollmentMetricsReporter.GET_SPEAKER);
    }

    private void reportGetSpeakerSuccess() {
        this.mMetricsReporter.reportGetSpeakerSuccess();
    }

    @VisibleForTesting
    boolean getIsSpeakerEnrolledBlocking() {
        return isSpeakerEnrolled().subscribeOn(this.mSchedulerProvider.ioThread()).blockingGet().booleanValue();
    }

    public void hasUserOptedInForVoicePrivacySettings(@NonNull final ResultCallback<Boolean> resultCallback) {
        this.mCompositeDisposable.add(hasUserOptedInForVoicePrivacySettings().subscribeOn(this.mSchedulerProvider.ioThread()).observeOn(this.mSchedulerProvider.uiThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDSettingsProvider$oL_gmIt6v07DkbTzlLnB_WkviUA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeakerIDSettingsProvider.this.lambda$hasUserOptedInForVoicePrivacySettings$4$SpeakerIDSettingsProvider(resultCallback, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDSettingsProvider$7Yr7KMG_LWOJ_2cWJZVFE-VDz7w
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeakerIDSettingsProvider.this.lambda$hasUserOptedInForVoicePrivacySettings$5$SpeakerIDSettingsProvider(resultCallback, (Throwable) obj);
            }
        }));
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SettingsProvider
    public boolean isUVREnrolled() {
        Log.d(TAG, "check Speaker ID eligibility");
        try {
            boolean isSpeakerEnrolledBlocking = getIsSpeakerEnrolledBlocking();
            reportGetSpeakerSuccess();
            return isSpeakerEnrolledBlocking;
        } catch (Throwable th) {
            reportGetSpeakerFailure(th);
            return false;
        }
    }

    public /* synthetic */ void lambda$hasUserOptedInForVoicePrivacySettings$4$SpeakerIDSettingsProvider(ResultCallback resultCallback, Boolean bool) throws Throwable {
        Log.i(TAG, "hasUserOptedInForVoicePrivacySettings - onSuccess");
        this.mMetricsReporter.reportVoicePrivacyCheckSuccess();
        resultCallback.onResult(bool);
    }

    public /* synthetic */ void lambda$hasUserOptedInForVoicePrivacySettings$5$SpeakerIDSettingsProvider(ResultCallback resultCallback, Throwable th) throws Throwable {
        Log.i(TAG, "hasUserOptedInForVoicePrivacySettings - onError");
        this.mMetricsReporter.reportVoicePrivacyCheckFailure();
        this.mMetricsReporter.recordNonFatalError(th, SpeakerIDEnrollmentMetricsReporter.VOICE_PRIVACY_CHECK);
        resultCallback.onError(new CallbackErrorMetadata());
    }

    public /* synthetic */ void lambda$isSpeakerEnrolled$2$SpeakerIDSettingsProvider(SingleEmitter singleEmitter) throws Throwable {
        singleEmitter.onSuccess(callGetSpeakerByPersonId());
    }

    public /* synthetic */ void lambda$isUVREnrolled$0$SpeakerIDSettingsProvider(ResultCallback resultCallback, Boolean bool) throws Throwable {
        String str = TAG;
        Log.i(str, "Eligible for speaker ID enrollment: " + bool);
        reportGetSpeakerSuccess();
        resultCallback.onResult(bool);
    }

    public /* synthetic */ void lambda$isUVREnrolled$1$SpeakerIDSettingsProvider(ResultCallback resultCallback, Throwable th) throws Throwable {
        Log.e(TAG, "Exception occurred while trying to obtain speaker information", th, new Object[0]);
        reportGetSpeakerFailure(th);
        resultCallback.onError(new CallbackErrorMetadata());
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SettingsProvider
    public void removeUVRModel(@NonNull ResponseCallback responseCallback) {
        responseCallback.onSuccess();
    }

    @VisibleForTesting
    SpeakerIDSettingsProvider(@NonNull CompositeDisposable compositeDisposable, @NonNull SchedulerProvider schedulerProvider, @NonNull SpeakerIDEnrollmentMetricsReporter speakerIDEnrollmentMetricsReporter, @NonNull PersonIdProvider personIdProvider, @NonNull CoralService coralService, @NonNull SpeakerIDAPIProvider speakerIDAPIProvider) {
        this.mCompositeDisposable = compositeDisposable;
        this.mSchedulerProvider = schedulerProvider;
        this.mMetricsReporter = speakerIDEnrollmentMetricsReporter;
        this.mPersonIdProvider = personIdProvider;
        this.mCoralService = coralService;
        this.mSpeakerIDAPIProvider = speakerIDAPIProvider;
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SettingsProvider
    public void isUVREnrolled(@NonNull final ResultCallback<Boolean> resultCallback) {
        Log.d(TAG, "check Speaker ID Enrolled");
        this.mCompositeDisposable.add(isSpeakerEnrolled().subscribeOn(this.mSchedulerProvider.ioThread()).observeOn(this.mSchedulerProvider.uiThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDSettingsProvider$UihXoBIPr4XMavFyE-F6J7HuXnw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeakerIDSettingsProvider.this.lambda$isUVREnrolled$0$SpeakerIDSettingsProvider(resultCallback, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDSettingsProvider$KUY6jjEVkypgXm7w8vkGZBPTTVg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeakerIDSettingsProvider.this.lambda$isUVREnrolled$1$SpeakerIDSettingsProvider(resultCallback, (Throwable) obj);
            }
        }));
    }

    @VisibleForTesting
    Single<Boolean> hasUserOptedInForVoicePrivacySettings() {
        EnrollmentAPI provideEnrollmentAPI = this.mSpeakerIDAPIProvider.provideEnrollmentAPI();
        if (provideEnrollmentAPI == null) {
            Log.i(TAG, "Enrollment API is not initialized.");
            return Single.error(new Exception("Enrollment API is not initialized."));
        }
        return provideEnrollmentAPI.getSettingsValueForKey("Alexa.VoiceHistory.retentionPeriod").map(new Function() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDSettingsProvider$Nmq60pm9ixHV_JeWTiusUKiaYUU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                boolean isVoiceHistoryRetentionPeriodZero;
                isVoiceHistoryRetentionPeriodZero = SpeakerIDSettingsProvider.this.isVoiceHistoryRetentionPeriodZero((String) obj);
                return Boolean.valueOf(isVoiceHistoryRetentionPeriodZero);
            }
        });
    }
}
