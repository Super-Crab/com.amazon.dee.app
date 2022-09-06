package com.amazon.client.metrics.common.clickstream;

import android.util.Log;
import com.amazon.client.metrics.common.clickstream.internal.IECommerceInfo;
import com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.internal.IUsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidECommerceInfo;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidEventBasedUsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidUsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidWeblabInfo;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSECommerceInfo;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSEventBasedUsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSUsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSWeblabInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes11.dex */
public class ClickStreamDataConverter {
    private static final String CLICKSTREAM_INGO_SIMPLE_CLASS_NAME = "ClickStreamInfo";
    private static final String ECOMMERCE_INFO_SIMPLE_CLASS_NAME = "ECommerceInfo";
    private static final String EVENT_BASED_USAGE_SIMPLE_CLASS_NAME = "EventBasedUsageInfo";
    private static final String IMPRESSION_TRACKING_DATA_SIMPLE_CLASS_NAME = "ImpressionTrackingData";
    private static final String USAGE_INGO_SIMPLE_CLASS_NAME = "UsageInfo";
    private static final String WEBLAB_INGO_SIMPLE_CLASS_NAME = "WeblabInfo";

    public static Collection<ClickStreamInfo> convertClickStreamInfoCollection_fromFirstPartyToCommon(Collection<com.amazon.client.metrics.clickstream.ClickStreamInfo> collection) {
        if (collection == null) {
            Log.i("convertClickStreamInfo", "Returning null for null FirstParty ClickStreamInfo Collection input");
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (com.amazon.client.metrics.clickstream.ClickStreamInfo clickStreamInfo : collection) {
            ClickStreamInfo convertClickStreamInfo_fromFirstPartyToCommon = convertClickStreamInfo_fromFirstPartyToCommon(clickStreamInfo);
            if (convertClickStreamInfo_fromFirstPartyToCommon != null) {
                arrayList.add(convertClickStreamInfo_fromFirstPartyToCommon);
            }
        }
        return arrayList;
    }

    public static Collection<ClickStreamInfo> convertClickStreamInfoCollection_fromThirdPartyToCommon(Collection<com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo> collection) {
        if (collection == null) {
            Log.i("convertClickStreamInfo", "Returning null for null ThirdParty ClickStreamInfo Collection input");
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo clickStreamInfo : collection) {
            ClickStreamInfo convertClickStreamInfo_fromThirdPartyToCommon = convertClickStreamInfo_fromThirdPartyToCommon(clickStreamInfo);
            if (convertClickStreamInfo_fromThirdPartyToCommon != null) {
                arrayList.add(convertClickStreamInfo_fromThirdPartyToCommon);
            }
        }
        return arrayList;
    }

    public static com.amazon.client.metrics.clickstream.ClickStreamInfo convertClickStreamInfo_fromCommonToFirstParty(ClickStreamInfo clickStreamInfo) {
        if (clickStreamInfo == null) {
            Log.i("convertClickStreamInfo", "Returning null for null Common ClickStreamInfo input");
            return null;
        } else if (clickStreamInfo instanceof ECommerceInfo) {
            return convertECommerceInfo_fromCommonToFirstParty((ECommerceInfo) clickStreamInfo);
        } else {
            if (clickStreamInfo instanceof EventBasedUsageInfo) {
                return convertEventBasedUsageInfo_fromCommonToFirstParty((EventBasedUsageInfo) clickStreamInfo);
            }
            if (clickStreamInfo instanceof UsageInfo) {
                return convertUsageInfo_fromCommonToFirstParty((UsageInfo) clickStreamInfo);
            }
            if (clickStreamInfo instanceof WeblabInfo) {
                return convertWeblabInfo_fromCommonToFirstParty((WeblabInfo) clickStreamInfo);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Returning null for input that can not be converted to FirstParty ClickStreamInfo: ");
            outline107.append(clickStreamInfo.getClass().getSimpleName());
            Log.i("convertClickStreamInfo", outline107.toString());
            return null;
        }
    }

    public static com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo convertClickStreamInfo_fromCommonToThirdParty(ClickStreamInfo clickStreamInfo) {
        if (clickStreamInfo == null) {
            Log.i("convertClickStreamInfo", "Returning null for null Common ClickStreamInfo input");
            return null;
        } else if (clickStreamInfo instanceof ECommerceInfo) {
            return convertECommerceInfo_fromCommonToThirdParty((ECommerceInfo) clickStreamInfo);
        } else {
            if (clickStreamInfo instanceof EventBasedUsageInfo) {
                return convertEventBasedUsageInfo_fromCommonToThirdParty((EventBasedUsageInfo) clickStreamInfo);
            }
            if (clickStreamInfo instanceof ImpressionTrackingData) {
                return convertImpressionTrackingData_fromCommonToThirdParty((ImpressionTrackingData) clickStreamInfo);
            }
            if (clickStreamInfo instanceof UsageInfo) {
                return convertUsageInfo_fromCommonToThirdParty((UsageInfo) clickStreamInfo);
            }
            if (clickStreamInfo instanceof WeblabInfo) {
                return convertWeblabInfo_fromCommonToThirdParty((WeblabInfo) clickStreamInfo);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Returning null for input that can not be converted to ThirdParty ClickStreamInfo: ");
            outline107.append(clickStreamInfo.getClass().getSimpleName());
            Log.i("convertClickStreamInfo", outline107.toString());
            return null;
        }
    }

    public static ClickStreamInfo convertClickStreamInfo_fromFirstPartyToCommon(com.amazon.client.metrics.clickstream.ClickStreamInfo clickStreamInfo) {
        if (clickStreamInfo == null) {
            Log.i("convertClickStreamInfo", "Returning null for null FirstParty ClickStreamInfo input");
            return null;
        } else if (clickStreamInfo instanceof com.amazon.client.metrics.clickstream.ECommerceInfo) {
            return convertECommerceInfo_fromFirstPartyToCommon((com.amazon.client.metrics.clickstream.ECommerceInfo) clickStreamInfo);
        } else {
            if (clickStreamInfo instanceof com.amazon.client.metrics.clickstream.EventBasedUsageInfo) {
                return convertEventBasedUsageInfo_fromFirstPartyToCommon((com.amazon.client.metrics.clickstream.EventBasedUsageInfo) clickStreamInfo);
            }
            if (clickStreamInfo instanceof com.amazon.client.metrics.clickstream.UsageInfo) {
                return convertUsageInfo_fromFirstPartyToCommon((com.amazon.client.metrics.clickstream.UsageInfo) clickStreamInfo);
            }
            if (clickStreamInfo instanceof com.amazon.client.metrics.clickstream.WeblabInfo) {
                return convertWeblabInfo_fromFirstPartyToCommon((com.amazon.client.metrics.clickstream.WeblabInfo) clickStreamInfo);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Returning null for input that can not be converted to Common ClickStreamInfo: ");
            outline107.append(clickStreamInfo.getClass().getSimpleName());
            Log.i("convertClickStreamInfo", outline107.toString());
            return null;
        }
    }

    public static ClickStreamInfo convertClickStreamInfo_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo clickStreamInfo) {
        if (clickStreamInfo == null) {
            Log.i("convertClickStreamInfo", "Returning null for null ThirdParty ClickStreamInfo input");
            return null;
        } else if (clickStreamInfo instanceof com.amazon.client.metrics.thirdparty.clickstream.ECommerceInfo) {
            return convertECommerceInfo_fromThirdPartyToCommon((com.amazon.client.metrics.thirdparty.clickstream.ECommerceInfo) clickStreamInfo);
        } else {
            if (clickStreamInfo instanceof com.amazon.client.metrics.thirdparty.clickstream.EventBasedUsageInfo) {
                return convertEventBasedUsageInfo_fromThirdPartyToCommon((com.amazon.client.metrics.thirdparty.clickstream.EventBasedUsageInfo) clickStreamInfo);
            }
            if (clickStreamInfo instanceof com.amazon.client.metrics.thirdparty.clickstream.ImpressionTrackingData) {
                return convertImpressionTrackingData_fromThirdPartyToCommon((com.amazon.client.metrics.thirdparty.clickstream.ImpressionTrackingData) clickStreamInfo);
            }
            if (clickStreamInfo instanceof com.amazon.client.metrics.thirdparty.clickstream.UsageInfo) {
                return convertUsageInfo_fromThirdPartyToCommon((com.amazon.client.metrics.thirdparty.clickstream.UsageInfo) clickStreamInfo);
            }
            if (clickStreamInfo instanceof com.amazon.client.metrics.thirdparty.clickstream.WeblabInfo) {
                return convertWeblabInfo_fromThirdPartyToCommon((com.amazon.client.metrics.thirdparty.clickstream.WeblabInfo) clickStreamInfo);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Returning null for input that can not be converted to Common ClickStreamInfo: ");
            outline107.append(clickStreamInfo.getClass().getSimpleName());
            Log.i("convertClickStreamInfo", outline107.toString());
            return null;
        }
    }

    public static com.amazon.client.metrics.clickstream.ECommerceInfo convertECommerceInfo_fromCommonToFirstParty(ECommerceInfo eCommerceInfo) {
        if (eCommerceInfo == null) {
            Log.i("convertECommerceInfo", "Returning null for null Common ECommerceInfo input");
            return null;
        }
        IECommerceInfo delegateECommerceInfo = eCommerceInfo.getDelegateECommerceInfo();
        if (delegateECommerceInfo instanceof FireOSECommerceInfo) {
            return ((FireOSECommerceInfo) delegateECommerceInfo).getDelegateFirstPartyECommerceInfo();
        }
        Log.i("convertECommerceInfo", "Returning null for ECommerceInfo that does not delegate tasks to FirstParty ECommerceInfo");
        return null;
    }

    public static com.amazon.client.metrics.thirdparty.clickstream.ECommerceInfo convertECommerceInfo_fromCommonToThirdParty(ECommerceInfo eCommerceInfo) {
        if (eCommerceInfo == null) {
            Log.i("convertECommerceInfo", "Returning null for null Common ECommerceInfo input");
            return null;
        }
        IECommerceInfo delegateECommerceInfo = eCommerceInfo.getDelegateECommerceInfo();
        if (delegateECommerceInfo instanceof AndroidECommerceInfo) {
            return ((AndroidECommerceInfo) delegateECommerceInfo).getDelegateThirdPartyECommerceInfo();
        }
        Log.i("convertECommerceInfo", "Returning null for ECommerceInfo that does not delegate tasks to ThirdParty ECommerceInfo");
        return null;
    }

    public static ECommerceInfo convertECommerceInfo_fromFirstPartyToCommon(com.amazon.client.metrics.clickstream.ECommerceInfo eCommerceInfo) {
        if (eCommerceInfo == null) {
            Log.i("convertECommerceInfo", "Returning null for null FirstParty ECommerceInfo input");
            return null;
        }
        return new ECommerceInfo(new FireOSECommerceInfo(eCommerceInfo));
    }

    public static ECommerceInfo convertECommerceInfo_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.clickstream.ECommerceInfo eCommerceInfo) {
        if (eCommerceInfo == null) {
            Log.i("convertECommerceInfo", "Returning null for null ThirdParty ECommerceInfo input");
            return null;
        }
        return new ECommerceInfo(new AndroidECommerceInfo(eCommerceInfo));
    }

    public static com.amazon.client.metrics.clickstream.EventBasedUsageInfo convertEventBasedUsageInfo_fromCommonToFirstParty(EventBasedUsageInfo eventBasedUsageInfo) {
        if (eventBasedUsageInfo == null) {
            Log.i("convertEventBasedUsage", "Returning null for null Common EventBasedUsageInfo input");
            return null;
        }
        ClickStreamInfo delegateEventBasedUsageClickstreamInfo = eventBasedUsageInfo.getDelegateEventBasedUsageClickstreamInfo();
        if (delegateEventBasedUsageClickstreamInfo instanceof FireOSEventBasedUsageInfo) {
            return ((FireOSEventBasedUsageInfo) delegateEventBasedUsageClickstreamInfo).getDelegateEventBasedUsageInfo();
        }
        Log.i("convertEventBasedUsage", "Returning null for EventBasedUsageInfo that does not delegate tasks to FirstParty EventBasedUsageInfo");
        return null;
    }

    public static com.amazon.client.metrics.thirdparty.clickstream.EventBasedUsageInfo convertEventBasedUsageInfo_fromCommonToThirdParty(EventBasedUsageInfo eventBasedUsageInfo) {
        if (eventBasedUsageInfo == null) {
            Log.i("convertEventBasedUsage", "Returning null for null Common EventBasedUsageInfo input");
            return null;
        }
        ClickStreamInfo delegateEventBasedUsageClickstreamInfo = eventBasedUsageInfo.getDelegateEventBasedUsageClickstreamInfo();
        if (delegateEventBasedUsageClickstreamInfo instanceof AndroidEventBasedUsageInfo) {
            return ((AndroidEventBasedUsageInfo) delegateEventBasedUsageClickstreamInfo).getDelegateEventBasedUsageInfo();
        }
        Log.i("convertEventBasedUsage", "Returning null for EventBasedUsageInfo that does not delegate tasks to ThirdParty EventBasedUsageInfo");
        return null;
    }

    public static EventBasedUsageInfo convertEventBasedUsageInfo_fromFirstPartyToCommon(com.amazon.client.metrics.clickstream.EventBasedUsageInfo eventBasedUsageInfo) {
        if (eventBasedUsageInfo == null) {
            Log.i("convertEventBasedUsage", "Returning null for null FirstParty EventBasedUsageInfo input");
            return null;
        }
        return new EventBasedUsageInfo(new FireOSEventBasedUsageInfo(eventBasedUsageInfo));
    }

    public static EventBasedUsageInfo convertEventBasedUsageInfo_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.clickstream.EventBasedUsageInfo eventBasedUsageInfo) {
        if (eventBasedUsageInfo == null) {
            Log.i("convertEventBasedUsage", "Returning null for null ThirdParty EventBasedUsageInfo input");
            return null;
        }
        return new EventBasedUsageInfo(new AndroidEventBasedUsageInfo(eventBasedUsageInfo));
    }

    public static com.amazon.client.metrics.thirdparty.clickstream.ImpressionTrackingData convertImpressionTrackingData_fromCommonToThirdParty(ImpressionTrackingData impressionTrackingData) {
        if (impressionTrackingData == null) {
            Log.i("convertImpressionTrack", "Returning null for null Common ImpressionTrackingData input");
            return null;
        }
        IImpressionTrackingData delegateImpressionTrackingData = impressionTrackingData.getDelegateImpressionTrackingData();
        if (delegateImpressionTrackingData instanceof AndroidImpressionTrackingData) {
            return ((AndroidImpressionTrackingData) delegateImpressionTrackingData).getDelegateImpressionTrackingData();
        }
        Log.i("convertImpressionTrack", "Returning null for ImpressionTrackingData that does not delegate task to ThirdParty ImpressionTrackingData");
        return null;
    }

    public static ImpressionTrackingData convertImpressionTrackingData_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.clickstream.ImpressionTrackingData impressionTrackingData) {
        if (impressionTrackingData == null) {
            Log.i("convertImpressionTrack", "Returning null for null ThirdParty ImpressionTrackingData input");
            return null;
        }
        return new ImpressionTrackingData(new AndroidImpressionTrackingData(impressionTrackingData));
    }

    public static com.amazon.client.metrics.clickstream.UsageInfo convertUsageInfo_fromCommonToFirstParty(UsageInfo usageInfo) {
        if (usageInfo == null) {
            Log.i("convertUsageInfo", "Returning null for null Common UsageInfo input");
            return null;
        }
        IUsageInfo delegateUsageInfo = usageInfo.getDelegateUsageInfo();
        if (delegateUsageInfo instanceof FireOSUsageInfo) {
            return ((FireOSUsageInfo) delegateUsageInfo).getDelegateUsageInfo();
        }
        Log.i("convertUsageInfo", "Returning null for UsageInfo that does not delegate tasks to FirstParty UsageInfo");
        return null;
    }

    public static com.amazon.client.metrics.thirdparty.clickstream.UsageInfo convertUsageInfo_fromCommonToThirdParty(UsageInfo usageInfo) {
        if (usageInfo == null) {
            Log.i("convertUsageInfo", "Returning null for null Common UsageInfo input");
            return null;
        }
        IUsageInfo delegateUsageInfo = usageInfo.getDelegateUsageInfo();
        if (delegateUsageInfo instanceof AndroidUsageInfo) {
            return ((AndroidUsageInfo) delegateUsageInfo).getDelegateUsageInfo();
        }
        Log.i("convertUsageInfo", "Returning null for UsageInfo that does not delegate tasks to ThirdParty UsageInfo");
        return null;
    }

    public static UsageInfo convertUsageInfo_fromFirstPartyToCommon(com.amazon.client.metrics.clickstream.UsageInfo usageInfo) {
        if (usageInfo == null) {
            Log.i("convertUsageInfo", "Returning null for null FirstParty UsageInfo input");
            return null;
        }
        return new UsageInfo(new FireOSUsageInfo(usageInfo));
    }

    public static UsageInfo convertUsageInfo_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.clickstream.UsageInfo usageInfo) {
        if (usageInfo == null) {
            Log.i("convertUsageInfo", "Returning null for null ThirdParty UsageInfo input");
            return null;
        }
        return new UsageInfo(new AndroidUsageInfo(usageInfo));
    }

