package com.amazon.alexa.navigation.menu;

import android.view.View;
import androidx.annotation.LayoutRes;
/* loaded from: classes9.dex */
public abstract class MenuItem {
    static final int NO_NEW_COLOR = 0;
    private final int iconId;
    private final boolean lineUnderneath;
    private final int nameId;
    private final TestId testId;
    private int textColor = R.color.main_menu_primary_text;
    private int iconColor = 0;

    public MenuItem(int i, int i2, boolean z, TestId testId) {
        this.nameId = i;
        this.iconId = i2;
        this.lineUnderneath = z;
        this.testId = testId;
    }

    public int getIconColor() {
        return this.iconColor;
    }

    public int getIconId() {
        return this.iconId;
    }

    @LayoutRes
    public abstract int getMenuItemLayout();

    public int getNameId() {
        return this.nameId;
    }

    public TestId getTestId() {
        return this.testId;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public boolean isLineUnderneath() {
        return this.lineUnderneath;
    }

    public abstract void onClick(View view);

    public void setIconColor(int i) {
        this.iconColor = i;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public MenuItem withIconColor(int i) {
        setIconColor(i);
        return this;
    }

    public MenuItem withTextColor(int i) {
        setTextColor(i);
        return this;
    }
}
