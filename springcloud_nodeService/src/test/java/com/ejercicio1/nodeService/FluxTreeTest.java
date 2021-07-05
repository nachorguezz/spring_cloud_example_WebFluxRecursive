package com.ejercicio1.nodeService;

import com.ejercicio1.nodeService.controller.NodeController;
import com.ejercicio1.nodeService.domain.Node;
import com.ejercicio1.nodeService.request.NodeRequest;
import com.ejercicio1.nodeService.request.TreeElement;
import com.ejercicio1.nodeService.service.NodeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers= NodeController.class)
@ContextConfiguration(classes=NodeController.class)
public class FluxTreeTest {
    @Autowired
    private WebTestClient testClient;

    @MockBean
    private NodeServiceImpl nodeService;

    @Test
    public void shouldReturnNodeTree(){
        TreeElement desc_1_1_1 = new TreeElement("desc_1_1_1", "desc_1_1_1", new ArrayList<>());
        TreeElement desc_1_1_2 = new TreeElement("desc_1_1_2", "desc_1_1_2", new ArrayList<>());
        TreeElement desc_1_1 = new TreeElement("desc_1_2", "desc_1_2", Arrays.asList(desc_1_1_1, desc_1_1_2));
        TreeElement desc_1_2 = new TreeElement("desc_1_1", "desc_1_1", new ArrayList<>());
        TreeElement desc_2_1 = new TreeElement("desc2_1", "desc2_1", new ArrayList<>());
        TreeElement root_1 = new TreeElement("root_1", "root_1", Arrays.asList(desc_1_1, desc_1_2));
        TreeElement root_2 = new TreeElement("root_2", "root_2", Arrays.asList(desc_2_1));

        when(nodeService.getExpandedTree())
                .thenReturn(Flux.just(root_1, root_2));

        testClient.get().uri("/node")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].name").isEqualTo(root_1.getName())
                .jsonPath("$[0].nodeRefs").isArray()
                .jsonPath("$[0].nodeRefs").isNotEmpty()
                .jsonPath("$[1].name").isEqualTo(root_2.getName())
                .jsonPath("$[1].nodeRefs[0].nodeRefs").isEmpty()
                .jsonPath("$[3]").doesNotExist();
    }


    @Test
    public void shouldSaveANode() {
        Mono<NodeRequest> unsavedNodeMono = Mono.just(testNode("nodeTest"));
        Node savedNode = testNode("nodeTest").toNodeRootModel();
        Mono<Node> savedNodeMono = Mono.just(savedNode);

        when(nodeService.save(any()))
                .thenReturn(savedNodeMono);

        testClient.post()
                .uri("/node")
                .contentType(MediaType.APPLICATION_JSON)
                .body(unsavedNodeMono, NodeRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$.name").isEqualTo(savedNode.getName());
    }


    private NodeRequest testNode(String name) {
        NodeRequest node = new NodeRequest();
        node.setName(name);
        return node;
    }

}
