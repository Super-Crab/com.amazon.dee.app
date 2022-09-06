package androidx.transition;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;
/* loaded from: classes.dex */
class PathProperty<T> extends Property<T, Float> {
    private float mCurrentFraction;
    private final float mPathLength;
    private final PathMeasure mPathMeasure;
    private final PointF mPointF;
    private final float[] mPosition;
    private final Property<T, PointF> mProperty;

    PathProperty(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.mPosition = new float[2];
        this.mPointF = new PointF();
        this.mProperty = property;
        this.mPathMeasure = new PathMeasure(path, false);
        this.mPathLength = this.mPathMeasure.getLength();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.util.Property
    /* renamed from: get  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Float mo206get(Object obj) {
        return get((PathProperty<T>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.util.Property
    public /* bridge */ /* synthetic */ void set(Object obj, Float f) {
        set2((PathProperty<T>) obj, f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.util.Property
    public Float get(T t) {
        return Float.valueOf(this.mCurrentFraction);
    }

    /* renamed from: set  reason: avoid collision after fix types in other method */
    public void set2(T t, Float f) {
        this.mCurrentFraction = f.floatValue();
        this.mPathMeasure.getPosTan(f.floatValue() * this.mPathLength, this.mPosition, null);
        PointF pointF = this.mPointF;
        float[] fArr = this.mPosition;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.mProperty.set(t, pointF);
    }
}