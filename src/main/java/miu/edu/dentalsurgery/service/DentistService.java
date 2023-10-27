package miu.edu.dentalsurgery.service;
import miu.edu.dentalsurgery.model.Dentist;

import java.util.List;

public interface DentistService {
    List<Dentist> getAllDentists();
    Dentist getDentistById(Long dentsistId);
    Dentist updateDentist(Dentist dentist);
    void deleteDentist(Dentist dentist);
    Dentist addNewDentist(Dentist dentist);
}
