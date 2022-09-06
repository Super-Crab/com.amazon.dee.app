package com.amazon.alexa;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;
/* compiled from: AttachmentDataSource.java */
/* loaded from: classes.dex */
public class hVu implements DataSource {
    public static final String zZm = "hVu";
    public final shl BIo;
    public String jiA;
    public Aml zQM;
    public Uri zyO;

    /* compiled from: AttachmentDataSource.java */
    /* loaded from: classes.dex */
    public static class zZm implements DataSource.Factory {
        public final shl zZm;

        public zZm(shl shlVar) {
            this.zZm = shlVar;
        }

        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
        /* renamed from: createDataSource */
        public DataSource mo7468createDataSource() {
            return new hVu(this.zZm, null);
        }
    }

    public /* synthetic */ hVu(shl shlVar, GVF gvf) {
        this.BIo = shlVar;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void addTransferListener(TransferListener transferListener) {
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        Aml aml = this.zQM;
        if (aml != null) {
            this.BIo.BIo(aml.getAttachmentIdentifier());
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.zyO;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        this.zyO = dataSpec.uri;
        this.jiA = this.zyO.toString().substring(4);
        StringBuilder zZm2 = C0179Pya.zZm("Getting Attachment ");
        zZm2.append(this.jiA);
        zZm2.append(" from AttachmentStore");
        zZm2.toString();
        this.zQM = this.BIo.zQM(cIy.zZm(this.jiA));
        Aml aml = this.zQM;
        if (aml != null) {
            return aml.getInputStream().available();
        }
        throw new IllegalStateException("Attachment does not exist");
    }

    @Override // com.google.android.exoplayer2.upstream.DataReader
    public int read(byte[] bArr, int i, int i2) throws IOException {
        Aml aml = this.zQM;
        if (aml != null) {
            return aml.getInputStream().read(bArr, i, i2);
        }
        throw new IllegalStateException("Attachment is null");
    }
}
