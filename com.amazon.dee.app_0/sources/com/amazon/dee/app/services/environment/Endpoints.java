package com.amazon.dee.app.services.environment;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public class Endpoints {
    protected static final String CURRENT_SCHEMA_VERSION = "4";
    private final String alexaApiUrl;
    private final String awsRegion;
    private final String retailDomain;
    private final String retailUrl;
    private final String skillsStoreUrl;
    private final String websiteApiUrl;
    private final String websiteUrl;

    public Endpoints(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        this.alexaApiUrl = str;
        this.websiteApiUrl = str2;
        this.websiteUrl = str3;
        this.retailDomain = str4;
        this.retailUrl = str5;
        this.awsRegion = str6;
        this.skillsStoreUrl = str7;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Endpoints)) {
            return false;
        }
        Endpoints endpoints = (Endpoints) obj;
        if (((((((this.alexaApiUrl == null && endpoints.alexaApiUrl == null) || ((str = this.alexaApiUrl) != null && str.equals(endpoints.alexaApiUrl))) && ((this.websiteApiUrl == null && endpoints.websiteApiUrl == null) || ((str6 = this.websiteApiUrl) != null && str6.equals(endpoints.websiteApiUrl)))) && ((this.websiteUrl == null && endpoints.websiteUrl == null) || ((str5 = this.websiteUrl) != null && str5.equals(endpoints.websiteUrl)))) && ((this.retailDomain == null && endpoints.retailDomain == null) || ((str4 = this.retailDomain) != null && str4.equals(endpoints.retailDomain)))) && ((this.retailUrl == null && endpoints.retailUrl == null) || ((str3 = this.retailUrl) != null && str3.equals(endpoints.retailUrl)))) && ((this.awsRegion == null && endpoints.awsRegion == null) || ((str2 = this.awsRegion) != null && str2.equals(endpoints.awsRegion)))) {
            if (this.skillsStoreUrl == null && endpoints.skillsStoreUrl == null) {
                return true;
            }
            String str7 = this.skillsStoreUrl;
            if (str7 != null && str7.equals(endpoints.skillsStoreUrl)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public String getAlexaApiUrl() {
        return this.alexaApiUrl;
    }

    @Nullable
    public String getAwsRegion() {
        return this.awsRegion;
    }

    @Nullable
    public String getRetailDomain() {
        return this.retailDomain;
    }

    @Nullable
    public String getRetailUrl() {
        return this.retailUrl;
    }

    @Nullable
    public String getSkillsStoreUrl() {
        return this.skillsStoreUrl;
    }

    @Nullable
    public String getWebsiteApiUrl() {
        return this.websiteApiUrl;
    }

    @Nullable
    public String getWebsiteUrl() {
        return this.websiteUrl;
    }

    public int hashCode() {
        String str = this.alexaApiUrl;
        int i = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.websiteApiUrl;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.websiteUrl;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.retailDomain;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.retailUrl;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.awsRegion;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.skillsStoreUrl;
        if (str7 != null) {
            i = str7.hashCode();
        }
        return hashCode6 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Endpoints{alexaApiUrl = '");
        outline107.append(this.alexaApiUrl);
        outline107.append("', websiteApiUrl = '");
        outline107.append(this.websiteApiUrl);
        outline107.append("', websiteUrl = '");
        outline107.append(this.websiteUrl);
        outline107.append("', retailDomain = '");
        outline107.append(this.retailDomain);
        outline107.append("', retailUrl = '");
        outline107.append(this.retailUrl);
        outline107.append("', awsRegion = '");
        outline107.append(this.awsRegion);
        outline107.append("', skillsStoreUrl = '");
        outline107.append(this.skillsStoreUrl);
        outline107.append("'");
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
