package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import com.amazon.client.metrics.thirdparty.utils.DeviceCategoryUtil;
import java.util.Locale;
/* loaded from: classes11.dex */
public class UserAgentHelper {
    private Context mContext;
    private String mDeviceCategory;
    private DeviceInfoManager mDeviceInfoManager;
    private String mUserAgent;

    public UserAgentHelper(Context context, DeviceInfoManager deviceInfoManager) {
        this.mContext = context;
        this.mDeviceInfoManager = deviceInfoManager;
    }

    private String emptyIfNull(String str) {
        return str == null ? "" : str;
    }

    private String generateUserAgent() {
        if (TextUtils.isEmpty(this.mDeviceCategory)) {
            this.mDeviceCategory = DeviceCategoryUtil.getDeviceCategory(this.mContext);
        }
        return String.format(Locale.US, "AMZN(%s/%s/%s,%s/%s,//,DCM)", emptyIfNull(this.mDeviceCategory), Build.PRODUCT, this.mDeviceInfoManager.getDeviceInfo().getDeviceInfo("deviceType"), "Android", Build.VERSION.RELEASE);
    }

    private boolean hasUserAgent(MetricEntry metricEntry) {
        for (DataPoint dataPoint : metricEntry.getDatapoints()) {
            if (ClickStreamData.USER_AGENT.getName().equals(dataPoint.getName())) {
                return true;
            }
        }
        return false;
    }

    public void addUserAgentIfNotSet(MetricEntry metricEntry) {
        if (!hasUserAgent(metricEntry)) {
            if (TextUtils.isEmpty(this.mUserAgent)) {
                this.mUserAgent = generateUserAgent();
            }
            metricEntry.getDatapoints().add(new DataPoint(ClickStreamData.USER_AGENT.getName(), this.mUserAgent, 1, DataPointType.DV));
        }
    }
}
