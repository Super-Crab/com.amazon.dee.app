package com.amazon.alexa.biloba.service;

import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.model.AlertConfigurationListModel;
import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.model.RelationshipGroupModel;
import com.amazon.alexa.biloba.view.recent.ActivityContainerByDate;
import okhttp3.Response;
@Deprecated
/* loaded from: classes6.dex */
public interface BilobaFrontEndService {

    /* loaded from: classes6.dex */
    public interface AddAlertConfigurationsListener {
        void onError(Throwable th);

        void onSuccess();
    }

    /* loaded from: classes6.dex */
    public interface DeleteAlertConfigurationsListener {
        void onError(Throwable th);

        void onSuccess();
    }

    /* loaded from: classes6.dex */
    public interface GetAlertConfigurationsListener {
        void onError(Throwable th);

        void onSuccess(AlertConfigurationListModel alertConfigurationListModel);
    }

    /* loaded from: classes6.dex */
    public interface ModifyAlertConfigurationsListener {
        void onError(Throwable th);

        void onSuccess();
    }

    /* loaded from: classes6.dex */
    public interface RecentActivitiesListener {
        void onError(Throwable th);

        void onSuccess(ActivityContainerByDate activityContainerByDate);
    }

    /* loaded from: classes6.dex */
    public interface RelationshipListener {
        void onError(Throwable th);

        void onSuccess(RelationshipGroupModel relationshipGroupModel);
    }

    void addAlertConfiguration(@NonNull String str, @NonNull AlertConfigurationViewItemModel alertConfigurationViewItemModel, @NonNull AddAlertConfigurationsListener addAlertConfigurationsListener);

    void deleteAlertConfiguration(@NonNull String str, @NonNull String str2, @NonNull DeleteAlertConfigurationsListener deleteAlertConfigurationsListener);

    void fetchAlertConfigurations(@NonNull String str, @NonNull GetAlertConfigurationsListener getAlertConfigurationsListener);

    void fetchRelationships(@NonNull RelationshipListener relationshipListener);

    Response getDashboardCards();

    void modifyAlertConfiguration(@NonNull String str, @NonNull String str2, @NonNull AlertConfigurationViewItemModel alertConfigurationViewItemModel, @NonNull ModifyAlertConfigurationsListener modifyAlertConfigurationsListener);
}