    public static com.amazon.client.metrics.clickstream.WeblabInfo convertWeblabInfo_fromCommonToFirstParty(WeblabInfo weblabInfo) {
        if (weblabInfo == null) {
            Log.i("convertWeblabInfo", "Returning null for null Common WeblabInfo input");
            return null;
        }
        ClickStreamInfo delegateClickstreamWeblabInfo = weblabInfo.getDelegateClickstreamWeblabInfo();
        if (delegateClickstreamWeblabInfo instanceof FireOSWeblabInfo) {
            return ((FireOSWeblabInfo) delegateClickstreamWeblabInfo).getDelegateWeblabInfo();
        }
        Log.i("convertWeblabInfo", "Returning null for WeblabInfo that does not delegate tasks to FirstParty WeblabInfo");
        return null;
    }

    public static com.amazon.client.metrics.thirdparty.clickstream.WeblabInfo convertWeblabInfo_fromCommonToThirdParty(WeblabInfo weblabInfo) {
        if (weblabInfo == null) {
            Log.i("convertWeblabInfo", "Returning null for null Common WeblabInfo input");
            return null;
        }
        ClickStreamInfo delegateClickstreamWeblabInfo = weblabInfo.getDelegateClickstreamWeblabInfo();
        if (delegateClickstreamWeblabInfo instanceof AndroidWeblabInfo) {
            return ((AndroidWeblabInfo) delegateClickstreamWeblabInfo).getDelegateWeblabInfo();
        }
        Log.i("convertWeblabInfo", "Returning null for WeblabInfo that does not delegate tasks to ThirdParty WeblabInfo");
        return null;
    }

