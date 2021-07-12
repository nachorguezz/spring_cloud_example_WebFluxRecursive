package com.ejercicio1.nodeService.repository;

import com.ejercicio1.nodeService.domain.Node;
import com.ejercicio1.nodeService.domain.NodeDesc;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NodeCrudRepository extends ReactiveCrudRepository<Node, String>{

    Flux<NodeDesc> findByParentId(String parent_Id);

}
