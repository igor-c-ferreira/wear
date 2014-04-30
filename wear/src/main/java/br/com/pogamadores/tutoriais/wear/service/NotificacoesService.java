package br.com.pogamadores.tutoriais.wear.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

public class NotificacoesService extends IntentService {
    private static final String NOTIFICATION_ACTION = "br.com.pogamadores.tutoriais.wear.services.action.NOTIFICATION";
    private static final String PARAM_TYPE = "br.com.pogamadores.tutoriais.wear.services.action.TYPE";

    public static void startActionForNotification(Context context, String type) {
        Intent intent = new Intent(context, NotificacoesService.class);
        intent.setAction(NOTIFICATION_ACTION);
        intent.putExtra(PARAM_TYPE, type);
        context.startService(intent);
    }

    public NotificacoesService() {
        super("NotificacoesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if(action.equals(NOTIFICATION_ACTION)) {
                String type = intent.getStringExtra(PARAM_TYPE);
                handleNotification(type);
            }
        }
    }

    private void handleNotification(String type) {

    }
}
