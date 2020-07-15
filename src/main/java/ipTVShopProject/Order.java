package ipTVShopProject;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String status;
    private Long productId;
    private String productName;
    private String installationAddress;
    private Long customerId;
    private String orderDate;

    @PostPersist
    public void onPostPersist(){

        if(this.getStatus().equals("JOINORDED")){
            //System.out.println("==========JOINORDED=============");
            JoinOrdered joinOrdered = new JoinOrdered();
            BeanUtils.copyProperties(this, joinOrdered);
            joinOrdered.publishAfterCommit();
        }else if(this.getStatus().equals("ASORDED")) {
            //System.out.println("==============ASORDED===================");
            AsOrdered asOrdered = new AsOrdered();
            BeanUtils.copyProperties(this, asOrdered);
            asOrdered.publishAfterCommit();
        }

    }

    @PostUpdate
    public void onPostUpdate(){

        if(this.getStatus().equals("CANCELORDERED")){
            //System.out.println("==============JOINORDED===================");
            CancelOrdered cancelOrdered = new CancelOrdered();
            BeanUtils.copyProperties(this, cancelOrdered);
            cancelOrdered.publishAfterCommit();
        }else if(this.getStatus().equals("ORDERCANCELED")){
            // System.out.println("================ORDERCANCELED=================");
            OrderCanceled orderCanceled = new OrderCanceled();
            BeanUtils.copyProperties(this, orderCanceled);
            orderCanceled.setStatus("ORDERCANCELED");
            orderCanceled.publishAfterCommit();
        }else if(this.getStatus().equals("JOINORDERCOMPLETE")){
            // System.out.println("================JOINORDERCOMPLETE======================");
            JoinOrderCompleted joinOrderCompleted = new JoinOrderCompleted();
            BeanUtils.copyProperties(this, joinOrderCompleted);
            joinOrderCompleted.publishAfterCommit();

        }else if(this.getStatus().equals("ASORDERCOMPLETE")){
            // System.out.println("================ASORDERCOMPLETE======================");
            AsOrderCompleted asOrderCompleted = new AsOrderCompleted();
            BeanUtils.copyProperties(this, asOrderCompleted);
            asOrderCompleted.publishAfterCommit();

        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getInstallationAddress() {
        return installationAddress;
    }

    public void setInstallationAddress(String installationAddress) {
        this.installationAddress = installationAddress;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }




}