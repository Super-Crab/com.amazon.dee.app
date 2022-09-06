package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
/* loaded from: classes3.dex */
public final class ISBNResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse */
    public ISBNParsedResult mo10122parse(Result result) {
        if (result.getBarcodeFormat() != BarcodeFormat.EAN_13) {
            return null;
        }
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.length() != 13) {
            return null;
        }
        if (!massagedText.startsWith("978") && !massagedText.startsWith("979")) {
            return null;
        }
        return new ISBNParsedResult(massagedText);
    }
}
