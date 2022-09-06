package com.amazon.alexa.biloba.generated.network.api;

import com.amazon.alexa.biloba.generated.models.EmergencySettingsResponse;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.StringUtil;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.dee.app.http.CoralService;
import rx.Observable;
/* loaded from: classes6.dex */
public class EmergencySettingsApi {
    public static final String TAG = "EmergencySettingsApi";
    CoralService coralService;
    PersonIdProvider personIdProvider;
    SchedulerProvider schedulerProvider;

    public EmergencySettingsApi() {
    }

    private CoralService.Request getEmergencySettingsRequest(String str) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/emergencySettings");
        StringBuilder sb2 = new StringBuilder();
        if (str != null) {
            if (sb2.length() == 0) {
                sb2.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            } else {
                sb2.append(WebConstants.UriConstants.AMPERSAND_KEY);
            }
            sb2.append("groupId");
            sb2.append(Config.Compare.EQUAL_TO);
            sb2.append(StringUtil.parameterToString(str));
        }
        sb.append((CharSequence) sb2);
        return this.coralService.request(sb.toString()).withHeader("X-Amzn-OperatorId", this.personIdProvider.getPersonId());
    }

    public CoralService getCoralService() {
        return this.coralService;
    }

    public Observable<EmergencySettingsResponse> getEmergencySettingsObservable(String str) {
        return getEmergencySettingsRequest(str).get().as(EmergencySettingsResponse.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
    }

    public PersonIdProvider getPersonIdProvider() {
        return this.personIdProvider;
    }

    public SchedulerProvider getSchedulerProvider() {
        return this.schedulerProvider;
    }

    public void setCoralService(CoralService coralService) {
        this.coralService = coralService;
    }

    public void setPersonIdProvider(PersonIdProvider personIdProvider) {
        this.personIdProvider = personIdProvider;
    }

    public void setSchedulerProvider(SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
    }

    public EmergencySettingsApi(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        this.coralService = coralService;
        this.personIdProvider = personIdProvider;
        this.schedulerProvider = schedulerProvider;
    }
}
