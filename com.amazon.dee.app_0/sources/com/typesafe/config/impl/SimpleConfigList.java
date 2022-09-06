package com.typesafe.config.impl;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.logging.log4j.util.Chars;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SimpleConfigList extends AbstractConfigValue implements ConfigList, Container, Serializable {
    private static final long serialVersionUID = 2;
    private final boolean resolved;
    private final List<AbstractConfigValue> value;

    /* loaded from: classes3.dex */
    private static class ResolveModifier implements AbstractConfigValue.Modifier {
        ResolveContext context;
        final ResolveSource source;

        ResolveModifier(ResolveContext resolveContext, ResolveSource resolveSource) {
            this.context = resolveContext;
            this.source = resolveSource;
        }

        @Override // com.typesafe.config.impl.AbstractConfigValue.Modifier
        public AbstractConfigValue modifyChildMayThrow(String str, AbstractConfigValue abstractConfigValue) throws AbstractConfigValue.NotPossibleToResolve {
            ResolveResult<? extends AbstractConfigValue> resolve = this.context.resolve(abstractConfigValue, this.source);
            this.context = resolve.context;
            return resolve.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleConfigList(ConfigOrigin configOrigin, List<AbstractConfigValue> list) {
        this(configOrigin, list, ResolveStatus.fromValues(list));
    }

    private SimpleConfigList modify(AbstractConfigValue.NoExceptionsModifier noExceptionsModifier, ResolveStatus resolveStatus) {
        try {
            return modifyMayThrow(noExceptionsModifier, resolveStatus);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new ConfigException.BugOrBroken("unexpected checked exception", e2);
        }
    }

    private SimpleConfigList modifyMayThrow(AbstractConfigValue.Modifier modifier, ResolveStatus resolveStatus) throws Exception {
        int i = 0;
        ArrayList arrayList = null;
        for (AbstractConfigValue abstractConfigValue : this.value) {
            AbstractConfigValue modifyChildMayThrow = modifier.modifyChildMayThrow(null, abstractConfigValue);
            if (arrayList == null && modifyChildMayThrow != abstractConfigValue) {
                arrayList = new ArrayList();
                for (int i2 = 0; i2 < i; i2++) {
                    arrayList.add(this.value.get(i2));
                }
            }
            if (arrayList != null && modifyChildMayThrow != null) {
                arrayList.add(modifyChildMayThrow);
            }
            i++;
        }
        if (arrayList != null) {
            if (resolveStatus != null) {
                return new SimpleConfigList(mo10176origin(), arrayList, resolveStatus);
            }
            return new SimpleConfigList(mo10176origin(), arrayList);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static UnsupportedOperationException weAreImmutable(String str) {
        return new UnsupportedOperationException(GeneratedOutlineSupport1.outline75("ConfigList is immutable, you can't call List.'", str, "'"));
    }

    private static ListIterator<ConfigValue> wrapListIterator(final ListIterator<AbstractConfigValue> listIterator) {
        return new ListIterator<ConfigValue>() { // from class: com.typesafe.config.impl.SimpleConfigList.3
            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                return listIterator.hasNext();
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return listIterator.hasPrevious();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return listIterator.nextIndex();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return listIterator.previousIndex();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                throw SimpleConfigList.weAreImmutable("listIterator().remove");
            }

            @Override // java.util.ListIterator
            public void add(ConfigValue configValue) {
                throw SimpleConfigList.weAreImmutable("listIterator().add");
            }

            @Override // java.util.ListIterator, java.util.Iterator
            /* renamed from: next */
            public ConfigValue mo10246next() {
                return (ConfigValue) listIterator.next();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.ListIterator
            /* renamed from: previous */
            public ConfigValue mo10247previous() {
                return (ConfigValue) listIterator.previous();
            }

            @Override // java.util.ListIterator
            public void set(ConfigValue configValue) {
                throw SimpleConfigList.weAreImmutable("listIterator().set");
            }
        };
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue(this);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends ConfigValue> collection) {
        throw weAreImmutable("addAll");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean canEqual(Object obj) {
        return obj instanceof SimpleConfigList;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw weAreImmutable(MetricsConstants.Method.CACHE_CLEAR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SimpleConfigList concatenate(SimpleConfigList simpleConfigList) {
        ConfigOrigin mergeOrigins = SimpleConfigOrigin.mergeOrigins(mo10176origin(), simpleConfigList.mo10176origin());
        ArrayList arrayList = new ArrayList(simpleConfigList.value.size() + this.value.size());
        arrayList.addAll(this.value);
        arrayList.addAll(simpleConfigList.value);
        return new SimpleConfigList(mergeOrigins, arrayList);
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.value.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.value.containsAll(collection);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean equals(Object obj) {
        if (!(obj instanceof SimpleConfigList) || !canEqual(obj)) {
            return false;
        }
        List<AbstractConfigValue> list = this.value;
        List<AbstractConfigValue> list2 = ((SimpleConfigList) obj).value;
        return list == list2 || list.equals(list2);
    }

    @Override // com.typesafe.config.impl.Container
    public boolean hasDescendant(AbstractConfigValue abstractConfigValue) {
        return AbstractConfigValue.hasDescendantInList(this.value, abstractConfigValue);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.value.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<ConfigValue> iterator() {
        final Iterator<AbstractConfigValue> it2 = this.value.iterator();
        return new Iterator<ConfigValue>() { // from class: com.typesafe.config.impl.SimpleConfigList.2
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it2.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw SimpleConfigList.weAreImmutable("iterator().remove");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            /* renamed from: next */
            public ConfigValue mo10245next() {
                return (ConfigValue) it2.next();
            }
        };
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.value.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<ConfigValue> listIterator() {
        return wrapListIterator(this.value.listIterator());
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw weAreImmutable("removeAll");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        String[] split;
        if (this.value.isEmpty()) {
            sb.append("[]");
            return;
        }
        sb.append("[");
        if (configRenderOptions.getFormatted()) {
            sb.append('\n');
        }
        for (AbstractConfigValue abstractConfigValue : this.value) {
            if (configRenderOptions.getOriginComments()) {
                for (String str : abstractConfigValue.mo10176origin().description().split("\n")) {
                    AbstractConfigValue.indent(sb, i + 1, configRenderOptions);
                    sb.append('#');
                    if (!str.isEmpty()) {
                        sb.append(Chars.SPACE);
                    }
                    sb.append(str);
                    sb.append("\n");
                }
            }
            if (configRenderOptions.getComments()) {
                for (String str2 : abstractConfigValue.mo10176origin().comments()) {
                    AbstractConfigValue.indent(sb, i + 1, configRenderOptions);
                    GeneratedOutlineSupport1.outline180(sb, "# ", str2, "\n");
                }
            }
            int i2 = i + 1;
            AbstractConfigValue.indent(sb, i2, configRenderOptions);
            abstractConfigValue.render(sb, i2, z, configRenderOptions);
            sb.append(",");
            if (configRenderOptions.getFormatted()) {
                sb.append('\n');
            }
        }
        sb.setLength(sb.length() - 1);
        if (configRenderOptions.getFormatted()) {
            sb.setLength(sb.length() - 1);
            sb.append('\n');
            AbstractConfigValue.indent(sb, i, configRenderOptions);
        }
        sb.append("]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public ResolveStatus resolveStatus() {
        return ResolveStatus.fromBoolean(this.resolved);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public ResolveResult<? extends SimpleConfigList> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        if (this.resolved) {
            return ResolveResult.make(resolveContext, this);
        }
        if (resolveContext.isRestrictedToChild()) {
            return ResolveResult.make(resolveContext, this);
        }
        try {
            ResolveModifier resolveModifier = new ResolveModifier(resolveContext, resolveSource.pushParent(this));
            return ResolveResult.make(resolveModifier.context, modifyMayThrow(resolveModifier, resolveContext.options().getAllowUnresolved() ? null : ResolveStatus.RESOLVED));
        } catch (AbstractConfigValue.NotPossibleToResolve e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new ConfigException.BugOrBroken("unexpected checked exception", e3);
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw weAreImmutable("retainAll");
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.value.size();
    }

    @Override // java.util.List
    public List<ConfigValue> subList(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue abstractConfigValue : this.value.subList(i, i2)) {
            arrayList.add(abstractConfigValue);
        }
        return arrayList;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.value.toArray();
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        return ConfigValueType.LIST;
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends ConfigValue> collection) {
        throw weAreImmutable("addAll");
    }

    @Override // java.util.List
    /* renamed from: get */
    public ConfigValue get2(int i) {
        return this.value.get(i);
    }

    @Override // java.util.List
    public ListIterator<ConfigValue> listIterator(int i) {
        return wrapListIterator(this.value.listIterator(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy  reason: collision with other method in class */
    public SimpleConfigList mo10238newCopy(ConfigOrigin configOrigin) {
        return new SimpleConfigList(configOrigin, this.value);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: relativized  reason: collision with other method in class */
    public SimpleConfigList mo10251relativized(final Path path) {
        return modify(new AbstractConfigValue.NoExceptionsModifier() { // from class: com.typesafe.config.impl.SimpleConfigList.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.typesafe.config.impl.AbstractConfigValue.NoExceptionsModifier
            public AbstractConfigValue modifyChild(String str, AbstractConfigValue abstractConfigValue) {
                return abstractConfigValue.mo10251relativized(path);
            }
        }, resolveStatus());
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw weAreImmutable(BulkOperationType.remove);
    }

    @Override // com.typesafe.config.impl.Container
    /* renamed from: replaceChild  reason: collision with other method in class */
    public SimpleConfigList mo10252replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        List<AbstractConfigValue> replaceChildInList = AbstractConfigValue.replaceChildInList(this.value, abstractConfigValue, abstractConfigValue2);
        if (replaceChildInList == null) {
            return null;
        }
        return new SimpleConfigList(mo10176origin(), replaceChildInList);
    }

    @Override // java.util.List
    public ConfigValue set(int i, ConfigValue configValue) {
        throw weAreImmutable("set");
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.value.toArray(tArr);
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped  reason: collision with other method in class */
    public List<Object> mo10253unwrapped() {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue abstractConfigValue : this.value) {
            arrayList.add(abstractConfigValue.mo10253unwrapped());
        }
        return arrayList;
    }

    SimpleConfigList(ConfigOrigin configOrigin, List<AbstractConfigValue> list, ResolveStatus resolveStatus) {
        super(configOrigin);
        this.value = list;
        this.resolved = resolveStatus == ResolveStatus.RESOLVED;
        if (resolveStatus == ResolveStatus.fromValues(list)) {
            return;
        }
        throw new ConfigException.BugOrBroken("SimpleConfigList created with wrong resolve status: " + this);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(ConfigValue configValue) {
        throw weAreImmutable(BulkOperationType.add);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    /* renamed from: remove */
    public ConfigValue mo10240remove(int i) {
        throw weAreImmutable(BulkOperationType.remove);
    }

    @Override // java.util.List
    public void add(int i, ConfigValue configValue) {
        throw weAreImmutable(BulkOperationType.add);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue, com.typesafe.config.ConfigValue
    /* renamed from: withOrigin  reason: collision with other method in class */
    public SimpleConfigList mo10244withOrigin(ConfigOrigin configOrigin) {
        return (SimpleConfigList) super.mo10244withOrigin(configOrigin);
    }
}
