package com.typesafe.config;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/* loaded from: classes3.dex */
public interface Config extends ConfigMergeable {
    /* renamed from: atKey */
    Config mo10224atKey(String str);

    Config atPath(String str);

    void checkValid(Config config, String... strArr);

    Set<Map.Entry<String, ConfigValue>> entrySet();

    Object getAnyRef(String str);

    List<? extends Object> getAnyRefList(String str);

    boolean getBoolean(String str);

    List<Boolean> getBooleanList(String str);

    Long getBytes(String str);

    List<Long> getBytesList(String str);

    /* renamed from: getConfig */
    Config mo10225getConfig(String str);

    List<? extends Config> getConfigList(String str);

    double getDouble(String str);

    List<Double> getDoubleList(String str);

    long getDuration(String str, TimeUnit timeUnit);

    Duration getDuration(String str);

    List<Duration> getDurationList(String str);

    List<Long> getDurationList(String str, TimeUnit timeUnit);

    <T extends Enum<T>> T getEnum(Class<T> cls, String str);

    <T extends Enum<T>> List<T> getEnumList(Class<T> cls, String str);

    int getInt(String str);

    List<Integer> getIntList(String str);

    boolean getIsNull(String str);

    ConfigList getList(String str);

    long getLong(String str);

    List<Long> getLongList(String str);

    ConfigMemorySize getMemorySize(String str);

    List<ConfigMemorySize> getMemorySizeList(String str);

    @Deprecated
    Long getMilliseconds(String str);

    @Deprecated
    List<Long> getMillisecondsList(String str);

    @Deprecated
    Long getNanoseconds(String str);

    @Deprecated
    List<Long> getNanosecondsList(String str);

    Number getNumber(String str);

    List<Number> getNumberList(String str);

    /* renamed from: getObject */
    ConfigObject mo10226getObject(String str);

    List<? extends ConfigObject> getObjectList(String str);

    Period getPeriod(String str);

    String getString(String str);

    List<String> getStringList(String str);

    TemporalAmount getTemporal(String str);

    /* renamed from: getValue */
    ConfigValue mo10227getValue(String str);

    boolean hasPath(String str);

    boolean hasPathOrNull(String str);

    boolean isEmpty();

    boolean isResolved();

    ConfigOrigin origin();

    /* renamed from: resolve */
    Config mo10228resolve();

    /* renamed from: resolve */
    Config mo10229resolve(ConfigResolveOptions configResolveOptions);

    /* renamed from: resolveWith */
    Config mo10230resolveWith(Config config);

    /* renamed from: resolveWith */
    Config mo10231resolveWith(Config config, ConfigResolveOptions configResolveOptions);

    /* renamed from: root */
    ConfigObject mo10232root();

    @Override // com.typesafe.config.ConfigMergeable
    /* renamed from: withFallback */
    Config mo10234withFallback(ConfigMergeable configMergeable);

    /* renamed from: withOnlyPath */
    Config mo10235withOnlyPath(String str);

    /* renamed from: withValue */
    Config mo10236withValue(String str, ConfigValue configValue);

    /* renamed from: withoutPath */
    Config mo10237withoutPath(String str);
}
