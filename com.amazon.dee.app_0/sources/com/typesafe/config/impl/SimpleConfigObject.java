package com.typesafe.config.impl;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.impl.AbstractConfigValue;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.util.Chars;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SimpleConfigObject extends AbstractConfigObject implements Serializable {
    private static final String EMPTY_NAME = "empty config";
    private static final SimpleConfigObject emptyInstance = empty(SimpleConfigOrigin.newSimple(EMPTY_NAME));
    private static final long serialVersionUID = 2;
    private final boolean ignoresFallbacks;
    private final boolean resolved;
    private final Map<String, AbstractConfigValue> value;

    /* loaded from: classes3.dex */
    private static final class RenderComparator implements Comparator<String>, Serializable {
        private static final long serialVersionUID = 1;

        private RenderComparator() {
        }

        private static boolean isAllDigits(String str) {
            int length = str.length();
            if (length == 0) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            boolean isAllDigits = isAllDigits(str);
            boolean isAllDigits2 = isAllDigits(str2);
            if (!isAllDigits || !isAllDigits2) {
                if (isAllDigits) {
                    return -1;
                }
                if (!isAllDigits2) {
                    return str.compareTo(str2);
                }
                return 1;
            }
            return Integer.compare(Integer.parseInt(str), Integer.parseInt(str2));
        }
    }

    /* loaded from: classes3.dex */
    private static final class ResolveModifier implements AbstractConfigValue.Modifier {
        ResolveContext context;
        final Path originalRestrict;
        final ResolveSource source;

        ResolveModifier(ResolveContext resolveContext, ResolveSource resolveSource) {
            this.context = resolveContext;
            this.source = resolveSource;
            this.originalRestrict = resolveContext.restrictToChild();
        }

        @Override // com.typesafe.config.impl.AbstractConfigValue.Modifier
        public AbstractConfigValue modifyChildMayThrow(String str, AbstractConfigValue abstractConfigValue) throws AbstractConfigValue.NotPossibleToResolve {
            Path remainder;
            if (this.context.isRestrictedToChild()) {
                if (!str.equals(this.context.restrictToChild().first()) || (remainder = this.context.restrictToChild().remainder()) == null) {
                    return abstractConfigValue;
                }
                ResolveResult<? extends AbstractConfigValue> resolve = this.context.restrict(remainder).resolve(abstractConfigValue, this.source);
                this.context = resolve.context.unrestricted().restrict(this.originalRestrict);
                return resolve.value;
            }
            ResolveResult<? extends AbstractConfigValue> resolve2 = this.context.unrestricted().resolve(abstractConfigValue, this.source);
            this.context = resolve2.context.unrestricted().restrict(this.originalRestrict);
            return resolve2.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleConfigObject(ConfigOrigin configOrigin, Map<String, AbstractConfigValue> map, ResolveStatus resolveStatus, boolean z) {
        super(configOrigin);
        if (map != null) {
            this.value = map;
            this.resolved = resolveStatus == ResolveStatus.RESOLVED;
            this.ignoresFallbacks = z;
            if (resolveStatus == ResolveStatus.fromValues(map.values())) {
                return;
            }
            throw new ConfigException.BugOrBroken("Wrong resolved status on " + this);
        }
        throw new ConfigException.BugOrBroken("creating config object with null map");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final SimpleConfigObject empty() {
        return emptyInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final SimpleConfigObject emptyMissing(ConfigOrigin configOrigin) {
        return new SimpleConfigObject(SimpleConfigOrigin.newSimple(configOrigin.description() + " (not found)"), Collections.emptyMap());
    }

    private static boolean mapEquals(Map<String, ConfigValue> map, Map<String, ConfigValue> map2) {
        if (map == map2) {
            return true;
        }
        Set<String> keySet = map.keySet();
        if (!keySet.equals(map2.keySet())) {
            return false;
        }
        for (String str : keySet) {
            if (!map.get(str).equals(map2.get(str))) {
                return false;
            }
        }
        return true;
    }

    private static int mapHash(Map<String, ConfigValue> map) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.addAll(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        for (String str : arrayList) {
            i += map.get(str).hashCode();
        }
        return ((arrayList.hashCode() + 41) * 41) + i;
    }

    private SimpleConfigObject modify(AbstractConfigValue.NoExceptionsModifier noExceptionsModifier) {
        try {
            return modifyMayThrow(noExceptionsModifier);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new ConfigException.BugOrBroken("unexpected checked exception", e2);
        }
    }

    private SimpleConfigObject modifyMayThrow(AbstractConfigValue.Modifier modifier) throws Exception {
        HashMap hashMap = null;
        for (String str : keySet()) {
            AbstractConfigValue abstractConfigValue = this.value.get(str);
            AbstractConfigValue modifyChildMayThrow = modifier.modifyChildMayThrow(str, abstractConfigValue);
            if (modifyChildMayThrow != abstractConfigValue) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(str, modifyChildMayThrow);
            }
        }
        if (hashMap == null) {
            return this;
        }
        HashMap hashMap2 = new HashMap();
        boolean z = false;
        for (String str2 : keySet()) {
            if (hashMap.containsKey(str2)) {
                AbstractConfigValue abstractConfigValue2 = (AbstractConfigValue) hashMap.get(str2);
                if (abstractConfigValue2 != null) {
                    hashMap2.put(str2, abstractConfigValue2);
                    if (abstractConfigValue2.resolveStatus() == ResolveStatus.UNRESOLVED) {
                        z = true;
                    }
                }
            } else {
                AbstractConfigValue abstractConfigValue3 = this.value.get(str2);
                hashMap2.put(str2, abstractConfigValue3);
                if (abstractConfigValue3.resolveStatus() == ResolveStatus.UNRESOLVED) {
                    z = true;
                }
            }
        }
        return new SimpleConfigObject(mo10176origin(), hashMap2, z ? ResolveStatus.UNRESOLVED : ResolveStatus.RESOLVED, ignoresFallbacks());
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    public AbstractConfigValue attemptPeekWithPartialResolve(String str) {
        return this.value.get(str);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    protected boolean canEqual(Object obj) {
        return obj instanceof ConfigObject;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.value.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.value.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, ConfigValue>> entrySet() {
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, AbstractConfigValue> entry : this.value.entrySet()) {
            hashSet.add(new AbstractMap.SimpleImmutableEntry(entry.getKey(), entry.getValue()));
        }
        return hashSet;
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean equals(Object obj) {
        return (obj instanceof ConfigObject) && canEqual(obj) && mapEquals(this, (ConfigObject) obj);
    }

    @Override // com.typesafe.config.impl.Container
    public boolean hasDescendant(AbstractConfigValue abstractConfigValue) {
        for (AbstractConfigValue abstractConfigValue2 : this.value.values()) {
            if (abstractConfigValue2 == abstractConfigValue) {
                return true;
            }
        }
        for (AbstractConfigValue abstractConfigValue3 : this.value.values()) {
            if ((abstractConfigValue3 instanceof Container) && ((Container) abstractConfigValue3).hasDescendant(abstractConfigValue)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public int hashCode() {
        return mapHash(this);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    protected boolean ignoresFallbacks() {
        return this.ignoresFallbacks;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.value.keySet();
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.impl.AbstractConfigValue
    protected void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        int i2;
        char c;
        int i3;
        if (isEmpty()) {
            sb.append("{}");
        } else {
            boolean z2 = configRenderOptions.getJson() || !z;
            if (z2) {
                int i4 = i + 1;
                sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
                if (configRenderOptions.getFormatted()) {
                    sb.append('\n');
                }
                i2 = i4;
            } else {
                i2 = i;
            }
            String[] strArr = (String[]) keySet().toArray(new String[size()]);
            Arrays.sort(strArr, new RenderComparator());
            int length = strArr.length;
            int i5 = 0;
            int i6 = 0;
            while (i6 < length) {
                String str = strArr[i6];
                AbstractConfigValue abstractConfigValue = this.value.get(str);
                if (configRenderOptions.getOriginComments()) {
                    String[] split = abstractConfigValue.mo10176origin().description().split("\n");
                    int length2 = split.length;
                    int i7 = 0;
                    while (i7 < length2) {
                        String str2 = split[i7];
                        String[] strArr2 = split;
                        AbstractConfigValue.indent(sb, i + 1, configRenderOptions);
                        sb.append('#');
                        if (!str2.isEmpty()) {
                            sb.append(Chars.SPACE);
                        }
                        sb.append(str2);
                        sb.append("\n");
                        i7++;
                        split = strArr2;
                    }
                }
                if (configRenderOptions.getComments()) {
                    for (String str3 : abstractConfigValue.mo10176origin().comments()) {
                        AbstractConfigValue.indent(sb, i2, configRenderOptions);
                        sb.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        if (!str3.startsWith(" ")) {
                            sb.append(Chars.SPACE);
                        }
                        sb.append(str3);
                        sb.append("\n");
                    }
                }
                AbstractConfigValue.indent(sb, i2, configRenderOptions);
                int i8 = i6;
                abstractConfigValue.render(sb, i2, false, str, configRenderOptions);
                if (configRenderOptions.getFormatted()) {
                    if (configRenderOptions.getJson()) {
                        sb.append(",");
                        i3 = 2;
                        c = '\n';
                    } else {
                        c = '\n';
                        i3 = 1;
                    }
                    sb.append(c);
                    i5 = i3;
                } else {
                    sb.append(",");
                    i5 = 1;
                }
                i6 = i8 + 1;
            }
            sb.setLength(sb.length() - i5);
            if (z2) {
                if (configRenderOptions.getFormatted()) {
                    sb.append('\n');
                    if (z2) {
                        AbstractConfigValue.indent(sb, i, configRenderOptions);
                    }
                }
                sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        }
        if (!z || !configRenderOptions.getFormatted()) {
            return;
        }
        sb.append('\n');
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    ResolveStatus resolveStatus() {
        return ResolveStatus.fromBoolean(this.resolved);
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.impl.AbstractConfigValue
    ResolveResult<? extends AbstractConfigObject> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        if (resolveStatus() == ResolveStatus.RESOLVED) {
            return ResolveResult.make(resolveContext, this);
        }
        try {
            ResolveModifier resolveModifier = new ResolveModifier(resolveContext, resolveSource.pushParent(this));
            return ResolveResult.make(resolveModifier.context, modifyMayThrow(resolveModifier)).asObjectResult();
        } catch (AbstractConfigValue.NotPossibleToResolve e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new ConfigException.BugOrBroken("unexpected checked exception", e3);
        }
    }

    @Override // java.util.Map
    public int size() {
        return this.value.size();
    }

    @Override // java.util.Map
    public Collection<ConfigValue> values() {
        return new HashSet(this.value.values());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final SimpleConfigObject empty(ConfigOrigin configOrigin) {
        if (configOrigin == null) {
            return empty();
        }
        return new SimpleConfigObject(configOrigin, Collections.emptyMap());
    }

    private SimpleConfigObject newCopy(ResolveStatus resolveStatus, ConfigOrigin configOrigin, boolean z) {
        return new SimpleConfigObject(configOrigin, this.value, resolveStatus, z);
    }

    @Override // com.typesafe.config.impl.Container
    /* renamed from: replaceChild  reason: collision with other method in class */
    public SimpleConfigObject mo10252replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        HashMap hashMap = new HashMap(this.value);
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry.getValue() == abstractConfigValue) {
                if (abstractConfigValue2 != null) {
                    entry.setValue(abstractConfigValue2);
                } else {
                    hashMap.remove(entry.getKey());
                }
                return new SimpleConfigObject(mo10176origin(), hashMap, ResolveStatus.fromValues(hashMap.values()), this.ignoresFallbacks);
            }
        }
        throw new ConfigException.BugOrBroken("SimpleConfigObject.replaceChild did not find " + abstractConfigValue + " in " + this);
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped  reason: collision with other method in class */
    public Map<String, Object> mo10253unwrapped() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, AbstractConfigValue> entry : this.value.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().mo10253unwrapped());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: withFallbacksIgnored  reason: collision with other method in class */
    public SimpleConfigObject mo10254withFallbacksIgnored() {
        return this.ignoresFallbacks ? this : newCopy(resolveStatus(), mo10176origin(), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: withOnlyPath  reason: collision with other method in class */
    public SimpleConfigObject mo10256withOnlyPath(Path path) {
        SimpleConfigObject mo10257withOnlyPathOrNull = mo10257withOnlyPathOrNull(path);
        return mo10257withOnlyPathOrNull == null ? new SimpleConfigObject(mo10176origin(), Collections.emptyMap(), ResolveStatus.RESOLVED, this.ignoresFallbacks) : mo10257withOnlyPathOrNull;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: withOnlyPathOrNull  reason: collision with other method in class */
    public SimpleConfigObject mo10257withOnlyPathOrNull(Path path) {
        String first = path.first();
        Path remainder = path.remainder();
        AbstractConfigValue abstractConfigValue = this.value.get(first);
        if (remainder != null) {
            abstractConfigValue = (abstractConfigValue == null || !(abstractConfigValue instanceof AbstractConfigObject)) ? null : ((AbstractConfigObject) abstractConfigValue).mo10257withOnlyPathOrNull(remainder);
        }
        if (abstractConfigValue == null) {
            return null;
        }
        return new SimpleConfigObject(mo10176origin(), Collections.singletonMap(first, abstractConfigValue), abstractConfigValue.resolveStatus(), this.ignoresFallbacks);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: withoutPath  reason: collision with other method in class */
    public SimpleConfigObject mo10261withoutPath(Path path) {
        String first = path.first();
        Path remainder = path.remainder();
        AbstractConfigValue abstractConfigValue = this.value.get(first);
        if (abstractConfigValue != null && remainder != null && (abstractConfigValue instanceof AbstractConfigObject)) {
            AbstractConfigObject mo10261withoutPath = ((AbstractConfigObject) abstractConfigValue).mo10261withoutPath(remainder);
            HashMap hashMap = new HashMap(this.value);
            hashMap.put(first, mo10261withoutPath);
            return new SimpleConfigObject(mo10176origin(), hashMap, ResolveStatus.fromValues(hashMap.values()), this.ignoresFallbacks);
        } else if (remainder != null || abstractConfigValue == null) {
            return this;
        } else {
            HashMap hashMap2 = new HashMap(this.value.size() - 1);
            for (Map.Entry<String, AbstractConfigValue> entry : this.value.entrySet()) {
                if (!entry.getKey().equals(first)) {
                    hashMap2.put(entry.getKey(), entry.getValue());
                }
            }
            return new SimpleConfigObject(mo10176origin(), hashMap2, ResolveStatus.fromValues(hashMap2.values()), this.ignoresFallbacks);
        }
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.ConfigObject, java.util.Map
    /* renamed from: get  reason: collision with other method in class */
    public ConfigValue mo10248get(Object obj) {
        return this.value.get(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: mergedWithObject */
    public SimpleConfigObject mo10249mergedWithObject(AbstractConfigObject abstractConfigObject) {
        requireNotIgnoringFallbacks();
        if (abstractConfigObject instanceof SimpleConfigObject) {
            SimpleConfigObject simpleConfigObject = (SimpleConfigObject) abstractConfigObject;
            HashMap hashMap = new HashMap();
            HashSet<String> hashSet = new HashSet();
            hashSet.addAll(keySet());
            hashSet.addAll(simpleConfigObject.keySet());
            boolean z = true;
            boolean z2 = false;
            for (String str : hashSet) {
                AbstractConfigValue abstractConfigValue = this.value.get(str);
                AbstractConfigValue abstractConfigValue2 = simpleConfigObject.value.get(str);
                if (abstractConfigValue != null) {
                    abstractConfigValue2 = abstractConfigValue2 == null ? abstractConfigValue : abstractConfigValue.mo10234withFallback((ConfigMergeable) abstractConfigValue2);
                }
                hashMap.put(str, abstractConfigValue2);
                if (abstractConfigValue != abstractConfigValue2) {
                    z2 = true;
                }
                if (abstractConfigValue2.resolveStatus() == ResolveStatus.UNRESOLVED) {
                    z = false;
                }
            }
            ResolveStatus fromBoolean = ResolveStatus.fromBoolean(z);
            boolean ignoresFallbacks = simpleConfigObject.ignoresFallbacks();
            if (z2) {
                return new SimpleConfigObject(AbstractConfigObject.mergeOrigins(this, simpleConfigObject), hashMap, fromBoolean, ignoresFallbacks);
            }
            return (fromBoolean == resolveStatus() && ignoresFallbacks == ignoresFallbacks()) ? this : newCopy(fromBoolean, mo10176origin(), ignoresFallbacks);
        }
        throw new ConfigException.BugOrBroken("should not be reached (merging non-SimpleConfigObject)");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: newCopy  reason: collision with other method in class */
    public SimpleConfigObject mo10250newCopy(ResolveStatus resolveStatus, ConfigOrigin configOrigin) {
        return newCopy(resolveStatus, configOrigin, this.ignoresFallbacks);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: relativized */
    public SimpleConfigObject mo10251relativized(final Path path) {
        return modify(new AbstractConfigValue.NoExceptionsModifier() { // from class: com.typesafe.config.impl.SimpleConfigObject.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.typesafe.config.impl.AbstractConfigValue.NoExceptionsModifier
            public AbstractConfigValue modifyChild(String str, AbstractConfigValue abstractConfigValue) {
                return abstractConfigValue.mo10251relativized(path);
            }
        });
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.ConfigObject
    /* renamed from: withOnlyKey */
    public SimpleConfigObject mo10255withOnlyKey(String str) {
        return mo10256withOnlyPath(Path.newKey(str));
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.ConfigObject
    /* renamed from: withoutKey */
    public SimpleConfigObject mo10260withoutKey(String str) {
        return mo10261withoutPath(Path.newKey(str));
    }

    @Override // com.typesafe.config.impl.AbstractConfigObject, com.typesafe.config.ConfigObject
    /* renamed from: withValue */
    public SimpleConfigObject mo10258withValue(String str, ConfigValue configValue) {
        Map map;
        if (configValue != null) {
            if (this.value.isEmpty()) {
                map = Collections.singletonMap(str, (AbstractConfigValue) configValue);
            } else {
                HashMap hashMap = new HashMap(this.value);
                hashMap.put(str, (AbstractConfigValue) configValue);
                map = hashMap;
            }
            return new SimpleConfigObject(mo10176origin(), map, ResolveStatus.fromValues(map.values()), this.ignoresFallbacks);
        }
        throw new ConfigException.BugOrBroken("Trying to store null ConfigValue in a ConfigObject");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleConfigObject(ConfigOrigin configOrigin, Map<String, AbstractConfigValue> map) {
        this(configOrigin, map, ResolveStatus.fromValues(map.values()), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigObject
    /* renamed from: withValue  reason: collision with other method in class */
    public SimpleConfigObject mo10259withValue(Path path, ConfigValue configValue) {
        String first = path.first();
        Path remainder = path.remainder();
        if (remainder == null) {
            return mo10258withValue(first, configValue);
        }
        AbstractConfigValue abstractConfigValue = this.value.get(first);
        if (abstractConfigValue != null && (abstractConfigValue instanceof AbstractConfigObject)) {
            return mo10258withValue(first, (ConfigValue) ((AbstractConfigObject) abstractConfigValue).mo10259withValue(remainder, configValue));
        }
        return mo10258withValue(first, (ConfigValue) ((AbstractConfigValue) configValue).atPath(SimpleConfigOrigin.newSimple(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("withValue("), remainder.render(), ")")), remainder).mo10232root());
    }
}
