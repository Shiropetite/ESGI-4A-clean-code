INSERT INTO ref_hero (id, name, max_health_points, power_points, armor_points, rarity)
VALUES
    (1, "Tank", 1000, 100, 20, "Commun"),
    (2, "Tank", 1100, 110, 21, "Rare"),
    (3, "Tank", 1200, 120, 22, "Légendaire"),
    (4, "Assassin", 800, 200, 5, "Commun"),
    (5, "Assassin", 880, 220, 5.5, "Rare"),
    (6, "Assassin", 960, 240, 6, "Légendaire"),
    (7, "Mage", 700, 150, 10, "Commun"),
    (8, "Mage", 770, 165, 11, "Rare"),
    (9, "Mage", 840, 180, 12, "Légendaire");

INSERT INTO hero_bonus (id, bonus, strong, weak)
VALUES
    (1, 20, "Tank", "Mage"),
    (2, 30, "Assassin", "Tank"),
    (3, 25, "Mage", "Assassin");