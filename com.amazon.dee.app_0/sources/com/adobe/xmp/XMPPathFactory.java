package com.adobe.xmp;

import com.adobe.xmp.impl.Utils;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public final class XMPPathFactory {
    private XMPPathFactory() {
    }

    private static void assertFieldNS(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty field namespace URI", 101);
        }
    }

    private static void assertFieldName(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty f name", 102);
        }
    }

    private static void assertQualNS(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty qualifier namespace URI", 101);
        }
    }

    private static void assertQualName(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty qualifier name", 102);
        }
    }

    public static String composeArrayItemPath(String str, int i) throws XMPException {
        if (i <= 0) {
            if (i != -1) {
                throw new XMPException("Array index must be larger than zero", 104);
            }
            return GeneratedOutlineSupport1.outline72(str, "[last()]");
        }
        return str + JsonReaderKt.BEGIN_LIST + i + JsonReaderKt.END_LIST;
    }

    public static String composeFieldSelector(String str, String str2, String str3, String str4) throws XMPException {
        XMPPath expandXPath = XMPPathParser.expandXPath(str2, str3);
        if (expandXPath.size() == 2) {
            StringBuilder outline108 = GeneratedOutlineSupport1.outline108(str, JsonReaderKt.BEGIN_LIST);
            outline108.append(expandXPath.getSegment(1).getName());
            outline108.append("=\"");
            outline108.append(str4);
            outline108.append("\"]");
            return outline108.toString();
        }
        throw new XMPException("The fieldName name must be simple", 102);
    }

    public static String composeLangSelector(String str, String str2) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "[?xml:lang=\"");
        outline113.append(Utils.normalizeLangValue(str2));
        outline113.append("\"]");
        return outline113.toString();
    }

    public static String composeQualifierPath(String str, String str2) throws XMPException {
        assertQualNS(str);
        assertQualName(str2);
        XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
        if (expandXPath.size() == 2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("/?");
            outline107.append(expandXPath.getSegment(1).getName());
            return outline107.toString();
        }
        throw new XMPException("The qualifier name must be simple", 102);
    }

    public static String composeStructFieldPath(String str, String str2) throws XMPException {
        assertFieldNS(str);
        assertFieldName(str2);
        XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
        if (expandXPath.size() == 2) {
            StringBuilder outline104 = GeneratedOutlineSupport1.outline104('/');
            outline104.append(expandXPath.getSegment(1).getName());
            return outline104.toString();
        }
        throw new XMPException("The field name must be simple", 102);
    }
}
