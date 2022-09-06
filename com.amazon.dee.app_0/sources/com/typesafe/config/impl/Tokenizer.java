package com.typesafe.config.impl;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigSyntax;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class Tokenizer {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class ProblemException extends Exception {
        private static final long serialVersionUID = 1;
        private final Token problem;

        ProblemException(Token token) {
            this.problem = token;
        }

        Token problem() {
            return this.problem;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class TokenIterator implements Iterator<Token> {
        static final String firstNumberChars = "0123456789-";
        static final String notInUnquotedText = "$\"{}[]:=,+#`^?!@*&\\";
        static final String numberChars = "0123456789eE+-.";
        private final boolean allowComments;
        private final Reader input;
        private ConfigOrigin lineOrigin;
        private final SimpleConfigOrigin origin;
        private final WhitespaceSaver whitespaceSaver;
        private final LinkedList<Integer> buffer = new LinkedList<>();
        private int lineNumber = 1;
        private final Queue<Token> tokens = new LinkedList();

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class WhitespaceSaver {
            private StringBuilder whitespace = new StringBuilder();
            private boolean lastTokenWasSimpleValue = false;

            WhitespaceSaver() {
            }

            private Token createWhitespaceTokenFromSaver(ConfigOrigin configOrigin, int i) {
                Token newIgnoredWhitespace;
                if (this.whitespace.length() > 0) {
                    if (this.lastTokenWasSimpleValue) {
                        newIgnoredWhitespace = Tokens.newUnquotedText(TokenIterator.lineOrigin(configOrigin, i), this.whitespace.toString());
                    } else {
                        newIgnoredWhitespace = Tokens.newIgnoredWhitespace(TokenIterator.lineOrigin(configOrigin, i), this.whitespace.toString());
                    }
                    this.whitespace.setLength(0);
                    return newIgnoredWhitespace;
                }
                return null;
            }

            private Token nextIsASimpleValue(ConfigOrigin configOrigin, int i) {
                Token createWhitespaceTokenFromSaver = createWhitespaceTokenFromSaver(configOrigin, i);
                if (!this.lastTokenWasSimpleValue) {
                    this.lastTokenWasSimpleValue = true;
                }
                return createWhitespaceTokenFromSaver;
            }

            private Token nextIsNotASimpleValue(ConfigOrigin configOrigin, int i) {
                this.lastTokenWasSimpleValue = false;
                return createWhitespaceTokenFromSaver(configOrigin, i);
            }

            void add(int i) {
                this.whitespace.appendCodePoint(i);
            }

            Token check(Token token, ConfigOrigin configOrigin, int i) {
                if (TokenIterator.isSimpleValue(token)) {
                    return nextIsASimpleValue(configOrigin, i);
                }
                return nextIsNotASimpleValue(configOrigin, i);
            }
        }

        TokenIterator(ConfigOrigin configOrigin, Reader reader, boolean z) {
            this.origin = (SimpleConfigOrigin) configOrigin;
            this.input = reader;
            this.allowComments = z;
            this.lineOrigin = this.origin.mo10263withLineNumber(this.lineNumber);
            this.tokens.add(Tokens.START);
            this.whitespaceSaver = new WhitespaceSaver();
        }

        private void appendTripleQuotedString(StringBuilder sb, StringBuilder sb2) throws ProblemException {
            int i = 0;
            while (true) {
                int nextCharRaw = nextCharRaw();
                if (nextCharRaw == 34) {
                    i++;
                } else if (i >= 3) {
                    sb.setLength(sb.length() - 3);
                    putBack(nextCharRaw);
                    return;
                } else if (nextCharRaw == -1) {
                    throw problem("End of input but triple-quoted string was still open");
                } else {
                    if (nextCharRaw == 10) {
                        this.lineNumber++;
                        this.lineOrigin = this.origin.mo10263withLineNumber(this.lineNumber);
                    }
                    i = 0;
                }
                sb.appendCodePoint(nextCharRaw);
                sb2.appendCodePoint(nextCharRaw);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isSimpleValue(Token token) {
            return Tokens.isSubstitution(token) || Tokens.isUnquotedText(token) || Tokens.isValue(token);
        }

        static boolean isWhitespace(int i) {
            return ConfigImplUtil.isWhitespace(i);
        }

        static boolean isWhitespaceNotNewline(int i) {
            return i != 10 && ConfigImplUtil.isWhitespace(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ConfigOrigin lineOrigin(ConfigOrigin configOrigin, int i) {
            return ((SimpleConfigOrigin) configOrigin).mo10263withLineNumber(i);
        }

        private int nextCharAfterWhitespace(WhitespaceSaver whitespaceSaver) {
            while (true) {
                int nextCharRaw = nextCharRaw();
                if (nextCharRaw == -1) {
                    return -1;
                }
                if (!isWhitespaceNotNewline(nextCharRaw)) {
                    return nextCharRaw;
                }
                whitespaceSaver.add(nextCharRaw);
            }
        }

        private int nextCharRaw() {
            if (this.buffer.isEmpty()) {
                try {
                    return this.input.read();
                } catch (IOException e) {
                    throw new ConfigException.IO(this.origin, GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("read error: ")), e);
                }
            }
            return this.buffer.pop().intValue();
        }

        private ProblemException problem(String str) {
            return problem("", str, (Throwable) null);
        }

        private Token pullComment(int i) {
            boolean z;
            int nextCharRaw;
            if (i != 47) {
                z = false;
            } else if (nextCharRaw() != 47) {
                throw new ConfigException.BugOrBroken("called pullComment but // not seen");
            } else {
                z = true;
            }
            StringBuilder sb = new StringBuilder();
            while (true) {
                nextCharRaw = nextCharRaw();
                if (nextCharRaw == -1 || nextCharRaw == 10) {
                    break;
                }
                sb.appendCodePoint(nextCharRaw);
            }
            putBack(nextCharRaw);
            if (z) {
                return Tokens.newCommentDoubleSlash(this.lineOrigin, sb.toString());
            }
            return Tokens.newCommentHash(this.lineOrigin, sb.toString());
        }

        private void pullEscapeSequence(StringBuilder sb, StringBuilder sb2) throws ProblemException {
            int nextCharRaw = nextCharRaw();
            if (nextCharRaw != -1) {
                sb2.appendCodePoint(92);
                sb2.appendCodePoint(nextCharRaw);
                if (nextCharRaw == 34) {
                    sb.append('\"');
                    return;
                } else if (nextCharRaw == 47) {
                    sb.append('/');
                    return;
                } else if (nextCharRaw == 92) {
                    sb.append('\\');
                    return;
                } else if (nextCharRaw == 98) {
                    sb.append('\b');
                    return;
                } else if (nextCharRaw == 102) {
                    sb.append('\f');
                    return;
                } else if (nextCharRaw == 110) {
                    sb.append('\n');
                    return;
                } else if (nextCharRaw == 114) {
                    sb.append('\r');
                    return;
                } else if (nextCharRaw == 116) {
                    sb.append('\t');
                    return;
                } else if (nextCharRaw == 117) {
                    char[] cArr = new char[4];
                    for (int i = 0; i < 4; i++) {
                        int nextCharRaw2 = nextCharRaw();
                        if (nextCharRaw2 != -1) {
                            cArr[i] = (char) nextCharRaw2;
                        } else {
                            throw problem("End of input but expecting 4 hex digits for \\uXXXX escape");
                        }
                    }
                    String str = new String(cArr);
                    sb2.append(cArr);
                    try {
                        sb.appendCodePoint(Integer.parseInt(str, 16));
                        return;
                    } catch (NumberFormatException e) {
                        throw problem(str, String.format("Malformed hex digits after \\u escape in string: '%s'", str), e);
                    }
                } else {
                    throw problem(Tokenizer.asString(nextCharRaw), String.format("backslash followed by '%s', this is not a valid escape sequence (quoted strings use JSON escaping, so use double-backslash \\\\ for literal backslash)", Tokenizer.asString(nextCharRaw)));
                }
            }
            throw problem("End of input but backslash in string had nothing after it");
        }

        private Token pullNextToken(WhitespaceSaver whitespaceSaver) throws ProblemException {
            Token pullQuotedString;
            Token token;
            int nextCharAfterWhitespace = nextCharAfterWhitespace(whitespaceSaver);
            if (nextCharAfterWhitespace == -1) {
                return Tokens.END;
            }
            if (nextCharAfterWhitespace == 10) {
                Token newLine = Tokens.newLine(this.lineOrigin);
                this.lineNumber++;
                this.lineOrigin = this.origin.mo10263withLineNumber(this.lineNumber);
                return newLine;
            }
            if (startOfComment(nextCharAfterWhitespace)) {
                token = pullComment(nextCharAfterWhitespace);
            } else {
                if (nextCharAfterWhitespace == 34) {
                    pullQuotedString = pullQuotedString();
                } else if (nextCharAfterWhitespace == 36) {
                    pullQuotedString = pullSubstitution();
                } else if (nextCharAfterWhitespace == 58) {
                    pullQuotedString = Tokens.COLON;
                } else if (nextCharAfterWhitespace == 61) {
                    pullQuotedString = Tokens.EQUALS;
                } else if (nextCharAfterWhitespace == 91) {
                    pullQuotedString = Tokens.OPEN_SQUARE;
                } else if (nextCharAfterWhitespace == 93) {
                    pullQuotedString = Tokens.CLOSE_SQUARE;
                } else if (nextCharAfterWhitespace == 123) {
                    pullQuotedString = Tokens.OPEN_CURLY;
                } else if (nextCharAfterWhitespace == 125) {
                    pullQuotedString = Tokens.CLOSE_CURLY;
                } else if (nextCharAfterWhitespace != 43) {
                    pullQuotedString = nextCharAfterWhitespace != 44 ? null : Tokens.COMMA;
                } else {
                    pullQuotedString = pullPlusEquals();
                }
                if (pullQuotedString != null) {
                    token = pullQuotedString;
                } else if (firstNumberChars.indexOf(nextCharAfterWhitespace) >= 0) {
                    token = pullNumber(nextCharAfterWhitespace);
                } else if (notInUnquotedText.indexOf(nextCharAfterWhitespace) >= 0) {
                    throw problem(Tokenizer.asString(nextCharAfterWhitespace), GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Reserved character '"), Tokenizer.asString(nextCharAfterWhitespace), "' is not allowed outside quotes"), true);
                } else {
                    putBack(nextCharAfterWhitespace);
                    token = pullUnquotedText();
                }
            }
            if (token == null) {
                throw new ConfigException.BugOrBroken("bug: failed to generate next token");
            }
            return token;
        }

        private Token pullNumber(int i) throws ProblemException {
            char[] charArray;
            StringBuilder sb = new StringBuilder();
            sb.appendCodePoint(i);
            int nextCharRaw = nextCharRaw();
            boolean z = false;
            while (nextCharRaw != -1 && numberChars.indexOf(nextCharRaw) >= 0) {
                if (nextCharRaw == 46 || nextCharRaw == 101 || nextCharRaw == 69) {
                    z = true;
                }
                sb.appendCodePoint(nextCharRaw);
                nextCharRaw = nextCharRaw();
            }
            putBack(nextCharRaw);
            String sb2 = sb.toString();
            try {
                if (z) {
                    return Tokens.newDouble(this.lineOrigin, Double.parseDouble(sb2), sb2);
                }
                return Tokens.newLong(this.lineOrigin, Long.parseLong(sb2), sb2);
            } catch (NumberFormatException unused) {
                for (char c : sb2.toCharArray()) {
                    if (notInUnquotedText.indexOf(c) >= 0) {
                        throw problem(Tokenizer.asString(c), GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Reserved character '"), Tokenizer.asString(c), "' is not allowed outside quotes"), true);
                    }
                }
                return Tokens.newUnquotedText(this.lineOrigin, sb2);
            }
        }

        private Token pullPlusEquals() throws ProblemException {
            int nextCharRaw = nextCharRaw();
            if (nextCharRaw != 61) {
                throw problem(Tokenizer.asString(nextCharRaw), GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("'+' not followed by =, '"), Tokenizer.asString(nextCharRaw), "' not allowed after '+'"), true);
            }
            return Tokens.PLUS_EQUALS;
        }

        private Token pullQuotedString() throws ProblemException {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb2.appendCodePoint(34);
            while (true) {
                int nextCharRaw = nextCharRaw();
                if (nextCharRaw != -1) {
                    if (nextCharRaw == 92) {
                        pullEscapeSequence(sb, sb2);
                    } else if (nextCharRaw == 34) {
                        sb2.appendCodePoint(nextCharRaw);
                        if (sb.length() == 0) {
                            int nextCharRaw2 = nextCharRaw();
                            if (nextCharRaw2 == 34) {
                                sb2.appendCodePoint(nextCharRaw2);
                                appendTripleQuotedString(sb, sb2);
                            } else {
                                putBack(nextCharRaw2);
                            }
                        }
                        return Tokens.newString(this.lineOrigin, sb.toString(), sb2.toString());
                    } else if (ConfigImplUtil.isC0Control(nextCharRaw)) {
                        throw problem(Tokenizer.asString(nextCharRaw), GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("JSON does not allow unescaped "), Tokenizer.asString(nextCharRaw), " in quoted strings, use a backslash escape"));
                    } else {
                        sb.appendCodePoint(nextCharRaw);
                        sb2.appendCodePoint(nextCharRaw);
                    }
                } else {
                    throw problem("End of input but string quote was still open");
                }
            }
        }

        private Token pullSubstitution() throws ProblemException {
            ConfigOrigin configOrigin = this.lineOrigin;
            int nextCharRaw = nextCharRaw();
            if (nextCharRaw != 123) {
                throw problem(Tokenizer.asString(nextCharRaw), GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("'$' not followed by {, '"), Tokenizer.asString(nextCharRaw), "' not allowed after '$'"), true);
            }
            boolean z = false;
            int nextCharRaw2 = nextCharRaw();
            if (nextCharRaw2 == 63) {
                z = true;
            } else {
                putBack(nextCharRaw2);
            }
            WhitespaceSaver whitespaceSaver = new WhitespaceSaver();
            ArrayList arrayList = new ArrayList();
            while (true) {
                Token pullNextToken = pullNextToken(whitespaceSaver);
                if (pullNextToken == Tokens.CLOSE_CURLY) {
                    return Tokens.newSubstitution(configOrigin, z, arrayList);
                }
                if (pullNextToken != Tokens.END) {
                    Token check = whitespaceSaver.check(pullNextToken, configOrigin, this.lineNumber);
                    if (check != null) {
                        arrayList.add(check);
                    }
                    arrayList.add(pullNextToken);
                } else {
                    throw problem(configOrigin, "Substitution ${ was not closed with a }");
                }
            }
        }

        private Token pullUnquotedText() {
            ConfigOrigin configOrigin = this.lineOrigin;
            StringBuilder sb = new StringBuilder();
            int nextCharRaw = nextCharRaw();
            while (nextCharRaw != -1 && notInUnquotedText.indexOf(nextCharRaw) < 0 && !isWhitespace(nextCharRaw) && !startOfComment(nextCharRaw)) {
                sb.appendCodePoint(nextCharRaw);
                if (sb.length() == 4) {
                    String sb2 = sb.toString();
                    if (sb2.equals("true")) {
                        return Tokens.newBoolean(configOrigin, true);
                    }
                    if (sb2.equals("null")) {
                        return Tokens.newNull(configOrigin);
                    }
                } else if (sb.length() == 5 && sb.toString().equals(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE)) {
                    return Tokens.newBoolean(configOrigin, false);
                }
                nextCharRaw = nextCharRaw();
            }
            putBack(nextCharRaw);
            return Tokens.newUnquotedText(configOrigin, sb.toString());
        }

        private void putBack(int i) {
            if (this.buffer.size() <= 2) {
                this.buffer.push(Integer.valueOf(i));
                return;
            }
            throw new ConfigException.BugOrBroken("bug: putBack() three times, undesirable look-ahead");
        }

        private void queueNextToken() throws ProblemException {
            Token pullNextToken = pullNextToken(this.whitespaceSaver);
            Token check = this.whitespaceSaver.check(pullNextToken, this.origin, this.lineNumber);
            if (check != null) {
                this.tokens.add(check);
            }
            this.tokens.add(pullNextToken);
        }

        private boolean startOfComment(int i) {
            if (i != -1 && this.allowComments) {
                if (i == 35) {
                    return true;
                }
                if (i == 47) {
                    int nextCharRaw = nextCharRaw();
                    putBack(nextCharRaw);
                    if (nextCharRaw == 47) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.tokens.isEmpty();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Does not make sense to remove items from token stream");
        }

        private ProblemException problem(String str, String str2) {
            return problem(str, str2, (Throwable) null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        /* renamed from: next */
        public Token mo10264next() {
            Token remove = this.tokens.remove();
            if (this.tokens.isEmpty() && remove != Tokens.END) {
                try {
                    queueNextToken();
                } catch (ProblemException e) {
                    this.tokens.add(e.problem());
                }
                if (this.tokens.isEmpty()) {
                    throw new ConfigException.BugOrBroken("bug: tokens queue should not be empty here");
                }
            }
            return remove;
        }

        private ProblemException problem(String str, String str2, boolean z) {
            return problem(str, str2, z, (Throwable) null);
        }

        private ProblemException problem(String str, String str2, Throwable th) {
            return problem(this.lineOrigin, str, str2, th);
        }

        private ProblemException problem(String str, String str2, boolean z, Throwable th) {
            return problem(this.lineOrigin, str, str2, z, th);
        }

        private static ProblemException problem(ConfigOrigin configOrigin, String str, String str2, Throwable th) {
            return problem(configOrigin, str, str2, false, th);
        }

        private static ProblemException problem(ConfigOrigin configOrigin, String str, String str2, boolean z, Throwable th) {
            if (str != null && str2 != null) {
                return new ProblemException(Tokens.newProblem(configOrigin, str, str2, z, th));
            }
            throw new ConfigException.BugOrBroken("internal error, creating bad ProblemException");
        }

        private static ProblemException problem(ConfigOrigin configOrigin, String str) {
            return problem(configOrigin, "", str, (Throwable) null);
        }
    }

    Tokenizer() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String asString(int i) {
        return i == 10 ? "newline" : i == 9 ? "tab" : i == -1 ? "end of file" : ConfigImplUtil.isC0Control(i) ? String.format("control character 0x%x", Integer.valueOf(i)) : String.format("%c", Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String render(Iterator<Token> it2) {
        StringBuilder sb = new StringBuilder();
        while (it2.hasNext()) {
            sb.append(it2.next().tokenText());
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Iterator<Token> tokenize(ConfigOrigin configOrigin, Reader reader, ConfigSyntax configSyntax) {
        return new TokenIterator(configOrigin, reader, configSyntax != ConfigSyntax.JSON);
    }
}
