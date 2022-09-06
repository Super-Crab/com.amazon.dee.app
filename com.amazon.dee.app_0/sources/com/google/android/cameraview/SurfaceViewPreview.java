package com.google.android.cameraview;

import android.content.Context;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import org.reactnative.camera.R;
/* loaded from: classes2.dex */
class SurfaceViewPreview extends PreviewImpl {
    final SurfaceView mSurfaceView;

    SurfaceViewPreview(Context context, ViewGroup viewGroup) {
        this.mSurfaceView = (SurfaceView) View.inflate(context, R.layout.surface_view, viewGroup).findViewById(R.id.surface_view);
        SurfaceHolder holder = this.mSurfaceView.getHolder();
        holder.setType(3);
        holder.addCallback(new SurfaceHolder.Callback() { // from class: com.google.android.cameraview.SurfaceViewPreview.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                SurfaceViewPreview.this.setSize(i2, i3);
                if (!ViewCompat.isInLayout(SurfaceViewPreview.this.mSurfaceView)) {
                    SurfaceViewPreview.this.dispatchSurfaceChanged();
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                SurfaceViewPreview.this.setSize(0, 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public Class getOutputClass() {
        return SurfaceHolder.class;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public Surface getSurface() {
        return getSurfaceHolder().getSurface();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public SurfaceHolder getSurfaceHolder() {
        return this.mSurfaceView.getHolder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public View getView() {
        return this.mSurfaceView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public boolean isReady() {
        return (getWidth() == 0 || getHeight() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public void setDisplayOrientation(int i) {
    }
}
