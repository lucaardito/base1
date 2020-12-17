package it.corso.springboot.esempibase.controllers;

import it.corso.springboot.esempibase.data.FormDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class HomeController {

    @Value("${homepage}")
    private String home;

    @Value("${tablepage}")
    private String table;

    @Value("${afterpost}")
    private String afterPost;


    @RequestMapping("/")
    public String welcome(Model model) {
        String pattern = "EEEEE dd MMMMM yyyy HH:mm:ss.SSSZ";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        model.addAttribute("now", sdf.format(new Date()));
        return home;
    }

    @RequestMapping("/table/{name}")
    public String testTable(@PathVariable String name, @RequestParam(required=false, name="cognome") String cognome, Model model) {
        List<String> headers =Arrays.asList("Matricola", "Nome", "Stipendio", "Stato");
        List<Map<String, Object>> persone = new ArrayList<>();

        persone.add(mapFromData("10201A", "Mario Rossi", "50000", "attivo"));
        persone.add(mapFromData("12346V", "Sergio Bianchi", "34000", "attivo"));
        persone.add(mapFromData("4353AA", "Marco Neri", "14000", "cassa integrazione"));

        model.addAttribute("nome", name);
        if (cognome == null) {
            cognome = " ";
        }
        model.addAttribute("cognome", cognome);

        model.addAttribute("headers", headers);
        model.addAttribute("righe", persone);

        return table;
    }
    @PostMapping("/testform")
    public String post(FormDTO dto, Model model) {
        model.addAttribute("a", dto.getA());
        model.addAttribute("b", dto.getB());
        return afterPost;
    }

    private Map<String, Object> mapFromData(String matricola, String nome, String salario, String stato) {
        Map<String, Object> persona = new HashMap<>();
        persona.put("Matricola", matricola);
        persona.put("Nome", nome);
        persona.put("Stipendio", salario);
        persona.put("Stato", stato);
        return persona;
    }


}
