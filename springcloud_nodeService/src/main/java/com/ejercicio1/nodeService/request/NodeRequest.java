package com.ejercicio1.nodeService.request;

import com.ejercicio1.nodeService.domain.NodeDesc;
import com.ejercicio1.nodeService.domain.NodeRoot;
import lombok.Data;

@Data
public class NodeRequest {
    private String id;
    private String name;
    private String description;
    private String parentId;

    public NodeRequest(String id, String name, String description, String parentId) {
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }

    public NodeRequest() {

    }

    public NodeDesc toNodeDescModel(){
        return NodeDesc.nodeDescBuilder().id(this.id).name(this.name)
                .description(this.description).parentId(this.parentId).build();
    }

    public NodeRoot toNodeRootModel(){
        return NodeRoot.nodeRootBuilder().id(this.id).name(this.name).build();
    }
}
