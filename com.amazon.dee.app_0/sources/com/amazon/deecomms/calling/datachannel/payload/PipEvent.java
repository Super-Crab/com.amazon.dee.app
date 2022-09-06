package com.amazon.deecomms.calling.datachannel.payload;
/* loaded from: classes12.dex */
public final class PipEvent {
    private final String action;
    private final String callId;
    private final String position;
    private final String requestId;
    private final String visibility;

    /* loaded from: classes12.dex */
    public enum Action {
        UPDATE
    }

    /* loaded from: classes12.dex */
    public static final class Builder {
        private Action action;
        private String callId;
        private Position position;
        private String requestId;
        private Visibility visibility;

        public PipEvent build() {
            return new PipEvent(this.requestId, this.callId, this.action, this.visibility, this.position);
        }

        public Builder withAction(Action action) {
            this.action = action;
            return this;
        }

        public Builder withCallId(String str) {
            this.callId = str;
            return this;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withRequestId(String str) {
            this.requestId = str;
            return this;
        }

        public Builder withVisibility(Visibility visibility) {
            this.visibility = visibility;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public enum Position {
    }

    /* loaded from: classes12.dex */
    public enum Visibility {
        VISIBLE,
        INVISIBLE
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getPosition() {
        return this.position;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getVisibility() {
        return this.visibility;
    }

    private PipEvent(String str, String str2, Action action, Visibility visibility, Position position) {
        this.requestId = str;
        this.callId = str2;
        this.action = action.name();
        String str3 = null;
        this.visibility = visibility != null ? visibility.name() : null;
        this.position = position != null ? position.name() : str3;
    }
}
