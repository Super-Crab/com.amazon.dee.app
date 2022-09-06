package com.amazon.alexa.accessory.repositories.sidewalk;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.io.Buffer;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.repositories.sidewalk.SidewalkProducer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.io.IOException;
/* loaded from: classes6.dex */
public class MemorySidewalkRepository extends BaseProducer<SidewalkProducer.ActionHandler> implements SidewalkRepository, SidewalkProducer, SidewalkProvider {
    private static final int SIZE = 3072;
    private static final String TAG = "MemorySidewalkRepository:";
    private MemorySidewalkSink sidewalkSink;
    private final Buffer sidewalkSource = new Buffer(SIZE);
    private boolean disposed = false;
    private boolean initialized = false;

    @Override // com.amazon.alexa.accessory.repositories.sidewalk.SidewalkProvider
    public void dispose() {
        if (this.disposed) {
            Logger.d("%s repository has already been disposed", TAG);
            return;
        }
        Logger.d("%s Disposing repository...", TAG);
        this.disposed = true;
        try {
            if (this.sidewalkSource != null) {
                this.sidewalkSource.close();
            }
            if (this.sidewalkSink == null) {
                return;
            }
            this.sidewalkSink.close();
        } catch (IOException e) {
            Logger.e("%s Error in closing sink and source", TAG, e);
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.sidewalk.SidewalkRepository
    public Single<Source> getInputStream() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.sidewalk.-$$Lambda$MemorySidewalkRepository$FiW9-C8bWBlankLzseCSC0zkOEs
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                MemorySidewalkRepository.this.lambda$getInputStream$0$MemorySidewalkRepository(singleEmitter);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.sidewalk.SidewalkRepository
    public Single<Sink> getOutputStream() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.sidewalk.-$$Lambda$MemorySidewalkRepository$XszPytlHjKLN_1bIVQyRbP6SP1Q
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                MemorySidewalkRepository.this.lambda$getOutputStream$1$MemorySidewalkRepository(singleEmitter);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.sidewalk.SidewalkProvider
    public void initialize() {
        Logger.d("%s Initializing repository...", TAG);
        this.initialized = true;
        this.sidewalkSink = new MemorySidewalkSink(getHandler());
    }

    public /* synthetic */ void lambda$getInputStream$0$MemorySidewalkRepository(SingleEmitter singleEmitter) throws Throwable {
        if (this.disposed) {
            Logger.e("%s Unable to get input stream as repository is disposed", TAG);
            singleEmitter.onError(new IllegalStateException("Sidewalk repository has been disposed"));
            return;
        }
        Logger.d("%s Providing input stream...", TAG);
        singleEmitter.onSuccess(this.sidewalkSource);
    }

    public /* synthetic */ void lambda$getOutputStream$1$MemorySidewalkRepository(SingleEmitter singleEmitter) throws Throwable {
        if (this.disposed) {
            Logger.e("%s Unable to get output stream as repository is disposed", TAG);
            singleEmitter.onError(new IllegalStateException("Sidewalk repository has been disposed"));
        } else if (!this.initialized) {
            Logger.e("%s Unable to get output stream as capability is not initialized", TAG);
            singleEmitter.onError(new IllegalStateException("Sidewalk capability is not initialized"));
        } else {
            Logger.d("%s Providing output stream...", TAG);
            singleEmitter.onSuccess(this.sidewalkSink);
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.sidewalk.SidewalkProvider
    public void provideData(SizedSource sizedSource) {
        try {
            Logger.d("%s Received %d bytes data from accessory", TAG, Integer.valueOf(sizedSource.size()));
            IOUtils.transfer(sizedSource, this.sidewalkSource);
            Logger.d("%s Transferred %d bytes data to sink successfully", TAG, Integer.valueOf(sizedSource.size()));
        } catch (IOException e) {
            Logger.e("%s Error in transferring data from accessory to sink", TAG, e);
        }
    }
}
