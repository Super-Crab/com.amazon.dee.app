package com.amazon.alexa.dnssd.dependencies;

import com.amazon.alexa.dnssd.DefaultDnssdService;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {DnssdModule.class})
@Singleton
/* loaded from: classes7.dex */
public interface DnssdComponent {
    void inject(DefaultDnssdService defaultDnssdService);
}
