package com.example.petstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.repo.PetRepo;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	@Inject
	PetRepo petRepo ;

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		List<Pet> pets = new ArrayList<Pet>();
		Pet pet1 = new Pet();
		pet1.setPetId(1);
		pet1.setPetAge(3);
		pet1.setPetName("Boola");
		pet1.setPetType("Dog");

		Pet pet2 = new Pet();
		pet2.setPetId(2);
		pet2.setPetAge(4);
		pet2.setPetName("Sudda");
		pet2.setPetType("Cat");

		Pet pet3 = new Pet();
		pet3.setPetId(3);
		pet3.setPetAge(2);
		pet3.setPetName("Peththappu");
		pet3.setPetType("Bird");

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		pet.setPetId(petId);
		pet.setPetAge(3);
		pet.setPetName("Buula");
		pet.setPetType("Dog");

		return Response.ok(pet).build();
		
	}

	// Search Pet by name
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Search Pet by Name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("search/{petName}")
	public Response getPetByName(@PathParam("petName") String name){
		List<Pet> petList = petRepo.findPetsByPetName(name);
		return Response.ok(petList).build();
	}


	// change petType
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "change petType", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("changePetType/{id}/{petType}")
	public Response changePetType(@PathParam("id") int id, @PathParam("petType") String petType){
		Optional<Pet> pet = petRepo.findById(id);
		if(pet.isEmpty()){
			System.out.println("ksjdfdjf");
			return Response.status(404).entity("Not found").build();
		} else{
			pet.get().setPetType(petType);
			Pet pet1 = petRepo.save(pet.get());
			return Response.ok(pet1).build();
		}

	}

	// update PetName
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "update PetName", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("changePetName/{id}/{petName}")
	public Response changePetName(@PathParam("id") int id, @PathParam("petName") String petName){
		Optional<Pet> pet = petRepo.findById(id);
		if(pet.isEmpty()){
			System.out.println("Not Found");
			return Response.status(404).entity("Not found").build();
		} else{
			pet.get().setPetName(petName);
			Pet pet1 = petRepo.save(pet.get());
			return Response.ok(pet1).build();
		}

	}


	// Delete Pet by petType
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete Pet by petType", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("deletePetByPetType/{petType}")
	public Response deletePetByPetType(@PathParam("petType") String petType){

		petRepo.deletePetsByPetType(petType);
		return Response.ok().build();

	}


	// find Pets by petType
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "find Pets by petType", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("findPetsByPetType/{petType}")
	public Response findPetsByPetType(@PathParam("petType") String petType){

		List<Pet> petList = petRepo.findPetsByPetType(petType);
		return Response.ok(petList).build();

	}


	// add new pet
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Add new pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@POST
	@Path("addNewPet")
	public Response addNewPet(@RequestBody Pet pet){

		Pet petRes = petRepo.save(pet);
		return Response.ok(petRes).build();

	}


	// change petType
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Change petType", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("viewPet/{id}")
	public Response viewPet(@PathParam("id") int id){
		Optional<Pet> pet = petRepo.findById(id);
		if(pet.isEmpty()){

			return Response.status(404).entity("Not found").build();
		} else{
			Pet pet1 = petRepo.findById(id).get();
			return Response.ok(pet1).build();
		}

	}


	// get all pets
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "get all pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("getAllPets")
	public Response getAllPets(){
		List<Pet> pets = petRepo.findAll();
		return Response.ok(pets).build();
	}


}
