package com.amazon.dee.app.ui.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.StringRes;
/* loaded from: classes12.dex */
public class MenuItem {
    private int badgeId;
    private Context context;
    private boolean hasDividerBelow;
    private final MenuIdentifier identifier;
    private boolean isVisible;
    private int textId;

    public MenuItem(Context context, MenuIdentifier menuIdentifier, @StringRes int i, boolean z, boolean z2) {
        this.hasDividerBelow = false;
        this.context = context;
        this.identifier = menuIdentifier;
        this.hasDividerBelow = z;
        this.isVisible = z2;
        this.textId = i;
    }

    public MenuItem copy() {
        MenuItem menuItem = new MenuItem(this.context, this.identifier, this.textId, this.hasDividerBelow, this.isVisible);
        menuItem.setBadgeId(this.badgeId);
        return menuItem;
    }

    public Drawable getBadgeIcon() {
        int i = this.badgeId;
        if (i > 0) {
            return this.context.getDrawable(i);
        }
        return null;
    }

    public MenuIdentifier getIdentifier() {
        return this.identifier;
    }

    public CharSequence getText() {
        int i = this.textId;
        if (i > 0) {
            return this.context.getText(i);
        }
        return null;
    }

    public boolean isEqual(Object obj) {
        boolean z;
        if (obj == null || !(obj instanceof MenuItem)) {
            return false;
        }
        MenuItem menuItem = (MenuItem) obj;
        if (getBadgeIcon() != null) {
            z = getBadgeIcon().equals(menuItem.getBadgeIcon());
        } else {
            z = menuItem.getBadgeIcon() == null;
        }
        return getIdentifier() == menuItem.getIdentifier() && getText().equals(menuItem.getText()) && isVisible() == menuItem.isVisible() && showDivider() == menuItem.showDivider() && z;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeBadgeId() {
        this.badgeId = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBadgeId(@StringRes int i) {
        this.badgeId = i;
    }

    public void setDivider(boolean z) {
        this.hasDividerBelow = z;
    }

    public void setText(@StringRes int i) {
        this.textId = i;
    }

    public void setVisible(boolean z) {
        this.isVisible = z;
    }

    public boolean showDivider() {
        return this.hasDividerBelow;
    }
}
