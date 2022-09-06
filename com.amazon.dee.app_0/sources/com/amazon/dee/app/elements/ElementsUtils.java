package com.amazon.dee.app.elements;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.dee.app.ui.util.UiUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public final class ElementsUtils {
    public static final String APP_BUNDLE = "APP_BUNDLE";
    private static final String LOG_TAG = "com.amazon.dee.app.elements.ElementsUtils";
    public static final String ROOT_APP_NAME = "ElementsRootComponent";
    public static final String ROUTE_CONTEXT = "ROUTE_CONTEXT";
    public static final String V2_PREFIX = "v2/";

    /* renamed from: com.amazon.dee.app.elements.ElementsUtils$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    protected ElementsUtils() {
    }

    public static Bundle convertHashMapToBundle(@NonNull HashMap<String, Object> hashMap) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                bundle.putCharSequence(entry.getKey(), (String) value);
            } else if (value instanceof Double) {
                bundle.putDouble(entry.getKey(), ((Double) value).doubleValue());
            } else if (value instanceof Integer) {
                bundle.putInt(entry.getKey(), ((Integer) value).intValue());
            } else if (value instanceof Boolean) {
                bundle.putBoolean(entry.getKey(), ((Boolean) value).booleanValue());
            } else if (value instanceof HashMap) {
                bundle.putBundle(entry.getKey(), convertHashMapToBundle((HashMap) value));
            } else if (value != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid value type associated with ");
                outline107.append(entry.getKey());
                throw new IllegalArgumentException(outline107.toString());
            }
        }
        return bundle;
    }

    public static List<Object> deepReadableArrayToList(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            int ordinal = readableArray.getType(i).ordinal();
            if (ordinal == 0) {
                arrayList.add(i, null);
            } else if (ordinal == 1) {
                arrayList.add(i, Boolean.valueOf(readableArray.getBoolean(i)));
            } else if (ordinal == 2) {
                arrayList.add(i, Double.valueOf(readableArray.getDouble(i)));
            } else if (ordinal == 3) {
                arrayList.add(i, readableArray.getString(i));
            } else if (ordinal == 4) {
                arrayList.add(i, deepReadableMapToMap(readableArray.mo6944getMap(i)));
            } else if (ordinal == 5) {
                arrayList.add(i, deepReadableArrayToList(readableArray.mo6943getArray(i)));
            }
        }
        return arrayList;
    }

    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public static Map<String, Object> deepReadableMapToMap(ReadableMap readableMap) {
        HashMap hashMap = new HashMap();
        if (readableMap == null) {
            return hashMap;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            int ordinal = readableMap.getType(nextKey).ordinal();
            if (ordinal == 0) {
                hashMap.put(nextKey, null);
            } else if (ordinal == 1) {
                hashMap.put(nextKey, Boolean.valueOf(readableMap.getBoolean(nextKey)));
            } else if (ordinal == 2) {
                hashMap.put(nextKey, Double.valueOf(readableMap.getDouble(nextKey)));
            } else if (ordinal == 3) {
                hashMap.put(nextKey, readableMap.getString(nextKey));
            } else if (ordinal == 4) {
                hashMap.put(nextKey, deepReadableMapToMap(readableMap.mo6945getMap(nextKey)));
            } else if (ordinal == 5) {
                hashMap.put(nextKey, deepReadableArrayToList(readableMap.getArray(nextKey)));
            }
        }
        return hashMap;
    }

    public static Route.Theme getJasperTheme(boolean z, boolean z2) {
        return z2 ? z ? Route.Theme.JASPER_LIGHT_FONT_V2 : Route.Theme.JASPER_DARK_FONT_V2 : z ? Route.Theme.JASPER_LIGHT : Route.Theme.JASPER_DARK;
    }

    @Nullable
    public static Route.Theme getLoggedOnUsersEffectiveTheme(Context context, @Nullable UserIdentity userIdentity, @NonNull Route route) {
        Route.Theme theme = route.getTheme();
        if (theme != Route.Theme.DEFAULT) {
            return theme;
        }
        if (context != null) {
            return getJasperTheme(UiUtils.isLightMode(context), useJasperThemeWithFontV2(context));
        }
        return useChannelsTheme(userIdentity) ? Route.Theme.CHANNELS : Route.Theme.DARK;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean getOrDefault(Map map, String str, boolean z) {
        if (!map.containsKey(str)) {
            return z;
        }
        Object obj = map.get(str);
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
    }

    public static ReactFeature getReactFeatureFromRouteContext(@NonNull RouteContext routeContext) {
        Preconditions.checkNotNull(routeContext);
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        String uri = routeContext.toUri();
        if (TextUtils.isEmpty(uri)) {
            Log.e(LOG_TAG, "Uri was empty!");
        }
        if (!uri.startsWith(V2_PREFIX)) {
            uri = GeneratedOutlineSupport1.outline72(V2_PREFIX, uri);
        }
        bundle2.putString("uri", uri);
        bundle2.putBundle(ElementsRouteKeys.ARGS, routeContext.getBundle(ElementsRouteKeys.ARGS));
        bundle.putBundle("route", bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putParcelable(ROUTE_CONTEXT, routeContext);
        bundle3.putBundle(APP_BUNDLE, bundle);
        String requestId = routeContext.getRequestId();
        bundle.putString("requestId", requestId);
        bundle.putString(ElementsRouteKeys.THEME, routeContext.getTheme().name().toLowerCase());
        bundle.putBundle(ElementsRouteKeys.STATE, convertHashMapToBundle(routeContext.getState()));
        return new ReactFeatureBuilder().withAppName(ROOT_APP_NAME).withInstanceId(requestId).withLaunchOptions(bundle).build();
    }

    public static boolean isElementsRoute(@NonNull Route route) {
        return 1 == route.getAdapterId();
    }

    private static boolean useChannelsTheme(@Nullable UserIdentity userIdentity) {
        return userIdentity != null && userIdentity.hasFeature("ALEXA_MOBILE_APP_GENERIC_FEATURE_3");
    }

    private static boolean useJasperThemeWithFontV2(Context context) {
        return context != null && FontResolver.isLocaleArabic(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getOrDefault(Map map, String str, String str2) {
        if (!map.containsKey(str)) {
            return str2;
        }
        Object obj = map.get(str);
        return obj instanceof String ? (String) obj : str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getOrDefault(Map map, String str, int i) {
        if (!map.containsKey(str)) {
            return i;
        }
        Object obj = map.get(str);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            return Integer.parseInt((String) obj);
        }
        return obj instanceof Double ? ((Double) obj).intValue() : i;
    }
}
