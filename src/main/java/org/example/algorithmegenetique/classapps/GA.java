package org.example.algorithmegenetique.classapps;

import java.util.Random;

public class GA {
    private GestionnaireCircuit gestionnaireCircuit;
    private double tauxMutation = 0.015;
    private int tailleTournoi = 5;
    private boolean elitisme = true;
    private Random random = new Random();

    public GA(GestionnaireCircuit gestionnaireCircuit) {
        this.gestionnaireCircuit = gestionnaireCircuit;
    }

    public Population evoluerPopulation(Population pop) {
        Population nouvellePopulation = new Population(gestionnaireCircuit, pop.taillePopulation(), false);
        int elitismeOffset = 0;
        if (elitisme) {
            nouvellePopulation.sauvegarderCircuit(0, pop.getFittest());
            elitismeOffset = 1;
        }

        for (int i = elitismeOffset; i < nouvellePopulation.taillePopulation(); i++) {
            Circuit parent1 = selectionTournoi(pop);
            Circuit parent2 = selectionTournoi(pop);
            Circuit enfant = crossover(parent1, parent2);
            nouvellePopulation.sauvegarderCircuit(i, enfant);
        }

        for (int i = elitismeOffset; i < nouvellePopulation.taillePopulation(); i++) {
            muter(nouvellePopulation.getCircuit(i));
        }

        return nouvellePopulation;
    }

    public Circuit crossover(Circuit parent1, Circuit parent2) {
        Circuit enfant = new Circuit(gestionnaireCircuit);
        int startPos = (int) (random.nextDouble() * parent1.tailleCircuit());
        int endPos = (int) (random.nextDouble() * parent1.tailleCircuit());

        for (int i = 0; i < enfant.tailleCircuit(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                enfant.setVille(i, parent1.getVille(i));
            } else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    enfant.setVille(i, parent1.getVille(i));
                }
            }
        }

        for (int i = 0; i < parent2.tailleCircuit(); i++) {
            if (!enfant.contientVille(parent2.getVille(i))) {
                for (int j = 0; j < enfant.tailleCircuit(); j++) {
                    if (enfant.getVille(j) == null) {
                        enfant.setVille(j, parent2.getVille(i));
                        break;
                    }
                }
            }
        }

        return enfant;
    }

    public void muter(Circuit circuit) {
        for (int i = 0; i < circuit.tailleCircuit(); i++) {
            if (random.nextDouble() < tauxMutation) {
                int j = (int) (circuit.tailleCircuit() * random.nextDouble());

                Ville ville1 = circuit.getVille(i);
                Ville ville2 = circuit.getVille(j);

                circuit.setVille(j, ville1);
                circuit.setVille(i, ville2);
            }
        }
    }

    public Circuit selectionTournoi(Population pop) {
        Population tournoi = new Population(gestionnaireCircuit, tailleTournoi, false);
        for (int i = 0; i < tailleTournoi; i++) {
            int randomId = (int) (random.nextDouble() * pop.taillePopulation());
            tournoi.sauvegarderCircuit(i, pop.getCircuit(randomId));
        }
        return tournoi.getFittest();
    }
}
