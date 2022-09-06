package com.google.zxing.client.result;

import com.google.zxing.Result;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private static String parseName(String str) {
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            return str.substring(indexOf + 1) + Chars.SPACE + str.substring(0, indexOf);
        }
        return str;
    }

    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse */
    public AddressBookParsedResult mo10122parse(Result result) {
        String[] matchDoCoMoPrefixedField;
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.startsWith("MECARD:") && (matchDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("N:", massagedText, true)) != null) {
            String parseName = parseName(matchDoCoMoPrefixedField[0]);
            String matchSingleDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("SOUND:", massagedText, true);
            String[] matchDoCoMoPrefixedField2 = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("TEL:", massagedText, true);
            String[] matchDoCoMoPrefixedField3 = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("EMAIL:", massagedText, true);
            String matchSingleDoCoMoPrefixedField2 = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("NOTE:", massagedText, false);
            String[] matchDoCoMoPrefixedField4 = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("ADR:", massagedText, true);
            String matchSingleDoCoMoPrefixedField3 = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("BDAY:", massagedText, true);
            return new AddressBookParsedResult(ResultParser.maybeWrap(parseName), null, matchSingleDoCoMoPrefixedField, matchDoCoMoPrefixedField2, null, matchDoCoMoPrefixedField3, null, null, matchSingleDoCoMoPrefixedField2, matchDoCoMoPrefixedField4, null, AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("ORG:", massagedText, true), !ResultParser.isStringOfDigits(matchSingleDoCoMoPrefixedField3, 8) ? null : matchSingleDoCoMoPrefixedField3, null, AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("URL:", massagedText, true), null);
        }
        return null;
    }
}