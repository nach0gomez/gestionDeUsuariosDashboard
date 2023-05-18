package com.gestionusuarios.dashboard.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity // * indicamos que hace referencia hacia la BD
@Table(name = "usuarios") //* aqui le indicamos a que tabla debe de ir, hibernate tambien necesita la columna que es
                            // * y cual es su PK, con la anotacion ID
@ToString @EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "password")
    private String password;


}
