package com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
/* loaded from: classes13.dex */
public class DSSServiceConfiguration {
    private static final String KEY_API_GATEWAY_BASE_URL = "DSSServiceConfiguration.ApiGatewayBaseUrl";
    private static final String KEY_BASE_URL = "DSSServiceConfiguration.BaseURL";
    private static final String KEY_DEBUG_ENABLED = "DSSServiceConfiguration.DebugEnabled";
    private static final String KEY_PREFIX = "DSSServiceConfiguration.";
    private static final String KEY_STAGE_INDEX = "DSSServiceConfiguration.StageIndex";
    private final HttpUrl mApiGatewayBaseUrl;
    private final HttpUrl mBaseUrl;
    private final DSSStage mDSSStage;
    private final boolean mDebugEnabled;

    /* loaded from: classes13.dex */
    public enum DSSStage {
        GAMMA,
        PROD;

        public static List<String> toStringValues() {
            ArrayList arrayList = new ArrayList();
            for (DSSStage dSSStage : values()) {
                arrayList.add(dSSStage.name());
            }
            return arrayList;
        }
    }

    private DSSServiceConfiguration(DSSStage dSSStage, String str, String str2, boolean z) {
        this(dSSStage, HttpUrl.parse(str), HttpUrl.parse(str2), z);
    }

    public static DSSServiceConfiguration fromBundle(Bundle bundle) {
        if (bundle != null) {
            if (!bundle.containsKey(KEY_BASE_URL)) {
                return null;
            }
            return new DSSServiceConfiguration(DSSStage.values()[bundle.getInt(KEY_STAGE_INDEX)], bundle.getString(KEY_BASE_URL), bundle.getString(KEY_API_GATEWAY_BASE_URL), bundle.getBoolean(KEY_DEBUG_ENABLED));
        }
        throw new IllegalArgumentException("bundle can not be null");
    }

    public static DSSServiceConfiguration fromSharedPreferences(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            if (!sharedPreferences.contains(KEY_BASE_URL)) {
                return null;
            }
            return new DSSServiceConfiguration(DSSStage.values()[sharedPreferences.getInt(KEY_STAGE_INDEX, 0)], sharedPreferences.getString(KEY_BASE_URL, null), sharedPreferences.getString(KEY_API_GATEWAY_BASE_URL, null), sharedPreferences.getBoolean(KEY_DEBUG_ENABLED, false));
        }
        throw new IllegalArgumentException("sharedPreferences can not be null");
    }

    public static DSSServiceConfiguration gamma() {
        return new DSSServiceConfiguration(DSSStage.GAMMA, "https://dss-na-gamma.amazon.com", "https://re8sfystqa.execute-api.us-east-1.amazonaws.com/gamma/", false);
    }

    public static DSSServiceConfiguration prod() {
        return new DSSServiceConfiguration(DSSStage.PROD, "https://dss-na.amazon.com", "https://wl.amazon-dss.com/", false);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DSSServiceConfiguration.class != obj.getClass()) {
            return false;
        }
        DSSServiceConfiguration dSSServiceConfiguration = (DSSServiceConfiguration) obj;
        return this.mDebugEnabled == dSSServiceConfiguration.mDebugEnabled && Objects.equal(this.mBaseUrl, dSSServiceConfiguration.mBaseUrl) && this.mDSSStage == dSSServiceConfiguration.mDSSStage;
    }

    public HttpUrl getApiGatewayBaseUrl() {
        return this.mApiGatewayBaseUrl;
    }

    public HttpUrl getBaseUrl() {
        return this.mBaseUrl;
    }

    public DSSStage getStage() {
        return this.mDSSStage;
    }

    public int hashCode() {
        return Objects.hashCode(this.mBaseUrl, this.mDSSStage, Boolean.valueOf(this.mDebugEnabled));
    }

    public boolean isDebugEnabled() {
        return this.mDebugEnabled;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mBaseUrl", this.mBaseUrl).add("mDSSStage", this.mDSSStage).add("mDebugEnabled", this.mDebugEnabled).toString();
    }

    public void writeToBundle(Bundle bundle) {
        if (bundle != null) {
            bundle.putString(KEY_BASE_URL, this.mBaseUrl.toString());
            bundle.putString(KEY_API_GATEWAY_BASE_URL, this.mApiGatewayBaseUrl.toString());
            bundle.putInt(KEY_STAGE_INDEX, this.mDSSStage.ordinal());
            bundle.putBoolean(KEY_DEBUG_ENABLED, this.mDebugEnabled);
            return;
        }
        throw new IllegalArgumentException("bundle can not be null");
    }

    public void writeToSharedPreferences(SharedPreferences.Editor editor) {
        if (editor != null) {
            editor.putString(KEY_BASE_URL, this.mBaseUrl.toString());
            editor.putString(KEY_API_GATEWAY_BASE_URL, this.mApiGatewayBaseUrl.toString());
            editor.putInt(KEY_STAGE_INDEX, this.mDSSStage.ordinal());
            editor.putBoolean(KEY_DEBUG_ENABLED, this.mDebugEnabled);
            return;
        }
        throw new IllegalArgumentException("editor can not be null");
    }

    @VisibleForTesting
    DSSServiceConfiguration(DSSStage dSSStage, HttpUrl httpUrl, HttpUrl httpUrl2, boolean z) {
        if (dSSStage != null) {
            if (httpUrl == null) {
                throw new IllegalArgumentException("httpUrl can not be null");
            }
            if (httpUrl2 != null) {
                this.mBaseUrl = httpUrl;
                this.mApiGatewayBaseUrl = httpUrl2;
                this.mDSSStage = dSSStage;
                this.mDebugEnabled = z;
                return;
            }
            throw new IllegalArgumentException("apiGatewayBaseUrl can not be null");
        }
        throw new IllegalArgumentException("dssStage can not be null");
    }

    public static DSSServiceConfiguration gamma(boolean z) {
        return new DSSServiceConfiguration(DSSStage.GAMMA, "https://dss-na-gamma.amazon.com", "https://re8sfystqa.execute-api.us-east-1.amazonaws.com/gamma/", z);
    }

    public static DSSServiceConfiguration prod(boolean z) {
        return new DSSServiceConfiguration(DSSStage.PROD, "https://dss-na.amazon.com", "https://wl.amazon-dss.com/", z);
    }
}
