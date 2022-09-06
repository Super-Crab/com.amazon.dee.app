package com.amazon.whisperjoin.common.sharedtypes.exceptions;

import java.util.Locale;
/* loaded from: classes13.dex */
public final class BarcodeFormatException extends Exception {
    public BarcodeFormatException(String str) {
        super(String.format(Locale.ENGLISH, "Barcode <%s> doesn't follow FFS 2D Bar Code specification.", str));
    }
}
