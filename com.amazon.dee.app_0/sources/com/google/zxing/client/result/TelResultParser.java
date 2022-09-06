package com.google.zxing.client.result;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.zxing.Result;
/* loaded from: classes3.dex */
public final class TelResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse  reason: collision with other method in class */
    public TelParsedResult mo10122parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.startsWith("tel:") || massagedText.startsWith("TEL:")) {
            String outline55 = massagedText.startsWith("TEL:") ? GeneratedOutlineSupport1.outline55(massagedText, 4, GeneratedOutlineSupport1.outline107("tel:")) : massagedText;
            int indexOf = massagedText.indexOf(63, 4);
            return new TelParsedResult(indexOf < 0 ? massagedText.substring(4) : massagedText.substring(4, indexOf), outline55, null);
        }
        return null;
    }
}
