package org.apache.commons.fileupload.servlet;

import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.io.FileCleaningTracker;
/* loaded from: classes4.dex */
public class FileCleanerCleanup implements ServletContextListener {
    public static final String FILE_CLEANING_TRACKER_ATTRIBUTE = GeneratedOutlineSupport1.outline40(FileCleanerCleanup.class, new StringBuilder(), ".FileCleaningTracker");

    public static FileCleaningTracker getFileCleaningTracker(ServletContext servletContext) {
        return (FileCleaningTracker) servletContext.getAttribute(FILE_CLEANING_TRACKER_ATTRIBUTE);
    }

    public static void setFileCleaningTracker(ServletContext servletContext, FileCleaningTracker fileCleaningTracker) {
        servletContext.setAttribute(FILE_CLEANING_TRACKER_ATTRIBUTE, fileCleaningTracker);
    }

    @Override // javax.servlet.ServletContextListener
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        getFileCleaningTracker(servletContextEvent.getServletContext()).exitWhenFinished();
    }

    @Override // javax.servlet.ServletContextListener
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        setFileCleaningTracker(servletContextEvent.getServletContext(), new FileCleaningTracker());
    }
}
