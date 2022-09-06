package com.google.zxing.client.result;

import com.google.zxing.Result;
/* loaded from: classes3.dex */
public final class URLTOResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse  reason: collision with other method in class */
    public URIParsedResult mo10122parse(Result result) {
        int indexOf;
        String massagedText = ResultParser.getMassagedText(result);
        String str = null;
        if ((massagedText.startsWith("urlto:") || massagedText.startsWith("URLTO:")) && (indexOf = massagedText.indexOf(58, 6)) >= 0) {
            if (indexOf > 6) {
                str = massagedText.substring(6, indexOf);
            }
            return new URIParsedResult(massagedText.substring(indexOf + 1), str);
        }
        return null;
    }
}
