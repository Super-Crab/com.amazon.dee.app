package com.amazon.alexa.fitness.components;

import kotlin.Metadata;
/* compiled from: SummaryRouteAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/fitness/components/SummaryRouteAdapter;", "Lcom/amazon/alexa/fitness/components/RouteAdapter;", "()V", "adapt", "", "center", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SummaryRouteAdapter extends RouteAdapter {
    @Override // com.amazon.alexa.fitness.components.RouteAdapter, com.amazon.alexa.fitness.components.MapViewAdapter
    public void adapt() {
        super.adapt();
        center();
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0006, code lost:
        r0 = r0.getMap();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void center() {
        /*
            r12 = this;
            com.amazon.alexa.fitness.components.WorkoutMapView r0 = r12.getMapView()
            if (r0 == 0) goto L88
            com.here.sdk.mapview.MapView r0 = r0.getMap()
            if (r0 == 0) goto L88
            java.util.List r1 = r12.getCoordinates()
            java.util.List r1 = kotlin.collections.CollectionsKt.toList(r1)
            boolean r2 = r1.isEmpty()
            r2 = r2 ^ 1
            if (r2 == 0) goto L7f
            com.here.sdk.mapview.MapCamera r2 = r0.getCamera()     // Catch: java.lang.Exception -> L4a
            com.here.sdk.core.GeoBox r3 = com.here.sdk.core.GeoBox.containing(r1)     // Catch: java.lang.Exception -> L4a
            if (r3 != 0) goto L29
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Exception -> L4a
        L29:
            r4 = 4621819117588971520(0x4024000000000000, double:10.0)
            r6 = 4621819117588971520(0x4024000000000000, double:10.0)
            r8 = 4621819117588971520(0x4024000000000000, double:10.0)
            r10 = 4621819117588971520(0x4024000000000000, double:10.0)
            com.here.sdk.core.GeoBox r1 = r3.expandedBy(r4, r6, r8, r10)     // Catch: java.lang.Exception -> L4a
            com.here.sdk.mapview.MapCamera$OrientationUpdate r3 = new com.here.sdk.mapview.MapCamera$OrientationUpdate     // Catch: java.lang.Exception -> L4a
            r4 = 0
            java.lang.Double r6 = java.lang.Double.valueOf(r4)     // Catch: java.lang.Exception -> L4a
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch: java.lang.Exception -> L4a
            r3.<init>(r6, r4)     // Catch: java.lang.Exception -> L4a
            r2.lookAt(r1, r3)     // Catch: java.lang.Exception -> L4a
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Exception -> L4a
            goto L88
        L4a:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Error occurred moving camera around route: "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "AFX-SummaryRouteAdapter"
            android.util.Log.e(r2, r1)
            com.here.sdk.mapview.MapCamera r1 = r0.getCamera()
            java.util.List r2 = r12.getCoordinates()
            r3 = 0
            java.lang.Object r2 = r2.get(r3)
            com.here.sdk.core.GeoCoordinates r2 = (com.here.sdk.core.GeoCoordinates) r2
            r1.lookAt(r2)
            com.here.sdk.mapview.MapCamera r0 = r0.getCamera()
            r1 = 4625759767262920704(0x4032000000000000, double:18.0)
            r0.zoomTo(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            goto L88
        L7f:
            com.amazon.alexa.fitness.components.WorkoutMapView r0 = r12.getMapView()
            if (r0 == 0) goto L88
            r0.center()
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.components.SummaryRouteAdapter.center():void");
    }
}
