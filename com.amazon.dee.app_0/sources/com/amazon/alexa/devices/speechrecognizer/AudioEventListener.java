package com.amazon.alexa.devices.speechrecognizer;

import com.amazon.alexa.devices.AlexaException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public interface AudioEventListener {

    /* loaded from: classes6.dex */
    public enum ABORT_REASON {
        STOP_AUDIO_CAPTURE(0),
        USER_ABORT(1),
        TIMEOUT(2);
        
        private static Map<Integer, ABORT_REASON> intToReasonMap = new HashMap();
        private int code;

        static {
            ABORT_REASON[] values;
            for (ABORT_REASON abort_reason : values()) {
                intToReasonMap.put(Integer.valueOf(abort_reason.code), abort_reason);
            }
        }

        ABORT_REASON(int i) {
            this.code = i;
        }

        public static ABORT_REASON fromCode(int i) {
            return intToReasonMap.get(Integer.valueOf(i));
        }

        public int getReasonCode() {
            return this.code;
        }
    }

    String startUtterance(AudioEvent audioEvent) throws AlexaException;

    void stopUtterance(String str, ABORT_REASON abort_reason) throws AlexaException;
}
