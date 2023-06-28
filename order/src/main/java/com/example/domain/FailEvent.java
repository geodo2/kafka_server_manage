package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FailEvent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fail_event_id")
    public Long id;

    private boolean complete;
    private String jsonObject;

    public static FailEvent of(String jsonObject) {
        return new FailEvent(null, false, jsonObject);
    }

    public void Complete() {
        this.complete = true;
    }
}
