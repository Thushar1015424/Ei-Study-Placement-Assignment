// Target Interface (PaymentProcessor)
public interface PaymentProcessor {
    void processPayment(double amount);
}

// Adaptee (PayPalPayment)
public class PayPalPayment {
    public void makePayment(double amount) {
        System.out.println("Paid " + amount + " via PayPal.");
    }
}

// Adaptee (StripePayment)
public class StripePayment {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Stripe.");
    }
}

// Adapter for PayPal
public class PayPalAdapter implements PaymentProcessor {
    private PayPalPayment payPalPayment;

    public PayPalAdapter(PayPalPayment payPalPayment) {
        this.payPalPayment = payPalPayment;
    }

    @Override
    public void processPayment(double amount) {
        payPalPayment.makePayment(amount);
    }
}

// Adapter for Stripe
public class StripeAdapter implements PaymentProcessor {
    private StripePayment stripePayment;

    public StripeAdapter(StripePayment stripePayment) {
        this.stripePayment = stripePayment;
    }

    @Override
    public void processPayment(double amount) {
        stripePayment.pay(amount);
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPalPayment());
        payPalProcessor.processPayment(150.0);

        PaymentProcessor stripeProcessor = new StripeAdapter(new StripePayment());
        stripeProcessor.processPayment(300.0);
    }
}
