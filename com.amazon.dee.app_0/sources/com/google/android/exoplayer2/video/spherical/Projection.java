package com.google.android.exoplayer2.video.spherical;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
public final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface DrawMode {
    }

    /* loaded from: classes2.dex */
    public static final class Mesh {
        private final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshArr) {
            this.subMeshes = subMeshArr;
        }

        public SubMesh getSubMesh(int i) {
            return this.subMeshes[i];
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }
    }

    /* loaded from: classes2.dex */
    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int i, float[] fArr, float[] fArr2, int i2) {
            this.textureId = i;
            Assertions.checkArgument(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
            this.vertices = fArr;
            this.textureCoords = fArr2;
            this.mode = i2;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    public Projection(Mesh mesh, int i) {
        this(mesh, mesh, i);
    }

    public static Projection createEquirectangular(int i) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, i);
    }

    public Projection(Mesh mesh, Mesh mesh2, int i) {
        this.leftMesh = mesh;
        this.rightMesh = mesh2;
        this.stereoMode = i;
        this.singleMesh = mesh == mesh2;
    }

    public static Projection createEquirectangular(float f, int i, int i2, float f2, float f3, int i3) {
        float[] fArr;
        int i4;
        int i5 = i;
        boolean z = true;
        Assertions.checkArgument(f > 0.0f);
        Assertions.checkArgument(i5 >= 1);
        Assertions.checkArgument(i2 >= 1);
        Assertions.checkArgument(f2 > 0.0f && f2 <= 180.0f);
        if (f3 <= 0.0f || f3 > 360.0f) {
            z = false;
        }
        Assertions.checkArgument(z);
        float radians = (float) Math.toRadians(f2);
        float radians2 = (float) Math.toRadians(f3);
        float f4 = radians / i5;
        float f5 = radians2 / i2;
        int i6 = i2 + 1;
        int outline4 = GeneratedOutlineSupport1.outline4(i6, 2, 2, i5);
        float[] fArr2 = new float[outline4 * 3];
        float[] fArr3 = new float[outline4 * 2];
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < i5) {
            float f6 = radians / 2.0f;
            float f7 = (i7 * f4) - f6;
            int i10 = i7 + 1;
            float f8 = (i10 * f4) - f6;
            int i11 = 0;
            while (i11 < i6) {
                int i12 = i8;
                int i13 = 0;
                int i14 = i9;
                int i15 = 2;
                while (i13 < i15) {
                    float f9 = f8;
                    float f10 = i13 == 0 ? f7 : f9;
                    float f11 = i11 * f5;
                    float f12 = f5;
                    int i16 = i12 + 1;
                    float f13 = f7;
                    int i17 = i10;
                    double d = f;
                    int i18 = i6;
                    double d2 = (f11 + 3.1415927f) - (radians2 / 2.0f);
                    float f14 = radians;
                    double d3 = f10;
                    int i19 = i13;
                    fArr2[i12] = -((float) (Math.cos(d3) * Math.sin(d2) * d));
                    int i20 = i16 + 1;
                    float[] fArr4 = fArr3;
                    int i21 = i7;
                    fArr2[i16] = (float) (Math.sin(d3) * d);
                    int i22 = i20 + 1;
                    fArr2[i20] = (float) (Math.cos(d3) * Math.cos(d2) * d);
                    int i23 = i14 + 1;
                    fArr4[i14] = f11 / radians2;
                    int i24 = i23 + 1;
                    fArr4[i23] = ((i21 + i19) * f4) / f14;
                    if (i11 == 0 && i19 == 0) {
                        i4 = i19;
                    } else {
                        if (i11 == i2) {
                            i4 = i19;
                            if (i4 != 1) {
                                fArr = fArr4;
                            }
                        } else {
                            fArr = fArr4;
                            i4 = i19;
                        }
                        i14 = i24;
                        i15 = 2;
                        i12 = i22;
                        i13 = i4 + 1;
                        f5 = f12;
                        f8 = f9;
                        fArr3 = fArr;
                        f7 = f13;
                        i10 = i17;
                        i6 = i18;
                        radians = f14;
                        i7 = i21;
                    }
                    System.arraycopy(fArr2, i22 - 3, fArr2, i22, 3);
                    i22 += 3;
                    fArr = fArr4;
                    System.arraycopy(fArr, i24 - 2, fArr, i24, 2);
                    i14 = i24 + 2;
                    i15 = 2;
                    i12 = i22;
                    i13 = i4 + 1;
                    f5 = f12;
                    f8 = f9;
                    fArr3 = fArr;
                    f7 = f13;
                    i10 = i17;
                    i6 = i18;
                    radians = f14;
                    i7 = i21;
                }
                i11++;
                i8 = i12;
                i9 = i14;
                i7 = i7;
            }
            i5 = i;
            i7 = i10;
        }
        return new Projection(new Mesh(new SubMesh(0, fArr2, fArr3, 1)), i3);
    }
}
