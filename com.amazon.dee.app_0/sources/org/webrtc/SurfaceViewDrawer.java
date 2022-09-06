package org.webrtc;

import android.opengl.GLES20;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.facebook.react.uimanager.ViewProps;
import org.webrtc.GlDrawer;
import org.webrtc.WebRTCRendererShim;
/* loaded from: classes5.dex */
public class SurfaceViewDrawer extends GlDrawer {
    private static final float PRECISION_DELTA = 0.001f;
    private static final String SCALING_VERTEX_SHADER = "varying vec2 interp_tc;\nvarying vec2 orig_tc;\nprecision mediump float;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\nuniform float scaleFactor;\n\nvoid main() {\n    gl_Position = vec4(in_pos.x * scaleFactor, in_pos.y * scaleFactor, 0, 1);\n    orig_tc = in_tc.xy;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n";
    private long animationStartTime;
    private final float borderRadius;
    private float scaleFactor;
    private WebRTCRendererShim.Shape shape;
    private static final String FRAGMENT_SHADER_TEMPLATE = "#extension GL_OES_EGL_image_external : require\n#extension GL_OES_standard_derivatives : require\nprecision mediump float;\nvarying vec2 orig_tc;\nvarying vec2 interp_tc;\n\nuniform <<SHADER_TYPE>> oes_tex;\nuniform float radiusSquared;\nuniform float aspectRatio;\nuniform vec2 cornerCoords;\nuniform float alpha;\n\nvoid main() {\n  vec4 frag = texture2D(oes_tex, interp_tc);\n  vec2 fragCoords = vec2(orig_tc.x * aspectRatio, orig_tc.y);\n  vec2 clipFragCoords;\n  clipFragCoords.x  = abs(fragCoords.x - (aspectRatio * 0.5));\n  clipFragCoords.y  = abs(fragCoords.y - 0.5);\n  float distanceSquared = dot(clipFragCoords - cornerCoords, clipFragCoords - cornerCoords);\n  float delta = fwidth(distanceSquared);\n  bool isWithinCorner = clipFragCoords.x > cornerCoords.x && clipFragCoords.y > cornerCoords.y;\n  bool isOutsideBorderRadius = distanceSquared > radiusSquared;\n  bool isOpaque = !(isWithinCorner && isOutsideBorderRadius);\n  float targetAlpha = max(float(isOpaque), 1.0 - smoothstep(radiusSquared, radiusSquared + delta, distanceSquared)) * alpha;\n  gl_FragColor = frag * targetAlpha;\n}\n";
    private static final String SHADER_TYPE_KEY = "<<SHADER_TYPE>>";
    private static final String SHADER_TYPE_OES = "samplerExternalOES";
    @VisibleForTesting
    static final String ROUNDED_FRAGMENT_SHADER_OES = FRAGMENT_SHADER_TEMPLATE.replace(SHADER_TYPE_KEY, SHADER_TYPE_OES);
    private static final String SHADER_TYPE_RGB = "sampler2D";
    @VisibleForTesting
    static final String ROUNDED_FRAGMENT_SHADER_RGB = FRAGMENT_SHADER_TEMPLATE.replace(SHADER_TYPE_KEY, SHADER_TYPE_RGB);
    private float alpha = 1.0f;
    private long animationTimeInMillis = 0;

    @Deprecated
    /* loaded from: classes5.dex */
    public enum Shape {
        Circle,
        Rectangle,
        RoundedRectangle
    }

    public SurfaceViewDrawer(WebRTCRendererShim.Shape shape, float f) {
        this.scaleFactor = 1.0f;
        this.shape = shape;
        this.borderRadius = f;
        this.scaleFactor = 1.0f;
    }

    private void animate() {
        if (this.scaleFactor < 1.0f) {
            this.scaleFactor = Math.min(((float) (SystemClock.uptimeMillis() - getAnimationStartTime())) / getAnimationTimeInMillis(), 1.0f);
        }
    }

