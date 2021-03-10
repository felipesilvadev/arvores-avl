package arvore_avl;

public class ArvoreAvl {
	private No raiz;
	
	public No getRaiz() {
		return raiz;
	}
	
	public void inserir(Integer valor) {
		No novoNo = new No(valor);
		inserirAVL(this.raiz, novoNo);
	}
	
	public void inserirAVL(No noBase, No noInserir) {
		if (noBase == null) {
			this.raiz = noInserir;
			return;
		}
		
		if (noInserir.getValor() < noBase.getValor()) {
			if (noBase.getEsquerda() == null) {
				noBase.setEsquerda(noInserir);
				noInserir.setPai(noBase);
				verificarBalanceamento(noBase);
				return;
			}
			
			inserirAVL(noBase.getEsquerda(), noInserir);
			return;
		} 
		
		if (noInserir.getValor() > noBase.getValor()) {
			if (noBase.getDireita() == null) {
				noBase.setDireita(noInserir);
				noInserir.setPai(noBase);
				verificarBalanceamento(noBase);
				return;
			}
			
			inserirAVL(noBase.getDireita(), noInserir);
			return;
		}
		
		System.out.println("Valor " + noInserir.getValor() + " já existente na árvore");
	}
	
	public void remover(Integer valor) {
		removerAVL(this.raiz, valor);
	}
	
	public void removerAVL(No noBase, Integer valor) {
		if (noBase == null) {
			return;
		}
		
		if (noBase.getValor() > valor) {
			removerAVL(noBase.getEsquerda(), valor);
			return;
		}
		
		if (noBase.getValor() < valor) {
			removerAVL(noBase.getDireita(), valor);
			return;
		}
		
		removerNoEncontrado(noBase);
	}
	
	public void removerNoEncontrado(No noRemover) {
		No aux;
		
		if (noRemover.getEsquerda() == null || noRemover.getDireita() == null) {
			if (noRemover.getPai() == null) {
				this.raiz = null;
				return;
			}
			
			aux = noRemover;
		} else {
			aux = sucessor(noRemover);
			noRemover.setValor(aux.getValor());
		}
		
		No aux2;
		
		if (aux.getEsquerda() != null) {
			aux2 = aux.getEsquerda();
		} else {
			aux2 = aux.getDireita();
		}
		
		if (aux2 != null) {
			aux2.setPai(aux.getPai());
		}
		
		if (aux.getPai() == null) {
			this.raiz = aux2;
		} else {
			if (aux == aux.getPai().getEsquerda()) {
				aux.getPai().setEsquerda(aux2);
			} else {
				aux.getPai().setDireita(aux2);
			}
			verificarBalanceamento(aux.getPai());
		}
		
		aux = null;
	}
	
	public void verificarBalanceamento(No noBase) {
		setBalanceamento(noBase);
		int balanceamento = noBase.getBalanceamento();
		
		if (balanceamento == -2) {
			if(altura(noBase.getEsquerda().getEsquerda()) >= altura(noBase.getEsquerda().getDireita())) {
				noBase = rotacaoDireita(noBase);
				
			} else {
				noBase = duplaRotacaoEsquerdaDireita(noBase);
			}
		} else if (balanceamento == 2) {
			if (altura(noBase.getDireita().getDireita()) >= altura(noBase.getDireita().getEsquerda())) {
				noBase = rotacaoEsquerda(noBase);
			} else {
				noBase = duplaRotacaoDireitaEsquerda(noBase);
			}
		}
		
		if (noBase.getPai() != null) {
			verificarBalanceamento(noBase.getPai());
		} else {
			this.raiz = noBase;
		}
	}
	
	public No rotacaoDireita(No inicial) {
		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());
		inicial.setEsquerda(esquerda.getDireita());
		
		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}
		
		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);
		
		if (esquerda.getPai() != null) {
			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}
		
		setBalanceamento(inicial);
		setBalanceamento(esquerda);
		
		return esquerda;
	}
	
	public No rotacaoEsquerda(No inicial) {
		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());
		inicial.setDireita(direita.getEsquerda());
		
		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}
		
		direita.setEsquerda(inicial);
		inicial.setPai(direita);
		
		if (direita.getPai() != null) {
			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}
		
		setBalanceamento(inicial);
		setBalanceamento(direita);
		
		return direita;
	}
	
	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}
	
	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}
	
	public int altura(No noBase) {
		if (noBase == null) {
			return -1;
		}
		
		if (noBase.getEsquerda() == null && noBase.getDireita() == null) {
			return 0;
		}
		
		if (noBase.getEsquerda() == null) {
			return altura(noBase.getDireita()) + 1;
		}
		
		if (noBase.getDireita() == null) {
			return altura(noBase.getEsquerda()) + 1;
		}
		
		return Math.max(altura(noBase.getEsquerda()), altura(noBase.getDireita())) + 1;
	}
	
	public No sucessor(No noBase) {
		if (noBase.getDireita() != null) {
			No direita = noBase.getDireita();
			
			while (direita.getEsquerda() != null) {
				direita = direita.getEsquerda();
			}
			
			return direita;
		}
		
		No pai = noBase.getPai();
		
		while (pai != null && noBase == pai.getDireita()) {
			noBase = pai;
			pai = noBase.getPai();
		}
		
		return pai;
	}
	
	public void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}	
	
	public void imprimirPrefixado(No noBase) {
		if (noBase != null) {
			System.out.print(noBase.getValor() + "\t");
			imprimirPrefixado(noBase.getEsquerda());
			imprimirPrefixado(noBase.getDireita());
		}	
	}
	
	public void imprimirInterfixado(No noBase) {
		if (noBase != null) {
			imprimirInterfixado(noBase.getEsquerda());
			System.out.print(noBase.getValor() + "\t");
			imprimirInterfixado(noBase.getDireita());
		}	
	}
	
	public void imprimirPosfixado(No noBase) {
		if (noBase != null) {
			imprimirPosfixado(noBase.getEsquerda());
			imprimirPosfixado(noBase.getDireita());
			System.out.print(noBase.getValor() + "\t");
		}	
	}
}
 