package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
/* loaded from: classes6.dex */
public class AlexaDialogRequest {
    static final DialogRequestKeys KEY_INVOCATION_TYPE = new DialogRequestKeys(DialogRequestKeysEnum.INVOCATION_TYPE);
    static final DialogRequestKeys KEY_LAUNCH_TYPE = new DialogRequestKeys(DialogRequestKeysEnum.LAUNCH_TYPE);
    static final DialogRequestKeys KEY_WAKE_WORD = new DialogRequestKeys(DialogRequestKeysEnum.WAKE_WORD);
    private final String invocationType;
    private final LaunchType launchType;
    private final AlexaWakeWord wakeWord;

    /* loaded from: classes6.dex */
    public static class Builder {
        private String invocationType;
        private LaunchType launchType;
        private AlexaWakeWord wakeWord;

        private Builder() {
        }

        public AlexaDialogRequest build() {
            return new AlexaDialogRequest(this);
        }

        public Builder setInvocationType(@Nullable String str) {
            this.invocationType = str;
            return this;
        }

        public Builder setLaunchType(LaunchType launchType) {
            this.launchType = launchType;
            return this;
        }

        public Builder setWakeWord(AlexaWakeWord alexaWakeWord) {
            this.wakeWord = alexaWakeWord;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class DialogRequestKeys implements Bundles.Key {
        private final DialogRequestKeysEnum enumVal;

        DialogRequestKeys(DialogRequestKeysEnum dialogRequestKeysEnum) {
            this.enumVal = dialogRequestKeysEnum;
        }

        @Override // com.amazon.alexa.api.Bundles.Key
        public String name() {
            return this.enumVal.getName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public enum DialogRequestKeysEnum {
        INVOCATION_TYPE(AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE),
        LAUNCH_TYPE("launchType"),
        WAKE_WORD(AlexaMetricsConstants.EventConstants.WAKE_WORD);
        
        private String name;

        DialogRequestKeysEnum(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    public AlexaDialogRequest(@Nullable Bundle bundle) {
        this.invocationType = getString(bundle, KEY_INVOCATION_TYPE);
        this.launchType = LaunchType.fromString(getString(bundle, KEY_LAUNCH_TYPE));
        this.wakeWord = getAlexaWakeWord(bundle);
    }

    private AlexaDialogRequest(Builder builder) {
        this.invocationType = builder.invocationType;
        this.launchType = builder.launchType != null ? builder.launchType : LaunchType.UNKNOWN;
        this.wakeWord = builder.wakeWord;
    }

    public static Builder builder() {
        return new Builder();
    }

    private AlexaWakeWord getAlexaWakeWord(@Nullable Bundle bundle) {
        if (bundle != null) {
            return (AlexaWakeWord) Bundles.getOptionalParcelable(bundle, KEY_WAKE_WORD, AlexaWakeWord.class);
        }
        return null;
    }

    private String getString(@Nullable Bundle bundle, Bundles.Key key) {
        if (bundle != null) {
            return Bundles.getOptionalString(bundle, key);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_INVOCATION_TYPE.name(), this.invocationType);
        bundle.putString(KEY_LAUNCH_TYPE.name(), this.launchType.name());
        bundle.putParcelable(KEY_WAKE_WORD.name(), this.wakeWord);
        return bundle;
    }

    @Nullable
    public String getInvocationType() {
        return this.invocationType;
    }

    public LaunchType getLaunchType() {
        return this.launchType;
    }

    @Nullable
    public AlexaWakeWord getWakeWord() {
        return this.wakeWord;
    }
}
