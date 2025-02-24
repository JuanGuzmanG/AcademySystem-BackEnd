package jjgg.academysystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false)
    private Long document;

    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthDate;
    private String email;
    private String CountryBirth;
    private Long phoneNumber;
    private String gender;
    private String bloodType;
    private String photo;
    private String documentType;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    private Set<userRol> userrol = new HashSet<>();

    public User() {
    }

    public User(Long document, String password, String firstName, String middleName, String lastName,
                String secondLastName, LocalDate birthDate, String email, Long phoneNumber,
                String documentType, String countryBirth, String gender, String bloodType,
                String photo, Set<userRol> userrol) {
        this.document = document;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.documentType = documentType;
        CountryBirth = countryBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.photo = photo;
        this.userrol = userrol;
    }

    @Override
    public String toString() {
        return "User{" +
                "document=" + document +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", typeDocument=" + documentType +
                ", CountryBirth=" + CountryBirth +
                ", gender=" + gender +
                ", bloodType=" + bloodType +
                ", photo='" + photo + '\'' +
                ", userrol=" + userrol +
                '}';
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getCountryBirth() {
        return CountryBirth;
    }

    public void setCountryBirth(String countryBirth) {
        CountryBirth = countryBirth;
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

    public Set<userRol> getUserrol() {
        return userrol;
    }

    public void setUserrol(Set<userRol> userrol) {
        this.userrol = userrol;
    }

}
