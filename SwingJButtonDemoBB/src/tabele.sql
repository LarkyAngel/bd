CREATE TABLE Konkurs (
	id_kon INTEGER PRIMARY KEY,
	data DATE NOT NULL,
	termin_zgloszen DATE NOT NULL,
	nazwa_org VARCHAR(40) NOT NULL,
	miejsce VARCHAR(60) NOT NULL	
);

CREATE TABLE Seria (
	id_kon INTEGER NOT NULL PRIMARY KEY REFERENCES Konkurs,
	nr_serii INTEGER PRIMARY KEY,
	numer INTEGER NOT NULL
);

CREATE TABLE Reprezentacja (
	nazwa_rep VARCHAR(40) PRIMARY KEY,
	kwota_bazowa INTEGER NOT NULL
);

CREATE TABLE Zawodnik (
	id_zaw INTEGER PRIMARY KEY,
	nazwa_rep VARCHAR(40) NOT NULL REFERENCES Reprezentacja,
	imie VARCHAR(20) NOT NULL,
	nazwisko VARCHAR(40) NOT NULL
);

CREATE TABLE Zgloszenie (
	id_zaw INTEGER NOT NULL REFERENCES Zawodnik,
	id_kon INTEGER NOT NULL REFERENCES Konkurs,
	PRIMARY KEY (id_zaw, id_kon)
);

CREATE TABLE Wynik (
	id_zaw INTEGER NOT NULL,
	id_kon INTEGER NOT NULL,
	FOREIGN KEY (id_zaw, id_kon) REFERENCES Zgloszenie(id_zaw, id_kon),
	nr_serii INTEGER NOT NULL REFERENCES Seria,
	PRIMARY KEY (id_zaw, id_kon, nr_serii),
	punkty DECIMAL NOT NULL,
	odleglosc DECIMAL NOT NULL
);

CREATE OR REPLACE FUNCTION f() RETURNS TRIGGER AS $$
DECLARE
	curr RECORD;
BEGIN
	FOR curr IN (SELECT COUNT(*) AS cnt, o.nazwa_rep, o.kwota_bazowa FROM
	((Zgloszenie k NATURAL JOIN Zawodnik l) m
	NATURAL JOIN Reprezentacja n) o
	GROUP BY o.id_kon, o.nazwa_rep, o.kwota_bazowa)
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
	SELECT n.cnt FROM (SELECT COUNT(*) AS cnt, m.id_kon FROM (Zgloszenie l
	NATURAL JOIN Konkurs k) m
	GROUP BY m.id_kon) n WHERE n.id_kon = _id_kon
	INTO sum;
	IF sum IS NULL THEN RETURN FALSE;
	END IF;
	RETURN sum > 50;
END;
$$ LANGUAGE plpgsql;