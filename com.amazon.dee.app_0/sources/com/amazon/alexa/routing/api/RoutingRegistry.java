package com.amazon.alexa.routing.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.amazon.alexa.routing.api.Route;
/* loaded from: classes10.dex */
public interface RoutingRegistry extends Iterable<Route> {

    /* loaded from: classes10.dex */
    public interface Register {
        Register asRoot();

        Register doNotKeepInBackStack();

        @Deprecated
        Register withHandleHeaderTitle();

        Register withHeaderTitle(@StringRes Integer num);

        Register withParent(String str);

        Register withParentDefault(String str);

        Register withSingleChildRouteInBackStack();

        Register withTemplate(@NonNull String str);

        Register withTheme(Route.Theme theme);
    }

    @NonNull
    Route get(String str);

    @Nullable
    RouteMatch getByUrl(String str);

    Register register(@NonNull Route route);

    @NonNull
    Register register(String str, int i);

    void unregister(@NonNull Route route);
}
