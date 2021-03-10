package arvore_avl;

public class TesteArvoreAvl {

	public static void main(String[] args) {
		ArvoreAvl arvore = new ArvoreAvl();
		
		arvore.inserir(10);
		arvore.inserir(5);
		arvore.inserir(8);
		arvore.inserir(2);
		arvore.inserir(7);
		arvore.inserir(12);
		arvore.imprimirInterfixado(arvore.getRaiz());
		
		System.out.println();
		
		arvore.remover(7);
		arvore.remover(5);
		arvore.imprimirInterfixado(arvore.getRaiz());
	}

}
