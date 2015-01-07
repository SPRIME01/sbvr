package fiuba.tesis.nlp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Assert;

import org.junit.Test;

import fiuba.tesis.nlp.parser.SbvrParser;

public class TestTermExtractors {
	
	protected List<String> readTermsFrom(String s) {		
		try {
			List<String> sentences = new ArrayList<String>();
			sentences.add(s);
		
			SbvrParser parser = new SbvrParser();
			parser.parse(sentences);
    	
			return parser.getTerms();
		} catch(XPathExpressionException ex) {
			Assert.fail(ex.getMessage());
		}catch(ParserConfigurationException ex) {
			Assert.fail(ex.getMessage());
		}
		return new ArrayList<String>();
	}
	
	@Test
	public void testPersonIsNamedOnCreditCard() {
    	List<String> terms = readTermsFrom("person is named on credit card");    	
    	Assert.assertEquals("person", terms.get(0));
    	Assert.assertEquals("credit card", terms.get(1));
	}
	
	@Test
	public void testRentalHasDropOffBranch() {
		List<String> terms = readTermsFrom("rental has drop-off branch.");
    	Assert.assertEquals("rental", terms.get(0));
    	Assert.assertEquals("drop-off branch", terms.get(1));
	}
	
	@Test
	public void testRentalHasRentalPrice() {
		List<String> terms = readTermsFrom("rental has rental price.");
    	Assert.assertEquals("rental", terms.get(0));
    	Assert.assertEquals("rental price", terms.get(1));
	}
	
	@Test
	public void testRentalHasPickUpBranch() {
		List<String> terms = readTermsFrom("rental has pick-up branch.");
    	Assert.assertEquals("rental", terms.get(0));
    	Assert.assertEquals("pick-up branch", terms.get(1));
	}
	
	@Test
	public void testRentalHasGracePeriod() {
		List<String> terms = readTermsFrom("rental has grace period.");
    	Assert.assertEquals("rental", terms.get(0));
    	Assert.assertEquals("grace period", terms.get(1));
	}
	
	@Test
	public void testRentalHasActualReturnDateTime() {
		List<String> terms = readTermsFrom("rental has actual return date-time.");
    	Assert.assertEquals("rental", terms.get(0));
    	Assert.assertEquals("actual return date-time", terms.get(1));
	}
	
	@Test
	public void testRentalHasActualPickUpDateTime() {
		List<String> terms = readTermsFrom("rental has actual pick-up date-time.");
    	Assert.assertEquals("rental", terms.get(0));
    	Assert.assertEquals("actual pick-up date-time", terms.get(1));
	}
	
	@Test
	public void testRentalHasRentedCar() {
		List<String> terms = readTermsFrom("rental has rented-car.");
    	Assert.assertEquals("rental", terms.get(0));
    	Assert.assertEquals("rented-car", terms.get(1));
	}
	
	@Test
	public void testRentalHasEstimatedRentalPrice() {
		List<String> terms = readTermsFrom("rental has estimated-rental-price.");
    	Assert.assertEquals("rental", terms.get(0));
    	Assert.assertEquals("estimated-rental-price", terms.get(1));
	}
	
	@Test
	public void testRentalIncursRecoveryCharge() {
		List<String> terms = readTermsFrom("rental incurs recovery charge.");
    	Assert.assertEquals("recovery charge", terms.get(0));
    	Assert.assertEquals("rental", terms.get(1));
	}
	
	@Test
	public void testRentalPriceIsConvertedToNonLocalCurrency() {
		List<String> terms = readTermsFrom("rental price is converted to non-local currency.");
		Assert.assertEquals("rental price", terms.get(0));
		Assert.assertEquals("non-local currency", terms.get(1));
	}
	
	@Test
	public void testAdditionalDriverIsAuthorizedForRental() {
		List<String> terms = readTermsFrom("additional driver is authorized for rental.");
		Assert.assertEquals("additional driver", terms.get(0));
		Assert.assertEquals("rental", terms.get(1));
	}
	
