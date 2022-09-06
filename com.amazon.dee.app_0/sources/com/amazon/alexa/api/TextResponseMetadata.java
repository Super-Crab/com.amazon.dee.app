package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public class TextResponseMetadata {
    private static final String EMPTY_STRING = "";
    private final String namespace;
    private final String promptId;
    private final String token;
    private final String variant;

    /* loaded from: classes6.dex */
    static class Adapter implements u<TextResponseMetadata> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public enum TextResponseMetadataKeys implements Bundles.Key {
            TOKEN,
            PROMPT_ID,
            NAMESPACE,
            VARIANT
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public TextResponseMetadata mo844createFromBundle(Bundle bundle) {
            String string = bundle.getString(TextResponseMetadataKeys.TOKEN.name());
            String string2 = bundle.getString(TextResponseMetadataKeys.PROMPT_ID.name(), "");
            String string3 = bundle.getString(TextResponseMetadataKeys.NAMESPACE.name(), "");
            return TextResponseMetadata.builder().setToken(string).setPromptId(string2).setNamespace(string3).setVariant(bundle.getString(TextResponseMetadataKeys.VARIANT.name(), "")).build();
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(TextResponseMetadata textResponseMetadata) {
            Bundle bundle = new Bundle();
            bundle.putString(TextResponseMetadataKeys.TOKEN.name(), textResponseMetadata.token);
            bundle.putString(TextResponseMetadataKeys.PROMPT_ID.name(), textResponseMetadata.promptId);
            bundle.putString(TextResponseMetadataKeys.NAMESPACE.name(), textResponseMetadata.namespace);
            bundle.putString(TextResponseMetadataKeys.VARIANT.name(), textResponseMetadata.variant);
            return bundle;
        }
    }

    /* loaded from: classes6.dex */
    public static class Builder {
        private String namespace;
        private String promptId;
        private String token;
        private String variant;

        private Builder() {
        }

        public TextResponseMetadata build() {
            return new TextResponseMetadata(this);
        }

        public Builder setNamespace(String str) {
            this.namespace = str;
            return this;
        }

        public Builder setPromptId(String str) {
            this.promptId = str;
            return this;
        }

        public Builder setToken(String str) {
            this.token = str;
            return this;
        }

        public Builder setVariant(String str) {
            this.variant = str;
            return this;
        }
    }

    private TextResponseMetadata(Builder builder) {
        this.token = builder.token;
        this.promptId = builder.promptId;
        this.namespace = builder.namespace;
        this.variant = builder.variant;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Nullable
    public String getNamespace() {
        return this.namespace;
    }

    @Nullable
    public String getPromptId() {
        return this.promptId;
    }

    public String getToken() {
        return this.token;
    }

    @Nullable
    public String getVariant() {
        return this.variant;
    }
}
