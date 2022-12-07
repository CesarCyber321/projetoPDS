package controllers;

import java.util.List;

import models.Cadastro;
import play.mvc.Controller;

public class Cadastros extends Controller {
	public static void inicio() {
		render();
	}
	public static void form() {
		render();
	}
	

	public static void salvar(Cadastro a) {
		a.nome = a.nome.toUpperCase();
		a.email = a.email.toLowerCase();
		
		a.agente = a.agente.toUpperCase();
		a.elo = a.elo.toUpperCase();
		
		a.save();
		form();
		
		
		
		
		
	}

	public static void listar() {
		String busca = params.get("busca");
		List<Cadastro> lista;
		if(busca == null) {
			 lista = Cadastro.findAll();
		}else {
			lista = Cadastro.find("select c from Cadastro c "
					+" where c.nome like ?1 or c.email like ?2 or "
					+"c.agente like ?3 or "
					+"c.elo like ?4", 
					"%"+busca.toLowerCase()+"%", 
					"%"+busca.toLowerCase()+"%",
					"%"+busca.toLowerCase()+"%", 
					"%"+busca.toLowerCase()+"%").fetch();
		}
		
		render(lista);
	}
	
	public static void editar(long id) {
		Cadastro a =Cadastro.findById(id);
		renderTemplate("Cadastros/form.html", a);
	}
	
	public static void deletar(long id) {
		Cadastro usu =Cadastro.findById(id);
		usu.delete();
		listar();
	}

}
