package com.github.welingtonveiga.mensageiro.view.timeline;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

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

        final ProgressDialog loader =  ProgressDialog.show(getActivity(), "Por favor Aguarde ...", "Carregando mensagens.");

        new AsyncTask<Void, Void, List<Message>>() {
            @Override
            protected List<Message> doInBackground(Void... voids) {
                return messageService.getLastStatuses();
            }

            @Override
            protected void onPostExecute(List<Message> statuses) {
                TimelineFragment.this.setListAdapter(new TimelineAdapter(
                                TimelineFragment.this.getActivity(),
                                R.layout.fragment_timeline_item,
                                statuses
                        )
                );
                loader.dismiss();
            }
        }.execute();



    }
}
