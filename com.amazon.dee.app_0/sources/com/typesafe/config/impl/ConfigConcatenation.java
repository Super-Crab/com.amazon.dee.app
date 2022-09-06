package com.typesafe.config.impl;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import com.typesafe.config.impl.ConfigString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigConcatenation extends AbstractConfigValue implements Unmergeable, Container {
    private final List<AbstractConfigValue> pieces;

    ConfigConcatenation(ConfigOrigin configOrigin, List<AbstractConfigValue> list) {
        super(configOrigin);
        this.pieces = list;
        if (list.size() >= 2) {
            boolean z = false;
            for (AbstractConfigValue abstractConfigValue : list) {
                if (!(abstractConfigValue instanceof ConfigConcatenation)) {
                    if (abstractConfigValue instanceof Unmergeable) {
                        z = true;
                    }
                } else {
                    throw new ConfigException.BugOrBroken("ConfigConcatenation should never be nested: " + this);
                }
            }
            if (z) {
                return;
            }
            throw new ConfigException.BugOrBroken("Created concatenation without an unmergeable in it: " + this);
        }
        throw new ConfigException.BugOrBroken("Created concatenation with less than 2 items: " + this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigValue concatenate(List<AbstractConfigValue> list) {
        List<AbstractConfigValue> consolidate = consolidate(list);
        if (consolidate.isEmpty()) {
            return null;
        }
        if (consolidate.size() == 1) {
            return consolidate.get(0);
        }
        return new ConfigConcatenation(SimpleConfigOrigin.mergeOrigins((List<? extends AbstractConfigValue>) consolidate), consolidate);
    }

    static List<AbstractConfigValue> consolidate(List<AbstractConfigValue> list) {
        if (list.size() < 2) {
            return list;
        }
        ArrayList<AbstractConfigValue> arrayList = new ArrayList(list.size());
        for (AbstractConfigValue abstractConfigValue : list) {
            if (abstractConfigValue instanceof ConfigConcatenation) {
                arrayList.addAll(((ConfigConcatenation) abstractConfigValue).pieces);
            } else {
                arrayList.add(abstractConfigValue);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (AbstractConfigValue abstractConfigValue2 : arrayList) {
            if (arrayList2.isEmpty()) {
                arrayList2.add(abstractConfigValue2);
            } else {
                join(arrayList2, abstractConfigValue2);
            }
        }
        return arrayList2;
    }

    private static boolean isIgnoredWhitespace(AbstractConfigValue abstractConfigValue) {
        return (abstractConfigValue instanceof ConfigString) && !((ConfigString) abstractConfigValue).wasQuoted();
    }

    private static void join(ArrayList<AbstractConfigValue> arrayList, AbstractConfigValue abstractConfigValue) {
        AbstractConfigValue abstractConfigValue2 = arrayList.get(arrayList.size() - 1);
        if ((abstractConfigValue2 instanceof ConfigObject) && (abstractConfigValue instanceof SimpleConfigList)) {
            abstractConfigValue2 = DefaultTransformer.transform(abstractConfigValue2, ConfigValueType.LIST);
        } else if ((abstractConfigValue2 instanceof SimpleConfigList) && (abstractConfigValue instanceof ConfigObject)) {
            abstractConfigValue = DefaultTransformer.transform(abstractConfigValue, ConfigValueType.LIST);
        }
        boolean z = abstractConfigValue2 instanceof ConfigObject;
        if (z && (abstractConfigValue instanceof ConfigObject)) {
            abstractConfigValue2 = abstractConfigValue.mo10234withFallback((ConfigMergeable) abstractConfigValue2);
        } else {
            boolean z2 = abstractConfigValue2 instanceof SimpleConfigList;
            if (z2 && (abstractConfigValue instanceof SimpleConfigList)) {
                abstractConfigValue2 = ((SimpleConfigList) abstractConfigValue2).concatenate((SimpleConfigList) abstractConfigValue);
            } else if ((!z2 && !z) || !isIgnoredWhitespace(abstractConfigValue)) {
                if (!(abstractConfigValue2 instanceof ConfigConcatenation) && !(abstractConfigValue instanceof ConfigConcatenation)) {
                    if ((abstractConfigValue2 instanceof Unmergeable) || (abstractConfigValue instanceof Unmergeable)) {
                        abstractConfigValue2 = null;
                    } else {
                        String transformToString = abstractConfigValue2.transformToString();
                        String transformToString2 = abstractConfigValue.transformToString();
                        if (transformToString != null && transformToString2 != null) {
                            abstractConfigValue2 = new ConfigString.Quoted(SimpleConfigOrigin.mergeOrigins(abstractConfigValue2.mo10176origin(), abstractConfigValue.mo10176origin()), GeneratedOutlineSupport1.outline72(transformToString, transformToString2));
                        } else {
                            throw new ConfigException.WrongType(abstractConfigValue2.mo10176origin(), "Cannot concatenate object or list with a non-object-or-list, " + abstractConfigValue2 + " and " + abstractConfigValue + " are not compatible");
                        }
                    }
                } else {
                    throw new ConfigException.BugOrBroken("unflattened ConfigConcatenation");
                }
            }
        }
        if (abstractConfigValue2 == null) {
            arrayList.add(abstractConfigValue);
            return;
        }
        arrayList.remove(arrayList.size() - 1);
        arrayList.add(abstractConfigValue2);
    }

    private ConfigException.NotResolved notResolved() {
        return new ConfigException.NotResolved("need to Config#resolve(), see the API docs for Config#resolve(); substitution not resolved: " + this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigConcatenation;
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean equals(Object obj) {
        return (obj instanceof ConfigConcatenation) && canEqual(obj) && this.pieces.equals(((ConfigConcatenation) obj).pieces);
    }

    @Override // com.typesafe.config.impl.Container
    public boolean hasDescendant(AbstractConfigValue abstractConfigValue) {
        return AbstractConfigValue.hasDescendantInList(this.pieces, abstractConfigValue);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public int hashCode() {
        return this.pieces.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean ignoresFallbacks() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        for (AbstractConfigValue abstractConfigValue : this.pieces) {
            abstractConfigValue.render(sb, i, z, configRenderOptions);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public ResolveStatus resolveStatus() {
        return ResolveStatus.UNRESOLVED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = resolveContext.depth() + 2;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("concatenation has ");
            outline107.append(this.pieces.size());
            outline107.append(" pieces:");
            ConfigImpl.trace(depth - 1, outline107.toString());
            Iterator<AbstractConfigValue> it2 = this.pieces.iterator();
            int i = 0;
            while (it2.hasNext()) {
                ConfigImpl.trace(depth, i + RealTimeTextConstants.COLON_SPACE + it2.next());
                i++;
            }
        }
        ArrayList arrayList = new ArrayList(this.pieces.size());
        ResolveContext resolveContext2 = resolveContext;
        for (AbstractConfigValue abstractConfigValue : this.pieces) {
            Path restrictToChild = resolveContext2.restrictToChild();
            ResolveResult<? extends AbstractConfigValue> resolve = resolveContext2.unrestricted().resolve(abstractConfigValue, resolveSource);
            Object obj = resolve.value;
            resolveContext2 = resolve.context.restrict(restrictToChild);
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                ConfigImpl.trace(resolveContext.depth(), "resolved concat piece to " + obj);
            }
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        List<AbstractConfigValue> consolidate = consolidate(arrayList);
        if (consolidate.size() > 1 && resolveContext.options().getAllowUnresolved()) {
            return ResolveResult.make(resolveContext2, new ConfigConcatenation(mo10176origin(), consolidate));
        }
        if (consolidate.isEmpty()) {
            return ResolveResult.make(resolveContext2, null);
        }
        if (consolidate.size() == 1) {
            return ResolveResult.make(resolveContext2, consolidate.get(0));
        }
        throw new ConfigException.BugOrBroken("Bug in the library; resolved list was joined to too many values: " + consolidate);
    }

    @Override // com.typesafe.config.impl.Unmergeable
    public Collection<ConfigConcatenation> unmergedValues() {
        return Collections.singleton(this);
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped */
    public Object mo10253unwrapped() {
        throw notResolved();
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        throw notResolved();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy  reason: collision with other method in class */
    public ConfigConcatenation mo10238newCopy(ConfigOrigin configOrigin) {
        return new ConfigConcatenation(configOrigin, this.pieces);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: relativized  reason: collision with other method in class */
    public ConfigConcatenation mo10251relativized(Path path) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue abstractConfigValue : this.pieces) {
            arrayList.add(abstractConfigValue.mo10251relativized(path));
        }
        return new ConfigConcatenation(mo10176origin(), arrayList);
    }

    @Override // com.typesafe.config.impl.Container
    /* renamed from: replaceChild  reason: collision with other method in class */
    public ConfigConcatenation mo10252replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        List<AbstractConfigValue> replaceChildInList = AbstractConfigValue.replaceChildInList(this.pieces, abstractConfigValue, abstractConfigValue2);
        if (replaceChildInList == null) {
            return null;
        }
        return new ConfigConcatenation(mo10176origin(), replaceChildInList);
    }
}
