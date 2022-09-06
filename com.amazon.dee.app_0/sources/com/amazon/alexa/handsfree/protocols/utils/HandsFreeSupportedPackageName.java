package com.amazon.alexa.handsfree.protocols.utils;

import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public enum HandsFreeSupportedPackageName {
    M_SHOP_BLENDERS_PRIDE_PACKAGE_NAME("in.amazon.mShop.android.shopping"),
    M_SHOP_PATRON_PACKAGE_NAME("com.amazon.mShop.android.shopping"),
    ALEXA_PACKAGE_NAME("com.amazon.dee.app");
    
    private final String mPackageName;

    HandsFreeSupportedPackageName(@NonNull String str) {
        this.mPackageName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mPackageName;
    }
}
