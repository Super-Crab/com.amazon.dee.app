package com.amazon.alexa.biloba.service;

import android.os.AsyncTask;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.membership.MembershipConstants;
import com.amazon.alexa.biloba.model.AlertConfigurationListModel;
import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.model.CareContact;
import com.amazon.alexa.biloba.model.RelationshipGroupModel;
import com.amazon.alexa.biloba.service.BilobaFrontEndService;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes6.dex */
public class DefaultBilobaFrontEndService implements BilobaFrontEndService {
    private CoralService coralService;
    private FrontEndServiceRequest requests;
    public static final String TAG = BilobaFrontEndService.class.getSimpleName();
    public static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);

    @VisibleForTesting
    public DefaultBilobaFrontEndService(FrontEndServiceRequest frontEndServiceRequest) {
        this.requests = frontEndServiceRequest;
    }

    private Date converDateToLocalDate(String str) throws ParseException {
        String str2 = str.substring(0, str.lastIndexOf(".")) + "Z";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        return simpleDateFormat.parse(str2);
    }

    public static String convertToJsonString(AlertConfigurationViewItemModel alertConfigurationViewItemModel) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("alertType", alertConfigurationViewItemModel.getAlertConfigurationTypeInString());
        jSONObject.put("start", alertConfigurationViewItemModel.getStartTime());
        jSONObject.put("end", alertConfigurationViewItemModel.getEndTime());
        jSONObject.put("status", alertConfigurationViewItemModel.getAlertConfigurationEnabledInString());
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("jsonString: ");
        outline107.append(jSONObject.toString());
        LogUtils.i(str, outline107.toString());
        return jSONObject.toString();
    }

    @Override // com.amazon.alexa.biloba.service.BilobaFrontEndService
    public void addAlertConfiguration(String str, AlertConfigurationViewItemModel alertConfigurationViewItemModel, final BilobaFrontEndService.AddAlertConfigurationsListener addAlertConfigurationsListener) {
        new AsyncTask<AlertConfigurationRequest, Void, Response>() { // from class: com.amazon.alexa.biloba.service.DefaultBilobaFrontEndService.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Response doInBackground(AlertConfigurationRequest... alertConfigurationRequestArr) {
                HttpUrl addAlertConfigurationUrl = DefaultBilobaFrontEndService.this.requests.addAlertConfigurationUrl(alertConfigurationRequestArr[0].getRelationshipId());
                String str2 = DefaultBilobaFrontEndService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpUrl: ");
                outline107.append(addAlertConfigurationUrl.toString());
                LogUtils.i(str2, outline107.toString());
                try {
                    return DefaultBilobaFrontEndService.this.requests.executeHttpRequest(addAlertConfigurationUrl, 1, DefaultBilobaFrontEndService.convertToJsonString(alertConfigurationRequestArr[0].getAlertConfigurationViewItemModel()));
                } catch (JSONException e) {
                    LogUtils.e(DefaultBilobaFrontEndService.TAG, "Error converting json object to string", e);
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Response response) {
                super.onPostExecute((AnonymousClass3) response);
                if (response != null && response.isSuccessful()) {
                    addAlertConfigurationsListener.onSuccess();
                    return;
                }
                BilobaFrontEndService.AddAlertConfigurationsListener addAlertConfigurationsListener2 = addAlertConfigurationsListener;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to add alert configuration List with error");
                outline107.append(response.body());
                addAlertConfigurationsListener2.onError(new AlertConfigurationException(outline107.toString(), -2));
            }
        }.execute(new AlertConfigurationRequest(str, alertConfigurationViewItemModel));
    }

    @Override // com.amazon.alexa.biloba.service.BilobaFrontEndService
    public void deleteAlertConfiguration(String str, String str2, final BilobaFrontEndService.DeleteAlertConfigurationsListener deleteAlertConfigurationsListener) {
        new AsyncTask<AlertConfigurationRequest, Void, Response>() { // from class: com.amazon.alexa.biloba.service.DefaultBilobaFrontEndService.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Response doInBackground(AlertConfigurationRequest... alertConfigurationRequestArr) {
                HttpUrl deleteAlertConfigurationUrl = DefaultBilobaFrontEndService.this.requests.deleteAlertConfigurationUrl(alertConfigurationRequestArr[0].getRelationshipId(), alertConfigurationRequestArr[0].getAlertConfigurationId());
                String str3 = DefaultBilobaFrontEndService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpUrl: ");
                outline107.append(deleteAlertConfigurationUrl.toString());
                LogUtils.i(str3, outline107.toString());
                return DefaultBilobaFrontEndService.this.requests.executeHttpRequest(deleteAlertConfigurationUrl, 2);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Response response) {
                super.onPostExecute((AnonymousClass4) response);
                if (response != null && response.isSuccessful()) {
                    deleteAlertConfigurationsListener.onSuccess();
                    return;
                }
                BilobaFrontEndService.DeleteAlertConfigurationsListener deleteAlertConfigurationsListener2 = deleteAlertConfigurationsListener;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to delete alert configuration List with error");
                outline107.append(response.body());
                deleteAlertConfigurationsListener2.onError(new AlertConfigurationException(outline107.toString(), -2));
            }
        }.execute(new AlertConfigurationRequest(str, str2));
    }

    @Override // com.amazon.alexa.biloba.service.BilobaFrontEndService
    public void fetchAlertConfigurations(String str, final BilobaFrontEndService.GetAlertConfigurationsListener getAlertConfigurationsListener) {
        new AsyncTask<String, Void, AlertConfigurationListModel>() { // from class: com.amazon.alexa.biloba.service.DefaultBilobaFrontEndService.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public AlertConfigurationListModel doInBackground(String... strArr) {
                HttpUrl alertConfigurationUrl = DefaultBilobaFrontEndService.this.requests.getAlertConfigurationUrl(strArr[0]);
                String str2 = DefaultBilobaFrontEndService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpUrl: ");
                outline107.append(alertConfigurationUrl.toString());
                LogUtils.i(str2, outline107.toString());
                if (DefaultBilobaFrontEndService.this.requests.executeHttpRequest(alertConfigurationUrl, 0) == null) {
                    LogUtils.e(DefaultBilobaFrontEndService.TAG, "HTTP response is null");
                }
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(AlertConfigurationListModel alertConfigurationListModel) {
                super.onPostExecute((AnonymousClass1) alertConfigurationListModel);
                if (alertConfigurationListModel != null) {
                    getAlertConfigurationsListener.onSuccess(alertConfigurationListModel);
                } else {
                    getAlertConfigurationsListener.onError(new AlertConfigurationException("Unable to fetch alert configuration List", -2));
                }
            }
        }.execute(str);
    }

    @Override // com.amazon.alexa.biloba.service.BilobaFrontEndService
    public void fetchRelationships(BilobaFrontEndService.RelationshipListener relationshipListener) {
        RelationshipGroupModel relationshipGroupModel = new RelationshipGroupModel();
        relationshipGroupModel.setGroupId("asrlsEventbusTestCustomerId");
        relationshipGroupModel.setGroupName("Care");
        CareContact careContact = new CareContact();
        careContact.setFirstName("Dobby beta");
        careContact.setLastName(MembershipConstants.CR);
        careContact.setNickName("Beta CR");
        careContact.setAlexaEnabled(true);
        careContact.setCoboEnabled(true);
        careContact.setDropInEnabled(false);
        careContact.setPhoneNumber("2065551234");
        careContact.setPhoneNumberType("Mobile");
        careContact.setCommsEnabled(true);
        careContact.setCommsId("amzn1.comms.id.person.amzn1~amzn1.account.AFXWXTNQGR7LRM2DYD5T7TQP7KNQ");
        relationshipGroupModel.setGroupOwner(careContact);
        relationshipListener.onSuccess(relationshipGroupModel);
    }

    @Override // com.amazon.alexa.biloba.service.BilobaFrontEndService
    public Response getDashboardCards() {
        return this.requests.executeHttpRequest(this.requests.getDashboardCardsUrl(), 0);
    }

    @Override // com.amazon.alexa.biloba.service.BilobaFrontEndService
    public void modifyAlertConfiguration(String str, String str2, AlertConfigurationViewItemModel alertConfigurationViewItemModel, final BilobaFrontEndService.ModifyAlertConfigurationsListener modifyAlertConfigurationsListener) {
        new AsyncTask<AlertConfigurationRequest, Void, Response>() { // from class: com.amazon.alexa.biloba.service.DefaultBilobaFrontEndService.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Response doInBackground(AlertConfigurationRequest... alertConfigurationRequestArr) {
                HttpUrl modifyAlertConfigurationUrl = DefaultBilobaFrontEndService.this.requests.modifyAlertConfigurationUrl(alertConfigurationRequestArr[0].getRelationshipId(), alertConfigurationRequestArr[0].getAlertConfigurationId());
                String str3 = DefaultBilobaFrontEndService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpUrl: ");
                outline107.append(modifyAlertConfigurationUrl.toString());
                LogUtils.i(str3, outline107.toString());
                try {
                    return DefaultBilobaFrontEndService.this.requests.executeHttpRequest(modifyAlertConfigurationUrl, 3, DefaultBilobaFrontEndService.convertToJsonString(alertConfigurationRequestArr[0].getAlertConfigurationViewItemModel()));
                } catch (JSONException e) {
                    LogUtils.e(DefaultBilobaFrontEndService.TAG, "Error converting json object to string", e);
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Response response) {
                super.onPostExecute((AnonymousClass2) response);
                if (response != null && response.isSuccessful()) {
                    modifyAlertConfigurationsListener.onSuccess();
                    return;
                }
                BilobaFrontEndService.ModifyAlertConfigurationsListener modifyAlertConfigurationsListener2 = modifyAlertConfigurationsListener;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to modify alert configuration List with error");
                outline107.append(response.body());
                modifyAlertConfigurationsListener2.onError(new AlertConfigurationException(outline107.toString(), -2));
            }
        }.execute(new AlertConfigurationRequest(str, str2, alertConfigurationViewItemModel));
    }
}
