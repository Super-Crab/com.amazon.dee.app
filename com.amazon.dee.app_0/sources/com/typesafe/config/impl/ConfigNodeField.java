package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes3.dex */
final class ConfigNodeField extends AbstractConfigNode {
    private final ArrayList<AbstractConfigNode> children;

    public ConfigNodeField(Collection<AbstractConfigNode> collection) {
        this.children = new ArrayList<>(collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> comments() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractConfigNode> it2 = this.children.iterator();
        while (it2.hasNext()) {
            AbstractConfigNode next = it2.next();
            if (next instanceof ConfigNodeComment) {
                arrayList.add(((ConfigNodeComment) next).commentText());
            }
        }
        return arrayList;
    }

    public ConfigNodePath path() {
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i) instanceof ConfigNodePath) {
                return (ConfigNodePath) this.children.get(i);
            }
        }
        throw new ConfigException.BugOrBroken("Field node doesn't have a path");
    }

    public ConfigNodeField replaceValue(AbstractConfigNodeValue abstractConfigNodeValue) {
        ArrayList arrayList = new ArrayList(this.children);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof AbstractConfigNodeValue) {
                arrayList.set(i, abstractConfigNodeValue);
                return new ConfigNodeField(arrayList);
            }
        }
        throw new ConfigException.BugOrBroken("Field node doesn't have a value");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Token separator() {
        Token token;
        Iterator<AbstractConfigNode> it2 = this.children.iterator();
        while (it2.hasNext()) {
            AbstractConfigNode next = it2.next();
            if ((next instanceof ConfigNodeSingleToken) && ((token = ((ConfigNodeSingleToken) next).token()) == Tokens.PLUS_EQUALS || token == Tokens.COLON || token == Tokens.EQUALS)) {
                return token;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigNode
    public Collection<Token> tokens() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractConfigNode> it2 = this.children.iterator();
        while (it2.hasNext()) {
            arrayList.addAll(it2.next().tokens());
        }
        return arrayList;
    }

    public AbstractConfigNodeValue value() {
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i) instanceof AbstractConfigNodeValue) {
                return (AbstractConfigNodeValue) this.children.get(i);
            }
        }
        throw new ConfigException.BugOrBroken("Field node doesn't have a value");
    }
}
