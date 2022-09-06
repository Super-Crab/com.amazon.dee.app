package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AlexaUserInterfaceOptions {
    private final boolean enableHints;
    private final boolean enableTyping;
    private final String hintText;
    private final Theme theme;

    /* loaded from: classes6.dex */
    public static class Builder {
        private boolean enableHints;
        private boolean enableTyping;
        private String hintText;
        private Theme theme;

        private Builder() {
        }

        public AlexaUserInterfaceOptions build() {
            return new AlexaUserInterfaceOptions(this);
        }

        public Builder setHintText(String str) {
            this.enableHints = true;
            this.hintText = str;
            return this;
        }

        public Builder setHintsEnabled(boolean z) {
            this.enableHints = z;
            return this;
        }

        public Builder setTheme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public Builder setTypingEnabled(boolean z) {
            this.enableTyping = z;
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public enum Theme {
        DEFAULT,
        MINIMAL;

        public static Theme fromString(@Nullable String str) {
            try {
                return str == null ? DEFAULT : valueOf(str);
            } catch (Exception unused) {
                return DEFAULT;
            }
        }
    }

    /* loaded from: classes6.dex */
    enum UiOptionKeys implements Bundles.Key {
        HINT_TEXT,
        ENABLE_HINTS,
        THEME,
        ENABLE_TYPING
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaUserInterfaceOptions(@Nullable Bundle bundle) {
        this.hintText = Bundles.getOptionalString(bundle, UiOptionKeys.HINT_TEXT);
        this.enableHints = Bundles.getBoolean(bundle, UiOptionKeys.ENABLE_HINTS);
        this.theme = Theme.fromString(Bundles.getOptionalString(bundle, UiOptionKeys.THEME));
        this.enableTyping = Bundles.getBoolean(bundle, UiOptionKeys.ENABLE_TYPING);
    }

    private AlexaUserInterfaceOptions(Builder builder) {
        this.hintText = builder.hintText;
        this.enableHints = builder.enableHints;
        this.theme = builder.theme == null ? Theme.DEFAULT : builder.theme;
        this.enableTyping = builder.enableTyping;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AlexaUserInterfaceOptions.class != obj.getClass()) {
            return false;
        }
        AlexaUserInterfaceOptions alexaUserInterfaceOptions = (AlexaUserInterfaceOptions) obj;
        return this.enableHints == alexaUserInterfaceOptions.enableHints && Objects.equals(this.hintText, alexaUserInterfaceOptions.hintText) && this.theme == alexaUserInterfaceOptions.theme && this.enableTyping == alexaUserInterfaceOptions.enableTyping;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(UiOptionKeys.HINT_TEXT.name(), this.hintText);
        bundle.putBoolean(UiOptionKeys.ENABLE_HINTS.name(), this.enableHints);
        bundle.putString(UiOptionKeys.THEME.name(), this.theme.name());
        bundle.putBoolean(UiOptionKeys.ENABLE_TYPING.name(), this.enableTyping);
        return bundle;
    }

    @Nullable
    public String getHintText() {
        return this.hintText;
    }

    public boolean getHintsEnabled() {
        return this.enableHints;
    }

    public Theme getTheme() {
        return this.theme;
    }

    public boolean getTypingEnabled() {
        return this.enableTyping;
    }

    public int hashCode() {
        return Objects.hash(this.hintText, Boolean.valueOf(this.enableHints), this.theme, Boolean.valueOf(this.enableTyping));
    }
}
