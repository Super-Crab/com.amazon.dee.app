package com.amazon.alexa.biloba.generated.network.api;

import com.amazon.alexa.biloba.generated.models.EmergencyContact;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.StringUtil;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.dee.app.http.CoralService;
import rx.Observable;
/* loaded from: classes6.dex */
public class CommsApi {
    public static final String TAG = "CommsApi";
    CoralService coralService;
    PersonIdProvider personIdProvider;
    SchedulerProvider schedulerProvider;

    public CommsApi() {
    }

    private CoralService.Request emergencyContactRequest(String str) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/comms/emergencyContact");
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

    public Observable<EmergencyContact> emergencyContactObservable(String str) {
        return emergencyContactRequest(str).get().as(EmergencyContact.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
    }

    public CoralService getCoralService() {
        return this.coralService;
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

    public CommsApi(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        this.coralService = coralService;
        this.personIdProvider = personIdProvider;
        this.schedulerProvider = schedulerProvider;
    }
}
