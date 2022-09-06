package com.amazon.whisperjoin.common.sharedtypes.barcode;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.BarcodeFormatException;
/* loaded from: classes13.dex */
public interface BarcodeParser {
    BarcodeDetails parse(String str) throws BarcodeFormatException;
}
