package com.github.welingtonveiga.mensageiro.view.timeline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;

import com.github.welingtonveiga.mensageiro.R;
import com.github.welingtonveiga.mensageiro.domain.model.Message;
import com.github.welingtonveiga.mensageiro.domain.services.MessageService;

import java.util.List;

public class TimelineFragment  extends ListFragment {

    private static final String TAG = TimelineFragment.class.getName();

    private final MessageService messageService = new MessageService();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 1 - Exibe diálogo de carregamento.
        final ProgressDialog loader =  ProgressDialog.show(getActivity(), "Por favor Aguarde ...", "Carregando mensagens.");

        new AsyncTask<Void, Void, List<Message>>() {
            @Override
            protected List<Message> doInBackground(Void... voids) {
                // 2 - Realiza, em background (outra Thread), tarefa IO bloqueante.
                return messageService.getLastStatuses();
            }

            @Override
            protected void onPostExecute(List<Message> statuses) {
                // 3 - Código executado na thread principal (UI), após realização da tarefa.
                Activity activity = TimelineFragment.this.getActivity();
                ListAdapter adapter = new TimelineAdapter(activity, R.layout.fragment_timeline_item,statuses);
                TimelineFragment.this.setListAdapter(adapter);

                // 4 - Esconde o diálogo de carregando.
                loader.dismiss();
            }
        }.execute();



    }
}
