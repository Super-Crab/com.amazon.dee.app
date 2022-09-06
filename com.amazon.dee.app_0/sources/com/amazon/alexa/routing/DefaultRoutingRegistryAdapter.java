package com.amazon.alexa.routing;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
/* loaded from: classes10.dex */
public class DefaultRoutingRegistryAdapter implements RoutingRegistryAdapter {
    private static final String TAG = "DefaultRoutingRegistryAdapter";
    private final SparseArray<RoutingAdapter> adapters = new SparseArray<>();
    private final PublishSubject<Integer> onAdapterAdded = PublishSubject.create();

    @Override // com.amazon.alexa.routing.api.RoutingRegistryAdapter
    public boolean canEnter(@NonNull Route route) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("canEnter: ");
        outline107.append(route.getName());
        outline107.append(" - ");
        outline107.append(route.getAdapterId());
        outline107.toString();
        if (route.getAdapterId() == 1) {
            return true;
        }
        RoutingAdapter.RouteConfiguration configuration = getConfiguration(route);
        return configuration != null && configuration.canEnter();
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistryAdapter
    public boolean canLeave(@NonNull Route route) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("canLeave: ");
        outline107.append(route.getName());
        outline107.append(" - ");
        outline107.append(route.getAdapterId());
        outline107.toString();
        if (route.getAdapterId() == 1) {
            return true;
        }
        RoutingAdapter.RouteConfiguration configuration = getConfiguration(route);
        return configuration != null && configuration.canLeave();
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistryAdapter
    public boolean canNavigate(@NonNull Route route) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("canEnter: ");
        outline107.append(route.getName());
        outline107.append(" - ");
        outline107.append(route.getAdapterId());
        outline107.toString();
        if (route.getAdapterId() == 1) {
            return true;
        }
        RoutingAdapter.RouteConfiguration configuration = getConfiguration(route);
        return configuration != null && configuration.canNavigate();
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistryAdapter
    public RoutingAdapter get(int i) {
        return this.adapters.get(i);
    }

    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        RoutingAdapter routingAdapter = this.adapters.get(route.getAdapterId());
        if (routingAdapter != null) {
            return routingAdapter.getConfiguration(route);
        }
        return null;
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistryAdapter
    public Observable<Integer> onAdapterAdded() {
        return this.onAdapterAdded.hide();
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistryAdapter
    public void register(@NonNull RoutingAdapter routingAdapter) {
        if (this.adapters.get(routingAdapter.getId(), null) == null) {
            this.adapters.put(routingAdapter.getId(), routingAdapter);
            this.onAdapterAdded.onNext(Integer.valueOf(routingAdapter.getId()));
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Adapter ");
        outline107.append(routingAdapter.getClass().getSimpleName());
        outline107.append(" is already registered");
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistryAdapter
    public void unregister(@NonNull RoutingAdapter routingAdapter) {
        this.adapters.remove(routingAdapter.getId());
    }
}
