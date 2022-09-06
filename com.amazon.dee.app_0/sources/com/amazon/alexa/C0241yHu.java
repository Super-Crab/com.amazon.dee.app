package com.amazon.alexa;

import android.content.ComponentName;
import android.net.Uri;
import com.amazon.alexa.client.alexaservice.capabilities.legacy.LegacyFlagsAdapter;
import com.amazon.alexa.client.alexaservice.capabilities.v2.CapabilityAutoUpdateGsonTypeAdapter;
import com.amazon.alexa.client.alexaservice.capabilities.v2.CapabilityIsFollowingGsonTypeAdapter;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload.AlexaMediaPayload;
import com.amazon.alexa.client.alexaservice.networking.adapters.AlexaMediaPayloadAdapter;
import com.amazon.alexa.client.alexaservice.networking.adapters.AutoValueAdapterFactory;
import com.amazon.alexa.client.alexaservice.networking.adapters.CapabilityAdapter;
import com.amazon.alexa.client.alexaservice.networking.adapters.ComponentNameAdapter;
import com.amazon.alexa.client.alexaservice.networking.adapters.ComponentStateAdapter;
import com.amazon.alexa.client.alexaservice.networking.adapters.DateAdapter;
import com.amazon.alexa.client.alexaservice.networking.adapters.LocaleAdapter;
import com.amazon.alexa.client.alexaservice.networking.adapters.MessageAdapter;
import com.amazon.alexa.client.alexaservice.networking.adapters.MessageHeaderAdapter;
import com.amazon.alexa.client.alexaservice.networking.adapters.RawStringPayloadAdapter;
import com.amazon.alexa.client.alexaservice.networking.adapters.UriAdapter;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.CapabilityInterface;
import com.amazon.alexa.client.core.capabilities.CapabilityType;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.PackageName;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Date;
import java.util.Locale;
import javax.inject.Provider;
/* compiled from: NetworkingModule_ProvidesGsonFactory.java */
/* renamed from: com.amazon.alexa.yHu  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0241yHu implements Factory<Gson> {
    public final Provider<ComponentStateAdapter> BIo;
    public final Provider<MessageAdapter> zZm;

    public C0241yHu(Provider<MessageAdapter> provider, Provider<ComponentStateAdapter> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        Provider<MessageAdapter> provider = this.zZm;
        Provider<ComponentStateAdapter> provider2 = this.BIo;
        return (Gson) Preconditions.checkNotNull(new GsonBuilder().registerTypeAdapter(Uri.class, new UriAdapter()).registerTypeAdapter(Date.class, new DateAdapter()).registerTypeAdapter(Locale.class, new LocaleAdapter()).registerTypeHierarchyAdapter(Cta.class, new LegacyFlagsAdapter()).registerTypeHierarchyAdapter(ComponentName.class, new ComponentNameAdapter()).registerTypeHierarchyAdapter(Message.class, provider.mo10268get()).registerTypeHierarchyAdapter(ComponentState.class, provider2.mo10268get()).registerTypeHierarchyAdapter(Header.class, new MessageHeaderAdapter()).registerTypeHierarchyAdapter(DialogRequestIdentifier.class, DialogRequestIdentifier.getTypeAdapter()).registerTypeHierarchyAdapter(Namespace.class, Namespace.getTypeAdapter()).registerTypeHierarchyAdapter(Name.class, Name.getTypeAdapter()).registerTypeHierarchyAdapter(PackageName.class, PackageName.getTypeAdapter()).registerTypeHierarchyAdapter(RawStringPayload.class, new RawStringPayloadAdapter()).registerTypeHierarchyAdapter(AlexaMediaPayload.class, new AlexaMediaPayloadAdapter()).registerTypeHierarchyAdapter(Puy.class, Puy.zZm()).registerTypeHierarchyAdapter(C0176Pce.class, C0176Pce.zZm()).registerTypeHierarchyAdapter(pHD.class, pHD.zZm()).registerTypeHierarchyAdapter(xNT.class, xNT.zZm()).registerTypeHierarchyAdapter(Hir.class, Hir.zZm()).registerTypeHierarchyAdapter(FHI.class, FHI.zZm()).registerTypeHierarchyAdapter(GWl.class, GWl.zZm()).registerTypeHierarchyAdapter(AbstractC0188bKf.class, AbstractC0188bKf.zZm()).registerTypeHierarchyAdapter(zYH.class, zYH.zZm()).registerTypeHierarchyAdapter(ZYY.class, ZYY.zZm()).registerTypeHierarchyAdapter(CapabilityType.class, CapabilityType.getTypeAdapter()).registerTypeHierarchyAdapter(CapabilityInterface.class, CapabilityInterface.getTypeAdapter()).registerTypeHierarchyAdapter(dnp.class, dnp.zZm()).registerTypeHierarchyAdapter(Capability.class, new CapabilityAdapter()).registerTypeHierarchyAdapter(Csx.class, Csx.zZm()).registerTypeHierarchyAdapter(SFx.class, SFx.zZm()).registerTypeHierarchyAdapter(jVi.class, jVi.zZm()).registerTypeHierarchyAdapter(EPu.class, new CapabilityAutoUpdateGsonTypeAdapter()).registerTypeHierarchyAdapter(zjD.class, new CapabilityIsFollowingGsonTypeAdapter()).registerTypeHierarchyAdapter(CapabilityInterface.class, CapabilityInterface.getTypeAdapter()).registerTypeHierarchyAdapter(CapabilityType.class, CapabilityType.getTypeAdapter()).registerTypeHierarchyAdapter(eYr.class, eYr.zZm.zZm()).registerTypeAdapterFactory(AutoValueAdapterFactory.zZm()).create(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
