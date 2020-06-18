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

firstvhost	
mytest.deadletter.routing.queue
D	idle	0	0	0			
firstvhost	
mytest.routing.queue
D DLX DLK Args	idle	5	0	5	0.00/s		

secondvhost	
mytest.deadletter.routing.queue
D	idle	0	0	0			
secondvhost	
mytest.routing.queue
D DLX DLK Args	idle	5	0	5	0.00/s		



The following guides illustrate how to use some features concretely:

* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)

