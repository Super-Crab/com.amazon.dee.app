package com.amazon.alexa.audiopersonalization.api;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/* loaded from: classes6.dex */
public class AudioProfileGsonWrapper {

    @SuppressFBWarnings
    /* loaded from: classes6.dex */
    public class AudioChannel {
        public Band[] bands;
        public String type;

        public AudioChannel() {
        }
    }

    @SuppressFBWarnings
    /* loaded from: classes6.dex */
    public class Band {
        public Integer frequency;
        public Float threshold;

        public Band() {
        }
    }
}