    public static WeblabInfo convertWeblabInfo_fromFirstPartyToCommon(com.amazon.client.metrics.clickstream.WeblabInfo weblabInfo) {
        if (weblabInfo == null) {
            Log.i("convertWeblabInfo", "Returning null for null FirstParty WeblabInfo input");
            return null;
        }
        return new WeblabInfo(new FireOSWeblabInfo(weblabInfo));
    }

    public static WeblabInfo convertWeblabInfo_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.clickstream.WeblabInfo weblabInfo) {
        if (weblabInfo == null) {
            Log.i("convertWeblabInfo", "Returning null for null ThirdParty WeblabInfo input");
            return null;
        }
        return new WeblabInfo(new AndroidWeblabInfo(weblabInfo));
    }

    public static Class<? extends com.amazon.client.metrics.clickstream.ClickStreamInfo> convertClickStreamInfo_fromCommonToFirstParty(Class<? extends ClickStreamInfo> cls) {
        if (cls == null) {
            Log.i("convertClickStreamInfo", "Returning null for null Common ClickStreamInfo class input");
            return null;
        }
        String simpleName = cls.getSimpleName();
        char c = 65535;
        switch (simpleName.hashCode()) {
            case -1517175345:
                if (simpleName.equals(USAGE_INGO_SIMPLE_CLASS_NAME)) {
                    c = 2;
                    break;
                }
                break;
            case 110852054:
                if (simpleName.equals(CLICKSTREAM_INGO_SIMPLE_CLASS_NAME)) {
                    c = 4;
                    break;
                }
                break;
            case 962589454:
                if (simpleName.equals(ECOMMERCE_INFO_SIMPLE_CLASS_NAME)) {
                    c = 0;
                    break;
                }
                break;
            case 1146574375:
                if (simpleName.equals(WEBLAB_INGO_SIMPLE_CLASS_NAME)) {
                    c = 3;
                    break;
                }
                break;
            case 1634063510:
                if (simpleName.equals(EVENT_BASED_USAGE_SIMPLE_CLASS_NAME)) {
                    c = 1;
                    break;
                }
                break;
        }
        if (c == 0) {
            return com.amazon.client.metrics.clickstream.ECommerceInfo.class;
        }
        if (c == 1) {
            return com.amazon.client.metrics.clickstream.EventBasedUsageInfo.class;
        }
        if (c == 2) {
            return com.amazon.client.metrics.clickstream.UsageInfo.class;
        }
        if (c == 3) {
            return com.amazon.client.metrics.clickstream.WeblabInfo.class;
        }
        if (c == 4) {
            return com.amazon.client.metrics.clickstream.ClickStreamInfo.class;
        }
        return null;
    }

    public static Class<? extends com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo> convertClickStreamInfo_fromCommonToThirdParty(Class<? extends ClickStreamInfo> cls) {
        if (cls == null) {
            Log.i("convertClickStreamInfo", "Returning null for null Common ClickStreamInfo class input");
            return null;
        }
        String simpleName = cls.getSimpleName();
        char c = 65535;
        switch (simpleName.hashCode()) {
            case -1539105846:
                if (simpleName.equals(IMPRESSION_TRACKING_DATA_SIMPLE_CLASS_NAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1517175345:
                if (simpleName.equals(USAGE_INGO_SIMPLE_CLASS_NAME)) {
                    c = 3;
                    break;
                }
                break;
            case 110852054:
                if (simpleName.equals(CLICKSTREAM_INGO_SIMPLE_CLASS_NAME)) {
                    c = 5;
                    break;
                }
                break;
            case 962589454:
                if (simpleName.equals(ECOMMERCE_INFO_SIMPLE_CLASS_NAME)) {
                    c = 0;
                    break;
                }
                break;
            case 1146574375:
                if (simpleName.equals(WEBLAB_INGO_SIMPLE_CLASS_NAME)) {
                    c = 4;
                    break;
                }
                break;
            case 1634063510:
                if (simpleName.equals(EVENT_BASED_USAGE_SIMPLE_CLASS_NAME)) {
                    c = 1;
                    break;
                }
                break;
        }
        if (c == 0) {
            return com.amazon.client.metrics.thirdparty.clickstream.ECommerceInfo.class;
        }
        if (c == 1) {
            return com.amazon.client.metrics.thirdparty.clickstream.EventBasedUsageInfo.class;
        }
        if (c == 2) {
            return com.amazon.client.metrics.thirdparty.clickstream.ImpressionTrackingData.class;
        }
        if (c == 3) {
            return com.amazon.client.metrics.thirdparty.clickstream.UsageInfo.class;
        }
        if (c == 4) {
            return com.amazon.client.metrics.thirdparty.clickstream.WeblabInfo.class;
        }
        if (c == 5) {
            return com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo.class;
        }
        return null;
    }
}
