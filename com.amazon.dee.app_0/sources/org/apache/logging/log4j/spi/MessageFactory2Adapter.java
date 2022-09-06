package org.apache.logging.log4j.spi;

import java.util.Objects;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.MessageFactory2;
import org.apache.logging.log4j.message.SimpleMessage;
/* loaded from: classes4.dex */
public class MessageFactory2Adapter implements MessageFactory2 {
    private final MessageFactory wrapped;

    public MessageFactory2Adapter(MessageFactory messageFactory) {
        this.wrapped = (MessageFactory) Objects.requireNonNull(messageFactory);
    }

    public MessageFactory getOriginal() {
        return this.wrapped;
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(CharSequence charSequence) {
        return new SimpleMessage(charSequence);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj) {
        return this.wrapped.newMessage(str, obj);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2) {
        return this.wrapped.newMessage(str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3) {
        return this.wrapped.newMessage(str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return this.wrapped.newMessage(str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return this.wrapped.newMessage(str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return this.wrapped.newMessage(str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return this.wrapped.newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return this.wrapped.newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return this.wrapped.newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return this.wrapped.newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(Object obj) {
        return this.wrapped.newMessage(obj);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(String str) {
        return this.wrapped.newMessage(str);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(String str, Object... objArr) {
        return this.wrapped.newMessage(str, objArr);
    }
}
