// Strategy Interface (PaymentMethod)
public interface PaymentMethod {
    void pay(double amount);
}

// Concrete Strategy (CreditCard)
public class CreditCard implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

// Concrete Strategy (PayPal)
public class PayPal implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

// Context (PaymentProcessor)
public class PaymentProcessor {
    private PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processPayment(double amount) {
        paymentMethod.pay(amount);
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor(new CreditCard());
        paymentProcessor.processPayment(100.0);

        paymentProcessor.setPaymentMethod(new PayPal());
        paymentProcessor.processPayment(200.0);
    }
}
