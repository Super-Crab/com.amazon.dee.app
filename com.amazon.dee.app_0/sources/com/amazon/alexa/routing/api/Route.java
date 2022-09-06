package com.amazon.alexa.routing.api;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public final class Route implements Parcelable {
    private static final int DEFAULT_PRIORITY = 50;
    private final int adapterId;
    private ArrayList<RouteAlias> aliases;
    private int contentMode;
    private Route defaultChild;
    private boolean doNotKeepInBackStack;
    private boolean handlesHeaderTitle;
    @StringRes
    private Integer headerTitleResId;
    private boolean isOverlay;
    private String module;
    private final String name;
    private String parent;
    private int priority;
    private boolean root;
    private boolean singleChildRouteInBackStack;
    private boolean supportsDelegatedProfile;
    private RouteTemplate template;
    private Theme theme;
    private String viewControllerFactoryClassName;
    private static final String TAG = Route.class.getSimpleName();
    public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() { // from class: com.amazon.alexa.routing.api.Route.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Route mo2391createFromParcel(Parcel parcel) {
            return new Route(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Route[] mo2392newArray(int i) {
            return new Route[i];
        }
    };

    /* renamed from: com.amazon.alexa.routing.api.Route$2  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$routing$api$Route$Theme = new int[Theme.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$routing$api$Route$Theme[Theme.DARK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$routing$api$Route$Theme[Theme.LIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$routing$api$Route$Theme[Theme.JASPER_DARK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$routing$api$Route$Theme[Theme.JASPER_LIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$routing$api$Route$Theme[Theme.JASPER_LIGHT_FONT_V2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$routing$api$Route$Theme[Theme.JASPER_DARK_FONT_V2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$routing$api$Route$Theme[Theme.CHANNELS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class Builder {
        Route route;

        public Builder(String str, int i) {
            this.route = new Route(str, i);
        }

        public Builder asRoot() {
            this.route.setRoot(true);
            return this;
        }

        public Route build() {
            return this.route;
        }

        public Builder doNotKeepInBackStack() {
            this.route.setDoNotKeepInBackStack(true);
            return this;
        }

        public Builder isOverlay() {
            this.route.setOverlay(true);
            return this;
        }

        public Builder supportsDelegatedProfile() {
            this.route.setSupportsDelegatedProfile(true);
            return this;
        }

        public Builder withAlias(String str) {
            Route route = this.route;
            route.addAlias(new RouteAlias(route, str));
            return this;
        }

        public Builder withContentMode(int i) {
            this.route.setContentMode(i);
            return this;
        }

        @Deprecated
        public Builder withHandleHeaderTitle() {
            this.route.setHandlesHeaderTitle(true);
            return this;
        }

        public Builder withHeaderTitle(@StringRes Integer num) {
            this.route.setHeaderTitle(num);
            return this;
        }

        public Builder withModule(String str) {
            this.route.module = str;
            return this;
        }

        public Builder withParent(@NonNull String str) {
            this.route.setParent(str);
            return this;
        }

        public Builder withPriority(int i) {
            this.route.setPriority(i);
            return this;
        }

        public Builder withSingleChildRouteInBackStack() {
            this.route.setSingleChildRouteInBackStack(true);
            return this;
        }

        public Builder withTemplate(@NonNull String str) {
            this.route.setTemplate(new RouteTemplate(str));
            return this;
        }

        public Builder withTheme(Theme theme) {
            this.route.setTheme(theme);
            return this;
        }

        public Builder withAlias(String str, int i) {
            Route route = this.route;
            route.addAlias(new RouteAlias(route, str, i));
            return this;
        }

        public Builder(String str, String str2) {
            this.route = new Route(str, str2);
        }

        public Builder withAlias(String str, int i, ParameterMapper parameterMapper) {
            Route route = this.route;
            route.addAlias(new RouteAlias(route, str, i, parameterMapper));
            return this;
        }

        public Builder withAlias(String str, ParameterMapper parameterMapper) {
            Route route = this.route;
            route.addAlias(new RouteAlias(route, str, parameterMapper));
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public enum Theme {
        LIGHT("light"),
        DARK("dark"),
        CHANNELS("channels"),
        JASPER_LIGHT("jasper.light"),
        JASPER_LIGHT_FONT_V2("jasper.light.font.v2"),
        JASPER_DARK("jasper.dark"),
        JASPER_DARK_FONT_V2("jasper.dark.font.v2"),
        DEFAULT("default");
        
        @VisibleForTesting
        static final int BACKGROUND_CHANNELS = -15320748;
        private static final int BACKGROUND_DARK = -16708844;
        private static final int BACKGROUND_LIGHT = -1;
        private String theme;

        Theme(String str) {
            this.theme = str;
        }

        public int getBackgroundColor(Context context) {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return BACKGROUND_DARK;
                }
                ThemeUtil.setTheme(context);
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicBackground);
            }
            return -1;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.theme;
        }
    }

    public Route(String str, int i) {
        this.contentMode = 1;
        this.priority = 50;
        this.module = null;
        this.name = str;
        this.theme = Theme.DEFAULT;
        this.adapterId = i;
        this.aliases = new ArrayList<>();
        this.contentMode = 1;
        this.isOverlay = false;
        this.supportsDelegatedProfile = false;
    }

    public void addAlias(RouteAlias routeAlias) {
        this.aliases.add(routeAlias);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean doNotKeepInBackStack() {
        return this.doNotKeepInBackStack;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Route.class != obj.getClass()) {
            return false;
        }
        Route route = (Route) obj;
        if (this.singleChildRouteInBackStack != route.singleChildRouteInBackStack || this.root != route.root || this.doNotKeepInBackStack != route.doNotKeepInBackStack || !this.name.equals(route.name)) {
            return false;
        }
        String str = this.parent;
        if (str == null ? route.parent != null : !str.equals(route.parent)) {
            return false;
        }
        Route route2 = this.defaultChild;
        if (route2 == null ? route.defaultChild != null : !route2.equals(route.defaultChild)) {
            return false;
        }
        if (this.theme != route.theme || this.adapterId != route.adapterId || this.priority != route.priority || this.module != route.module || this.isOverlay != route.isOverlay) {
            return false;
        }
        RouteTemplate routeTemplate = this.template;
        RouteTemplate routeTemplate2 = route.template;
        return routeTemplate != null ? routeTemplate.equals(routeTemplate2) : routeTemplate2 == null;
    }

    public int getAdapterId() {
        return this.adapterId;
    }

    public List<RouteAlias> getAliases() {
        return this.aliases;
    }

    public int getContentMode() {
        return this.contentMode;
    }

    @Nullable
    public Route getDefaultChild() {
        return this.defaultChild;
    }

    public String getName() {
        return this.name;
    }

    @Nullable
    public String getParent() {
        return this.parent;
    }

    public int getPriority() {
        return this.priority;
    }

    @Nullable
    public RouteTemplate getTemplate() {
        return this.template;
    }

    @Nullable
    public Theme getTheme() {
        return this.theme;
    }

    @Nullable
    public String getViewControllerFactoryClassName() {
        return this.viewControllerFactoryClassName;
    }

    @Deprecated
    public boolean handlesHeaderTitle() {
        return this.handlesHeaderTitle;
    }

    public boolean hasAssociatedViewControllerFactory() {
        return !TextUtils.isEmpty(this.viewControllerFactoryClassName);
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        String str = this.parent;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        Route route = this.defaultChild;
        int hashCode3 = (hashCode2 + (route != null ? route.hashCode() : 0)) * 31;
        RouteTemplate routeTemplate = this.template;
        int hashCode4 = (((((((hashCode3 + (routeTemplate != null ? routeTemplate.hashCode() : 0)) * 31) + (this.singleChildRouteInBackStack ? 1 : 0)) * 31) + (this.root ? 1 : 0)) * 31) + (this.doNotKeepInBackStack ? 1 : 0)) * 31;
        Theme theme = this.theme;
        int outline7 = (GeneratedOutlineSupport1.outline7(this.module, (((((hashCode4 + (theme != null ? theme.hashCode() : 0)) * 31) + this.adapterId) * 31) + this.priority) * 31, 31) + (this.isOverlay ? 1 : 0)) * 31;
        String str2 = this.viewControllerFactoryClassName;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return outline7 + i;
    }

    @StringRes
    public Integer headerTitle() {
        return this.headerTitleResId;
    }

    public boolean is(String str) {
        return TextUtils.equals(getName(), str);
    }

    public boolean isOverlay() {
        return this.isOverlay;
    }

    public boolean isReactNative() {
        RouteTemplate routeTemplate = this.template;
        return routeTemplate != null && (routeTemplate.toString().startsWith("v2") || getAdapterId() == 1);
    }

    public boolean isRoot() {
        return this.parent == null || this.root;
    }

    public boolean isSingleChildRouteInBackStack() {
        return this.singleChildRouteInBackStack;
    }

    public void setContentMode(int i) {
        this.contentMode = i;
    }

    public void setDefaultChild(@NonNull Route route) {
        this.defaultChild = route;
    }

    public void setDoNotKeepInBackStack(boolean z) {
        this.doNotKeepInBackStack = z;
    }

    @Deprecated
    public void setHandlesHeaderTitle(boolean z) {
        this.handlesHeaderTitle = z;
    }

    public void setHeaderTitle(@StringRes Integer num) {
        this.headerTitleResId = num;
    }

    public void setOverlay(boolean z) {
        this.isOverlay = z;
    }

    public void setParent(@NonNull String str) throws IllegalArgumentException {
        this.parent = str;
        String str2 = this.name;
        if (str2 == null || !str2.equals(str)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trying to instantiate a route with itself as its parent: ");
        outline107.append(this.name);
        String sb = outline107.toString();
        Log.e(TAG, sb);
        throw new IllegalArgumentException(sb);
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public void setRoot(boolean z) {
        this.root = z;
    }

    public void setSingleChildRouteInBackStack(boolean z) {
        this.singleChildRouteInBackStack = z;
    }

    public void setSupportsDelegatedProfile(boolean z) {
        this.supportsDelegatedProfile = z;
    }

    public void setTemplate(@NonNull RouteTemplate routeTemplate) {
        this.template = routeTemplate;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public boolean supportsDelegatedProfile() {
        return this.supportsDelegatedProfile;
    }

    public String toString() {
        return this.name;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.parent);
        parcel.writeParcelable(this.defaultChild, i);
        parcel.writeParcelable(this.template, i);
        parcel.writeInt(this.singleChildRouteInBackStack ? 1 : 0);
        parcel.writeInt(this.root ? 1 : 0);
        parcel.writeInt(this.doNotKeepInBackStack ? 1 : 0);
        parcel.writeSerializable(this.theme);
        parcel.writeInt(this.adapterId);
        parcel.writeInt(this.priority);
        parcel.writeString(this.module);
        parcel.writeInt(this.isOverlay ? 1 : 0);
        parcel.writeString(this.viewControllerFactoryClassName);
        parcel.writeInt(this.contentMode);
    }

    public Route(String str, String str2) {
        this.contentMode = 1;
        this.priority = 50;
        this.module = null;
        this.name = str;
        this.theme = Theme.DEFAULT;
        this.adapterId = 18;
        this.aliases = new ArrayList<>();
        this.contentMode = 1;
        this.isOverlay = false;
        this.viewControllerFactoryClassName = str2;
    }

    public Route(Parcel parcel) throws IllegalArgumentException {
        boolean z = true;
        this.contentMode = 1;
        this.priority = 50;
        this.module = null;
        this.name = parcel.readString();
        this.parent = parcel.readString();
        this.defaultChild = (Route) parcel.readParcelable(Route.class.getClassLoader());
        this.template = (RouteTemplate) parcel.readParcelable(RouteTemplate.class.getClassLoader());
        this.singleChildRouteInBackStack = parcel.readInt() == 1;
        this.root = parcel.readInt() == 1;
        this.doNotKeepInBackStack = parcel.readInt() == 1;
        this.theme = (Theme) parcel.readSerializable();
        this.adapterId = parcel.readInt();
        this.priority = parcel.readInt();
        this.module = parcel.readString();
        this.isOverlay = parcel.readInt() != 1 ? false : z;
        this.viewControllerFactoryClassName = parcel.readString();
        this.contentMode = parcel.readInt();
        this.handlesHeaderTitle = false;
        this.headerTitleResId = null;
        this.aliases = new ArrayList<>();
    }
}
