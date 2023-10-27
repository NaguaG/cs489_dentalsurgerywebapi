package miu.edu.dentalsurgery.service.Impl;

import miu.edu.dentalsurgery.model.Surgery;
import miu.edu.dentalsurgery.repository.SurgeryRepository;
import miu.edu.dentalsurgery.service.SurgeryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SurgeryServiceImpl implements SurgeryService {
    private SurgeryRepository surgeryRepository;
    public SurgeryServiceImpl(SurgeryRepository surgeryRepository){
        this.surgeryRepository = surgeryRepository;
    }

    @Override
    public List<Surgery> getAllSurgeryList() {
        return surgeryRepository.findAll();
    }

    @Override
    public Surgery getSurgeryById(Long surgeryId) {
        return surgeryRepository.findById(surgeryId)
                .orElse(null);
    }

    @Override
    public Surgery updateSurgery(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }

    @Override
    public void deleteSurgery(Surgery surgery) {
        surgeryRepository.delete(surgery);
    }

    @Override
    public Surgery addNewSurgery(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }
}
