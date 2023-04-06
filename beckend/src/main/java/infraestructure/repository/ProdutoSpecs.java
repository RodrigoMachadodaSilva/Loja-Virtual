package infraestructure.repository;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.loja.lojavirtual.entity.Produto;
import com.loja.lojavirtual.repository.filter.ProdutoFilter;

public class ProdutoSpecs {

	public static Specification<Produto> usandoFiltro(ProdutoFilter filtro) {
		return (root, query, builder) -> {
			root.fetch("categoria"); //Resolve o problema do N+1
			root.fetch("marca");

			var predicates = new ArrayList<Predicate>();

			if (filtro.getCategoriaId() != null) {
				predicates.add(builder.equal(root.get("categoria"), filtro.getCategoriaId()));
			}

			if (filtro.getMarcaId() != null) {
				predicates.add(builder.equal(root.get("marca"), filtro.getMarcaId()));
			}
			if (filtro.getNome() != null) {
				predicates.add(builder.equal(root.get("nome"), filtro.getNome()));
			}

			if (filtro.getValor_Venda() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("valor_Venda"), filtro.getValor_Venda()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
