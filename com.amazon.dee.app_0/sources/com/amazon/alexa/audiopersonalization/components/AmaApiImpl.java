package com.amazon.alexa.audiopersonalization.components;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.audiopersonalization.api.AmaApi;
import com.amazon.alexa.audiopersonalization.api.CompletableDelegate;
import com.amazon.alexa.audiopersonalization.api.ErrorDelegate;
import com.amazon.alexa.audiopersonalization.api.ErrorMessageDelegate;
import com.amazon.alexa.audiopersonalization.api.SuccessDelegate;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Set;
/* loaded from: classes6.dex */
public class AmaApiImpl implements AmaApi {
    private static final int DEVICE_ID = 0;
    private static final String TAG = "AmaApiImpl";
    private Disposable audioAssessmentAbortSubscription;
    private final BudsSessionRetriever mSessionMonitor;

    public AmaApiImpl(BudsSessionRetriever budsSessionRetriever) {
        this.mSessionMonitor = budsSessionRetriever;
    }

    private boolean bothBudsInEar(Set<Device.DeviceInformation> set) {
        String deviceType;
        boolean z = false;
        boolean z2 = false;
        for (Device.DeviceInformation deviceInformation : set) {
            if (deviceInformation != null && (deviceType = deviceInformation.getDeviceType()) != null && deviceInformation.getStatus() != null && deviceInformation.getStatus().getPresence() == Device.DevicePresence.DEVICE_PRESENCE_ACTIVE) {
                if ("A2QDHDQIWC2LTG".equals(deviceType)) {
                    z = true;
                }
                if ("A31PMVIWCRNTX2".equals(deviceType)) {
                    z2 = true;
                }
                if (z2 && z) {
                    return true;
                }
            }
        }
        return z && z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getAssessmentMode$7(ErrorDelegate errorDelegate, SuccessDelegate successDelegate, StateOuterClass.State state) throws Throwable {
        if (state == null) {
            Log.e(TAG, "Null state when getting assessment mode");
            errorDelegate.onError();
            return;
        }
        successDelegate.onSuccess(Integer.valueOf(state.getInteger()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getAssessmentMode$8(ErrorDelegate errorDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error in getAssessmentMode: " + th);
        errorDelegate.onError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getAudioProfile$12(ErrorMessageDelegate errorMessageDelegate, SuccessDelegate successDelegate, Hearing.Audiogram audiogram) throws Throwable {
        if (audiogram == null) {
            Log.e(TAG, "Null state when getting audio profile status");
            errorMessageDelegate.onError(new Throwable(GeneratedOutlineSupport1.outline91(new StringBuilder(), TAG, "::getAudioProfileStatus Null state when getting audio profile status")));
            return;
        }
        successDelegate.onSuccess(audiogram);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getAudioProfile$13(ErrorMessageDelegate errorMessageDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error in getAudioProfileStatus: " + th);
        errorMessageDelegate.onError(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getBudsInEar$15(ErrorDelegate errorDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error emmited in getBudsInEar: " + th);
        errorDelegate.onError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getFeatureStatus$1(ErrorMessageDelegate errorMessageDelegate, SuccessDelegate successDelegate, StateOuterClass.State state) throws Throwable {
        if (state == null) {
            Log.e(TAG, "Could not get feature status, state returned from device is null");
            errorMessageDelegate.onError(new Throwable(GeneratedOutlineSupport1.outline91(new StringBuilder(), TAG, "::getFeatureStatus: Cannot get feature status, state returned from device is null")));
            return;
        }
        boolean z = state.getBoolean();
        String str = "getFeatureStatus got response it is " + z;
        successDelegate.onSuccess(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getFeatureStatus$2(ErrorMessageDelegate errorMessageDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error in getFeatureStatus: " + th);
        errorMessageDelegate.onError(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPersonalizationLevel$4(ErrorDelegate errorDelegate, SuccessDelegate successDelegate, Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) throws Throwable {
        if (mediaEnhancementCorrectionAmount == null) {
            Log.e(TAG, "Null state when querying personalization level");
            errorDelegate.onError();
            return;
        }
        successDelegate.onSuccess(Integer.valueOf(mediaEnhancementCorrectionAmount.getCorrectionAmount()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPersonalizationLevel$5(ErrorDelegate errorDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error in getPersonalizationLevel: " + th);
        errorDelegate.onError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setAssessmentMode$6(CompletableDelegate completableDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error in setAssessmentMode: " + th);
        completableDelegate.onError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setAudioProfile$11(CompletableDelegate completableDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error received when setting audio profile: " + th);
        completableDelegate.onError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setFeatureStatus$0(CompletableDelegate completableDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error in setFeatureStatus: " + th);
        completableDelegate.onError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setPersonalizationLevel$3(CompletableDelegate completableDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error in setPersonalizationLevel: " + th);
        completableDelegate.onError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$subscribeToAudioAssessmentAbort$9(CompletableDelegate completableDelegate, StateOuterClass.State state) throws Throwable {
        if (state == null) {
            Log.e(TAG, "Null state received while listening for assessment abort.");
            completableDelegate.onError();
        }
        if (state.getInteger() == 3) {
            completableDelegate.onComplete();
        }
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    @SuppressFBWarnings
    @SuppressLint({"CheckResult"})
    public void getAssessmentMode(String str, @NonNull final SuccessDelegate<Integer> successDelegate, @NonNull final ErrorDelegate errorDelegate) {
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Log.e(TAG, "Cannot get assessment mode, session is null");
            errorDelegate.onError();
            return;
        }
        sessionFromMacAddress.getStateRepository().query(StateFeature.HEARING_ASSESSMENT_MODE_ENABLED).firstOrError().subscribe(new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$BD5bqujQP-PvE2iYXoQxA3xRidE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$getAssessmentMode$7(ErrorDelegate.this, successDelegate, (StateOuterClass.State) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$WbP73bZGlCy25ijbjTnyR1ASyS4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$getAssessmentMode$8(ErrorDelegate.this, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    @SuppressFBWarnings
    @SuppressLint({"CheckResult"})
    public void getAudioProfile(String str, @NonNull final SuccessDelegate<Hearing.Audiogram> successDelegate, @NonNull final ErrorMessageDelegate errorMessageDelegate) {
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Throwable th = new Throwable(GeneratedOutlineSupport1.outline91(new StringBuilder(), TAG, "::getAudioProfileStatus: Null session when querying audio profile status."));
            Log.e(TAG, "Null session or device when querying audio profile status.");
            errorMessageDelegate.onError(th);
            return;
        }
        sessionFromMacAddress.getHearingEnhancementRepository().getAudiogram(0).subscribe(new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$-f8ElOJnKM0RqJL89IcYJUon5PI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$getAudioProfile$12(ErrorMessageDelegate.this, successDelegate, (Hearing.Audiogram) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$NeqEBZ5mlF7CPHWf92v_5A2cb88
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$getAudioProfile$13(ErrorMessageDelegate.this, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    @SuppressFBWarnings
    @SuppressLint({"CheckResult"})
    public void getBudsInEar(String str, @NonNull final SuccessDelegate<Boolean> successDelegate, @NonNull final ErrorDelegate errorDelegate) {
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Log.e(TAG, "Null session or device when querying audio profile status.");
            errorDelegate.onError();
            return;
        }
        sessionFromMacAddress.getDeviceRepository().queryDeviceInformationSet().take(1L).singleOrError().subscribe(new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$TxprLFHUJUaZgjIgme7bbEQcook
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.this.lambda$getBudsInEar$14$AmaApiImpl(errorDelegate, successDelegate, (Set) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$HiJ9po5sFfynI9EKSOGWqpwQm2k
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$getBudsInEar$15(ErrorDelegate.this, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    @SuppressFBWarnings
    @SuppressLint({"CheckResult"})
    public void getFeatureStatus(String str, @NonNull final SuccessDelegate<Boolean> successDelegate, @NonNull final ErrorMessageDelegate errorMessageDelegate) {
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Log.e(TAG, "Cannot get feature status, session is null");
            errorMessageDelegate.onError(new Throwable(GeneratedOutlineSupport1.outline91(new StringBuilder(), TAG, "::getFeatureStatus: Cannot get feature status, session is null")));
            return;
        }
        sessionFromMacAddress.getStateRepository().query(StateFeature.MEDIA_ENHANCEMENT_ENABLED).firstOrError().subscribe(new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$kDwXRhZMktozBfCTDdHw_w15iUw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$getFeatureStatus$1(ErrorMessageDelegate.this, successDelegate, (StateOuterClass.State) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$aU0yrySce2B9YYTfwstdMfW1eYk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$getFeatureStatus$2(ErrorMessageDelegate.this, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    @SuppressFBWarnings
    @SuppressLint({"CheckResult"})
    public void getPersonalizationLevel(String str, @NonNull final SuccessDelegate<Integer> successDelegate, @NonNull final ErrorDelegate errorDelegate) {
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Log.w(TAG, "Null session or device when querying personalization level");
            errorDelegate.onError();
            return;
        }
        sessionFromMacAddress.getHearingEnhancementRepository().getMediaEnhancementCorrectionAmount(0).subscribe(new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$yWHEFPVYT4N8DOKioCZ0ONd_NA0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$getPersonalizationLevel$4(ErrorDelegate.this, successDelegate, (Hearing.MediaEnhancementCorrectionAmount) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$LyDxAq1htaKLjb2FoNOuvk5rQ00
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$getPersonalizationLevel$5(ErrorDelegate.this, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$getBudsInEar$14$AmaApiImpl(ErrorDelegate errorDelegate, SuccessDelegate successDelegate, Set set) throws Throwable {
        if (set == null) {
            Log.e(TAG, "Null state when getting deviceInformationList");
            errorDelegate.onError();
            return;
        }
        boolean bothBudsInEar = bothBudsInEar(set);
        String str = TAG;
        Log.i(str, "Success buds in ear is " + bothBudsInEar);
        successDelegate.onSuccess(Boolean.valueOf(bothBudsInEar));
    }

    public /* synthetic */ void lambda$subscribeToAudioAssessmentAbort$10$AmaApiImpl(CompletableDelegate completableDelegate, Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error received when listening for assessment abort: " + th);
        if (this.audioAssessmentAbortSubscription != null) {
            this.audioAssessmentAbortSubscription = null;
        }
        completableDelegate.onError();
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    @SuppressFBWarnings
    @SuppressLint({"CheckResult"})
    public void setAssessmentMode(int i, String str, @NonNull final CompletableDelegate completableDelegate) {
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Log.e(TAG, "Cannot set assessment mode, session is null");
            completableDelegate.onError();
            return;
        }
        int integer = StateFeature.HEARING_ASSESSMENT_MODE_ENABLED.toInteger();
        Completable completable = sessionFromMacAddress.getStateRepository().set(StateOuterClass.State.newBuilder().setFeature(integer).setInteger(i).mo10084build());
        completableDelegate.getClass();
        completable.subscribe(new $$Lambda$bFawF2WojhaU4vcBaN7OIUwUrF4(completableDelegate), new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$SoefXQ35u9dyJikUPv7-cggZJJU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$setAssessmentMode$6(CompletableDelegate.this, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    @SuppressFBWarnings
    @SuppressLint({"CheckResult"})
    public void setAudioProfile(Hearing.Audiogram audiogram, String str, @NonNull final CompletableDelegate completableDelegate) {
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Log.e(TAG, "Cannot set audio profile, session is null");
            completableDelegate.onError();
            return;
        }
        Completable audiogram2 = sessionFromMacAddress.getHearingEnhancementRepository().setAudiogram(audiogram);
        completableDelegate.getClass();
        audiogram2.subscribe(new $$Lambda$bFawF2WojhaU4vcBaN7OIUwUrF4(completableDelegate), new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$sARL22-DmMpNZiEc6mURvdD1kXs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$setAudioProfile$11(CompletableDelegate.this, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    @SuppressFBWarnings
    @SuppressLint({"CheckResult"})
    public void setFeatureStatus(boolean z, String str, @NonNull final CompletableDelegate completableDelegate) {
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Log.e(TAG, "Cannot set feature status, session is null");
            completableDelegate.onError();
            return;
        }
        int integer = StateFeature.MEDIA_ENHANCEMENT_ENABLED.toInteger();
        Completable completable = sessionFromMacAddress.getStateRepository().set(StateOuterClass.State.newBuilder().setFeature(integer).setBoolean(z).mo10084build());
        completableDelegate.getClass();
        completable.subscribe(new $$Lambda$bFawF2WojhaU4vcBaN7OIUwUrF4(completableDelegate), new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$w992GcbsPmS4hclTVofK4j3HXPQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$setFeatureStatus$0(CompletableDelegate.this, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    @SuppressFBWarnings
    @SuppressLint({"CheckResult"})
    public void setPersonalizationLevel(int i, String str, @NonNull final CompletableDelegate completableDelegate) {
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Log.e(TAG, "Cannot set personalization level, session is null");
            completableDelegate.onError();
            return;
        }
        Completable mediaEnhancementCorrectionAmount = sessionFromMacAddress.getHearingEnhancementRepository().setMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount.newBuilder().setCorrectionAmount(i).mo10084build());
        completableDelegate.getClass();
        mediaEnhancementCorrectionAmount.subscribe(new $$Lambda$bFawF2WojhaU4vcBaN7OIUwUrF4(completableDelegate), new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$GeL9XCQ52CKKxQFLc5Zbga0la9g
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$setPersonalizationLevel$3(CompletableDelegate.this, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    public void subscribeToAudioAssessmentAbort(String str, @NonNull final CompletableDelegate completableDelegate) {
        if (this.audioAssessmentAbortSubscription != null) {
            return;
        }
        AccessorySession sessionFromMacAddress = this.mSessionMonitor.getSessionFromMacAddress(str);
        if (sessionFromMacAddress == null) {
            Log.e(TAG, "Cannot get listen for assessment abort, session is null");
            completableDelegate.onError();
            return;
        }
        this.audioAssessmentAbortSubscription = sessionFromMacAddress.getStateRepository().query(StateFeature.HEARING_ASSESSMENT_MODE_ENABLED).subscribe(new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$L6mdoDQkaoD5hfHkxoTAfqv850E
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.lambda$subscribeToAudioAssessmentAbort$9(CompletableDelegate.this, (StateOuterClass.State) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.audiopersonalization.components.-$$Lambda$AmaApiImpl$ZqZ88uuCotbJFdojxjex7_yqSKU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmaApiImpl.this.lambda$subscribeToAudioAssessmentAbort$10$AmaApiImpl(completableDelegate, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AmaApi
    public void unsubscribeToAudioAssessmentAbort(String str) {
        Disposable disposable = this.audioAssessmentAbortSubscription;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.audioAssessmentAbortSubscription.dispose();
        this.audioAssessmentAbortSubscription = null;
    }
}
