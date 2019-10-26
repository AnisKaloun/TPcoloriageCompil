package Algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

class sommet {
	char name;
	int nbrArrete;
	public sommet(char name) {
		this.name = name;
		this.nbrArrete=0;
	}
	public int getNbrArrete() {
		return nbrArrete;
	}
	public void setNbrArrete(int nbrArrete) {
		this.nbrArrete = nbrArrete;
	}


}

class arrete{
	int type;
	sommet a;
	sommet b;
	public arrete(int type, sommet a, sommet b) {
		this.type = type;
		this.a = a;
		this.b = b;
	}


}

class sommetColore
{
	sommet s;
	int color;
	public sommetColore(sommet s,int color)
	{
		this.s=s;
		this.color=color;
	}

}

class graph implements Cloneable {
	ArrayList <sommet> Sommets;
	ArrayList <arrete> Arretes;

	public graph(ArrayList<sommet> sommets, ArrayList<arrete> arretes) {
		Arretes=arretes;
		Sommets=sommets;
	}

	public graph() {
		ArrayList <sommet> Sommets=new ArrayList<sommet>();
		ArrayList <arrete> Arretes=new ArrayList<arrete>();
		// TODO Auto-generated constructor stub
	}

	public graph(graph g) {
		Sommets=(ArrayList<sommet>) g.Sommets.clone();
		for (int i=0;i<Sommets.size();i++)
		{
			Sommets.get(i).setNbrArrete(0);	
		}
		Arretes=(ArrayList<arrete>)g.Arretes.clone();
		// TODO Auto-generated constructor stub
	}

	void AfficherGraph(graph G)
	{
		System.out.println("on affiche les sommets \n");
		for(int i=0;i<G.Sommets.size();i++)
		{
			System.out.printf("Sommet %d : %c nombre de voisins %d \n",i,G.Sommets.get(i).name,G.Sommets.get(i).getNbrArrete());	
		}

		System.out.println("on affiche les arretes \n");
		System.out.println("type 0:arrete interference \n");
		System.out.println("type 1:arrete preference \n");
		for(int i=0;i<G.Arretes.size();i++)
		{
			System.out.printf("Arrete %d type  %d :couple (%c,%c) \n",i,G.Arretes.get(i).type,Arretes.get(i).a.name,Arretes.get(i).b.name);	
		}

	}

	void RemplirDegres(graph G)
	{
		for(int i=0;i<this.Sommets.size();i++)
		{
			for (int j=0;j<this.Arretes.size();j++)
			{
				if(this.Arretes.get(j).a==this.Sommets.get(i) ||this.Arretes.get(j).b==this.Sommets.get(i))
				{
					int x=this.Sommets.get(i).getNbrArrete();
					this.Sommets.get(i).setNbrArrete(x+1);
				}

			}
		}
	}

	void EnleverSommetEtArrete(graph G,sommet s)
	{
		G.Sommets.remove(s);
		for(int i=0;i<G.Arretes.size();i++)
		{
			if(G.Arretes.get(i).a==s ||G.Arretes.get(i).b==s)
			{
				if(G.Arretes.get(i).a==s)
				{
					sommet index=G.Arretes.get(i).b;
					int x=this.Sommets.get(this.Sommets.indexOf(index)).getNbrArrete();
					this.Sommets.get(this.Sommets.indexOf(index)).setNbrArrete(x-1);	
				}
				else
				{
					sommet index=G.Arretes.get(i).a;
					int x=this.Sommets.get(this.Sommets.indexOf(index)).getNbrArrete();
					this.Sommets.get(this.Sommets.indexOf(index)).setNbrArrete(x-1);		
				}
				G.Arretes.remove(i);

			}

		}

	}

}

public class algo {

	public static void main(String[] args) {
		ArrayList <sommet> sommet= new ArrayList<sommet>();
		sommet x=new sommet('x');
		sommet y=new sommet('y');
		sommet u=new sommet('u');
		sommet v=new sommet('v');
		sommet t=new sommet('t');
		sommet z=new sommet('z');
		sommet.add(x);
		sommet.add(y);
		sommet.add(u);
		sommet.add(v);
		sommet.add(t);
		sommet.add(z);
		ArrayList <arrete> arretes=new ArrayList<arrete>();
		arretes.add(new arrete(0,x,v));
		arretes.add(new arrete(0,x,u));
		arretes.add(new arrete(0,x,y));
		arretes.add(new arrete(0,y,t));
		arretes.add(new arrete(0,y,u));
		arretes.add(new arrete(1,u,t));
		arretes.add(new arrete(0,v,t));
		arretes.add(new arrete(0,v,z));
		graph G = new graph(sommet,arretes);
		G.RemplirDegres(G);
		G.AfficherGraph(G);
		System.out.print("donnez le k a colorier\n");
		Scanner sc=new Scanner(System.in);
		int H=sc.nextInt();
		algo g=new algo();        
		sommet Petit=g.ChercherSommetPlusPetitDegres(G);
		if(!g.isColoriable(Petit,H))
		{
			System.out.print("le graph n'ai pas k coloriable \n");	
			sc.close();
			return;	
		}
		ArrayList <sommetColore> ColoredSommet= new ArrayList<sommetColore>();
		graph source =new graph(G);
		source.RemplirDegres(source);
		g.AlgoColoriage(G,H,source,ColoredSommet);
		sc.close();
		for (int i=0;i<ColoredSommet.size();i++)
		{
			System.out.println("sommet "+ColoredSommet.get(i).s.name+" couleur "+ColoredSommet.get(i).color);	
		}

	}
	boolean isColoriable(sommet s,int k)
	{
		if(s.getNbrArrete()>=k)
		{
			return false;
		}
		return true;
	}
	sommet ChercherSommetPlusPetitDegres(graph G)
	{
		sommet Petit=G.Sommets.get(0);	
		for(int i=0 ; i<G.Sommets.size();i++)
		{
			if(G.Sommets.get(i).getNbrArrete()<=Petit.getNbrArrete())
			{
				Petit=G.Sommets.get(i);
			}

		}
		return Petit;
	}

