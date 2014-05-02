package br.com.pogamadores.tutoriais.wear.notificacao;

import android.app.Notification;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.preview.support.wearable.notifications.RemoteInput;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;

import br.com.pogamadores.tutoriais.wear.R;
import br.com.pogamadores.tutoriais.wear.broadcast.NotificacoesReceiver;

public class NotificacaoBuilder
{
    public static final String NOTIFICACAO_SIMPLES = "br.com.pogamadores.tutoriais.wear.notificacao.tipo.NOTIFICACAO_SIMPLES";
    public static final String NOTIFICACAO_BIG_TEXT = "br.com.pogamadores.tutoriais.wear.notificacao.tipo.NOTIFICACAO_BIG_TEXT";
    public static final String NOTIFICACAO_MULTIPLAS_PAGINAS = "br.com.pogamadores.tutoriais.wear.notificacao.tipo.NOTIFICACAO_MULTIPLAS_PAGINAS";
    public static final String NOTIFICACAO_INBOX = "br.com.pogamadores.tutoriais.wear.notificacao.tipo.NOTIFICACAO_INBOX";
    public static final String NOTIFICACAO_IMAGEM = "br.com.pogamadores.tutoriais.wear.notificacao.tipo.NOTIFICACAO_IMAGEM";
    public static final String NOTIFICACAO_ACAO = "br.com.pogamadores.tutoriais.wear.notificacao.tipo.NOTIFICACAO_ACAO";
    public static final String NOTIFICACAO_ACAO_ABERTA = "br.com.pogamadores.tutoriais.wear.notificacao.tipo.NOTIFICACAO_ACAO_ABERTA";

    public static Notification buildNotificacao(Context context, String type) {
        Notification notification;
        switch (type) {
            case NOTIFICACAO_SIMPLES:
                notification = buildNotificacaoSimples(context);
                break;
            case NOTIFICACAO_BIG_TEXT:
                notification = buildNotificacaoBigText(context);
                break;
            case NOTIFICACAO_MULTIPLAS_PAGINAS:
                notification = buildNotificacaoMultiplasPaginas(context);
                break;
            case NOTIFICACAO_INBOX:
                notification = buildNotificacaoInbox(context);
                break;
            case NOTIFICACAO_IMAGEM:
                notification = buildNotificacaoImagem(context);
                break;
            case NOTIFICACAO_ACAO:
                notification = buildNotificacaoAcao(context);
                break;
            case NOTIFICACAO_ACAO_ABERTA:
                notification = buildNotificacaoAcaoAberta(context);
                break;
            default:
                notification = null;
                break;
        }
        return notification;
    }

    private static Notification buildNotificacaoInbox(Context context) {
        NotificationCompat.Builder builder = construirBuilderSimples(context);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.addLine(context.getString(R.string.primeiro_item));
        style.addLine(context.getString(R.string.segundo_item));
        style.setBigContentTitle(context.getString(R.string.titulo_notificacao));
        style.setSummaryText(context.getString(R.string.texto_exemplo));

        builder.setStyle(style);

        return finalizarNotificacao(builder, null, null);
    }

    private static Notification buildNotificacaoMultiplasPaginas(Context context) {
        NotificationCompat.Builder builder = construirBuilderSimples(context);

        Notification segundaPagina = new NotificationCompat.Builder(context)
                .setContentTitle(context.getString(R.string.titulo_notificacao))
                .setContentText(context.getString(R.string.primeiro_item))
                .build();

        Notification terceiraPagina = new NotificationCompat.Builder(context)
                .setContentTitle(context.getString(R.string.titulo_notificacao))
                .setContentText(context.getString(R.string.segundo_item))
                .build();

        return finalizarNotificacao(builder, null, new Notification[]{segundaPagina, terceiraPagina});
    }

    protected static NotificationCompat.Builder construirBuilderSimples(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentText(context.getResources().getString(R.string.texto_exemplo))
                .setContentTitle(context.getResources().getString(R.string.titulo_notificacao))
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher))
                .setContentIntent(NotificacoesReceiver.exemploPedingIntent(context, R.string.mensagem_retorno))
                .setDeleteIntent(NotificacoesReceiver.exemploPedingIntent(context, R.string.mensagem_exclusao));
        return builder;
    }

    protected static Notification finalizarNotificacao(NotificationCompat.Builder builder, WearableNotifications.Action acao, Notification[] paginas) {
        WearableNotifications.Builder wearBuilder = new WearableNotifications.Builder(builder);
        wearBuilder.setLocalOnly(false);
        if(acao != null) {
            wearBuilder.addAction(acao);
        }

        if(paginas != null) {
            for(Notification pagina : paginas) {
                wearBuilder.addPage(pagina);
            }
        }

        Notification notification = wearBuilder.build();

        if(notification != null) {
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            notification.defaults = Notification.DEFAULT_ALL;
        }
        return notification;
    }

    protected static Notification buildNotificacaoSimples(Context context) {
        NotificationCompat.Builder builder = construirBuilderSimples(context);
        return finalizarNotificacao(builder, null, null);
    }

    protected static Notification buildNotificacaoBigText(Context context) {
        NotificationCompat.Builder builder = construirBuilderSimples(context);

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.setBigContentTitle(context.getString(R.string.titulo_notificacao));
        style.setSummaryText(context.getString(R.string.texto_exemplo));
        style.bigText(context.getString(R.string.texto_grande));

        builder.setStyle(style);

        return finalizarNotificacao(builder, null, null);
    }

    protected static Notification buildNotificacaoImagem(Context context) {
        NotificationCompat.Builder builder = construirBuilderSimples(context);

        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_launcher));
        style.setBigContentTitle(context.getString(R.string.titulo_notificacao));
        style.setSummaryText(context.getString(R.string.texto_exemplo));

        builder.setStyle(style);

        return finalizarNotificacao(builder, null, null);
    }

    protected static Notification buildNotificacaoAcao(Context context) {
        NotificationCompat.Builder builder = construirBuilderSimples(context);

        RemoteInput input = new RemoteInput.Builder(NotificacoesReceiver.EXTRA_RETORNO)
                .setLabel(context.getString(R.string.titulo_acao))
                .setChoices(new String[] {context.getString(R.string.sim),
                        context.getString(R.string.nao)})
                .build();

        WearableNotifications.Action acao = new WearableNotifications.Action.Builder(R.drawable.ic_full_reply,
                context.getString(R.string.exemplo_acao),
                NotificacoesReceiver.exemploPedingIntent(context,R.string.exemplo_acao))
                .addRemoteInput(input)
                .build();

        return finalizarNotificacao(builder, acao, null);
    }

    protected static Notification buildNotificacaoAcaoAberta(Context context) {
        NotificationCompat.Builder builder = construirBuilderSimples(context);

        RemoteInput entrada = new RemoteInput.Builder(NotificacoesReceiver.EXTRA_RETORNO)
                .setLabel(context.getString(R.string.titulo_acao))
                .build();

        WearableNotifications.Action acao = new WearableNotifications.Action.Builder(R.drawable.ic_full_reply,
                context.getString(R.string.exemplo_acao),
                NotificacoesReceiver.exemploPedingIntent(context,R.string.exemplo_acao))
                .addRemoteInput(entrada)
                .build();

        return finalizarNotificacao(builder, acao, null);
    }

}
