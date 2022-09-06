package com.amazon.identity.auth.device;

import java.util.Locale;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class lz {
    public static String a(Locale locale) {
        return locale.toString().replace("_", ProcessIdUtil.DEFAULT_PROCESSID);
    }
}
