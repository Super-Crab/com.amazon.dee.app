package com.google.android.exoplayer2.util;

import android.content.Context;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
/* loaded from: classes2.dex */
public final class GlUtil {
    private static final String EXTENSION_PROTECTED_CONTENT = "EGL_EXT_protected_content";
    private static final String EXTENSION_SURFACELESS_CONTEXT = "EGL_KHR_surfaceless_context";
    private static final String TAG = "GlUtil";

    /* loaded from: classes2.dex */
    public static final class Attribute {
        @Nullable
        private Buffer buffer;
        private final int index;
        private final int location;
        public final String name;
        private int size;

        public Attribute(int i, int i2) {
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i, 35722, iArr, 0);
            byte[] bArr = new byte[iArr[0]];
            int[] iArr2 = new int[1];
            int i3 = iArr[0];
            GLES20.glGetActiveAttrib(i, i2, i3, iArr2, 0, new int[1], 0, new int[1], 0, bArr, 0);
            this.name = new String(bArr, 0, GlUtil.strlen(bArr));
            this.location = GLES20.glGetAttribLocation(i, this.name);
            this.index = i2;
        }

        public void bind() {
            GLES20.glBindBuffer(34962, 0);
            GLES20.glVertexAttribPointer(this.location, this.size, (int) FujifilmMakernoteDirectory.TAG_MAX_APERTURE_AT_MIN_FOCAL, false, 0, (Buffer) Assertions.checkNotNull(this.buffer, "call setBuffer before bind"));
            GLES20.glEnableVertexAttribArray(this.index);
            GlUtil.checkGlError();
        }

        public void setBuffer(float[] fArr, int i) {
            this.buffer = GlUtil.createBuffer(fArr);
            this.size = i;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Uniform {
        private final int location;
        public final String name;
        private int texId;
        private final int type;
        private int unit;
        private final float[] value;

        public Uniform(int i, int i2) {
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i, 35719, iArr, 0);
            int[] iArr2 = new int[1];
            byte[] bArr = new byte[iArr[0]];
            int i3 = iArr[0];
            GLES20.glGetActiveUniform(i, i2, i3, new int[1], 0, new int[1], 0, iArr2, 0, bArr, 0);
            this.name = new String(bArr, 0, GlUtil.strlen(bArr));
            this.location = GLES20.glGetUniformLocation(i, this.name);
            this.type = iArr2[0];
            this.value = new float[1];
        }

        public void bind() {
            if (this.type == 5126) {
                GLES20.glUniform1fv(this.location, 1, this.value, 0);
                GlUtil.checkGlError();
            } else if (this.texId != 0) {
                GLES20.glActiveTexture(this.unit + 33984);
                int i = this.type;
                if (i == 36198) {
                    GLES20.glBindTexture(36197, this.texId);
                } else if (i == 35678) {
                    GLES20.glBindTexture(3553, this.texId);
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unexpected uniform type: ");
                    outline107.append(this.type);
                    throw new IllegalStateException(outline107.toString());
                }
                GLES20.glUniform1i(this.location, this.unit);
                GLES20.glTexParameteri(3553, 10240, 9729);
                GLES20.glTexParameteri(3553, 10241, 9729);
                GLES20.glTexParameteri(3553, 10242, 33071);
                GLES20.glTexParameteri(3553, 10243, 33071);
                GlUtil.checkGlError();
            } else {
                throw new IllegalStateException("call setSamplerTexId before bind");
            }
        }

        public void setFloat(float f) {
            this.value[0] = f;
        }

        public void setSamplerTexId(int i, int i2) {
            this.texId = i;
            this.unit = i2;
        }
    }

    private GlUtil() {
    }

    private static void addShader(int i, String str, int i2) {
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = {0};
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 1) {
            throwGlError(GLES20.glGetShaderInfoLog(glCreateShader) + ", source: " + str);
        }
        GLES20.glAttachShader(i2, glCreateShader);
        GLES20.glDeleteShader(glCreateShader);
        checkGlError();
    }

    public static void checkGlError() {
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError != 0) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("glError ");
                outline107.append(GLU.gluErrorString(glGetError));
                Log.e(TAG, outline107.toString());
            } else {
                return;
            }
        }
    }

    public static int compileProgram(String[] strArr, String[] strArr2) {
        return compileProgram(TextUtils.join("\n", strArr), TextUtils.join("\n", strArr2));
    }

    public static FloatBuffer createBuffer(float[] fArr) {
        return (FloatBuffer) createBuffer(fArr.length).put(fArr).flip();
    }

    public static int createExternalTexture() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, IntBuffer.wrap(iArr));
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameteri(36197, 10241, 9729);
        GLES20.glTexParameteri(36197, 10240, 9729);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        checkGlError();
        return iArr[0];
    }

    public static Attribute[] getAttributes(int i) {
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(i, 35721, iArr, 0);
        if (iArr[0] == 2) {
            Attribute[] attributeArr = new Attribute[iArr[0]];
            for (int i2 = 0; i2 < iArr[0]; i2++) {
                attributeArr[i2] = new Attribute(i, i2);
            }
            return attributeArr;
        }
        throw new IllegalStateException("expected two attributes");
    }

    public static Uniform[] getUniforms(int i) {
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(i, 35718, iArr, 0);
        Uniform[] uniformArr = new Uniform[iArr[0]];
        for (int i2 = 0; i2 < iArr[0]; i2++) {
            uniformArr[i2] = new Uniform(i, i2);
        }
        return uniformArr;
    }

    public static boolean isProtectedContentExtensionSupported(Context context) {
        String eglQueryString;
        int i = Util.SDK_INT;
        if (i < 24) {
            return false;
        }
        if (i < 26 && ("samsung".equals(Util.MANUFACTURER) || "XT1650".equals(Util.MODEL))) {
            return false;
        }
        return (Util.SDK_INT >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) && (eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) != null && eglQueryString.contains(EXTENSION_PROTECTED_CONTENT);
    }

    public static boolean isSurfacelessContextExtensionSupported() {
        String eglQueryString;
        return Util.SDK_INT >= 17 && (eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) != null && eglQueryString.contains(EXTENSION_SURFACELESS_CONTEXT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int strlen(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] == 0) {
                return i;
            }
        }
        return bArr.length;
    }

    private static void throwGlError(String str) {
        Log.e(TAG, str);
    }

    public static int compileProgram(String str, String str2) {
        int glCreateProgram = GLES20.glCreateProgram();
        checkGlError();
        addShader(35633, str, glCreateProgram);
        addShader(35632, str2, glCreateProgram);
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = {0};
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to link shader program: \n");
            outline107.append(GLES20.glGetProgramInfoLog(glCreateProgram));
            throwGlError(outline107.toString());
        }
        checkGlError();
        return glCreateProgram;
    }

    public static FloatBuffer createBuffer(int i) {
        return ByteBuffer.allocateDirect(i * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }
}
