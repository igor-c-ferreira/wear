package br.com.pogamadores.tutoriais.wear.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.preview.support.v4.app.NotificationManagerCompat;

import br.com.pogamadores.tutoriais.wear.notificacao.NotificacaoBuilder;

/**
 * <p>NotificacoesService é um {@link android.app.IntentService} que, neste exemplo,
 * será responsável por disparar as notificações.</p>
 * <p>O uso dessa classe é feita atavés do métodos {@link NotificacoesService#startActionForNotification(android.content.Context, String)}</p>
 */
public class NotificacoesService extends IntentService {

    private static final String ACAO_NOTIFICACAO =
            "br.com.pogamadores.tutoriais.wear.services.acao.ACAO_NOTIFICACAO";
    private static final String PARAMETRO_TIPO =
            "br.com.pogamadores.tutoriais.wear.services.acao.PARAMETRO_TIPO";

    /**
     * Este método é responsável por inicializar o Serviço com todas as opções necessárias
     * @param context   Contexto usado para chamar o serviço
     * @param tipo      Tipo da notificação que será criada
     */
    public static void startActionForNotification(Context context, String tipo) {
        Intent intent = new Intent(context, NotificacoesService.class);
        intent.setAction(ACAO_NOTIFICACAO);
        intent.putExtra(PARAMETRO_TIPO, tipo);
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
                String tipo = intent.getStringExtra(PARAMETRO_TIPO);

                /**
                 * Pega a instância do {@link android.preview.support.v4.app.NotificationManagerCompat}
                 * que será responsável por disparar as notificações.*/
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

                /**
                 * Dispara a notificação criada pelo builder {@link br.com.pogamadores.tutoriais.wear.notificacao.NotificacaoBuilder}*/
                managerCompat.notify(0, NotificacaoBuilder.buildNotificacao(this,tipo));
            }
        }
    }

}
