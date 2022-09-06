package com.amazon.alexa.response;

import com.amazon.alexa.context.Context;
import com.amazon.alexa.event.Event;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes10.dex */
public class Response<NamespaceType, ResponseType, InstanceType, PayloadType> {
    @CheckForNull
    private final Context context;
    @CheckForNull
    private final Event<NamespaceType, ResponseType, InstanceType, PayloadType> event;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes10.dex */
    public static class ResponseBuilder<NamespaceType, ResponseType, InstanceType, PayloadType> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Context context;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Event<NamespaceType, ResponseType, InstanceType, PayloadType> event;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        ResponseBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Response<NamespaceType, ResponseType, InstanceType, PayloadType> build() {
            return new Response<>(this.event, this.context);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ResponseBuilder<NamespaceType, ResponseType, InstanceType, PayloadType> context(Context context) {
            this.context = context;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ResponseBuilder<NamespaceType, ResponseType, InstanceType, PayloadType> event(Event<NamespaceType, ResponseType, InstanceType, PayloadType> event) {
            this.event = event;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Response.ResponseBuilder(event=");
            outline107.append(this.event);
            outline107.append(", context=");
            outline107.append(this.context);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Response(Event<NamespaceType, ResponseType, InstanceType, PayloadType> event, Context context) {
        this.event = event;
        this.context = context;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <NamespaceType, ResponseType, InstanceType, PayloadType> ResponseBuilder<NamespaceType, ResponseType, InstanceType, PayloadType> builder() {
        return new ResponseBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof Response;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Response)) {
            return false;
        }
        Response response = (Response) obj;
        if (!response.canEqual(this)) {
            return false;
        }
        Event<NamespaceType, ResponseType, InstanceType, PayloadType> event = getEvent();
        Event<NamespaceType, ResponseType, InstanceType, PayloadType> event2 = response.getEvent();
        if (event != null ? !event.equals(event2) : event2 != null) {
            return false;
        }
        Context context = getContext();
        Context context2 = response.getContext();
        return context != null ? context.equals(context2) : context2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Context getContext() {
        return this.context;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Event<NamespaceType, ResponseType, InstanceType, PayloadType> getEvent() {
        return this.event;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Event<NamespaceType, ResponseType, InstanceType, PayloadType> event = getEvent();
        int i = 43;
        int hashCode = event == null ? 43 : event.hashCode();
        Context context = getContext();
        int i2 = (hashCode + 59) * 59;
        if (context != null) {
            i = context.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Response(event=");
        outline107.append(getEvent());
        outline107.append(", context=");
        outline107.append(getContext());
        outline107.append(")");
        return outline107.toString();
    }
}
