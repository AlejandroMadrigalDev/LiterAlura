package com.aluracursos.LiterAlura.service;

import tools.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        return mapper.readValue(json, clase);
    }
}
