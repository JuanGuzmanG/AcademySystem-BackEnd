package jjgg.academysystem.DTO;

import jjgg.academysystem.entities.Rol;

import java.time.LocalDate;
import java.util.Set;

public class UserResponseDTO {
    private Long document;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthDate;
    private String email;
    private String countryBirth;
    private Long phoneNumber;
    private String gender;
    private String bloodType;
    private String photo;
    private String documentType;
    private Set<Rol> rols;

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryBirth() {
        return countryBirth;
    }

    public void setCountryBirth(String countryBirth) {
        this.countryBirth = countryBirth;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Set<Rol> getRols() {
        return rols;
    }

    public void setRols(Set<Rol> rols) {
        this.rols = rols;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "document=" + document +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", countryBirth='" + countryBirth + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", gender='" + gender + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", photo='" + photo + '\'' +
                ", documentType='" + documentType + '\'' +
                ", rols=" + rols +
                '}';
    }
}

