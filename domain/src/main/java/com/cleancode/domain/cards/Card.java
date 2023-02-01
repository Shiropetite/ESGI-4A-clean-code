package com.cleancode.domain.cards;


public abstract class Card {

    private static final int MAX_LEVEL = 100;

    private String name;
    private int maxHealthPoints;
    private int healthPoints;
    private int experiencePoints;
    private int powerPoints;
    private int armorPoints;
    private CardSpecialities speciality;
    private CardRarities rarity;
    private int level;

    public Card() {};

    public Card(
            String name,
            int maxHealthPoints,
            int experiencePoints,
            int powerPoints,
            int armorPoints,
            CardSpecialities speciality,
            CardRarities rarity
    ) {
        this.name = name;
        this.rarity = rarity;
        this.maxHealthPoints = maxHealthPoints + addRarityPercent(maxHealthPoints);
        this.powerPoints = powerPoints + addRarityPercent(powerPoints);
        this.armorPoints = armorPoints + addRarityPercent(armorPoints);
        this.healthPoints = this.maxHealthPoints;
        this.experiencePoints = experiencePoints;
        this.speciality = speciality;
        this.level = 1;
    }

    public Card(
            String name,
            int maxHealthPoints,
            int experiencePoints,
            int powerPoints,
            int armorPoints,
            CardSpecialities speciality,
            CardRarities rarity,
            int level
    ) {
        this.name = name;
        this.rarity = rarity;
        this.maxHealthPoints = maxHealthPoints + addRarityPercent(maxHealthPoints);
        this.powerPoints = powerPoints + addRarityPercent(powerPoints);
        this.armorPoints = armorPoints + addRarityPercent(armorPoints);
        this.healthPoints = this.maxHealthPoints;
        this.experiencePoints = experiencePoints;
        this.speciality = speciality;
        this.level = level;
    }

    private int addRarityPercent(int valueToIncrease) {
        return (int) Math.round(valueToIncrease * this.rarity.getPercent());
    }

    public void nextLevel() {
        if (this.level == MAX_LEVEL) { throw new RuntimeException("Le niveau maximal est déjà atteint"); }
        this.experiencePoints = 0;
        this.level++;
        this.maxHealthPoints += this.maxHealthPoints * 0.1;
        this.healthPoints += this.healthPoints * 0.1;
        this.powerPoints += this.powerPoints * 0.1;
        this.armorPoints += this.armorPoints * 0.1;
    }

    public void attack(Card cardVictim) {
        cardVictim.healthPoints += this.armorPoints - this.powerPoints;
    }

    public int getHealthPoints() { return this.healthPoints; }

    public int getMaxHealthPoints() { return this.maxHealthPoints; }

    public int getPowerPoints() { return this.powerPoints; }

    public int getArmorPoints() { return this.armorPoints; }
}
