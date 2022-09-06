package com.amazon.communication;

import com.amazon.communication.PowerManagerWrapper;
/* loaded from: classes12.dex */
public class NullPowerManager implements PowerManagerWrapper {
    public static final PowerManagerWrapper.WakeLock NULL_WAKE_LOCK = new PowerManagerWrapper.WakeLock() { // from class: com.amazon.communication.NullPowerManager.1
        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public void acquire() {
        }

        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public void acquire(long j) {
        }

        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public boolean isHeld() {
            return false;
        }

        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public void release() {
        }

        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public void setReferenceCounted(boolean z) {
        }
    };

    @Override // com.amazon.communication.PowerManagerWrapper
    public PowerManagerWrapper.WakeLock newWakeLock(int i, String str) {
        return NULL_WAKE_LOCK;
    }
}