	sommet ChercherSommetPlusGrandDegres(graph G)
	{
		sommet Grand=G.Sommets.get(0);	
		for(int i=0 ; i>G.Sommets.size();i++)
		{
			if(G.Sommets.get(i).getNbrArrete()<=Grand.getNbrArrete())
			{
				Grand=G.Sommets.get(i);
			}

		}
		return Grand;
	}

	void AlgoColoriage(graph G,int k,graph source, ArrayList<sommetColore> coloredSommet)
	{

		sommet x=this.ChercherSommetPlusPetitDegres(G);
		System.out.println("on choisis "+x.name+"\n");
		if(!this.isColoriable(x, k))
		{
			System.out.println("on doit splitter içi \n");	
		}
		else
		{
			//içi on est dans le cas ou on peut eliminer une variable

			//on as enlever le sommet
			G.EnleverSommetEtArrete(G, x);
			System.out.println("on affiche le nouveau graph\n");
			graph H=new graph(G.Sommets,G.Arretes);
			H.AfficherGraph(H); 
			if(H.Sommets.size()>=1)
			{
				this.AlgoColoriage(H, k,source,coloredSommet);
			}

			//içi on est arrivé
			//on donne les couleurs
			//on doit recrée le graph içi
			//on doit vérifier chaque sommet qu'on rajoute quel sont c voisins
			//et si c voisins sont déjà dans l'array
			//System.out.println("sommet choisis "+x.name);
			//	System.out.println("on affiche la source");
			//	source.AfficherGraph(source);
			this.ColorerSommet(x,source, coloredSommet, k);


		}


	}

	void ColorerSommet(sommet s,graph g,ArrayList<sommetColore>coloredSommet,int k)
	{
		if(coloredSommet.size()==0)
		{
			coloredSommet.add(new sommetColore(s,1));
			System.out.println("je suis dans le premier coloriage\n");
			System.out.println("le sommet colorié "+coloredSommet.get(0).s.name+" couleur "+coloredSommet.get(0).color);
		}
		else
		{

			System.out.println("je suis dans le else\n");
			//on initialise les couleurs
			int voisincoloré=0;
			int PreferedColor=-1;
			ArrayList<Integer> color=new ArrayList<Integer>();
			for(int i=0;i<k;i++)
			{
				color.add(i+1);	
			}
			System.out.println("les couleurs dispo :");
			for(int i=0;i<k;i++)
			{
				System.out.println(""+color.get(i));	
			}
			for(int i=0;i<coloredSommet.size();i++)
			{
				//içi on cherche si le s est voisin du sommet qui est déjà coloré	
				if(isVoisin(s,coloredSommet.get(i),g))
				{
					//on enleve les couleurs pas disponibles
					if(!isVoisinPreference(s,coloredSommet.get(i), g))
					{
					voisincoloré++;
					System.out.println("je rentre içi je supprime de la list la couleur \n "+coloredSommet.get(i).color);
					int x=color.indexOf(coloredSommet.get(i).color);
					System.out.println("x :"+x);
					if(x>=0)
					color.remove(x);
					}
					else
					{
						 PreferedColor=color.indexOf(coloredSommet.get(i).color);
						System.out.println("prefered color x :"+color.get(PreferedColor));
						PreferedColor=color.get(PreferedColor);
					}
				}
			}
			//içi il y a des couleurs de dispo
			if(!color.isEmpty())
			{
				if(PreferedColor==-1)
				{
				int x=color.get(0);
				System.out.println("je colorie le sommet "+s.name+" avec la couleur "+x);
				coloredSommet.add(new sommetColore(s,x));
				}
				else
				{
				System.out.println("je colorie le sommet "+s.name+" avec la couleur "+PreferedColor);	
				coloredSommet.add(new sommetColore(s,PreferedColor));
				PreferedColor=-1;
				}
			}
		}

	}
	private boolean isVoisin(sommet s, sommetColore sommetColore, graph g) {

		System.out.println("sommet a colorier "+s.name+" sommet déjà colorier "+sommetColore.s.name);
		for (int i=0;i<g.Arretes.size();i++)
		{
			if((g.Arretes.get(i).a==s && g.Arretes.get(i).b==sommetColore.s)||( g.Arretes.get(i).a==sommetColore.s && g.Arretes.get(i).b==s))
			{
				System.out.println("l'Arrete est presente");
				return true;
			}
		}
		return false;
	}

	private boolean isVoisinPreference(sommet s, sommetColore sommetColore, graph g) {

		System.out.println("sommet a colorier "+s.name+" sommet déjà colorier "+sommetColore.s.name);
		for (int i=0;i<g.Arretes.size();i++)
		{
			if((g.Arretes.get(i).a==s && g.Arretes.get(i).b==sommetColore.s)||( g.Arretes.get(i).a==sommetColore.s && g.Arretes.get(i).b==s))
			{
				if (g.Arretes.get(i).type==1)
				{
				System.out.println("j'ai trouvé l'arrete de preference");
				return true;
				}
			}
		}
		return false;
	}




}
