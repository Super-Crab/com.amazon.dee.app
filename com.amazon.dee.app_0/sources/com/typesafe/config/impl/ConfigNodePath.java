package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Collection;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigNodePath extends AbstractConfigNode {
    private final Path path;
    final ArrayList<Token> tokens;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNodePath(Path path, Collection<Token> collection) {
        this.path = path;
        this.tokens = new ArrayList<>(collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConfigNodePath first() {
        ArrayList arrayList = new ArrayList(this.tokens);
        for (int i = 0; i < arrayList.size(); i++) {
            if (Tokens.isUnquotedText((Token) arrayList.get(i)) && ((Token) arrayList.get(i)).tokenText().equals(".")) {
                return new ConfigNodePath(this.path.subPath(0, 1), arrayList.subList(0, i));
            }
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConfigNodePath subPath(int i) {
        ArrayList arrayList = new ArrayList(this.tokens);
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (Tokens.isUnquotedText((Token) arrayList.get(i3)) && ((Token) arrayList.get(i3)).tokenText().equals(".")) {
                i2++;
            }
            if (i2 == i) {
                return new ConfigNodePath(this.path.subPath(i), arrayList.subList(i3 + 1, arrayList.size()));
            }
        }
        throw new ConfigException.BugOrBroken("Tried to remove too many elements from a Path node");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigNode
    public Collection<Token> tokens() {
        return this.tokens;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Path value() {
        return this.path;
    }
}
