package com.amazon.alexa.devices.speechrecognizer;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class AudioEvent {
    private long beginIndex;
    private long beginWakewordIndex;
    private int confidence;
    private long endWakewordIndex;
    private SpeechInitiator initiator;
    private Map<String, byte[]> metadataMap;
    private int preRollByteIndex;
    private String token;
    private String wakeword;

    /* loaded from: classes6.dex */
    public static class Builder {
        private final AudioEvent audioEvent = new AudioEvent();

        public AudioEvent build() {
            return this.audioEvent;
        }

        public Builder withBeginIndex(long j) {
            if (j >= 0) {
                this.audioEvent.beginIndex = j;
                return this;
            }
            throw new IllegalArgumentException("beginIndex cannot be negative");
        }

        public Builder withMetadata(String str, byte[] bArr) {
            if (!TextUtils.isEmpty(str)) {
                this.audioEvent.metadataMap.put(str, bArr);
                return this;
            }
            throw new IllegalArgumentException("Metadata format");
        }

        public Builder withMultiTurn() {
            this.audioEvent.wakeword = null;
            AudioEvent audioEvent = this.audioEvent;
            audioEvent.beginWakewordIndex = audioEvent.beginIndex;
            AudioEvent audioEvent2 = this.audioEvent;
            audioEvent2.endWakewordIndex = audioEvent2.beginIndex;
            this.audioEvent.confidence = -1;
            this.audioEvent.initiator = SpeechInitiator.EXPECT_SPEECH_PROMPT;
            return this;
        }

        public Builder withPreroll(int i) {
            this.audioEvent.preRollByteIndex = i;
            return this;
        }

        public Builder withPressToTalk() {
            this.audioEvent.wakeword = null;
            AudioEvent audioEvent = this.audioEvent;
            audioEvent.beginWakewordIndex = audioEvent.beginIndex;
            AudioEvent audioEvent2 = this.audioEvent;
            audioEvent2.endWakewordIndex = audioEvent2.beginIndex;
            this.audioEvent.confidence = -1;
            this.audioEvent.initiator = SpeechInitiator.PRESS_TO_TALK;
            return this;
        }

        public Builder withPushToTalk() {
            this.audioEvent.wakeword = null;
            AudioEvent audioEvent = this.audioEvent;
            audioEvent.beginWakewordIndex = audioEvent.beginIndex;
            AudioEvent audioEvent2 = this.audioEvent;
            audioEvent2.endWakewordIndex = audioEvent2.beginIndex;
            this.audioEvent.confidence = -1;
            this.audioEvent.initiator = SpeechInitiator.PUSH_TO_TALK;
            return this;
        }

        public Builder withToken(String str) {
            this.audioEvent.token = str;
            return this;
        }

        public Builder withWakeword(String str, long j, long j2, int i) {
            this.audioEvent.wakeword = str;
            this.audioEvent.beginWakewordIndex = j;
            if (j >= 0) {
                this.audioEvent.endWakewordIndex = j2;
                if (j2 >= 0) {
                    this.audioEvent.confidence = i;
                    if (TextUtils.isEmpty(str)) {
                        if (i != -1) {
                            throw new IllegalArgumentException("Must set confidence to -1 when Wakework is empty");
                        }
                    } else if (i < 0 || i > 100) {
                        throw new IllegalArgumentException("Confidence must be within the range of 0 to 100");
                    }
                    this.audioEvent.initiator = SpeechInitiator.WAKE_WORD;
                    return this;
                }
                throw new IllegalArgumentException("endWakewordIndex less than 0");
            }
            throw new IllegalArgumentException("beginWakewordIndex less than 0");
        }

        public Builder withoutWakeword() {
            this.audioEvent.wakeword = null;
            AudioEvent audioEvent = this.audioEvent;
            audioEvent.beginWakewordIndex = audioEvent.beginIndex;
            AudioEvent audioEvent2 = this.audioEvent;
            audioEvent2.endWakewordIndex = audioEvent2.beginIndex;
            this.audioEvent.confidence = -1;
            this.audioEvent.initiator = SpeechInitiator.TTS_PROMPT;
            return this;
        }
    }

    public long getBeginIndex() {
        return this.beginIndex;
    }

    public long getBeginWakewordIndex() {
        return this.beginWakewordIndex;
    }

    public int getConfidence() {
        return this.confidence;
    }

    public long getEndWakewordIndex() {
        return this.endWakewordIndex;
    }

    public SpeechInitiator getInitiator() {
        return this.initiator;
    }

    public Map<String, byte[]> getMetadataMap() {
        return this.metadataMap;
    }

    public int getPreRollByteIndex() {
        return this.preRollByteIndex;
    }

    public String getToken() {
        return this.token;
    }

    public String getWakeword() {
        return this.wakeword;
    }

    private AudioEvent() {
        this.metadataMap = new HashMap();
    }
}
