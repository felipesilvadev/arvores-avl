package arvore_avl;

public class No {
	private No esquerda, direita, pai;
	private Integer valor, balanceamento;
	
	public No(Integer valor) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
		setValor(valor);
	}
	
	public Integer getValor() {
		return this.valor;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public Integer getBalanceamento() {
		return this.balanceamento;
	}
	
	public void setBalanceamento(Integer balanceamento) {
		this.balanceamento = balanceamento;
	}
	
	public No getPai() {
		return pai;
	}

	public No setPai(No pai) {
		return this.pai = pai;
	}

	public No getDireita() {
		return direita;
	}

	public No setDireita(No direita) {
		return this.direita = direita;
	}
	
	public No getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}
}
