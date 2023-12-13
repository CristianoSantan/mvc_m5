package mvc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mvc.model.Compras;
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

		return modelAndView;
	}

	// Aviso: Removido parâmetro desnecessário
	// O parâmetro "ModelMap model" foi removido do método "cadastrar" porque não está sendo usado para adicionar atributos à camada de visualização (pagina html).
	// Se futuramente for necessário adicionar dados à camada de visualização, o parâmetro e as linhas correspondentes podem ser reintroduzidos.
	// Foi removido também a validação com as anotações @Validated e o parâmetro "BindingResult result", para o codigo ficar mais limpo sem muitas camadas.
	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute("compras") Compras compras) {
        comprasRepository.save(compras);

		return "redirect:/compras";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		comprasRepository.deleteById(id);

		return "redirect:/compras";
	}
}
