package com.amazon.alexa.voice.handsfree.settings.locale;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.devices.locales.CertifiedLocaleVoiceAppProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.locale.UVRLocale;
import com.amazon.alexa.handsfree.protocols.utils.ArtifactDownloadStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.settings.locale.LocalePreferencesManager;
import com.amazon.alexa.handsfree.settings.locale.VoiceAppLocalesProvider;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.CountryCode;
import com.amazon.alexa.marketplace.api.Region;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceProvider;
import com.amazon.alexa.voice.handsfree.settings.locale.languagedata.DataProvider;
import com.amazon.alexa.voice.handsfree.settings.locale.languagedata.LanguageData;
import com.amazon.alexa.voice.handsfree.settings.locale.languagedata.MultiLanguageData;
import com.amazon.alexa.voice.handsfree.settings.locale.languagedata.MultiLanguageDataProvider;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.ui.locale.LocaleUpdateCallback;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.Language;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerParameters;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerParameters;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageGroup;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes11.dex */
public class HandsFreeLocaleAuthority implements AlexaLocaleAuthority, UVRLocale {
    private static final String EMPTY_STRING = "";
    private static final String IN_COUNTRY_CODE = "in";
    private static final String LOCALE_UPDATE_METRIC_EVENT_SEPARATOR = ":";
    private final AlexaResponsesLocaleInteractor mAlexaResponsesLocaleInteractor;
    private final CertifiedLocaleVoiceAppProvider mCertifiedLocaleVoiceAppProvider;
    private final Context mContext;
    private final DataProvider mDataProvider;
    private Set<Locale> mEligibleLocales;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private boolean mHasShouldShowLanguageSelectorUpdated;
    private final IdentityService mIdentityService;
    private final LocalePreferencesManager mLocalePreferencesManager;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final MultiLanguageDataProvider mMultiLanguageDataProvider;
    private boolean mShouldShowLanguageSelector;
    private final VoiceAppLocalesProvider mVoiceAppLocalesProvider;
    private static final Set<CountryCode> EU_COUNTRY_SET = new HashSet(Arrays.asList(CountryCode.GB, CountryCode.DE, CountryCode.FR, CountryCode.IT, CountryCode.ES, CountryCode.NL));
    private static final EnumMap<Region, Set<CountryCode>> REGION_COUNTRY_MAP = new EnumMap<Region, Set<CountryCode>>(Region.class) { // from class: com.amazon.alexa.voice.handsfree.settings.locale.HandsFreeLocaleAuthority.1
        {
            put((AnonymousClass1) Region.EUROPE, (Region) new HashSet(Arrays.asList(CountryCode.GB, CountryCode.DE, CountryCode.FR, CountryCode.IT, CountryCode.ES, CountryCode.NL, CountryCode.IN)));
            put((AnonymousClass1) Region.NORTH_AMERICA_AND_WORLD, (Region) new HashSet(Arrays.asList(CountryCode.US, CountryCode.CA, CountryCode.MX, CountryCode.BR)));
            put((AnonymousClass1) Region.FAR_EAST, (Region) new HashSet(Arrays.asList(CountryCode.JP, CountryCode.AU)));
        }
    };
    @VisibleForTesting
    static final EnumMap<Region, List<Locale>> SPEAKER_ID_SUPPORTED_LOCALES_MAP = new EnumMap<Region, List<Locale>>(Region.class) { // from class: com.amazon.alexa.voice.handsfree.settings.locale.HandsFreeLocaleAuthority.2
        {
            put((AnonymousClass2) Region.NORTH_AMERICA_AND_WORLD, (Region) Arrays.asList(HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.en, CountryCode.US), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.en, CountryCode.CA), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.es, CountryCode.US), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.es, CountryCode.MX), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.pt, CountryCode.BR), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.fr, CountryCode.CA), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.en, CountryCode.IN), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.en, CountryCode.GB), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.es, CountryCode.ES), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.de, CountryCode.DE), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.en, CountryCode.AU), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.fr, CountryCode.FR), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.it, CountryCode.IT), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.ja, CountryCode.JP)));
            put((AnonymousClass2) Region.EUROPE, (Region) Arrays.asList(HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.en, CountryCode.GB), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.en, CountryCode.IN), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.es, CountryCode.ES), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.it, CountryCode.IT), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.de, CountryCode.DE), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.fr, CountryCode.FR), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.hi, CountryCode.IN), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.en, CountryCode.US)));
            put((AnonymousClass2) Region.FAR_EAST, (Region) Arrays.asList(HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.en, CountryCode.AU), HandsFreeLocaleAuthority.convertToLocale(LanguageData.LanguageCode.ja, CountryCode.JP)));
        }
    };
    private static final String TAG = HandsFreeLocaleAuthority.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public enum MetricType {
        LANGUAGE_SELECTOR_PRESELECTED_LANGUAGE("LanguageSelectorPreSelectedLanguageSelected"),
        LANGUAGE_SELECTOR_SUPPORTED_LANGUAGE("LanguageSelectorSupportedLanguageSelected"),
        LANGUAGE_SELECTOR_SHOWN_FIRST_TIME("LanguageSelectorShownFirstTime"),
        LANGUAGE_SELECTOR_SHOWN_NOT_CERTIFIED("LanguageSelectorShownNotCertified"),
        LANGUAGE_SELECTOR_NOT_SHOWN("LanguageSelectorNotShown"),
        DEFAULT_LOCALE_SET_ON_SIGN_IN("DefaultLocaleSetOnSignIn"),
        DLS_LANGUAGE_SELECTOR_PRESELECTED_LANGUAGE("DlsLanguageSelectorPreSelectedLanguageSelected"),
        DLS_LANGUAGE_SELECTOR_SUPPORTED_LANGUAGE("DlsLanguageSelectorSupportedLanguageSelected"),
        DLS_LANGUAGE_SELECTOR_SHOWN_FIRST_TIME("DlsLanguageSelectorShownFirstTime"),
        DLS_LANGUAGE_SELECTOR_SHOWN_NOT_CERTIFIED("DlsLanguageSelectorShownNotCertified"),
        DLS_LANGUAGE_SELECTOR_NOT_SHOWN("DlsLanguageSelectorNotShown");
        
        private final String mValue;

        MetricType(@NonNull String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandsFreeLocaleAuthority(@NonNull Context context) {
        this(context, new LocalePreferencesManager(context), new AlexaResponsesLocaleInteractor(context), ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(context, FalcoSettingsComponent.class)).voiceAppLocalesProvider(), DeviceTypeInformationProvider.getInstance(context).getDeviceInformationForCurrentDevice(context).getCertifiedLocaleVoiceAppProvider(), new IdentityServiceProvider().provideIdentityService(), MetricsBuilderProvider.getInstance(context), new DataProvider(context), new MultiLanguageDataProvider(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Locale convertToLocale(@NonNull LanguageData.LanguageCode languageCode, @NonNull CountryCode countryCode) {
        return new Locale(languageCode.toString(), countryCode.toString());
    }

    private CountryCode getCountryCode() {
        UserIdentity user;
        IdentityService identityService = this.mIdentityService;
        if (identityService == null || (user = identityService.getUser(TAG)) == null) {
            return null;
        }
        return user.getEffectiveMarketplace().getCountryCode();
    }

    @NonNull
    private static Language getLanguage(@Nullable Locale locale) {
        Language.Builder builder = new Language.Builder();
        if (locale == null) {
            builder.country("").displayCountry("").language("").displayLanguage("");
        } else {
            builder.country(locale.getCountry()).displayCountry(locale.getDisplayCountry(locale)).language(locale.getLanguage()).displayLanguage(locale.getDisplayLanguage(locale));
        }
        return builder.build();
    }

    private LanguageData getLanguageData() {
        Locale phoneLanguage = getPhoneLanguage();
        return this.mDataProvider.getLanguageSelectorData(getCountryCode(), phoneLanguage);
    }

    @NonNull
    private static LanguageGroup getLanguageGroup(@Nullable List<Locale> list) {
        return new LanguageGroup(getLanguages(list));
    }

    @NonNull
    private static ArrayList<LanguageGroup> getLanguageGroups(@Nullable Set<List<Locale>> set) {
        ArrayList<LanguageGroup> arrayList = new ArrayList<>();
        if (set != null) {
            for (List<Locale> list : set) {
                arrayList.add(getLanguageGroup(list));
            }
        }
        return arrayList;
    }

    private String getLanguageTags(@NonNull List<Locale> list) {
        StringBuilder sb = new StringBuilder(list.get(0).toLanguageTag());
        for (int i = 1; i < list.size(); i++) {
            sb.append(":");
            sb.append(list.get(i).toLanguageTag());
        }
        return sb.toString();
    }

    @NonNull
    private static ArrayList<Language> getLanguages(@Nullable List<Locale> list) {
        ArrayList<Language> arrayList = new ArrayList<>();
        if (list != null) {
            for (Locale locale : list) {
                arrayList.add(getLanguage(locale));
            }
        }
        return arrayList;
    }

    private MultiLanguageData getMultiLanguageData() {
        Locale phoneLanguage = getPhoneLanguage();
        return this.mMultiLanguageDataProvider.getLanguageSelectorData(getCountryCode(), phoneLanguage);
    }

    private Set<Locale> getOtherAvailableLanguages() {
        HashSet hashSet = new HashSet(getEligibleLocales());
        hashSet.removeAll(getRecommendedLocales());
        hashSet.remove(getPreSelectedLocale());
        return hashSet;
    }

    private Locale getPhoneLanguage() {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        int i = Build.VERSION.SDK_INT;
        return configuration.getLocales().get(0);
    }

    @Nullable
    private Locale getPreSelectedLocale() {
        Locale preferredLanguage = getLanguageData().getPreferredLanguage();
        if (getEligibleLocales().contains(preferredLanguage)) {
            return preferredLanguage;
        }
        return null;
    }

    private Set<Locale> getRecommendedLocales() {
        Set<Locale> supportedLanguages = getLanguageData().getSupportedLanguages();
        HashSet hashSet = new HashSet();
        for (Locale locale : supportedLanguages) {
            if (getEligibleLocales().contains(locale)) {
                hashSet.add(locale);
            }
        }
        return hashSet;
    }

    private void recordDlsFtueLocaleChange(@NonNull String str) {
        this.mMetricsBuilderProvider.newBuilder().withFtueDlsLanguageSelectorLocaleSelected(TAG, str).emit(this.mContext);
    }

    private void recordFtueLocaleChange(@NonNull Locale locale) {
        this.mMetricsBuilderProvider.newBuilder().withFtueLanguageSelectorLocaleSelected(TAG, locale.toLanguageTag()).emit(this.mContext);
    }

    private void recordPerformanceMetric(@NonNull String str) {
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, str).emit(this.mContext);
    }

    public void connect() {
        this.mAlexaResponsesLocaleInteractor.connect(false);
    }

    public boolean ensureInitialized() {
        return this.mAlexaResponsesLocaleInteractor.ensureInitialized() && (isSpeakerIDSupported() || this.mVoiceAppLocalesProvider.ensureInitialized());
    }

    @VisibleForTesting
    Set<Locale> getEligibleLocales() {
        List<Locale> arrayList;
        if (this.mEligibleLocales == null) {
            if (isSpeakerIDSupported()) {
                arrayList = getSpeakerIdLocalesBasedOnPFM();
                String str = TAG;
                Log.d(str, "List of supported locales by SpeakerID and AIS: " + arrayList);
            } else {
                arrayList = new ArrayList<>();
                for (String str2 : this.mVoiceAppLocalesProvider.getVoiceAppSupportedLocales()) {
                    arrayList.add(Locale.forLanguageTag(str2));
                }
            }
            List<Locale> certifiedLocales = this.mCertifiedLocaleVoiceAppProvider.getCertifiedLocales(this.mContext);
            this.mEligibleLocales = new HashSet();
            if (certifiedLocales.isEmpty()) {
                Log.d(TAG, "Empty Certified Locales List.");
                for (Locale locale : arrayList) {
                    this.mEligibleLocales.add(locale);
                }
            } else if (arrayList.isEmpty()) {
                Log.d(TAG, "Empty Supported Locales List.");
                for (Locale locale2 : certifiedLocales) {
                    this.mEligibleLocales.add(locale2);
                }
            } else {
                for (Locale locale3 : certifiedLocales) {
                    if (arrayList.contains(locale3)) {
                        this.mEligibleLocales.add(locale3);
                    }
                }
            }
        }
        Log.d(TAG, String.format("Eligible locales from Language Selector: %s", this.mEligibleLocales));
        return this.mEligibleLocales;
    }

    public LanguageCombinationPrimerParameters getLanguageSelectorCombinationParameters() {
        return new LanguageCombinationPrimerParameters.Builder().currentLanguage(getLanguageGroup(getPreselectedLocaleCombination())).availableLanguages(getLanguageGroups(getOtherAvailableLanguageCombinations())).recommendedLanguages(getLanguageGroups(getRecommendedLocaleCombinations())).build();
    }

    public LanguagePrimerParameters getLanguageSelectorParameters() {
        return new LanguagePrimerParameters.Builder().currentLanguage(getLanguage(getPreSelectedLocale())).availableLanguages(getLanguages(new ArrayList(getOtherAvailableLanguages()))).recommendedLanguages(getLanguages(new ArrayList(getRecommendedLocales()))).build();
    }

    @Override // com.amazon.alexa.voice.ui.locale.GetLocaleAuthority
    @NonNull
    public Locale getLocale() {
        Locale phoneLanguage = getPhoneLanguage();
        Log.d(TAG, String.format("Get locale %s", phoneLanguage));
        return phoneLanguage;
    }

    @Override // com.amazon.alexa.voice.ui.locale.GetLocaleAuthority
    @NonNull
    public List<Locale> getLocales() {
        Locale phoneLanguage = getPhoneLanguage();
        Log.d(TAG, String.format("Get locales %s", phoneLanguage));
        return Arrays.asList(phoneLanguage);
    }

    @VisibleForTesting
    Set<List<Locale>> getOtherAvailableLanguageCombinations() {
        HashSet hashSet = new HashSet();
        Set<Locale> eligibleLocales = getEligibleLocales();
        Iterator<Locale> it2 = eligibleLocales.iterator();
        while (it2.hasNext()) {
            hashSet.add(Arrays.asList(it2.next()));
        }
        for (List<Locale> list : MultiLanguageDataProvider.LOCALE_COMBINATIONS) {
            if (list != null && !list.isEmpty() && eligibleLocales.contains(list.get(0))) {
                hashSet.add(list);
            }
        }
        hashSet.remove(getPreselectedLocaleCombination());
        hashSet.removeAll(getRecommendedLocaleCombinations());
        String str = TAG;
        Log.i(str, "Other available language combinations are: " + hashSet);
        return hashSet;
    }

    @Nullable
    List<Locale> getPreselectedLocaleCombination() {
        Locale preferredLanguage = getMultiLanguageData().getPreferredLanguage();
        if (getEligibleLocales().contains(preferredLanguage)) {
            return Arrays.asList(preferredLanguage);
        }
        return null;
    }

    @VisibleForTesting
    Set<List<Locale>> getRecommendedLocaleCombinations() {
        Set<List<Locale>> supportedLanguages = getMultiLanguageData().getSupportedLanguages();
        HashSet hashSet = new HashSet();
        Set<Locale> eligibleLocales = getEligibleLocales();
        for (List<Locale> list : supportedLanguages) {
            if (list != null && !list.isEmpty() && eligibleLocales.contains(list.get(0))) {
                hashSet.add(list);
            }
        }
        String str = TAG;
        Log.i(str, "Recommended locale combinations are: " + hashSet);
        return hashSet;
    }

    @NonNull
    @VisibleForTesting
    Locale getSpeakerIdEligibleLocale() {
        Locale preferredLanguage = getLanguageData().getPreferredLanguage();
        return getSpeakerIdLocalesBasedOnPFM().contains(preferredLanguage) ? preferredLanguage : SPEAKER_ID_SUPPORTED_LOCALES_MAP.get(Region.FAR_EAST).contains(preferredLanguage) ? convertToLocale(LanguageData.LanguageCode.en, CountryCode.AU) : Locale.US;
    }

    @NonNull
    List<Locale> getSpeakerIdLocalesBasedOnPFM() {
        LanguageData languageData = getLanguageData();
        if (languageData.getCountryCode() != null) {
            for (Region region : REGION_COUNTRY_MAP.keySet()) {
                if (REGION_COUNTRY_MAP.get(region).contains(CountryCode.fromString(languageData.getCountryCode()))) {
                    return SPEAKER_ID_SUPPORTED_LOCALES_MAP.get(region);
                }
            }
        }
        return new ArrayList();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.locale.UVRLocale
    public boolean isEuPFM() {
        return EU_COUNTRY_SET.contains(CountryCode.fromString(getLanguageData().getCountryCode()));
    }

    @VisibleForTesting
    boolean isSpeakerIDSupported() {
        return Arrays.asList(EnrollmentType._1PSV_ONLY, EnrollmentType._1PSV_DUAL).contains(this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType());
    }

    public void setDefaultLocale() {
        Locale preSelectedLocale;
        if (this.mLocalePreferencesManager.wasUpdatedByUser()) {
            Log.d(TAG, "setLocale: Locale was already updated by user.");
            return;
        }
        if (isSpeakerIDSupported()) {
            preSelectedLocale = getSpeakerIdEligibleLocale();
            String str = TAG;
            Log.d(str, "setLocale: SpeakerID eligible locale is: " + preSelectedLocale);
        } else {
            preSelectedLocale = getPreSelectedLocale() == null ? Locale.US : getPreSelectedLocale();
            String str2 = TAG;
            Log.d(str2, "setLocale: preselected locale is: " + preSelectedLocale);
        }
        this.mAlexaResponsesLocaleInteractor.updateLocales(Arrays.asList(preSelectedLocale), ArtifactDownloadStateProvider.DownloadStarter.SIGN_IN);
        recordPerformanceMetric(MetricType.DEFAULT_LOCALE_SET_ON_SIGN_IN.getValue());
        String str3 = TAG;
        Log.d(str3, "setLocale: Updating locale to " + preSelectedLocale);
    }

    @Override // com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority
    public void setLocale(@NonNull Locale locale) {
        setLocales(Arrays.asList(locale));
    }

    @Override // com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority
    public void setLocale(@NonNull Locale locale, LocaleUpdateCallback localeUpdateCallback) {
    }

    @Override // com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority
    public void setLocales(@NonNull List<Locale> list) {
        Log.d(TAG, String.format("Setting locales %s", list));
        this.mAlexaResponsesLocaleInteractor.updateLocales(list, ArtifactDownloadStateProvider.DownloadStarter.LANGUAGE_SELECTOR);
        this.mLocalePreferencesManager.settingUpdatedByUser();
        if (this.mAlexaResponsesLocaleInteractor.isDLSWeblabEnabled()) {
            if (list.equals(getPreselectedLocaleCombination())) {
                recordPerformanceMetric(MetricType.DLS_LANGUAGE_SELECTOR_PRESELECTED_LANGUAGE.getValue());
            } else if (getRecommendedLocaleCombinations().contains(list)) {
                recordPerformanceMetric(MetricType.DLS_LANGUAGE_SELECTOR_SUPPORTED_LANGUAGE.getValue());
            }
            recordDlsFtueLocaleChange(getLanguageTags(list));
            return;
        }
        if (list.get(0).equals(getPreSelectedLocale())) {
            recordPerformanceMetric(MetricType.LANGUAGE_SELECTOR_PRESELECTED_LANGUAGE.getValue());
        } else if (getRecommendedLocales().contains(list.get(0))) {
            recordPerformanceMetric(MetricType.LANGUAGE_SELECTOR_SUPPORTED_LANGUAGE.getValue());
        }
        recordFtueLocaleChange(list.get(0));
    }

    @Override // com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority
    public void setLocales(@NonNull List<Locale> list, LocaleUpdateCallback localeUpdateCallback) {
    }

    public boolean shouldShowLanguageSelector() {
        String value;
        String value2;
        String value3;
        if (this.mHasShouldShowLanguageSelectorUpdated && this.mShouldShowLanguageSelector == (!this.mLocalePreferencesManager.wasLastUpdatedLocaleCertifiedAndSupported())) {
            return this.mShouldShowLanguageSelector;
        }
        this.mShouldShowLanguageSelector = false;
        if (getEligibleLocales().isEmpty()) {
            Log.d(TAG, String.format("Skipping Language Selector as there is no list of locales eligible. Preselected locale %s will be used.", isSpeakerIDSupported() ? getSpeakerIdEligibleLocale() : getPreSelectedLocale()));
        } else if (!this.mLocalePreferencesManager.wasUpdatedByUser()) {
            if (IN_COUNTRY_CODE.equalsIgnoreCase(getCountryCode() == null ? null : getCountryCode().toString())) {
                Log.d(TAG, "Showing LS for non-updated by user - Indian customers.");
                this.mShouldShowLanguageSelector = true;
            } else if (!this.mCertifiedLocaleVoiceAppProvider.getCertifiedLocales(this.mContext).contains(getPhoneLanguage())) {
                Log.d(TAG, "Showing LS for non-updated by user - non-certified phone lang.");
                this.mShouldShowLanguageSelector = true;
            } else if (!getLanguageData().getSupportedLanguages().contains(getPhoneLanguage())) {
                Log.d(TAG, "Showing LS for non-updated by user - phone lang not in PFM supported lang map.");
                this.mShouldShowLanguageSelector = true;
            }
            if (this.mShouldShowLanguageSelector) {
                if (this.mAlexaResponsesLocaleInteractor.isDLSWeblabEnabled()) {
                    value2 = MetricType.DLS_LANGUAGE_SELECTOR_SHOWN_FIRST_TIME.getValue();
                } else {
                    value2 = MetricType.LANGUAGE_SELECTOR_SHOWN_FIRST_TIME.getValue();
                }
                recordPerformanceMetric(value2);
            }
        } else if (!this.mLocalePreferencesManager.wasLastUpdatedLocaleCertifiedAndSupported()) {
            Log.d(TAG, "Showing LS for non-certified or supported locale - non-certified phone lang.");
            this.mShouldShowLanguageSelector = true;
            if (this.mAlexaResponsesLocaleInteractor.isDLSWeblabEnabled()) {
                value = MetricType.DLS_LANGUAGE_SELECTOR_SHOWN_NOT_CERTIFIED.getValue();
            } else {
                value = MetricType.LANGUAGE_SELECTOR_SHOWN_NOT_CERTIFIED.getValue();
            }
            recordPerformanceMetric(value);
        }
        if (!this.mShouldShowLanguageSelector) {
            if (this.mAlexaResponsesLocaleInteractor.isDLSWeblabEnabled()) {
                value3 = MetricType.DLS_LANGUAGE_SELECTOR_NOT_SHOWN.getValue();
            } else {
                value3 = MetricType.LANGUAGE_SELECTOR_NOT_SHOWN.getValue();
            }
            recordPerformanceMetric(value3);
        }
        this.mHasShouldShowLanguageSelectorUpdated = true;
        return this.mShouldShowLanguageSelector;
    }

    @VisibleForTesting
    HandsFreeLocaleAuthority(@NonNull Context context, @NonNull LocalePreferencesManager localePreferencesManager, @NonNull AlexaResponsesLocaleInteractor alexaResponsesLocaleInteractor, @NonNull VoiceAppLocalesProvider voiceAppLocalesProvider, @NonNull CertifiedLocaleVoiceAppProvider certifiedLocaleVoiceAppProvider, @Nullable IdentityService identityService, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull DataProvider dataProvider, @NonNull MultiLanguageDataProvider multiLanguageDataProvider, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mContext = context;
        this.mLocalePreferencesManager = localePreferencesManager;
        this.mAlexaResponsesLocaleInteractor = alexaResponsesLocaleInteractor;
        this.mVoiceAppLocalesProvider = voiceAppLocalesProvider;
        this.mCertifiedLocaleVoiceAppProvider = certifiedLocaleVoiceAppProvider;
        this.mIdentityService = identityService;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mDataProvider = dataProvider;
        this.mMultiLanguageDataProvider = multiLanguageDataProvider;
        this.mEnrollmentTypeResolverLazy = lazy;
    }
}
