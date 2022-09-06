package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigNodeInclude extends AbstractConfigNode {
    private final ArrayList<AbstractConfigNode> children;
    private final boolean isRequired;
    private final ConfigIncludeKind kind;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNodeInclude(Collection<AbstractConfigNode> collection, ConfigIncludeKind configIncludeKind, boolean z) {
        this.children = new ArrayList<>(collection);
        this.kind = configIncludeKind;
        this.isRequired = z;
    }

    public final Collection<AbstractConfigNode> children() {
        return this.children;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isRequired() {
        return this.isRequired;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConfigIncludeKind kind() {
        return this.kind;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String name() {
        Iterator<AbstractConfigNode> it2 = this.children.iterator();
        while (it2.hasNext()) {
            AbstractConfigNode next = it2.next();
            if (next instanceof ConfigNodeSimpleValue) {
                return (String) Tokens.getValue(((ConfigNodeSimpleValue) next).token()).mo10253unwrapped();
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
}
