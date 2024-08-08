package cit270;

import java.io.File;
import java.io.*;
import java.util.Scanner;

public class Item {
	private int id;
	private String name;
	private double price;
	private int quantity;

	//add constructors
	public Item(int i, String n, Double p, int q) {
		SetID(i);
		SetName(n);
		SetPrice(p);
		SetQuantity(q);
	}
	
	//add get and set methods
	public void SetID(int i) {
		id = i;
	}
	
	public void SetName(String n) {
		name = n;
	}
	
	public void SetPrice(Double p) {
		price = p;
	}
	
	public void SetQuantity(int q) {
		quantity = q;
	}
	
	public int GetID() {
		return id;
	}
	
	public String GetName() {
		return name;
	}
	
	public Double GetPrice() {
		return price;
	}
	
	public int GetQuantity() {
		return quantity;
	}
}
