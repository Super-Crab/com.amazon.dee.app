package com.amazon.alexa.rangecontroller.lib.model.factory;

import com.amazon.alexa.changereport.Cause;
import com.amazon.alexa.changereport.Change;
import com.amazon.alexa.changereport.ChangeReport;
import com.amazon.alexa.changereport.payload.ChangeReportPayload;
import com.amazon.alexa.changereport.type.ChangeReportType;
import com.amazon.alexa.context.Context;
import com.amazon.alexa.event.Event;
import com.amazon.alexa.property.Property;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.PropertyName;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.Generated;
import org.apache.commons.net.ntp.TimeStamp;
/* loaded from: classes9.dex */
public class RcChangeReportFactory {
    private Context context;
    private Event<NamespaceName, HeaderName, EmptyInstance, ChangeReportPayload> event;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcChangeReportFactory() {
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcChangeReportFactory context(Context context) {
        this.context = context;
        return this;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcChangeReportFactory event(Event<NamespaceName, HeaderName, EmptyInstance, ChangeReportPayload> event) {
        this.event = event;
        return this;
    }

    @NonNull
    public static RcChangeReportFactory forTimestamp(@Nonnull TimeStamp timeStamp) {
        return forTimestamp(timeStamp, null);
    }

    @NonNull
    private static RcChangeReportFactory newInstance(Event<NamespaceName, HeaderName, EmptyInstance, ChangeReportPayload> event, Context context) {
        return new RcChangeReportFactory().event(event).context(context);
    }

    @NonNull
    public ChangeReport<NamespaceName, HeaderName, EmptyInstance, ChangeReportPayload> create() {
        return ChangeReport.builder().event(this.event).context(this.context).build();
    }

    @NonNull
    protected static RcChangeReportFactory forTimestamp(@Nonnull TimeStamp timeStamp, @CheckForNull UUID uuid) {
        Property build = Property.builder().namespace(NamespaceName.RANGE_CONTROLLER).name(PropertyName.TIMESTAMP).value(timeStamp).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(build);
        return newInstance(RcEventFactory.forChangeReport(ChangeReportPayload.builder().change(Change.builder().cause(Cause.builder().type(ChangeReportType.APP_INTERACTION).build()).properties(arrayList).build()).build(), uuid).create(), null);
    }
}