	@Test
	public void testRentalCarIsIncludedInCarGroup() {
		List<String> terms = readTermsFrom("rental car is included in car group.");
		Assert.assertEquals("rental car", terms.get(0));
		Assert.assertEquals("car group", terms.get(1));
	}
	
	@Test
	public void testRentalCarIsStoredAtBranch() {
		List<String> terms = readTermsFrom("rental car is stored at branch.");
		Assert.assertEquals("rental car", terms.get(0));
		Assert.assertEquals("branch", terms.get(1));
	}
	
	@Test
	public void testRentalCarIsScheduledForScheduledService() {
		List<String> terms = readTermsFrom("rental car is scheduled for scheduled service.");
		Assert.assertEquals("rental car", terms.get(0));
		Assert.assertEquals("scheduled service", terms.get(1));
	}
	
	@Test
	public void testRequestForPickUpIsMadeAtBranch() {
		List<String> terms = readTermsFrom("request for pick-up is made at branch.");
		Assert.assertEquals("request for pick-up", terms.get(0));
		Assert.assertEquals("branch", terms.get(1));
	}
	
	@Test
	public void testMovedCarIsIncludedInCarMovement() {
		List<String> terms = readTermsFrom("moved car is included in car movement.");
		Assert.assertEquals("moved car", terms.get(0));
		Assert.assertEquals("car movement", terms.get(1));
	}
	
	@Test
	public void testDriverIsAuthorizedForRental() {
		List<String> terms = readTermsFrom("driver is authorized for rental.");
		Assert.assertEquals("driver", terms.get(0));
		Assert.assertEquals("rental", terms.get(1));
	}
	
	@Test
	public void testCreditCardIsProvisionallyChargedForEstimatedRentalPrice() {
		List<String> terms = readTermsFrom("credit card is provisionally charged for estimated rental price.");
		Assert.assertEquals("credit card", terms.get(0));
		Assert.assertEquals("estimated rental price", terms.get(1));
	}
	
	@Test
	public void testDriverIsQualified() {
		List<String> terms = readTermsFrom("driver is qualified.");
		Assert.assertEquals("driver", terms.get(0));
	}
	
	@Test
	public void testRentalIsOpened() {
		List<String> terms = readTermsFrom("rental is opened.");
		Assert.assertEquals("rental", terms.get(0));
	}
	
	@Test
	public void testRentedCarIsRecoveredFromRecoveryLocationToBranch() {
		List<String> terms = readTermsFrom("rented car is recovered from recovery location to branch.");
		Assert.assertEquals("rented car", terms.get(0));
		Assert.assertEquals("recovery location", terms.get(1));
		Assert.assertEquals("branch", terms.get(2));
	}
	
//	@Test
//	public void testRentalBeingOpen() {
//		List<String> terms = readTermsFrom("rental being open.");
//		Assert.assertEquals("rental", terms.get(0));
//	}
	
//	@Test
//	public void testFuelLevelBeingFull() {
//		List<String> terms = readTermsFrom("fuel level being full.");
//		Assert.assertEquals("fuel level", terms.get(0));
//	}
	
	@Test
	public void testRentalHasRentalPeriod() {
		List<String> terms = readTermsFrom("rental has rental period.");
		Assert.assertEquals("rental", terms.get(0));
		Assert.assertEquals("rental period", terms.get(1));
	}
	
	@Test
	public void testRentalHasReturnBranch() {
		List<String> terms = readTermsFrom("rental has return-branch.");
		Assert.assertEquals("rental", terms.get(0));
		Assert.assertEquals("return-branch", terms.get(1));
	}
	
	@Test
	public void testRentalHasReturnBranch2() {
		List<String> terms = readTermsFrom("rental has return branch.");
		Assert.assertEquals("rental", terms.get(0));
		Assert.assertEquals("return branch", terms.get(1));
	}
	
