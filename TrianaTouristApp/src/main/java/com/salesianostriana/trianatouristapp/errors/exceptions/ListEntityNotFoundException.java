package com.salesianostriana.trianatouristapp.errors.exceptions;

public class ListEntityNotFoundException extends EntityNotFoundException{

    public ListEntityNotFoundException(Class clase){

        super(String.format("No se han podido encontrar los elementos del tipo %s",clase));

    }
}
