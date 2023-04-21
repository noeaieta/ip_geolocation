CREATE TABLE public.currencies (
    name text,
    code text NOT NULL,
	PRIMARY KEY (code)
);

CREATE TABLE public.countries (
    name text,
    timezones text[],
    code_iso text NOT NULL,
    latitude double precision,
    longitude double precision,
    languages text[],
    currency text,
	PRIMARY KEY (code_iso),
	CONSTRAINT fk_currency
      FOREIGN KEY(currency) 
	  REFERENCES currencies(code)
);

CREATE TABLE public.distances (
    id integer NOT NULL,
    to_buenos_aires double precision NOT NULL,
    country text
	PRIMARY KEY (id)
	CONSTRAINT fk_country
      FOREIGN KEY(country) 
	  REFERENCES countries(code_iso)
);

CREATE TABLE public.geolocatedip (
    ip text NOT NULL,
    country text
	PRIMARY KEY (ip)
	CONSTRAINT fk_country
      FOREIGN KEY(country) 
	  REFERENCES countries(code_iso)
);