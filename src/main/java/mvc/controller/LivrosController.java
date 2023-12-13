package mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mvc.model.Livros;
import mvc.repository.LivrosRepository;

@Controller
@RequestMapping("/livros")
public class LivrosController {
    
	@Autowired
	private LivrosRepository livrosRepository;

	@GetMapping
	public ModelAndView livros() {
		ModelAndView modelAndView = new ModelAndView("views/livros/index.html");
		
		modelAndView.addObject("livros", livrosRepository.findAll());
		modelAndView.addObject("livro", new Livros());

		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Livros livros) {
		livrosRepository.save(livros);

		return "redirect:/livros";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		livrosRepository.deleteById(id);

		return "redirect:/livros";
	}

}
