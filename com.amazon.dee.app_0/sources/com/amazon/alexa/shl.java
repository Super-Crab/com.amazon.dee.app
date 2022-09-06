package com.amazon.alexa;

import android.os.Build;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.attachments.FileDescriptorAttachment;
import com.amazon.alexa.client.alexaservice.audio.ScaledVolumeProcessor;
import com.amazon.alexa.client.alexaservice.audioprovider.AlexaAudioSource;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.comms.config.util.DeviceConfigConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AttachmentStore.java */
@Singleton
/* loaded from: classes.dex */
public class shl {
    public static final String zZm = "shl";
    public final Vyl JTe;
    public final DtB Qle;
    public final AlexaClientEventBus jiA;
    public final ScaledVolumeProcessor zyO;
    public final Map<cIy, Aml> BIo = new HashMap();
    public final Map<cIy, Condition> zQM = new HashMap();
    public final Lock LPk = new ReentrantLock();

    /* compiled from: AttachmentStore.java */
    /* loaded from: classes.dex */
    public enum zZm {
        Bufferred,
        SpeechmarkParsing
    }

    @Inject
    public shl(ScaledVolumeProcessor scaledVolumeProcessor, AlexaClientEventBus alexaClientEventBus, DtB dtB, Vyl vyl) {
        this.zyO = scaledVolumeProcessor;
        this.jiA = alexaClientEventBus;
        this.Qle = dtB;
        this.JTe = vyl;
    }

    public Aml BIo(Aml aml) {
        try {
            this.LPk.lock();
            if (HSA.BIo.equals(aml.getDataFormat())) {
                aml = new Jns(this.jiA, this.zyO, aml);
            }
            this.BIo.put(aml.getAttachmentIdentifier(), aml);
            return aml;
        } finally {
            this.LPk.unlock();
        }
    }

    @Nullable
    public Aml zQM(cIy ciy) {
        try {
            this.LPk.lock();
            StringBuilder sb = new StringBuilder();
            sb.append("Retrieving attachment ");
            sb.append(ciy.getValue());
            sb.toString();
            if (!this.BIo.containsKey(ciy)) {
                Condition newCondition = this.LPk.newCondition();
                this.zQM.put(ciy, newCondition);
                boolean z = false;
                try {
                    z = newCondition.await(DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS, TimeUnit.MILLISECONDS);
                    this.zQM.remove(ciy);
                } catch (InterruptedException unused) {
                    Log.e(zZm, "Thread Execution Interrupted");
                }
                if (!z) {
                    Log.e(zZm, "Timeout to retrieve attachment expired");
                }
            }
            return this.BIo.get(ciy);
        } finally {
            this.LPk.unlock();
        }
    }

    public Aml zZm() {
        return zZm(cIy.zZm(), zZm.Bufferred);
    }

    @Nullable
    public Aml zyO(cIy ciy) {
        try {
            this.LPk.lock();
            if (this.BIo.containsKey(ciy)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Retrieving attachment ");
                sb.append(ciy.getValue());
                sb.toString();
                return this.BIo.get(ciy);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Attachment does not exist  ");
            sb2.append(ciy.getValue());
            sb2.toString();
            this.LPk.unlock();
            return null;
        } finally {
            this.LPk.unlock();
        }
    }

    public Aml zZm(cIy ciy) {
        return zZm(ciy, zZm.SpeechmarkParsing);
    }

    public Aml zZm(ZZq zZq) {
        try {
            this.LPk.lock();
            FileDescriptorAttachment fileDescriptorAttachment = new FileDescriptorAttachment(zZq);
            this.BIo.put(fileDescriptorAttachment.getAttachmentIdentifier(), fileDescriptorAttachment);
            return fileDescriptorAttachment;
        } finally {
            this.LPk.unlock();
        }
    }

    public void BIo(@Nullable cIy ciy) {
        try {
            this.LPk.lock();
            if (ciy == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Deleting attachment ");
            sb.append(ciy.getValue());
            sb.toString();
            Aml remove = this.BIo.remove(ciy);
            Condition remove2 = this.zQM.remove(ciy);
            if (remove2 != null) {
                remove2.signal();
            }
            if (remove != null) {
                remove.close();
            }
        } finally {
            this.LPk.unlock();
        }
    }

    public Aml zZm(AlexaAudioSource alexaAudioSource) {
        return BIo(new FileDescriptorAttachment(alexaAudioSource));
    }

    public Aml zZm(Aml aml) {
        try {
            this.LPk.lock();
            Aml zZm2 = ((mdH) this.JTe).zZm(aml);
            this.BIo.put(zZm2.getAttachmentIdentifier(), zZm2);
            return zZm2;
        } finally {
            this.LPk.unlock();
        }
    }

    public void zQM() {
        try {
            this.LPk.lock();
            for (cIy ciy : this.BIo.keySet()) {
                this.BIo.get(ciy).close();
            }
            this.zQM.clear();
            this.BIo.clear();
        } finally {
            this.LPk.unlock();
        }
    }

    public final Aml zZm(cIy ciy, zZm zzm) {
        Aml kbv;
        try {
            this.LPk.lock();
            StringBuilder sb = new StringBuilder();
            sb.append("Creating ");
            sb.append(zzm.name());
            sb.append(" attachment for ");
            sb.append(ciy.getValue());
            sb.toString();
            if (zzm.ordinal() != 1) {
                kbv = new DvY(ciy);
            } else {
                int i = Build.VERSION.SDK_INT;
                kbv = new kBv(ciy, this.Qle);
            }
            this.BIo.put(ciy, kbv);
            Condition remove = this.zQM.remove(ciy);
            if (remove != null) {
                remove.signal();
            }
            return kbv;
        } finally {
            this.LPk.unlock();
        }
    }

    public void BIo() {
        try {
            this.LPk.lock();
            this.zQM.clear();
            this.BIo.clear();
        } finally {
            this.LPk.unlock();
        }
    }
}
