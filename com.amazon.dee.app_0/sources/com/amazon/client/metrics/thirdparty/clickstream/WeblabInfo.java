package com.amazon.client.metrics.thirdparty.clickstream;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamHelper;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class WeblabInfo implements ClickStreamInfo {
    private static final String WL_HIT_TYPE = "weblab-trigger";
    private static final String WL_PAGE_ASSEMBLY_TYPE = "main";
    private static final String WL_PAGE_TYPE = "Weblab";
    private static final String WL_SUB_PAGE_TYPE = "Trigger";
    private static final char WL_TREATMENT_DIVIDER = '/';
    private final String clientID;
    private final String weblab;

    public WeblabInfo(String str, String str2) {
        verifyString(WL_PAGE_TYPE, str);
        verifyString("Weblab Client ID", str2);
        int indexOf = str.indexOf(47);
        if (indexOf > 0 && indexOf == str.lastIndexOf(47) && indexOf < str.length() - 1) {
            this.weblab = str;
            this.clientID = str2;
            return;
        }
        throw new IllegalArgumentException("Invalid Weblab! Must be of the format WEBLAB_ID/TREATMENT");
    }

    private static void verifyString(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException(String.format("%s cannot be null or empty", str));
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        ArrayList arrayList = new ArrayList(6);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.PAGE_ASSEMBLY_TYPE.getName(), "main");
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.PAGE_TYPE.getName(), WL_PAGE_TYPE);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.SUB_PAGE_TYPE.getName(), WL_SUB_PAGE_TYPE);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.HIT_TYPE.getName(), WL_HIT_TYPE);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.WEBLAB_CLIENT_ID.getName(), this.clientID);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.WEBLAB.getName(), this.weblab);
        return arrayList;
    }
}
