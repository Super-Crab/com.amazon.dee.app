package org.webrtc;

import android.opengl.GLES20;
import java.nio.FloatBuffer;
import java.util.IdentityHashMap;
import java.util.Map;
import org.webrtc.RendererCommon;
/* loaded from: classes5.dex */
public class GlDrawer implements RendererCommon.GlDrawer {
    protected static final String DEFAULT_FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oes_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(oes_tex, interp_tc);\n}\n";
    private static final String DEFAULT_VERTEX_SHADER = "varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n";
    private static final FloatBuffer FULL_RECTANGLE_BUF = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
    private static final FloatBuffer FULL_RECTANGLE_TEX_BUF = GlUtil.createFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
    protected static final String RGB_FRAGMENT_SHADER = "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D oes_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(oes_tex, interp_tc);\n}\n";
    private static final String TAG = "GlDrawer";
    private static final String YUV_FRAGMENT_SHADER = "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\n\nvoid main() {\n  float y = texture2D(y_tex, interp_tc).r;\n  float u = texture2D(u_tex, interp_tc).r - 0.5;\n  float v = texture2D(v_tex, interp_tc).r - 0.5;\n  gl_FragColor = vec4(y + 1.403 * v,                       y - 0.344 * u - 0.714 * v,                       y + 1.77 * u, 1);\n}\n";
    protected final Map<String, Shader> shaders = new IdentityHashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes5.dex */
    public static class Shader {
        public final GlShader glShader;
        public final int texMatrixLocation;

        public Shader(String str, String str2) {
            this.glShader = new GlShader(str, str2);
            this.texMatrixLocation = this.glShader.getUniformLocation("texMatrix");
        }
    }

    private void drawRectangle(int i, int i2, int i3, int i4) {
        GLES20.glViewport(i, i2, i3, i4);
        GLES20.glDrawArrays(5, 0, 4);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawOes(int i, float[] fArr, int i2, int i3, int i4, int i5, int i6, int i7) {
        prepareShader(getVertexShader(), getFragmentShader(), fArr, i6, i7);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i);
        drawRectangle(i4, i5, i6, i7);
        GLES20.glBindTexture(36197, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawRgb(int i, float[] fArr, int i2, int i3, int i4, int i5, int i6, int i7) {
        prepareShader(getVertexShader(), getRgbFragmentShader(), fArr, i6, i7);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        drawRectangle(i4, i5, i6, i7);
        GLES20.glBindTexture(3553, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawYuv(int[] iArr, float[] fArr, int i, int i2, int i3, int i4, int i5, int i6) {
        prepareShader(DEFAULT_VERTEX_SHADER, getYuvFragmentShader(), fArr, i5, i6);
        for (int i7 = 0; i7 < 3; i7++) {
            GLES20.glActiveTexture(33984 + i7);
            GLES20.glBindTexture(3553, iArr[i7]);
        }
        drawRectangle(i3, i4, i5, i6);
        for (int i8 = 0; i8 < 3; i8++) {
            GLES20.glActiveTexture(i8 + 33984);
            GLES20.glBindTexture(3553, 0);
        }
    }

    protected String getFragmentShader() {
        return DEFAULT_FRAGMENT_SHADER;
    }

    protected String getRgbFragmentShader() {
        return RGB_FRAGMENT_SHADER;
    }

    protected String getVertexShader() {
        return DEFAULT_VERTEX_SHADER;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getYuvFragmentShader() {
        return YUV_FRAGMENT_SHADER;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Shader prepareShader(String str, String str2, float[] fArr, float f, float f2) {
        Shader shader;
        if (this.shaders.containsKey(str2)) {
            shader = this.shaders.get(str2);
        } else {
            Shader shader2 = new Shader(str, str2);
            this.shaders.put(str2, shader2);
            shader2.glShader.useProgram();
            if (str2 == getYuvFragmentShader()) {
                GLES20.glUniform1i(shader2.glShader.getUniformLocation("y_tex"), 0);
                GLES20.glUniform1i(shader2.glShader.getUniformLocation("u_tex"), 1);
                GLES20.glUniform1i(shader2.glShader.getUniformLocation("v_tex"), 2);
            } else {
                GLES20.glUniform1i(shader2.glShader.getUniformLocation("oes_tex"), 0);
            }
            GlUtil.checkNoGLES2Error("Initialize fragment shader uniform values.");
            shader2.glShader.setVertexAttribArray("in_pos", 2, FULL_RECTANGLE_BUF);
            shader2.glShader.setVertexAttribArray("in_tc", 2, FULL_RECTANGLE_TEX_BUF);
            shader = shader2;
        }
        shader.glShader.useProgram();
        GLES20.glUniformMatrix4fv(shader.texMatrixLocation, 1, false, fArr, 0);
        return shader;
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void release() {
        for (Shader shader : this.shaders.values()) {
            shader.glShader.release();
        }
        this.shaders.clear();
    }
}