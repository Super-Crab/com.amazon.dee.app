package com.amazon.alexa.voice.handsfree.settings.locale.languagedata;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.marketplace.api.CountryCode;
import com.amazon.alexa.voice.handsfree.R;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public class DataProvider {
    private static final String ANY_LANGUAGE = "any";
    private static final String COMMA_SEPARATOR = "\\s*,\\s*";
    private static final int PFM_POSITION = 0;
    private static final int PHONE_LANGUAGE_POSITION = 1;
    private static final int PREFERRED_LANGUAGE_POSITION = 2;
    private static final String RES_DATA_SEPARATOR = Pattern.quote(AccessoryMetricsConstants.DELIMITER);
    private static final int SUPPORTED_LANGUAGES_POSITION = 3;
    private static final String TAG = "DataProvider";
    private final Context mContext;

    public DataProvider(@NonNull Context context) {
        this.mContext = context;
    }

    private Set<Locale> getLocales(@NonNull String str) {
        String[] split = str.split(COMMA_SEPARATOR);
        HashSet hashSet = new HashSet();
        for (String str2 : split) {
            hashSet.add(Locale.forLanguageTag(str2));
        }
        return hashSet;
    }

    private boolean isAValidRule(@NonNull String[] strArr, @Nullable CountryCode countryCode, @NonNull Locale locale) {
        if (!strArr[0].equalsIgnoreCase(countryCode.toString())) {
            return false;
        }
        return strArr[1].equalsIgnoreCase(locale.getLanguage()) || strArr[1].equalsIgnoreCase("any");
    }

    public LanguageData getLanguageSelectorData(@Nullable CountryCode countryCode, @NonNull Locale locale) {
        if (countryCode != null) {
            for (String str : this.mContext.getResources().getStringArray(R.array.language_selector_rules)) {
                String[] split = str.split(RES_DATA_SEPARATOR);
                if (isAValidRule(split, countryCode, locale)) {
                    Log.d(TAG, String.format("Returning combination of %s-%s", countryCode, locale));
                    return new LanguageData(split[0], locale, Locale.forLanguageTag(split[2]), getLocales(split[3]));
                }
            }
        }
        String[] split2 = this.mContext.getResources().getString(R.string.language_selector_rule_any_pfm_and_phone_lang).split(RES_DATA_SEPARATOR);
        Log.d(TAG, String.format("PFM Not Supported: %s", Arrays.asList(split2)));
        return new LanguageData(null, locale, Locale.forLanguageTag(split2[2]), getLocales(split2[3]));
    }
}
