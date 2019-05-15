package it.polito.tdp.borders.model;

public class Country {
String abb;
int id;
String nome;
public Country(String a,int b,String c) {
	abb=a;
	id=b;
	nome=c;
}
public Integer getid() {

	return id;
}
public String getNome() {
	
	return nome;
}
}
