package com.example.petstore.repo;

import com.example.petstore.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepo extends JpaRepository<Pet, Integer> {
//    @Query("SELECT a from Pet a where a.petName = :pet_name")
//    public List<Pet> findPetByName(@Param("pet_name") String pet_name);

    public List<Pet> findPetsByPetName(String petName);
    public void deletePetsByPetType(String petType);
    public List<Pet> findPetsByPetType(String petType);





}
