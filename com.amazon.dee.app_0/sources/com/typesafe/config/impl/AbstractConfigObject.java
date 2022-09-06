package com.typesafe.config.impl;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class AbstractConfigObject extends AbstractConfigValue implements ConfigObject, Container {
    private final SimpleConfig config;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractConfigObject(ConfigOrigin configOrigin) {
        super(configOrigin);
        this.config = new SimpleConfig(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigOrigin mergeOrigins(Collection<? extends AbstractConfigValue> collection) {
        if (!collection.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            SimpleConfigOrigin simpleConfigOrigin = null;
            int i = 0;
            for (AbstractConfigValue abstractConfigValue : collection) {
                if (simpleConfigOrigin == null) {
                    simpleConfigOrigin = abstractConfigValue.mo10176origin();
                }
                if (!(abstractConfigValue instanceof AbstractConfigObject) || ((AbstractConfigObject) abstractConfigValue).resolveStatus() != ResolveStatus.RESOLVED || !((ConfigObject) abstractConfigValue).isEmpty()) {
                    arrayList.add(abstractConfigValue.mo10176origin());
                    i++;
                }
            }
            if (i == 0) {
                arrayList.add(simpleConfigOrigin);
            }
            return SimpleConfigOrigin.mergeOrigins((Collection<? extends ConfigOrigin>) arrayList);
        }
        throw new ConfigException.BugOrBroken("can't merge origins on empty list");
    }

    private static UnsupportedOperationException weAreImmutable(String str) {
        return new UnsupportedOperationException(GeneratedOutlineSupport1.outline72("ConfigObject is immutable, you can't call Map.", str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract AbstractConfigValue attemptPeekWithPartialResolve(String str);

    @Override // java.util.Map
    public void clear() {
        throw weAreImmutable(MetricsConstants.Method.CACHE_CLEAR);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: constructDelayedMerge  reason: collision with other method in class */
    protected /* bridge */ /* synthetic */ AbstractConfigValue mo10159constructDelayedMerge(ConfigOrigin configOrigin, List list) {
        return mo10159constructDelayedMerge(configOrigin, (List<AbstractConfigValue>) list);
    }

    @Override // com.typesafe.config.ConfigObject, java.util.Map
    /* renamed from: get  reason: collision with other method in class */
    public abstract ConfigValue mo10248get(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: mergedWithObject */
    public abstract AbstractConfigObject mo10249mergedWithObject(AbstractConfigObject abstractConfigObject);

    /* renamed from: newCopy */
    protected abstract AbstractConfigObject mo10250newCopy(ResolveStatus resolveStatus, ConfigOrigin configOrigin);

    /* JADX INFO: Access modifiers changed from: protected */
    public final AbstractConfigValue peekAssumingResolved(String str, Path path) {
        try {
            return attemptPeekWithPartialResolve(str);
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(path, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractConfigValue peekPath(Path path) {
        return peekPath(this, path);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends ConfigValue> map) {
        throw weAreImmutable("putAll");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: relativized */
    public abstract AbstractConfigObject mo10251relativized(Path path);

    @Override // com.typesafe.config.impl.AbstractConfigValue
    protected abstract void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions);

    @Override // com.typesafe.config.impl.AbstractConfigValue
    abstract ResolveResult<? extends AbstractConfigObject> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve;

    @Override // com.typesafe.config.impl.AbstractConfigValue, com.typesafe.config.impl.MergeableValue
    /* renamed from: toFallbackValue  reason: collision with other method in class */
    public AbstractConfigObject mo10233toFallbackValue() {
        return this;
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        return ConfigValueType.OBJECT;
    }

    @Override // com.typesafe.config.ConfigObject
    /* renamed from: withOnlyKey  reason: collision with other method in class */
    public abstract AbstractConfigObject mo10255withOnlyKey(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: withOnlyPath */
    public abstract AbstractConfigObject mo10256withOnlyPath(Path path);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: withOnlyPathOrNull */
    public abstract AbstractConfigObject mo10257withOnlyPathOrNull(Path path);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: withValue */
    public abstract AbstractConfigObject mo10259withValue(Path path, ConfigValue configValue);

    @Override // com.typesafe.config.ConfigObject
    /* renamed from: withValue  reason: collision with other method in class */
    public abstract AbstractConfigObject mo10258withValue(String str, ConfigValue configValue);

    @Override // com.typesafe.config.ConfigObject
    /* renamed from: withoutKey  reason: collision with other method in class */
    public abstract AbstractConfigObject mo10260withoutKey(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: withoutPath */
    public abstract AbstractConfigObject mo10261withoutPath(Path path);

    private static AbstractConfigValue peekPath(AbstractConfigObject abstractConfigObject, Path path) {
        try {
            Path remainder = path.remainder();
            AbstractConfigValue attemptPeekWithPartialResolve = abstractConfigObject.attemptPeekWithPartialResolve(path.first());
            if (remainder == null) {
                return attemptPeekWithPartialResolve;
            }
            if (!(attemptPeekWithPartialResolve instanceof AbstractConfigObject)) {
                return null;
            }
            return peekPath((AbstractConfigObject) attemptPeekWithPartialResolve, remainder);
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(path, e);
        }
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: constructDelayedMerge */
    protected AbstractConfigObject mo10159constructDelayedMerge(ConfigOrigin configOrigin, List<AbstractConfigValue> list) {
        return new ConfigDelayedMergeObject(configOrigin, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy */
    public AbstractConfigObject mo10238newCopy(ConfigOrigin configOrigin) {
        return mo10250newCopy(resolveStatus(), configOrigin);
    }

    @Override // java.util.Map
    public ConfigValue put(String str, ConfigValue configValue) {
        throw weAreImmutable(MetricsConstants.Method.CACHE_PUT);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    /* renamed from: remove */
    public ConfigValue mo10164remove(Object obj) {
        throw weAreImmutable(BulkOperationType.remove);
    }

    @Override // com.typesafe.config.ConfigObject
    /* renamed from: toConfig  reason: collision with other method in class */
    public SimpleConfig mo10165toConfig() {
        return this.config;
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue, com.typesafe.config.ConfigValue
    /* renamed from: withOrigin */
    public AbstractConfigObject mo10244withOrigin(ConfigOrigin configOrigin) {
        return (AbstractConfigObject) super.mo10244withOrigin(configOrigin);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue, com.typesafe.config.ConfigValue, com.typesafe.config.ConfigMergeable
    /* renamed from: withFallback  reason: collision with other method in class */
    public AbstractConfigObject mo10234withFallback(ConfigMergeable configMergeable) {
        return (AbstractConfigObject) super.mo10234withFallback(configMergeable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigOrigin mergeOrigins(AbstractConfigObject... abstractConfigObjectArr) {
        return mergeOrigins(Arrays.asList(abstractConfigObjectArr));
    }
}
