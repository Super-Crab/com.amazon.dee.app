package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.impl.ConfigString;
import java.util.Collection;
import java.util.Collections;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigNodeSimpleValue extends AbstractConfigNodeValue {
    final Token token;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNodeSimpleValue(Token token) {
        this.token = token;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Token token() {
        return this.token;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigNode
    public Collection<Token> tokens() {
        return Collections.singletonList(this.token);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractConfigValue value() {
        if (Tokens.isValue(this.token)) {
            return Tokens.getValue(this.token);
        }
        if (Tokens.isUnquotedText(this.token)) {
            return new ConfigString.Unquoted(this.token.origin(), Tokens.getUnquotedText(this.token));
        }
        if (Tokens.isSubstitution(this.token)) {
            return new ConfigReference(this.token.origin(), new SubstitutionExpression(PathParser.parsePathExpression(Tokens.getSubstitutionPathExpression(this.token).iterator(), this.token.origin()), Tokens.getSubstitutionOptional(this.token)));
        }
        throw new ConfigException.BugOrBroken("ConfigNodeSimpleValue did not contain a valid value token");
    }
}
