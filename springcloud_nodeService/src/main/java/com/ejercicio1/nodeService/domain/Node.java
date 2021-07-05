package com.ejercicio1.nodeService.domain;

import com.ejercicio1.nodeService.request.TreeElement;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@Document(collection = "node")
@Getter
@Builder
public class Node {

    @Id
    private final String id;
    private final String name;

    public TreeElement toTreeElement(){
        return new TreeElement(id, name, new ArrayList<>());
    }

}
