package com.amazon.alexa.delegatedidentity.model;

import java.util.Objects;
/* loaded from: classes6.dex */
public final class Token {
    private final Long expiry;
    private final TokenType type;
    private final String value;

    /* loaded from: classes6.dex */
    public static final class Builder {
        private Long expiry;
        private TokenType type;
        private String value;

        public Token build() {
            return new Token(this);
        }

        public Builder withExpiry(Long l) {
            this.expiry = l;
            return this;
        }

        public Builder withType(TokenType tokenType) {
            this.type = tokenType;
            return this;
        }

        public Builder withValue(String str) {
            this.value = str;
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public enum TokenType {
        ACTOR,
        DELEGATION
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Token.class != obj.getClass()) {
            return false;
        }
        Token token = (Token) obj;
        return this.value.equals(token.value) && this.type == token.type;
    }

    public Long getExpiry() {
        return this.expiry;
    }

    public TokenType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return Objects.hash(this.value, this.type, this.expiry);
    }

    private Token(Builder builder) {
        this.value = builder.value;
        this.type = builder.type;
        this.expiry = builder.expiry;
    }
}
