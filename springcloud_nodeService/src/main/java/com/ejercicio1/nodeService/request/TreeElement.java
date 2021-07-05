package com.ejercicio1.nodeService.request;


import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class TreeElement{
    private String id;
    private String name;
    private List<TreeElement> nodeRefs;

    public TreeElement(String id, String name, List<TreeElement> nodeRefs) {
        this.id = id;
        this.name = name;
        this.nodeRefs = nodeRefs;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addTreeElement(TreeElement t) {
        this.nodeRefs.add(t);
    }

}
