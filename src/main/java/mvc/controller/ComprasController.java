package mvc.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mvc.model.Compras;
import mvc.model.Usuarios;
import mvc.model.Livros;
import mvc.repository.ComprasRepository;
import mvc.repository.LivrosRepository;
import mvc.repository.UsuarioRepository;

@Controller
@RequestMapping("/compras")
public class ComprasController {
        
	@Autowired
	private ComprasRepository comprasRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LivrosRepository livrosRepository;

	@GetMapping
	public ModelAndView compras() {
		ModelAndView modelAndView = new ModelAndView("views/compras/index.html");

		modelAndView.addObject("listaUsuarios", usuarioRepository.findAll());
		modelAndView.addObject("listaLivros", livrosRepository.findAll());
		modelAndView.addObject("compras", comprasRepository.findAll());
		modelAndView.addObject("compra", new Compras());
		// modelAndView.addObject("livro", new Livros());
		// modelAndView.addObject("usuario", new Usuarios());

		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Validated @ModelAttribute("compras") Compras compras, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "compras";
        }

        model.addAttribute("id", compras.getId());
        model.addAttribute("data_compra", compras.getData_compra());
        model.addAttribute("data_compra", compras.getData_compra());
        model.addAttribute("livro", compras.getLivro());
        model.addAttribute("usuario", compras.getUsuario());

        comprasRepository.save(compras);

		return "redirect:/compras";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		comprasRepository.deleteById(id);

		return "redirect:/compras";
	}
}
