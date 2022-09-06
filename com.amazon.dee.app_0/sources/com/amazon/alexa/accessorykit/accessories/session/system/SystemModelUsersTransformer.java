package com.amazon.alexa.accessorykit.accessories.session.system;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
import java.util.List;
/* loaded from: classes6.dex */
public class SystemModelUsersTransformer implements MapModelTransformer<System.Users> {
    private static final String USERS_KEY = "users";
    private final ArrayModelTransformer<List<System.User>> arrayTransformer;
    private final ContainerProvider containerProvider;

    public SystemModelUsersTransformer(ContainerProvider containerProvider, ArrayModelTransformer<List<System.User>> arrayModelTransformer) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(arrayModelTransformer, "arrayTransformer");
        this.containerProvider = containerProvider;
        this.arrayTransformer = arrayModelTransformer;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public System.Users mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(System.Users users) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putArray(USERS_KEY, this.arrayTransformer.transformToArray(users.getUsersList()));
        return map;
    }
}
