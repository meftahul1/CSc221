package com.example.demo.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    //create a getID method
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private boolean completed;
}