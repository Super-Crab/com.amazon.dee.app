package com.adobe.xmp.impl.xpath;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.Utils;
import com.adobe.xmp.properties.XMPAliasInfo;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class XMPPathParser {
    private XMPPathParser() {
    }

    public static XMPPath expandXPath(String str, String str2) throws XMPException {
        if (str == null || str2 == null) {
            throw new XMPException("Parameter must not be null", 4);
        }
        XMPPath xMPPath = new XMPPath();
        PathPosition pathPosition = new PathPosition();
        pathPosition.path = str2;
        parseRootNode(str, pathPosition, xMPPath);
        while (pathPosition.stepEnd < str2.length()) {
            pathPosition.stepBegin = pathPosition.stepEnd;
            skipPathDelimiter(str2, pathPosition);
            int i = pathPosition.stepBegin;
            pathPosition.stepEnd = i;
            XMPPathSegment parseStructSegment = str2.charAt(i) != '[' ? parseStructSegment(pathPosition) : parseIndexSegment(pathPosition);
            if (parseStructSegment.getKind() == 1) {
                if (parseStructSegment.getName().charAt(0) == '@') {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(WebConstants.UriConstants.QUESTIONMARK_KEY);
                    outline107.append(parseStructSegment.getName().substring(1));
                    parseStructSegment.setName(outline107.toString());
                    if (!"?xml:lang".equals(parseStructSegment.getName())) {
                        throw new XMPException("Only xml:lang allowed with '@'", 102);
                    }
                }
                if (parseStructSegment.getName().charAt(0) == '?') {
                    pathPosition.nameStart++;
                    parseStructSegment.setKind(2);
                }
            } else {
                if (parseStructSegment.getKind() != 6) {
                    continue;
                } else {
                    if (parseStructSegment.getName().charAt(1) == '@') {
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("[?");
                        outline1072.append(parseStructSegment.getName().substring(2));
                        parseStructSegment.setName(outline1072.toString());
                        if (!parseStructSegment.getName().startsWith("[?xml:lang=")) {
                            throw new XMPException("Only xml:lang allowed with '@'", 102);
                        }
                    }
                    if (parseStructSegment.getName().charAt(1) == '?') {
                        pathPosition.nameStart++;
                        parseStructSegment.setKind(5);
                    }
                }
                xMPPath.add(parseStructSegment);
            }
            verifyQualName(pathPosition.path.substring(pathPosition.nameStart, pathPosition.nameEnd));
            xMPPath.add(parseStructSegment);
        }
        return xMPPath;
    }

    private static XMPPathSegment parseIndexSegment(PathPosition pathPosition) throws XMPException {
        XMPPathSegment xMPPathSegment;
        pathPosition.stepEnd++;
        if ('0' > pathPosition.path.charAt(pathPosition.stepEnd) || pathPosition.path.charAt(pathPosition.stepEnd) > '9') {
            while (pathPosition.stepEnd < pathPosition.path.length() && pathPosition.path.charAt(pathPosition.stepEnd) != ']' && pathPosition.path.charAt(pathPosition.stepEnd) != '=') {
                pathPosition.stepEnd++;
            }
            if (pathPosition.stepEnd >= pathPosition.path.length()) {
                throw new XMPException("Missing ']' or '=' for array index", 102);
            }
            if (pathPosition.path.charAt(pathPosition.stepEnd) != ']') {
                pathPosition.nameStart = pathPosition.stepBegin + 1;
                int i = pathPosition.stepEnd;
                pathPosition.nameEnd = i;
                pathPosition.stepEnd = i + 1;
                char charAt = pathPosition.path.charAt(pathPosition.stepEnd);
                if (charAt != '\'' && charAt != '\"') {
                    throw new XMPException("Invalid quote in array selector", 102);
                }
                while (true) {
                    pathPosition.stepEnd++;
                    if (pathPosition.stepEnd < pathPosition.path.length()) {
                        if (pathPosition.path.charAt(pathPosition.stepEnd) == charAt) {
                            if (pathPosition.stepEnd + 1 >= pathPosition.path.length() || pathPosition.path.charAt(pathPosition.stepEnd + 1) != charAt) {
                                break;
                            }
                            pathPosition.stepEnd++;
                        }
                    } else {
                        break;
                    }
                }
                if (pathPosition.stepEnd >= pathPosition.path.length()) {
                    throw new XMPException("No terminating quote for array selector", 102);
                }
                pathPosition.stepEnd++;
                xMPPathSegment = new XMPPathSegment(null, 6);
            } else if (!"[last()".equals(pathPosition.path.substring(pathPosition.stepBegin, pathPosition.stepEnd))) {
                throw new XMPException("Invalid non-numeric array index", 102);
            } else {
                xMPPathSegment = new XMPPathSegment(null, 4);
            }
        } else {
            while (pathPosition.stepEnd < pathPosition.path.length() && '0' <= pathPosition.path.charAt(pathPosition.stepEnd) && pathPosition.path.charAt(pathPosition.stepEnd) <= '9') {
                pathPosition.stepEnd++;
            }
            xMPPathSegment = new XMPPathSegment(null, 3);
        }
        if (pathPosition.stepEnd >= pathPosition.path.length() || pathPosition.path.charAt(pathPosition.stepEnd) != ']') {
            throw new XMPException("Missing ']' for array index", 102);
        }
        pathPosition.stepEnd++;
        xMPPathSegment.setName(pathPosition.path.substring(pathPosition.stepBegin, pathPosition.stepEnd));
        return xMPPathSegment;
    }

    private static void parseRootNode(String str, PathPosition pathPosition, XMPPath xMPPath) throws XMPException {
        XMPPathSegment xMPPathSegment;
        while (pathPosition.stepEnd < pathPosition.path.length() && "/[*".indexOf(pathPosition.path.charAt(pathPosition.stepEnd)) < 0) {
            pathPosition.stepEnd++;
        }
        int i = pathPosition.stepEnd;
        int i2 = pathPosition.stepBegin;
        if (i != i2) {
            String verifyXPathRoot = verifyXPathRoot(str, pathPosition.path.substring(i2, i));
            XMPAliasInfo findAlias = XMPMetaFactory.getSchemaRegistry().findAlias(verifyXPathRoot);
            if (findAlias == null) {
                xMPPath.add(new XMPPathSegment(str, Integer.MIN_VALUE));
                xMPPathSegment = new XMPPathSegment(verifyXPathRoot, 1);
            } else {
                xMPPath.add(new XMPPathSegment(findAlias.getNamespace(), Integer.MIN_VALUE));
                XMPPathSegment xMPPathSegment2 = new XMPPathSegment(verifyXPathRoot(findAlias.getNamespace(), findAlias.getPropName()), 1);
                xMPPathSegment2.setAlias(true);
                xMPPathSegment2.setAliasForm(findAlias.getAliasForm().getOptions());
                xMPPath.add(xMPPathSegment2);
                if (findAlias.getAliasForm().isArrayAltText()) {
                    xMPPathSegment = new XMPPathSegment("[?xml:lang='x-default']", 5);
                } else if (!findAlias.getAliasForm().isArray()) {
                    return;
                } else {
                    xMPPathSegment = new XMPPathSegment("[1]", 3);
                }
                xMPPathSegment.setAlias(true);
                xMPPathSegment.setAliasForm(findAlias.getAliasForm().getOptions());
            }
            xMPPath.add(xMPPathSegment);
            return;
        }
        throw new XMPException("Empty initial XMPPath step", 102);
    }

    private static XMPPathSegment parseStructSegment(PathPosition pathPosition) throws XMPException {
        pathPosition.nameStart = pathPosition.stepBegin;
        while (pathPosition.stepEnd < pathPosition.path.length() && "/[*".indexOf(pathPosition.path.charAt(pathPosition.stepEnd)) < 0) {
            pathPosition.stepEnd++;
        }
        int i = pathPosition.stepEnd;
        pathPosition.nameEnd = i;
        int i2 = pathPosition.stepBegin;
        if (i != i2) {
            return new XMPPathSegment(pathPosition.path.substring(i2, i), 1);
        }
        throw new XMPException("Empty XMPPath segment", 102);
    }

    private static void skipPathDelimiter(String str, PathPosition pathPosition) throws XMPException {
        if (str.charAt(pathPosition.stepBegin) == '/') {
            pathPosition.stepBegin++;
            if (pathPosition.stepBegin >= str.length()) {
                throw new XMPException("Empty XMPPath segment", 102);
            }
        }
        if (str.charAt(pathPosition.stepBegin) == '*') {
            pathPosition.stepBegin++;
            if (pathPosition.stepBegin < str.length() && str.charAt(pathPosition.stepBegin) == '[') {
                return;
            }
            throw new XMPException("Missing '[' after '*'", 102);
        }
    }

    private static void verifyQualName(String str) throws XMPException {
        int indexOf = str.indexOf(58);
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            if (Utils.isXMLNameNS(substring)) {
                if (XMPMetaFactory.getSchemaRegistry().getNamespaceURI(substring) == null) {
                    throw new XMPException("Unknown namespace prefix for qualified name", 102);
                }
                return;
            }
        }
        throw new XMPException("Ill-formed qualified name", 102);
    }

    private static void verifySimpleXMLName(String str) throws XMPException {
        if (Utils.isXMLName(str)) {
            return;
        }
        throw new XMPException("Bad XML name", 102);
    }

    private static String verifyXPathRoot(String str, String str2) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Schema namespace URI is required", 101);
        }
        if (str2.charAt(0) == '?' || str2.charAt(0) == '@') {
            throw new XMPException("Top level name must not be a qualifier", 102);
        }
        if (str2.indexOf(47) >= 0 || str2.indexOf(91) >= 0) {
            throw new XMPException("Top level name must be simple", 102);
        }
        String namespacePrefix = XMPMetaFactory.getSchemaRegistry().getNamespacePrefix(str);
        if (namespacePrefix == null) {
            throw new XMPException("Unregistered schema namespace URI", 101);
        }
        int indexOf = str2.indexOf(58);
        if (indexOf < 0) {
            verifySimpleXMLName(str2);
            return GeneratedOutlineSupport1.outline72(namespacePrefix, str2);
        }
        verifySimpleXMLName(str2.substring(0, indexOf));
        verifySimpleXMLName(str2.substring(indexOf));
        String substring = str2.substring(0, indexOf + 1);
        String namespacePrefix2 = XMPMetaFactory.getSchemaRegistry().getNamespacePrefix(str);
        if (namespacePrefix2 == null) {
            throw new XMPException("Unknown schema namespace prefix", 101);
        }
        if (!substring.equals(namespacePrefix2)) {
            throw new XMPException("Schema namespace URI and prefix mismatch", 101);
        }
        return str2;
    }
}
