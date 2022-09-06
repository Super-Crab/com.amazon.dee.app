package com.amazon.alexa.sharing.api;

import android.util.Log;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingException;
import com.amazon.alexa.sharing.api.model.ResultHandler;
import com.amazon.alexa.sharing.repo.interfaces.IAnnouncement;
import com.amazon.alexa.sharing.repo.models.Result;
import com.amazon.alexa.sharing.repo.models.acms.announcement.Announcement;
import com.amazon.alexa.sharing.repo.models.acms.announcement.SendAudioAnnouncementInput;
import com.amazon.alexa.sharing.repo.models.acms.announcement.enums.AnnouncementType;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commsnetworking.CommsNetworkRequest;
import com.amazon.commsnetworking.NetworkException;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.amazon.commsnetworking.metrics.MetricMetadata;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class AnnouncementAPI implements IAnnouncement {
    private static final String GET_ANNOUNCEMENT = "getAnnouncement";
    private static final String SEND_ANNOUNCEMENT = "sendAnnouncement";
    private static final String TAG = "Announcement-API";
    private final CommsBridgeService commsBridgeService;
    private final Lazy<AlexaCommsCoreRemoteConfigurationService> commsConfigService;
    private final Lazy<INetworkingClient> networkingClientLazy;
    private final ResultHandler resultHandler = new ResultHandler(TAG);

    public AnnouncementAPI(CommsBridgeService commsBridgeService, Lazy<INetworkingClient> lazy, Lazy<AlexaCommsCoreRemoteConfigurationService> lazy2) {
        this.commsBridgeService = commsBridgeService;
        this.networkingClientLazy = lazy;
        this.commsConfigService = lazy2;
    }

    private void constructJSONObject(String str, String str2, String str3, JSONObject jSONObject, String str4) throws JSONException {
        jSONObject.put("type", str4);
        jSONObject.put("directedId", str2);
        jSONObject.put(str4 == Constants.TEXT_ANNOUNCEMENT_MESSAGE_TYPE ? "messageText" : "mediaId", str);
        jSONObject.put("senderFirstName", str3);
    }

    private INetworkResponse executeNetworkRequest(INetworkRequest iNetworkRequest) throws NetworkException {
        return this.networkingClientLazy.mo358get().execute(iNetworkRequest);
    }

    private Announcement[] getAnnouncementFromResponse(JSONObject jSONObject) throws AlexaSharingException {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("announcements");
            if (jSONArray != null) {
                Announcement[] announcementArr = new Announcement[jSONArray.length()];
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    announcementArr[i] = new Announcement.Builder().setID(jSONObject2.getString("id")).setType(AnnouncementType.valueOf(jSONObject2.getString("type"))).setMessageText(jSONObject2.getString("messageText")).setExpiryDate(jSONObject2.getString("expiryDate")).setSourceName(jSONObject2.getString("sourceName")).setOriginDate(jSONObject2.getString("originDate")).setMediaId(jSONObject2.optString("mediaId", null)).setSpeakerName(jSONObject2.optString("speakerName", null)).build();
                }
                return announcementArr;
            }
            throw new AlexaSharingException("Get all announcement was not successful, ResponseBody was null");
        } catch (JSONException e) {
            Log.e(TAG, "Get all announcement was not successful, JSONException Occurred", e);
            throw new AlexaSharingException("Get all announcement was not successful, JSONException Occurred");
        }
    }

    private INetworkRequest getAnnouncementNetworkRequest(String str, String str2) {
        return new CommsNetworkRequest.Builder().withMethod(CommsNetworkRequest.Method.POST).withUrl("https://alexa-comms-mobile-service.amazon.com", String.format(com.amazon.alexa.sharing.Constants.ANNOUNCEMENT_PATH, str2)).withCommsNetworkingRequestTag(new MetricMetadata(com.amazon.alexa.sharing.Constants.ALEXA_SHARING_CLIENT_SOURCE, String.format(MetricKeys.ALEXA_SHARING_NETWORK_METRIC_FORMAT, GET_ANNOUNCEMENT))).build();
    }

    private INetworkRequest postAnnouncementNetworkRequest(String str, JSONObject jSONObject, String str2) {
        return new CommsNetworkRequest.Builder().withMethod(CommsNetworkRequest.Method.POST).withUrl("https://alexa-comms-mobile-service.amazon.com", String.format(com.amazon.alexa.sharing.Constants.ANNOUNCEMENT_PATH, str2)).withCommsNetworkingRequestTag(new MetricMetadata(com.amazon.alexa.sharing.Constants.ALEXA_SHARING_CLIENT_SOURCE, String.format(MetricKeys.ALEXA_SHARING_NETWORK_METRIC_FORMAT, SEND_ANNOUNCEMENT))).build().withPayload("application/json", jSONObject.toString());
    }

    @Override // com.amazon.alexa.sharing.repo.interfaces.IAnnouncement
    public Announcement[] getAllAnnouncements(String str) throws AlexaSharingException {
        try {
            return getAnnouncementFromResponse(new JSONObject(executeNetworkRequest(getAnnouncementNetworkRequest("getAllAnnouncements", str)).getBody()));
        } catch (NetworkException | JSONException e) {
            Log.e(TAG, "Get all announcement was not successful, Exception Occurred", e);
            throw new AlexaSharingException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("AnnouncementAPI Error has occurred")));
        }
    }

    @Override // com.amazon.alexa.sharing.repo.interfaces.IAnnouncement
    public void openCreateAnnouncement() {
        try {
            throw new Exception("Method not implemented");
        } catch (Exception unused) {
            Log.e(TAG, "Method not implemented");
        }
    }

    @Override // com.amazon.alexa.sharing.repo.interfaces.IAnnouncement
    public Result sendAudioAnnouncement(SendAudioAnnouncementInput sendAudioAnnouncementInput, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            constructJSONObject(sendAudioAnnouncementInput.getFilePath(), str, sendAudioAnnouncementInput.getFirstName(), jSONObject, "announcement/audio");
            return this.resultHandler.processNetworkResponse(executeNetworkRequest(postAnnouncementNetworkRequest("sendAudioAnnouncement", jSONObject, str)), "sendAudioAnnouncement");
        } catch (NetworkException | JSONException e) {
            Log.e(TAG, "Sending audio announcement was not successful", e);
            return this.resultHandler.exceptionOccurredResponse("sendAudioAnnouncement");
        }
    }

    @Override // com.amazon.alexa.sharing.repo.interfaces.IAnnouncement
    public Result sendTextAnnouncement(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            constructJSONObject(str, str2, str3, jSONObject, Constants.TEXT_ANNOUNCEMENT_MESSAGE_TYPE);
            return this.resultHandler.processNetworkResponse(executeNetworkRequest(postAnnouncementNetworkRequest("sendTextAnnouncement", jSONObject, str2)), "sendTextAnnouncement");
        } catch (NetworkException | JSONException e) {
            Log.e(TAG, "Sending text announcement was not successful", e);
            return this.resultHandler.exceptionOccurredResponse("sendTextAnnouncement");
        }
    }
}
