package miu.edu.dentalsurgery;

import jakarta.annotation.PostConstruct;
import miu.edu.dentalsurgery.controller.AddressController;
import miu.edu.dentalsurgery.controller.PatientController;
import miu.edu.dentalsurgery.dto.address.AddressRequest;
import miu.edu.dentalsurgery.dto.patient.PatientRequest;
import miu.edu.dentalsurgery.model.Role;
import miu.edu.dentalsurgery.model.User;
import miu.edu.dentalsurgery.repository.RoleRepository;
import miu.edu.dentalsurgery.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DentalSurgeriesAppSystemWebApiApplication {
	private AddressController addressController;
	private PatientController patientController;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public DentalSurgeriesAppSystemWebApiApplication(AddressController addressController, PatientController patientController,
													 UserRepository userRepository, RoleRepository roleRepository,
													 PasswordEncoder passwordEncoder){
		this.addressController = addressController;
		this.patientController = patientController;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}



	public static void main(String[] args) {
		SpringApplication.run(DentalSurgeriesAppSystemWebApiApplication.class, args);
	}

	@PostConstruct
	public void initAdminUser() {
		var adminUser = userRepository.findUserByUserName("admin");
		if(adminUser.isEmpty()) {
			List<Role> listAdminRoles = new ArrayList<>();
			var adminRole = roleRepository.findRoleByRoleName("ROLE_ADMIN");
			if(adminRole.isEmpty()) {
				var newAdminRole =  new Role(null, "ROLE_ADMIN");
				listAdminRoles.add(newAdminRole);
			} else {
				listAdminRoles.add(adminRole.get());
			}
			User newAdminUser = new User(null, "Admin", "Admin", "admin",
					passwordEncoder.encode("test1234"), "admin@fairfieldlibrary.com",
					true,true, true, true);
			newAdminUser.setRoles(listAdminRoles);
			userRepository.save(newAdminUser);
		}
	}
	@Bean
	CommandLineRunner commandLineRunner(){
		return (args) -> {
			System.out.println("Hello Dental Surgery WebAPI server. Starting...");
			System.out.println("Dental Surgery WebAPI server. Started.\nRunning Apache Tomcat service and Listening for HTTP Request on Port number, 8080");//			addNewPatient("P100", "Gillian", "White", "IA", "Fairfield", "52556");
//			addNewPatient("P105", "Jill", "Bell", "PA", "Philadelphia", "19006");
//			addNewPatient("P108", "Ian", "Mackey", "CA", "San-Francisco", "19565");
//			addNewPatient("P110", "John", "Walker", "VA", "Leesburg", "95666");

		};
	}
	public void addNewPatient(String patNo, String lastName, String firstName, String state, String city, String zipCode){
		PatientRequest patientRequest = new PatientRequest(patNo, lastName, firstName, new AddressRequest(state, city, zipCode));
		patientController.addNewPatient(patientRequest);
	}



}
