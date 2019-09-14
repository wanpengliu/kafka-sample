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
