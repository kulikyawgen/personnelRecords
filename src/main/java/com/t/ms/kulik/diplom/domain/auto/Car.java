package com.t.ms.kulik.diplom.domain.auto;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carTitle;
    @ManyToOne
    private Producer producer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarTitle() {
        return carTitle;
    }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
