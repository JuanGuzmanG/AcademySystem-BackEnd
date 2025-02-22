package jjgg.academysystem.entities;

import jakarta.persistence.*;

import java.util.Date;
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
    private Date birthDate;
    private String email;
    private String phoneNumber;

    private Long typeDocument;
    private Long CountryBirth;
    private Long gender;
    private Long bloodType;
    private Long rol;

    private String photo;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    private Set<userRol> userrol = new HashSet<>();

    public User() {
    }

    public User(Long document, String password, String firstName, String middleName, String lastName, String secondLastName, Date birthDate, String email, String phoneNumber, Long typeDocument, Long countryBirth, Long gender, Long bloodType, Long rol, String photo, Set<userRol> userrol) {
        this.document = document;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.typeDocument = typeDocument;
        CountryBirth = countryBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.rol = rol;
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
                ", typeDocument=" + typeDocument +
                ", CountryBirth=" + CountryBirth +
                ", gender=" + gender +
                ", bloodType=" + bloodType +
                ", rol=" + rol +
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(Long typeDocument) {
        this.typeDocument = typeDocument;
    }

    public Long getCountryBirth() {
        return CountryBirth;
    }

    public void setCountryBirth(Long countryBirth) {
        CountryBirth = countryBirth;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getBloodType() {
        return bloodType;
    }

    public void setBloodType(Long bloodType) {
        this.bloodType = bloodType;
    }

    public Long getRol() {
        return rol;
    }

    public void setRol(Long rol) {
        this.rol = rol;
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
