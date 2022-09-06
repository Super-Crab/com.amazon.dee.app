package com.amazon.alexa.accessory.capabilities.central;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Central;
import com.amazon.alexa.accessory.repositories.central.CentralSupplier;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
/* loaded from: classes.dex */
public final class CentralCapability extends AccessoryCapability {
    private final CentralSupplier centralSupplier;
    private ControlStream stream;

    public CentralCapability(CentralSupplier centralSupplier) {
        Preconditions.notNull(centralSupplier, "centralSupplier");
        this.centralSupplier = centralSupplier;
    }

    private ControlMessageHandler<Central.GetCentralInformation> getCentralInformationHandler() {
        final Central.CentralInformation mo10084build = Central.CentralInformation.newBuilder().setName(this.centralSupplier.getName()).setPlatform(Central.Platform.ANDROID).mo10084build();
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.central.-$$Lambda$CentralCapability$qjsi8mzLH-LECpy6zxkJsXy0Yic
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                Central.GetCentralInformation getCentralInformation = (Central.GetCentralInformation) obj;
                controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_CENTRAL_INFORMATION).setResponse(Accessories.Response.newBuilder().setCentralInformation(Central.CentralInformation.this).mo10084build()).mo10084build()));
            }
        };
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.GET_CENTRAL_INFORMATION, getCentralInformationHandler());
        accessoryDescriptor.add(this.stream);
    }
}
