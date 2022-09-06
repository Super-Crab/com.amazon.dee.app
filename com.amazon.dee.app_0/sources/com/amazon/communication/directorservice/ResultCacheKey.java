package com.amazon.communication.directorservice;
/* loaded from: classes12.dex */
public class ResultCacheKey {
    private String marketplaceId;
    private String purpose;
    private String service;

    private ResultCacheKey(String str, String str2, String str3) {
        this.marketplaceId = str;
        this.service = str2;
        this.purpose = str3;
    }

    public static ResultCacheKey newKey(String str, String str2, String str3) {
        return new ResultCacheKey(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ResultCacheKey.class != obj.getClass()) {
            return false;
        }
        ResultCacheKey resultCacheKey = (ResultCacheKey) obj;
        String str = this.marketplaceId;
        if (str == null) {
            if (resultCacheKey.marketplaceId != null) {
                return false;
            }
        } else if (!str.equals(resultCacheKey.marketplaceId)) {
            return false;
        }
        String str2 = this.purpose;
        if (str2 == null) {
            if (resultCacheKey.purpose != null) {
                return false;
            }
        } else if (!str2.equals(resultCacheKey.purpose)) {
            return false;
        }
        String str3 = this.service;
        if (str3 == null) {
            if (resultCacheKey.service != null) {
                return false;
            }
        } else if (!str3.equals(resultCacheKey.service)) {
            return false;
        }
        return true;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public String getService() {
        return this.service;
    }

    public int hashCode() {
        String str = this.marketplaceId;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.purpose;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.service;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }
}
