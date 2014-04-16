--
-- Tabellenstruktur für Tabelle `user`
--

--
-- create database dassystem 
-- and insert following code
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` text NOT NULL,
  `forename` text NOT NULL,
  `surname` text NOT NULL,
  `password` text NOT NULL,
  `birthdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `email`, `forename`, `surname`, `password`, `birthdate`) VALUES
(1, 'marcelh89@googlemail.com', 'Marcel', 'Hinderlich', '123', '1989-11-10');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;