	@Test
	public void testDriverHasDriverLicense() {
		List<String> terms = readTermsFrom("driver has driver license.");
		Assert.assertEquals("driver", terms.get(0));
		Assert.assertEquals("driver license", terms.get(1));
	}
	
	@Test
	public void testCarHasCountryOfRegistration() {
		List<String> terms = readTermsFrom("car has country of registration.");
		Assert.assertEquals("car", terms.get(0));
		Assert.assertEquals("country of registration", terms.get(1));
	}
	
	@Test
	public void testRentalCarHasFuelLevel() {
		List<String> terms = readTermsFrom("rental car has fuel level.");
		Assert.assertEquals("rental car", terms.get(0));
		Assert.assertEquals("fuel level", terms.get(1));
	}
	
	@Test
	public void testRentalCarHasServiceMileage() {
		List<String> terms = readTermsFrom("rental car has service mileage.");
		Assert.assertEquals("rental car", terms.get(0));
		Assert.assertEquals("service mileage", terms.get(1));
	}
	
	@Test
	public void testRentalCarHasOdometerReading() {
		List<String> terms = readTermsFrom("rental car has odometer reading.");
		Assert.assertEquals("rental car", terms.get(0));
		Assert.assertEquals("odometer reading", terms.get(1));
	}
	
	@Test
	public void testCarMovementIncludesMovedCar() {
		List<String> terms = readTermsFrom("car movement includes moved-car.");
		Assert.assertEquals("car movement", terms.get(0));
		Assert.assertEquals("moved-car", terms.get(1));
	}
	
	@Test
	public void testCarMovementClaimsCarGroup() {
		List<String> terms = readTermsFrom("car movement claims car group.");
		Assert.assertEquals("car movement", terms.get(0));
		Assert.assertEquals("car group", terms.get(1));
	}
	
	@Test
	public void testCarMovementHasDropOffDateTime() {
		List<String> terms = readTermsFrom("car movement has drop-off date-time.");
		Assert.assertEquals("car movement", terms.get(0));
		Assert.assertEquals("drop-off date-time", terms.get(1));
	}
	
	@Test
	public void testCountryContainsSite() {
		List<String> terms = readTermsFrom("country contains site.");
		Assert.assertEquals("country", terms.get(0));
		Assert.assertEquals("site", terms.get(1));
	}
	
	@Test
	public void testOperatingCompanyHasInsurer() {
		List<String> terms = readTermsFrom("operating company has insurer.");
		Assert.assertEquals("operating company", terms.get(0));
		Assert.assertEquals("insurer", terms.get(1));
	}
	
	@Test
	public void testAdvanceRentalHasScheduledPickUpDateTime() {
		List<String> terms = readTermsFrom("advance rental has scheduled pick-up date-time.");
		Assert.assertEquals("advance rental", terms.get(0));
		Assert.assertEquals("scheduled pick-up date-time", terms.get(1));
	}
	
	@Test
	public void testBadExperienceHasNotificationDateTime() {
		List<String> terms = readTermsFrom("bad experience has notification date-time.");
		Assert.assertEquals("bad experience", terms.get(0));
		Assert.assertEquals("notification date-time", terms.get(1));
	}
	
	@Test
	public void testRequestForPickUpIncludesContractNumber() {
		List<String> terms = readTermsFrom("request for pick-up includes contract number.");
		Assert.assertEquals("request for pick-up", terms.get(0));
		Assert.assertEquals("contract number", terms.get(1));
	}
	
	@Test
	public void testLocalAreaOwnsRentalCar() {
		List<String> terms = readTermsFrom("local area owns rental car.");
		Assert.assertEquals("local area", terms.get(0));
		Assert.assertEquals("rental car", terms.get(1));
	}
	
	@Test
	public void testLocalAreaManagesBranch() {
		List<String> terms = readTermsFrom("local area manages branch.");
		Assert.assertEquals("local area", terms.get(0));
		Assert.assertEquals("branch", terms.get(1));
	}
	
}
