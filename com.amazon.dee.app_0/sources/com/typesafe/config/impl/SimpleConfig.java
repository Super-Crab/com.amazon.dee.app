package com.typesafe.config.impl;

import com.amazon.alexa.presence.service.PresenceServiceConfiguration;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.callercontext.ContextChain;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigMemorySize;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigResolveOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SimpleConfig implements Config, MergeableValue, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 1;
    private final AbstractConfigObject object;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.typesafe.config.impl.SimpleConfig$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit = new int[ChronoUnit.values().length];

        static {
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.DAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.WEEKS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MONTHS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.YEARS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum MemoryUnit {
        BYTES("", 1024, 0),
        KILOBYTES("kilo", 1000, 1),
        MEGABYTES("mega", 1000, 2),
        GIGABYTES("giga", 1000, 3),
        TERABYTES("tera", 1000, 4),
        PETABYTES("peta", 1000, 5),
        EXABYTES("exa", 1000, 6),
        ZETTABYTES("zetta", 1000, 7),
        YOTTABYTES("yotta", 1000, 8),
        KIBIBYTES("kibi", 1024, 1),
        MEBIBYTES("mebi", 1024, 2),
        GIBIBYTES("gibi", 1024, 3),
        TEBIBYTES("tebi", 1024, 4),
        PEBIBYTES("pebi", 1024, 5),
        EXBIBYTES("exbi", 1024, 6),
        ZEBIBYTES("zebi", 1024, 7),
        YOBIBYTES("yobi", 1024, 8);
        
        private static Map<String, MemoryUnit> unitsMap = makeUnitsMap();
        final BigInteger bytes;
        final int power;
        final int powerOf;
        final String prefix;

        MemoryUnit(String str, int i, int i2) {
            this.prefix = str;
            this.powerOf = i;
            this.power = i2;
            this.bytes = BigInteger.valueOf(i).pow(i2);
        }

        private static Map<String, MemoryUnit> makeUnitsMap() {
            MemoryUnit[] values;
            HashMap hashMap = new HashMap();
            for (MemoryUnit memoryUnit : values()) {
                hashMap.put(memoryUnit.prefix + "byte", memoryUnit);
                hashMap.put(memoryUnit.prefix + "bytes", memoryUnit);
                if (memoryUnit.prefix.length() == 0) {
                    hashMap.put("b", memoryUnit);
                    hashMap.put("B", memoryUnit);
                    hashMap.put("", memoryUnit);
                } else {
                    String substring = memoryUnit.prefix.substring(0, 1);
                    String upperCase = substring.toUpperCase();
                    int i = memoryUnit.powerOf;
                    if (i == 1024) {
                        hashMap.put(substring, memoryUnit);
                        hashMap.put(upperCase, memoryUnit);
                        hashMap.put(upperCase + ContextChain.TAG_INFRA, memoryUnit);
                        hashMap.put(upperCase + "iB", memoryUnit);
                    } else if (i == 1000) {
                        if (memoryUnit.power == 1) {
                            hashMap.put(substring + "B", memoryUnit);
                        } else {
                            hashMap.put(upperCase + "B", memoryUnit);
                        }
                    } else {
                        throw new RuntimeException("broken MemoryUnit enum");
                    }
                }
            }
            return hashMap;
        }

        static MemoryUnit parseUnit(String str) {
            return unitsMap.get(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleConfig(AbstractConfigObject abstractConfigObject) {
        this.object = abstractConfigObject;
    }

    private static void addMissing(List<ConfigException.ValidationProblem> list, String str, Path path, ConfigOrigin configOrigin) {
        addProblem(list, path, configOrigin, GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline107("No setting at '"), path.render(), "', expecting: ", str));
    }

    private static void addProblem(List<ConfigException.ValidationProblem> list, Path path, ConfigOrigin configOrigin, String str) {
        list.add(new ConfigException.ValidationProblem(path.render(), configOrigin, str));
    }

    private static void addWrongType(List<ConfigException.ValidationProblem> list, String str, AbstractConfigValue abstractConfigValue, Path path) {
        SimpleConfigOrigin mo10176origin = abstractConfigValue.mo10176origin();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Wrong value type at '");
        GeneratedOutlineSupport1.outline181(outline107, path.render(), "', expecting: ", str, " but got: ");
        outline107.append(getDesc(abstractConfigValue));
        addProblem(list, path, mo10176origin, outline107.toString());
    }

    private static void checkListCompatibility(Path path, SimpleConfigList simpleConfigList, SimpleConfigList simpleConfigList2, List<ConfigException.ValidationProblem> list) {
        if (simpleConfigList.isEmpty() || simpleConfigList2.isEmpty()) {
            return;
        }
        AbstractConfigValue abstractConfigValue = simpleConfigList.get2(0);
        Iterator<ConfigValue> it2 = simpleConfigList2.iterator();
        while (it2.hasNext()) {
            AbstractConfigValue abstractConfigValue2 = (AbstractConfigValue) it2.next();
            if (!haveCompatibleTypes(abstractConfigValue, abstractConfigValue2)) {
                SimpleConfigOrigin mo10176origin = abstractConfigValue2.mo10176origin();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("List at '");
                outline107.append(path.render());
                outline107.append("' contains wrong value type, expecting list of ");
                outline107.append(getDesc(abstractConfigValue));
                outline107.append(" but got element of type ");
                outline107.append(getDesc(abstractConfigValue2));
                addProblem(list, path, mo10176origin, outline107.toString());
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkValid(Path path, ConfigValueType configValueType, AbstractConfigValue abstractConfigValue, List<ConfigException.ValidationProblem> list) {
        if (haveCompatibleTypes(configValueType, abstractConfigValue)) {
            ConfigValueType configValueType2 = ConfigValueType.LIST;
            if (configValueType != configValueType2 || !(abstractConfigValue instanceof SimpleConfigObject) || (DefaultTransformer.transform(abstractConfigValue, configValueType2) instanceof SimpleConfigList)) {
                return;
            }
            addWrongType(list, configValueType, abstractConfigValue, path);
            return;
        }
        addWrongType(list, configValueType, abstractConfigValue, path);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void checkValidObject(Path path, AbstractConfigObject abstractConfigObject, AbstractConfigObject abstractConfigObject2, List<ConfigException.ValidationProblem> list) {
        Path newKey;
        for (Map.Entry<String, ConfigValue> entry : abstractConfigObject.entrySet()) {
            String key = entry.getKey();
            if (path != null) {
                newKey = Path.newKey(key).prepend(path);
            } else {
                newKey = Path.newKey(key);
            }
            AbstractConfigValue mo10248get = abstractConfigObject2.mo10248get((Object) key);
            if (mo10248get == null) {
                addMissing(list, entry.getValue(), newKey, abstractConfigObject2.mo10176origin());
            } else {
                checkValid(newKey, entry.getValue(), mo10248get, list);
            }
        }
    }

    private static boolean couldBeNull(AbstractConfigValue abstractConfigValue) {
        return DefaultTransformer.transform(abstractConfigValue, ConfigValueType.NULL).valueType() == ConfigValueType.NULL;
    }

    private static AbstractConfigValue findKey(AbstractConfigObject abstractConfigObject, String str, ConfigValueType configValueType, Path path) {
        return throwIfNull(findKeyOrNull(abstractConfigObject, str, configValueType, path), configValueType, path);
    }

    private static AbstractConfigValue findKeyOrNull(AbstractConfigObject abstractConfigObject, String str, ConfigValueType configValueType, Path path) {
        AbstractConfigValue peekAssumingResolved = abstractConfigObject.peekAssumingResolved(str, path);
        if (peekAssumingResolved != null) {
            if (configValueType != null) {
                peekAssumingResolved = DefaultTransformer.transform(peekAssumingResolved, configValueType);
            }
            if (configValueType != null && peekAssumingResolved.valueType() != configValueType && peekAssumingResolved.valueType() != ConfigValueType.NULL) {
                throw new ConfigException.WrongType(peekAssumingResolved.mo10176origin(), path.render(), configValueType.name(), peekAssumingResolved.valueType().name());
            }
            return peekAssumingResolved;
        }
        throw new ConfigException.Missing(path.render());
    }

    private static AbstractConfigValue findOrNull(AbstractConfigObject abstractConfigObject, Path path, ConfigValueType configValueType, Path path2) {
        try {
            String first = path.first();
            Path remainder = path.remainder();
            if (remainder == null) {
                return findKeyOrNull(abstractConfigObject, first, configValueType, path2);
            }
            return findOrNull((AbstractConfigObject) findKey(abstractConfigObject, first, ConfigValueType.OBJECT, path2.subPath(0, path2.length() - remainder.length())), remainder, configValueType, path2);
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(path, e);
        }
    }

    private static void findPaths(Set<Map.Entry<String, ConfigValue>> set, Path path, AbstractConfigObject abstractConfigObject) {
        for (Map.Entry<String, ConfigValue> entry : abstractConfigObject.entrySet()) {
            ConfigValue value = entry.getValue();
            Path newKey = Path.newKey(entry.getKey());
            if (path != null) {
                newKey = newKey.prepend(path);
            }
            if (value instanceof AbstractConfigObject) {
                findPaths(set, newKey, (AbstractConfigObject) value);
            } else if (!(value instanceof ConfigNull)) {
                set.add(new AbstractMap.SimpleImmutableEntry(newKey.render(), value));
            }
        }
    }

    private ConfigNumber getConfigNumber(String str) {
        return (ConfigNumber) find(str, ConfigValueType.NUMBER);
    }

    private static String getDesc(ConfigValueType configValueType) {
        return configValueType.name().toLowerCase();
    }

    private <T extends Enum<T>> T getEnumValue(String str, Class<T> cls, ConfigValue configValue) {
        String str2 = (String) configValue.mo10253unwrapped();
        try {
            return (T) Enum.valueOf(cls, str2);
        } catch (IllegalArgumentException unused) {
            ArrayList arrayList = new ArrayList();
            T[] enumConstants = cls.getEnumConstants();
            if (enumConstants != null) {
                for (T t : enumConstants) {
                    arrayList.add(t.name());
                }
            }
            throw new ConfigException.BadValue(configValue.mo10176origin(), str, String.format("The enum class %s has no constant of the name '%s' (should be one of %s.)", cls.getSimpleName(), str2, arrayList));
        }
    }

    private <T> List<T> getHomogeneousUnwrappedList(String str, ConfigValueType configValueType) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue abstractConfigValue : getList(str)) {
            if (configValueType != null) {
                abstractConfigValue = DefaultTransformer.transform(abstractConfigValue, configValueType);
            }
            if (abstractConfigValue.valueType() == configValueType) {
                arrayList.add(abstractConfigValue.mo10253unwrapped());
            } else {
                SimpleConfigOrigin mo10176origin = abstractConfigValue.mo10176origin();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("list of ");
                outline107.append(configValueType.name());
                String sb = outline107.toString();
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("list of ");
                outline1072.append(abstractConfigValue.valueType().name());
                throw new ConfigException.WrongType(mo10176origin, str, sb, outline1072.toString());
            }
        }
        return arrayList;
    }

    private <T extends ConfigValue> List<T> getHomogeneousWrappedList(String str, ConfigValueType configValueType) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue abstractConfigValue : getList(str)) {
            if (configValueType != null) {
                abstractConfigValue = DefaultTransformer.transform(abstractConfigValue, configValueType);
            }
            if (abstractConfigValue.valueType() == configValueType) {
                arrayList.add(abstractConfigValue);
            } else {
                SimpleConfigOrigin mo10176origin = abstractConfigValue.mo10176origin();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("list of ");
                outline107.append(configValueType.name());
                String sb = outline107.toString();
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("list of ");
                outline1072.append(abstractConfigValue.valueType().name());
                throw new ConfigException.WrongType(mo10176origin, str, sb, outline1072.toString());
            }
        }
        return arrayList;
    }

    private static String getUnits(String str) {
        int length = str.length() - 1;
        while (length >= 0 && Character.isLetter(str.charAt(length))) {
            length--;
        }
        return str.substring(length + 1);
    }

    private ConfigValue hasPathPeek(String str) {
        Path newPath = Path.newPath(str);
        try {
            return this.object.peekPath(newPath);
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(newPath, e);
        }
    }

    private static boolean haveCompatibleTypes(ConfigValue configValue, AbstractConfigValue abstractConfigValue) {
        if (couldBeNull((AbstractConfigValue) configValue)) {
            return true;
        }
        return haveCompatibleTypes(configValue.valueType(), abstractConfigValue);
    }

    public static long parseBytes(String str, ConfigOrigin configOrigin, String str2) {
        BigInteger bigInteger;
        String unicodeTrim = ConfigImplUtil.unicodeTrim(str);
        String units = getUnits(unicodeTrim);
        String unicodeTrim2 = ConfigImplUtil.unicodeTrim(unicodeTrim.substring(0, unicodeTrim.length() - units.length()));
        if (unicodeTrim2.length() != 0) {
            MemoryUnit parseUnit = MemoryUnit.parseUnit(units);
            if (parseUnit != null) {
                try {
                    if (unicodeTrim2.matches("[0-9]+")) {
                        bigInteger = parseUnit.bytes.multiply(new BigInteger(unicodeTrim2));
                    } else {
                        bigInteger = new BigDecimal(parseUnit.bytes).multiply(new BigDecimal(unicodeTrim2)).toBigInteger();
                    }
                    if (bigInteger.bitLength() < 64) {
                        return bigInteger.longValue();
                    }
                    throw new ConfigException.BadValue(configOrigin, str2, "size-in-bytes value is out of range for a 64-bit long: '" + str + "'");
                } catch (NumberFormatException unused) {
                    throw new ConfigException.BadValue(configOrigin, str2, GeneratedOutlineSupport1.outline75("Could not parse size-in-bytes number '", unicodeTrim2, "'"));
                }
            }
            throw new ConfigException.BadValue(configOrigin, str2, GeneratedOutlineSupport1.outline75("Could not parse size-in-bytes unit '", units, "' (try k, K, kB, KiB, kilobytes, kibibytes)"));
        }
        throw new ConfigException.BadValue(configOrigin, str2, GeneratedOutlineSupport1.outline75("No number in size-in-bytes value '", str, "'"));
    }

    public static long parseDuration(String str, ConfigOrigin configOrigin, String str2) {
        TimeUnit timeUnit;
        String unicodeTrim = ConfigImplUtil.unicodeTrim(str);
        String units = getUnits(unicodeTrim);
        String unicodeTrim2 = ConfigImplUtil.unicodeTrim(unicodeTrim.substring(0, unicodeTrim.length() - units.length()));
        if (unicodeTrim2.length() != 0) {
            String outline72 = (units.length() <= 2 || units.endsWith("s")) ? units : GeneratedOutlineSupport1.outline72(units, "s");
            if (!outline72.equals("") && !outline72.equals("ms") && !outline72.equals("millis") && !outline72.equals("milliseconds")) {
                if (!outline72.equals(PresenceServiceConfiguration.US) && !outline72.equals("micros") && !outline72.equals("microseconds")) {
                    if (!outline72.equals("ns") && !outline72.equals("nanos") && !outline72.equals("nanoseconds")) {
                        if (!outline72.equals("d") && !outline72.equals("days")) {
                            if (!outline72.equals("h") && !outline72.equals("hours")) {
                                if (!outline72.equals("s") && !outline72.equals(BizMetricsConstants.DURATION_METADATA_NAME)) {
                                    if (!outline72.equals("m") && !outline72.equals("minutes")) {
                                        throw new ConfigException.BadValue(configOrigin, str2, GeneratedOutlineSupport1.outline75("Could not parse time unit '", units, "' (try ns, us, ms, s, m, h, d)"));
                                    }
                                    timeUnit = TimeUnit.MINUTES;
                                } else {
                                    timeUnit = TimeUnit.SECONDS;
                                }
                            } else {
                                timeUnit = TimeUnit.HOURS;
                            }
                        } else {
                            timeUnit = TimeUnit.DAYS;
                        }
                    } else {
                        timeUnit = TimeUnit.NANOSECONDS;
                    }
                } else {
                    timeUnit = TimeUnit.MICROSECONDS;
                }
            } else {
                timeUnit = TimeUnit.MILLISECONDS;
            }
            try {
                if (unicodeTrim2.matches("[+-]?[0-9]+")) {
                    return timeUnit.toNanos(Long.parseLong(unicodeTrim2));
                }
                return (long) (Double.parseDouble(unicodeTrim2) * timeUnit.toNanos(1L));
            } catch (NumberFormatException unused) {
                throw new ConfigException.BadValue(configOrigin, str2, GeneratedOutlineSupport1.outline75("Could not parse duration number '", unicodeTrim2, "'"));
            }
        }
        throw new ConfigException.BadValue(configOrigin, str2, GeneratedOutlineSupport1.outline75("No number in duration value '", str, "'"));
    }

    public static Period parsePeriod(String str, ConfigOrigin configOrigin, String str2) {
        ChronoUnit chronoUnit;
        String unicodeTrim = ConfigImplUtil.unicodeTrim(str);
        String units = getUnits(unicodeTrim);
        String unicodeTrim2 = ConfigImplUtil.unicodeTrim(unicodeTrim.substring(0, unicodeTrim.length() - units.length()));
        if (unicodeTrim2.length() != 0) {
            String outline72 = (units.length() <= 2 || units.endsWith("s")) ? units : GeneratedOutlineSupport1.outline72(units, "s");
            if (!outline72.equals("") && !outline72.equals("d") && !outline72.equals("days")) {
                if (!outline72.equals("w") && !outline72.equals("weeks")) {
                    if (!outline72.equals("m") && !outline72.equals("mo") && !outline72.equals("months")) {
                        if (!outline72.equals(ReactProperties.HereMapMarker.Y) && !outline72.equals("years")) {
                            throw new ConfigException.BadValue(configOrigin, str2, GeneratedOutlineSupport1.outline75("Could not parse time unit '", units, "' (try d, w, mo, y)"));
                        }
                        chronoUnit = ChronoUnit.YEARS;
                    } else {
                        chronoUnit = ChronoUnit.MONTHS;
                    }
                } else {
                    chronoUnit = ChronoUnit.WEEKS;
                }
            } else {
                chronoUnit = ChronoUnit.DAYS;
            }
            try {
                return periodOf(Integer.parseInt(unicodeTrim2), chronoUnit);
            } catch (NumberFormatException unused) {
                throw new ConfigException.BadValue(configOrigin, str2, GeneratedOutlineSupport1.outline75("Could not parse duration number '", unicodeTrim2, "'"));
            }
        }
        throw new ConfigException.BadValue(configOrigin, str2, GeneratedOutlineSupport1.outline75("No number in period value '", str, "'"));
    }

    private AbstractConfigValue peekPath(Path path) {
        return mo10232root().peekPath(path);
    }

    private static Period periodOf(int i, ChronoUnit chronoUnit) {
        if (!chronoUnit.isTimeBased()) {
            int i2 = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[chronoUnit.ordinal()];
            if (i2 == 1) {
                return Period.ofDays(i);
            }
            if (i2 == 2) {
                return Period.ofWeeks(i);
            }
            if (i2 == 3) {
                return Period.ofMonths(i);
            }
            if (i2 == 4) {
                return Period.ofYears(i);
            }
            throw new DateTimeException(chronoUnit + " cannot be converted to a java.time.Period");
        }
        throw new DateTimeException(chronoUnit + " cannot be converted to a java.time.Period");
    }

    private static AbstractConfigValue throwIfNull(AbstractConfigValue abstractConfigValue, ConfigValueType configValueType, Path path) {
        if (abstractConfigValue.valueType() == ConfigValueType.NULL) {
            throw new ConfigException.Null(abstractConfigValue.mo10176origin(), path.render(), configValueType != null ? configValueType.name() : null);
        }
        return abstractConfigValue;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue(this);
    }

    @Override // com.typesafe.config.Config
    public Config atPath(String str) {
        return mo10232root().mo10175atPath(str);
    }

    @Override // com.typesafe.config.Config
    public Set<Map.Entry<String, ConfigValue>> entrySet() {
        HashSet hashSet = new HashSet();
        findPaths(hashSet, null, this.object);
        return hashSet;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof SimpleConfig) {
            return this.object.equals(((SimpleConfig) obj).object);
        }
        return false;
    }

    AbstractConfigValue find(Path path, ConfigValueType configValueType, Path path2) {
        return throwIfNull(findOrNull(this.object, path, configValueType, path2), configValueType, path2);
    }

    @Override // com.typesafe.config.Config
    public Object getAnyRef(String str) {
        return find(str, null).mo10253unwrapped();
    }

    @Override // com.typesafe.config.Config
    public List<? extends Object> getAnyRefList(String str) {
        ArrayList arrayList = new ArrayList();
        for (ConfigValue configValue : getList(str)) {
            arrayList.add(configValue.mo10253unwrapped());
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    public boolean getBoolean(String str) {
        return ((Boolean) find(str, ConfigValueType.BOOLEAN).mo10253unwrapped()).booleanValue();
    }

    @Override // com.typesafe.config.Config
    public List<Boolean> getBooleanList(String str) {
        return getHomogeneousUnwrappedList(str, ConfigValueType.BOOLEAN);
    }

    @Override // com.typesafe.config.Config
    public Long getBytes(String str) {
        try {
            return Long.valueOf(getLong(str));
        } catch (ConfigException.WrongType unused) {
            AbstractConfigValue find = find(str, ConfigValueType.STRING);
            return Long.valueOf(parseBytes((String) find.mo10253unwrapped(), find.mo10176origin(), str));
        }
    }

    @Override // com.typesafe.config.Config
    public List<Long> getBytesList(String str) {
        ArrayList arrayList = new ArrayList();
        for (ConfigValue configValue : getList(str)) {
            if (configValue.valueType() == ConfigValueType.NUMBER) {
                arrayList.add(Long.valueOf(((Number) configValue.mo10253unwrapped()).longValue()));
            } else if (configValue.valueType() == ConfigValueType.STRING) {
                arrayList.add(Long.valueOf(parseBytes((String) configValue.mo10253unwrapped(), configValue.mo10176origin(), str)));
            } else {
                throw new ConfigException.WrongType(configValue.mo10176origin(), str, "memory size string or number of bytes", configValue.valueType().name());
            }
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    public List<? extends Config> getConfigList(String str) {
        List<ConfigObject> objectList = getObjectList(str);
        ArrayList arrayList = new ArrayList();
        for (ConfigObject configObject : objectList) {
            arrayList.add(configObject.mo10165toConfig());
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    public double getDouble(String str) {
        return getNumber(str).doubleValue();
    }

    @Override // com.typesafe.config.Config
    public List<Double> getDoubleList(String str) {
        ArrayList arrayList = new ArrayList();
        for (Number number : getNumberList(str)) {
            arrayList.add(Double.valueOf(number.doubleValue()));
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    public long getDuration(String str, TimeUnit timeUnit) {
        AbstractConfigValue find = find(str, ConfigValueType.STRING);
        return timeUnit.convert(parseDuration((String) find.mo10253unwrapped(), find.mo10176origin(), str), TimeUnit.NANOSECONDS);
    }

    @Override // com.typesafe.config.Config
    public List<Long> getDurationList(String str, TimeUnit timeUnit) {
        ArrayList arrayList = new ArrayList();
        for (ConfigValue configValue : getList(str)) {
            if (configValue.valueType() == ConfigValueType.NUMBER) {
                arrayList.add(Long.valueOf(timeUnit.convert(((Number) configValue.mo10253unwrapped()).longValue(), TimeUnit.MILLISECONDS)));
            } else if (configValue.valueType() == ConfigValueType.STRING) {
                arrayList.add(Long.valueOf(timeUnit.convert(parseDuration((String) configValue.mo10253unwrapped(), configValue.mo10176origin(), str), TimeUnit.NANOSECONDS)));
            } else {
                throw new ConfigException.WrongType(configValue.mo10176origin(), str, "duration string or number of milliseconds", configValue.valueType().name());
            }
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    public <T extends Enum<T>> T getEnum(Class<T> cls, String str) {
        return (T) getEnumValue(str, cls, find(str, ConfigValueType.STRING));
    }

    @Override // com.typesafe.config.Config
    public <T extends Enum<T>> List<T> getEnumList(Class<T> cls, String str) {
        List<ConfigString> homogeneousWrappedList = getHomogeneousWrappedList(str, ConfigValueType.STRING);
        ArrayList arrayList = new ArrayList();
        for (ConfigString configString : homogeneousWrappedList) {
            arrayList.add(getEnumValue(str, cls, configString));
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    public int getInt(String str) {
        return getConfigNumber(str).intValueRangeChecked(str);
    }

    @Override // com.typesafe.config.Config
    public List<Integer> getIntList(String str) {
        ArrayList arrayList = new ArrayList();
        for (ConfigNumber configNumber : getHomogeneousWrappedList(str, ConfigValueType.NUMBER)) {
            arrayList.add(Integer.valueOf(configNumber.intValueRangeChecked(str)));
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    public boolean getIsNull(String str) {
        return findOrNull(str, null).valueType() == ConfigValueType.NULL;
    }

    @Override // com.typesafe.config.Config
    public ConfigList getList(String str) {
        return (ConfigList) find(str, ConfigValueType.LIST);
    }

    @Override // com.typesafe.config.Config
    public long getLong(String str) {
        return getNumber(str).longValue();
    }

    @Override // com.typesafe.config.Config
    public List<Long> getLongList(String str) {
        ArrayList arrayList = new ArrayList();
        for (Number number : getNumberList(str)) {
            arrayList.add(Long.valueOf(number.longValue()));
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    public ConfigMemorySize getMemorySize(String str) {
        return ConfigMemorySize.ofBytes(getBytes(str).longValue());
    }

    @Override // com.typesafe.config.Config
    public List<ConfigMemorySize> getMemorySizeList(String str) {
        List<Long> bytesList = getBytesList(str);
        ArrayList arrayList = new ArrayList();
        for (Long l : bytesList) {
            arrayList.add(ConfigMemorySize.ofBytes(l.longValue()));
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    @Deprecated
    public Long getMilliseconds(String str) {
        return Long.valueOf(getDuration(str, TimeUnit.MILLISECONDS));
    }

    @Override // com.typesafe.config.Config
    @Deprecated
    public List<Long> getMillisecondsList(String str) {
        return getDurationList(str, TimeUnit.MILLISECONDS);
    }

    @Override // com.typesafe.config.Config
    @Deprecated
    public Long getNanoseconds(String str) {
        return Long.valueOf(getDuration(str, TimeUnit.NANOSECONDS));
    }

    @Override // com.typesafe.config.Config
    @Deprecated
    public List<Long> getNanosecondsList(String str) {
        return getDurationList(str, TimeUnit.NANOSECONDS);
    }

    @Override // com.typesafe.config.Config
    public Number getNumber(String str) {
        return getConfigNumber(str).mo10253unwrapped();
    }

    @Override // com.typesafe.config.Config
    public List<Number> getNumberList(String str) {
        return getHomogeneousUnwrappedList(str, ConfigValueType.NUMBER);
    }

    @Override // com.typesafe.config.Config
    public List<ConfigObject> getObjectList(String str) {
        return getHomogeneousWrappedList(str, ConfigValueType.OBJECT);
    }

    @Override // com.typesafe.config.Config
    public Period getPeriod(String str) {
        AbstractConfigValue find = find(str, ConfigValueType.STRING);
        return parsePeriod((String) find.mo10253unwrapped(), find.mo10176origin(), str);
    }

    @Override // com.typesafe.config.Config
    public String getString(String str) {
        return (String) find(str, ConfigValueType.STRING).mo10253unwrapped();
    }

    @Override // com.typesafe.config.Config
    public List<String> getStringList(String str) {
        return getHomogeneousUnwrappedList(str, ConfigValueType.STRING);
    }

    @Override // com.typesafe.config.Config
    public TemporalAmount getTemporal(String str) {
        try {
            return getDuration(str);
        } catch (ConfigException.BadValue unused) {
            return getPeriod(str);
        }
    }

    @Override // com.typesafe.config.Config
    public boolean hasPath(String str) {
        ConfigValue hasPathPeek = hasPathPeek(str);
        return (hasPathPeek == null || hasPathPeek.valueType() == ConfigValueType.NULL) ? false : true;
    }

    @Override // com.typesafe.config.Config
    public boolean hasPathOrNull(String str) {
        return hasPathPeek(str) != null;
    }

    public final int hashCode() {
        return this.object.hashCode() * 41;
    }

    @Override // com.typesafe.config.Config
    public boolean isEmpty() {
        return this.object.isEmpty();
    }

    @Override // com.typesafe.config.Config
    public boolean isResolved() {
        return mo10232root().resolveStatus() == ResolveStatus.RESOLVED;
    }

    @Override // com.typesafe.config.Config
    public ConfigOrigin origin() {
        return this.object.mo10176origin();
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Config("), this.object.toString(), ")");
    }

    private static void addMissing(List<ConfigException.ValidationProblem> list, ConfigValue configValue, Path path, ConfigOrigin configOrigin) {
        addMissing(list, getDesc(configValue), path, configOrigin);
    }

    private static String getDesc(ConfigValue configValue) {
        if (configValue instanceof AbstractConfigObject) {
            AbstractConfigObject abstractConfigObject = (AbstractConfigObject) configValue;
            if (!abstractConfigObject.isEmpty()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("object with keys ");
                outline107.append(abstractConfigObject.keySet());
                return outline107.toString();
            }
            return getDesc(configValue.valueType());
        }
        return getDesc(configValue.valueType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleConfig atKey(ConfigOrigin configOrigin, String str) {
        return mo10232root().atKey(configOrigin, str);
    }

    AbstractConfigValue find(String str, ConfigValueType configValueType) {
        Path newPath = Path.newPath(str);
        return find(newPath, configValueType, newPath);
    }

    @Override // com.typesafe.config.Config
    /* renamed from: getConfig  reason: collision with other method in class */
    public SimpleConfig mo10225getConfig(String str) {
        return mo10226getObject(str).mo10165toConfig();
    }

    @Override // com.typesafe.config.Config
    /* renamed from: getObject  reason: collision with other method in class */
    public AbstractConfigObject mo10226getObject(String str) {
        return (AbstractConfigObject) find(str, ConfigValueType.OBJECT);
    }

    @Override // com.typesafe.config.Config
    /* renamed from: getValue  reason: collision with other method in class */
    public AbstractConfigValue mo10227getValue(String str) {
        return find(str, null);
    }

    @Override // com.typesafe.config.Config
    /* renamed from: root  reason: collision with other method in class */
    public AbstractConfigObject mo10232root() {
        return this.object;
    }

    @Override // com.typesafe.config.impl.MergeableValue
    /* renamed from: toFallbackValue  reason: collision with other method in class */
    public AbstractConfigObject mo10233toFallbackValue() {
        return this.object;
    }

    @Override // com.typesafe.config.Config
    /* renamed from: withOnlyPath  reason: collision with other method in class */
    public SimpleConfig mo10235withOnlyPath(String str) {
        return new SimpleConfig(mo10232root().mo10256withOnlyPath(Path.newPath(str)));
    }

    @Override // com.typesafe.config.Config
    /* renamed from: withValue  reason: collision with other method in class */
    public SimpleConfig mo10236withValue(String str, ConfigValue configValue) {
        return new SimpleConfig(mo10232root().mo10259withValue(Path.newPath(str), configValue));
    }

    @Override // com.typesafe.config.Config
    /* renamed from: withoutPath  reason: collision with other method in class */
    public SimpleConfig mo10237withoutPath(String str) {
        return new SimpleConfig(mo10232root().mo10261withoutPath(Path.newPath(str)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addMissing(List<ConfigException.ValidationProblem> list, ConfigValueType configValueType, Path path, ConfigOrigin configOrigin) {
        addMissing(list, getDesc(configValueType), path, configOrigin);
    }

    private static boolean haveCompatibleTypes(ConfigValueType configValueType, AbstractConfigValue abstractConfigValue) {
        if (configValueType == ConfigValueType.NULL || couldBeNull(abstractConfigValue)) {
            return true;
        }
        return configValueType == ConfigValueType.OBJECT ? abstractConfigValue instanceof AbstractConfigObject : configValueType == ConfigValueType.LIST ? (abstractConfigValue instanceof SimpleConfigList) || (abstractConfigValue instanceof SimpleConfigObject) : configValueType == ConfigValueType.STRING || (abstractConfigValue instanceof ConfigString) || configValueType == abstractConfigValue.valueType();
    }

    @Override // com.typesafe.config.Config
    /* renamed from: atKey  reason: collision with other method in class */
    public SimpleConfig mo10224atKey(String str) {
        return mo10232root().mo10174atKey(str);
    }

    @Override // com.typesafe.config.Config
    /* renamed from: resolve  reason: collision with other method in class */
    public SimpleConfig mo10228resolve() {
        return mo10229resolve(ConfigResolveOptions.defaults());
    }

    @Override // com.typesafe.config.Config
    /* renamed from: resolveWith  reason: collision with other method in class */
    public SimpleConfig mo10230resolveWith(Config config) {
        return mo10231resolveWith(config, ConfigResolveOptions.defaults());
    }

    @Override // com.typesafe.config.Config, com.typesafe.config.ConfigMergeable
    /* renamed from: withFallback */
    public SimpleConfig mo10234withFallback(ConfigMergeable configMergeable) {
        return this.object.mo10234withFallback(configMergeable).mo10165toConfig();
    }

    private static void addWrongType(List<ConfigException.ValidationProblem> list, ConfigValue configValue, AbstractConfigValue abstractConfigValue, Path path) {
        addWrongType(list, getDesc(configValue), abstractConfigValue, path);
    }

    @Override // com.typesafe.config.Config
    public Duration getDuration(String str) {
        AbstractConfigValue find = find(str, ConfigValueType.STRING);
        return Duration.ofNanos(parseDuration((String) find.mo10253unwrapped(), find.mo10176origin(), str));
    }

    @Override // com.typesafe.config.Config
    /* renamed from: resolve  reason: collision with other method in class */
    public SimpleConfig mo10229resolve(ConfigResolveOptions configResolveOptions) {
        return mo10231resolveWith((Config) this, configResolveOptions);
    }

    @Override // com.typesafe.config.Config
    /* renamed from: resolveWith  reason: collision with other method in class */
    public SimpleConfig mo10231resolveWith(Config config, ConfigResolveOptions configResolveOptions) {
        AbstractConfigValue resolve = ResolveContext.resolve(this.object, ((SimpleConfig) config).object, configResolveOptions);
        return resolve == this.object ? this : new SimpleConfig((AbstractConfigObject) resolve);
    }

    private static void addWrongType(List<ConfigException.ValidationProblem> list, ConfigValueType configValueType, AbstractConfigValue abstractConfigValue, Path path) {
        addWrongType(list, getDesc(configValueType), abstractConfigValue, path);
    }

    private static void checkValid(Path path, ConfigValue configValue, AbstractConfigValue abstractConfigValue, List<ConfigException.ValidationProblem> list) {
        if (haveCompatibleTypes(configValue, abstractConfigValue)) {
            if ((configValue instanceof AbstractConfigObject) && (abstractConfigValue instanceof AbstractConfigObject)) {
                checkValidObject(path, (AbstractConfigObject) configValue, (AbstractConfigObject) abstractConfigValue, list);
                return;
            }
            boolean z = configValue instanceof SimpleConfigList;
            if (z && (abstractConfigValue instanceof SimpleConfigList)) {
                checkListCompatibility(path, (SimpleConfigList) configValue, (SimpleConfigList) abstractConfigValue, list);
                return;
            } else if (!z || !(abstractConfigValue instanceof SimpleConfigObject)) {
                return;
            } else {
                SimpleConfigList simpleConfigList = (SimpleConfigList) configValue;
                AbstractConfigValue transform = DefaultTransformer.transform(abstractConfigValue, ConfigValueType.LIST);
                if (transform instanceof SimpleConfigList) {
                    checkListCompatibility(path, simpleConfigList, (SimpleConfigList) transform, list);
                    return;
                } else {
                    addWrongType(list, configValue, abstractConfigValue, path);
                    return;
                }
            }
        }
        addWrongType(list, configValue, abstractConfigValue, path);
    }

    private AbstractConfigValue findOrNull(Path path, ConfigValueType configValueType, Path path2) {
        return findOrNull(this.object, path, configValueType, path2);
    }

    private AbstractConfigValue findOrNull(String str, ConfigValueType configValueType) {
        Path newPath = Path.newPath(str);
        return findOrNull(newPath, configValueType, newPath);
    }

    @Override // com.typesafe.config.Config
    public List<Duration> getDurationList(String str) {
        List<Long> durationList = getDurationList(str, TimeUnit.NANOSECONDS);
        ArrayList arrayList = new ArrayList(durationList.size());
        for (Long l : durationList) {
            arrayList.add(Duration.ofNanos(l.longValue()));
        }
        return arrayList;
    }

    @Override // com.typesafe.config.Config
    public void checkValid(Config config, String... strArr) {
        SimpleConfig simpleConfig = (SimpleConfig) config;
        if (simpleConfig.mo10232root().resolveStatus() == ResolveStatus.RESOLVED) {
            if (mo10232root().resolveStatus() == ResolveStatus.RESOLVED) {
                ArrayList arrayList = new ArrayList();
                if (strArr.length == 0) {
                    checkValidObject(null, simpleConfig.mo10232root(), mo10232root(), arrayList);
                } else {
                    for (String str : strArr) {
                        Path newPath = Path.newPath(str);
                        AbstractConfigValue peekPath = simpleConfig.peekPath(newPath);
                        if (peekPath != null) {
                            AbstractConfigValue peekPath2 = peekPath(newPath);
                            if (peekPath2 != null) {
                                checkValid(newPath, peekPath, peekPath2, arrayList);
                            } else {
                                addMissing(arrayList, peekPath, newPath, origin());
                            }
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    throw new ConfigException.ValidationFailed(arrayList);
                }
                return;
            }
            throw new ConfigException.NotResolved("need to Config#resolve() each config before using it, see the API docs for Config#resolve()");
        }
        throw new ConfigException.BugOrBroken("do not call checkValid() with an unresolved reference config, call Config#resolve(), see Config#resolve() API docs");
    }
}
