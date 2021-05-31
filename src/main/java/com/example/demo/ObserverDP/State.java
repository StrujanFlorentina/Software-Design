package com.example.demo.ObserverDP;

import com.example.demo.model.Medic;
import com.example.demo.model.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class State {
    private Patient state1;
    private Medic state2;
}
