package com.amazon.alexa.biloba.generated.network.api;

import com.amazon.alexa.biloba.generated.models.ActivitiesResponse;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.StringUtil;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.dee.app.http.CoralService;
import java.util.List;
import rx.Observable;
/* loaded from: classes6.dex */
public class ActivityApi {
    public static final String TAG = "ActivityApi";
    CoralService coralService;
    PersonIdProvider personIdProvider;
    SchedulerProvider schedulerProvider;

    public ActivityApi() {
    }

    private CoralService.Request getActivitiesRequest(String str, String str2, Integer num, List<String> list) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/activities");
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
        if (str2 != null) {
            if (sb2.length() == 0) {
                sb2.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            } else {
                sb2.append(WebConstants.UriConstants.AMPERSAND_KEY);
            }
            sb2.append("nextToken");
            sb2.append(Config.Compare.EQUAL_TO);
            sb2.append(StringUtil.parameterToString(str2));
        }
        if (num != null) {
            if (sb2.length() == 0) {
                sb2.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            } else {
                sb2.append(WebConstants.UriConstants.AMPERSAND_KEY);
            }
            sb2.append("maxResults");
            sb2.append(Config.Compare.EQUAL_TO);
            sb2.append(StringUtil.parameterToString(num));
        }
        if (list != null) {
            if (sb2.length() == 0) {
                sb2.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            } else {
                sb2.append(WebConstants.UriConstants.AMPERSAND_KEY);
            }
            sb2.append("category");
            sb2.append(Config.Compare.EQUAL_TO);
            sb2.append(StringUtil.parameterToString(list));
        }
        sb.append((CharSequence) sb2);
        return this.coralService.request(sb.toString()).withHeader("X-Amzn-OperatorId", this.personIdProvider.getPersonId());
    }

    public Observable<ActivitiesResponse> getActivitiesObservable(String str, String str2, Integer num, List<String> list) {
        return getActivitiesRequest(str, str2, num, list).get().as(ActivitiesResponse.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
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

    public ActivityApi(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        this.coralService = coralService;
        this.personIdProvider = personIdProvider;
        this.schedulerProvider = schedulerProvider;
    }
}
