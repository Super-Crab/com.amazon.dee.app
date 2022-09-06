package com.amazon.device.crashmanager.utils;

import com.amazon.device.utils.det.DetUtil;
import com.amazon.dp.logger.DPLogger;
import java.io.Writer;
/* loaded from: classes12.dex */
public class CrashReportUtil {
    private static final DPLogger log = new DPLogger("CrashReportUtil");
    private final String TAG = CrashReportUtil.class.getSimpleName();

    public static void addCrashDuplicateHeader(String str, String str2, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil, Writer writer, DetUtil.HeaderProcessor headerProcessor) throws Exception {
        if (str == null || str2 == null) {
            return;
        }
        Integer valueOf = Integer.valueOf(crashDescriptorDedupeUtil.getCount(str, str2));
        log.debug("CrashReportUtil", "About to add de-dupe count into header", "Crash Descriptor", str2, "De-dupe Count", valueOf);
        if (valueOf.intValue() <= 1) {
            return;
        }
        headerProcessor.process("CrashDuplicateCount", valueOf.toString(), writer);
    }
}
