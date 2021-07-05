package com.ejercicio1.nodeService.service;

import com.ejercicio1.nodeService.domain.Node;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface INodeService {
    Flux findAll();

    Mono save(Node node);
}
