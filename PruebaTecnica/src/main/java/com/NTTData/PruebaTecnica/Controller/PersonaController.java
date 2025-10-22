package com.NTTData.PruebaTecnica.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class PersonaController {

    @GetMapping("/personas")
    public List<Map<String, Object>> getPersonas() {

        String url = "https://randomuser.me/api/?results=10&inc=gender,name,location,email,dob,picture&noinfo";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> res = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> ressponse = (List<Map<String, Object>>) res.get("results");

        return ressponse.stream().map(r -> {
            Map<String, Object> persona = new LinkedHashMap<>();

            Map<String,Object> name = (Map<String, Object>) r.get("name");
            Map<String,Object> location = (Map<String, Object>) r.get("location");
            Map<String,Object> street = (Map<String, Object>) r.get("street");
            Map<String,Object> dob = (Map<String, Object>) r.get("dob");
            Map<String,Object> picture = (Map<String, Object>) r.get("picture");



            persona.put("nombre", name.get("title") + " " + name.get("first") + " " + name.get("last"));
            persona.put("genero", r.get("gender"));
            persona.put("ubicacionn", location.get("country"));
            persona.put("correo", r.get("email"));
            persona.put("fechaNacimiento", dob.get("date"));
            persona.put("foto", picture.get("large"));

            return persona;

        }).collect(Collectors.toList());


    }

}
