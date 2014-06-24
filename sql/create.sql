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
  `dozent` boolean NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `email`, `forename`, `surname`, `password`, `birthdate`, `dozent`) VALUES
(1, 'marcelh89@googlemail.com', 'Marcel', 'Hinderlich', '123', '1989-11-10', false);

-- Vorlesung Tabelle
drop table if exists Vorlesung
CREATE TABLE IF NOT EXISTS Vorlesung (
  vid     		integer  	not null auto_increment
 ,name  		varchar(20) not null
 ,inhalt  		varchar(255)
 ,anmeldecode   varchar(50)
 ,PRIMARY KEY(vid) 
);
INSERT INTO `Vorlesung` (`name`, `inhalt`) VALUES ('Mobile Informationssysteme','Alles rund um Marcel Hinderlich'),('Sonnenblumen','�l'); 


CREATE TABLE IF NOT EXISTS VorlesungWochentag (
  vwid     		integer  	not null auto_increment
 ,vid			integer 	not null
 ,wochentag		varchar(20) not null
 ,begin  		varchar(20) not null
 ,ende  		varchar(20) not null
 ,raumnr 		varchar(50) not null
 ,dozentid		integer		not null
 ,PRIMARY KEY(vwid)
 ,FOREIGN KEY(vid) REFERENCES Vorlesung(vid) on delete no action on update no action
 ,FOREIGN KEY(dozentid) REFERENCES user(id) on delete no action on update no action
);
INSERT INTO `dassystem`.`vorlesungwochentag` (`vwid`, `vid`, `wochentag`, `begin`, `ende`, `raumnr`, `dozentid`) VALUES (NULL, '1', 'Montag', '9:00', '10:00', 'A34', '2');

CREATE TABLE IF NOT EXISTS VorlesungTeilnehmer (
  vtid     		integer  	not null auto_increment
  ,vid			integer 	not null
  ,uid			integer 	not null
  ,datum		date		not null
  ,PRIMARY KEY (`vtid`)
  ,FOREIGN KEY(vid) REFERENCES Vorlesung(vid) on delete no action on update no action
  ,FOREIGN KEY(uid) REFERENCES user(id) on delete no action on update no action
);
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;