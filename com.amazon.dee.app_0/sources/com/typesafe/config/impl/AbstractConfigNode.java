package com.typesafe.config.impl;

import com.typesafe.config.parser.ConfigNode;
import java.util.Collection;
/* loaded from: classes3.dex */
abstract class AbstractConfigNode implements ConfigNode {
    public final boolean equals(Object obj) {
        return (obj instanceof AbstractConfigNode) && render().equals(((AbstractConfigNode) obj).render());
    }

    public final int hashCode() {
        return render().hashCode();
    }

    @Override // com.typesafe.config.parser.ConfigNode
    public final String render() {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens()) {
            sb.append(token.tokenText());
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Collection<Token> tokens();
}
