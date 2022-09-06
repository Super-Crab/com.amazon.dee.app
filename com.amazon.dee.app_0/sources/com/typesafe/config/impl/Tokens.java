package com.typesafe.config.impl;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.ConfigString;
import java.util.List;
import org.apache.logging.log4j.util.Chars;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class Tokens {
    static final Token START = Token.newWithoutOrigin(TokenType.START, "start of file", "");
    static final Token END = Token.newWithoutOrigin(TokenType.END, "end of file", "");
    static final Token COMMA = Token.newWithoutOrigin(TokenType.COMMA, "','", ",");
    static final Token EQUALS = Token.newWithoutOrigin(TokenType.EQUALS, "'='", Config.Compare.EQUAL_TO);
    static final Token COLON = Token.newWithoutOrigin(TokenType.COLON, "':'", ":");
    static final Token OPEN_CURLY = Token.newWithoutOrigin(TokenType.OPEN_CURLY, "'{'", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
    static final Token CLOSE_CURLY = Token.newWithoutOrigin(TokenType.CLOSE_CURLY, "'}'", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    static final Token OPEN_SQUARE = Token.newWithoutOrigin(TokenType.OPEN_SQUARE, "'['", "[");
    static final Token CLOSE_SQUARE = Token.newWithoutOrigin(TokenType.CLOSE_SQUARE, "']'", "]");
    static final Token PLUS_EQUALS = Token.newWithoutOrigin(TokenType.PLUS_EQUALS, "'+='", "+=");

    /* loaded from: classes3.dex */
    private static abstract class Comment extends Token {
        private final String text;

        /* loaded from: classes3.dex */
        static final class DoubleSlashComment extends Comment {
            DoubleSlashComment(ConfigOrigin configOrigin, String str) {
                super(configOrigin, str);
            }

            @Override // com.typesafe.config.impl.Token
            public String tokenText() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("//");
                outline107.append(((Comment) this).text);
                return outline107.toString();
            }
        }

        /* loaded from: classes3.dex */
        static final class HashComment extends Comment {
            HashComment(ConfigOrigin configOrigin, String str) {
                super(configOrigin, str);
            }

            @Override // com.typesafe.config.impl.Token
            public String tokenText() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MqttTopic.MULTI_LEVEL_WILDCARD);
                outline107.append(((Comment) this).text);
                return outline107.toString();
            }
        }

        Comment(ConfigOrigin configOrigin, String str) {
            super(TokenType.COMMENT, configOrigin);
            this.text = str;
        }

        @Override // com.typesafe.config.impl.Token
        protected boolean canEqual(Object obj) {
            return obj instanceof Comment;
        }

        @Override // com.typesafe.config.impl.Token
        public boolean equals(Object obj) {
            return super.equals(obj) && ((Comment) obj).text.equals(this.text);
        }

        @Override // com.typesafe.config.impl.Token
        public int hashCode() {
            return GeneratedOutlineSupport1.outline7(this.text, (super.hashCode() + 41) * 41, 41);
        }

        String text() {
            return this.text;
        }

        @Override // com.typesafe.config.impl.Token
        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("'#"), this.text, "' (COMMENT)");
        }
    }

    /* loaded from: classes3.dex */
    private static class IgnoredWhitespace extends Token {
        private final String value;

        IgnoredWhitespace(ConfigOrigin configOrigin, String str) {
            super(TokenType.IGNORED_WHITESPACE, configOrigin);
            this.value = str;
        }

        @Override // com.typesafe.config.impl.Token
        protected boolean canEqual(Object obj) {
            return obj instanceof IgnoredWhitespace;
        }

        @Override // com.typesafe.config.impl.Token
        public boolean equals(Object obj) {
            return super.equals(obj) && ((IgnoredWhitespace) obj).value.equals(this.value);
        }

        @Override // com.typesafe.config.impl.Token
        public int hashCode() {
            return this.value.hashCode() + ((super.hashCode() + 41) * 41);
        }

        @Override // com.typesafe.config.impl.Token
        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("'"), this.value, "' (WHITESPACE)");
        }

        @Override // com.typesafe.config.impl.Token
        public String tokenText() {
            return this.value;
        }
    }

    /* loaded from: classes3.dex */
    private static class Line extends Token {
        Line(ConfigOrigin configOrigin) {
            super(TokenType.NEWLINE, configOrigin);
        }

        @Override // com.typesafe.config.impl.Token
        protected boolean canEqual(Object obj) {
            return obj instanceof Line;
        }

        @Override // com.typesafe.config.impl.Token
        public boolean equals(Object obj) {
            return super.equals(obj) && ((Line) obj).lineNumber() == lineNumber();
        }

        @Override // com.typesafe.config.impl.Token
        public int hashCode() {
            return ((super.hashCode() + 41) * 41) + lineNumber();
        }

        @Override // com.typesafe.config.impl.Token
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'\\n'@");
            outline107.append(lineNumber());
            return outline107.toString();
        }

        @Override // com.typesafe.config.impl.Token
        public String tokenText() {
            return "\n";
        }
    }

    /* loaded from: classes3.dex */
    private static class Problem extends Token {
        private final Throwable cause;
        private final String message;
        private final boolean suggestQuotes;
        private final String what;

        Problem(ConfigOrigin configOrigin, String str, String str2, boolean z, Throwable th) {
            super(TokenType.PROBLEM, configOrigin);
            this.what = str;
            this.message = str2;
            this.suggestQuotes = z;
            this.cause = th;
        }

        @Override // com.typesafe.config.impl.Token
        protected boolean canEqual(Object obj) {
            return obj instanceof Problem;
        }

        Throwable cause() {
            return this.cause;
        }

        @Override // com.typesafe.config.impl.Token
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                Problem problem = (Problem) obj;
                if (problem.what.equals(this.what) && problem.message.equals(this.message) && problem.suggestQuotes == this.suggestQuotes && ConfigImplUtil.equalsHandlingNull(problem.cause, this.cause)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.typesafe.config.impl.Token
        public int hashCode() {
            int hashCode = (Boolean.valueOf(this.suggestQuotes).hashCode() + GeneratedOutlineSupport1.outline7(this.message, GeneratedOutlineSupport1.outline7(this.what, (super.hashCode() + 41) * 41, 41), 41)) * 41;
            Throwable th = this.cause;
            return th != null ? (th.hashCode() + hashCode) * 41 : hashCode;
        }

        String message() {
            return this.message;
        }

        boolean suggestQuotes() {
            return this.suggestQuotes;
        }

        @Override // com.typesafe.config.impl.Token
        public String toString() {
            StringBuilder outline104 = GeneratedOutlineSupport1.outline104(Chars.QUOTE);
            GeneratedOutlineSupport1.outline176(outline104, this.what, Chars.QUOTE, " (");
            return GeneratedOutlineSupport1.outline91(outline104, this.message, ")");
        }

        String what() {
            return this.what;
        }
    }

    /* loaded from: classes3.dex */
    private static class Substitution extends Token {
        private final boolean optional;
        private final List<Token> value;

        Substitution(ConfigOrigin configOrigin, boolean z, List<Token> list) {
            super(TokenType.SUBSTITUTION, configOrigin);
            this.optional = z;
            this.value = list;
        }

        @Override // com.typesafe.config.impl.Token
        protected boolean canEqual(Object obj) {
            return obj instanceof Substitution;
        }

        @Override // com.typesafe.config.impl.Token
        public boolean equals(Object obj) {
            return super.equals(obj) && ((Substitution) obj).value.equals(this.value);
        }

        @Override // com.typesafe.config.impl.Token
        public int hashCode() {
            return this.value.hashCode() + ((super.hashCode() + 41) * 41);
        }

        boolean optional() {
            return this.optional;
        }

        @Override // com.typesafe.config.impl.Token
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Token token : this.value) {
                sb.append(token.toString());
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'${");
            outline107.append(sb.toString());
            outline107.append("}'");
            return outline107.toString();
        }

        @Override // com.typesafe.config.impl.Token
        public String tokenText() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("${");
            outline107.append(this.optional ? WebConstants.UriConstants.QUESTIONMARK_KEY : "");
            return GeneratedOutlineSupport1.outline91(outline107, Tokenizer.render(this.value.iterator()), EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        }

        List<Token> value() {
            return this.value;
        }
    }

    /* loaded from: classes3.dex */
    private static class UnquotedText extends Token {
        private final String value;

        UnquotedText(ConfigOrigin configOrigin, String str) {
            super(TokenType.UNQUOTED_TEXT, configOrigin);
            this.value = str;
        }

        @Override // com.typesafe.config.impl.Token
        protected boolean canEqual(Object obj) {
            return obj instanceof UnquotedText;
        }

        @Override // com.typesafe.config.impl.Token
        public boolean equals(Object obj) {
            return super.equals(obj) && ((UnquotedText) obj).value.equals(this.value);
        }

        @Override // com.typesafe.config.impl.Token
        public int hashCode() {
            return this.value.hashCode() + ((super.hashCode() + 41) * 41);
        }

        @Override // com.typesafe.config.impl.Token
        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("'"), this.value, "'");
        }

        @Override // com.typesafe.config.impl.Token
        public String tokenText() {
            return this.value;
        }

        String value() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class Value extends Token {
        private final AbstractConfigValue value;

        Value(AbstractConfigValue abstractConfigValue) {
            this(abstractConfigValue, null);
        }

        @Override // com.typesafe.config.impl.Token
        protected boolean canEqual(Object obj) {
            return obj instanceof Value;
        }

        @Override // com.typesafe.config.impl.Token
        public boolean equals(Object obj) {
            return super.equals(obj) && ((Value) obj).value.equals(this.value);
        }

        @Override // com.typesafe.config.impl.Token
        public int hashCode() {
            return ((super.hashCode() + 41) * 41) + this.value.hashCode();
        }

        @Override // com.typesafe.config.impl.Token
        public String toString() {
            if (value().resolveStatus() == ResolveStatus.RESOLVED) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'");
                outline107.append(value().mo10253unwrapped());
                outline107.append("' (");
                outline107.append(this.value.valueType().name());
                outline107.append(")");
                return outline107.toString();
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("'<unresolved value>' (");
            outline1072.append(this.value.valueType().name());
            outline1072.append(")");
            return outline1072.toString();
        }

        AbstractConfigValue value() {
            return this.value;
        }

        Value(AbstractConfigValue abstractConfigValue, String str) {
            super(TokenType.VALUE, abstractConfigValue.mo10176origin(), str);
            this.value = abstractConfigValue;
        }
    }

    Tokens() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getCommentText(Token token) {
        if (token instanceof Comment) {
            return ((Comment) token).text();
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get comment text from ", token));
    }

    static Throwable getProblemCause(Token token) {
        if (token instanceof Problem) {
            return ((Problem) token).cause();
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get problem cause from ", token));
    }

    static String getProblemMessage(Token token) {
        if (token instanceof Problem) {
            return ((Problem) token).message();
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get problem message from ", token));
    }

    static boolean getProblemSuggestQuotes(Token token) {
        if (token instanceof Problem) {
            return ((Problem) token).suggestQuotes();
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get problem suggestQuotes from ", token));
    }

    static String getProblemWhat(Token token) {
        if (token instanceof Problem) {
            return ((Problem) token).what();
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get problem what from ", token));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean getSubstitutionOptional(Token token) {
        if (token instanceof Substitution) {
            return ((Substitution) token).optional();
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get substitution optionality from ", token));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Token> getSubstitutionPathExpression(Token token) {
        if (token instanceof Substitution) {
            return ((Substitution) token).value();
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get substitution from ", token));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getUnquotedText(Token token) {
        if (token instanceof UnquotedText) {
            return ((UnquotedText) token).value();
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get unquoted text from ", token));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigValue getValue(Token token) {
        if (token instanceof Value) {
            return ((Value) token).value();
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get value of non-value token ", token));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isComment(Token token) {
        return token instanceof Comment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isIgnoredWhitespace(Token token) {
        return token instanceof IgnoredWhitespace;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isNewline(Token token) {
        return token instanceof Line;
    }

    static boolean isProblem(Token token) {
        return token instanceof Problem;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSubstitution(Token token) {
        return token instanceof Substitution;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isUnquotedText(Token token) {
        return token instanceof UnquotedText;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValue(Token token) {
        return token instanceof Value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValueWithType(Token token, ConfigValueType configValueType) {
        return isValue(token) && getValue(token).valueType() == configValueType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newBoolean(ConfigOrigin configOrigin, boolean z) {
        ConfigBoolean configBoolean = new ConfigBoolean(configOrigin, z);
        return newValue(configBoolean, "" + z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newCommentDoubleSlash(ConfigOrigin configOrigin, String str) {
        return new Comment.DoubleSlashComment(configOrigin, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newCommentHash(ConfigOrigin configOrigin, String str) {
        return new Comment.HashComment(configOrigin, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newDouble(ConfigOrigin configOrigin, double d, String str) {
        return newValue(ConfigNumber.newNumber(configOrigin, d, str), str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newIgnoredWhitespace(ConfigOrigin configOrigin, String str) {
        return new IgnoredWhitespace(configOrigin, str);
    }

    static Token newInt(ConfigOrigin configOrigin, int i, String str) {
        return newValue(ConfigNumber.newNumber(configOrigin, i, str), str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newLine(ConfigOrigin configOrigin) {
        return new Line(configOrigin);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newLong(ConfigOrigin configOrigin, long j, String str) {
        return newValue(ConfigNumber.newNumber(configOrigin, j, str), str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newNull(ConfigOrigin configOrigin) {
        return newValue(new ConfigNull(configOrigin), "null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newProblem(ConfigOrigin configOrigin, String str, String str2, boolean z, Throwable th) {
        return new Problem(configOrigin, str, str2, z, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newString(ConfigOrigin configOrigin, String str, String str2) {
        return newValue(new ConfigString.Quoted(configOrigin, str), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newSubstitution(ConfigOrigin configOrigin, boolean z, List<Token> list) {
        return new Substitution(configOrigin, z, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newUnquotedText(ConfigOrigin configOrigin, String str) {
        return new UnquotedText(configOrigin, str);
    }

    static Token newValue(AbstractConfigValue abstractConfigValue) {
        return new Value(abstractConfigValue);
    }

    static Token newValue(AbstractConfigValue abstractConfigValue, String str) {
        return new Value(abstractConfigValue, str);
    }
}
