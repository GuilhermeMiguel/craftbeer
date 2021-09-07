INSERT INTO `category` (`id_category`, `name`, `description`, `enabled`) VALUES
(1,	'Pilsen', 'Apresenta aroma e sabor acentuados pelo lúpulo, além da cor dourada.', 1),
(2,	'Lager', 'Apresenta baixa fermentação, com ação do levedo em baixa temperatura.', 1),
(3,	'IPA', 'Apresenta sabor bem amargo, além do aroma frutado.', 1),
(4,	'Tripel', 'Apresenta cor clara, sabor amargo cítrico e aroma frutado. É bem carbonatada, o que lhe confere uma espuma bastante cremosa. ', 1),
(5,	'Porter', 'Apresenta cor escura advinda da torra acentuada do malte, que também lhe proporciona aroma e sabor de café ou chocolate amargo.', 1),
(6,	'Stout', 'Apresenta aroma torrado e sabor amargo, que lembra café ou chocolate. Sua espuma é cremosa e pode ser clara ou amarronzada. É mais forte e escura que a Porter.', 1),
(7,	'Weizenbier', 'Apresenta cor clara e opaca, com sabor e aroma frutados, lembrando banana e cravo. A bebida é refrescante.', 1);

INSERT INTO `beer` (`id_beer`, `name`, `milliliters`, `alcohol_content`, `price`, `category_id_category`) VALUES
(1,	'Dado Bier Session', 350.0, '4,1%', 7.90, 3),
(2,	'Roleta Russa Easy', 355.0, '4,8%', 8.90, 3),
(3,	'St. Patricks Coffee', 500.0, '6%', 14.90, 3),
(4,	'St. Patricks Imperial Pils', 500.0, '8%', 14.90, 1),
(5,	'Comendador Pilsen', 500.0, '4,2%', 14.90, 1),
(6,	'Bamberg Micalateia Pilsen', 500.0, '4,8%', 14.90, 1),
(7,	'St. Patricks Imperial Pils', 500.0, '6%', 14.90, 1),
(8,	'Heilige Bohemian Pilsener', 500.0, '5,1%', 17.90, 1),
(9,	'Goat Milk Stout', 355.0, '4,8%', 21.90, 6),
(10, 'Dry Stout Exclusive', 350.0, '4,1%', 21.90, 6),
(11, 'Fullers Imperial Stout', 500.0, '10,7%', 199.99, 6),
(12, 'Hop Cashmere', 330.0, '10%', 46.9, 4),
(13, 'Leopoldina Belgian Tripel', 750.0, '9%', 62.90, 4),
(14, 'Bodebrown Tripel Montfort', 750.0, '10%', 64.90, 4),
(15, 'Straffe Hendrik Wild 2017', 330.0, '9%', 49.90, 4);


