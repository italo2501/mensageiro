package com.github.welingtonveiga.mensageiro.view.timeline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.welingtonveiga.mensageiro.R;
import com.github.welingtonveiga.mensageiro.domain.model.Message;
import com.github.welingtonveiga.mensageiro.domain.services.MessageService;
import com.github.welingtonveiga.mensageiro.util.LocaleProvider;
import com.github.welingtonveiga.mensageiro.view.main.MainActivity;
import com.github.welingtonveiga.mensageiro.view.status.StatusActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class TimelineAdapter extends ArrayAdapter<Message> {

    private static final String TAG = TimelineAdapter.class.getName();

    private final Context context;
    private final List<Message> statuses;
    private final SimpleDateFormat formatter;


    public TimelineAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        this.context = context;
        this.statuses = objects;
        formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyy", LocaleProvider.get(context));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.fragment_timeline_item,parent, false);
        }

        final Message statusMessage = statuses.get(position);
        Log.d(TAG, statusMessage.toString());

        TextView author = (TextView) view.findViewById(R.id.list_item_text_user);
        author.setText(statusMessage.getAuthor());

        TextView message = (TextView) view.findViewById(R.id.list_item_text_message);
        message.setText(statusMessage.getMessage());

        TextView createdAt = (TextView) view.findViewById(R.id.list_item_text_created_at);
        createdAt.setText(formatter.format(statusMessage.getCreatedAt()));

        ImageView actionEdit = (ImageView) view.findViewById(R.id.edit_action);
        actionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StatusActivity.class);
                intent.putExtra("message", statusMessage);
                getContext().startActivity(intent);
            }
        });

        final ImageView actionFavorite = (ImageView) view.findViewById(R.id.favorite_action);
        if (statusMessage.isLiked()){
            actionFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
        }

        actionFavorite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                statusMessage.setLiked(!statusMessage.isLiked());
                new PostTask().execute(statusMessage);
                if (statusMessage.isLiked()) {
                    actionFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                } else {
                    actionFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }
            }
        });

        return view;

    }

    private final class PostTask extends AsyncTask<Message, Void, String> {
        // 1 - Instanciando serviço.
        private final MessageService service = new MessageService();

        @Override
        protected String doInBackground(Message... messages) {
            String message = TimelineAdapter.this.getContext()
                    .getResources()
                    .getString(R.string.publish_message_succes);

            try {
                // 2 - Para cada mensagem enviada para essa task, vamos publicá-la utilizando o
                // serviço.
                for (Message m: messages) {
                    service.publish(m);
                }
            } catch (Exception e) {
                // 3 - Se algo falhar (REDE, disponibilidade do serviço, etc precisamos dar o retorno)
                Log.e(TAG, "Error posting status! " + e.getMessage() ,e);
                message = TimelineAdapter.this.getContext().getString(R.string.publish_message_failed);
            }

            return message;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // 1 - Exibir mensagem sobre a tela com o resultado da operação.
            Toast.makeText(TimelineAdapter.this.getContext(), result, Toast.LENGTH_LONG).show();

        }
    }
}
