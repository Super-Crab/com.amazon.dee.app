package com.typesafe.config.impl;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigSyntax;
import com.typesafe.config.ConfigValueType;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class PathParser {
    static ConfigOrigin apiOrigin = SimpleConfigOrigin.newSimple("path parameter");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Element {
        boolean canBeEmpty;
        StringBuilder sb;

        Element(String str, boolean z) {
            this.canBeEmpty = z;
            this.sb = new StringBuilder(str);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Element(");
            outline107.append(this.sb.toString());
            outline107.append(",");
            return GeneratedOutlineSupport1.outline97(outline107, this.canBeEmpty, ")");
        }
    }

    PathParser() {
    }

    private static void addPathText(List<Element> list, boolean z, String str) {
        int indexOf = z ? -1 : str.indexOf(46);
        Element element = (Element) GeneratedOutlineSupport1.outline25(list, 1);
        if (indexOf < 0) {
            element.sb.append(str);
            if (!z || element.sb.length() != 0) {
                return;
            }
            element.canBeEmpty = true;
            return;
        }
        element.sb.append(str.substring(0, indexOf));
        list.add(new Element("", false));
        addPathText(list, false, str.substring(indexOf + 1));
    }

    private static Path fastPathBuild(Path path, String str, int i) {
        int lastIndexOf = str.lastIndexOf(46, i - 1);
        new ArrayList().add(Tokens.newUnquotedText(null, str));
        Path path2 = new Path(str.substring(lastIndexOf + 1, i), path);
        return lastIndexOf < 0 ? path2 : fastPathBuild(path2, str, lastIndexOf);
    }

    private static boolean looksUnsafeForFastParser(String str) {
        int length = str.length();
        if (str.isEmpty() || str.charAt(0) == '.' || str.charAt(length - 1) == '.') {
            return true;
        }
        boolean z = true;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == '_')) {
                z = false;
            } else if (charAt == '.') {
                if (z) {
                    return true;
                }
                z = true;
            } else if (charAt != '-' || z) {
                return true;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Path parsePath(String str) {
        Path speculativeFastParsePath = speculativeFastParsePath(str);
        if (speculativeFastParsePath != null) {
            return speculativeFastParsePath;
        }
        StringReader stringReader = new StringReader(str);
        try {
            Iterator<Token> it2 = Tokenizer.tokenize(apiOrigin, stringReader, ConfigSyntax.CONF);
            it2.next();
            return parsePathExpression(it2, apiOrigin, str);
        } finally {
            stringReader.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Path parsePathExpression(Iterator<Token> it2, ConfigOrigin configOrigin) {
        return parsePathExpression(it2, configOrigin, null, null, ConfigSyntax.CONF);
    }

    static ConfigNodePath parsePathNode(String str) {
        return parsePathNode(str, ConfigSyntax.CONF);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ConfigNodePath parsePathNodeExpression(Iterator<Token> it2, ConfigOrigin configOrigin) {
        return parsePathNodeExpression(it2, configOrigin, null, ConfigSyntax.CONF);
    }

    private static Path speculativeFastParsePath(String str) {
        String unicodeTrim = ConfigImplUtil.unicodeTrim(str);
        if (looksUnsafeForFastParser(unicodeTrim)) {
            return null;
        }
        return fastPathBuild(null, unicodeTrim, unicodeTrim.length());
    }

    private static Collection<Token> splitTokenOnPeriod(Token token, ConfigSyntax configSyntax) {
        String str = token.tokenText();
        if (str.equals(".")) {
            return Collections.singletonList(token);
        }
        String[] split = str.split(ArcusConfig.PATH_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            if (configSyntax == ConfigSyntax.CONF) {
                arrayList.add(Tokens.newUnquotedText(token.origin(), str2));
            } else {
                arrayList.add(Tokens.newString(token.origin(), str2, GeneratedOutlineSupport1.outline75(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, str2, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)));
            }
            arrayList.add(Tokens.newUnquotedText(token.origin(), "."));
        }
        if (str.charAt(str.length() - 1) != '.') {
            arrayList.remove(arrayList.size() - 1);
        }
        return arrayList;
    }

    protected static Path parsePathExpression(Iterator<Token> it2, ConfigOrigin configOrigin, String str) {
        return parsePathExpression(it2, configOrigin, str, null, ConfigSyntax.CONF);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigNodePath parsePathNode(String str, ConfigSyntax configSyntax) {
        StringReader stringReader = new StringReader(str);
        try {
            Iterator<Token> it2 = Tokenizer.tokenize(apiOrigin, stringReader, configSyntax);
            it2.next();
            return parsePathNodeExpression(it2, apiOrigin, str, configSyntax);
        } finally {
            stringReader.close();
        }
    }

    protected static ConfigNodePath parsePathNodeExpression(Iterator<Token> it2, ConfigOrigin configOrigin, String str, ConfigSyntax configSyntax) {
        ArrayList arrayList = new ArrayList();
        return new ConfigNodePath(parsePathExpression(it2, configOrigin, str, arrayList, configSyntax), arrayList);
    }

    protected static Path parsePathExpression(Iterator<Token> it2, ConfigOrigin configOrigin, String str, ArrayList<Token> arrayList, ConfigSyntax configSyntax) {
        String unquotedText;
        ArrayList<Element> arrayList2 = new ArrayList();
        arrayList2.add(new Element("", false));
        if (it2.hasNext()) {
            while (it2.hasNext()) {
                Token next = it2.next();
                if (arrayList != null) {
                    arrayList.add(next);
                }
                if (!Tokens.isIgnoredWhitespace(next)) {
                    if (Tokens.isValueWithType(next, ConfigValueType.STRING)) {
                        addPathText(arrayList2, true, Tokens.getValue(next).transformToString());
                    } else if (next != Tokens.END) {
                        if (Tokens.isValue(next)) {
                            AbstractConfigValue value = Tokens.getValue(next);
                            if (arrayList != null) {
                                arrayList.remove(arrayList.size() - 1);
                                arrayList.addAll(splitTokenOnPeriod(next, configSyntax));
                            }
                            unquotedText = value.transformToString();
                        } else if (Tokens.isUnquotedText(next)) {
                            if (arrayList != null) {
                                arrayList.remove(arrayList.size() - 1);
                                arrayList.addAll(splitTokenOnPeriod(next, configSyntax));
                            }
                            unquotedText = Tokens.getUnquotedText(next);
                        } else {
                            throw new ConfigException.BadPath(configOrigin, str, "Token not allowed in path expression: " + next + " (you can double-quote this token if you really want it here)");
                        }
                        addPathText(arrayList2, false, unquotedText);
                    } else {
                        continue;
                    }
                }
            }
            PathBuilder pathBuilder = new PathBuilder();
            for (Element element : arrayList2) {
                if (element.sb.length() == 0 && !element.canBeEmpty) {
                    throw new ConfigException.BadPath(configOrigin, str, "path has a leading, trailing, or two adjacent period '.' (use quoted \"\" empty string if you want an empty element)");
                }
                pathBuilder.appendKey(element.sb.toString());
            }
            return pathBuilder.result();
        }
        throw new ConfigException.BadPath(configOrigin, str, "Expecting a field name or path here, but got nothing");
    }
}
