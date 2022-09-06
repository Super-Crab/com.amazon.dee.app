package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes3.dex */
abstract class ConfigNodeComplexValue extends AbstractConfigNodeValue {
    protected final ArrayList<AbstractConfigNode> children;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNodeComplexValue(Collection<AbstractConfigNode> collection) {
        this.children = new ArrayList<>(collection);
    }

    public final Collection<AbstractConfigNode> children() {
        return this.children;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConfigNodeComplexValue indentText(AbstractConfigNode abstractConfigNode) {
        ArrayList arrayList = new ArrayList(this.children);
        int i = 0;
        while (i < arrayList.size()) {
            AbstractConfigNode abstractConfigNode2 = (AbstractConfigNode) arrayList.get(i);
            if ((abstractConfigNode2 instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) abstractConfigNode2).token())) {
                i++;
                arrayList.add(i, abstractConfigNode);
            } else if (abstractConfigNode2 instanceof ConfigNodeField) {
                ConfigNodeField configNodeField = (ConfigNodeField) abstractConfigNode2;
                AbstractConfigNodeValue value = configNodeField.value();
                if (value instanceof ConfigNodeComplexValue) {
                    arrayList.set(i, configNodeField.replaceValue(((ConfigNodeComplexValue) value).indentText(abstractConfigNode)));
                }
            } else if (abstractConfigNode2 instanceof ConfigNodeComplexValue) {
                arrayList.set(i, ((ConfigNodeComplexValue) abstractConfigNode2).indentText(abstractConfigNode));
            }
            i++;
        }
        return mo10213newNode(arrayList);
    }

    /* renamed from: newNode */
    abstract ConfigNodeComplexValue mo10213newNode(Collection<AbstractConfigNode> collection);

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
