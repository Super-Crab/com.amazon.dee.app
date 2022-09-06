package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Locale;
/* loaded from: classes.dex */
public final class AudioContentType {
    private final int bitRate;
    private final int frameSize;
    private final String name;
    private final int preambleSize;
    private final int rate;

    /* loaded from: classes.dex */
    public static final class Builder {
        public int bitRate;
        public int frameSize;
        public String name;
        public int preambleSize;
        public int rate;

        public Builder bitRate(int i) {
            this.bitRate = i;
            return this;
        }

        public AudioContentType build() {
            Preconditions.notNull(this.name, "name");
            return new AudioContentType(this);
        }

        public Builder frameSize(int i) {
            this.frameSize = i;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder preambleSize(int i) {
            this.preambleSize = i;
            return this;
        }

        public Builder rate(int i) {
            this.rate = i;
            return this;
        }
    }

    public AudioContentType(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.preambleSize = builder.preambleSize;
        this.name = builder.name;
        this.rate = builder.rate;
        this.bitRate = builder.bitRate;
        this.frameSize = builder.frameSize;
    }

    public String toString() {
        return String.format(Locale.US, "%s; rate=%d; preamble-size=%d; bit-rate=%d; frame-size-milliseconds=%d", this.name, Integer.valueOf(this.rate), Integer.valueOf(this.preambleSize), Integer.valueOf(this.bitRate), Integer.valueOf(this.frameSize));
    }
}
