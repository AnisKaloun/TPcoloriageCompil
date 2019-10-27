package Algo;

import java.util.ArrayList;
import java.util.Scanner;

class sommet {
	char name;
	int nbrArrete;
	boolean spilled;
	public sommet(char name) {
		this.name = name;
		this.nbrArrete=0;
		spilled=false;
	}
	public int getNbrArrete() {
		return nbrArrete;
	}
	public void setNbrArrete(int nbrArrete) {
		this.nbrArrete = nbrArrete;
	}
	public boolean isSpilled() {
		return spilled;
	}
	public void setSpilled(boolean spilled) {
		this.spilled = spilled;
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
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
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

		System.out.println("on affiche les arretes ");
		System.out.println("type 0:arrete interference");
		System.out.println("type 1:arrete preference ");
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
				if((this.Arretes.get(j).a==this.Sommets.get(i) ||this.Arretes.get(j).b==this.Sommets.get(i))&& (this.Arretes.get(j).type==0))
				{
					int x=this.Sommets.get(i).getNbrArrete();
					this.Sommets.get(i).setNbrArrete(x+1);
				}

			}
		}
	}

	void EnleverSommetEtArrete(graph G,sommet s)
	{

		for(int i=0;i<G.Arretes.size();i++)
		{
			if((G.Arretes.get(i).a==s || G.Arretes.get(i).b==s)&&( G.Arretes.get(i).type==0))
			{
				if(G.Arretes.get(i).a==s)
				{
					sommet index=G.Arretes.get(i).b;
					if(this.Sommets.indexOf(index)>=0)
					{
						int x=this.Sommets.get(this.Sommets.indexOf(index)).getNbrArrete();
						this.Sommets.get(this.Sommets.indexOf(index)).setNbrArrete(x-1);

					}
				}
				else if(G.Arretes.get(i).b==s)
				{
					sommet index=G.Arretes.get(i).a;
					if(this.Sommets.indexOf(index)>=0)
					{
						int x=this.Sommets.get(this.Sommets.indexOf(index)).getNbrArrete();
						this.Sommets.get(this.Sommets.indexOf(index)).setNbrArrete(x-1);

					}
				}
				G.Arretes.remove(i);
				i--;
			}

		}
		G.Sommets.remove(s);
	}

	boolean hasPreferencedArrete(sommet s)
	{
		for (int i=0 ;i<Arretes.size();i++)
		{
			if((Arretes.get(i).a==s|| Arretes.get(i).b==s) && Arretes.get(i).type==1)
			{
				return true;
			}

		}
		return false;

	}
}

public class algo {

	public static void main(String[] args) {
		//graph 1
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
		sc.nextLine();
		algo g=new algo();        
		ArrayList <sommetColore> ColoredSommet= new ArrayList<sommetColore>();
		graph source =new graph(G);
		source.RemplirDegres(source);
		g.AlgoColoriage(G,H,source,ColoredSommet);
		g.affichergraphColorier(ColoredSommet);

		//graph2
        System.out.println("\n \n \n \n le 2 eme graph");
		ArrayList <sommet> sommet2= new ArrayList<sommet>();
		sommet a=new sommet('a');
		sommet b=new sommet('b');
		sommet c=new sommet('c');
		sommet d=new sommet('d');
		sommet2.add(a);
		sommet2.add(b);
		sommet2.add(c);
		sommet2.add(d);
		ArrayList <arrete> arretes2=new ArrayList<arrete>();
		arretes2.add(new arrete(0,a,b));
		arretes2.add(new arrete(0,b,c));
		arretes2.add(new arrete(0,c,d));
		arretes2.add(new arrete(0,d,a));
		graph G1 = new graph(sommet2,arretes2);
		G1.RemplirDegres(G1);
		G1.AfficherGraph(G1);
		System.out.print("donnez le k a colorier\n");
		int H1=sc.nextInt();
		algo g1=new algo();        
		g1.ChercherSommetPlusPetitDegres(G1);
		ArrayList <sommetColore> ColoredSommet1= new ArrayList<sommetColore>();
		graph source1 =new graph(G1);
		source1.RemplirDegres(source1);
		g1.AlgoColoriage(G1,H1,source1,ColoredSommet1);
		sc.close();
		g1.affichergraphColorier(ColoredSommet1);


	}




