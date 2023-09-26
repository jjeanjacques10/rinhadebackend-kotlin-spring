USE persondb;

DROP TABLE IF EXISTS people;
CREATE TABLE people (
  id binary(16) NOT NULL,
  name varchar(100) DEFAULT NULL,
  nick_name varchar(32) UNIQUE NOT NULL,
  birth_day date DEFAULT NULL,
  stack varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_5q139wl7nav7gcpn2uovyfl8r (nick_name)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
