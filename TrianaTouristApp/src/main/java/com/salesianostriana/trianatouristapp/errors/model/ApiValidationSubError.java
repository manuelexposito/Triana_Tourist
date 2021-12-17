package com.salesianostriana.trianatouristapp.errors.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiValidationSubError extends ApiSubError{

    private String objeto, campo, mensaje;

    private Object valorRechazado;


}
