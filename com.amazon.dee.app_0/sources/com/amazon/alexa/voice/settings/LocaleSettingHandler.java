package com.amazon.alexa.voice.settings;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.locale.LocaleParameters;
import com.amazon.alexa.voice.locale.LocaleUpdateCallback;
import com.amazon.alexa.voice.locale.SupportedLocalesCallback;
import com.amazon.alexa.voice.ui.onedesign.util.CharSequenceUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class LocaleSettingHandler implements SettingsHandler {
    @VisibleForTesting
    static final String AVAILABLE_LOCALES = "availableLocales";
    @VisibleForTesting
    static final String CURRENT_LOCALE_VALUE = "currentLocale";
    static final String KEY_COUNTRY = "country";
    static final String KEY_LANGUAGE = "language";
    static final String KEY_LOCALE = "locale";
    @VisibleForTesting
    static final String NEEDS_LOCALE_EDUCATION = "needsLocaleEducation";
    @VisibleForTesting
    static final String PRESELECTED_LOCALE = "preselectedLocale";
    @VisibleForTesting
    static final String RECOMMENDED_LOCALES = "recommendedLocales";
    @VisibleForTesting
    static final String REQUEST_ALL_LOCALE = "voice::localeSettingsRequest::allLocales";
    @VisibleForTesting
    static final String REQUEST_CURRENT_LOCALE = "voice::localeSettingsRequest::currentLocale";
    @VisibleForTesting
    static final String RESPONSE_ALL_LOCALE = "voice::localeSettingsResponse::allLocales";
    @VisibleForTesting
    static final String RESPONSE_CURRENT_LOCALE = "voice::localeSettingsResponse::currentLocale";
    @VisibleForTesting
    static final String SET_LOCALE_VALUE = "setLocale";
    private static final String TAG = "LocaleSettingHandler";
    private final EventBus eventBus;
    private final LocaleInteractor localeInteractor;
    private UUID subscriptionId;

    public LocaleSettingHandler(LocaleInteractor localeInteractor, EventBus eventBus) {
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

    private JSONObject getLocaleJson(Locale locale) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_LANGUAGE, CharSequenceUtils.capitalize(locale.getDisplayLanguage(locale)));
        jSONObject.put(KEY_COUNTRY, CharSequenceUtils.capitalize(locale.getDisplayCountry(locale)));
        jSONObject.put("locale", locale.toLanguageTag());
        return jSONObject;
    }

    private void publishAllLocaleSettingsInfo(LocaleParameters localeParameters) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(CURRENT_LOCALE_VALUE, getLocaleJson(localeParameters.getCurrentLocale()));
        jSONObject.put(RECOMMENDED_LOCALES, getLocaleArray(localeParameters.getRecommendedLocales()));
        jSONObject.put(AVAILABLE_LOCALES, getLocaleArray(localeParameters.getSupportedLocales()));
        jSONObject.put(PRESELECTED_LOCALE, getLocaleJson(localeParameters.getPreselectedLocale()));
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
            jSONObject.put(CURRENT_LOCALE_VALUE, getLocaleJson(this.localeInteractor.getPrimaryLocale()));
            String str = "EventBus message : " + build.getEventType();
            String str2 = "EventBus payload : " + build.getPayloadAsString();
            this.eventBus.publish(new Message.Builder().setEventType(RESPONSE_CURRENT_LOCALE).setPayload(jSONObject.toString()).setSource(Message.Source.Local).build());
        } catch (Exception e) {
            Log.e(TAG, "Error constructing locale settings info", e);
        }
    }

    private void requestAllLocaleSettingsInfo() {
        this.localeInteractor.fetchSupportedLocales(new SupportedLocalesCallback() { // from class: com.amazon.alexa.voice.settings.-$$Lambda$LocaleSettingHandler$bO9ht5ddz6jpp5_MrZXMkjHY8Ug
            @Override // com.amazon.alexa.voice.locale.SupportedLocalesCallback
            public final void onLocaleParams(LocaleParameters localeParameters) {
                LocaleSettingHandler.this.lambda$requestAllLocaleSettingsInfo$1$LocaleSettingHandler(localeParameters);
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
            this.localeInteractor.setSystemLocalesUpdatedTo(Arrays.asList(Locale.forLanguageTag(jSONObject.getJSONObject(SET_LOCALE_VALUE).getString("locale"))), 2, new LocaleUpdateCallback() { // from class: com.amazon.alexa.voice.settings.-$$Lambda$LocaleSettingHandler$C7yGHSH134Vhd_ipebOAvLfyCcc
                @Override // com.amazon.alexa.voice.locale.LocaleUpdateCallback
                public final void onLocaleUpdated(boolean z, List list) {
                    LocaleSettingHandler.this.lambda$handle$0$LocaleSettingHandler(z, list);
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

    public /* synthetic */ void lambda$handle$0$LocaleSettingHandler(boolean z, List list) {
        requestAllLocaleSettingsInfo();
    }

    public /* synthetic */ void lambda$requestAllLocaleSettingsInfo$1$LocaleSettingHandler(LocaleParameters localeParameters) {
        try {
            publishAllLocaleSettingsInfo(localeParameters);
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
