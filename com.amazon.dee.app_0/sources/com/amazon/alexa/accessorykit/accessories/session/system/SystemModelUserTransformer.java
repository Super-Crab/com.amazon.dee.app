package com.amazon.alexa.accessorykit.accessories.session.system;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.accessories.utils.WritableArrayUtils;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class SystemModelUserTransformer implements MapModelTransformer<System.User> {
    private static final String AAP_CONNECTED_KEY = "aap_connected";
    private static final String ADDRESS_KEY = "address";
    private static final String CONNECTED_AUDIO_TYPES_KEY = "connected_audio_types";
    private static final String CONNECTED_KEY = "connected";
    private static final String FOCUS_KEY = "focus";
    private static final String ID_KEY = "id";
    private static final String LAST_KNOWN_AAP_CAPABILITY_KEY = "last_known_aap_capability";
    private static final String NAME_KEY = "name";
    private static final String PRIMARY_AUDIO_CONNECTION_TYPE_KEY = "primary_audio_connection_type";
    private static final String SELF_KEY = "self";
    private static final String VOLUME_KEY = "volume";
    private final ContainerProvider containerProvider;

    public SystemModelUserTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public System.User mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(System.User user) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putInt("id", user.getId());
        map.putString("name", user.getName());
        map.putString("address", user.getAddress());
        map.putArray(CONNECTED_AUDIO_TYPES_KEY, WritableArrayUtils.createWritableArray(this.containerProvider, user.getConnectedAudioTypesList()));
        map.putString(PRIMARY_AUDIO_CONNECTION_TYPE_KEY, user.getPrimaryAudioConnectionType().name());
        map.putBoolean("connected", user.getConnected());
        map.putInt("volume", user.getVolume());
        map.putString(FOCUS_KEY, user.getFocus().name());
        map.putBoolean(SELF_KEY, user.getSelf());
        map.putBoolean(AAP_CONNECTED_KEY, user.getAapConnected());
        map.putString(LAST_KNOWN_AAP_CAPABILITY_KEY, user.getLastKnownAapCapability().name());
        return map;
    }
}
