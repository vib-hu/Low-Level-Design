package atm;

import atm.services.MasterCardService;
import atm.services.NetworkService;
import atm.services.VisaService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws Exception  {
        // ========== Setup ATM with Services ==========
        List<NetworkService> services = new ArrayList<>();
        services.add(new VisaService());
        services.add(new MasterCardService());

        Atm atm = new Atm(services);

        // ========== Scenario 1: Successful Transaction ==========
        System.out.println("========== Scenario 1: Successful Transaction ==========");
        performSuccessfulTransaction(atm);

        // Reset for next scenario
        System.out.println("\n");
        atm = new Atm(services);

        // ========== Scenario 2: Transaction with Error ==========
        System.out.println("========== Scenario 2: Transaction with Error ==========");
        performTransactionWithError(atm);

        // Reset for next scenario
        System.out.println("\n");
        atm = new Atm(services);

        // ========== Scenario 3: User Cancels Transaction ==========
        System.out.println("========== Scenario 3: User Cancels Transaction ==========");
        performCancelledTransaction(atm);

        // Reset for next scenario
        System.out.println("\n");
        atm = new Atm(services);

        // Reset for next scenario
        System.out.println("\n");
        atm = new Atm(services);

        // ========== Scenario 5: Invalid Operations ==========
        System.out.println("========== Scenario 5: Invalid Operations ==========");
        performInvalidOperations(atm);
    }
    // ========== Scenario Implementations ==========

    private static void performSuccessfulTransaction(Atm atm) throws Exception {
        // Step 1: Insert Card
        Card visaCard = new Card("1234567890123456", "VISA");
        atm.insertCard(visaCard);
        System.out.println("Current State: " + atm.getCurrentState().getClass().getSimpleName());

        // Step 2: Enter PIN
        atm.enterPin("1234");
        System.out.println("Current State: " + atm.getCurrentState().getClass().getSimpleName());

        // Step 3: Enter Amount
        atm.enterAmount(new BigDecimal("100.00"));
        System.out.println("Current State: " + atm.getCurrentState().getClass().getSimpleName());

        // Step 4: Confirm Transaction
        atm.confirm();
        System.out.println("Current State: " + atm.getCurrentState().getClass().getSimpleName());
    }

    private static void performTransactionWithError(Atm atm) throws Exception {
        // Step 1: Insert Card
        Card visaCard = new Card("1234567890123456", "VISA");
        atm.insertCard(visaCard);

        // Step 2: Enter PIN
        atm.enterPin("1234");

        // Step 3: Enter Amount
        atm.enterAmount(new BigDecimal("500.00"));

        // Step 4: Confirm Transaction (VisaService returns false, so will go to ErrorState)
        atm.confirm();
        System.out.println("Current State: " + atm.getCurrentState().getClass().getSimpleName());

        // Step 5: Handle Error - Go Back
        atm.goBack();
        System.out.println("Current State: " + atm.getCurrentState().getClass().getSimpleName());

        // Step 6: Change Amount and Retry
        atm.enterAmount(new BigDecimal("50.00"));
        atm.confirm();
    }

    private static void performCancelledTransaction(Atm atm) {
        // Step 1: Insert Card
        Card masterCard = new Card("9876543210987654", "MASTERCARD");
        atm.insertCard(masterCard);

        // Step 2: Enter PIN
        atm.enterPin("5678");

        // Step 3: Cancel Transaction
        atm.cancel();
        System.out.println("Current State: " + atm.getCurrentState().getClass().getSimpleName());
    }

    private static void performInvalidOperations(Atm atm) throws Exception {
        // Try to enter PIN without card
        atm.enterPin("1234");

        // Insert card
        Card card = new Card("5555666677778888", "MASTERCARD");
        atm.insertCard(card);

        // Try to enter amount without PIN
        atm.enterAmount(new BigDecimal("100.00"));

        // Enter PIN
        atm.enterPin("5555");

        // Try to insert another card
        Card anotherCard = new Card("9999888877776666", "VISA");
        atm.insertCard(anotherCard);

        // Enter amount
        atm.enterAmount(new BigDecimal("75.00"));

        // Try to enter PIN again
        atm.enterPin("5555");
    }
}
