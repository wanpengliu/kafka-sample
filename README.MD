## To make jackson support polymorphic deserialization
  method 2 is probably the easiest to use

### method 1.

* on interface class, specify all subclasses 


    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({@JsonSubTypes.Type(name ="cardPaymentEvent", value = CardPaymentEvent.class),
            @JsonSubTypes.Type(name ="onlinePaymentEvent", value = OnlinePaymentEvent.class)
            })
        
* on subclasses, specify name


    @JsonTypeName("cardPaymentEvent")
    @JsonTypeName("onlinePaymentEvent")

* need new messageConverter and setMessageConverter on listenerContainerFactory


    @Bean
    public StringJsonMessageConverter converter() {
        return new StringJsonMessageConverter();
    }


### method 2. 

* on interface class - use CLASS instead of NAME


    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")        
    
* need new messageConverter and **setMessageConverter on listenerContainerFactory**


    @Bean
    public StringJsonMessageConverter converter() {
        return new StringJsonMessageConverter();
    }    
    
* add object to be deserialized in the consumer factory


    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(PaymentEvent.class));    

### method 3. apparently this works, but I never managed to get it working

* replace default object mapper and register subclass types


    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerSubtypes(CardPaymentEvent.class, OnlinePaymentEvent.class);
    return objectMapper;
    
 * on interface class
 
     
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
        
 * on subclasses, specify name
 
 
    @JsonTypeName("cardPaymentEvent")
    @JsonTypeName("onlinePaymentEvent")
        
* need new messageConverter and setMessageConverter on listenerContainerFactory


    @Bean
    public StringJsonMessageConverter converter() {
        return new StringJsonMessageConverter();
    }        

## To run locally    
http://localhost:8080/swagger-ui.html    

## generics
List<T>
T is typeparameters from the class


## Transaction
* Listener
    
    
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

    containerProperties.setTransactionManager(transactionManager);
    
    transaction manager can be set, even if there is no transaction to follow or commit at the end
        
* Producer


    configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "kafka-template-tx.");
    producerFactory.setTransactionIdPrefix("kafka-template-tx.");
    
* when exception happens, by default it rolls back/retry 10 times and eventually give up.
  The same record (same key and value) can be sent to the same topic by the same producer again
  
  SeekToCurrentErrorHandler helps to retry default 10 times
  it takes a BiConsumer for one of the constructors to run after max retry fails
  
* one producer (producer config) for multipe messagelistenercontainer seems to be working fine        