package com.amazon.alexa.api;

import android.net.Uri;
import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.AlexaUriValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AlexaBaseURLs {
    static final URLsKeys KEY_AVS_URL = new URLsKeys(URLsKeysEnum.AVS);
    private Uri avsURL;

    /* loaded from: classes6.dex */
    public static class Builder {
        private Uri avsURL;

        private Builder() {
        }

        private void validateUri(Uri uri) {
            Uri uri2 = this.avsURL;
            if (uri2 == null) {
                return;
            }
            if (!AlexaUriValidator.hasValidScheme(uri2)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid scheme: ");
                outline107.append(this.avsURL.getScheme());
                outline107.append(". Must be: ");
                outline107.append("https");
                throw new IllegalArgumentException(outline107.toString());
            } else if (AlexaUriValidator.hasValidHost(this.avsURL)) {
            } else {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Invalid host: ");
                outline1072.append(this.avsURL.getHost());
                outline1072.append(". Must end with one of: ");
                outline1072.append(Arrays.toString(AlexaUriValidator.SUPPORTED_DOMAINS));
                throw new IllegalArgumentException(outline1072.toString());
            }
        }

        public AlexaBaseURLs build() {
            validateUri(this.avsURL);
            return new AlexaBaseURLs(this);
        }

        public Builder setAVSURL(Uri uri) {
            this.avsURL = uri;
            return this;
        }

        public Builder setAVSURL(String str) {
            this.avsURL = Uri.parse(str);
            return this;
        }
    }

    /* loaded from: classes6.dex */
    static class URLsBundleAdapter implements u<AlexaBaseURLs> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public AlexaBaseURLs mo844createFromBundle(Bundle bundle) {
            return new AlexaBaseURLs(bundle);
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(AlexaBaseURLs alexaBaseURLs) {
            return alexaBaseURLs.getBundle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class URLsKeys implements Bundles.Key {
        private final URLsKeysEnum enumVal;

        URLsKeys(URLsKeysEnum uRLsKeysEnum) {
            this.enumVal = uRLsKeysEnum;
        }

        @Override // com.amazon.alexa.api.Bundles.Key
        public String name() {
            return this.enumVal.getName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public enum URLsKeysEnum {
        AVS("avs");
        
        private String name;

        URLsKeysEnum(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaBaseURLs(@Nullable Bundle bundle) {
        this.avsURL = getUri(bundle, KEY_AVS_URL);
    }

    private AlexaBaseURLs(Builder builder) {
        this.avsURL = builder.avsURL;
    }

    public static Builder builder() {
        return new Builder();
    }

    static Builder getBuilder(@NonNull AlexaBaseURLs alexaBaseURLs) {
        return builder().setAVSURL(alexaBaseURLs.getAVSURL());
    }

    private Uri getUri(@Nullable Bundle bundle, Bundles.Key key) {
        if (bundle != null) {
            return (Uri) Bundles.getOptionalParcelable(bundle, key, Uri.class);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AlexaBaseURLs) {
            return Objects.equals(getAVSURL(), ((AlexaBaseURLs) obj).getAVSURL());
        }
        return false;
    }

    @Nullable
    public Uri getAVSURL() {
        return this.avsURL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_AVS_URL.name(), this.avsURL);
        return bundle;
    }

    public int hashCode() {
        return Objects.hash(getAVSURL());
    }
}
