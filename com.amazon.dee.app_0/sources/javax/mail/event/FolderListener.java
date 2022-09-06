package javax.mail.event;

import java.util.EventListener;
/* loaded from: classes3.dex */
public interface FolderListener extends EventListener {
    void folderCreated(FolderEvent folderEvent);

    void folderDeleted(FolderEvent folderEvent);

    void folderRenamed(FolderEvent folderEvent);
}
