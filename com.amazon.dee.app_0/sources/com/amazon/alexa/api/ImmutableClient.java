package com.amazon.alexa.api;

import androidx.annotation.Nullable;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class ImmutableClient extends ExtendedClient {
    public ImmutableClient(ClientRole clientRole, String str, String str2, AlexaApiVersion alexaApiVersion, @Nullable ArrayList<ExtendedClient> arrayList, @Nullable String str3, @Nullable String str4) {
        super(clientRole, str, str2, alexaApiVersion, arrayList != null ? new ArrayList(arrayList) : new ArrayList(), str3, str4);
    }

    @Override // com.amazon.alexa.api.ExtendedClient
    public void addSubClient(ExtendedClient extendedClient) {
        throw new UnsupportedOperationException("Cannot add a sub client to an immutable client");
    }

    @Override // com.amazon.alexa.api.ExtendedClient
    public void clearSubClients() {
        throw new UnsupportedOperationException("Cannot clear the sub client list on an immutable client");
    }

    @Override // com.amazon.alexa.api.ExtendedClient
    public void removeSubClient(ExtendedClient extendedClient) {
        throw new UnsupportedOperationException("Cannot remove a sub client from an immutable client");
    }

    @Override // com.amazon.alexa.api.ExtendedClient
    public void setActiveSubClient(ExtendedClient extendedClient) {
        throw new UnsupportedOperationException("Cannot set the active sub client on an immutable client");
    }
}
