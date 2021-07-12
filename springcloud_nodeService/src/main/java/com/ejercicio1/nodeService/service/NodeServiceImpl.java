package com.ejercicio1.nodeService.service;

import com.ejercicio1.nodeService.domain.Node;
import com.ejercicio1.nodeService.domain.NodeRoot;
import com.ejercicio1.nodeService.repository.NodeCrudRepository;
import com.ejercicio1.nodeService.request.TreeElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NodeServiceImpl implements INodeService {
    @Autowired
    private NodeCrudRepository nodeCrudRepository;

    public Flux findAll() {
        return nodeCrudRepository.findAll();
    }

    public Mono save(Node node) {
        return nodeCrudRepository.save(node);
    }


    private Mono<TreeElement> addChildren(TreeElement parent){
        return nodeCrudRepository.findByParentId(parent.getId()).map(Node:toTreeElement).collectList().map(children -> {
            parent.setNodeRefs(children);
            return parent;
        });
    }

    public Flux<TreeElement> getExpandedTree() {
        return nodeCrudRepository.findAll().filter(node -> node instanceof NodeRoot).map(Node::toTreeElement).flatMap(this::addChildren);
    }
}
