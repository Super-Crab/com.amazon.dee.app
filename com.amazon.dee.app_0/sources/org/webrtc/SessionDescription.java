package org.webrtc;

import java.util.Locale;
/* loaded from: classes5.dex */
public class SessionDescription {
    public final String description;
    public final Type type;

    /* loaded from: classes5.dex */
    public enum Type {
        OFFER,
        PRANSWER,
        ANSWER;

        public String canonicalForm() {
            return name().toLowerCase(Locale.US);
        }
    }

    public SessionDescription(Type type, String str) {
        this.type = type;
        this.description = str;
    }
}
