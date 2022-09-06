package com.amazon.alexa.biloba.generated.network.api;

import com.amazon.alexa.biloba.generated.models.CardsResponse;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.StringUtil;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.dee.app.http.CoralService;
import java.util.List;
import rx.Observable;
/* loaded from: classes6.dex */
public class CardApi {
    public static final String TAG = "CardApi";
    CoralService coralService;
    PersonIdProvider personIdProvider;
    SchedulerProvider schedulerProvider;

    public CardApi() {
    }

    private CoralService.Request getCardsRequest(String str, List<String> list) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/cards");
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

    public Observable<CardsResponse> getCardsObservable(String str, List<String> list) {
        return getCardsRequest(str, list).get().as(CardsResponse.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
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

    public CardApi(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        this.coralService = coralService;
        this.personIdProvider = personIdProvider;
        this.schedulerProvider = schedulerProvider;
    }
}
