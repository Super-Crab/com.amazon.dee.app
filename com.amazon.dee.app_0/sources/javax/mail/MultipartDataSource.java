package javax.mail;

import javax.activation.DataSource;
/* loaded from: classes3.dex */
public interface MultipartDataSource extends DataSource {
    BodyPart getBodyPart(int i) throws MessagingException;

    int getCount();
}
