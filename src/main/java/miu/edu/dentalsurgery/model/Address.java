package miu.edu.dentalsurgery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String state;
    private String city;
    private String zipcode;
    @OneToOne(mappedBy = "address")
    //@JsonIgnore
    @JsonBackReference
    //@JsonManagedReference
    private Patient patient;
    @OneToOne(mappedBy = "address")
    private Surgery surgery;

    public Address(String state, String city, String zipcode) {
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder("Address{ ");
        sb.append("addressId=").append(addressId);
        sb.append(", state='").append(state).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", zipcode='").append(zipcode).append('\'');
        sb.append("}");

        return sb.toString();
    }



}
