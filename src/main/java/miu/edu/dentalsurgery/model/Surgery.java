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
@Table(name = "surgeries")
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surgeryId;
    @Column(name = "surgeryNo")
    private String surgeryNumber;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments;

    public Surgery(String surgeryNumber){
        this.surgeryNumber = surgeryNumber;
    }
}
