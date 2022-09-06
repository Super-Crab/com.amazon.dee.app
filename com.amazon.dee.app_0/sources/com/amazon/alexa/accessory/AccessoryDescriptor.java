package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.transport.TransportDispatcher;
/* loaded from: classes.dex */
public interface AccessoryDescriptor {
    void add(AccessoryStream accessoryStream);

    TransportDispatcher getAuthenticationAgnosticDispatcher();

    TransportDispatcher getAuthenticationAwareDispatcher();

    void remove(AccessoryStream accessoryStream);
}
