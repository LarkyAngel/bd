DROP TABLE IF EXISTS Reprezentacja CASCADE;


CREATE TABLE Reprezentacja (
	id_rep SERIAL PRIMARY KEY,
	nazwa VARCHAR(40) UNIQUE NOT NULL
);


DROP TABLE IF EXISTS Konkurs CASCADE;

CREATE TABLE Konkurs (
	id_kon SERIAL PRIMARY KEY,
	data DATE NOT NULL,
	termin_zgloszen DATE NOT NULL,
	id_org INTEGER NOT NULL REFERENCES Reprezentacja,
	miejsce VARCHAR(60) NOT NULL,
	status INTEGER NOT NULL DEFAULT 1 
	/*1 - nowy, 2 - w trakcie, 3 - zakonczony */
);


DROP TABLE IF EXISTS REPREZENTACJA_KONKURS CASCADE;

CREATE TABLE REPREZENTACJA_KONKURS (
	id_rep INTEGER REFERENCES Reprezentacja,
	id_kon INTEGER REFERENCES Konkurs,
	kwota_bazowa INTEGER NOT NULL,
	PRIMARY KEY(id_rep, id_kon)
);


DROP TABLE IF EXISTS Seria CASCADE;

CREATE TABLE Seria (
	id_serii SERIAL PRIMARY KEY,
	id_kon INTEGER NOT NULL REFERENCES Konkurs,
	numer INTEGER NOT NULL
);


DROP TABLE IF EXISTS Zawodnik CASCADE;

CREATE TABLE Zawodnik (
	id_zaw SERIAL PRIMARY KEY,
	id_rep INTEGER NOT NULL REFERENCES Reprezentacja,
	imie VARCHAR(20) NOT NULL,
	nazwisko VARCHAR(40) NOT NULL
);


DROP TABLE IF EXISTS Zgloszenie CASCADE;

CREATE TABLE Zgloszenie (
	id_zgl SERIAL PRIMARY KEY,
	id_zaw INTEGER NOT NULL REFERENCES Zawodnik,
	id_kon INTEGER NOT NULL REFERENCES Konkurs,
	wynik_zaw DECIMAL,
	miejsce_zaw INTEGER
);

DROP TABLE IF EXISTS Wynik CASCADE;

CREATE TABLE Wynik (
	id_wyn SERIAL PRIMARY KEY,
	id_serii INTEGER REFERENCES Seria,
	id_zgl INTEGER REFERENCES Zgloszenie,
	ocena_zb DECIMAL NOT NULL,
	dlugosc DECIMAL NOT NULL,
	czy_dsq BOOLEAN NOT NULL,
	punkty DECIMAL NOT NULL
);

CREATE OR REPLACE FUNCTION f() RETURNS TRIGGER AS $$
DECLARE
	curr RECORD;
BEGIN
	FOR curr IN (SELECT COUNT(*) AS cnt, q.nazwa, q.kwota_bazowa FROM
        (((Zgloszenie k NATURAL JOIN Zawodnik l) m
        NATURAL JOIN Reprezentacja n) o NATURAL JOIN REPREZENTACJA_KONKURS p) q
        GROUP BY q.id_kon, q.nazwa, q.kwota_bazowa)
	LOOP
		IF curr.cnt > curr.kwota_bazowa THEN
			RAISE EXCEPTION
			'Liczba zgłoszeń zawodników danej reprezentacji przekracza jej kwotę bazową!';
		END IF;
	END LOOP;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER t AFTER INSERT OR UPDATE OR DELETE ON Zgloszenie
EXECUTE PROCEDURE f();

CREATE OR REPLACE FUNCTION rozgrywana_jest_kwalifikacyjna(_id_kon INTEGER) RETURNS BOOLEAN AS $$
DECLARE
	sum INTEGER;
BEGIN
	SELECT n.cnt FROM (SELECT COUNT(*) AS cnt, id_kon FROM Zgloszenie
	GROUP BY id_kon) n WHERE n.id_kon = _id_kon
	INTO sum;
	IF sum IS NULL THEN RETURN FALSE;
	END IF;
	RETURN sum > 50;
END;
$$ LANGUAGE plpgsql;
