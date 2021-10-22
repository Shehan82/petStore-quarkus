package com.example.petstore;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Schema(name = "Pet")
@Entity
@Table(name = "pet")
public class Pet {

	@Schema(required = true, description = "Pet id")
	@JsonProperty("pet_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_id")
	private Integer petId;

	@Schema(required = true, description = "Pet type")
	@JsonProperty("pet_type")
	@Column(name = "pet_type")
	private String petType;

	@Schema(required = true, description = "Pet name")
	@JsonProperty("pet_name")
	@Column(name = "pet_name")
	private String petName;

	@JsonProperty("pet_age")
	@Column(name = "pet_age")
	private Integer petAge;

	public Pet(String petType, String petName, Integer petAge) {
		this.petType = petType;
		this.petName = petName;
		this.petAge = petAge;
	}

	public Pet() {
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public Integer getPetAge() {
		return petAge;
	}

	public void setPetAge(Integer petAge) {
		this.petAge = petAge;
	}

}
