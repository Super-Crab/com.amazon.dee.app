package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.ConfigurationKeySet;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMap;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class LocaleConfiguration {
    private static final Collection<String> KEY_SET;
    private String mCountryCode = null;
    private String mCountryOfResidence = null;
    private String mLanguageLocale = null;
    private String mMarketplace = null;
    private String mRealm = null;
    private String mRegion = null;

    static {
        ArrayList outline129 = GeneratedOutlineSupport1.outline129("LocaleConfiguration.CountryCode", "LocaleConfiguration.CountryOfResidence", "LocaleConfiguration.LanguageLocale", "LocaleConfiguration.Marketplace", "LocaleConfiguration.Realm");
        outline129.add("LocaleConfiguration.Region");
        KEY_SET = Collections.unmodifiableList(new ArrayList(outline129));
    }

    public static LocaleConfiguration fromSharedPreferences(SharedPreferences sharedPreferences) {
        LocaleConfiguration localeConfiguration = new LocaleConfiguration();
        localeConfiguration.setCountryCode(sharedPreferences.getString("LocaleConfiguration.CountryCode", null));
        localeConfiguration.setCountryOfResidence(sharedPreferences.getString("LocaleConfiguration.CountryOfResidence", null));
        localeConfiguration.setLanguageLocale(sharedPreferences.getString("LocaleConfiguration.LanguageLocale", null));
        localeConfiguration.setMarketplace(sharedPreferences.getString("LocaleConfiguration.Marketplace", null));
        localeConfiguration.setRealm(sharedPreferences.getString("LocaleConfiguration.Realm", null));
        localeConfiguration.setRegion(sharedPreferences.getString("LocaleConfiguration.Region", null));
        return localeConfiguration;
    }

    public static ConfigurationKeySet getConfigurationKeySet() {
        return new ConfigurationKeySet(KEY_SET);
    }

    public static LocaleConfiguration getFromBundle(Bundle bundle) {
        LocaleConfiguration localeConfiguration = new LocaleConfiguration();
        localeConfiguration.setCountryCode(bundle.getString("LocaleConfiguration.CountryCode"));
        localeConfiguration.setCountryOfResidence(bundle.getString("LocaleConfiguration.CountryOfResidence"));
        localeConfiguration.setLanguageLocale(bundle.getString("LocaleConfiguration.LanguageLocale"));
        localeConfiguration.setMarketplace(bundle.getString("LocaleConfiguration.Marketplace"));
        localeConfiguration.setRealm(bundle.getString("LocaleConfiguration.Realm"));
        localeConfiguration.setRegion(bundle.getString("LocaleConfiguration.Region"));
        return localeConfiguration;
    }

    public DataMap buildDataMap() {
        DataMap dataMap = new DataMap();
        String str = this.mCountryCode;
        if (str != null) {
            dataMap.putStringValue("LocaleConfiguration.CountryCode", str);
        }
        String str2 = this.mCountryOfResidence;
        if (str2 != null) {
            dataMap.putStringValue("LocaleConfiguration.CountryOfResidence", str2);
        }
        String str3 = this.mLanguageLocale;
        if (str3 != null) {
            dataMap.putStringValue("LocaleConfiguration.LanguageLocale", str3);
        }
        String str4 = this.mMarketplace;
        if (str4 != null) {
            dataMap.putStringValue("LocaleConfiguration.Marketplace", str4);
        }
        String str5 = this.mRealm;
        if (str5 != null) {
            dataMap.putStringValue("LocaleConfiguration.Realm", str5);
        }
        String str6 = this.mRegion;
        if (str6 != null) {
            dataMap.putStringValue("LocaleConfiguration.Region", str6);
        }
        return dataMap;
    }

    public Map<String, String> buildMap() {
        HashMap hashMap = new HashMap();
        String str = this.mCountryCode;
        if (str != null) {
            hashMap.put("LocaleConfiguration.CountryCode", str);
        }
        String str2 = this.mCountryOfResidence;
        if (str2 != null) {
            hashMap.put("LocaleConfiguration.CountryOfResidence", str2);
        }
        String str3 = this.mLanguageLocale;
        if (str3 != null) {
            hashMap.put("LocaleConfiguration.LanguageLocale", str3);
        }
        String str4 = this.mMarketplace;
        if (str4 != null) {
            hashMap.put("LocaleConfiguration.Marketplace", str4);
        }
        String str5 = this.mRealm;
        if (str5 != null) {
            hashMap.put("LocaleConfiguration.Realm", str5);
        }
        String str6 = this.mRegion;
        if (str6 != null) {
            hashMap.put("LocaleConfiguration.Region", str6);
        }
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocaleConfiguration.class != obj.getClass()) {
            return false;
        }
        LocaleConfiguration localeConfiguration = (LocaleConfiguration) obj;
        return Objects.equal(this.mCountryCode, localeConfiguration.mCountryCode) && Objects.equal(this.mCountryOfResidence, localeConfiguration.mCountryOfResidence) && Objects.equal(this.mLanguageLocale, localeConfiguration.mLanguageLocale) && Objects.equal(this.mMarketplace, localeConfiguration.mMarketplace) && Objects.equal(this.mRealm, localeConfiguration.mRealm) && Objects.equal(this.mRegion, localeConfiguration.mRegion);
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public String getCountryOfResidence() {
        return this.mCountryOfResidence;
    }

    public String getLanguageLocale() {
        return this.mLanguageLocale;
    }

    public String getMarketplace() {
        return this.mMarketplace;
    }

    public String getRealm() {
        return this.mRealm;
    }

    public String getRegion() {
        return this.mRegion;
    }

    public int hashCode() {
        return Objects.hashCode(this.mCountryCode, this.mCountryOfResidence, this.mLanguageLocale, this.mMarketplace, this.mRealm, this.mRegion);
    }

    public void setCountryCode(String str) {
        this.mCountryCode = str;
    }

    public void setCountryOfResidence(String str) {
        this.mCountryOfResidence = str;
    }

    public void setLanguageLocale(String str) {
        this.mLanguageLocale = str;
    }

    public void setMarketplace(String str) {
        this.mMarketplace = str;
    }

    public void setRealm(String str) {
        this.mRealm = str;
    }

    public void setRegion(String str) {
        this.mRegion = str;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mCountryCode", this.mCountryCode).add("mCountryOfResidence", this.mCountryOfResidence).add("mLanguageLocale", this.mLanguageLocale).add("mMarketplace", this.mMarketplace).add("mRealm", this.mRealm).add("mRegion", this.mRegion).toString();
    }

    public void writeToBundle(Bundle bundle) {
        bundle.putString("LocaleConfiguration.CountryCode", this.mCountryCode);
        bundle.putString("LocaleConfiguration.CountryOfResidence", this.mCountryOfResidence);
        bundle.putString("LocaleConfiguration.LanguageLocale", this.mLanguageLocale);
        bundle.putString("LocaleConfiguration.Marketplace", this.mMarketplace);
        bundle.putString("LocaleConfiguration.Realm", this.mRealm);
        bundle.putString("LocaleConfiguration.Region", this.mRegion);
    }

    public void writeToSharedPreferences(SharedPreferences.Editor editor) {
        editor.putString("LocaleConfiguration.CountryCode", this.mCountryCode);
        editor.putString("LocaleConfiguration.CountryOfResidence", this.mCountryOfResidence);
        editor.putString("LocaleConfiguration.LanguageLocale", this.mLanguageLocale);
        editor.putString("LocaleConfiguration.Marketplace", this.mMarketplace);
        editor.putString("LocaleConfiguration.Realm", this.mRealm);
        editor.putString("LocaleConfiguration.Region", this.mRegion);
    }
}
