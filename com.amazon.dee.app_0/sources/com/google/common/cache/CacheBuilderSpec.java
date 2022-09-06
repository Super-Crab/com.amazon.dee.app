package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.LocalCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes2.dex */
public final class CacheBuilderSpec {
    private static final Splitter KEYS_SPLITTER = Splitter.on((char) JsonReaderKt.COMMA).trimResults();
    private static final Splitter KEY_VALUE_SPLITTER = Splitter.on((char) Chars.EQ).trimResults();
    private static final ImmutableMap<String, ValueParser> VALUE_PARSERS = ImmutableMap.builder().mo7828put("initialCapacity", new InitialCapacityParser()).mo7828put("maximumSize", new MaximumSizeParser()).mo7828put("maximumWeight", new MaximumWeightParser()).mo7828put("concurrencyLevel", new ConcurrencyLevelParser()).mo7828put("weakKeys", new KeyStrengthParser(LocalCache.Strength.WEAK)).mo7828put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).mo7828put("weakValues", new ValueStrengthParser(LocalCache.Strength.WEAK)).mo7828put("recordStats", new RecordStatsParser()).mo7828put("expireAfterAccess", new AccessDurationParser()).mo7828put("expireAfterWrite", new WriteDurationParser()).mo7828put("refreshAfterWrite", new RefreshDurationParser()).mo7828put("refreshInterval", new RefreshDurationParser()).mo7826build();
    @VisibleForTesting
    long accessExpirationDuration;
    @VisibleForTesting
    @NullableDecl
    TimeUnit accessExpirationTimeUnit;
    @VisibleForTesting
    @NullableDecl
    Integer concurrencyLevel;
    @VisibleForTesting
    @NullableDecl
    Integer initialCapacity;
    @VisibleForTesting
    @NullableDecl
    LocalCache.Strength keyStrength;
    @VisibleForTesting
    @NullableDecl
    Long maximumSize;
    @VisibleForTesting
    @NullableDecl
    Long maximumWeight;
    @VisibleForTesting
    @NullableDecl
    Boolean recordStats;
    @VisibleForTesting
    long refreshDuration;
    @VisibleForTesting
    @NullableDecl
    TimeUnit refreshTimeUnit;
    private final String specification;
    @VisibleForTesting
    @NullableDecl
    LocalCache.Strength valueStrength;
    @VisibleForTesting
    long writeExpirationDuration;
    @VisibleForTesting
    @NullableDecl
    TimeUnit writeExpirationTimeUnit;

    /* renamed from: com.google.common.cache.CacheBuilderSpec$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$cache$LocalCache$Strength = new int[LocalCache.Strength.values().length];

        static {
            try {
                $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes2.dex */
    static class AccessDurationParser extends DurationParser {
        AccessDurationParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        protected void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
            cacheBuilderSpec.accessExpirationDuration = j;
            cacheBuilderSpec.accessExpirationTimeUnit = timeUnit;
        }
    }

    /* loaded from: classes2.dex */
    static class ConcurrencyLevelParser extends IntegerParser {
        ConcurrencyLevelParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        protected void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i) {
            Preconditions.checkArgument(cacheBuilderSpec.concurrencyLevel == null, "concurrency level was already set to ", cacheBuilderSpec.concurrencyLevel);
            cacheBuilderSpec.concurrencyLevel = Integer.valueOf(i);
        }
    }

    /* loaded from: classes2.dex */
    static abstract class DurationParser implements ValueParser {
        DurationParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            TimeUnit timeUnit;
            Preconditions.checkArgument(str2 != null && !str2.isEmpty(), "value of key %s omitted", str);
            try {
                char charAt = str2.charAt(str2.length() - 1);
                if (charAt == 'd') {
                    timeUnit = TimeUnit.DAYS;
                } else if (charAt == 'h') {
                    timeUnit = TimeUnit.HOURS;
                } else if (charAt == 'm') {
                    timeUnit = TimeUnit.MINUTES;
                } else if (charAt == 's') {
                    timeUnit = TimeUnit.SECONDS;
                } else {
                    throw new IllegalArgumentException(CacheBuilderSpec.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", str, str2));
                }
                parseDuration(cacheBuilderSpec, Long.parseLong(str2.substring(0, str2.length() - 1)), timeUnit);
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2));
            }
        }

        protected abstract void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit);
    }

    /* loaded from: classes2.dex */
    static class InitialCapacityParser extends IntegerParser {
        InitialCapacityParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        protected void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i) {
            Preconditions.checkArgument(cacheBuilderSpec.initialCapacity == null, "initial capacity was already set to ", cacheBuilderSpec.initialCapacity);
            cacheBuilderSpec.initialCapacity = Integer.valueOf(i);
        }
    }

    /* loaded from: classes2.dex */
    static abstract class IntegerParser implements ValueParser {
        IntegerParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 != null && !str2.isEmpty(), "value of key %s omitted", str);
            try {
                parseInteger(cacheBuilderSpec, Integer.parseInt(str2));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e);
            }
        }

        protected abstract void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i);
    }

    /* loaded from: classes2.dex */
    static class KeyStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public KeyStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2) {
            boolean z = true;
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            if (cacheBuilderSpec.keyStrength != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "%s was already set to %s", str, cacheBuilderSpec.keyStrength);
            cacheBuilderSpec.keyStrength = this.strength;
        }
    }

    /* loaded from: classes2.dex */
    static abstract class LongParser implements ValueParser {
        LongParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 != null && !str2.isEmpty(), "value of key %s omitted", str);
            try {
                parseLong(cacheBuilderSpec, Long.parseLong(str2));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e);
            }
        }

        protected abstract void parseLong(CacheBuilderSpec cacheBuilderSpec, long j);
    }

    /* loaded from: classes2.dex */
    static class MaximumSizeParser extends LongParser {
        MaximumSizeParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        protected void parseLong(CacheBuilderSpec cacheBuilderSpec, long j) {
            boolean z = true;
            Preconditions.checkArgument(cacheBuilderSpec.maximumSize == null, "maximum size was already set to ", cacheBuilderSpec.maximumSize);
            if (cacheBuilderSpec.maximumWeight != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "maximum weight was already set to ", cacheBuilderSpec.maximumWeight);
            cacheBuilderSpec.maximumSize = Long.valueOf(j);
        }
    }

    /* loaded from: classes2.dex */
    static class MaximumWeightParser extends LongParser {
        MaximumWeightParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        protected void parseLong(CacheBuilderSpec cacheBuilderSpec, long j) {
            boolean z = true;
            Preconditions.checkArgument(cacheBuilderSpec.maximumWeight == null, "maximum weight was already set to ", cacheBuilderSpec.maximumWeight);
            if (cacheBuilderSpec.maximumSize != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "maximum size was already set to ", cacheBuilderSpec.maximumSize);
            cacheBuilderSpec.maximumWeight = Long.valueOf(j);
        }
    }

    /* loaded from: classes2.dex */
    static class RecordStatsParser implements ValueParser {
        RecordStatsParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2) {
            boolean z = false;
            Preconditions.checkArgument(str2 == null, "recordStats does not take values");
            if (cacheBuilderSpec.recordStats == null) {
                z = true;
            }
            Preconditions.checkArgument(z, "recordStats already set");
            cacheBuilderSpec.recordStats = true;
        }
    }

    /* loaded from: classes2.dex */
    static class RefreshDurationParser extends DurationParser {
        RefreshDurationParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        protected void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.refreshTimeUnit == null, "refreshAfterWrite already set");
            cacheBuilderSpec.refreshDuration = j;
            cacheBuilderSpec.refreshTimeUnit = timeUnit;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface ValueParser {
        void parse(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2);
    }

    /* loaded from: classes2.dex */
    static class ValueStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2) {
            boolean z = true;
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            if (cacheBuilderSpec.valueStrength != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "%s was already set to %s", str, cacheBuilderSpec.valueStrength);
            cacheBuilderSpec.valueStrength = this.strength;
        }
    }

    /* loaded from: classes2.dex */
    static class WriteDurationParser extends DurationParser {
        WriteDurationParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        protected void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
            cacheBuilderSpec.writeExpirationDuration = j;
            cacheBuilderSpec.writeExpirationTimeUnit = timeUnit;
        }
    }

    private CacheBuilderSpec(String str) {
        this.specification = str;
    }

    public static CacheBuilderSpec disableCaching() {
        return parse("maximumSize=0");
    }

    @NullableDecl
    private static Long durationInNanos(long j, @NullableDecl TimeUnit timeUnit) {
        if (timeUnit == null) {
            return null;
        }
        return Long.valueOf(timeUnit.toNanos(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String format(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }

    public static CacheBuilderSpec parse(String str) {
        CacheBuilderSpec cacheBuilderSpec = new CacheBuilderSpec(str);
        if (!str.isEmpty()) {
            for (String str2 : KEYS_SPLITTER.split(str)) {
                ImmutableList copyOf = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(str2));
                Preconditions.checkArgument(!copyOf.isEmpty(), "blank key-value pair");
                boolean z = false;
                Preconditions.checkArgument(copyOf.size() <= 2, "key-value pair %s with more than one equals sign", str2);
                String str3 = (String) copyOf.get(0);
                ValueParser mo7740get = VALUE_PARSERS.mo7740get(str3);
                if (mo7740get != null) {
                    z = true;
                }
                Preconditions.checkArgument(z, "unknown key %s", str3);
                mo7740get.parse(cacheBuilderSpec, str3, copyOf.size() == 1 ? null : (String) copyOf.get(1));
            }
        }
        return cacheBuilderSpec;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec cacheBuilderSpec = (CacheBuilderSpec) obj;
        return Objects.equal(this.initialCapacity, cacheBuilderSpec.initialCapacity) && Objects.equal(this.maximumSize, cacheBuilderSpec.maximumSize) && Objects.equal(this.maximumWeight, cacheBuilderSpec.maximumWeight) && Objects.equal(this.concurrencyLevel, cacheBuilderSpec.concurrencyLevel) && Objects.equal(this.keyStrength, cacheBuilderSpec.keyStrength) && Objects.equal(this.valueStrength, cacheBuilderSpec.valueStrength) && Objects.equal(this.recordStats, cacheBuilderSpec.recordStats) && Objects.equal(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(cacheBuilderSpec.writeExpirationDuration, cacheBuilderSpec.writeExpirationTimeUnit)) && Objects.equal(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(cacheBuilderSpec.accessExpirationDuration, cacheBuilderSpec.accessExpirationTimeUnit)) && Objects.equal(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(cacheBuilderSpec.refreshDuration, cacheBuilderSpec.refreshTimeUnit));
    }

    public int hashCode() {
        return Objects.hashCode(this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(this.refreshDuration, this.refreshTimeUnit));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CacheBuilder<Object, Object> toCacheBuilder() {
        CacheBuilder<Object, Object> newBuilder = CacheBuilder.newBuilder();
        Integer num = this.initialCapacity;
        if (num != null) {
            newBuilder.initialCapacity(num.intValue());
        }
        Long l = this.maximumSize;
        if (l != null) {
            newBuilder.maximumSize(l.longValue());
        }
        Long l2 = this.maximumWeight;
        if (l2 != null) {
            newBuilder.maximumWeight(l2.longValue());
        }
        Integer num2 = this.concurrencyLevel;
        if (num2 != null) {
            newBuilder.concurrencyLevel(num2.intValue());
        }
        LocalCache.Strength strength = this.keyStrength;
        if (strength != null) {
            if (strength.ordinal() == 2) {
                newBuilder.weakKeys();
            } else {
                throw new AssertionError();
            }
        }
        LocalCache.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            int ordinal = strength2.ordinal();
            if (ordinal == 1) {
                newBuilder.softValues();
            } else if (ordinal == 2) {
                newBuilder.weakValues();
            } else {
                throw new AssertionError();
            }
        }
        Boolean bool = this.recordStats;
        if (bool != null && bool.booleanValue()) {
            newBuilder.recordStats();
        }
        TimeUnit timeUnit = this.writeExpirationTimeUnit;
        if (timeUnit != null) {
            newBuilder.expireAfterWrite(this.writeExpirationDuration, timeUnit);
        }
        TimeUnit timeUnit2 = this.accessExpirationTimeUnit;
        if (timeUnit2 != null) {
            newBuilder.expireAfterAccess(this.accessExpirationDuration, timeUnit2);
        }
        TimeUnit timeUnit3 = this.refreshTimeUnit;
        if (timeUnit3 != null) {
            newBuilder.refreshAfterWrite(this.refreshDuration, timeUnit3);
        }
        return newBuilder;
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(toParsableString()).toString();
    }
}
