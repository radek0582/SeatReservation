package net.javaguides.springboot.tutorial.obserwator;

public interface Podmiot {
    public void zarejestrujObserwatora (Obserwator o);
    public void usunObserwatora (Obserwator o);
    public void powiadomObserwatorow ();
}
