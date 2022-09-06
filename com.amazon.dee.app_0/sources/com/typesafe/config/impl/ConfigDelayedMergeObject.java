package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.impl.AbstractConfigValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigDelayedMergeObject extends AbstractConfigObject implements Unmergeable, ReplaceableMergeStack {
    private final List<AbstractConfigValue> stack;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ConfigDelayedMergeObject(com.typesafe.config.ConfigOrigin r2, java.util.List<com.typesafe.config.impl.AbstractConfigValue> r3) {
        /*
            r1 = this;
            r1.<init>(r2)
            r1.stack = r3
            boolean r2 = r3.isEmpty()
            if (r2 != 0) goto L3e
            r2 = 0
            java.lang.Object r2 = r3.get(r2)
            boolean r2 = r2 instanceof com.typesafe.config.impl.AbstractConfigObject
            if (r2 == 0) goto L36
            java.util.Iterator r2 = r3.iterator()
        L18:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L35
            java.lang.Object r3 = r2.next()
            com.typesafe.config.impl.AbstractConfigValue r3 = (com.typesafe.config.impl.AbstractConfigValue) r3
            boolean r0 = r3 instanceof com.typesafe.config.impl.ConfigDelayedMerge
            if (r0 != 0) goto L2d
            boolean r3 = r3 instanceof com.typesafe.config.impl.ConfigDelayedMergeObject
            if (r3 != 0) goto L2d
            goto L18
        L2d:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "placed nested DelayedMerge in a ConfigDelayedMergeObject, should have consolidated stack"
            r2.<init>(r3)
            throw r2
        L35:
            return
        L36:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "created a delayed merge object not guaranteed to be an object"
            r2.<init>(r3)
            throw r2
        L3e:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "creating empty delayed merge object"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.ConfigDelayedMergeObject.<init>(com.typesafe.config.ConfigOrigin, java.util.List):void");
    }

    private static ConfigException notResolved() {
        return new ConfigException.NotResolved("need to Config#resolve() before using this object, see the API docs for Config#resolve()");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    public AbstractConfigValue attemptPeekWithPartialResolve(String str) {
        for (AbstractConfigValue abstractConfigValue : this.stack) {
            if (abstractConfigValue instanceof AbstractConfigObject) {
                AbstractConfigValue attemptPeekWithPartialResolve = ((AbstractConfigObject) abstractConfigValue).attemptPeekWithPartialResolve(str);
                if (attemptPeekWithPartialResolve != null) {
                    if (attemptPeekWithPartialResolve.ignoresFallbacks()) {
                        return attemptPeekWithPartialResolve;
                    }
                } else if (abstractConfigValue instanceof Unmergeable) {
                    throw new ConfigException.BugOrBroken("should not be reached: unmergeable object returned null value");
                }
            } else if (!(abstractConfigValue instanceof Unmergeable)) {
                if (abstractConfigValue.resolveStatus() == ResolveStatus.UNRESOLVED) {
                    if (abstractConfigValue instanceof ConfigList) {
                        return null;
                    }
                    throw new ConfigException.BugOrBroken("Expecting a list here, not " + abstractConfigValue);
                } else if (abstractConfigValue.ignoresFallbacks()) {
                    return null;
                } else {
                    throw new ConfigException.BugOrBroken("resolved non-object should ignore fallbacks");
                }
            } else {
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Key '", str, "' is not available at '");
                outline115.append(mo10176origin().description());
                outline115.append("' because value at '");
                throw new ConfigException.NotResolved(GeneratedOutlineSupport1.outline93(outline115, abstractConfigValue.mo10176origin().description(), "' has not been resolved and may turn out to contain or hide '", str, "'. Be sure to Config#resolve() before using a config object."));
            }
        }
        throw new ConfigException.BugOrBroken("Delayed merge stack does not contain any unmergeable values");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigDelayedMergeObject;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        throw notResolved();
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        throw notResolved();
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, ConfigValue>> entrySet() {
        throw notResolved();
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigDelayedMergeObject) || !canEqual(obj)) {
            return false;
        }
        List<AbstractConfigValue> list = this.stack;
        List<AbstractConfigValue> list2 = ((ConfigDelayedMergeObject) obj).stack;
        return list == list2 || list.equals(list2);
    }

    @Override // com.typesafe.config.impl.Container
    public boolean hasDescendant(AbstractConfigValue abstractConfigValue) {
        return AbstractConfigValue.hasDescendantInList(this.stack, abstractConfigValue);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public int hashCode() {
        return this.stack.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean ignoresFallbacks() {
        return ConfigDelayedMerge.stackIgnoresFallbacks(this.stack);
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        throw notResolved();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        throw notResolved();
    }

    @Override // com.typesafe.config.impl.ReplaceableMergeStack
    public AbstractConfigValue makeReplacement(ResolveContext resolveContext, int i) {
        return ConfigDelayedMerge.makeReplacement(resolveContext, this.stack, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public void render(StringBuilder sb, int i, boolean z, String str, ConfigRenderOptions configRenderOptions) {
        ConfigDelayedMerge.render(this.stack, sb, i, z, str, configRenderOptions);
    }

    @Override // com.typesafe.config.impl.Container
    /* renamed from: replaceChild */
    public AbstractConfigValue mo10252replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        List<AbstractConfigValue> replaceChildInList = AbstractConfigValue.replaceChildInList(this.stack, abstractConfigValue, abstractConfigValue2);
        if (replaceChildInList == null) {
            return null;
        }
        return new ConfigDelayedMergeObject(mo10176origin(), replaceChildInList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public ResolveStatus resolveStatus() {
        return ResolveStatus.UNRESOLVED;
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.impl.AbstractConfigValue
    ResolveResult<? extends AbstractConfigObject> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        return ConfigDelayedMerge.resolveSubstitutions(this, this.stack, resolveContext, resolveSource).asObjectResult();
    }

    @Override // java.util.Map
    public int size() {
        throw notResolved();
    }

    @Override // com.typesafe.config.impl.Unmergeable
    public Collection<AbstractConfigValue> unmergedValues() {
        return this.stack;
    }

    @Override // java.util.Map
    public Collection<ConfigValue> values() {
        throw notResolved();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: withOnlyPath */
    public AbstractConfigObject mo10256withOnlyPath(Path path) {
        throw notResolved();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: withOnlyPathOrNull */
    public AbstractConfigObject mo10257withOnlyPathOrNull(Path path) {
        throw notResolved();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: withoutPath */
    public AbstractConfigObject mo10261withoutPath(Path path) {
        throw notResolved();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: mergedWithNonObject  reason: collision with other method in class */
    public final ConfigDelayedMergeObject mo10190mergedWithNonObject(AbstractConfigValue abstractConfigValue) {
        requireNotIgnoringFallbacks();
        return (ConfigDelayedMergeObject) mergedWithNonObject(this.stack, abstractConfigValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: mergedWithTheUnmergeable  reason: collision with other method in class */
    public final ConfigDelayedMergeObject mo10192mergedWithTheUnmergeable(Unmergeable unmergeable) {
        requireNotIgnoringFallbacks();
        return (ConfigDelayedMergeObject) mergedWithTheUnmergeable(this.stack, unmergeable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: newCopy  reason: collision with other method in class */
    public ConfigDelayedMergeObject mo10250newCopy(ResolveStatus resolveStatus, ConfigOrigin configOrigin) {
        if (resolveStatus == resolveStatus()) {
            return new ConfigDelayedMergeObject(configOrigin, this.stack);
        }
        throw new ConfigException.BugOrBroken("attempt to create resolved ConfigDelayedMergeObject");
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.impl.AbstractConfigValue
    protected void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        render(sb, i, z, null, configRenderOptions);
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped  reason: collision with other method in class */
    public Map<String, Object> mo10253unwrapped() {
        throw notResolved();
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.ConfigObject, java.util.Map
    /* renamed from: get  reason: collision with other method in class */
    public ConfigValue mo10248get(Object obj) {
        throw notResolved();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: mergedWithObject */
    public final ConfigDelayedMergeObject mo10249mergedWithObject(AbstractConfigObject abstractConfigObject) {
        return mo10190mergedWithNonObject((AbstractConfigValue) abstractConfigObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: relativized */
    public ConfigDelayedMergeObject mo10251relativized(Path path) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue abstractConfigValue : this.stack) {
            arrayList.add(abstractConfigValue.mo10251relativized(path));
        }
        return new ConfigDelayedMergeObject(mo10176origin(), arrayList);
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.ConfigObject
    /* renamed from: withOnlyKey */
    public ConfigDelayedMergeObject mo10255withOnlyKey(String str) {
        throw notResolved();
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.ConfigObject
    /* renamed from: withoutKey */
    public ConfigDelayedMergeObject mo10260withoutKey(String str) {
        throw notResolved();
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.ConfigObject
    /* renamed from: withValue */
    public ConfigDelayedMergeObject mo10258withValue(String str, ConfigValue configValue) {
        throw notResolved();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: withValue  reason: collision with other method in class */
    public ConfigDelayedMergeObject mo10259withValue(Path path, ConfigValue configValue) {
        throw notResolved();
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.impl.AbstractConfigValue, com.typesafe.config.ConfigValue, com.typesafe.config.ConfigMergeable
    /* renamed from: withFallback  reason: collision with other method in class */
    public ConfigDelayedMergeObject mo10234withFallback(ConfigMergeable configMergeable) {
        return (ConfigDelayedMergeObject) super.mo10234withFallback(configMergeable);
    }
}
