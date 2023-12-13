package mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mvc.model.Usuarios;
import mvc.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ModelAndView usuario() {
		ModelAndView modelAndView = new ModelAndView("views/usuarios/index.html");
		
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		modelAndView.addObject("usuario", new Usuarios());

		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Usuarios usuario) {
		usuarioRepository.save(usuario);

		return "redirect:/usuarios";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		usuarioRepository.deleteById(id);

		return "redirect:/usuarios";
	}

}
