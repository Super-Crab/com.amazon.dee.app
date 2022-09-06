package com.amazon.alexa.fitness.location;

import android.location.Location;
import com.amazon.alexa.fitness.api.LocationServiceListener;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocationService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/location/Location;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class LocationServiceImpl$requestLocationOnce$1 extends Lambda implements Function1<Location, Unit> {
    final /* synthetic */ LocationServiceImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocationServiceImpl$requestLocationOnce$1(LocationServiceImpl locationServiceImpl) {
        super(1);
        this.this$0 = locationServiceImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Location location) {
        invoke2(location);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable Location location) {
        Map map;
        if (location != null) {
            map = this.this$0.listeners;
            for (WeakReference weakReference : map.values()) {
                LocationServiceListener locationServiceListener = (LocationServiceListener) weakReference.get();
                if (locationServiceListener != null) {
                    locationServiceListener.onLocationUpdated(location);
                }
            }
        }
    }
}
