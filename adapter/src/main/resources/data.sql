insert into hero_ref (name, max_health_points, power_points, armor_points, rarity)
values
    ("Tank", 1000, 100, 20, "Commun"),
    ("Tank", 1100, 110, 21, "Rare"),
    ("Tank", 1200, 120, 22, "Légendaire"),
    ("Assassin", 800, 200, 5, "Commun"),
    ("Assassin", 880, 220, 5.5, "Rare"),
    ("Assassin", 960, 240, 6, "Légendaire"),
    ("Mage", 700, 150, 10, "Commun"),
    ("Mage", 770, 165, 11, "Rare"),
    ("Mage", 840, 180, 12, "Légendaire");

insert into hero_bonus (bonus, strong, weak)
values
    (20, "Tank", "Mage"),
    (30, "Assassin", "Tank"),
    (25, "Mage", "Assassin");

insert into hero_pack (name, requiredTokens, numberOfCards, commonChance, rareChance, legendaryChance)
values
    ("Argent", 1, 3, 0.75, 0.2, 0.05),
    ("Diamant", 2, 5, 0.5, 0.35, 0.15);