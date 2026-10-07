package mx.edu.utez.proyecto1C.model.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1C.model.persona.Persona;

@Entity
@Table(name = "usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "username1",
            nullable = false,
            unique = true
    )
    private String username;
    private String password;

    private boolean isEnable;

    @Enumerated(EnumType.STRING)
    private Roles rol;


    @Transient
    private String campoPrueba;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;


}
