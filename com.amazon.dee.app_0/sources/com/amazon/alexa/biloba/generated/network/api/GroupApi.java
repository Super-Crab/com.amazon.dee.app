package com.amazon.alexa.biloba.generated.network.api;

import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.StringUtil;
import com.amazon.alexa.biloba.model.ListGroupsResponse;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.dee.app.http.CoralService;
import rx.Observable;
/* loaded from: classes6.dex */
public class GroupApi {
    public static final String TAG = "GroupApi";
    CoralService coralService;
    PersonIdProvider personIdProvider;
    SchedulerProvider schedulerProvider;

    public GroupApi() {
    }

    private CoralService.Request leaveGroupRequest(String str) {
        return this.coralService.request("/api/biloba/v1/groups/{groupId}/leave".replaceAll("\\{groupId\\}", StringUtil.escapeString(str == null ? null : StringUtil.escapeString(str))) + ((CharSequence) new StringBuilder())).withHeader("X-Amzn-OperatorId", this.personIdProvider.getPersonId());
    }

    private CoralService.Request listGroupsRequest() {
        return this.coralService.request("/api/biloba/v1/groups" + ((CharSequence) new StringBuilder())).withHeader("X-Amzn-OperatorId", this.personIdProvider.getPersonId());
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

    public Observable<Void> leaveGroupObservable(String str) {
        return leaveGroupRequest(str).post(null).as(Void.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
    }

    public Observable<ListGroupsResponse> listGroupsObservable() {
        return listGroupsRequest().get().as(ListGroupsResponse.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
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

    public GroupApi(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        this.coralService = coralService;
        this.personIdProvider = personIdProvider;
        this.schedulerProvider = schedulerProvider;
    }
}
