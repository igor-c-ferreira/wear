package br.com.pogamadores.tutoriais.wear.broadcast;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

/**
 * Simples implementação de um {@link android.content.BroadcastReceiver} para tratar as respostas
 * das notificações.
 */
public class NotificacoesReceiver extends BroadcastReceiver {

    public static final String EXTRA_MENSAGEM =
            "br.com.pogamadores.tutoriais.wear.receiver.extra.EXTRA_MENSAGEM";
    public static final String EXTRA_RETORNO =
            "br.com.pogamadores.tutoriais.wear.receiver.extra.EXTRA_RETORNO";
    public static final String EXEMPLO_ACAO =
            "br.com.pogamadores.tutoriais.wear.receiver.acao.EXEMPLO_ACAO";

    /**
     * Método que cria um {@link android.app.PendingIntent} com as informações necessárias
     * @param context           Contexto que executa a ação
     * @param messageResId      Id da mensagem de texto que será usada como resposta
     * @return  Instância do {@link android.app.PendingIntent} configurada
     */
    public static PendingIntent exemploPendingIntent(Context context, int messageResId) {
        Intent intent = new Intent(EXEMPLO_ACAO)
                .setClass(context, NotificacoesReceiver.class);
        intent.putExtra(EXTRA_MENSAGEM, context.getString(messageResId));
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(context, messageResId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    public NotificacoesReceiver(){super();}

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(EXEMPLO_ACAO)) {

            String mensagem = intent.getStringExtra(EXTRA_MENSAGEM);
            String retorno = intent.getStringExtra(EXTRA_RETORNO);

            if (retorno != null && mensagem != null) {
                mensagem = mensagem + ": \"" + retorno + "\"";
            } else if(retorno != null) {
                mensagem = retorno;
            }

            if(mensagem != null && mensagem.trim().length() > 0)
                Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();

            /**
             * Cancela todas as {@link android.app.Notification} relacionadas à aplicação.*/
            NotificationManagerCompat.from(context).cancelAll();
        }
    }
}
