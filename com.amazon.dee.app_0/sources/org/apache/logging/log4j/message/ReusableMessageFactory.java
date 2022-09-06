package org.apache.logging.log4j.message;

import java.io.Serializable;
import org.apache.logging.log4j.util.PerformanceSensitive;
@PerformanceSensitive({"allocation"})
/* loaded from: classes4.dex */
public final class ReusableMessageFactory implements MessageFactory2, Serializable {
    private static final long serialVersionUID = -8970940216592525651L;
    public static final ReusableMessageFactory INSTANCE = new ReusableMessageFactory();
    private static ThreadLocal<ReusableParameterizedMessage> threadLocalParameterized = new ThreadLocal<>();
    private static ThreadLocal<ReusableSimpleMessage> threadLocalSimpleMessage = new ThreadLocal<>();
    private static ThreadLocal<ReusableObjectMessage> threadLocalObjectMessage = new ThreadLocal<>();

    private static ReusableObjectMessage getObject() {
        ReusableObjectMessage reusableObjectMessage = threadLocalObjectMessage.get();
        if (reusableObjectMessage == null) {
            ReusableObjectMessage reusableObjectMessage2 = new ReusableObjectMessage();
            threadLocalObjectMessage.set(reusableObjectMessage2);
            return reusableObjectMessage2;
        }
        return reusableObjectMessage;
    }

    private static ReusableParameterizedMessage getParameterized() {
        ReusableParameterizedMessage reusableParameterizedMessage = threadLocalParameterized.get();
        if (reusableParameterizedMessage == null) {
            reusableParameterizedMessage = new ReusableParameterizedMessage();
            threadLocalParameterized.set(reusableParameterizedMessage);
        }
        if (reusableParameterizedMessage.reserved) {
            reusableParameterizedMessage = new ReusableParameterizedMessage();
        }
        return reusableParameterizedMessage.reserve();
    }

    private static ReusableSimpleMessage getSimple() {
        ReusableSimpleMessage reusableSimpleMessage = threadLocalSimpleMessage.get();
        if (reusableSimpleMessage == null) {
            ReusableSimpleMessage reusableSimpleMessage2 = new ReusableSimpleMessage();
            threadLocalSimpleMessage.set(reusableSimpleMessage2);
            return reusableSimpleMessage2;
        }
        return reusableSimpleMessage;
    }

    public static void release(Message message) {
        if (message instanceof Clearable) {
            ((Clearable) message).clear();
        }
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(CharSequence charSequence) {
        ReusableSimpleMessage simple = getSimple();
        simple.set(charSequence);
        return simple;
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(String str, Object... objArr) {
        return getParameterized().set(str, objArr);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj) {
        return getParameterized().set(str, obj);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2) {
        return getParameterized().set(str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3) {
        return getParameterized().set(str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return getParameterized().set(str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(String str) {
        ReusableSimpleMessage simple = getSimple();
        simple.set(str);
        return simple;
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(Object obj) {
        ReusableObjectMessage object = getObject();
        object.set(obj);
        return object;
    }
}
