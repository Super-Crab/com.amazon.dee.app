package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.sync.DataSyncIncomingMessageHandler;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
@Module
/* loaded from: classes11.dex */
public class DataSyncModule {
    @AhfScope
    @Provides
    public DataSyncIncomingMessageHandler provideDataSyncIncomingMessageHandler() {
        return new DataSyncIncomingMessageHandler(new ArrayList());
    }
}
