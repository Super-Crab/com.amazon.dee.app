package com.amazon.alexa.drive.entertainment.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.model.AudiblePlayPayload;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardItem;
import com.amazon.alexa.drive.entertainment.model.EntertainmentItemModel;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.entertainment.model.PlayPayload;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class FetchEntertainmentCardDataAsyncTask extends AsyncTask<String, Integer, Object[]> {
    private static final int ENTERTAINMENT_ITEM_LIMIT = 10;
    private static final String TAG = FetchEntertainmentCardDataAsyncTask.class.getSimpleName();
    private Context mContext;
    private DeviceInformation mDeviceInformation;
    private String mDeviceType;
    private String mDsn;
    private EntertainmentAsyncTaskFactory mEntertainmentAsyncTaskFactory;
    private final EntertainmentMetrics mEntertainmentMetrics;
    private EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener mOnEntertainmentCardsFetchCompleteListener;

    public FetchEntertainmentCardDataAsyncTask(EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener onEntertainmentCardsFetchCompleteListener, EntertainmentAsyncTaskFactory entertainmentAsyncTaskFactory, Context context, EntertainmentMetrics entertainmentMetrics) {
        Log.i(TAG, "FetchEntertainmentCardDataAsyncTask Constructor");
        this.mOnEntertainmentCardsFetchCompleteListener = onEntertainmentCardsFetchCompleteListener;
        this.mEntertainmentAsyncTaskFactory = entertainmentAsyncTaskFactory;
        this.mDeviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline21(DeviceInformation.class);
        this.mEntertainmentMetrics = entertainmentMetrics;
        Preconditions.checkNotNull(this.mDeviceInformation);
        try {
            this.mDsn = this.mDeviceInformation.getDeviceSerialNumber();
            this.mDeviceType = this.mDeviceInformation.getDeviceType();
        } catch (DeviceInformationException e) {
            String str = TAG;
            Log.e(str, "DeviceInformationException " + e);
        }
        this.mContext = context;
    }

    String getContentToken(JSONObject jSONObject) {
        Log.i(TAG, "getContentToken ");
        try {
            return jSONObject.getJSONObject(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_MEDIA_COLLECTION).getString("contentToken");
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "getContentToken() | JSONException: " + e);
            return null;
        }
    }

    String getContentTokenForSection(JSONObject jSONObject, String str) {
        Log.i(TAG, "getContentTokenForSection");
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_SECTION);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                JSONArray jSONArray2 = jSONObject2.getJSONArray("behaviors");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    if (jSONArray2.getJSONObject(i2).optString("type", "").equals(str)) {
                        return getContentToken(jSONObject2);
                    }
                }
            }
            return null;
        } catch (JSONException e) {
            Log.e(TAG, "getContentTokenForSection() | JSONException: " + e);
            return null;
        }
    }

    String getDSN() {
        return this.mDsn;
    }

    String getDeviceType() {
        return this.mDeviceType;
    }

    EntertainmentCardItem getEntertainmentCardFromJSON(JSONObject jSONObject) {
        PlayPayload audiblePlayPayload;
        Log.i(TAG, "getEntertainmentCardFromJSON ");
        EntertainmentItemModel entertainmentItemModel = (EntertainmentItemModel) new Gson().fromJson(jSONObject.toString(), (Class<Object>) EntertainmentItemModel.class);
        EntertainmentCardItem entertainmentCardItem = new EntertainmentCardItem();
        entertainmentCardItem.setTitle(entertainmentItemModel.getTitle());
        entertainmentCardItem.setSubTitle(entertainmentItemModel.getSubTitle());
        entertainmentCardItem.setProviderId(entertainmentItemModel.getProviderId());
        entertainmentCardItem.setQueueId(entertainmentItemModel.getContentToken());
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS);
            JSONArray jSONArray2 = jSONObject.getJSONArray(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_IMAGES);
            if (jSONArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getJSONObject(i));
                }
                entertainmentCardItem.setActions(arrayList);
            }
            if (jSONArray2 != null) {
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    arrayList2.add(jSONArray2.getJSONObject(i2));
                }
                entertainmentCardItem.setAlbumArtUrls(arrayList2);
            }
            JSONObject jSONObject2 = jSONArray.getJSONObject(0);
            String string = jSONObject2.getString("uri");
            String replace = jSONObject2.getString(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_PAYLOAD_TEMPLATE).replace("\n", "");
            entertainmentCardItem.setPlayUri(string);
            if (!EntertainmentConstants.AUDIBLE_PROVIDER_ID.equals(entertainmentItemModel.getProviderId()) && !EntertainmentConstants.KINDLE_PROVIDER_ID.equals(entertainmentItemModel.getProviderId())) {
                audiblePlayPayload = new PlayPayload(new JSONObject(replace));
                audiblePlayPayload.setDeviceSerialNumber(getDSN());
                audiblePlayPayload.setDeviceType(getDeviceType());
                entertainmentCardItem.setPlayPayload(audiblePlayPayload);
                return entertainmentCardItem;
            }
            audiblePlayPayload = new AudiblePlayPayload(new JSONObject(replace));
            audiblePlayPayload.setDeviceSerialNumber(getDSN());
            audiblePlayPayload.setDeviceType(getDeviceType());
            entertainmentCardItem.setPlayPayload(audiblePlayPayload);
            return entertainmentCardItem;
        } catch (JSONException e) {
            Log.e(TAG, "JSONException: " + e);
            return null;
        }
    }

    EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener getOnEntertainmentCardsFetchCompleteListener() {
        return this.mOnEntertainmentCardsFetchCompleteListener;
    }

    JSONArray getSectionJsonArray(Object[] objArr) {
        return (JSONArray) objArr[0];
    }

    JSONObject getSectionJsonObject(Object[] objArr) {
        return (JSONObject) objArr[0];
    }

    void handleAPIResult(String str, Object[] objArr) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != 3208415) {
            if (hashCode == 1970241253 && str.equals(EntertainmentConstants.TYPE_SECTION)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("home")) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            Log.i(TAG, "onPostExecute TYPE_HOME");
            this.mEntertainmentAsyncTaskFactory.createFetchEntertainmentCardDataAsyncTask(this.mOnEntertainmentCardsFetchCompleteListener).execute(GeneratedOutlineSupport1.outline72(EntertainmentConstants.ENT_SECTION, getContentTokenForSection(getSectionJsonObject(objArr), EntertainmentConstants.RECENTLY_PLAYED_SECTION_TYPE)), EntertainmentConstants.TYPE_SECTION);
        } else if (c != 1) {
            Log.i(TAG, "GetJSON | onPostExecute(): unexpected result");
        } else {
            Log.i(TAG, "onPostExecute TYPE_SECTION");
            parseSectionJSONToCards(getSectionJsonArray(objArr));
        }
    }

    List<EntertainmentCardItem> parseSectionJSONToCards(JSONArray jSONArray) {
        Log.i(TAG, "parseSectionJSONToCards ");
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray2 = jSONArray.getJSONObject(0).getJSONArray(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS);
            if (jSONArray2 == null) {
                return null;
            }
            for (int i = 0; i < Math.min(10, jSONArray2.length()); i++) {
                EntertainmentCardItem entertainmentCardFromJSON = getEntertainmentCardFromJSON(jSONArray2.getJSONObject(i));
                if (entertainmentCardFromJSON != null) {
                    arrayList.add(entertainmentCardFromJSON);
                }
            }
            getOnEntertainmentCardsFetchCompleteListener().onEntertainmentCardsFetchComplete(arrayList);
            return arrayList;
        } catch (JSONException unused) {
            Log.e(TAG, "Json exception when parsing entertainment json array");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Object[] doInBackground(String... strArr) {
        Object jSONArray;
        CoralService coralService = (CoralService) GeneratedOutlineSupport1.outline21(CoralService.class);
        Preconditions.checkNotNull(coralService);
        try {
            Response execute = coralService.request(strArr[0]).get().asRaw().execute();
            if (execute == null || execute.body() == null) {
                return null;
            }
            if ("home".equals(strArr[1])) {
                jSONArray = new JSONObject(execute.body().string());
            } else {
                jSONArray = new JSONArray(execute.body().string());
            }
            return new Object[]{jSONArray, strArr[1]};
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("doInBackground() : ", e, TAG);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Object[] objArr) {
        Log.i(TAG, "onPostExecute ");
        if (objArr == null) {
            this.mEntertainmentMetrics.logFetchEntertainmentCardDataApi("Failure");
            getOnEntertainmentCardsFetchCompleteListener().onEntertainmentCardsFetchFailed(new MediaErrorPayload(this.mContext.getString(R.string.media_error_load_title), this.mContext.getString(R.string.media_error_play_subtitle)));
            return;
        }
        this.mEntertainmentMetrics.logFetchEntertainmentCardDataApi("Success");
        handleAPIResult((String) objArr[1], objArr);
    }
}
