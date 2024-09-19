

public class DiningCourt {
    
    private Entree firstEntree;
    private Entree secondEntree;
    private Entree thirdEntree;

    public DiningCourt(Entree firstEntree, Entree secondEntree, Entree thirdEntree) {
        this.firstEntree = firstEntree;
        this.secondEntree = secondEntree;
        this.thirdEntree = thirdEntree;
    }

    public DiningCourt(DiningCourt diningCourt) {
        this.firstEntree = diningCourt.getFirstEntree();
        this.secondEntree = diningCourt.getSecondEntree();
        this.thirdEntree = diningCourt.getThirdEntree();
    }

    public DiningCourt() {
        firstEntree = null;
        secondEntree = null;
        thirdEntree = null;
    }

    public Entree getFirstEntree() {
        return firstEntree;
    }

    public Entree getSecondEntree() {
        return secondEntree;
    }    
    
    public Entree getThirdEntree() {
        return thirdEntree;
    } 

    public void setFirstEntree(Entree firstEntree) {
        this.firstEntree = firstEntree;
    }

    public void setSecondEntree(Entree secondEntree) {
        this.secondEntree = secondEntree;
    }

    public void setThirdEntree(Entree thirdEntree) {
        this.thirdEntree = thirdEntree;
    }

    public Entree getLowestCalorieEntree() {
        if (firstEntree.getCalories() < secondEntree.getCalories()) {
            if (firstEntree.getCalories() < thirdEntree.getCalories()) {
                return firstEntree;
            } else if (thirdEntree.getCalories() < firstEntree.getCalories()) {
                return thirdEntree;
            }
        } else if (secondEntree.getCalories() < firstEntree.getCalories()) {
            if (secondEntree.getCalories() < thirdEntree.getCalories()) {
                return secondEntree;
            } else if (thirdEntree.getCalories() < secondEntree.getCalories()) {
                return thirdEntree;
            }
        }
        return thirdEntree;
    }

    public Entree getHeighestCalorieEntree() {
        if (firstEntree.getCalories() > secondEntree.getCalories()) {
            if (firstEntree.getCalories() > thirdEntree.getCalories()) {
                return firstEntree;
            } else if (thirdEntree.getCalories() > firstEntree.getCalories()) {
                return thirdEntree;
            }
        } else if (secondEntree.getCalories() > firstEntree.getCalories()) {
            if (secondEntree.getCalories() > thirdEntree.getCalories()) {
                return secondEntree;
            } else if (thirdEntree.getCalories() > secondEntree.getCalories()) {
                return thirdEntree;
            }
        }
        return thirdEntree;
    }

    public void printVegetarianEntrees() {
        int i = 0;
        if (firstEntree.isVegetarian()) {
            System.out.printf("Option %d: %s\n", i, firstEntree.getName());
            i++;
        }
        if (secondEntree.isVegetarian()) {
            System.out.printf("Option %d: %s\n", i, secondEntree.getName());
            i++;
        }
        if (thirdEntree.isVegetarian()) {
            System.out.printf("Option %d: %s\n", i, thirdEntree.getName());
            i++;
        }
        if (!firstEntree.isVegetarian() && !secondEntree.isVegetarian() && !thirdEntree.isVegetarian()) {
            System.out.println("No vegetarian options are available :(");
        }
    }

    public void printVeganEntrees() {
        int i = 0;
        if (firstEntree.isVegan()) {
            System.out.printf("Option %d: %s\n", i, firstEntree.getName());
            i++;
        }
        if (secondEntree.isVegan()) {
            System.out.printf("Option %d: %s\n", i, secondEntree.getName());
            i++;
        }
        if (thirdEntree.isVegan()) {
            System.out.printf("Option %d: %s\n", i, thirdEntree.getName());
            i++;
        }

        if (!firstEntree.isVegan() && !secondEntree.isVegan() && !thirdEntree.isVegan()) {
            System.out.println("No vegan options are available :(");
        }
    }

    public String toString() {
        return String.format("DiningCourt<firstEntree=%s,secondEntree=%s,thirdEntree%s>", firstEntree, secondEntree, thirdEntree);
    }


}