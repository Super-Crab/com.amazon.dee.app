package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class LanguageCombinationListItem {
    private final LanguageGroup languageGroup;
    private boolean selected;

    public LanguageCombinationListItem(@NonNull LanguageGroup languageGroup, boolean z) {
        this.languageGroup = languageGroup;
        this.selected = z;
    }

    public LanguageGroup getLanguageGroup() {
        return this.languageGroup;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }
}
