package br.com.pogamadores.tutoriais.wear.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import br.com.pogamadores.tutoriais.wear.notificacao.NotificacaoBuilder;

public class NotificacoesService extends IntentService {
    private static final String ACAO_NOTIFICACAO = "br.com.pogamadores.tutoriais.wear.services.action.ACAO_NOTIFICACAO";
    private static final String PARAMETRO_TIPO = "br.com.pogamadores.tutoriais.wear.services.action.PARAMETRO_TIPO";

    public static void startActionForNotification(Context context, String type) {
        Intent intent = new Intent(context, NotificacoesService.class);
        intent.setAction(ACAO_NOTIFICACAO);
        intent.putExtra(PARAMETRO_TIPO, type);
        context.startService(intent);
    }

    public NotificacoesService() {
        super("NotificacoesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if(action.equals(ACAO_NOTIFICACAO)) {
                String type = intent.getStringExtra(PARAMETRO_TIPO);
                disparararNotificacao(type);
            }
        }
    }

    private void disparararNotificacao(String type) {
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(0, NotificacaoBuilder.buildNotificacao(this,type));
    }
}
