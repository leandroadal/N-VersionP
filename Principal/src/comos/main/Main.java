package comos.main;


import br.ufal.aracomp.cosmos.emprestimo.impl.ComponentFactory;
import br.ufal.aracomp.cosmos.emprestimo.spec.dt.UsuarioDT;
import br.ufal.aracomp.cosmos.emprestimo.spec.prov.IEmprestimoOps;
import br.ufal.aracomp.cosmos.limite.spec.prov.ILimiteOps;
import br.ufal.aracomp.cosmos.limite2.spec.prov.ILimiteOps2;
import br.ufal.aracomp.cosmos.limite3.spec.prov.ILimiteOps3;
import comos.conectorEmprestimoLimite.ConectorEmprestimoLimite;
import comos.excecao.OperacaoNaoDisponivelException;

public class Main {

	public static void main(String[] args) {
		// Instanciando emprestimo
		br.ufal.aracomp.cosmos.emprestimo.spec.prov.IManager compEmp = 
				ComponentFactory.createInstance();
		// Instanciando Limite1
		br.ufal.aracomp.cosmos.limite.spec.prov.IManager compLimite =
				br.ufal.aracomp.cosmos.limite.impl.ComponentFactory.createInstance();
		// Instanciando Limite2
		br.ufal.aracomp.cosmos.limite2.spec.prov.IManager compLimite2 =
				br.ufal.aracomp.cosmos.limite2.impl.ComponentFactory.createInstance();
		// Instanciando Limite3
		br.ufal.aracomp.cosmos.limite3.spec.prov.IManager compLimite3 =
				br.ufal.aracomp.cosmos.limite3.impl.ComponentFactory.createInstance();
	
		// Instaciando o conector
		ILimiteOps limiteOps;
		ILimiteOps2 limiteOps2;
		ILimiteOps3 limiteOps3;
		limiteOps = (ILimiteOps)compLimite.getProvidedInterface("ILimiteOps");
		limiteOps2 = (ILimiteOps2)compLimite2.getProvidedInterface("ILimiteOps2");
		limiteOps3 = (ILimiteOps3)compLimite3.getProvidedInterface("ILimiteOps3");
		try {
			ConectorEmprestimoLimite conector = new ConectorEmprestimoLimite(limiteOps, limiteOps2, limiteOps3);
		
		
		compEmp.setRequiredInterface("ILimiteReq", conector);
		
		IEmprestimoOps objEmpOps = (IEmprestimoOps)compEmp.getProvidedInterface("IEmprestimoOps");
		UsuarioDT usuario = new UsuarioDT();
		usuario.rendimentos = "1001";
		System.out.println(objEmpOps.liberarEmprestimoAutomatico(usuario));
		} catch (OperacaoNaoDisponivelException e) {
			System.out.println(e.getMessage());
		}

	}

}
