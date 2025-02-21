package jjgg.academysystem.models;

import jakarta.persistence.*;

@Entity
public class userRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Rol rol;

}
