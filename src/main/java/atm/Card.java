package atm;

public class Card {
    private String cardId;
    private String cardNetwork;

    public Card(String cardId, String cardNetwork){
        this.cardId = cardId;
        this.cardNetwork = cardNetwork;
    }

    public String getCardId(){
        return cardId;
    }

    public String getCardNetwork(){
        return cardNetwork;
    }
}
