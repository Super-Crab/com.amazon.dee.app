package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigSyntax;
import com.typesafe.config.ConfigValueType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigDocumentParser {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ParseContext {
        private final ConfigOrigin baseOrigin;
        private final ConfigSyntax flavor;
        private final Iterator<Token> tokens;
        private final String ExpectingClosingParenthesisError = "expecting a close parentheses ')' here, not: ";
        private int lineNumber = 1;
        private final Stack<Token> buffer = new Stack<>();
        int equalsCount = 0;

        ParseContext(ConfigSyntax configSyntax, ConfigOrigin configOrigin, Iterator<Token> it2) {
            this.tokens = it2;
            this.flavor = configSyntax;
            this.baseOrigin = configOrigin;
        }

        private String addQuoteSuggestion(String str, String str2) {
            return addQuoteSuggestion(null, this.equalsCount > 0, str, str2);
        }

        private boolean checkElementSeparator(Collection<AbstractConfigNode> collection) {
            boolean z = false;
            if (this.flavor == ConfigSyntax.JSON) {
                Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(collection);
                if (nextTokenCollectingWhitespace == Tokens.COMMA) {
                    collection.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                    return true;
                }
                putBack(nextTokenCollectingWhitespace);
                return false;
            }
            Token nextToken = nextToken();
            while (true) {
                if (!Tokens.isIgnoredWhitespace(nextToken) && !isUnquotedWhitespace(nextToken)) {
                    if (Tokens.isComment(nextToken)) {
                        collection.add(new ConfigNodeComment(nextToken));
                    } else if (!Tokens.isNewline(nextToken)) {
                        break;
                    } else {
                        this.lineNumber++;
                        collection.add(new ConfigNodeSingleToken(nextToken));
                        z = true;
                    }
                } else {
                    collection.add(new ConfigNodeSingleToken(nextToken));
                }
                nextToken = nextToken();
            }
            if (nextToken == Tokens.COMMA) {
                collection.add(new ConfigNodeSingleToken(nextToken));
                return true;
            }
            putBack(nextToken);
            return z;
        }

        private AbstractConfigNodeValue consolidateValues(Collection<AbstractConfigNode> collection) {
            AbstractConfigNodeValue abstractConfigNodeValue = null;
            if (this.flavor == ConfigSyntax.JSON) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(collection);
            int i = 0;
            while (true) {
                if (Tokens.isIgnoredWhitespace(nextTokenCollectingWhitespace)) {
                    arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                    nextTokenCollectingWhitespace = nextToken();
                } else if (!Tokens.isValue(nextTokenCollectingWhitespace) && !Tokens.isUnquotedText(nextTokenCollectingWhitespace) && !Tokens.isSubstitution(nextTokenCollectingWhitespace) && nextTokenCollectingWhitespace != Tokens.OPEN_CURLY && nextTokenCollectingWhitespace != Tokens.OPEN_SQUARE) {
                    putBack(nextTokenCollectingWhitespace);
                    if (i < 2) {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            AbstractConfigNode abstractConfigNode = (AbstractConfigNode) it2.next();
                            if (abstractConfigNode instanceof AbstractConfigNodeValue) {
                                abstractConfigNodeValue = (AbstractConfigNodeValue) abstractConfigNode;
                            } else if (abstractConfigNodeValue == null) {
                                collection.add(abstractConfigNode);
                            } else {
                                putBack((Token) new ArrayList(abstractConfigNode.tokens()).get(0));
                            }
                        }
                        return abstractConfigNodeValue;
                    }
                    for (int size = arrayList.size() - 1; size >= 0 && (arrayList.get(size) instanceof ConfigNodeSingleToken); size--) {
                        putBack(((ConfigNodeSingleToken) arrayList.get(size)).token());
                        arrayList.remove(size);
                    }
                    return new ConfigNodeConcatenation(arrayList);
                } else {
                    AbstractConfigNodeValue parseValue = parseValue(nextTokenCollectingWhitespace);
                    i++;
                    if (parseValue != null) {
                        arrayList.add(parseValue);
                        nextTokenCollectingWhitespace = nextToken();
                    } else {
                        throw new ConfigException.BugOrBroken("no value");
                    }
                }
            }
        }

        private static boolean isIncludeKeyword(Token token) {
            return Tokens.isUnquotedText(token) && Tokens.getUnquotedText(token).equals("include");
        }

        private boolean isKeyValueSeparatorToken(Token token) {
            return this.flavor == ConfigSyntax.JSON ? token == Tokens.COLON : token == Tokens.COLON || token == Tokens.EQUALS || token == Tokens.PLUS_EQUALS;
        }

        private static boolean isUnquotedWhitespace(Token token) {
            if (!Tokens.isUnquotedText(token)) {
                return false;
            }
            String unquotedText = Tokens.getUnquotedText(token);
            for (int i = 0; i < unquotedText.length(); i++) {
                if (!ConfigImplUtil.isWhitespace(unquotedText.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        private Token nextToken() {
            Token popToken = popToken();
            if (this.flavor == ConfigSyntax.JSON) {
                if (Tokens.isUnquotedText(popToken) && !isUnquotedWhitespace(popToken)) {
                    throw parseError(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Token not allowed in valid JSON: '"), Tokens.getUnquotedText(popToken), "'"));
                }
                if (Tokens.isSubstitution(popToken)) {
                    throw parseError("Substitutions (${} syntax) not allowed in JSON");
                }
            }
            return popToken;
        }

        private Token nextTokenCollectingWhitespace(Collection<AbstractConfigNode> collection) {
            Token nextToken;
            while (true) {
                nextToken = nextToken();
                if (!Tokens.isIgnoredWhitespace(nextToken) && !Tokens.isNewline(nextToken) && !isUnquotedWhitespace(nextToken)) {
                    if (!Tokens.isComment(nextToken)) {
                        break;
                    }
                    collection.add(new ConfigNodeComment(nextToken));
                } else {
                    collection.add(new ConfigNodeSingleToken(nextToken));
                    if (Tokens.isNewline(nextToken)) {
                        this.lineNumber = nextToken.lineNumber() + 1;
                    }
                }
            }
            int lineNumber = nextToken.lineNumber();
            if (lineNumber >= 0) {
                this.lineNumber = lineNumber;
            }
            return nextToken;
        }

        private ConfigNodeComplexValue parseArray() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new ConfigNodeSingleToken(Tokens.OPEN_SQUARE));
            AbstractConfigNodeValue consolidateValues = consolidateValues(arrayList);
            if (consolidateValues != null) {
                arrayList.add(consolidateValues);
            } else {
                Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(arrayList);
                if (nextTokenCollectingWhitespace == Tokens.CLOSE_SQUARE) {
                    arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                    return new ConfigNodeArray(arrayList);
                } else if (!Tokens.isValue(nextTokenCollectingWhitespace) && nextTokenCollectingWhitespace != Tokens.OPEN_CURLY && nextTokenCollectingWhitespace != Tokens.OPEN_SQUARE && !Tokens.isUnquotedText(nextTokenCollectingWhitespace) && !Tokens.isSubstitution(nextTokenCollectingWhitespace)) {
                    throw parseError("List should have ] or a first element after the open [, instead had token: " + nextTokenCollectingWhitespace + " (if you want " + nextTokenCollectingWhitespace + " to be part of a string value, then double-quote it)");
                } else {
                    arrayList.add(parseValue(nextTokenCollectingWhitespace));
                }
            }
            while (checkElementSeparator(arrayList)) {
                AbstractConfigNodeValue consolidateValues2 = consolidateValues(arrayList);
                if (consolidateValues2 != null) {
                    arrayList.add(consolidateValues2);
                } else {
                    Token nextTokenCollectingWhitespace2 = nextTokenCollectingWhitespace(arrayList);
                    if (!Tokens.isValue(nextTokenCollectingWhitespace2) && nextTokenCollectingWhitespace2 != Tokens.OPEN_CURLY && nextTokenCollectingWhitespace2 != Tokens.OPEN_SQUARE && !Tokens.isUnquotedText(nextTokenCollectingWhitespace2) && !Tokens.isSubstitution(nextTokenCollectingWhitespace2)) {
                        if (this.flavor != ConfigSyntax.JSON && nextTokenCollectingWhitespace2 == Tokens.CLOSE_SQUARE) {
                            putBack(nextTokenCollectingWhitespace2);
                        } else {
                            throw parseError("List should have had new element after a comma, instead had token: " + nextTokenCollectingWhitespace2 + " (if you want the comma or " + nextTokenCollectingWhitespace2 + " to be part of a string value, then double-quote it)");
                        }
                    } else {
                        arrayList.add(parseValue(nextTokenCollectingWhitespace2));
                    }
                }
            }
            Token nextTokenCollectingWhitespace3 = nextTokenCollectingWhitespace(arrayList);
            if (nextTokenCollectingWhitespace3 == Tokens.CLOSE_SQUARE) {
                arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace3));
                return new ConfigNodeArray(arrayList);
            }
            throw parseError("List should have ended with ] or had a comma, instead had token: " + nextTokenCollectingWhitespace3 + " (if you want " + nextTokenCollectingWhitespace3 + " to be part of a string value, then double-quote it)");
        }

        private ConfigException parseError(String str) {
            return parseError(str, null);
        }

        private ConfigNodeInclude parseInclude(ArrayList<AbstractConfigNode> arrayList) {
            Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(arrayList);
            if (Tokens.isUnquotedText(nextTokenCollectingWhitespace)) {
                String unquotedText = Tokens.getUnquotedText(nextTokenCollectingWhitespace);
                if (unquotedText.startsWith("required(")) {
                    String replaceFirst = unquotedText.replaceFirst("required\\(", "");
                    if (replaceFirst.length() > 0) {
                        putBack(Tokens.newUnquotedText(nextTokenCollectingWhitespace.origin(), replaceFirst));
                    }
                    arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                    ConfigNodeInclude parseIncludeResource = parseIncludeResource(arrayList, true);
                    Token nextTokenCollectingWhitespace2 = nextTokenCollectingWhitespace(arrayList);
                    if (Tokens.isUnquotedText(nextTokenCollectingWhitespace2) && Tokens.getUnquotedText(nextTokenCollectingWhitespace2).equals(")")) {
                        return parseIncludeResource;
                    }
                    throw parseError(GeneratedOutlineSupport1.outline62("expecting a close parentheses ')' here, not: ", nextTokenCollectingWhitespace2));
                }
                putBack(nextTokenCollectingWhitespace);
                return parseIncludeResource(arrayList, false);
            }
            putBack(nextTokenCollectingWhitespace);
            return parseIncludeResource(arrayList, false);
        }

        private ConfigNodeInclude parseIncludeResource(ArrayList<AbstractConfigNode> arrayList, boolean z) {
            ConfigIncludeKind configIncludeKind;
            Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(arrayList);
            if (Tokens.isUnquotedText(nextTokenCollectingWhitespace)) {
                String unquotedText = Tokens.getUnquotedText(nextTokenCollectingWhitespace);
                String str = "url(";
                if (unquotedText.startsWith(str)) {
                    configIncludeKind = ConfigIncludeKind.URL;
                } else if (unquotedText.startsWith("file(")) {
                    configIncludeKind = ConfigIncludeKind.FILE;
                    str = "file(";
                } else if (unquotedText.startsWith("classpath(")) {
                    configIncludeKind = ConfigIncludeKind.CLASSPATH;
                    str = "classpath(";
                } else {
                    throw parseError(GeneratedOutlineSupport1.outline62("expecting include parameter to be quoted filename, file(), classpath(), or url(). No spaces are allowed before the open paren. Not expecting: ", nextTokenCollectingWhitespace));
                }
                String replaceFirst = unquotedText.replaceFirst("[^(]*\\(", "");
                if (replaceFirst.length() > 0) {
                    putBack(Tokens.newUnquotedText(nextTokenCollectingWhitespace.origin(), replaceFirst));
                }
                arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                Token nextTokenCollectingWhitespace2 = nextTokenCollectingWhitespace(arrayList);
                if (Tokens.isValueWithType(nextTokenCollectingWhitespace2, ConfigValueType.STRING)) {
                    arrayList.add(new ConfigNodeSimpleValue(nextTokenCollectingWhitespace2));
                    Token nextTokenCollectingWhitespace3 = nextTokenCollectingWhitespace(arrayList);
                    if (Tokens.isUnquotedText(nextTokenCollectingWhitespace3) && Tokens.getUnquotedText(nextTokenCollectingWhitespace3).startsWith(")")) {
                        String substring = Tokens.getUnquotedText(nextTokenCollectingWhitespace3).substring(1);
                        if (substring.length() > 0) {
                            putBack(Tokens.newUnquotedText(nextTokenCollectingWhitespace3.origin(), substring));
                        }
                        return new ConfigNodeInclude(arrayList, configIncludeKind, z);
                    }
                    throw parseError(GeneratedOutlineSupport1.outline62("expecting a close parentheses ')' here, not: ", nextTokenCollectingWhitespace3));
                }
                throw parseError("expecting include " + str + ") parameter to be a quoted string, rather than: " + nextTokenCollectingWhitespace2);
            } else if (Tokens.isValueWithType(nextTokenCollectingWhitespace, ConfigValueType.STRING)) {
                arrayList.add(new ConfigNodeSimpleValue(nextTokenCollectingWhitespace));
                return new ConfigNodeInclude(arrayList, ConfigIncludeKind.HEURISTIC, z);
            } else {
                throw parseError(GeneratedOutlineSupport1.outline62("include keyword is not followed by a quoted string, but by: ", nextTokenCollectingWhitespace));
            }
        }

        private ConfigNodePath parseKey(Token token) {
            if (this.flavor == ConfigSyntax.JSON) {
                if (Tokens.isValueWithType(token, ConfigValueType.STRING)) {
                    return PathParser.parsePathNodeExpression(Collections.singletonList(token).iterator(), this.baseOrigin.mo10263withLineNumber(this.lineNumber));
                }
                throw parseError(GeneratedOutlineSupport1.outline62("Expecting close brace } or a field name here, got ", token));
            }
            ArrayList arrayList = new ArrayList();
            while (true) {
                if (!Tokens.isValue(token) && !Tokens.isUnquotedText(token)) {
                    break;
                }
                arrayList.add(token);
                token = nextToken();
            }
            if (!arrayList.isEmpty()) {
                putBack(token);
                return PathParser.parsePathNodeExpression(arrayList.iterator(), this.baseOrigin.mo10263withLineNumber(this.lineNumber));
            }
            throw parseError(GeneratedOutlineSupport1.outline62("expecting a close parentheses ')' here, not: ", token));
        }

        /* JADX WARN: Code restructure failed: missing block: B:72:0x015b, code lost:
            return new com.typesafe.config.impl.ConfigNodeObject(r0);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private com.typesafe.config.impl.ConfigNodeComplexValue parseObject(boolean r11) {
            /*
                Method dump skipped, instructions count: 432
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.ConfigDocumentParser.ParseContext.parseObject(boolean):com.typesafe.config.impl.ConfigNodeComplexValue");
        }

        private AbstractConfigNodeValue parseValue(Token token) {
            AbstractConfigNodeValue configNodeSimpleValue;
            int i = this.equalsCount;
            if (!Tokens.isValue(token) && !Tokens.isUnquotedText(token) && !Tokens.isSubstitution(token)) {
                if (token == Tokens.OPEN_CURLY) {
                    configNodeSimpleValue = parseObject(true);
                } else if (token == Tokens.OPEN_SQUARE) {
                    configNodeSimpleValue = parseArray();
                } else {
                    throw parseError(addQuoteSuggestion(token.toString(), GeneratedOutlineSupport1.outline62("Expecting a value but got wrong token: ", token)));
                }
            } else {
                configNodeSimpleValue = new ConfigNodeSimpleValue(token);
            }
            if (this.equalsCount == i) {
                return configNodeSimpleValue;
            }
            throw new ConfigException.BugOrBroken("Bug in config parser: unbalanced equals count");
        }

        private Token popToken() {
            if (this.buffer.isEmpty()) {
                return this.tokens.next();
            }
            return this.buffer.pop();
        }

        private void putBack(Token token) {
            this.buffer.push(token);
        }

        ConfigNodeRoot parse() {
            AbstractConfigNodeValue parseValue;
            boolean z;
            ArrayList arrayList = new ArrayList();
            Token nextToken = nextToken();
            if (nextToken == Tokens.START) {
                Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(arrayList);
                if (nextTokenCollectingWhitespace != Tokens.OPEN_CURLY && nextTokenCollectingWhitespace != Tokens.OPEN_SQUARE) {
                    if (this.flavor == ConfigSyntax.JSON) {
                        if (nextTokenCollectingWhitespace == Tokens.END) {
                            throw parseError("Empty document");
                        }
                        throw parseError(GeneratedOutlineSupport1.outline62("Document must have an object or array at root, unexpected token: ", nextTokenCollectingWhitespace));
                    }
                    putBack(nextTokenCollectingWhitespace);
                    z = true;
                    parseValue = parseObject(false);
                } else {
                    parseValue = parseValue(nextTokenCollectingWhitespace);
                    z = false;
                }
                if ((parseValue instanceof ConfigNodeObject) && z) {
                    arrayList.addAll(((ConfigNodeComplexValue) parseValue).children());
                } else {
                    arrayList.add(parseValue);
                }
                Token nextTokenCollectingWhitespace2 = nextTokenCollectingWhitespace(arrayList);
                if (nextTokenCollectingWhitespace2 != Tokens.END) {
                    throw parseError(GeneratedOutlineSupport1.outline62("Document has trailing tokens after first object or array: ", nextTokenCollectingWhitespace2));
                }
                if (z) {
                    return new ConfigNodeRoot(Collections.singletonList(new ConfigNodeObject(arrayList)), this.baseOrigin);
                }
                return new ConfigNodeRoot(arrayList, this.baseOrigin);
            }
            throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("token stream did not begin with START, had ", nextToken));
        }

        AbstractConfigNodeValue parseSingleValue() {
            Token nextToken = nextToken();
            if (nextToken == Tokens.START) {
                Token nextToken2 = nextToken();
                if (!Tokens.isIgnoredWhitespace(nextToken2) && !Tokens.isNewline(nextToken2) && !isUnquotedWhitespace(nextToken2) && !Tokens.isComment(nextToken2)) {
                    if (nextToken2 != Tokens.END) {
                        if (this.flavor == ConfigSyntax.JSON) {
                            AbstractConfigNodeValue parseValue = parseValue(nextToken2);
                            if (nextToken() != Tokens.END) {
                                throw parseError("Parsing JSON and the value set in withValueText was either a concatenation or had trailing whitespace, newlines, or comments");
                            }
                            return parseValue;
                        }
                        putBack(nextToken2);
                        AbstractConfigNodeValue consolidateValues = consolidateValues(new ArrayList());
                        if (nextToken() != Tokens.END) {
                            throw parseError("The value from withValueText cannot have leading or trailing newlines, whitespace, or comments");
                        }
                        return consolidateValues;
                    }
                    throw parseError("Empty value");
                }
                throw parseError("The value from withValueText cannot have leading or trailing newlines, whitespace, or comments");
            }
            throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("token stream did not begin with START, had ", nextToken));
        }

        private String addQuoteSuggestion(Path path, boolean z, String str, String str2) {
            String outline76;
            String render = path != null ? path.render() : null;
            if (str.equals(Tokens.END.toString())) {
                if (render == null) {
                    return str2;
                }
                outline76 = GeneratedOutlineSupport1.outline76(str2, " (if you intended '", render, "' to be part of a value, instead of a key, try adding double quotes around the whole value");
            } else if (render != null) {
                StringBuilder outline116 = GeneratedOutlineSupport1.outline116(str2, " (if you intended ", str, " to be part of the value for '", render);
                outline116.append("', try enclosing the value in double quotes");
                outline76 = outline116.toString();
            } else {
                outline76 = GeneratedOutlineSupport1.outline76(str2, " (if you intended ", str, " to be part of a key or string value, try enclosing the key or value in double quotes");
            }
            if (z) {
                return GeneratedOutlineSupport1.outline72(outline76, ", or you may be able to rename the file .properties rather than .conf)");
            }
            return GeneratedOutlineSupport1.outline72(outline76, ")");
        }

        private ConfigException parseError(String str, Throwable th) {
            return new ConfigException.Parse(this.baseOrigin.mo10263withLineNumber(this.lineNumber), str, th);
        }
    }

    ConfigDocumentParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigNodeRoot parse(Iterator<Token> it2, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
        return new ParseContext(configParseOptions.getSyntax() == null ? ConfigSyntax.CONF : configParseOptions.getSyntax(), configOrigin, it2).parse();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigNodeValue parseValue(Iterator<Token> it2, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
        return new ParseContext(configParseOptions.getSyntax() == null ? ConfigSyntax.CONF : configParseOptions.getSyntax(), configOrigin, it2).parseSingleValue();
    }
}
