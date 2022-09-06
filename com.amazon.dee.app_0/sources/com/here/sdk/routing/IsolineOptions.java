package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public final class IsolineOptions {
    @NonNull
    public Calculation calculationOptions;
    @Nullable
    public CarOptions carOptions;
    @Nullable
    public EVCarOptions evCarOptions;
    @Nullable
    public EVTruckOptions evTruckOptions;
    @Nullable
    public TruckOptions truckOptions;

    /* loaded from: classes3.dex */
    public static final class Calculation {
        @NonNull
        public IsolineCalculationMode isolineCalculationMode;
        @Nullable
        public Integer maxPoints;
        @NonNull
        public IsolineRangeType rangeType;
        @NonNull
        public List<Integer> rangeValues;

        public Calculation(@NonNull IsolineRangeType isolineRangeType) {
            this.rangeType = isolineRangeType;
            this.rangeValues = new ArrayList();
            this.isolineCalculationMode = IsolineCalculationMode.BALANCED;
            this.maxPoints = null;
        }

        public Calculation(@NonNull IsolineRangeType isolineRangeType, @NonNull List<Integer> list, @NonNull IsolineCalculationMode isolineCalculationMode, @Nullable Integer num) {
            this.rangeType = isolineRangeType;
            this.rangeValues = list;
            this.isolineCalculationMode = isolineCalculationMode;
            this.maxPoints = num;
        }
    }

    public IsolineOptions(@NonNull Calculation calculation, @NonNull CarOptions carOptions) {
        IsolineOptions make = make(calculation, carOptions);
        this.calculationOptions = make.calculationOptions;
        this.carOptions = make.carOptions;
        this.truckOptions = make.truckOptions;
        this.evCarOptions = make.evCarOptions;
        this.evTruckOptions = make.evTruckOptions;
    }

    public IsolineOptions(@NonNull Calculation calculation, @NonNull EVCarOptions eVCarOptions) {
        IsolineOptions make = make(calculation, eVCarOptions);
        this.calculationOptions = make.calculationOptions;
        this.carOptions = make.carOptions;
        this.truckOptions = make.truckOptions;
        this.evCarOptions = make.evCarOptions;
        this.evTruckOptions = make.evTruckOptions;
    }

    public IsolineOptions(@NonNull Calculation calculation, @NonNull EVTruckOptions eVTruckOptions) {
        IsolineOptions make = make(calculation, eVTruckOptions);
        this.calculationOptions = make.calculationOptions;
        this.carOptions = make.carOptions;
        this.truckOptions = make.truckOptions;
        this.evCarOptions = make.evCarOptions;
        this.evTruckOptions = make.evTruckOptions;
    }

    public IsolineOptions(@NonNull Calculation calculation, @NonNull TruckOptions truckOptions) {
        IsolineOptions make = make(calculation, truckOptions);
        this.calculationOptions = make.calculationOptions;
        this.carOptions = make.carOptions;
        this.truckOptions = make.truckOptions;
        this.evCarOptions = make.evCarOptions;
        this.evTruckOptions = make.evTruckOptions;
    }

    private static native IsolineOptions make(@NonNull Calculation calculation, @NonNull CarOptions carOptions);

    private static native IsolineOptions make(@NonNull Calculation calculation, @NonNull EVCarOptions eVCarOptions);

    private static native IsolineOptions make(@NonNull Calculation calculation, @NonNull EVTruckOptions eVTruckOptions);

    private static native IsolineOptions make(@NonNull Calculation calculation, @NonNull TruckOptions truckOptions);
}
