package com.amazon.blueshift.bluefront.android.request;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import javax.annotation.Nullable;
/* loaded from: classes11.dex */
public class SpeechRequestConfig {
    private final String mLocale;
    private final String mProfile;
    private final String mRequestId;
    private final SpeechRequestParameters<?, ?> mRequestParameters;

    /* JADX INFO: Access modifiers changed from: protected */
    public SpeechRequestConfig(@Nullable String str, SpeechRequestParameters<?, ?> speechRequestParameters, String str2, @Nullable String str3) {
        Preconditions.checkNotNull(speechRequestParameters, "requestParameters cannot be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str2), "locale cannot be null or empty");
        this.mRequestId = str;
        this.mRequestParameters = speechRequestParameters;
        this.mLocale = str2;
        this.mProfile = str3;
    }

    public String getLocale() {
        return this.mLocale;
    }

    public String getProfile() {
        return this.mProfile;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public SpeechRequestParameters<?, ?> getRequestParameters() {
        return this.mRequestParameters;
    }
}
