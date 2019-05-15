package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
     BordersDAO dao=new BordersDAO();
	 Graph<Country,DefaultEdge> grafo;
	 List<Country> paesi;
	 Map<Integer,Country> mappapaesi=new HashMap<Integer,Country>();
	 List<Border> borders;
	public Model() {
	
	}
	public void creaGrafo(int a) {
		grafo=new SimpleGraph<>(DefaultEdge.class);
		paesi=dao.loadAllCountries(a);
		Graphs.addAllVertices(grafo, paesi);
		for(Country c:paesi) {
			mappapaesi.put(c.getid(),c);
		}
		borders=dao.confinanti(a);
		for (Border b:borders) {
			DefaultEdge de=grafo.getEdge(mappapaesi.get(b.getId1()), mappapaesi.get(b.getId2()));
			if(de==null) {grafo.addEdge(mappapaesi.get(b.getId1()), mappapaesi.get(b.getId2()));}
		}
		System.out.println(""+grafo.vertexSet().size()+" "+grafo.edgeSet().size());
		
	}
	public Map<String,List<String>> getElencoConfinanti() {
		 Map<String,List<String>> confinanti=new HashMap<String,List<String>>();
		for(Country c:paesi) {
			List<Country> paesicollegati=new ArrayList<Country>();
			paesicollegati=Graphs.neighborListOf(grafo, c);
			List<String> pc=new ArrayList<String>();
			for(Country co:paesicollegati) {
				pc.add(co.getNome());
				
			}
			confinanti.put(c.getNome(), pc);
			
		}
		return confinanti;
	}
	
    
}
