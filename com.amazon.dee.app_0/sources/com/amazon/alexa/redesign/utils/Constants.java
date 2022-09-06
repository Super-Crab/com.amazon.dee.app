package com.amazon.alexa.redesign.utils;

import com.amazon.alexa.redesign.entity.templates.CarouselChipTemplateModel;
import com.amazon.alexa.redesign.entity.templates.CarouselGridTemplateModel;
import com.amazon.alexa.redesign.entity.templates.HeroCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.ListTemplateModel;
import com.amazon.alexa.redesign.entity.templates.MiniCardTemplateModel;
import com.amazon.alexa.redesign.entity.templates.SkillsSingleTemplateModel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public final class Constants {
    public static final String APP_ONLY_STATUS_NAMESPACE = "AppOnlyStatus";
    public static final String DIFF_UTIL_TAG = "DiffUtilCallback";
    public static final String HOME_CHANNEL_NATIVE = "HomeChannelNative: ";
    public static final String HOME_FEED_API = "/api/content";
    public static final String IMAGE_TYPE_ICON = "Icon";
    public static final String IS_APP_ONLY = "isAppOnly";
    public static final String IS_APP_ONLY_RESPONSE_FIELD = "appOnly";
    public static final String KEY_CLICK_TO_DISMISS_CARDS = "clickToDismissCardId";
    public static final String KEY_DISMISS_CARDS = "dismissCardId";
    public static final String LATENCY_IMPROVEMENTS_WEBLAB = "CH_HOME_PAGE_LOAD_OPTIMIZATION_ANDROID";
    public static final String METADATA = "metadata";
    public static final long MIN_DURATION_FOR_VIEW = TimeUnit.SECONDS.toMillis(2);
    public static final double MIN_PERC_MINI_TEMPLATE = 1.0d;
    public static final double MIN_PERC_NON_MINI = 0.5d;
    public static final String POLLING_WEBLAB = "CH_HOME_CONTENT_REFRESH_ANDROID";
    public static final String REFERER_HEADER = "jasperhomeheader";
    public static final String REFERER_HEADER_TWA = "homeheader";
    public static final String REFERER_VOX_CARD = "jasperhomevoxcard";
    public static final String REFERER_VOX_CARD_TWA_KEYBOARD = "homekeyboard";
    public static final String REQUEST_METHOD_DELETE = "DELETE";
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    public static final String SKELETON = "skeleton";
    public static final Map<String, Integer> SLOT_COUNTS;
    public static final int TWA_MAX_REQUEST_COUNT = 10;
    public static final String UNIVERSAL_DEVICE_PICKER_ROUTE = "universal-device-picker";
    public static final String VIEW_CONTROLLER_TAG = "HomeRedesignViewCtrlr";
    public static final String VIEW_UPDATE_TYPE_CACHE = "viewUpdateTypeCache";
    public static final String VIEW_UPDATE_TYPE_ERROR_PAGE_RETRY = "viewUpdateTypeErrorPageRetry";
    public static final String VIEW_UPDATE_TYPE_EVENT_BUS = "viewUpdateTypeEventBus";
    public static final String VIEW_UPDATE_TYPE_PULLREFRESH = "viewUpdateTypePullRefresh";
    public static final String VIEW_UPDATE_TYPE_REFRESH_BUTTON = "viewUpdateTypeRefreshButton";
    public static final String VIEW_UPDATE_TYPE_SERVER = "viewUpdateTypeServer";
    public static final String VIEW_UPDATE_TYPE_SERVER_NO_CACHE = "viewUpdateTypeServerNoCache";
    public static final String VIEW_UPDATE_TYPE_SKELETON = "viewUpdateTypeSkeleton";
    public static final double VOX_CARD_FADE_OUT_PERC = 0.71d;
    public static final double VOX_CARD_FADE_OUT_PERC_FOS = 0.6666d;
    public static final double VOX_OFFLINE_ALPHA = 0.3d;
    public static final double VOX_ONLINE_ALPHA = 1.0d;

    /* loaded from: classes10.dex */
    public static class AutomationConstants {
        public static boolean isQABuild = false;
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(MiniCardTemplateModel.MINI_CARD_TEMPLATE_TYPE, 2);
        hashMap.put(HeroCardTemplateModel.HERO_CARD_TEMPLATE_TYPE, 3);
        hashMap.put(ListTemplateModel.LIST_CARD_TEMPLATE_TYPE, 5);
        hashMap.put(SkillsSingleTemplateModel.SKILLS_SINGLE_TEMPLATE_TYPE, 5);
        hashMap.put(CarouselChipTemplateModel.CAROUSEL_CHIP_TEMPLATE_TYPE, 2);
        hashMap.put(CarouselGridTemplateModel.CAROUSEL_GRID_TEMPLATE_TYPE, 2);
        SLOT_COUNTS = Collections.unmodifiableMap(hashMap);
    }

    private Constants() {
    }
}
