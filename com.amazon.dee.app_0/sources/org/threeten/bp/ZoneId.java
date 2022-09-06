package org.threeten.bp;

import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;
import org.threeten.bp.zone.ZoneRulesProvider;
/* loaded from: classes5.dex */
public abstract class ZoneId implements Serializable {
    public static final TemporalQuery<ZoneId> FROM = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.ZoneId.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: queryFrom  reason: avoid collision after fix types in other method */
        public ZoneId mo13072queryFrom(TemporalAccessor temporalAccessor) {
            return ZoneId.from(temporalAccessor);
        }
    };
    public static final Map<String, String> SHORT_IDS;
    private static final long serialVersionUID = 8352817235686L;

    static {
        HashMap outline134 = GeneratedOutlineSupport1.outline134("ACT", "Australia/Darwin", "AET", "Australia/Sydney");
        outline134.put("AGT", "America/Argentina/Buenos_Aires");
        outline134.put("ART", "Africa/Cairo");
        outline134.put("AST", "America/Anchorage");
        outline134.put("BET", "America/Sao_Paulo");
        outline134.put("BST", "Asia/Dhaka");
        outline134.put("CAT", "Africa/Harare");
        outline134.put("CNT", "America/St_Johns");
        outline134.put("CST", "America/Chicago");
        outline134.put("CTT", "Asia/Shanghai");
        outline134.put("EAT", "Africa/Addis_Ababa");
        outline134.put("ECT", "Europe/Paris");
        outline134.put("IET", "America/Indiana/Indianapolis");
        outline134.put("IST", "Asia/Kolkata");
        outline134.put("JST", "Asia/Tokyo");
        outline134.put("MIT", "Pacific/Apia");
        outline134.put("NET", "Asia/Yerevan");
        outline134.put("NST", "Pacific/Auckland");
        outline134.put("PLT", "Asia/Karachi");
        outline134.put("PNT", "America/Phoenix");
        outline134.put("PRT", "America/Puerto_Rico");
        outline134.put("PST", "America/Los_Angeles");
        outline134.put("SST", "Pacific/Guadalcanal");
        outline134.put("VST", "Asia/Ho_Chi_Minh");
        outline134.put("EST", "-05:00");
        outline134.put("MST", "-07:00");
        outline134.put("HST", "-10:00");
        SHORT_IDS = Collections.unmodifiableMap(outline134);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoneId() {
        if (getClass() == ZoneOffset.class || getClass() == ZoneRegion.class) {
            return;
        }
        throw new AssertionError("Invalid subclass");
    }

    public static ZoneId from(TemporalAccessor temporalAccessor) {
        ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zone());
        if (zoneId != null) {
            return zoneId;
        }
        throw new DateTimeException("Unable to obtain ZoneId from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
    }

    public static Set<String> getAvailableZoneIds() {
        return new HashSet(ZoneRulesProvider.getAvailableZoneIds());
    }

    public static ZoneId of(String str, Map<String, String> map) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        Jdk8Methods.requireNonNull(map, "aliasMap");
        String str2 = map.get(str);
        if (str2 != null) {
            str = str2;
        }
        return of(str);
    }

    public static ZoneId ofOffset(String str, ZoneOffset zoneOffset) {
        Jdk8Methods.requireNonNull(str, "prefix");
        Jdk8Methods.requireNonNull(zoneOffset, "offset");
        if (str.length() == 0) {
            return zoneOffset;
        }
        if (!str.equals("GMT") && !str.equals(Constants.UTC) && !str.equals("UT")) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid prefix, must be GMT, UTC or UT: ", str));
        }
        if (zoneOffset.getTotalSeconds() == 0) {
            return new ZoneRegion(str, zoneOffset.getRules());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(zoneOffset.getId());
        return new ZoneRegion(outline107.toString(), zoneOffset.getRules());
    }

    public static ZoneId systemDefault() {
        return of(TimeZone.getDefault().getID(), SHORT_IDS);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZoneId)) {
            return false;
        }
        return getId().equals(((ZoneId) obj).getId());
    }

    public String getDisplayName(TextStyle textStyle, Locale locale) {
        return new DateTimeFormatterBuilder().appendZoneText(textStyle).toFormatter(locale).format(new DefaultInterfaceTemporalAccessor() { // from class: org.threeten.bp.ZoneId.2
            @Override // org.threeten.bp.temporal.TemporalAccessor
            public long getLong(TemporalField temporalField) {
                throw new UnsupportedTemporalTypeException(GeneratedOutlineSupport1.outline82("Unsupported field: ", temporalField));
            }

            @Override // org.threeten.bp.temporal.TemporalAccessor
            public boolean isSupported(TemporalField temporalField) {
                return false;
            }

            @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
            public <R> R query(TemporalQuery<R> temporalQuery) {
                if (temporalQuery == TemporalQueries.zoneId()) {
                    return (R) ZoneId.this;
                }
                return (R) super.query(temporalQuery);
            }
        });
    }

    public abstract String getId();

    public abstract ZoneRules getRules();

    public int hashCode() {
        return getId().hashCode();
    }

    public ZoneId normalized() {
        try {
            ZoneRules rules = getRules();
            if (rules.isFixedOffset()) {
                return rules.getOffset(Instant.EPOCH);
            }
        } catch (ZoneRulesException unused) {
        }
        return this;
    }

    public String toString() {
        return getId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void write(DataOutput dataOutput) throws IOException;

    public static ZoneId of(String str) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        if (str.equals("Z")) {
            return ZoneOffset.UTC;
        }
        if (str.length() != 1) {
            if (!str.startsWith("+") && !str.startsWith(ProcessIdUtil.DEFAULT_PROCESSID)) {
                if (!str.equals(Constants.UTC) && !str.equals("GMT") && !str.equals("UT")) {
                    if (!str.startsWith("UTC+") && !str.startsWith("GMT+") && !str.startsWith("UTC-") && !str.startsWith("GMT-")) {
                        if (!str.startsWith("UT+") && !str.startsWith("UT-")) {
                            return ZoneRegion.ofId(str, true);
                        }
                        ZoneOffset of = ZoneOffset.of(str.substring(2));
                        if (of.getTotalSeconds() == 0) {
                            return new ZoneRegion("UT", of.getRules());
                        }
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UT");
                        outline107.append(of.getId());
                        return new ZoneRegion(outline107.toString(), of.getRules());
                    }
                    ZoneOffset of2 = ZoneOffset.of(str.substring(3));
                    if (of2.getTotalSeconds() == 0) {
                        return new ZoneRegion(str.substring(0, 3), of2.getRules());
                    }
                    return new ZoneRegion(str.substring(0, 3) + of2.getId(), of2.getRules());
                }
                return new ZoneRegion(str, ZoneOffset.UTC.getRules());
            }
            return ZoneOffset.of(str);
        }
        throw new DateTimeException(GeneratedOutlineSupport1.outline72("Invalid zone: ", str));
    }
}
