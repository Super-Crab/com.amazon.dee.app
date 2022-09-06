package com.amazon.deecomms.calling.datachannel;

import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.calling.service.DataChannelEvent;
import com.amazon.comms.calling.service.DataChannelEventListener;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.enums.DataChannelLabel;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class AmcIncallDataChannelEventListenerImpl implements DataChannelEventListener {
    private static AmcIncallDataChannelEventListenerImpl instance;
    private static CommsLogger log = CommsLogger.getLogger(AmcIncallDataChannelEventListenerImpl.class);
    private Call call;
    private boolean isRegisteredToCall = false;
    private final Map<String, List<AmcIncallDataChannelApplication>> applicationMap = new HashMap();

    AmcIncallDataChannelEventListenerImpl() {
    }

    public static AmcIncallDataChannelEventListenerImpl getInstance(Call call) {
        if (instance == null) {
            instance = new AmcIncallDataChannelEventListenerImpl();
        }
        if (!instance.isRegisteredToCall()) {
            instance.registerDataChannelEventListener(call);
        }
        return instance;
    }

    private void registerDataChannelEventListener(Call call) {
        this.call = call;
        this.call.registerDataChannelEventListener(this);
        this.isRegisteredToCall = true;
    }

    public boolean isRegisteredToCall() {
        return this.isRegisteredToCall;
    }

    @Override // com.amazon.comms.calling.service.DataChannelEventListener
    public void onBufferedAmountChange(Call call, DataChannelEvent dataChannelEvent) {
    }

    @Override // com.amazon.comms.calling.service.DataChannelEventListener
    public void onMessageReceived(Call call, DataChannelEvent dataChannelEvent) {
        log.i("DataChannel event received");
        if (!DataChannelLabel.IN_CALL_APPLICATION.getName().equals((String) dataChannelEvent.getParams().get("label"))) {
            log.w("Unknown DataChannel label. Ignoring.");
            return;
        }
        DataChannelDTO dataChannelDTO = (DataChannelDTO) dataChannelEvent.getParams().get("data");
        if (dataChannelDTO == null) {
            log.w("Null data received for DataChannel event. Ignoring.");
            return;
        }
        try {
            CommsDataChannelEvent commsDataChannelEvent = (CommsDataChannelEvent) new GsonBuilder().setVersion(PayloadVersion.LATEST.getValue()).create().fromJson(new String(dataChannelDTO.getData(), StandardCharsets.UTF_8), (Class<Object>) CommsDataChannelEvent.class);
            if (commsDataChannelEvent.getHeader().getNamespace() != Namespace.IN_CALL_CAPABILITY && commsDataChannelEvent.getHeader().getNamespace() != Namespace.IN_CALL_PIP) {
                log.w("Wrong namespace for DataChannel event. Ignoring.");
                return;
            }
            String commsDataChannelHeader = commsDataChannelEvent.getHeader().getInstance();
            if (!this.applicationMap.containsKey(commsDataChannelHeader)) {
                log.w("Data Channel message received for unknown application. Ignoring.");
                return;
            }
            for (AmcIncallDataChannelApplication amcIncallDataChannelApplication : this.applicationMap.get(commsDataChannelHeader)) {
                amcIncallDataChannelApplication.onDataChannelEventReceived(commsDataChannelEvent);
            }
        } catch (JsonSyntaxException e) {
            CommsLogger commsLogger = log;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Parsing of DataChannel event failed.\nException was: ");
            outline1.append(e.getMessage());
            outline1.append(Arrays.toString(e.getStackTrace()));
            commsLogger.w(outline1.toString());
        }
    }

    @Override // com.amazon.comms.calling.service.DataChannelEventListener
    public void onStateChange(Call call, DataChannelEvent dataChannelEvent) {
    }

    public void registerApplication(AmcIncallDataChannelApplication amcIncallDataChannelApplication) {
        if (!this.applicationMap.keySet().contains(amcIncallDataChannelApplication.getApplicationName())) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(amcIncallDataChannelApplication);
            this.applicationMap.put(amcIncallDataChannelApplication.getApplicationName(), arrayList);
            return;
        }
        List<AmcIncallDataChannelApplication> list = this.applicationMap.get(amcIncallDataChannelApplication.getApplicationName());
        list.add(amcIncallDataChannelApplication);
        this.applicationMap.put(amcIncallDataChannelApplication.getApplicationName(), list);
    }

    public void unregisterDataChannelEventListener() {
        if (this.isRegisteredToCall) {
            this.call.unregisterDataChannelEventListener(this);
            this.call = null;
            this.isRegisteredToCall = false;
        }
        this.applicationMap.clear();
    }
}
