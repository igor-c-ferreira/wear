package br.com.pogamadores.tutoriais.wear.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificacoesReceiver extends BroadcastReceiver {
    public NotificacoesReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Informação recebida!", Toast.LENGTH_SHORT).show();
    }
}
