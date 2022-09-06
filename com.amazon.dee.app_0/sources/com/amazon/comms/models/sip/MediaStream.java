package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class MediaStream {
    private Direction direction;
    private Type type;

    /* loaded from: classes11.dex */
    public enum Direction {
        RECVONLY,
        SENDRECV,
        SENDONLY,
        INACTIVE,
        OFF
    }

    /* loaded from: classes11.dex */
    public static class MediaStreamBuilder {
        private Direction direction;
        private Type type;

        MediaStreamBuilder() {
        }

        public MediaStream build() {
            return new MediaStream(this.type, this.direction);
        }

        public MediaStreamBuilder direction(Direction direction) {
            this.direction = direction;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaStream.MediaStreamBuilder(type=");
            outline107.append(this.type);
            outline107.append(", direction=");
            outline107.append(this.direction);
            outline107.append(")");
            return outline107.toString();
        }

        public MediaStreamBuilder type(Type type) {
            this.type = type;
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public enum Type {
        AUDIO,
        VIDEO,
        TEXT
    }

    public MediaStream() {
    }

    public static MediaStreamBuilder builder() {
        return new MediaStreamBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof MediaStream;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaStream)) {
            return false;
        }
        MediaStream mediaStream = (MediaStream) obj;
        if (!mediaStream.canEqual(this)) {
            return false;
        }
        Type type = getType();
        Type type2 = mediaStream.getType();
        if (type != null ? !type.equals(type2) : type2 != null) {
            return false;
        }
        Direction direction = getDirection();
        Direction direction2 = mediaStream.getDirection();
        return direction != null ? direction.equals(direction2) : direction2 == null;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        Type type = getType();
        int i = 43;
        int hashCode = type == null ? 43 : type.hashCode();
        Direction direction = getDirection();
        int i2 = (hashCode + 59) * 59;
        if (direction != null) {
            i = direction.hashCode();
        }
        return i2 + i;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaStream(type=");
        outline107.append(getType());
        outline107.append(", direction=");
        outline107.append(getDirection());
        outline107.append(")");
        return outline107.toString();
    }

    private MediaStream(Type type, Direction direction) {
        this.type = type;
        this.direction = direction;
    }
}
