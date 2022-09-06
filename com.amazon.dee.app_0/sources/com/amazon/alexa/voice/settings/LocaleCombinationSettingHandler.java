package com.amazon.alexa.voice.settings;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.voice.locale.LocaleCombinationParameters;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.locale.LocaleUpdateCallback;
import com.amazon.alexa.voice.locale.SupportedLocaleCombinationsCallback;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LocaleGroup;
import com.amazon.alexa.voice.ui.onedesign.util.CharSequenceUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class LocaleCombinationSettingHandler implements SettingsHandler {
    @VisibleForTesting
    static final String AVAILABLE_LOCALES = "availableCombinationLocales";
    @VisibleForTesting
    static final String CURRENT_LOCALE_VALUE = "currentLocales";
    static final String KEY_COUNTRY = "country";
    static final String KEY_LANGUAGE = "language";
    static final String KEY_LOCALE = "locale";
    @VisibleForTesting
    static final String NEEDS_LOCALE_EDUCATION = "needsLocaleEducation";
    @VisibleForTesting
    static final String PRESELECTED_LOCALE = "preselectedLocales";
    @VisibleForTesting
    static final String RECOMMENDED_LOCALES = "recommendedCombinationLocales";
    @VisibleForTesting
    static final String REQUEST_ALL_LOCALE = "voice::localeCombinationSettingsRequest::allLocales";
    @VisibleForTesting
    static final String REQUEST_CURRENT_LOCALE = "voice::localeCombinationSettingsRequest::currentLocales";
    @VisibleForTesting
    static final String RESPONSE_ALL_LOCALE = "voice::localeCombinationSettingsResponse::allLocales";
    @VisibleForTesting
    static final String RESPONSE_CURRENT_LOCALE = "voice::localeCombinationSettingsResponse::currentLocales";
    @VisibleForTesting
    static final String SET_LOCALE_VALUE = "setLocales";
    private static final String TAG = "LocaleCombinationSettingHandler";
    private final EventBus eventBus;
    private final LocaleInteractor localeInteractor;
    private UUID subscriptionId;

    public LocaleCombinationSettingHandler(LocaleInteractor localeInteractor, EventBus eventBus) {
        this.localeInteractor = localeInteractor;
        this.eventBus = eventBus;
    }

    private JSONArray getLocaleArray(List<Locale> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Locale locale : list) {
            jSONArray.put(getLocaleJson(locale));
        }
        return jSONArray;
    }

    private JSONArray getLocaleCombinationArray(ArrayList<LocaleGroup> arrayList) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<LocaleGroup> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            jSONArray.put(getLocaleArray(it2.next().getLocales()));
        }
        return jSONArray;
    }

    private JSONObject getLocaleJson(Locale locale) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_LANGUAGE, CharSequenceUtils.capitalize(locale.getDisplayLanguage(locale)));
        jSONObject.put(KEY_COUNTRY, CharSequenceUtils.capitalize(locale.getDisplayCountry(locale)));
        jSONObject.put("locale", locale.toLanguageTag());
        return jSONObject;
    }

    private void publishAllLocaleSettingsInfo(LocaleCombinationParameters localeCombinationParameters) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(CURRENT_LOCALE_VALUE, getLocaleArray(localeCombinationParameters.getCurrentLocale().getLocales()));
        jSONObject.put(RECOMMENDED_LOCALES, getLocaleCombinationArray(localeCombinationParameters.getRecommendedLocales()));
        jSONObject.put(AVAILABLE_LOCALES, getLocaleCombinationArray(localeCombinationParameters.getSupportedLocales()));
        jSONObject.put(PRESELECTED_LOCALE, getLocaleArray(localeCombinationParameters.getPreselectedLocale().getLocales()));
        Message build = new Message.Builder().setEventType(RESPONSE_ALL_LOCALE).setPayload(jSONObject.toString()).setSource(Message.Source.Local).build();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EventBus message : ");
        outline107.append(build.getEventType());
        outline107.toString();
        String str = "EventBus payload : " + build.getPayloadAsString();
        this.eventBus.publish(build);
    }

    private void publishCurrentLocaleSettingsInfo() {
        Message build;
        Log.i(TAG, "publishCurrentLocaleSettingsInfo : ");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NEEDS_LOCALE_EDUCATION, this.localeInteractor.needsLanguagePickerEducation());
            jSONObject.put(CURRENT_LOCALE_VALUE, getLocaleArray(this.localeInteractor.getCurrentLocales()));
            String str = "EventBus message : " + build.getEventType();
            String str2 = "EventBus payload : " + build.getPayloadAsString();
            this.eventBus.publish(new Message.Builder().setEventType(RESPONSE_CURRENT_LOCALE).setPayload(jSONObject.toString()).setSource(Message.Source.Local).build());
        } catch (Exception e) {
            Log.e(TAG, "Error constructing locale settings info", e);
        }
    }

    private void requestAllLocaleSettingsInfo() {
        this.localeInteractor.fetchSupportedLocalesCombinations(new SupportedLocaleCombinationsCallback() { // from class: com.amazon.alexa.voice.settings.-$$Lambda$LocaleCombinationSettingHandler$0zXriekfnQPBA7O_dJ-L-ei6BoY
            @Override // com.amazon.alexa.voice.locale.SupportedLocaleCombinationsCallback
            public final void onLocaleCombinationParams(LocaleCombinationParameters localeCombinationParameters) {
                LocaleCombinationSettingHandler.this.lambda$requestAllLocaleSettingsInfo$1$LocaleCombinationSettingHandler(localeCombinationParameters);
            }
        });
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public UUID getSubscriptionId() {
        return this.subscriptionId;
    }

    @Override // com.amazon.alexa.eventbus.api.MessageHandler
    public void handle(@NonNull Message message) {
        String payloadAsString = message.getPayloadAsString();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Incoming EventBus message : ");
        outline107.append(message.getEventType());
        outline107.toString();
        String str = "Incoming EventBus payload : " + message.getPayloadAsString();
        if (TextUtils.isEmpty(payloadAsString)) {
            if (REQUEST_CURRENT_LOCALE.equals(message.getEventType())) {
                publishCurrentLocaleSettingsInfo();
                return;
            } else {
                requestAllLocaleSettingsInfo();
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(payloadAsString);
            if (!jSONObject.has(SET_LOCALE_VALUE)) {
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray(SET_LOCALE_VALUE);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(Locale.forLanguageTag(jSONArray.getJSONObject(i).getString("locale")));
            }
            this.localeInteractor.setSystemLocalesUpdatedTo(arrayList, 2, new LocaleUpdateCallback() { // from class: com.amazon.alexa.voice.settings.-$$Lambda$LocaleCombinationSettingHandler$821HJb3wXLxRTcaQ6U5ORfwKGC0
                @Override // com.amazon.alexa.voice.locale.LocaleUpdateCallback
                public final void onLocaleUpdated(boolean z, List list) {
                    LocaleCombinationSettingHandler.this.lambda$handle$0$LocaleCombinationSettingHandler(z, list);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error constructing locale settings info", e);
        }
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public boolean isEventTypeSupported(String str) {
        return REQUEST_CURRENT_LOCALE.equals(str) || REQUEST_ALL_LOCALE.equals(str);
    }

    public /* synthetic */ void lambda$handle$0$LocaleCombinationSettingHandler(boolean z, List list) {
        requestAllLocaleSettingsInfo();
    }

    public /* synthetic */ void lambda$requestAllLocaleSettingsInfo$1$LocaleCombinationSettingHandler(LocaleCombinationParameters localeCombinationParameters) {
        try {
            publishAllLocaleSettingsInfo(localeCombinationParameters);
        } catch (Exception e) {
            Log.e(TAG, "Error constructing locale settings info", e);
        }
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void startSubscription(UUID uuid) {
        this.subscriptionId = uuid;
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void stopSubscription() {
        this.subscriptionId = null;
    }
}
