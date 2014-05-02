package br.com.pogamadores.tutoriais.wear.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.pogamadores.tutoriais.wear.R;
import br.com.pogamadores.tutoriais.wear.notificacao.NotificacaoBuilder;
import br.com.pogamadores.tutoriais.wear.service.NotificacoesService;

public class HomeFragment extends Fragment
{
    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        associarAcao(rootView.findViewById(R.id.btn_notificacao_simples), NotificacaoBuilder.NOTIFICACAO_SIMPLES);
        associarAcao(rootView.findViewById(R.id.btn_notificacao_texto), NotificacaoBuilder.NOTIFICACAO_BIG_TEXT);
        associarAcao(rootView.findViewById(R.id.btn_notificacao_imagem), NotificacaoBuilder.NOTIFICACAO_IMAGEM);
        associarAcao(rootView.findViewById(R.id.btn_notificacao_acao), NotificacaoBuilder.NOTIFICACAO_ACAO);
        associarAcao(rootView.findViewById(R.id.btn_notificacao_acao_aberta), NotificacaoBuilder.NOTIFICACAO_ACAO_ABERTA);

        return rootView;
    }

    protected void associarAcao(View view, final String tipo) {
        if(view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NotificacoesService.startActionForNotification(getActivity(),tipo);
                }
            });
        }
    }
}
