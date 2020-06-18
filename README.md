# Creating a spring boot project to send messages to multiple vhosts

### Steps
#### First of all you need to create the virtual hosts manually
curl -u guest:guest -X PUT http://localhost:15672/api/vhosts/firstvhost

curl -u guest:guest -X PUT http://localhost:15672/api/vhosts/secondvhost

#### Now follow these steps
* Create a Spring boot project
* Add spring rabbit dependencies
* Create a global configuration with exchange, queue names etc.
* Create a configuration for each virtual host connection.
* Add the above connectionFactories for eac h vhost to SimpleRoutingConnectionFactory
* try sending messages.


### Sample project

* In this project a message is send to two different vhosts.
* Note that the que names, exchange names will be same for both vhosts.
* Once you run the app you can see following vhots and queues
* also you will be able to see messages in the queues.


| vhost         |   exchanges   |  Queues | Routig key |
| ------------- | ------------- | -------------| -------------|
| firstvhost  | mytest.exchange  | |mytest.routing.queue|mytest.routing.key|
| firstvhost | mytest.deadletter.exchange  | mytest.deadletter.routing.queue|mytest.deadletter.routing.key|
| secondvhost  | mytest.exchange  | |mytest.routing.queue|mytest.routing.key|
| secondvhost | mytest.deadletter.exchange | mytest.deadletter.routing.queue|	mytest.deadletter.routing.key|


The following guides illustrate how to use some features concretely:

* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)