    private boolean equalToZero(float f) {
        return Math.abs(f) < PRECISION_DELTA;
    }

    private synchronized long getAnimationStartTime() {
        return this.animationStartTime;
    }

    private synchronized float getAnimationTimeInMillis() {
        return (float) this.animationTimeInMillis;
    }

    private float[] getCornerCoords(float f, float f2) {
        float f3 = (f2 * 0.5f) - f;
        float f4 = 0.5f - f;
        return (equalToZero(f3) || equalToZero(f4)) ? new float[]{0.0f, 0.0f} : new float[]{f3, f4};
    }

    private float getTargetRadius(float f, float f2) {
        WebRTCRendererShim.Shape shape = getShape();
        float min = shape == WebRTCRendererShim.Shape.Circle ? Math.min(f, f2) / 2.0f : 0.0f;
        if (shape == WebRTCRendererShim.Shape.RoundedRectangle) {
            min = this.borderRadius;
        }
        return min / f2;
    }

    private boolean nextDrawIsRectangle() {
        return (getShape() == WebRTCRendererShim.Shape.Rectangle) || (getShape() == WebRTCRendererShim.Shape.RoundedRectangle && equalToZero(this.borderRadius));
    }

    @Override // org.webrtc.GlDrawer
    protected String getFragmentShader() {
        return nextDrawIsRectangle() ? "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oes_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(oes_tex, interp_tc);\n}\n" : ROUNDED_FRAGMENT_SHADER_OES;
    }

    @Override // org.webrtc.GlDrawer
    protected String getRgbFragmentShader() {
        return nextDrawIsRectangle() ? "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D oes_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(oes_tex, interp_tc);\n}\n" : ROUNDED_FRAGMENT_SHADER_RGB;
    }

    public synchronized WebRTCRendererShim.Shape getShape() {
        return this.shape;
    }

    @Override // org.webrtc.GlDrawer
    protected String getVertexShader() {
        return SCALING_VERTEX_SHADER;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.webrtc.GlDrawer
    public GlDrawer.Shader prepareShader(String str, String str2, float[] fArr, float f, float f2) {
        float targetRadius = getTargetRadius(f, f2);
        animate();
        GlDrawer.Shader prepareShader = super.prepareShader(str, str2, fArr, f, f2);
        if (str2 == getYuvFragmentShader()) {
            return prepareShader;
        }
        GLES20.glUniform1f(prepareShader.glShader.getUniformLocation("scaleFactor"), this.scaleFactor);
        if (!nextDrawIsRectangle()) {
            float f3 = targetRadius - (getShape() == WebRTCRendererShim.Shape.Circle ? 1.0f / f2 : 0.0f);
            float f4 = f / f2;
            float[] cornerCoords = getCornerCoords(targetRadius, f4);
            GLES20.glUniform1f(prepareShader.glShader.getUniformLocation("radiusSquared"), f3 * f3);
            GLES20.glUniform2fv(prepareShader.glShader.getUniformLocation("cornerCoords"), 1, cornerCoords, 0);
            GLES20.glUniform1f(prepareShader.glShader.getUniformLocation(ViewProps.ASPECT_RATIO), f4);
            GLES20.glUniform1f(prepareShader.glShader.getUniformLocation("alpha"), this.alpha);
            GlUtil.checkNoGLES2Error("Initialize round fragment shader uniform values.");
        }
        return prepareShader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setAlpha(float f) {
        this.alpha = f;
    }

    public synchronized void setShape(WebRTCRendererShim.Shape shape, int i) {
        if (this.shape != shape || !equalToZero(getAnimationTimeInMillis() - i)) {
            this.shape = shape;
            this.scaleFactor = i > 0 ? 0.0f : 1.0f;
            this.animationTimeInMillis = i;
            this.animationStartTime = SystemClock.uptimeMillis();
        }
    }
}
