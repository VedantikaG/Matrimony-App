package com.yaksha.training.matrimony.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Matrimony")
public class Matrimony {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 40)
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank
    @Size(min = 2, max = 100)
    private String address;

    @NotBlank
    private String religion;

    @NotBlank
    private String occupation;

    private String hobbies;

    @NotNull
    private Boolean is_match_found = false;

    public Matrimony() {
    }

    public Matrimony(Long id, String fullName, Gender gender, String address, String religion, String occupation, String hobbies) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.religion = religion;
        this.occupation = occupation;
        this.hobbies = hobbies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public Boolean getIs_match_found() {
        return is_match_found;
    }

    public void setIs_match_found(Boolean is_match_found) {
        this.is_match_found = is_match_found;
    }

    @Override
    public String toString() {
        return "Matrimony{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", religion='" + religion + '\'' +
                ", occupation='" + occupation + '\'' +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }
}
