package com.example.practicaBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.practicaBackend.entities.Client;
import com.example.practicaBackend.services.ClientService;

@RestController
public class ClientController {

	@Autowired
	private ClientService service;
	
	@PostMapping("/addClient")
	public Client addClient(@RequestBody Client client) {
		return service.saveClient(client);
	}
	
	@PostMapping("/addClients")
	public List<Client> addClients(@RequestBody List<Client> clients) {
		return service.saveClients(clients);
	}
	
	@GetMapping("/clients")
	public List<Client> findAllClients(){
		return service.getClients();
	}
	
	@GetMapping("/client/{id}")
	public Client findClientById(@PathVariable int id){
		return service.getClientById(id);
	}
	
	@GetMapping("/clientId/{idType}")
	public List<Client> findCliendByIdType(@PathVariable String idType){
		return service.getClientsByIdType(idType);
	}
	
	@GetMapping("/clientAge/{age}")
	public List<Client> findCliendByAge(@PathVariable int age){
		return service.getClientsByAge(age);
	}
	
	@PutMapping("/clients/update")
	public Client updateClient(@RequestBody Client client) {
		return service.updateClient(client);
	}
	
	@DeleteMapping("/clients/{id}")
	public String deleteClient(@PathVariable int id) {
		return service.deleteById(id);
	}
}
