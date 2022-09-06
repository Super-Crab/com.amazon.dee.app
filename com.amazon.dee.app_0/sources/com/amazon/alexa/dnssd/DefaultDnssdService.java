package com.amazon.alexa.dnssd;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.dnssd.dependencies.DaggerDnssdComponent;
import com.amazon.alexa.dnssd.dependencies.DnssdModule;
import com.amazon.alexa.dnssd.subscribers.StartDiscoverySubscriber;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class DefaultDnssdService implements DnssdService {
    private static final String TAG = "DefaultDnssdService";
    @Inject
    EventBus eventBus;
    @Inject
    StartDiscoverySubscriber startDiscoverySubscriber;

    public DefaultDnssdService(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        DaggerDnssdComponent.builder().dnssdModule(new DnssdModule(componentGetter, context)).build().inject(this);
    }

    @Override // com.amazon.alexa.protocols.service.api.InitializableComponent
    public void initializeComponent(ComponentGetter componentGetter, Context context) {
        Log.i(TAG, "Initializing DNS-SD component....");
        this.eventBus.subscribe(this.startDiscoverySubscriber);
        Log.i(TAG, "Initialized DNS-SD component");
    }
}
