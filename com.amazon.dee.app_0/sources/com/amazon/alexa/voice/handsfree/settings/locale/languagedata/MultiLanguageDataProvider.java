package com.amazon.alexa.voice.handsfree.settings.locale.languagedata;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.marketplace.api.CountryCode;
import com.amazon.alexa.voice.handsfree.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public class MultiLanguageDataProvider {
    private static final String ANY_LANGUAGE = "any";
    private static final String COMMA_SEPARATOR = "\\s*,\\s*";
    private static final int PFM_POSITION = 0;
    private static final int PHONE_LANGUAGE_POSITION = 1;
    private static final int PREFERRED_LANGUAGE_POSITION = 2;
    private static final int SUPPORTED_LANGUAGES_POSITION = 3;
    private final Context mContext;
    private static final String TAG = DataProvider.class.getSimpleName();
    private static final String RES_DATA_SEPARATOR = Pattern.quote(AccessoryMetricsConstants.DELIMITER);
    public static final Set<List<Locale>> LOCALE_COMBINATIONS = new HashSet<List<Locale>>() { // from class: com.amazon.alexa.voice.handsfree.settings.locale.languagedata.MultiLanguageDataProvider.1
        {
            add(Arrays.asList(new Locale("en", "IN"), new Locale("hi", "IN")));
            add(Arrays.asList(new Locale("hi", "IN"), new Locale("en", "IN")));
            add(Arrays.asList(new Locale("en", "US"), new Locale("hi", "IN")));
            add(Arrays.asList(new Locale("hi", "IN"), new Locale("en", "US")));
            add(Arrays.asList(new Locale("en", "US"), new Locale("es", "US")));
            add(Arrays.asList(new Locale("es", "US"), new Locale("en", "US")));
            add(Arrays.asList(new Locale("ja", "JP"), new Locale("en", "US")));
            add(Arrays.asList(new Locale("en", "US"), new Locale("ja", "JP")));
            add(Arrays.asList(new Locale("de", "DE"), new Locale("en", "US")));
            add(Arrays.asList(new Locale("en", "US"), new Locale("de", "DE")));
            add(Arrays.asList(new Locale("es", "ES"), new Locale("en", "US")));
            add(Arrays.asList(new Locale("en", "US"), new Locale("es", "ES")));
            add(Arrays.asList(new Locale("it", "IT"), new Locale("en", "US")));
            add(Arrays.asList(new Locale("en", "US"), new Locale("it", "IT")));
            add(Arrays.asList(new Locale("fr", "FR"), new Locale("en", "US")));
            add(Arrays.asList(new Locale("en", "US"), new Locale("fr", "FR")));
            add(Arrays.asList(new Locale("en", "CA"), new Locale("fr", "CA")));
            add(Arrays.asList(new Locale("fr", "CA"), new Locale("en", "CA")));
            add(Arrays.asList(new Locale("de", "DE"), new Locale("en", "GB")));
            add(Arrays.asList(new Locale("en", "GB"), new Locale("de", "DE")));
            add(Arrays.asList(new Locale("es", "ES"), new Locale("en", "GB")));
            add(Arrays.asList(new Locale("en", "GB"), new Locale("es", "ES")));
            add(Arrays.asList(new Locale("fr", "FR"), new Locale("en", "GB")));
            add(Arrays.asList(new Locale("en", "GB"), new Locale("fr", "FR")));
            add(Arrays.asList(new Locale("it", "IT"), new Locale("en", "GB")));
            add(Arrays.asList(new Locale("en", "GB"), new Locale("it", "IT")));
            add(Arrays.asList(new Locale("en", "US"), new Locale("pt", "BR")));
            add(Arrays.asList(new Locale("pt", "BR"), new Locale("en", "US")));
            add(Arrays.asList(new Locale("en", "US"), new Locale("es", "MX")));
            add(Arrays.asList(new Locale("es", "MX"), new Locale("en", "US")));
        }
    };

    public MultiLanguageDataProvider(@NonNull Context context) {
        this.mContext = context;
    }

    private Set<List<Locale>> getLocales(@NonNull String str) {
        boolean z;
        String[] split = str.split(COMMA_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (String str2 : split) {
            Locale forLanguageTag = Locale.forLanguageTag(str2);
            arrayList.add(forLanguageTag);
            hashSet.add(Arrays.asList(forLanguageTag));
        }
        for (List<Locale> list : LOCALE_COMBINATIONS) {
            if (list != null && list.size() >= 2) {
                Iterator<Locale> it2 = list.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!arrayList.contains(it2.next())) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    hashSet.add(list);
                }
            }
        }
        return hashSet;
    }

    private boolean isAValidRule(@NonNull String[] strArr, @Nullable CountryCode countryCode, @NonNull Locale locale) {
        if (!strArr[0].equalsIgnoreCase(countryCode.toString())) {
            return false;
        }
        return strArr[1].equalsIgnoreCase(locale.getLanguage()) || strArr[1].equalsIgnoreCase("any");
    }

    public MultiLanguageData getLanguageSelectorData(@Nullable CountryCode countryCode, @NonNull Locale locale) {
        if (countryCode != null) {
            for (String str : this.mContext.getResources().getStringArray(R.array.language_selector_rules)) {
                String[] split = str.split(RES_DATA_SEPARATOR);
                if (isAValidRule(split, countryCode, locale)) {
                    Log.d(TAG, String.format("Returning combination of %s-%s", countryCode, locale));
                    return new MultiLanguageData(split[0], locale, Locale.forLanguageTag(split[2]), getLocales(split[3]));
                }
            }
        }
        String[] split2 = this.mContext.getResources().getString(R.string.language_selector_rule_any_pfm_and_phone_lang).split(RES_DATA_SEPARATOR);
        Log.d(TAG, String.format("PFM Not Supported: %s", Arrays.asList(split2)));
        return new MultiLanguageData(null, locale, Locale.forLanguageTag(split2[2]), getLocales(split2[3]));
    }
}
