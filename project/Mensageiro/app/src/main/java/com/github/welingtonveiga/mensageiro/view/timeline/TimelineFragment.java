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
import java.util.concurrent.atomic.AtomicReference;

public class TimelineFragment  extends ListFragment {

    private final MessageService messageService = new MessageService();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 1 - Exibe diálogo de carregamento.
        new MessagesLoaderAsyncTask(getActivity(), this, messageService).execute();
    }



    private static class MessagesLoaderAsyncTask extends AsyncTask<Void, Void, List<Message>> {

        private final AtomicReference<ProgressDialog> loader = new AtomicReference<>();

        private final Activity activity;

        private final ListFragment fragment;

        private final MessageService service;

        private MessagesLoaderAsyncTask(Activity activity, ListFragment fragment, MessageService service) {
            this.activity = activity;
            this.fragment = fragment;
            this.service = service;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //loader.set(ProgressDialog.show(activity, "Por favor Aguarde ...", "Carregando mensagens."));
        }

        @Override
        protected List<Message> doInBackground(Void... voids) {
            // 2 - Realiza, em background (outra Thread), tarefa IO bloqueante.
            return service.getLastStatuses();
        }

        @Override
        protected void onPostExecute(List<Message> statuses) {
            // 3 - Código executado na thread principal (UI), após realização da tarefa.
            Activity activity = fragment.getActivity();
            ListAdapter adapter = new TimelineAdapter(activity, R.layout.fragment_timeline_item,statuses);
            fragment.setListAdapter(adapter);

            // 4 - Esconde o diálogo de carregando.
            //loader.get().dismiss();
        }
    }
}
