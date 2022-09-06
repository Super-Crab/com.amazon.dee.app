package com.brentvatne.receiver;
/* loaded from: classes13.dex */
public interface BecomingNoisyListener {
    public static final BecomingNoisyListener NO_OP = new BecomingNoisyListener() { // from class: com.brentvatne.receiver.BecomingNoisyListener.1
        @Override // com.brentvatne.receiver.BecomingNoisyListener
        public void onAudioBecomingNoisy() {
        }
    };

    void onAudioBecomingNoisy();
}
