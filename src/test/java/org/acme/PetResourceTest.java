package org.acme;

import com.example.petstore.Pet;
import com.example.petstore.repo.PetRepo;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PetResourceTest {

    @Inject
    PetRepo petRepo ;

    @Test
    public void testPetEndpoint() {
        given()
                .when().get("/v1/pets")
                .then()
                .statusCode(200)
             .body(hasItem(
 		            allOf(
    		                hasEntry("pet_id", "1"),
    		                hasEntry("pet_type", "Dog"),
    		                hasEntry("pet_name", "Boola"),
    		                hasEntry("pet_age", "3")
    		            )
    		      )
    		 );
    }

    @Test
    public void testGetPetByName() {

        given().contentType(MediaType.APPLICATION_JSON)
                .when().get("/v1/pets/search/Sisi")
                .then()
                .statusCode(200).body("size", notNullValue());
    }

    @Test
    public void testChangePetType() {

        given().contentType(MediaType.APPLICATION_JSON)
                .when().get("/v1/pets/changePetType/1/Bird")
                .then()
                .statusCode(200)
                .body("petType", is("Bird"));
    }

    @Test
    public void testChangePetName() {

        given().contentType(MediaType.APPLICATION_JSON)
                .when().get("/v1/pets/changePetName/1/Sese")
                .then()
                .statusCode(200)
                .body("petName", is("Sese"));
    }

    @Test
    public void testDeletePetByPetType() {
        Pet pet = new Pet();
        pet.setPetName("Peththa");
        pet.setPetType("Bird");
        pet.setPetAge(2);
        petRepo.save(pet);

        given().contentType(MediaType.APPLICATION_JSON)
                .when().get("/v1/pets/deletePetByPetType/Bird")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFindPetsByPetType() {

        given().contentType(MediaType.APPLICATION_JSON)
                .when().get("/v1/pets/findPetsByPetType/Dog")
                .then()
                .statusCode(200)
                .body("size", notNullValue());
    }

    @Test
    public void testAddNewPet() {
        Pet pet = new Pet();
        pet.setPetName("Tom");
        pet.setPetType("Dog");
        pet.setPetAge(6);

        given().contentType(MediaType.APPLICATION_JSON)
                .body(pet)
                .when().post("/v1/pets/addNewPet")
                .then()
                .statusCode(200)
                .body("petAge", is("Tom"));
    }

    @Test
    public void testViewPet() {
        Pet pet = new Pet();
        pet.setPetId(1);
        pet.setPetName("Tom");
        pet.setPetType("Dog");
        pet.setPetAge(6);
        petRepo.save(pet);

        given().contentType(MediaType.APPLICATION_JSON)
                .when().get("/v1/pets/viewPet/1")
                .then()
                .statusCode(200)
                .body("petName", is("Tom"));
    }

    @Test
    public void testGetAllPets() {
        given().contentType(MediaType.APPLICATION_JSON)
                .when().get("/v1/pets/getAllPets")
                .then()
                .statusCode(200)
                .body("size", notNullValue());
    }

}