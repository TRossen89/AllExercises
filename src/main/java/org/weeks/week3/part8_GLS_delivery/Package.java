package org.weeks.week3.part8_GLS_delivery;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString

@Entity
@Table(name = "package")
@NoArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tracking_number", unique = true)
    private String trackingNumber;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "delivery_status")
    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;


    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime timeStamp;

    public Package(String trackingNumber, String senderName, String receiverName, DeliveryStatus deliveryStatus) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.deliveryStatus = deliveryStatus;
    }

    @PrePersist
    public void timeStamp() throws RuntimeException {

        LocalDateTime localDateTime = LocalDateTime.now();

        this.timeStamp = localDateTime;

    }

    @PreUpdate
    public void timeStampUpdated() throws RuntimeException {

        LocalDateTime localDateTime = LocalDateTime.now();

        this.timeStamp = localDateTime;
    }




}

