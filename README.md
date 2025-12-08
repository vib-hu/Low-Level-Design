# Low-Level-Design
Question: https://leetcode.com/discuss/interview-question/990227/udaan-assignment-cricket-match-dashboard

## Which design pattern best fit for questions with workflow like ATM?
The **State Pattern** fits this workflow. Your vending machine example already uses it.

### Why State Pattern fits ATM workflows

1. **Sequential steps**: Each step (card insertion → PIN entry → amount selection) is a distinct state.
2. **State-specific behavior**: Each state allows only certain actions (e.g., can't enter PIN before card).
3. **Navigation**: You can move forward, backward, or cancel.
4. **Encapsulation**: Each state handles its own logic and transitions.

### How it maps to ATM

- **IdleState**: Waiting for card
- **CardInsertedState**: Card inserted, waiting for PIN
- **PinEnteredState**: PIN entered, waiting for amount
- **AmountEnteredState**: Amount entered, ready to process
- **TransactionCompleteState**: Transaction finished

Each state can:

- Transition to the next state
- Go back to a previous state
- Cancel and return to idle
