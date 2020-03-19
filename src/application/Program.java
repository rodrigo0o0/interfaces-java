package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;



public class Program {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
		
			System.out.println("Enter the rental data: ");
			System.out.print("Car model : ");
			String carModel = sc.nextLine();
	
			System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
			Date start = sdf.parse(sc.nextLine());
			System.out.print("Return (dd/MM/yyyy hh:mm): ");
			Date finish = sdf.parse(sc.nextLine());
			CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));
			
			System.out.print("Enter the price per Hour: ");
			double pricePerHour = sc.nextDouble();
			System.out.print("Enter the price per day: ");
			double pricePerDay = sc.nextDouble();
			RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
			rentalService.processInvoice(carRental);
			System.out.println(carRental.getInvoice());
			
			
		}catch(ParseException e) {
			System.out.println("ERRO : "+ e.getMessage());
		}
		
		
		sc.close();
	}
}
