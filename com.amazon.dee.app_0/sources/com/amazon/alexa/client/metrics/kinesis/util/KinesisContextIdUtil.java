package com.amazon.alexa.client.metrics.kinesis.util;
/* loaded from: classes6.dex */
public class KinesisContextIdUtil {
    private static final KinesisContextIdUtil EMPTY_ID = new KinesisContextIdUtil("");
    private final String id;

    public KinesisContextIdUtil(String str) {
        this.id = str;
    }

    public static synchronized KinesisContextIdUtil getEmptyId() {
        KinesisContextIdUtil kinesisContextIdUtil;
        synchronized (KinesisContextIdUtil.class) {
            kinesisContextIdUtil = EMPTY_ID;
        }
        return kinesisContextIdUtil;
    }

    public static KinesisContextIdUtil valueOf(String str) {
        if (str != null && !str.equals("")) {
            return new KinesisContextIdUtil(str);
        }
        return getEmptyId();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || KinesisContextIdUtil.class != obj.getClass()) {
            return false;
        }
        KinesisContextIdUtil kinesisContextIdUtil = (KinesisContextIdUtil) obj;
        String str = this.id;
        if (str == null) {
            if (kinesisContextIdUtil.id != null) {
                return false;
            }
        } else if (!str.equals(kinesisContextIdUtil.id)) {
            return false;
        }
        return true;
    }

    public String getValue() {
        return this.id;
    }

    public int hashCode() {
        String str = this.id;
        return 31 + (str == null ? 0 : str.hashCode());
    }
}
