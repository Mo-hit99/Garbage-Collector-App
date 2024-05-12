package com.project.garbagecollectoruser.Common;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.maps.model.Marker;
import com.project.garbagecollectoruser.Model.DriverGeoModel;
import com.project.garbagecollectoruser.Model.RiderModel;
import com.project.garbagecollectoruser.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Common {

    public static final String RIDER_INFO_REFERENCE = "Riders";
    public static final String TOKEN_REFERENCE = "Token";
    public static final String DRIVERS_LOCATION_REFERENCE = "DriversLocation"; // Same as Driver app
    public static final String DRIVER_INFO_REFERENCE = "DriverInfo";
    public static RiderModel currentRider;

    public static final String NOTI_TITLE = "title";
    public static final String NOTI_CONTENT = "body";
    public static Set<DriverGeoModel> driversFound = new HashSet<DriverGeoModel>();
    public static HashMap<String, Marker> markerList = new HashMap<>();

    public static String buildWelcomeMessage() {
        if(Common.currentRider != null) {
            return new StringBuilder("Welcome ")
                    .append(Common.currentRider.getFirstName())
                    .append(" ")
                    .append(Common.currentRider.getLastName()).toString();
        } else {
            return "";
        }
    }

    public static void showNotification(Context context, int id, String title, String body, Intent intent) {
        PendingIntent pendingIntent = null;
        if(intent != null) {
            pendingIntent = PendingIntent.getActivity(context,id,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        }
        String NOTIFICATION_CHANNEL_ID = "garbage_collector";
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Garbage Collector",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Garbage Collector");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.drawable.baseline_directions_car_24)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.baseline_directions_car_24));
        if(pendingIntent != null) {
            builder.setContentIntent(pendingIntent);
        }
        Notification notification = builder.build();
        notificationManager.notify(id,notification);
    }

    public static String buildName(String firstName, String lastName) {
        return new StringBuilder(firstName).append(" ").append(lastName).toString();
    }

    public static void setWelcomeMessage(TextView txt_welcome) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(hour >= 1 && hour <= 12) {
            txt_welcome.setText(new StringBuilder("Good Morning."));
        } else if(hour >= 13 && hour <= 17)
            txt_welcome.setText(new StringBuilder("Good Afternoon."));
        else
            txt_welcome.setText(new StringBuilder("Good Evening."));
    }
}
