package jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
	
	private String category;
	private String title;
	private int year;
	private double price;
	
	
	
	public String getCategory() {
		return category;
	}
	
	@XmlAttribute
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	@XmlElement
	public void setYear(int year) {
		this.year = year;
	}
	public double getPrice() {
		return price;
	}
	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
