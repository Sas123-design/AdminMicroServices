package com.capgemini.loanprocessingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.loanprocessingsystem.entity.ApplyLoan;
import com.capgemini.loanprocessingsystem.entity.Client;
import com.capgemini.loanprocessingsystem.entity.LoanPrograms;
import com.capgemini.loanprocessingsystem.response.Response;
import com.capgemini.loanprocessingsystem.service.ClientService;
import com.capgemini.loanprocessingsystem.service.LoanApplicationService;
import com.capgemini.loanprocessingsystem.service.LoanProgramService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private LoanProgramService loanProgramService;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@GetMapping("/viewclient")
	public Response<List<Client>> viewAllCients(){
		List<Client> list=clientService.showAllClients();
		return new Response<List<Client>>(false, "All clients", list);
	}
	
	@GetMapping("/viewprograms")
	public Response<List<LoanPrograms>> viewAllPrograms(){
		List<LoanPrograms> list=loanProgramService.findAllPrograms();
		return new Response<List<LoanPrograms>>(false, "All progrmas", list);
	}
	
	@GetMapping("/viewapplications")
	public Response<List<ApplyLoan>> viewAllApplications(){
		List<ApplyLoan> list=loanApplicationService.findAllApplications();
		return new Response<List<ApplyLoan>>(false, "All applications", list);
	}
	
	@PostMapping("/addclient")
	public Response<Client> addClient(@RequestBody Client client){
		Client client1=clientService.saveClient(client);
		if(client!=null) {
			return new Response<Client>(false, "Client Added", client1);
		}else {
			return new Response<Client>(true, "Client not Added", null);
		}
	}
	
	@PostMapping("/addprogram")
	public Response<LoanPrograms> addProgram(@RequestBody LoanPrograms program){
		LoanPrograms program1=loanProgramService.saveProgram(program);
		if(program1!=null) {
			return new Response<LoanPrograms>(false, "Loan Program Added", program1);
		}else {
			return new Response<LoanPrograms>(true, "program not Added", null);
		}
	}
	
	@PutMapping("/updateclient")
	public Response<Client> updateClient(@RequestBody Client client){
		Client client1=clientService.saveClient(client);
		if(client!=null) {
			return new Response<Client>(false, "Client updated", client1);
		}else {
			return new Response<Client>(true, "Client not updated", null);
		}
	}
	
	@PutMapping("/updateprogram")
	public Response<LoanPrograms> updateProgram(@RequestBody LoanPrograms program){
		LoanPrograms theProgram=loanProgramService.updatePrograms(program);
		if(theProgram!=null) {
			return new Response<LoanPrograms>(false, "Program updated", theProgram);
		}else {
			return new Response<LoanPrograms>(true, "Program not updated", null);
		}
	}
	
	@DeleteMapping("/deleteclient")
	public Response<Client> deleteClient(@PathVariable String email){
		Client client=clientService.getClientByEmail(email);
		if(client!=null) {
			clientService.deleteClient(email);
			return new Response<Client>(false, "client deleted", client);
		}else {
			return new Response<Client>(true, "client not found", null);
		}
	}
	
	@DeleteMapping("/deleteprogram/{id}")
	public Response<LoanPrograms> deleteProgram(@PathVariable int id){
		LoanPrograms program=loanProgramService.findByName(id);
		if(program!=null) {
			loanProgramService.deleteProgram(id);
			return new Response<LoanPrograms>(false, "program deleted", program);
		}else {
			return new Response<LoanPrograms>(true, "program not found", null);
		}
	}
}
