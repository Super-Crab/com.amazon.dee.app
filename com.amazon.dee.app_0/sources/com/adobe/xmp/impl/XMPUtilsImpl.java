package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPAliasInfo;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.exif.makernotes.SonyType1MakernoteDirectory;
import java.util.Iterator;
import kotlin.text.Typography;
/* loaded from: classes.dex */
public class XMPUtilsImpl implements XMPConst {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String COMMAS = ",，､﹐﹑、،՝";
    private static final String CONTROLS = "\u2028\u2029";
    private static final String QUOTES = "\"«»〝〞〟―‹›";
    private static final String SEMICOLA = ";；﹔؛;";
    private static final String SPACES = " \u3000〿";
    private static final int UCK_COMMA = 2;
    private static final int UCK_CONTROL = 5;
    private static final int UCK_NORMAL = 0;
    private static final int UCK_QUOTE = 4;
    private static final int UCK_SEMICOLON = 3;
    private static final int UCK_SPACE = 1;

    private XMPUtilsImpl() {
    }

    public static void appendProperties(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z2, boolean z3) throws XMPException {
        ParameterAsserts.assertImplementation(xMPMeta);
        ParameterAsserts.assertImplementation(xMPMeta2);
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta2;
        Iterator iterateChildren = ((XMPMetaImpl) xMPMeta).getRoot().iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode = (XMPNode) iterateChildren.next();
            XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), xMPNode.getName(), false);
            boolean z4 = true;
            if (findSchemaNode == null) {
                findSchemaNode = new XMPNode(xMPNode.getName(), xMPNode.getValue(), new PropertyOptions().setSchemaNode(true));
                xMPMetaImpl.getRoot().addChild(findSchemaNode);
            } else {
                z4 = false;
            }
            Iterator iterateChildren2 = xMPNode.iterateChildren();
            while (iterateChildren2.hasNext()) {
                XMPNode xMPNode2 = (XMPNode) iterateChildren2.next();
                if (z || !Utils.isInternalProperty(xMPNode.getName(), xMPNode2.getName())) {
                    appendSubtree(xMPMetaImpl, xMPNode2, findSchemaNode, z2, z3);
                }
            }
            if (!findSchemaNode.hasChildren() && (z4 || z3)) {
                xMPMetaImpl.getRoot().removeChild(findSchemaNode);
            }
        }
    }

    private static void appendSubtree(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, XMPNode xMPNode2, boolean z, boolean z2) throws XMPException {
        XMPNode findChildNode = XMPNodeUtils.findChildNode(xMPNode2, xMPNode.getName(), false);
        boolean z3 = z2 && (!xMPNode.getOptions().isSimple() ? !xMPNode.hasChildren() : xMPNode.getValue() == null || xMPNode.getValue().length() == 0);
        if (z2 && z3) {
            if (findChildNode == null) {
                return;
            }
            xMPNode2.removeChild(findChildNode);
            return;
        }
        if (findChildNode != null) {
            if (!z) {
                PropertyOptions options = xMPNode.getOptions();
                if (options != findChildNode.getOptions()) {
                    return;
                }
                if (options.isStruct()) {
                    Iterator iterateChildren = xMPNode.iterateChildren();
                    while (iterateChildren.hasNext()) {
                        appendSubtree(xMPMetaImpl, (XMPNode) iterateChildren.next(), findChildNode, z, z2);
                        if (z2 && !findChildNode.hasChildren()) {
                            xMPNode2.removeChild(findChildNode);
                        }
                    }
                    return;
                } else if (!options.isArrayAltText()) {
                    if (!options.isArray()) {
                        return;
                    }
                    Iterator iterateChildren2 = xMPNode.iterateChildren();
                    while (iterateChildren2.hasNext()) {
                        XMPNode xMPNode3 = (XMPNode) iterateChildren2.next();
                        Iterator iterateChildren3 = findChildNode.iterateChildren();
                        boolean z4 = false;
                        while (iterateChildren3.hasNext()) {
                            if (itemValuesMatch(xMPNode3, (XMPNode) iterateChildren3.next())) {
                                z4 = true;
                            }
                        }
                        if (!z4) {
                            XMPNode xMPNode4 = (XMPNode) xMPNode3.clone();
                            xMPNode2.addChild(xMPNode4);
                            findChildNode = xMPNode4;
                        }
                    }
                    return;
                } else {
                    Iterator iterateChildren4 = xMPNode.iterateChildren();
                    while (iterateChildren4.hasNext()) {
                        XMPNode xMPNode5 = (XMPNode) iterateChildren4.next();
                        if (xMPNode5.hasQualifier() && XMPConst.XML_LANG.equals(xMPNode5.getQualifier(1).getName())) {
                            int lookupLanguageItem = XMPNodeUtils.lookupLanguageItem(findChildNode, xMPNode5.getQualifier(1).getValue());
                            if (!z2 || !(xMPNode5.getValue() == null || xMPNode5.getValue().length() == 0)) {
                                if (lookupLanguageItem == -1) {
                                    if (!XMPConst.X_DEFAULT.equals(xMPNode5.getQualifier(1).getValue()) || !findChildNode.hasChildren()) {
                                        xMPNode5.cloneSubtree(findChildNode);
                                    } else {
                                        XMPNode xMPNode6 = new XMPNode(xMPNode5.getName(), xMPNode5.getValue(), xMPNode5.getOptions());
                                        xMPNode5.cloneSubtree(xMPNode6);
                                        findChildNode.addChild(1, xMPNode6);
                                    }
                                }
                            } else if (lookupLanguageItem != -1) {
                                findChildNode.removeChild(lookupLanguageItem);
                                if (!findChildNode.hasChildren()) {
                                    xMPNode2.removeChild(findChildNode);
                                }
                            }
                        }
                    }
                    return;
                }
            }
            xMPMetaImpl.setNode(findChildNode, xMPNode.getValue(), xMPNode.getOptions(), true);
            xMPNode2.removeChild(findChildNode);
        }
        xMPNode2.addChild((XMPNode) xMPNode.clone());
    }

    private static String applyQuotes(String str, char c, char c2, boolean z) {
        if (str == null) {
            str = "";
        }
        int i = 0;
        boolean z2 = false;
        while (i < str.length()) {
            int classifyCharacter = classifyCharacter(str.charAt(i));
            if (i != 0 || classifyCharacter != 4) {
                if (classifyCharacter != 1) {
                    if (classifyCharacter == 3 || classifyCharacter == 5 || (classifyCharacter == 2 && !z)) {
                        break;
                    }
                    z2 = false;
                    i++;
                } else if (z2) {
                    break;
                } else {
                    z2 = true;
                    i++;
                }
            } else {
                break;
            }
        }
        if (i < str.length()) {
            StringBuffer stringBuffer = new StringBuffer(str.length() + 2);
            int i2 = 0;
            while (i2 <= i && classifyCharacter(str.charAt(i)) != 4) {
                i2++;
            }
            stringBuffer.append(c);
            stringBuffer.append(str.substring(0, i2));
            while (i2 < str.length()) {
                stringBuffer.append(str.charAt(i2));
                if (classifyCharacter(str.charAt(i2)) == 4 && isSurroundingQuote(str.charAt(i2), c, c2)) {
                    stringBuffer.append(str.charAt(i2));
                }
                i2++;
            }
            stringBuffer.append(c2);
            return stringBuffer.toString();
        }
        return str;
    }

    public static String catenateArrayItems(XMPMeta xMPMeta, String str, String str2, String str3, String str4, boolean z) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        ParameterAsserts.assertImplementation(xMPMeta);
        if (str3 == null || str3.length() == 0) {
            str3 = "; ";
        }
        if (str4 == null || str4.length() == 0) {
            str4 = EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED;
        }
        XMPNode findNode = XMPNodeUtils.findNode(((XMPMetaImpl) xMPMeta).getRoot(), XMPPathParser.expandXPath(str, str2), false, null);
        if (findNode == null) {
            return "";
        }
        if (!findNode.getOptions().isArray() || findNode.getOptions().isArrayAlternate()) {
            throw new XMPException("Named property must be non-alternate array", 4);
        }
        checkSeparator(str3);
        char charAt = str4.charAt(0);
        char checkQuotes = checkQuotes(str4, charAt);
        StringBuffer stringBuffer = new StringBuffer();
        Iterator iterateChildren = findNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode = (XMPNode) iterateChildren.next();
            if (xMPNode.getOptions().isCompositeProperty()) {
                throw new XMPException("Array items must be simple", 4);
            }
            stringBuffer.append(applyQuotes(xMPNode.getValue(), charAt, checkQuotes, z));
            if (iterateChildren.hasNext()) {
                stringBuffer.append(str3);
            }
        }
        return stringBuffer.toString();
    }

    private static char checkQuotes(String str, char c) throws XMPException {
        char charAt;
        if (classifyCharacter(c) == 4) {
            if (str.length() == 1) {
                charAt = c;
            } else {
                charAt = str.charAt(1);
                if (classifyCharacter(charAt) != 4) {
                    throw new XMPException("Invalid quoting character", 4);
                }
            }
            if (charAt != getClosingQuote(c)) {
                throw new XMPException("Mismatched quote pair", 4);
            }
            return charAt;
        }
        throw new XMPException("Invalid quoting character", 4);
    }

    private static void checkSeparator(String str) throws XMPException {
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            int classifyCharacter = classifyCharacter(str.charAt(i));
            if (classifyCharacter == 3) {
                if (z) {
                    throw new XMPException("Separator can have only one semicolon", 4);
                }
                z = true;
            } else if (classifyCharacter != 1) {
                throw new XMPException("Separator can have only spaces and one semicolon", 4);
            }
        }
        if (z) {
            return;
        }
        throw new XMPException("Separator must have one semicolon", 4);
    }

    private static int classifyCharacter(char c) {
        if (SPACES.indexOf(c) < 0) {
            if (8192 <= c && c <= 8203) {
                return 1;
            }
            if (COMMAS.indexOf(c) >= 0) {
                return 2;
            }
            if (SEMICOLA.indexOf(c) >= 0) {
                return 3;
            }
            if (QUOTES.indexOf(c) >= 0) {
                return 4;
            }
            if (12296 <= c && c <= 12303) {
                return 4;
            }
            if (8216 <= c && c <= 8223) {
                return 4;
            }
            return (c < ' ' || CONTROLS.indexOf(c) >= 0) ? 5 : 0;
        }
        return 1;
    }

    private static char getClosingQuote(char c) {
        switch (c) {
            case '\"':
                return '\"';
            case 171:
                return Typography.rightGuillemete;
            case 187:
                return Typography.leftGuillemete;
            case 8213:
                return (char) 8213;
            case 8216:
                return Typography.rightSingleQuote;
            case 8218:
                return (char) 8219;
            case 8220:
                return (char) 8221;
            case SonyType1MakernoteDirectory.TAG_AF_POINT_SELECTED /* 8222 */:
                return (char) 8223;
            case 8249:
                return (char) 8250;
            case 8250:
                return (char) 8249;
            case 12296:
                return (char) 12297;
            case 12298:
                return (char) 12299;
            case 12300:
                return (char) 12301;
            case 12302:
                return (char) 12303;
            case 12317:
                return (char) 12319;
            default:
                return (char) 0;
        }
    }

    private static boolean isClosingingQuote(char c, char c2, char c3) {
        return c == c3 || (c2 == 12317 && c == 12318) || c == 12319;
    }

    private static boolean isSurroundingQuote(char c, char c2, char c3) {
        return c == c2 || isClosingingQuote(c, c2, c3);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean itemValuesMatch(com.adobe.xmp.impl.XMPNode r5, com.adobe.xmp.impl.XMPNode r6) throws com.adobe.xmp.XMPException {
        /*
            com.adobe.xmp.options.PropertyOptions r0 = r5.getOptions()
            com.adobe.xmp.options.PropertyOptions r1 = r6.getOptions()
            boolean r1 = r0.equals(r1)
            r2 = 0
            if (r1 == 0) goto L10
            return r2
        L10:
            int r1 = r0.getOptions()
            r3 = 1
            if (r1 != 0) goto L5a
            java.lang.String r0 = r5.getValue()
            java.lang.String r1 = r6.getValue()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L26
            return r2
        L26:
            com.adobe.xmp.options.PropertyOptions r0 = r5.getOptions()
            boolean r0 = r0.getHasLanguage()
            com.adobe.xmp.options.PropertyOptions r1 = r6.getOptions()
            boolean r1 = r1.getHasLanguage()
            if (r0 == r1) goto L39
            return r2
        L39:
            com.adobe.xmp.options.PropertyOptions r0 = r5.getOptions()
            boolean r0 = r0.getHasLanguage()
            if (r0 == 0) goto Lb8
            com.adobe.xmp.impl.XMPNode r5 = r5.getQualifier(r3)
            java.lang.String r5 = r5.getValue()
            com.adobe.xmp.impl.XMPNode r6 = r6.getQualifier(r3)
            java.lang.String r6 = r6.getValue()
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto Lb8
            return r2
        L5a:
            boolean r0 = r0.isStruct()
            if (r0 == 0) goto L8c
            int r0 = r5.getChildrenLength()
            int r1 = r6.getChildrenLength()
            if (r0 == r1) goto L6b
            return r2
        L6b:
            java.util.Iterator r5 = r5.iterateChildren()
        L6f:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto Lb8
            java.lang.Object r0 = r5.next()
            com.adobe.xmp.impl.XMPNode r0 = (com.adobe.xmp.impl.XMPNode) r0
            java.lang.String r1 = r0.getName()
            com.adobe.xmp.impl.XMPNode r1 = com.adobe.xmp.impl.XMPNodeUtils.findChildNode(r6, r1, r2)
            if (r1 == 0) goto L8b
            boolean r0 = itemValuesMatch(r0, r1)
            if (r0 != 0) goto L6f
        L8b:
            return r2
        L8c:
            java.util.Iterator r5 = r5.iterateChildren()
        L90:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto Lb8
            java.lang.Object r0 = r5.next()
            com.adobe.xmp.impl.XMPNode r0 = (com.adobe.xmp.impl.XMPNode) r0
            java.util.Iterator r1 = r6.iterateChildren()
        La0:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto Lb4
            java.lang.Object r4 = r1.next()
            com.adobe.xmp.impl.XMPNode r4 = (com.adobe.xmp.impl.XMPNode) r4
            boolean r4 = itemValuesMatch(r0, r4)
            if (r4 == 0) goto La0
            r0 = r3
            goto Lb5
        Lb4:
            r0 = r2
        Lb5:
            if (r0 != 0) goto L90
            return r2
        Lb8:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPUtilsImpl.itemValuesMatch(com.adobe.xmp.impl.XMPNode, com.adobe.xmp.impl.XMPNode):boolean");
    }

    public static void removeProperties(XMPMeta xMPMeta, String str, String str2, boolean z, boolean z2) throws XMPException {
        XMPAliasInfo[] findAliases;
        ParameterAsserts.assertImplementation(xMPMeta);
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta;
        if (str2 != null && str2.length() > 0) {
            if (str == null || str.length() == 0) {
                throw new XMPException("Property name requires schema namespace", 4);
            }
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPNode findNode = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), expandXPath, false, null);
            if (findNode == null) {
                return;
            }
            if (!z && Utils.isInternalProperty(expandXPath.getSegment(0).getName(), expandXPath.getSegment(1).getName())) {
                return;
            }
            XMPNode parent = findNode.getParent();
            parent.removeChild(findNode);
            if (!parent.getOptions().isSchemaNode() || parent.hasChildren()) {
                return;
            }
            parent.getParent().removeChild(parent);
        } else if (str == null || str.length() <= 0) {
            Iterator iterateChildren = xMPMetaImpl.getRoot().iterateChildren();
            while (iterateChildren.hasNext()) {
                if (removeSchemaChildren((XMPNode) iterateChildren.next(), z)) {
                    iterateChildren.remove();
                }
            }
        } else {
            XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), str, false);
            if (findSchemaNode != null && removeSchemaChildren(findSchemaNode, z)) {
                xMPMetaImpl.getRoot().removeChild(findSchemaNode);
            }
            if (!z2) {
                return;
            }
            for (XMPAliasInfo xMPAliasInfo : XMPMetaFactory.getSchemaRegistry().findAliases(str)) {
                XMPNode findNode2 = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), XMPPathParser.expandXPath(xMPAliasInfo.getNamespace(), xMPAliasInfo.getPropName()), false, null);
                if (findNode2 != null) {
                    findNode2.getParent().removeChild(findNode2);
                }
            }
        }
    }

    private static boolean removeSchemaChildren(XMPNode xMPNode, boolean z) {
        Iterator iterateChildren = xMPNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateChildren.next();
            if (z || !Utils.isInternalProperty(xMPNode.getName(), xMPNode2.getName())) {
                iterateChildren.remove();
            }
        }
        return !xMPNode.hasChildren();
    }

    public static void separateArrayItems(XMPMeta xMPMeta, String str, String str2, String str3, PropertyOptions propertyOptions, boolean z) throws XMPException {
        String str4;
        StringBuilder sb;
        char c;
        int i;
        int classifyCharacter;
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        if (str3 != null) {
            ParameterAsserts.assertImplementation(xMPMeta);
            XMPNode separateFindCreateArray = separateFindCreateArray(str, str2, propertyOptions, (XMPMetaImpl) xMPMeta);
            int length = str3.length();
            int i2 = 0;
            char c2 = 0;
            int i3 = 0;
            while (i2 < length) {
                while (i2 < length) {
                    c2 = str3.charAt(i2);
                    i3 = classifyCharacter(c2);
                    if (i3 == 0 || i3 == 4) {
                        break;
                    }
                    i2++;
                }
                if (i2 >= length) {
                    return;
                }
                int i4 = 1;
                if (i3 != 4) {
                    char c3 = c2;
                    int i5 = i2;
                    while (i5 < length) {
                        c3 = str3.charAt(i5);
                        i3 = classifyCharacter(c3);
                        if (i3 != 0 && i3 != 4 && ((i3 != 2 || !z) && (i3 != 1 || (i = i5 + 1) >= length || ((classifyCharacter = classifyCharacter((c3 = str3.charAt(i)))) != 0 && classifyCharacter != 4 && (classifyCharacter != 2 || !z))))) {
                            break;
                        }
                        i5++;
                    }
                    str4 = str3.substring(i2, i5);
                    i2 = i5;
                    c2 = c3;
                } else {
                    char closingQuote = getClosingQuote(c2);
                    i2++;
                    str4 = "";
                    int i6 = i3;
                    char c4 = c2;
                    while (true) {
                        if (i2 >= length) {
                            c2 = c4;
                            i3 = i6;
                            break;
                        }
                        c4 = str3.charAt(i2);
                        i6 = classifyCharacter(c4);
                        if (i6 == 4 && isSurroundingQuote(c4, c2, closingQuote)) {
                            int i7 = i2 + 1;
                            if (i7 < length) {
                                c = str3.charAt(i7);
                                classifyCharacter(c);
                            } else {
                                c = ';';
                            }
                            if (c4 != c) {
                                if (isClosingingQuote(c4, c2, closingQuote)) {
                                    c2 = c4;
                                    i3 = i6;
                                    i2 = i7;
                                    break;
                                }
                                sb = new StringBuilder();
                            } else {
                                str4 = GeneratedOutlineSupport1.outline47(str4, c4);
                                i2 = i7;
                                i2++;
                            }
                        } else {
                            sb = new StringBuilder();
                        }
                        str4 = GeneratedOutlineSupport1.outline89(sb, str4, c4);
                        i2++;
                    }
                }
                while (true) {
                    if (i4 > separateFindCreateArray.getChildrenLength()) {
                        i4 = -1;
                        break;
                    } else if (str4.equals(separateFindCreateArray.getChild(i4).getValue())) {
                        break;
                    } else {
                        i4++;
                    }
                }
                if (i4 < 0) {
                    separateFindCreateArray.addChild(new XMPNode("[]", str4, null));
                }
            }
            return;
        }
        throw new XMPException("Parameter must not be null", 4);
    }

    private static XMPNode separateFindCreateArray(String str, String str2, PropertyOptions propertyOptions, XMPMetaImpl xMPMetaImpl) throws XMPException {
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, null);
        if (verifySetOptions.isOnlyArrayOptions()) {
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPNode findNode = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), expandXPath, false, null);
            if (findNode != null) {
                PropertyOptions options = findNode.getOptions();
                if (!options.isArray() || options.isArrayAlternate()) {
                    throw new XMPException("Named property must be non-alternate array", 102);
                }
                if (verifySetOptions.equalArrayTypes(options)) {
                    throw new XMPException("Mismatch of specified and existing array form", 102);
                }
            } else {
                findNode = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), expandXPath, true, verifySetOptions.setArray(true));
                if (findNode == null) {
                    throw new XMPException("Failed to create named array", 102);
                }
            }
            return findNode;
        }
        throw new XMPException("Options can only provide array form", 103);
    }
}
