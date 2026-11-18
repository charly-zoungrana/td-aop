package net.zoungrana.test;

import net.zoungrana.metier.Compte;
import net.zoungrana.metier.IMetierBanque;
import net.zoungrana.metier.MetierBanqueImpl;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
       new Application().start();
    }

    private void start() {
        System.out.println("Démarrage de l'application");
        Scanner scanner=new Scanner(System.in);

        System.out.print("Entrer le code du compte :");
        Long code=scanner.nextLong();
        System.out.print("Entrer le solde initial: ");
        double solde=scanner.nextDouble();

        IMetierBanque metierBanque=new MetierBanqueImpl();
        metierBanque.addCompte(new Compte(code,solde));

        while (true){
            try{
                System.out.print("Type d'opération: ");
                String type=scanner.next();
                if(type.equals("quitter")){
                    System.out.println("Fin de l'application");
                    break;
                }
                System.out.print("Montant: ");
                double montant=scanner.nextDouble();

                if(type.equals("v")){
                    metierBanque.verser(code,montant);
                }else if(type.equals("r")){
                    metierBanque.retirer(code,montant);
                }

                Compte compte=metierBanque.consulterCompte(code);
                System.out.println("Etat du compte: "+compte.toString());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}

