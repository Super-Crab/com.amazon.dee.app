package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class Token {
    private final String debugString;
    private final ConfigOrigin origin;
    private final String tokenText;
    private final TokenType tokenType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token(TokenType tokenType, ConfigOrigin configOrigin) {
        this(tokenType, configOrigin, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token newWithoutOrigin(TokenType tokenType, String str, String str2) {
        return new Token(tokenType, null, str2, str);
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof Token;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Token) && canEqual(obj) && this.tokenType == ((Token) obj).tokenType;
    }

    public int hashCode() {
        return this.tokenType.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int lineNumber() {
        ConfigOrigin configOrigin = this.origin;
        if (configOrigin != null) {
            return configOrigin.lineNumber();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ConfigOrigin origin() {
        ConfigOrigin configOrigin = this.origin;
        if (configOrigin != null) {
            return configOrigin;
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline62("tried to get origin from token that doesn't have one: ", this));
    }

    public String toString() {
        String str = this.debugString;
        return str != null ? str : this.tokenType.name();
    }

    public String tokenText() {
        return this.tokenText;
    }

    final TokenType tokenType() {
        return this.tokenType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token(TokenType tokenType, ConfigOrigin configOrigin, String str) {
        this(tokenType, configOrigin, str, null);
    }

    Token(TokenType tokenType, ConfigOrigin configOrigin, String str, String str2) {
        this.tokenType = tokenType;
        this.origin = configOrigin;
        this.debugString = str2;
        this.tokenText = str;
    }
}
