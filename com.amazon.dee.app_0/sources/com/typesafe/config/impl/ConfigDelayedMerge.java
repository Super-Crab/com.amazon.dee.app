package com.typesafe.config.impl;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes3.dex */
final class ConfigDelayedMerge extends AbstractConfigValue implements Unmergeable, ReplaceableMergeStack {
    private final List<AbstractConfigValue> stack;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ConfigDelayedMerge(com.typesafe.config.ConfigOrigin r2, java.util.List<com.typesafe.config.impl.AbstractConfigValue> r3) {
        /*
            r1 = this;
            r1.<init>(r2)
            r1.stack = r3
            boolean r2 = r3.isEmpty()
            if (r2 != 0) goto L2d
            java.util.Iterator r2 = r3.iterator()
        Lf:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L2c
            java.lang.Object r3 = r2.next()
            com.typesafe.config.impl.AbstractConfigValue r3 = (com.typesafe.config.impl.AbstractConfigValue) r3
            boolean r0 = r3 instanceof com.typesafe.config.impl.ConfigDelayedMerge
            if (r0 != 0) goto L24
            boolean r3 = r3 instanceof com.typesafe.config.impl.ConfigDelayedMergeObject
            if (r3 != 0) goto L24
            goto Lf
        L24:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "placed nested DelayedMerge in a ConfigDelayedMerge, should have consolidated stack"
            r2.<init>(r3)
            throw r2
        L2c:
            return
        L2d:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "creating empty delayed merge value"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.ConfigDelayedMerge.<init>(com.typesafe.config.ConfigOrigin, java.util.List):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean stackIgnoresFallbacks(List<AbstractConfigValue> list) {
        return ((AbstractConfigValue) GeneratedOutlineSupport1.outline24(list, -1)).ignoresFallbacks();
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    protected boolean canEqual(Object obj) {
        return obj instanceof ConfigDelayedMerge;
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigDelayedMerge) || !canEqual(obj)) {
            return false;
        }
        List<AbstractConfigValue> list = this.stack;
        List<AbstractConfigValue> list2 = ((ConfigDelayedMerge) obj).stack;
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

    @Override // com.typesafe.config.impl.AbstractConfigValue
    protected boolean ignoresFallbacks() {
        return stackIgnoresFallbacks(this.stack);
    }

    @Override // com.typesafe.config.impl.ReplaceableMergeStack
    public AbstractConfigValue makeReplacement(ResolveContext resolveContext, int i) {
        return makeReplacement(resolveContext, this.stack, i);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy */
    protected AbstractConfigValue mo10238newCopy(ConfigOrigin configOrigin) {
        return new ConfigDelayedMerge(configOrigin, this.stack);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    protected void render(StringBuilder sb, int i, boolean z, String str, ConfigRenderOptions configRenderOptions) {
        render(this.stack, sb, i, z, str, configRenderOptions);
    }

    @Override // com.typesafe.config.impl.Container
    /* renamed from: replaceChild */
    public AbstractConfigValue mo10252replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        List<AbstractConfigValue> replaceChildInList = AbstractConfigValue.replaceChildInList(this.stack, abstractConfigValue, abstractConfigValue2);
        if (replaceChildInList == null) {
            return null;
        }
        return new ConfigDelayedMerge(mo10176origin(), replaceChildInList);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    ResolveStatus resolveStatus() {
        return ResolveStatus.UNRESOLVED;
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        return resolveSubstitutions(this, this.stack, resolveContext, resolveSource);
    }

    @Override // com.typesafe.config.impl.Unmergeable
    public Collection<AbstractConfigValue> unmergedValues() {
        return this.stack;
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped */
    public Object mo10253unwrapped() {
        throw new ConfigException.NotResolved("called unwrapped() on value with unresolved substitutions, need to Config#resolve() first, see API docs");
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        throw new ConfigException.NotResolved("called valueType() on value with unresolved substitutions, need to Config#resolve() first, see API docs");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigValue makeReplacement(ResolveContext resolveContext, List<AbstractConfigValue> list, int i) {
        List<AbstractConfigValue> subList = list.subList(i, list.size());
        AbstractConfigValue abstractConfigValue = null;
        if (subList.isEmpty()) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                ConfigImpl.trace(resolveContext.depth(), "Nothing else in the merge stack, replacing with null");
            }
            return null;
        }
        for (AbstractConfigValue abstractConfigValue2 : subList) {
            if (abstractConfigValue != null) {
                abstractConfigValue2 = abstractConfigValue.mo10234withFallback((ConfigMergeable) abstractConfigValue2);
            }
            abstractConfigValue = abstractConfigValue2;
        }
        return abstractConfigValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ReplaceableMergeStack replaceableMergeStack, List<AbstractConfigValue> list, ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        ResolveSource pushParent;
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = resolveContext.depth();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("delayed merge stack has ");
            outline107.append(list.size());
            outline107.append(" items:");
            ConfigImpl.trace(depth, outline107.toString());
            Iterator<AbstractConfigValue> it2 = list.iterator();
            int i = 0;
            while (it2.hasNext()) {
                ConfigImpl.trace(resolveContext.depth() + 1, i + RealTimeTextConstants.COLON_SPACE + it2.next());
                i++;
            }
        }
        AbstractConfigValue abstractConfigValue = null;
        int i2 = 0;
        ResolveContext resolveContext2 = resolveContext;
        for (AbstractConfigValue abstractConfigValue2 : list) {
            if (!(abstractConfigValue2 instanceof ReplaceableMergeStack)) {
                if (abstractConfigValue2 instanceof Unmergeable) {
                    AbstractConfigValue makeReplacement = replaceableMergeStack.makeReplacement(resolveContext, i2 + 1);
                    if (ConfigImpl.traceSubstitutionsEnabled()) {
                        ConfigImpl.trace(resolveContext2.depth(), "remainder portion: " + makeReplacement);
                    }
                    if (ConfigImpl.traceSubstitutionsEnabled()) {
                        ConfigImpl.trace(resolveContext2.depth(), "building sourceForEnd");
                    }
                    ResolveSource replaceWithinCurrentParent = resolveSource.replaceWithinCurrentParent((AbstractConfigValue) replaceableMergeStack, makeReplacement);
                    if (ConfigImpl.traceSubstitutionsEnabled()) {
                        ConfigImpl.trace(resolveContext2.depth(), "  sourceForEnd before reset parents but after replace: " + replaceWithinCurrentParent);
                    }
                    pushParent = replaceWithinCurrentParent.resetParents();
                } else {
                    if (ConfigImpl.traceSubstitutionsEnabled()) {
                        ConfigImpl.trace(resolveContext2.depth(), "will resolve end against the original source with parent pushed");
                    }
                    pushParent = resolveSource.pushParent(replaceableMergeStack);
                }
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    ConfigImpl.trace(resolveContext2.depth(), "sourceForEnd      =" + pushParent);
                }
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth2 = resolveContext2.depth();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Resolving highest-priority item in delayed merge ");
                    sb.append(abstractConfigValue2);
                    sb.append(" against ");
                    sb.append(pushParent);
                    sb.append(" endWasRemoved=");
                    sb.append(resolveSource != pushParent);
                    ConfigImpl.trace(depth2, sb.toString());
                }
                ResolveResult<? extends AbstractConfigValue> resolve = resolveContext2.resolve(abstractConfigValue2, pushParent);
                ConfigMergeable configMergeable = resolve.value;
                resolveContext2 = resolve.context;
                if (configMergeable != null) {
                    if (abstractConfigValue == null) {
                        abstractConfigValue = configMergeable;
                    } else {
                        if (ConfigImpl.traceSubstitutionsEnabled()) {
                            ConfigImpl.trace(resolveContext2.depth() + 1, "merging " + abstractConfigValue + " with fallback " + configMergeable);
                        }
                        abstractConfigValue = abstractConfigValue.mo10234withFallback(configMergeable);
                    }
                }
                i2++;
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    ConfigImpl.trace(resolveContext2.depth(), "stack merged, yielding: " + abstractConfigValue);
                }
            } else {
                throw new ConfigException.BugOrBroken("A delayed merge should not contain another one: " + replaceableMergeStack);
            }
        }
        return ResolveResult.make(resolveContext2, abstractConfigValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: mergedWithNonObject  reason: collision with other method in class */
    public ConfigDelayedMerge mo10190mergedWithNonObject(AbstractConfigValue abstractConfigValue) {
        return (ConfigDelayedMerge) mergedWithNonObject(this.stack, abstractConfigValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: mergedWithObject  reason: collision with other method in class */
    public final ConfigDelayedMerge mo10249mergedWithObject(AbstractConfigObject abstractConfigObject) {
        return (ConfigDelayedMerge) mergedWithObject(this.stack, abstractConfigObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: mergedWithTheUnmergeable  reason: collision with other method in class */
    public final ConfigDelayedMerge mo10192mergedWithTheUnmergeable(Unmergeable unmergeable) {
        return (ConfigDelayedMerge) mergedWithTheUnmergeable(this.stack, unmergeable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: relativized  reason: collision with other method in class */
    public ConfigDelayedMerge mo10251relativized(Path path) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue abstractConfigValue : this.stack) {
            arrayList.add(abstractConfigValue.mo10251relativized(path));
        }
        return new ConfigDelayedMerge(mo10176origin(), arrayList);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    protected void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        render(sb, i, z, null, configRenderOptions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void render(List<AbstractConfigValue> list, StringBuilder sb, int i, boolean z, String str, ConfigRenderOptions configRenderOptions) {
        boolean comments = configRenderOptions.getComments();
        if (comments) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("# unresolved merge of ");
            outline107.append(list.size());
            outline107.append(" values follows (\n");
            sb.append(outline107.toString());
            if (str == null) {
                AbstractConfigValue.indent(sb, i, configRenderOptions);
                sb.append("# this unresolved merge will not be parseable because it's at the root of the object\n");
                AbstractConfigValue.indent(sb, i, configRenderOptions);
                sb.append("# the HOCON format has no way to list multiple root objects in a single file\n");
            }
        }
        ArrayList<AbstractConfigValue> arrayList = new ArrayList();
        arrayList.addAll(list);
        Collections.reverse(arrayList);
        int i2 = 0;
        for (AbstractConfigValue abstractConfigValue : arrayList) {
            if (comments) {
                AbstractConfigValue.indent(sb, i, configRenderOptions);
                if (str != null) {
                    StringBuilder outline109 = GeneratedOutlineSupport1.outline109("#     unmerged value ", i2, " for key ");
                    outline109.append(ConfigImplUtil.renderJsonString(str));
                    outline109.append(" from ");
                    sb.append(outline109.toString());
                } else {
                    sb.append("#     unmerged value " + i2 + " from ");
                }
                i2++;
                sb.append(abstractConfigValue.mo10176origin().description());
                sb.append("\n");
                for (String str2 : abstractConfigValue.mo10176origin().comments()) {
                    AbstractConfigValue.indent(sb, i, configRenderOptions);
                    GeneratedOutlineSupport1.outline180(sb, "# ", str2, "\n");
                }
            }
            AbstractConfigValue.indent(sb, i, configRenderOptions);
            if (str != null) {
                sb.append(ConfigImplUtil.renderJsonString(str));
                if (configRenderOptions.getFormatted()) {
                    sb.append(" : ");
                } else {
                    sb.append(":");
                }
            }
            abstractConfigValue.render(sb, i, z, configRenderOptions);
            sb.append(",");
            if (configRenderOptions.getFormatted()) {
                sb.append('\n');
            }
        }
        sb.setLength(sb.length() - 1);
        if (configRenderOptions.getFormatted()) {
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        if (comments) {
            AbstractConfigValue.indent(sb, i, configRenderOptions);
            sb.append("# ) end of unresolved merge\n");
        }
    }
}
