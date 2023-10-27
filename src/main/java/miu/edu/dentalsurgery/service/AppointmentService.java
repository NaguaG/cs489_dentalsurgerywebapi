package miu.edu.dentalsurgery.service;
import miu.edu.dentalsurgery.model.Appointment;

import java.util.List;

public interface AppointmentService {
    public List<Appointment> getAllAppointment();
    public Appointment getAppointmentById(Long appointmentId);
    public Appointment updateAppointment(Appointment appointment);
    public void deleteAppointment(Appointment appointment);
    public Appointment addNewAppointment(Appointment appointment);
}
