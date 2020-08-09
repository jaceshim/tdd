package jace.shim.tdd.chapter7;

public class SpyEmailNotifier implements EmailNotifier {
    private boolean called;
    private String email;

    public String getSentEmail() {
        return this.email;
    }

    @Override
    public void sendRegisterEmail(String email) {
        this.called = true;
        this.email = email;
    }

    public boolean isCalled() {
        return this.called;
    }
}
