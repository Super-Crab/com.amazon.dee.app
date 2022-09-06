package com.amazon.deecomms.calling.util;

import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.datachannel.Capability;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelEvent;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelHeader;
import com.amazon.deecomms.calling.datachannel.EventName;
import com.amazon.deecomms.calling.datachannel.InCallOrientation;
import com.amazon.deecomms.calling.datachannel.Namespace;
import com.amazon.deecomms.calling.datachannel.PayloadVersion;
import com.amazon.deecomms.calling.datachannel.capabilities.screenevents.OrientationChange;
import com.amazon.deecomms.calling.enums.DataChannelLabel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public final class AmcCallUtil {
    private static final String APPLICATION = "Calling";
    private static CommsLogger log = CommsLogger.getLogger(AmcCallUtil.class);

    private AmcCallUtil() {
    }

    private static InCallOrientation getOrientation(int i) {
        if (i != 1) {
            if (i == 2) {
                return InCallOrientation.PORTRAIT_UP;
            }
            if (i != 3) {
                return InCallOrientation.PORTRAIT_DOWN;
            }
            return InCallOrientation.LANDSCAPE_RIGHT;
        }
        return InCallOrientation.LANDSCAPE_LEFT;
    }

    public static void sendOrientationChangeEvent(Call call, int i, int i2) {
        OrientationChange build = OrientationChange.builder().with(getOrientation(i), getOrientation(i2)).build();
        CommsDataChannelHeader build2 = CommsDataChannelHeader.builder().withNamespace(Namespace.IN_CALL_CAPABILITY).withInstance(APPLICATION).withCapability(Capability.SCREEN_EVENTS).withName(EventName.NEW_SCREEN_ORIENTATION.toString()).build();
        Gson create = new GsonBuilder().setVersion(PayloadVersion.LATEST.getValue()).create();
        String json = create.toJson(CommsDataChannelEvent.builder().withHeader(build2).withPayload(create.toJsonTree(build)).build());
        call.sendData(new DataChannelDTO(DataChannelLabel.IN_CALL_APPLICATION.getName(), json.getBytes(StandardCharsets.UTF_8), false));
        GeneratedOutlineSupport1.outline159("Sent OrientationChangeEvent through DataChannel data: ", json, log);
    }
}
