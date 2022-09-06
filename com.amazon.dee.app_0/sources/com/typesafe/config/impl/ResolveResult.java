package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.impl.AbstractConfigValue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ResolveResult<V extends AbstractConfigValue> {
    public final ResolveContext context;
    public final V value;

    private ResolveResult(ResolveContext resolveContext, V v) {
        this.context = resolveContext;
        this.value = v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <V extends AbstractConfigValue> ResolveResult<V> make(ResolveContext resolveContext, V v) {
        return new ResolveResult<>(resolveContext, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public ResolveResult<AbstractConfigObject> asObjectResult() {
        if (this.value instanceof AbstractConfigObject) {
            return this;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expecting a resolve result to be an object, but it was ");
        outline107.append(this.value);
        throw new ConfigException.BugOrBroken(outline107.toString());
    }

    ResolveResult<AbstractConfigValue> asValueResult() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveResult<V> popTrace() {
        return make(this.context.popTrace(), this.value);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ResolveResult(");
        outline107.append(this.value);
        outline107.append(")");
        return outline107.toString();
    }
}
