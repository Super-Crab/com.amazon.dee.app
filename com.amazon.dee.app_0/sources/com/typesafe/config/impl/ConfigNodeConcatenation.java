package com.typesafe.config.impl;

import java.util.Collection;
/* loaded from: classes3.dex */
final class ConfigNodeConcatenation extends ConfigNodeComplexValue {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNodeConcatenation(Collection<AbstractConfigNode> collection) {
        super(collection);
    }

    @Override // com.typesafe.config.impl.ConfigNodeComplexValue
    /* renamed from: newNode */
    protected /* bridge */ /* synthetic */ ConfigNodeComplexValue mo10213newNode(Collection collection) {
        return mo10213newNode((Collection<AbstractConfigNode>) collection);
    }

    @Override // com.typesafe.config.impl.ConfigNodeComplexValue
    /* renamed from: newNode  reason: collision with other method in class */
    protected ConfigNodeConcatenation mo10213newNode(Collection<AbstractConfigNode> collection) {
        return new ConfigNodeConcatenation(collection);
    }
}
