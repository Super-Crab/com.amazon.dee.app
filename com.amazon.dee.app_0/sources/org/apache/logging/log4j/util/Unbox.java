package org.apache.logging.log4j.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
@PerformanceSensitive({"allocation"})
/* loaded from: classes4.dex */
public class Unbox {
    private static final int BITS_PER_INT = 32;
    private static final int RINGBUFFER_MIN_SIZE = 32;
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final int RINGBUFFER_SIZE = calculateRingBufferSize("log4j.unbox.ringbuffer.size");
    private static final int MASK = RINGBUFFER_SIZE - 1;
    private static ThreadLocal<State> threadLocalState = new ThreadLocal<>();
    private static WebSafeState webSafeState = new WebSafeState();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class State {
        private int current;
        private final StringBuilder[] ringBuffer = new StringBuilder[Unbox.RINGBUFFER_SIZE];

        State() {
            int i = 0;
            while (true) {
                StringBuilder[] sbArr = this.ringBuffer;
                if (i < sbArr.length) {
                    sbArr[i] = new StringBuilder(21);
                    i++;
                } else {
                    return;
                }
            }
        }

        public StringBuilder getStringBuilder() {
            StringBuilder[] sbArr = this.ringBuffer;
            int i = Unbox.MASK;
            int i2 = this.current;
            this.current = i2 + 1;
            StringBuilder sb = sbArr[i & i2];
            sb.setLength(0);
            return sb;
        }

        public boolean isBoxedPrimitive(StringBuilder sb) {
            int i = 0;
            while (true) {
                StringBuilder[] sbArr = this.ringBuffer;
                if (i < sbArr.length) {
                    if (sb == sbArr[i]) {
                        return true;
                    }
                    i++;
                } else {
                    return false;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class WebSafeState {
        private final ThreadLocal<int[]> current;
        private final ThreadLocal<StringBuilder[]> ringBuffer;

        private WebSafeState() {
            this.ringBuffer = new ThreadLocal<>();
            this.current = new ThreadLocal<>();
        }

        public StringBuilder getStringBuilder() {
            StringBuilder[] sbArr = this.ringBuffer.get();
            if (sbArr == null) {
                sbArr = new StringBuilder[Unbox.RINGBUFFER_SIZE];
                for (int i = 0; i < sbArr.length; i++) {
                    sbArr[i] = new StringBuilder(21);
                }
                this.ringBuffer.set(sbArr);
                this.current.set(new int[1]);
            }
            int[] iArr = this.current.get();
            int i2 = Unbox.MASK;
            int i3 = iArr[0];
            iArr[0] = i3 + 1;
            StringBuilder sb = sbArr[i2 & i3];
            sb.setLength(0);
            return sb;
        }

        public boolean isBoxedPrimitive(StringBuilder sb) {
            StringBuilder[] sbArr = this.ringBuffer.get();
            if (sbArr == null) {
                return false;
            }
            for (StringBuilder sb2 : sbArr) {
                if (sb == sb2) {
                    return true;
                }
            }
            return false;
        }
    }

    private Unbox() {
    }

    @PerformanceSensitive({"allocation"})
    public static StringBuilder box(float f) {
        StringBuilder sb = getSB();
        sb.append(f);
        return sb;
    }

    private static int calculateRingBufferSize(String str) {
        String stringProperty = PropertiesUtil.getProperties().getStringProperty(str, String.valueOf(32));
        try {
            int parseInt = Integer.parseInt(stringProperty);
            if (parseInt < 32) {
                LOGGER.warn("Invalid {} {}, using minimum size {}.", (Object) str, (Object) stringProperty, (Object) 32);
                parseInt = 32;
            }
            return ceilingNextPowerOfTwo(parseInt);
        } catch (Exception unused) {
            LOGGER.warn("Invalid {} {}, using default size {}.", (Object) str, (Object) stringProperty, (Object) 32);
            return 32;
        }
    }

    private static int ceilingNextPowerOfTwo(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }

    static int getRingbufferSize() {
        return RINGBUFFER_SIZE;
    }

    private static StringBuilder getSB() {
        return Constants.ENABLE_THREADLOCALS ? getState().getStringBuilder() : webSafeState.getStringBuilder();
    }

    private static State getState() {
        State state = threadLocalState.get();
        if (state == null) {
            State state2 = new State();
            threadLocalState.set(state2);
            return state2;
        }
        return state;
    }

    @PerformanceSensitive({"allocation"})
    public static StringBuilder box(double d) {
        StringBuilder sb = getSB();
        sb.append(d);
        return sb;
    }

    @PerformanceSensitive({"allocation"})
    public static StringBuilder box(short s) {
        StringBuilder sb = getSB();
        sb.append((int) s);
        return sb;
    }

    @PerformanceSensitive({"allocation"})
    public static StringBuilder box(int i) {
        StringBuilder sb = getSB();
        sb.append(i);
        return sb;
    }

    @PerformanceSensitive({"allocation"})
    public static StringBuilder box(char c) {
        StringBuilder sb = getSB();
        sb.append(c);
        return sb;
    }

    @PerformanceSensitive({"allocation"})
    public static StringBuilder box(long j) {
        StringBuilder sb = getSB();
        sb.append(j);
        return sb;
    }

    @PerformanceSensitive({"allocation"})
    public static StringBuilder box(byte b) {
        StringBuilder sb = getSB();
        sb.append((int) b);
        return sb;
    }

    @PerformanceSensitive({"allocation"})
    public static StringBuilder box(boolean z) {
        StringBuilder sb = getSB();
        sb.append(z);
        return sb;
    }
}
