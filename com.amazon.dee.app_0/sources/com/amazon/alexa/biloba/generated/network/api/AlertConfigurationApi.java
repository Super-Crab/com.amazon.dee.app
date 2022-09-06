package com.amazon.alexa.biloba.generated.network.api;

import com.amazon.alexa.biloba.generated.models.AlertConfiguration;
import com.amazon.alexa.biloba.generated.models.AlertConfigurationsResponse;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.StringUtil;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.dee.app.http.CoralService;
import rx.Observable;
/* loaded from: classes6.dex */
public class AlertConfigurationApi {
    public static final String TAG = "AlertConfigurationApi";
    CoralService coralService;
    PersonIdProvider personIdProvider;
    SchedulerProvider schedulerProvider;

    public AlertConfigurationApi() {
    }

    private CoralService.Request createAlertConfigurationRequest(String str, AlertConfiguration alertConfiguration) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/alertConfigurations");
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

    private CoralService.Request deleteAlertConfigurationRequest(String str, String str2) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/alertConfigurations/{alertConfigurationId}".replaceAll("\\{alertConfigurationId\\}", StringUtil.escapeString(str == null ? null : StringUtil.escapeString(str))));
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

    private CoralService.Request getAlertConfigurationsRequest(String str, String str2, Integer num) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/alertConfigurations");
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
        sb.append((CharSequence) sb2);
        return this.coralService.request(sb.toString()).withHeader("X-Amzn-OperatorId", this.personIdProvider.getPersonId());
    }

    private CoralService.Request updateAlertConfigurationRequest(String str, String str2, AlertConfiguration alertConfiguration) {
        StringBuilder sb = new StringBuilder("/api/biloba/v1/alertConfigurations/{alertConfigurationId}".replaceAll("\\{alertConfigurationId\\}", StringUtil.escapeString(str == null ? null : StringUtil.escapeString(str))));
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

    public Observable<AlertConfiguration> createAlertConfigurationObservable(String str, AlertConfiguration alertConfiguration) {
        return createAlertConfigurationRequest(str, alertConfiguration).post(alertConfiguration).as(AlertConfiguration.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
    }

    public Observable<Void> deleteAlertConfigurationObservable(String str, String str2) {
        return deleteAlertConfigurationRequest(str, str2).delete().as(Void.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
    }

    public Observable<AlertConfigurationsResponse> getAlertConfigurationsObservable(String str, String str2, Integer num) {
        return getAlertConfigurationsRequest(str, str2, num).get().as(AlertConfigurationsResponse.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
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

    public Observable<AlertConfiguration> updateAlertConfigurationObservable(String str, String str2, AlertConfiguration alertConfiguration) {
        return updateAlertConfigurationRequest(str, str2, alertConfiguration).put(alertConfiguration).as(AlertConfiguration.class).toObservable().observeOn(this.schedulerProvider.ui()).subscribeOn(this.schedulerProvider.io());
    }

    public AlertConfigurationApi(CoralService coralService, PersonIdProvider personIdProvider, SchedulerProvider schedulerProvider) {
        this.coralService = coralService;
        this.personIdProvider = personIdProvider;
        this.schedulerProvider = schedulerProvider;
    }
}
