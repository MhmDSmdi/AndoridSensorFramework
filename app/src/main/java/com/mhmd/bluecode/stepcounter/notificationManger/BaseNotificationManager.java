package com.mhmd.bluecode.stepcounter.notificationManger;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import com.mhmd.bluecode.stepcounter.R;

import java.util.ArrayList;
import java.util.List;


public class BaseNotificationManager {

    private NotificationManager notificationManager;
    private NotificationListener listener;
    private List<Notification> notificationList;
    private Context mContext;

    public BaseNotificationManager(Context mContext) {
        this.mContext = mContext;
        notificationList = new ArrayList<>();
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public BaseNotificationManager(Context mContext, NotificationListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        notificationList = new ArrayList<>();
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void createNotificationChannel(int channelNameResource, int channelDescriptionResource, String channelID ,int importance) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelID, mContext.getString(channelNameResource), importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(R.color.colorAccent);
            notificationChannel.canShowBadge();
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void notifyNotification(Notification notification, int id) {
        if (listener != null)
            listener.onSendNotification();
        notificationList.add(notification);
        notificationManager.notify(id, notification);
    }
}