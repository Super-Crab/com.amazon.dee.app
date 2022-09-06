package com.amazon.alexa.fitness.components;

import android.location.Location;
import com.here.sdk.core.GeoCoordinates;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CenterAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/location/Location;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CenterAdapter$adapt$1 extends Lambda implements Function1<Location, Unit> {
    final /* synthetic */ CenterAdapter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CenterAdapter$adapt$1(CenterAdapter centerAdapter) {
        super(1);
        this.this$0 = centerAdapter;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Location location) {
        invoke2(location);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable Location location) {
        if (location != null) {
            this.this$0.center(new GeoCoordinates(location.getLatitude(), location.getLongitude()));
            this.this$0.preventAutoCentering = false;
        }
    }
}
