package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.boot.jaxb.hbm.spi.SubEntityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Patient;
import com.example.demo.model.Response;
import com.example.demo.model.Review;
import com.example.demo.ObserverDP.State;
import com.example.demo.ObserverDP.Subject;
import com.example.demo.constants.NotificationEndpoints;
import com.example.demo.model.Appointment;
import com.example.demo.model.Diagnostic;
import com.example.demo.model.Login;
import com.example.demo.model.Medic;
import com.example.demo.serviceimplementation.PatientServiceImpl;
import com.example.demo.serviceimplementation.ReviewServiceImpl;
import com.example.demo.serviceimplementation.AppointmentServiceImpl;
import com.example.demo.serviceimplementation.DiagnosticServiceImpl;
import com.example.demo.serviceimplementation.MedicServiceImpl;

@Controller
@RestController
@CrossOrigin("*") //pt folosire postman
//@CrossOrigin(origins = {"localhost:8080"})
public class AppController{

	//to create bean of implementation class
	@Autowired
	private PatientServiceImpl patientServiceImpl;
	@Autowired
	private DiagnosticServiceImpl diagnosticServiceImpl;
	@Autowired
	private MedicServiceImpl medicServiceImpl;
	@Autowired 
	private ReviewServiceImpl reviewServiceImpl;
	@Autowired 
	private AppointmentServiceImpl appServiceImpl;

	@Autowired
	private SimpMessagingTemplate template;

	//Subject subject = new Subject();
	//Patient patient = new Patient(subject);
	//List<Patient> p =new ArrayList<Patient>();

	@PostMapping(value="login")
	public Response login(@RequestBody Login login) {
		Response r=new Response();
		if(login.getEmail().equals("admin@admin.ro") && login.getPassword().equals("admin1")){
			r.setType("admin");
			r.setId(1);
		}else{
			if(patientServiceImpl.getByEmail(login.getEmail()) !=null && patientServiceImpl.getByEmail(login.getEmail()) !=null &&
			patientServiceImpl.getByEmail(login.getEmail()) == patientServiceImpl.getByPassword(login.getPassword())){
				r.setType("patient");
				r.setId(patientServiceImpl.getByEmail(login.getEmail()).getId());
			}else{
				if(medicServiceImpl.getByEmail(login.getEmail()) !=null && medicServiceImpl.getByEmail(login.getEmail()) !=null &&
				medicServiceImpl.getByEmail(login.getEmail()) == medicServiceImpl.getByPassword(login.getPassword())){
					r.setType("medic");	
					r.setId(medicServiceImpl.getByEmail(login.getEmail()).getId());			
				}else{
					r.setType("bad");
					r.setId(0);
				}
			}
		}
		return r;
	}

	///////////// S A V E 

	@PostMapping(value="patients")
	public Patient savePatient(@RequestBody Patient patient) {
		patientServiceImpl.savePatient(patient);
		return patient;
	}
	
	@PostMapping(value="diagnostic")
	public Diagnostic saveDiagnostic(@RequestBody Diagnostic diagnostic) {
		diagnosticServiceImpl.saveDiagnostic(diagnostic);
		return diagnostic;
	}

	@PostMapping(value="medics")
	public Medic saveMedic(@RequestBody Medic medic) {
		medicServiceImpl.saveMedic(medic);
		return medic;
	}

	@PostMapping(value="review")
	public Review saveReview(@RequestBody Review review) {
		reviewServiceImpl.saveReview(review);
		//State s = new State(review.getPatient(),review.getMedic());
		//subject.setState(s);
		return review;
	}

	@PostMapping(value="appointment")
	public Appointment saveAppointment(@RequestBody Appointment appointment) {
		if(appServiceImpl.getAppointmentByTime(appointment.getTime())!= null && appServiceImpl.getAppointmentByDate(appointment.getDate())!=null &&
		appServiceImpl.getAppointmentByTime(appointment.getTime())==appServiceImpl.getAppointmentByDate(appointment.getDate())){
			return null;
		}else{
			appServiceImpl.saveAppointment(appointment);
			return appointment;
		}
	}

	///////////////////// F I N D   A L L

	@GetMapping(value="patients")
	public List<Patient> findAllPatients(){
		return patientServiceImpl.findAllPatients();
	}

	@GetMapping(value="medics")
	public List<Medic> findAllMedics(){
		return medicServiceImpl.findAllMedics();
	}

	@GetMapping(value="app")
	public List<Appointment> findAllAppointments(){
		return appServiceImpl.findAllAppointments();
	}

	//////////////////// F I N D   B Y   I D
	@GetMapping("patients/{id}")
	public Patient getPatientById(@PathVariable int id) {
		return patientServiceImpl.getPatientById(id);
	}

	@GetMapping("medics/{id}")
	public Medic getMedicById(@PathVariable int id) {
		return medicServiceImpl.getMedicById(id);
	}

	@GetMapping("appointment/{id}")
	public Appointment getAppById(@PathVariable int id) {
		return appServiceImpl.getAppointmentById(id);
	}

	//////////////////////// U P D A T E
	
	@PutMapping(value="patients/{id}")
	public Patient updatePatient(@PathVariable int id, @RequestBody Patient patient) {
		return patientServiceImpl.updatePatient(id, patient);
	}

	@PutMapping(value="medics/{id}")
	public Medic updateMedic(@PathVariable int id, @RequestBody Medic medic) {
		return medicServiceImpl.updateMedic(id, medic);
	}

	@PutMapping(value="appointments/{id}")
	public Appointment updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
		this.template.convertAndSend(NotificationEndpoints.MEDIC_APP_ADITION+appointment.getMedic().getId(), "A pacient made an appointment on "+appointment.getDate()+" at "+appointment.getTime());
		return appServiceImpl.updateAppointment(id, appointment);
	}

	////////////////////////// D E L E T E
	
	@DeleteMapping("patients/{id}")
	public String deletePatient(@PathVariable int id) {
		patientServiceImpl.deletePatient(id);
		return "Patient deleted";
	}

	@DeleteMapping("medics/{id}")
	public String deleteMedic(@PathVariable int id) {
		medicServiceImpl.deleteMedic(id);
		return "Medic deleted";
	}

	@DeleteMapping("appointments/{id}")
	public String deleteApp(@PathVariable int id) {
		appServiceImpl.deleteAppointment(id);
		return "Appoinment deleted";
	}
}
