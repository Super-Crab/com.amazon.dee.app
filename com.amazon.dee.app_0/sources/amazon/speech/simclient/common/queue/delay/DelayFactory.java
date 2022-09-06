package amazon.speech.simclient.common.queue.delay;

import java.util.Random;
/* loaded from: classes.dex */
public class DelayFactory {
    public static Delay exponential(long j) {
        return exponentialWithMaxDelay(j, Long.MAX_VALUE);
    }

    public static Delay exponentialWithMaxDelay(final long j, final long j2) {
        if (j >= 0) {
            if (j2 > 0) {
                return new Delay() { // from class: amazon.speech.simclient.common.queue.delay.DelayFactory.5
                    @Override // amazon.speech.simclient.common.queue.delay.Delay
                    public long delay(int i) {
                        return (long) Math.min(Math.pow(2.0d, i) * j, j2);
                    }
                };
            }
            throw new IllegalArgumentException("Maximum ms must be positive");
        }
        throw new IllegalArgumentException("Delay can not be negative");
    }

    public static Delay fixed(final long j) {
        if (j >= 0) {
            return new Delay() { // from class: amazon.speech.simclient.common.queue.delay.DelayFactory.2
                @Override // amazon.speech.simclient.common.queue.delay.Delay
                public long delay(int i) {
                    return j;
                }
            };
        }
        throw new IllegalArgumentException("Delay can not be negative");
    }

    public static Delay linear(final long j) {
        if (j >= 0) {
            return new Delay() { // from class: amazon.speech.simclient.common.queue.delay.DelayFactory.3
                @Override // amazon.speech.simclient.common.queue.delay.Delay
                public long delay(int i) {
                    return j * (i + 1);
                }
            };
        }
        throw new IllegalArgumentException("Delay can not be negative");
    }

    public static Delay none() {
        return new Delay() { // from class: amazon.speech.simclient.common.queue.delay.DelayFactory.1
            @Override // amazon.speech.simclient.common.queue.delay.Delay
            public long delay(int i) {
                return 0L;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long randomInRange(long j, long j2, Random random) {
        return (random.nextFloat() * ((float) (j2 - j))) + j;
    }

    public static Delay exponential(long j, long j2) {
        return exponential(j, j2, new Random());
    }

    static Delay exponential(final long j, final long j2, final Random random) {
        if (j < 0 || j2 < 0) {
            throw new IllegalArgumentException("Delay can not be negative");
        }
        if (j <= j2) {
            return new Delay() { // from class: amazon.speech.simclient.common.queue.delay.DelayFactory.6
                @Override // amazon.speech.simclient.common.queue.delay.Delay
                public long delay(int i) {
                    return (long) Math.min(Math.pow(2.0d, i) * DelayFactory.randomInRange(j, j2, random), 9.223372036854776E18d);
                }
            };
        }
        throw new IllegalArgumentException("Min delay can not be greater than max delay");
    }

    public static Delay linear(long j, long j2) {
        return linear(j, j2, new Random());
    }

    static Delay linear(final long j, final long j2, final Random random) {
        if (j < 0 || j2 < 0) {
            throw new IllegalArgumentException("Delay can not be negative");
        }
        if (j <= j2) {
            return new Delay() { // from class: amazon.speech.simclient.common.queue.delay.DelayFactory.4
                @Override // amazon.speech.simclient.common.queue.delay.Delay
                public long delay(int i) {
                    return DelayFactory.randomInRange(j, j2, random) * (i + 1);
                }
            };
        }
        throw new IllegalArgumentException("Min delay can not be greater than max delay");
    }
}
