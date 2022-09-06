package com.amazon.alexa.biloba.generated.network.api;

import com.amazon.alexa.biloba.generated.models.Setting;
import com.amazon.alexa.biloba.generated.models.SettingValueObject;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.StringUtil;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.dee.app.http.CoralService;
import rx.Observable;
/* loaded from: classes6.dex */
public class SettingsApi {
    public static final String TAG = "SettingsApi";
    CoralService coralService;
    PersonIdProvider personIdProvider;
    SchedulerProvider schedulerProvider;

    public SettingsApi() {
    }

    private CoralService.Request getSettingRequest(String str, String str2) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/settings/{settingType}".replaceAll("\\{settingType\\}", StringUtil.escapeString(str == null ? null : StringUtil.escapeString(str))));
        StringBuilder sb2 = new StringBuilder();
        if (str2 != null) {
            if (sb2.length() == 0) {
                sb2.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            } else {
                sb2.append(WebConstants.UriConstants.AMPERSAND_KEY);
            }
            sb2.append("groupId");
            sb2.append(Config.Compare.EQUAL_TO);
            sb2.append(StringUtil.parameterToString(str2));
        }
        sb.append((CharSequence) sb2);
        return this.coralService.request(sb.toString()).withHeader("X-Amzn-OperatorId", this.personIdProvider.getPersonId());
    }

    private CoralService.Request setSettingRequest(String str, String str2, SettingValueObject settingValueObject) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/settings/{settingType}".replaceAll("\\{settingType\\}", StringUtil.escapeString(str == null ? null : StringUtil.escapeString(str))));
        StringBuilder sb2 = new StringBuilder();
        if (str2 != null) {
            if (sb2.length() == 0) {
                sb2.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            } else {
                sb2.append(WebConstants.UriConstants.AMPERSAND_KEY);
            }
            sb2.append("groupId");
            sb2.append(Config.Compare.EQUAL_TO);
            sb2.append(StringUtil.parameterToString(str2));
        }
        sb.append((CharSequence) sb2);
        return this.coralService.request(sb.toString()).withHeader("X-Amzn-OperatorId", this.personIdProvider.getPersonId());
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

    public Observable<Setting> getSettingObservable(String str, String str2) {
        return getSettingRequest(str, str2).get().as(Setting.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
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

    public Observable<Void> setSettingObservable(String str, String str2, SettingValueObject settingValueObject) {
        return setSettingRequest(str, str2, settingValueObject).put(settingValueObject).as(Void.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
    }

    public SettingsApi(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        this.coralService = coralService;
        this.personIdProvider = personIdProvider;
        this.schedulerProvider = schedulerProvider;
    }
}
