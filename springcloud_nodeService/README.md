# Spring Boot + WebFlux + MongoDb + Docker

# Starting
In this demo we generate a project with spring boot, which have got a controller with 2 entpoints. The firts is for insert new register and the other to obtains inserted registries in a tree structure according to their dependencies.

For this example, we using two entities *NodeRoot* and *NodeDesc* with the following values:

<table>
<tr><th>NodeRoot</th><th>NodeDesc</th></tr>
<tr><td>

| Field | Type |
|--|--|
|id|String|
|name|String|

</td><td>

| Field | Type |
|--|--|
|id|String|
|name|String|
|description|String|
|parentId|String|

</td></tr> </table>
 
Both entities are stored in the same collection called *node*.

# Requirements
Yo should got installed *docker*.  
To run the application correctly it is necessary to have first deployed the other components of the project:
1. Config Server (https://github.com/nachorguezz/springcloud_configServer.git)
2. Discovery Service (https://github.com/nachorguezz/springcloud_discoveryService.git)
3. Gateway Service (https://github.com/nachorguezz/springcloud_gatewayService.git)

# Instalation
We use *docker* to generate the mongoDB container. Use following command:

```
    docker run -it -v mongodata:/data/db -p 27017:27017 --name mongodb -d mongo
```
Once the application will be running, you should got the next containers:

````
    - mongodb     # With the database
```` 

# Test
For this application, we should be do some calls.

First, two to insert new nodeRoots. 
Then, we have to insert some new NodeDesc specifying the id of the parent with the parentId attribute. 
Finally, we should one call to obtain inserted nodes in a tree structure.

To do this, you can use the following requests with *curl*: 

1. To insert new nodeRoot:
```
    curl --location --request POST 'localhost:8080/node' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "node_root"
    }'
```


2. To insert new nodeDesc:
```
    curl --location --request POST 'localhost:8080/node' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "node_desc",
        "description": "This is a description"
        "parentId": "60dc89c0f9f7366e7a787137"
    }'
```

2. For recovery inserted nodes.
```
    curl --location --request GET 'localhost:8080/node'
```
