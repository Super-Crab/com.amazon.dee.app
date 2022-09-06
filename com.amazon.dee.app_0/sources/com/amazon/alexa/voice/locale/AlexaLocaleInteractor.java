package com.amazon.alexa.voice.locale;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.compat.AlexaSupportedLocalesListener;
import com.amazon.alexa.api.compat.AlexaSupportedLocalesListenerv2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.voice.locale.LocaleCombinationParameters;
import com.amazon.alexa.voice.locale.LocaleParameters;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LocaleGroup;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes11.dex */
public class AlexaLocaleInteractor extends NoOpAlexaServiceConnectionListener implements LocaleInteractor {
    private static final List<String> ALLOWED_COUNTRY_CODE_FOR_MATCHING_LOCALE = Collections.unmodifiableList(Arrays.asList("SA", "AE"));
    private static final String EMPTY_STRING = "";
    private static final String LOCALE_UPDATE_METRIC_EVENT_SEPARATOR = ":";
    private static final String TAG = "AlexaLocaleInteractor";
    private final AlexaServicesConnection alexaServicesConnection;
    private List<Locale> cachedCurrentLocales;
    private final DlsFeatureEnabler dlsFeatureEnabler;
    private final IdentityService identityService;
    private final LocaleAPI localeAPI;
    private final LocalePreference localePreference;
    private final VoxMetricEventProcessingService voxMetricEventProcessingService;
    private ArrayList<Locale> cachedSupportedLocales = new ArrayList<>();
    private ArrayList<List<Locale>> cachedSupportedLocalesCombinations = new ArrayList<>();
    private final LocaleCache localeCache = new LocaleCache();

    /* loaded from: classes11.dex */
    private class LocaleCache implements AlexaLocalesListener {
        private LocaleCache() {
        }

