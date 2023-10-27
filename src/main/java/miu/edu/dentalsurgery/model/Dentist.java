package miu.edu.dentalsurgery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dentists")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dentistId;
    private String lastName;
    private String firstname;
    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointment;
    public Dentist(String lastName, String firstname){
        this.lastName = lastName;
        this.firstname = firstname;
    }
}
