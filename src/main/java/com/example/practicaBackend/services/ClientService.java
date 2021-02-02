package com.example.practicaBackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.practicaBackend.entities.Client;
import com.example.practicaBackend.entities.Photo;
import com.example.practicaBackend.repositories.ClientRepository;
import com.example.practicaBackend.repositories.PhotoRepository;

@Service
public class ClientService {

	private ClientRepository repository;
	private PhotoRepository photoRepo;

	@Autowired
	public ClientService(ClientRepository repository, PhotoRepository photoRepo) {
		this.repository = repository;
		this.photoRepo = photoRepo;
	}

	public Client saveClient(Client client) {
		Photo photo = new Photo();
		photo.setFoto(client.getFoto());
		photo.setIdentificacion(client.getIdentificacion());
		photoRepo.save(photo);
		return repository.save(client);
	}

	public List<Client> saveClients(List<Client> clients) {
		Photo auxiliar = new Photo();
		List<Photo> photos = new ArrayList<Photo>();
		for (int i = 0; i < clients.size(); i++) {
			auxiliar.setFoto(clients.get(i).getFoto());
			auxiliar.setIdentificacion(clients.get(i).getIdentificacion());
			photos.add(auxiliar);
		}
		photoRepo.saveAll(photos);
		return repository.saveAll(clients);
	}

	public List<Client> getClients() {
		List<Photo> photos = photoRepo.findAll();
		List<Client> clients = repository.findAll();
		for (int i = 0; i < clients.size(); i++) {
			for (int j = 0; j < photos.size(); j++) {
				if (clients.get(i).getIdentificacion() == photos.get(j).getIdentificacion()) {
					clients.get(i).setFoto(photos.get(j).getFoto());
					break;
				}
			}
		}
		return clients;
	}

	public Client getClientById(int id) {
		Photo auxiliar = photoRepo.findById(id).orElse(null);
		Client client = repository.findById(id).orElse(null);
		client.setFoto(auxiliar.getFoto());
		return client;
	}

	public String deleteById(int id) {
		photoRepo.deleteById(id);
		repository.deleteById(id);
		return "Client removed";
	}

	public Client updateClient(Client client) {
		Photo newphoto = photoRepo.findById(client.getIdentificacion()).orElse(null);
		newphoto.setFoto(client.getFoto());
		Client existingClient = repository.findById(client.getIdentificacion()).orElse(null);
		existingClient.setApellidos(client.getApellidos());
		existingClient.setCiudadNacimiento(client.getCiudadNacimiento());
		existingClient.setEdad(client.getEdad());
		existingClient.setFoto(client.getFoto());
		existingClient.setNombre(client.getNombre());
		existingClient.setTipoId(client.getTipoId());
		photoRepo.save(newphoto);
		return repository.save(existingClient);
	}

	public List<Client> getClientsByIdType(String idType) {
		List<Client> clientsByIdType = this.getClients();
		List<Client> auxiliarClients = new ArrayList<Client>();
		List<Photo> photos = photoRepo.findAll();
		for (int i = 0; i < clientsByIdType.size(); i++) {
			if (clientsByIdType.get(i).getTipoId().equalsIgnoreCase(idType)) {
				auxiliarClients.add(clientsByIdType.get(i));
			}
		}
		for (int i = 0; i < auxiliarClients.size(); i++) {
			for (int j = 0; j < photos.size(); j++) {
				if (auxiliarClients.get(i).getIdentificacion() == photos.get(j).getIdentificacion()) {
					auxiliarClients.get(i).setFoto(photos.get(j).getFoto());
					break;
				}
			}
		}
		return auxiliarClients;
	}

	public List<Client> getClientsByAge(int age) {
		List<Client> clientsByIdType = this.getClients();
		List<Client> auxiliarClients = new ArrayList<Client>();
		List<Photo> photos = photoRepo.findAll();
		for (int i = 0; i < clientsByIdType.size(); i++) {
			if (clientsByIdType.get(i).getEdad() >= age) {
				auxiliarClients.add(clientsByIdType.get(i));
			}
		}
		for (int i = 0; i < auxiliarClients.size(); i++) {
			for (int j = 0; j < photos.size(); j++) {
				if (auxiliarClients.get(i).getIdentificacion() == photos.get(j).getIdentificacion()) {
					auxiliarClients.get(i).setFoto(photos.get(j).getFoto());
					break;
				}
			}
		}
		return auxiliarClients;
	}
}