        @Override // com.amazon.alexa.api.AlexaLocalesListener
        public void onLocales(@NonNull List<Locale> list) {
            if (list == null || list.isEmpty()) {
                return;
            }
            AlexaLocaleInteractor.this.cachedCurrentLocales = list;
            if (AlexaLocaleInteractor.this.dlsFeatureEnabler.isDLSEnabled() || AlexaLocaleInteractor.this.cachedCurrentLocales.size() <= 1) {
                return;
            }
            final List<Locale> asList = Arrays.asList((Locale) AlexaLocaleInteractor.this.cachedCurrentLocales.get(0));
            String str = "Reset locales to " + asList;
            AlexaLocaleInteractor.this.localeAPI.setLocales(AlexaLocaleInteractor.this.alexaServicesConnection, asList, new AlexaApiCallbacks() { // from class: com.amazon.alexa.voice.locale.AlexaLocaleInteractor.LocaleCache.1
                @Override // com.amazon.alexa.api.AlexaApiCallbacks
                public void onFailure(ApiCallFailure apiCallFailure) {
                    AlexaLocaleInteractor.this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.VOX_LOCALE_UPDATED_FAILED));
                }

                @Override // com.amazon.alexa.api.AlexaApiCallbacks
                public void onSuccess() {
                    AlexaLocaleInteractor.this.cachedCurrentLocales = asList;
                    AlexaLocaleInteractor.this.localePreference.settingUpdatedByUser(false);
                    AlexaLocaleInteractor.this.recordMetricEvent(asList, 0);
                }
            });
        }
    }

    /* loaded from: classes11.dex */
    private class OneOffAlexaSupportedLocalesListener implements AlexaSupportedLocalesListener {
        private final SupportedLocalesCallback callback;

        OneOffAlexaSupportedLocalesListener(SupportedLocalesCallback supportedLocalesCallback) {
            this.callback = supportedLocalesCallback;
        }

        @Override // com.amazon.alexa.api.compat.AlexaSupportedLocalesListener
        public void onSupportedLocales(Set<Locale> set) {
            AlexaLocaleInteractor.this.cachedSupportedLocales.clear();
            AlexaLocaleInteractor.this.cachedSupportedLocales.addAll(set);
            AlexaLocaleInteractor.this.localeAPI.deregisterSupportedLocales(AlexaLocaleInteractor.this.alexaServicesConnection, this);
            this.callback.onLocaleParams(AlexaLocaleInteractor.this.buildLocaleParameters());
        }
    }

    /* loaded from: classes11.dex */
    private class OneOffAlexaSupportedLocalesListenerv2 implements AlexaSupportedLocalesListenerv2 {
        private final SupportedLocaleCombinationsCallback callback;

        OneOffAlexaSupportedLocalesListenerv2(SupportedLocaleCombinationsCallback supportedLocaleCombinationsCallback) {
            this.callback = supportedLocaleCombinationsCallback;
        }

        @Override // com.amazon.alexa.api.compat.AlexaSupportedLocalesListenerv2
        public void onSupportedLocales(Set<Locale> set, Set<? extends List<Locale>> set2) {
            AlexaLocaleInteractor.this.cachedSupportedLocalesCombinations.clear();
            HashSet hashSet = new HashSet();
            Iterator<Locale> it2 = set.iterator();
            while (it2.hasNext()) {
                hashSet.add(Arrays.asList(it2.next()));
            }
            hashSet.addAll(set2);
            AlexaLocaleInteractor.this.cachedSupportedLocalesCombinations.addAll(hashSet);
            AlexaLocaleInteractor.this.localeAPI.deregisterSupportedLocalesv2(AlexaLocaleInteractor.this.alexaServicesConnection, this);
            this.callback.onLocaleCombinationParams(AlexaLocaleInteractor.this.buildLocaleCombinationParameters());
        }
    }

    public AlexaLocaleInteractor(AlexaServicesConnection alexaServicesConnection, LocaleAPI localeAPI, LocalePreference localePreference, IdentityService identityService, VoxMetricEventProcessingService voxMetricEventProcessingService, DlsFeatureEnabler dlsFeatureEnabler) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.localeAPI = localeAPI;
        this.localePreference = localePreference;
        this.identityService = identityService;
        this.voxMetricEventProcessingService = voxMetricEventProcessingService;
        this.dlsFeatureEnabler = dlsFeatureEnabler;
    }

    private String getMarketPlaceCountryCode() {
        UserIdentity user;
        IdentityService identityService = this.identityService;
        if (identityService == null || (user = identityService.getUser(TAG)) == null) {
            return "";
        }
        if (ALLOWED_COUNTRY_CODE_FOR_MATCHING_LOCALE.contains(user.getCountryOfResidence())) {
            return user.getCountryOfResidence();
        }
        return user.getEffectiveMarketplace().getCountryCode().toString();
    }

    private Locale getPreselectedLocale(Locale locale, ArrayList<Locale> arrayList) {
        return (arrayList == null || arrayList.size() == 0 || this.localePreference.wasUpdatedByUser()) ? locale : LocaleRecommendationPreselectionHelper.getPreselectedLocale(Locale.getDefault(), arrayList);
    }

    private LocaleGroup getPreselectedLocaleCombinations(LocaleGroup localeGroup, ArrayList<LocaleGroup> arrayList) {
        return (arrayList == null || arrayList.size() == 0 || this.localePreference.wasUpdatedByUser()) ? localeGroup : LocaleRecommendationPreselectionHelper.getPreselectedLocaleCombination(Locale.getDefault(), arrayList);
    }

    private ArrayList<Locale> getRecommendedLocales() {
        return TextUtils.isEmpty(getMarketPlaceCountryCode()) ? Lists.newArrayList(getPrimaryLocale()) : LocaleRecommendationPreselectionHelper.getRecommendedLocale(getMarketPlaceCountryCode(), this.cachedSupportedLocales);
    }

    private ArrayList<LocaleGroup> getRecommendedLocalesCombinations() {
        return TextUtils.isEmpty(getMarketPlaceCountryCode()) ? Lists.newArrayList(new LocaleGroup(getCurrentLocales())) : LocaleRecommendationPreselectionHelper.getRecommendedLocaleCombination(getMarketPlaceCountryCode(), this.cachedSupportedLocalesCombinations);
    }

    private ArrayList<LocaleGroup> getSupportedLocalesCombinations() {
        ArrayList<LocaleGroup> arrayList = new ArrayList<>();
        Iterator<List<Locale>> it2 = this.cachedSupportedLocalesCombinations.iterator();
        while (it2.hasNext()) {
            arrayList.add(new LocaleGroup(it2.next()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMetricEvent(List<Locale> list, int i) {
        StringBuilder sb = new StringBuilder(list.get(0).toLanguageTag());
        for (int i2 = 1; i2 < list.size(); i2++) {
            sb.append(":");
            sb.append(list.get(i2).toLanguageTag());
        }
        String sb2 = sb.toString();
        String str = i == 0 ? VoxMetricEventName.BY_SYSTEM_LOCALE : i == 1 ? VoxMetricEventName.BY_USER_FTUE : VoxMetricEventName.BY_USER_SETTINGS;
        if (this.dlsFeatureEnabler.isDLSEnabled()) {
            this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.DLS_VOX_LOCALE_UPDATED, sb2, str));
        } else {
            this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.VOX_LOCALE_UPDATED, sb2, str));
        }
        this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.VOX_LOCALE_UPDATED_SUCCESS));
    }

    private void updateLocales(final List<Locale> list, final LocaleUpdateCallback localeUpdateCallback, final int i) {
        GeneratedOutlineSupport1.outline149("updateLocales, localeUpdateEvent: ", i);
        if (list == null || list.isEmpty()) {
            return;
        }
        this.localeAPI.setLocales(this.alexaServicesConnection, list, new AlexaApiCallbacks() { // from class: com.amazon.alexa.voice.locale.AlexaLocaleInteractor.1
            @Override // com.amazon.alexa.api.AlexaApiCallbacks
            public void onFailure(ApiCallFailure apiCallFailure) {
                LocaleUpdateCallback localeUpdateCallback2 = localeUpdateCallback;
                if (localeUpdateCallback2 != null) {
                    localeUpdateCallback2.onLocaleUpdated(false, AlexaLocaleInteractor.this.getCurrentLocales());
                }
                AlexaLocaleInteractor.this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.VOX_LOCALE_UPDATED_FAILED));
            }

            @Override // com.amazon.alexa.api.AlexaApiCallbacks
            public void onSuccess() {
                boolean z = true;
                if (localeUpdateCallback != null) {
                    AlexaLocaleInteractor.this.cachedCurrentLocales = list;
                    localeUpdateCallback.onLocaleUpdated(true, list);
                }
                LocalePreference localePreference = AlexaLocaleInteractor.this.localePreference;
                if (i == 0) {
                    z = false;
                }
                localePreference.settingUpdatedByUser(z);
                AlexaLocaleInteractor.this.recordMetricEvent(list, i);
            }
        });
    }

    @VisibleForTesting
    LocaleCombinationParameters buildLocaleCombinationParameters() {
        LocaleGroup localeGroup = new LocaleGroup(getCurrentLocales());
        ArrayList<LocaleGroup> recommendedLocalesCombinations = getRecommendedLocalesCombinations();
        return new LocaleCombinationParameters.Builder().currentLocale(localeGroup).supportedLocales(getSupportedLocalesCombinations()).recommendedLocales(recommendedLocalesCombinations).preselectedLocale(getPreselectedLocaleCombinations(localeGroup, recommendedLocalesCombinations)).removeDuplicates().sort().build();
    }

    @VisibleForTesting
    LocaleParameters buildLocaleParameters() {
        Locale primaryLocale = getPrimaryLocale();
        ArrayList<Locale> recommendedLocales = getRecommendedLocales();
        return new LocaleParameters.Adjuster(new LocaleParameters(new LocaleParameters.Builder().currentLocale(primaryLocale).supportedLocales(this.cachedSupportedLocales).recommendedLocales(recommendedLocales).preselectedLocale(getPreselectedLocale(primaryLocale, recommendedLocales)).build())).removeDuplicates().sort().getResult();
    }

    @Override // com.amazon.alexa.voice.locale.LocaleInteractor
    public void fetchSupportedLocales(SupportedLocalesCallback supportedLocalesCallback) {
        this.localeAPI.registerSupportedLocales(this.alexaServicesConnection, new OneOffAlexaSupportedLocalesListener(supportedLocalesCallback));
    }

    @Override // com.amazon.alexa.voice.locale.LocaleInteractor
    public void fetchSupportedLocalesCombinations(SupportedLocaleCombinationsCallback supportedLocaleCombinationsCallback) {
        this.localeAPI.registerSupportedLocalesv2(this.alexaServicesConnection, new OneOffAlexaSupportedLocalesListenerv2(supportedLocaleCombinationsCallback));
    }

    @Override // com.amazon.alexa.voice.locale.LocaleInteractor
    public List<Locale> getCurrentLocales() {
        return this.cachedCurrentLocales;
    }

    @Override // com.amazon.alexa.voice.locale.LocaleInteractor
    public Locale getPrimaryLocale() {
        getCurrentLocales();
        List<Locale> list = this.cachedCurrentLocales;
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    @Override // com.amazon.alexa.voice.locale.LocaleInteractor
    public void initialize() {
        this.alexaServicesConnection.registerListener(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003a  */
    @Override // com.amazon.alexa.voice.locale.LocaleInteractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean needsLanguagePickerEducation() {
        /*
            r7 = this;
            com.amazon.alexa.voice.locale.LocalePreference r0 = r7.localePreference
            boolean r0 = r0.wasUpdatedByUser()
            r1 = 0
            if (r0 == 0) goto L11
            java.lang.String r0 = "AlexaLocaleInteractor"
            java.lang.String r2 = "User has chosen a locale already. Language picker education not needed"
            android.util.Log.w(r0, r2)
            return r1
        L11:
            java.util.List r0 = r7.getCurrentLocales()
            if (r0 == 0) goto L77
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L1e
            goto L77
        L1e:
            java.lang.String r2 = r7.getMarketPlaceCountryCode()
            com.amazon.alexa.protocols.marketplace.CountryCode r3 = com.amazon.alexa.protocols.marketplace.CountryCode.IN
            java.lang.String r3 = r3.toString()
            boolean r3 = r2.equals(r3)
            r4 = 1
            if (r3 == 0) goto L30
            return r4
        L30:
            java.util.Iterator r3 = r0.iterator()
        L34:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L53
            java.lang.Object r5 = r3.next()
            java.util.Locale r5 = (java.util.Locale) r5
            if (r5 == 0) goto L52
            java.lang.String r6 = r5.getCountry()
            if (r6 == 0) goto L52
            java.lang.String r5 = r5.getCountry()
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L34
        L52:
            return r1
        L53:
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.Iterator r0 = r0.iterator()
        L5c:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L70
            java.lang.Object r5 = r0.next()
            java.util.Locale r5 = (java.util.Locale) r5
            java.lang.String r5 = r5.getCountry()
            r3.add(r5)
            goto L5c
        L70:
            boolean r0 = r3.contains(r2)
            if (r0 != 0) goto L77
            return r4
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.locale.AlexaLocaleInteractor.needsLanguagePickerEducation():boolean");
    }

    @Override // com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener, com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        this.localeAPI.registerLocalesListener(this.alexaServicesConnection, this.localeCache);
    }

    @Override // com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener, com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        this.localeAPI.deregisterLocalesListener(this.alexaServicesConnection, this.localeCache);
    }

    @Override // com.amazon.alexa.voice.locale.LocaleInteractor
    public void release() {
        this.alexaServicesConnection.deregisterListener(this);
    }

    @Override // com.amazon.alexa.voice.locale.LocaleInteractor
    public void setSystemLocalesUpdatedTo(@NonNull List<Locale> list, int i, @Nullable LocaleUpdateCallback localeUpdateCallback) {
        GeneratedOutlineSupport1.outline149("Request to update locales : ", i);
        if (i == 0) {
            if (!this.localePreference.wasUpdatedByUser()) {
                updateLocales(list, localeUpdateCallback, i);
            } else {
                Log.w(TAG, "Not updating the locales setting,setting was previously updated by the user");
            }
        } else if (i == 1) {
            updateLocales(list, localeUpdateCallback, i);
        } else if (i != 2) {
            Log.w(TAG, "Unknown event");
        } else {
            updateLocales(list, localeUpdateCallback, i);
        }
    }
}
