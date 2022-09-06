package com.amazon.alexa.fitness.components;

import com.amazon.alexa.fitness.ui.R;
import com.here.sdk.core.GeoCoordinates;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SinglePointRouteAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/components/SinglePointRouteAdapter;", "Lcom/amazon/alexa/fitness/components/MarkerAdapter;", "coordinate", "Lcom/here/sdk/core/GeoCoordinates;", "(Lcom/here/sdk/core/GeoCoordinates;)V", "markerResourceId", "", "getMarkerResourceId", "()I", "setMarkerResourceId", "(I)V", "adapt", "", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SinglePointRouteAdapter extends MarkerAdapter {
    private final GeoCoordinates coordinate;
    private int markerResourceId;

    public SinglePointRouteAdapter(@NotNull GeoCoordinates coordinate) {
        Intrinsics.checkParameterIsNotNull(coordinate, "coordinate");
        this.coordinate = coordinate;
        this.markerResourceId = R.drawable.blue_circle_with_stroke;
    }

    @Override // com.amazon.alexa.fitness.components.MarkerAdapter, com.amazon.alexa.fitness.components.MapViewAdapter
    public void adapt() {
        addMarker(this.coordinate);
    }

    @Override // com.amazon.alexa.fitness.components.MarkerAdapter
    protected int getMarkerResourceId() {
        return this.markerResourceId;
    }

    @Override // com.amazon.alexa.fitness.components.MarkerAdapter
    protected void setMarkerResourceId(int i) {
        this.markerResourceId = i;
    }
}
