package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes3.dex */
public final class SMSMMSResultParser extends ResultParser {
    private static void addNumberVia(Collection<String> collection, Collection<String> collection2, String str) {
        int indexOf = str.indexOf(59);
        String str2 = null;
        if (indexOf < 0) {
            collection.add(str);
            collection2.add(null);
            return;
        }
        collection.add(str.substring(0, indexOf));
        String substring = str.substring(indexOf + 1);
        if (substring.startsWith("via=")) {
            str2 = substring.substring(4);
        }
        collection2.add(str2);
    }

    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse  reason: collision with other method in class */
    public SMSParsedResult mo10122parse(Result result) {
        String str;
        String substring;
        String massagedText = ResultParser.getMassagedText(result);
        String str2 = null;
        if (massagedText.startsWith("sms:") || massagedText.startsWith("SMS:") || massagedText.startsWith("mms:") || massagedText.startsWith("MMS:")) {
            Map<String, String> parseNameValuePairs = ResultParser.parseNameValuePairs(massagedText);
            boolean z = false;
            if (parseNameValuePairs == null || parseNameValuePairs.isEmpty()) {
                str = null;
            } else {
                str2 = parseNameValuePairs.get("subject");
                str = parseNameValuePairs.get("body");
                z = true;
            }
            int indexOf = massagedText.indexOf(63, 4);
            if (indexOf >= 0 && z) {
                substring = massagedText.substring(4, indexOf);
            } else {
                substring = massagedText.substring(4);
            }
            int i = -1;
            ArrayList arrayList = new ArrayList(1);
            ArrayList arrayList2 = new ArrayList(1);
            while (true) {
                int i2 = i + 1;
                int indexOf2 = substring.indexOf(44, i2);
                if (indexOf2 > i) {
                    addNumberVia(arrayList, arrayList2, substring.substring(i2, indexOf2));
                    i = indexOf2;
                } else {
                    addNumberVia(arrayList, arrayList2, substring.substring(i2));
                    return new SMSParsedResult((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str2, str);
                }
            }
        } else {
            return null;
        }
    }
}
