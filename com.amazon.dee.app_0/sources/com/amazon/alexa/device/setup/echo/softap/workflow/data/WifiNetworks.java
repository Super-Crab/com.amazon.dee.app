package com.amazon.alexa.device.setup.echo.softap.workflow.data;

import com.amazon.device.setup.thrift.APDetail;
import java.util.List;
/* loaded from: classes6.dex */
public class WifiNetworks {
    public List<APDetail> knownNetworks;
    public List<APDetail> scannedNetworks;

    public WifiNetworks(List<APDetail> list, List<APDetail> list2) {
        this.scannedNetworks = list;
        this.knownNetworks = list2;
    }
}
