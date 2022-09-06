package org.apache.logging.log4j.spi;

import com.amazon.comms.config.util.DeviceConfigConstants;
import java.util.EnumSet;
import java.util.Iterator;
/* loaded from: classes4.dex */
public enum StandardLevel {
    OFF(0),
    FATAL(100),
    ERROR(200),
    WARN(300),
    INFO(400),
    DEBUG(500),
    TRACE(DeviceConfigConstants.VIDEO_BITRATE_600_KBPS),
    ALL(Integer.MAX_VALUE);
    
    private static final EnumSet<StandardLevel> LEVELSET = EnumSet.allOf(StandardLevel.class);
    private final int intLevel;

    StandardLevel(int i) {
        this.intLevel = i;
    }

    public static StandardLevel getStandardLevel(int i) {
        StandardLevel standardLevel = OFF;
        Iterator it2 = LEVELSET.iterator();
        while (it2.hasNext()) {
            StandardLevel standardLevel2 = (StandardLevel) it2.next();
            if (standardLevel2.intLevel() > i) {
                break;
            }
            standardLevel = standardLevel2;
        }
        return standardLevel;
    }

    public int intLevel() {
        return this.intLevel;
    }
}
