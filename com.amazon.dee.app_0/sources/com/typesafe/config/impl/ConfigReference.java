package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import com.typesafe.config.impl.ResolveSource;
import java.util.Collection;
import java.util.Collections;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigReference extends AbstractConfigValue implements Unmergeable {
    private final SubstitutionExpression expr;
    private final int prefixLength;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigReference(ConfigOrigin configOrigin, SubstitutionExpression substitutionExpression) {
        this(configOrigin, substitutionExpression, 0);
    }

    private ConfigException.NotResolved notResolved() {
        return new ConfigException.NotResolved("need to Config#resolve(), see the API docs for Config#resolve(); substitution not resolved: " + this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigReference;
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean equals(Object obj) {
        return (obj instanceof ConfigReference) && canEqual(obj) && this.expr.equals(((ConfigReference) obj).expr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubstitutionExpression expression() {
        return this.expr;
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public int hashCode() {
        return this.expr.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean ignoresFallbacks() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        sb.append(this.expr.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public ResolveStatus resolveStatus() {
        return ResolveStatus.UNRESOLVED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) {
        AbstractConfigValue abstractConfigValue;
        ResolveContext addCycleMarker = resolveContext.addCycleMarker(this);
        try {
            ResolveSource.ResultWithPath lookupSubst = resolveSource.lookupSubst(addCycleMarker, this.expr, this.prefixLength);
            addCycleMarker = lookupSubst.result.context;
            if (lookupSubst.result.value != 0) {
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth = addCycleMarker.depth();
                    ConfigImpl.trace(depth, "recursively resolving " + lookupSubst + " which was the resolution of " + this.expr + " against " + resolveSource);
                }
                ResolveSource resolveSource2 = new ResolveSource((AbstractConfigObject) lookupSubst.pathFromRoot.last(), lookupSubst.pathFromRoot);
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth2 = addCycleMarker.depth();
                    ConfigImpl.trace(depth2, "will recursively resolve against " + resolveSource2);
                }
                ResolveResult<? extends AbstractConfigValue> resolve = addCycleMarker.resolve(lookupSubst.result.value, resolveSource2);
                abstractConfigValue = resolve.value;
                addCycleMarker = resolve.context;
            } else {
                abstractConfigValue = (V) resolveContext.options().getResolver().lookup(this.expr.path().render());
            }
        } catch (AbstractConfigValue.NotPossibleToResolve e) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                int depth3 = addCycleMarker.depth();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("not possible to resolve ");
                outline107.append(this.expr);
                outline107.append(", cycle involved: ");
                outline107.append(e.traceString());
                ConfigImpl.trace(depth3, outline107.toString());
            }
            if (!this.expr.optional()) {
                SimpleConfigOrigin mo10176origin = mo10176origin();
                throw new ConfigException.UnresolvedSubstitution(mo10176origin, this.expr + " was part of a cycle of substitutions involving " + e.traceString(), e);
            }
            abstractConfigValue = null;
        }
        if (abstractConfigValue == null && !this.expr.optional()) {
            if (addCycleMarker.options().getAllowUnresolved()) {
                return ResolveResult.make(addCycleMarker.removeCycleMarker(this), this);
            }
            throw new ConfigException.UnresolvedSubstitution(mo10176origin(), this.expr.toString());
        }
        return ResolveResult.make(addCycleMarker.removeCycleMarker(this), abstractConfigValue);
    }

    @Override // com.typesafe.config.impl.Unmergeable
    public Collection<ConfigReference> unmergedValues() {
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

    private ConfigReference(ConfigOrigin configOrigin, SubstitutionExpression substitutionExpression, int i) {
        super(configOrigin);
        this.expr = substitutionExpression;
        this.prefixLength = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy  reason: collision with other method in class */
    public ConfigReference mo10238newCopy(ConfigOrigin configOrigin) {
        return new ConfigReference(configOrigin, this.expr, this.prefixLength);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: relativized  reason: collision with other method in class */
    public ConfigReference mo10251relativized(Path path) {
        SubstitutionExpression substitutionExpression = this.expr;
        return new ConfigReference(mo10176origin(), substitutionExpression.changePath(substitutionExpression.path().prepend(path)), this.prefixLength + path.length());
    }
}
