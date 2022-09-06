package com.amazon.alexa.api;

import android.os.Bundle;
/* loaded from: classes6.dex */
public class AlexaPreferences {
    @Deprecated
    private static final String KEY_PREFER_DISPLAY_OVER_LOCKSCREEN_WITH_VERIFIED_VOICE = "displayOverLockscreenWithVerifiedVoice";
    private static final String KEY_PREFER_DISPLAY_OVER_LOCKSCREEN_WITH_VERIFIED_VOICE_VALUE = "displayOverLockscreenWithVerifiedVoiceValue";
    private final ShowOnLockscreenWithVerifiedVoiceValue preferDisplayOverLockscreenWithVerifiedVoice;

    /* loaded from: classes6.dex */
    public static class Builder {
        private ShowOnLockscreenWithVerifiedVoiceValue preferDisplayOverLockscreenWithVerifiedVoice;

        private Builder() {
        }

        public AlexaPreferences build() {
            return new AlexaPreferences(this);
        }

        public Builder setPreferDisplayOverLockscreenWithVerifiedVoice(ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue) {
            if (showOnLockscreenWithVerifiedVoiceValue == null) {
                showOnLockscreenWithVerifiedVoiceValue = ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_PERSONAL;
            }
            this.preferDisplayOverLockscreenWithVerifiedVoice = showOnLockscreenWithVerifiedVoiceValue;
            return this;
        }

        @Deprecated
        public Builder setPreferDisplayOverLockscreenWithVerifiedVoice(boolean z) {
            ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue;
            if (z) {
                ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue2 = this.preferDisplayOverLockscreenWithVerifiedVoice;
                if (showOnLockscreenWithVerifiedVoiceValue2 == null || showOnLockscreenWithVerifiedVoiceValue2.equals("null") || this.preferDisplayOverLockscreenWithVerifiedVoice.equals(ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_ALL)) {
                    showOnLockscreenWithVerifiedVoiceValue = ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_PERSONAL;
                }
                return this;
            }
            showOnLockscreenWithVerifiedVoiceValue = ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_ALL;
            this.preferDisplayOverLockscreenWithVerifiedVoice = showOnLockscreenWithVerifiedVoiceValue;
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public enum ShowOnLockscreenWithVerifiedVoiceValue {
        BLOCK_NONE,
        BLOCK_ALL,
        BLOCK_PERSONAL
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaPreferences(Bundle bundle) {
        String string = bundle.getString(KEY_PREFER_DISPLAY_OVER_LOCKSCREEN_WITH_VERIFIED_VOICE_VALUE);
        if (string == null) {
            string = (bundle.getBoolean(KEY_PREFER_DISPLAY_OVER_LOCKSCREEN_WITH_VERIFIED_VOICE, true) ? ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_PERSONAL : ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_ALL).name();
        }
        this.preferDisplayOverLockscreenWithVerifiedVoice = getEnumFromString(string);
    }

    private AlexaPreferences(Builder builder) {
        this.preferDisplayOverLockscreenWithVerifiedVoice = builder.preferDisplayOverLockscreenWithVerifiedVoice;
    }

    public static Builder builder() {
        return new Builder();
    }

    private ShowOnLockscreenWithVerifiedVoiceValue getEnumFromString(String str) {
        ShowOnLockscreenWithVerifiedVoiceValue[] values;
        ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue = ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_PERSONAL;
        for (ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue2 : ShowOnLockscreenWithVerifiedVoiceValue.values()) {
            if (showOnLockscreenWithVerifiedVoiceValue2.toString().equals(str)) {
                showOnLockscreenWithVerifiedVoiceValue = showOnLockscreenWithVerifiedVoiceValue2;
            }
        }
        return showOnLockscreenWithVerifiedVoiceValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PREFER_DISPLAY_OVER_LOCKSCREEN_WITH_VERIFIED_VOICE_VALUE, preferDisplayOverLockscreenWithVerifiedVoiceValue());
        bundle.putBoolean(KEY_PREFER_DISPLAY_OVER_LOCKSCREEN_WITH_VERIFIED_VOICE, preferDisplayOverLockscreenWithVerifiedVoice());
        return bundle;
    }

    public boolean preferDisplayOverLockscreenWithVerifiedVoice() {
        ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue = this.preferDisplayOverLockscreenWithVerifiedVoice;
        return showOnLockscreenWithVerifiedVoiceValue != null && !showOnLockscreenWithVerifiedVoiceValue.equals(ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_ALL);
    }

    public String preferDisplayOverLockscreenWithVerifiedVoiceValue() {
        ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue = this.preferDisplayOverLockscreenWithVerifiedVoice;
        if (showOnLockscreenWithVerifiedVoiceValue == null) {
            showOnLockscreenWithVerifiedVoiceValue = ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_PERSONAL;
        }
        return showOnLockscreenWithVerifiedVoiceValue.name();
    }
}
