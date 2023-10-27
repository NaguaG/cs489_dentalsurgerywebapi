package miu.edu.dentalsurgery.service;
import miu.edu.dentalsurgery.model.Surgery;

import java.util.List;

public interface SurgeryService {
    List<Surgery> getAllSurgeryList();
    Surgery getSurgeryById(Long surgeryId);
    Surgery updateSurgery(Surgery surgery);
    void deleteSurgery(Surgery surgery);
    Surgery addNewSurgery(Surgery surgery);
}
