package com.google.zxing.client.result;

import com.google.zxing.Result;
/* loaded from: classes3.dex */
public final class WifiResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse  reason: collision with other method in class */
    public WifiParsedResult mo10122parse(Result result) {
        String substring;
        String matchSinglePrefixedField;
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.startsWith("WIFI:") && (matchSinglePrefixedField = ResultParser.matchSinglePrefixedField("S:", (substring = massagedText.substring(5)), ';', false)) != null && !matchSinglePrefixedField.isEmpty()) {
            String matchSinglePrefixedField2 = ResultParser.matchSinglePrefixedField("P:", substring, ';', false);
            String matchSinglePrefixedField3 = ResultParser.matchSinglePrefixedField("T:", substring, ';', false);
            if (matchSinglePrefixedField3 == null) {
                matchSinglePrefixedField3 = "nopass";
            }
            return new WifiParsedResult(matchSinglePrefixedField3, matchSinglePrefixedField, matchSinglePrefixedField2, Boolean.parseBoolean(ResultParser.matchSinglePrefixedField("H:", substring, ';', false)), ResultParser.matchSinglePrefixedField("I:", substring, ';', false), ResultParser.matchSinglePrefixedField("A:", substring, ';', false), ResultParser.matchSinglePrefixedField("E:", substring, ';', false), ResultParser.matchSinglePrefixedField("H:", substring, ';', false));
        }
        return null;
    }
}
