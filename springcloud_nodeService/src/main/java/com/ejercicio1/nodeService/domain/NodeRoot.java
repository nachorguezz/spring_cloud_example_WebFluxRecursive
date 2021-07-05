package com.ejercicio1.nodeService.domain;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "node")

public class NodeRoot extends Node{

    @Builder(builderMethodName = "nodeRootBuilder")
    public NodeRoot(String id, String name) {
        super(id, name);
    }
}
