package atm;

import atm.services.MasterCardService;
import atm.services.NetworkService;
import atm.services.VisaService;
import atm.states.*;

import java.math.BigDecimal;
import java.util.List;

public class Atm {
    private final AtmMachineState idleState;
    private final AtmMachineState cardInsertedState;
    private final AtmMachineState pinEnteredState;
    private final AtmMachineState amountEnteredState;
    private final AtmMachineState errorState;
    private BigDecimal amount;
    private List<NetworkService> networkServices;

    private AtmMachineState currentState;
    private AtmMachineState prevState;

    private Card card;
    private String pin;

    public Atm(List<NetworkService> networkServices){
        idleState = new IdleState(this);
        cardInsertedState = new CardInsertedState(this);
        pinEnteredState = new PinEnteredState(this);
        amountEnteredState = new AmountEnteredState(this);
        errorState = new ErrorState(this);
        currentState = idleState;
        this.networkServices = networkServices;
    }

    public void setState(AtmMachineState state){
        this.prevState = currentState;
        this.currentState = state;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public void ejectCard() {
        System.out.println("Ejecting Card ");
        card = null;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean deductMoney() {
        if (card == null || pin == null || amount == null) {
            System.out.println("Missing transaction details");
            return false;
        }

        // Get appropriate service based on card type
        NetworkService service = getServiceForCard(card);
        if (service == null) {
            System.out.println("Service not available for this card type");
            return false;
        }

        // Deduct money using the service
        String cardId = card.getCardId();
        boolean success = service.deductMoney(cardId, pin, amount);

        if (success) {
            System.out.println("Money deducted successfully from account");
        } else {
            System.out.println("Failed to deduct money. Insufficient balance or network error.");
        }

        return success;
    }

    public void dispenseMoney(){
        if (amount != null) {
            System.out.println("Dispensing money: $" + amount);
        } else {
            System.out.println("No amount to dispense");
        }
    }

    // ========== State Getters ==========
    public AtmMachineState getPinEnteredState() {
        return pinEnteredState;
    }

    public AtmMachineState getIdleState() {
        return idleState;
    }

    public AtmMachineState getAmountEnteredState() {
        return amountEnteredState;
    }

    public AtmMachineState getErrorState() {
        return errorState;
    }

    public AtmMachineState getCardInsertedState() {
        return cardInsertedState;
    }

    public Object getCurrentState() {
        return currentState;
    }

    // ========== Public API - Delegates to Current State ==========
    public void insertCard(Card card) {
        currentState.insertCard(card);
    }

    public void enterPin(String pin) {
        currentState.enterPin(pin);
    }

    public void enterAmount(BigDecimal amount) throws Exception {
        currentState.enterAmount(amount);
    }

    public void confirm() {
        currentState.confirm();
    }

    public void goBack() {
        currentState.goBack();
    }

    public void cancel() {
        currentState.cancel();
    }

    public void error() {
        currentState.error();
    }

    // ========== Private API ==========
    private NetworkService getServiceForCard(Card card) {
        String cardNetwork = card.getCardNetwork(); // Assuming Card has this method

        if ("VISA".equalsIgnoreCase(cardNetwork)) {
            return getVisaService();
        } else if ("MASTERCARD".equalsIgnoreCase(cardNetwork)) {
            return getMasterCardService();
        }

        System.out.println("Unsupported card type: " + cardNetwork);
        return null;
    }

    private NetworkService getVisaService(){
        return networkServices.stream()
                .filter(service -> service instanceof VisaService)
                .findFirst()
                .orElse(null);
    }

    private NetworkService getMasterCardService(){
        return networkServices.stream()
                .filter(service -> service instanceof MasterCardService)
                .findFirst()
                .orElse(null);
    }

}
