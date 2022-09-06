package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import androidx.annotation.RequiresPermission;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.vision.Frame;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public class CameraSource {
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_FRONT = 1;
    private int facing;
    private int rotation;
    private Context zze;
    private final Object zzf;
    @GuardedBy("cameraLock")
    private Camera zzg;
    private Size zzh;
    private float zzi;
    private int zzj;
    private int zzk;
    private boolean zzl;
    private SurfaceTexture zzm;
    private boolean zzn;
    private Thread zzo;
    private zzb zzp;
    private Map<byte[], ByteBuffer> zzq;

    /* loaded from: classes2.dex */
    public static class Builder {
        private final Detector<?> zzr;
        private CameraSource zzs = new CameraSource();

        public Builder(Context context, Detector<?> detector) {
            if (context != null) {
                if (detector == null) {
                    throw new IllegalArgumentException("No detector supplied.");
                }
                this.zzr = detector;
                this.zzs.zze = context;
                return;
            }
            throw new IllegalArgumentException("No context supplied.");
        }

        public CameraSource build() {
            CameraSource cameraSource = this.zzs;
            cameraSource.getClass();
            cameraSource.zzp = new zzb(this.zzr);
            return this.zzs;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            this.zzs.zzl = z;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                this.zzs.facing = i;
                return this;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(27, "Invalid camera: ", i));
        }

        public Builder setRequestedFps(float f) {
            if (f > 0.0f) {
                this.zzs.zzi = f;
                return this;
            }
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid fps: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline28(45, "Invalid preview size: ", i, ReactProperties.HereMapMarker.X, i2));
            }
            this.zzs.zzj = i;
            this.zzs.zzk = i2;
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    /* loaded from: classes2.dex */
    public interface ShutterCallback {
        void onShutter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class zza implements Camera.PreviewCallback {
        private zza() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.zzp.zza(bArr, camera);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class zzb implements Runnable {
        private Detector<?> zzr;
        private long zzw;
        private ByteBuffer zzy;
        private long zzu = SystemClock.elapsedRealtime();
        private final Object lock = new Object();
        private boolean zzv = true;
        private int zzx = 0;

        zzb(Detector<?> detector) {
            this.zzr = detector;
        }

        @SuppressLint({"Assert"})
        final void release() {
            this.zzr.release();
            this.zzr = null;
        }

        @Override // java.lang.Runnable
        @SuppressLint({"InlinedApi"})
        public final void run() {
            Frame build;
            ByteBuffer byteBuffer;
            while (true) {
                synchronized (this.lock) {
                    while (this.zzv && this.zzy == null) {
                        try {
                            this.lock.wait();
                        } catch (InterruptedException unused) {
                            return;
                        }
                    }
                    if (!this.zzv) {
                        return;
                    }
                    build = new Frame.Builder().setImageData(this.zzy, CameraSource.this.zzh.getWidth(), CameraSource.this.zzh.getHeight(), 17).setId(this.zzx).setTimestampMillis(this.zzw).setRotation(CameraSource.this.rotation).build();
                    byteBuffer = this.zzy;
                    this.zzy = null;
                }
                try {
                    this.zzr.receiveFrame(build);
                } catch (Exception e) {
                    Log.e("CameraSource", "Exception thrown from receiver.", e);
                } finally {
                    CameraSource.this.zzg.addCallbackBuffer(byteBuffer.array());
                }
            }
        }

        final void setActive(boolean z) {
            synchronized (this.lock) {
                this.zzv = z;
                this.lock.notifyAll();
            }
        }

        final void zza(byte[] bArr, Camera camera) {
            synchronized (this.lock) {
                if (this.zzy != null) {
                    camera.addCallbackBuffer(this.zzy.array());
                    this.zzy = null;
                }
                if (!CameraSource.this.zzq.containsKey(bArr)) {
                    return;
                }
                this.zzw = SystemClock.elapsedRealtime() - this.zzu;
                this.zzx++;
                this.zzy = (ByteBuffer) CameraSource.this.zzq.get(bArr);
                this.lock.notifyAll();
            }
        }
    }

    /* loaded from: classes2.dex */
    class zzc implements Camera.PictureCallback {
        private PictureCallback zzz;

        private zzc() {
        }

        @Override // android.hardware.Camera.PictureCallback
        public final void onPictureTaken(byte[] bArr, Camera camera) {
            PictureCallback pictureCallback = this.zzz;
            if (pictureCallback != null) {
                pictureCallback.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.zzf) {
                if (CameraSource.this.zzg != null) {
                    CameraSource.this.zzg.startPreview();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    static class zzd implements Camera.ShutterCallback {
        private ShutterCallback zzaa;

        private zzd() {
        }

        @Override // android.hardware.Camera.ShutterCallback
        public final void onShutter() {
            ShutterCallback shutterCallback = this.zzaa;
            if (shutterCallback != null) {
                shutterCallback.onShutter();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class zze {
        private Size zzab;
        private Size zzac;

        public zze(Camera.Size size, @Nullable Camera.Size size2) {
            this.zzab = new Size(size.width, size.height);
            if (size2 != null) {
                this.zzac = new Size(size2.width, size2.height);
            }
        }

        public final Size zzb() {
            return this.zzab;
        }

        @Nullable
        public final Size zzc() {
            return this.zzac;
        }
    }

    private CameraSource() {
        this.zzf = new Object();
        this.facing = 0;
        this.zzi = 30.0f;
        this.zzj = 1024;
        this.zzk = 768;
        this.zzl = false;
        this.zzq = new HashMap();
    }

    @SuppressLint({"InlinedApi"})
    private final Camera zza() throws IOException {
        int i;
        int i2;
        int i3 = this.facing;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i5 >= Camera.getNumberOfCameras()) {
                i5 = -1;
                break;
            }
            Camera.getCameraInfo(i5, cameraInfo);
            if (cameraInfo.facing == i3) {
                break;
            }
            i5++;
        }
        if (i5 != -1) {
            Camera open = Camera.open(i5);
            int i6 = this.zzj;
            int i7 = this.zzk;
            Camera.Parameters parameters = open.getParameters();
            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
            ArrayList arrayList = new ArrayList();
            for (Camera.Size size : supportedPreviewSizes) {
                float f = size.width / size.height;
                Iterator<Camera.Size> it2 = supportedPictureSizes.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Camera.Size next = it2.next();
                        if (Math.abs(f - (next.width / next.height)) < 0.01f) {
                            arrayList.add(new zze(size, next));
                            break;
                        }
                    }
                }
            }
            if (arrayList.size() == 0) {
                Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
                for (Camera.Size size2 : supportedPreviewSizes) {
                    arrayList.add(new zze(size2, null));
                }
            }
            int size3 = arrayList.size();
            int i8 = 0;
            int i9 = Integer.MAX_VALUE;
            zze zzeVar = null;
            while (i8 < size3) {
                Object obj = arrayList.get(i8);
                i8++;
                zze zzeVar2 = (zze) obj;
                Size zzb2 = zzeVar2.zzb();
                int abs = Math.abs(zzb2.getHeight() - i7) + Math.abs(zzb2.getWidth() - i6);
                if (abs < i9) {
                    zzeVar = zzeVar2;
                    i9 = abs;
                }
            }
            if (zzeVar == null) {
                throw new IOException("Could not find suitable preview size.");
            }
            Size zzc2 = zzeVar.zzc();
            this.zzh = zzeVar.zzb();
            int i10 = (int) (this.zzi * 1000.0f);
            int i11 = Integer.MAX_VALUE;
            int[] iArr = null;
            for (int[] iArr2 : open.getParameters().getSupportedPreviewFpsRange()) {
                int abs2 = Math.abs(i10 - iArr2[1]) + Math.abs(i10 - iArr2[0]);
                if (abs2 < i11) {
                    iArr = iArr2;
                    i11 = abs2;
                }
            }
            if (iArr == null) {
                throw new IOException("Could not find suitable preview frames per second range.");
            }
            Camera.Parameters parameters2 = open.getParameters();
            if (zzc2 != null) {
                parameters2.setPictureSize(zzc2.getWidth(), zzc2.getHeight());
            }
            parameters2.setPreviewSize(this.zzh.getWidth(), this.zzh.getHeight());
            parameters2.setPreviewFpsRange(iArr[0], iArr[1]);
            parameters2.setPreviewFormat(17);
            int rotation = ((WindowManager) this.zze.getSystemService("window")).getDefaultDisplay().getRotation();
            if (rotation != 0) {
                if (rotation == 1) {
                    i4 = 90;
                } else if (rotation == 2) {
                    i4 = 180;
                } else if (rotation != 3) {
                    StringBuilder sb = new StringBuilder(31);
                    sb.append("Bad rotation value: ");
                    sb.append(rotation);
                    Log.e("CameraSource", sb.toString());
                } else {
                    i4 = 270;
                }
            }
            Camera.CameraInfo cameraInfo2 = new Camera.CameraInfo();
            Camera.getCameraInfo(i5, cameraInfo2);
            int i12 = cameraInfo2.facing;
            int i13 = cameraInfo2.orientation;
            if (i12 == 1) {
                i = (i13 + i4) % 360;
                i2 = (360 - i) % 360;
            } else {
                i = ((i13 - i4) + 360) % 360;
                i2 = i;
            }
            this.rotation = i / 90;
            open.setDisplayOrientation(i2);
            parameters2.setRotation(i);
            if (this.zzl) {
                if (parameters2.getSupportedFocusModes().contains("continuous-video")) {
                    parameters2.setFocusMode("continuous-video");
                } else {
                    Log.i("CameraSource", "Camera auto focus is not supported on this device.");
                }
            }
            open.setParameters(parameters2);
            open.setPreviewCallbackWithBuffer(new zza());
            open.addCallbackBuffer(zza(this.zzh));
            open.addCallbackBuffer(zza(this.zzh));
            open.addCallbackBuffer(zza(this.zzh));
            open.addCallbackBuffer(zza(this.zzh));
            return open;
        }
        throw new IOException("Could not find requested camera.");
    }

    @SuppressLint({"InlinedApi"})
    private final byte[] zza(Size size) {
        byte[] bArr = new byte[((int) Math.ceil(((size.getWidth() * size.getHeight()) * ImageFormat.getBitsPerPixel(17)) / 8.0d)) + 1];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.zzq.put(bArr, wrap);
        return bArr;
    }

    public int getCameraFacing() {
        return this.facing;
    }

    public Size getPreviewSize() {
        return this.zzh;
    }

    public void release() {
        synchronized (this.zzf) {
            stop();
            this.zzp.release();
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start() throws IOException {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                return this;
            }
            this.zzg = zza();
            this.zzm = new SurfaceTexture(100);
            this.zzg.setPreviewTexture(this.zzm);
            this.zzn = true;
            this.zzg.startPreview();
            this.zzo = new Thread(this.zzp);
            this.zzp.setActive(true);
            this.zzo.start();
            return this;
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                return this;
            }
            this.zzg = zza();
            this.zzg.setPreviewDisplay(surfaceHolder);
            this.zzg.startPreview();
            this.zzo = new Thread(this.zzp);
            this.zzp.setActive(true);
            this.zzo.start();
            this.zzn = false;
            return this;
        }
    }

    public void stop() {
        synchronized (this.zzf) {
            this.zzp.setActive(false);
            if (this.zzo != null) {
                try {
                    this.zzo.join();
                } catch (InterruptedException unused) {
                }
                this.zzo = null;
            }
            if (this.zzg != null) {
                this.zzg.stopPreview();
                this.zzg.setPreviewCallbackWithBuffer(null);
                try {
                    if (this.zzn) {
                        this.zzg.setPreviewTexture(null);
                    } else {
                        this.zzg.setPreviewDisplay(null);
                    }
                } catch (Exception e) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 32);
                    sb.append("Failed to clear camera preview: ");
                    sb.append(valueOf);
                    Log.e("CameraSource", sb.toString());
                }
                this.zzg.release();
                this.zzg = null;
            }
            this.zzq.clear();
        }
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                zzd zzdVar = new zzd();
                zzdVar.zzaa = shutterCallback;
                zzc zzcVar = new zzc();
                zzcVar.zzz = pictureCallback;
                this.zzg.takePicture(zzdVar, null, null, zzcVar);
            }
        }
    }
}
