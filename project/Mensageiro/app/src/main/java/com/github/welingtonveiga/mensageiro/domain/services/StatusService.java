package com.github.welingtonveiga.mensageiro.domain.services;

import com.github.welingtonveiga.mensageiro.domain.model.Status;
import com.github.welingtonveiga.mensageiro.util.RestClient;

import java.util.List;

public class StatusService {

    private final RestClient restClient = new RestClient();

    public List<Status> getLastStatuses() {
        return restClient.getAll("https://service-api.herokuapp.com/statuses?_sort=created_at&_order=DESC", Status.class);
    }

}
