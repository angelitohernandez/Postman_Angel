package mx.edu.utez.proyecto1C.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospedajeDTO {

    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    @Pattern(
            regexp = "^(INDIVIDUAL|DOBLE|SUITE)$",
            message = "El tipo de habitación debe ser INDIVIDUAL, DOBLE o SUITE"
    )
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio")
    @Min(value = 1, message = "El número de noches debe ser al menos 1")
    @Max(value = 30, message = "La reservación no puede superar las 30 noches")
    private Integer numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 huésped")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    @Pattern(
            regexp = "^(BAJA|REGULAR|ALTA)$",
            message = "La temporada debe ser BAJA, REGULAR o ALTA"
    )
    private String temporada;

    @NotNull(message = "Debe indicar si incluye desayuno")
    private Boolean incluyeDesayuno;

    @NotNull(message = "Debe indicar si incluye estacionamiento")
    private Boolean incluyeEstacionamiento;
}
