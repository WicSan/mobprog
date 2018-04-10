package ch.hslu.mobprog.intentwigdet;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(final int appWidgetId : appWidgetIds) {
            final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget_provider);
            views.setTextViewText(R.id.appwidget_text, "My App Widget");
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
