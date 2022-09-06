package com.amazon.alexa.sharing.repo.interfaces;

import com.amazon.alexa.sharing.api.exceptions.AlexaSharingException;
import com.amazon.alexa.sharing.repo.models.Result;
import com.amazon.alexa.sharing.repo.models.acms.announcement.Announcement;
import com.amazon.alexa.sharing.repo.models.acms.announcement.SendAudioAnnouncementInput;
/* loaded from: classes10.dex */
public interface IAnnouncement {
    Announcement[] getAllAnnouncements(String str) throws AlexaSharingException;

    void openCreateAnnouncement();

    Result sendAudioAnnouncement(SendAudioAnnouncementInput sendAudioAnnouncementInput, String str);

    Result sendTextAnnouncement(String str, String str2, String str3);
}
