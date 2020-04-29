package com.uca.capas.tarea3.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.text.ParseException;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public String ingresar() {
		return "index";
	}
	
	@RequestMapping("/procesar")
	public ModelAndView parametros(
			@RequestParam String nombre, 
			@RequestParam String apellido,
			@RequestParam String fnacimiento,
			@RequestParam String lnacimiento,
			@RequestParam String instituto,
			@RequestParam String numero,
			@RequestParam String numerotelefonico
		) {
		ModelAndView mav = new ModelAndView();
		List<String> listaerrors = new ArrayList<>();
		
		if(nombre.length()>25 || nombre.length()<1) {
			listaerrors.add("Nombres (mínimo 1 carácter y máximo 25 caracteres)");
		}
		if(apellido.length()>25 || apellido.length()<1) {
			listaerrors.add("Apellidos (mínimo 1 carácter y máximo 25 caracteres)");
		}
		if(lnacimiento.length()>25 || lnacimiento.length()<1) {
			listaerrors.add("Lugar de Nacimiento (mínimo 1 carácter y máximo 25 caracteres)");
		}
		if(instituto.length()>100 || instituto.length()<1) {
			listaerrors.add("Instituto o Colegio de procedencia (mínimo 1 carácter y máximo 100 caracteres)");
		}
		if(numero.length()!=8) {
			listaerrors.add("Teléfono fijo (8 números exactamente)");
		}
		if(numerotelefonico.length()!=8) {
			listaerrors.add("Teléfono movil (8 números exactamente)");
		}
		
		String d1="1-1-2003";
		if(fnacimiento.equals("")) {
			fnacimiento = "1-1-1";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date1 = sdf.parse(d1);
	        Date date2 = sdf.parse(fnacimiento);
	        System.out.println("Date1"+sdf.format(date1));
	        System.out.println("Date2"+sdf.format(date2));System.out.println();

	        if(date2.before(date1)){
	            listaerrors.add("Fecha de Nacimiento (no puede ser menor al 1 de enero de 2003)");
	        }

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		mav.addObject("errores", listaerrors);
		mav.setViewName("procesar");
		

		return mav;
		
		}

}