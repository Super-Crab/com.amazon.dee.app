package com.google.zxing.oned.rss.expanded.decoders;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AI01AndOtherAIs extends AI01decoder {
    private static final int HEADER_SIZE = 4;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AI01AndOtherAIs(BitArray bitArray) {
        super(bitArray);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder
    public String parseInformation() throws NotFoundException, FormatException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(01)");
        int length = outline107.length();
        outline107.append(getGeneralDecoder().extractNumericValueFromBitArray(4, 4));
        encodeCompressedGtinWithoutAI(outline107, 8, length);
        return getGeneralDecoder().decodeAllCodes(outline107, 48);
    }
}
