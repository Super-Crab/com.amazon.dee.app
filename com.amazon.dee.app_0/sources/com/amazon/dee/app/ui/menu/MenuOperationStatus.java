package com.amazon.dee.app.ui.menu;
/* loaded from: classes12.dex */
public enum MenuOperationStatus {
    SUCCESS("Success"),
    MENU_ITEM_NOT_FOUND("Unknown menu item"),
    BADGE_ICON_NOT_FOUND("Unknown badge icon");
    
    private final String description;

    MenuOperationStatus(String str) {
        this.description = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.description;
    }
}
