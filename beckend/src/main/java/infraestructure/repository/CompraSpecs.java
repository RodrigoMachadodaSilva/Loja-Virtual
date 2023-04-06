package infraestructure.repository;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.loja.lojavirtual.entity.Compra;
import com.loja.lojavirtual.repository.filter.CompraFilter;

public class CompraSpecs {

	public static Specification<Compra> usandoFiltro(CompraFilter filtro) {
		return (root, query, builder) -> {
			root.fetch("cliente");

			var predicates = new ArrayList<Predicate>();

			if (filtro.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
			}



			if (filtro.getDataCriacao() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacao()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
