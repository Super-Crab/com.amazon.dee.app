package org.joda.time.tz;

import org.bouncycastle.asn1.cmc.BodyPartID;
import org.joda.time.DateTimeZone;
/* loaded from: classes5.dex */
public class CachedDateTimeZone extends DateTimeZone {
    private static final int cInfoCacheMask;
    private static final long serialVersionUID = 5472298452022250685L;
    private final transient Info[] iInfoCache;
    private final DateTimeZone iZone;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class Info {
        private String iNameKey;
        Info iNextInfo;
        public final long iPeriodStart;
        public final DateTimeZone iZoneRef;
        private int iOffset = Integer.MIN_VALUE;
        private int iStandardOffset = Integer.MIN_VALUE;

        Info(DateTimeZone dateTimeZone, long j) {
            this.iPeriodStart = j;
            this.iZoneRef = dateTimeZone;
        }

        public String getNameKey(long j) {
            Info info = this.iNextInfo;
            if (info == null || j < info.iPeriodStart) {
                if (this.iNameKey == null) {
                    this.iNameKey = this.iZoneRef.getNameKey(this.iPeriodStart);
                }
                return this.iNameKey;
            }
            return info.getNameKey(j);
        }

        public int getOffset(long j) {
            Info info = this.iNextInfo;
            if (info == null || j < info.iPeriodStart) {
                if (this.iOffset == Integer.MIN_VALUE) {
                    this.iOffset = this.iZoneRef.getOffset(this.iPeriodStart);
                }
                return this.iOffset;
            }
            return info.getOffset(j);
        }

        public int getStandardOffset(long j) {
            Info info = this.iNextInfo;
            if (info == null || j < info.iPeriodStart) {
                if (this.iStandardOffset == Integer.MIN_VALUE) {
                    this.iStandardOffset = this.iZoneRef.getStandardOffset(this.iPeriodStart);
                }
                return this.iStandardOffset;
            }
            return info.getStandardOffset(j);
        }
    }

    static {
        Integer num;
        int i;
        try {
            num = Integer.getInteger("org.joda.time.tz.CachedDateTimeZone.size");
        } catch (SecurityException unused) {
            num = null;
        }
        if (num == null) {
            i = 512;
        } else {
            int i2 = 0;
            for (int intValue = num.intValue() - 1; intValue > 0; intValue >>= 1) {
                i2++;
            }
            i = 1 << i2;
        }
        cInfoCacheMask = i - 1;
    }

    private CachedDateTimeZone(DateTimeZone dateTimeZone) {
        super(dateTimeZone.getID());
        this.iInfoCache = new Info[cInfoCacheMask + 1];
        this.iZone = dateTimeZone;
    }

    private Info createInfo(long j) {
        long j2 = j & (-4294967296L);
        Info info = new Info(this.iZone, j2);
        long j3 = BodyPartID.bodyIdMax | j2;
        Info info2 = info;
        while (true) {
            long nextTransition = this.iZone.nextTransition(j2);
            if (nextTransition == j2 || nextTransition > j3) {
                break;
            }
            Info info3 = new Info(this.iZone, nextTransition);
            info2.iNextInfo = info3;
            info2 = info3;
            j2 = nextTransition;
        }
        return info;
    }

    public static CachedDateTimeZone forZone(DateTimeZone dateTimeZone) {
        return dateTimeZone instanceof CachedDateTimeZone ? (CachedDateTimeZone) dateTimeZone : new CachedDateTimeZone(dateTimeZone);
    }

    private Info getInfo(long j) {
        int i = (int) (j >> 32);
        Info[] infoArr = this.iInfoCache;
        int i2 = cInfoCacheMask & i;
        Info info = infoArr[i2];
        if (info == null || ((int) (info.iPeriodStart >> 32)) != i) {
            Info createInfo = createInfo(j);
            infoArr[i2] = createInfo;
            return createInfo;
        }
        return info;
    }

    @Override // org.joda.time.DateTimeZone
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CachedDateTimeZone)) {
            return false;
        }
        return this.iZone.equals(((CachedDateTimeZone) obj).iZone);
    }

    @Override // org.joda.time.DateTimeZone
    public String getNameKey(long j) {
        return getInfo(j).getNameKey(j);
    }

    @Override // org.joda.time.DateTimeZone
    public int getOffset(long j) {
        return getInfo(j).getOffset(j);
    }

    @Override // org.joda.time.DateTimeZone
    public int getStandardOffset(long j) {
        return getInfo(j).getStandardOffset(j);
    }

    public DateTimeZone getUncachedZone() {
        return this.iZone;
    }

    @Override // org.joda.time.DateTimeZone
    public int hashCode() {
        return this.iZone.hashCode();
    }

    @Override // org.joda.time.DateTimeZone
    public boolean isFixed() {
        return this.iZone.isFixed();
    }

    @Override // org.joda.time.DateTimeZone
    public long nextTransition(long j) {
        return this.iZone.nextTransition(j);
    }

    @Override // org.joda.time.DateTimeZone
    public long previousTransition(long j) {
        return this.iZone.previousTransition(j);
    }
}
