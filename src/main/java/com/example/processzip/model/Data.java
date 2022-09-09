package com.example.processzip.model;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Builder
@Entity
@Table(name = "data")
public class Data {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;

    private String firstName;
    private String lastName;
    private String date;

    public Data(UUID id, String firstName, String lastName, String date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
    }

    public String getFirstName(){
        return  firstName;
    }

    public String getLastName(){
        return  lastName;
    }

    public String getDate(){
        return  date;
    }

    public UUID getId(){
        return  id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setId(UUID id){
        this.id = id;
    }
}
