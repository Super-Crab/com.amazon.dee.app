package com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo;

import com.amazon.alexa.device.setup.echo.softap.linkcode.LinkCodeAuthorizer;
import com.amazon.alexa.device.setup.echo.softap.locale.LocaleAndEndpointConfigurator;
import com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManager;
/* loaded from: classes6.dex */
public final class EchoSoftAPWorkflowManagerFactory {
    private EchoSoftAPWorkflowManagerFactory() {
    }

    public static EchoSoftAPWorkflowManager getEchoSoftAPWorkflowManager(SoftAPWifiManager softAPWifiManager, LinkCodeAuthorizer linkCodeAuthorizer, LocaleAndEndpointConfigurator localeAndEndpointConfigurator) {
        return new EchoSoftAPWorkflowManager(softAPWifiManager, linkCodeAuthorizer, localeAndEndpointConfigurator);
    }
}
