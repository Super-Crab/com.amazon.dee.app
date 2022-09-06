package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public class TextResponse {
    private static final String EMPTY_STRING = "";
    private final String description;
    private final TextResponseMetadata metadata;
    private final String title;
    private final String url;

    /* loaded from: classes6.dex */
    static class Adapter implements u<TextResponse> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public enum TextResponseKeys implements Bundles.Key {
            TITLE,
            DESCRIPTION,
            URL,
            METADATA
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public TextResponse mo844createFromBundle(Bundle bundle) {
            String string = bundle.getString(TextResponseKeys.TITLE.name());
            String string2 = bundle.getString(TextResponseKeys.DESCRIPTION.name(), "");
            return TextResponse.builder().setTitle(string).setDescription(string2).setUrl(bundle.getString(TextResponseKeys.URL.name(), "")).setMetadata((TextResponseMetadata) BundleTransformer.getDefaultInstance().getFromBundle(bundle.getBundle(TextResponseKeys.METADATA.name()), TextResponseMetadata.class)).build();
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(TextResponse textResponse) {
            Bundle bundle = new Bundle();
            bundle.putString(TextResponseKeys.TITLE.name(), textResponse.title);
            bundle.putString(TextResponseKeys.DESCRIPTION.name(), textResponse.description);
            bundle.putString(TextResponseKeys.URL.name(), textResponse.url);
            if (textResponse.metadata != null) {
                bundle.putBundle(TextResponseKeys.METADATA.name(), BundleTransformer.getDefaultInstance().toBundle(textResponse.metadata));
            }
            return bundle;
        }
    }

    /* loaded from: classes6.dex */
    public static class Builder {
        private String description;
        private TextResponseMetadata metadata;
        private String title;
        private String url;

        private Builder() {
        }

        public TextResponse build() {
            return new TextResponse(this);
        }

        public Builder setDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder setMetadata(TextResponseMetadata textResponseMetadata) {
            this.metadata = textResponseMetadata;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }
    }

    private TextResponse(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.url = builder.url;
        this.metadata = builder.metadata;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Nullable
    public TextResponseMetadata getMetadata() {
        return this.metadata;
    }

    public String getTitle() {
        return this.title;
    }

    @Nullable
    public String getUrl() {
        return this.url;
    }
}
