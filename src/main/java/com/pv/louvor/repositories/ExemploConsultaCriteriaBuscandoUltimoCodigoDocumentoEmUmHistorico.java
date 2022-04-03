@Override
	public Page<VistoriaViatura> buscaViaturaDisposnivel(PrefixoUnidadeFilter prefixoUnidadeFilter, Pageable pageable) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<VistoriaViatura> query = cb.createQuery(VistoriaViatura.class);
		
		Root<VistoriaViatura> vistoriaViatura = query.from(VistoriaViatura.class);
		
		Join<VistoriaViatura, Viatura> joinViatura = vistoriaViatura.join("viatura");
		Join<Viatura, ViaturaUpm> joinViaturaUpm = joinViatura.join("listaViaturaUpm");
		Join<ViaturaUpm, UnidadePolicialMilitar> joinUnidadePolicialMilitar = joinViaturaUpm.join("upm");
		
		
		
		Predicate[] predicates = criarRestricoes(prefixoUnidadeFilter, 
												 cb,
												 joinViatura, 
												 joinViaturaUpm,
												 joinUnidadePolicialMilitar,
												 vistoriaViatura, query);
		
		query.select(vistoriaViatura);
		
		TypedQuery<VistoriaViatura> typedQuery = manager.createQuery(query.where(predicates));
		
		adicionarRestricoesDePaginacao(typedQuery, pageable);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable,total(prefixoUnidadeFilter));
	}
	
	private Predicate[] criarRestricoes(PrefixoUnidadeFilter prefixoUnidadeFilter, CriteriaBuilder cb,
			Join<VistoriaViatura, Viatura> joinViatura, Join<Viatura, ViaturaUpm> joinViaturaUpm,
			Join<ViaturaUpm, UnidadePolicialMilitar> joinUnidadePolicialMilitar, Root<VistoriaViatura> vistoriaViatura,
			CriteriaQuery<?> query) {

		List<Predicate> predicates = new ArrayList<>();
		
		Subquery<Integer> subQuery = query.subquery(Integer.class);
		
		Root<VistoriaViatura> vistoria = subQuery.from(VistoriaViatura.class);
		subQuery.select(cb.max(vistoria.get("id"))).where(cb.equal(vistoriaViatura.get("viatura"), vistoria.get("viatura")));
		Predicate ultimaVistoria = cb.and(vistoriaViatura.get("id").in(subQuery));
		predicates.add(ultimaVistoria); // codigo em criteria que busca o ultimo item do historico de cada documento
		
		
			return predicates.toArray(new Predicate[predicates.size()]);
	}

	select * from log_documento_criticado logdocumen0_ where logdocumen0_.cod_log_documento_criticado in (select max(logdocumen1_.cod_log_documento_criticado) from log_documento_criticado logdocumen1_ where logdocumen0_.cod_documento_criticado=logdocumen1_.cod_documento_criticado) // codigo sql que busca o ultimo item do historico de cada documento