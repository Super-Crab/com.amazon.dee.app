package amazon.communication;
/* loaded from: classes.dex */
public class MessageHandlerRegistrar {
    public MessageHandlerRegistrar(MessageHandler messageHandler, ICommunicationManager iCommunicationManager, int i) throws RegistrationFailedException {
        iCommunicationManager.registerMessageHandler(i, messageHandler);
    }
}
