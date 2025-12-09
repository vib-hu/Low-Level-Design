# 🏧 ATM Design Using State Pattern — Best Practices

## Purpose

This document summarizes best practices for designing an **ATM system using the State Pattern**, based on practical system behavior rather than over-granular or purely textbook designs.

---

## Core Principle

> A state represents a **stable condition** where the system is **waiting for something external**  
> (user input, hardware response, bank response, or operator intervention).

If the system is **not waiting**, it **should not be modeled as a state**.

---

## Common Design Mistake

Many designs incorrectly mix:

- ✅ **Behavior states** – valid
- ❌ **Milestones / events / actions** – invalid as states

This leads to **overcomplicated and misleading state models**.

---

## Correct ATM State Classification

### ✅ Valid Behavior States

These states control what actions are allowed:

- **Idle** – waiting for card
- **CardInserted** – waiting for PIN
- **Authenticated** – PIN verified, waiting for transaction
- **TransactionInProgress** – waiting for bank/hardware responses
- **Error** – waiting for retry or reset
- **OutOfService** *(optional)*

---

### ❌ Invalid as States (Should Be Actions)

The following **must not be modeled as states**:

- Amount Entered
- Dispensing Cash
- Printing Receipt
- Cash Dispensed
- Receipt Printed

These are **instant actions or events**, not waiting conditions.

---

## Minimal & Clean ATM State Flow

