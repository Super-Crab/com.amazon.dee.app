package com.amazon.comms.ringservice.pjsip;

import amazon.communication.rlm.PackCodes;
import com.amazon.comms.log.CommsLogger;
import org.pjsip.pjsua2.LogEntry;
import org.pjsip.pjsua2.LogWriter;
/* loaded from: classes12.dex */
public class PjsipLogWriter extends LogWriter {
    private static final int SPLIT_BUFFER_SIZE = 4000;
    private static final CommsLogger log = CommsLogger.getLogger(PjsipLogWriter.class);

    @Override // org.pjsip.pjsua2.LogWriter
    public void write(LogEntry logEntry) {
        String msg = logEntry.getMsg();
        while (true) {
            String str = null;
            if (msg.length() > 4000) {
                int lastIndexOf = msg.lastIndexOf(10, PackCodes.PACK_CODE_MAX) + 1;
                if (lastIndexOf == 0) {
                    lastIndexOf = 4000;
                }
                String substring = msg.substring(lastIndexOf);
                msg = msg.substring(0, lastIndexOf - 1);
                str = substring;
            }
            int level = logEntry.getLevel();
            if (level == 0) {
                log.e(msg);
            } else if (level == 1) {
                log.w(msg);
            } else if (level == 2) {
                log.i(msg);
            } else if (level == 3) {
                log.ds(msg);
            } else if (level == 4) {
                log.ds(msg);
            } else if (level != 5) {
                log.v(msg);
            } else {
                log.ds(msg);
            }
            if (str == null) {
                return;
            }
            msg = str;
        }
    }
}
