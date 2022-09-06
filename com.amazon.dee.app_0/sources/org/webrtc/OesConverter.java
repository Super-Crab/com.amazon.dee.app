package org.webrtc;

import android.opengl.GLES20;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import java.nio.FloatBuffer;
import org.webrtc.ThreadUtils;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class OesConverter {
    private static final FloatBuffer FULL_RECTANGLE_BUF = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
    private static final FloatBuffer FULL_RECTANGLE_TEX_BUF = GlUtil.createFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
    private static final String TAG = "OesConverter";
    private int gs1FrameBufferId;
    private int gs1RadiusLocation;
    private int gs1RenderBufferId;
    private int gs1TexMatrixLocation;
    private int gs1TextureId;
    private int gs1WidthLocation;
    private int gs2FrameBufferId;
    private int gs2HeightLocation;
    private int gs2RadiusLocation;
    private int gs2RenderBufferId;
    private int gs2TexMatrixLocation;
    private int rgbTextureId;
    private final GlShader shadergs1;
    private final GlShader shadergs2;
    private final ThreadUtils.ThreadChecker threadChecker = new ThreadUtils.ThreadChecker();
    private boolean released = false;
    private final String strVShader = "varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n";
    private final String strGaussianFShaderH = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oes_tex;\nuniform float width;\nuniform float radius;\n\nvoid main() {\n  vec4 sum = vec4(0.0);\n  vec2 tc = interp_tc;\n  float blur = radius/width;\n  sum += texture2D(oes_tex, vec2(tc.x - 4.0*blur, tc.y)) * 0.0162162162;\n  sum += texture2D(oes_tex, vec2(tc.x - 3.0*blur, tc.y)) * 0.0540540541;\n  sum += texture2D(oes_tex, vec2(tc.x - 2.0*blur, tc.y)) * 0.1216216216;\n  sum += texture2D(oes_tex, vec2(tc.x - 1.0*blur, tc.y)) * 0.1945945946;\n  sum += texture2D(oes_tex, vec2(tc.x, tc.y)) * 0.2270270270;\n  sum += texture2D(oes_tex, vec2(tc.x + 1.0*blur, tc.y)) * 0.1945945946;\n  sum += texture2D(oes_tex, vec2(tc.x + 2.0*blur, tc.y)) * 0.1216216216;\n  sum += texture2D(oes_tex, vec2(tc.x + 3.0*blur, tc.y)) * 0.0540540541;\n  sum += texture2D(oes_tex, vec2(tc.x + 4.0*blur, tc.y)) * 0.0162162162;\n  gl_FragColor = vec4(sum.rgb, 1.0);\n}\n";
    private final String strGaussianFShaderV = "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D rgb_tex;\nuniform float height;\nuniform float radius;\n\nvoid main() {\n  vec4 sum = vec4(0.0);\n  vec2 tc = interp_tc;\n  float blur = radius/height;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y - 4.0*blur)) * 0.0162162162;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y - 3.0*blur)) * 0.0540540541;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y - 2.0*blur)) * 0.1216216216;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y - 1.0*blur)) * 0.1945945946;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y)) * 0.2270270270;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y + 1.0*blur)) * 0.1945945946;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y + 2.0*blur)) * 0.1216216216;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y + 3.0*blur)) * 0.0540540541;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y + 4.0*blur)) * 0.0162162162;\n  gl_FragColor = vec4(sum.rgb, 1.0);\n}\n";

    public OesConverter() {
        this.threadChecker.checkIsOnValidThread();
        this.shadergs1 = new GlShader("varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oes_tex;\nuniform float width;\nuniform float radius;\n\nvoid main() {\n  vec4 sum = vec4(0.0);\n  vec2 tc = interp_tc;\n  float blur = radius/width;\n  sum += texture2D(oes_tex, vec2(tc.x - 4.0*blur, tc.y)) * 0.0162162162;\n  sum += texture2D(oes_tex, vec2(tc.x - 3.0*blur, tc.y)) * 0.0540540541;\n  sum += texture2D(oes_tex, vec2(tc.x - 2.0*blur, tc.y)) * 0.1216216216;\n  sum += texture2D(oes_tex, vec2(tc.x - 1.0*blur, tc.y)) * 0.1945945946;\n  sum += texture2D(oes_tex, vec2(tc.x, tc.y)) * 0.2270270270;\n  sum += texture2D(oes_tex, vec2(tc.x + 1.0*blur, tc.y)) * 0.1945945946;\n  sum += texture2D(oes_tex, vec2(tc.x + 2.0*blur, tc.y)) * 0.1216216216;\n  sum += texture2D(oes_tex, vec2(tc.x + 3.0*blur, tc.y)) * 0.0540540541;\n  sum += texture2D(oes_tex, vec2(tc.x + 4.0*blur, tc.y)) * 0.0162162162;\n  gl_FragColor = vec4(sum.rgb, 1.0);\n}\n");
        this.shadergs1.useProgram();
        this.shadergs1.setVertexAttribArray("in_pos", 2, FULL_RECTANGLE_BUF);
        this.shadergs1.setVertexAttribArray("in_tc", 2, FULL_RECTANGLE_TEX_BUF);
        this.gs1TexMatrixLocation = this.shadergs1.getUniformLocation("texMatrix");
        this.gs1WidthLocation = this.shadergs1.getUniformLocation("width");
        this.gs1RadiusLocation = this.shadergs1.getUniformLocation("radius");
        this.gs1TextureId = GlUtil.generateTexture(3553);
        this.shadergs2 = new GlShader("varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n", "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D rgb_tex;\nuniform float height;\nuniform float radius;\n\nvoid main() {\n  vec4 sum = vec4(0.0);\n  vec2 tc = interp_tc;\n  float blur = radius/height;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y - 4.0*blur)) * 0.0162162162;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y - 3.0*blur)) * 0.0540540541;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y - 2.0*blur)) * 0.1216216216;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y - 1.0*blur)) * 0.1945945946;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y)) * 0.2270270270;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y + 1.0*blur)) * 0.1945945946;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y + 2.0*blur)) * 0.1216216216;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y + 3.0*blur)) * 0.0540540541;\n  sum += texture2D(rgb_tex, vec2(tc.x, tc.y + 4.0*blur)) * 0.0162162162;\n  gl_FragColor = vec4(sum.rgb, 1.0);\n}\n");
        this.shadergs2.useProgram();
        this.shadergs2.setVertexAttribArray("in_pos", 2, FULL_RECTANGLE_BUF);
        this.shadergs2.setVertexAttribArray("in_tc", 2, FULL_RECTANGLE_TEX_BUF);
        this.gs2TexMatrixLocation = this.shadergs2.getUniformLocation("texMatrix");
        this.gs2HeightLocation = this.shadergs2.getUniformLocation("height");
        this.gs2RadiusLocation = this.shadergs2.getUniformLocation("radius");
        int[] iArr = new int[2];
        GLES20.glGenFramebuffers(2, iArr, 0);
        this.gs1FrameBufferId = iArr[0];
        this.gs2FrameBufferId = iArr[1];
        GLES20.glGenRenderbuffers(2, iArr, 0);
        this.gs1RenderBufferId = iArr[0];
        this.gs2RenderBufferId = iArr[1];
    }

    public void convert(int i, int i2, int i3, int i4, float[] fArr) {
        this.shadergs1.useProgram();
        GLES20.glUniform1i(this.shadergs1.getUniformLocation("oes_tex"), 0);
        GLES20.glUniformMatrix4fv(this.gs1TexMatrixLocation, 1, false, fArr, 0);
        GLES20.glUniform1f(this.gs1WidthLocation, i2);
        float f = i4;
        GLES20.glUniform1f(this.gs1RadiusLocation, f);
        GLES20.glBindFramebuffer(36160, this.gs1FrameBufferId);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i);
        GLES20.glViewport(0, 0, i2, i3);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindTexture(36197, 0);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glBindTexture(3553, 0);
        this.shadergs2.useProgram();
        GLES20.glUniform1i(this.shadergs2.getUniformLocation("rgb_tex"), 1);
        GLES20.glUniformMatrix4fv(this.gs2TexMatrixLocation, 1, false, fArr, 0);
        GLES20.glUniform1f(this.gs2HeightLocation, i3);
        GLES20.glUniform1f(this.gs2RadiusLocation, f);
        GLES20.glBindFramebuffer(36160, this.gs2FrameBufferId);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.gs1TextureId);
        GLES20.glViewport(0, 0, i2, i3);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glBindTexture(3553, 0);
        GlUtil.checkNoGLES2Error("convert");
    }

    public void release() {
        this.threadChecker.checkIsOnValidThread();
        GLES20.glDeleteBuffers(4, new int[]{this.gs1FrameBufferId, this.gs1RenderBufferId, this.gs2FrameBufferId, this.gs2RenderBufferId}, 0);
        GLES20.glDeleteTextures(1, new int[]{this.gs1TextureId}, 0);
        this.shadergs1.release();
        this.shadergs2.release();
        this.released = true;
    }

    public void setParameters(int i, int i2, int i3) {
        this.rgbTextureId = i3;
        GLES20.glBindFramebuffer(36160, this.gs1FrameBufferId);
        GLES20.glBindTexture(3553, this.gs1TextureId);
        GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, FujifilmMakernoteDirectory.TAG_FILM_MODE, null);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glBindRenderbuffer(36161, this.gs1RenderBufferId);
        GLES20.glRenderbufferStorage(36161, 33189, i, i2);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.gs1TextureId, 0);
        GLES20.glFramebufferRenderbuffer(36160, 36096, 36161, this.gs1RenderBufferId);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindRenderbuffer(36161, 0);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glBindFramebuffer(36160, this.gs2FrameBufferId);
        GLES20.glBindTexture(3553, this.rgbTextureId);
        GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, FujifilmMakernoteDirectory.TAG_FILM_MODE, null);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glBindRenderbuffer(36161, this.gs2RenderBufferId);
        GLES20.glRenderbufferStorage(36161, 33189, i, i2);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.rgbTextureId, 0);
        GLES20.glFramebufferRenderbuffer(36160, 36096, 36161, this.gs2RenderBufferId);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindRenderbuffer(36161, 0);
        GLES20.glBindFramebuffer(36160, 0);
        GlUtil.checkNoGLES2Error("Initialize FBO parameters");
    }
}
