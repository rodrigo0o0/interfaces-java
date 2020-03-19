package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	private double pricePerHour;
	private double pricePerDay;
	private BrazilTaxService brazilTaxService;
	
	public void processInvoice(CarRental carRental) {
		//getTime pega o valor em milissegundos da data
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		//pega a diferença em horas
		double basicPayment = 0;
		double hours = (double) (t2 - t1) / 1000/60/60;
		if(hours <= 12.0) {
			//Math.ceil arredonda pra cima
			basicPayment = Math.ceil(hours) * pricePerHour;
		}else {
			basicPayment = Math.ceil(hours/24) * pricePerDay;
		}
		double tax = brazilTaxService.tax(basicPayment);
		carRental.setInvoice(new Invoice(basicPayment,tax));
	}
	
	public double getPricePerHour() {
		return pricePerHour;
	}


	public RentalService(double pricePerHour, double pricePerDay, BrazilTaxService brazilTaxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.brazilTaxService = brazilTaxService;
	}

	public RentalService() {
	}
	
	public BrazilTaxService getBrazilTaxService() {
		return brazilTaxService;
	}

	public void setBrazilTaxService(BrazilTaxService brazilTaxService) {
		this.brazilTaxService = brazilTaxService;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}


	public double getPricePerDay() {
		return pricePerDay;
	}


	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}


}
