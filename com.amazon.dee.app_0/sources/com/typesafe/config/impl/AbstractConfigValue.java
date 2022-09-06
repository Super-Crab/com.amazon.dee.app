package com.typesafe.config.impl;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.util.Chars;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class AbstractConfigValue implements ConfigValue, MergeableValue {
    private final SimpleConfigOrigin origin;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public interface Modifier {
        AbstractConfigValue modifyChildMayThrow(String str, AbstractConfigValue abstractConfigValue) throws Exception;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public abstract class NoExceptionsModifier implements Modifier {
        /* JADX INFO: Access modifiers changed from: protected */
        public NoExceptionsModifier() {
        }

        abstract AbstractConfigValue modifyChild(String str, AbstractConfigValue abstractConfigValue);

        @Override // com.typesafe.config.impl.AbstractConfigValue.Modifier
        public final AbstractConfigValue modifyChildMayThrow(String str, AbstractConfigValue abstractConfigValue) throws Exception {
            try {
                return modifyChild(str, abstractConfigValue);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new ConfigException.BugOrBroken("Unexpected exception", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class NotPossibleToResolve extends Exception {
        private static final long serialVersionUID = 1;
        private final String traceString;

        /* JADX INFO: Access modifiers changed from: package-private */
        public NotPossibleToResolve(ResolveContext resolveContext) {
            super("was not possible to resolve");
            this.traceString = resolveContext.traceString();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String traceString() {
            return this.traceString;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractConfigValue(ConfigOrigin configOrigin) {
        this.origin = (SimpleConfigOrigin) configOrigin;
    }

    private final AbstractConfigValue delayMerge(Collection<AbstractConfigValue> collection, AbstractConfigValue abstractConfigValue) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(collection);
        arrayList.add(abstractConfigValue);
        return mo10159constructDelayedMerge(AbstractConfigObject.mergeOrigins(arrayList), arrayList);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean hasDescendantInList(List<AbstractConfigValue> list, AbstractConfigValue abstractConfigValue) {
        for (AbstractConfigValue abstractConfigValue2 : list) {
            if (abstractConfigValue2 == abstractConfigValue) {
                return true;
            }
        }
        for (AbstractConfigValue abstractConfigValue3 : list) {
            if ((abstractConfigValue3 instanceof Container) && ((Container) abstractConfigValue3).hasDescendant(abstractConfigValue)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void indent(StringBuilder sb, int i, ConfigRenderOptions configRenderOptions) {
        if (configRenderOptions.getFormatted()) {
            while (i > 0) {
                sb.append("    ");
                i--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static List<AbstractConfigValue> replaceChildInList(List<AbstractConfigValue> list, AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        int i = 0;
        while (i < list.size() && list.get(i) != abstractConfigValue) {
            i++;
        }
        if (i != list.size()) {
            ArrayList arrayList = new ArrayList(list);
            if (abstractConfigValue2 != null) {
                arrayList.set(i, abstractConfigValue2);
            } else {
                arrayList.remove(i);
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
            return null;
        }
        throw new ConfigException.BugOrBroken("tried to replace " + abstractConfigValue + " which is not in " + list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigValue;
    }

    /* renamed from: constructDelayedMerge */
    protected AbstractConfigValue mo10159constructDelayedMerge(ConfigOrigin configOrigin, List<AbstractConfigValue> list) {
        return new ConfigDelayedMerge(configOrigin, list);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigValue) || !canEqual(obj)) {
            return false;
        }
        ConfigValue configValue = (ConfigValue) obj;
        return valueType() == configValue.valueType() && ConfigImplUtil.equalsHandlingNull(mo10253unwrapped(), configValue.mo10253unwrapped());
    }

    public int hashCode() {
        Object mo10253unwrapped = mo10253unwrapped();
        if (mo10253unwrapped == null) {
            return 0;
        }
        return mo10253unwrapped.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean ignoresFallbacks() {
        return resolveStatus() == ResolveStatus.RESOLVED;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final AbstractConfigValue mergedWithNonObject(Collection<AbstractConfigValue> collection, AbstractConfigValue abstractConfigValue) {
        requireNotIgnoringFallbacks();
        if (resolveStatus() == ResolveStatus.RESOLVED) {
            return mo10254withFallbacksIgnored();
        }
        return delayMerge(collection, abstractConfigValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final AbstractConfigValue mergedWithObject(Collection<AbstractConfigValue> collection, AbstractConfigObject abstractConfigObject) {
        requireNotIgnoringFallbacks();
        if (!(this instanceof AbstractConfigObject)) {
            return mergedWithNonObject(collection, abstractConfigObject);
        }
        throw new ConfigException.BugOrBroken("Objects must reimplement mergedWithObject");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final AbstractConfigValue mergedWithTheUnmergeable(Collection<AbstractConfigValue> collection, Unmergeable unmergeable) {
        requireNotIgnoringFallbacks();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(collection);
        arrayList.addAll(unmergeable.unmergedValues());
        return mo10159constructDelayedMerge(AbstractConfigObject.mergeOrigins(arrayList), arrayList);
    }

    /* renamed from: newCopy */
    protected abstract AbstractConfigValue mo10238newCopy(ConfigOrigin configOrigin);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: relativized */
    public AbstractConfigValue mo10251relativized(Path path) {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, String str, ConfigRenderOptions configRenderOptions) {
        String renderStringUnquotedIfPossible;
        if (str != null) {
            if (configRenderOptions.getJson()) {
                renderStringUnquotedIfPossible = ConfigImplUtil.renderJsonString(str);
            } else {
                renderStringUnquotedIfPossible = ConfigImplUtil.renderStringUnquotedIfPossible(str);
            }
            sb.append(renderStringUnquotedIfPossible);
            if (configRenderOptions.getJson()) {
                if (configRenderOptions.getFormatted()) {
                    sb.append(" : ");
                } else {
                    sb.append(":");
                }
            } else if (this instanceof ConfigObject) {
                if (configRenderOptions.getFormatted()) {
                    sb.append(Chars.SPACE);
                }
            } else {
                sb.append(Config.Compare.EQUAL_TO);
            }
        }
        render(sb, i, z, configRenderOptions);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void requireNotIgnoringFallbacks() {
        if (!ignoresFallbacks()) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("method should not have been called with ignoresFallbacks=true ");
        outline107.append(getClass().getSimpleName());
        throw new ConfigException.BugOrBroken(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveStatus resolveStatus() {
        return ResolveStatus.RESOLVED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws NotPossibleToResolve {
        return ResolveResult.make(resolveContext, this);
    }

    @Override // com.typesafe.config.impl.MergeableValue
    /* renamed from: toFallbackValue  reason: collision with other method in class */
    public AbstractConfigValue mo10233toFallbackValue() {
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        render(sb, 0, true, null, ConfigRenderOptions.concise());
        return getClass().getSimpleName() + "(" + sb.toString() + ")";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String transformToString() {
        return null;
    }

    /* renamed from: withFallbacksIgnored */
    protected AbstractConfigValue mo10254withFallbacksIgnored() {
        if (ignoresFallbacks()) {
            return this;
        }
        throw new ConfigException.BugOrBroken("value class doesn't implement forced fallback-ignoring " + this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleConfig atKey(ConfigOrigin configOrigin, String str) {
        return new SimpleConfigObject(configOrigin, Collections.singletonMap(str, this)).mo10165toConfig();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleConfig atPath(ConfigOrigin configOrigin, Path path) {
        SimpleConfig atKey = atKey(configOrigin, path.last());
        for (Path parent = path.parent(); parent != null; parent = parent.parent()) {
            atKey = atKey.atKey(configOrigin, parent.last());
        }
        return atKey;
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: origin  reason: collision with other method in class */
    public SimpleConfigOrigin mo10176origin() {
        return this.origin;
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: withOrigin  reason: collision with other method in class */
    public AbstractConfigValue mo10244withOrigin(ConfigOrigin configOrigin) {
        return this.origin == configOrigin ? this : mo10238newCopy(configOrigin);
    }

    @Override // com.typesafe.config.ConfigValue, com.typesafe.config.ConfigMergeable
    /* renamed from: withFallback */
    public AbstractConfigValue mo10234withFallback(ConfigMergeable configMergeable) {
        if (ignoresFallbacks()) {
            return this;
        }
        ConfigValue mo10233toFallbackValue = ((MergeableValue) configMergeable).mo10233toFallbackValue();
        if (mo10233toFallbackValue instanceof Unmergeable) {
            return mo10192mergedWithTheUnmergeable((Unmergeable) mo10233toFallbackValue);
        }
        if (mo10233toFallbackValue instanceof AbstractConfigObject) {
            return mo10249mergedWithObject((AbstractConfigObject) mo10233toFallbackValue);
        }
        return mo10190mergedWithNonObject((AbstractConfigValue) mo10233toFallbackValue);
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: atKey  reason: collision with other method in class */
    public SimpleConfig mo10174atKey(String str) {
        return atKey(SimpleConfigOrigin.newSimple(GeneratedOutlineSupport1.outline75("atKey(", str, ")")), str);
    }

    /* renamed from: mergedWithNonObject */
    protected AbstractConfigValue mo10190mergedWithNonObject(AbstractConfigValue abstractConfigValue) {
        requireNotIgnoringFallbacks();
        return mergedWithNonObject(Collections.singletonList(this), abstractConfigValue);
    }

    /* renamed from: mergedWithObject */
    protected AbstractConfigValue mo10249mergedWithObject(AbstractConfigObject abstractConfigObject) {
        requireNotIgnoringFallbacks();
        return mergedWithObject(Collections.singletonList(this), abstractConfigObject);
    }

    /* renamed from: mergedWithTheUnmergeable */
    protected AbstractConfigValue mo10192mergedWithTheUnmergeable(Unmergeable unmergeable) {
        requireNotIgnoringFallbacks();
        return mergedWithTheUnmergeable(Collections.singletonList(this), unmergeable);
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: atPath  reason: collision with other method in class */
    public SimpleConfig mo10175atPath(String str) {
        return atPath(SimpleConfigOrigin.newSimple(GeneratedOutlineSupport1.outline75("atPath(", str, ")")), Path.newPath(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        sb.append(mo10253unwrapped().toString());
    }

    @Override // com.typesafe.config.ConfigValue
    public final String render() {
        return render(ConfigRenderOptions.defaults());
    }

    @Override // com.typesafe.config.ConfigValue
    public final String render(ConfigRenderOptions configRenderOptions) {
        StringBuilder sb = new StringBuilder();
        render(sb, 0, true, null, configRenderOptions);
        return sb.toString();
    }
}
