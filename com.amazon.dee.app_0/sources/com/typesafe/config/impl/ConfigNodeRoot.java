package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigSyntax;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigNodeRoot extends ConfigNodeComplexValue {
    private final ConfigOrigin origin;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNodeRoot(Collection<AbstractConfigNode> collection, ConfigOrigin configOrigin) {
        super(collection);
        this.origin = configOrigin;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasValue(String str) {
        Path parsePath = PathParser.parsePath(str);
        ArrayList arrayList = new ArrayList(this.children);
        for (int i = 0; i < arrayList.size(); i++) {
            AbstractConfigNode abstractConfigNode = (AbstractConfigNode) arrayList.get(i);
            if (abstractConfigNode instanceof ConfigNodeComplexValue) {
                if (!(abstractConfigNode instanceof ConfigNodeArray)) {
                    if (abstractConfigNode instanceof ConfigNodeObject) {
                        return ((ConfigNodeObject) abstractConfigNode).hasValue(parsePath);
                    }
                } else {
                    throw new ConfigException.WrongType(this.origin, "The ConfigDocument had an array at the root level, and values cannot be modified inside an array.");
                }
            }
        }
        throw new ConfigException.BugOrBroken("ConfigNodeRoot did not contain a value");
    }

    @Override // com.typesafe.config.impl.ConfigNodeComplexValue
    /* renamed from: newNode */
    protected /* bridge */ /* synthetic */ ConfigNodeComplexValue mo10213newNode(Collection collection) {
        return mo10213newNode((Collection<AbstractConfigNode>) collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConfigNodeRoot setValue(String str, AbstractConfigNodeValue abstractConfigNodeValue, ConfigSyntax configSyntax) {
        ArrayList arrayList = new ArrayList(this.children);
        for (int i = 0; i < arrayList.size(); i++) {
            AbstractConfigNode abstractConfigNode = (AbstractConfigNode) arrayList.get(i);
            if (abstractConfigNode instanceof ConfigNodeComplexValue) {
                if (!(abstractConfigNode instanceof ConfigNodeArray)) {
                    if (abstractConfigNode instanceof ConfigNodeObject) {
                        if (abstractConfigNodeValue == null) {
                            arrayList.set(i, ((ConfigNodeObject) abstractConfigNode).removeValueOnPath(str, configSyntax));
                        } else {
                            arrayList.set(i, ((ConfigNodeObject) abstractConfigNode).setValueOnPath(str, abstractConfigNodeValue, configSyntax));
                        }
                        return new ConfigNodeRoot(arrayList, this.origin);
                    }
                } else {
                    throw new ConfigException.WrongType(this.origin, "The ConfigDocument had an array at the root level, and values cannot be modified inside an array.");
                }
            }
        }
        throw new ConfigException.BugOrBroken("ConfigNodeRoot did not contain a value");
    }

    protected ConfigNodeComplexValue value() {
        Iterator<AbstractConfigNode> it2 = this.children.iterator();
        while (it2.hasNext()) {
            AbstractConfigNode next = it2.next();
            if (next instanceof ConfigNodeComplexValue) {
                return (ConfigNodeComplexValue) next;
            }
        }
        throw new ConfigException.BugOrBroken("ConfigNodeRoot did not contain a value");
    }

    @Override // com.typesafe.config.impl.ConfigNodeComplexValue
    /* renamed from: newNode  reason: collision with other method in class */
    protected ConfigNodeRoot mo10213newNode(Collection<AbstractConfigNode> collection) {
        throw new ConfigException.BugOrBroken("Tried to indent the root object");
    }
}
