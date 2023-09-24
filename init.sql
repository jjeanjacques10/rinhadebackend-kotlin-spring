USE persondb;

DROP TABLE IF EXISTS people;
CREATE TABLE people (
  id binary(16) NOT NULL,
  name varchar(100) DEFAULT NULL,
  nick_name varchar(32) UNIQUE NOT NULL,
  birth_day date DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_5q139wl7nav7gcpn2uovyfl8r (nick_name)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS people_seq;
CREATE TABLE people_seq (
  next_val bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS person_stack;
CREATE TABLE person_stack (
  person_id binary(16) NOT NULL,
  stack varchar(255) DEFAULT NULL,
  KEY FKg82a81ikptx8drg7f0xr00qtp (person_id),
  CONSTRAINT FKg82a81ikptx8drg7f0xr00qtp FOREIGN KEY (person_id) REFERENCES people (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;