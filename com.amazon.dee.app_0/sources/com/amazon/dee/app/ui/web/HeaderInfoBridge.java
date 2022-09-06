package com.amazon.dee.app.ui.web;

import android.text.TextUtils;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.dee.app.services.header.HeaderCacheService;
import com.amazon.deecomms.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class HeaderInfoBridge extends JavaScriptBridge {
    private HeaderCacheService headerCacheService;
    private JavaScriptDelegate javaScriptDelegate;
    private Map<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    private class ConfigureHouseholdPickerMethod implements JavaScriptBridgeMethod {
        private ConfigureHouseholdPickerMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            boolean optBoolean = jSONObject.optBoolean("visible", false);
            JSONArray optJSONArray = jSONObject.optJSONArray("accounts");
            HouseholdLibraryInfo householdLibraryInfo = null;
            String optString = jSONObject.optString("selectedAccount", null);
            if (!optBoolean || optJSONArray.length() <= 1) {
                HeaderInfoBridge.this.javaScriptDelegate.setHouseholdVisible(false);
            } else {
                ArrayList arrayList = new ArrayList(optJSONArray.length());
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String optString2 = jSONObject2.optString("description");
                    if (TextUtils.isEmpty(optString2)) {
                        optString2 = jSONObject2.getString("firstName");
                    }
                    HouseholdLibraryInfo householdLibraryInfo2 = new HouseholdLibraryInfo(optString2, jSONObject2.getString("customerId"));
                    arrayList.add(householdLibraryInfo2);
                    if (TextUtils.equals(householdLibraryInfo2.customerId, optString)) {
                        householdLibraryInfo = householdLibraryInfo2;
                    }
                }
                HeaderInfoBridge.this.javaScriptDelegate.setHouseholdVisible(true);
                HeaderInfoBridge.this.javaScriptDelegate.setHouseholdLibraries(arrayList);
                HeaderInfoBridge.this.javaScriptDelegate.setSelectedLibrary(householdLibraryInfo);
            }
            HeaderInfoBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class SetHeaderInfoMethod implements JavaScriptBridgeMethod {
        private SetHeaderInfoMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            HeaderInfoBridge.setHeader(HeaderInfoBridge.this.javaScriptDelegate, jSONObject);
            HeaderInfoBridge.this.headerCacheService.cacheRequest(jSONObject);
            HeaderInfoBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public HeaderInfoBridge(JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, HeaderCacheService headerCacheService) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.javaScriptDelegate = javaScriptDelegate;
        this.headerCacheService = headerCacheService;
        this.headerCacheService.setJavaScriptDelegate(this.javaScriptDelegate);
        this.mMethods.put("setHeader", new SetHeaderInfoMethod());
        this.mMethods.put("configureHouseholdPicker", new ConfigureHouseholdPickerMethod());
    }

    public static void setHeader(JavaScriptDelegate javaScriptDelegate, JSONObject jSONObject) throws JSONException {
        CharSequence optString;
        if (jSONObject == null || (optString = jSONObject.optString("title")) == null) {
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("selectedDevice");
        JSONArray optJSONArray = jSONObject.optJSONArray("devices");
        JSONArray optJSONArray2 = jSONObject.optJSONArray(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS);
        if (optJSONArray2 != null) {
            for (int i = 0; i < optJSONArray2.length(); i++) {
                JSONObject jSONObject2 = optJSONArray2.getJSONObject(i);
                String string = jSONObject2.getString("id");
                CharSequence string2 = jSONObject2.getString("title");
                if (TextUtils.equals(string, "a2s-your-skills-title") || TextUtils.equals(string, "a2s-gateway-title")) {
                    javaScriptDelegate.setYourSkillsTitleAndDisplay(string2, TextUtils.equals(string, "a2s-your-skills-title"));
                    javaScriptDelegate.setYourSkillsVisible(true);
                }
            }
        } else {
            javaScriptDelegate.setYourSkillsVisible(false);
        }
        DeviceInfo deviceInfo = null;
        if (optJSONArray == null) {
            javaScriptDelegate.setHeaderVisible(true);
            javaScriptDelegate.setHeaderTitle(optString);
            javaScriptDelegate.setDevices(null);
            return;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        String optString2 = optJSONObject != null ? optJSONObject.optString(Constants.BUNDLE_SERIAL_NUMBER, null) : null;
        int length = optJSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject3 = optJSONArray.getJSONObject(i2);
            DeviceInfo deviceInfo2 = new DeviceInfo(jSONObject3.getString("accountName"), jSONObject3.getString(Constants.BUNDLE_SERIAL_NUMBER), jSONObject3.optString("deviceType"));
            arrayList.add(deviceInfo2);
            if (TextUtils.equals(deviceInfo2.serialNumber, optString2)) {
                deviceInfo = deviceInfo2;
            }
        }
        javaScriptDelegate.setHeaderVisible(true);
        javaScriptDelegate.setHeaderTitle(optString);
        javaScriptDelegate.setDevices(arrayList);
        javaScriptDelegate.setSelectedDevice(deviceInfo);
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "HeaderInfoBridge";
    }
}
