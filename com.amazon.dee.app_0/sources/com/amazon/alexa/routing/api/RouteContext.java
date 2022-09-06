package com.amazon.alexa.routing.api;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.routing.api.Route;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes10.dex */
public final class RouteContext implements Parcelable {
    private int contentMode;
    private boolean fromMainMenu;
    private final Bundle parameters;
    private final String requestId;
    private final Route route;
    private final HashMap<String, Object> state;
    private static final String TAG = RouteContext.class.getSimpleName();
    public static final Parcelable.Creator<RouteContext> CREATOR = new Parcelable.Creator<RouteContext>() { // from class: com.amazon.alexa.routing.api.RouteContext.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RouteContext mo2393createFromParcel(Parcel parcel) {
            return new RouteContext(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RouteContext[] mo2394newArray(int i) {
            return new RouteContext[i];
        }
    };

    public RouteContext(@NonNull Route route, @NonNull Bundle bundle, @NonNull String str) {
        this.state = new HashMap<>();
        this.fromMainMenu = false;
        this.contentMode = 1;
        this.route = route;
        this.parameters = bundle;
        boolean z = !TextUtils.isEmpty(str);
        this.requestId = TextUtils.isEmpty(str) ? UUID.randomUUID().toString() : str;
        this.contentMode = route.getContentMode();
    }

    public static Bundle mergeParameters(RouteContext routeContext, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putAll(routeContext.parameters);
        if (bundle == null) {
            return bundle2;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                String str2 = (String) obj;
                if (TextUtils.isEmpty(str2)) {
                    removeParameterIfOptional(routeContext.route.getTemplate(), str, bundle2);
                } else {
                    bundle2.putString(str, str2);
                }
            }
        }
        return bundle2;
    }

    public static boolean removeParameterIfOptional(RouteTemplate routeTemplate, String str, Bundle bundle) {
        if (routeTemplate == null) {
            bundle.remove(str);
            return true;
        } else if (!routeTemplate.isOptional(str)) {
            return false;
        } else {
            bundle.remove(str);
            return true;
        }
    }

    private boolean sameQueryString(RouteContext routeContext) {
        return TextUtils.equals(this.parameters.getString(BilobaRouteUtil.RAW_QUERY_STRING), routeContext.parameters.getString(BilobaRouteUtil.RAW_QUERY_STRING));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return isSameRouteAs(obj) && this.requestId.equals(((RouteContext) obj).requestId);
    }

    @Nullable
    public Object get(@NonNull String str) {
        return this.parameters.get(str);
    }

    public boolean getBoolean(@NonNull String str, boolean z) {
        return this.parameters.getBoolean(str, z);
    }

    @Nullable
    public Bundle getBundle(@NonNull String str) {
        return this.parameters.getBundle(str);
    }

    public int getContentMode() {
        return this.contentMode;
    }

    public int getInt(@NonNull String str, int i) {
        return this.parameters.getInt(str, i);
    }

    @NonNull
    public Collection<String> getNames() {
        return this.parameters.keySet();
    }

    @Nullable
    public <T extends Parcelable> T getParcelable(@NonNull String str) {
        return (T) this.parameters.getParcelable(str);
    }

    @NonNull
    public String getRequestId() {
        return this.requestId;
    }

    @NonNull
    public Route getRoute() {
        return this.route;
    }

    @NonNull
    public HashMap getState() {
        return this.state;
    }

    @Nullable
    public String getString(@NonNull String str, @Nullable String str2) {
        return this.parameters.getString(str, str2);
    }

    @NonNull
    public Route.Theme getTheme() {
        return this.route.getTheme();
    }

    public int hashCode() {
        return this.requestId.hashCode() + this.parameters.hashCode() + (this.route.hashCode() * 31);
    }

    public boolean isEmpty() {
        return this.parameters.isEmpty();
    }

    public boolean isFromMainMenu() {
        return this.fromMainMenu;
    }

    public boolean isSameRouteAs(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RouteContext.class != obj.getClass()) {
            return false;
        }
        RouteContext routeContext = (RouteContext) obj;
        if (!this.route.equals(routeContext.route)) {
            return false;
        }
        return BundleUtils.equals(this.parameters, routeContext.parameters) && sameQueryString(routeContext);
    }

    public void setContentMode(int i) {
        this.contentMode = i;
    }

    public void setFromMainMenu(boolean z) {
        this.fromMainMenu = z;
    }

    public void setStatePair(@NonNull String str, @Nullable Object obj) {
        if (obj != null) {
            this.state.put(str, obj);
        } else {
            this.state.remove(str);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RouteContext{route=");
        outline107.append(this.route);
        outline107.append(", parameters=");
        outline107.append(this.parameters);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @NonNull
    public String toUri() {
        RouteTemplate template = this.route.getTemplate();
        if (template == null) {
            return this.route.getName();
        }
        return template.toString(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.route, i);
        parcel.writeInt(this.contentMode);
        parcel.writeBundle(this.parameters);
        parcel.writeString(this.requestId);
    }

    public int getInt(@NonNull String str) {
        return this.parameters.getInt(str);
    }

    @Nullable
    public String getString(@NonNull String str) {
        return this.parameters.getString(str);
    }

    public RouteContext(@NonNull Route route, @NonNull Bundle bundle) {
        this(route, bundle, UUID.randomUUID().toString());
    }

    public RouteContext(@NonNull Route route) {
        this(route, new Bundle());
    }

    public RouteContext(@NonNull RouteContext routeContext, @NonNull Bundle bundle) {
        this.state = new HashMap<>();
        this.fromMainMenu = false;
        this.contentMode = 1;
        this.route = routeContext.getRoute();
        this.parameters = mergeParameters(routeContext, bundle);
        this.requestId = UUID.randomUUID().toString();
        this.contentMode = routeContext.getContentMode();
        if (!routeContext.getState().isEmpty()) {
            this.state.putAll(routeContext.getState());
        }
    }

    public RouteContext(Parcel parcel) {
        this.state = new HashMap<>();
        this.fromMainMenu = false;
        this.contentMode = 1;
        this.route = (Route) parcel.readParcelable(Route.class.getClassLoader());
        this.contentMode = parcel.readInt();
        this.parameters = parcel.readBundle(RouteContext.class.getClassLoader());
        this.requestId = parcel.readString();
    }
}