	private  void affichergraphColorier(ArrayList<sommetColore> coloredSommet) {
		for (int i=0;i<coloredSommet.size();i++)
		{
			if(coloredSommet.get(i).s.isSpilled())
			{


				System.out.println("sommet - spilled "+coloredSommet.get(i).s.name+" pas de couleur");	

			}
			else
			{
				System.out.println("sommet "+coloredSommet.get(i).s.name+" couleur "+coloredSommet.get(i).color);	
			}
		}

	}
	boolean isColoriable(graph g,int k)
	{
		if(ChercherSommetPlusPetitDegres(g).getNbrArrete()<k)
			return true;
		return false;
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

		for(int i=0 ; i<G.Sommets.size();i++)
		{
			if(G.Sommets.get(i).getNbrArrete()>Grand.getNbrArrete())
			{
				Grand=G.Sommets.get(i);
			}

		}
		return Grand;
	}

	void AlgoColoriage(graph G,int k,graph source, ArrayList<sommetColore> coloredSommet)
	{

		sommet x=null;
		if(!this.isColoriable(G, k))
		{
			System.out.println("on doit splitter içi \n");
			x=this.ChercherSommetPlusGrandDegres(G);
			System.out.println("on choisis "+x.name+"\n");
			x.setSpilled(true);
			G.EnleverSommetEtArrete(G,x);
		}
		else {
			//içi on est dans le cas ou on peut eliminer une variable

			x=this.ChercherSommetPlusPetitDegres(G);
			System.out.println("on choisis "+x.name+"\n");
			//on as enlever le sommet
			G.EnleverSommetEtArrete(G, x);

		}
		System.out.println("on affiche le nouveau graph\n");
		graph H=new graph(G.Sommets,G.Arretes);
		H.AfficherGraph(H);
		if(H.Sommets.size()>=1)
		{
			this.AlgoColoriage(H,k,source,coloredSommet);
		}

		//içi on est arrivé
		//on donne les couleurs
		//on doit recrée le graph içi
		//on doit vérifier chaque sommet qu'on rajoute quel sont c voisins
		//et si c voisins sont déjà dans l'array
		this.ColorerSommet(x,source, coloredSommet, k);

	}

	void ColorerSommet(sommet s,graph g,ArrayList<sommetColore>coloredSommet,int k)
	{
		if(coloredSommet.size()==0)
		{
			coloredSommet.add(new sommetColore(s,1));
			System.out.println("le sommet colorié "+coloredSommet.get(0).s.name+" couleur "+coloredSommet.get(0).color);
		}
		else
		{

			int PreferedColor=-1;
			ArrayList<Integer> color=new ArrayList<Integer>();
			for(int i=0;i<k;i++)
			{
				color.add(i+1);	
			}
	
			for(int i=0;i<coloredSommet.size();i++)
			{
				//içi on cherche si le s est voisin du sommet qui est déjà coloré	
				if(isVoisin(s,coloredSommet.get(i),g))
				{
					//on enleve les couleurs pas disponibles
					if(!isVoisinPreference(s,coloredSommet.get(i), g))
					{
						int x=color.indexOf(coloredSommet.get(i).color);
						if(x>=0)
						{
							color.remove(x);
						}
					}
					else
					{
						PreferedColor=color.indexOf(coloredSommet.get(i).color);
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
					if(!s.isSpilled())
					System.out.println("je colorie le sommet "+s.name+" avec la couleur "+x);
					else
					{
					System.out.println("on peut colorier le sommet spiller "+s.name);
				    System.out.println("je colorie le sommet "+s.name+" avec la couleur "+x);
				    s.setSpilled(false);
					}
					coloredSommet.add(new sommetColore(s,x));
				}
				else
				{
					System.out.println("je colorie le sommet "+s.name+" avec la couleur "+PreferedColor);	
					coloredSommet.add(new sommetColore(s,PreferedColor));
					PreferedColor=-1;
				}
			}
			else if(s.isSpilled() && color.isEmpty())
			{
				System.out.println("pas de couleur dispo pour  le sommet spiller "+s.name);	
				coloredSommet.add(new sommetColore(s,-1));
			}
			else {
				System.out.println("ce graph n'ai pas k coloriable");
				System.exit(0);
			}
		}

	}
	private boolean isVoisin(sommet s, sommetColore sommetColore, graph g) {

		for (int i=0;i<g.Arretes.size();i++)
		{
			if((g.Arretes.get(i).a==s && g.Arretes.get(i).b==sommetColore.s)||( g.Arretes.get(i).a==sommetColore.s && g.Arretes.get(i).b==s))
			{
				return true;
			}
		}
		return false;
	}

	private boolean isVoisinPreference(sommet s, sommetColore sommetColore, graph g) {

		for (int i=0;i<g.Arretes.size();i++)
		{
			if((g.Arretes.get(i).a==s && g.Arretes.get(i).b==sommetColore.s)||( g.Arretes.get(i).a==sommetColore.s && g.Arretes.get(i).b==s))
			{
				if (g.Arretes.get(i).type==1)
				{
					return true;
				}
			}
		}
		return false;
	}




}
