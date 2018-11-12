package com.example.deepaks.krishiseva.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.util.PreferenceUtils;
import com.example.deepaks.krishiseva.view.dashboard.activity.DashboardActivity;

/**
 * Implementation of App Widget functionality.
 */
public class ElectricityStatusWidget extends AppWidgetProvider {

    public static final String POWER_WIDGET_DATA_KEY = "POWER_WIDGET_DATA_KEY";

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, String ingredientText) {

        CharSequence widgetText = ingredientText;
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.electricity_status_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        Intent intent = new Intent(context, DashboardActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent
                , 0);
        views.setOnClickPendingIntent(R.id.id_launch, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId
                    , PreferenceUtils.getString(POWER_WIDGET_DATA_KEY));
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

