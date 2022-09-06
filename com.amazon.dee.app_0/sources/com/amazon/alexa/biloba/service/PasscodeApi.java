package com.amazon.alexa.biloba.service;

import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.model.PersonIdentityClient;
import com.amazon.alexa.biloba.model.PersonIdentitySetupRequest;
import com.amazon.alexa.biloba.model.PersonIdentitySetupRequestMetadata;
import com.amazon.alexa.biloba.model.PersonIdentitySetupResponse;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.dee.app.http.CoralService;
import rx.Observable;
/* loaded from: classes6.dex */
public class PasscodeApi {
    private static final String APP_VERSION = "2.2.0";
    private static final String COMPONENT_VERSION = "1.0";
    public static final String TAG = "PasscodeApi";
    CoralService coralService;
    SchedulerProvider schedulerProvider;

    public PasscodeApi(CoralService coralService, SchedulerProvider schedulerProvider) {
        this.coralService = coralService;
        this.schedulerProvider = schedulerProvider;
    }

    private CoralService.Request getPasscodeRequest() {
        return this.coralService.request("/api/person-auth/v1/setup");
    }

    public Observable<PersonIdentitySetupResponse> getPasscodeAuthObservable(String str) {
        String str2 = TAG;
        LogUtils.d(str2, "Getting passcode auth for customerID: " + str + " and appVersion: " + APP_VERSION);
        return getPasscodeRequest().post(new PersonIdentitySetupRequest(str, "Alexa Companion App", new PersonIdentitySetupRequestMetadata("en_US", PersonIdentitySetupRequestMetadata.PersonIdentityClientPlatformEnum.ANDROID, new PersonIdentityClient(APP_VERSION, "1.0", "ALEXA_APP", PersonIdentityClient.PersonIdentityClientSupportedEnum.NOT_APPLICABLE)))).as(PersonIdentitySetupResponse.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
    }
}
