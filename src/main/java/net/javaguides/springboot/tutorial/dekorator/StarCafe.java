package net.javaguides.springboot.tutorial.dekorator;

public class StarCafe {
    public static void main (String [] args){
        Napój napój1 = new Espresso();

        System.out.println(napój1.pobierzOpis() + napój1.koszt());

        Napój napój2 = new MocnoPalona();
        napój2 = new Czekolada(napój2);
        napój2 = new Czekolada(napój2);
        napój2 = new BitaSmietana(napój2);

        System.out.println(napój2.pobierzOpis() + " " + napój2.koszt());

        Napój napój3 = new StarCafeSpecial();
        napój3 = new MleczkoSojowe(napój3);
        napój3 = new Czekolada(napój3);
        napój3 = new BitaSmietana(napój3);

        System.out.println(napój3.pobierzOpis() + " " + napój3.koszt());

        Napój nowaKawa = new MocnoPalona();
        nowaKawa = new Cukier(nowaKawa);

        System.out.println(nowaKawa.pobierzOpis() + " " + nowaKawa.koszt());
    }
}
