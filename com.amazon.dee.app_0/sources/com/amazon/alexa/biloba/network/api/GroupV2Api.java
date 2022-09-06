package com.amazon.alexa.biloba.network.api;

import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.StringUtil;
import com.amazon.alexa.biloba.model.ListGroupsResponse;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.dee.app.http.CoralService;
import javax.inject.Inject;
import rx.Observable;
/* loaded from: classes6.dex */
public class GroupV2Api {
    public static final String TAG = "GroupV2Api";
    CoralService coralService;
    PersonIdProvider personIdProvider;
    SchedulerProvider schedulerProvider;

    public GroupV2Api() {
    }

    private CoralService.Request getGroup(String str) {
        return this.coralService.request("/api/biloba/v2/groups/{groupId}".replaceAll("\\{groupId\\}", StringUtil.escapeString(str == null ? "~latest" : StringUtil.escapeString(str))) + ((CharSequence) new StringBuilder())).withHeader("X-Amzn-OperatorId", this.personIdProvider.getPersonId());
    }

    private CoralService.Request leaveGroupRequest(String str) {
        return this.coralService.request("/api/biloba/v2/groups/{groupId}/leave".replaceAll("\\{groupId\\}", StringUtil.escapeString(str == null ? null : StringUtil.escapeString(str))) + ((CharSequence) new StringBuilder())).withHeader("X-Amzn-OperatorId", this.personIdProvider.getPersonId());
    }

    public CoralService getCoralService() {
        return this.coralService;
    }

    public Observable<ListGroupsResponse> getGroupsObservable(String str) {
        return getGroup(str).get().as(ListGroupsResponse.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
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

    public void setCoralService(CoralService coralService) {
        this.coralService = coralService;
    }

    public void setPersonIdProvider(PersonIdProvider personIdProvider) {
        this.personIdProvider = personIdProvider;
    }

    public void setSchedulerProvider(SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
    }

    @Inject
    public GroupV2Api(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        this.coralService = coralService;
        this.personIdProvider = personIdProvider;
        this.schedulerProvider = schedulerProvider;
    }
}
