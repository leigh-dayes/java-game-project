package GamePlay;

import java.util.Random;
import Villagers.*;
import java.util.List;
import java.util.ArrayList;


public class Fight {
    
    private Player user;
    private List<Villager> friendlies = new ArrayList<Villager>();
    private List<Villager> enemies = new ArrayList<Villager>();
    //private boolean fightOver = false;

    public Fight(Player user) {
        this.user = user;
    }
    public void setFriendlies(List<Villager> friendlies) {
        this.friendlies = friendlies;
    }
    public void setEnemies(List<Villager> enemies) {
        this.enemies = enemies;
    }
    public void fighting() {
        // fight loop
        boolean myTurn = true;
        Random rand = new Random();
        boolean fightOver = false;
        //int timer = 1;
        while(!fightOver) {
            //check to see if any enemies
            if (enemies.isEmpty()) {
                System.out.println("The fight is over, You won!");
                fightOver = true;
            }
            else {
                // a random int 0 - 3, 0 = miss, 1 and 2 = standard hit, 3 = critical hit
                int accuracy = rand.nextInt(4);
                myTurn = !myTurn;
                int eSize = enemies.size();
                // player turn
                if(myTurn) {
                    Villager target = enemies.get(rand.nextInt(eSize));
                    fAttack(rand.nextInt(2), target, accuracy);
                }
                // enemy turn
                else {
                    Villager attacker = enemies.get(rand.nextInt(eSize));
                    int attackDamage = accuracy * attacker.getAttackPower();
                    if (accuracy == 0) {
                        System.out.println(attacker.getFirstName() + " has swung wildly and missed!");
                    }
                    // see if they attack you or you allies
                    int defender = 0;
                    // see if we have allies
                    if (friendlies.size() > 0) {
                        defender = rand.nextInt(friendlies.size());
                    }
                    if (defender == 0) {
                        int defence = user.getArmourDefence();
                        int damage = attackDamage - defence;
                        
                        if (damage > 0) {
                            user.decrementHealth(damage);
                            System.out.println(attacker.getFirstName() + " strikes you causing " + damage + " damage");
                            System.out.println("Your health is down to " + user.getHealth() + "%");
                            if(!user.isAlive()) {
                                System.out.println("You have died in battle, RIP.");
                                fightOver = true;
                            }
                        }
                        else if (damage <= 0 && accuracy == 0) {
                            System.out.println("You have blocked the attack!");
                        }

                    }
                    // an ally is defending the attack
                    else {
                        Villager allyDefender = friendlies.get(defender);
                        int defence = allyDefender.getArmourDefence();
                        int damage = attackDamage - defence;
                        if (damage > 0) {
                            allyDefender.decrementHealth(damage);
                            System.out.println(attacker.getFirstName() + " strikes your ally " + allyDefender.getFirstName() + " causing " + damage + " damage");
                            System.out.println(allyDefender.getFirstName() + " health is down to " + allyDefender.getHealth() + "%");
                            if(!allyDefender.isAlive()) {
                                System.out.println(allyDefender.getFirstName() + " has died in battle, his sacrifice will not be in vein!");
                                friendlies.remove(allyDefender);
                            }
                        }
                        else if (damage <= 0 && accuracy == 0) {
                            System.out.println("Your ally " + allyDefender.getFirstName() + " has blocked the attack!");
                        }
                    }

                }
            }
            System.out.println(" ");
        }
    }
    /**
     * A function that either attacks using the player or one of the friendlies
     */
    public void fAttack(int choice, Villager target, int accuracy) {
        Villager attacker;
        String attackMessage;
        if (choice == 0 || friendlies.isEmpty()) {
            attacker = user;
            attackMessage= "You";
        }
        // friendly
        else {
            Random rand = new Random();
            attacker = friendlies.get(rand.nextInt(friendlies.size()));
            attackMessage = "Your ally, " + attacker.getFirstName();
        }
        if (accuracy == 0) {
            System.out.println(attackMessage + " have swung wildly and missed!");
        }
        int playerAttack = accuracy * attacker.getAttackPower();
        int targetDefence = target.getArmourDefence();
        int damage = playerAttack - targetDefence;
        if (damage > 0) {
            target.decrementHealth(damage);
            System.out.println(attackMessage + " hit your opponent " + target.getFirstName() + ", causing " + damage + " damage!");
            System.out.println(target.getFirstName() + " is down to " + target.getHealth() + "% health");
            if (!target.isAlive()) {
                System.out.println(target.getFirstName() + " has been killed in battle.");
                enemies.remove(target);
            }
        }
        else if (damage <= 0 && accuracy == 0) {
            System.out.println("Your opponent has blocked the attack!");
        }
    }
    /**
     * A function to check if the user died during battle
     */
    public boolean userAlive() {
        return user.isAlive();
    }
}
