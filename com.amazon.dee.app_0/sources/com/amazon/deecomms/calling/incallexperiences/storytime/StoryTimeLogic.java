package com.amazon.deecomms.calling.incallexperiences.storytime;

import android.view.MotionEvent;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.datachannel.Capability;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelEvent;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelHeader;
import com.amazon.deecomms.calling.datachannel.EventName;
import com.amazon.deecomms.calling.datachannel.Namespace;
import com.amazon.deecomms.calling.datachannel.PayloadVersion;
import com.amazon.deecomms.calling.datachannel.capabilities.screenevents.ReserveInCallControls;
import com.amazon.deecomms.calling.datachannel.capabilities.screenevents.ScreenEvent;
import com.amazon.deecomms.calling.enums.DataChannelLabel;
import com.amazon.deecomms.calling.model.Boundary;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.StandardCharsets;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Singleton
/* loaded from: classes12.dex */
public class StoryTimeLogic {
    private static final String APPLICATION = "StoryTime";
    private static CommsLogger log = CommsLogger.getLogger(StoryTimeLogic.class);
    private final Call call;
    private final Gson gson = new GsonBuilder().setVersion(PayloadVersion.LATEST.getValue()).create();

    /* JADX INFO: Access modifiers changed from: package-private */
    public StoryTimeLogic(Call call) {
        this.call = call;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReserveInCallControls processStoryTimeDataChannelEvents(CommsDataChannelEvent commsDataChannelEvent) {
        if (!commsDataChannelEvent.getHeader().getName().equals(EventName.RESERVE_IN_CALL_CONTROLS.toString())) {
            log.d("Wrong event name for StoryTime DataChannel event");
            return null;
        }
        return (ReserveInCallControls) this.gson.fromJson(commsDataChannelEvent.getPayload(), (Class<Object>) ReserveInCallControls.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean processTouchEvents(MotionEvent motionEvent, Boundary boundary) {
        if (motionEvent.getAction() != 0) {
            log.d("Not a ACTION_DOWN event. Ignoring!");
            return false;
        } else if (boundary.contains(motionEvent.getX(), motionEvent.getY())) {
            CommsLogger commsLogger = log;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Clicked inside in-call controls area, x=");
            outline1.append(motionEvent.getX());
            outline1.append(", y=");
            outline1.append(motionEvent.getY());
            commsLogger.d(outline1.toString());
            return false;
        } else {
            CommsDataChannelHeader build = CommsDataChannelHeader.builder().withNamespace(Namespace.IN_CALL_CAPABILITY).withInstance(APPLICATION).withCapability(Capability.SCREEN_EVENTS).withName(EventName.SCREEN_EVENT.toString()).build();
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            motionEvent.getPointerCoords(motionEvent.getPointerCount() - 1, pointerCoords);
            String json = this.gson.toJson(CommsDataChannelEvent.builder().withHeader(build).withPayload(this.gson.toJsonTree(ScreenEvent.builder().withAction(MotionEvent.actionToString(motionEvent.getAction())).withPointerCoords(pointerCoords).build())).build());
            this.call.sendData(new DataChannelDTO(DataChannelLabel.IN_CALL_APPLICATION.getName(), json.getBytes(StandardCharsets.UTF_8), false));
            GeneratedOutlineSupport1.outline159("Sent event through DataChannel data=", json, log);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendApplicationEvent(EventName eventName) {
        String json = this.gson.toJson(CommsDataChannelEvent.builder().withHeader(CommsDataChannelHeader.builder().withNamespace(Namespace.IN_CALL_CAPABILITY).withInstance(APPLICATION).withCapability(Capability.APPLICATION).withName(eventName.toString()).build()).build());
        this.call.sendData(new DataChannelDTO(DataChannelLabel.IN_CALL_APPLICATION.getName(), json.getBytes(StandardCharsets.UTF_8), false));
        GeneratedOutlineSupport1.outline159("Sent event through DataChannel data=", json, log);
    }
}
