package org.webrtc;

import android.opengl.GLES20;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import java.nio.Buffer;
import java.nio.FloatBuffer;
/* loaded from: classes5.dex */
public class GlShader {
    private static final String TAG = "GlShader";
    private int program;

    public GlShader(String str, String str2) {
        int compileShader = compileShader(35633, str);
        int compileShader2 = compileShader(35632, str2);
        this.program = GLES20.glCreateProgram();
        int i = this.program;
        if (i != 0) {
            GLES20.glAttachShader(i, compileShader);
            GLES20.glAttachShader(this.program, compileShader2);
            GLES20.glLinkProgram(this.program);
            int[] iArr = {0};
            GLES20.glGetProgramiv(this.program, 35714, iArr, 0);
            if (iArr[0] == 1) {
                GLES20.glDeleteShader(compileShader);
                GLES20.glDeleteShader(compileShader2);
                GlUtil.checkNoGLES2Error("Creating GlShader");
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not link program: ");
            outline107.append(GLES20.glGetProgramInfoLog(this.program));
            Logging.e(TAG, outline107.toString());
            throw new RuntimeException(GLES20.glGetProgramInfoLog(this.program));
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("glCreateProgram() failed. GLES20 error: ");
        outline1072.append(GLES20.glGetError());
        throw new RuntimeException(outline1072.toString());
    }

    private static int compileShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            GLES20.glCompileShader(glCreateShader);
            int[] iArr = {0};
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            if (iArr[0] == 1) {
                GlUtil.checkNoGLES2Error("compileShader");
                return glCreateShader;
            }
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Could not compile shader ", i, ":");
            outline109.append(GLES20.glGetShaderInfoLog(glCreateShader));
            Logging.e(TAG, outline109.toString());
            throw new RuntimeException(GLES20.glGetShaderInfoLog(glCreateShader));
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("glCreateShader() failed. GLES20 error: ");
        outline107.append(GLES20.glGetError());
        throw new RuntimeException(outline107.toString());
    }

    public int getAttribLocation(String str) {
        int i = this.program;
        if (i != -1) {
            int glGetAttribLocation = GLES20.glGetAttribLocation(i, str);
            if (glGetAttribLocation < 0) {
                throw new RuntimeException(GeneratedOutlineSupport1.outline75("Could not locate '", str, "' in program"));
            }
            return glGetAttribLocation;
        }
        throw new RuntimeException("The program has been released");
    }

    public int getUniformLocation(String str) {
        int i = this.program;
        if (i != -1) {
            int glGetUniformLocation = GLES20.glGetUniformLocation(i, str);
            if (glGetUniformLocation < 0) {
                throw new RuntimeException(GeneratedOutlineSupport1.outline75("Could not locate uniform '", str, "' in program"));
            }
            return glGetUniformLocation;
        }
        throw new RuntimeException("The program has been released");
    }

    public void release() {
        Logging.d(TAG, "Deleting shader.");
        int i = this.program;
        if (i != -1) {
            GLES20.glDeleteProgram(i);
            this.program = -1;
        }
    }

    public void setVertexAttribArray(String str, int i, FloatBuffer floatBuffer) {
        setVertexAttribArray(str, i, 0, floatBuffer);
    }

    public void useProgram() {
        int i = this.program;
        if (i != -1) {
            GLES20.glUseProgram(i);
            GlUtil.checkNoGLES2Error("glUseProgram");
            return;
        }
        throw new RuntimeException("The program has been released");
    }

    public void setVertexAttribArray(String str, int i, int i2, FloatBuffer floatBuffer) {
        if (this.program != -1) {
            int attribLocation = getAttribLocation(str);
            GLES20.glEnableVertexAttribArray(attribLocation);
            GLES20.glVertexAttribPointer(attribLocation, i, (int) FujifilmMakernoteDirectory.TAG_MAX_APERTURE_AT_MIN_FOCAL, false, i2, (Buffer) floatBuffer);
            GlUtil.checkNoGLES2Error("setVertexAttribArray");
            return;
        }
        throw new RuntimeException("The program has been released");
    }
}
