package Algo;

import java.util.ArrayList;
import java.util.Scanner;

class sommet {
	char name;
	int nbrArrete;
	public sommet(char name) {
		super();
		this.name = name;
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
		super();
		this.type = type;
		this.a = a;
		this.b = b;
	}

}

class graph {
	ArrayList <sommet> Sommets;
	ArrayList <arrete> Arretes;
	public graph(ArrayList<sommet> sommets, ArrayList<arrete> arretes) {
		super();
		Sommets = sommets;
		Arretes = arretes;
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


}

public class algo {

	public static void main(String[] args) {
		int k;
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
        sc.nextLine();
        }


}
