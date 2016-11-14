package com.github.welingtonveiga.mensageiro.domain.services;

import com.github.welingtonveiga.mensageiro.domain.model.Message;
import com.github.welingtonveiga.mensageiro.util.RestClient;

import java.util.List;

public class MessageService {

    private  static final String SERVICE_API = "https://service-api.herokuapp.com/statuses?1=1";

    private static final String ORDER_BY_CREATION_DESC = "&_sort=created_at&_order=DESC";

    private final RestClient restClient = new RestClient();

    public List<Message> getLastStatuses() {
        return restClient.getAll(SERVICE_API + ORDER_BY_CREATION_DESC, Message[].class);
    }
}
