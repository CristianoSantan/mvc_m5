
# Aviso ⚠

*ComprasController.java*

Aviso: Removido parâmetro desnecessário 
O parâmetro "ModelMap model" foi removido do método "cadastrar" porque não está sendo usado para adicionar atributos à camada de visualização (pagina html).
Se futuramente for necessário adicionar dados à camada de visualização, o parâmetro e as linhas correspondentes podem ser reintroduzidos.
Foi removido também a validação com as anotações @Validated e o parâmetro "BindingResult result", para o codigo ficar mais limpo sem muitas camadas.
Obs.: O erro estava no formulario.

```
@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute("compras") Compras compras) {

		// Logica provisoria para salvar o preço do livro em compras.
		double preco = livrosRepository.findById(compras.getLivro().getId()).get().getPreco();
		compras.setTotal_compra(preco);

        comprasRepository.save(compras);

		return "redirect:/compras";
	}
```
