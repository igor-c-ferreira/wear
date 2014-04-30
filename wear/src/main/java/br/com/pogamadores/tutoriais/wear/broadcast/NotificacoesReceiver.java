package br.com.pogamadores.tutoriais.wear.broadcast;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificacoesReceiver extends BroadcastReceiver {

    public static final String EXTRA_MENSAGEM =
            "br.com.pogamadores.tutoriais.wear.receiver.extra.EXTRA_MENSAGEM";
    public static final String EXTRA_RETORNO =
            "br.com.pogamadores.tutoriais.wear.receiver.extra.EXTRA_RETORNO";

    public static final String EXEMPLO_ACAO =
            "br.com.pogamadores.tutoriais.wear.receiver.action.EXEMPLO_ACAO";

    public static PendingIntent exemploPedingIntent(Context context, int messageResId) {
        Intent intent = new Intent(EXEMPLO_ACAO)
                .setClass(context, NotificacoesReceiver.class);
        intent.putExtra(EXTRA_MENSAGEM, context.getString(messageResId));
        return PendingIntent.getBroadcast(context, messageResId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public NotificacoesReceiver(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(EXEMPLO_ACAO)) {

            String mensagem = intent.getStringExtra(EXTRA_MENSAGEM);
            String retorno = intent.getStringExtra(EXTRA_RETORNO);

            if (retorno != null) {
                mensagem = mensagem + ": \"" + retorno + "\"";
            }

            if(mensagem != null && mensagem.trim().length() > 0)
                Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
        }
    }
}
