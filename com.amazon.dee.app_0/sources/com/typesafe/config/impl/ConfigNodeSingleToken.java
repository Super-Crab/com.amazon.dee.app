package com.typesafe.config.impl;

import java.util.Collection;
import java.util.Collections;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ConfigNodeSingleToken extends AbstractConfigNode {
    final Token token;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNodeSingleToken(Token token) {
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
}
