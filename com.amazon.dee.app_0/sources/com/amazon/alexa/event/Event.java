package com.amazon.alexa.event;

import com.amazon.alexa.endpoint.Endpoint;
import com.amazon.alexa.header.Header;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes7.dex */
public class Event<NamespaceType, NameType, InstanceType, PayloadType> {
    @CheckForNull
    private final Endpoint endpoint;
    @CheckForNull
    private final Header<NamespaceType, NameType, InstanceType> header;
    @CheckForNull
    private final PayloadType payload;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes7.dex */
    public static class EventBuilder<NamespaceType, NameType, InstanceType, PayloadType> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Endpoint endpoint;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Header<NamespaceType, NameType, InstanceType> header;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private PayloadType payload;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        EventBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Event<NamespaceType, NameType, InstanceType, PayloadType> build() {
            return new Event<>(this.header, this.endpoint, this.payload);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public EventBuilder<NamespaceType, NameType, InstanceType, PayloadType> endpoint(Endpoint endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public EventBuilder<NamespaceType, NameType, InstanceType, PayloadType> header(Header<NamespaceType, NameType, InstanceType> header) {
            this.header = header;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public EventBuilder<NamespaceType, NameType, InstanceType, PayloadType> payload(PayloadType payloadtype) {
            this.payload = payloadtype;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Event.EventBuilder(header=");
            outline107.append(this.header);
            outline107.append(", endpoint=");
            outline107.append(this.endpoint);
            outline107.append(", payload=");
            return GeneratedOutlineSupport1.outline88(outline107, this.payload, ")");
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected Event(Header<NamespaceType, NameType, InstanceType> header, Endpoint endpoint, PayloadType payloadtype) {
        this.header = header;
        this.endpoint = endpoint;
        this.payload = payloadtype;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <NamespaceType, NameType, InstanceType, PayloadType> EventBuilder<NamespaceType, NameType, InstanceType, PayloadType> builder() {
        return new EventBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof Event;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        if (!event.canEqual(this)) {
            return false;
        }
        Header<NamespaceType, NameType, InstanceType> header = getHeader();
        Header<NamespaceType, NameType, InstanceType> header2 = event.getHeader();
        if (header != null ? !header.equals(header2) : header2 != null) {
            return false;
        }
        Endpoint endpoint = getEndpoint();
        Endpoint endpoint2 = event.getEndpoint();
        if (endpoint != null ? !endpoint.equals(endpoint2) : endpoint2 != null) {
            return false;
        }
        PayloadType payload = getPayload();
        Object payload2 = event.getPayload();
        return payload != null ? payload.equals(payload2) : payload2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Endpoint getEndpoint() {
        return this.endpoint;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Header<NamespaceType, NameType, InstanceType> getHeader() {
        return this.header;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PayloadType getPayload() {
        return this.payload;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Header<NamespaceType, NameType, InstanceType> header = getHeader();
        int i = 43;
        int hashCode = header == null ? 43 : header.hashCode();
        Endpoint endpoint = getEndpoint();
        int hashCode2 = ((hashCode + 59) * 59) + (endpoint == null ? 43 : endpoint.hashCode());
        PayloadType payload = getPayload();
        int i2 = hashCode2 * 59;
        if (payload != null) {
            i = payload.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Event(header=");
        outline107.append(getHeader());
        outline107.append(", endpoint=");
        outline107.append(getEndpoint());
        outline107.append(", payload=");
        outline107.append(getPayload());
        outline107.append(")");
        return outline107.toString();
    }
}
