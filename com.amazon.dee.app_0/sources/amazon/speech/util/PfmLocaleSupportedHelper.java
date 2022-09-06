package amazon.speech.util;

import amazon.speech.util.DebugUtil;
import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
/* loaded from: classes.dex */
public class PfmLocaleSupportedHelper {
    private static final String AOT_JP = "amazon.speech.sim.aotjp";
    private static final String METRIC_EVENT_DEFAULT_ALEXA_LOCALE_DE = "sim_alexa_locale_default_DE_De";
    private static final String METRIC_EVENT_DEFAULT_ALEXA_LOCALE_JP = "sim_alexa_locale_default_JP_Ja";
    private static final String METRIC_EVENT_DEFAULT_ALEXA_LOCALE_UK = "sim_alexa_locale_default_UK_En";
    private static final String METRIC_EVENT_DEFAULT_ALEXA_LOCALE_US = "sim_alexa_locale_default_US_En";
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.SIM, PfmLocaleSupportedHelper.class);
    private static PfmLocaleSupportedHelper sInstance;
    private HashSet<String> mSupportedLanguages = new HashSet<String>() { // from class: amazon.speech.util.PfmLocaleSupportedHelper.1
        {
            add(Locale.ENGLISH.getLanguage());
            add(Locale.GERMANY.getLanguage());
        }
    };
    private HashMap<String, Locale> mPfmLocaleMap = new HashMap<String, Locale>() { // from class: amazon.speech.util.PfmLocaleSupportedHelper.2
        {
            put(LocaleInfo.ObfuscatedMarketplaceId.US_MARKETPLACE_ID, Locale.US);
            put(LocaleInfo.ObfuscatedMarketplaceId.UK_MARKETPLACE_ID, Locale.UK);
            put(LocaleInfo.ObfuscatedMarketplaceId.DE_MARKETPLACE_ID, Locale.GERMANY);
        }
    };
    private HashMap<Locale, String> mMetricEvents = new HashMap<Locale, String>() { // from class: amazon.speech.util.PfmLocaleSupportedHelper.3
        {
            put(Locale.US, PfmLocaleSupportedHelper.METRIC_EVENT_DEFAULT_ALEXA_LOCALE_US);
            put(Locale.UK, PfmLocaleSupportedHelper.METRIC_EVENT_DEFAULT_ALEXA_LOCALE_UK);
            put(Locale.GERMANY, PfmLocaleSupportedHelper.METRIC_EVENT_DEFAULT_ALEXA_LOCALE_DE);
        }
    };

    private PfmLocaleSupportedHelper(Context context) {
        PackageManager packageManager;
        if (context == null || (packageManager = context.getPackageManager()) == null || !packageManager.hasSystemFeature(AOT_JP)) {
            return;
        }
        Log.d(TAG, "JP PFM is supported, adding JP to locales and PFMs");
        this.mSupportedLanguages.add(Locale.JAPAN.getLanguage());
        this.mPfmLocaleMap.put(LocaleInfo.ObfuscatedMarketplaceId.JP_MARKETPLACE_ID, Locale.JAPAN);
        this.mMetricEvents.put(Locale.JAPAN, METRIC_EVENT_DEFAULT_ALEXA_LOCALE_JP);
    }

    public static PfmLocaleSupportedHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PfmLocaleSupportedHelper(context);
        }
        return sInstance;
    }

    public HashMap<Locale, String> getLocaleMetricEventsMap() {
        return this.mMetricEvents;
    }

    public HashSet<String> getSupportedLanguages() {
        return this.mSupportedLanguages;
    }

    public HashSet<Locale> getSupportedLocales() {
        return (HashSet) this.mMetricEvents.keySet();
    }

    public HashMap<String, Locale> getSupportedPfmLocaleMap() {
        return this.mPfmLocaleMap;
    }

    public HashSet<String> getSupportedPfms() {
        return (HashSet) this.mPfmLocaleMap.keySet();
    }

    public boolean isSupportedLanguage(String str) {
        return this.mSupportedLanguages.contains(str);
    }

    public boolean isSupportedLocale(Locale locale) {
        return this.mMetricEvents.containsKey(locale);
    }

    public boolean isSupportedPfm(String str) {
        return this.mPfmLocaleMap.containsKey(str);
    }
}
