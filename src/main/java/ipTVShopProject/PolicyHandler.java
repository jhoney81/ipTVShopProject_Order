package ipTVShopProject;

import ipTVShopProject.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyHandler{

    @StreamListener(KafkaProcessor.INPUT)
    public void onEvent(@Payload String message) {

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    OrderRepository orderRepository;


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverJoinCompleted_JoinCompletionNotify(@Payload JoinCompleted joinCompleted){

        if(joinCompleted.isMe() && joinCompleted.getStatus()!=null){

//            System.out.println("111111111111111111111111111111111111111111111" );
            Optional<Order> orders = orderRepository.findById(joinCompleted.getOrderId());
            orders.get().setId(joinCompleted.getId());
            orders.get().setStatus("JOINORDERCOMPLETE");
            orderRepository.save(orders.get());

//            }
            System.out.println("##### listener JoinCompletionNotify status : " + joinCompleted.getStatus());

        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCancelAccepted_OrderCancelAccept(@Payload OrderCancelAccepted orderCancelAccepted){

        if(orderCancelAccepted.isMe() && orderCancelAccepted.getStatus()!=null){

            System.out.println("333333333333333333333333333333333333333333" );

            Optional<Order> orders = orderRepository.findById(orderCancelAccepted.getOrderId());
            orders.get().setId(orderCancelAccepted.getId());
            orders.get().setStatus("ORDERCANCELED");
            orderRepository.save(orders.get());


            System.out.println("##### listener OrderCancelAccept status : " + orderCancelAccepted.getStatus());
            System.out.println("##### listener OrderCancelAccept status : " + orderCancelAccepted.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAsCompleted_AsCompletionNotify(@Payload AsCompleted asCompleted){

        if(asCompleted.isMe() && asCompleted.getStatus()!=null){

            System.out.println("2222222222222222222222222222222222222222222222222222222" );
            Optional<Order> orders = orderRepository.findById(asCompleted.getOrderId());
            orders.get().setId(asCompleted.getId());
            orders.get().setStatus("ASORDERCOMPLETE");
            orderRepository.save(orders.get());

            System.out.println("##### listener AsCompletionNotify status : " + asCompleted.getStatus());

        }

    }

}