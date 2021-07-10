# Creating a spring boot project to send messages to multiple vhosts - rabbitmq

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
| firstvhost  | mytest.exchange  |mytest.routing.queue|mytest.routing.key|
| firstvhost | mytest.deadletter.exchange  | mytest.deadletter.routing.queue|mytest.deadletter.routing.key|
| secondvhost  | mytest.exchange  |mytest.routing.queue|mytest.routing.key|
| secondvhost | mytest.deadletter.exchange | mytest.deadletter.routing.queue|	mytest.deadletter.routing.key|


The following guides illustrate how to use some features concretely:

* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)

#### Output after running project
```
Sending Message to secondvhost count = 1
2020-06-18 10:46:56.661  INFO 21971 --- [           main] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: localhost:5672
2020-06-18 10:46:56.699  INFO 21971 --- [           main] o.s.a.r.c.CachingConnectionFactory       : Created new connection: secondConnectionFactory#100f9bbe:0/SimpleConnection@522ba524 [delegate=amqp://guest@127.0.0.1:5672/secondvhost, localPort= 57953]
Sending Message to firstvhost count = 2
2020-06-18 10:46:56.779  INFO 21971 --- [           main] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: localhost:5672
2020-06-18 10:46:56.793  INFO 21971 --- [           main] o.s.a.r.c.CachingConnectionFactory       : Created new connection: firstConnectionFactory#367795c7:0/SimpleConnection@22bd2039 [delegate=amqp://guest@127.0.0.1:5672/firstvhost, localPort= 57954]
Sending Message to secondvhost count = 3
Sending Message to firstvhost count = 4
Sending Message to secondvhost count = 5
Sending Message to firstvhost count = 6
Sending Message to secondvhost count = 7
Sending Message to firstvhost count = 8
Sending Message to secondvhost count = 9
Sending Message to firstvhost count = 10
```
