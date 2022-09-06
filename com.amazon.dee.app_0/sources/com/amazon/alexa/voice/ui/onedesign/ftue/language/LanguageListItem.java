package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class LanguageListItem {
    private final Language language;
    private boolean selected;

    public LanguageListItem(@NonNull Language language, boolean z) {
        this.language = language;
        this.selected = z;
    }

    public Language getLanguage() {
        return this.language;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }
}
