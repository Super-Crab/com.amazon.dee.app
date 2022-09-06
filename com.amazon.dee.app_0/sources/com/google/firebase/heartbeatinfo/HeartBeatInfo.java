package com.google.firebase.heartbeatinfo;

import androidx.annotation.NonNull;
/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* loaded from: classes3.dex */
public interface HeartBeatInfo {

    /* compiled from: com.google.firebase:firebase-common@@19.3.0 */
    /* loaded from: classes3.dex */
    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);
        
        private final int code;

        HeartBeat(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }
    }

    @NonNull
    HeartBeat getHeartBeatCode(@NonNull String str);
}
