package com.typesafe.config.impl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public interface ReplaceableMergeStack extends Container {
    AbstractConfigValue makeReplacement(ResolveContext resolveContext, int i);
}
