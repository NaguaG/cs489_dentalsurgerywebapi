package miu.edu.dentalsurgery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    @Column(name = "patNo")
    private String patientNumber;
    @Column(nullable = false)
    @NotBlank(message = "Patient last name is required, not be null or empty !!!")
    private String lastName;
    @Column(nullable = false)
    @NotBlank(message = "Patient first name is required, not be null or empty !!!")
    private String firstName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", unique = true, nullable = true)
    //@JsonIgnore
    @JsonManagedReference
    //@JsonBackReference
    private Address address;
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Patient{");
        sb.append("patientId=").append(patientId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }


}
